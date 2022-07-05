package crypto.trading.system.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crypto.trading.system.domain.Crypto;
import crypto.trading.system.domain.TradingTransaction;
import crypto.trading.system.domain.Wallet;
import crypto.trading.system.dto.JsonWrapperObject;
import crypto.trading.system.dto.TradingTransactionDTO;
import crypto.trading.system.service.CryptoService;
import crypto.trading.system.service.TradingTransactionService;
import crypto.trading.system.service.WalletService;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "http://localhost:4200")
public class TradingTransactionController
{
	@Autowired
	private TradingTransactionService tradingTransactionService;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private CryptoService cryptoService;

	Logger logger = LoggerFactory.getLogger(TradingTransactionController.class);
	
	/*
	 * API TO RETRIEVE ALL THE USERS TRADING HISTORY 
	 * 
	 * */
	@GetMapping("/getAllUserTradingTransactions")
	public ResponseEntity<JsonWrapperObject> getAllUserTradingTransactions(@RequestBody String input) throws Exception
	{
		JsonWrapperObject result = new JsonWrapperObject();
		JSONObject jsnobject = new JSONObject(input);
		String userIdString = jsnobject.getString("userId");
		Long userId = Long.parseLong(userIdString);
		List<TradingTransaction> tradingTransactionList = tradingTransactionService.findAllByUserId(userId);
		List<TradingTransactionDTO> dtoList = new ArrayList<>();
		for(TradingTransaction t : tradingTransactionList)
		{
			TradingTransactionDTO dto = new TradingTransactionDTO();
			dto.setGivenCryptoAmount(t.getGivenCryptoAmount());
			dto.setTakenCryptoAmount(t.getTakenCryptoAmount());
			dto.setGivenCryptoName(t.getGivenCryptoName());
			dto.setTakenCryptoName(t.getTakenCryptoName());
			dto.setTransactionTimestamp(t.getTransactionTimestamp());
			dtoList.add(dto);
		}
		
		result.setData(dtoList);
		result.setStatus("Success");
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
	/*
	 * API WHICH ALLOW USERS TO TRADE BASED ON THE LATEST BEST AGGREGATED PRICE 
	 * 
	 * */
	@PostMapping("/tradeCrypto")
	public ResponseEntity<JsonWrapperObject> tradeCrypto(@RequestBody String input) throws Exception
	{
		JsonWrapperObject result = new JsonWrapperObject();
		try
		{
			JSONObject jsnobject = new JSONObject(input);
			String givenCryptoName = jsnobject.getString("givenCryptoName");
			String takenCryptoName = jsnobject.getString("takenCryptoName");
			String amountToTradeString = jsnobject.getString("amountToTrade");
			String cryptoTradePairIdString = jsnobject.getString("cryptoTradePairId");
			String userIdString = jsnobject.getString("userId");
			logger.info(amountToTradeString);
			float amountToTrade = Float.parseFloat(amountToTradeString);
			logger.info(Float.toString(amountToTrade));
			Long userId = Long.parseLong(userIdString);
			Long cryptoTradePairId = Long.parseLong(cryptoTradePairIdString);
			
			//Get askPrice of target crypto from db
			Crypto takenCrypto = cryptoService.findById(cryptoTradePairId);
			float askPrice = takenCrypto.getAskPrice();
			
			//get amount of crypto to be traded for intended target amount of target crypto
			float givenCryptoAmount = tradingTransactionService.amountToTrade(amountToTrade, askPrice);
			
			//check if user wallet has enough to be traded
			Wallet givenCryptoWallet= walletService.findByCryptoNameAndUserId(givenCryptoName, userId);
			float walletBalance = givenCryptoWallet.getCryptoAmount();
			
			//if not enough then send failure status and rejection message to be display to front
			if(walletBalance < givenCryptoAmount || givenCryptoWallet == null)
			{
				result.setStatus("Failure");
				result.setDescription("Do not have enough amount to trade.");
				return ResponseEntity.status(HttpStatus.OK).body(result);
			}
			else//pass balance check, so deduct balance and carry out trade
			{
				//update existing wallet for givenCrypto
				float givenCryptoBalance = walletBalance - givenCryptoAmount;
				walletService.updateUserWallet(givenCryptoBalance, givenCryptoWallet.getWalletId());
				
				//create new wallet or update existing wallet for taken crypto
				Wallet takenCryptoWallet = walletService.findByCryptoNameAndUserId(takenCryptoName, userId);
				if(takenCryptoWallet == null)
				{
					takenCryptoWallet = new Wallet();
					takenCryptoWallet.setCryptoAmount(amountToTrade);
					takenCryptoWallet.setCryptoName(takenCryptoName);
					takenCryptoWallet.setUserId(userId);
					walletService.saveWallet(takenCryptoWallet);
				}
				else
				{
					walletService.updateUserWallet(amountToTrade, takenCryptoWallet.getWalletId());
				}
				
				//create transaction record of tracking
				TradingTransaction newTransaction = new TradingTransaction();
				newTransaction.setGivenCryptoAmount(givenCryptoAmount);
				newTransaction.setGivenCryptoName(givenCryptoName);
				newTransaction.setTakenCryptoAmount(amountToTrade);
				newTransaction.setTakenCryptoName(takenCryptoName);
				newTransaction.setUserId(userId);
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				newTransaction.setTransactionTimestamp(timestamp);
				newTransaction.setSource(takenCrypto.getAskSource());
				tradingTransactionService.saveTransaction(newTransaction);
			}	
		}
		catch(Exception e)
		{
			logger.error("Encountered error when processing trade, error due to {}", e.getMessage());
		}
		result.setData("");
		result.setStatus("Success");
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
	
}

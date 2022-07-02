package crypto.trading.system.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crypto.trading.system.domain.TradingTransaction;
import crypto.trading.system.domain.Wallet;
import crypto.trading.system.dto.JsonWrapperObject;
import crypto.trading.system.dto.TradingTransactionDTO;
import crypto.trading.system.service.TradingTransactionService;

@RestController
@RequestMapping("/transaction")
@CrossOrigin(origins = "http://localhost:4200")
public class TradingTransactionController
{
	@Autowired
	private TradingTransactionService tradingTransactionService;

	Logger logger = LoggerFactory.getLogger(TradingTransactionController.class);
	
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
}

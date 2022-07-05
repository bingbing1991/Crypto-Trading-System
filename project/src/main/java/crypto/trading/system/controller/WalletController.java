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

import crypto.trading.system.domain.Wallet;
import crypto.trading.system.dto.JsonWrapperObject;
import crypto.trading.system.dto.WalletDTO;
import crypto.trading.system.service.WalletService;

@RestController
@RequestMapping("/wallet")
@CrossOrigin(origins = "http://localhost:4200")
public class WalletController
{
	@Autowired
	private WalletService walletService;

	Logger logger = LoggerFactory.getLogger(WalletController.class);
	
	/*
	 * API TO RETRIEVE ALL THE USERS CRYPTOCURRENCIES WALLET BALANCE
	 * 
	 * */
	@GetMapping("/getAllUserWallet")
	public ResponseEntity<JsonWrapperObject> getAllUserWallet(@RequestBody String input) throws Exception
	{
		JsonWrapperObject result = new JsonWrapperObject();
		JSONObject jsnobject = new JSONObject(input);
		String userIdString = jsnobject.getString("userId");
		Long userId = Long.parseLong(userIdString);
		List<Wallet> walletList = walletService.findAllByUserId(userId);
		List<WalletDTO> dtoList = new ArrayList<>();
		for(Wallet w : walletList)
		{
			WalletDTO dto = new WalletDTO();
			dto.setCryptoAmount(w.getCryptoAmount());
			dto.setCryptoName(w.getCryptoName());
			dtoList.add(dto);
		}
		
		result.setData(dtoList);
		result.setStatus("Success");
		
		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}

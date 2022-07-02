package crypto.trading.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import crypto.trading.system.domain.Crypto;
//import crypto.trading.system.dto.CryptoDTO;
//import crypto.trading.system.dto.JsonWrapperObject;
import crypto.trading.system.service.CryptoService;

//import java.util.Arrays;
//import java.util.List;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/crypto")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class CryptoController
{
	@Autowired
	private CryptoService cryptoService;
	
//	private final String url = "https://api.binance.com/api/v3/ticker/bookTicker";
//	
//	@Scheduled(fixedRate = 10000)
//	public void updateCryto() throws Exception
//	{
//		RestTemplate restTemplate = new RestTemplate();
//		String input = restTemplate.getForObject(url, String.class);
//		ObjectMapper mapper = new ObjectMapper();		
//		List<CryptoDTO> inputList = Arrays.asList(mapper.readValue(input, CryptoDTO[].class));
//		for(CryptoDTO dto : inputList)
//		{
//			String name = dto.getSymbol();
//			float bidPrice = Float.parseFloat(dto.getBidPrice());
//			float askPrice = Float.parseFloat(dto.getAskPrice());
//			if(name != null && !"".equals(name))
//			{
//				//check if this crypto already exist in the DB
//				Crypto existingCrypto  = cryptoService.findByName(name);
//				if(existingCrypto == null)
//				{
//					existingCrypto = new Crypto();
//					existingCrypto.setAskPrice(askPrice);
//					existingCrypto.setBidPrice(bidPrice);
//					existingCrypto.setName(name);
//					cryptoService.save(existingCrypto);
//				}
//				cryptoService.updateCrypto(bidPrice, askPrice, existingCrypto.getCryptoId());
//			}
//		}
//	}
}

package crypto.trading.system.project;

import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import crypto.trading.system.domain.Crypto;
import crypto.trading.system.dto.Crypto1DTO;
import crypto.trading.system.dto.Crypto2DTO;
import crypto.trading.system.service.CryptoService;

@SpringBootApplication
@ComponentScan({"crypto.trading.system.service,crypto.trading.system.controller"})
@EntityScan("crypto.trading.system.domain")
@EnableJpaRepositories("crypto.trading.system.repository")
@EnableScheduling
public class ProjectApplication
{
	@Autowired
	private CryptoService cryptoService;
	
	private final String url1 = "https://api.binance.com/api/v3/ticker/bookTicker";
	private final String url2 = "https://api.huobi.pro/market/tickers";
	
	Logger logger = LoggerFactory.getLogger(ProjectApplication.class);
	
	public static void main(String[] args)
	{
        SpringApplication.run(ProjectApplication.class, args);
	}
	
	
	/*
	 * INTERVAL SCHEDULAR API TO RETRIEVE FROM SOURCES 
	 * AND UPDATE DB WITH CRYPTO TRADING PAIRSLATEST AGGREGATED PRICE
	 * 
	 * */
	@Scheduled(fixedRate = 10000)
	public void updateCryto() throws Exception
	{
		logger.info("start scheduling update crypto price!");
		RestTemplate restTemplate = new RestTemplate();
		String input1 = restTemplate.getForObject(url1, String.class);
		String input2 = restTemplate.getForObject(url2, String.class);
		JSONObject jsnobject = new JSONObject(input2);
		JSONArray jsonArray = jsnobject.getJSONArray("data");  
		String input3 = jsonArray.toString();
		ObjectMapper mapper = new ObjectMapper();		
		List<Crypto1DTO> inputList1 = Arrays.asList(mapper.readValue(input1, Crypto1DTO[].class));
		List<Crypto2DTO> inputList2 = Arrays.asList(mapper.readValue(input3, Crypto2DTO[].class));
		for(Crypto1DTO dto : inputList1)
		{
//			logger.info("Name of Cryto is: {}",dto.getSymbol());
			String name = dto.getSymbol().toUpperCase();
			float bidPrice = Float.parseFloat(dto.getBidPrice());
			float askPrice = Float.parseFloat(dto.getAskPrice());
			if(name != null && !"".equals(name))
			{
				//check if this crypto already exist in the DB
				Crypto existingCrypto  = cryptoService.findByName(name);
				if(existingCrypto == null)//insert new crypto records
				{
					existingCrypto = new Crypto();
					existingCrypto.setAskPrice(askPrice);
					existingCrypto.setBidPrice(bidPrice);
					existingCrypto.setName(name);
					existingCrypto.setBidSource(url1);
					existingCrypto.setAskSource(url1);
					cryptoService.save(existingCrypto);
				}
				else//updating existing crypto records
				{
					existingCrypto.setBidSource(url1);
					existingCrypto.setAskSource(url1);
					cryptoService.updateBidDetailAndAskDetailById(bidPrice, askPrice, url1, url1, existingCrypto.getCryptoId());
				}
			}
		}
		for(Crypto2DTO dto : inputList2)
		{
//			logger.info("Name of Cryto is: {}",dto.getSymbol());
			String name = dto.getSymbol().toUpperCase();
			float bidPrice = Float.parseFloat(dto.getBid());
			float askPrice = Float.parseFloat(dto.getAsk());
			if(name != null && !"".equals(name))
			{
				//check if this crypto already exist in the DB
				Crypto existingCrypto  = cryptoService.findByName(name);
				if(existingCrypto == null)//insert new crypto records
				{
					existingCrypto = new Crypto();
					existingCrypto.setAskPrice(askPrice);
					existingCrypto.setBidPrice(bidPrice);
					existingCrypto.setName(name);
					existingCrypto.setBidSource(url2);
					existingCrypto.setAskSource(url2);
					
					cryptoService.save(existingCrypto);
				}
				else//updating existing crypto records
				{
					if(existingCrypto.getAskPrice() < askPrice && existingCrypto.getBidPrice() < bidPrice)
					{
						cryptoService.updateBidDetailAndAskDetailById(bidPrice, askPrice, url2, url2, existingCrypto.getCryptoId());
					}
					else if(existingCrypto.getAskPrice() < askPrice)
					{
						cryptoService.updateAskDetailById(askPrice, url2, existingCrypto.getCryptoId());
					}
					else if(existingCrypto.getBidPrice() < bidPrice)
					{
						cryptoService.updateBidDetailById(bidPrice, url2, existingCrypto.getCryptoId());
					}
				}
			}
		}
		logger.info("end of scheduling update crypto price!");
	}
	

}

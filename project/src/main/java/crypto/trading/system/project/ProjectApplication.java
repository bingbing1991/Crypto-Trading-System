package crypto.trading.system.project;

import java.util.Arrays;
import java.util.List;

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
import crypto.trading.system.dto.CryptoDTO;
import crypto.trading.system.service.CryptoService;

@SpringBootApplication
@ComponentScan({"crypto.trading.system.service"})
@EntityScan("crypto.trading.system.domain")
@EnableJpaRepositories("crypto.trading.system.repository")
@EnableScheduling
public class ProjectApplication
{
	@Autowired
	private CryptoService cryptoService;
	
	private final String url = "https://api.binance.com/api/v3/ticker/bookTicker";
	
	Logger logger = LoggerFactory.getLogger(ProjectApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	
	
	@Scheduled(fixedRate = 10000)
	public void updateCryto() throws Exception
	{
		logger.info("start scheduling update crypto price!");
		RestTemplate restTemplate = new RestTemplate();
		String input = restTemplate.getForObject(url, String.class);
		ObjectMapper mapper = new ObjectMapper();		
		List<CryptoDTO> inputList = Arrays.asList(mapper.readValue(input, CryptoDTO[].class));
		for(CryptoDTO dto : inputList)
		{
//			logger.info("Name of Cryto is: {}",dto.getSymbol());
			String name = dto.getSymbol();
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
					cryptoService.save(existingCrypto);
				}
				else//updating existing crypto records
				{
					cryptoService.updateCrypto(bidPrice, askPrice, existingCrypto.getCryptoId());
				}
			}
		}
		logger.info("end of scheduling update crypto price!");
	}
	

}

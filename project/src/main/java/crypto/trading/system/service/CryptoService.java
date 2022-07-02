package crypto.trading.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crypto.trading.system.domain.Crypto;
import crypto.trading.system.repository.CryptoDAO;

@Service
public class CryptoService {
	
	@Autowired
	private CryptoDAO cryptoDAO;
	
	public Crypto findById(Long id)
	{
		Crypto crypto = cryptoDAO.findByCryptoId(id);
		return crypto;
	}
	
	public Crypto findByName(String name)
	{
		Crypto crypto = cryptoDAO.findByName(name);
		return crypto;
	}
	
	public List<Crypto> findAll()
	{
		List<Crypto> cryptoList = cryptoDAO.findAll();
		return cryptoList;
	}
	
	public void updateCrypto(float bidPrice, float askPrice, Long cryptoId)
	{
		cryptoDAO.updateCryptoById(bidPrice, askPrice, cryptoId);
	}
	
//	public void updateCrypto(float bidPrice, float askPrice, Long cryptoId)
//	{
//		Crypto crypto = new Crypto();
//		crypto.setAskPrice(askPrice);
//		crypto.setBidPrice(bidPrice);
//		crypto.setCryptoId(cryptoId);
//		cryptoDAO.save(crypto);
//	}
	
	public Crypto save(Crypto crypto)
	{
		return cryptoDAO.save(crypto);
	}
}

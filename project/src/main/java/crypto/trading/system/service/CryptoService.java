package crypto.trading.system.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
	
	public void updateBidDetailAndAskDetailById(float bidPrice, float askPrice, String bidSource, String askSource, Long cryptoId)
	{
		cryptoDAO.updateBidDetailAndAskDetailById(bidPrice, askPrice, bidSource, askSource, cryptoId);
	}
	

	public void updateBidDetailById(float bidPrice, String bidSource, Long id)
	{
		cryptoDAO.updateBidDetailById(bidPrice, bidSource, id);
	}

	public void updateAskDetailById(float askPrice, String askSource, Long id)
	{
		cryptoDAO.updateAskDetailById(askPrice, askSource, id);
	}
	
	public Crypto save(Crypto crypto)
	{
		return cryptoDAO.save(crypto);
	}
}
package crypto.trading.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crypto.trading.system.domain.Wallet;
import crypto.trading.system.repository.WalletDAO;

@Service
public class WalletService
{
	@Autowired
	private WalletDAO walletDAO;
	
	public Wallet findByWalletId(Long id)
	{
		Wallet wallet = walletDAO.findByWalletId(id);
		return wallet;
	}
	
	public List<Wallet> findAllByUserId(Long id)
	{
		List<Wallet> walletList = walletDAO.findAllByUserId(id);
		return walletList;
	}
	
	public Wallet saveUser(Wallet wallet)
	{
		return walletDAO.save(wallet);
	}
	
	public Wallet findByCryptoName(String name)
	{
		Wallet wallet = walletDAO.findByCrytoName(name);
		return wallet;
	}
	
	public void updateUserWallet(float cryptoAmount, Long walletId)
	{
		walletDAO.updateUserWalletById(cryptoAmount, walletId);
	}

}

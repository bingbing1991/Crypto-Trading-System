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
	
	public Wallet saveWallet(Wallet wallet)
	{
		return walletDAO.save(wallet);
	}
	
	public Wallet findByCryptoNameAndUserId(String name, Long id)
	{
		Wallet wallet = walletDAO.findByCryptoNameAndUserId(name, id);
		return wallet;
	}
	
	public void updateUserWallet(float cryptoAmount, Long walletId)
	{
		walletDAO.updateUserWalletById(cryptoAmount, walletId);
	}

}

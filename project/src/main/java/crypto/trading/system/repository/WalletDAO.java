package crypto.trading.system.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import crypto.trading.system.domain.Wallet;

public interface WalletDAO extends JpaRepository<Wallet, Long>
{
	public Wallet findByWalletId(Long walletId);
	
	public List<Wallet> findAllByUserId(Long userId);
	
	public Wallet findByCrytoName(String cryptoName);
	
	@Transactional
	@Modifying
	@Query("update Wallet w set w.cryptoAmount = ?1 where w.walletId = ?2")
	public void updateUserWalletById(float wallet, Long walletId);
}

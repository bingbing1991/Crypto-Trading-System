package crypto.trading.system.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import crypto.trading.system.domain.Wallet;

@Repository
public interface WalletDAO extends JpaRepository<Wallet, Long>
{
	public Wallet findByWalletId(Long walletId);
	
	public List<Wallet> findAllByUserId(Long userId);
	
	public Wallet findByCryptoNameAndUserId(String cryptoName, Long id);
	
	@Transactional
	@Modifying
	@Query("update Wallet w set w.cryptoAmount = ?1 where w.walletId = ?2")
	public void updateUserWalletById(float wallet, Long walletId);
}

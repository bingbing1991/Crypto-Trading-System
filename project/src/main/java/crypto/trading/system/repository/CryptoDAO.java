package crypto.trading.system.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import crypto.trading.system.domain.Crypto;

@Repository
public interface CryptoDAO extends JpaRepository<Crypto, Long>
{
	
	public Crypto findByCryptoId(Long id);
	
	public Crypto findByName(String name);
	
	@Transactional
	@Modifying
	@Query("update Crypto c set c.bidPrice = ?1, c.askPrice=?2 , c.bidSource= ?3 , c.askSource=?4 where c.id = ?5")
	public void updateBidDetailAndAskDetailById(float bidPrice, float askPrice, String bidSource, String askSource, Long id);

	@Transactional
	@Modifying
	@Query("update Crypto c set c.bidPrice = ?1, c.bidSource= ?2 where c.id = ?3")
	public void updateBidDetailById(float bidPrice, String bidSource, Long id);

	@Transactional
	@Modifying
	@Query("update Crypto c set c.askPrice = ?1, c.askSource= ?2 where c.id = ?3")
	public void updateAskDetailById(float askPrice, String askSource, Long id);
}
package crypto.trading.system.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import crypto.trading.system.domain.UserAccount;

public interface UserDAO extends JpaRepository<UserAccount, Long>
{
	
	public UserAccount findByUserId(Long userId);
	
	public UserAccount findByEmail(String email);
	
	public UserAccount findByEmailAndPassword(String email, String password);
	
//	@Transactional
//	@Modifying
//	@Query("update UserAccount a set a.wallet = ?1 where a.id = ?2")
//	public void updateUserWalletById(float wallet, Long userId);
}

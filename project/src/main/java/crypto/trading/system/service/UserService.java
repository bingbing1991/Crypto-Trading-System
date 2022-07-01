package crypto.trading.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crypto.trading.system.domain.UserAccount;
import crypto.trading.system.repository.UserDAO;

@Service
public class UserService 
{
	@Autowired
	private UserDAO userDAO;
	
	public UserAccount findByUserId(Long id)
	{
		UserAccount user = userDAO.findByUserId(id);
		return user;
	}
	
	public UserAccount saveUser(UserAccount user)
	{
		return userDAO.save(user);
	}
	
//	public void updateUserWallet(float wallet, Long userId)
//	{
//		userDAO.updateUserWalletById(wallet, userId);
//	}
	
	public UserAccount findUserByEmailId(String email)
	{
		UserAccount foundUser = userDAO.findByEmail(email);
		return foundUser;
	}
	
	public UserAccount findUserByEmailIdAndPassword(String email, String password)
	{
		UserAccount foundUser = userDAO.findByEmailAndPassword(email, password);
		return foundUser;
	}
}

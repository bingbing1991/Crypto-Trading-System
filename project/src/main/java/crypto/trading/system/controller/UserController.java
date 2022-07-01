package crypto.trading.system.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

import crypto.trading.system.domain.UserAccount;
import crypto.trading.system.domain.Wallet;
import crypto.trading.system.service.UserService;
import crypto.trading.system.service.WalletService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
public class UserController
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private WalletService walletService;
	
	@PostMapping("/registerUser")
//	@CrossOrigin(origins = "http://localhost:4200")
	public UserAccount registerUser(@RequestBody UserAccount user) throws Exception
	{
		String tempEmailId = user.getEmail();
		if(tempEmailId != null && !"".equals(tempEmailId))
		{
			//check if this emailId already exist in the DB
			UserAccount foundUser = userService.findUserByEmailId(tempEmailId);
			if(foundUser != null)
			{
//				Log.info("UserAccount with this " +tempEmailId+ " already exists.");
				throw new Exception("UserAccount with this " +tempEmailId+ " already exists.");
			}
		}
		UserAccount registeredUser = new UserAccount();
		registeredUser = userService.saveUser(user);
		return registeredUser;
	}
	
	@PostMapping("/LoginUser")
//	@CrossOrigin(origins = "http://localhost:4200")
	public UserAccount loginUser(@RequestBody UserAccount user) throws Exception
	{
		String tempEmail = user.getEmail();
		String tempPassword = user.getPassword();
		UserAccount foundUser = null;
		
		if(tempEmail != null && tempPassword != null)
		{
			foundUser = userService.findUserByEmailIdAndPassword(tempEmail, tempPassword);
			if(foundUser == null)
			{
				throw new Exception("Invalid Username or Password");
			}
		}
		return foundUser;
	}
	
	@GetMapping("/CheckUserWallet")
//	@CrossOrigin(origins = "http://localhost:4200")
	public List<Wallet> getUser(@RequestBody UserAccount user) throws Exception
	{
		Long userID = user.getId();
		UserAccount foundUser;
		
		if(userID != null)
		{
			foundUser = userService.findByUserId(userID);
			if(foundUser == null)
			{
				throw new Exception("Can't Find UserAccount details");
			}
		}
		else
		{
			throw new Exception("No user Id input");
		}
		
		List<Wallet> wList = new ArrayList<>();
		wList = walletService.findAllByUserId(userID);
		
		return wList;
	}
}


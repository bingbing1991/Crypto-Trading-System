package crypto.trading.system.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Wallet")
@Table(name = "Wallet")
public class Wallet
{
	public Wallet()
	{
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id") 
	private Long walletId;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "crypto_name")
	private String cryptoName;

	@Column(name = "crypto_amount")
	private String cryptoAmount;

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCryptoName() {
		return cryptoName;
	}

	public void setCryptoName(String cryptoName) {
		this.cryptoName = cryptoName;
	}

	public String getCryptoAmount() {
		return cryptoAmount;
	}

	public void setCryptoAmount(String cryptoAmount) {
		this.cryptoAmount = cryptoAmount;
	}
	
	
}

package crypto.trading.system.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Wallet")
@Table(name = "Wallet")
public class Wallet
{
	public Wallet()
	{
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_WALLET_ID")
	@SequenceGenerator(name = "SEQ_WALLET_ID", initialValue = 2, allocationSize = 1, sequenceName = "SEQ_WALLET_ID")
	@Column(name = "id") 
	private Long walletId;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "crypto_name")
	private String cryptoName;

	@Column(name = "crypto_amount")
	private float cryptoAmount;

	public Long getWalletId() {
		return walletId;
	}

	public void setWalletId(Long walletId) {
		this.walletId = walletId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCryptoName() {
		return cryptoName;
	}

	public void setCryptoName(String cryptoName) {
		this.cryptoName = cryptoName;
	}

	public float getCryptoAmount() {
		return cryptoAmount;
	}

	public void setCryptoAmount(float cryptoAmount) {
		this.cryptoAmount = cryptoAmount;
	}
	
	
}

package crypto.trading.system.dto;

public class WalletDTO
{
	private String cryptoName;

	private float cryptoAmount;

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

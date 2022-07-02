package crypto.trading.system.dto;

import java.sql.Timestamp;

public class TradingTransactionDTO
{
	private String givenCryptoName;
	
	private String takenCryptoName;
	
	private float givenCryptoAmount;

	private float takenCryptoAmount;

	private Timestamp transactionTimestamp;

	public String getGivenCryptoName() {
		return givenCryptoName;
	}

	public void setGivenCryptoName(String givenCryptoName) {
		this.givenCryptoName = givenCryptoName;
	}

	public String getTakenCryptoName() {
		return takenCryptoName;
	}

	public void setTakenCryptoName(String takenCryptoName) {
		this.takenCryptoName = takenCryptoName;
	}

	public float getGivenCryptoAmount() {
		return givenCryptoAmount;
	}

	public void setGivenCryptoAmount(float givenCryptoAmount) {
		this.givenCryptoAmount = givenCryptoAmount;
	}

	public float getTakenCryptoAmount() {
		return takenCryptoAmount;
	}

	public void setTakenCryptoAmount(float takenCryptoAmount) {
		this.takenCryptoAmount = takenCryptoAmount;
	}

	public Timestamp getTransactionTimestamp() {
		return transactionTimestamp;
	}

	public void setTransactionTimestamp(Timestamp transactionTimestamp) {
		this.transactionTimestamp = transactionTimestamp;
	}
	
	
}

/**
 * 
 */
package crypto.trading.system.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "TradingTransaction")
@Table(name = "Transaction")
public class TradingTransaction
{
	public TradingTransaction()
	{
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence_generator")
    @SequenceGenerator(name = "transaction_sequence_generator", sequenceName = "SEQ_TRANSACTION")
	@Column(name = "id") 
	private Long transactionId;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "given_crypto_name")
	private String givenCryptoName;
	
	@Column(name = "taken_crypto_name")
	private String takenCryptoName;
	
	@Column(name = "given_crypto_amount")
	private float givenCryptoAmount;
	
	@Column(name = "taken_crypto_amount")
	private float takenCryptoAmount;
	
	@Column(name = "transaction_timestamp")
	private Timestamp transactionTimestamp;

	public Timestamp getTransactionTimestamp() {
		return transactionTimestamp;
	}

	public void setTransactionTimestamp(Timestamp transactionTimestamp) {
		this.transactionTimestamp = transactionTimestamp;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

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

	
	
	
}

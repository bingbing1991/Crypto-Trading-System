package crypto.trading.system.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity(name = "Crypto")
@Table(name = "Crypto")
	public class Crypto
	{
		public Crypto()
		{
			
		}
		
		@Id
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CRYPTO_ID")
		@SequenceGenerator(name = "SEQ_CRYPTO_ID", initialValue = 1, allocationSize = 1, sequenceName = "SEQ_CRYPTO_ID")
		@Column(name = "id")
		private Long cryptoId;
		
		@Column(name = "name")
		private String name;
		
		@Column(name = "bid_price")
		private float bidPrice;

		@Column(name = "ask_price")
		private float askPrice;
		
		@Column(name = "bid_source")
		private String bidSource;
		
		@Column(name = "ask_source")
		private String askSource;

		public Long getCryptoId() {
			return cryptoId;
		}

		public void setCryptoId(Long cryptoId) {
			this.cryptoId = cryptoId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public float getBidPrice() {
			return bidPrice;
		}

		public void setBidPrice(float bidPrice) {
			this.bidPrice = bidPrice;
		}

		public float getAskPrice() {
			return askPrice;
		}

		public void setAskPrice(float askPrice) {
			this.askPrice = askPrice;
		}

		public String getBidSource() {
			return bidSource;
		}

		public void setBidSource(String bidSource) {
			this.bidSource = bidSource;
		}

		public String getAskSource() {
			return askSource;
		}

		public void setAskSource(String askSource) {
			this.askSource = askSource;
		}
		
}

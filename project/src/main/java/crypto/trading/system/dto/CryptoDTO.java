package crypto.trading.system.dto;

public class CryptoDTO
{
	private String symbol;
	
	private String bidPrice;
	
	private String askPrice;
	
	private String bidQty;
	
	private String askQty;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getBidPrice() {
		return bidPrice;
	}

	public void setBidPrice(String bidPrice) {
		this.bidPrice = bidPrice;
	}

	public String getAskPrice() {
		return askPrice;
	}

	public void setAskPrice(String askPrice) {
		this.askPrice = askPrice;
	}

	public String getBidQty() {
		return bidQty;
	}

	public void setBidQty(String bidQty) {
		this.bidQty = bidQty;
	}

	public String getAskQty() {
		return askQty;
	}

	public void setAskQty(String askQty) {
		this.askQty = askQty;
	}
	
	
}
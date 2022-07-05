package crypto.trading.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crypto.trading.system.domain.TradingTransaction;
import crypto.trading.system.repository.TradingTransactionDAO;

@Service
public class TradingTransactionService
{
	@Autowired
	private TradingTransactionDAO tradingTransactionDAO;
	
	public TradingTransaction findByTransactionId(Long id)
	{
		TradingTransaction tradingTransaction = tradingTransactionDAO.findByTransactionId(id);
		return tradingTransaction;
	}
	
	public List<TradingTransaction> findAllByUserId(Long id)
	{
		List<TradingTransaction> tradingTransactionList = tradingTransactionDAO.findAllByUserId(id);
		return tradingTransactionList;
	}
	
	public TradingTransaction saveTransaction(TradingTransaction tradingTransaction)
	{
		return tradingTransactionDAO.save(tradingTransaction);
	}
	
	public float amountToTrade(float amount, float rate)
	{
		float tradeAmount;
		tradeAmount = amount * rate; 
		return tradeAmount;
	}
}

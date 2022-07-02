package crypto.trading.system.repository;

import java.util.List;

//import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import crypto.trading.system.domain.TradingTransaction;

@Repository
public interface TradingTransactionDAO extends JpaRepository<TradingTransaction, Long>
{
	public TradingTransaction findByTransactionId(Long transactionId);
	
	public List<TradingTransaction> findAllByUserId(Long userId);
	
	
}

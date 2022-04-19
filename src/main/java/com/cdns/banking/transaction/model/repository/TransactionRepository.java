package com.cdns.banking.transaction.model.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cdns.banking.transaction.model.TransactionEntity;

/**
 * TransactionRepository
 * 
 * @version 1.0
 */
public interface TransactionRepository extends CrudRepository<TransactionEntity, String>
{
	/**
	 * findTransactionByAccountID
	 * 
	 * @param accountID String
	 * @return List<Transaction>
	 */
	List<TransactionEntity> findTransactionByAccountID(String accountID);
}

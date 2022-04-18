package com.cdns.banking.transaction.service;

import java.util.List;

import org.springframework.boot.configurationprocessor.json.JSONException;

import com.cdns.banking.transaction.model.TransactionEntity;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * TransactionService
 * 
 * @version 1.0
 */
public interface TransactionService {

	/**
	 * balanceDebit
	 * 
	 * @param transaction {@link TransactionEntity}
	 * @return {@link TransactionEntity}
	 */
	public TransactionEntity balanceDebit(TransactionEntity transaction);

	/**
	 * balanceCredit
	 * 
	 * @param transaction {@link TransactionEntity}
	 * @return {@link TransactionEntity}
	 */
	public TransactionEntity balanceCredit(TransactionEntity transaction);

	/**
	 * performPayment
	 * 
	 * @param transaction {@link TransactionEntity}
	 * @return String[]
	 * @throws JSONException
	 * @throws JsonProcessingException
	 */
	public String[] performPayment(TransactionEntity transaction) throws JsonProcessingException, JSONException;

	/**
	 * getTransactionEntityByAccountID
	 * 
	 * @param transaction {@link String}
	 * @return {@link List}
	 */
	public List<TransactionEntity> getTransactionEntityByAccountID(String accountID);
}

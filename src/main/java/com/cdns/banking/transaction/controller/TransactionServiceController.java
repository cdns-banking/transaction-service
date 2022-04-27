package com.cdns.banking.transaction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdns.banking.transaction.model.TransactionEntity;
import com.cdns.banking.transaction.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * TransactionServiceController
 * 
 * @version 1.0
 */
@RestController
@RequestMapping("/transaction")
public class TransactionServiceController {

	/**
	 * transactionService
	 */
	@Autowired
	private TransactionService transactionService;

	/**
	 * viewTransactions
	 * 
	 * @param accountNumber
	 * @return List<TransactionEntity>
	 */
	@GetMapping("/transaction/{accountNumber}")
	public List<TransactionEntity> viewTransactions(@PathVariable String accountNumber) {
		return transactionService.getTransactionEntityByAccountNumber(accountNumber);
	}

	/**
	 * makeTransaction
	 * 
	 * @param transaction TransactionEntity
	 * @return String
	 */
	@PostMapping("/transaction")
	public String makeTransaction(@RequestBody TransactionEntity transaction) {
		String result = "Transaction Failed!";
		try {
			result = transactionService.performTransaction(transaction);
		} catch (JsonProcessingException e) {
			return result;
		} catch (JSONException e) {
			return result;
		}
		return result;
	}
}

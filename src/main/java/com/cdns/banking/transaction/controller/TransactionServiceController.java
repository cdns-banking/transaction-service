package com.cdns.banking.transaction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
public class TransactionServiceController {
	/**
	 * transactionService
	 */
	@Autowired
	private TransactionService transactionService;

	/**
	 * payment
	 * 
	 * @param transaction {@link TransactionEntity}
	 * @return {@link String}
	 */
	@PostMapping("/transaction")
	public String transaction(@RequestBody TransactionEntity transaction) {
		String[] result = new String[] { "Payment Failed!", "Internal Server Error!" };
		try {
			result = transactionService.performPayment(transaction);
		} catch (JsonProcessingException e) {
			return result[0] + " " + result[1];
		} catch (JSONException e) {
			return result[0] + " " + result[1];
		}
		return result[0] + " " + result[1];
	}

	/**
	 * viewStatement
	 * 
	 * @param accountID {@link String}
	 * @return List<Transaction>
	 */
	@GetMapping("/statement/{accountID}")
	public List<TransactionEntity> viewStatement(@PathVariable String accountID) {
		return transactionService.getTransactionEntityByAccountID(accountID);
	}
}

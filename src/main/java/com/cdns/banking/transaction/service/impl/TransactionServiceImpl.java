package com.cdns.banking.transaction.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.cdns.banking.transaction.model.Account;
import com.cdns.banking.transaction.model.TransactionEntity;
import com.cdns.banking.transaction.model.repository.TransactionRepository;
import com.cdns.banking.transaction.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TransactionServiceImpl
 * 
 * @version 1.0
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	/**
	 * transactionRepository
	 */
	@Autowired
	private TransactionRepository transactionRepository;

	/**
	 * environment
	 */
	@Autowired
	Environment environment;

	/**
	 * restTemplate
	 */
	@Autowired
	private RestTemplate restTemplate;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransactionEntity balanceDebit(TransactionEntity transaction) {
		return transactionRepository.save(transaction);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TransactionEntity balanceCredit(TransactionEntity transaction) {
		return transactionRepository.save(transaction);
	}

	/**
	 * {@inheritDoc}
	 */
	@Transactional
	@Override
	public String performTransaction(TransactionEntity transaction) throws JsonProcessingException, JSONException {
		String result = "Transaction Success.";

		// get Account details for source account
		Account sourceAccount = restTemplate.getForObject(
				environment.getProperty("base-url.account-service") + "/number/" + transaction.getAccountNumber(),
				Account.class);

		// get balance for source account,
		int balance = sourceAccount.getBalance();

		// payment should fail if not enough balance
		if (balance >= transaction.getAmount()) {
			// get accountNumber for the user phoneNumber
			String accountNumberofDestination = restTemplate.getForObject(
					environment.getProperty("base-url.account-service") + "/phone/" + transaction.getPhoneNumber(),
					String.class);
			if (null != accountNumberofDestination && accountNumberofDestination.trim().length() != 0) {

				sourceAccount.setBalance(sourceAccount.getBalance() - transaction.getAmount());

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);

				ObjectMapper Obj = new ObjectMapper();

				JSONObject debitJsonObject = new JSONObject(Obj.writeValueAsString(sourceAccount));

				HttpEntity<String> debitRequest = new HttpEntity<String>(debitJsonObject.toString(), headers);

				// update balance of source account
				restTemplate.postForObject(environment.getProperty("base-url.account-service") + "/transaction",
						debitRequest, String.class);

				// get Account details for destination account
				Account destinationAccount = restTemplate.getForObject(
						environment.getProperty("base-url.account-service") + "/number/" + accountNumberofDestination,
						Account.class);

				TransactionEntity creditTransaction = new TransactionEntity();
				creditTransaction.setAccountNumber(accountNumberofDestination);
				creditTransaction.setRemarks(accountNumberofDestination + ": " + transaction.getRemarks());
				creditTransaction.setTransactionDate(transaction.getTransactionDate());
				creditTransaction.setTransactionType("credit");
				creditTransaction.setAmount(transaction.getAmount());

				List<TransactionEntity> transactionList = new ArrayList<>();
				transactionList.add(transaction);
				transactionList.add(creditTransaction);
				transactionRepository.saveAll(transactionList);

				destinationAccount.setBalance(destinationAccount.getBalance() + transaction.getAmount());

				JSONObject creditJsonObject = new JSONObject(Obj.writeValueAsString(destinationAccount));
				HttpEntity<String> creditRequest = new HttpEntity<String>(creditJsonObject.toString(), headers);

				// update balance of source account
				restTemplate.postForObject("http://account-service/account/transaction", creditRequest, String.class);
			} else {
				result = "Destination Account does not exist!";
			}
		} else {
			result = "Low Balance to make the transaction!";
		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TransactionEntity> getTransactionEntityByAccountNumber(String accountNumber) {
		return transactionRepository.findTransactionByAccountNumber(accountNumber);
	}

}

package com.cdns.banking.transaction.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
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
	 * 
	 * @throws JSONException
	 * @throws JsonProcessingException
	 */
	@Transactional
	@Override
	public String[] performPayment(TransactionEntity transaction) throws JsonProcessingException, JSONException {
		String[] result = { "Payment Success.", "" };

		// get Account details for source account
		Account sourceAccount = restTemplate
				.getForObject("http://account-service/account/id/" + transaction.getAccountID(), Account.class);

		// get balance for source account,
		int balance = sourceAccount.getBalance();

		// if low balance, transaction failed.
		if (balance >= transaction.getAmount()) {
			// get accountID for phoneNumber
			String accountIDofDestination = restTemplate
					.getForObject("http://account-service/account/phone/" + transaction.getPhoneNumber(), String.class);
			if (null != accountIDofDestination && accountIDofDestination.trim().length() != 0) {
				// record debit transaction in t_transactions table for source
				// debitTransaction(transaction);

				sourceAccount.setBalance(sourceAccount.getBalance() - transaction.getAmount());

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);

				ObjectMapper Obj = new ObjectMapper();

				JSONObject debitJsonObject = new JSONObject(Obj.writeValueAsString(sourceAccount));

				HttpEntity<String> debitRequest = new HttpEntity<String>(debitJsonObject.toString(), headers);

				// update balance of source account
				restTemplate.postForObject("http://account-service/account/transaction", debitRequest, String.class);

				// get Account details for destination account
				Account destinationAccount = restTemplate
						.getForObject("http://account-service/account/id/" + accountIDofDestination, Account.class);

				TransactionEntity creditTransaction = new TransactionEntity();
				creditTransaction.setTransactionDate(transaction.getTransactionDate());
				creditTransaction.setTransactionType("credit");
				creditTransaction.setAccountID(accountIDofDestination);
				creditTransaction.setRemarks(accountIDofDestination + ": " + transaction.getRemarks());
				creditTransaction.setAmount(transaction.getAmount());

				// record credit transaction in t_transactions table for destination
				// creditTransaction(creditTransaction);

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
				result = new String[] { "Payment Failed!", "Target Account Doesn't Exist!" };
			}
		} else {
			result = new String[] { "Payment Failed!", "Not Enough Balance!" };
		}

		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TransactionEntity> getTransactionEntityByAccountID(String accountID) {
		return transactionRepository.findTransactionByAccountID(accountID);
	}

}

package com.cdns.banking.transaction;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.cdns.banking.transaction.controller.TransactionServiceController;
import com.cdns.banking.transaction.model.TransactionEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * TransactionServiceApplicationTests
 * 
 * @version 1.0
 */
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TransactionServiceApplicationTests {

	/**
	 * port
	 */
	@LocalServerPort
	private int port;

	/**
	 * controller
	 */
	@Autowired
	TransactionServiceController controller;

	/**
	 * environment
	 */
	@Autowired
	Environment environment;

	/**
	 * restTemplate
	 */
	@Autowired
	TestRestTemplate restTemplate;

	/**
	 * contextLoads
	 */
	@Test
	void contextLoads() {
	}

	/**
	 * testViewTransactions
	 * 
	 * @throws JSONException
	 * @throws JsonProcessingException
	 */
	@Test
	void testViewTransactions() throws JsonProcessingException, JSONException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		ObjectMapper Obj = new ObjectMapper();

		TransactionEntity transactionEntity = openDepositEntityObject();

		JSONObject transactionJsonObj = new JSONObject(Obj.writeValueAsString(transactionEntity));

		HttpEntity<String> depositRequest = new HttpEntity<String>(transactionJsonObj.toString(), headers);

		assertThat(this.restTemplate.postForObject(environment.getProperty("test.transaction.url") + port + "/deposit",
				depositRequest, String.class)).contains("Success");
	}

	/**
	 * openDepositEntityObject
	 * 
	 * @return {@link DepositEntity}
	 */
	private TransactionEntity openDepositEntityObject() {
		TransactionEntity transactionEntity = new TransactionEntity();
		transactionEntity.setAccountNumber(null);
		transactionEntity.setAmount(1000);
		transactionEntity.setPhoneNumber("89010203");
		transactionEntity.setRemarks("JUnit Test");
		transactionEntity.setTransactionDate("2022-04-15");
		transactionEntity.setTransactionID(null);
		transactionEntity.setTransactionType("debit");
		return transactionEntity;
	}

	/**
	 * testMakeTransaction
	 */
	@Test
	void testMakeTransaction() {

	}

}

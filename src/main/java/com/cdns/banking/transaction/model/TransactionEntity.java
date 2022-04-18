package com.cdns.banking.transaction.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * TransactionEntity
 * 
 * @version 1.0
 */
@Entity
@Table(name = "t_transaction")
public class TransactionEntity {
	/**
	 * transactionID
	 */
	@Id
	@GenericGenerator(name = "generator", strategy = "uuid2", parameters = {})
	@GeneratedValue(generator = "generator")
	@Column(name = "transaction_id", columnDefinition = "uniqueidentifier")
	private String transactionID;

	/**
	 * accountID
	 */
	@Column(name = "account_id", columnDefinition = "uniqueidentifier")
	private String accountID;

	/**
	 * transactionDate
	 */
	@Column(name = "transaction_date")
	private String transactionDate;

	/**
	 * amount
	 */
	@Column(name = "amount")
	private int amount;

	/**
	 * transactionType
	 */
	@Column(name = "transaction_type")
	private String transactionType;

	/**
	 * remarks
	 */
	@Column(name = "remarks")
	private String remarks;

	/**
	 * phoneNumber
	 */
	private String phoneNumber;

	/**
	 * getTransactionID
	 * 
	 * @return transactionID {@link String}
	 */
	public String getTransactionID() {
		return transactionID;
	}

	/**
	 * setTransactionID
	 * 
	 * @param transactionID {@link String}
	 */
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	/**
	 * getAccountID
	 * 
	 * @return accountID {@link String}
	 */
	public String getAccountID() {
		return accountID;
	}

	/**
	 * setAccountID
	 * 
	 * @param accountID {@link String}
	 */
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	/**
	 * getTransactionDate
	 * 
	 * @return transactionDate {@link String}
	 */
	public String getTransactionDate() {
		return transactionDate;
	}

	/**
	 * setTransactionDate
	 * 
	 * @param transactionDate {@link String}
	 */
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * getAmount
	 * 
	 * @return amount {@link String}
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * setAmount
	 * 
	 * @param amount {@link String}
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * getTransactionType
	 * 
	 * @return transactionType {@link String}
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * setTransactionType
	 * 
	 * @param transactionType {@link String}
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * getRemarks
	 * 
	 * @return remarks {@link String}
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * setRemarks
	 * 
	 * @param remarks {@link String}
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * getPhoneNumber
	 * 
	 * @return phoneNumber {@link String}
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * setPhoneNumber
	 * 
	 * @param phoneNumber {@link String}
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}

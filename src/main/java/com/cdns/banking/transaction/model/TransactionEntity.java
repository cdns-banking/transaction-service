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
	private String accountNumber;

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
	 * @return transactionID String
	 */
	public String getTransactionID() {
		return transactionID;
	}

	/**
	 * setTransactionID
	 * 
	 * @param transactionID String
	 */
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	/**
	 * getAccountNumber
	 * 
	 * @return accountID String
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * setAccountNumber
	 * 
	 * @param accountID String
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * getTransactionDate
	 * 
	 * @return transactionDate String
	 */
	public String getTransactionDate() {
		return transactionDate;
	}

	/**
	 * setTransactionDate
	 * 
	 * @param transactionDate String
	 */
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * getAmount
	 * 
	 * @return amount String
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * setAmount
	 * 
	 * @param amount String
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * getTransactionType
	 * 
	 * @return transactionType String
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * setTransactionType
	 * 
	 * @param transactionType String
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * getRemarks
	 * 
	 * @return remarks String
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * setRemarks
	 * 
	 * @param remarks String
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * getPhoneNumber
	 * 
	 * @return phoneNumber String
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * setPhoneNumber
	 * 
	 * @param phoneNumber String
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}

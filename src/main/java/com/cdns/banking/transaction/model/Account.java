package com.cdns.banking.transaction.model;

/**
 * Account
 * 
 * @version 1.0
 */
public class Account {

	/**
	 * accountID
	 */
	private String accountID;

	/**
	 * userID
	 */
	private String userID;

	/**
	 * accountNumber
	 */
	private String accountNumber;

	/**
	 * accountType
	 */
	private int accountType;

	/**
	 * balance
	 */
	private int balance;

	/**
	 * accountStatus
	 */
	private String accountStatus;

	/**
	 * getAccountID
	 * 
	 * @return String
	 */
	public String getAccountID() {
		return accountID;
	}

	/**
	 * setAccountID
	 * 
	 * @param accountID String
	 */
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	/**
	 * getAccountStatus
	 * 
	 * @return accountStatus Integer
	 */
	public String getAccountStatus() {
		return accountStatus;
	}

	/**
	 * setAccountStatus
	 * 
	 * @param accountStatus Integer
	 */
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	/**
	 * getUserID
	 * 
	 * @return userID Integer
	 */
	public String getUserID() {
		return userID;
	}

	/**
	 * setUserID
	 * 
	 * @param userID Integer
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * getAccountNumber
	 * 
	 * @return Integer
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * setAccountNumber
	 * 
	 * @param accountNumber Integer
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * getAccountType
	 * 
	 * @return accountType Integer
	 */
	public int getAccountType() {
		return accountType;
	}

	/**
	 * setAccountType
	 * 
	 * @param accountType Integer
	 */
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}

	/**
	 * getBalance
	 * 
	 * @return balance Integer
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * setBalance
	 * 
	 * @param balance Integer
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}
}
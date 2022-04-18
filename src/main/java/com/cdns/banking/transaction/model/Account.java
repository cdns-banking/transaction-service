package com.cdns.banking.transaction.model;

/**
 * Account
 * 
 * @version 1.0
 */
public class Account
{
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
	 * @return {@link String}
	 */
	public String getAccountID()
	{
		return accountID;
	}

	/**
	 * setAccountID
	 * 
	 * @param accountID {@link String}
	 */
	public void setAccountID(String accountID)
	{
		this.accountID = accountID;
	}

	/**
	 * getUserID
	 * 
	 * @return userID {@link Integer}
	 */
	public String getUserID()
	{
		return userID;
	}

	/**
	 * setUserID
	 * 
	 * @param userID {@link Integer}
	 */
	public void setUserID(String userID)
	{
		this.userID = userID;
	}

	/**
	 * getAccountNumber
	 * 
	 * @return {@link Integer}
	 */
	public String getAccountNumber()
	{
		return accountNumber;
	}

	/**
	 * setAccountNumber
	 * 
	 * @param accountNumber {@link Integer}
	 */
	public void setAccountNumber(String accountNumber)
	{
		this.accountNumber = accountNumber;
	}

	/**
	 * getAccountType
	 * 
	 * @return accountType {@link Integer}
	 */
	public int getAccountType()
	{
		return accountType;
	}

	/**
	 * setAccountType
	 * 
	 * @param accountType {@link Integer}
	 */
	public void setAccountType(int accountType)
	{
		this.accountType = accountType;
	}

	/**
	 * getBalance
	 * 
	 * @return balance {@link Integer}
	 */
	public int getBalance()
	{
		return balance;
	}

	/**
	 * setBalance
	 * 
	 * @param balance {@link Integer}
	 */
	public void setBalance(int balance)
	{
		this.balance = balance;
	}

	/**
	 * getAccountStatus
	 * 
	 * @return accountStatus {@link Integer}
	 */
	public String getAccountStatus()
	{
		return accountStatus;
	}

	/**
	 * setAccountStatus
	 * 
	 * @param accountStatus {@link Integer}
	 */
	public void setAccountStatus(String accountStatus)
	{
		this.accountStatus = accountStatus;
	}
}

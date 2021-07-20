package com.example.models;

import java.util.Random;

import com.example.exceptions.WithdrawalNegativeBalanceException;
import com.example.logging.Logging;

public class Account {
	private int accountNumber;
	private int id;
	private int balance;
	private String accountType;
	
	
	
	
	public Account() {
		
	}

	public Account(int number, int id, int balance, String type) {
		this.accountNumber=number;
		this.id = id;
		this.balance = balance;
		this.accountType = type;
	}
	
	public Account(int id, int balance, String type) {
		this.balance = balance;
		this.accountType = type;
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	/*
	public void makeWithdrawal(int amount) {
	if (amount == balance) {
		balance = 0;
	}else if(amount > balance) {
		
		throw new WithdrawalNegativeBalanceException();
	}else {
		balance = balance -= amount;
	}
}
	public void makeDeposit(int amount) {
		balance = balance += amount;
	}
	public void transfer(int amount) {
		if(amount == balance) {
			balance = 0;
		}else if(amount > balance) {
			
			throw new WithdrawalNegativeBalanceException();
		}else {
		balance = balance -=amount;
	}
	}
*/
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", id=" + id + ", balance=" + balance + ", accountType="
				+ accountType + "]";
	}

	
	

	
	}
	
	
	

	




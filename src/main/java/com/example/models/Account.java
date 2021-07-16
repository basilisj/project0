package com.example.models;

public class Account {
	private String user;
	private double balance;
	
	public Account() {
		
	}

	public Account(String user, double balance) {
		this.user = user;
		this.balance = balance;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [user=" + user + ", account balance=" + balance + "]";
	}
}

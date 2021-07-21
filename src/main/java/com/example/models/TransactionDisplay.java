package com.example.models;

public class TransactionDisplay {

	private int tNumber;
	private int accId;
	private String transactionType;
	private int transactionAmount;
	
	public TransactionDisplay() {
		super();
	}

	public TransactionDisplay(int tNumber, int accId, String transactionType, int transactionAmount) {
		super();
		this.tNumber = tNumber;
		this.accId = accId;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
	}

	public int gettNumber() {
		return tNumber;
	}

	public void settNumber(int tNumber) {
		this.tNumber = tNumber;
	}

	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(int transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	@Override
	public String toString() {
		return "TransactionDisplay [tNumber=" + tNumber + ", accId=" + accId + ", transactionType=" + transactionType
				+ ", transactionAmount=" + transactionAmount + "]";
	}
	
}

package com.example.dao;

import java.sql.SQLException;
import java.util.List;

import com.example.models.Account;
import com.example.models.TransactionDisplay;
import com.example.models.User;

public interface AccountDao {
	
	List<Account> getAllAccounts();
	
	//public List<Account> getAllAccounts(User u);
	
	Account getAccountByUser(User u);
	//Account a
	void createAccount(Account a, User u) throws SQLException;
	
	void deleteAccount(Account a);
	
	//void makeWithdrawal(User u, int withdrawal);// throws SQLException;

	//public void deposit(User u, int deposit);
	
	//public void checkBalance();

	void transfer();
	//Account makeWithdrawal(User u, int deopsit) throws SQLException;

	void deposit();
	void withdraw();
	void balance();
	
	public List<TransactionDisplay> getAllTransactions();
	
	
	
	

}

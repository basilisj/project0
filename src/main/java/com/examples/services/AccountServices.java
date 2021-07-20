package com.examples.services;

import java.sql.SQLException;
import java.util.List;

import com.example.dao.AccountDao;
import com.example.exceptions.UserNameIsTakenException;
import com.example.logging.Logging;
import com.example.models.Account;
import com.example.models.User;

public class AccountServices {

	private AccountDao aDao;
	public List<Account> getBalance;
	
	public AccountServices(AccountDao a) {
		this.aDao = a;
	}
	//create another exception
	public Account createAccount(int id, int balance, String type) {
		Account a = new Account(id, balance, type);
		User u = new User();
		try {
			aDao.createAccount(a, u);
			Logging.logger.info("User created a new account.");
			
		}catch(SQLException e) {
			Logging.logger.warn("Account number already exists in the database");
			throw new UserNameIsTakenException();
		}
		return a;
	}
	
	//public Account withdraw(int amount) {
	//	Account a = aDao.makeWithdrawal(a);
		//try {
		//	aDao.makeWithdrawal(a);
		//	Logging.logger.info("User made a withdrawal.");
	///	}ca
//	}
	
	
}

package com.examples.services;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.example.dao.UserDao;
import com.example.exceptions.InvalidCredentialsException;
import com.example.exceptions.UserDoesNotExistException;
import com.example.exceptions.UserNameIsTakenException;
import com.example.logging.Logging;
import com.example.models.User;

public class UserServices {
	
	private UserDao uDao;
	
	public UserServices(UserDao u) {
		this.uDao = u;
	}
	
	public User signUp(String first, String last, String email, 
			String phone, String password) throws UserNameIsTakenException {
		User u = new User(first, last, email, phone, password);
		
		try {
			uDao.createUser(u);
			Logging.logger.info("New user " + first + " "+ last + " has registered");
		} catch (SQLException e) {
			Logging.logger.warn("Username created that already exists in the database");
			throw new UserNameIsTakenException();			
		}
		
		
		return u;
	}
	
	public User signIn(String username, String password) throws UserDoesNotExistException, InvalidCredentialsException{
		User u = uDao.getUserByUsername(username);
		if(u.getId()==0) {
			Logging.logger.warn("Username does not exists.");
			throw new UserDoesNotExistException();
		}else if(!u.getPassword().equals(password)) {
			Logging.logger.warn("Username inputed invalid credentials.");
			throw new InvalidCredentialsException();
		}else {
			Logging.logger.info("User was logged in.");
			return u;
		}
	}
	
	
	
	
}

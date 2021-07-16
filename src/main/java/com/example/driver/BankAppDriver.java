package com.example.driver;

import com.example.dao.UserDao;
import com.example.dao.UserDaoDB;
import com.examples.services.UserServices;

public class BankAppDriver {

	public static void main(String[] args) {
		UserDao uDao = new UserDaoDB();
		UserServices uServ = new UserServices(uDao);
		
		uServ.signUp("Bob", "Green", "bobg@mymail.com", "213456789", "please");

	}

}

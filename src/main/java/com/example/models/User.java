package com.example.models;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class User{

	/**
	 * 
	 */
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String phoneNumber;
	private String password;
	private List<Account> accounts;
	//no args-constructor
	public User() {

		accounts = new ArrayList<Account>();
	}
	//parametized constructor
	//used to send user info to the database
	public User(String firstName, String lastName, String email, String phoneNumber, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.username = lastName + firstName +(new Random().nextInt(9000)+1000);
		this.accounts = new ArrayList<Account>();
	}
	//used to get the user info from the database
	public User(int id, String firstName, String lastName, String email, String username, String phoneNumber, String password) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.username = lastName + firstName +(new Random().nextInt(9000)+1000);
		this.accounts = new ArrayList<Account>();
	}
	//getters and setters 
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", username=" + username + ", phoneNumber=" + phoneNumber + ", password=" + password + ", accounts="
				+ accounts + "]";
	}
	
}

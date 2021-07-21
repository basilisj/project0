package com.example.driver;

import java.util.List;
import java.util.Scanner;

import com.example.dao.AccountDao;
import com.example.dao.AccountDaoDB;
import com.example.dao.UserDao;
import com.example.dao.UserDaoDB;
import com.example.exceptions.WithdrawalNegativeBalanceException;
import com.example.models.User;
import com.examples.services.AccountServices;
import com.examples.services.UserServices;
import com.example.models.Account;
import com.example.models.TransactionDisplay;

public class BankAppDriver {

	private static UserDao uDao = new UserDaoDB();
	private static AccountDao aDao = new AccountDaoDB();
	private static UserServices uServ = new UserServices(uDao);
	private static AccountServices aServ = new AccountServices(aDao);
	//private static Account a = new Account();
	//private static User u;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		AccountDaoDB accountDaoDB = new AccountDaoDB();
		
		
	
boolean done = false;
		
		User u = null;
		//Account a = null;
		while(!done) {
			if(u == null) {
				System.out.println("Welcome to YourBank!!!\n\n" +"Login or Signup?\n\n" +"Press 1 to Login\n" +"Press 2 to Signup");
				int choice = Integer.parseInt(scan.nextLine());
				if(choice == 1) {
					System.out.print("Please enter your username: ");
					String username = scan.nextLine();
					System.out.print("Please enter your password: ");
					String password = scan.nextLine();
					try {
						u = uServ.signIn(username, password);
						System.out.println("Welcome " + u.getFirstName());
				/*		
				do {
						System.out.println("What would you like to do?");
						
						System.out.println("1. Check balance.");
						System.out.println("2. Deposit");
						System.out.println("3. Withdraw");
						System.out.println("4. Transfer funds");
						System.out.println("5. Exit");
						choice = scan.nextInt();
						switch(choice) {
						case 1:
							System.out.println("Balance = "+ a.getBalance());
							break;
						case 2:
							int amount;
							System.out.println("Amount to deposit: ");
							amount = scan.nextInt();
							if (amount <=0)
								System.out.println("Can't deposit a negative amount.");
							else {
								//balance += amount;
								System.out.println( a.getBalance() + amount + "has been deposited");
							}
							break;
						case 3:
							System.out.println("Amount to withdraw: ");
							amount = scan.nextInt();
							if (amount <= 0 || amount> balance)
								System.out.println("Withdrawal can't be processed.");
							else {
								balance -=amount;
								System.out.println("$" + amount + "has been withdrawn.");
							}
							break;
						case 4:
							break;
						case 5:
							done = true;
							break;
							default:
								System.out.println("Wrong choice.");
						
						}
						
							
						}while(!done);
						
				*/
						} catch(Exception e) {
						System.out.println("Username or password was incorect. Goodbye");
						done = true;
					}
				} else {
					System.out.print("Please enter you first name: ");
					String first = scan.nextLine();
					System.out.print("Please enter your last name: ");
					String last = scan.nextLine();
					System.out.print("Please enter your email: ");
					String email = scan.nextLine();
					System.out.println("Please enter your 10 digit phone number.");
					String phone = scan.nextLine();
					System.out.print("Please enter a password: ");
					String password = scan.nextLine();
					try {
						u = uServ.signUp(first, last, email, phone, password);
						System.out.println("You may now log in with the username: " + u.getUsername()+"\n Please go back to the login page.");
						
						break;
					} catch (Exception e) {
						System.out.println("Sorry, we could not process your request");
						System.out.println("Please try again later");
						done = true;
					}
				}
			} else {
				System.out.println("What would you like to do?\n");
				System.out.println("1. View account balance.\n"
						+ "2. Create a bank account.\n"
						+ "3. Deposit\n"
						+ "4. Withdraw\n"
						+ "5. Transfer\n"
						+ "6. Log out");
				int choice = Integer.parseInt(scan.nextLine());
				switch(choice) {
				case 1:
					accountDaoDB.balance();
										
					break;
				case 2:
					System.out.println("To create a bank account you need an initial balance and a type of account (checkings/savings)\n");
				System.out.println("Please insert you initial deopsit amount: ");
				int balance = Integer.parseInt(scan.nextLine());
				System.out.println("Please specify what type of account you want: ");
				String accountType = scan.nextLine();
				aServ.createAccount(u.getId(), balance, accountType);
				System.out.println("Are you finished?\n\n Press 1 for yes, press 2 for no.");
				choice = Integer.parseInt(scan.nextLine());
				done = (choice == 1)? true : false;
				break;
				case 3:
					accountDaoDB.deposit();
					
					break;
				case 4:
					accountDaoDB.withdraw();
					
					break;
				case 5:
					accountDaoDB.transfer();
					break;
				case 6:
					done = true;
					break;
				case 7:
					
					List<TransactionDisplay> transa = aServ.getAllTransaction();
					for(TransactionDisplay tran: transa) {
						System.out.println(aServ.getAllTransaction());
					}
					break;
					default:
						System.out.println("Incorrect input, please try again.");
						break;
			}
				
			
		}

		
	
		}	
		scan.close();
		System.out.println("Goodbye :)");
	}
}

/*
System.out.println("To view posts press 1, to create a post press 2");
int choice = Integer.parseInt(in.nextLine());
//If the user chooses 1, we will show them the list of posts
if(choice == 1) {
	List<PostDisplay> posts = pServ.getAllPosts();
	for(PostDisplay post: posts) {
		System.out.println(post.getUsername() + ":");
		System.out.println(post.getContent());
		System.out.println();
	}
	System.out.println("Are you finished? Press 1 for yes, press 2 for no");
	choice = Integer.parseInt(in.nextLine());
	done = (choice == 1) ? true : false;
} else {
	System.out.println("Please enter your content below:");
	String content = in.nextLine();
	pServ.addPost(u.getId(), u.getId(), content);
	System.out.println("Post was received, are you finished? Press 1 for yes, press 2 for no");
	choice = Integer.parseInt(in.nextLine());
	done = (choice == 1) ? true : false;
}System.out.println("What would you like to do?");
						char option = '\0';
						System.out.println("A. Check balance.");
						System.out.println("B. Deposit");
						System.out.println("C. Withdraw");
						System.out.println("D. Transfer funds");
						System.out.println("E. Exit");
						
						do
						{
							option = scan.next().charAt(0);
						
						switch(option) {
						case 'A':
							System.out.println("Balance = "+ balance);
						
						}
do {
	System.out.println("What would you like to do?");
	choice = scan.nextInt();
	System.out.println("1. Check balance.");
	System.out.println("2. Deposit");
	System.out.println("3. Withdraw");
	System.out.println("4. Transfer funds");
	System.out.println("5. Exit");
	
	switch(choice) {
	case 1:
		System.out.println("Balance = "+ a.getBalance());
		break;
	case 2:
		int amount;
		System.out.println("Amount to deposit: ");
		amount = scan.nextInt();
		if (amount <=0)
			System.out.println("Can't deposit a negative amount.");
		else {
			//balance += amount;
			System.out.println( a.getBalance() + amount + "has been deposited");
		}
		break;
	case 3:
		System.out.println("Amount to withdraw: ");
		amount = scan.nextInt();
		if (amount <= 0 || amount> balance)
			System.out.println("Withdrawal can't be processed.");
		else {
			balance -=amount;
			System.out.println("$" + amount + "has been withdrawn.");
		}
		break;
	case 4:
		break;
	case 5:
		done = true;
		break;
		default:
			System.out.println("Wrong choice.");
	
	}
	
		
	}while(!done);
*/
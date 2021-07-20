package com.example.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.logging.Logging;
import com.example.models.Account;
import com.example.models.User;
import com.example.utils.ConnectionUtil;

public class AccountDaoDB implements AccountDao{
public Scanner scan = new Scanner(System.in);
	private ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();
	
	@Override
	public List<Account> getAllAccounts() {
	List<Account> accountList = new ArrayList<Account>();
		
	try {
	Connection con = conUtil.getConnection();
		String sql = "SELECT * FROM account";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			accountList.add(new Account(rs.getInt(1), rs.getInt(2),
					rs.getInt(3), rs.getString(4)));
		}
		return accountList;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
		
	}

	

	@Override
	public Account getAccountByUser(User u) {

		List<Account> accList = new ArrayList<Account>();
		
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "SELECT * FROM account WHERE id = '" + u.getId() +"'";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Account a = new Account(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
				accList.add(0, a);
			}
			return accList.get(0);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	
	@Override
	public void createAccount(Account a, User u) throws SQLException {
		try {
			Connection con = conUtil.getConnection();
			//String sql = "INSERT INTO account(acc_number, acc_balance, acc_type)"
			//		+ "values (?,?,?)";
			//PreparedStatement ps = con.prepareStatement(sql);
			con.setAutoCommit(false);			
			String sql = "call create_account(?,?,?)";
			CallableStatement cs = con.prepareCall(sql);
			
			cs.setInt(1, a.getId());
			cs.setInt(2, a.getBalance());
			cs.setString(3, a.getAccountType());
			
			cs.execute();
					
			con.setAutoCommit(true);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteAccount(Account a) {
		try {
			Connection con = conUtil.getConnection();
			String sql = "DELETE FROM account WHERE account.acc_number = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, a.getAccountNumber());
			
			ps.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}



	
	@Override
	public void makeWithdrawal(User u, int withdrawal) { //throws SQLException {
		Account a = getAccountByUser(u);
		try {
			Connection con = conUtil.getConnection();
			String sql = "UPDATE account set acc_balance=? WHERE id=?";
					
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, a.getBalance() - withdrawal);
			ps.setInt(2, u.getId());
			
			
			ps.execute();
					
		}catch(SQLException e) {
			e.printStackTrace();	
			
	}
	


	}



	@Override
	public void deposit(User u, int deposit) {
		Account a = getAccountByUser(u);
		try {
			Connection con = conUtil.getConnection();
			String sql = "UPDATE account set acc_balance=? WHERE id=?";
					
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, deposit + a.getBalance());
			ps.setInt(2, u.getId());
			
			
			ps.execute();
					
		}catch(SQLException e) {
			e.printStackTrace();	
		}			
	}



	@Override
	public void checkBalance() {
		 try {
	            // int id
	            System.out.println("Enter customer id   ? ");
	            int id = scan.nextInt();
	            try {
	            	Connection con = conUtil.getConnection();
	                // create a statement object to send to database
	                String sql = "SELECT * FROM users where id = ? ";
	                PreparedStatement preparedStatement = con.prepareStatement(sql);
	                // prepare all data before insert it
	                preparedStatement.setInt(1, id);
	                // return 0 if not query compete  Or 1 if not
	                ResultSet result = preparedStatement.executeQuery();

	                if (result.next())
	                {
	                    System.out.println("Current Balance = " + result.getInt("balance"));
	                }else
	                {
	                    System.out.println(" Customer not found ");
	               //     ConsoleController.sleepAndReShowMenu(bankName);
	                }
	                preparedStatement.close();
	            } catch (SQLException throwables) {
	                System.out.println(" error from database   ");
	            }
	        } catch (Exception e) {
	            System.out.println("Sorry Input error ");
	        }

	    }



	@Override
	public void transfer() {
		System.out.print("Please enter the account number:");
        int acc_num = scan.nextInt();
        System.out.print("Please enter the transfer amount:");
        int money = scan.nextInt();
        if (money > 0) {
            String sql1 = "SELECT acc_balance FROM account WHERE acc_number = ?";
            Connection con = conUtil.getConnection();
            try {
                PreparedStatement ps= con.prepareStatement(sql1);
                ps.setInt(1, acc_num);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int acc_balance = rs.getInt("acc_balance");
                    if (money <= acc_balance) {
                        System.out.print("Please enter the receiving account number number:");
                        int toAcc_num = scan.nextInt();
                        String sql2 = "SELECT acc_balance FROM account WHERE acc_number = ?";
                        PreparedStatement s = con.prepareStatement(sql2);
                        s.setInt(1, toAcc_num);
                        rs = s.executeQuery();
                        if (rs.next()) {
                            String sql3 = "UPDATE account SET acc_balance = acc_balance - ? where acc_number = ?";
                            PreparedStatement p= con.prepareStatement(sql3);
                            p.setInt(1, money);
                            p.setInt(2, acc_num);
                            p.executeUpdate();
                            String sql4 = "UPDATE account SET acc_balance = acc_balance + ? WHERE acc_number = ?";
                            PreparedStatement pt = con.prepareStatement(sql4);
                            pt.setInt(1, money);
                            pt.setInt(2, toAcc_num);
                            pt.executeUpdate();
                            Logging.logger.info("Successful transfer ");
                            System.out.println("The transfer is successful!");
                        } else {
                        	Logging.logger.warn("The recieving account number does not exists.");
                            System.out.println("The receiving account number does not exist!");
                        }
                    } else {
                        System.out.println("The card balance is insufficient!");
                    }
                } else {
                    System.out.println("The account number is wrong!");
                  //  System.out.println("Please re-verify and enter the correct transfer amount!");
                   // this.transfer();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } //finally {
               // try {
               //    if (rs != null) {
                //        rs.close();
                //   }
                //   if (preparedStatement != null) {
               //         .close();
                //  }
               // } catch (SQLException e) {
               //     e.printStackTrace();
             //  }
          //  }
       } else {
           System.out.println("Please re-verify and enter the correct transfer amount!");
            this.transfer();
        }
		
	}

		
	
	
	

}

package ssa;

import java.util.ArrayList;
import java.util.List;

public class Mainline {

	public static void main(String[] args) {	
		/*
		 *  Test the Checking Account according to the instructions
		 * 
		 */
		
		System.out.println("Now testing the functionality of a checking account:");
		System.out.println("======================================");
		
		System.out.println("Creating a checking account, initializing the account number to 100, " + 
		                   "setting the description to \"My personal checking account\",\n" + 
				           "and setting the initial amount to $500.00\n");
		
		Account checking = new CheckingAccount(500.00, "My personal checking account");		
		Testchecking(checking);
		
		System.out.println("======================================");
		System.out.println("======================================");
		System.out.println("======================================");
		System.out.println("Now testing the functionality of a savings account:");
		System.out.println("======================================");
		
		System.out.println("Creating a savings account, intializing the account number to 200, " +
		                   "setting the description to \"My personal savings account\",\n" +
				           "and setting the initial amount to $1000.00");
		
		// Initialize interest rate to the national average of 0.06%
		Account savings = new SavingsAccount(1000.00, 0.06, "My personal savings account");
		TestSavingsAccount(savings);
		
		System.out.println("======================================");
		System.out.println("======================================");
		System.out.println("======================================");
		System.out.println("Now displaying both accounts at the end... using polymorphism!");
		System.out.println("======================================");
		
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(checking);
		accounts.add(savings);
		
		// Polymorphism at work
		for(Account account : accounts) {
			// Will automatically call the toString() method of the appropriate type of account
			System.out.println(account);
		}
		
		System.out.println("Transfer 100.00 from savings to checking");
		savings.Transfer(checking, 100.00);
		
		System.out.println("======================================");
		System.out.println("======================================");
		System.out.println("======================================");
		System.out.println("Now displaying both accounts at the end... using polymorphism!");
		System.out.println("======================================");
		
		// Polymorphism at work
		for(Account account : accounts) {
			// Will automatically call the toString() method of the appropriate type of account
			System.out.println(account);
		}
		
		//Transaction t = new Transaction(Transaction.TransactionType.DEP, 20.00, 120.00, 100.00);
		//System.out.println(t);
	}
	
	private static void Testchecking(Account checking) {
		System.out.println("********************************************************");
		System.out.println("Display the details of checking at this point:\n");
		
		// Will automatically call the toString() method
		System.out.println(checking);
		System.out.println("********************************************************");
		
		System.out.println("Deposit another 200.00");
		checking.deposit(200.00);
		
		System.out.println("********************************************************");
		System.out.println("Display the details of checking at this point:\n");
		
		// Will automatically call the toString() method
		System.out.println(checking);
		System.out.println("********************************************************");
		
		System.out.println("Withdraw 600.00");
		checking.withdraw(600.00);
		
		System.out.println("********************************************************");
		System.out.println("Display the details of checking at this point:\n");
		
		// Will automatically call the toString() method
		System.out.println(checking);
		System.out.println("********************************************************");
		
		System.out.println("Deposit another 100.00\n");
		checking.deposit(100.00);
		
		System.out.println("Withdraw 300.00\n");
		checking.withdraw(300.00);
		
		System.out.println("********************************************************");
		System.out.println("Display the details of checking at this point:\n");
		
		// Will automatically call the toString() method
		System.out.println(checking);
		System.out.println("********************************************************");
		
		System.out.println("Withdraw 200.00");
		checking.withdraw(200.00);
		
		System.out.println("********************************************************");
		System.out.println("Display the details of checking at this point:\n");
		
		// Will automatically call the toString() method
		System.out.println(checking);
		System.out.println("********************************************************");		
	}
	
	private static void TestSavingsAccount(Account savings) {
		System.out.println("********************************************************");
		System.out.println("Display the details of savings at this point:\n");
		
		// Will automatically call the toString() method
		System.out.println(savings);
		System.out.println("********************************************************");
		
		System.out.println("Withdraw 750.00");
		savings.withdraw(750.00);
		
		System.out.println("********************************************************");
		System.out.println("Display the details of savings at this point:\n");
		
		// Will automatically call the toString() method
		System.out.println(savings);
		System.out.println("********************************************************");
		
		System.out.println("Withdraw 250.00");
		savings.withdraw(250.00);
		
		System.out.println("********************************************************");
		System.out.println("Display the details of savings at this point:\n");
		
		// Will automatically call the toString() method
		System.out.println(savings);
		System.out.println("********************************************************");
		
		System.out.println("Deposit 200.00");
		savings.deposit(200.00);
		
		System.out.println("********************************************************");
		System.out.println("Display the details of savings at this point:\n");
		
		// Will automatically call the toString() method
		System.out.println(savings);
		System.out.println("********************************************************");
		
		
	}

}

package testaccount;

import static org.junit.Assert.*;

import org.junit.Test;

import ssa.Account;

public class AccountTestMethods {

	@Test
	public void testSetDescription() {
		Account account = new Account();
		
		String description = "This is a description";
		
		account.setDescription(description);
		assertEquals(description, account.getDescription());
	}

	@Test
	public void testDeposit() {
		Account account = new Account();
		
		// Try depositing an amount of 0
		account.deposit(0.0);
		assertEquals(0, account.getBalance(), 0.01); // Balance should still be 0
						
		// Try depositing a negative amount
		account.deposit(-1000);
		assertEquals(0, account.getBalance(), 0.01); // Balance should still be 0
		
		// Deposit a valid amount
		assertEquals(0, account.getBalance(), 0.01);
		account.deposit(10);
		assertEquals(10, account.getBalance(), 0.01); // Balance should be 10
	}

	@Test
	public void testWithdraw() {
		Account account = new Account();
		
		// Try withdrawing from an empty account
		account.withdraw(500.00);
		assertEquals(0, account.getBalance(), 0.01);
				
		// Try withdrawing an amount of 0
		account.withdraw(0);
		assertEquals(0, account.getBalance(), 0.01);
		
		// Try withdrawing a negative amount
		account.withdraw(-100);
		assertEquals(0, account.getBalance(), 0.01);
		
		// Try withdrawing more money than the balance
		account.deposit(100);
		assertEquals(100, account.getBalance(), 0.01);
		account.withdraw(200);
		assertEquals(100, account.getBalance(), 0.01);
		
		// Try withdrawing a valid amount
		account.deposit(100);
		assertEquals(200, account.getBalance(), 0.01);
		account.withdraw(90);
		assertEquals(110, account.getBalance(), 0.01);
	}

	@Test
	public void testTransferFrom() {
		Account account1 = new Account();
		Account account2 = new Account();
		
		account1.deposit(450);
		account2.deposit(700);
		
		// Attempt to transfer to the same account - expect the transfer to fail
		assertEquals(450, account1.getBalance(), 0.01);
		assertEquals(700, account2.getBalance(), 0.01);
		account1.transferFrom(account1, 1);
		assertEquals(450, account1.getBalance(), 0.01);
		assertEquals(700, account2.getBalance(), 0.01);
		
		// Invalid transfer amount - expect the amounts in both accounts to remain 
		// unchanged
		assertEquals(450, account1.getBalance(), 0.01);
		assertEquals(700, account2.getBalance(), 0.01);
		account1.transferFrom(account2, -1);
		assertEquals(450, account1.getBalance(), 0.01);
		assertEquals(700, account2.getBalance(), 0.01);
		
		// Transfer an amount more than is in account1
		// Amounts should remain unchanged
		assertEquals(450, account1.getBalance(), 0.01);
		assertEquals(700, account2.getBalance(), 0.01);
		account2.transferFrom(account1, 500);
		assertEquals(450, account1.getBalance(), 0.01);
		assertEquals(700, account2.getBalance(), 0.01);
		
		// Transfer a valid amount
		assertEquals(450, account1.getBalance(), 0);
		assertEquals(700, account2.getBalance(), 0);
		account1.transferFrom(account2, 100);
		assertEquals(550, account1.getBalance(), 0.01);
		assertEquals(600, account2.getBalance(), 0.01);
		
		// Transfer a valid amount
		assertEquals(550, account1.getBalance(), 0);
		assertEquals(600, account2.getBalance(), 0);
		account2.transferFrom(account1, 200);
		assertEquals(350, account1.getBalance(), 0.01);
		assertEquals(800, account2.getBalance(), 0.01);
	}
	
	@Test
	public void testPrint() {
		Account account = new Account(1212, "My account");
		account.deposit(1600);
		account.withdraw(400);
		
		String printResult = account.print();
		System.out.print(printResult);
		assertEquals("Account 1212 balance is $1,200.00", printResult);
	}
	
	/*
	 * Test Scenarios
	 */
	
	@Test
	public void testScenario1() {
		Account account = new Account();
		
		account.deposit(500);     // Deposit 500 
		account.deposit(50);      // Deposit 50
		account.withdraw(300);    // Withdraw 300
		account.withdraw(249.99); // Withdraw 249.99
						
		// Balance should be 0.01
		assertEquals(0.01, account.getBalance(), 0.01);		
	}
	
	@Test
	public void testScenario2() {
		// create checking1 - default constructor
		Account checking1 = new Account();
		checking1.setDescription("Checking 1");
		
		// Check checking1
		assertEquals("Checking 1", checking1.getDescription());
		
		checking1.withdraw(100.00); // bal 0
		checking1.deposit(500.00); // bal 500
		checking1.deposit(200.00); // bal 700
		checking1.setDescription("Checking 1 Modified");
		checking1.deposit(200.00); // bal 900
		checking1.withdraw(900); // bal 0
		checking1.deposit(1.03); // bal 1.03
		
		// Checking new description of checking1
		assertEquals("Checking 1 Modified", checking1.getDescription());
		
		// Check the balance at this point and use a tolerance
		assertEquals(1.03, checking1.getBalance(), 0.01);

		// create checking2 - description constructor
		Account checking2 = new Account("Checking 2");
		checking2.setDescription("Checking 1");
		checking2.withdraw(100.00); // bal 0
		checking2.deposit(500.00); // bal 500
		checking2.deposit(200.00); // bal 700
		checking2.setDescription("Checking 1 Modified");
		checking2.deposit(200.00); // bal 900
		checking2.withdraw(900.00); // bal 0
		checking2.deposit(2.02); // bal 2.02

		// Check the balance at this point and use a tolerance
		assertEquals(2.02, checking2.getBalance(), 0.01);
		
		// create savings3 - description constructor
		Account savings3 = new Account("Savings 3");
		savings3.setDescription("Checking 1");
		savings3.withdraw(100.00); // bal 0
		savings3.deposit(300.00); // bal 300
		savings3.deposit(200.00); // bal 500
		savings3.setDescription("Checking 1 Modified");
		savings3.deposit(100.00); // bal 600
		savings3.withdraw(600.00); // bal 0
		savings3.deposit(3.01); // bal 3.01
		
		// Check the balance at this point and use a tolerance
		assertEquals(3.01, savings3.getBalance(), 0.01);

		System.out.println("Transfer amount all accounts ...");
		checking1.transferFrom(checking2, 1.01);
		checking2.transferFrom(savings3, 1.01);
		savings3.transferFrom(checking1, 1.01);

		// Check the individual balances after the transfers
		assertEquals(1.03, checking1.getBalance(), 0.01);
		assertEquals(2.02, checking2.getBalance(), 0.01);
		assertEquals(3.01, savings3.getBalance(), 0.01);
	}
}

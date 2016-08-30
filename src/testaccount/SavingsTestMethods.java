package testaccount;

import static org.junit.Assert.*;

import ssa.Account;
import ssa.Savings;

import org.junit.Test;

public class SavingsTestMethods {

	@Test
	public void testSetMinBalanceForInt() {
		Savings account = new Savings();
		
		account.setMinBalanceForInt(1);
		assertEquals(1, account.getMinBalanceForInt(), 0.01);
		
		// Attempt to set to an invalid value
		account.setMinBalanceForInt(-2);
		assertEquals(1, account.getMinBalanceForInt(), 0.01);
	}

	@Test
	public void testSetInterestRate() {
		Savings account = new Savings();
		
		account.setInterestRate(0.015);
		assertEquals(0.015, account.getInterestRate(false), 0.01); // As a decimal
		assertEquals(1.5, account.getInterestRate(true), 0.01); // As a percent
		
		// Attempt to set to an invalid value
		account.setInterestRate(0);
		assertEquals(0.015, account.getInterestRate(false), 0.01); // As a decimal
	}

	@Test
	public void testCalcDepositInterest() {
		Savings account = new Savings();
		
		account.deposit(1000);
		
		double interest = account.calcDepositInterest(12); // Should be $15		
		assertEquals(15, interest, 0.001);
		assertEquals(1015, account.getBalance(), 0.01);
		
		interest = account.calcDepositInterest(3); // Should be $3.81
		assertEquals(3.81, interest, 0.01);
		assertEquals(1018.81, account.getBalance(), 0.01);
		
		// Will fail due to an invalid value for months
		interest = account.calcDepositInterest(-1); // Should be $0
		assertEquals(0.0, interest, 0.01);
		assertEquals(1018.81, account.getBalance(), 0.01);
	}
	
	@Test
	public void testPrint() {
		Savings account = new Savings(1212, "My account");
		account.deposit(1600);
		account.withdraw(400);
		account.calcDepositInterest(12); // $18
		account.calcDepositInterest(10); // $15.23
		
		String printResult = account.print();
		System.out.print(printResult);
		assertEquals("Account 1212 balance is $1,233.23 and has earned total interest of $33.23", printResult);
	}
}

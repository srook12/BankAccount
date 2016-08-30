package testaccount;

import static org.junit.Assert.*;
import ssa.Checking;

import org.junit.Test;

public class CheckingTestMethods {

	@Test
	// Already tested withdraw extensively for Account
	// Need to verify the correct check numbers
	public void testWithdraw() {
		Checking account = new Checking();
		
		account.deposit(1200);
		
		// Write 10 checks for $100 each and verify the check numbers
		for(int idx = 0; idx < 10; idx++) {
			account.withdraw(100);
			assertEquals((100+idx), account.getCheckNumber());
		}
		
		// Last check should be #109 and $200 should be in the account
		assertEquals(109, account.getCheckNumber());
		assertEquals(200, account.getBalance(), 0.01);
		
		// Test the overloaded withdraw
		account.withdraw(20, 1212);
		assertEquals(1212, account.getCheckNumber());
		assertEquals(180, account.getBalance(), 0.01);
		
		// Write one more check and it should be number 1213 if not specified
		account.withdraw(10);
		assertEquals(1213, account.getCheckNumber());
		assertEquals(170, account.getBalance(), 0.01);
	}
}

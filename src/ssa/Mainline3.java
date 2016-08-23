package ssa;

public class Mainline3 {

	public static void main(String[] args) {
		// Testing the different constructors
		Account account1 = new Account();
		Account account2 = new Account("My description");
		Account account3 = new Account(1212, "Another description");
		Account account4 = new Account();
		
		System.out.println(account1.print());
		System.out.println(account2.print());
		System.out.println(account3.print());
		System.out.println(account4.print());
		
		// Manipulate account1
		account1.withdraw(0.01); // Should fail
		account1.deposit(725.50); // 725.50
		account1.deposit(20000); // 20725.50
		account1.deposit(0.01); // 20725.51
		account1.withdraw(-1); // Should fail
		account1.withdraw(1200.00); // 19525.51
		
		// Manipulate account2
		account2.setDescription("Changed description"); // Is allowed
		account2.deposit(200.00); // 200
		account2.deposit(10.00); // 210
		account2.withdraw(35.00); // 175
		account2.withdraw(200.00); // Fail
		account2.withdraw(100.00); // 75.00
		
		// Manipulate account3
		account3.deposit(500.00);
		
		System.out.println(account1.print());
		System.out.println(account2.print());
		System.out.println(account3.print());
		
		// Do some transfers
		account2.transferFrom(account1, 1500);
		account3.transferFrom(account1, 3000);
		account1.transferFrom(account4, -5); // Should fail
		account1.transferFrom(account4, 1); // Should fail
		
		System.out.println(account1.print()); // 15025.51
		System.out.println(account2.print()); // 1575
		System.out.println(account3.print()); // 3500
		System.out.println(account4.print()); // 0
	}

}

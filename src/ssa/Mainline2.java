package ssa;

public class Mainline2 {

	public static void main(String[] args) {
		// create checking1 - default constructor
		Account checking1 = new CheckingAccount();
//		      checking1.id = 10;
//		      checking1.balance = 1.00;
		int checking1Id = checking1.getId();
		checking1.setDescription("Checking 1");
		checking1.withdraw(100.00); // bal 0
		checking1.deposit(500.00); // bal 500
		checking1.deposit(200.00); // bal 700
		checking1.setDescription("Checking 1 Modified");
		checking1.deposit(200.00); // bal 900
		checking1.withdraw(900); // bal 0
		checking1.deposit(1.03); // bal 1.00

		// create checking2 - description constructor
		Account checking2 = new CheckingAccount("Checking 2");
//		      checking2.id = 20;
//		      checking2.balance = 2.00;
		int checking2Id = checking2.getId();
		checking2.setDescription("Checking 1");
		checking2.withdraw(100.00); // bal 0
		checking2.deposit(500.00); // bal 500
		checking2.deposit(200.00); // bal 700
		checking2.setDescription("Checking 1 Modified");
		checking2.deposit(200.00); // bal 900
		checking2.withdraw(900.00); // bal 0
		checking2.deposit(2.02); // bal 2.02

		// create savings3 - description constructor
		Account savings3 = new SavingsAccount(0.06, "Savings 3");
//		      savings3.id = 20;
//		      savings3.balance = 2.00;
		int savings3Id = savings3.getId();
		savings3.setDescription("Checking 1");
		savings3.withdraw(100.00); // bal 0
		savings3.deposit(300.00); // bal 300
		savings3.deposit(200.00); // bal 500
		savings3.setDescription("Checking 1 Modified");
		savings3.deposit(100.00); // bal 600
		savings3.withdraw(600.00); // bal 0
		savings3.deposit(3.01); // bal 3.01

		// total should be 6.06;
		System.out.printf("Total all accounts is %.2f\n", checking1.getBalance() + checking2.getBalance() + savings3.getBalance());

		System.out.println("Transfer amount all accounts ...");
		checking1.transferFrom(checking2, 1.01);
		checking2.transferFrom(savings3, 1.01);
		savings3.transferFrom(checking1, 1.01);

		// total should be 6.06;
		System.out.printf("Total all accounts is %.2f\n", checking1.getBalance() + checking2.getBalance() + savings3.getBalance());


		//System.out.println(checking1);
		//System.out.println(checking2);
		//System.out.println(savings3);
	}

}

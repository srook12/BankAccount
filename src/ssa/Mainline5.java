package ssa;

public class Mainline5 {

	public static void main(String[] args) {
		double interestEarned;
		
		// Testing all the constructors
		Savings savings1 = new Savings();
		Savings savings2 = new Savings(0.006);
		Savings savings3 = new Savings("My savings 3", 0.017);
		Savings savings4 = new Savings(1212, "My savings 4");
		Savings savings5 = new Savings("My savings 5");
		
		// Do some stuff with savings1
		
		savings1.deposit(-40.00);
		savings1.deposit(0);
		savings1.withdraw(0);
		savings1.deposit(-1.75);
		savings1.withdraw(0.15);
		savings1.setMinBalanceForInt(100.00);
		System.out.println(savings1.print());
		interestEarned = savings1.calcDepositInterest(1);
		System.out.printf("Interest earned on savings1 at this point: $%.2f\n", interestEarned);
		System.out.println(savings1.print());
		
		// Do some stuff with savings2
		savings2.withdraw(3); // Should fail
		savings2.deposit(2000);
		interestEarned = savings2.calcDepositInterest(15);
		System.out.printf("Interest earned on savings2 at this point: $%.2f\n", interestEarned);
		System.out.println(savings2.print());
		savings2.withdraw(200);
		savings2.deposit(300);
		System.out.println(savings2.print());
		interestEarned = savings2.calcDepositInterest(0);
		System.out.printf("Interest earned on savings2 at this point: $%.2f\n", interestEarned);
		System.out.println(savings2.print());
		interestEarned = savings2.calcDepositInterest(15);
		System.out.printf("Interest earned on savings2 at this point: $%.2f\n", interestEarned);
		System.out.println(savings2.print());
		
		savings1.transferFrom(savings2, 400);
		System.out.println(savings1.print());
		System.out.println(savings2.print());
		
		System.out.println(savings2.getInterestRate(false));
		System.out.println(savings2.getInterestRate(true) + "%");
		
	}

}

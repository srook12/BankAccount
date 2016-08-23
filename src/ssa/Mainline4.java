package ssa;

public class Mainline4 {
	
	public static void main(String[] args) {
		CheckingAccount checking1 = new CheckingAccount("My checking1");
		checking1.deposit(100.00);
		System.out.println(checking1.print()); // 100
		checking1.deposit(700);
		System.out.println(checking1.print()); // 800
		checking1.withdraw(50);
		System.out.println(checking1.print()); // 750
		checking1.writeCheck("08/15/16", "Abc. Inc.", 35.65, "Pool supplies");
		System.out.println(checking1.print()); // 714.35
		checking1.writeCheck("08/23/16"," ", 10000, " "); // Should bounce
		checking1.writeCheck("08/20/16", "X Chemicals", 100.00, "Chemical X"); // 614.35
		System.out.println(checking1);
		
		checking1.printChecks();
	}
}

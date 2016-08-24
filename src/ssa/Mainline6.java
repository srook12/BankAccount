package ssa;

public class Mainline6 {
	public static void main(String[] args) {
		TransactionAccount checking1 = new CheckingAccount();
		TransactionAccount savings1 = new SavingsAccount(0.05);
		
		checking1.deposit(200);
		
		System.out.println(checking1);
	}
}

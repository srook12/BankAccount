package ssa;

public class Checking extends Account {
	private int checkNumber;
	
	public static final int STARTING_CHECK_NUM = 100;
	private static int nextCheckNumber = STARTING_CHECK_NUM;
	
	public Checking() {
		// Call to super() is implicit
	}
		
	public Checking(String description) {
		super(description);
	}
		
	public Checking(int id, String description) {
		super(id, description);
	}
		
	public int getCheckNumber() {
		return checkNumber;
	}
	
	private void setCheckNumber(int checkNumber) {
		this.checkNumber = checkNumber;
	}
	
	@Override
	// Only thing different is incrementing the check number - the
	// rest is handled by the withdraw method in Account
	public double withdraw(double amount) {
		return withdraw(amount, nextCheckNumber);
	}
	
	// Overloaded to handle the case where the user submits a check
	// number
	public double withdraw(double amount, int checkNumber) {
		setCheckNumber(checkNumber);
		nextCheckNumber = ++checkNumber;
		return super.withdraw(amount);
	}
	
	// Nothing needed for deposit - exactly the same as in Account
}

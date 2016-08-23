package ssa;

public class CheckingAccount extends Account {	
	// What makes a checking account different from a general account
	private CheckRegistry checkRegistry = new CheckRegistry();
	
	private static final String CHECK_BOUNCED = "Error - the check bounced!";
	
	public CheckingAccount() {
		super();
	}
	
	public CheckingAccount(String description) {
		super(description);
	}
	
	public CheckingAccount(int id, String description) {
		super(id, description);
	}
	
	// Simulate writing a check
	public void writeCheck(String date, String recipient, double amount, String memo) {
		double amountBefore = getBalance();
		double amountAfter = super.withdraw(amount, TransactionType.CHK);
		
		// Check to see whether the withdrawal succeeded
		if(amountAfter < amountBefore) {		
			// On success, add the check to the registry
			checkRegistry.addCheck(new Check(date, recipient, amount, memo));
		} else {
			System.out.println(CHECK_BOUNCED);
		}
	}
	
	// Print the checks written from this account
	public void printChecks() {
		// Automatically calls the toString() method
		System.out.println(checkRegistry);
	}
		
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(super.toString());
		sb.append(String.format("Account Type: Checking\t\t\t\t\t\t\tAccount #: %-12d\n", getId()));
		sb.append("---------------------------------------------------------------------------------------------------\n");
		sb.append(getTransactionLog());
		sb.append(String.format("Current balance: $%-6.2f\n", getBalance()));
		sb.append("==================================================================");
		
		return sb.toString();
	}
}

package ssa;

public class CheckingAccount extends Account {
	public static final String INSUFFICENT_FUNDS = "Insufficient funds! You cannot withdraw $%.2f " +
			                                       "when your checking account contains only $%.2f.\n";
	
	// Open the checking account with the given starting balance and description
	public CheckingAccount(double startingBalance, String description) {
		super(startingBalance, description);
	}
	
	// Allows the user to withdraw from the checking account if sufficient funds are available.
	// If insufficient funds are available, no money is withdrawn and an error message is printed.
	// All specific types of accounts must implement this method.
	
	@Override
	public double withdraw(double amount) {
		// Store in a temporary variable so we don't need multiple calls to getBalance();
		double currentBalance = getBalance();
		
		// Do we have the funds to do the withdrawal?
		if(amount <= currentBalance) {
			setBalance(currentBalance - amount);
			
			// Log the transaction on a successful withdrawal
			logTransaction(new Transaction(Transaction.TransactionType.WD, amount*-1, currentBalance, 
					                       currentBalance - amount));			
		} else {
			System.out.printf(INSUFFICENT_FUNDS, amount, currentBalance);
		}
		
		// Return the updated balance for the account
		return getBalance();
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(super.toString());
		sb.append(String.format("Account Type: Checking\t\t\t\t\t\t\tAccount #: %-12d\n", getAccountId()));
		sb.append("---------------------------------------------------------------------------------------------------\n");
		sb.append(getTransactionLog());
		sb.append(String.format("Current balance: $%-6.2f\n", getBalance()));
		sb.append("==================================================================");
		
		return sb.toString();
	}
}

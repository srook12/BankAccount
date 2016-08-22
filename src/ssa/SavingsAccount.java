package ssa;

public class SavingsAccount extends Account {
	// Expressed as a percentage - any calculations done would need to first convert
	// this to its decimal value
	private double interestRate = 0.0;
	
	// A savings account usually has a set number of maximum transactions per month
	public int numWithdrawals = 0;
	public static final int MAX_WITHDRAWALS_PER_MONTH = 6;
	
	public static final String INSUFFICENT_FUNDS = "Insufficient funds! You cannot withdraw $%.2f " +
                                                   "when your checking account contains only $%.2f.\n";
	public static final String OVER_WITHDRAWAL_LIMIT = "Error! You have made the maximum withdrawals of " +
                                                       MAX_WITHDRAWALS_PER_MONTH + " this month.\n";
	
	public SavingsAccount(double startingBalance, double interestRate, String description) {
		super(startingBalance, description);
		
		this.interestRate = interestRate;
	}
	
	public double withdraw(double amount) {
		// Store in a temporary variable so we don't need multiple calls to getBalance();
		double currentBalance = getBalance();
						
		// Do we have the funds to do the withdrawal?
		if(amount <= currentBalance) {
			
			// For a savings account, the total number of withdrawals must be below a certain threshold
			// per month
			if(numWithdrawals <= MAX_WITHDRAWALS_PER_MONTH) {
				setBalance(currentBalance - amount);
				
				// Increase the number on a successful withdrawal
				numWithdrawals++;
				
				// Log the transaction on a successful withdrawal
				logTransaction(new Transaction(Transaction.TransactionType.WD, amount*-1, currentBalance, 
						                       currentBalance - amount));	
			} else {
				System.out.println(OVER_WITHDRAWAL_LIMIT);
			}				
		} else {
			System.out.printf(INSUFFICENT_FUNDS, amount, currentBalance);
		}
				
		// Return the updated balance for the account
		return getBalance();
	}
	
	public int getNumWithdrawals() {
		return numWithdrawals;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(super.toString());
		sb.append(String.format("Account Type: Savings\t\t\t\t\t\t\tAccount #: %-12d\n" +
		                        "Current Withdrawls/Max Number of Withdrawals per month: %d/%d\t\t" +
				                "Interest Rate: %02.2f%%\n", getAccountId(), numWithdrawals, 
				                MAX_WITHDRAWALS_PER_MONTH, interestRate));
		sb.append("---------------------------------------------------------------------------------------------------\n");
		sb.append(getTransactionLog());
		sb.append(String.format("Current balance: $%-6.2f\n", getBalance()));
		sb.append("==================================================================");
		
		return sb.toString();
	}
}

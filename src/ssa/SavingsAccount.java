package ssa;

public class SavingsAccount extends Account {
	// Expressed as a percentage - any calculations done would need to first convert
	// this to its decimal value
	private double interestRate = 0.0;
	
	// A savings account usually has a set number of maximum transactions per month
	public int numWithdrawals = 0;
	public static final int MAX_WITHDRAWALS_PER_MONTH = 6;
	
	public static final String OVER_WITHDRAWAL_LIMIT = "Error! You have made the maximum withdrawals of " +
                                                       MAX_WITHDRAWALS_PER_MONTH + " this month.\n";
	
	public SavingsAccount(double interestRate) {
		super();
		this.interestRate = interestRate;
	}
	
	public SavingsAccount(double interestRate, String description) {
		super(description);
		this.interestRate = interestRate;
	}
	
	public SavingsAccount(double interestRate, int id, String description) {
		super(id, description);		
		this.interestRate = interestRate;
	}
	
	@Override
	public double withdraw(double amount, TransactionType transactionType) {					
		// For a savings account, the total number of withdrawals must be below a certain threshold
		// per month
		if(numWithdrawals <= MAX_WITHDRAWALS_PER_MONTH) {
			double currentBalance = getBalance();
			double amountAfter = super.withdraw(amount, transactionType);
				
			// Check if the withdraw was successfully executed
			if(amountAfter < currentBalance) {
				// Increase the number on a successful withdrawal
				numWithdrawals++;
			} 
		} else {
			System.out.printf(OVER_WITHDRAWAL_LIMIT);
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
				                "Interest Rate: %02.2f%%\n", getId(), numWithdrawals, 
				                MAX_WITHDRAWALS_PER_MONTH, interestRate));
		sb.append("---------------------------------------------------------------------------------------------------\n");
		sb.append(getTransactionLog());
		sb.append(String.format("Current balance: $%-6.2f\n", getBalance()));
		sb.append("==================================================================");
		
		return sb.toString();
	}
}

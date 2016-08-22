package ssa;

import java.util.ArrayList;
import java.util.List;

/* Basic account class. All classes at our bank have these common basic traits. Specialized
 * accounts can be added simply by extending this class. Made abstract because it makes no sense
 * to create just an Account.
 */
public abstract class Account {
	private int accountId;
	private String description;
	private double balance;
	
	// A list of all transactions relevant to this account
	private List<Transaction> transactions = new ArrayList<Transaction>();

	// Allows unique account numbers to be assigned to any account
	// nextAccountId is static so only one copy is present for ALL types of accounts
	public static final int STARTING_ACCOUNT_ID = 100;
	private static int nextAccountId = STARTING_ACCOUNT_ID;
	
	public static final String TRANSFER_FAILED = "Error - the transfer failed! Please see the specific " +
	                                             "account message.";
	public static final String TRANSFER_SAME_ACCOUNT = "Error - you cannot transfer between the same account!";
	
	public Account(double startingBalance, String description) {
		balance = startingBalance;
		accountId = nextAccountId;
		this.description = description; 
		
		nextAccountId+=100;
	}
	
	// Should be able to view the account number, but never to set it
	public int getAccountId() {
		return accountId;
	}
	
	// Methods to get and set the account number
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	// For our purposes, all accounts provide the depositing functionality by adding the money to the
	// account - so include it in the general Account class
	public double deposit(double amount) {
		double currentBalance = getBalance();
		
		setBalance(balance += amount);

		// Log a deposit transaction
		logTransaction(new Transaction(Transaction.TransactionType.DEP, amount, currentBalance, 
                currentBalance + amount));	
		
		return balance;
	}
	
	// The functionality of withdrawal on the other hand is determined by the type of account
	public abstract double withdraw(double amount);
	
	public double getBalance() {
		return balance;
	}
	
	// In a professional implementation, I would have Account and all account types in one package
	// and main in another package. Then only the methods defined in the specific account types can
	// modify the balance. By following the assignment structure, it IS possible for a user to directly
	// manipulate the balance of the account directly
	protected void setBalance(double balance) {
		this.balance = balance;
	}
	
	// This method will transfer the amount from the calling account to accountTo. If the withdrawal violates
	// the rules of the calling account, the transfer will fail.
	public void Transfer(Account toAccount, double amount) {
		// Make sure these are two different accounts...
		if(this.getAccountId() != toAccount.getAccountId()) {
					
			// this represents the calling account
			double amountBeforeTransfer = this.getBalance();
		
			double amountAfterTransfer = this.withdraw(amount);
		
			// If the withdrawal succeeds, then the balance changed
			if(amountAfterTransfer < amountBeforeTransfer) {
				// Deposit the amount to toAccount
				toAccount.deposit(amount);
			}		
			// If here, the transfer failed
			else {
				System.out.println(TRANSFER_FAILED);
			}
		} else {
			System.out.println(TRANSFER_SAME_ACCOUNT);
		}
	}
	
	// Adds a transaction to the log for this particular account
	public void logTransaction(Transaction transaction) {
		transactions.add(transaction);
	}
	
	// Writes the transaction log to a string and returns it
	public String getTransactionLog() {
		StringBuffer sb = new StringBuffer();
	
		sb.append(String.format("%-5s %-40s %-15s %-15s %15s\n", "Id", "Date & Time", "Description",
				                "Amount", "Balance"));
		sb.append("---------------------------------------------------------------------------------------------------\n");
		for(Transaction transaction : transactions) {
			// Will automatically call the toString method of transaction
			sb.append(transaction).append("\n");
		}
		
		return sb.toString();
	}
		
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Account Statement for ").append(description);
		sb.append("\n==================================================================\n");
		return sb.toString();
	}
}

package ssa;

import java.text.DecimalFormat;

/* Basic account class. All classes at our bank have these common basic traits. Specialized
 * accounts can be added simply by extending this class. Made abstract because it makes no sense
 * to create just an Account.
 */
public class Account {
	private int id;
	private String description;
	private double balance;
	
	// A list of all transactions relevant to this account
	private TransactionLog transactionLog = new TransactionLog();

	// Allows unique account numbers to be assigned to any account
	// nextAccountId is static so only one copy is present for ALL types of accounts
	public static final int STARTING_ACCOUNT_ID = 100;
	public static final int ACCOUNT_ID_INCREMENT = 100;
	private static int nextAccountId = STARTING_ACCOUNT_ID;
	
	public static final double STARTING_BALANCE = 0.0;
	
	public static final String BALANCE_FORMAT = "$#,##0.00";
	
	public static final String DEFAULT_DESCRIPTION = "My basic account";
	
	public static final String TRANSFER_FAILED = "Error - the transfer failed! Please see the specific " +
	                                             "account message.";
	public static final String TRANSFER_SAME_ACCOUNT = "Error - you cannot transfer between the same account!";
	
	public static final String AMOUNT_BELOW_ZERO = "Error - amount must be above 0!";
	
	public static final String INSUFFICENT_FUNDS = "Insufficient funds! You cannot withdraw $%.2f " +
            "when your account contains only $%.2f.\n";
	
	public Account() {
		this(nextAccountId, DEFAULT_DESCRIPTION);
		nextAccountId+=ACCOUNT_ID_INCREMENT;
	}
	
	public Account(String description) {
		this(nextAccountId, description);
		nextAccountId+=ACCOUNT_ID_INCREMENT;
	}
	
	public Account(int id, String description) {
		this.balance = 0.0;
		this.id = id;
		this.description = description;
	}
	
	public Account(double startingBalance, String description) {
		balance = STARTING_BALANCE;
		this.description = description; 
		
		// Account id is internally generated - the user cannot touch it. Once the
		// id is assigned, it should never be changed. Thus, no setter is necessary
		id = nextAccountId;
		nextAccountId+=100;
	}
	
	// Should be able to view the account number, but never to set it
	public int getId() {
		return id;
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
	public double deposit(double amount, TransactionType transactionType) {		
		if(amount <= 0) {
			System.out.println(AMOUNT_BELOW_ZERO);
		} else {
			double currentBalance = balance;
		
			balance += amount;

			// Log a deposit transaction
			transactionLog.logTransaction(new Transaction(transactionType, amount, currentBalance, 
					                      balance));	
		}
		
		return balance;
	}
	
	// For a standard deposit
	public double deposit(double amount) {
		return deposit(amount, TransactionType.DEP);
	}
	
	// Allows the user to withdraw from the checking account if sufficient funds are available.
	// If insufficient funds are available, no money is withdrawn and an error message is printed.
	// All specific types of accounts must implement this method.
	public double withdraw(double amount, TransactionType transactionType) {
		// Store in a temporary variable for later reference
		double currentBalance = balance;
				
		// Do we have the funds to do the withdrawal?
		if(amount <= 0) {
			System.out.println(AMOUNT_BELOW_ZERO);
		} else if(amount <= currentBalance) {
			balance -= amount;
					
			// Log the transaction on a successful withdrawal
			transactionLog.logTransaction(new Transaction(transactionType, amount*-1, 
							              currentBalance, balance));			
		} else {
			System.out.printf(INSUFFICENT_FUNDS, amount, currentBalance);
		}
				
		// Return the updated balance for the account
		return balance;
	}
	
	// Basic withdrawal
	public double withdraw(double amount) {
		return withdraw(amount, TransactionType.WD);
	}
	
	public double getBalance() {
		return balance;
	}
	
	// In a professional implementation, I would have Account and all account types in one package
	// and main in another package. Then only the methods defined in the specific account types can
	// modify the balance. By following the assignment structure, it IS possible for a user to directly
	// manipulate the balance of the account directly
	/*
	private void setBalance(double balance) {
		this.balance = balance;
	} */
	
	public TransactionLog getTransactionLog() {
		return transactionLog;
	}
	
	// This method will transfer the amount from the calling account to accountTo. If the withdrawal violates
	// the rules of the calling account, the transfer will fail.
	public void transferFrom(Account fromAccount, double amount) {
		// Make sure these are two different accounts...
		if(this.getId() != fromAccount.getId()) {
					
			// this represents the calling account
			double amountBeforeTransfer = fromAccount.getBalance();
		
			double amountAfterTransfer = fromAccount.withdraw(amount, TransactionType.TRNS);
		
			// If the withdrawal succeeds, then the balance changed
			if(amountAfterTransfer < amountBeforeTransfer) {	
				// Deposit the amount
				this.deposit(amount, TransactionType.TRNS);
			}		
			// If here, the transfer failed
			else {
				System.out.println(TRANSFER_FAILED);
			}
		} else {
			System.out.println(TRANSFER_SAME_ACCOUNT);
		}
	}
	
	// Required print method
	public String print() {
		StringBuffer sb = new StringBuffer();
		DecimalFormat balanceFormatter = new DecimalFormat(BALANCE_FORMAT);
		
		sb.append("Account ").append(id).append(" balance is ");
		sb.append(balanceFormatter.format(balance));
		
		return sb.toString();
	}
			
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Account Statement for ").append(description);
		sb.append("\n==================================================================\n");
		sb.append("Account id: " + id);
		return sb.toString();
	}
}

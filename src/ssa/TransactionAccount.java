package ssa;

public class TransactionAccount extends Account {
	// A list of all transactions relevant to this account
	private static TransactionLog transactionLog = new TransactionLog();
	
	public TransactionAccount() {
		super();
	}
	
	public TransactionAccount(String description) {
		super(description);
	}
	
	public TransactionAccount(int id, String description) {
		super(id, description);
	}
	
	// For our purposes, all accounts provide the depositing functionality by adding the money to the
	// account - so include it in the general Account class
	public double deposit(double amount, TransactionType transactionType) {		
		double balanceBefore = getBalance();
		double balanceAfter = super.deposit(amount);
		
		// Log transaction if the deposit was successful
		if(balanceAfter > balanceBefore) {		
			transactionLog.logTransaction(new Transaction(getId(), 
					transactionType, amount, balanceBefore, balanceAfter));			
		}
		
		return balanceAfter;
	}
	
	public double deposit(double amount) {
		return deposit(amount, TransactionType.DEP);
	}
	
	// Allows the user to withdraw from the checking account if sufficient funds are available.
	// If insufficient funds are available, no money is withdrawn and an error message is printed.
	// All specific types of accounts must implement this method.
	public double withdraw(double amount, TransactionType transactionType) {
		double balanceBefore = getBalance();
		double balanceAfter = super.withdraw(amount);
		
		// Log transaction if the withdrawal was successful
		if(balanceAfter < balanceBefore) {			
			transactionLog.logTransaction(new Transaction(getId(), transactionType, 
					amount*-1, balanceBefore, balanceAfter));
		}
		
		return balanceAfter;
	}
	
	public double withdraw(double amount) {
		return withdraw(amount, TransactionType.WD);
	}
	
	// This method will transfer the amount from the calling account to accountTo. If the withdrawal violates
	// the rules of the calling account, the transfer will fail.
	public void transferFrom(Account fromAccount, double amount) {
		double toBalanceBeforeTransfer = getBalance();
		double fromBalanceBeforeTransfer = fromAccount.getBalance();
		
		super.transferFrom(fromAccount, amount);
		
		double toBalanceAfterTransfer = getBalance();
		double fromBalanceAfterTransfer = fromAccount.getBalance();
		
		// If the withdrawal succeeds, then the balance changed
		if(fromBalanceAfterTransfer < fromBalanceBeforeTransfer) {	
			// log withdrawal
			transactionLog.logTransaction(new Transaction(fromAccount.getId(), TransactionType.TRNS, 
					amount*-1, fromBalanceBeforeTransfer, fromBalanceAfterTransfer));
			
			// log deposit
			transactionLog.logTransaction(new Transaction(getId(), TransactionType.TRNS, 
					amount, toBalanceBeforeTransfer, toBalanceAfterTransfer));
		}
	}
	
	public TransactionLog getTransactionLog() {
		return transactionLog;
	}
}

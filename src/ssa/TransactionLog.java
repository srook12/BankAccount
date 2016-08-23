package ssa;

import java.util.ArrayList;
import java.util.List;

public class TransactionLog {
	private List<Transaction> transactionLog = new ArrayList<Transaction>();
	
	// Adds a transaction to the log for this particular account
	public void logTransaction(Transaction transaction) {
		transactionLog.add(transaction);
	}
		
	// Writes the transaction log to a string and returns it
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append(String.format("%-5s %-40s %-15s %-15s %15s\n", "Id", "Date & Time", "Description",
					            "Amount", "Balance"));
		sb.append("---------------------------------------------------------------------------------------------------\n");
		for(Transaction transaction : transactionLog) {
			// Will automatically call the toString method of transaction
			sb.append(transaction).append("\n");
		}
			
		return sb.toString();
	}
	
	
}

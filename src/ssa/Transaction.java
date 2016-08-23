package ssa;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {		
	private String date;
	
	private static final String DATE_FORMAT = "MM/dd/yy @ h:mm::ss a";
	
	private TransactionType transType;
	private double amount;
	private double amountBefore;
	private double amountAfter;
	
	private int transactionId;
	
	// Allows for assigning unique transaction ids
	private static final int STARTING_TRANSACTION_ID = 1;
	private static int nextTransactionId = STARTING_TRANSACTION_ID; 
	
	public Transaction(TransactionType transType, double amount, double amountBefore, double amountAfter) {
		SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
		
		this.date = dateFormatter.format(new Date());
		this.transType = transType;
		this.amount = amount;
		this.amountBefore = amountBefore;
		this.amountAfter = amountAfter;
		
		transactionId = nextTransactionId;
		nextTransactionId++;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		DecimalFormat amountFormatter = new DecimalFormat("+$#,##0.00;-$#");
		DecimalFormat balanceFormatter = new DecimalFormat("$#,##0.00");
		
		sb.append(String.format("%-5d %-40s %-15s %-15s %15s\n", transactionId, date, 
				                transType.getDescription(), amountFormatter.format(amount), 
				                balanceFormatter.format(amountBefore)));
		sb.append(String.format("%78s %15s\n", " ", balanceFormatter.format(amountAfter)));
		sb.append("---------------------------------------------------------------------------------------------------");
		
		return sb.toString();
	}
}

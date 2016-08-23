package ssa;

import java.text.DecimalFormat;

public class Check {
	private int checkNum = STARTING_CHECK_NUMBER;
	private int nextCheckNum = STARTING_CHECK_NUMBER;
	private static final int STARTING_CHECK_NUMBER = 100;
	
	private String date;
	private String recipient;
	private double amount;
	private String amountAsText;
	private String memo = "";
	
	private static final String DELIMITER = "---------------------------------------------------------------------\n";
	private static final String AMOUNT_FORMAT = "$#,##0.00";
	
	private static final String CHECK_FORMAT1 = "Check #: %-41d Date: %12s\n\n";
	private static final String CHECK_FORMAT2 = "Recipient: %-39s Amount: %10s\n\n";
	private static final String CHECK_FORMAT3 = "%-" +  AmountToTextUtility.CHECK_TEXT_AMT_LENGTH + "s\n\n";
	private static final String CHECK_FORMAT4 = "%-50s XXXXXXXXX\n";
	
	public Check(String date, String recipient, double amount, 
			     String memo) {
		this.checkNum = nextCheckNum;
		nextCheckNum++;
		
		this.date = date;
		this.recipient = recipient;
		this.amount = amount;
		this.amountAsText = AmountToTextUtility.amountAsText(amount);
		this.memo = memo;
	}
			
	public String toString() {
		StringBuffer sb = new StringBuffer();
		DecimalFormat amountFormatter = new DecimalFormat(AMOUNT_FORMAT);
		
		sb.append(DELIMITER);
		sb.append(String.format(CHECK_FORMAT1, checkNum, date));	
		sb.append(String.format(CHECK_FORMAT2, recipient, 
				  amountFormatter.format(amount)));
		sb.append(String.format(CHECK_FORMAT3, amountAsText));
		sb.append(String.format(CHECK_FORMAT4, memo));
		sb.append(DELIMITER);
		
		return sb.toString();
	}
}

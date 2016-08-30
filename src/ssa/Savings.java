package ssa;

import java.text.DecimalFormat;

public class Savings extends Account {
	private double interestRate = DEFAULT_INTEREST_RATE;	
	private double minBalanceForInt = 0.0;
	private double totalInterestEarned = 0.0;
	
	public static final int INTEREST_PERIOD = 12;
	public static final double DEFAULT_INTEREST_RATE = 0.015;
	
	public static final String ERROR_MONTHS = "Number of months must be more than 0!";
	public static final String BELOW_MIN_BAL = "The balance for this account is below the " +
			"minimum of $%.2f to earn interest.";
	public static final String TOTAL_INT_MESS = " and has earned total interest of %s";
	
	public Savings() {
		super();
	}
	
	public Savings(double interestRate) {
		setInterestRate(interestRate);
	}
	
	public Savings(String description) {
		this(description, DEFAULT_INTEREST_RATE);
	}
	
	public Savings(String description, double interestRate) {
		super(description);
		setInterestRate(interestRate);
	}
	
	public Savings(int id, String description) {
		this(id, description, DEFAULT_INTEREST_RATE);
	}
	
	public Savings(int id, String description, double interestRate) {
		super(id, description);
		setInterestRate(interestRate);
	}
	
	public double getMinBalanceForInt() {
		return minBalanceForInt;
	}

	public void setMinBalanceForInt(double minBalanceForInt) {
		if(minBalanceForInt > 0) {
			this.minBalanceForInt = minBalanceForInt;
		}
	}
	
	public double getTotalInterestEarned() {
		return totalInterestEarned;
	}

	public double getInterestRate(boolean asPercent) {
		double interestRate = this.interestRate;
		
		if(asPercent) {
			interestRate *= 100;
		}
		
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		if(interestRate > 0) {
			this.interestRate = interestRate;
		}
	}
	
	// Calculate the interest and deposit it
	public double calcDepositInterest(int months) {
		double interestEarned = 0.0;
		
		if(getBalance() > minBalanceForInt) {
			if(months > 0) {
				interestEarned = Double.parseDouble(String.format("%.2f", (getInterestRate(false) / INTEREST_PERIOD) * 
						months * getBalance())); 		
				deposit(interestEarned);
				totalInterestEarned += interestEarned;
			} else {
				System.out.println(ERROR_MONTHS);
			}
		} else {
			System.out.println(String.format(BELOW_MIN_BAL, getMinBalanceForInt()));
		}
			
		return interestEarned;
	}
	
	public double calcDepositInterestContinuously(int months) {
		double interestEarned = 0.0;
		double newAmount = 0.0;
		
		if(months > 0) {
			newAmount = getBalance() * Math.pow(Math.E, getInterestRate(false) * 
					(months/INTEREST_PERIOD)); 
			
			interestEarned = newAmount - getBalance();
			deposit(interestEarned);
		} else {
			System.out.println(ERROR_MONTHS);
		}
		
		return interestEarned;
	}
	
	public String print() {
		StringBuffer sb = new StringBuffer();
		DecimalFormat balanceFormatter = new DecimalFormat(BALANCE_FORMAT);
		
		sb.append(super.print()).append(String.format(TOTAL_INT_MESS, 
				balanceFormatter.format(getTotalInterestEarned())));
		
		return sb.toString();
	}
}

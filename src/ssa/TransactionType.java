package ssa;

public enum TransactionType { WD ("Withdrawal"), 
		                      DEP ("Deposit"),
		                      CHK ("Check"),
		                      TRNS ("Transfer");
	
	public String description;
		
	TransactionType(String description) { this.description = description; }
		
	public String getDescription() { return description; }		
}

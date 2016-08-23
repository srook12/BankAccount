package ssa;

// Utility class to convert a check amount to text
public class AmountToTextUtility {
	// For parsing dollar amounts to words
	private static final String THOUSAND = " Thousand";
	private static final String HUNDRED = " Hundred";
	private static final String AND = " and ";
	private static final String DASH = "-";
	private static final String COMMA = ", ";
	private static final String DOLLARS = " Dollars";
	private static final String ZERO_CENTS = "xx";
	private static final String CENTS_OUT_OF_100 = "/100";
		
	private static final String ONE_TEXT = "One";
	private static final String ONE_AMT = "1";
	private static final String TWO_TEXT = "Two";
	private static final String TWO_AMT = "2";
	private static final String THREE_TEXT = "Three";
	private static final String THREE_AMT = "3";
	private static final String FOUR_TEXT = "Four";
	private static final String FOUR_AMT = "4";
	private static final String FIVE_TEXT = "Five";
	private static final String FIVE_AMT = "5";
		
	private static final String SIX_TEXT = "Six";
	private static final String SIX_AMT = "6";
	private static final String SEVEN_TEXT = "Seven";
	private static final String SEVEN_AMT = "7";
	private static final String EIGHT_TEXT = "Eight";
	private static final String EIGHT_AMT = "8";
	private static final String NINE_TEXT = "Nine";
	private static final String NINE_AMT = "9";
	private static final String TEN_TEXT = "Ten";
	private static final String TEN_AMT = "10";
		
	private static final String ELEVEN_TEXT = "Eleven";
	private static final String ELVEN_AMT = "11";
	private static final String TWELVE_TEXT = "Twelve";
	private static final String TWELVE_AMT = "12";
	private static final String THIRTEEN_TEXT = "Thirteen";
	private static final String THIRTEEN_AMT = "13";
	private static final String FOURTEEN_TEXT = "Fourteen";
	private static final String FOURTEEN_AMT = "14";
	private static final String FIFTEEN_TEXT = "Fifteen";
	private static final String FIFTEEN_AMT = "15";
		
	private static final String SIXTEEN_TEXT = "Sixteen";
	private static final String SIXTEEN_AMT = "16";
	private static final String SEVENTEEN_TEXT = "Seventeen";
	private static final String SEVENTEEN_AMT = "17";
	private static final String EIGHTEEN_TEXT = "Eighteen";
	private static final String EIGHTEEN_AMT = "18";
	private static final String NINETEEN_TEXT = "Nineteen";
	private static final String NINETEEN_AMT = "19";
	
	private static final String TWENTY_TEXT = "Twenty";
	private static final String THIRTY_TEXT = "Thirty";
	private static final String FORTY_TEXT = "Forty";
	private static final String FIFTY_TEXT = "Fifty";
	private static final String SIXTY_TEXT = "Sixty";
	private static final String SEVENTY_TEXT = "Seventy";
	private static final String EIGHTY_TEXT = "Eighty";
	private static final String NINETY_TEXT = "Ninety";
	
	public static final int CHECK_TEXT_AMT_LENGTH = 65;
	
	public static String amountAsText(double amount) {
		StringBuffer sb = new StringBuffer();
		int dollarAmount = (int) amount;
				
		if((dollarAmount / 1000) > 0) {
			sb.append(convertDollarAmountToText(dollarAmount / 1000)).append(THOUSAND).append(COMMA);
			
			dollarAmount %= 1000;
		}
		
		if((dollarAmount / 100) > 0) {
			sb.append(convertDollarAmountToText(dollarAmount / 100)).append(HUNDRED).append(COMMA);
			dollarAmount %= 100;
		}
		
		sb.append(convertDollarAmountToText(dollarAmount)).append(DOLLARS).append(AND);
		
		String centsPortion = convertCentsPortionToText(amount);
		int numDashes = CHECK_TEXT_AMT_LENGTH - (sb.length() + centsPortion.length());
		
		for(int i = 1; i <= numDashes; i++) {
			sb.append(DASH);
		}
		
		sb.append(centsPortion);
				
		return sb.toString();
	}
	
	private static String convertDollarAmountToText(int dollarAmount) {
		StringBuffer sb = new StringBuffer();
		String dollarString = String.valueOf(dollarAmount);
		
		if(dollarAmount < 10) {
			sb.append(singleDigit(dollarAmount));			
		} else if(dollarAmount < 20) {
			switch(dollarString) {
				case TEN_AMT:
					sb.append(TEN_TEXT);
					break;
				case ELVEN_AMT:
					sb.append(ELEVEN_TEXT);
					break;
				case TWELVE_AMT:
					sb.append(TWELVE_TEXT);
					break;
				case THIRTEEN_AMT:
					sb.append(THIRTEEN_TEXT);
					break;
				case FOURTEEN_AMT:
					sb.append(FOURTEEN_TEXT);
					break;
				case FIFTEEN_AMT:
					sb.append(FIFTEEN_TEXT);
					break;
				case SIXTEEN_AMT:
					sb.append(SIXTEEN_TEXT);
					break;
				case SEVENTEEN_AMT:
					sb.append(SEVENTEEN_TEXT);
					break;
				case EIGHTEEN_AMT:
					sb.append(EIGHTEEN_TEXT);
					break;
				case NINETEEN_AMT:
					sb.append(NINETEEN_TEXT);
					break;
			} 
		} else {
			if(dollarAmount < 30) {
				sb.append(TWENTY_TEXT);
			} else if(dollarAmount < 40) {
				sb.append(THIRTY_TEXT);
			} else if(dollarAmount < 50) {
				sb.append(FORTY_TEXT);
			} else if(dollarAmount < 60) {
				sb.append(FIFTY_TEXT);
			} else if(dollarAmount < 70) {
				sb.append(SIXTY_TEXT);
			} else if(dollarAmount < 80) {		
				sb.append(SEVENTY_TEXT);
			} else if(dollarAmount < 90) {
				sb.append(EIGHTY_TEXT);
			} else {
				sb.append(NINETY_TEXT);
			}
		
			// append the hyphened portion of the number if necessary
			sb.append(hyphenedPortion(dollarAmount));
		}
		
		return sb.toString();
	}
	
	// Converts the cents on the check into a textual format
	private static String convertCentsPortionToText(double amount) {
		String dollarString = String.valueOf(amount);
		String centsPortionString = dollarString.split("\\.")[1];
		
		if(centsPortionString.equals("0")) {
			centsPortionString = ZERO_CENTS;
		}
		
		return centsPortionString + CENTS_OUT_OF_100;
	}
	
	private static String singleDigit(int digit) {
		String text = "";
		String stringDigit = String.valueOf(digit);
		
		switch(stringDigit) {
			case ONE_AMT:
				text = ONE_TEXT;
				break;
			case TWO_AMT:
				text = TWO_TEXT;
				break;
			case THREE_AMT:
				text = THREE_TEXT;
				break;
			case FOUR_AMT:
				text = FOUR_TEXT;
				break;
			case FIVE_AMT:
				text = FIVE_TEXT;
				break;
			case SIX_AMT:
				text = SIX_TEXT;
				break;
			case SEVEN_AMT:
				text = SEVEN_TEXT;
				break;
			case EIGHT_AMT:
				text = EIGHT_TEXT;
				break;
			case NINE_AMT:
				text = NINE_TEXT;
				break;
		}
		
		return text;
	}
	
	private static String hyphenedPortion(int dollarAmount) {
		StringBuffer sb = new StringBuffer();
		int onesDigit = dollarAmount % 10;
		
		if(onesDigit > 0) {
			sb.append(DASH).append(singleDigit(onesDigit));
		}
		
		return sb.toString();
	}
}

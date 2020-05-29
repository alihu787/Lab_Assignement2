package operation;

import java.nio.channels.NonReadableChannelException;
import java.util.Scanner;

import domain.RatePayer;

/**
 * @author Takeogh
 * @version 1.0
 * 
 */
public class AddRatePayer extends FunctionalDialog {
	
	private static final int MAX_NO_USER_INPUTS = 6;
	private static final String NAME_PROMPT = "What is the Rate Payer's name? ";
	private static final String ADDRESS_PROMPT = "What is the Rate Payer's address? ";
	private static final String POSTCODE_PROMPT = "What is the Rate Payer's PostCode? ";
	private static final String PHONE_PROMPT = "What is the Rate Payer's phone (Optional)? ";
	private static final String TYPE_PROMPT = "What is the Rate Payer's business type (Public or private etc)? ";
	private static final String CHARITY_PROMPT = "Does Rate Payer do charity? ";
	private String nameString;
	private String addressString;
	private String postcodeString;
	private String phoneString;
	private String typeString;
	private boolean charity;
	


	public AddRatePayer(Scanner console) {
		super(MAX_NO_USER_INPUTS, console);
	}

	@Override
	public void obtainInput(int i) {
		switch (i)
		{
		case 0:
			System.out.println(NAME_PROMPT);
			nameString = getScanner().nextLine();
			break;
		case 1:
			System.out.println(ADDRESS_PROMPT);
			addressString = getScanner().nextLine();
			break;
		case 2:
			System.out.println(POSTCODE_PROMPT);
			postcodeString = getScanner().nextLine();
			break;
		case 3:
			System.out.println(PHONE_PROMPT);
			phoneString = getScanner().nextLine();
			break;
		case 4:
			System.out.println(TYPE_PROMPT);
			typeString = getScanner().nextLine();
			break;
		case 5:
			charity = obtainBooleanInput(CHARITY_PROMPT);
			break;
		}
		
	}

	private boolean obtainBooleanInput(String prompt) {
		System.out.println(prompt);
		return validateBoolean();
	}

	private boolean validateBoolean(){
		boolean userInput;
		System.out.print("Enter a selection -(true or false)");

		if (!getScanner().hasNextBoolean()) {
			userInput = false;
			System.out.println("Invalid choice. Assuming false.");
		}	
		else
			userInput = getScanner().nextBoolean();	// obtain the input
		getScanner().nextLine();					// gets rid of the newline after the input we just read
		System.out.println();		                // put a space before the next output	
		return userInput; 

	}

	@Override
	public void respondToInput() {
		RatePayer ratePayer = new RatePayer(nameString, addressString, postcodeString, phoneString, typeString, charity);
		System.out.println(ratePayer.toString());
		System.out.println("Rate Payer Added Successfully");
		setStillRunning(false);
		
	}

}

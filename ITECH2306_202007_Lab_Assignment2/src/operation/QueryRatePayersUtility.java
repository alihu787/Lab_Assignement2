package operation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import domain.Property;
import domain.RatePayer;
import domain.serializationAndDeserializationProperties;
import domain.serializingAndDeserializingRatePayers;

public class QueryRatePayersUtility extends FunctionalDialog{
	
	serializingAndDeserializingRatePayers ratePayersSeirzlize = new serializingAndDeserializingRatePayers();
	serializationAndDeserializationProperties propertiesSerializer = new serializationAndDeserializationProperties();
	
	ArrayList<RatePayer> ratePayers;
	ArrayList<Property> properties;
	int ratePayerNumber;
	String name;
	

	public QueryRatePayersUtility( Scanner console) {
		super(1, console);

		try {
			ratePayersSeirzlize.serializeRatePayers();
			ratePayersSeirzlize.deceriazlizeRatePayers();
			ratePayers = ratePayersSeirzlize.getRatePayers();
			
			propertiesSerializer.serializeProperties();
			propertiesSerializer.deceriazlizeProperties();
			properties = propertiesSerializer.getProperties();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void obtainInput(int i) {
		
		System.out.println("0.  Exit");
		ratePayersSeirzlize.printRatePayers();
		
		switch (i) {
		case 0:
			ratePayerNumber = obtainIntInput(ratePayers.size(), "\nSelect ratepayer from the above ratepayers.");
			if (ratePayerNumber == 0)
				setStillRunning(false);
			break;
			
		default:
			break;
		}
		
	}
	
	private int obtainIntInput(int max, String prompt) {
		System.out.println(prompt);
		return validateInt(0, max);
	}
	
	private int validateInt(int min, int max) {
		int userInput;
		do {
			System.out.print("Enter a selection ("+min + "-" + max +"):");
			if (!getScanner().hasNextInt())
				userInput = max+1;
			else
				userInput = getScanner().nextInt();	// obtain the input
			getScanner().nextLine();					// gets rid of the newline after the number we just read
			if (userInput < min || userInput > max)
				System.out.println("Invalid choice.");
		} while (userInput < min || userInput > max);
		System.out.println();		// put a space before the next output	
		return userInput; 

	}

	@Override
	protected void respondToInput() {
			
			name = ratePayers.get(ratePayerNumber-1).getName();
			
			System.out.println("Following are properties of " + name + ":");
			
			for (int i = 0 ; i < properties.size() ; i++)
			{
				if (name.equals(properties.get(i).getOwner().getName()))
					System.out.println(properties.get(i));
			}

		
	}
	
}

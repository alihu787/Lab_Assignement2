package operation;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Scanner;

import domain.Commercial;
import domain.Hospital;
import domain.Industrial;
import domain.OtherProperty;
import domain.Property;
import domain.RatePayer;
import domain.Residential;
import domain.SchoolCommunity;
import domain.VacantLand;

import java.time.LocalDate;


/**
 * @author Takeogh
 * @version 1.0
 * 
 */
public class AddProperty extends FunctionalDialog {

	private static final NumberFormat MYFORMAT = NumberFormat.getNumberInstance();
	private static final int MAX_NO_USER_INPUTS = 4;
	private static final int RESIDENTIAL = 1, COMMERCIAL = 2, VACANT_LAND = 3, HOSPITAL = 4, INDUSTRIAL = 5,
			SCHOOL_COMMUNITY = 6, OTHER = 7, END = 0;
	private static final int MAX_PROPERTY_TYPES = 7;
	private static final String PROPERTY_TYPE_PROMPT = "What type of property are we dealing with? \n" + RESIDENTIAL
			+ ". Residential \n" + COMMERCIAL + ". Commercial \n" + VACANT_LAND + ". Vacant Land \n" + HOSPITAL
			+ ". Hospital \n" + INDUSTRIAL + ". Industrial \n" + SCHOOL_COMMUNITY + ". School/Community \n" + OTHER
			+ ". Other \n" + END + ".  To exit";

	private int propertyType;
	private String description;
	private String location;
	private double area;
	private double siteValue;
	private double capitalImprovedValue;
	private double capitalImprovedRate;
	private double netAnnualValue;
	private String valuationDate;
	private String extraFeature1;
	private String extraFeature2;
	private String extraFeature3;

	public AddProperty(Scanner console) {
		super(13, console);
	}

	@Override
	public void obtainInput(int i) {
		switch (i) {
		case 0:
			propertyType = obtainIntInput(MAX_PROPERTY_TYPES, PROPERTY_TYPE_PROMPT);
			if (propertyType == END)
				setStillRunning(false);
			break;
		case 1:
			System.out.println("Enter property description (lot number and portfolio):");
			description = getScanner().next();
			break;
		case 2:
			System.out.println("Enter property location:");
			location = getScanner().next();
			break;
		case 3:
			area = obtainDoubleInput(50000.00, "Enter area in meter squares:");
			break;
		case 4:
			capitalImprovedValue = obtainDoubleInput(50000000.00, "Enter CIV:");
			break;
		case 5:
			netAnnualValue = obtainDoubleInput(10000000, "Enter net annaul value:");
			break;
		case 6:
			if (propertyType == 1) {
				System.out.println("Enter resident type:");
				extraFeature1 = getScanner().next();
				System.out.println("Enter architectural style:");
				extraFeature2 = getScanner().next();
			}
			break;
		case 7:
			if (propertyType == 2) {
				System.out.println("Enter Business Name:");
				extraFeature1 = getScanner().next();
				System.out.println("Enter ABN:");
				extraFeature2 = getScanner().next();
			}
			break;
		case 8:
			if (propertyType == 3) {
				System.out.println("Enter Overlays:");
				extraFeature1 = getScanner().next();
			}
			break;
		case 9:
			if (propertyType == 4) {
				System.out.println("Private?");
				extraFeature1 = getScanner().next();
				System.out.println("Faicilities:");
				extraFeature2 = getScanner().next();
				System.out.println("No of floors:");
				extraFeature3 = getScanner().next();
			}
			break;
		case 10:
			if (propertyType == 5) {
				System.out.println("Hazard status:");
				extraFeature1 = getScanner().next();
				System.out.println("Heavy vehicle status:");
				extraFeature2 = getScanner().next();
			}
			break;
		case 11:
			if (propertyType == 6) {
				System.out.println("Classification – public, private or independent:");
				extraFeature1 = getScanner().next();
			}
			break;
		case 12:
			if (propertyType == 7) {
				System.out.println("Special description?");
				extraFeature1 = getScanner().next();
			}
			break;

		}
	}

	private double obtainDoubleInput(double max, String prompt) {
		System.out.println(prompt);
		return validateDouble(100.00, max);
	}

	private double validateDouble(double min, double max) {
		double userInput;
		do {
			MYFORMAT.setMinimumFractionDigits(2);
			MYFORMAT.setMaximumFractionDigits(2);
			System.out.print("Enter a selection (" + MYFORMAT.format(min) + "-" + MYFORMAT.format(max) + "):");

			if (!getScanner().hasNextDouble())
				userInput = max + 0.01;
			else
				userInput = getScanner().nextDouble(); // obtain the input
			getScanner().nextLine(); // gets rid of the newline after the number we just read
			if (userInput < min || userInput > max)
				System.out.println("Invalid choice.");
		} while (userInput < min || userInput > max);
		System.out.println(); // put a space before the next output
		return userInput;

	}

	private int obtainIntInput(int max, String prompt) {
		System.out.println(prompt);
		return validateInt(0, max);
	}

	private int validateInt(int min, int max) {
		int userInput;
		do {
			System.out.print("Enter a selection (" + min + "-" + max + "):");
			if (!getScanner().hasNextInt())
				userInput = max + 1;
			else
				userInput = getScanner().nextInt(); // obtain the input
			getScanner().nextLine(); // gets rid of the newline after the number we just read
			if (userInput < min || userInput > max)
				System.out.println("Invalid choice.");
		} while (userInput < min || userInput > max);
		System.out.println(); // put a space before the next output
		return userInput;

	}

	@Override
	public void respondToInput() {
		Property property = null;
		switch (propertyType) {
		case (RESIDENTIAL):
			property = new Residential(extraFeature1,extraFeature2);
			break;
		case (COMMERCIAL):
			property = new Commercial(extraFeature1, extraFeature2);
			break;
		case (VACANT_LAND):
			break;
		case (HOSPITAL):
			property = new Hospital(extraFeature1, extraFeature2, extraFeature3);
			break;
		case (INDUSTRIAL):
			property = new Industrial(extraFeature1, extraFeature2);
			break;
		case (SCHOOL_COMMUNITY):
			property = new SchoolCommunity(extraFeature1, extraFeature2);
			break;
		case (OTHER):
			property = new OtherProperty();
			break;
		case (END):
			break;
		}
		
		if (property != null)
		{
			property.setArea(area);
			property.setCapitalImprovedValue(capitalImprovedValue);
			property.setDescription(description);
			property.setLocation(location);
			property.setNetAnnualValue(netAnnualValue);
			property.setOwner(new RatePayer());
			property.setValuationDate(property.dateToString(LocalDate.now()));
			
			System.out.println("Property Added Successfully");
			System.out.println("Property [description=" + property.getDescription() + ", capitalImprovedValue=" + property.getCapitalImprovedValue()
				+ ", capitalImprovedRate=" + property.getCapitalImprovedRate() + "] \n");
		}

	}

}

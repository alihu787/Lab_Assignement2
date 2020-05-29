package domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.cert.CertPathChecker;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

public class serializationAndDeserializationProperties {

	private ArrayList<Property> properties;

	Property property;

	private String description;
	private String location;
	private double area;
	private double siteValue;
	private double capitalImprovedValue;
	private double capitalImprovedRate;
	private double netAnnualValue;
	private String valuationDate;
	private RatePayer owner;
	private String featureOne;
	private String featureTwo;
	private String featureThree;
	
	public ArrayList<Property> getProperties()
	{
		return properties;
	}
	
	public void deceriazlizeProperties()
	{
		try
        {
            FileInputStream fis = new FileInputStream("src/data/serializedProperties");
            ObjectInputStream ois = new ObjectInputStream(fis);
 
            properties = (ArrayList) ois.readObject();
 
            ois.close();
            fis.close();
        } 
        catch (IOException ioe) 
        {
            ioe.printStackTrace();
            return;
        } 
        catch (ClassNotFoundException c) 
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
         
	}
	
	public void printAllProperties()
	{
		for (int i = 0 ; i < properties.size(); i++)
		{
			System.out.print(i+ "  ");
			System.out.println(properties.get(i));
		}
	}

	public void serializeProperties() throws IOException {
		System.out.println("Serializaion started for Properties");

		properties = loadProperties();

		try {
			FileOutputStream fos = new FileOutputStream("src/data/serializedProperties");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(properties);
			oos.close();
			fos.close();
			System.out.println("Serializaion for properties completed");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public ArrayList<Property> loadProperties() throws IOException {
		String row;

		ArrayList<Property> propertiesCopy = new ArrayList<Property>();

		File csvFile = new File("src/data/ITECH2306_2020_Load_Properties.csv");
		if (csvFile.isFile()) {
			BufferedReader csvReader = new BufferedReader(
					new FileReader("src/data/ITECH2306_2020_Load_Properties.csv"));
			while ((row = csvReader.readLine()) != null) {
				property = null;
				
				String[] data = row.split(",");
				

				if (validatePropertyRecord(data)) {
					description = data[1];
					location = data[2];
					area = Double.parseDouble(data[3]);
					siteValue = Double.parseDouble(data[4]);
					capitalImprovedValue = Double.parseDouble(data[5]);
					capitalImprovedRate = Double.parseDouble(data[6]);
					netAnnualValue = Double.parseDouble(data[7]);
					valuationDate = data[8];
					owner = findRatePayer(data[9]);
					

					String objectType = data[0].toLowerCase();

					switch (objectType) {
					case "residential":
						property = new Residential(data[10], data[11]);
						break;
					case "commercial":
						property = new Residential(data[10], data[11]);
						break;
					case "vacantland":
						property = new VacantLand();
						break;
					case "hospital":
						property = new Hospital(data[10], data[11],data[12]);
						break;
					case "industrial":
						property = new Industrial(data[10], data[11]);
						break;
					case "schoolcommunity":
						property = new SchoolCommunity(data[10], data[11]);
						break;
					case "other":
						property = new OtherProperty(data[10]);

					default:
						break;
					}

					if (property != null) {
						property.setUpExtraServices();
						property.setArea(area);
						property.setCapitalImprovedValue(capitalImprovedValue);
						property.setDescription(description);
						property.setLocation(location);
						property.setNetAnnualValue(netAnnualValue);
						property.setOwner(owner);
						property.setValuationDate(property.dateToString(LocalDate.now()));
						
						propertiesCopy.add(property);
					}
				}
				else {
					System.out.println("Error: Invalid property record");
					continue;
				}
				
			}
		}

		return propertiesCopy;
	}
	
	public boolean validatePropertyRecord(String[] data)
	{
		String nameString = data[0].toLowerCase();
		
		if (!nameString.equals("residential") && !nameString.equals("commercial") && !nameString.equals("schoolcommunity") &&
				!nameString.equals("vacantland") && !nameString.equals("hospital") && !nameString .equals("industrial") &&
				!nameString.equals("other"))
		{
			System.out.println("Invalid property type");
			return false;
		}
		
		if (data.length > 13 || data.length < 10)
		{
			System.out.println("Invalid property");
			return false;
		}
		
		if (!checkNull(data))
		{
			System.out.println("Invalid property");
			return false;
		}
			
		
		area = Double.parseDouble(data[3]);
		siteValue = Double.parseDouble(data[4]);
		capitalImprovedValue = Double.parseDouble(data[5]);
		capitalImprovedRate = Double.parseDouble(data[6]);
		netAnnualValue = Double.parseDouble(data[7]);
		
		if (!(checkDouble(area) && checkDouble(siteValue) && checkDouble(capitalImprovedValue) 
				&& checkDouble(capitalImprovedRate) && checkDouble(netAnnualValue)))
		{
			System.out.println("Value is less than 0.00. Invalid property");
			return false;
		}
		
		return true;
	}
	
	public boolean checkDouble(double value)
	{
		if (value <= 0.00)
			return false;
		
		return true;
	}

	public boolean checkNull(String[] data) {
		for (int i = 0; i < data.length; i++) {
			if (data[i] == null || data[i] == "")
				return false;
		}

		return true;
	}

	public RatePayer findRatePayer(String name) throws IOException {
		ArrayList<RatePayer> ratePayers = serializingAndDeserializingRatePayers.getRatePayers();
		for (int i = 0; i < ratePayers.size(); i++) {
			if (name.equals(ratePayers.get(i).getName())) {
				return ratePayers.get(i);
			}
		}

		return null;

	}

}

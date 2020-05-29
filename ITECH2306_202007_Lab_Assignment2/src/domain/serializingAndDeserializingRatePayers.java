package domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.xml.validation.Validator;

public class serializingAndDeserializingRatePayers {

	private String name;
	private String address;
	private String postcode;
	private String phone;
	private String type;
	private boolean charity;

	public static ArrayList<RatePayer> ratePayers;

	public static ArrayList<RatePayer> getRatePayers() {
		return ratePayers;
	}

	public void serializeRatePayers() throws IOException {
		System.out.println("Serializaion started for ratepayers");

		ratePayers = loadRatePayers();

		try {
			FileOutputStream fos = new FileOutputStream("src/data/serializedRatePayers");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(ratePayers);
			oos.close();
			fos.close();
			System.out.println("Serializaion for taxpayers completed");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public ArrayList<RatePayer> loadRatePayers() throws IOException {
		String row;

		ArrayList<RatePayer> ratePayersCopy = new ArrayList<RatePayer>();

		File csvFile = new File("src/data/ITECH2306_2020_Load_RatePayers.csv");
		if (csvFile.isFile()) {
			BufferedReader csvReader = new BufferedReader(
					new FileReader("src/data/ITECH2306_2020_Load_RatePayers.csv"));
			while ((row = csvReader.readLine()) != null) {
				String[] data = row.split(",");

				if (validateRatePayerRecord(data)) {
					name = data[0];
					address = data[1];
					postcode = data[2];
					phone = data[3];
					type = data[4];
					charity = Boolean.parseBoolean(data[5]);

					RatePayer ratePayer = new RatePayer(name, address, postcode, phone, type, charity);

					ratePayersCopy.add(ratePayer);
				} else {
					System.out.println("Error: Record Not Valid");
					continue;
				}

			}
		}

		return ratePayersCopy;
	}

	public boolean validateRatePayerRecord(String[] data) {
		if (data.length != 6) {
			return false;
		}

		if (!valid(data)) {
			return false;
		}

		return true;
	}

	public void deceriazlizeRatePayers() {
		try {
			FileInputStream fis = new FileInputStream("src/data/serializedRatePayers");
			ObjectInputStream ois = new ObjectInputStream(fis);

			ratePayers = (ArrayList) ois.readObject();

			ois.close();
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			return;
		}

		System.out.println("Deserelization Successfull");

	}

	public void printRatePayers() {
		for (int i = 0; i < ratePayers.size(); i++) {
			System.out.print(i + 1 + ".  ");
			System.out.println(ratePayers.get(i));
		}
	}

	public boolean valid(String[] data) {
		if (data[0] == null || data[1] == null || data[2] == null || data[4] == null || data[5] == null) {
			return false;
		} else if (data[0] == "" || data[1] == "" || data[2] == "" || data[4] == "" || data[5] == "") {
			return false;
		} else
			return true;
	}

}

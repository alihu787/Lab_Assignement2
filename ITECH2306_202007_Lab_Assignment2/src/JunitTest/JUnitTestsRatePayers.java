package JunitTest;

import static org.junit.Assert.*;

import org.junit.Test;

import domain.serializationAndDeserializationProperties;
import domain.serializingAndDeserializingRatePayers;

public class JUnitTestsRatePayers {


		
		serializingAndDeserializingRatePayers ratePayerTester = new serializingAndDeserializingRatePayers();

		@Test
		public void validRatePayerstest() {
			String[] dataStrings = new String[6];
			
			dataStrings[0] = "Bonnings Ltd";
			dataStrings[1] = "166 Sturt St. Ballarat VIC";
			dataStrings[2] = "3350";
			dataStrings[3] = "03 53789621";
			dataStrings[4] = "Public";
			dataStrings[5] = "true";
			
			assertEquals(ratePayerTester.validateRatePayerRecord(dataStrings), true);	
			
			dataStrings[3] = "";
			assertEquals(ratePayerTester.validateRatePayerRecord(dataStrings), true);	
		
	}
		

		@Test
		public void inValidRatePayerstest() {
			String[] dataStrings = new String[6];
			
			dataStrings[0] = "";
			dataStrings[1] = "166 Sturt St. Ballarat VIC";
			dataStrings[2] = "3350";
			dataStrings[3] = "03 53789621";
			dataStrings[4] = "Public";
			dataStrings[5] = "true";
			
			assertEquals(ratePayerTester.validateRatePayerRecord(dataStrings), false);		
			
			dataStrings = new String[7];
			dataStrings[0] = "Abc";
			dataStrings[1] = "166 Sturt St. Ballarat VIC";
			dataStrings[2] = "3350";
			dataStrings[3] = "03 53789621";
			dataStrings[4] = "Public";
			dataStrings[5] = "true";
			dataStrings[6] = "abc";
			assertEquals(ratePayerTester.validateRatePayerRecord(dataStrings), false);		

		
	}
		

}

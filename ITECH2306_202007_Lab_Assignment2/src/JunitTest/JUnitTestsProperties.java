package JunitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

import domain.serializationAndDeserializationProperties;

class JUnitTestsProperties {
	serializationAndDeserializationProperties PropertiesTester = new serializationAndDeserializationProperties();


	@Test
	public void validPropertiesTest()
	{
		String[] dataStrings = new String[12];
		dataStrings[0] = "residential";
		dataStrings[1] = "166 Sturt St. Ballarat VIC";
		dataStrings[2] = "3350";
		dataStrings[3] = "03789621";
		dataStrings[4] = "3423";
		dataStrings[5] = "3223";
		dataStrings[6] = "23";
		dataStrings[7] = "3350";
		dataStrings[8] = "0353789621";
		dataStrings[9] = "Public";
		dataStrings[10] = "true";
		dataStrings[11] = "abc";
		
		assertEquals(PropertiesTester.validatePropertyRecord(dataStrings), true);
		
		
	}
	
	@Test
	public void inValidPropertiesTest()
	{
		String[] dataStrings = new String[12];
		dataStrings[0] = "abc";
		dataStrings[1] = "166 Sturt St. Ballarat VIC";
		dataStrings[2] = "3350";
		dataStrings[3] = "03789621";
		dataStrings[4] = "3423";
		dataStrings[5] = "3223";
		dataStrings[6] = "23";
		dataStrings[7] = "3350";
		dataStrings[8] = "0353789621";
		dataStrings[9] = "Public";
		dataStrings[10] = "true";
		dataStrings[11] = "abc";
		
		assertEquals(PropertiesTester.validatePropertyRecord(dataStrings), false);
		
		dataStrings[0] = "commercial";
		dataStrings[11] = "";
		
		assertEquals(PropertiesTester.validatePropertyRecord(dataStrings), false);
		
		
	}

}

package operation;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import JunitTest.JUnitTestsRatePayers;
import domain.RatePayer;
import domain.serializingAndDeserializingRatePayers;

/**
 * @author Takeogh
 * @version 1.0
 * 
 */
public class CouncilSystem {

	public static void main(String[] args) throws IOException {
		
		CouncilSystem cs = new CouncilSystem();
		cs.startApplication();
	}

	private void startApplication() throws IOException {
		
		MainMenu mm = new MainMenu();
		mm.operateMenu();	
	}

}

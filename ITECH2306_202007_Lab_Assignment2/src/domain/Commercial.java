package domain;

/**
 * @author Takeogh
 * @version 1.0
 * 
 */
public class Commercial extends Property{
	
	private String businessName;
	private String ABN;
	private static final double CIV_RATE = 0.0059;
	private static final int WASTE_MANAGEMENT_UNITS = 2;
	private static final double WASTE_MANAGEMENT_FEES = 350.00;
	private static final double FIRE_SERVICES_BASE = 200;
	private static final double FIRE_SERVICES_PERCENT = 0.00006;
	//These would be better in a multi-element variable e.g. array but we haven't got there yet in the course
	private ServiceType wasteManagement;
	private ServiceType fireServicesLevy;

	public Commercial() {
		this.setABN("Dummy");
		this.setBusinessName("Dummy");
		this.setCapitalImprovedRate(CIV_RATE);
	}
	
	public Commercial(String businessName, String ABN) {
		this.setABN(ABN);
		this.setBusinessName(businessName);
		this.setCapitalImprovedRate(CIV_RATE);
	}

	@Override
	public void setUpExtraServices() {
		wasteManagement = new UnitAndRateService("Waste Management",
				  WASTE_MANAGEMENT_UNITS,
				  WASTE_MANAGEMENT_FEES);
		fireServicesLevy = new BaseAndPercentageOfValueService("Fire Levy",
																FIRE_SERVICES_BASE,
																FIRE_SERVICES_PERCENT,
																getCapitalImprovedValue());
		
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getABN() {
		return ABN;
	}

	public void setABN(String aBN) {
		ABN = aBN;
	}

	@Override
	public double calculateExtraServices() {
		return wasteManagement.calculateChargeForServiceType() +
				   fireServicesLevy.calculateChargeForServiceType();
	}
	
	@Override
	public String toString() {
		return  super.toString() + "Commercial [\n" + 
									wasteManagement.toString() + "\n" +
									fireServicesLevy.toString() + " ]\n ";
	}
}

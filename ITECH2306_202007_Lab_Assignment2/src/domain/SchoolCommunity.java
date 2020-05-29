package domain;

/**
 * @author Takeogh
 * @version 1.0
 * 
 */
public class SchoolCommunity extends Property {

	private String classification;
	private String category;
	
	private static final double CIV_RATE = 0.0025;
	private static final int INDUSTIRAL_WASTE_DISPOSAL_UNITS = 4;
	private static final double INDUSTIRAL_WASTE_DISPOSAL_FEES = 500.00;
	private static final double FIRE_SERVICES_BASE = 200;
	private static final double FIRE_SERVICES_PERCENT = 0.00006;
	private double TRAFFIC_MANAGMENT_LEVY_BASE = 200;
	private static final int TRAFFIC_MANAGMENT_UNIT = 1;
	//These would be better in a multi-element variable e.g. array but we haven't got there yet in the course
	private ServiceType industrialWasteDisposal;
	private ServiceType fireServicesLevy;
	private ServiceType trafficManagmentLevy;
	
	int communityCategory;
	
	public SchoolCommunity(int communityCategory) {
		this.communityCategory = communityCategory;
		this.setCapitalImprovedRate(CIV_RATE);
	}
	
	public SchoolCommunity(String classification, String category)
	{
		this.classification = classification;
		this.category = category;
	}
	
	@Override
	public String toString() {
		return  super.toString() + "School [\n" + 
									industrialWasteDisposal.toString() + "\n" +
									fireServicesLevy.toString() + " ]\n " +
									trafficManagmentLevy.toString() + " ]\n ";
	}

	@Override
	public void setUpExtraServices() {
		industrialWasteDisposal = new UnitAndRateService("Insdustiral waste Disposal",
				INDUSTIRAL_WASTE_DISPOSAL_UNITS,
				INDUSTIRAL_WASTE_DISPOSAL_FEES);
		fireServicesLevy = new BaseAndPercentageOfValueService("Fire Levy",
																FIRE_SERVICES_BASE,
																FIRE_SERVICES_PERCENT,
																getCapitalImprovedValue());
		
		if (communityCategory == 1)
			TRAFFIC_MANAGMENT_LEVY_BASE = TRAFFIC_MANAGMENT_LEVY_BASE + 60;
		else if (communityCategory == 2) {
			TRAFFIC_MANAGMENT_LEVY_BASE = TRAFFIC_MANAGMENT_LEVY_BASE + 80;
		}
		else {
			TRAFFIC_MANAGMENT_LEVY_BASE = TRAFFIC_MANAGMENT_LEVY_BASE + 100;
		}
		
		trafficManagmentLevy = new UnitAndRateService("Traffic Management Levy",
				TRAFFIC_MANAGMENT_UNIT, TRAFFIC_MANAGMENT_LEVY_BASE);
		
		
			
		
		
	}

	@Override
	public double calculateExtraServices() {
		return industrialWasteDisposal.calculateChargeForServiceType() +
				   fireServicesLevy.calculateChargeForServiceType() 
		+ trafficManagmentLevy.calculateChargeForServiceType();
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		this.classification = classification;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	

}

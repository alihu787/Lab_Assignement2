package domain;

/**
 * @author Takeogh
 * @version 1.0
 * 
 */
public class Hospital extends Property{
	
	private String status;
	private String facilities;
	private String floors;
	private static final double CIV_RATE = 0.0035;
	private static final int INDUSTIRAL_WASTE_DISPOSAL_UNITS = 2;
	private static final double INDUSTIRAL_WASTE_DISPOSAL_FEES = 500.00;
	private static final double FIRE_SERVICES_BASE = 200;
	private static final double FIRE_SERVICES_PERCENT = 0.00006;
	//These would be better in a multi-element variable e.g. array but we haven't got there yet in the course
	private ServiceType industrialWasteDisposal;
	private ServiceType fireServicesLevy;

	public Hospital() {
		this.setCapitalImprovedRate(CIV_RATE);
	}
	
	public Hospital(String status, String facilities, String floors)
	{
		this.facilities = facilities;
		this.floors = floors;
		this.status = status;
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
		
	}

	@Override
	public double calculateExtraServices() {
		return industrialWasteDisposal.calculateChargeForServiceType() +
				   fireServicesLevy.calculateChargeForServiceType();
	}
	
	@Override
	public String toString() {
		return  super.toString() + "Hospital [\n" + 
									industrialWasteDisposal.toString() + "\n" +
									fireServicesLevy.toString() + " ]\n ";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	public String getFloors() {
		return floors;
	}

	public void setFloors(String floors) {
		this.floors = floors;
	}
	
}

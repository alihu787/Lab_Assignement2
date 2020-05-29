package domain;

/**
 * @author Takeogh
 * @version 1.0
 * 
 */
public class Industrial extends Property {

	private String hazardStatus;
	private String heavyVehicleStatus;
	
	private static final double CIV_RATE = 0.0059;
	private static final int INDUSTIRAL_WASTE_DISPOSAL_UNITS = 4;
	private static final double INDUSTIRAL_WASTE_DISPOSAL_FEES = 500.00;
	private static final double FIRE_SERVICES_BASE = 200;
	private static final double FIRE_SERVICES_PERCENT = 0.00006;
	//These would be better in a multi-element variable e.g. array but we haven't got there yet in the course
	private ServiceType industrialWasteDisposal;
	private ServiceType fireServicesLevy;
	
	public Industrial() {
		this.setCapitalImprovedRate(CIV_RATE);
	}
	
	public Industrial(String hazardStatus, String heavyVehicalStatus)
	{
		this.hazardStatus = hazardStatus;
		this.heavyVehicleStatus = heavyVehicalStatus;
		this.setCapitalImprovedRate(CIV_RATE);
	}

	@Override
	public double calculateExtraServices() {
		return industrialWasteDisposal.calculateChargeForServiceType() +
				   fireServicesLevy.calculateChargeForServiceType();
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
	public String toString() {
		return  super.toString() + "Industiral [\n" + 
									industrialWasteDisposal.toString() + "\n" +
									fireServicesLevy.toString() + " ]\n ";
	}

	public String getHazardStatus() {
		return hazardStatus;
	}

	public void setHazardStatus(String hazardStatus) {
		this.hazardStatus = hazardStatus;
	}

	public String getHeavyVehicleStatus() {
		return heavyVehicleStatus;
	}

	public void setHeavyVehicleStatus(String heavyVehicleStatus) {
		this.heavyVehicleStatus = heavyVehicleStatus;
	}
	
	

	
}

package domain;

/**
 * @author Takeogh
 * @version 1.0
 * @created 02-Apr-2020 8:30:00am
 */
public class VacantLand extends Property{

	private String[] overlays;
	
	private static final double CIV_RATE = 0.0020;
	private static final double FIRE_SERVICES_BASE = 110;
	private static final double FIRE_SERVICES_PERCENT = 0.00006;

	private ServiceType fireServicesLevy;

	public VacantLand() {
	}
	
	public VacantLand(String[] overlays)
	{
		this.overlays = overlays;
	}
	
	public String[] getOverlays() {
		return overlays;
	}
	public void setOverlays(String[] overlays) {
		this.overlays = overlays;
	}
	@Override
	public void setUpExtraServices() {
		fireServicesLevy = new BaseAndPercentageOfValueService("Fire Levy",
																FIRE_SERVICES_BASE,
																FIRE_SERVICES_PERCENT,
																getCapitalImprovedValue());
		
	}

	@Override
	public double calculateExtraServices() {
		return   fireServicesLevy.calculateChargeForServiceType();
	}
	
	@Override
	public String toString() {
		return  super.toString() + "VacanLand [\n" + 
									fireServicesLevy.toString() + " ]\n ";
	}

}

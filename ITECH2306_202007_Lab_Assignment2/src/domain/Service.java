package domain;

/**
 * @author Takeogh
 * @version 1.0
 * @created 02-Apr-2020 8:30:00am
 */
public abstract class Service {
	private String description;
	private double units;
	private double base;
	private double rate;

	public Service(String description, double units, double base, double rate) {
		this.setDescription(description);
		this.setUnits(units);
		this.setBase(base);
		this.setRate(rate);
	}

	protected abstract double calculateServiceAmount();
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getUnits() {
		return units;
	}
	public void setUnits(double units) {
		this.units = units;
	}
	public double getBase() {
		return base;
	}
	public void setBase(double base) {
		this.base = base;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}

}

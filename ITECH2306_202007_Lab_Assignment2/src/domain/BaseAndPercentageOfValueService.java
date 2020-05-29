package domain;

import java.io.Serializable;

/**
 * @author Takeogh
 * @version 1.0
 *  
 */
public class BaseAndPercentageOfValueService extends ServiceType implements Serializable{
	private double base;
	private double percentage;
	private double value;
	
	public BaseAndPercentageOfValueService(String description, double base, double percentage, double value) {
		super(description);
		this.setBase(base);
		this.setPercentage(percentage);
		this.setValue(value);
	}

	@Override
	protected double calculateChargeForServiceType() {
		// TODO Auto-generated method stub
		return this.getBase() + (this.getPercentage() * this.getValue());
	}
	
	public double getBase() {
		return base;
	}

	public void setBase(double base) {
		this.base = base;
	}

	public double getPercentage() {
		return percentage;
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return super.toString() + "[base=" + base + ", percentage=" + percentage + ", value=" + value
				+ "]";
	}

}

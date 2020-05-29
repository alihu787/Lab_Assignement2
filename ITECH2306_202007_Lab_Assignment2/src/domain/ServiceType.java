package domain;

import java.io.Serializable;

/**
 * @author Takeogh
 * @version 1.0
 * 
 */
public abstract class ServiceType implements Serializable {
	private String description;
	public ServiceType(String description) {
		this.setDescription(description);
	}
	
	protected abstract double calculateChargeForServiceType();

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "ServiceType [description=" + description + "]";
	}

}

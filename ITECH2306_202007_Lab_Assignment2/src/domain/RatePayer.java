package domain;

import java.io.Serializable;

/**
 * @author Takeogh
 * @version 1.0
 * 
 */
public class RatePayer implements Serializable {

	private String name;
	private String address;
	private String postcode;
	private String phone;
	private String type;
	private boolean charity;
	//Discount might not necessarily be on RatePayer but for convenience at the moment we place it here.
	private double charityDiscountPercentage = 0.20;
	private static final String DUMMY_VALUE = "Dummy Value";
	
	public RatePayer()
	{
		this.setName(DUMMY_VALUE);
		this.setAddress(DUMMY_VALUE);
		this.setPostcode(DUMMY_VALUE);
		this.setPhone(DUMMY_VALUE);
		this.setType(DUMMY_VALUE);
		this.setCharity(false);
	}
	
	public RatePayer(String nameString, String addressString, String postCodeString, String phoneString, String typeString, boolean charity) {
		this.setName(nameString);
		this.setAddress(addressString);
		this.setPostcode(postCodeString);
		this.setPhone(phoneString);
		this.setType(typeString);
		this.setCharity(charity);
		
		if (charity == false)
			setCharityDiscountPercentage(0);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isCharity() {
		return charity;
	}

	public void setCharity(boolean charity) {
		this.charity = charity;
	}

	
	public double getCharityDiscountPercentage() {
		return charityDiscountPercentage;
	}

	public void setCharityDiscountPercentage(double charityDiscountPercentage) {
		this.charityDiscountPercentage = charityDiscountPercentage;
	}

	@Override
	public String toString() {
		return "RatePayer [name=" + name + ", address=" + address + ", postcode=" + postcode + ", \nphone=" + phone
				+ ", type=" + type + ", charity=" + charity + ", charityDiscountPercentage=" + charityDiscountPercentage
				+ "]";
	}
	
	

}

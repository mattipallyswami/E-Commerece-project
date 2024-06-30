package com.naresh.Database.DTO;

public class SellerAddressDto {
	
	private int addressId;
	
	 
	private String streetNo;
	
 
	private String buildingName;
	
 
	private String locality;
	
 
	private String city;
	
	 
	private String state;
	
 
	private String pincode;
	
 
	private String sellerName;


	public int getAddressId() {
		return addressId;
	}


	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}


	public String getStreetNo() {
		return streetNo;
	}


	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}


	public String getBuildingName() {
		return buildingName;
	}


	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}


	public String getLocality() {
		return locality;
	}


	public void setLocality(String locality) {
		this.locality = locality;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getPincode() {
		return pincode;
	}


	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


	public String getSellerName() {
		return sellerName;
	}


	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}


	@Override
	public String toString() {
		return "SellerAddressDto [addressId=" + addressId + ", streetNo=" + streetNo + ", buildingName=" + buildingName
				+ ", locality=" + locality + ", city=" + city + ", state=" + state + ", pincode=" + pincode
				+ ", sellerName=" + sellerName + "]";
	}


	public SellerAddressDto(int addressId, String streetNo, String buildingName, String locality, String city,
			String state, String pincode, String sellerName) {
		super();
		this.addressId = addressId;
		this.streetNo = streetNo;
		this.buildingName = buildingName;
		this.locality = locality;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.sellerName = sellerName;
	}


	public SellerAddressDto() {
		super();
	}
	
	


}

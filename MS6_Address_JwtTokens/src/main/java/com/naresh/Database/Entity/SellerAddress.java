package com.naresh.Database.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="ADDRESS999")
public class SellerAddress {
	
    @Id
	@Column(name="address_id")
	private int addressId;
	
	@Column(name="street_no")
	private String streetNo;
	
	@Column(name="building_name")
	private String buildingName;
	
	@Column(name="locality")
	private String locality;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="pincode")
	private String pincode;
	
	@Column(name="seller_name")
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
	public SellerAddress(int addressId, String streetNo, String buildingName, String locality, String city,
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

	public SellerAddress() {
		super();
	}

	@Override
	public String toString() {
		return "SellerAddress [addressId=" + addressId + ", streetNo=" + streetNo + ", buildingName=" + buildingName
				+ ", locality=" + locality + ", city=" + city + ", state=" + state + ", pincode=" + pincode
				+ ", sellerName=" + sellerName + "]";
	}
 
}

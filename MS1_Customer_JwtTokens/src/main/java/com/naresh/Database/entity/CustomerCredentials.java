package com.naresh.Database.entity;

public class CustomerCredentials {
	
	private String customerName;
	private String password;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "CustomerCredentials [customerName=" + customerName + ", password=" + password + "]";
	}
	public CustomerCredentials(String customerName, String password) {
		super();
		this.customerName = customerName;
		this.password = password;
	}
	public CustomerCredentials() {
		super();
	}
	
	
	

}

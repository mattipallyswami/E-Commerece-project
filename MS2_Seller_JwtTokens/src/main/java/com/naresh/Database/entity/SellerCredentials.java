package com.naresh.Database.entity;

public class SellerCredentials {
	
	private String sellerName;
	private String password;
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "SellerCredentials [sellerName=" + sellerName + ", password=" + password + "]";
	}
	public SellerCredentials(String sellerName, String password) {
		super();
		this.sellerName = sellerName;
		this.password = password;
	}
	public SellerCredentials() {
		super();
	}
	 
 
}

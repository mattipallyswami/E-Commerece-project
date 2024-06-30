package com.naresh.Database.Dto;

import java.time.LocalDate;



import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
 
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

 
public class CustomerDto   {
	
	 
	 
	private String customerId;
	 
	private String customerName;
	 
	private String mobileNo;
	
 
	private String emailId;
	  
	private String password;
	
	private LocalDateTime createdOn;
	
	
	

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	 

	public CustomerDto(String customerId, String customerName, String mobileNo, String emailId, String password,
			LocalDateTime createdOn) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
		this.password = password;
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "CustomerDto [customerId=" + customerId + ", customerName=" + customerName + ", mobileNo=" + mobileNo
				+ ", emailId=" + emailId + ", password=" + password + ", createdOn=" + createdOn + "]";
	}

	public CustomerDto() {
		super();
	}
	
	 // lombok
	
	
	
	
} 


	 
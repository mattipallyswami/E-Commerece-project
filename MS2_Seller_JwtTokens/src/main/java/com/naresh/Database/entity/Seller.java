package com.naresh.Database.entity;

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

@Entity
@Table(name="seller777")
public class Seller implements UserDetails {
	
	 
	@Column(name="seller_id")
	private String sellerId;
	
	@Id
	@Column(name="seller_name")
	private String sellerName;
	
	@Column(name="password")
	private String password;

	
	@Column(name="mobile")
	private String mobile;
	
	@Column(name="email_id")
	private String emailId;
	
	 
	
	@Column(name="created_on")
	private LocalDateTime createdOn;



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}



	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.sellerName;
	}



	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}



	public String getSellerId() {
		return sellerId;
	}



	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}



	public String getSellerName() {
		return sellerName;
	}



	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}



	public String getMobile() {
		return mobile;
	}



	public void setMobile(String mobile) {
		this.mobile = mobile;
	}



	public String getEmailId() {
		return emailId;
	}



	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}



	public LocalDateTime getCreatedOn() {
		return createdOn;
	}



	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	@Override
	public String toString() {
		return "Seller [sellerId=" + sellerId + ", sellerName=" + sellerName + ", password=" + password + ", mobile="
				+ mobile + ", emailId=" + emailId + ", createdOn=" + createdOn + "]";
	}



	public Seller(String sellerId, String sellerName, String password, String mobile, String emailId,
			LocalDateTime createdOn) {
		super();
		this.sellerId = sellerId;
		this.sellerName = sellerName;
		this.password = password;
		this.mobile = mobile;
		this.emailId = emailId;
		this.createdOn = createdOn;
	}



	public Seller() {
		super();
	}
	
  // lombok
	
 

  	
}
 
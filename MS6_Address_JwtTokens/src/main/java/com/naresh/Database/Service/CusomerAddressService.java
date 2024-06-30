package com.naresh.Database.Service;

import java.util.List;

import com.naresh.Database.DTO.CustomerAddressDto;
 

public interface CusomerAddressService {
	
	public String addAddress(CustomerAddressDto addressDto,String token);
 	
	 
	public CustomerAddressDto gettingAddressById();
	
	 public CustomerAddressDto getCustomerAddress(String token);
 
}

package com.naresh.Database.Service;

import java.util.List;

import com.naresh.Database.DTO.CustomerAddressDto;
import com.naresh.Database.DTO.SellerAddressDto;

public interface SellerAddressService {
	
	public String addSellerAddress(SellerAddressDto sellerAddressDto, String token) ;
	
	public  SellerAddressDto  getAddrs(int aid);

}

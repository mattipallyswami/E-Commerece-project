package com.naresh.Database.Mapping;

 
import java.util.List;
 


import org.mapstruct.Mapper;

import com.naresh.Database.DTO.CustomerAddressDto;
import com.naresh.Database.DTO.SellerAddressDto;
import com.naresh.Database.Entity.CustomerAddress;
import com.naresh.Database.Entity.SellerAddress;
 
@Mapper
public interface Converter {
	
	
	
	CustomerAddress addressDtoToAddress(CustomerAddressDto addressDto);
	
	CustomerAddressDto addressToAddressDto(CustomerAddress address);
 
	SellerAddress  sellerAddressDtoToSellerAddress(SellerAddressDto sellerAddressDto);
	
	SellerAddressDto  sellerAddressToSellerAddressDto(SellerAddress sellerAddress);
	 

}

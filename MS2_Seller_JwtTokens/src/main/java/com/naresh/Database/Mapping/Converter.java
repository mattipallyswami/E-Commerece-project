package com.naresh.Database.Mapping;

import org.mapstruct.Mapper;


import com.naresh.Database.Dto.CustomerDto;
import com.naresh.Database.entity.Seller;

@Mapper
public interface Converter {
	
	 	
	CustomerDto customerToCustomerDto(Seller customer);
	
	Seller customerDtoToCustomer(CustomerDto customerDto);
	
	
 
}

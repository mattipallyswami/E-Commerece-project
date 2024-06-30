package com.naresh.Database.Mapping;

import org.mapstruct.Mapper;


import com.naresh.Database.Dto.CustomerDto;
import com.naresh.Database.entity.Customer;

@Mapper
public interface Converter {
	
	 	
	CustomerDto customerToCustomerDto(Customer customer);
	
	Customer customerDtoToCustomer(CustomerDto customerDto);
	
	
 
}

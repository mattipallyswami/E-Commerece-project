package com.naresh.Database.Service;
import com.naresh.Database.CustomException.AddressNotFoundWithCustomerName;
import com.naresh.Database.CustomException.ProductAlreadyExists;

import com.naresh.Database.CustomException.ProductNotFound;
import com.naresh.Database.DTO.CustomerAddressDto;
import com.naresh.Database.Entity.CustomerAddress;
import com.naresh.Database.Repository.*;
import com.naresh.Database.Mapping.ConverterImpl;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerAddressServiceImpl implements CusomerAddressService{
	
	
	private static final Logger logger = Logger.getLogger(CustomerAddressServiceImpl.class.getName());
	
	
	public static final String  SCREATE_KRY="asdfghjkzxcvbnmvjnzl";
	
	@Autowired
	CustomerAddressRepository customeraddressRepository;
	
	@Autowired
	JWTtoken jWTtoken;

	ConverterImpl converterImpl=new ConverterImpl();

	@Override
	public String addAddress(CustomerAddressDto addressDto, String token) {
		 
		
		
		   logger.info(" staring of Service method of Address App");

		CustomerAddress adrress=converterImpl.addressDtoToAddress(addressDto);
		
		// before saving we need to set customername(fk)
		
		String customerName= jWTtoken.getCustomerNameFromToken(token);
		
		adrress.setCustomerName(customerName);
		
		customeraddressRepository.save(adrress);
		
		return "address details added sucessfully";
	}
	 
	@Override
	public CustomerAddressDto gettingAddressById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerAddressDto getCustomerAddress(String token) {

    	
		CustomerAddress customerAddress=customeraddressRepository.findByCustomerName(jWTtoken.getCustomerNameFromToken(token));
		
		
		if(customerAddress==null)
		{
			throw new AddressNotFoundWithCustomerName("address not found for thuis customer"+jWTtoken.getCustomerNameFromToken(token),new Throwable("please add address first"));
		}
		
		return  converterImpl.addressToAddressDto(customerAddress);
	 	
		 
	}


	 
 
}

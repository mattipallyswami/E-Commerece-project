package com.naresh.Database.Service;
import com.naresh.Database.Service.*;
import com.naresh.Database.DTO.CustomerAddressDto;
import com.naresh.Database.DTO.SellerAddressDto;
import com.naresh.Database.Entity.CustomerAddress;
import com.naresh.Database.Entity.SellerAddress;
import com.naresh.Database.Mapping.ConverterImpl;
 
import com.naresh.Database.Repository.SellerAddressRepository;

import java.util.List;
import java.util.logging.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerAddressServiceImpl implements SellerAddressService{

	@Autowired
	JWTtoken jWTtoken;
	
	@Autowired
	SellerAddressRepository sellerAddressRepository;
	
	private static final Logger logger = Logger.getLogger(CustomerAddressServiceImpl.class.getName());

	
	ConverterImpl converterImpl   = new ConverterImpl();
	
	@Override
	public String addSellerAddress(SellerAddressDto sellerAddressDto, String token) {


		logger.info(" staring of Service method of Address App");

		    SellerAddress sellerAddress=converterImpl.sellerAddressDtoToSellerAddress(sellerAddressDto);
		
		// before saving we need to set customername(fk)
		
		String sellerName= jWTtoken.getSellerNameFromToken(token);
		
		sellerAddress.setSellerName(sellerName);
		
		sellerAddressRepository.save(sellerAddress);
		
		return "seller address details added sucessfully";
	 
	}

	@Override
	public  SellerAddressDto  getAddrs(int aid) {
	  
	 	
		SellerAddress addr=	sellerAddressRepository.findById(aid).get();
		
		return converterImpl.sellerAddressToSellerAddressDto(addr);
		  
	}

}

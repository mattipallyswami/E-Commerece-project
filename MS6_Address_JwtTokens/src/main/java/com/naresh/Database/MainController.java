package com.naresh.Database;

import java.util.List;


import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.naresh.Database.DTO.CustomerAddressDto;
import com.naresh.Database.DTO.SellerAddressDto;
import com.naresh.Database.Service.CusomerAddressService;
import com.naresh.Database.Service.SellerAddressService;
import com.naresh.Database.Service.CustomerAddressServiceImpl;

import jakarta.servlet.http.HttpServletRequest;


@RestController
public class MainController {
	
	private static final Logger logger = Logger.getLogger(MainController.class.getName());

	@Autowired
	SellerAddressService sellerAddressService;
	@Autowired
	CusomerAddressService addressService;
	
	
	// endpoints for deignClients
	
	@GetMapping("get/address")
	public ResponseEntity<CustomerAddressDto> getCustomerAddressByName(@RequestHeader("Authorization") String token)
	{
		return ResponseEntity.status(HttpStatus.OK).body(addressService.getCustomerAddress(token));
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	@PostMapping("/add/cust/address")
	public ResponseEntity<?> addAddress(@RequestBody CustomerAddressDto addressDto ,@RequestHeader("Authorization") String token)
	{
		   logger.info(" staring of Controller method of Address App");
 	
		String msg=addressService.addAddress(addressDto, token);
	 	return ResponseEntity.status(HttpStatus.CREATED).body(msg);
		
	}
	
	
	@PostMapping(path="add/sell/address")
	public ResponseEntity<?> addAddressOfSeller(@RequestBody SellerAddressDto sellerAddressDto,@RequestHeader("Authorization")String token)
	{
		
		logger.info(" staring of Controller method of Address App");
	 	
		String msg=sellerAddressService.addSellerAddress(sellerAddressDto, token) ;
	 	return ResponseEntity.status(HttpStatus.CREATED).body(msg);
		
	}
	
	@PostMapping("dummy")
	public String dummy() {
		//TODO: process POST request
		
		return "iam dummy from address ";
	}
	
	
	@GetMapping("get/addr/{aid}")
	public ResponseEntity<?> getAddress(@PathVariable("aid") int aid) {
	 
		
		return ResponseEntity.status(HttpStatus.OK).body(sellerAddressService.getAddrs(aid));

 	
	}
	
	
 
}

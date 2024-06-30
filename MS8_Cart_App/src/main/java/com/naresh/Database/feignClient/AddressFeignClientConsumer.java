package com.naresh.Database.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.naresh.Database.DTO.CustomerAddressDto;


@FeignClient("MS6-Address")
public interface AddressFeignClientConsumer {
	
	@GetMapping("/Address/get/address")
	public ResponseEntity<CustomerAddressDto> getCustomerAddressByName(@RequestHeader("Authorization") String token);
	 

}

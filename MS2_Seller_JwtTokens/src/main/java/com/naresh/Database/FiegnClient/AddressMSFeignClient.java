package com.naresh.Database.FiegnClient;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.naresh.Database.Dto.SellerAddressDto;
@FeignClient("MS6-Address")
public interface AddressMSFeignClient {
	
	@PostMapping(path="/Address/add/sell/address")
	public ResponseEntity<String> addAddressOfSeller(@RequestBody SellerAddressDto sellerAddressDto,@RequestHeader("Authorization")String token);
	
	@GetMapping(value="/Address/get/addr/{aid}")
	public ResponseEntity<SellerAddressDto> gettinAddressOfSeller(@PathVariable ("aid") int aid);
}

package com.naresh.Database.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.naresh.Database.DTO.CustomerAddressDto;


@FeignClient(name="MS3-Product")
public interface ProductFeignClientsConsumer {
	
	@GetMapping(path="/Product/get/price/{productId}")
	public ResponseEntity<Long> getPrice(@PathVariable("productId") String productId);
	
}

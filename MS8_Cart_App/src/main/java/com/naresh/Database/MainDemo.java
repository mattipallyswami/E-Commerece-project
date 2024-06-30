package com.naresh.Database;
import com.naresh.Database.Service.*;
import com.naresh.Database.feignClient.ProductFeignClientsConsumer;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.naresh.Database.DTO.CartDto;
import com.naresh.Database.DTO.CartItemDto;
import com.naresh.Database.Entity.Cart;
import com.naresh.Database.Entity.CartItem;



@RestController
public class MainDemo {

//	@Autowired
//	FeignClientsConsume feignClientsConsume;
//	
//	
	@Autowired
	CartService cartService;
	
	@Autowired
	CartItemService cartItemService;
	

	// cart
	
	@GetMapping("get/total/{cartId}")
	
	public ResponseEntity<Double> getTotal(@PathVariable("cartId") int cartId)
	{
		
		return ResponseEntity.status(HttpStatus.OK).body(cartService.cartTotalValue(cartId));
	}

	 
	
	

	 	
	
	@PostMapping("get/price/{id}")
	public ResponseEntity<?> getCartvalue(@PathVariable("id") String  id)
	{
	 	return ResponseEntity.status(HttpStatus.OK).body(cartService.getProductPrice(id));
		
	}
	
	
	@PostMapping("add/cart")
	public ResponseEntity<String> addCart(@RequestBody CartDto cartDto,@RequestHeader("Authorization") String token)
	{
		
		//String token=request.getHeader("Authorization");
		
	 	return ResponseEntity.status(HttpStatus.CREATED).body(cartService.createYourCart(cartDto,token));
		
	}
	
	

	@PostMapping("add/cartItem/{cartId}")
	public ResponseEntity<?> addCartItem(@RequestBody CartItem cartItem,@PathVariable("cartId")int cartId)
	{
	 	return ResponseEntity.status(HttpStatus.CREATED).body(cartItemService.addCartItem(cartItem,cartId));
		
	}
//	@GetMapping("get/product/price/{pid}")
//	public  ResponseEntity getprice(@PathVariable("pid") String pid) {
//	 	return ResponseEntity.status(HttpStatus.OK).body(feignClientsConsume.getPrice(pid));
//	}
	
}

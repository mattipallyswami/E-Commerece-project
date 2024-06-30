package com.naresh.Database;
import com.naresh.Database.Service.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.naresh.Database.DTO.CartDto;
import com.naresh.Database.DTO.CartItemDto;
import com.naresh.Database.Entity.CartItem;

@RestController
public class CartOperations {
	
	@Autowired
	CartService cartService;
	
	 @PostMapping("add/product/to/cart")
	 public ResponseEntity<String> addingProductToCart(@RequestBody CartItemDto cartItemDto,@RequestHeader("Authorization") String token)
	 {
		 
		 
		 
		 return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addProductToCart(cartItemDto, token));
	 }
	 @PostMapping("add/products/to/cart")
	 public ResponseEntity<String> addProducts(@RequestBody List<CartItemDto> cartItemDto,@RequestHeader("Authorization") String token)
	 {
		 
		 
		 
		 return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addProductsToCart(cartItemDto, token));
	 }
	 
	    
	   @GetMapping("get/my/cart")
		
		public ResponseEntity<CartDto> getMyCart(@RequestHeader("Authorization") String token)
		{
			
			return ResponseEntity.status(HttpStatus.OK).body(cartService.getMyCart(token));
		}
	

	     @GetMapping("get/my/cartItems")
		
		public ResponseEntity<List<CartItemDto>> getMyAllCartItems(@RequestHeader("Authorization") String token)
		{
			
			return ResponseEntity.status(HttpStatus.OK).body(cartService.getcartProducts(token));
		}
		
	
	     @PutMapping("delete/product/{productId}")
	     public ResponseEntity<String>  deleteProductFromCart(@PathVariable("productId") String productId,@RequestHeader("Authorization") String token)
	     {
				return ResponseEntity.status(HttpStatus.OK).body(cartService.removeProductFromCart(productId,token));
	    	 
	     }
	     
	      @PutMapping("clear/cart")
	      public ResponseEntity<String> deleteAllProductsFromCart(@RequestHeader("Authorization") String token)
	      {
	     	 
          return ResponseEntity.status(HttpStatus.OK).body(cartService.clearCart(token));

	     	 
	     }
	     
	
	
	
	
	
	
	

}

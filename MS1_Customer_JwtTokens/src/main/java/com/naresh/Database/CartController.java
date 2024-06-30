package com.naresh.Database;
import com.naresh.Database.Dto.CartDto;
import com.naresh.Database.Dto.CartItemDto;
import com.naresh.Database.Service.CartService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
	
	// TODO:  handling CartAlreadyException raised from producer while consumig /Cart/add/cart
	
	
	
	@Autowired
	CartService cartService;
	
	
	@PostMapping("/create/cart")
	
	public ResponseEntity<String> createYourCart(@RequestBody CartDto cartDto,@RequestHeader("Authorization") String token)
	{
		
		System.out.println(token);
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.createCart(cartDto,token));	
	}
	
	 
	
    @PostMapping("/add/product/to/cart")
	
	public ResponseEntity<String> addingSingleProductToCart(@RequestBody CartItemDto cartItemDto,@RequestHeader("Authorization") String token)
	{
		
		System.out.println(token);
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addSingleProductToCart(cartItemDto, token));	
	}
	
	
     @PostMapping("/add/products/to/cart")
	
	public ResponseEntity<String> addProductsToCart(@RequestBody List<CartItemDto> cartItemDto,@RequestHeader("Authorization") String token)
	{
		
		System.out.println(token);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(cartService.addingProductsToCart(cartItemDto, token));	
	}
	
     @GetMapping("/get/cart/details")
 	public ResponseEntity<CartDto> gettingCart(@RequestHeader("Authorization") String token)
 	{
 		return ResponseEntity.status(HttpStatus.OK).body(cartService.getMyCartDetails(token));	

 	}
 	
     
     @GetMapping("/get/cart/products")
  	public ResponseEntity<List<CartItemDto>> gettingAllMyProducts(@RequestHeader("Authorization") String token)
  	{
  		return ResponseEntity.status(HttpStatus.OK).body(cartService.getAllMyCartProducts(token));	

  	}
  	
     
   @PutMapping("/delete/cart/product/{productId}")
  	public ResponseEntity<String> removeSingleProductFromCart(@PathVariable("productId") String productId,@RequestHeader("Authorization") String token)
  	{
  		return ResponseEntity.status(HttpStatus.OK).body(cartService.removeProductFromCart(productId,token));	

  	}
   
   @PutMapping("/delete/all/products")
   public ResponseEntity<String> removingAllProductsFromCart(@RequestHeader("Authorization") String token)
   {
 		return ResponseEntity.status(HttpStatus.OK).body(cartService.removeAllProductsFromCart(token));	
	   
   }
  	
	
}

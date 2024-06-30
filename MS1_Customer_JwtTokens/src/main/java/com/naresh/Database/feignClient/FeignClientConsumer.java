package com.naresh.Database.feignClient;
import com.naresh.Database.Dto.OrderDto;
import  com.naresh.Database.Dto.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.naresh.Database.Dto.CartDto;
 

@FeignClient("MS8-Cart-App")
public interface FeignClientConsumer {
	
	

	@PostMapping(path="/Cart/add/cart")
	public ResponseEntity<String> addCart(@RequestBody CartDto cartDto,@RequestHeader("Authorization") String token);
	
   
      @PostMapping("/Cart/add/product/to/cart")
 	 public ResponseEntity<String> addingProductToCart(@RequestBody CartItemDto cartItemDto,@RequestHeader("Authorization") String token);
 	 
 		
      @PostMapping("/Cart/add/products/to/cart")
 	   public ResponseEntity<String> addProductsToCart(@RequestBody List<CartItemDto> cartItemDto,@RequestHeader("Authorization") String token);
 	 
      @GetMapping("/Cart/get/my/cart")
		public ResponseEntity<CartDto> getMyCart(@RequestHeader("Authorization") String token);
      
       
       @GetMapping("/Cart/get/my/cartItems")
		public ResponseEntity<List<CartItemDto>> getMyAllCartItems(@RequestHeader("Authorization") String token);
      
       @PutMapping("/Cart/delete/product/{productId}")
	     public ResponseEntity<String>  deleteProductFromCart(@PathVariable("productId") String productId,@RequestHeader("Authorization") String token);
	     
          @PutMapping("/Cart/clear/cart")
	      public ResponseEntity<String> deleteAllProductsFromCart(@RequestHeader("Authorization") String token);
      
          
          
     // ***********Order related endpints 
          
      	@PostMapping("/Cart/create/order")
    	public ResponseEntity<String> placeYourOrder(@RequestBody OrderDto orderDto,@RequestHeader("Authorization") String token);
          
          
          

    	@GetMapping("/Cart/get/orders")
    	public ResponseEntity<List<OrderDto>> getAllOrders(@RequestHeader("Authorization")String token);
          
    	@GetMapping("/Cart/get/order/{orderId}")
    	public ResponseEntity<OrderDto> getOrderById(@PathVariable("orderId") int orderId,@RequestHeader("Authorization")String token);
          
          

    	@DeleteMapping("/Cart/delete/order/{orderId}")
    	public ResponseEntity<String> deleteOrderById(@PathVariable("orderId") int orderId,@RequestHeader("Authorization")String token);
          
          
          
    	@PostMapping("/Cart/get/orders")
    	public ResponseEntity<List<OrderDto>> getOrdersByDate(@RequestParam("orderDate") LocalDate orderDate,@RequestHeader("Authorization")String token);
          
          
    	
    	@PutMapping("/Cart/update/order/{orderId}")
    	public ResponseEntity<String> upadteOrderById(@PathVariable("orderId") int orderId,@RequestBody OrderDto orderDto,@RequestHeader("Authorization") String token);
          
          
          
          
          
}

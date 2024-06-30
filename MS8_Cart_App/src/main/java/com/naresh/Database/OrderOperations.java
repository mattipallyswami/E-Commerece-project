package com.naresh.Database;
import com.naresh.Database.Entity.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.naresh.Database.DTO.OrderDto;
import com.naresh.Database.Service.OrderService;
@RestController
public class OrderOperations {
	
	
	@Autowired
	OrderService orderService;
	 
	
	@PostMapping("/create/order")
	public ResponseEntity<String> placeYourOrder(@RequestBody OrderDto orderDto,@RequestHeader("Authorization") String token) {
		//TODO: process POST request
		
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.CreateOrder(orderDto, token));
	}
	
	@GetMapping("get/orders")
	public ResponseEntity<List<OrderDto>> getAllOrders(@RequestHeader("Authorization")String token)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders(token));
		
	}
	
	
	@GetMapping("/get/order/{orderId}")
	public ResponseEntity<OrderDto> getOrderById(@PathVariable("orderId") int orderId,@RequestHeader("Authorization")String token)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderByOrderId(orderId,token));
	
	}
	
	@DeleteMapping("/delete/order/{orderId}")
	public ResponseEntity<String> deleteOrderById(@PathVariable("orderId") int orderId,@RequestHeader("Authorization")String token)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.cancelOrderByOrderId(orderId, token));
		
	}
	
	
	
	@PostMapping("get/orders")
	public ResponseEntity<List<OrderDto>> getOrdersByDate(@RequestParam("orderDate") LocalDate orderDate,@RequestHeader("Authorization")String token)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrdersByDate(orderDate,token));
		
	}
	
	
	
	@PutMapping("update/order/{orderId}")
	public ResponseEntity<String> upadteOrderById(@PathVariable("orderId") int orderId,@RequestBody OrderDto orderDto,@RequestHeader("Authorization") String token)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderByOrder(orderDto, orderId, token));
		
	}


}

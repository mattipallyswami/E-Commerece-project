package com.naresh.Database;
import com.naresh.Database.Service.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.naresh.Database.Dto.*;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;
	
	
	@PostMapping("/place/order")
	public ResponseEntity<String>  placingYourOrder(@RequestBody OrderDto orderDto,@RequestHeader("Authorization") String token)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createYourOrder(orderDto, token));
		
	}
	
	
	
	@GetMapping("/get/all/orders")
	public ResponseEntity<List<OrderDto>> getAllOrders(@RequestHeader("Authorization")String token)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllMyOrders(token));
  	}
	
	
	@GetMapping("/get/order/by/{orderId}")
	 public ResponseEntity<OrderDto> gettingOrderById(@PathVariable("orderId") int orderId,@RequestHeader("Authorization")String token)
	 {
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderByOrderId(orderId,token));

	 }
	
	@DeleteMapping("/delete/order/by/{orderId}")
	 public ResponseEntity<String> cancellingOrderByOrderId(@PathVariable("orderId") int orderId,@RequestHeader("Authorization")String token)
	 {
		return ResponseEntity.status(HttpStatus.OK).body(orderService.deleteOrderByOrderId(orderId, token));

	 }
	
	
	@PostMapping("/get/orders/by/date")
	public ResponseEntity<List<OrderDto>> gettingOrdersByDate(@RequestParam("orderDate") LocalDate date,@RequestHeader("Authorization")String token)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersByDate(date, token));
		
	}
	
	
	@PutMapping("/update/order/by/{orderId}")
	public ResponseEntity<String> updatingOrderByOrderId(@PathVariable("orderId") int orderId,@RequestBody OrderDto orderDto,@RequestHeader("Authorization") String token)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrderByOrderId(orderId, orderDto, token));
		
	}
	
	
	
}

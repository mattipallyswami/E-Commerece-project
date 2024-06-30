package com.naresh.Database.Service;

import com.naresh.Database.Dto.OrderDto;

import java.time.LocalDate;
import java.util.*;
public interface OrderService {
	
	public String createYourOrder(OrderDto orderDto,String token);
	
	
	
	public List<OrderDto> getAllMyOrders(String token);
	
	
	public OrderDto getOrderByOrderId(int orderId,String token);

	
	public String  deleteOrderByOrderId(int orderId,String token);
	
	
	
	public List<OrderDto> getOrdersByDate(LocalDate date,String token);
	
	
	
	public String updateOrderByOrderId(int orderId,OrderDto orderDto,String token);
	
	
	
}

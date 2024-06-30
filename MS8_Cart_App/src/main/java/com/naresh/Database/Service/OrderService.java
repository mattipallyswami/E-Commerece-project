package com.naresh.Database.Service;
import com.naresh.Database.DTO.OrderDto;

import java.time.LocalDate;
import java.util.List;
import com.naresh.Database.Entity.*;
public interface OrderService {
	
	public String CreateOrder(OrderDto orderDto,String token);
	
	public List<OrderDto> getAllOrders(String token) ;
	
	public OrderDto getOrderByOrderId(Integer orderId,String token);
	
	public String cancelOrderByOrderId(Integer orderId,String token) ;
	
	public List<OrderDto> getAllOrdersByDate(LocalDate date,String token);
	
	public String updateOrderByOrder(OrderDto orderDto,Integer orderId,String token);
	

}

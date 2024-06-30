package com.naresh.Database.Service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naresh.Database.Dto.OrderDto;
import com.naresh.Database.feignClient.FeignClientConsumer;


@Service
public class OrderServiceImpl implements OrderService {

	
	@Autowired
	FeignClientConsumer feignClientConsumer;
	
	@Override
	public String createYourOrder(OrderDto orderDto, String token) {

	
		return   feignClientConsumer.placeYourOrder(orderDto, token).getBody();
		
	 
	}

	@Override
	public List<OrderDto> getAllMyOrders(String token) {


		return feignClientConsumer.getAllOrders(token).getBody();
		
	  
	}

	@Override
	public OrderDto getOrderByOrderId(int orderId,String token) {

		return feignClientConsumer.getOrderById(orderId,token).getBody();
	}

	@Override
	public String deleteOrderByOrderId(int orderId, String token) {
		 
		return feignClientConsumer.deleteOrderById(orderId, token).getBody();
	}

	@Override
	public List<OrderDto> getOrdersByDate(LocalDate date,String token) {
		  
		return feignClientConsumer.getOrdersByDate(date,token).getBody();
	}

	@Override
	public String updateOrderByOrderId(int orderId, OrderDto orderDto, String token) {

	
		return feignClientConsumer.upadteOrderById(orderId, orderDto, token).getBody();

		}

}

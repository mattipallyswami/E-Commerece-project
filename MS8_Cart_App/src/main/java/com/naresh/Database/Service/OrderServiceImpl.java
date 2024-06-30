package com.naresh.Database.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naresh.Database.CustomException.NoOrdersFound;
import com.naresh.Database.DTO.CartItemDto;
import com.naresh.Database.DTO.CustomerAddressDto;
import com.naresh.Database.DTO.OrderDto;
import com.naresh.Database.feignClient.*;
import com.naresh.Database.Token.*;
import com.naresh.Database.Entity.*;
import com.naresh.Database.Mapper.ConverterImpl;
import com.naresh.Database.Repository.CartItemRepository;
import com.naresh.Database.Repository.CartRepository;
import  com.naresh.Database.Repository.*;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	AddressFeignClientConsumer addressFeignClientConsumer;
	
	@Autowired
	JwtToken JwtToken;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	CartServiceImpl cartServiceImpl;
	
	ConverterImpl converterImpl=new ConverterImpl();
	

	@Autowired
	CartItemRepository cartItemRepository;
	
	@Override
	public String CreateOrder(OrderDto orderDto,String token) {

		String customerName=JwtToken.getCustomerNameOfToken(token);
		
		CustomerAddressDto  customerAddressDto=addressFeignClientConsumer.getCustomerAddressByName(token).getBody();
	
		Order order=converterImpl.orderDtoToOrder(orderDto);
		
		
		order.setOrderDate(LocalDate.now());
		
		order.setOrderStatus("placed");
		
        List<CartItem> orderingcartItems=order.getOrderdCartItems();
		
		double totalproductsPrice=orderingcartItems.stream().mapToDouble(item->(cartServiceImpl.getProductPrice(item.getProductId()))*item.getCartItemQuantity()).sum();
		
		order.setTotal(totalproductsPrice);
		 
		
		order.setCustomerName(customerName);
		
		order.setAddressId(customerAddressDto.getAddressId());
		
		
		 
		
		// what actually in cart
		
	 	    Cart customerCart=cartRepository.findByCustomerName(customerName);
		
		    List<CartItem> cartItems =customerCart.getCartItems();
		    
		   List<String> existingProductIdsInCart=cartItems.stream().map(CartItem::getProductId).collect(Collectors.toList());
		
		     
		   Order savedorder=orderRepository.save(order);
		   
		   System.out.println(savedorder.getOrderId());
	        
	      // what actuallly wnats to order
		    
		//   Filter and update cart items
		   List<CartItem> orderedItems=orderingcartItems.stream()
		        .filter(cartItem -> existingProductIdsInCart.contains(cartItem.getProductId())).collect(Collectors.toList());

		   
		   
		   orderedItems.forEach(item->
		   {
			   CartItem exactCartItem=cartItemRepository.getByCartIdAndProductId(customerCart.getCartId(), item.getProductId());
			   
			   
			   exactCartItem.setOrder(savedorder) ;
			   
			   exactCartItem.setCartItemQuantity(item.getCartItemQuantity());
			   cartItemRepository.save(exactCartItem);
		   });
		   
		  
		   
		   
		   
		   //		        .forEach(cartItem -> {
//		        	
//		            CartItem existingCartItem = cartItemRepository.getByCartCartIdAndProductId(customerCart.getCartId(), cartItem.getProductId());
//		            existingCartItem.setOrder(savedorder);  // Associate cart item with the new order
//		            
//		            CartItem updatedCartItem=cartItemRepository.save(existingCartItem);  // Save the updated cart item
//		           
//		            System.out.println(updatedCartItem.getOrder());
//		            
//		        });
		   
		   // Save the order after updating the cart items to avoid concurrent modification
	     //   orderRepository.save(order);
	        
//		    orderingcartItems.stream().forEach(cartitem->
//		     {
//			
//			   if(existingProductIdsInCart.contains(cartitem.getProductId()))
//			   {
//				   
//              ///************TODO iam using cascad in order so it will reflect its child i.e cartItem
//				   
// 			   
////				 
////				  cartitem.setCartItemId(cartItem.getCartItemId());
////				
//				  orderRepository.save(order);
//				  
//				  CartItem cartItem= cartItemRepository.getByCartCartIdAndProductId(customerCart.getCartId(), cartitem.getProductId());	  
//				
//				  cartItem.setOrder(order);
//				  
//				  cartItemRepository.save(cartItem);
//			   }
			
			
		  //  });
		
		
		return "order placed sucessfully";
	}

	@Override
	public OrderDto getOrderByOrderId(Integer orderId,String token) 
	{
           String customerName=JwtToken.getCustomerNameOfToken(token);
		
          List<Order> existingorders= orderRepository.findByCustomerName(customerName);
           
          // checking client entered cartId matches with anyof existing cartIds
          
          boolean isOrderIdExists=existingorders.stream().anyMatch(existingorder->existingorder.getOrderId()==orderId);   
          
			if (isOrderIdExists) {

				Order order = orderRepository.findById(orderId).get();

				System.out.println(order);

				return converterImpl.orderToOrderDto(order);

			} else {

				throw new NoOrdersFound(" order not found with this is" + orderId, new Throwable("no order found"));

			}
         
	}

	@Override
	public List<OrderDto> getAllOrders(String token) {


		
		String customerName=JwtToken.getCustomerNameOfToken(token);
		
		  List<Order> allOrders=orderRepository.findByCustomerName(customerName);
		
		  
		    if(allOrders.isEmpty())
		    {
		    	throw new NoOrdersFound("no orders placed till now",new Throwable("no orders found"));
		    }
		    else
		    {
		    	return  converterImpl.orderListToOrderDtoList(allOrders);
		     
		    }
	}

	@Override
	public String cancelOrderByOrderId(Integer orderId, String token) {


		String customerName=JwtToken.getCustomerNameOfToken(token);
		
		  List<Order> allOrders=orderRepository.findByCustomerName(customerName);
		  
		  
		
		  boolean isOrderIdExists =allOrders.stream().anyMatch(order->order.getOrderId()==orderId);
		
		
		  if(isOrderIdExists)
			{
			  // setting null to records of cartItem where orderId is matches--> to delete respective orderid form orders table 
			  // to handle child record exception while deleting order
			  
                 List<CartItem> existedcartItems=cartItemRepository.findByOrderOrderId(orderId);
			   
                 existedcartItems.stream().forEach(existedcartItem->existedcartItem.setOrder(null)); 
                
                 System.out.println(existedcartItems);
                 // now we can delete order from order table ,as we dont have rspective orderid in cartitems table
                 // we dont get childrecord found exception
                 
				orderRepository.deleteById(orderId);

				return "order deleted sucessfully";

			} else {

				throw new NoOrdersFound(" order not found with this id" + orderId, new Throwable("no order found"));

			}
		
	}

	@Override
	public List<OrderDto> getAllOrdersByDate(LocalDate date,String token) {

     List<Order> existedOrders= orderRepository.findByOrderDateAndCustomerName(date, JwtToken.getCustomerNameOfToken(token));
     
        if(existedOrders.isEmpty())
        {
			throw new NoOrdersFound(" orders not found for this customer" + JwtToken.getCustomerNameOfToken(token), new Throwable("no orders found"));
	
        }
     
   	 return   converterImpl.orderListToOrderDtoList(existedOrders);
	 
	}

	@Override
	public String updateOrderByOrder(OrderDto orderDto, Integer orderId, String token) {
		
		
		// to upadteorder must recive it from db suing identifier 
		
		   Order exstingOrder=orderRepository.findById(orderId).get();
		

		// checking authenticated user to access orderId or not
		
		
		 List<Order> allexistingorders= orderRepository.findByCustomerName(JwtToken.getCustomerNameOfToken(token));
		
		 boolean isOrderExists=allexistingorders.stream().anyMatch(existingorder->existingorder.getOrderId()==orderId);
		
		if(isOrderExists)
		{
			// updated cartItem details---> only want to update ordered quantity
			
			Order updatedorder =converterImpl.orderDtoToOrder(orderDto);
			
			List<CartItem> upadtedCartItems=updatedorder.getOrderdCartItems();   
			
			// existing cartItem details----
			
			List<CartItem> existingCartItems=cartItemRepository.findByOrderOrderId(orderId);
			
			
	    // updating existingCartItems with new orders quantity values 
			
			
			// changing upadtedCartItems to map<productId,cartItem>  or  map<productId,quantity>// we can take directly quantity as we have this only property taht we wnat to upadte
			
			
			Map<String,CartItem> map1=upadtedCartItems.stream().collect(Collectors.toMap(CartItem::getProductId, upadtedCartItem->upadtedCartItem));
			
			
			
			// upadting cartItems of rezpectiev cartItems
			
			existingCartItems.stream().forEach(existingCartItem->
			{
				CartItem exactUpdatedCartItem=map1.get(existingCartItem.getProductId());
				
			     if(exactUpdatedCartItem!=null)
			     { 
			    	 existingCartItem.setCartItemQuantity(exactUpdatedCartItem.getCartItemQuantity());
			    	 
			    	 cartItemRepository.save(existingCartItem);
			    	
			     }
				
			});
			
			// upadtin order in orders table()
			
			exstingOrder.setOrderDate(LocalDate.now());
				
			exstingOrder.setOrderStatus("placed");
				
		        List<CartItem> upadatingcartItems=updatedorder.getOrderdCartItems();
				
				double totalupdatedproductsPrice=upadatingcartItems.stream().mapToDouble(item->(cartServiceImpl.getProductPrice(item.getProductId()))*item.getCartItemQuantity()).sum();
				
				exstingOrder.setTotal(totalupdatedproductsPrice);
			
			orderRepository.save(exstingOrder);
			
			return "order updated Sucessfully";
			
		}
		else
		{
			throw new NoOrdersFound(" order not found with this orderId" +orderId , new Throwable("no orders found"));

		}
		
	
	}
	 
	 

}

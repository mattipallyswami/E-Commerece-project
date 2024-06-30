package com.naresh.Database.Mapper;

import org.mapstruct.Mapper;


import org.mapstruct.Mapping;

import com.naresh.Database.DTO.CartDto;
import com.naresh.Database.DTO.CartItemDto;
import com.naresh.Database.DTO.OrderDto;
import com.naresh.Database.Entity.Cart;
import com.naresh.Database.Entity.CartItem;
import com.naresh.Database.Entity.Order;

import java.util.List;
@Mapper
public interface Converter {
	
	@Mapping(source = "cartItems",target ="cartItemDto")
 	CartDto cartToCartDto(Cart cart);
	
	@Mapping(source = "cartItemDto",target ="cartItems")
	Cart cartDtoToCart(CartDto cartDto);
	
	@Mapping(source = "cart",target ="cartDto")
	CartItemDto cartItemToCartItemDto(CartItem cartItem);
	
	@Mapping(source = "cartDto",target ="cart")
	CartItem cartItemDtoToCartItem(CartItemDto cartItemDto);
	
	@Mapping(source = "orderdCartItemsDto",target ="orderdCartItems")
	 Order  orderDtoToOrder(OrderDto orderDto);
	 
	@Mapping(source = "orderdCartItems",target ="orderdCartItemsDto")
	OrderDto orderToOrderDto(Order order);
	 
	@Mapping(source = "orderdCartItems",target ="orderdCartItemsDto")
	List<OrderDto> orderListToOrderDtoList(List<Order> orders);
	
	
	@Mapping(source = "orderdCartItemsDto",target ="orderdCartItems")
	List<Order> orderDtoListToOrderList(List<OrderDto> orders);
}

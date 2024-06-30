package com.naresh.Database.Service;
import  com.naresh.Database.Entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naresh.Database.DTO.CartItemDto;
import com.naresh.Database.Entity.CartItem;

import com.naresh.Database.Repository.*;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	CartItemRepository cartItemRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	
	@Override
	public String addCartItem(CartItem cartItem,int cartId) {
	 
		Cart cart=new Cart();
		cart.setCartId(cartId);
		
		
		System.out.println(cart.getCartId());
//	  
//		CartItem cart= new CartItem();
//		
//		
//		cart.setCart(cartItem.getCartDto());
//		
//		
//		cartItem.setCart(cartItem.getCart());
		
		cartItem.setCart(cart);
		
		cartItemRepository.save(cartItem);
		
		
		return "cart item added sucessfully";
		
	}

}

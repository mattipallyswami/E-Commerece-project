package com.naresh.Database.Service;

import java.util.List;

import com.naresh.Database.DTO.CartDto;
import com.naresh.Database.DTO.CartItemDto;
import com.naresh.Database.Entity.Cart;
import com.naresh.Database.Entity.CartItem;

public interface CartService {
	
	public String createYourCart(CartDto cartDto,String token);
	
	public long  getProductPrice(String pid);
	
	public double cartTotalValue(int cartId);
	
	 
	
	 	
	public String addProductToCart(CartItemDto cartItemDto,String token);
	public String addProductsToCart(List<CartItemDto> cartItemDto,String token);
	
	public List<CartItemDto> getcartProducts(String token);

	public CartDto getMyCart(String token);
	
	public String removeProductFromCart(String productId,String token);
	
	public String clearCart(String token);
	
	
}

package com.naresh.Database.Service;

import com.naresh.Database.Dto.CartDto;

import com.naresh.Database.Dto.CartItemDto;

import java.util.List;


public interface CartService {
	
	
	public String createCart(CartDto cartDto,String token);
	
	
	
	public String addSingleProductToCart(CartItemDto cartItemDto,String token);
	
	public String addingProductsToCart(List<CartItemDto> cartItemDto,String token);
	
	
	public CartDto getMyCartDetails(String token);
	
	public List<CartItemDto> getAllMyCartProducts(String token);
	
	public String removeProductFromCart(String productId,String token);
	
	public String removeAllProductsFromCart(String token);

	
	
	
}

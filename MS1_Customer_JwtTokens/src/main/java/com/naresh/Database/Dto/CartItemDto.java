package com.naresh.Database.Dto;

import jakarta.persistence.Column;

public class CartItemDto {
	 
	
	private int cartItemId;
	
	 
	private int cartItemQuantity;
	
	 
	private CartDto cartDto;
	
	 
	private String productId;


	public int getCartItemId() {
		return cartItemId;
	}


	public void setCartItemId(int cartItemId) {
		this.cartItemId = cartItemId;
	}


	public int getCartItemQuantity() {
		return cartItemQuantity;
	}


	public void setCartItemQuantity(int cartItemQuantity) {
		this.cartItemQuantity = cartItemQuantity;
	}


		public CartDto getCartDto() {
		return cartDto;
	}


	public void setCartDto(CartDto cartDto) {
		this.cartDto = cartDto;
	}

 

	public CartItemDto(int cartItemId, int cartItemQuantity, CartDto cartDto, String productId) {
		super();
		this.cartItemId = cartItemId;
		this.cartItemQuantity = cartItemQuantity;
		this.cartDto = cartDto;
		this.productId = productId;
	}


	@Override
	public String toString() {
		return "CartItemDto [cartItemId=" + cartItemId + ", cartItemQuantity=" + cartItemQuantity + ", cartDto="
				+ cartDto + ", productId=" + productId + "]";
	}


	public String getProductId() {
		return productId;
	}


	public void setProductId(String productId) {
		this.productId = productId;
	}


	public CartItemDto() {
		super();
	}

	
	
	
}

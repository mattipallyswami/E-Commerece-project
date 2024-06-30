package com.naresh.Database.Dto;

import java.util.List;

public class CartDto {
	
	
	
	private int cartId;
	
	private int cartTotal;
	
	private String customerName;
	
	
	private List<CartItemDto> cartItemDto;

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getCartTotal() {
		return cartTotal;
	}

	public void setCartTotal(int cartTotal) {
		this.cartTotal = cartTotal;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	 
	@Override
	public String toString() {
		return "CartDto [cartId=" + cartId + ", cartTotal=" + cartTotal + ", customerName=" + customerName
				+ ", cartItemDto=" + cartItemDto + "]";
	}

	public CartDto(int cartId, int cartTotal, String customerName, List<CartItemDto> cartItemDto) {
		super();
		this.cartId = cartId;
		this.cartTotal = cartTotal;
		this.customerName = customerName;
		this.cartItemDto = cartItemDto;
	}

	public List<CartItemDto> getCartItemDto() {
		return cartItemDto;
	}

	public void setCartItemDto(List<CartItemDto> cartItemDto) {
		this.cartItemDto = cartItemDto;
	}

	public CartDto() {
		super();
	}

	 
	   

}

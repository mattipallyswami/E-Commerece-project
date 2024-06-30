package com.naresh.Database.Dto;

import java.time.LocalDate;

import java.util.List;

 
 

public class OrderDto {
	 
	private int orderId;
	
 
	private LocalDate orderDate;
	
 
	private String orderStatus;
	
	 
	private double total;
 
	private String customerName;
	
	 
	private int addressId;
	
	
	 private List<CartItemDto> orderdCartItemsDto;


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public LocalDate getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}


	public String getOrderStatus() {
		return orderStatus;
	}


	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public int getAddressId() {
		return addressId;
	}


	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}


	 

	public OrderDto() {
		super();
	}


	public List<CartItemDto> getOrderdCartItemsDto() {
		return orderdCartItemsDto;
	}


	public void setOrderdCartItemsDto(List<CartItemDto> orderdCartItemsDto) {
		this.orderdCartItemsDto = orderdCartItemsDto;
	}


	public OrderDto(int orderId, LocalDate orderDate, String orderStatus, double total, String customerName,
			int addressId, List<CartItemDto> orderdCartItemsDto) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.total = total;
		this.customerName = customerName;
		this.addressId = addressId;
		this.orderdCartItemsDto = orderdCartItemsDto;
	}


	@Override
	public String toString() {
		return "OrderDto [orderId=" + orderId + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus + ", total="
				+ total + ", customerName=" + customerName + ", addressId=" + addressId + ", orderdCartItemsDto="
				+ orderdCartItemsDto + "]";
	}


	 

	 


}

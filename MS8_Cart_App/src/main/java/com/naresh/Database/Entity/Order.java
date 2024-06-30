package com.naresh.Database.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name="orders777")
public class Order {
	
	
	@SequenceGenerator(name="order_seq_gen",sequenceName ="order_seq",initialValue = 5001,allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "order_seq_gen")
	@Id
	@Column(name="order_id")
	private int orderId;
	
	@Column(name="order_date")
	private LocalDate orderDate;
	
	@Column(name="order_status")
	private String orderStatus;
	
	@Column(name="total")
	private double total;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="address_id")
	private int addressId;
	
	
    @OneToMany(mappedBy = "order")
	private List<CartItem> orderdCartItems;



	public int getOrderId() {
		return orderId;
	}


	// iam not using cascade. so inserting order will not going insert its child i.e cartItems
		//@OneToMany
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



	public List<CartItem> getOrderdCartItems() {
		return orderdCartItems;
	}



	public void setOrderdCartItems(List<CartItem> orderdCartItems) {
		this.orderdCartItems = orderdCartItems;
	}



	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", orderStatus=" + orderStatus + ", total="
				+ total + ", customerName=" + customerName + ", addressId=" + addressId + ", orderdCartItems="
				+ orderdCartItems + "]";
	}



	public Order(int orderId, LocalDate orderDate, String orderStatus, double total, String customerName, int addressId,
			List<CartItem> orderdCartItems) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderStatus = orderStatus;
		this.total = total;
		this.customerName = customerName;
		this.addressId = addressId;
		this.orderdCartItems = orderdCartItems;
	}



	public Order() {
		super();
	}
	
	
	
	 

}

package com.naresh.Database.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames ="customer_name"),name="cart777")
public class Cart {
	
	// one to onei scofigured in db
	
	
 	 
	  @Column(name="cart_id")
	  @Id
      private int cartId;
	
	  @Column(name="cart_total")
	  private double cartTotal;
	  
	  @Column(name="customer_name")
	  private String customerName;
	    
	  
	  
	  @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
	  private List<CartItem> cartItems;
	  
 

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getCartTotal() {
		return cartTotal;
	}

	public void setCartTotal(double cartTotal) {
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
		return "Cart [cartId=" + cartId + ", cartTotal=" + cartTotal + ", customerName=" + customerName + ", cartItems="
				+ cartItems + "]";
	}

	public Cart(int cartId, long cartTotal, String customerName) {
		super();
		this.cartId = cartId;
		this.cartTotal = cartTotal;
		this.customerName = customerName;
	}

	public Cart() {
		super();
	}

	 
	
	 
}

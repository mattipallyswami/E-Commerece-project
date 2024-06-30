package com.naresh.Database.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;


@Entity
@Table(name="cart_item777")
public class CartItem {
	
	
	
	@SequenceGenerator(name="cart_seq_gen",sequenceName = "cart_seq",initialValue = 1004,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cart_seq_gen")
	@Column(name="cart_item_id")
	@Id
	private int cartItemId;
	
	@Column(name="cart_item_quantity")
	private int cartItemQuantity;
	
 
	
//	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cart_id")
	private Cart cart;
	
	@Column(name="product_id")
	private String productId;

	
	//@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="order_id")
	private Order order;
	
	
	
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

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
 
	 
 
	public CartItem(int cartItemId, int cartItemQuantity, Cart cart, String productId, Order order) {
		super();
		this.cartItemId = cartItemId;
		this.cartItemQuantity = cartItemQuantity;
		this.cart = cart;
		this.productId = productId;
		this.order = order;
	}

	@Override
	public String toString() {
		return "CartItem [cartItemId=" + cartItemId + ", cartItemQuantity=" + cartItemQuantity + ",  productId=" + productId + "]";
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public CartItem() {
		super();
	}
 
}

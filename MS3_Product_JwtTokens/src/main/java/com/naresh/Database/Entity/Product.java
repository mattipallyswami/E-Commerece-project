package com.naresh.Database.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="product777")
public class Product {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name="product_id")
	private String productId;
	
	@Column(name="product_name")
	private String productName;
	
	@Column(name="price")
	private long price;
	
	@Column(name="description")
	private String description;
	
	@Column(name="manufacturer")
	private String manufacturer;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="category")
	private String category;
	
	@Column(name="status")
	private String status;
	
	
	// just fk takinf as common column 
	@Column(name="seller_name")
	private String sellerName;

	 
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
 

	public Product(String productId, String productName, long price, String description, String manufacturer,
			int quantity, String category, String status, String sellerName) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.description = description;
		this.manufacturer = manufacturer;
		this.quantity = quantity;
		this.category = category;
		this.status = status;
		this.sellerName = sellerName;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price
				+ ", description=" + description + ", manufacturer=" + manufacturer + ", quantity=" + quantity
				+ ", category=" + category + ", status=" + status + ", sellerName=" + sellerName + "]";
	}

	public Product() {
		super();
	}
	
	// lombok
	
	
	
	
 
}

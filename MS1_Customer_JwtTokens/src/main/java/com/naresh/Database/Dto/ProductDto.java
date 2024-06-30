package com.naresh.Database.Dto;
 

public class ProductDto {
	 
//	private String  productId;
	
	 
	private String productName;
	 
	private long price;
	
	 
	private String description;
	
	 
	private String manufacturer;
	
	 
	private int quantity;
	
 
	private String category;
	
	 
	private String status;
	
	 
	private String sellerName;
	// lombok


	 


	public String getProductName() {
		return productName;
	}

//
//	public String getProductId() {
//		return productId;
//	}
//
//
//	public void setProductId(String productId) {
//		this.productId = productId;
//	}


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


 
	public ProductDto(String productName, long price, String description, String manufacturer, int quantity,
			String category, String status, String sellerName) {
		super();
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
		return "ProductDto [productName=" + productName + ", price=" + price + ", description=" + description
				+ ", manufacturer=" + manufacturer + ", quantity=" + quantity + ", category=" + category + ", status="
				+ status + ", sellerName=" + sellerName + "]";
	}

	public ProductDto() {
		super();
	}
	
	

}

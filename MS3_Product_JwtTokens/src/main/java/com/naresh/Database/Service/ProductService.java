package com.naresh.Database.Service;

import java.util.List;

import com.naresh.Database.DTO.ProductDto;
import com.naresh.Database.Entity.Product;

public interface ProductService {
	
	public String addProduct(ProductDto productDto);
 	
	public String addProducts(List<ProductDto> productDtos) ;
	
	public List<ProductDto> gettingAllProducts();
	
	public ProductDto gettingProductByName(String productName);
	
	
	public long getProductPriceById(String productid);
	
 
}

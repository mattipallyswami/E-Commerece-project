package com.naresh.Database.Mapping;

import com.naresh.Database.DTO.ProductDto;
import com.naresh.Database.Entity.Product;
import java.util.List;
 


import org.mapstruct.Mapper;
 
@Mapper
public interface Converter {
	
	
	
	Product productDtoToProduct(ProductDto productDto);
	
	ProductDto productToProductDto(Product product);
	// lists
	
	List<Product> productDtosToProducts(List<ProductDto> productDtos);
	
	List<ProductDto> productsToProductDtos(List<Product> products);


}

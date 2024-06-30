package com.naresh.Database.Service;
import com.naresh.Database.CustomException.ProductAlreadyExists;
import com.naresh.Database.CustomException.ProductNotFound;
import com.naresh.Database.DTO.ProductDto;
import com.naresh.Database.Entity.Product;
import com.naresh.Database.Repository.*;
import com.naresh.Database.Mapping.ConverterImpl;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductServiceImpl implements ProductService{
	
	
	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());

	
	@Autowired
	ProductRepository productRepository;

	ConverterImpl converterImpl=new ConverterImpl();
	@Override
	public String addProduct(ProductDto productDto) {
		
		logger.info("starting of service method");
	 
		 //dto->entity
		
		Product product=converterImpl.productDtoToProduct(productDto);
		
	  Product prod=	productRepository.findByProductName(product.getProductName());
 	 
 		if(prod!=null)
 		{
 		throw new ProductAlreadyExists("product already exists with this name:"+product.getProductName(),new Throwable("product already found in DB with same name"));
 		}
	 	productRepository.save(product);
		
		return "product details added sucessfully";
		
		
	 	}

	@Override
	public String addProducts(List<ProductDto> productDtos) {
		 
		logger.info("starting of service method of Product App");
	
		
		List<Product> products=converterImpl.productDtosToProducts(productDtos);
		
		
		products.stream().forEach(product->
		{
		  Product prods=productRepository.findByProductName(product.getProductName());
			
			if(prods!=null)
			{
		 		throw new ProductAlreadyExists("product already exists with this name:"+product.getProductName(),new Throwable("product already found in DB with same name"));
	
			}
			else
			{
				productRepository.save(product);
			}
		});
			
	   	
	  return "products details added sucessfully";	

	
	
	}

	@Override
	public List<ProductDto> gettingAllProducts() {
		 
		
		logger.info("starting of service method of Product App");
		

		List<Product> products=productRepository.findAll();
		
		if(products.isEmpty())
		{
			throw new ProductNotFound("no products found", new Throwable("no products added to DB "));
	
		}
		return	converterImpl.productsToProductDtos(products);
		
    }

	@Override
	public ProductDto gettingProductByName(String productName) {
		
		logger.info("starting of service method of Product App");

		
		Product product=productRepository.findByProductName(productName);
		
		
		if(product==null)
		{
			throw new ProductNotFound("product not found with this name"+productName, new Throwable("product not existed in db "));
		}
		
		return  converterImpl.productToProductDto(product);
	 	  
	}

	@Override
	public long getProductPriceById(String productid) {

 	
		Product product=productRepository.findById(productid).get();
		
		
		return product.getPrice();
		
	 	
		 
	}
	
	
	
	
	
 
}

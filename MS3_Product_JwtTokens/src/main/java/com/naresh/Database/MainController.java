package com.naresh.Database;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.naresh.Database.DTO.ProductDto;
import com.naresh.Database.Service.ProductService;
import com.naresh.Database.Service.ProductServiceImpl;


@RestController
public class MainController {
	
	
	@Autowired
	ProductService productService;
	
	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());


	@PostMapping(path="add/product")
	public ResponseEntity<String> addingProduct(@RequestBody ProductDto productDto)
	{
		logger.info("starting of Controller method");
	 
		String result=productService.addProduct(productDto);
		
	 	return ResponseEntity.status(HttpStatus.CREATED).body(result);
		
	}
	


	@PostMapping(path="add/products")
	public ResponseEntity<String> addingProducts(@RequestBody List<ProductDto> productDtos)
	{
		logger.info("starting of Controller method");
		
	 	String result=productService.addProducts(productDtos);
	 	return ResponseEntity.status(HttpStatus.CREATED).body(result);
		
	}
	
	@GetMapping(path="get/products")
	public ResponseEntity<List<ProductDto>> getAllProducts()
	{
		logger.info("starting of Controller method of Product App");	
		
	    List<ProductDto> productDtos=productService.gettingAllProducts();
	
	 	return ResponseEntity.status(HttpStatus.OK).body(productDtos);
		
	 }
	

	@GetMapping(path="get/product/by/{productName}")
	public ResponseEntity<ProductDto> getproductByName(@PathVariable("productName") String productName)
	{
		logger.info("starting of Controller method of Product App");	
		
	    ProductDto productDto=productService.gettingProductByName(productName);
	
	 	return ResponseEntity.status(HttpStatus.OK).body(productDto);
		
	 }
 
}

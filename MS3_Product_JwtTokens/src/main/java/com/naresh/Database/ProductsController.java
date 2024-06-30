package com.naresh.Database;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.naresh.Database.Service.ProductService;
import com.naresh.Database.Service.ProductServiceImpl;

@RestController
public class ProductsController {
	
	
	@Autowired
	ProductService productService;
	
	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());
	
	@GetMapping("get/price/{productId}")
	public ResponseEntity<Long> getPrice(@PathVariable("productId") String productId)
	{
	 	
		return ResponseEntity.status(HttpStatus.OK).body(productService.getProductPriceById(productId));
		
	}

}

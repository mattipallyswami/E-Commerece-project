package com.naresh.Database.Service;
import java.util.logging.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.naresh.Database.Repostory.SellerRepository;
import com.naresh.Database.Security.ApiSecurityConfiguration;
import com.naresh.Database.entity.Seller;

import jakarta.servlet.http.HttpServletRequest;

import com.naresh.Database.CustomExceptions.*;
import com.naresh.Database.Dto.AddressDto;
import com.naresh.Database.Dto.ProductDto;
import com.naresh.Database.Dto.SellerAddressDto;
import com.naresh.Database.FiegnClient.AddressMSFeignClient;


@Service
public class SellerService implements UserDetailsService{

	
	
	@Autowired
	AddressMSFeignClient addressMSFeignClient;
	private static final Logger logger = Logger.getLogger(SellerService.class.getName());

	@Autowired
	SellerRepository sellerRepository;
 
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	 
	
	@Override
	public UserDetails loadUserByUsername(String sellerName) throws UsernameNotFoundException {
	 
		UserDetails seller=sellerRepository.findById(sellerName).orElseThrow(()->new UsernameNotFoundException("seller not found with this name"));
		
		
		return seller; 
	}
	
	
	public String addSeller(Seller seller) {
		logger.info("staring of service class method");

		if (sellerRepository.existsById(seller.getSellerName())) {
			throw new SellerAlreadyExist("seller Already Exist with this id" + seller.getSellerId(),
					new Throwable("duplicate customerId found"));
		} else {

			seller.setPassword(bCryptPasswordEncoder.encode(seller.getPassword()));
			seller.setCreatedOn(LocalDateTime.now());
			sellerRepository.save(seller);

			return "seller Registerd suceesfully with " + seller.getSellerId();

		}

	}
	
	public Seller getSellerBySellerName(String SellerName) {

		logger.info("staring of service class method");

		Seller seller = sellerRepository.findById(SellerName)
				.orElseThrow(() -> new SellerNotFound("seller not found with this name"));

		System.out.println(seller);
		return seller;

	}

	// getAllSellers  // 
	// at filter-> is seller and existed in db and loggedin 
	public List<Seller> getAllSellers()
	{
		
		 logger.info("staring of service class method");

	 	return  sellerRepository.findAll();
 	}
	
	public Seller  getSellerById(String sellerId)
	{
		 logger.info("staring of service class method");
      	return  sellerRepository.findBysellerId(sellerId).orElseThrow(()->new SellerNotFound("seller Not Found for this id"+sellerId, new Throwable("seller not exists for this id")));
	 	
	}
	
	
	public boolean isProtectedApi(HttpServletRequest request) {

		Set<String> protectedPrefixes = new HashSet<>();
		protectedPrefixes.add("/get");
		protectedPrefixes.add("/delete");
		protectedPrefixes.add("/add");
		

		logger.info(String.valueOf(protectedPrefixes));

		String reqUrl = request.getServletPath();

		System.out.println(reqUrl);
//		 
//		 System.out.println(request.getPathInfo());
//			
//		 System.out.println(request.getContextPath());

//		 System.out.println(request.getServletPath());
//		 

		boolean results = protectedPrefixes.stream().anyMatch(prefix -> {
			boolean result = reqUrl.startsWith(prefix) ? true : false;

			return result;
		});

		return results;
	}
	
	
	// consuming products Services
	
 	public ResponseEntity<List<ProductDto>> gettingAllProducts()
	{
		 logger.info("staring of service class method of Seller App");

		
		String url="http://localhost:5579/Product/get/products";
		
		
		RestTemplate restTemplate=new RestTemplate();
		
		
		ParameterizedTypeReference<List<ProductDto>> parameterizedTypeReference   = new ParameterizedTypeReference<List<ProductDto>>() {};
		
		
		ResponseEntity<List<ProductDto>> response=restTemplate.exchange(url, HttpMethod.GET, null, parameterizedTypeReference);
		
		
		return response;// response-> responseEntity
	}
	
	public ResponseEntity<ProductDto> gettingProductByName(String productNames)
	{
		 logger.info("staring of service class method of Seller App");

		String url="http://localhost:5579/Product/get/product/by/{productName}";
		
		
		Map<String,String> values=new HashMap<>();
		
		values.put("productName", productNames);
		
		RestTemplate restTemplate=new RestTemplate();
		
		
		ParameterizedTypeReference<ProductDto> parameterizedTypeReference   = new ParameterizedTypeReference<ProductDto>() {};
		
		
		ResponseEntity<ProductDto> response=restTemplate.exchange(url, HttpMethod.GET,null,parameterizedTypeReference, values);
	 	  
		return response;// response-> responseEntity
	}
	// consum,ing address

	public ResponseEntity<String> addingAddress(AddressDto addressDto,String token)
	{
		
		 logger.info("staring of service class method of seller App");
		 
		// addressDto,Token(request Headers) -->HttpEntity
		
		
		
		HttpHeaders requestHeader=new HttpHeaders();
		requestHeader.add("Authorization", token);
		
		HttpEntity entity=new HttpEntity<AddressDto>(addressDto,requestHeader);
		
		
		String url="http://localhost:5580/Address/add/address";
		
		
		RestTemplate restTemplate=new RestTemplate();
		
		
		ResponseEntity<String>  response=restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		
 		return response;
		
	}
	
	//
	public ResponseEntity<String> addAddress(SellerAddressDto sellerAddressDto,String token)
	{
		
		 logger.info("staring of service class method of seller App");
		 
		// addressDto,Token(request Headers) -->HttpEntity
		
		
		
		HttpHeaders requestHeader=new HttpHeaders();
		requestHeader.set("Authorization", token);
		
		HttpEntity<SellerAddressDto> entity=new HttpEntity<SellerAddressDto>(sellerAddressDto,requestHeader);
		
		
		String url="http://localhost:5580/Address/add/sell/address";
		
		
		RestTemplate restTemplate=new RestTemplate();
		
		
		ResponseEntity<String>  response=restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		
 		return response;
		
	}
	
	// ********** fiegn clients
	
	
	 public ResponseEntity<?> addaddres(SellerAddressDto sellerAddressDto,String token )
	 {
			 logger.info("staring of service class method of seller App");
	 	 
		return addressMSFeignClient.addAddressOfSeller(sellerAddressDto, token);
	 }
	//  feign clients using path-variable
	 
	 public  ResponseEntity<SellerAddressDto> getAddr(int aid)
	 {
		 
		 return   addressMSFeignClient.gettinAddressOfSeller(aid);
 	 	 
	 }
	 
	 
	 
	 
	public ResponseEntity<SellerAddressDto> getAddress(int id)
	{
		logger.info("staring of service class method of seller App");
		
		   String url="http://localhost:5580/Address/get/addr/{aid}";
		   
		      Map<String,Integer> values=new HashMap<>();
		      values.put("aid", id);
		   
		      RestTemplate restTemplate=new RestTemplate();
				
		      ParameterizedTypeReference<SellerAddressDto> result=new ParameterizedTypeReference<SellerAddressDto>() {
				                  };
		      
		   
				                  ResponseEntity<SellerAddressDto> res = restTemplate.exchange(url, HttpMethod.GET, null, result, values);
	 	   
		return res;
		   
		   
		   
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

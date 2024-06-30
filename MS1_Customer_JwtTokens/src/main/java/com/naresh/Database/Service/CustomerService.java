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

import com.naresh.Database.Repostory.CustomerRepository;
import com.naresh.Database.Security.ApiSecurityConfiguration;
import com.naresh.Database.entity.Customer;

import jakarta.servlet.http.HttpServletRequest;

import com.naresh.Database.CustomExceptions.*;
import com.naresh.Database.Dto.AddressDto;
import com.naresh.Database.Dto.ProductDto;


@Service
public class CustomerService implements UserDetailsService{

	
	
	private static final Logger logger = Logger.getLogger(CustomerService.class.getName());

	@Autowired
	CustomerRepository customerRepository;
 
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	 
	
	@Override
	public UserDetails loadUserByUsername(String customerName) throws UsernameNotFoundException {
	 
		
		
		UserDetails customer=customerRepository.findById(customerName).orElseThrow(()->new UsernameNotFoundException("customer not found with this name"));
		
		
		return customer; 
	}
	
	
	
	public String addCustomer(Customer customer)
	{
	if(customerRepository.existsById(customer.getCustomerName()))
	{
		throw new CustomerAlreadyExist("customer Already Exist with this id"+customer.getCustomerId(), new Throwable("duplicate customerId found"));
	}
	else
	{
		
		customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
		      customer.setCreatedOn(LocalDateTime.now());
              customerRepository.save(customer);
		  
		   return "customerRegisterd suceesfully with "+customer.getCustomerId();
		
    }

	}
	
	
	public Customer getCustomerById(String customerName)
	{
		
	 	Customer customer=customerRepository.findById(customerName).orElseThrow(()->new CustomerNotFound("customer not found with this name"));
	 	
//	 	customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
	 	
	 	System.out.println(customer);
		return customer;
		
	}


	 
	 
	public boolean isProtectedApi(HttpServletRequest request)
	{
		Set<String> protectedPrefixes   = new HashSet<>();
		 protectedPrefixes.add("/get");
		 protectedPrefixes.add("/delete");
		 protectedPrefixes.add("/create");
		 protectedPrefixes.add("/add");
		 protectedPrefixes.add("/place");
		 protectedPrefixes.add("/update");
		 logger.info(String.valueOf(protectedPrefixes));
		
		String reqUrl=request.getServletPath();
		
	 	
 		 System.out.println(reqUrl);
//		 
//		 System.out.println(request.getPathInfo());
//			
//		 System.out.println(request.getContextPath());
		 
//		 System.out.println(request.getServletPath());
//		 
			
		
		
		boolean results=protectedPrefixes.stream().anyMatch(prefix->
		{
			boolean result=reqUrl.startsWith(prefix)?true:false;
			
			return result;
		});
	  
		return results;
	}
	
	
	
   public Customer getCustomerByCustomerId(String customerId)
   {
	   
	 Customer customer  =customerRepository.findByCustomerId(customerId).orElseThrow(()->new CustomerNotFound("customer not found with this "+customerId, new Throwable("no customer exist in db")));
	   
	   
	return customer;
	
   }
	

	// consuming products Services
	
	public ResponseEntity<List<ProductDto>> gettingAllProducts()
	{
		 logger.info("staring of service class method of User App");

		
		String url="http://localhost:5579/Product/get/products";
		
		
		RestTemplate restTemplate=new RestTemplate();
		
		
		ParameterizedTypeReference<List<ProductDto>> parameterizedTypeReference   = new ParameterizedTypeReference<List<ProductDto>>() {};
		
		
		ResponseEntity<List<ProductDto>> response=restTemplate.exchange(url, HttpMethod.GET, null, parameterizedTypeReference);
		
		
		return response;// response-> responseEntity
	}
	
	public ResponseEntity<?> gettingProductByName(String productNames)
	{
		 logger.info("staring of service class method of User App");

		String url="http://localhost:5579/Product/get/product/by/{productName}";
		
		
		Map<String,String> values=new HashMap<>();
		
		values.put("productName", productNames);
		
		RestTemplate restTemplate=new RestTemplate();
		
		
		ParameterizedTypeReference<ProductDto> parameterizedTypeReference   = new ParameterizedTypeReference<ProductDto>() {};
		
		
		ResponseEntity<?> response=restTemplate.exchange(url, HttpMethod.GET,null,String.class, values);
	 	  
	 
		
		 logger.info("dubug:"+response.getBody());
		 logger.info("dubug:"+response.getHeaders());
		 logger.info("dubug:"+response.getStatusCode());

		 
			return response;// response-> responseEntity
	 	}
	
	// consumin address 
	
	public ResponseEntity<String> addingAddress(AddressDto addressDto,String token)
	{
		
		 logger.info("staring of service class method of User App");
		 
		// addressDto,Token(request Headers) -->HttpEntity
		
		
		
		HttpHeaders requestHeader=new HttpHeaders();
		requestHeader.add("Authorization", token);
		
		HttpEntity entity=new HttpEntity<AddressDto>(addressDto,requestHeader);
		
		
		String url="http://localhost:5580/Address/add/cust/address";
		
		
		RestTemplate restTemplate=new RestTemplate();
		
		
		ResponseEntity<String>  response=restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
		
 		return response;
		
	}
	
	
	
	
	
	
	
 	
}

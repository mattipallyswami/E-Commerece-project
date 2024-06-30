package com.naresh.Database;
import java.util.List;



import java.util.logging.Logger;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.naresh.Database.Dto.AddressDto;
import com.naresh.Database.Dto.ProductDto;

import com.naresh.Database.entity.Customer;
import com.naresh.Database.entity.CustomerCredentials;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;

import com.naresh.Database.CustomExceptions.CustomerAlreadyExist;
import com.naresh.Database.CustomExceptions.InvalidCredentials;
import com.naresh.Database.Security.JwtTokenSecurityFilter;
import com.naresh.Database.Service.*;
import com.naresh.Database.Token.*;



@RestController
public class MainController {
	
	
	private static final Logger logger = Logger.getLogger(MainController.class.getName());
	@Autowired
	AuthenticationManager authenticationManager;
	
 
	@Autowired
	CustomerService customerService;
	
	@Autowired
	TokenManager tokenManager;
	

    @Value("${name}")
     String name;
    
    @Value("${email}")
    String email;
    
 
 	@Value("${gmail}")
 	String gmail;
    
	@PostMapping(path="/customer/reg")
	    public ResponseEntity<?> CustomerRegistration(@RequestBody Customer customer)
	  {
		
		try
		{
			String msg=customerService.addCustomer(customer);
			return ResponseEntity.status(HttpStatus.CREATED).body(msg);
		}
		catch(CustomerAlreadyExist ex)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	  }
	

	@PostMapping(path="customer/dummy")
	    public ResponseEntity<?> Customerdummy()
	  { 
		 	return ResponseEntity.status(HttpStatus.OK).body("iam dummy"+name+"emil is "+email +"environmental gamil is"+gmail);
	 
	  }
	
	
  	
   @PostMapping(path="customer/login",produces = MediaType.APPLICATION_JSON_VALUE)
 	
      public ResponseEntity<?> CustomerLogin(@RequestBody CustomerCredentials customerCredentials)
 	  {
 		
 		      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
 					new  UsernamePasswordAuthenticationToken(customerCredentials.getCustomerName(),customerCredentials.getPassword());
 		
 		 try
 		  {
 			 authenticationManager.authenticate(usernamePasswordAuthenticationToken);// internally i t calls loadUserName() from UUserDetailsService and checks
 		   }
 	     catch(BadCredentialsException ex)
 		  {
  		 	 
 	    	throw new InvalidCredentials("invaid credentials",new Throwable("invalid username or password "));
 	      }
 		
  
 	     String token=this.tokenManager.tokenCreation(customerCredentials.getCustomerName());
  		
 	       Customer loginCustomer= customerService.getCustomerById(customerCredentials.getCustomerName());
 	     
 	  	return ResponseEntity.status(HttpStatus.OK).header("Authorization", token).body("welcome login sucessfully"+loginCustomer);
  }
   
   
   // consuming products services
   
   
   @GetMapping(path="get/products")
   public ResponseEntity<List<ProductDto>> getttingAllProducts()
   {
	    
	   logger.info("staring of Controller method of User App");
	  return  customerService.gettingAllProducts();
 	   
   }
   
   
   @GetMapping(path="get/product/by/{productName}")
   public ResponseEntity<?> gettingProductWithName(@PathVariable("productName") String productName)
   {
	    
	   logger.info(" staring of Controller method of User App");

	  return  customerService.gettingProductByName(productName);
 	   
   }
   
  // consuming Address Services
   
   
   
   @PostMapping("add/addr")
   
   public ResponseEntity<String> addingAddress(@RequestBody AddressDto addressDto,HttpServletRequest request)
   {
	   logger.info(" staring of Controller method of User App");
	   
	   
	   String token=request.getHeader("Authorization");
	   
	   
	 	  return  customerService.addingAddress(addressDto, token);
 	   
   }
   
   
  
   
   
   
}

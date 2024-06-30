package com.naresh.Database;

import java.util.List;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.naresh.Database.CustomExceptions.InvalidCredentials;
import com.naresh.Database.CustomExceptions.SellerAlreadyExist;
import com.naresh.Database.Dto.ProductDto;
import com.naresh.Database.Dto.SellerAddressDto;
import com.naresh.Database.FiegnClient.AddressMSFeignClient;
import com.naresh.Database.Service.SellerService;
import com.naresh.Database.Token.TokenManager;
import com.naresh.Database.entity.Seller;
import com.naresh.Database.entity.SellerCredentials;



@RestController
public class MainController {
 
	@Value("${name}")
	String name;
 
	@Value("${gmail}")
	String gmail;
	private static final Logger logger = Logger.getLogger(MainController.class.getName());
	@Autowired
	AuthenticationManager authenticationManager;
	
 
	@Autowired
	SellerService sellerService;
	
	@Autowired
	TokenManager tokenManager;
 
	
	@PostMapping(path="/seller/reg")
	    public ResponseEntity<?> SellerRegistration(@RequestBody Seller seller)
	  {
		 logger.info("staring of Controller method");

		try
		{
			String msg=sellerService.addSeller(seller);
			return ResponseEntity.status(HttpStatus.CREATED).body(msg);
		}
		catch(SellerAlreadyExist ex)
		{
			return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
		}
	  }

	  @GetMapping(path="/seller/dummy")
	    public ResponseEntity<?> Sellerdummy()
	  {
		 logger.info("staring of Controller method");
 
			return ResponseEntity.status(HttpStatus.CONFLICT).body("iam dummy "+name+"environment gmail is:"+gmail);
	 
	  }
   	
   @PostMapping(path="/seller/login",produces = MediaType.APPLICATION_JSON_VALUE)
 
       public ResponseEntity<?> sellerLogin(@RequestBody SellerCredentials sellerCredentials)
 	  {
  		
		 logger.info("staring of Controller method");

  		    	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
 					new  UsernamePasswordAuthenticationToken(sellerCredentials.getSellerName(),sellerCredentials.getPassword());
  		
 		 try
 		  {
 			 authenticationManager.authenticate(usernamePasswordAuthenticationToken);// internally i t calls loadUserName() from UUserDetailsService and checks
 		   }
  	     catch(BadCredentialsException ex)
  		  {
   		 	 
 	    	throw new InvalidCredentials("invaid credentials",new Throwable("invalid username or password "));
  	      }
  		
  
  	     String token=this.tokenManager.tokenCreation(sellerCredentials.getSellerName());
   		
  	       Seller loginSeller= sellerService.getSellerBySellerName(sellerCredentials.getSellerName());
  	     
  	  	return ResponseEntity.status(HttpStatus.OK).header("Authorization", token).body("welcome login sucessfully"+loginSeller);
    }
   
   
   @GetMapping(path="/get/seller")
   public ResponseEntity<List<Seller>> getAllSellers()
   {
	 logger.info("staring of Controller method");
	    
	 return ResponseEntity.status(HttpStatus.OK).body(sellerService.getAllSellers());
	   
   }
   
   @GetMapping(path="/get/seller/by/{id}")
   public ResponseEntity<Seller> getSellerById(@PathVariable("id") String sellerId)
   {
		 logger.info("staring of Controller method");
 
	return ResponseEntity.status(HttpStatus.OK).body(sellerService.getSellerById(sellerId));
	   
   }
   
   // consuming product rest services
   // here ResponseEntity<List<ProductDto>> --> List<ProductDto> si the type of response body type
   
   @GetMapping(path="get/products")
   public ResponseEntity<List<ProductDto>> getAllProducts()
   {
		 logger.info("staring of Controller method of Seller App");

	   return sellerService.gettingAllProducts();
	   
   }
   

   @GetMapping(path="get/product/by/{productName}")
   public ResponseEntity<ProductDto> gettingProductWithName(@PathVariable("productName") String productName)
   {
		 logger.info("staring of Controller method of Seller App");

	   return sellerService.gettingProductByName(productName);
	   
   }
   
   
   
   
   // consuming addresss 
   
   @PostMapping("add/address")
   public ResponseEntity<?> add(@RequestBody SellerAddressDto sellerAddressDto,@RequestHeader("Authorization") String token) {
       //TODO: process POST request
       
	   
	   logger.info("staring of Controller method of Seller App");
	   String msg=sellerService.addAddress(sellerAddressDto, token).getBody();
	   
       return ResponseEntity.status(HttpStatus.CREATED).body(msg);
   }
   // suing feign clients
   
   
   @PostMapping("add/addr")
   public ResponseEntity<?> adding(@RequestBody SellerAddressDto sellerAddressDto,@RequestHeader("Authorization") String token) {
      
	   logger.info("staring of Controller method of Seller App");
	   return sellerService.addaddres(sellerAddressDto, token);
      
   }
   
   @GetMapping("get/addre/{aid}")
   public ResponseEntity<SellerAddressDto> getting(@PathVariable("aid") int aid)
   {
	   return sellerService.getAddr(aid);
	   
   }
   
   
   
   
   
   
   @PostMapping("get/dummmy")
   public ResponseEntity<?> funny()
   {
	    String url="http://localhost:5580/Address/dummy";
	   
	   
	   RestTemplate restTemplate= new RestTemplate();
	   
	   String msg=restTemplate.exchange(url, HttpMethod.POST, null, String.class).getBody();
	   
	   return ResponseEntity.status(HttpStatus.OK).body(msg);
	   
   }
   
   @GetMapping("get/address/{id}")
   public ResponseEntity<SellerAddressDto> getAddress(@PathVariable("id") int id)
   {
	   logger.info("staring of Controller method of Seller App");
	   
	   return    sellerService.getAddress(id);
 	   
   }
   
   
   
   
    
  }

package com.naresh.Database.Token;

import java.util.Date;



import org.springframework.stereotype.Component;

 
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenManager {

	
	//token-> header,payload,signature(secratekey)
	
	public static final String  SCREATE_KRY="asdfghjkzxcvbnmvjnzl";
	
	 public String tokenCreation(String customerName)
	 {
	 	 String token=Jwts.builder().setSubject(customerName)  // payload
					.setIssuedAt(new Date(System.currentTimeMillis())) //payload
					 .setExpiration(new Date(System.currentTimeMillis()+5 * 60*1000))//payload
					.signWith(SignatureAlgorithm.HS512, SCREATE_KRY)  // header,algorithm// dont give like "SCREATE_KRY" because it is already string, it includes "" in token creation
                          .compact();

               return   token;     
         }
	
	 
	 public String getCustomerNameofToken(String token)
	 {
		
		 
		 String customerNameofToken =  Jwts.parser()
				 .setSigningKey(SCREATE_KRY)
		 		  .parseClaimsJws(token)
				  .getBody()
				  .getSubject();
				
		 return customerNameofToken;
	 }
	
	 
	 
	  
	 
	 public String getSignature(String token)
	 {
		
		 
		  
		 String signature=Jwts.parser()
		 .setSigningKey(SCREATE_KRY)
		 .parseClaimsJws(token)
		 .getSignature();
		  
			System.out.println(signature);	 
		 return signature;
	 }
	
	public boolean isValidUser(String customerName,String token)
	{
	 	
		String tokenCustomerName =  Jwts.parser()
		 .setSigningKey(SCREATE_KRY)
 		  .parseClaimsJws(token)
		  .getBody()
		  .getSubject();
		

		System.out.println("login customer name : "+customerName);
		System.out.println("token customer name  "+tokenCustomerName);

	 
		
	 	return customerName.equals(tokenCustomerName);
		
	}
	
	public boolean isTokenNotExpired(String token)
	{
		
		
		Date expiretime=Jwts.parser()
		.setSigningKey(SCREATE_KRY)
		  .parseClaimsJws(token)
		.getBody().getExpiration();
		
		System.out.println("current time:  "+new Date());
		System.out.println("token time:   "+expiretime);

		
	return 	expiretime.after(new Date());
		
	}
	
	  public boolean isValidToken(String customerName,String token)
	  {
	 	return isValidUser(customerName, token)&&isTokenNotExpired(token);
		
	 	}
	
	
	
	
	
}

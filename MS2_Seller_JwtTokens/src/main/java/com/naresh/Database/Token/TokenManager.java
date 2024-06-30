package com.naresh.Database.Token;

import java.util.Date;



import org.springframework.stereotype.Component;

 
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenManager {

	
	//token-> header,payload,signature(secratekey)
	
	public static final String  SCREATE_KRY="qwertyuiopasdf";
	
	 public String tokenCreation(String sellerName)
	 {
	 	 String token=Jwts.builder().setSubject(sellerName)  // payload
					.setIssuedAt(new Date(System.currentTimeMillis())) //payload
					 .setExpiration(new Date(System.currentTimeMillis()+5 * 60*1000))//payload
					.signWith(SignatureAlgorithm.HS512, SCREATE_KRY)  // header,algorithm// dont give like "SCREATE_KRY" because it is already string, it includes "" in token creation
                          .compact();

               return   token;     
         }
	
	 
	 public String getSellerNameofToken(String token)
	 {
		
		 
		 String SellerNameofToken =  Jwts.parser()
				 .setSigningKey(SCREATE_KRY)
		 		  .parseClaimsJws(token)
				  .getBody()
				  .getSubject();
				
		 return SellerNameofToken;
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
	
	public boolean isValidSeller(String sellerName,String token)
	{
	 	
		String tokenSellerName =  Jwts.parser()
		 .setSigningKey(SCREATE_KRY)
 		  .parseClaimsJws(token)
		  .getBody()
		  .getSubject();
		

		System.out.println("login customer name : "+sellerName);
		System.out.println("token customer name  "+tokenSellerName);

	 
		
	 	return sellerName.equals(tokenSellerName);
		
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
	
	  public boolean isValidToken(String sellerName,String token)
	  {
	 	return isValidSeller(sellerName, token)&&isTokenNotExpired(token);
		
	 	}
	
	
	
	
	
}

package com.naresh.Database.Token;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@Component
public class JwtToken {
	
	
	public static final String  SCREATE_KRY="asdfghjkzxcvbnmvjnzl";

	
	
	public Claims tokenParsing(String token)
	{
		
		return   Jwts.parser()
		        .setSigningKey(SCREATE_KRY)
		        .parseClaimsJws(token)
		        .getBody();
	}
	
	public String getCustomerNameOfToken(String token)
	{
		
		return 	tokenParsing(token).getSubject();
	 
	}
	
	
 
}

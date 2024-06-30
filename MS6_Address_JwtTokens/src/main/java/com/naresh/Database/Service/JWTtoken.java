package com.naresh.Database.Service;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;


@Component
public class JWTtoken {

	
	public static final String  SCREATE_KRY1="asdfghjkzxcvbnmvjnzl";
	public static final String  SCREATE_KRY2="qwertyuiopasdf";
	
	
	public Claims extractingToken(String token)
	{
		return Jwts.parser()
		            .setSigningKey(SCREATE_KRY1)
		            .parseClaimsJws(token)
		            .getBody();
	 }
	
	public String getCustomerNameFromToken(String token)
	{
	 	return extractingToken(token).getSubject();
		
	}
	public Claims extractingSellerToken(String token)
	{
		return Jwts.parser()
		            .setSigningKey(SCREATE_KRY2)
		            .parseClaimsJws(token)
		            .getBody();
	 }
	
	public String getSellerNameFromToken(String token)
	{
	 	return extractingSellerToken(token).getSubject();
		
	}
	
	
	
	
}

package com.naresh.Database.Security;

import java.io.IOException;

import java.util.Set;
import java.util.logging.Logger;
import java.util.HashSet;


import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import com.naresh.Database.Token.*;
import com.fasterxml.jackson.databind.ObjectMapper;
 
import com.naresh.Database.CustomErrorResponse.ErrorResponse;
import com.naresh.Database.CustomExceptions.TokenNotFound;
import com.naresh.Database.Service.*;

 
//@Component
//@Order(Ordered.HIGHEST_PRECEDENCE)
public class JwtTokenSecurityFilter extends OncePerRequestFilter   {

	
	
	@Autowired
	TokenManager tokenManager;

	@Autowired
	SellerService sellerService;
	
	
	@Autowired
	HandlerExceptionResolver handlerExceptionResolver;
	 
	
	private static final Logger logger = Logger.getLogger(JwtTokenSecurityFilter.class.getName());
	 
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("starting of filter");
		 
		boolean isProtectedApi=sellerService.isProtectedApi(request);
		 try
		 {
		 
		 if(isProtectedApi)
		 {
		 
			 logger.info("it is protected Api");
		
		   String token=request.getHeader("Authorization");
		
		   
		   String sellerNameOfToken=null;
		 
		    if(token!=null)
		    {
		     
		    	 
		    sellerNameOfToken=tokenManager.getSellerNameofToken(token); // signtureException Occures here only// JWTExpiredException occures only
		    
 
		    }
		    else
   	        {
		    	 logger.info("token is missing as for protected Api");
 
 		     throw new TokenNotFound("token not found",new Throwable("token is missing please enter token"));
 		     }
		 
		if(sellerNameOfToken!=null&&SecurityContextHolder.getContext().getAuthentication()==null)
		{
		 	// userDetails form db
			 logger.info("validating token");

			
			UserDetails userDetails=sellerService.loadUserByUsername(sellerNameOfToken);
			
			
		 
			boolean isTokenValid=tokenManager.isValidToken(userDetails.getUsername(), token);//userDetails.getUsername()//@Overridepublic String getUsername() {return this.sellerName };
			 
			 
			if(isTokenValid)
			{
				 logger.info(" token validated");
				
			  UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
					  new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null,null);
			
			  SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		 	 
			  logger.info("authentication object seted to SecurityContextHolder ");
			  
		    }
			else
				
			{
				 logger.info("token is invalid");
				 logger.info(String.valueOf(isTokenValid));
				 logger.info(String.valueOf(userDetails));
				  
			}

		 	
		  }
		// filterChain.doFilter(request, response);
		 }
		 else
		 {  
			 logger.info("it is non protected Api");
		 	  
		 }
	      filterChain.doFilter(request, response);
		 
	     }	
		 
		 catch(Exception ex) { 
		    handlerExceptionResolver.resolveException(request, response,null, ex);
		
	          }
	}

	  
	 
}
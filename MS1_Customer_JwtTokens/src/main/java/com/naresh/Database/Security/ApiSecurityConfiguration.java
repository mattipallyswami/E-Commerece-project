package com.naresh.Database.Security;

import java.util.logging.Logger;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 
@Configuration

public class ApiSecurityConfiguration {
	
	
	private static final Logger logger = Logger.getLogger(ApiSecurityConfiguration.class.getName());
	

	
         @Bean
 	    public JwtTokenSecurityFilter jwtTokenSecurityFilter() {
 	        return new JwtTokenSecurityFilter();
 	    }
	
//	@Autowired
//	JwtTokenSecurityFilter jwtTokenSecurityFilter;
	  
	
	  @Bean
	  BCryptPasswordEncoder createBCryptPasswordEncoder()
	  {
		  return new BCryptPasswordEncoder();
	  }
	
	  @Bean
	  AuthenticationManager getAuthenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception
	  {
		  return authenticationConfiguration.getAuthenticationManager();
	  }
	  
	  
	  
	 @Bean
	SecurityFilterChain httpSecurity(HttpSecurity security)
	{
		
		
		SecurityFilterChain filterChain=null;
		try
		{
			logger.info("making which url is protected");
			
			
			security.csrf(csrf->csrf.disable())
			       .cors(cors->cors.disable())
			       .authorizeHttpRequests(
			    		   authorize->authorize.requestMatchers("/customer/info","/customer/reg","/customer/login","customer/dummy")
			    		   .permitAll()
			    		   .anyRequest()
			    		   .authenticated()
			       )
			   
 		       .addFilterBefore(jwtTokenSecurityFilter(), UsernamePasswordAuthenticationFilter.class);
			
			
			filterChain=security.build();
			
			logger.info("end of securityConfig");
			
		}
		catch(Exception e)
		{
			logger.info("error is:" +e.getLocalizedMessage());
		}
		
		 
		
		return filterChain;
		
	}
	 
		
	 

}

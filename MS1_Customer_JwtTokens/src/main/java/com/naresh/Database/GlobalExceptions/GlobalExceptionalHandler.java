package com.naresh.Database.GlobalExceptions;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import com.naresh.Database.CustomErrorResponse.ErrorResponse;
import com.naresh.Database.CustomExceptions.CartAlreadyExistForThisCustomer;
import com.naresh.Database.CustomExceptions.CustomerNotFound;
import com.naresh.Database.CustomExceptions.InvalidCredentials;
import com.naresh.Database.CustomExceptions.TokenNotFound;
import com.naresh.Database.Security.ApiSecurityConfiguration;

import feign.FeignException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
@RestControllerAdvice
public class GlobalExceptionalHandler {
	
	private static final Logger logger = Logger.getLogger(GlobalExceptionalHandler.class.getName());

	@ExceptionHandler(FeignException.class)
	
	public ResponseEntity<String> FeignExceptionHandler(FeignException ex)
	
	{
		// if (ex instanceof CartAlreadyExistForThisCustomer) {

			 
			 
			       
		  
        //		logger.info(ex.getClass().getName());
        //		logger.info(ex.getMessage());
		
		//Object error=ex.contentUTF8().valueOf("cause");
		
		 //JSONObject json = new JSONO
		
                 ErrorResponse errorResponse= new ErrorResponse();
       
      		errorResponse.setMsg(ex.getMessage());
          	String error=ex.contentUTF8();
//        	errorResponse.setCause(error.valueOf("cause"));
		
		 errorResponse.setCause(ex.getCause()!=null?ex.getCause().getMessage():null);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		 
		
	}
	
	@ExceptionHandler(CartAlreadyExistForThisCustomer.class)
	public ResponseEntity<ErrorResponse> CartAlreadyExistForThisCustomerHandler(CartAlreadyExistForThisCustomer ex)
	{
		 
		ErrorResponse errorResponse= new ErrorResponse();
		
		errorResponse.setMsg(ex.getMessage());
		
		errorResponse.setCause(ex.getCause()!=null?ex.getCause().getMessage():null);
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		
		
	}
	
	@ExceptionHandler(TokenNotFound.class)
	public ResponseEntity<?> tokenNotFoundHandler(TokenNotFound ex)
	{
		
		 
		ErrorResponse errorResponse= new ErrorResponse();
		
		errorResponse.setMsg(ex.getMessage());
		
		errorResponse.setCause(ex.getCause()!=null?ex.getCause().getMessage():null);
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	 }

	@ExceptionHandler(CustomerNotFound.class)
	public ResponseEntity<?> CustomerNotFoundHandler(CustomerNotFound ex)
	{
		
		 
		ErrorResponse errorResponse= new ErrorResponse();
		
		errorResponse.setMsg(ex.getMessage());
		
		errorResponse.setCause(ex.getCause()!=null?ex.getCause().getMessage():null);
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	 }
	 
	@ExceptionHandler(InvalidCredentials.class)
	public ResponseEntity<?> InvalidCredentialsHandler(InvalidCredentials ex)
	{
		 
		ErrorResponse errorResponse= new ErrorResponse();
		
		errorResponse.setMsg(ex.getMessage());
		
		errorResponse.setCause(ex.getCause()!=null?ex.getCause().getMessage():null);
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	 }
	
	
 
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> ExpiredJwtExceptionHandler(Exception ex) {
		if (ex instanceof SignatureException) {

			ErrorResponse errorResponse = new ErrorResponse();

			errorResponse.setMsg("inavlid token Signature");

			errorResponse.setCause("JWT signature does not match locally computed signature");

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
		}
		
		if (ex instanceof ExpiredJwtException) {
			
			ExpiredJwtException exx =(ExpiredJwtException)ex;
			
			logger.info(String.valueOf(exx.getClaims()));
			logger.info(String.valueOf(exx.getClaims().getExpiration()));
 			logger.info(String.valueOf(exx.getClaims().getSubject()));
		  //   logger.info(String.valueOf(exx.getHeader()));
//			logger.info(String.valueOf(exx.getMessage()));
//			logger.info(String.valueOf(exx.getLocalizedMessage()));
 			 
			
			ErrorResponse errorResponse = new ErrorResponse();
			 	errorResponse.setMsg("token Expired");

			errorResponse.setCause("JWT expired at"+exx.getClaims().getExpiration()+"Current time:" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss")));

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    	}

		return null;
	}
	
	
	
	// products ->exceptions
	
	
	 
	@ExceptionHandler(HttpClientErrorException.NotFound.class)
	public ResponseEntity<?> HttpClientErrorExceptionHandler(HttpClientErrorException.NotFound ex)
	{
		//here ex -> msg ,cause are under body
		 
		ErrorResponse errorResponse= new ErrorResponse();
		
		
		logger.info("error is"+ex.getResponseBodyAsString());
		logger.info("error is"+ex.getResponseBodyAsString().charAt(1));
		logger.info("error is"+ex.getResponseBodyAsString().charAt(2));
		logger.info("error is"+ex.getLocalizedMessage());
		logger.info("error is"+ex.getCause());
		logger.info("error is"+ex.getStatusCode());
		
		
		
		errorResponse.setMsg(ex.getMessage());
		
		errorResponse.setCause(ex.getCause()!=null?ex.getCause().getMessage():null);
		
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	 }
	 
	
	
	
	
	
	
	
	
	
	
	
	
	

}

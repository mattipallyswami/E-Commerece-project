package com.naresh.Database.GlobalExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.naresh.Controller.CustomErrorResponse.ErrorResponse;
import com.naresh.Database.CustomException.AddressNotFoundWithCustomerName;
import com.naresh.Database.CustomException.ProductAlreadyExists;
import com.naresh.Database.CustomException.ProductNotFound;
import io.jsonwebtoken.SignatureException;
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(AddressNotFoundWithCustomerName.class)
	public ResponseEntity<ErrorResponse> AddressNotFoundWithCustomerNameHandler(AddressNotFoundWithCustomerName ex)
	{
          ErrorResponse errorResponse=new ErrorResponse();
		
		errorResponse.setMsg(ex.getMessage());
		errorResponse.setCause(ex.getCause()!=null?ex.getCause().getMessage():null);
	 	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		
	}
	
	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<ErrorResponse> ProductAlreadyExistsHandler(SignatureException ex)
	{
		ErrorResponse errorResponse=new ErrorResponse();
		
		errorResponse.setMsg("JWT signature does not match locally computed signature");
		errorResponse.setCause(ex.getCause()!=null?ex.getCause().getMessage():null);
	 	return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
		
	}

	@ExceptionHandler(ProductNotFound.class)
	public ResponseEntity<ErrorResponse> ProductNotFoundHandler(ProductNotFound ex)
	{
		ErrorResponse errorResponse=new ErrorResponse();
		
		errorResponse.setMsg(ex.getMessage());
		errorResponse.setCause(ex.getCause()!=null?ex.getCause().getMessage():null);
	 	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
		
	}
	
	
	

}

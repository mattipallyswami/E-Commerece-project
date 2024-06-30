package com.naresh.Database.GlobalExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.naresh.Controller.CustomErrorResponse.ErrorResponse;
import com.naresh.Database.CustomException.ProductAlreadyExists;
import com.naresh.Database.CustomException.ProductNotFound;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	
	
	@ExceptionHandler(ProductAlreadyExists.class)
	public ResponseEntity<ErrorResponse> ProductAlreadyExistsHandler(ProductAlreadyExists ex)
	{
		ErrorResponse errorResponse=new ErrorResponse();
		
		errorResponse.setMsg(ex.getMessage());
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

package com.naresh.Database.GlobalException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.naresh.Database.CustomErrorResponse.ErrorResponse;
import com.naresh.Database.CustomException.CartAlreadyExistForThisCustomer;
import com.naresh.Database.CustomException.CartIdNotFound;
import com.naresh.Database.CustomException.NoOrdersFound;
import com.naresh.Database.CustomException.ThisProductAlreadyExistedInTheCart;

import feign.FeignException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<String> FeignExceptionHandler(FeignException ex)
	{
		ErrorResponse ErrorResponse=new ErrorResponse();
		String error=ex.contentUTF8();
		ErrorResponse.setMsg(ex.getMessage());
		ErrorResponse.setCause(ex.getCause()!=null?ex.getCause().getMessage():null);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);	
	}
	
	@ExceptionHandler(NoOrdersFound.class)
	public ResponseEntity<ErrorResponse> NoOrdersFoundHandler(NoOrdersFound ex)
	{
		ErrorResponse ErrorResponse=new ErrorResponse();
		
		ErrorResponse.setMsg(ex.getMessage());
		ErrorResponse.setCause(ex.getCause()!=null?ex.getCause().getMessage():null);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse);	
	}
	
	@ExceptionHandler(CartIdNotFound.class)
	public ResponseEntity<ErrorResponse> CartIdNotFoundHandler(CartIdNotFound ex)
	{
		ErrorResponse ErrorResponse=new ErrorResponse();
		
		ErrorResponse.setMsg(ex.getMessage());
		ErrorResponse.setCause(ex.getCause()!=null?ex.getCause().getMessage():null);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse);	
	}
	@ExceptionHandler(CartAlreadyExistForThisCustomer.class)
	public ResponseEntity<ErrorResponse> CartAlreadyExistForThisCustomerHandler(CartAlreadyExistForThisCustomer ex)
	{
		ErrorResponse ErrorResponse=new ErrorResponse();
		
		ErrorResponse.setMsg(ex.getMessage());
		ErrorResponse.setCause(ex.getCause()!=null?ex.getCause().getMessage():null);
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse);	
	}
	
	@ExceptionHandler(ThisProductAlreadyExistedInTheCart.class)
	public ResponseEntity<ErrorResponse> ThisProductAlreadyExistedInTheCartHandler(ThisProductAlreadyExistedInTheCart ex)
	{
		ErrorResponse ErrorResponse=new ErrorResponse();
		
		ErrorResponse.setMsg(ex.getMessage());
		ErrorResponse.setCause(ex.getCause()!=null?ex.getCause().getMessage():null);
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponse);	
	}
	

}

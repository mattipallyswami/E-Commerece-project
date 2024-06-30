package com.naresh.Database.CustomExceptions;

import feign.FeignException;
import feign.Request;

public class CartAlreadyExistForThisCustomer extends FeignException {

	protected CartAlreadyExistForThisCustomer(int status, String message, Throwable cause) {
		super(status, message, cause);
		// TODO Auto-generated constructor stub
	}
	protected CartAlreadyExistForThisCustomer(int status, String message, Request request, Throwable cause) {
		super(status, message, request, cause);

	}
	 
	
	 

}

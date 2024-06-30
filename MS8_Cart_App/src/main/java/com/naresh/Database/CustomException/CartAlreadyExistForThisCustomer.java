package com.naresh.Database.CustomException;

public class CartAlreadyExistForThisCustomer extends RuntimeException{
	
	public CartAlreadyExistForThisCustomer(String msg)
	{
		super(msg);
	}
	public CartAlreadyExistForThisCustomer(String msg,Throwable cause)
	{
		super(msg,cause);
	}

}

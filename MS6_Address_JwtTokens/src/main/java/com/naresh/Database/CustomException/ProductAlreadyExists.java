package com.naresh.Database.CustomException;

public class ProductAlreadyExists extends RuntimeException{

	
	public ProductAlreadyExists(String msg)
	{
		super(msg);
	}
   public ProductAlreadyExists(String msg,Throwable cause)
	{
		super(msg,cause);
	}
	
	
}

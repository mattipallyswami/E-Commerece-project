package com.naresh.Database.CustomExceptions;

public class SellerNotFound extends RuntimeException{
	
	public SellerNotFound(String msg)
	{
	 super(msg);
	}
	
	public SellerNotFound(String msg,Throwable cause)
	{
		 super(msg,cause);	
	}
}

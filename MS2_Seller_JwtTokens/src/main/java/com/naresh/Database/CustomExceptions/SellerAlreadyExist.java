package com.naresh.Database.CustomExceptions;

public class SellerAlreadyExist extends RuntimeException{
	
	public SellerAlreadyExist(String msg)
	{
	 super(msg);
	}
	
	public SellerAlreadyExist(String msg,Throwable cause)
	{
		 super(msg,cause);	
	}
	
}

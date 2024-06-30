package com.naresh.Database.CustomExceptions;

public class CustomerAlreadyExist extends RuntimeException{
	
	public CustomerAlreadyExist(String msg)
	{
	 super(msg);
	}
	
	public CustomerAlreadyExist(String msg,Throwable cause)
	{
		 super(msg,cause);	
	}
	
}

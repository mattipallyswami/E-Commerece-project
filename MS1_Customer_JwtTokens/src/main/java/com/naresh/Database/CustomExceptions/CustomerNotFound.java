package com.naresh.Database.CustomExceptions;

public class CustomerNotFound extends RuntimeException{
	
	public CustomerNotFound(String msg)
	{
	 super(msg);
	}
	
	public CustomerNotFound(String msg,Throwable cause)
	{
		 super(msg,cause);	
	}
}

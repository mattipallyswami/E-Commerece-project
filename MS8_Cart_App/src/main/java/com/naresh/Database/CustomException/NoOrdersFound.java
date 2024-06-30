package com.naresh.Database.CustomException;

public class NoOrdersFound extends RuntimeException{
	
	public NoOrdersFound(String msg)
	{
		super(msg);
	}
	public NoOrdersFound(String msg,Throwable cause)
	{
		super(msg,cause);
	}
}

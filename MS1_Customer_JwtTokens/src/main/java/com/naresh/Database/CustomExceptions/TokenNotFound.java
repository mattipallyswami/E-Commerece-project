package com.naresh.Database.CustomExceptions;

public class TokenNotFound  extends RuntimeException{
	
	public TokenNotFound(String msg)
	{
	 super(msg);
	}
	
	public TokenNotFound(String msg,Throwable cause)
	{
		 super(msg,cause);	
	}
}

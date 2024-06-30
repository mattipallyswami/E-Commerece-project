package com.naresh.Database.CustomExceptions;

public class InvalidCredentials extends RuntimeException{
	
	public InvalidCredentials(String msg)
	{
	 super(msg);
	}
	
	public InvalidCredentials(String msg,Throwable cause)
	{
		 super(msg,cause);	
	}
}

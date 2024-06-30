package com.naresh.Database.CustomException;

public class DuplicateCartNotAllowed extends RuntimeException{
	
	public DuplicateCartNotAllowed(String msg)
	{
		super(msg);
	}
	public DuplicateCartNotAllowed(String msg,Throwable cause)
	{
		super(msg,cause);
	}

}

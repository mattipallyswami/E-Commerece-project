package com.naresh.Database.CustomException;

public class CartIdNotFound extends RuntimeException{
	
	public CartIdNotFound(String msg)
	{
		super(msg);
	}
	public CartIdNotFound(String msg,Throwable cause)
	{
		super(msg,cause);
	}
}

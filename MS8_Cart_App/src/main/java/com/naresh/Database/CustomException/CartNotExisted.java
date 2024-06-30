package com.naresh.Database.CustomException;

public class CartNotExisted extends RuntimeException{
	
	public CartNotExisted(String msg)
	{
		super(msg);
	}
	public CartNotExisted(String msg,Throwable cause)
	{
		super(msg,cause);
	}
}

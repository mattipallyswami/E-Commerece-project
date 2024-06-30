package com.naresh.Database.CustomException;

public class ThisProductAlreadyExistedInTheCart extends RuntimeException{
	
	public ThisProductAlreadyExistedInTheCart(String msg)
	{
		super(msg);
	}
	public ThisProductAlreadyExistedInTheCart(String msg,Throwable cause)
	{
		super(msg,cause);
	}

	

}

package com.naresh.Database.CustomException;

public class AddressNotFoundWithCustomerName extends RuntimeException{

	
	public AddressNotFoundWithCustomerName(String msg)
	{
		super(msg);
	}
   public AddressNotFoundWithCustomerName(String msg,Throwable cause)
	{
		super(msg,cause);
	}
}

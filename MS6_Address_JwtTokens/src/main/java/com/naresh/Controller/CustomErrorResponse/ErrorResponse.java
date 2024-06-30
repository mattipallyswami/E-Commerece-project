package com.naresh.Controller.CustomErrorResponse;

public class ErrorResponse {
	
	
	private String msg;
	private String cause;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCause() {
		return cause;
	}
	public void setCause(String cause) {
		this.cause = cause;
	}
	@Override
	public String toString() {
		return "ErrorResponse [msg=" + msg + ", cause=" + cause + "]";
	}
	public ErrorResponse(String msg, String cause) {
		super();
		this.msg = msg;
		this.cause = cause;
	}
	public ErrorResponse( ) {
		super();
		 
	}
	

}

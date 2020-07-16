package com.example.restwebservices.pojos;

import java.util.Date;

public class ExceptionResponse {

	private Date timeStamp;
	private String message;
	private String Details;
	public ExceptionResponse(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		Details = details;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return Details;
	}
	public void setDetails(String details) {
		Details = details;
	}
	@Override
	public String toString() {
		return "ExceptionResponse [timeStamp=" + timeStamp + ", message=" + message + ", Details=" + Details + "]";
	}
	
	
}

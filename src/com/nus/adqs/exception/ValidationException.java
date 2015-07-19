package com.nus.adqs.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("serial")
public class ValidationException extends Exception{
	
	private List<String> messages = new ArrayList<String>();
	public List<String> getMessages() {return messages;}
	public void setMessages(List<String> messages) {this.messages = messages;}
	
	public ValidationException(String... messages){this.messages = Arrays.asList(messages);}
	public ValidationException(String message, Throwable t){super(message,t);}
	
	public ValidationException(List<String> messages){
		super("Validation Error !! ");
		this.messages = messages;
	}



}

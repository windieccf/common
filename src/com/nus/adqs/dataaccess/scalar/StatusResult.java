package com.nus.adqs.dataaccess.scalar;

import java.util.List;

import com.nus.adqs.constant.ConstantWeb;

public class StatusResult {

	private String status = ConstantWeb.MSG_SUCCESS;
	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;}
	
	private List<String> messages;
	public List<String> getMessages() {return messages;}
	public void setMessages(List<String> messages) {this.messages = messages;}
	
	
	public StatusResult(){}
	public StatusResult(String status){this.status=status;}
	public StatusResult(String status , List<String> messages){
		this(status);
		this.messages = messages;
	}
	
	/************************************** SHORT HAND METHOD ****************************************/
	public static StatusResult generateErrorResult(List<String> messages){
		return new StatusResult(ConstantWeb.MSG_ERROR, messages);
	}
	
	public static StatusResult generateSuccessResult(List<String> messages){
		return new StatusResult(ConstantWeb.MSG_SUCCESS, messages);
	}
	
	
}

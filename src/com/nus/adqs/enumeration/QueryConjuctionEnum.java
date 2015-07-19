package com.nus.adqs.enumeration;

public enum QueryConjuctionEnum {
	AND("AND"),
	OR("OR");
	
	private String value;
	public String getValue() { return value; }	
	private QueryConjuctionEnum(String value){this.value = value;}
	

}

package com.nus.adqs.enumeration;

public enum EnumUnicode {
	COMMERCIAL_AT("&#64;","\u0040"),
	COPYRIGHT("&#169;","\u00A9"),
	TM("&#8482;","\u2122");
	
	
	private String htmlCode;
	public String getHtmlCode() {return htmlCode;}
	
	private String unicode;
	public String getUnicode() {return unicode;}


	EnumUnicode(String htmlCode,String unicode){
		this.htmlCode = htmlCode;
		this.unicode = unicode;
	}
	
	
}

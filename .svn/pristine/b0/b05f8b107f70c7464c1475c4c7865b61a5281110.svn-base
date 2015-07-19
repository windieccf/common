package com.nus.adqs.dataaccess.scalar;

import com.nus.adqs.util.StringUtil;

public class PathUriEntity {
	
	private String documentId;
	public String getDocumentId() {return documentId;}
	public void setDocumentId(String documentId) {this.documentId = documentId;}

	private String contentType;
	public String getContentType() {return contentType;}
	public void setContentType(String contentType) {this.contentType = contentType;}
	
	private String path;
	public String getPath() {return path;}
	public void setPath(String path) {this.path = path;}
	
	private String uri;
	public String getUri() {return StringUtil.isEmpty(uri) ? this.getPath() : this.uri;}
	public void setUri(String uri) {this.uri = uri;}
	
	
	public PathUriEntity(){}
	public PathUriEntity(String path){
		this.path = path;
	}
	public PathUriEntity(String path, String uri){
		this.path = path;
		this.uri = uri;
	}
	
	public PathUriEntity(String documentId,String path, String uri){
		this.documentId = documentId;
		this.path = path;
		this.uri = uri;
	}
	

}

package com.nus.adqs.dataaccess.scalar;

import com.nus.adqs.constant.ConstantContentType;
import com.nus.adqs.constant.ConstantWeb;
import com.nus.adqs.util.StringUtil;

public class PreviewEntity {
	
	private String status;
	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;}
	
	private String returnType;
	public String getReturnType() {return returnType;}
	public void setReturnType(String returnType) {this.returnType = returnType;}
	
	private String content;
	public String getContent() {return content;}
	public void setContent(String content) {this.content = content;}
	
	private int currentPage = 0;
	public int getCurrentPage() {return currentPage;}
	public void setCurrentPage(int currentPage) {this.currentPage = currentPage;}
	
	private int totalPage = 0;
	public int getTotalPage() {return totalPage;}
	public void setTotalPage(int totalPage) {this.totalPage = totalPage;}
	
	private String index;
	public String getIndex() {return index;}
	public void setIndex(String index) {this.index = index;}
	
	private String sourceId;
	public String getSourceId() {return sourceId;}
	public void setSourceId(String sourceId) {this.sourceId = sourceId;}
	
	public PreviewEntity(){}
	public PreviewEntity(String status, String returnType, String content){
		this(status,returnType,content,null,null,0,0);
	}
	
	public PreviewEntity(String status, String returnType, String content, String index, String sourceId, int currentPage, int totalPage){
		this.status = status;
		this.returnType = returnType;
		this.content = content;
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.index = index;
		this.sourceId = sourceId;
	}
	
	public static PreviewEntity generateEmptyContent(){
		return new PreviewEntity(ConstantWeb.MSG_ERROR,ConstantContentType.RETURN_TYPE_IMAGE,StringUtil.EMPTY_DOCUMENT_PATTERN);
	}

}

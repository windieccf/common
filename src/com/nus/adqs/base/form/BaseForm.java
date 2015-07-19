package com.nus.adqs.base.form;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.nus.adqs.annotation.Filterable;
import com.nus.adqs.annotation.Parameter;
import com.nus.adqs.constant.ConstantBoolean;
import com.nus.adqs.dataaccess.persistence.SystemSetupHome;
import com.nus.adqs.dataaccess.scalar.form.LabelValue;
import com.nus.adqs.util.MathUtil;
import com.nus.adqs.util.StringUtil;

public class BaseForm<T> {
	
	private int currentPage = 1;
	public int getCurrentPage() {return (totalPage == 0) ? 0 : currentPage;}
	public void setCurrentPage(int currentPage) {this.currentPage = currentPage;}
	public boolean isPageStart(){return (this.currentPage <=1) ;}
	public boolean isPageEnd(){return (this.currentPage>=this.totalPage) ;}
	
	private int rowPerPage = SystemSetupHome.getParam().getRowPerPageasInt();//10;
	public int getRowPerPage() {return rowPerPage;}
	public void setRowPerPage(int rowPerPage) {this.rowPerPage = rowPerPage;}
	
	private int totalPage = 0;
	public int getTotalPage() {return totalPage;}
	public void setTotalPage(int totalPage) {this.totalPage = totalPage;}
	
	private long totalRecord=0;
	public long getTotalRecord() {return totalRecord;}
	public void setTotalRecord(long totalRecord) {this.totalRecord = totalRecord;}
	public boolean isRecordExist(){return this.totalRecord > 0;}
	
	public void calculatePagination(){
		if(totalRecord > 0)
			totalPage = MathUtil.convertBigDecimal(totalRecord).divide(MathUtil.convertBigDecimal(this.getRowPerPage()),0,BigDecimal.ROUND_UP).intValue();
	}
	
	public int getStartPage(){return ((this.currentPage - 1) * this.getRowPerPage());}
	public int getPageStartRecord(){return (totalPage == 0) ? 0 : ( ((currentPage-1) * this.getRowPerPage()) + 1 );}
	public int getPageEndRecord(){
		int endRecord = this.getPageStartRecord() + this.getRowPerPage() -1;
		return (int) ((endRecord > this.getTotalRecord()) ? this.getTotalRecord() : endRecord);
	}
	
	
	/*private List<LabelValue> searchCriteria = new ArrayList<LabelValue>();
	public List<LabelValue> getSearchCriteria() {return searchCriteria;}
	public void setSearchCriteria(List<LabelValue> searchCriteria) {this.searchCriteria = searchCriteria;}
	*/
	
	private String sortField;
	public String getSortField() {return sortField;}
	public void setSortField(String sortField) {this.sortField = sortField;}
	
	private String sortOrder;
	public String getSortOrder() {return sortOrder;}
	public void setSortOrder(String sortOrder) {this.sortOrder = sortOrder;}
	public boolean isOrderAscending(){return ConstantBoolean.YES.equals(sortOrder);}
	public String getOrderAscendingHtml(){return this.isOrderAscending()? "icon-chevron-up" : "icon-chevron-down";}

	private T selectedEntity;
	public T getSelectedEntity() {return selectedEntity;}
	public void setSelectedEntity(T selectedEntity) {this.selectedEntity = selectedEntity;}
	
	private List<T> entities = new ArrayList<T>();
	public List<T> getEntities() {return entities;}
	public void setEntities(List<T> entities) {this.entities = entities;}
	
	private Long selectedId = 0L;
	public Long getSelectedId() {return selectedId;}
	public void setSelectedId(Long selectedId) {this.selectedId = selectedId;}
	
	@SuppressWarnings("unchecked")
	public BaseForm(){
		try {
			this.selectedEntity  = ((Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<LabelValue> getFilters(){
		List<LabelValue> searchFilters = new ArrayList<LabelValue>();
		
		Field fields[] = this.getClass().getDeclaredFields();
		for(Field field : fields){
			
			if(field.getAnnotation(Filterable.class) !=null ){
				field.setAccessible(true);
				try {
					Object value = field.get(this);
					if(value == null || value.toString().length() == 0)
						continue; 
					
					Filterable filterable = field.getAnnotation(Filterable.class);
					if(filterable.multiColumns().length > 0){
						List<LabelValue> labelValues = new ArrayList<LabelValue>();
						for(String column : filterable.multiColumns()){
							labelValues.add(new LabelValue(column,value, filterable.operator()));
						}
						// set the chaining of label value
						LabelValue labelValue = null;
						for(LabelValue chainValue : labelValues){
							if(labelValue == null)
								labelValue = chainValue;
							else
								labelValue.setChainValue(chainValue);
						}
						searchFilters.add(labelValues.get(0));
						
					}else if(!StringUtils.isEmpty(filterable.jpaColumn())){
						searchFilters.add(new LabelValue(filterable.jpaColumn(),value,filterable.operator()));
					}else
						searchFilters.add(new LabelValue(field.getName(),value,filterable.operator()));
					
				} catch (Exception e) {
					e.printStackTrace();
				} 
				
			}
		}

		return searchFilters;
	}
	
	public List<LabelValue> getSortOrders(){
		List<LabelValue> orders= new ArrayList<LabelValue>();
		if(!StringUtil.isEmpty(this.getSortField())){
			orders.add(new LabelValue(this.getSortField(),this.getSortOrder()));
		}
		
		return orders;
	}
	
	@SuppressWarnings("unchecked")
	public <X>List<X> getParameterAsList(Class<X> type, String paramName){
		
		Field fields[] = this.getClass().getDeclaredFields();
		Object value = null;
		for(Field field : fields){
			
			if(field.getAnnotation(Parameter.class)==null)
				continue;
			
			field.setAccessible(true);
			String name = field.getAnnotation(Parameter.class).bindingName();
			name = (StringUtil.isEmpty(name)) ? field.getName() : name;
			
			if(!name.equals(paramName))
				continue;
			
			try {
				value = field.get(this);
				break;
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return (List<X>) value;
	}
	
	
}

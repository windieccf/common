package com.nus.adqs.dataaccess.scalar.form;

import java.util.ArrayList;
import java.util.List;

import com.nus.adqs.constant.ConstantOperator;

public class LabelValue {
	
	private String label;
	public String getLabel() {return label;}
	public void setLabel(String label) {this.label = label;}
	
	private Object value;
	public Object getValue() {return value;}
	public void setValue(Object value) {this.value = value;}
	public Object getJpaValue() {return (ConstantOperator.LIKE.equals(this.operator) ? "%"+this.value.toString().toUpperCase()+"%" : this.value);}
	public String getValueAsString(){return this.value == null ? "" : String.valueOf(this.value); }
	public Long getValueAsLong(){return (this.getValue() == null) ? null :  (Long)this.getValue();}
	
	private String operator;
	public String getOperator() {return operator;}
	public void setOperator(String operator) {this.operator = operator;}
	
	private LabelValue chainValue;
	public LabelValue getChainValue() {return chainValue;}
	public void setChainValue(LabelValue chainValue) {this.chainValue = chainValue;}
	public boolean hasChainValue(){return (this.chainValue!=null);}
	
	public List<String> getChainLabels(){
		List<String> columns = new ArrayList<String>();
		columns.add(label);

		LabelValue chained = this.chainValue;
		while(chained!=null){
			columns.add(chained.getLabel());
			chained = chained.getChainValue();
		}
		return columns;
	}
	
	public List<Object> getChainValues(){
		List<Object> values = new ArrayList<Object>();
		values.add(this.getJpaValue());
		LabelValue chained = this.chainValue;
		while(chained!=null){
			values.add(chained.getJpaValue());
			chained = chained.getChainValue();
		}
		return values;
	}
	
	
	public LabelValue(){}
	public LabelValue(String label, Object value){
		this.label = label;
		this.value = value;
	}
	
	public LabelValue(String label, Object value, String operator){
		this.label = label;
		this.value = value;
		this.operator = operator;
	}

}

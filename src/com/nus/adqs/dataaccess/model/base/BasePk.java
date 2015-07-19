package com.nus.adqs.dataaccess.model.base;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class BasePk implements Serializable {
    private ArrayList<Object> getColumns() {
        ArrayList<Object> columns = new ArrayList<Object>();
        
        Field[] fields = this.getClass().getDeclaredFields();
        
        for (Field f : fields) {
            if (f.getAnnotation(javax.persistence.Column.class) != null)
                try {
                    f.setAccessible(true);
                    columns.add(f.get(this));
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
        }
        
        return columns;        
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
                
        ArrayList<Object> columns = getColumns();        
        for (Object column : columns) result = prime * result + (column == null ? 0 : column.hashCode());        
        return result;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == null || ! other.getClass().equals(getClass())) return false;
        
        ArrayList<Object> thisColumns = getColumns();
        ArrayList<Object> otherColumns = ((BasePk) other).getColumns();
        
        int size = thisColumns.size();
        
        for (int i=0; i<size; i++) {
            Object thisColumn = thisColumns.get(i);
            Object otherColumn = otherColumns.get(i);
            
            if (thisColumn instanceof String && otherColumn instanceof String) {
                thisColumn = thisColumn == null ? null : ((String) thisColumn).trim().toUpperCase();
                otherColumn = otherColumn == null ? null : ((String) otherColumn).trim().toUpperCase();                
            }
            
            if (!(thisColumn == null ? otherColumn == null : thisColumn.equals(otherColumn))) return false;                            
        }        
        
        return true;
    }
    
    @Override
    public String toString() {
        String s = "(";
        
        Field[] fields = this.getClass().getDeclaredFields();
        
        for (Field f : fields) {
            if (f.getAnnotation(javax.persistence.Column.class) != null)
                try {
                    f.setAccessible(true);
                    s += f.getName() + "=" + f.get(this) + ";";
                } catch (IllegalArgumentException e) {                    
                    e.printStackTrace();
                } catch (IllegalAccessException e) {                    
                    e.printStackTrace();
                }
        }
        
        return s + ")";
    }

    public Object getField(String fieldName) {
        try {
            Field field = this.getClass().getDeclaredField(fieldName);
            if (field.getAnnotation(javax.persistence.Column.class) == null) throw new IllegalArgumentException("Field not annotated with @Column");
            field.setAccessible(true);
            return field.get(this);            
            
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Invalid Field Name");        
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }        
    }

    public void setField(String fieldName, Object value) {
        try {
            Field field = this.getClass().getDeclaredField(fieldName.trim());
            if (field.getAnnotation(javax.persistence.Column.class) == null) throw new IllegalArgumentException("Field not annotated with @Column");
            field.setAccessible(true);
            
//            Helper.trace(value);            
//            if (value instanceof String) value = Expressions.instance().createValueExpression((String) value).getValue();            
//            Helper.trace(value);
            
            field.set(this, value);            
            
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("Invalid Field Name");        
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    
    public boolean isSet(){
    		Class<?> klass = this.getClass();
    		Field[] fields = klass.getDeclaredFields();
    		for(Field field : fields){
    			if(field.getAnnotation(javax.persistence.Column.class)!=null) {
    				field.setAccessible(true);
    				Object __obj = null;
    				
					try {__obj = field.get(this);}
					catch (IllegalArgumentException e) {} 
					catch (IllegalAccessException e) {}
					
					if(__obj == null) return false;
    			}
    		}
    	return true;
    }
    
    public String getAsString(){
    	String temp = "";
    	Class<?> klass = this.getClass();
    	Field[] fields = klass.getDeclaredFields();
    	for(Field field : fields){
			if(field.getAnnotation(javax.persistence.Column.class)!=null){
				field.setAccessible(true);
				Object __obj = null;
				
				try {__obj = field.get(this);}
				catch (IllegalArgumentException e) {} 
				catch (IllegalAccessException e) {}
				
				if(__obj == null){
					temp = "";
					break;
				}else {
					temp += __obj.toString();
				}	
			}
		}
    	return temp;
    }
    
}

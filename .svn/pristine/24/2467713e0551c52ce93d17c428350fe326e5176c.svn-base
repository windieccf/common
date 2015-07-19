package com.nus.adqs.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

public class BeanUtil {
	
	@SuppressWarnings({ "rawtypes" })
	public static void copyProperties(Object destinationBean,Object sourceBean,String... excludedPropertyName)throws Exception{
		if(excludedPropertyName == null || excludedPropertyName.length == 0)
			PropertyUtils.copyProperties(destinationBean, sourceBean);
		else{
			Map map = BeanUtil.describe(destinationBean, sourceBean);
			for(String exclude : excludedPropertyName)
				map.remove(exclude);
			
			PropertyUtils.copyProperties(destinationBean,map);
		}
	}
	
	public static Map<String,Object> describe(Object destinationBean,Object sourceBean, String... excludedPropertyName)throws Exception{
		Map<String,Object> map = null;
		// mostlikely error happen., then we have to mannually dig
		if(map == null){
			map = new HashMap<String,Object>();
			List<Field> fields = new ArrayList<Field>();
			List<String> excludedProperties = Arrays.asList(excludedPropertyName);
			
			
			@SuppressWarnings("rawtypes")
			Class currentKlass = destinationBean.getClass();
			while(true){
				fields.addAll(Arrays.asList(currentKlass.getDeclaredFields()));
				currentKlass = currentKlass.getSuperclass();
				if(currentKlass == null || "java.lang.Object".equals(currentKlass.getName()))
					break;
			}
			
			for(Field field : fields){
				try{
					if(excludedProperties.contains(field.getName()))
						continue;
					
					map.put(field.getName(), PropertyUtils.getProperty(sourceBean, field.getName()));
				}catch(Exception e){/*IGNORED*/}
			}
		}
		
		return map;
	}
	
	
	public static Long getAsLong(Object obj){
		if(obj == null) 
			return null;
		
		return Long.parseLong(obj.toString());
	}
	
	public static Integer getAsInteger(Object obj){
		if(obj == null) 
			return null;
		
		return Integer.parseInt(obj.toString());
	}
	
	public static String getAsString(Object txt){
		if(txt == null)
			return null;
		
		if(txt instanceof Character)
			return StringUtil.convertCharToString((Character)txt);
		
		return (String) txt;
		
	}
	

}

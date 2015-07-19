package com.nus.adqs.service;

import java.util.HashMap;
import java.util.Map;

import com.nus.adqs.annotation.ServiceRegistry;

public class ServiceLocator {

	private Map<Class<?>,Object> cache = new HashMap<Class<?>,Object>();
	private static ServiceLocator instance;
	private ServiceLocator(){}
	
	public static ServiceLocator getInstance(){
		
		if(ServiceLocator.instance == null)
			ServiceLocator.instance = new ServiceLocator();
		
		return ServiceLocator.instance;
	}
	
	@SuppressWarnings("unchecked")
	public <X>X getService(Class<X> type)throws Exception{
		return (X) this.lookup(type);
	}
	
	
	@SuppressWarnings("static-access")
	private Object lookup(Class<?> key) throws Exception{
		if(!cache.containsKey(key)){
			String implName = (key.getAnnotation(ServiceRegistry.class)).className();
			cache.put(key, this.getClass().forName(implName).newInstance());
		}
		return cache.get(key);
	}
	
	
	
	
}

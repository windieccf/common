package com.nus.adqs.util.gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class ClassExclusionStrategy implements ExclusionStrategy{

	private List<Class<?>> classes = new ArrayList<Class<?>>();
	private List<String> fields = new ArrayList<String>();
	
	public ClassExclusionStrategy(Class<?>... classArray){
		this.classes = Arrays.asList(classArray);
	}
	
	public ClassExclusionStrategy(String... fields){
		this.fields = Arrays.asList(fields);
	}
	
	@Override
	public boolean shouldSkipClass(Class<?> klass) {
		return classes.contains(klass);
	}

	@Override
	public boolean shouldSkipField(FieldAttributes field) {
		return fields.contains(field.getName());
	}

}

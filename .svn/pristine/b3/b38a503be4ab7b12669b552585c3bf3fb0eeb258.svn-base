package com.nus.adqs.dataaccess.persistence;

import javax.persistence.EntityManager;

public abstract class EmLocator {
	
	protected static EmLocator instance;
	protected EmLocator(){
		/*Meant to be implemented by child class*/
		EmLocator.instance = this;
	}
	
	public static EmLocator getInstance(){
		if(EmLocator.instance == null )
			throw new RuntimeException("EmLocator.getInstance() :: EmLocator has not been initialize");
		
		return EmLocator.instance;
	}
	
	public static EntityManager getEm(){return EmLocator.getInstance().getEntityManager();}
	public abstract EntityManager getEntityManager();

}

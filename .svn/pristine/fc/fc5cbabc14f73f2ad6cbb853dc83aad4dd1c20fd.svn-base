package com.nus.adqs.delegate.scheduler;

import com.nus.adqs.dataaccess.model.master.scheduler.Scheduler;
import com.nus.adqs.delegate.BaseDelegate;
import com.nus.adqs.exception.ValidationException;
import com.nus.adqs.service.scheduler.SchedulerService;

public class SchedulerDelegate extends BaseDelegate<Scheduler>{
	
	public Class<SchedulerService> getServiceClass(){return SchedulerService.class;}
	private static SchedulerDelegate instance;
	
	private SchedulerDelegate(){	/*SINGLETON*/}
	
	public static SchedulerDelegate getInstance(){
		if( SchedulerDelegate.instance == null)
			SchedulerDelegate.instance = new SchedulerDelegate();
		
		return SchedulerDelegate.instance;
	}
	
	/************ EXTRAS ************/
	
	public Scheduler doSave(Scheduler entity) throws ValidationException, Exception{
		return BaseDelegate.getService(this.getServiceClass()).save(entity);
	}
	
	

}

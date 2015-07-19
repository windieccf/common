package com.nus.adqs.service.scheduler;

import com.nus.adqs.annotation.ServiceRegistry;
import com.nus.adqs.dataaccess.model.master.scheduler.Scheduler;
import com.nus.adqs.exception.ValidationException;
import com.nus.adqs.service.BaseService;


@ServiceRegistry(className="com.nus.adqs.service.scheduler.impl.SchedulerImpl")
public interface SchedulerService extends BaseService<Scheduler>{
	
	public Scheduler save(Scheduler entity)throws ValidationException, Exception;

}

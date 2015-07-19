package com.nus.adqs.service.scheduler.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.nus.adqs.base.form.BaseForm;
import com.nus.adqs.dataaccess.model.master.scheduler.Scheduler;
import com.nus.adqs.dataaccess.persistence.EmLocator;
import com.nus.adqs.exception.ValidationException;
import com.nus.adqs.service.impl.BaseServiceImpl;
import com.nus.adqs.service.scheduler.SchedulerService;
import com.nus.adqs.util.BeanUtil;

public class SchedulerImpl extends BaseServiceImpl<Scheduler> implements SchedulerService{

	
	public Scheduler save(Scheduler entity)throws ValidationException, Exception{
		
		Scheduler scheduler = super.getById(entity.getPk());
		scheduler.setNextRunTime(entity.getNextRunTime());
		scheduler.setLastRunTime(entity.getLastRunTime());
		
		EmLocator.getEm().persist(scheduler);
		//EmLocator.getEm().flush();	
		return scheduler;
	}
	
	
	// TODO :: Save method
	public Scheduler save(BaseForm<Scheduler> form)throws ValidationException, Exception {
		this.validate(form);
		
		Scheduler formEntity = form.getSelectedEntity();
		Scheduler entity = super.getById(formEntity.getPk());
		List<String> excludeProperties = new ArrayList<String>();
		excludeProperties.addAll(Arrays.asList(BaseServiceImpl.RESTRICTED_COPY));
		excludeProperties.add("code");
		excludeProperties.add("description");
		excludeProperties.add("lastRunTime");
		excludeProperties.add("taskStatus");
		excludeProperties.add("runClassName");
		BeanUtil.copyProperties(entity, form.getSelectedEntity(), excludeProperties.toArray(new String[0]));
		
		if(entity.isOneTime()){
			entity.setRecur(0L);
		}else if(entity.isHourly() || entity.isDaily()){
			entity.setRunOnDates(null);
			entity.setRunOnDays(null);
			entity.setRunOnMonths(null);
		}else if(entity.isWeekly()){
			entity.setRunOnDates(null);
			entity.setRunOnMonths(null);
			entity.setRunOnDays( StringUtils.join(form.getParameterAsList(String.class, "runDays"),","));
		}else{
			entity.setRunOnDays(null);
			entity.setRunOnDates(StringUtils.join(form.getParameterAsList(String.class, "runMonthDates"), ","));
			entity.setRunOnMonths(StringUtils.join(form.getParameterAsList(String.class, "runMonths"), ","));
		}
		
		EmLocator.getEm().persist(entity);
		EmLocator.getEm().flush();		

		return entity;
	}
	
	@Override
	public void validate(BaseForm<Scheduler> form) throws ValidationException,Exception {
		Scheduler entity = form.getSelectedEntity();
		List<String> errors = new ArrayList<String>();
		
		if(entity.getNextRunTime() == null)
			errors.add("Next run time is required");
		
		if(entity.isHourly() || entity.isDaily() || entity.isWeekly()){
			if(entity.getRecur() == null)
				errors.add("Recurring is required");
		}
		
		if(entity.isWeekly() && form.getParameterAsList(String.class, "runDays").isEmpty())
			errors.add("Run on days is required");
		
		if(entity.isMonthly() ){
			if(form.getParameterAsList(String.class, "runMonths").isEmpty())
				errors.add("Run on months is required");
			
			if(form.getParameterAsList(String.class, "runMonthDates").isEmpty())
				errors.add("Run dates is required");
		}
			
		if(!errors.isEmpty())
			throw new ValidationException(errors);
		
	}

}

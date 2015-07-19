package com.nus.adqs.delegate.task;

import java.util.Date;

import com.nus.adqs.dataaccess.model.taskmanagement.template.TaskTemplate;
import com.nus.adqs.delegate.BaseDelegate;
import com.nus.adqs.service.task.TaskTemplateService;

public class TaskSchedulerDelegate extends BaseDelegate<TaskTemplate>{

	public Class<TaskTemplateService> getServiceClass(){return TaskTemplateService.class;}
	private static TaskSchedulerDelegate instance;
	
	private TaskSchedulerDelegate(){	/*SINGLETON*/}
	public static TaskSchedulerDelegate getInstance(){
		if( TaskSchedulerDelegate.instance == null)
			TaskSchedulerDelegate.instance = new TaskSchedulerDelegate();
		
		return TaskSchedulerDelegate.instance;
	}
	
	
	/********************************************* EXTRAS *********************************************************/
	
	public void generateTaskFromTemplate(Date callTime) throws Exception{
		super.getService(this.getServiceClass()).generateTaskFromTemplate(callTime);
	}
}

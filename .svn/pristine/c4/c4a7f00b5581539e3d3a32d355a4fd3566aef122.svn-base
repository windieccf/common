package com.nus.adqs.service.task;

import java.util.Date;

import com.nus.adqs.annotation.ServiceRegistry;
import com.nus.adqs.dataaccess.model.taskmanagement.template.TaskTemplate;
import com.nus.adqs.service.BaseService;


@ServiceRegistry(className="com.nus.adqs.service.task.impl.TaskTemplateServiceImpl")
public interface TaskTemplateService extends BaseService<TaskTemplate>{
	
	public void generateTaskFromTemplate(Date callTime) throws Exception;

}

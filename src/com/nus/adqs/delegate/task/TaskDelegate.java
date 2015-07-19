package com.nus.adqs.delegate.task;

import java.util.List;
import java.util.Map;

import com.nus.adqs.dataaccess.model.taskmanagement.SubTask;
import com.nus.adqs.dataaccess.model.taskmanagement.Task;
import com.nus.adqs.dataaccess.scalar.form.FilePartEntity;
import com.nus.adqs.dataaccess.scalar.form.ManagerDashboardEntity;
import com.nus.adqs.dataaccess.scalar.form.UserDashboardEntity;
import com.nus.adqs.dataaccess.scalar.form.UserSubTaskEntity;
import com.nus.adqs.dataaccess.scalar.result.TaskDueEntity;
import com.nus.adqs.delegate.BaseDelegate;
import com.nus.adqs.service.task.TaskService;

public class TaskDelegate extends BaseDelegate<Task>{
	
	public Class<TaskService> getServiceClass(){return TaskService.class;}
	private static TaskDelegate instance;
	
	private TaskDelegate(){	/*SINGLETON*/}
	public static TaskDelegate getInstance(){
		if( TaskDelegate.instance == null)
			TaskDelegate.instance = new TaskDelegate();
		
		return TaskDelegate.instance;
	}
	
	 
	/*********************** EXTRAS *********************************/
	public List<ManagerDashboardEntity> doRetrieveManagmentDashboard(Long userId) throws Exception{
		return BaseDelegate.getService(this.getServiceClass()).doRetrieveManagmentDashboard(userId);
	}
	
	public List<ManagerDashboardEntity> doRetrieveManagmentDashboard(Long userId, Long taskId) throws Exception{
		return BaseDelegate.getService(this.getServiceClass()).doRetrieveManagmentDashboard(userId, taskId);
	}
	
	public int doCountUserTaskStatusByType( Long taskId, String type){
		return BaseDelegate.getService(this.getServiceClass()).doCountUserTaskStatusByType(taskId, type);
	}
	
	public ManagerDashboardEntity doRetrieveUserTaskStatusByType( int rowPerPage, int startIndex , Long taskId, String type){
		return BaseDelegate.getService(this.getServiceClass()).doRetrieveUserTaskStatusByType(rowPerPage, startIndex, taskId, type);
	}
	
	public void saveTask( Task task, Map<String, String[]> parameters, List<FilePartEntity> files) throws Exception{
		 BaseDelegate.getService(this.getServiceClass()).saveTask(task, parameters, files);
	}
	
	public int doCountUserTaskStatusByUserIdType( Long userId, String type){
		return BaseDelegate.getService(this.getServiceClass()).doCountUserTaskStatusByUserIdType(userId, type);
	}
	
	public List<UserDashboardEntity> doRetrieveUserDashboard(Long userId) throws Exception{
		return BaseDelegate.getService(this.getServiceClass()).doRetrieveUserDashboard(userId);
	}

	public List<UserDashboardEntity> doRetrieveUserDashboardByType(int rowPerPage, int startIndex, String type, Long userId) throws Exception{
		return BaseDelegate.getService(this.getServiceClass()).doRetrieveUserDashboardByType(rowPerPage, startIndex, type, userId);
	}
	
	public UserSubTaskEntity doRetrieveUserSubTask(Long subtaskAssignId) throws Exception{
		return BaseDelegate.getService(this.getServiceClass()).doRetrieveUserSubTask(subtaskAssignId);
	}
	
	public void SaveUserSubTask(UserSubTaskEntity userSubtaskEntity,FilePartEntity file) throws Exception{
		BaseDelegate.getService(this.getServiceClass()).doSaveUserSubTask(userSubtaskEntity,file);
	}
	
	public UserSubTaskEntity doRetrieveLastUploadFile(Long subtaskAssignId) throws Exception{
		return BaseDelegate.getService(this.getServiceClass()).doRetrieveLastUploadFile(subtaskAssignId);
	}

	public void doLaunchSubTask(Task task, SubTask subTask) throws Exception{
		BaseDelegate.getService(this.getServiceClass()).doLaunchSubTask(task, subTask);
	}

	public List<TaskDueEntity> doRetrieveDueSubTask(long interval){
		return BaseDelegate.getService(this.getServiceClass()).doRetrieveDueSubTask(interval);
	}

}

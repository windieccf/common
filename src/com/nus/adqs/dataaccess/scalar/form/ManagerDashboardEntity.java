package com.nus.adqs.dataaccess.scalar.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nus.adqs.constant.ConstantStatus;

public class ManagerDashboardEntity {
	
	private String taskName;
	public String getTaskName() {return taskName;}
	public void setTaskName(String taskName) {this.taskName = taskName;}
	
	private Long taskId;
	public Long getTaskId() {return taskId;}
	public void setTaskId(Long taskId) {this.taskId = taskId;}
	
	private Date startDate = new Date();
	public Date getStartDate() {return startDate;}
	public void setStartDate(Date startDate) {this.startDate = startDate;}
	
	private Date endDate = new Date();
	public Date getEndDate() {return endDate;}
	public void setEndDate(Date endDate) {this.endDate = endDate;}
	
	private List<UserTaskStatusEntity> userStatuses = new ArrayList<UserTaskStatusEntity>();
	public List<UserTaskStatusEntity> getUserStatuses() {return userStatuses;}
	public void setUserStatuses(List<UserTaskStatusEntity> userStatuses) {this.userStatuses = userStatuses;}
	public UserTaskStatusEntity addNewUserStatus(){
		userStatuses.add(new UserTaskStatusEntity());
		return userStatuses.get(userStatuses.size()-1);
	}

	private List<SubmissionStatusEntity> submissions = new ArrayList<SubmissionStatusEntity>();
	public List<SubmissionStatusEntity> getSubmissions() {return submissions;}
	public void setSubmissions(List<SubmissionStatusEntity> submissions) {this.submissions = submissions;}
	public void addNewSubmission(Long id, String fullname, Date date){ 
		SubmissionStatusEntity entity =  new SubmissionStatusEntity(id, fullname, date);
		this.submissions.add(entity);
	} 

	private List<TaskStatusDashboardEntity> statuses = new ArrayList<TaskStatusDashboardEntity>();
	public List<TaskStatusDashboardEntity> getStatuses() {return statuses;}
	public void setStatuses(List<TaskStatusDashboardEntity> statuses) {this.statuses = statuses;}
	
	public ManagerDashboardEntity(){
		statuses.add(new TaskStatusDashboardEntity(ConstantStatus.TMS_TASK_USER_PENDING));
		statuses.add(new TaskStatusDashboardEntity(ConstantStatus.TMS_TASK_USER_INPROGRESS));
		statuses.add(new TaskStatusDashboardEntity(ConstantStatus.TMS_TASK_USER_SUBMITTED));
		statuses.add(new TaskStatusDashboardEntity(ConstantStatus.TMS_TASK_USER_REJECTED));
		statuses.add(new TaskStatusDashboardEntity(ConstantStatus.TMS_TASK_CONSOLIDATED));
	}
	
	
	
	/**************************** UTILITIES ****************************/
	
	private TaskStatusDashboardEntity getStatusByCode(String statusCode){
		for(TaskStatusDashboardEntity item : statuses){
			if(item.getStatus().equals(statusCode))
				return item;
		}
		return null;
	}
	
	
	public void setPending(int count){this.getStatusByCode(ConstantStatus.TMS_TASK_USER_PENDING).setCount(count);}
	public void setInprogress(int count){this.getStatusByCode(ConstantStatus.TMS_TASK_USER_INPROGRESS).setCount(count);}
	public void setSubmitted(int count){this.getStatusByCode(ConstantStatus.TMS_TASK_USER_SUBMITTED).setCount(count);}
	public void setRejected(int count){this.getStatusByCode(ConstantStatus.TMS_TASK_USER_REJECTED).setCount(count);}
	public void setConsolidated(int count){this.getStatusByCode(ConstantStatus.TMS_TASK_CONSOLIDATED).setCount(count);}
	public void setCount(String status, int count){
		TaskStatusDashboardEntity entity = this.getStatusByCode(status);
		if(entity!=null)
			entity.setCount(count);
		
	}

}

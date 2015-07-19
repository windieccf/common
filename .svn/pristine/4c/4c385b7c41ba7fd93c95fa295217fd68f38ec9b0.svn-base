package com.nus.adqs.dataaccess.scalar.form;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nus.adqs.constant.ConstantStatus;

public class UserDashboardEntity {
	
	private Long subTaskAsgnId;
	public Long getSubTaskAsgnId() {return subTaskAsgnId;}
	public void setSubTaskAsgnId(Long subTaskAsgnId) {this.subTaskAsgnId = subTaskAsgnId;}
	
	private String taskName;
	public String getTaskName() {return taskName;}
	public void setTaskName(String taskName) {this.taskName = taskName;}
	
	private Long taskId;
	public Long getTaskId() {return taskId;}
	public void setTaskId(Long taskId) {this.taskId = taskId;}
	
	private Long subTaskId;
	public Long getSubTaskId() {return subTaskId;}
	public void setSubTaskId(Long subTaskId) {this.subTaskId = subTaskId;}
	
	private Date startDate = new Date();
	public Date getStartDate() {return startDate;}
	public void setStartDate(Date startDate) {this.startDate = startDate;}
	
	private Date endDate = new Date();
	public Date getEndDate() {return endDate;}
	public void setEndDate(Date endDate) {this.endDate = endDate;}
	
	private String subTaskName;
	public String getSubTaskName() {return subTaskName;}
	public void setSubTaskName(String subTaskName) {this.subTaskName = subTaskName;}
	
	private String userName;
	public String getUserName() {return userName;}
	public void setUserName(String userName) {this.userName = userName;}
	
	private String status;
	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;}
	
	private Date submittedDateTime;
	public Date getSubmittedDateTime() {return submittedDateTime;}
	public void setSubmittedDateTime(Date submittedDateTime) {this.submittedDateTime = submittedDateTime;}
	
	private Date inprogressDateTime;
	public Date getInprogressDateTime() {return inprogressDateTime;}
	public void setInprogressDateTime(Date inprogressDateTime) {this.inprogressDateTime = inprogressDateTime;}
	
	private Date pendingDateTime;
	public Date getPendingDateTime() {return pendingDateTime;}
	public void setPendingDateTime(Date pendingDateTime) {this.pendingDateTime = pendingDateTime;}
	
	private Date rejectedDateTime;
	public Date getRejectedDateTime() {return rejectedDateTime;}
	public void setRejectedDateTime(Date rejectedDateTime) {this.rejectedDateTime = rejectedDateTime;}

	private List<TaskStatusDashboardEntity> statuses = new ArrayList<TaskStatusDashboardEntity>();
	public List<TaskStatusDashboardEntity> getStatuses() {return statuses;}
	public void setStatuses(List<TaskStatusDashboardEntity> statuses) {this.statuses = statuses;}
	
	public UserDashboardEntity(){
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

package com.nus.adqs.dataaccess.scalar.form;

import java.util.Date;

public class UserTaskStatusEntity {
	
	private Long subTaskId;
	public Long getSubTaskId() {return subTaskId;}
	public void setSubTaskId(Long subTaskId) {this.subTaskId = subTaskId;}

	private String subTaskName;
	public String getSubTaskName() {return subTaskName;}
	public void setSubTaskName(String subTaskName) {this.subTaskName = subTaskName;}
	
	private Long subTaskAssignId;
	public Long getSubTaskAssignId() {return subTaskAssignId;}
	public void setSubTaskAssignId(Long subTaskAssignId) {this.subTaskAssignId = subTaskAssignId;}

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
	
	

}

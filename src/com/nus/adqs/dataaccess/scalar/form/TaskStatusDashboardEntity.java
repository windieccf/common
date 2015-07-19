package com.nus.adqs.dataaccess.scalar.form;

import com.nus.adqs.constant.ConstantStatus;

public class TaskStatusDashboardEntity {
	
	private String code;
	public String getCode() {return code;}
	public void setCode(String code) {this.code = code;}

	private String label; // label should have matching label as constant status
	public String getLabel() {return label;}
	
	private String colour;
	public String getColour() {return colour;}
	
	private int count;
	public int getCount() {return count;}
	public void setCount(int count) {this.count = count;}
	
	
	private String status; // refer to ConstantStatus
	public String getStatus() {return status;}
	public void setStatus(String status) {
		this.status = status;
		this.assignLabel(status);
	}
	
	private void assignLabel(String status){
		this.code = status;
		if(ConstantStatus.TMS_TASK_USER_PENDING.equals(status)){
			this.label = "Pending";
			this.colour = "#AAAAAA";
		}else if(ConstantStatus.TMS_TASK_USER_INPROGRESS.equals(status)){
			this.label = "In Progress";
			this.colour = "#88B8E6";
		}else if(ConstantStatus.TMS_TASK_USER_SUBMITTED.equals(status)){
			this.label = "Submitted";
			this.colour = "#9370DB";
		}else if(ConstantStatus.TMS_TASK_USER_REJECTED.equals(status)){
			this.label = "Rejected";
			this.colour = "#D84F5F";
		}else if(ConstantStatus.TMS_TASK_USER_ACCEPTED.equals(status)){
			this.label = "Completed";
			this.colour = "#97BE0D";
		}
	}
	
	public TaskStatusDashboardEntity(){
		
	}
	
	public TaskStatusDashboardEntity(String status){
		this.setStatus(status);
	}

}

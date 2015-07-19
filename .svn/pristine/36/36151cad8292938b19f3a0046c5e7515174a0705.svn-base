package com.nus.adqs.dataaccess.scalar.form;

import java.util.Date;

import org.ocpsoft.prettytime.PrettyTime;

public class SubmissionStatusEntity {
	
	private Long id; // refer to submission ID
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	private String fullname;
	public String getFullname() {return fullname;}
	public void setFullname(String fullname) {this.fullname = fullname;}
	
	private Date date;
	public Date getDate() {return date;}
	public void setDate(Date date) {
		this.date = date;
		this.durationDisplay = (date == null) ? "" : (new PrettyTime()).format(date);
	}
	
	private String durationDisplay;
	public String getDurationDisplay() {return durationDisplay;}
	public void setDurationDisplay(String durationDisplay) {this.durationDisplay = durationDisplay;}
	
	public SubmissionStatusEntity(){}
	public SubmissionStatusEntity(Long id, String fullname, Date date){
		this.id = id;
		this.fullname = fullname;
		this.setDate(date);
	}
	
}

package com.nus.adqs.dataaccess.model.taskmanagement;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;
import com.nus.adqs.dataaccess.model.security.user.UserInfo;

@SuppressWarnings("serial")
@Entity
@Table(name="TASK_DETAIL")
@SequenceGenerator(name="taskDetailSeq", schema=ConstantSchema.COMMON, sequenceName="taskDetailSeq")
public class TaskDetail extends BaseEntity {
	

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO,generator="taskDetailSeq")
	@Column(name="ID")
	private Integer id;
	public Integer getId() {return id;}
	public void setId(Integer id) {this.id = id;}
	@Override
	public Serializable getPk() {return this.getId();}
	
	@Column(name="NAME")
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	@Column(name="DESCRIPTION")
	private String description;
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	@Column(name="START_DATETIME") 
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDateTime;
	public Date getStartDateTime() {return startDateTime;}
	public void setStartDateTime(Date startDateTime) {this.startDateTime = startDateTime;}
	
	@Column(name="END_DATETIME") 
	@Temporal(TemporalType.TIMESTAMP)
	private Date endDateTime;
	public Date getEndDateTime() {return endDateTime;}
	public void setEndDateTime(Date endDateTime) {this.endDateTime = endDateTime;}
	
	@Column(name="PARENT_ID")
	private Integer parentId;
	public Integer getParentId() {return parentId;}
	public void setParentId(Integer parentId) {this.parentId = parentId;}
	
	@Column(name="INITIATOR_ID")
	private Integer initiatorId;
	public Integer getInitiatorId() {return initiatorId;}
	public void setInitiatorId(Integer initiatorId) {this.initiatorId = initiatorId;}
	
//	@OneToOne
//	private UserInfo initiator;
//	public UserInfo getInitiator() {return initiator;}
//	public void setInitiator(UserInfo initiator) {this.initiator = initiator;}


	//public List<UserInfo> getExecutors() {return executors;}
	//public void setExecutors(List<UserInfo> executors) {this.executors = executors;}
	
	@OneToMany
	private List<UserInfo> executors;
	public List<UserInfo> getExecutors() { return executors; }
	public void setExecutors(List<UserInfo> executors) { this.executors = executors; }
	   
	@OneToOne
	private ScheduleDetail scheduleDetail;
	public ScheduleDetail getScheduleDetail() {return scheduleDetail;}
	public void setScheduleDetail(ScheduleDetail scheduleDetail) {this.scheduleDetail = scheduleDetail;}
	
	@OneToMany
	private List<TemplateDetail> templateDetails;
	public List<TemplateDetail> getTemplateDetails() { return templateDetails; }
	public void setTemplateDetails(List<TemplateDetail> templateDetails) { this.templateDetails = templateDetails; }

	
	
}

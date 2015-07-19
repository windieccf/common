package com.nus.adqs.dataaccess.model.taskmanagement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.constant.ConstantStatus;
import com.nus.adqs.constant.ConstantTaskTemplate;
import com.nus.adqs.dataaccess.model.base.BaseEntity;
import com.nus.adqs.dataaccess.model.security.user.UserAccount;
import com.nus.adqs.util.StringUtil;


@SuppressWarnings("serial")
@Entity
@Table(name="SUB_TASK", schema=ConstantSchema.TMS)
@SequenceGenerator(name="subTaskSeq", schema=ConstantSchema.TMS, sequenceName="subTaskSeq")
public class SubTask extends BaseEntity{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO,generator="subTaskSeq")
	@Column(name="ID")
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	@Override
	public Serializable getPk() {return this.getId();}
	
	@Column(name="NAME")
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	@Column(name="DESCRIPTION", length=1000)
	private String description;
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	@Column(name="SEQ_NO")
	private Integer seqNo;
	public Integer getSeqNo() {return seqNo;}
	public void setSeqNo(Integer seqNo) {this.seqNo = seqNo;}
	
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
	
	@Column(name="CONSOLIDATE_RULE", length=1, columnDefinition="CHAR(1)")
	private String consolidateRule = ConstantTaskTemplate.CONSOLIDATE_AS_TAB;
	public String getConsolidateRule() {return consolidateRule;}
	public void setConsolidateRule(String consolidateRule) {this.consolidateRule = consolidateRule;}
	
	@Column(name="STATUS_01", length=1, columnDefinition="CHAR(1)")
	private String status01 = ConstantStatus.TMS_TASK_DRAFT;
	public String getStatus01() {return status01;}
	public void setStatus01(String status01) {this.status01 = status01;}
	public String getStatus01Readable() {
		if (ConstantStatus.TMS_TASK_DRAFT.equals(status01))
			return "DRAFT";
		else if (ConstantStatus.TMS_TASK_LAUNCH.equals(status01))
			return "LAUNCHED";
		else if (ConstantStatus.TMS_TASK_CONSOLIDATED.equals(status01))
			return "CONSOLIDATED";
		else
			return "UNKNOWN";
	}
	public boolean isConsolidated(){return ConstantStatus.TMS_TASK_CONSOLIDATED.equals(this.status01);}
	public boolean isDrafted(){return ConstantStatus.TMS_TASK_DRAFT.equals(this.status01);}
	public boolean isLaunched(){return ConstantStatus.TMS_TASK_LAUNCH.equals(this.status01);}
	public String retrieveStatusByEarlyState(String state){
		if(this.isDrafted())
			return this.getStatus01();

		if(this.isLaunched())
			return (ConstantStatus.TMS_TASK_DRAFT.equals(state)) ?  ConstantStatus.TMS_TASK_DRAFT : ConstantStatus.TMS_TASK_LAUNCH;
		
		// consolidated
		return (ConstantStatus.TMS_TASK_CONSOLIDATED.equals(state)) ? ConstantStatus.TMS_TASK_CONSOLIDATED : state;
	}
	public boolean canBeLaunched() {
		if (this.getDocuments().size() == 0) // subtask has template
			return false;
		if (!this.isDrafted()) // subTask in draft status
			return false;

		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		Date zeroedDate = cal.getTime();

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(this.getEndDateTime());
		endCal.set(Calendar.HOUR_OF_DAY, 0);
		endCal.set(Calendar.MINUTE, 0);
		endCal.set(Calendar.SECOND, 0);
		endCal.set(Calendar.MILLISECOND, 0);
		Date endDateTimeWithoutTimezone = endCal.getTime();

		if (zeroedDate.compareTo(endDateTimeWithoutTimezone) >= 0) // if today is more than or equal to the end date
			return false;

		int numberOfAssignment = 0;
		List<SubTaskAssignment> subTaskAssignments = this.getAssignments();
		for (SubTaskAssignment subTaskAssignment:subTaskAssignments) {
			if (ConstantStatus.DELETED.equals(subTaskAssignment.getStatus()))
				continue;
			numberOfAssignment++;
		}
		if (numberOfAssignment == 0) // more than 0 assigned person
			return false;

		return true;
	}
	
	@Column(name="TASK_ID")
	private Long taskId;
	public Long getTaskId() {return taskId;}
	public void setTaskId(Long taskId) {this.taskId = taskId;}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TASK_ID",referencedColumnName="ID",insertable=false,updatable=false)
	private Task task;
	public Task getTask() {return task;}
	public void setTask(Task task) {
		this.task = task;
		this.setTaskId((this.task == null) ? null : this.task.getId());
	}

	@OneToMany(mappedBy="subTask",fetch=FetchType.LAZY, cascade=CascadeType.ALL,orphanRemoval=true)
	private List<SubTaskDocument> documents = new ArrayList<SubTaskDocument>();
	public List<SubTaskDocument> getDocuments() {return documents;}
	public void setDocuments(List<SubTaskDocument> documents) {this.documents = documents;}
	public SubTaskDocument addNewDocument(){return new SubTaskDocument(this);}
	
	
	@OneToMany(mappedBy="subTask",fetch=FetchType.LAZY, cascade=CascadeType.ALL,orphanRemoval=true)
	private List<SubTaskAssignment> assignments = new ArrayList<SubTaskAssignment>();
	public List<SubTaskAssignment> getAssignments() {return assignments;}
	public void setAssignments(List<SubTaskAssignment> assignments) {this.assignments = assignments;}
	public SubTaskAssignment addNewAssignment(UserAccount userAccount){ return new SubTaskAssignment(this,userAccount);}
	public SubTaskAssignment addNewAssignment(){ return new SubTaskAssignment(this,null);}
	public void markAssignmentDelete(){
		for(SubTaskAssignment item : this.getAssignments()){
			item.setStatus(ConstantStatus.DELETED);
		}
	}
	public SubTaskAssignment retrieveOrAddNewSubTaskAssignmentByUserName(String username){
		if(StringUtil.isEmpty(username))
			return this.addNewAssignment();
		
		for(SubTaskAssignment item : this.getAssignments()){
			if(item.getUserAccount() == null)
				continue;
			
			if(item.getUserAccount().getUsername().equals(username))
				return item;
		}
		
		return this.addNewAssignment();
	}
	
	
	public SubTask(){}
	public SubTask(Task task){
		this.setTask(task);
		this.task.getSubTasks().add(this);
	}
	
	

}

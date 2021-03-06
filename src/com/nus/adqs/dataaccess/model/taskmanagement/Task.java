package com.nus.adqs.dataaccess.model.taskmanagement;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.constant.ConstantStatus;
import com.nus.adqs.dataaccess.model.base.BaseEntity;
import com.nus.adqs.dataaccess.model.taskmanagement.template.TaskTemplate;


@SuppressWarnings("serial")
@Entity
@Table(name="TASK" , schema=ConstantSchema.TMS)
@SequenceGenerator(name="taskSeq", schema=ConstantSchema.TMS, sequenceName="taskSeq")
public class Task extends BaseEntity{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO,generator="taskSeq")
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
	
	
	@Column(name="TASK_TEMPLATE_ID")
	private Long taskTemplateId;
	public Long getTaskTemplateId() {return taskTemplateId;}
	public void setTaskTemplateId(Long taskTemplateId) {this.taskTemplateId = taskTemplateId;}
	
	/************************************************** TABLE JOIN****************************************************************************************/

	@ManyToOne
	@JoinColumn(name="TASK_TEMPLATE_ID",referencedColumnName="ID",insertable=false,updatable=false)
	private TaskTemplate taskTemplate;
	public TaskTemplate getTaskTemplate() {return taskTemplate;}
	public void setTaskTemplate(TaskTemplate taskTemplate) {
		this.taskTemplate = taskTemplate;
		this.setTaskTemplateId((taskTemplate ==null) ? null : taskTemplate.getId());
	}

	@OneToMany(mappedBy="task",fetch=FetchType.LAZY, cascade=CascadeType.ALL,orphanRemoval=true)
	@OrderBy("ID")
	private List<SubTask> subTasks = new ArrayList<SubTask>();
	public List<SubTask> getSubTasks() {return subTasks;}
	public List<SubTask> getActiveSubTasks() {
		ArrayList<SubTask> activeSubTasks = new ArrayList<SubTask>();
		for (SubTask subTask:subTasks) {
			if (subTask.isActive())
				activeSubTasks.add(subTask);
		}
		return activeSubTasks;
	}
	public void setSubTasks(List<SubTask> subTasks) {this.subTasks = subTasks;}
	public SubTask addNewSubTask(){return new SubTask(this);}
	public void markSubTaskDelete(){
		for(SubTask item : this.getSubTasks()){
			item.setStatus(ConstantStatus.DELETED);
		}
	}
	
	
	
	
	public SubTask retrieveOrAddNewSubTaskById(Long subTaskId){
		if(subTaskId == null)
			return this.addNewSubTask();
		
		for(SubTask subTask : this.getSubTasks()){
			if(subTask.getId().equals(subTaskId))
				return subTask;
		}
		
		return this.addNewSubTask();
	}
	
	public void presetTaskEndDate(){
		Date date = null;
		for(SubTask subTask : this.getSubTasks()){
			date = (date == null) ? subTask.getEndDateTime() :   (  ( (subTask.getEndDateTime() !=null) && subTask.getEndDateTime().after(date) ) ?  subTask.getEndDateTime() : date ); 
		}
		this.setEndDateTime(date);
	}
	
	
}

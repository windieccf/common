package com.nus.adqs.dataaccess.model.taskmanagement.template;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ibm.icu.util.Calendar;
import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.constant.ConstantTaskTemplate;
import com.nus.adqs.dataaccess.model.base.BaseEntity;
import com.nus.adqs.util.DateUtil;


@SuppressWarnings("serial")
@Entity
@Table(name="SUB_TASK_TEMPLATE", schema=ConstantSchema.TMS)
@SequenceGenerator(name="subTaskTemplateSeq", schema=ConstantSchema.TMS, sequenceName="subTaskTemplateSeq")
public class SubTaskTemplate extends BaseEntity{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO,generator="subTaskTemplateSeq")
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
	
	@Column(name="START_RULE", length=1, columnDefinition="CHAR(1)")
	private String startRule = ConstantTaskTemplate.RULE_WITH_MAIN_TASK; // refer to the constant value
	public String getStartRule() {return startRule;}
	public void setStartRule(String startRule) {this.startRule = startRule;}

	@Column(name="OFF_SET_DAY")
	private Integer offSetDay = 0;
	public Integer getOffSetDay() {return offSetDay;}
	public void setOffSetDay(Integer offSetDay) {this.offSetDay = offSetDay;}
	
	@Column(name="CONSOLIDATE_RULE", length=1, columnDefinition="CHAR(1)")
	private String consolidateRule = ConstantTaskTemplate.CONSOLIDATE_AS_TAB;
	public String getConsolidateRule() {return consolidateRule;}
	public void setConsolidateRule(String consolidateRule) {this.consolidateRule = consolidateRule;}

	@Column(name="DURATION")
	private Integer duration;// the duration of the task
	public Integer getDuration() {return duration;}
	public void setDuration(Integer duration) {this.duration = duration;}
	
	@Column(name="TASK_TEMPLATE_ID")
	private Long taskTemplateId;
	public Long getTaskTemplateId() {return taskTemplateId;}
	public void setTaskTemplateId(Long taskTemplateId) {this.taskTemplateId = taskTemplateId;}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TASK_TEMPLATE_ID",referencedColumnName="ID",insertable=false,updatable=false)
	private TaskTemplate taskTemplate;
	public TaskTemplate getTaskTemplate() {return taskTemplate;}
	public void setTaskTemplate(TaskTemplate taskTemplate) {
		this.taskTemplate = taskTemplate;
		this.setTaskTemplateId((this.taskTemplate == null) ? null : this.taskTemplate.getId());
	}

	@OneToMany(mappedBy="subTaskTemplate",fetch=FetchType.LAZY, cascade=CascadeType.ALL,orphanRemoval=true)
	private List<SubTaskDocumentTemplate> documentTemplates = new ArrayList<SubTaskDocumentTemplate>();
	public List<SubTaskDocumentTemplate> getDocumentTemplates() {return documentTemplates;}
	public void setDocumentTemplates(List<SubTaskDocumentTemplate> documentTemplates) {this.documentTemplates = documentTemplates;}
	public SubTaskDocumentTemplate addNewDocumentTemplate(){return new SubTaskDocumentTemplate(this);}
	
	public SubTaskTemplate(){}
	public SubTaskTemplate(TaskTemplate taskTemplate){
		this.setTaskTemplate(taskTemplate);
		this.taskTemplate.getSubTaskTemplates().add(this);
	}
	
	
	/*************************************** UTILITY ***********************************************/
	public Date generateStartDate(Date date){
		if(ConstantTaskTemplate.RULE_WITH_MAIN_TASK.equals(this.startRule)){
			date = DateUtil.addTime(date, Calendar.DATE, this.offSetDay);
		}

		return date;
	}
	
	public Date generateEndDate(Date date){
		date = DateUtil.addTime(date, Calendar.DATE, this.duration);
		return date;
	}
	
	
}

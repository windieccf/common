package com.nus.adqs.dataaccess.model.taskmanagement.template;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;


@SuppressWarnings("serial")
@Entity
@Table(name="SUB_TASK_DOC_TEMPLATE", schema=ConstantSchema.TMS)
@SequenceGenerator(name="subTaskDocTemplateSeq", schema=ConstantSchema.TMS, sequenceName="subTaskDocTemplateSeq")
public class SubTaskDocumentTemplate extends BaseEntity{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO,generator="subTaskDocTemplateSeq")
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

	@Column(name="TEMPLATE_PATH",length=500)
	private String templatePath;
	public String getTemplatePath() {return templatePath;}
	public void setTemplatePath(String templatePath) {this.templatePath = templatePath;}
	
	@Column(name="SUB_TASK_TEMPLATE_ID")
	private Long subTaskTemplateId;
	public Long getSubTaskTemplateId() {return subTaskTemplateId;}
	public void setSubTaskTemplateId(Long subTaskTemplateId) {this.subTaskTemplateId = subTaskTemplateId;}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SUB_TASK_TEMPLATE_ID",referencedColumnName="ID",insertable=false,updatable=false)
	private SubTaskTemplate subTaskTemplate;
	public SubTaskTemplate getSubTaskTemplate() {return subTaskTemplate;}
	public void setSubTaskTemplate(SubTaskTemplate subTaskTemplate) {
		this.subTaskTemplate = subTaskTemplate;
		this.setSubTaskTemplateId((this.subTaskTemplate == null) ? null : this.subTaskTemplate.getId());
	}
	
	
	public SubTaskDocumentTemplate(){}
	public SubTaskDocumentTemplate(SubTaskTemplate subTaskTemplate){
		this.setSubTaskTemplate(subTaskTemplate);
		subTaskTemplate.getDocumentTemplates().add(this);
	}
	

}

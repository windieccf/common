package com.nus.adqs.dataaccess.model.taskmanagement;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;

@SuppressWarnings("serial")
@Entity
@Table(name="TEMPLATE_DETAIL")
@SequenceGenerator(name="templateDetailSeq", schema=ConstantSchema.COMMON, sequenceName="templateDetailSeq")
public class TemplateDetail extends BaseEntity {
	

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO,generator="templateDetailSeq")
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

	@Column(name="PATH")
	private String path;
	public String getPath() {return path;}
	public void setPath(String path) {this.path = path;}
	
}

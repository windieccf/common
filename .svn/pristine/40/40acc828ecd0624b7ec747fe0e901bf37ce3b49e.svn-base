package com.nus.adqs.dataaccess.model.master;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;


@SuppressWarnings("serial")
@Entity
@Table(name="ROLE_TYPE", schema=ConstantSchema.COMMON,  uniqueConstraints = @UniqueConstraint(columnNames = "CODE"))
@NamedQueries({@NamedQuery(name="RoleType_listByCode" ,query="SELECT e FROM RoleType e where e.code=:code")})
@SequenceGenerator(name="roleSeq", sequenceName="roleSeq")
public class RoleType extends BaseEntity{
	
	public static final String LIST_BY_CODE = "RoleType_listByCode";
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO,generator="roleSeq")
	@Column(name="ID")
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	@Override
	public Serializable getPk() {return this.getId();}
	
	@Column(name="CODE", length=30)
	private String code;
	public String getCode() {return code;}
	public void setCode(String code) {this.code = code;}

	@Column(name="DESCRIPTION", length=100)
	private String description;
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
}

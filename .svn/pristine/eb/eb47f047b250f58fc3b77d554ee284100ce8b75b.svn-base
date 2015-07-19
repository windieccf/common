package com.nus.adqs.dataaccess.model.master.search;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.nus.adqs.constant.ConstantIndexType;
import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;
import com.nus.adqs.dataaccess.model.master.PrivilegeType;


@SuppressWarnings("serial")
@Entity
@Table(name="INDEX_TYPE", schema=ConstantSchema.COMMON,  uniqueConstraints = @UniqueConstraint(columnNames = "CODE"))
@NamedQueries({@NamedQuery(name="IndexType_listByCode" ,query="SELECT e FROM IndexType e where e.code=:code")})
@SequenceGenerator(name="indexSeq", sequenceName="indexSeq")
public class IndexType extends BaseEntity{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO,generator="indexSeq")
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
	
	@Column(name="TYPE" , length=1, columnDefinition="CHAR(1)")
	private String type = ConstantIndexType.WEB; // refer to ConstantWeb.SEARCH_, only applicable for Web or document
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	

	@Column(name="PRIVILEGE_ID")
	private Long privilegeId;
	public Long getPrivilegeId() {return privilegeId;}
	public void setPrivilegeId(Long privilegeId) {this.privilegeId = privilegeId;}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PRIVILEGE_ID",referencedColumnName="ID",insertable=false,updatable=false)
	private PrivilegeType privilegeType;
	public PrivilegeType getPrivilegeType() {return privilegeType;}
	public void setPrivilegeType(PrivilegeType privilegeType) {
		this.privilegeType = privilegeType;
		this.setPrivilegeId((privilegeType == null) ? null : privilegeType.getId());
	}
	
	

}

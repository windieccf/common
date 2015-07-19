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

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;


@SuppressWarnings("serial")
@Entity
@Table(name="USER_ACCESS_MATRIX", schema=ConstantSchema.COMMON)
@NamedQueries(@NamedQuery(name="UserAccessMatrix_ListByPrivilegeCode", query="SELECT e FROM UserAccessMatrix e WHERE e.privilegeCode=:privilegeCode"))
@SequenceGenerator(name="uamSeq", sequenceName="uamSeq")
public class UserAccessMatrix extends BaseEntity{
	
	public static final String LIST_BY_PRIVILEGE_CODE = "UserAccessMatrix_ListByPrivilegeCode";
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO,generator="uamSeq")
	@Column(name="ID")
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	@Override
	public Serializable getPk() {return this.getId();}
	
	@Column(name="PRIV_CODE", length=30)
	private String privilegeCode;
	public String getPrivilegeCode() {return privilegeCode;}
	public void setPrivilegeCode(String privilegeCode) {this.privilegeCode = privilegeCode;}
	
	@Column(name="RECEIVER_CODE", length=30)
	private String receiverCode;
	public String getReceiverCode() {return receiverCode;}
	public void setReceiverCode(String receiverCode) {this.receiverCode = receiverCode;}
	
	@Column(name="PERM_CODES")
	private String permissionCodes;
	public String getPermissionCodes() {return permissionCodes;}
	public void setPermissionCodes(String permissionCodes) {this.permissionCodes = permissionCodes;}
	

}

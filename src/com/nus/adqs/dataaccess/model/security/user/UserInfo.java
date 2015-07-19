package com.nus.adqs.dataaccess.model.security.user;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;

@SuppressWarnings("serial")
@Entity
//@org.hibernate.annotations.Entity(dynamicInsert = true,dynamicUpdate=true)

@Table(name="USER_INFO",schema=ConstantSchema.COMMON )
@SequenceGenerator(name="userInfoSeq", sequenceName="userInfoSeq")
public class UserInfo extends BaseEntity {
	

	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO,generator="userInfoSeq")
	@Column(name="ID")
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	@Override
	public Serializable getPk() {return this.getId();}
	
	@Column(name="FULL_NAME")
	private String fullName;
	public String getFullName() {return fullName;}
	public void setFullName(String fullName) {this.fullName = fullName;}

	@Column(name="ALIAS")
	private String alias;
	public String getAlias() {return alias;}
	public void setAlias(String alias) {this.alias = alias;}
	
	@Column(name="EMAIL")
	private String email;
	public String getEmail() {return email;}
	public void setEmail(String email) {this.email = email;}
	
	@Column(name="DOB") 
	@Temporal(TemporalType.DATE)
	private Date dob;
	public Date getDob() {return dob;}
	public void setDob(Date dob) {this.dob = dob;}
	
	@Column(name="USER_ACCOUNT_ID") 
	private Long userAccountId;
	public Long getUserAccountId() {return userAccountId;}
	public void setUserAccountId(Long userAccountId) {this.userAccountId = userAccountId;}
	
	@OneToOne
	@JoinColumn(name="USER_ACCOUNT_ID",referencedColumnName="ID",insertable=false,updatable=false)
	private UserAccount userAccount;
	public UserAccount getUserAccount() {return userAccount;}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
		this.setUserAccountId((userAccount == null) ? null : userAccount.getId());
	}
	
	
	public UserInfo(){
		this.userAccount = new UserAccount();
	}

}

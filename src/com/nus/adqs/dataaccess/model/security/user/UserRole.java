package com.nus.adqs.dataaccess.model.security.user;

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

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;
import com.nus.adqs.dataaccess.model.master.RoleType;


@SuppressWarnings("serial")
@Entity
@NamedQueries({@NamedQuery(name="UserRole_ListByRoleIdAndUserStatus",  query = "SELECT e FROM UserRole e WHERE e.roleId=:roleId AND e.userAccount.status=:status")})
@Table(name="USER_ROLE", schema=ConstantSchema.COMMON)
@SequenceGenerator(name="userRoleSeq", sequenceName="userRoleSeq")
public class UserRole extends BaseEntity{
	
	public static final String LIST_BY_ROLE_ID_AND_USER_STATUS = "UserRole_ListByRoleIdAndUserStatus";
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO,generator="userRoleSeq")
	@Column(name="ID")
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	@Override
	public Serializable getPk() {return this.getId();}

	
	@Column(name="USER_ACCOUNT_ID")
	private Long userAccountId;
	public Long getUserAccountId() {return userAccountId;}
	public void setUserAccountId(Long userAccountId) {this.userAccountId = userAccountId;}
	
	@Column(name="ROLE_ID")
	private Long roleId;
	public Long getRoleId() {return roleId;}
	public void setRoleId(Long roleId) {this.roleId = roleId;}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="USER_ACCOUNT_ID",referencedColumnName="ID",insertable=false,updatable=false)
	private UserAccount userAccount;
	public UserAccount getUserAccount() {return userAccount;}
	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
		this.setUserAccountId((userAccount == null) ? null : userAccount.getId());
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ROLE_ID",referencedColumnName="ID",insertable=false,updatable=false)
	private RoleType role;
	public RoleType getRole() {return role;}
	public void setRole(RoleType role) {
		this.role = role;
		this.setRoleId((role == null) ? null : role.getId());
	}
	
	
	public UserRole(){}
	public UserRole(UserAccount userAccount){
		this(userAccount,null);
	}
	
	public UserRole(UserAccount userAccount , RoleType role){
		this.setRole(role);
		this.setUserAccount(userAccount);
		userAccount.getUserRoles().add(this);
	}
	
	
	
	
}

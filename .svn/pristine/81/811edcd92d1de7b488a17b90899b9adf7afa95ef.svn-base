package com.nus.adqs.dataaccess.model.security.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;
import com.nus.adqs.dataaccess.model.master.RoleType;


@SuppressWarnings("serial")
@Entity
@Table(name="USER_ACCOUNT", schema=ConstantSchema.COMMON,  uniqueConstraints = @UniqueConstraint(columnNames = "USERNAME"))
@NamedQueries({
	@NamedQuery(name="UserAccount_listByUsername", query="SELECT e FROM UserAccount e WHERE e.username=:username "),
	@NamedQuery(name="UserAccount_listByEmail", query="SELECT e FROM UserAccount e WHERE e.userInfo.email=:email ")
})
@SequenceGenerator(name="userAccountSeq", sequenceName="userAccountSeq")
public class UserAccount extends BaseEntity{
	
	public static final String LIST_BY_USER_NAME = "UserAccount_listByUsername";
	public static final String LIST_BY_EMAIL = "UserAccount_listByEmail";
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO,generator="userAccountSeq")
	@Column(name="ID")
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	@Override
	public Serializable getPk() {return this.getId();}
	
	@Column(name="USERNAME", length=30)
	private String username;
	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}

	@Column(name="PASSWORD", length=100)
	private String password;
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}
	
	@OneToOne(mappedBy="userAccount")
	private UserInfo userInfo;
	public UserInfo getUserInfo() {return userInfo;}
	public void setUserInfo(UserInfo userInfo) {this.userInfo = userInfo;}
	
	@OneToMany(mappedBy="userAccount",fetch=FetchType.LAZY, cascade=CascadeType.ALL,orphanRemoval=true)
	private List<UserRole> userRoles = new ArrayList<UserRole>();
	public List<UserRole> getUserRoles() {return userRoles;}
	public void setUserRoles(List<UserRole> userRoles) {this.userRoles = userRoles;}
	public UserRole addNewRole(RoleType role){return new UserRole(this, role);}
	public void removeRole(UserRole userRole){this.userRoles.remove(userRole);}
	public List<Long> getUserRolesAsLong(){
		List<Long> roles = new ArrayList<Long>();
		for(UserRole role : this.userRoles){
			roles.add(role.getRoleId());
		}
		return roles;
	}
	

}

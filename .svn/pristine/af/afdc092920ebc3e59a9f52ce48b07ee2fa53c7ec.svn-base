package com.nus.adqs.dataaccess.model.master;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;
import com.nus.adqs.enumeration.EnumPermission;
import com.nus.adqs.util.StringUtil;


@SuppressWarnings("serial")
@Entity
@Table(name="PRIVILEGE_TYPE", schema=ConstantSchema.COMMON,  uniqueConstraints = @UniqueConstraint(columnNames = "CODE"))
@NamedQueries({@NamedQuery(name="PrivilegeType_listByCode" ,query="SELECT e FROM PrivilegeType e where e.code=:code")})
@SequenceGenerator(name="privilegeSeq", sequenceName="privilegeSeq")
public class PrivilegeType extends BaseEntity{
	
	public static final String LIST_BY_CODE = "PrivilegeType_listByCode";
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO,generator="privilegeSeq")
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
	
	@OneToMany(mappedBy="privilegeType",fetch=FetchType.LAZY, cascade=CascadeType.ALL,orphanRemoval=true)
	private List<PrivilegePermission> privilegePermission= new ArrayList<PrivilegePermission>();
	public List<PrivilegePermission> getPrivilegePermission() {return privilegePermission;}
	public void setPrivilegePermission(List<PrivilegePermission> privilegePermission) {this.privilegePermission = privilegePermission;}
	public PrivilegePermission addNewPermission(EnumPermission permission){
		return new PrivilegePermission(this, permission);
	}
	public void removePermission(PrivilegePermission permission){
		this.privilegePermission.remove(permission);
	}
	public List<String> getPermissions(){
		List<String> items = new ArrayList<String>();
		for(PrivilegePermission item : this.privilegePermission){
			if(StringUtil.isEmpty(item.getPk().getPermissionCode()))
				continue;
			
			items.add(item.getPk().getPermissionCode());
		}
		return items;
	}
	
	
}

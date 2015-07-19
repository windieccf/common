package com.nus.adqs.dataaccess.model.master;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;
import com.nus.adqs.dataaccess.model.base.BasePk;
import com.nus.adqs.enumeration.EnumPermission;


@SuppressWarnings("serial")
@Entity
@Table(name="PRIV_PERM", schema=ConstantSchema.COMMON)
@SequenceGenerator(name="permSeq", sequenceName="permSeq")
public class PrivilegePermission extends BaseEntity{
	
	@EmbeddedId
	private Pk pk;
	public Pk getPk() {return pk;}
	public void setPk(Pk pk) {this.pk = pk;}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="PRIVILEGE_ID",referencedColumnName="ID",insertable=false,updatable=false)
	private PrivilegeType privilegeType;
	public PrivilegeType getPrivilegeType() {return privilegeType;}
	public void setPrivilegeType(PrivilegeType privilegeType) {
		this.privilegeType = privilegeType;
		this.getPk().setPrivilegeId((privilegeType == null) ? null : privilegeType.getId());
	}
	
	public PrivilegePermission(){this.pk = new Pk();}
	public PrivilegePermission(PrivilegeType privilegeType, EnumPermission permission){
		this();
		this.setPrivilegeType(privilegeType);
		this.getPk().setPermissionCode(permission.getCode());
		privilegeType.getPrivilegePermission().add(this);
	}
	
	public static class Pk extends BasePk{
		@Column(name="PRIVILEGE_ID")
		private Long privilegeId;
		public Long getPrivilegeId() {return privilegeId;}
		public void setPrivilegeId(Long privilegeId) {this.privilegeId = privilegeId;}
		
		@Column(name="PERMISSION_CODE", length=1 , columnDefinition="CHAR(1)")
		private String permissionCode;
		public String getPermissionCode() {return permissionCode;}
		public void setPermissionCode(String permissionCode) {this.permissionCode = permissionCode;}
	}

	 
	/********************* UTILITY *******************/
	public EnumPermission getPermission(){return EnumPermission.getByCode(this.getPk().getPermissionCode());}
	public void setPermission(EnumPermission permission){this.getPk().setPermissionCode(permission.getCode());}
	

}

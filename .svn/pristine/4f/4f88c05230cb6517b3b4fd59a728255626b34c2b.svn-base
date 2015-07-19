package com.nus.adqs.dataaccess.scalar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.nus.adqs.util.StringUtil;

public class PermissionPrivilegeEntity {
	
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	
	private Long roleId;
	public Long getRoleId() {return roleId;}
	public void setRoleId(Long roleId) {this.roleId = roleId;}

	private String code;
	public String getCode() {return code;}
	public void setCode(String code) {this.code = code;}
	
	private String label;
	public String getLabel() {return label;}
	public void setLabel(String label) {this.label = label;}
	
	private List<String> permissions = new ArrayList<String>();
	public List<String> getPermissions() {return permissions;}
	public void setPermissions(List<String> permissions) {this.permissions = permissions;}
	public boolean isPermissionExist(String code){return this.permissions.contains(code);}

	public void setPermissions(String permissions){
		if(StringUtil.isEmpty(permissions))	return;
		
		this.permissions = new ArrayList<String>(Arrays.asList(permissions.split(",")));
	}
	public String getPermissionsAsString(){return StringUtils.join(this.permissions,",");}
	
}

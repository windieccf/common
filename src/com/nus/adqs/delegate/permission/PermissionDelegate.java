package com.nus.adqs.delegate.permission;

import java.util.List;

import com.nus.adqs.dataaccess.model.master.PrivilegeType;
import com.nus.adqs.dataaccess.model.master.UserAccessMatrix;
import com.nus.adqs.dataaccess.model.security.user.UserAccount;
import com.nus.adqs.dataaccess.scalar.PermissionPrivilegeEntity;
import com.nus.adqs.dataaccess.scalar.result.PermissionAccess;
import com.nus.adqs.delegate.BaseDelegate;
import com.nus.adqs.exception.ValidationException;
import com.nus.adqs.service.permission.PermissionService;

public class PermissionDelegate extends BaseDelegate<UserAccessMatrix> {

	
	public Class<PermissionService> getServiceClass(){return PermissionService.class;}
	private static PermissionDelegate instance;
	
	private PermissionDelegate(){/*SINGLETON*/}
	
	public static PermissionDelegate getInstance(){
		if( PermissionDelegate.instance == null)
			PermissionDelegate.instance = new PermissionDelegate();
		
		return PermissionDelegate.instance;
	}
	
	
	/*EXTRAS*/

	public List<PermissionPrivilegeEntity> retrieveByPrivilegesCode(String code){
		return super.getService(this.getServiceClass()).retrieveByPrivilegesCode(code);
	}
	
	public void massUpdatePermissionByPrivilege(PrivilegeType privilegeType, List<PermissionPrivilegeEntity> permissionPrivileges) throws ValidationException, Exception{
		super.getService(this.getServiceClass()).massUpdatePermissionByPrivilege(privilegeType, permissionPrivileges);
	}
	
	public List<PermissionAccess>  getPermissionsByUserAccount(UserAccount userAccount) throws Exception{
		return super.getService(this.getServiceClass()).getPermissionsByUserAccount(userAccount);
	}
	
	
}

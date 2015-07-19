package com.nus.adqs.service.permission;

import java.util.List;

import com.nus.adqs.annotation.ServiceRegistry;
import com.nus.adqs.dataaccess.model.master.PrivilegeType;
import com.nus.adqs.dataaccess.model.master.UserAccessMatrix;
import com.nus.adqs.dataaccess.model.security.user.UserAccount;
import com.nus.adqs.dataaccess.scalar.PermissionPrivilegeEntity;
import com.nus.adqs.dataaccess.scalar.result.PermissionAccess;
import com.nus.adqs.exception.ValidationException;
import com.nus.adqs.service.BaseService;

@ServiceRegistry(className="com.nus.adqs.service.permission.impl.PermissionServiceImpl")
public interface PermissionService extends BaseService<UserAccessMatrix>{
	
	public List<PermissionPrivilegeEntity> retrieveByPrivilegesCode(String code);
	public void massUpdatePermissionByPrivilege(PrivilegeType privilegeType, List<PermissionPrivilegeEntity> permissionPrivileges)throws ValidationException, Exception;
	public List<PermissionAccess>  getPermissionsByUserAccount(UserAccount userAccount) throws Exception;
	
}

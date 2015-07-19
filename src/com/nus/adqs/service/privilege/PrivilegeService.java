package com.nus.adqs.service.privilege;

import com.nus.adqs.annotation.ServiceRegistry;
import com.nus.adqs.dataaccess.model.master.PrivilegeType;
import com.nus.adqs.service.BaseService;


@ServiceRegistry(className="com.nus.adqs.service.privilege.impl.PrivilegeServiceImpl")
public interface PrivilegeService extends BaseService<PrivilegeType>{
	
	public PrivilegeType getByCode(String code);

}

package com.nus.adqs.delegate.privilege;

import com.nus.adqs.dataaccess.model.master.PrivilegeType;
import com.nus.adqs.delegate.BaseDelegate;
import com.nus.adqs.service.privilege.PrivilegeService;

public class PrivilegeDelegate  extends BaseDelegate<PrivilegeType>{
	
	
	public Class<PrivilegeService> getServiceClass(){return PrivilegeService.class;}
	private static PrivilegeDelegate instance;
	
	private PrivilegeDelegate(){/*SINGLETON*/}
	
	public static PrivilegeDelegate getInstance(){
		if( PrivilegeDelegate.instance == null)
			PrivilegeDelegate.instance = new PrivilegeDelegate();
		
		return PrivilegeDelegate.instance;
	}
	
	
	/*EXTRAS*/
	public PrivilegeType doGetByCode(String code){
		return BaseDelegate.getService(this.getServiceClass()).getByCode(code);
	}
	
}

package com.nus.adqs.delegate.role;

import com.nus.adqs.dataaccess.model.master.RoleType;
import com.nus.adqs.delegate.BaseDelegate;
import com.nus.adqs.service.role.RoleService;

public class RoleDelegate extends BaseDelegate<RoleType>{
	
	public Class<RoleService> getServiceClass(){return RoleService.class;}
	private static RoleDelegate instance;
	
	private RoleDelegate(){	/*SINGLETON*/}
	
	public static RoleDelegate getInstance(){
		if( RoleDelegate.instance == null)
			RoleDelegate.instance = new RoleDelegate();
		
		return RoleDelegate.instance;
	}
	
	
	/*EXTRAS*/
	public RoleType doGetByCode(String code){
		return BaseDelegate.getService(this.getServiceClass()).getByCode(code);
	}
	
}

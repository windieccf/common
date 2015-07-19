package com.nus.adqs.delegate.menu;

import com.nus.adqs.dataaccess.model.master.MenuMaster;
import com.nus.adqs.delegate.BaseDelegate;
import com.nus.adqs.service.menu.MenuService;

public class MenuDelegate extends BaseDelegate<MenuMaster>{
	
	public Class<MenuService> getServiceClass(){return MenuService.class;}
	private static MenuDelegate instance;
	
	private MenuDelegate(){/*SINGLETON*/}
	
	public static MenuDelegate getInstance(){
		if( MenuDelegate.instance == null)
			MenuDelegate.instance = new MenuDelegate();
		
		return MenuDelegate.instance;
	}

}

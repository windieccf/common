package com.nus.adqs.service.menu.impl;

import com.nus.adqs.base.form.BaseForm;
import com.nus.adqs.dataaccess.model.master.MenuMaster;
import com.nus.adqs.exception.ValidationException;
import com.nus.adqs.service.impl.BaseServiceImpl;
import com.nus.adqs.service.menu.MenuService;

public class MenuServiceImpl extends BaseServiceImpl<MenuMaster> implements MenuService{

	@Override
	public void validate(BaseForm<MenuMaster> form) throws ValidationException,	Exception {
		/*IGNORED*/
	}

}

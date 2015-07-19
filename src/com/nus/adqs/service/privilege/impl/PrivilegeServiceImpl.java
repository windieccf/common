package com.nus.adqs.service.privilege.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nus.adqs.base.form.BaseForm;
import com.nus.adqs.dataaccess.model.master.PrivilegePermission;
import com.nus.adqs.dataaccess.model.master.PrivilegeType;
import com.nus.adqs.dataaccess.persistence.EmLocator;
import com.nus.adqs.enumeration.EnumPermission;
import com.nus.adqs.exception.ValidationException;
import com.nus.adqs.service.impl.BaseServiceImpl;
import com.nus.adqs.service.privilege.PrivilegeService;
import com.nus.adqs.util.BeanUtil;
import com.nus.adqs.util.StringUtil;

public class PrivilegeServiceImpl extends BaseServiceImpl<PrivilegeType> implements PrivilegeService{
	
	public PrivilegeType save(BaseForm<PrivilegeType> form)throws ValidationException, Exception {
		this.validate(form);
		List<String> permissions = form.getParameterAsList(String.class, "permissions");
		
		PrivilegeType item = new PrivilegeType();
		if(form.getSelectedEntity().isPkSet())
			item = super.getById(form.getSelectedEntity().getPk());
		
		
		List<String> excludeProperties = new ArrayList<String>();
		excludeProperties.addAll(Arrays.asList(BaseServiceImpl.RESTRICTED_COPY));
		excludeProperties.add("privilegePermission");
		BeanUtil.copyProperties(item, form.getSelectedEntity(), excludeProperties.toArray(new String[0]));
		
		// perform matching on the child table;
		List<PrivilegePermission> removeItems = new ArrayList<PrivilegePermission>();
		for(PrivilegePermission privPems : item.getPrivilegePermission()){
			if(permissions.contains(privPems.getPk().getPermissionCode())){
				permissions.remove(privPems.getPk().getPermissionCode());
			}else
				removeItems.add(privPems);
		}
		EmLocator.getEm().persist(item);

		for(PrivilegePermission removeItem : removeItems){
			item.removePermission(removeItem);
		}
		
		// after persist, re insert the list
		for(String permission : permissions){
			item.addNewPermission(EnumPermission.getByCode(permission));
		}
		EmLocator.getEm().persist(item);
		EmLocator.getEm().flush();	
		
		
		
		
		
		return null;
	}
	
	@Override
	public void validate(BaseForm<PrivilegeType> form)	throws ValidationException, Exception {
		
		List<String> errors = new ArrayList<String>();
		
		if(StringUtil.isEmpty(form.getSelectedEntity().getCode()))
			errors.add("Please fill in Code");
		
		if(StringUtil.isEmpty(form.getSelectedEntity().getDescription()))
			errors.add("Please fill in Description");
		
		if(!form.getSelectedEntity().isPkSet()){
			if(super.getByCode(form.getSelectedEntity().getCode()) !=null)
				errors.add("Code already exist. Please Choose other code");
		}
		
		if(!errors.isEmpty())
			throw new ValidationException(errors);
		
	}

}

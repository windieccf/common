package com.nus.adqs.service.role.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.nus.adqs.base.form.BaseForm;
import com.nus.adqs.constant.ConstantStatus;
import com.nus.adqs.dataaccess.model.master.RoleType;
import com.nus.adqs.dataaccess.model.security.user.UserRole;
import com.nus.adqs.dataaccess.persistence.EmLocator;
import com.nus.adqs.exception.ValidationException;
import com.nus.adqs.service.impl.BaseServiceImpl;
import com.nus.adqs.service.role.RoleService;
import com.nus.adqs.util.StringUtil;

public class RoleServiceImpl extends BaseServiceImpl<RoleType> implements RoleService{
	
	@SuppressWarnings("unchecked")
	public RoleType delete(BaseForm<RoleType> form) throws ValidationException, Exception {
		RoleType entity = this.getById(form.getSelectedEntity().getPk());
		if(entity == null)
			throw new ValidationException("Record with identifier ["+form.getSelectedEntity().getPk()+"] does not exist in the database");
		
		List<UserRole> userRoles = EmLocator.getEm().createNamedQuery(UserRole.LIST_BY_ROLE_ID_AND_USER_STATUS)
										.setParameter("roleId", entity.getId())
										.setParameter("status", ConstantStatus.ACTIVE)
										.getResultList();
		
		if(!userRoles.isEmpty()){
			List<String> users = new ArrayList<String>();
			for(UserRole userRole : userRoles){
				users.add(userRole.getUserAccount().getUsername());
			}
			
			StringBuffer errorMessage = new StringBuffer("Please remove the role from these users before proceed ")
										.append("[")
										.append(StringUtils.join(users,""))
										.append("]");
			
			throw new ValidationException(errorMessage.toString());
			
		}else{
			entity.setStatus(ConstantStatus.DELETED);
			EmLocator.getEm().persist(entity);
			EmLocator.getEm().flush();
		}
		return entity;
	}
	
	@Override
	public void validate(BaseForm<RoleType> form) throws ValidationException, Exception {
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

package com.nus.adqs.service.index.impl;

import java.util.ArrayList;
import java.util.List;

import com.nus.adqs.base.form.BaseForm;
import com.nus.adqs.dataaccess.model.master.search.IndexType;
import com.nus.adqs.exception.ValidationException;
import com.nus.adqs.service.impl.BaseServiceImpl;
import com.nus.adqs.service.index.IndexService;
import com.nus.adqs.util.StringUtil;

public class IndexServiceImpl extends BaseServiceImpl<IndexType> implements IndexService{

	
	
	@Override
	public void validate(BaseForm<IndexType> form) throws ValidationException, Exception {
		
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

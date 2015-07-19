package com.nus.adqs.service.crawler.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nus.adqs.base.form.BaseForm;
import com.nus.adqs.dataaccess.model.master.search.CrawlerRule;
import com.nus.adqs.dataaccess.model.master.search.CrawlerType;
import com.nus.adqs.dataaccess.persistence.EmLocator;
import com.nus.adqs.exception.ValidationException;
import com.nus.adqs.service.crawler.CrawlerService;
import com.nus.adqs.service.impl.BaseServiceImpl;
import com.nus.adqs.util.BeanUtil;
import com.nus.adqs.util.StringUtil;

public class CrawlerServiceImpl extends BaseServiceImpl<CrawlerType> implements CrawlerService{
	
	
	@Override
	public CrawlerType save(BaseForm<CrawlerType> form) throws ValidationException, Exception{
	
		this.validate(form);
		CrawlerType formCrawlerType = form.getSelectedEntity();
		CrawlerType crawlerType = new CrawlerType();
		
		if(formCrawlerType.isPkSet())
			crawlerType = super.getById(formCrawlerType.getPk());
		
		List<String> excludeProperties = new ArrayList<String>();
		excludeProperties.addAll(Arrays.asList(BaseServiceImpl.RESTRICTED_COPY));
		excludeProperties.add("rules");
		excludeProperties.add("indexType");
		BeanUtil.copyProperties(crawlerType, formCrawlerType, excludeProperties.toArray(new String[0]) );
		crawlerType.removeAllRules();
		EmLocator.getEm().persist(crawlerType);

		excludeProperties = new ArrayList<String>();
		excludeProperties.addAll(Arrays.asList(BaseServiceImpl.RESTRICTED_COPY));
		excludeProperties.add("crawlerType");
		excludeProperties.add("crawlerTypeId");
		
		String[] excludeRuleProperties = excludeProperties.toArray(new String[0]);
		
		List<CrawlerRule> formCrawlerRules = form.getParameterAsList(CrawlerRule.class, "rules");
		for(CrawlerRule formRule : formCrawlerRules){
			if(StringUtil.isEmpty(formRule.getValue())) continue;
			
			CrawlerRule rule = crawlerType.addNewRule();
			BeanUtil.copyProperties(rule, formRule, excludeRuleProperties);
		}
		EmLocator.getEm().persist(crawlerType);
		EmLocator.getEm().flush();
		return crawlerType;
	}
	

	@Override
	public void validate(BaseForm<CrawlerType> form) throws ValidationException, Exception {
		// TODO Auto-generated method stub
		
		List<String> errors = new ArrayList<String>();
		
		CrawlerType item = form.getSelectedEntity();
		
		if(StringUtil.isEmpty(item.getCode()))
			errors.add("Please fill in Code");
		
		if(StringUtil.isEmpty(item.getRootUri()))
			errors.add("Please fill in Root URI");
			
		if(item.getIndexTypeId() == null)
			errors.add("Please fill in index type");
		
		if(!errors.isEmpty())
			throw new ValidationException(errors);
		
	}

}

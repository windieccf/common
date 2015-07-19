package com.nus.adqs.rule.impl;

import com.nus.adqs.dataaccess.model.master.search.CrawlerRule;
import com.nus.adqs.rule.CrawlerRuleStrategy;

public class CrawlerRulePathStrategy extends CrawlerRuleStrategy{

	@Override
	public boolean executeRule(String text) {
		if(super.rules.isEmpty())
			return true;

		for(CrawlerRule rule : super.rules){
			if(super.evaluateRule(rule, text))
				return (rule.isRuleTypeAllow()) ? true : false;
		}
		
		return super.defaultReturnValue;
	}

}

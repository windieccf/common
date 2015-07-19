package com.nus.adqs.rule;

import java.util.ArrayList;
import java.util.List;

import com.nus.adqs.constant.ConstantCrawler;
import com.nus.adqs.dataaccess.model.master.search.CrawlerRule;
import com.nus.adqs.rule.impl.CrawlerRuleFileStrategy;
import com.nus.adqs.rule.impl.CrawlerRulePathStrategy;

public abstract class CrawlerRuleStrategy {
	
	protected List<CrawlerRule> rules = new ArrayList<CrawlerRule>();
	public void setRules(List<CrawlerRule> rules){this.rules = rules;}
	
	protected boolean defaultReturnValue = false;
	
	public static CrawlerRuleStrategy getEngine(String crawlerRuleType, List<CrawlerRule> crawlerRules){
		return CrawlerRuleStrategy.getEngine(crawlerRuleType, crawlerRules, false);
	}
	
	public static CrawlerRuleStrategy getEngine(String crawlerRuleType, List<CrawlerRule> crawlerRules, boolean defaultReturnValue){
		CrawlerRuleStrategy engine = null;
		if(ConstantCrawler.CRAWLER_TYPE_PATH.equals(crawlerRuleType))
			engine = new CrawlerRulePathStrategy();
		else if(ConstantCrawler.CRAWLER_TYPE_FILE.equals(crawlerRuleType))
			engine = new CrawlerRuleFileStrategy();
		
		engine.setRules(crawlerRules);
		engine.defaultReturnValue = defaultReturnValue;
		
		return engine;
	}
	
	protected boolean evaluateRule(CrawlerRule rule , String value ){
		if(rule.isConditionStartWith())
			return value.startsWith(rule.getValue());
		else if(rule.isConditionContain())
			return value.contains(rule.getValue());
		else 
			return value.endsWith(rule.getValue());
	}
	
	public abstract boolean executeRule(String text);
}

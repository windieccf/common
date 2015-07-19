package com.nus.adqs.delegate.crawler;

import com.nus.adqs.dataaccess.model.master.search.CrawlerType;
import com.nus.adqs.delegate.BaseDelegate;
import com.nus.adqs.service.crawler.CrawlerService;

public class CrawlerDelegate extends BaseDelegate<CrawlerType>{
	
	public Class<CrawlerService> getServiceClass(){return CrawlerService.class;}
	private static CrawlerDelegate instance;
	
	private CrawlerDelegate(){	/*SINGLETON*/}
	
	public static CrawlerDelegate getInstance(){
		if( CrawlerDelegate.instance == null)
			CrawlerDelegate.instance = new CrawlerDelegate();
		
		return CrawlerDelegate.instance;
	}
	

}
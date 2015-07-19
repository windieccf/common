package com.nus.adqs.delegate.index;

import com.nus.adqs.dataaccess.model.master.search.IndexType;
import com.nus.adqs.delegate.BaseDelegate;
import com.nus.adqs.service.index.IndexService;

public class IndexDelegate extends BaseDelegate<IndexType>{
	
	public Class<IndexService> getServiceClass(){return IndexService.class;}
	private static IndexDelegate instance;
	
	private IndexDelegate(){	/*SINGLETON*/}
	
	public static IndexDelegate getInstance(){
		if( IndexDelegate.instance == null)
			IndexDelegate.instance = new IndexDelegate();
		
		return IndexDelegate.instance;
	}
	

}

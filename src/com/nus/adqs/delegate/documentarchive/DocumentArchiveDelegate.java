package com.nus.adqs.delegate.documentarchive;

import com.nus.adqs.dataaccess.model.search.DocumentArchive;
import com.nus.adqs.delegate.BaseDelegate;
import com.nus.adqs.service.documentarchive.DocumentArchiveService;

public class DocumentArchiveDelegate extends BaseDelegate<DocumentArchive>{
	
	public Class<DocumentArchiveService> getServiceClass(){return DocumentArchiveService.class;}
	private static DocumentArchiveDelegate instance;
	
	private DocumentArchiveDelegate(){	/*SINGLETON*/}
	
	public static DocumentArchiveDelegate getInstance(){
		if( DocumentArchiveDelegate.instance == null)
			DocumentArchiveDelegate.instance = new DocumentArchiveDelegate();
		
		return DocumentArchiveDelegate.instance;
	}
	
	/*Extras*/
	public DocumentArchive doGetDocumentByUri(String uri) throws Exception{
		return super.getService(this.getServiceClass()).getDocumentByUri(uri);
	}

}

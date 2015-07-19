package com.nus.adqs.service.documentarchive;

import com.nus.adqs.annotation.ServiceRegistry;
import com.nus.adqs.dataaccess.model.search.DocumentArchive;
import com.nus.adqs.service.BaseService;

@ServiceRegistry(className="com.nus.adqs.service.documentarchive.impl.DocumentArchiveImpl")
public interface DocumentArchiveService extends BaseService<DocumentArchive>{
	
	public DocumentArchive getDocumentByUri(String uri) throws Exception;
	

}

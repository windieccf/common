package com.nus.adqs.service.documentarchive.impl;

import java.util.List;

import com.nus.adqs.base.form.BaseForm;
import com.nus.adqs.dataaccess.model.search.DocumentArchive;
import com.nus.adqs.dataaccess.persistence.EmLocator;
import com.nus.adqs.exception.ValidationException;
import com.nus.adqs.service.documentarchive.DocumentArchiveService;
import com.nus.adqs.service.impl.BaseServiceImpl;

public class DocumentArchiveImpl extends BaseServiceImpl<DocumentArchive> implements DocumentArchiveService{

	@SuppressWarnings("unchecked")
	@Override
	public DocumentArchive getDocumentByUri(String uri) throws Exception{
		
		DocumentArchive docArchive = new DocumentArchive();
		List<DocumentArchive> documentArchives = (List<DocumentArchive>)EmLocator.getEm().createNamedQuery(DocumentArchive.LIST_BY_URI)
														.setParameter("uri", uri)
														.getResultList();
		
		if(documentArchives.isEmpty()){
			docArchive.setUri(uri);
			try{
				EmLocator.getEm().getTransaction().begin();
				EmLocator.getEm().persist(docArchive);
				EmLocator.getEm().flush();
				EmLocator.getEm().getTransaction().commit();
			}catch(Exception e){
				e.printStackTrace();
				EmLocator.getEm().getTransaction().rollback();						
			}
		}else{
			docArchive = documentArchives.get(0);
		}
		
		return docArchive;
	}
	
	
	
	@Override
	public void validate(BaseForm<DocumentArchive> form)throws ValidationException, Exception {
		/*IGNORED*/
	}

}

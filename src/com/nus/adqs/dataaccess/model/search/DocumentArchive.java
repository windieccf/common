package com.nus.adqs.dataaccess.model.search;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;


@SuppressWarnings("serial")
@Entity
@Table(name="DOCUMENT_ARCHIVE", schema=ConstantSchema.COMMON)
@NamedQueries(@NamedQuery(name="DocumentArchive_listByUri", query="SELECT e FROM DocumentArchive e WHERE e.uri=:uri "))
@SequenceGenerator(name="docArchiveSeq", sequenceName="docArchiveSeq")
public class DocumentArchive extends BaseEntity{
	
	public static final String LIST_BY_URI = "DocumentArchive_listByUri";
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO,generator="docArchiveSeq")
	@Column(name="ID")
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	@Override
	public Serializable getPk() {return this.getId();}
	
	@Column(name="URI", length=500)
	private String uri;
	public String getUri() {return uri;}
	public void setUri(String uri) {this.uri = uri;}
	
}

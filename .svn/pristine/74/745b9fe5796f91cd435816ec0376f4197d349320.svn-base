package com.nus.adqs.dataaccess.model.lexicon;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;


@SuppressWarnings("serial")
@Entity
@Table(name="SEARCH_GROUP_PHRASE",schema=ConstantSchema.LEXICON)
@SequenceGenerator(name="searchGroupPhraseSeq", sequenceName="searchGroupPhraseSeq")
public class SearchGroupPhrase extends BaseEntity{
	

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="searchGroupPhraseSeq")
	@Column(name="ID")
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	@Override
	public Serializable getPk() {return this.getId();}
	
	@Column(name = "SEARCH_GROUP_ID")
	private Long searchGroupId;
	public Long getSearchGroupId() {return searchGroupId;}
	public void setSearchGroupId(Long searchGroupId) {this.searchGroupId = searchGroupId;}

	@Column(name = "PHRASE", length=500)
	private String phrase;
	public String getPhrase() {return phrase;}
	public void setPhrase(String phrase) {this.phrase = phrase;}
	
	/********************************** JOIN TABLE************************************/
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SEARCH_GROUP_ID",referencedColumnName="ID",insertable=false,updatable=false)
	private SearchGroup searchGroup;
	public SearchGroup getSearchGroup() {return searchGroup;}
	public void setSearchGroup(SearchGroup searchGroup) {
		this.searchGroup = searchGroup;
		this.setSearchGroupId((searchGroup == null) ? null : searchGroup.getId());
	}
	
	
}


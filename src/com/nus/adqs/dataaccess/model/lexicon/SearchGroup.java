package com.nus.adqs.dataaccess.model.lexicon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;


@SuppressWarnings("serial")
@Entity
@Table(name="SEARCH_GROUP",schema=ConstantSchema.LEXICON)
@SequenceGenerator(name="searchGroupSeq", sequenceName="searchGroupSeq")
public class SearchGroup extends BaseEntity{
	

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="searchGroupSeq")
	@Column(name="ID")
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	@Override
	public Serializable getPk() {return this.getId();}
	
	@Column(name = "NAME", length=50)
	private String name;
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	@Column(name = "DESCRIPTION")
	private String description;
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	@OneToMany(mappedBy="searchGroup",fetch=FetchType.LAZY, cascade=CascadeType.ALL,orphanRemoval=true)
	private List<SearchGroupPhrase> phrases = new ArrayList<SearchGroupPhrase>();
	public List<SearchGroupPhrase> getPhrases() {return phrases;}
	public void setPhrases(List<SearchGroupPhrase> phrases) {this.phrases = phrases;}
	
	
}


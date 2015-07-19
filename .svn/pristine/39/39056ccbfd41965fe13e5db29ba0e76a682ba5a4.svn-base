package com.nus.adqs.dataaccess.model.lexicon;

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
@NamedQueries(
	@NamedQuery(name="OverlapConjunction_ListByStatus",query="SELECT e FROM OverlapConjunction e WHERE e.status=:status")
)
@Table(name="OVERLAP_CONJUCTION",schema=ConstantSchema.LEXICON)
@SequenceGenerator(name="overlapConjSeq", sequenceName="overlapConjSeq")
public class OverlapConjunction extends BaseEntity{
	
	public static final String LIST_BY_STATUS = "OverlapConjunction_ListByStatus";

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="overlapConjSeq")
	@Column(name="ID")
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	@Override
	public Serializable getPk() {return this.getId();}
	
	@Column(name = "WORD",length=10)
	private String word;
	public String getWord() {return word;}
	public void setWord(String word) {this.word = word;}
	

}

package com.nus.adqs.dataaccess.model.master.search;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;


@SuppressWarnings("serial")
@Entity
@Table(name="CRAWLER_TYPE", schema=ConstantSchema.COMMON,  uniqueConstraints = @UniqueConstraint(columnNames = "CODE"))
@NamedQueries({@NamedQuery(name="CrawlerType_listByCode" ,query="SELECT e FROM CrawlerType e where e.code=:code")})
@SequenceGenerator(name="crawlerSeq", sequenceName="crawlerSeq")
public class CrawlerType extends BaseEntity{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO,generator="crawlerSeq")
	@Column(name="ID")
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	@Override
	public Serializable getPk() {return this.getId();}
	
	@Column(name="CODE", length=30)
	private String code;
	public String getCode() {return code;}
	public void setCode(String code) {this.code = code;}
	
	@Column(name="DESCRIPTION", length=100)
	private String description;
	public String getDescription() {return description;}
	public void setDescription(String description) {this.description = description;}
	
	@Column(name="INDEX_TYPE_ID")
	private Long indexTypeId;
	public Long getIndexTypeId() {return indexTypeId;}
	public void setIndexTypeId(Long indexTypeId) {this.indexTypeId = indexTypeId;}

	@Column(name="ROOT_URI", length=1000)
	private String rootUri;
	public String getRootUri() {return rootUri;}
	public void setRootUri(String rootUri) {this.rootUri = rootUri;}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="INDEX_TYPE_ID",referencedColumnName="ID",insertable=false,updatable=false)
	private IndexType indexType;
	public IndexType getIndexType() {return indexType;}
	public void setIndexType(IndexType indexType) {
		this.indexType = indexType;
		this.setIndexTypeId((indexType == null) ? null : indexType.getId());
	}

	@OneToMany(mappedBy="crawlerType",fetch=FetchType.LAZY, cascade=CascadeType.ALL,orphanRemoval=true)
	private List<CrawlerRule> rules = new ArrayList<CrawlerRule>();
	public List<CrawlerRule> getRules() {return rules;}
	public void setRules(List<CrawlerRule> rules) {this.rules = rules;}
	public CrawlerRule addNewRule(){return new CrawlerRule(this);}
	public void removeRule(CrawlerRule rule){this.rules.remove(rule);}
	public void removeAllRules(){
		while(this.rules.size() > 0){
			this.rules.remove(0);
		}
	}
	
	public List<CrawlerRule> getRulesByType(String type){
		List<CrawlerRule> rules = new ArrayList<CrawlerRule>();
		for(CrawlerRule item : this.getRules()){
			if(type.equals(item.getType()))
				rules.add(item);
		}
		return rules;
	}
	

}

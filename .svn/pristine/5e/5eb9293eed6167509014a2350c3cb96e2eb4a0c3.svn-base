package com.nus.adqs.dataaccess.model.master.search;

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

import com.nus.adqs.constant.ConstantCrawler;
import com.nus.adqs.constant.ConstantOperator;
import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;


@SuppressWarnings("serial")
@Entity
@Table(name="CRAWLER_RULE", schema=ConstantSchema.COMMON)
@SequenceGenerator(name="crawlerRuleSeq", sequenceName="crawlerRuleSeq")
public class CrawlerRule extends BaseEntity{
	
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO,generator="crawlerRuleSeq")
	@Column(name="ID")
	private Long id;
	public Long getId() {return id;}
	public void setId(Long id) {this.id = id;}
	@Override
	public Serializable getPk() {return this.getId();}
	
	@Column(name="CRAWLER_TYPE_ID")
	private Long crawlerTypeId;
	public Long getCrawlerTypeId() {return crawlerTypeId;}
	public void setCrawlerTypeId(Long crawlerTypeId) {this.crawlerTypeId = crawlerTypeId;}

	@Column(name="TYPE", length=1, columnDefinition="CHAR(1)")
	private String type = ConstantCrawler.CRAWLER_TYPE_FILE; // can only have type FILE or PATH
	public String getType() {return type;}
	public void setType(String type) {this.type = type;}
	public boolean isType(String type){return (type == null ) ? false : type.equals(this.type);}

	@Column(name="RULE_TYPE", length=10)
	private String ruleType = ConstantOperator.RULE_TYPE_RESTRICTION;
	public String getRuleType() {return ruleType;}
	public void setRuleType(String ruleType) {this.ruleType = ruleType;}
	public boolean isRuleType(String ruleType){return (ruleType == null) ? null : ruleType.equals(this.ruleType);}
	public boolean isRuleTypeRestriction(){return ConstantOperator.RULE_TYPE_RESTRICTION.equals(this.getRuleType());}
	public boolean isRuleTypeAllow(){return ConstantOperator.RULE_TYPE_ALLOW.equals(this.getRuleType());}
	
	@Column(name="CONDITION", length=10)
	private String condition = ConstantOperator.CONDITION_START_WITH;
	public String getCondition() {return condition;}
	public void setCondition(String condition) {this.condition = condition;}
	public boolean isCondition(String condition){return (condition == null) ? null : condition.equals(this.condition);}
	public boolean isConditionStartWith(){return ConstantOperator.CONDITION_START_WITH.equals(this.getCondition());}
	public boolean isConditionEndWith(){return ConstantOperator.CONDITION_END_WITH.equals(this.getCondition());}
	public boolean isConditionContain(){return ConstantOperator.CONDITION_CONTAIN.equals(this.getCondition());}
	
	
	@Column(name="VALUE", length=500)
	private String value;
	public String getValue() {return value;}
	public void setValue(String value) {this.value = value;}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CRAWLER_TYPE_ID",referencedColumnName="ID",insertable=false,updatable=false)
	private CrawlerType crawlerType;
	public CrawlerType getCrawlerType() {return crawlerType;}
	public void setCrawlerType(CrawlerType crawlerType) {
		this.crawlerType = crawlerType;
		this.setCrawlerTypeId((crawlerType == null) ? null : crawlerType.getId());
	}
	
	public CrawlerRule(){}
	public CrawlerRule(CrawlerType crawlerType){
		this.setCrawlerType(crawlerType);
		crawlerType.getRules().add(this);
		super.setCreatedById(crawlerType.getModifiedById());
	}

}

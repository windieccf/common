package com.nus.adqs.dataaccess.model.security;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.base.BaseEntity;
import com.nus.adqs.dataaccess.model.base.BasePk;


@SuppressWarnings("serial")
@Entity
@NamedQueries({
	@NamedQuery(name="SystemSetup_listByCode", query="SELECT e FROM SystemSetup e WHERE e.pk.module =:module"),
	@NamedQuery(name="SystemSetup_getByParamName", query="SELECT e FROM SystemSetup e WHERE e.pk.paramName =:paramName")
})
@Table(name="SYSTEM_SETUP",schema=ConstantSchema.COMMON)
public class SystemSetup extends BaseEntity{
	
	public static String LIST_BY_CODE = "SystemSetup_listByCode";
	public static String GET_BY_PARAM_NAME = "SystemSetup_getByParamName";
	
	@EmbeddedId
	private Pk pk;
	public Pk getPk() {return pk;}
	public void setPk(Pk pk) {this.pk = pk;}
	
	@Column(name="PARAM_VALUE")
	private String paramValue;
	public String getParamValue() {return paramValue;}
	public void setParamValue(String paramValue) {this.paramValue = paramValue;}
	
	public SystemSetup(){this.pk = new Pk();}
	public SystemSetup(String module, String paramName){
		this();
		getPk().setModule(module);
		getPk().setParamName(paramName);
	}


	public static class Pk extends BasePk{
		// for module naming, please rever to ConstantSchema.
		@Column(name="MODULE",length=10)
		private String module;
		public String getModule() {return module;}
		public void setModule(String module) {this.module = module;}
		
		// this must be a variable name follow by the camel case style, as will go through reflection to setup the data
		@Column(name="PARAM_NAME",length=50)
		private String paramName;
		public String getParamName() {return paramName;}
		public void setParamName(String paramName) {this.paramName = paramName;}
		
		public Pk(){}
		public Pk(String module, String paramName){
			this.module = module;
			this.paramName = paramName;
		}
	}

}

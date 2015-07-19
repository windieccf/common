package com.nus.adqs.dataaccess.persistence;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.nus.adqs.constant.ConstantSchema;
import com.nus.adqs.dataaccess.model.security.SystemSetup;
import com.nus.adqs.dataaccess.scalar.SystemParameter;

public class SystemSetupHome {
	
	private static SystemSetupHome INSTANCE = null;
	private SystemSetupHome(){/*ENSURE SINGLETON*/
		this.init();
	}
	
	public static SystemSetupHome getInstance(){
		if(SystemSetupHome.INSTANCE == null)
			SystemSetupHome.INSTANCE = new SystemSetupHome();
		
		return SystemSetupHome.INSTANCE;
	}
	
	public static SystemParameter getParam(){return SystemSetupHome.getInstance().getParameter();}
	
	
	/********************************** INIT & UTIL ****************************************/
	private void init(){
		this.parameter = initialiseParameter(ConstantSchema.COMMON);
	}
	
	
	@SuppressWarnings("unchecked")
	private SystemParameter initialiseParameter(String module){
		List<SystemSetup> systemSetups = EmLocator.getEm().createNamedQuery(SystemSetup.LIST_BY_CODE)
										.setParameter("module", module)
										.getResultList();
		
		SystemParameter systemParameter = new SystemParameter();
		// reflection begin
		for(SystemSetup setup : systemSetups){
			String paramName = setup.getPk().getParamName();
			String setterName = "set"+ StringUtils.capitalize(paramName);
			try {
				Method method = SystemParameter.class.getMethod(setterName, String.class);
				method.invoke(systemParameter, setup.getParamValue());
			} catch (Exception e) {/*IGNORED*/}
			
		}
		
		return systemParameter;
	}
	
	/********************************** PARAMETER STORE ****************************************/
	private SystemParameter parameter = new SystemParameter();
	public SystemParameter getParameter() {return parameter;}
	
	
}

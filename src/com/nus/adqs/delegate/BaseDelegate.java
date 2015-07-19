package com.nus.adqs.delegate;

import java.io.Serializable;
import java.util.List;

import com.nus.adqs.base.form.BaseForm;
import com.nus.adqs.dataaccess.scalar.form.LabelValue;
import com.nus.adqs.exception.ValidationException;
import com.nus.adqs.service.BaseService;
import com.nus.adqs.service.ServiceLocator;

public abstract class BaseDelegate<T> {
	
	public List<T> doList() throws Exception{
		return (List<T>) BaseDelegate.getService(this.getServiceClass()).list();
	}
	
	public List<T> doListByStatus(String status) throws Exception{
		return (List<T>) BaseDelegate.getService(this.getServiceClass()).listByStatus(status);
	}
	
	public List<T> doPaginate(int rowPerPage, int startIndex, List<LabelValue> filters, List<LabelValue> sortOrders){
		return BaseDelegate.getService(this.getServiceClass()).paginate(rowPerPage, startIndex, filters, sortOrders);
	}
	
	public Long doPaginateCount(List<LabelValue> filters){
		return BaseDelegate.getService(this.getServiceClass()).paginateCount(filters);
	}
	
	public T doGetById(Serializable id){
		return BaseDelegate.getService(this.getServiceClass()).getById(id);
	}
	
	public T doGetById(Long id){
		return BaseDelegate.getService(this.getServiceClass()).getById(id);
	}
	
	public T doGetByCode(String code){
		return BaseDelegate.getService(this.getServiceClass()).getByCode(code);
	}
	
	
	
	public T doSave(BaseForm<T> form) throws ValidationException, Exception{
		return BaseDelegate.getService(this.getServiceClass()).save(form);
	}
	
	public T doDelete(BaseForm<T> form) throws ValidationException, Exception{
		return BaseDelegate.getService(this.getServiceClass()).delete(form);
	}
	
	public T doReactivate(BaseForm<T> form) throws ValidationException, Exception{
		return BaseDelegate.getService(this.getServiceClass()).reActivate(form);
	}
	
	protected static <X>X getService(Class<X> klass){
		try{
			return (X) ServiceLocator.getInstance().getService(klass);
		}catch(Exception e){
			throw new IllegalArgumentException("BaseDelegate.getService" ,e);
		}
	}
	
	abstract public Class<? extends BaseService<T>> getServiceClass();

}

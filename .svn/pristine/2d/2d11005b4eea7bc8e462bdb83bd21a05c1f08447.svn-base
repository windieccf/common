package com.nus.adqs.service;

import java.io.Serializable;
import java.util.List;

import com.nus.adqs.base.form.BaseForm;
import com.nus.adqs.dataaccess.scalar.form.LabelValue;
import com.nus.adqs.exception.ValidationException;

public interface BaseService<T> {

	public List<T> paginate(int rowPerPage, int startIndex, List<LabelValue> filters, List<LabelValue> sortOrders);
	public Long paginateCount(List<LabelValue> filters);
	
	public List<T> list() throws Exception;
	public List<T> listByStatus(String status) throws Exception;
	public T getById(Serializable id);
	public T getByCode(String code);
	
	
	public T save(BaseForm<T> form) throws ValidationException, Exception;
	public T delete(BaseForm<T> form) throws ValidationException, Exception;
	public T reActivate(BaseForm<T> form) throws ValidationException, Exception;
	
}

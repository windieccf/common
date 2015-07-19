package com.nus.adqs.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import com.nus.adqs.base.form.BaseForm;
import com.nus.adqs.constant.ConstantBoolean;
import com.nus.adqs.constant.ConstantStatus;
import com.nus.adqs.dataaccess.model.base.BaseEntity;
import com.nus.adqs.dataaccess.persistence.EmLocator;
import com.nus.adqs.dataaccess.scalar.form.LabelValue;
import com.nus.adqs.enumeration.QueryConjuctionEnum;
import com.nus.adqs.exception.ValidationException;
import com.nus.adqs.util.BeanUtil;
import com.nus.adqs.util.StringUtil;

public abstract class BaseServiceImpl<T extends BaseEntity> {
	
	public static final String[] RESTRICTED_COPY = new String[]{"id","status","createdDateTime","modifiedDateTime"};
	
	public List<T> list()  throws Exception {
		List<T> entities = new ArrayList<T>();
		entities = EmLocator.getEm()
						.createQuery("SELECT e FROM "+this.klazz.getSimpleName()+" e",this.klazz)
						.getResultList();
		
		return entities;
	}
	
	public List<T> listByStatus(String status) throws Exception{
		List<T> entities = new ArrayList<T>();
		entities = EmLocator.getEm()
						.createQuery("SELECT e FROM "+this.klazz.getSimpleName()+" e WHERE e.status=:status ",this.klazz)
						.setParameter("status", status)
						.getResultList();
		
		return entities;
	}
	
	
	public T getById(Serializable id) {return this.getById(id, this.klazz);}
	public <X> X getById(Serializable id, Class<X> theClass) {return EmLocator.getEm().find(theClass, id);}
	

	public T save(BaseForm<T> form)throws ValidationException, Exception {
		this.validate(form);

		T entity = klazz.newInstance();
		if(form.getSelectedEntity().isPkSet())
			entity = this.getById(form.getSelectedEntity().getPk());
		
		BeanUtil.copyProperties(entity, form.getSelectedEntity(), BaseServiceImpl.RESTRICTED_COPY);
		EmLocator.getEm().persist(entity);
		EmLocator.getEm().flush();		

		return entity;
	}

	public T getByCode(String code) {
		return this.getByCode(code, this.klazz);
		/*@SuppressWarnings("unchecked")
		List<T> entities = EmLocator.getEm().createQuery("SELECT e FROM "+this.klazz.getSimpleName()+" e WHERE e.code=:code" )
										.setParameter("code", code)
										.getResultList();
		
		return (entities.isEmpty()) ? null  : entities.get(0);*/
	}
	
	
	protected <X>X getByCode(String code, Class<X> theClass) {
		@SuppressWarnings("unchecked")
		List<X> entities = EmLocator.getEm().createQuery("SELECT e FROM "+ theClass.getSimpleName()+" e WHERE e.code=:code" )
										.setParameter("code", code)
										.getResultList();
		
		return (entities.isEmpty()) ? null  : entities.get(0);
	}
	
	public T delete(BaseForm<T> form) throws ValidationException, Exception {
		T entity = this.getById(form.getSelectedEntity().getPk());
		if(entity == null)
			throw new ValidationException("Record with identifier ["+form.getSelectedEntity().getPk()+"] does not exist in the database");
		
		entity.setStatus(ConstantStatus.DELETED);
		form.setSelectedEntity(entity);
		this.save(form);
		return entity;
	}
	
	public T reActivate(BaseForm<T> form) throws ValidationException, Exception {
		T entity = this.getById(form.getSelectedEntity().getPk());
		if(entity == null)
			throw new ValidationException("Record with identifier ["+form.getSelectedEntity().getPk()+"] does not exist in the database");
		
		entity.setStatus(ConstantStatus.ACTIVE);
		form.setSelectedEntity(entity);
		this.save(form);
		return entity;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<T> paginate(int rowPerPage, int startIndex, List<LabelValue> filters, List<LabelValue> sortOrder) {
		// TODO Auto-generated method stub
		
		/*Long totalCount = this.paginateCount(form);
		form.setTotalRecord(totalCount);
		form.calculatePagination();
	*/
		
		StringBuffer mySql = new StringBuffer()
			.append("SELECT e FROM "+this.klazz.getSimpleName()+" e ");


		String condition = this.extractFilters(filters);
		List<Object> parameters = this.extractParameters(filters);
		
		if(!StringUtil.isEmpty(condition)){
			condition = StringUtil.reIndexJpaParameter(condition);
			mySql.append(" WHERE " +condition);
		}
		
		mySql.append(" " + this.generateOrderQuery(sortOrder));
		
		Query jpaQuery = EmLocator.getEm().createQuery(mySql.toString());
		
		for (int i = 0; i < parameters.size(); i++) {
			jpaQuery.setParameter(i+1, parameters.get(i));
		}
		
		jpaQuery.setMaxResults( rowPerPage);
		jpaQuery.setFirstResult(startIndex);
		
		return jpaQuery.getResultList();
	}

	
	public Long paginateCount(List<LabelValue> filters) {
		
		StringBuffer mySql = new StringBuffer()
					.append("SELECT COUNT(e) FROM "+this.klazz.getSimpleName()+" e ");
		
		String condition = this.extractFilters(filters);
		List<Object> parameters = this.extractParameters(filters);
		
		if(!StringUtil.isEmpty(condition)){
			condition = StringUtil.reIndexJpaParameter(condition);
			mySql.append(" WHERE " +condition);
		}
			
		Query jpaQuery = EmLocator.getEm().createQuery(mySql.toString());
		
		for (int i = 0; i < parameters.size(); i++) {
			jpaQuery.setParameter(i+1, parameters.get(i));
		}
		
		return (Long) jpaQuery.getSingleResult();
	}
	
	
	
	
	
	
	/****************************************** UTILITY **************************************************/
	@SuppressWarnings("unchecked")
	public BaseServiceImpl(){
		klazz = (Class<T>) ((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	private Class<T> klazz;
	
	
	public String extractFilters(List<LabelValue> filters){
		List<String> criteria = new ArrayList<String>();
		for(LabelValue filter : filters){
			List<String> columns = new ArrayList<String>();
			String operator = filter.getOperator();
			QueryConjuctionEnum conjunction = QueryConjuctionEnum.AND;
			if(filter.hasChainValue()){
				conjunction = QueryConjuctionEnum.OR;
				columns.addAll(filter.getChainLabels());
				criteria.add("( "+StringUtil.createQueryFragment(operator, false, conjunction, columns.toArray(new String[0]))+" )");
			}else{
				columns.add(filter.getLabel());
				criteria.add(StringUtil.createQueryFragment(operator, false, conjunction, columns.toArray(new String[0])));
			}
		}
		return StringUtils.join(criteria," "+QueryConjuctionEnum.AND.getValue()+" ");
	}
	
	public List<Object> extractParameters(List<LabelValue> filters){
		List<Object> params = new ArrayList<Object>();
		for(LabelValue filter : filters){
			
			if(filter.hasChainValue())
				params.addAll(filter.getChainValues());
			else
				params.add(filter.getJpaValue());
			
		}
		return params;
		
	}
	
	public String generateOrderQuery(List<LabelValue> sortOrders){
		List<String> orderList  = new ArrayList<String>();
		if(sortOrders.isEmpty())
			return  "" ;
		
		for(LabelValue sortOrder : sortOrders){
			boolean ascending =ConstantBoolean.YES.equals(sortOrder.getValue()) ;
			orderList.add(" " + sortOrder.getLabel()+" " + ((ascending) ? " ASC " : " DESC "));
			
		}
		
		
		return (orderList.isEmpty()) ? "" : " ORDER BY " + StringUtils.join(orderList, " , ");
	}
	
	protected String[] generateCopyExclusionWithDefault(String... texts){
		List<String> excludeProperties = new ArrayList<String>();
		excludeProperties.addAll(Arrays.asList(BaseServiceImpl.RESTRICTED_COPY));
		for(String text : texts){
			excludeProperties.add(text);
		}
		return excludeProperties.toArray(new String[0]);
	}
	
	abstract public void validate(BaseForm<T> form)throws ValidationException, Exception;
	
	
	
	
}

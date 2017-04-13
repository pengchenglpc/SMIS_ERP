package com.smis.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.simis.base.vo.PageVo;
import com.simis.base.vo.QueryVo;
import com.smis.common.constant.QueryConstant;
import com.smis.dao.base.IBaseDao;

@Repository 
public class BaseDaoImpl<T, PK extends Serializable> implements IBaseDao<T, PK> {
	private Class<T> entityClass;  
	protected SessionFactory sessionFactory;  

	public BaseDaoImpl() {  
		this.entityClass = null;  
		Class<?> c = getClass();  
		Type type = c.getGenericSuperclass();  
		if (type instanceof ParameterizedType) {  
			Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();  
			this.entityClass = (Class<T>) parameterizedType[0];  
		}  
	}  

	@Resource  
	public void setSessionFactory(SessionFactory sessionFactory) {  
		this.sessionFactory = sessionFactory;  
	}  

	protected Session getSession() {  
		return sessionFactory.getCurrentSession();  
	}  
	public T get(PK id) {
		return (T) getSession().get(entityClass, id);  
	}


	public void update(T entity) {
		this.getSession().merge(entity);
	}

	public PK save(T entity) {
		return (PK) getSession().save(entity);
	}

	public List<T> findByField(String field, PK value) {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		criteria.add(Restrictions.eq(field, value));
		return criteria.list();
	}

	public List<T> findAll() {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		return criteria.list();
	}

	public List<T> findByQuery(List<QueryVo> querys) {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		for(QueryVo query : querys){
			if(QueryConstant.QUERY_EQ.equals(query.getRestriction())){
				criteria.add(Restrictions.eq(query.getField(), query.getValue()));
			}else if(QueryConstant.QUERY_GE.equals(query.getRestriction())){
				criteria.add(Restrictions.ge(query.getField(), query.getValue()));
			}else if(QueryConstant.QUERY_GT.equals(query.getRestriction())){
				criteria.add(Restrictions.gt(query.getField(), query.getValue()));
			}else if(QueryConstant.QUERY_IN.equals(query.getRestriction())){
				criteria.add(Restrictions.in(query.getField(), (Object[])query.getValue()));
			}else if(QueryConstant.QUERY_ISNULL.equals(query.getRestriction())){
				criteria.add(Restrictions.isNull(query.getField()));
			}else if(QueryConstant.QUERY_LE.equals(query.getRestriction())){
				criteria.add(Restrictions.le(query.getField(), query.getValue()));
			}else if(QueryConstant.QUERY_LIKE.equals(query.getRestriction())){
				criteria.add(Restrictions.like(query.getField(), query.getValue()));
			}else if(QueryConstant.QUERY_LT.equals(query.getRestriction())){
				criteria.add(Restrictions.lt(query.getField(), query.getValue()));
			}
		}
		return criteria.list();
	}


	public List<T> findByProperties(Map<String, Serializable> properties) {
		Criteria criteria = this.getSession().createCriteria(entityClass);
		
		Iterator<String> iterator = properties.keySet().iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			criteria.add(Restrictions.eq(key, properties.get(key)));
		}
		return criteria.list();
	}

	public List<T> findByJpql(String jpql) {
		return findByJpql(jpql, null);
	}

	public List<T> findByJpql(String jpql, List<Object> params) {
		Query query = this.getSession().createQuery(jpql);
		if(!CollectionUtils.isEmpty(params)){
			for(int i = 0; i < params.size(); i++){
				query.setParameter(i, params.get(i));
			}
		}
		return query.list();
	}

	public void remove(PK id) {
		getSession().delete(id);
	}

	@Override
	public PageVo<T> findPageByJpql(String jpql, String countJqpl,int pageIndex, int pageSize, List<Object> params) {
		Query query = getSession().createQuery(jpql);
		if(!CollectionUtils.isEmpty(params)){
			for(int i =0; i < params.size(); i++){
				query.setParameter(i, params.get(i));
			}
		}
		query.setFirstResult((pageIndex - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<T> list = query.list();
		PageVo<T> page = new PageVo<T>();
		page.setData(list);
		page.setPageIndex(pageIndex);
		page.setPageSize(pageSize);
		return page;
	}

	@Override
	public Integer findTotalSize(String jpql) {
		return findTotalSize(jpql, null);
	}

	@Override
	public Integer findTotalSize(String jpql, List<Object> params) {
		Query q = getSession().createQuery(jpql);
		int i = 0;
		if(params != null){
			for(Object param : params){
				q.setParameter(i, param);
				i++;
			}
		}
		int count = ((Long) q.iterate().next()).intValue();
		return count;
	}
}

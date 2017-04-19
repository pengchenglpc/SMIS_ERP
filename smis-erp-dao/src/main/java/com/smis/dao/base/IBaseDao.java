package com.smis.dao.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.smis.model.vo.base.PageVo;
import com.smis.model.vo.base.QueryVo;

public interface IBaseDao<T, PK extends Serializable> {
	public T get(PK id);
	public void update(T entity);
	public PK save(T entity);
	public void remove(PK id);
	public List<T> findByField(String field, PK value);
	public List<T> findAll();
	public List<T> findByQuery(List<QueryVo> query);
	public List<T> findByProperties(Map<String, Serializable> properties);
	public List<T> findByJpql(String jpql);
	public List<T> findByJpql(String jpql, List<Object> params);
	public PageVo<T> findPageByJpql(String jpql, String countJqpl,int pageIndex, int pageSize, List<Object> params);
	public Integer findTotalSize(String jqpl);
	public Integer findTotalSize(String jqpl, List<Object> params);
}

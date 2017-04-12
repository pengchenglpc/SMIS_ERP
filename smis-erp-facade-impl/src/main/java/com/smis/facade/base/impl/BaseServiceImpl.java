package com.smis.facade.base.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import com.smis.dao.base.IBaseDao;
import com.smis.facade.base.IBaseService;

public class BaseServiceImpl<T, PK extends Serializable> implements IBaseService<T, PK> {
	
	@Resource
	private IBaseDao<T, PK> baseDao;
	
	public T get(PK id) {
		return baseDao.get(id);
	}

	public void update(T entity) {
		baseDao.update(entity);
	}

	public PK save(T entity) {
		return baseDao.save(entity);
	}

	public void remove(PK id) {
		baseDao.remove(id);
	}

}

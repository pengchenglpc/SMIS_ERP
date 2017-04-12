package com.smis.facade.base;

import java.io.Serializable;

public interface IBaseService<T, PK extends Serializable> {
	public T get(PK id);
	public void update(T entity);
	public PK save(T entity);
	public void remove(PK id);
}

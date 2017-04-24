package com.smis.dao.auth;

import java.io.Serializable;
import java.util.List;

import com.smis.dao.base.IBaseDao;
import com.smis.model.entity.auth.SysMenu;
import com.smis.model.vo.auth.SysMenuVo;

public interface ISysMenuDao extends IBaseDao<SysMenu, Serializable> {
	public List<SysMenuVo> loadAll();
}

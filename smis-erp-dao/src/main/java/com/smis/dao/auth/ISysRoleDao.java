package com.smis.dao.auth;

import java.io.Serializable;

import com.smis.dao.base.IBaseDao;
import com.smis.model.entity.auth.SysRole;
import com.smis.model.vo.auth.SysRoleVo;
import com.smis.model.vo.base.PageVo;

public interface ISysRoleDao extends IBaseDao<SysRole, Serializable>{
	public PageVo<SysRole> findPageRole(SysRoleVo role, int pageIndex, int pageSize);
}

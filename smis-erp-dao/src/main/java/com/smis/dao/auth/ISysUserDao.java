package com.smis.dao.auth;

import java.io.Serializable;

import com.smis.dao.base.IBaseDao;
import com.smis.model.entity.auth.SysUser;
import com.smis.model.vo.auth.SysUserVo;

public interface ISysUserDao extends IBaseDao<SysUser, Serializable> {
	public SysUserVo getUserByLoginName(String loginName);
	public Boolean updateLoginErr(Integer userId);
}

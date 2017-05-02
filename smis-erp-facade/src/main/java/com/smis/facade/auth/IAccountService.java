package com.smis.facade.auth;

import com.smis.model.vo.auth.SysRoleVo;
import com.smis.model.vo.base.PageVo;

public interface IAccountService {
	public PageVo<SysRoleVo> findRoleByPage(SysRoleVo role, int pageIndex, int pageSize);
}

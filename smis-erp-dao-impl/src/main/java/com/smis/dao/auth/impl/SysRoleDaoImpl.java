package com.smis.dao.auth.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.smis.dao.auth.ISysRoleDao;
import com.smis.dao.base.impl.BaseDaoImpl;
import com.smis.model.entity.auth.SysRole;
import com.smis.model.vo.auth.SysRoleVo;
import com.smis.model.vo.base.PageVo;

public class SysRoleDaoImpl extends BaseDaoImpl<SysRole, Serializable> implements ISysRoleDao {


	@Override
	public PageVo<SysRole> findPageRole(SysRoleVo role, int pageIndex, int pageSize) {
		StringBuilder hql = new StringBuilder("from SysRole _role where 1=1 ");
		StringBuilder countHql = new StringBuilder("select count(*) from SysRole _role where 1=1 ");
		List<Object> params = new ArrayList<Object>();
		StringBuilder whereHql = new StringBuilder();
		if(!StringUtils.isEmpty(role.getRoleName())){
			whereHql.append(" and _role.roleName like ?");
			params.add("%" + role.getRoleName() + "%");
		}
		hql.append(whereHql);
		countHql.append(whereHql);
		PageVo<SysRole> page = this.findPageByJpql(hql.toString(), countHql.toString(), pageIndex, pageSize, params);
		
		return page;
	}

}

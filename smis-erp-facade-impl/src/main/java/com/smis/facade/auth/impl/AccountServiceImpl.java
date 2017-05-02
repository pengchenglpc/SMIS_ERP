package com.smis.facade.auth.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smis.dao.auth.ISysRoleDao;
import com.smis.facade.auth.IAccountService;
import com.smis.model.entity.auth.SysRole;
import com.smis.model.vo.auth.SysRoleVo;
import com.smis.model.vo.base.PageVo;

@Service
@Transactional
public class AccountServiceImpl implements IAccountService {
	@Autowired
	private ISysRoleDao roleDao;
	
	@Override
	public PageVo<SysRoleVo> findRoleByPage(SysRoleVo role, int pageIndex, int pageSize) {
		PageVo<SysRole> page = roleDao.findPageRole(role, pageIndex, pageSize);
		
		PageVo<SysRoleVo> pageVo = new PageVo<SysRoleVo>();
		pageVo.setPageIndex(page.getPageIndex());
		pageVo.setPageSize(page.getPageSize());
		pageVo.setTotalSize(page.getTotalSize());
		List<SysRole> list = page.getData();
		List<SysRoleVo> listVo = new ArrayList<SysRoleVo>();
		for(SysRole _role : list){
			SysRoleVo roleVo = new SysRoleVo();
			BeanUtils.copyProperties(_role, roleVo);
			listVo.add(roleVo);
		}
		pageVo.setData(listVo);
		return pageVo;
	}

}

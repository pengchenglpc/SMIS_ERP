package com.smis.dao.auth.impl;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import com.smis.dao.auth.ISysMenuDao;
import com.smis.dao.base.impl.BaseDaoImpl;
import com.smis.model.entity.auth.SysMenu;
import com.smis.model.vo.auth.SysMenuVo;

@Repository
@Transactional
public class SysMenuDaoImpl extends BaseDaoImpl<SysMenu, Serializable> implements ISysMenuDao {

	@Override
	public List<SysMenuVo> loadAll() {
		List<SysMenu> list = this.findAll();
		List<SysMenuVo> listVo = new LinkedList<SysMenuVo>();
		for(SysMenu menu : list){
			SysMenuVo menuVo = new SysMenuVo();
			BeanUtils.copyProperties(menu, menuVo);
			listVo.add(menuVo);
		}
		return listVo;
	}

}

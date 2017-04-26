package com.smis.dao.auth;

import java.io.Serializable;
import java.util.List;

import com.smis.dao.base.IBaseDao;
import com.smis.model.entity.auth.SysUser;
import com.smis.model.vo.auth.SysMenuVo;
import com.smis.model.vo.auth.SysRoleRightVo;
import com.smis.model.vo.auth.SysUserVo;

public interface ISysUserDao extends IBaseDao<SysUser, Serializable> {
	/**
	 * 用户名查询用户信息
	 * @param loginName
	 * @return
	 */
	public SysUserVo getUserByLoginName(String loginName);
	
	/**
	 * 更新用户登录失败次数
	 * @param userId
	 * @return
	 */
	public Boolean updateLoginErr(Integer userId);
	
	/**
	 * 系统所有菜单
	 * @return
	 */
	public List<SysMenuVo> getSysMenu();
	
	/**
	 * 角色权限
	 * @return
	 */
	public List<SysRoleRightVo> getRoleRight();
}

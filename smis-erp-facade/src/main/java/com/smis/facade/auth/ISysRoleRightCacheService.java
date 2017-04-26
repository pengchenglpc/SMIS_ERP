package com.smis.facade.auth;

import java.util.List;

import com.smis.model.vo.auth.SysRoleRightVo;

public interface ISysRoleRightCacheService {
	/**
	 * 重新加载数据
	 */
	public void reload();
	/**
	 * 获取角色的权限
	 * 
	 * @return
	 */
	public List<SysRoleRightVo> getRoleRight(int roleID);
	
	/**
	 * 获取有权限的模块ID
	 * 
	 * @param 角色ID
	 * @return
	 */
	public List<Integer> getRightModule(int roleID);
	
	/**
	 * 更加模块ID获取权限
	 * 
	 * @param moduleID
	 * @param listRight
	 * @return
	 */
	public SysRoleRightVo getRightByMid(int moduleID, List<SysRoleRightVo> listRight);
}

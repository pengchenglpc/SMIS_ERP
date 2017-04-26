package com.smis.facade.auth;

import java.util.List;

import com.smis.model.vo.auth.SysMenuVo;
import com.smis.model.vo.auth.SysRoleRightVo;
import com.smis.model.vo.auth.TreeNode;

public interface ISysMenuCacheService {
	/**
	 * 该操作是否有权限
	 * 
	 * @param 角色ID
	 * @param 模块ID
	 * @param 操作名称
	 * @return
	 */
	public boolean getIsRight(int roleID, int moduleID, String opName);
	
	/**
	 * 获取有权限的菜单
	 * 
	 * @return
	 */
	public List<SysMenuVo> getRightMenu(int roleID);
	
	/**
	 * 根据模块ID获取菜单
	 * 
	 * @param menuID
	 * @return
	 */
	public SysMenuVo getMenuByID(int menuID);
	
	/**
	 * 获取操作权限
	 * 
	 * @param moduleID
	 * @param opName
	 * @return
	 */
	public SysMenuVo getOperator(int moduleID, String opName);
	
	/**
	 * 获取有权限的模块
	 * 
	 * @param listRight
	 * @return
	 */
	public List<Integer> getModule(List<SysRoleRightVo> listRight);
	
	/**
	 * 判断是否有权限
	 * 
	 * @param listRight
	 * @param moduleID
	 * @param operaValue
	 * @return
	 */
	public boolean isRight(List<SysRoleRightVo> listRight, int moduleID, int operaValue);
	
	/**
	 * 获取管理权限
	 * 
	 * @param moduleID
	 * @param userRoleID
	 * @param listRight
	 * @param listModule
	 * @return
	 */
	public List<TreeNode> getTreeNode(int moduleID, int userRoleID, List<SysRoleRightVo> listRight,
			List<Integer> listModule, List<SysRoleRightVo> listCurrentRight);
	
	/**
	 * 获取所有授权URL
	 * 
	 * @param roleID
	 * @return
	 */
	public List<String> getAuthUrls(int roleID);
}

package com.smis.facade.auth.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.smis.common.constant.Constants;
import com.smis.facade.auth.ILoginService;
import com.smis.facade.auth.ISysMenuCacheService;
import com.smis.facade.auth.ISysRoleRightCacheService;
import com.smis.model.vo.auth.SysMenuVo;
import com.smis.model.vo.auth.SysRoleRightVo;
import com.smis.model.vo.auth.TreeNode;

@Service
public class SysMenuCacheServiceImpl implements ISysMenuCacheService {

	/**
	 * 需要交易所授权的模块才显示的菜单
	 */
	public static List<Integer> AUTHMODULEIDS = new ArrayList<Integer>(Arrays.asList(900, 901, 902, 903));

	// 将角色权限缓存到内存中
	private static List<SysMenuVo> _list = null;

	@Autowired
	private ILoginService loginService;
	@Autowired
	private ISysRoleRightCacheService sysRoleRightCache;

	// 加载权限数据
	public void loadData() {
		if (_list == null) {
			List<SysMenuVo> list = loginService.getAllMenu();
			_list = list;
		}
	}

	/**
	 * 该操作是否有权限
	 * 
	 * @param 角色ID
	 * @param 模块ID
	 * @param 操作名称
	 * @return
	 */
	public boolean getIsRight(int roleID, int moduleID, String opName) {
		loadData();
		SysMenuVo opera = getOperator(moduleID, opName);
		if (opera == null)
			return false;
		else {
			List<SysRoleRightVo> listRight = sysRoleRightCache.getRoleRight(roleID);
			SysRoleRightVo item = sysRoleRightCache.getRightByMid(moduleID, listRight);
			if (item == null)
				return false;
			else {
				if (opName.equals("查看"))
					return true;
				else {
					int opValue = opera.getOperaValue();
					return (item.getRight() & opValue) == opValue;
				}
			}
		}
	}

	/**
	 * 获取有权限的菜单
	 * 
	 * @return
	 */
	public List<SysMenuVo> getRightMenu(int roleID) {
		loadData();
		if (roleID == Constants.RoleRootID)
			return _list;// 超级管理员拥有所有菜单权限
		List<SysMenuVo> listMenu = new ArrayList<SysMenuVo>();
		List<Integer> listModule = sysRoleRightCache.getRightModule(roleID);
		for (Integer moduleID : listModule) {
			SysMenuVo item = getMenuByID(moduleID);
			if (item != null && !listMenu.contains(item)) {
				listMenu.add(item);
				if (item.getParentId() != 0) {
					item = getMenuByID(item.getParentId());
					if (item != null && !listMenu.contains(item))
						listMenu.add(item);
				}
			}
		}
		return listMenu;
	}

	/**
	 * 根据模块ID获取菜单
	 * 
	 * @param menuID
	 * @return
	 */
	public SysMenuVo getMenuByID(int menuID) {
		loadData();
		for (SysMenuVo item : _list) {
			if (item.getMenuId().equals(menuID))
				return item;
		}
		return null;
	}

	/**
	 * 获取操作权限
	 * 
	 * @param moduleID
	 * @param opName
	 * @return
	 */
	public SysMenuVo getOperator(int moduleID, String opName) {
		loadData();
		for (SysMenuVo item : _list) {
			if (item.getParentId().equals(moduleID) && item.getMenuName().equals(opName))
				return item;
		}
		return null;
	}

	/**
	 * 根据父ID获取菜单
	 * 
	 * @param parentID
	 * @return
	 */
	private List<SysMenuVo> getMenuByParentID(int parentID) {
		loadData();
		List<SysMenuVo> list = new ArrayList<SysMenuVo>();
		for (SysMenuVo item : _list) {
			if (item.getParentId().equals(parentID))
				list.add(item);
		}
		return list;
	}

	/**
	 * 获取有权限的模块
	 * 
	 * @param listRight
	 * @return
	 */
	public List<Integer> getModule(List<SysRoleRightVo> listRight) {
		List<Integer> list = new ArrayList<Integer>();
		for (SysRoleRightVo item : listRight) {
			/*if (item.getModuleID().equals(Modules.MobileModule))
				continue;
			// 新增了查看首页权限， 由于其没有parentID, 导致空指针错误—— modify by chenzhenjun 201611121
			if (item.getModuleID().equals(Modules.HomeModule))
				continue;
				*/
			if (!list.contains(item.getModuleID())) {
				list.add(item.getModuleID());
			}
			SysMenuVo menu = getMenuByID(item.getModuleID());
			if (!list.contains(menu.getParentId())) {
				list.add(menu.getParentId());
			}
		}
		return list;
	}

	/**
	 * 判断是否有权限
	 * 
	 * @param listRight
	 * @param moduleID
	 * @param operaValue
	 * @return
	 */
	public boolean isRight(List<SysRoleRightVo> listRight, int moduleID, int operaValue) {
		for (SysRoleRightVo item : listRight) {
			if (item.getModuleID().equals(moduleID)) {
				return (item.getRight() & operaValue) == operaValue;
			}
		}
		return false;
	}

	private SysRoleRightVo getRoleRight(List<SysRoleRightVo> listCurrentRight, Integer moduleId) {
		if (listCurrentRight == null)
			return null;
		for (SysRoleRightVo roleRight : listCurrentRight) {
			if (roleRight.getModuleID().equals(moduleId))
				return roleRight;
		}
		return null;
	}

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
			List<Integer> listModule, List<SysRoleRightVo> listCurrentRight) {
		List<SysMenuVo> listMenu = getMenuByParentID(moduleID);
		if (listMenu.size() == 0)
			return null;
		List<TreeNode> list = new ArrayList<TreeNode>();
		for (SysMenuVo menu : listMenu) {
			Integer menuID = menu.getMenuId();

			int mType = menu.getMenuType();
			if (userRoleID != Constants.RoleRootID) {
				if (mType < 3 && !listModule.contains(menuID))
					continue;
			}

			int mid = mType == 3 ? menu.getParentId() : 0;
			int id = mType == 3 ? menu.getOperaValue() : menu.getMenuId();
			boolean checked = false;
			if (mType == 3) {
				if (listCurrentRight != null) {
					SysRoleRightVo roleRight = getRoleRight(listCurrentRight, mid);
					if (roleRight == null)
						continue;
					if ((roleRight.getRight() & id) != id)
						continue;
				}

				checked = isRight(listRight, mid, id);
			}
			TreeNode node = new TreeNode();
			node.setMid(mid);
			node.setId(id);
			node.setName(menu.getMenuName());
			// node.setOpen(moduleID == 0 ? true : false);
			node.setOpen(true);
			node.setChecked(checked);
			if (mType < 3) {// 一级 二级菜单寻找下级菜单
				node.setChildren(getTreeNode(menu.getMenuId(), userRoleID, listRight, listModule, listCurrentRight));
			}
			list.add(node);
		}
		return list;
	}

	/**
	 * 获取所有授权URL
	 * 
	 * @param roleID
	 * @return
	 */
	public List<String> getAuthUrls(int roleID) {
		loadData();
		List<String> listUrl = new ArrayList<String>();
		List<SysMenuVo> listMenu = _list;
		if (roleID == Constants.RoleRootID) {// 超级管理员拥有所有权限
			for (SysMenuVo itemMenu : listMenu) {
				String authUrl = itemMenu.getAuthUrls();
				if (!StringUtils.isEmpty(authUrl)) {// 授权URL
					String[] urls = authUrl.split(";");
					for (String url : urls) {
						if (!listUrl.contains(url))
							listUrl.add(url);
					}
				}
			}
		}
		else {// 查询授权表
				// 获取角色权限
			List<SysRoleRightVo> listRight = sysRoleRightCache.getRoleRight(roleID);
			for (SysRoleRightVo itemRight : listRight) {
				for (SysMenuVo itemMenu : listMenu) {
					String authUrl = itemMenu.getAuthUrls();
					// 菜单操作模块相同
					if (itemMenu.getParentId().equals(itemRight.getModuleID()) && !StringUtils.isEmpty(authUrl)) {// 授权URL不等于空
						int operaValue = itemMenu.getOperaValue();
						if (operaValue == 1 || (itemRight.getRight() & operaValue) == operaValue) {
							String[] urls = authUrl.split(";");
							for (String url : urls) {
								if (!listUrl.contains(url))
									listUrl.add(url);
							}
						}
					}
				}
			}
		}
		return listUrl;
	}

}

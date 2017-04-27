package com.smis.facade.auth.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smis.facade.auth.ILoginService;
import com.smis.facade.auth.ISysRoleRightCacheService;
import com.smis.model.vo.auth.SysRoleRightVo;
@Service
public class SysRoleRightCacheServiceImpl implements ISysRoleRightCacheService {

	// 将角色权限缓存到内存中
		private static List<SysRoleRightVo> _list = null;

		@Autowired
		private ILoginService loginService;

		// 加载权限数据
		public void loadData() {
			if (_list == null) {
				_list = loginService.getSysRoleRight();
			}
		}

		// 重新加载数据
		public void reload() {
			_list = loginService.getSysRoleRight();
		}


		/**
		 * 获取角色的权限
		 * 
		 * @return
		 */
		public List<SysRoleRightVo> getRoleRight(int roleID) {
			loadData();
			List<SysRoleRightVo> listRight = new ArrayList<SysRoleRightVo>();
			for (SysRoleRightVo item : _list) {
				if (item.getRoleID().equals(roleID)) {
					listRight.add(item);
				}
			}
			return listRight;
		}

		/**
		 * 获取有权限的模块ID
		 * 
		 * @param 角色ID
		 * @return
		 */
		public List<Integer> getRightModule(int roleID) {
			loadData();

			List<Integer> listModule = new ArrayList<Integer>();
			List<SysRoleRightVo> listRight = getRoleRight(roleID);
			if (listRight.size() > 0) {
				for (SysRoleRightVo item : listRight) {
					if (!listModule.contains(item.getModuleID())) {
						listModule.add(item.getModuleID());
					}
				}
			}
			return listModule;
		}

		/**
		 * 更加模块ID获取权限
		 * 
		 * @param moduleID
		 * @param listRight
		 * @return
		 */
		public SysRoleRightVo getRightByMid(int moduleID, List<SysRoleRightVo> listRight) {
			loadData();
			for (SysRoleRightVo item : listRight) {
				if (item.getModuleID().equals(moduleID)) {
					return item;
				}
			}
			return null;
		}
}

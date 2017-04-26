package com.smis.facade.auth;

import java.util.List;

import com.smis.model.vo.auth.SysMenuVo;
import com.smis.model.vo.auth.SysRoleRightVo;
import com.smis.model.vo.base.Message;

public interface ILoginService {
	/**
	 * 用户登录
	 * @param login
	 * @param password
	 * @param ip
	 * @return
	 */
	public Message login(String login, String password, String ip);
	
	/**
	 * 获取系统所有菜单
	 * @return
	 */
	public List<SysMenuVo> getAllMenu();
	
	/**
	 * 获取角色权限
	 */
	public List<SysRoleRightVo> getSysRoleRight();
}

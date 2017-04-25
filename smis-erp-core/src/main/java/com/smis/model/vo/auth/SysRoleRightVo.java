package com.smis.model.vo.auth;

public class SysRoleRightVo {
	private Integer roleID;				//角色
	
	private Integer moduleID;			//模块
	
	private Integer right;			//权限

	public Integer getRoleID() {
		return roleID;
	}

	public void setRoleID(Integer roleID) {
		this.roleID = roleID;
	}

	public Integer getModuleID() {
		return moduleID;
	}

	public void setModuleID(Integer moduleID) {
		this.moduleID = moduleID;
	}

	public Integer getRight() {
		return right;
	}

	public void setRight(Integer right) {
		this.right = right;
	}
}

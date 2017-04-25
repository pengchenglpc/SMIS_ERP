package com.smis.model.vo.auth;

import java.util.Date;

public class SysRoleVo {
	private Integer roleId;	//角色ID
	
	private String roleName;	//角色名称
	
	private String remark;	//角色说明
	
	private Integer isDelete = 1;	//0 已删除 1未删除
	
	private Integer status = 1;	//0无效 1有效
	
	private Date createTime;	//创建时间

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}

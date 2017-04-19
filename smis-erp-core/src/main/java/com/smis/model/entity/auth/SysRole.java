package com.smis.model.entity.auth;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 系统角色表
 * @author 李鹏程
 *
 */
public class SysRole implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2174841979740418618L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RoleId", nullable = false)
	private Integer roleId;	//角色ID
	
	@Column(name = "RoleName", nullable = false)
	private String roleName;	//角色名称
	
	@Column(name = "Remark", nullable = true)
	private String remark;	//角色说明
	
	@Column(name = "IsDelete", nullable = false)
	private Integer isDelete = 1;	//0 已删除 1未删除
	
	@Column(name = "Status", nullable = false)
	private Integer status = 1;	//0无效 1有效
	
	@Column(name = "CreateTime", nullable = false)
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

package com.smis.model.entity.auth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 角色权限表
 * @author 李鹏程
 *
 */
@Entity
@Table(name = "sys_role_right", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysRoleRight implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "RoleID", nullable = false)
	private Integer roleID;				//角色
	
	@Id
	@Column(name = "ModuleID", nullable = false)
	private Integer moduleID;			//模块
	
	@Column(name = "`Right`", nullable = false)
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
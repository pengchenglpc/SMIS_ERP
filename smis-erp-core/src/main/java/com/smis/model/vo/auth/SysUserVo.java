package com.smis.model.vo.auth;

import java.util.Date;

public class SysUserVo {
	private Integer id;
	
	private String loginName;	//登录名称
	
	private String userName;
	
	private String password;	//密码
	
	private Integer roleId;		//角色ID
	
	private Date createTime;	//创建时间
	
	private Integer status;		//状态 0：无效 1有效
	
	private Date lastLoginTime;	//最后登录时间
	
	private String lastLogiIp;	//最后登录IP
	
	private Integer isDelete;	//是否删除  0：已删除 1未删除
	
	private Integer loginErrCount;	//连续登录失败次数
	
	private Long loginErrTime;	//最后登录失败时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLogiIp() {
		return lastLogiIp;
	}
	public void setLastLogiIp(String lastLogiIp) {
		this.lastLogiIp = lastLogiIp;
	}
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Integer getLoginErrCount() {
		return loginErrCount;
	}
	public void setLoginErrCount(Integer loginErrCount) {
		this.loginErrCount = loginErrCount;
	}
	public Long getLoginErrTime() {
		return loginErrTime;
	}
	public void setLoginErrTime(Long loginErrTime) {
		this.loginErrTime = loginErrTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}

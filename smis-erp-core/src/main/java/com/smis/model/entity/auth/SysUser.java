package com.smis.model.entity.auth;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * 系统用户表
 * @author 李鹏程
 *
 */
@Entity
@Table(name = "sys_user", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8454454466998660773L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private Integer id;
	
	@Column(name="Login_Name", nullable=false)
	private String loginName;	//登录名称
	
	@Column(name="User_Name", nullable=false)
	private String userName;
	
	@Column(name="Password", nullable=false)
	private String password;	//密码
	
	@Column(name="Role_ID", nullable=false)
	private Integer roleId;		//角色ID
	
	@Column(name = "CreateTime", nullable = false)
	private Date createTime;	//创建时间
	
	@Column(name="Status", nullable=false)
	private Integer status;		//状态 0：无效 1有效
	
	@Column(name="Last_Login_Time", nullable=false)
	private Date lastLoginTime;	//最后登录时间
	
	@Column(name="Last_Login_IP", nullable=false)
	private String lastLogiIp;	//最后登录IP
	
	@Column(name="Is_Delete")
	private Integer isDelete;	//是否删除  0：已删除 1未删除
	
	@Column(name="Login_Err_Count", nullable =false)
	private Integer loginErrCount;	//连续登录失败次数
	
	@Column(name="Login_Err_Time", nullable=false)
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

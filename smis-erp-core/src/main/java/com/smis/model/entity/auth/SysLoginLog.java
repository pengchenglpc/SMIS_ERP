package com.smis.model.entity.auth;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Type;

/**
 * 系统登录日志
 * @author 李鹏程
 *
 */
@Entity
@Table(name = "sys_login_log", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class SysLoginLog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LogID", nullable = false)
	private Integer logID;

	@Column(name = "LoginName", nullable = false, length=50)
	@Type(type = "org.hibernate.type.StringType")
	private String loginName; // 登录用户名

	@Column(name = "UserID", nullable = true)
	private Integer userID; // 登录用户

	@Column(name = "LoginTime", nullable = false)
	private Date loginTime; // 登录时间

	@Column(name = "LoginIP", nullable = false, columnDefinition = "CHAR(20)")
	@Type(type = "org.hibernate.type.StringType")
	private String loginIP; // 登录IP

	@Column(name = "IsSuccess", nullable = false, columnDefinition = "BIT")
	@Type(type = "org.hibernate.type.IntegerType")
	private Integer isSuccess; // 是否登录成功
	
	@Column(name = "Remark", nullable = false, length=500)
	@Type(type = "org.hibernate.type.StringType")
	private String remark; // 备注

	public Integer getLogID() {
		return logID;
	}

	public void setLogID(Integer logID) {
		this.logID = logID;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}


	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLoginIP() {
		return loginIP;
	}

	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Integer isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	
}

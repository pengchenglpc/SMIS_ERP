package com.smis.controller.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.smis.model.vo.auth.SysUserVo;

public class BaseConstroller {
	public static final String LOGIN_CURRENT_USER_KEY = "COM.SMIS.LOGIN_USER";
	public static final String LOGIN_CURRENT_USER_KEY_COOKIE = "COM.SMIS.LOGIN_USER.COOKIE";
	public static final String LOGIN_CURRENT_USER_KEY_COOKIE_PWD = "COM.SMIS.LOGIN_USER.COOKIE_PWD";
	
	protected SysUserVo getLoginUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		SysUserVo userVo = (SysUserVo) session.getAttribute(LOGIN_CURRENT_USER_KEY);
		return userVo;
	}
	
	protected HttpSession getSession(HttpServletRequest reqeust){
		return reqeust.getSession();
	}
}

package com.smis.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.smis.controller.base.BaseConstroller;
import com.smis.facade.auth.ILoginService;
import com.smis.facade.auth.ISysMenuCacheService;
import com.smis.model.vo.auth.SysUserVo;
import com.smis.model.vo.base.Message;

public class WebMVCInterceptor implements HandlerInterceptor {
	private String[] EXCLUDE_URL = {"home/login.smis"};
	private String[] AUTH_URL = {"home/getMenu.smis", "home/logout.smis", "home/login_query.smis"};
	
	@Autowired
	private ILoginService loginService;
	
	@Autowired
	private ISysMenuCacheService sysMenuCache;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		String origin = "*";
		String referer = request.getHeader("referer");

		if (referer != null) {
			URL refererUrl = new URL(referer);
			origin = refererUrl.getProtocol() + "://" + refererUrl.getAuthority();
		}
		response.setHeader("Access-Control-Allow-Origin", origin);
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		String url = request.getRequestURI();
		for (int i = 0; i < EXCLUDE_URL.length; i++) {
			if (url.indexOf(EXCLUDE_URL[i]) != -1) {
				return true;
			}
		}
		
		// 判断session是否存储
		Message result = new Message();
		HttpSession session = request.getSession();
		if(session != null){
			SysUserVo user = (SysUserVo) session.getAttribute(BaseConstroller.LOGIN_CURRENT_USER_KEY);
			if(user == null){
				Cookie[] cookies = request.getCookies();
				if(cookies == null){
					this.setUnLogin(response, result);
					return false;
				}
				Cookie cookie = null,
					   pwd_cookie = null;
				for(Cookie _cookie : cookies){
					if(BaseConstroller.LOGIN_CURRENT_USER_KEY_COOKIE.equals(_cookie.getName())){
						cookie = _cookie;
					}
					if(BaseConstroller.LOGIN_CURRENT_USER_KEY_COOKIE_PWD.equals(_cookie.getName())){
						pwd_cookie = _cookie;
					}
				}
				if(cookie == null || pwd_cookie == null){
					this.setUnLogin(response, result);
					return false;
				}else{
					String loginName = cookie.getValue();
					String ip = request.getRemoteAddr();
					String password = pwd_cookie.getValue();
					result = loginService.login(loginName, password, ip);
					if(0 == result.getResultCode()){
						user = (SysUserVo) result.getData();
						session.setAttribute(BaseConstroller.LOGIN_CURRENT_USER_KEY, user);
					}else{
						this.setUnLogin(response, result);
						return false;
					}
				}
			}
			for (int i = 0; i < AUTH_URL.length; i++) {
				if (url.indexOf(AUTH_URL[i]) != -1) {
					return true;
				}
			}
			List<String> listAuth = sysMenuCache.getAuthUrls(user.getRoleId());

			for (String authUrl : listAuth) {
				if (url.indexOf(authUrl) != -1) {
					return true;
				}
			}
			result.setResultCode(-1);
			result.setMsg("无权访问！");
			this.retJson(response, result);
			return false;
		}
		this.setUnLogin(response, result);
		return false;
	}
	
	// 用户未登录
	private void setUnLogin(HttpServletResponse response, Message result) throws Exception {
		result.setLogin(false);
		result.setResultCode(-1);
		result.setMsg("用户未登录，请先登录！");
//		result.setData(SiteConfig.getUserCentUrl());
		retJson(response, result);

	}
	public void retJson(HttpServletResponse response, Message result) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(JSONObject.toJSONString(result));
	}

}

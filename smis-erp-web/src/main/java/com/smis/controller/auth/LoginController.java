package com.smis.controller.auth;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smis.common.util.LoggerUtil;
import com.smis.controller.base.BaseConstroller;
import com.smis.facade.auth.ILoginService;
import com.smis.model.vo.auth.SysUserVo;
import com.smis.model.vo.base.Message;

@Controller
@RequestMapping(value = "/home")
public class LoginController extends BaseConstroller {
	@Autowired
	private ILoginService loginService;
	
	@RequestMapping(value = "/login", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Message login(@RequestParam(value = "loginName", required = false) String loginName,
			@RequestParam("password") String password,@RequestParam("remember")String remember, HttpServletResponse response,
			HttpServletRequest request) {
		Message msg = new Message();
		String ip = request.getRemoteAddr();
		msg = loginService.login(loginName, password, ip);
		if(msg.getResultCode() == 0){
			SysUserVo userVo = (SysUserVo) msg.getData();
			if(userVo == null){
				LoggerUtil.MyLogger.info("登录程序异常");
				msg.setLogin(false);
				msg.setResultCode(-1);
				msg.setMsg("登录异常，请稍候再试");
			}else{
				HttpSession session = getSession(request);
				session.setAttribute(BaseConstroller.LOGIN_CURRENT_USER_KEY, userVo);
				if("1".equals(remember)){
					Cookie cookie = new Cookie(BaseConstroller.LOGIN_CURRENT_USER_KEY_COOKIE, loginName);
					cookie.setPath("/");
					cookie.setMaxAge(5*24*60*60);
					response.addCookie(cookie);
					cookie = new Cookie(BaseConstroller.LOGIN_CURRENT_USER_KEY_COOKIE_PWD, password);
					cookie.setPath("/");
					cookie.setMaxAge(5*24*60*60);
					response.addCookie(cookie);
				}
			}
		}else{
			msg.setLogin(false);
		}
		return msg;
	}
	
	public Message loginQuery(HttpServletResponse response,
			HttpServletRequest request){
		Message msg = new Message();
		SysUserVo user = this.getLoginUser(request);
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("userName", user.getUserName());
		msg.setData(retMap);
		return msg;
	}
	@RequestMapping(value = "/logout", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Message logout(HttpServletResponse response,
			HttpServletRequest request){
		Message msg = new Message();
		msg.setResultCode(0);
		HttpSession session = request.getSession();
		Enumeration<String> enumeration =session.getAttributeNames();
		while(enumeration.hasMoreElements()){
			session.removeAttribute(enumeration.nextElement());
		}
		session.invalidate();
		return msg;
	}
}

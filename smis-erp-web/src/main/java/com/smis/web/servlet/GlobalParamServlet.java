package com.smis.web.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.smis.common.util.GlobalParam;

public class GlobalParamServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(GlobalParamServlet.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = -2066453047521923795L;
	private ServletContext servletContext = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		this.servletContext = config.getServletContext();
		
		Integer loginErrCount = Integer.valueOf(config.getInitParameter("loginErrCount"));
		Integer loginErrFreezeTime = Integer.valueOf(config.getInitParameter("loginErrFreezeTime"));
		GlobalParam.getInstance().setLoginErrCount(loginErrCount);
		GlobalParam.getInstance().setLoginErrFreezeTime(loginErrFreezeTime);
		
		logger.info(String.format("加载系统配置%s", JSONObject.toJSONString(GlobalParam.getInstance())));
	}

}

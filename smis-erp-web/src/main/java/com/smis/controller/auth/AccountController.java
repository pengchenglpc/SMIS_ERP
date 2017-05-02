package com.smis.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smis.common.util.LoggerUtil;
import com.smis.controller.base.BaseConstroller;
import com.smis.facade.auth.IAccountService;
import com.smis.model.vo.auth.SysRoleVo;
import com.smis.model.vo.base.Message;
import com.smis.model.vo.base.PageVo;

@Controller
@RequestMapping(value = "/account")
public class AccountController extends BaseConstroller {
	
	@Autowired
	private IAccountService accountService;
	
	@RequestMapping(value = "/role_query", method={RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Message roleQuery(int pageIndex, int pageSize){
		Message msg = new Message();
		msg.setResultCode(0);
		try{
			PageVo<SysRoleVo> page = accountService.findRoleByPage(new SysRoleVo(), pageIndex, pageSize);
			msg.setData(page);
		}catch(Exception e){
			msg.setResultCode(-1);
			msg.setMsg("程序出错");
			LoggerUtil.MyLogger.error("程序出错", e);
		}
		return msg;
	}
}

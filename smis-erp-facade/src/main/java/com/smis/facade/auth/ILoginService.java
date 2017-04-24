package com.smis.facade.auth;

import java.util.List;

import com.smis.model.vo.auth.SysMenuVo;
import com.smis.model.vo.base.Message;

public interface ILoginService {
	public Message login(String login, String password, String ip);
	
	public List<SysMenuVo> getAllMenu();
}

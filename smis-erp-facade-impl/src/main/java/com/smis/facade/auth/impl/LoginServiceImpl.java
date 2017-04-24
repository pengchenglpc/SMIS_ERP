package com.smis.facade.auth.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smis.common.util.GlobalParam;
import com.smis.dao.auth.ISysLoginLogDao;
import com.smis.dao.auth.ISysMenuDao;
import com.smis.dao.auth.ISysUserDao;
import com.smis.facade.auth.ILoginService;
import com.smis.model.entity.auth.SysLoginLog;
import com.smis.model.vo.auth.SysMenuVo;
import com.smis.model.vo.auth.SysUserVo;
import com.smis.model.vo.base.Message;

@Service("loginService")
@Transactional
public class LoginServiceImpl implements ILoginService {
	@Autowired
	private ISysUserDao sysUserDao;
	@Autowired
	private ISysLoginLogDao logDao;
	@Autowired
	private ISysMenuDao menuDao;
	
	private void saveLoginLog(String login, String ip, SysUserVo user,Integer status, String remark){
		SysLoginLog log = new SysLoginLog();
		log.setLoginName(login);
		log.setLoginIP(ip);
		log.setLoginTime(new Date());
		log.setIsSuccess(status);
		log.setRemark(remark);
		if(user != null){
			log.setUserID(user.getId());
		}
		logDao.save(log);
	}
	
	@Override
	public Message login(String login, String password, String ip) {
		Message msg = new Message();
		msg.setResultCode(0);
		SysUserVo userVo = sysUserDao.getUserByLoginName(login);
		if(userVo == null){
			msg.setResultCode(-1);
			msg.setMsg("账号或密码不正确");
			saveLoginLog(login, ip, userVo,0, "用户不存在");
			return msg;
		}
		int loginErrCount = GlobalParam.getInstance().getLoginErrCount();
		int freezeTime = GlobalParam.getInstance().getLoginErrFreezeTime();
		long ts = System.currentTimeMillis() - freezeTime * 60 * 1000;
		if (userVo.getLoginErrCount() >= loginErrCount && userVo.getLoginErrTime() > ts) {// 超过错误次数冻结账户
			msg.setResultCode(-1);
			msg.setMsg(String.format("连续错误次数过多，冻结账户%s分钟", freezeTime));
			saveLoginLog(login, ip, userVo,0, String.format("连续错误次数过多，冻结账户%s分钟", freezeTime));
			return msg;
		}
		if(!password.equals(userVo.getPassword())){
			msg.setResultCode(-1);
			msg.setMsg("账号或密码不正确");
			sysUserDao.updateLoginErr(userVo.getId());
			saveLoginLog(login, ip, userVo,0, "密码错误");
			return msg;
		}
		if(userVo.getIsDelete() == 0){
			msg.setMsg("当前账户已被删除，请联系管理员");
			msg.setResultCode(-1);
			return msg;
		}
		if (userVo.getStatus() != 1) {
			msg.setResultCode(-1);
			msg.setMsg("当前账号已被冻结，请联系管理员");
			saveLoginLog(login, ip, userVo,0, "当前账号已被冻结");
			return msg;
		}
		saveLoginLog(login, ip, userVo,1, "登录成功");
		msg.setData(userVo);
		return msg;
	}

	@Override
	public List<SysMenuVo> getAllMenu() {
		return menuDao.loadAll();
	}


}

package com.smis.dao.auth.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.smis.common.util.LoggerUtil;
import com.smis.dao.auth.ISysUserDao;
import com.smis.dao.base.impl.BaseDaoImpl;
import com.smis.model.entity.auth.SysUser;
import com.smis.model.vo.auth.SysUserVo;

@Repository 
@Transactional
public class SysUserDaoImpl extends BaseDaoImpl<SysUser, Serializable> implements ISysUserDao {

	@Override
	public SysUserVo getUserByLoginName(String loginName) {
		List<SysUser> users = this.findByField("loginName", loginName);
		SysUserVo userVo = null;
		if(!CollectionUtils.isEmpty(users)){
			SysUser user = users.get(0);
			userVo = new SysUserVo();
			BeanUtils.copyProperties(user, userVo);
		}
		return userVo;
	}

	@Override
	public Boolean updateLoginErr(Integer userId) {
		try {
			Timestamp ts = new Timestamp(System.currentTimeMillis());
			SysUser sysUser = this.get(userId);
			Integer loginErrCount = sysUser.getLoginErrCount();
			if (loginErrCount.intValue() >= 10) {
				loginErrCount = 1;
			}
			else {
				if (sysUser.getLoginErrTime() + 24 * 60 * 60 * 1000 <= ts.getTime()) {
					loginErrCount = 1;// 超过24小时重新计算错误次数
				}
				else {
					loginErrCount++;
				}
			}
			sysUser.setLoginErrCount(loginErrCount);
			sysUser.setLoginErrTime(ts.getTime());
			this.update(sysUser);
			return true;
		}
		catch (Exception e) {
			LoggerUtil.MyLogger.error("程序出错", e);
			return false;
		}
	}
	
}

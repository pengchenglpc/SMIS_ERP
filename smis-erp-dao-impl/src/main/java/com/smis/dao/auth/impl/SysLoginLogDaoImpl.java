package com.smis.dao.auth.impl;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.smis.dao.auth.ISysLoginLogDao;
import com.smis.dao.base.impl.BaseDaoImpl;
import com.smis.model.entity.auth.SysLoginLog;

@Repository
@Transactional
public class SysLoginLogDaoImpl extends BaseDaoImpl<SysLoginLog, Serializable> implements ISysLoginLogDao {

}

package com.smis.common.util;

public class GlobalParam {
	private GlobalParam(){}
	private static GlobalParam INSTANCE = new GlobalParam();
	public static GlobalParam getInstance(){
		return INSTANCE; 
	}
	
	private Integer loginErrCount;	//最大允许连续登录次数
	private Integer loginErrFreezeTime;	//冻结时间
	public Integer getLoginErrCount() {
		return loginErrCount;
	}
	public void setLoginErrCount(Integer loginErrCount) {
		this.loginErrCount = loginErrCount;
	}
	public Integer getLoginErrFreezeTime() {
		return loginErrFreezeTime;
	}
	public void setLoginErrFreezeTime(Integer loginErrFreezeTime) {
		this.loginErrFreezeTime = loginErrFreezeTime;
	}
	
	
}

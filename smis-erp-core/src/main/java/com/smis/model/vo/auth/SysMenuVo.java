package com.smis.model.vo.auth;

public class SysMenuVo implements Comparable<SysMenuVo> {
	private Integer menuId;	//菜单ID
	
	private Integer parentId;	//上级菜单ID 一级菜单为0
	
	private Integer menuType;	//菜单类型 1=一级菜单 2=二级菜单 3=操作权限
	
	private String menuName;	//菜单名称
	
	private String url;	//菜单路径
	
	private String authUrls;	//授权URL 多个用;号隔开
	
	private Integer orderNum;	//排序编号
	
	private Integer operaValue;	//权限值

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthUrls() {
		return authUrls;
	}

	public void setAuthUrls(String authUrls) {
		this.authUrls = authUrls;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public Integer getOperaValue() {
		return operaValue;
	}

	public void setOperaValue(Integer operaValue) {
		this.operaValue = operaValue;
	}

	@Override
	public int compareTo(SysMenuVo arg0) {
		return this.getOrderNum().compareTo(arg0.getOrderNum());
	}
}

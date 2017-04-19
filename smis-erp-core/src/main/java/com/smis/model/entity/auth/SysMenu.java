package com.smis.model.entity.auth;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 系统菜单表
 * @author dell
 *
 */
public class SysMenu implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4340169319766371631L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RoleId", nullable = false)
	private Integer menuId;	//菜单ID
	
	@Column(name = "ParentId", nullable = false)
	private Integer parentId;	//上级菜单ID 一级菜单为0
	
	@Column(name = "MenuType", nullable = false)
	private Integer menuType;	//菜单类型 1=一级菜单 2=二级菜单 3=操作权限
	
	@Column(name = "MenuName", nullable = false)
	private String menuName;	//菜单名称
	
	@Column(name = "Url", nullable = false)
	private String url;	//菜单路径
	
	@Column(name = "AuthUrls", nullable = false)
	private String authUrls;	//授权URL 多个用;号隔开
	
	@Column(name = "OrderNum", nullable = false)
	private Integer orderNum;	//排序编号
	
	@Column(name = "OperaValue", nullable = false)
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
	
	
	
}

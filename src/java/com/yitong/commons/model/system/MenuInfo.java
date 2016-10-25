package com.yitong.commons.model.system;

import java.util.List;

public class MenuInfo {

	// Fields
	private String menuId;
	private String menuName;
	private String menuParId;
	private Integer menuSort;
	private String menuUrl;
	private String menuImg;
	private String menuDesc;
	// 子菜单
	private List children;

	// Constructors

	/** default constructor */
	public MenuInfo() {
	}

	// Property accessors

	public List getChildren() {
		return children;
	}

	public void setChildren(List children) {
		this.children = children;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuParId() {
		return menuParId;
	}

	public void setMenuParId(String menuParId) {
		this.menuParId = menuParId;
	}

	public Integer getMenuSort() {
		return menuSort;
	}

	public void setMenuSort(Integer menuSort) {
		this.menuSort = menuSort;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public String getMenuImg() {
		return menuImg;
	}

	public void setMenuImg(String menuImg) {
		this.menuImg = menuImg;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

}
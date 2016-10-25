package com.yitong.app.service.system;

import java.util.List;
import java.util.Map;

import com.yitong.commons.service.BaseService;

public class LoginService extends BaseService {

	/**
	 * 加载系统所有菜单
	 * 
	 * @return
	 */
	public List findAllMenus() {
		return this.findList("MenuInfo.findAll", null);
	} 

	/**
	 * 加载用户菜单
	 * 
	 * @return
	 */
	public List findMenuByUserId(Map params) {
		return this.findList("MenuInfo.findMenuByUserId", params);
	}

	/**
	 * 加载报表菜单
	 * 
	 * @param params
	 * @return
	 */
	public List findReportMenus(Map params) {
		return this.findList("ReportInfo.queryReportMenu", params);
	}

	/**
	 * 修改密码
	 * 
	 * @param user
	 * @return
	 */
	public boolean updatePwd(Map user) {
		return this.update("UserInfo.updateById", user);
	}
}

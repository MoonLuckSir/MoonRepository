package com.yitong.app.service.system;

import java.util.List;
import java.util.Map;

import com.yitong.commons.model.IListPage;
import com.yitong.commons.service.BaseService;

public class UserService extends BaseService {

	/**
	 * 查询并分页
	 * 
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("UserInfo.pageQuery", "UserInfo.pageCount",
				params, pageNo);
	}

	public boolean insert(Map params) {
		insert("UserInfo.insert", params);
		return true;
	}

	public boolean updateById(Map params) {
		return update("UserInfo.updateById", params);
	}

	public boolean delete(Map params) {
		return update("UserInfo.delete", params);
	}

	/**
	 * 加载
	 * 
	 * @param params
	 * @return
	 */
	public Map load(String params) {
		return (Map) load("UserInfo.loadById", params);
	}
	
	public Map query(Map params) {
		return (Map) load("UserInfo.query", params);
	}
	
	/**
	 * 重设密码
	 * 
	 * @param params
	 * @return
	 */
	public Map resetPwd(Map params) {
		return (Map) load("UserInfo.resetPwd", params);
		
	} 
	
	/**
	 * 查询角色
	 * 
	 * @param params
	 * @return
	 */
	public List findRoles(Map params) {
		return this.findList("RoleInfo.query", params);
		
	}
	/**
	 * 查询用户与角色关系
	 * 
	 * @param params
	 * @return
	 */
	public List findUserRoles(Map params) {
		return this.findList("UserRole.query", params);
	}
	
	/**
	 * 更新用户角色配制
	 * 
	 * @param rptIds
	 * @param params
	 * @return
	 */
	public boolean alotUserRoles(String[] roleIds, Map params) {
		this.delete("UserRole.delete", params);
		if (roleIds != null) {
			for (int i = 0; i < roleIds.length; i++) {
				params.put("roleId", roleIds[i]);
				this.insert("UserRole.insert", params);
			}
		}
		return true;
	}
	/**
	 * 查询报表
	 * 
	 * @param params
	 * @return
	 */
	public List findRpts(Map params) {
		return this.findList("ReportInfo.query", params);
		
	}
	
	/**报表角色关系
	 * 
	 * @param params
	 * @return
	 */
	public List findUserRpts(Map params) {
		return this.findList("UserRpt.query", params);
	}
	/**
	 * 更新用户角色配制
	 * 
	 * @param rptIds
	 * @param params
	 * @return
	 */
	public boolean alotUserRpts(String[] rptIds, Map params) {
		this.delete("UserRpt.delete", params);
		if (rptIds != null) {
			for (int i = 0; i < rptIds.length; i++) {
				params.put("rptId", rptIds[i]);
				this.insert("UserRpt.insert", params);
			}
		}
		return true;
	}
	
	/**
	 * 查找我下属机构
	 * @param errorCode
	 * @param fileName
	 * @return
	 */
	public String findOrgNo(String orgCode){
		List list = this.findList("UserInfo.findOrgNo", orgCode);
		StringBuffer returnValue = new StringBuffer();
		returnValue.append("'-1'");
		for(int i =0;i<list.size();i++){
			Map map  =(Map)list.get(i);
			returnValue.append(",'");
			returnValue.append(map.get("ORG_ID"));
			returnValue.append("'");
		}
		return returnValue.toString();
	}
}

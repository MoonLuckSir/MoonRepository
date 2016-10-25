package com.yitong.app.service.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yitong.commons.model.IListPage;
import com.yitong.commons.service.BaseService;

public class RoleService extends BaseService {

	/**
	 * 查询并分页
	 * 
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("RoleInfo.pageQuery", "RoleInfo.pageCount",
				params, pageNo);
	}

	public boolean insert(Map params) {
		insert("RoleInfo.insert", params);
		return true;
	}

	public boolean updateById(Map params) {
		return update("RoleInfo.updateById", params);
	}

	public boolean delete(Map params) {
		// 刪除原有角色菜单信息
		this.delete("RoleInfo.deleteRoleMenus", params);
		return update("RoleInfo.delete", params);
	}

	public Map load(Map params) {
		return (Map) load("RoleInfo.query", params);
	}

	public List findAllMenus() {
		return this.findList("MenuInfo.findAll", null);
	}

	public List findMenusByRoleId(String roleId) {
		return this.findList("MenuInfo.findByRoleId", roleId);
	}

	/**
	 * 配置角色对应的菜单
	 * 
	 * @param menus
	 * @param roleId
	 * @return
	 */
	public boolean alotMenus(List menus, Map params) {
		// 刪除原有角色菜单信息
		this.delete("RoleInfo.deleteRoleMenus", params);
		// 配置新的角色菜单
		if (menus != null) {
			List datas = new ArrayList();
			for (Iterator iter = menus.iterator(); iter.hasNext();) {
				Map tmp = new HashMap();
				tmp.putAll(params);
				tmp.put("menuId", iter.next());
				datas.add(tmp);
				// this.insert("RoleInfo.alotMenus", params);
			}
			this.batch4Update("RoleInfo.alotMenus", datas);
		}
		return true;
	}

	/**
	 * 查找用户信息
	 * 
	 * @param params
	 * @return
	 */
	public List findUsers(Map params) {
		return this.findList("UserInfo.query", params);
	}

	/**
	 * 按机构分配用户角色
	 * 
	 * @param users
	 * @param roleId
	 * @param orgId
	 * @return
	 */
	public boolean alotUsers(String[] users, String roleId, String orgId) {
		// 删除角色在某机构下的原始分配用户
		Map params = new HashMap();
		params.put("roleId", roleId);
		params.put("orgId", orgId);
		this.delete("RoleInfo.deleteRoleUsers", params);
		// 保存新的角色用户分配关系
		if (users != null) {
			List datas = new ArrayList();
			for (int i = 0; i < users.length; i++) {
				Map tmp = new HashMap();
				tmp.put("roleId", roleId);
				tmp.put("orgId", orgId);
				tmp.put("userId", users[i].trim());
				datas.add(tmp);
			}
			// this.insert("RoleInfo.alotUsers", params);
			this.batch4Update("RoleInfo.alotUsers", datas);
		}
		return true;
	}

	/**
	 * 删除某用户角色
	 * 
	 * @param params
	 * @return
	 */
	public boolean deleteRoleUser(Map params) {
		return this.delete("RoleInfo.deleteRoleUsers", params);
	}

	/**
	 * 加载报表清单
	 * 
	 * @param params
	 * @return
	 */
	public List findReports(Map params) {
		return this.findList("ReportInfo.query", params);
	}

	/**
	 * 加载角色报表关系
	 * 
	 * @param params
	 * @return
	 */
	public List findRoleReports(Map params) {
		return this.findList("RoleRpt.query", params);
	}

	/**
	 * 更新角色报表配制
	 * 
	 * @param rptIds
	 * @param params
	 * @return
	 */
	public boolean alotReports(String[] rptIds, Map params) {
		this.delete("RoleRpt.delete", params);
		if (rptIds != null) {
			for (int i = 0; i < rptIds.length; i++) {
				params.put("rptId", rptIds[i]);
				this.insert("RoleRpt.insert", params);
			}
		}
		return true;
	}

}

package com.yitong.app.service.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yitong.commons.model.IListPage;
import com.yitong.commons.model.IOrgTreeService;
import com.yitong.commons.model.system.Organ;
import com.yitong.commons.service.BaseService;

public class OrganService extends BaseService implements IOrgTreeService{

	/**
	 * 查询并分页
	 * 
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("OrganInfo.pageQuery", "OrganInfo.pageCount",
				params, pageNo);
	} 

	public Map load(Map params) {
		return (Map) load("OrganInfo.query", params);
	}

	public List findChildrenByOrgId(String orgId) {
		return this.findList("OrganInfo.findChildren", orgId);
	}

	public List findUsers(String orgId, String userType) {
		Map params = new HashMap();
		params.put("orgId", orgId);
		params.put("userType", userType);
		return this.findList("UserInfo.query", params);
	}

	public Organ loadOrganById(String orgId) {
		return (Organ) this.load("OrganInfo.loadById", orgId);
	}
}

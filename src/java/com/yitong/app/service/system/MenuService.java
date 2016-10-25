package com.yitong.app.service.system;

import java.util.Map;

import com.yitong.commons.model.IListPage;
import com.yitong.commons.service.BaseService;

public class MenuService extends BaseService {

	/**
	 * 查询并分页
	 * 
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("MenuInfo.pageQuery", "MenuInfo.pageCount",
				params, pageNo);
	}

	public boolean insert(Map params) {
		insert("MenuInfo.insert", params);
		return true;
	}

	public boolean updateById(Map params) {
		return update("MenuInfo.updateById", params);
	}

	public boolean delete(Map params) {
		return update("MenuInfo.delete", params);
	}

	public Map load(Map params) {
		return (Map) load("MenuInfo.query", params);
	}
}

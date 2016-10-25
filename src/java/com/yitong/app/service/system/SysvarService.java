package com.yitong.app.service.system;

import java.util.Map;

import com.yitong.commons.model.IListPage;
import com.yitong.commons.service.BaseService;

public class SysvarService extends BaseService {

	/**
	 * 查询并分页
	 * 
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("SysvarInfo.pageQuery", "SysvarInfo.pageCount",
				params, pageNo);
	}

	public boolean insert(Map params) {
		insert("SysvarInfo.insert", params);
		return true;
	}

	public boolean updateById(Map params) {
		return update("SysvarInfo.updateById", params);
	}

	public boolean delete(Map params) {
		return update("SysvarInfo.delete", params);
	}

	public Map load(String params) {
		return (Map) load("SysvarInfo.loadById", params);
	}
}

package com.yitong.app.service.system;

import java.util.Map;

import com.yitong.commons.model.Consts;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.service.BaseService;

public class ParamHisService extends BaseService {

	/**
	 * 查询并分页
	 * 
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("paramHis.pageQuery", "paramHis.pageCount",params, pageNo,Consts.PAGE_SIZE);
	}

	public boolean insert(Map params) {
		getIbatisDao().insert("paramHis.insert", params);
		return true;
	}

	public boolean updateById(Map params) {
		return getIbatisDao().update("paramHis.updateById", params);
	}

	public boolean delete(Map params) {
		return getIbatisDao().update("paramHis.delete", params);
	}

	/**
	 * 加载
	 * 
	 * @param params
	 * @return
	 */
	public Map load(String params) {
		return (Map) getIbatisDao().load("paramHis.loadById", params);
	}

}

package com.yitong.app.service.judicial.lscxsh;

import java.util.List;
import java.util.Map;

import com.yitong.commons.model.IListPage;
import com.yitong.commons.service.BaseService;

public class lscxShService extends BaseService {

	/**
	 * 查询并分页
	 * 
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("lscxSq.pageQuery", "lscxSq.pageCount",
				params, pageNo);
	} 

	public Map load(Map params) {
		return (Map) load("lscxSq.query", params);
	}

	public List findConList (Map params) {
		return this.findList("lscxCon.query1",params);
	}
	public List findMbList (Map params) {
		return this.findList("LscxMb.query",params);
	}
	public boolean insert(Map params) {
		insert("lscxSq.insert", params);
		return true;
	}

	public boolean updateById(Map params) {
		return update("lscxSq.updateById", params);
	}
	public boolean delete(Map params) {
		return update("lscxSq.delete", params);
	}
	public Map load(String params) {
		return (Map) load("lscxSq.loadById", params);
	}
	public boolean insertHis(Map params) {
		insert("lscxSqhis.insert", params);
		return true;
	}
	public List QueryHis(Map params) {
		return this.findList("lscxSqhis.query",params);
	} 
	public Map getConVal(Map params) {
		return (Map) load("lscxDtzx.query", params);
	}
}

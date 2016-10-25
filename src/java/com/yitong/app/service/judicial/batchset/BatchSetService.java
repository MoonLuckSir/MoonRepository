package com.yitong.app.service.judicial.batchset;

import java.util.List;
import java.util.Map;

import com.yitong.commons.model.IListPage;
import com.yitong.commons.service.BaseService;

//批量设置Service
public class BatchSetService extends BaseService {
	@SuppressWarnings("unchecked")
	public IListPage pageQuery(Map params, int pageNo) {
		logger.info("BatchSetService.........pageQuery...........");
		return this.pageQuery("batchSet.pageQuery", "batchSet.pageCount",
				params, pageNo);
	}

	/**
	 * 获取批量查询导入excel账号属性：内部账号、身份证
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getImportNoTypes() {
		return this.findList("batchSet.getImportNoTypes", null);
	}

	/**
	 * 获取批量查询交易名称列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getBatchTypesFromConfig() {
		return this.findList("batchSet.getBatchTypesConfig", null);
	}

	@SuppressWarnings("unchecked")
	public List getBatchTypesFromVAR() {
		return this.findList("batchSet.getBatchTypesFromVAR", null);
	}

	/**
	 * 获取启用的批量查询交易名称列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getBatchTypesQy() {
		return this.findList("batchSet.getBatchTypesQy", null);
	}

	@SuppressWarnings("unchecked")
	public boolean insert(Map params) {
		insert("batchSet.insert", params);
		return true;
	}

	@SuppressWarnings("unchecked")
	public Map load(String params) {
		logger.info("batchSet.loadById.........");
		return (Map) load("batchSet.loadById", params);
	}

	@SuppressWarnings("unchecked")
	public Map loadByType(Map params) {
		return loadByType("batchSet.loadByType", params);
	}

	@SuppressWarnings("unchecked")
	public boolean updateById(Map params) {
		logger.info("updateById............" + params);
		return update("batchSet.updateById", params);
	}

	@SuppressWarnings("unchecked")
	public boolean delete(Map params) {
		return delete("batchSet.delete", params);
	}

	@SuppressWarnings("unchecked")
	public Map loadByBatchTypeFromVAR(String tradeType) {
		return (Map) load("batchSet.loadByBatchTypeFromVAR", tradeType);
	}

	public String getBatchId() {
		return (String) this.load("batchSet.getBatchId", null);
	}
}

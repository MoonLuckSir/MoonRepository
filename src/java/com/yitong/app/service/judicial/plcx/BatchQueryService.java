package com.yitong.app.service.judicial.plcx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yitong.commons.model.IListPage;
import com.yitong.commons.service.BaseService;

public class BatchQueryService extends BaseService {
	/**提交审核列表
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQueryCommitAudit(Map params, int pageNo) {
		return this.pageQuery("plcx.pageQueryCommitAudit", "plcx.pageCountCommitAudit",
				params, pageNo);
	}
	
	
	/**审核列表
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQueryAudit(Map params, int pageNo) {
		return this.pageQuery("plcx.pageQueryAudit", "plcx.pageCountAudit",
				params, pageNo);
	}
	
	/**批量查询结果文件列表
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQueryDownload(Map params, int pageNo) {
		return this.pageQuery("plcx.pageQueryDownload", "plcx.pageCountDownload",
				params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Object insertInfo(Map params){
		logger.info("导入Excel........params");
		return this.insert("plcx.insert", params);
	}
	
	@SuppressWarnings("unchecked")
	public Object addInfo(Map params){
		return this.insert("plcx.addInfo", params);
	}
	
	@SuppressWarnings("unchecked")
	public Object loadBatchId(Map infoParams){
		return this.load("plcx.loadBatchId", infoParams);
	}
	
	/**从sequence中获取batchId,用于导入主、明细表共用
	 * @return
	 */
	public String getBatchId(){
		return (String) this.load("plcx.getBatchId", null);
	}
	/**
	 * 从BATCH_IMPORT_CONFIG参数表中获取是否需要查询日期条件的值
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map getDateQueryFlag(String batchType){
		return  (Map) this.load("plcx.getDateQueryFlag", batchType);
	}
	
	/**
	 * 从BATCH_IMPORT_CONFIG参数表中获取某交易类型对应的证件类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map getQueryNoType(String batchType){
		return  (Map) this.load("plcx.getQueryNoType", batchType);
	}
	
	/**从BACTH_IMPORT_CONFIG获取交易类型的配置信息
	 * @param batchType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map getBatchImportConfigInfo(String batchType){
		return  (Map) this.load("plcx.getBatchImportConfigInfo", batchType);
	}
	
	/**提交审核操作：更新importStatus='1',commit_audit_time
	 * @param batchId
	 */
	public void commitAudit(String batchId){
		this.update("plcx.commitAudit", batchId);
	}
	
	/**提交审核列表：对于importStatus为0未提交、3审核不通过的记录可以进行删除操作
	 * @param batchId
	 */
	@SuppressWarnings("unchecked")
	public void deleteInfo(Map batchIds){
		this.delete("plcx.deleteBatchImport", batchIds);
		this.delete("plcx.deleteBatchImportDetail", batchIds);
	}
	
	/**获取批量查询交易名称列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getBatchTypes(){
		return this.findList("plcx.getBatchTypes", null);
	}
	
	/**获取启用的批量查询交易名称列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getBatchTypesQy(){
		return this.findList("plcx.getBatchTypesQy", null);
	}
	
	/**获取批量查询导入excel账号属性：内部账号、身份证
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getImportNoTypes(){
		return this.findList("plcx.getImportNoTypes", null);
	}
	
	/**获取交易类型对应的可查询的证件种类名称及value值
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getImportNoTypesNamesWithValue(String queryNoTypes){
		Map params = new HashMap();
		params.put("queryNoTypes", queryNoTypes);
		return this.findList("plcx.getImportNoTypesNamesWithValue", params);
	}
	
	/**获取批量查询导入excel的列名
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List getExcelColumns(){
		return this.findList("plcx.getExcelColumns", null);
	}
	
	/**批量查询审核：审核通过、审核不通过操作
	 * @param batchIds
	 * @param importStatus
	 */
	@SuppressWarnings("unchecked")
	public void audit(String batchIds,String importStatus,Map params){
		String[] batchId = batchIds.split(",");
		params.put("batchIds", batchId);
		params.put("importStatus", importStatus);
		
		this.update("plcx.auditBatchImport", params);
		if(importStatus.equals("3")){//审核不通过时，需要将字表记录删除
			this.delete("plcx.deleteBatchImportDetail", params);
		}else{
			//审核通过时，有立即生成的数目，需要更新相应表状态值
			if(this.countGenRightNowNum(params)>0){
				this.update("plcx.updateStatusGenRightNow", null);
			}
		}
	}
	
	/**审核通过时，获取立即生成的数目
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int countGenRightNowNum(Map params){
		return (Integer)this.load("plcx.countGenRightNowNum", params);
	}
	

	
	/**批量查询结果文件：重新生成，将importStatus从4 更新为2
	 * @param batchId
	 */
	@SuppressWarnings("unchecked")
	public void resetImportStatus(String batchId){
		this.update("plcx.resetImportStatus", batchId);
		Map params = new HashMap();
		String[] batchIds = new String[1];
		batchIds[0] = batchId;
		params.put("batchIds", batchIds);
		//有立即生成的数目，需要更新相应表状态值
		if(this.countGenRightNowNum(params)>0){
			this.update("plcx.updateStatusGenRightNow", null);
		}
	}
	
	/**批量查询：状态为审核通过待生成、审核不通过，查看审核意见
	 * @param batchId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map viewAuditRemark(String batchId){
		return (Map)this.load("plcx.viewviewAuditRemark", batchId);
	}
}

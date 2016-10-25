package com.yitong.app.service.judicial.dxzpzfdj;

import java.util.HashMap;
import java.util.Map;

import com.yitong.commons.model.IListPage;
import com.yitong.commons.service.BaseService;

public class ZfdjService extends BaseService{

	//止付查询
	@SuppressWarnings("unchecked")
	public IListPage pageQueryZfTask(Map params, int pageNo){
		return this.pageQuery("zfdj.pageQuery", "zfdj.pageCount", 
				params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Map load(String params) {
		logger.info("zfdj.loadById.........");
		return (Map) load("zfdj.loadById", params);
	}
	

	//查询止付反馈信息
	
	@SuppressWarnings("unchecked")
	public IListPage pageQueryZffkTask(Map params, int pageNo){
		return this.pageQuery("zfdj.pageQueryZffkTask", "zfdj.pageCountZffkTask", 
				params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Map zffkload(String params) {
		logger.info("zfdj.loadByZffkId.........");
		return (Map) load("zfdj.loadByZffkId", params);
	}
	
	//止付解除查询
	@SuppressWarnings("unchecked")
	public IListPage pageQueryJcTask(Map params, int pageNo){
		return this.pageQuery("zfdj.pageQueryJcTask", "zfdj.pageCountJcTask", 
				params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Map jcload(String params) {
		logger.info("zfdj.loadByJcId.........");
		return (Map) load("zfdj.loadByJcId", params);
	}
	
	//查询止付解除反馈信息
	@SuppressWarnings("unchecked")
	public IListPage pageQueryJcfkTask(Map params, int pageNo){
		return this.pageQuery("zfdj.pageQueryJcfkTask", "zfdj.pageCountJcfkTask", 
				params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Map jcfkload(String params) {
		logger.info("zfdj.loadByJcfkId.........");
		return (Map) load("zfdj.loadByJcfkId", params);
	}
	
	//止付延期查询
	@SuppressWarnings("unchecked")
	public IListPage pageQueryYqTask(Map params, int pageNo){
		return this.pageQuery("zfdj.pageQueryYqTask", "zfdj.pageCountYqTask", 
				params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Map yqload(String params) {
		logger.info("zfdj.loadByYqId.........");
		return (Map) load("zfdj.loadByYqId", params);
	}
	
	//查询止付延期反馈信息
	@SuppressWarnings("unchecked")
	public IListPage pageQueryYqfkTask(Map params, int pageNo){
		return this.pageQuery("zfdj.pageQueryYqfkTask", "zfdj.pageCountYqfkTask", 
				params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Map yqfkload(String params) {
		logger.info("zfdj.loadByYqfkId.........");
		return (Map) load("zfdj.loadByYqfkId", params);
	}
	
	//冻结查询
	@SuppressWarnings("unchecked")
	public IListPage pageQueryDjTask(Map params, int pageNo){
		return this.pageQuery("zfdj.pageQueryDjTask", "zfdj.pageCountDjTask", 
				params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Map djload(String params) {
		logger.info("zfdj.loadByDjId.........");
		return (Map) load("zfdj.loadByDjId", params);
	}
	
	//查询冻结反馈信息
	@SuppressWarnings("unchecked")
	public IListPage pageQueryDjfkTask(Map params, int pageNo){
		return this.pageQuery("zfdj.pageQueryDjfkTask", "zfdj.pageCountDjfkTask", 
				params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Map djfkload(String params) {
		logger.info("zfdj.loadByDjfkId.........");
		return (Map) load("zfdj.loadByDjfkId", params);
	}
	
	//查询冻结解除信息
	@SuppressWarnings("unchecked")
	public IListPage pageQueryDjjcTask(Map params, int pageNo){
		return this.pageQuery("zfdj.pageQueryDjjcTask", "zfdj.pageCountDjjcTask", 
				params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Map djjcload(String params) {
		logger.info("zfdj.loadByDjjcId.........");
		return (Map) load("zfdj.loadByDjjcId", params);
	}
	
	//查询冻结解除反馈信息
	@SuppressWarnings("unchecked")
	public IListPage pageQueryDjjcfkTask(Map params, int pageNo){
		return this.pageQuery("zfdj.pageQueryDjjcfkTask", "zfdj.pageCountDjjcfkTask", 
				params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Map djjcfkload(String params) {
		logger.info("zfdj.loadByDjjcfkId.........");
		return (Map) load("zfdj.loadByDjjcfkId", params);
	}
	
	//查询冻结延期信息
	@SuppressWarnings("unchecked")
	public IListPage pageQueryDjyqTask(Map params, int pageNo){
		return this.pageQuery("zfdj.pageQueryDjyqTask", "zfdj.pageCountDjyqTask", 
				params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Map djyqload(String params) {
		logger.info("zfdj.loadByDjyqId.........");
		return (Map) load("zfdj.loadByDjyqId", params);
	}
	
	//查询冻结反馈信息
	@SuppressWarnings("unchecked")
	public IListPage pageQueryDjyqfkTask(Map params, int pageNo){
		return this.pageQuery("zfdj.pageQueryDjyqfkTask", "zfdj.pageCountDjyqfkTask", 
				params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Map djyqfkload(String params) {
		logger.info("zfdj.loadByDjyqfkId.........");
		return (Map) load("zfdj.loadByDjyqfkId", params);
	}
	
	//计算所有司法查控统计项目
	@SuppressWarnings("unchecked")
	public Map<String, String> dxzpSfckAddUp(Map map) {
		Map<String, String> params = new HashMap<String, String>();
		//统计止付业务笔数
		params.put("zfBizNum", (String) load("zfdj.countZfBizNum", map));
		//统计止付账户数
		params.put("zfAcctNum", (String) load("zfdj.countZfAcctNum", map));
		//统计止付账户余额
		String zfAcctBalance = (String) load("zfdj.countZfAcctBalance", map);
		
		if("null".equals(zfAcctBalance) || zfAcctBalance == null){
			zfAcctBalance = "0.00";
		}
		params.put("zfAcctBalance", zfAcctBalance);
		//统计冻结业务笔数
		params.put("djBizNum", (String) load("zfdj.countDjBizNum", map));
		//统计冻结账户数
		params.put("djAcctNum", (String) load("zfdj.countDjAcctNum", map));
		//统计冻结账户余额
		String djAcctBalance = (String) load("zfdj.countDjAcctBalance", map);
		
		if("null".equals(djAcctBalance) || djAcctBalance == null){
			djAcctBalance = "0.00";
		}
		params.put("djAcctBalance", djAcctBalance);
		//统计查询业务笔数
		params.put("qryBizNum", (String) load("zfdj.countQryBizNum", map));
		//统计查询账户数
		params.put("qryAcctNum", (String) load("zfdj.countQryAcctNum", map));
		//合计业务笔数
		params.put("totalBizNum", (String) load("zfdj.totalBizNum", map));
		//合计账户数
		params.put("totalAcctNum", (String) load("zfdj.totalAcctNum", map));
		//合计账户余额
		String totalAcctBalance = (String) load("zfdj.totalAcctBalance", map);
		
		if("null".equals(totalAcctBalance) || totalAcctBalance == null ){
			totalAcctBalance = "0.00";
		}
		params.put("totalAcctBalance", totalAcctBalance);
		
		return params;
	}
	
	/**
	 * 增加异常开卡
	 * @param params
	 * @return
	 */
	public boolean insertYckk(Map params) {
		insert("zfdj.insertYckk", params);
		return true;
	}
	
	/**
	 * 增加涉案账户
	 * @param params
	 * @return
	 */
	public boolean insertSazh(Map params) {
		insert("zfdj.insertSazh", params);
		return true;
	}
	
	/**
	 * 增加异常事件
	 * @param params
	 * @return
	 */
	public boolean insertYcsj(Map params) {
		insert("zfdj.insertYcsj", params);
		return true;
	}
	
	/**
	 * 增加案件举报
	 * @param params
	 * @return
	 */
	public boolean insertAjjb(Map params) {
		insert("zfdj.insertAjjb", params);
		return true;
	}
	
}

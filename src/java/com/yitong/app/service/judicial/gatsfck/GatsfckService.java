package com.yitong.app.service.judicial.gatsfck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.model.judicial.sfck.CxqqInfo_JCY;
import com.yitong.commons.service.BaseService;

public class GatsfckService extends BaseService {
	
	
	/**查询公安厅查询任务列表
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQueryGatTask(Map params, int pageNo){
		return this.pageQuery("gatck.pageQueryGatTask", "gatck.pageCountGatTask", 
				params, pageNo);
	}
	
	
	
	/**查询法院查询任务条件列表
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQueryFyCond(Map params, int pageNo){
		return this.pageQuery("fyck.pageQueryFyCond", "fyck.pageCountFyCond", 
				params, pageNo);
	}
	
	/**查询法院查询任务条件列表
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQueryZgfyCond(Map params, int pageNo){
		return this.pageQuery("fyck.pageQueryZgfyCond", "fyck.pageCountZgfyCond", 
				params, pageNo);
	}
	
	
	/**添加查询任务
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean addTask(Map params){
		this.insert("fyck.addTask", params);
		return true;
	}
	
	/**添加查询条件信息 法院
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean insertConData(Map params){
		
		this.insert("fyck.insertConData", params);
		return true;
	}
	
	
	/**
	 * 查找有无重复的查询请求单号
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean hasBDHM(Map params){
		if(this.load("fyck.loadConData", params) != null){
			return true;
		}
		return false;
	}
	
	/**
	 * 删除重复的报单编号
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean delBDHM(Map params){
		this.delete("fyck.delConData", params);
		return false;
	}
	
	/**
	 *	根据任务编号生成json参数
	 * @param params
	 * @param pageNo
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public String makeParams(List list,String taskId){
		 Map params = new HashMap();
		 params.put("queryList", list);
		 params.put("batchNum", taskId);
		 String paramStr = JSON.toJSONString(params);
		 System.out.println(paramStr);
		 return paramStr;
	}
	
	/**更改任务状态
	 * @param params
	 * @param pageNo
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public boolean updateTaskStu(Map params){
		
		this.update("fyck.updateTaskStu", params);
		 return true;
	}
	
}

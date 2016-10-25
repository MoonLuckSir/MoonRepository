package com.yitong.app.service.judicial.jcysfck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.model.judicial.sfck.CxqqInfo_JCY;
import com.yitong.commons.service.BaseService;

public class DjcxService extends BaseService {
	
	/**查询任务列表
	 * @param params
	 * @param pageNo
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public IListPage pageQueryTask(Map params, int pageNo){
		return this.pageQuery("djcx.pageQueryTask", "djcx.pageCountTask", 
				params, pageNo);
	}
	
	/**查询任务列表
	 * @param params
	 * @param pageNo
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public IListPage pageQueryAudit(Map params, int pageNo){
		return this.pageQuery("djcx.pageQueryAudit", "djcx.pageCountAudit", 
				params, pageNo);
	}
	
	/**加载所有启用的查询模板
	 * @param params
	 * @param pageNo
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public List findListCxmb(){
		return this.findList("djcx.findListCxmb", null);
	
	}
	
	
	
	
	
	/**添加查询任务
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean addTask(Map params){
		this.insert("djcx.addTask", params);
		return true;
	}
	
	/**添加查询条件信息
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean insertConData(Map params){
		
		this.insert("djcx.insertConData", params);
		return true;
	}
	
	public boolean insertConDataXml(CxqqInfo_JCY cxqq){
		this.insert("djcx.insertConDataXml", cxqq);
		return true;
	}
	
	/**查找有无重复的查询请求单号
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean hasBDHM(Map params){
		if(this.load("djcx.loadConData", params) != null){
			return true;
		}
		return false;
	}
	
	
	/**删除任务和任务下的所有条件
	 * @param params
	 * @param pageNo
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public boolean delTask(Map params){
		 this.delete("djcx.delTask", params);
		 this.delete("djcx.delTaskCondition", params);
		 return true;
	}
	
	/**提交审核任务
	 * @param params
	 * @param pageNo
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public boolean updateTaskStu(Map params){
		
		this.update("djcx.updateTaskStu", params);
		 return true;
	}
	
	
	
	
	/**下载查询结果列表
	 * @param params
	 * @param pageNo
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public IListPage pageQueryCxjg(Map params, int pageNo){
		return this.pageQuery("djcx.pageQueryCxjg", "djcx.pageCountCxjg", 
				params, pageNo);
	}
	
	/**查询任务信息列表
	 * @param params
	 * @param pageNo
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public IListPage pageQueryTaskInfo(Map params, int pageNo){
		return this.pageQuery("djcx.pageQueryTaskInfo", "djcx.pageCountTaskInfo", 
				params, pageNo);
	}
	
	/**
		根据任务编号生成json参数
	 * @param params
	 * @param pageNo
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public String makeParams(String taskId){
		 List<Map> list = new ArrayList<Map>();
		 list = this.findList("djcx.findConditionsByTaskId", taskId);
		 StringBuffer sb = new StringBuffer();
		 Map params = new HashMap();
		 params.put("queryList", list);
		 params.put("batchNum", taskId);
		 String paramStr = JSON.toJSONString(params);
		 System.out.println(paramStr);
		 return paramStr;
	}
	
	
	
}

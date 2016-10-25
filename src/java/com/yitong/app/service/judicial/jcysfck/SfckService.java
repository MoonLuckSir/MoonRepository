package com.yitong.app.service.judicial.jcysfck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yitong.commons.model.IListPage;
import com.yitong.commons.service.BaseService;

public class SfckService extends BaseService {
	
	/**查询模板列表
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQueryCxmb(Map params, int pageNo){
		return this.pageQuery("sfck.pageQueryCxmb", "sfck.pageCountCxmb", 
				params, pageNo);
	}
	
	
	/**添加查询模板
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean addCxmb(Map params){
		this.insert("sfck.addCxmb", params);
		return true;
	}
	
	/**加载查询模板信息
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map loadCxmb(String params){
		return (Map) this.load("sfck.loadCxmb", params);
	}
	
	/**修改模板信息
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateCxmb(Map params){
		this.update("sfck.modiCxmb", params);
		return true;
	}
	
	/**删除模板信息
	 * @param params
	 * @param pageNo
	 * @return delDcmb
	 */
	@SuppressWarnings("unchecked")
	public boolean delCxmb(String CXMB_ID){
		List<Map> list =  this.findList("sfck.findDcmbs", CXMB_ID);
		List<Map> list2 =  this.findList("sfck.findCons", CXMB_ID);
		for(Map m : list){
			String DCMB_ID =  (String) m.get("DCMB_ID");	
			this.delDcmb(DCMB_ID);
		}
		for(Map m : list2){
			String CONDITION_ID =  (String) m.get("CONDITION_ID");	
			this.delCon(CONDITION_ID);
		}
		this.delete("sfck.delCxmb", CXMB_ID);
		return true;
	}
	
	/**更改状态
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateStu(Map params){
		this.delete("sfck.updateStu", params);
		return true;
	}
	
	/**查询查询条件列表
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQueryCon(Map params, int pageNo){
		return this.pageQuery("sfck.pageQueryCon", "sfck.pageCountCon", 
				params, pageNo);
	}
	
	/**添加条件
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean addCon(Map params){
		this.insert("sfck.addCon", params);
		return true;
	}
	
	
	/**校验是否存在相同的物理名
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean checkCon(Map params){
		if(this.load("sfck.checkDcmb", params) != null){
			return false;
		}
		return true;
	}
	
	
	/**加载查询条件信息
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map loadCon(Map params){
		return (Map) this.load("sfck.loadCon", params);
	}
	
	/**修改条件信息
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateCon(Map params){
		this.update("sfck.modiCon", params);
		return true;
	}
	
	/**删除条件信息
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean delCon(String CONDITION_ID){
		this.delete("sfck.delCon", CONDITION_ID);
		this.delete("sfck.delCon2", CONDITION_ID);
		return true;
	}
	
	/**条件规则列表
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQueryRule(Map params, int pageNo){
		return this.pageQuery("sfck.pageQueryRule", "sfck.pageCountRule", 
				params, pageNo);
	}
	
	/**添加规则
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean addRule(Map params){
		this.insert("sfck.addRule", params);
		return true;
	}
	
	/**加载条件规则信息
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map loadRule(Map params){
		return (Map) this.load("sfck.loadRule", params);
	}
	
	/**修改规则信息
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateRule(Map params){
		this.update("sfck.updateRule", params);
		return true;
	}
	
	/**删除规则信息
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean delRule(Map params){
		this.delete("sfck.delRule", params);
		return true;
	}
	
	
	/**删除规则信息
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean isExistRule(String CONDITION_ID,String RULE_NAME){
		Map params = new HashMap();
		params.put("CONDITION_ID", CONDITION_ID);
		params.put("RULE_NAME", RULE_NAME);
		Object obj =  this.load("sfck.checkRule", params);
		return obj != null?true:false;
		
	}
	
	/**导出模板列表
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQueryDcmb(Map params, int pageNo){
		return this.pageQuery("sfck.pageQueryDcmb", "sfck.pageCountDcmb", 
				params, pageNo);
	}
	
	/**添加导出模板
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean addDcmb(Map params){
		this.insert("sfck.addDcmb", params);
		return true;
	}
	
	/**校验导出模板对应表是否重复
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean checkDcmb(Map params){
		if(this.load("sfck.checkDcmb", params) != null){
			return false;
		}
		return true;
	}
	
	/**加载导出模板信息
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map loadDcmb(String DCMB_ID){
		return (Map) this.load("sfck.loadDcmb", DCMB_ID);
	}
	
	/**修改导出模板
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateDcmb(Map params){
		this.update("sfck.updateDcmb", params);
		return true;
	}
	
	/**删除导出模板
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean delDcmb(String DCMB_ID){
		this.delete("sfck.delDcmb", DCMB_ID);
		this.delete("sfck.delDcmb2", DCMB_ID);
		return true;
	}
	
	/**更改导出模板状态
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateStuDc(Map params){
		this.delete("sfck.updateStuDc", params);
		return true;
	}
	
	/**导出字段列表
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQueryDczd(Map params, int pageNo){
		return this.pageQuery("sfck.pageQueryDczd", "sfck.pageCountDczd", 
				params, pageNo);
	}
	
	/**添加导出字段
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean addCol(Map params){
		this.insert("sfck.addCol", params);
		return true;
	}
	
	/**加载导出字段
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map loadCol(String COL_ID){
		return (Map) this.load("sfck.loadCol", COL_ID);
	}
	
	/**校验导出字段物理名称不可重复
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean checkDczd(Map params){
		if(this.load("sfck.checkDczd", params) != null){
			return false;
		}
		return true;
	}
	
	/**修改导出字段
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean updateCol(Map params){
		this.update("sfck.updateCol", params);
		return true;
	}
	
	/**删除导出字段
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean delCol(String COL_ID){
		this.delete("sfck.delCol", COL_ID);
		return true;
	}
	
	
}

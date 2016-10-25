package com.yitong.app.service.judicial.lscxsq;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yitong.commons.model.IListPage;
import com.yitong.commons.service.BaseService;
import com.yitong.commons.util.DateUtil;

public class lscxSqService extends BaseService {

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
	public String getSeq(Date date){
		String today = DateUtil.formatDateToStr(date, "yyyy-MM-dd");	
		Map map = (Map) this.load("lscxSq.maxIndex", today);
		int max = 0; //最大数
		
		String questId ="";
		String num = "";
		today = today.replaceAll("-", "");
		if(map!=null){
			if(map.get("MAXINDEX")!=null && !"".equals(map.get("MAXINDEX"))){
				max = Integer.parseInt(map.get("MAXINDEX").toString().trim());
				max = max+1;
				if(max<10){
				num = "000"+String.valueOf(max);
				}
				else if(max>=10&&max<100){
					num = "00"+String.valueOf(max);
				}
				else if(max>=100&&max<1000){
					num = "0"+String.valueOf(max);
				}
			}else{
				num =  "0001";
			}

			questId = today+num;
		}else{
			questId = today.replaceAll("-", "") +"0001";
		}
		
		return questId;
		
	}
	public boolean insertHis(Map params) {
		insert("lscxSqhis.insert", params);
		return true;
	}
	public List QueryHis(Map params) {
		return this.findList("lscxSqhis.query",params);
	} 
	public Map findMbMap (Map params) {
		return (Map)load("LscxMb.query",params);
	}
	public Map getConVal(Map params) {
		return (Map) load("lscxDtzx.query", params);
	}
}

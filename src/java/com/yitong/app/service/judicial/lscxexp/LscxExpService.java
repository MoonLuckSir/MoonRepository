package com.yitong.app.service.judicial.lscxexp;

import java.util.Map;

import com.yitong.commons.model.IListPage;
import com.yitong.commons.service.BaseService;

public class LscxExpService extends BaseService {

	/**
	 * 查询并分页
	 * 
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("lscxExp.pageQuery", "lscxExp.pageCount",
				params, pageNo);
	}

	public boolean insert(Map params) {
		insert("lscxExp.insert", params);
		return true;
	}

	public boolean updateById(Map params) {
		return update("lscxExp.updateById", params);
	}

	public boolean delete(Map params) {
		return update("lscxExp.delete", params);
	}

	/**
	 * 加载
	 * 
	 * @param params
	 * @return
	 */
	public Map load(String params) {
		return (Map) load("lscxExp.loadById", params);
	}
	public Map getZbxx(String params) {
		return (Map) load("lscxExp.getZbxx", params);
	}
	public Map query(Map params) {
		return (Map) load("lscxExp.query", params);
	}
	
	/**
	 * 得到明细编号  201009240001001 ==== 左边12位是主表编号 + 右边三位是序号
	 * @param date
	 * @return
	 */
	public String getSeq(String zbid){
//			String today = DateUtil.formatDateToStr(date, "yyyy-MM-dd");

		Map map = (Map) this.load("lscxExp.maxIndex", zbid);
		int max = 0; //最大数
		
		String questId ="";
		String num = "";
		if(map!=null){
			if(map.get("MAXINDEX")!=null && !"".equals(map.get("MAXINDEX"))){
				max = Integer.parseInt(map.get("MAXINDEX").toString().trim());
				max = max + 1;
				if(max<10){
				num = "00"+String.valueOf(max);
				}
				else if(max>=10&&max<100){
					num = "0"+String.valueOf(max);
				}			
				else{
					num =  String.valueOf(max);
				}
			}else{
				num =  "001";
			}

			questId = zbid + num;
		}else{
			
		}
		System.err.println("max=="+max);

		

		return questId;
		
	}
}

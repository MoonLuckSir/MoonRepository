package com.yitong.app.service.judicial.lscxhea;

import java.util.Map;

import com.yitong.commons.model.IListPage;
import com.yitong.commons.service.BaseService;

public class LscxHeaService extends BaseService {

	/**
	 * 查询并分页
	 * 
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("lscxHea.pageQuery", "lscxHea.pageCount",
				params, pageNo);
	}

	public boolean insert(Map params) {
		insert("lscxHea.insert", params);
		return true;
	}

	public boolean updateById(Map params) {
		return update("lscxHea.updateById", params);
	}

	public boolean delete(Map params) {
		return update("lscxHea.delete", params);
	}

	/**
	 * 加载
	 * 
	 * @param params
	 * @return
	 */
	public Map load(String params) {
		return (Map) load("lscxHea.loadById", params);
	}
	
	public Map query(Map params) {
		return (Map) load("lscxHea.query", params);
	}
	
	/**
	 * 得到明细编号  201009240001001 ==== 左边12位是主表编号 + 右边三位是序号
	 * @param date
	 * @return
	 */
	public String getSeq(String zbid){

		Map map = (Map) this.load("lscxHea.maxIndex", zbid);
		int max = 0; //最大数
		
		String questId ="";
		String num = "";
		if(map!=null){
			if(map.get("MAXINDEX")!=null && !"".equals(map.get("MAXINDEX"))){
				System.out.println("max = "+map.get("MAXINDEX"));
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


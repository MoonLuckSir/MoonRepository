package com.yitong.app.service.judicial.lscxmb;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.service.BaseService;
import com.yitong.commons.util.DateUtil;

public class LscxMbService extends BaseService {

	/**
	 * 查询并分页
	 * 
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("LscxMb.pageQuery", "LscxMb.pageCount",
				params, pageNo);
	}

	public boolean insert(Map params) {
		insert("LscxMb.insert", params);
		return true;
	}

	public boolean updateById(Map params) {
		return update("LscxMb.updateById", params);
	}

	public boolean delete(Map params) {
		return update("LscxMb.delete", params);
	}

	/**
	 * 加载
	 * 
	 * @param params
	 * @return
	 */
	public Map load(String params) {
		return (Map) load("LscxMb.loadById", params);
	}
	
	public Map query(Map params) {
		return (Map) load("LscxMb.query", params);
	}
	
	/**
	 * 得到主表编号 格式： 201009240001 ==== 左边8位是日期 + 右边四位是序号
	 * @param date
	 * @return
	 */
	public String getSeq(Date date){
		String today = DateUtil.formatDateToStr(date, "yyyy-MM-dd");	
		Map map = (Map) this.load("LscxMb.maxIndex", today);
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
		
//		
//		if(count<10){
//			num = "000"+String.valueOf(count+1);
//		}
//		else if(count>=10&&count<100){
//			num = "00"+String.valueOf(count+1);
//		}
//		else if(count>=100&&count<1000){
//			num = "0"+String.valueOf(count+1);
//		}
//		
//		else{
//			num =  String.valueOf(count+1);
//		}
		return questId;
		
	}
	//测试用的
	public List findSQLList(Map params) {
		return this.findList("lscxDtzx.query", params);
	}
	
	public List findVar(String rst) {
		return this.findList("lscxDtzx.findVar", rst);
	}
	
	public boolean updateByFlag(Map params) {
		return update("LscxMb.updateByFlag", params);
	}
}

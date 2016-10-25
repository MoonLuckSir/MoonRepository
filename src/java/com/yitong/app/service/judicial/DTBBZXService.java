package com.yitong.app.service.judicial;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.poi2.hssf.usermodel.HSSFRichTextString;
import org.apache.poi2.hssf.usermodel.HSSFRow;
import org.apache.poi2.hssf.usermodel.HSSFSheet;
import org.apache.poi2.hssf.usermodel.HSSFWorkbook;

import com.yitong.commons.model.Consts;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.model.inner.ListPage;
import com.yitong.commons.service.BaseService;
import com.yitong.commons.util.DBManager;
import com.yitong.commons.util.DateUtil;
import com.yitong.commons.util.StringUtil;

public class DTBBZXService extends BaseService implements Consts{

	private final int excelPage = 65535;
	
	public List findMbList (Map params) {
		return this.findList("LscxMb.query",params);
	}
	public List findConList (Map params) {
		return this.findList("lscxCon.query1",params);
	}
	public List findRulList (Map params) {
		return this.findList("lscxRul.query",params);
	}
	public List findSQList (Map params) {
		return this.findList("LscxMb.query",params);
	}
	public List findCOLList (Map params) {
		return this.findList("lscxCol.query1",params);
	}
	public List findEXPList (Map params) {
		return this.findList("lscxExp.query1",params);
	}
	//获得申请
	public Map load(String params) {
		return (Map) load("lscxSq.loadById", params);
	}
	
	public IListPage findResuList(Map params, int pageNo,int pageSize) {
		return pageQuery( "lscxDtzx.pageQuery","lscxDtzx.pageCount", params, pageNo,pageSize);
	}
	//200 是每页显示的行数
	public IListPage findResuListOds(Map params, int pageNo,int pageSize) {
		return pageQueryOds( "lscxDtzx.pageQuery","lscxDtzx.pageCount", params, pageNo, pageSize);
	}
	
	public IListPage findResuListBigdata(String REPMOD_NO,List COLList,Map params, int pageNo,int pageSize) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		//关联BP_ORGAN
		if("201211070002".equals(REPMOD_NO)
				||"201303120001".equals(REPMOD_NO) ||"201408060001".equals(REPMOD_NO)
				||"201408060002".equals(REPMOD_NO) ||"201408060003".equals(REPMOD_NO)
				||"201408060004".equals(REPMOD_NO) ){
			return this.pageQueryBigdataByJoin(REPMOD_NO,COLList, params, pageNo, pageSize);
		}if("201211070001".equals(REPMOD_NO)){
			return this.pageQueryBigdataByJoinSFTAB(REPMOD_NO,COLList, params, pageNo, pageSize);
		}//关联BP_ORGAN和ORGAN
		else if("201211070004".equals(REPMOD_NO)||"201211070005".equals(REPMOD_NO)
				||"201211070007".equals(REPMOD_NO) ||"201211070008".equals(REPMOD_NO)){
			return this.pageQueryBigdataByJoin2(REPMOD_NO,COLList, params, pageNo, pageSize);
		}else if("201211070003".equals(REPMOD_NO)){
			return this.pageQueryBigdataSFTAB(COLList, params, pageNo, pageSize);
		}
		//无关联  201211070006   对公活期账户交易明细查询 
		else{
			return this.pageQueryBigdata(COLList, params, pageNo, pageSize);
		}
		
	}
	
	
	public List findResultListBigdata (Map params,List<String> physical) throws SQLException  {
		Connection conn = null;
		ResultSet rsQry_1 = null;
		ResultSet rsQry_2 = null;
		ResultSet rsQry_3 = null;
		Statement stat = null;
		String REP_SQL =(String) params.get("SerSQL");
		String JOIN_SQL =(String) params.get("JOIN_SQL");
		String JOIN_SQL2 =(String) params.get("JOIN_SQL2");
		String REPMOD_NO = (String) params.get("REPMOD_NO");
		String rownum = (String) params.get("rownum");
				
		
		try {
			//conn = DriverManager.getConnection("jdbc:phoenix:10.0.137.84,10.0.137.86,10.0.137.87,10.0.137.88",props);
		 //conn=DBManager.getConn();
			conn = DBManager.getInstance().getConnection();  
		 stat = conn.createStatement();
		
			List<Map> dataList_1 = new ArrayList<Map>();
			List<Map> dataList_2 = new ArrayList<Map>();
			List<Map> dataList_3 = new ArrayList<Map>();
		//关联BP_ORGAN
			if("201211070002".equals(REPMOD_NO)
					||"201303120001".equals(REPMOD_NO) ||"201408060001".equals(REPMOD_NO)
					||"201408060002".equals(REPMOD_NO) ||"201408060003".equals(REPMOD_NO)
					||"201408060004".equals(REPMOD_NO) ){
				 rsQry_1 = stat.executeQuery(REP_SQL);
				 rsQry_2 = stat.executeQuery(JOIN_SQL);
				while(rsQry_1.next()){
					Map<String, String> dataMap = new HashMap<String, String>();
					//循环遍历所有列名，得到每一行所有列值
					for(String keyName : physical){
						dataMap.put(keyName, rsQry_1.getString(keyName));
					}
					
					if("201211070002".equals(REPMOD_NO) ){
						dataMap.put("SFACNO", rsQry_1.getString("SFACNO"));
					}else if("201303120001".equals(REPMOD_NO)){
						dataMap.put("NBZH", rsQry_1.getString("NBZH"));
					}else if("201408060001".equals(REPMOD_NO) || "201408060002".equals(REPMOD_NO)){
						dataMap.put("MDSBNO", rsQry_1.getString("MDSBNO"));
					}else if("201408060003".equals(REPMOD_NO) ){
						dataMap.put("PIOPNT", rsQry_1.getString("PIOPNT"));
					}else if("201408060004".equals(REPMOD_NO) ){
						dataMap.put("EIOPNT", rsQry_1.getString("EIOPNT"));
					}
					// 将得到的一行数据信息保存到list中
					dataList_1.add(dataMap);
				}
				
				Map<String, String> dataMap_2 = new HashMap<String, String>();
				while(rsQry_2.next()){
					dataMap_2.put(rsQry_2.getString("WD_ORGNO"), rsQry_2.getString("WD_ORGNO"));
				}
				
				/*while(rsQry_2.next()){
					Map<String, String> dataMap_2 = new HashMap<String, String>();
					//循环遍历所有列名，得到每一行所有列值
					dataMap_2.put("WD_ORGNO", rsQry_2.getString("WD_ORGNO"));
					
					// 将得到的一行数据信息保存到list中
					dataList_2.add(dataMap_2);
				}*/
				int k = 0;
				for(Map m : dataList_1){
					String JOINSTR = "";
					if("201211070002".equals(REPMOD_NO) ){
						JOINSTR = (String) m.get("SFACNO");
						JOINSTR = JOINSTR.substring(0,10);
					}else if("201303120001".equals(REPMOD_NO)){
						JOINSTR = (String) m.get("NBZH");
						JOINSTR = JOINSTR.substring(0,10);
					}else if("201408060001".equals(REPMOD_NO) || "201408060002".equals(REPMOD_NO)){
						JOINSTR = (String) m.get("MDSBNO");
					}else if("201408060003".equals(REPMOD_NO) ){
						JOINSTR = (String) m.get("PIOPNT");
					}else if("201408060004".equals(REPMOD_NO) ){
						JOINSTR = (String) m.get("EIOPNT");
					}
					String isExist =  dataMap_2.get(JOINSTR);
					if(!"".equals(isExist) && isExist != null){
						k++;
						m.put("RN", k);
						dataList_3.add(m);
					}
					/*for(Map m_2 : dataList_2){
						String WD_ORGNO = (String) m_2.get("WD_ORGNO");
						 if(WD_ORGNO.equals(JOINSTR)){
							dataList_3.add(m);
							break;
						}
					}*/
					
				}
				if(!"".equals(rownum) && rownum != null){
					String [] arr = rownum.split(",");
					List list = new ArrayList();
					for(int a=0;a<arr.length;a++){
						int b = Integer.valueOf(arr[a]);
						Map map = dataList_3.get(b-1);
						   list.add(map);
					}
					return list;
				}
				return dataList_3;
			}else if("201211070001".equals(REPMOD_NO)){
				String[] LIST_SQL = REP_SQL.split(";");
				 rsQry_2 = stat.executeQuery(JOIN_SQL);
				 Map<String, String> dataMap_2 = new HashMap<String, String>();
					while(rsQry_2.next()){
						dataMap_2.put(rsQry_2.getString("WD_ORGNO"), rsQry_2.getString("WD_ORGNO"));
					}
				 for(int i =0 ;i<LIST_SQL.length;i++){
					 System.out.println("LIST_SQL"+ i +"------------>"+LIST_SQL[i]);
					 	rsQry_1 = stat.executeQuery(LIST_SQL[i]);
						while(rsQry_1.next()){
							Map<String, String> dataMap = new HashMap<String, String>();
							//循环遍历所有列名，得到每一行所有列值
							for(String keyName : physical){
								dataMap.put(keyName, rsQry_1.getString(keyName));
							}
							
							if("201211070001".equals(REPMOD_NO)  ){
								dataMap.put("SFACNO", rsQry_1.getString("SFACNO"));
							}
							// 将得到的一行数据信息保存到list中
							dataList_1.add(dataMap);
						}
				 }
				 
					int k = 0;
					for(Map m : dataList_1){
						String JOINSTR = "";
						if("201211070001".equals(REPMOD_NO)){
							JOINSTR = (String) m.get("SFACNO");
							JOINSTR = JOINSTR.substring(0,10);
						}
						String isExist =  dataMap_2.get(JOINSTR);
						if(!"".equals(isExist) && isExist != null){
							k++;
							m.put("RN", k);
							dataList_3.add(m);
						}
					}
				if(!"".equals(rownum) && rownum != null){
					String [] arr = rownum.split(",");
					List list = new ArrayList();
					for(int a=0;a<arr.length;a++){
						int b = Integer.valueOf(arr[a]);
						Map map = dataList_3.get(b-1);
						   list.add(map);
					}
					return list;
				}
				return dataList_3;
			
			}
			//关联BP_ORGAN和ORGAN
			else if("201211070004".equals(REPMOD_NO)||"201211070005".equals(REPMOD_NO)
					||"201211070007".equals(REPMOD_NO) ||"201211070008".equals(REPMOD_NO)){
				 rsQry_1 = stat.executeQuery(REP_SQL);
				 rsQry_2 = stat.executeQuery(JOIN_SQL);
				 rsQry_3 = stat.executeQuery(JOIN_SQL2);
				
				//获取所欲ORGAN表的机构号-机构名称对应关系
				Map<String, String> dataMap_3 = new HashMap<String, String>();
				while(rsQry_3.next()){
					//循环遍历所有列名，得到每一行所有列值
					dataMap_3.put(rsQry_3.getString("ORG_NO"),rsQry_3.getString("ORG_NAME"));
				}
				 while(rsQry_1.next()){
						Map<String, String> dataMap = new HashMap<String, String>();
						//循环遍历所有列名，得到每一行所有列值
						for(String keyName : physical){
							if("ORG_NAME".equalsIgnoreCase(keyName)){
								if("201211070004".equals(REPMOD_NO) ||"201211070005".equals(REPMOD_NO) ){
									dataMap.put(keyName, dataMap_3.get(rsQry_1.getString("MSSBNO")));
								}else if("201211070007".equals(REPMOD_NO) ||"201211070008".equals(REPMOD_NO) ){
									dataMap.put(keyName, dataMap_3.get(rsQry_1.getString("MCSBNO")));
								}
							}else{
								dataMap.put(keyName, rsQry_1.getString(keyName));
							}
							
						}
						if("201211070005".equals(REPMOD_NO)){
							dataMap.put("PARAMS", rsQry_1.getString("PARAMS"));
							dataMap.put("MSITCD", rsQry_1.getString("MSITCD"));
						}
						if("201211070008".equals(REPMOD_NO)){
							dataMap.put("MCSBNO", rsQry_1.getString("MCSBNO"));
							dataMap.put("PARAMS", rsQry_1.getString("MCACCN"));
						}
						
						// 将得到的一行数据信息保存到list中
						dataList_1.add(dataMap);
					}
				 
					
					Map<String, String> dataMap_2 = new HashMap<String, String>();
					while(rsQry_2.next()){
						dataMap_2.put(rsQry_2.getString("WD_ORGNO"), rsQry_2.getString("WD_ORGNO"));
					}
					
					/*while(rsQry_2.next()){
						Map<String, String> dataMap_2 = new HashMap<String, String>();
						//循环遍历所有列名，得到每一行所有列值
						dataMap_2.put("WD_ORGNO", rsQry_2.getString("WD_ORGNO"));
						
						// 将得到的一行数据信息保存到list中
						dataList_2.add(dataMap_2);
					}*/
					int k = 0;
					for(Map m : dataList_1){
						
						String JOINSTR = "";
						if("201211070004".equals(REPMOD_NO) ||"201211070005".equals(REPMOD_NO) ){
							JOINSTR = (String) m.get("MSSBNO");
						}else if("201211070007".equals(REPMOD_NO) ||"201211070008".equals(REPMOD_NO) ){
							JOINSTR = (String) m.get("MCSBNO");
						}
						String isExist =  dataMap_2.get(JOINSTR);
						if(!"".equals(isExist) && isExist != null){
							k++;
							m.put("RN", k);
							dataList_3.add(m);
						}
						/*for(Map m_2 : dataList_2){
							String WD_ORGNO = (String) m_2.get("WD_ORGNO");
							 if(WD_ORGNO.equals(JOINSTR)){
								dataList_3.add(m);
								break;
							}
						}*/
					}
					if(!"".equals(rownum) && rownum != null){
						String [] arr = rownum.split(",");
						List list = new ArrayList();
						for(int a=0;a<arr.length;a++){
							int b = Integer.valueOf(arr[a]);
							Map map = dataList_3.get(b-1);
							   list.add(map);
						}
						return list;
					}
					return dataList_3;
			}//无关联 201211070003 对私活期账户交易明细查询 201211070006   对公活期账户交易明细查询 
			else{
				String[] LIST_SQL = REP_SQL.split(";");
				int k = 0;
				 for(int i =0 ;i<LIST_SQL.length;i++){
					 System.out.println("LIST_SQL"+ i +"------------>"+LIST_SQL[i]);
					 	rsQry_1 = stat.executeQuery(LIST_SQL[i]);
					 	 
						while(rsQry_1.next()){
							Map dataMap = new HashMap();
							//循环遍历所有列名，得到每一行所有列值
							for(String keyName : physical){
								dataMap.put(keyName, rsQry_1.getString(keyName));
							}
							
							k++;
							dataMap.put("RN", k);
							//追加字段
							dataMap.put("SFTRDT", rsQry_1.getString("SFTRDT"));
							dataMap.put("SFDFNM", rsQry_1.getString("SFDFNM"));
							dataMap.put("SFDFZH", rsQry_1.getString("SFDFZH"));
							dataMap.put("SFDFJG", rsQry_1.getString("SFDFJG"));
							dataMap.put("SFCATR", rsQry_1.getString("SFCATR"));
							dataMap.put("SFAMCD", rsQry_1.getString("SFAMCD"));
							dataMap.put("SFTRAM", rsQry_1.getString("SFTRAM"));
							dataMap.put("SFACNO", rsQry_1.getString("SFACNO"));
							if("201211070006".equals(REPMOD_NO)){
								dataMap.put("SFPKFG", rsQry_1.getString("SFPKFG"));
							}
						
							// 将得到的一行数据信息保存到list中
							dataList_3.add(dataMap);
						}
				 }
				
				 return dataList_3;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList();
		}finally{
			if(rsQry_1!= null){
				rsQry_1.close();
			}
			if(rsQry_2 != null){
				rsQry_2.close();
			}
			if(rsQry_3 != null){
				rsQry_3.close();
			}
			if(stat !=null){
				stat.close();
			}
			DBManager.closeConn(conn,stat);
		}
		
	
		
	}

	
	public IListPage reppageQuery(Map params, int pageNo) {
		return this.pageQuery("LscxMb.pageQuery", "LscxMb.pageCount",
				params, pageNo);
	}
	public List findHeaList(Map params) {
		return this.findList("lscxHea.query1", params);
	}
	public Map getConVal(Map params) {
		return (Map) load("lscxDtzx.query", params);
	}
	public boolean insertLog(Map params) {
		insert("lscxLog.insert", params);
		return true;
	}
	public Map findMbMap (Map params) {
		return (Map)load("LscxMb.query",params);
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
	public boolean insert(Map params) {
		insert("lscxSq.insert", params);
		return true;
	}
	public List findResultListOds (Map params) {
		return this.findListOds("lscxDtzx.query",params);
	}
	
	
	public List findResultList (Map params) {
		return this.findList("lscxDtzx.query",params);
	}
	public Map findLogMap (Map params) {
		return (Map)load("lscxLog.query",params);
	}
	
	public List findTable(Map params) {
		return this.findListOds("lscxHea.findTable", params);
	}
	
	// 拼html
	public String getQureyHtml(List ConList, String LB,
			HttpServletRequest request) {
		// 设置条件
		StringBuffer html = new StringBuffer();
		// 额外约束
		StringBuffer rule = new StringBuffer();
		// 页面控件
		StringBuffer object = new StringBuffer();

		// 隐藏组件
		StringBuffer hidden = new StringBuffer();
		StringBuffer hiddenjs = new StringBuffer();

		// 准备数据集
		int NumCon = ConList.size();
		Map aHs = new HashMap();
		for (int i = 0; i < NumCon; i++) {
			Map ConMap = (Map) ConList.get(i);
			aHs.put(ConMap.get("CONMX_NO"), ConMap.get("CONMX_NAME"));
		}
		String dateId = "";
		for (int i = 0; i < NumCon; i++) {
			Map ConMap = (Map) ConList.get(i);
			// 条件编号
			String CONMX_NO = (String) ConMap.get("CONMX_NO");
			// 条件名称
			String ConName = (String) ConMap.get("CONMX_NAME");
			// 条件ID
			String CONID = "M" + CONMX_NO;
			// 行ID
			String CLMID = "H" + CONMX_NO;
			CONID = CONID.trim();
			// 输入框类型 输入框，下拉框组件，选择组件
			String INPUT_TYPE = (String) ConMap.get("INPUT_TYPE");
			// 日期，机构，账号
			String INPUT_VALUE = (String) ConMap.get("INPUT_VALUE");
			// 数据类型 字符串，日期，数字
			String CONMX_TYPE = (String) ConMap.get("CONMX_TYPE");
			// 组件涉及列
			String ZJCONS = (String) ConMap.get("ZJCONS");
			// 默认值
			String DEFAULT = "";
			if (ConMap.get("DEFAULTVAL") != null)
				DEFAULT = (String) ConMap.get("DEFAULTVAL");
			// 翻译特殊默认值
			if (DEFAULT == null)
				DEFAULT = "";
			DEFAULT = getDEFAULT(DEFAULT, request);
			// 是否需要做合法性认证； 这个只针对 输入框".equals(INPUT_TYPE）时生效
			String ISCHECK = "";
			if (ConMap.get("ISCHECK") != null)
				ISCHECK = (String) ConMap.get("ISCHECK");
			// 加必输 红色星号
			String ISNOTNULL = "";
			if (ConMap.get("ISNOTNULL") != null)
				ISNOTNULL = ConMap.get("ISNOTNULL").toString();
			
			String param = request.getParameter("params");
			// 获得隐藏组件HTML
			if ("隐藏组件".equals(INPUT_TYPE)) {
				//gethidden(CONID, ConName, ZJCONS, hidden, hiddenjs, ConList);
				html.append(" <input type='hidden' name='");
				html.append(CONID);
				if(param != null){
					html.append("' value='");
					html.append(param);
				}
				html.append("' />");
				continue;
			}
			html.append(" <tr id ='" + CLMID + "'>");
			html.append(" <td width='40%' height='30' class='detail_label'>");
			html.append(ConName);
			html.append(":");
			html.append(" </td>");
			html.append(" <td width='60%' class='detail_content'>");
			if ("输入框".equals(INPUT_TYPE)) {
				html.append(" <input name='");
				html.append(CONID);
				html.append("'");
				html.append("style='width:260px' type='text' label='");
				html.append(ConName);
				html.append("'");
				if (CONMX_TYPE.equals("字符串"))
					html.append(" pattern='string' ");
				if (CONMX_TYPE.equals("日期"))
					html.append(" pattern='date' ");
				if (CONMX_TYPE.equals("数字")) {
					html.append(" pattern='money' ");
					html.append(" left='25' ");
					html.append(" right='4' ");

				}
				if (LB.equals("readonly")) {
					html.append(" readonly ");
				}

				if (ISCHECK.equals("是")) {
					html.append("  onblur='check" + CONID + "(this);' ");
				}

				html.append(" >");

			}
			if ("文本框".equals(INPUT_TYPE)) {					
				html.append(" <textarea rows='4' name='");
				html.append(CONID);
				html.append("' class='textArea' style='width:260px' type='text' label='");
				html.append(ConName);
				html.append("'></textarea>");
			}
			
			if ("选择组件".equals(INPUT_TYPE)) {
				// 第一个组件
				if ("日期".equals(INPUT_VALUE)) {
					html.append(" <input name='");
					html.append(CONID);
					html.append("'");
					html.append(" onclick='SelectDate(this)'");
					html.append(" id='");
					html.append(CONID);
					html.append("'");
					html.append("style='width:260px' type='text' label='");
					html.append(ConName);
					html.append("'");
					html.append(" pattern='date' ");
					html.append(" readonly ");
					html.append(">");
					if (!LB.equals("readonly")) {
						html.append(" <input type='button' class='btnDate2'");
						html.append(" onclick='SelectDate3(form.");
						html.append(CONID);
						html.append(",this)'>");
					}
					// du添加日期必须相同判断
					if ("结束日期".equals(ConName)) {
						request.setAttribute("endDateId", CONID);
					}
					if ("截止日期".equals(ConName)) {
						request.setAttribute("JZid", CONID);
					}

					if ("开始日期".equals(ConName)) {
						request.setAttribute("StartDateId", CONID);
					}
					dateId = dateId+","+CONID;
				}

				// 第二个组件
				if ("客户账号".equals(INPUT_VALUE)) {
					String[] aZJCONS = StringUtil.toArr(ZJCONS);
					// ZJCONS 这个值一定要在页面控制好
					// 组件参数设置
					// SQL
					String SQLID = "0003";
					// 要赋值的列
					StringBuffer tempSB = new StringBuffer();
					for (int k = 0; k < aZJCONS.length; k++) {
						String NBZH = "M" + aZJCONS[k];
						tempSB.append(NBZH);
						if (k != aZJCONS.length - 1)
							tempSB.append(",");

					}
					String COLS = tempSB.toString();
					// 对应页面的字段
					String CONDITIONCOL = CONID;
					// 函数名
					String FUNNAME = "khzh";
					html.append("<input name='");
					html.append(CONID);
					html.append("'");
					html.append("style='width:260px' type='text' label='");
					html.append(ConName);
					html.append("'");
					html.append(" pattern='String'  onblur='toset" + FUNNAME
							+ "();' ");
					if (LB.equals("readonly")) {
						html.append(" readonly ");
					}
					html.append(" >");
					object.append(setValue(SQLID, COLS, CONDITIONCOL, FUNNAME,
							"请选择账号！"));
					// 这里加规则
					if (!LB.equals("readonly")) {
						html.append(" <input type='button'  value='..' ");
						html.append(" id='");
						html.append(FUNNAME);
						html.append("'");
						html.append("	onclick='sel" + FUNNAME + "()'>");
						// 增加选取的方法
						object.append(selValue(SQLID, COLS, CONDITIONCOL,
								FUNNAME));
					}
				}

				// 第三个组件
				if ("证件号".equals(INPUT_VALUE)) {
					String[] aZJCONS = StringUtil.toArr(ZJCONS);
					// 组件参数设置
					// SQLID
					String SQLID = "0004";
					// 要赋值的列
					StringBuffer tempSB = new StringBuffer();
					for (int k = 0; k < aZJCONS.length; k++) {
						String NBZH = "M" + aZJCONS[k];
						tempSB.append(NBZH);
						if (k != aZJCONS.length - 1)
							tempSB.append(",");

					}
					String COLS = tempSB.toString();
					String CONDITIONCOL = CONID;
					// 默认条件
					//String DEFCONDITION = "1=1";
					// 函数名
					String FUNNAME = "zjh";
					html.append("<input name='");
					html.append(CONID);
					html.append("'");
					html.append("style='width:260px' type='text' label='");
					html.append(ConName);
					html.append("'");
					html.append(" pattern='String' onblur='toset" + FUNNAME
							+ "();'");
					object.append(setValue(SQLID, COLS, CONDITIONCOL, FUNNAME,
							"请选择账号！"));
					if (LB.equals("readonly")) {
						html.append(" readonly ");
					}
					html.append(" >");
					// 这里加规则
					if (!LB.equals("readonly")) {
						html.append(" <input type='button'  value='..' ");
						html.append(" id='");
						html.append(FUNNAME);
						html.append("'");
						html.append("	onclick='sel" + FUNNAME + "()'>");
						object.append(selValue(SQLID, COLS, CONDITIONCOL,
								FUNNAME));

					}
				}
				// 第四个组件
				if ("客户号".equals(INPUT_VALUE)) {
					String[] aZJCONS = StringUtil.toArr(ZJCONS);
					// 组件参数设置
					// SQLID
					String SQLID = "0002";
					// 要赋值的列
					StringBuffer tempSB = new StringBuffer();
					for (int k = 0; k < aZJCONS.length; k++) {
						String NBZH = "M" + aZJCONS[k];
						tempSB.append(NBZH);
						if (k != aZJCONS.length - 1)
							tempSB.append(",");

					}
					String COLS = tempSB.toString();
					String CONDITIONCOL = CONID;
					// 默认条件
					//String DEFCONDITION = "1=1";
					// 函数名
					String FUNNAME = "khh";
					html.append("<input name='");
					html.append(CONID);
					html.append("'");
					html.append("style='width:260px' type='text' label='");
					html.append(ConName);
					html.append("'");
					html.append(" pattern='String' onblur='toset" + FUNNAME
							+ "();'");
					object.append(setValue(SQLID, COLS, CONDITIONCOL, FUNNAME,
							"请选择账号！"));
					if (LB.equals("readonly")) {
						html.append(" readonly ");
					}
					html.append(" >");
					// 这里加规则
					if (!LB.equals("readonly")) {
						html.append(" <input type='button'  value='..' ");
						html.append(" id='");
						html.append(FUNNAME);
						html.append("'");
						html.append("	onclick='sel" + FUNNAME + "()'>");
						object.append(selValue(SQLID, COLS, CONDITIONCOL,
								FUNNAME));

					}
				}

				// 第四个机构组件
				if ("机构".equals(INPUT_VALUE)) {
					String orgNo = request.getParameter("orgNo");
					String SQL = "select  ORG_NAME as CONVAL  from  YTPUB.ORGAN  where ORG_NO='"
							+ orgNo + "'";
					String orgName = getPubStr(SQL);
					if (!LB.equals("readonly")) {
						html.append(" <input name='N"
										+ CONID
										+ "' readonly type='text'  style='width:260px;' onclick=\"select_orgPar(this,'N"
										+ CONID + "','" + CONID
										+ "')\" value='" + orgNo + " | "
										+ orgName + "'> ");
						html.append(" <input type='button' class='btnOrg2'  onclick=\"select_orgPar(this,'N"
										+ CONID
										+ "','"
										+ CONID
										+ "')\" value='..'>");
					} else {
						html.append(" <input name='N"
										+ CONID
										+ "'  readonly type='text'  style='width:260px;'  value='"
										+ orgNo + "|" + orgName + "'> ");
					}
					html.append(" <input name='" + CONID
							+ "' type='hidden' value='" + orgNo + "'>");
				}
				// 第五个机构组件 个人借据号选取
				if ("个人借据号选取".equals(INPUT_VALUE)) {
					String[] aZJCONS = StringUtil.toArr(ZJCONS);
					// 组件参数设置
					// SQLID
					String SQLID = "0005";
					// 要赋值的列
					StringBuffer tempSB = new StringBuffer();
					for (int k = 0; k < aZJCONS.length; k++) {
						String NBZH = "M" + aZJCONS[k];
						tempSB.append(NBZH);
						if (k != aZJCONS.length - 1)
							tempSB.append(",");

					}
					String COLS = tempSB.toString();
					String CONDITIONCOL = CONID;
					// 默认条件
					//String DEFCONDITION = "1=1";
					// 函数名
					String FUNNAME = "grjhh";
					html.append("<input name='");
					html.append(CONID);
					html.append("'");
					html.append("style='width:260px' type='text' label='");
					html.append(ConName);
					html.append("'");
					html.append(" pattern='String' onblur='toset" + FUNNAME
							+ "();'");
					object.append(setValue(SQLID, COLS, CONDITIONCOL, FUNNAME,
							"请选择借据号！"));
					if (LB.equals("readonly")) {
						html.append(" readonly ");
					}
					html.append(" >");
					// 这里加规则
					if (!LB.equals("readonly")) {
						html.append(" <input type='button'  value='..' ");
						html.append(" id='");
						html.append(FUNNAME);
						html.append("'");
						html.append("	onclick='sel" + FUNNAME + "()'>");
						object.append(selValue(SQLID, COLS, CONDITIONCOL,
								FUNNAME));

					}
				}
				// 第六个机构组件 对公借据号选取
				if ("对公借据号选取".equals(INPUT_VALUE)) {
					String[] aZJCONS = StringUtil.toArr(ZJCONS);
					// 组件参数设置
					// SQLID
					String SQLID = "0006";
					// 要赋值的列
					StringBuffer tempSB = new StringBuffer();
					for (int k = 0; k < aZJCONS.length; k++) {
						String NBZH = "M" + aZJCONS[k];
						tempSB.append(NBZH);
						if (k != aZJCONS.length - 1)
							tempSB.append(",");

					}
					String COLS = tempSB.toString();
					String CONDITIONCOL = CONID;
					// 默认条件
					//String DEFCONDITION = "1=1";
					// 函数名
					String FUNNAME = "dgjhh";
					html.append("<input name='");
					html.append(CONID);
					html.append("'");
					html.append("style='width:260px' type='text' label='");
					html.append(ConName);
					html.append("'");
					html.append(" pattern='String' onblur='toset" + FUNNAME
							+ "();'");
					object.append(setValue(SQLID, COLS, CONDITIONCOL, FUNNAME,
							"请选择借据号！"));
					if (LB.equals("readonly")) {
						html.append(" readonly ");
					}
					html.append(" >");
					// 这里加规则
					if (!LB.equals("readonly")) {
						html.append(" <input type='button'  value='..' ");
						html.append(" id='");
						html.append(FUNNAME);
						html.append("'");
						html.append("	onclick='sel" + FUNNAME + "()'>");
						object.append(selValue(SQLID, COLS, CONDITIONCOL,
								FUNNAME));

					}
				}
				// 第七个机构组件 个人欠息选取
				if ("个人欠息选取".equals(INPUT_VALUE)) {
					String[] aZJCONS = StringUtil.toArr(ZJCONS);
					// 组件参数设置
					// SQLID
					String SQLID = "0007";
					// 要赋值的列
					StringBuffer tempSB = new StringBuffer();
					for (int k = 0; k < aZJCONS.length; k++) {
						String NBZH = "M" + aZJCONS[k];
						tempSB.append(NBZH);
						if (k != aZJCONS.length - 1)
							tempSB.append(",");

					}
					String COLS = tempSB.toString();
					String CONDITIONCOL = CONID;
					// 默认条件
					//String DEFCONDITION = "1=1";
					// 函数名
					String FUNNAME = "grqxxq";
					html.append("<input name='");
					html.append(CONID);
					html.append("'");
					html.append(" type='text' label='");
					html.append(ConName);
					html.append("'");
					html.append(" pattern='String' onblur='toset" + FUNNAME
							+ "();'");
					object.append(setValue(SQLID, COLS, CONDITIONCOL, FUNNAME,
							"请选择借据号！"));
					if (LB.equals("readonly")) {
						html.append(" readonly ");
					}
					html.append(" >");
					// 这里加规则
					if (!LB.equals("readonly")) {
						html.append(" <input type='button'  value='..' ");
						html.append(" id='");
						html.append(FUNNAME);
						html.append("'");
						html.append("	onclick='sel" + FUNNAME + "()'>");
						object.append(selValue(SQLID, COLS, CONDITIONCOL,
								FUNNAME));

					}
				}
				// 第七个机构组件 对公欠息选取
				if ("对公欠息选取".equals(INPUT_VALUE)) {
					String[] aZJCONS = StringUtil.toArr(ZJCONS);
					// 组件参数设置
					// SQLID
					String SQLID = "0008";
					// 要赋值的列
					StringBuffer tempSB = new StringBuffer();
					for (int k = 0; k < aZJCONS.length; k++) {
						String NBZH = "M" + aZJCONS[k];
						tempSB.append(NBZH);
						if (k != aZJCONS.length - 1)
							tempSB.append(",");

					}
					String COLS = tempSB.toString();
					String CONDITIONCOL = CONID;
					// 默认条件
					//String DEFCONDITION = "1=1";
					// 函数名
					String FUNNAME = "dgqxxq";
					html.append("<input name='");
					html.append(CONID);
					html.append("'");
					html.append(" type='text' label='");
					html.append(ConName);
					html.append("'");
					html.append(" pattern='String' onblur='toset" + FUNNAME
							+ "();'");
					object.append(setValue(SQLID, COLS, CONDITIONCOL, FUNNAME,
							"请选择借据号！"));
					if (LB.equals("readonly")) {
						html.append(" readonly ");
					}
					html.append(" >");
					// 这里加规则
					if (!LB.equals("readonly")) {
						html.append(" <input type='button'  value='..' ");
						html.append(" id='");
						html.append(FUNNAME);
						html.append("'");
						html.append("	onclick='sel" + FUNNAME + "()'>");
						object.append(selValue(SQLID, COLS, CONDITIONCOL,
								FUNNAME));

					}
				}

			}
			if ("回显框".equals(INPUT_TYPE)) {
				html.append(" <input name='");
				html.append(CONID);
				html.append("'");
				html.append("style='width:260px' type='text' label='");
				html.append(ConName);
				html.append("'");
				html.append(" readonly ");
				html
						.append(" style='border-bottom: 1px;border-left: 1px; border-top: 1px;border-right: 1px'  ");
				if (!DEFAULT.equals("")) {
					html.append(" value='");
					html.append(DEFAULT);
					html.append("'");
				}

				html.append(" >");
			}

			if ("下拉框组件".equals(INPUT_TYPE)) {
				if ("币种".equals(INPUT_VALUE)) {

					if (LB.equals("readonly")) {
						html.append(" <input name='");
						html.append(CONID);
						html.append("'");
						html.append("style='width:260px' type='text' label='");
						html.append(ConName);
						html.append("'");
						html.append(" readonly ");
						html.append(" >");
					} else {

						html.append(" <select name='");
						html.append(CONID);
						html.append("'");
						html.append("  style='width:155px' >");
						String SQL = "";
						if (DBTYPE.equals("db2"))
							SQL = "select FLDIDX ID,FLDVAL VAL from pub.sop_mir_pklst where fldnam='HUOBDH'";
						if (DBTYPE.equals("orc"))
							SQL = "select FLDIDX ID,FLDVAL VAL from pub.sop_mir_pklst where fldnam='HUOBDH'";
						html.append(getOPTIONS(SQL, "1"));

						html.append(" </select>");
					}
				}

				if ("总账类型".equals(INPUT_VALUE)) {

					if (LB.equals("readonly")) {
						html.append(" <input name='");
						html.append(CONID);
						html.append("'");
						html.append("style='width:260px' type='text' label='");
						html.append(ConName);
						html.append("'");
						html.append(" readonly ");
						html.append(" >");
					} else {
						html.append(" <select name='");
						html.append(CONID);
						html.append("'");
						html.append("  style='width:155px' >");
						String SQL = "";
						if (DBTYPE.equals("db2"))
							SQL = "select cycno ID,cycnanme VAL from pub.data_cyc where cyctype ='ZNGZLX'";
						if (DBTYPE.equals("orc"))
							SQL = "select cycno ID,cycnanme VAL from pub.data_cyc where cyctype ='ZNGZLX'";
						html.append(getOPTIONS(SQL, "1"));
						html.append(" </select>");
					}
				}

			}
			if (ISNOTNULL != null && ISNOTNULL.equals("是")) {
				html.append("<FONT color='red'>*</FONT>");
			}
			html.append(" </td>");
			html.append(" </tr>");

			// 判断是否有分行线
			String ISFGF = "";
			if (ConMap.get("ISFGF") != null)
				ISFGF = ConMap.get("ISFGF").toString();
			if (ISFGF != null && ISFGF.equals("是")) {
				html.append(" <tr>");
				html.append(" <td colspan='2'><BR></td>");
				html.append(" </tr>");
			}

			// 这里加规则
			Map params = new HashMap();
			params.put("CONMX_NO", CONMX_NO);
			List RulList = this.findRulList(params);
			if (RulList != null) {
				int Numrul = RulList.size();
				for (int j = 0; j < Numrul; j++) {
					Map RulMap = (Map) RulList.get(j);
					// 必输,最大值,最小值,最大长度，最小长度,关联日期到，关联日期从,N选一
					String ruleName = (String) RulMap.get("RULE_NAME");
					String ruleValue = (String) RulMap.get("RULE_VALUE");

					if ("必输".equals(ruleName)) {
						String temInPut = " form." + CONID;
						rule.append(" if (");
						rule.append(temInPut);
						rule.append(".value==''){showErrorMsg('");
						rule.append(ConName);
						rule.append(" 必须要有数据！'); ");
						rule.append("   return false;}");

					}
					if ("最大值".equals(ruleName)) {
						if ("数字".equals(CONMX_TYPE)) {
							String temInPut = " form." + CONID;
							rule.append(" if (");
							rule.append(temInPut);
							rule.append(".value > " + ruleValue
									+ " ){showErrorMsg('");
							rule.append(ConName);
							rule.append(" 不能超过最大值:" + ruleValue + "！'); ");
							rule.append("   return false;}");
						}
						if ("日期".equals(CONMX_TYPE)) {

							if ("today".equals(ruleValue)) {
								Date date = DateUtil.getDate();
								ruleValue = DateUtil.formatDateToStr(date,
										"yyyy-MM-dd");
							}
							String temInPut = " form." + CONID;
							rule.append(" if (");
							rule.append(temInPut);
							rule.append(".value >'" + ruleValue
									+ "' ){showErrorMsg('");
							rule.append(ConName);
							rule.append(" 不能超过最大值:" + ruleValue + "！'); ");
							rule.append("   return false;}");
						}

					}
					if ("最小值".equals(ruleName)) {
						if ("数字".equals(CONMX_TYPE)) {
							String temInPut = " form." + CONID;
							String tempdate = "par" + CONID;

							rule.append(" var " + tempdate + "=");
							rule.append(temInPut);
							rule.append(".value ;");

							rule.append(tempdate + "=");
							rule.append(tempdate);
							rule.append(".replace('-','');");
							rule.append(tempdate + "=");
							rule.append(tempdate);
							rule.append(".replace('-','');");

							rule.append(" if ( " + temInPut + ".value!=''&&"
									+ tempdate + "< " + ruleValue
									+ " ){showErrorMsg('");
							rule.append(ConName);
							rule.append(" 不能于最小值:" + ruleValue + "！'); ");
							rule.append("   return false;}");
						}
						if ("日期".equals(CONMX_TYPE)) {

							if ("today".equals(ruleValue)) {
								Date date = DateUtil.getDate();
								ruleValue = DateUtil.formatDateToStr(date,
										"yyyy-MM-dd");
							}
							String temInPut = " form." + CONID;
							rule.append(" if (");
							rule.append(temInPut);
							rule.append(".value!=''&&");
							rule.append(temInPut);
							rule.append(".value < '" + ruleValue
									+ "' ){showErrorMsg('");
							rule.append(ConName);
							rule.append(" 不能小于最小值:" + ruleValue + "！'); ");
							rule.append("   return false;}");
						}
					}

					if ("最大长度".equals(ruleName)) {
						if ("字符串".equals(CONMX_TYPE)) {
							String temInPut = " form." + CONID + ".value";
							rule.append(" if (");
							rule.append(temInPut);
							rule.append(".length > " + ruleValue
									+ " ){showErrorMsg('");
							rule.append(ConName);
							rule.append(" 不能超过最大长度:" + ruleValue + "！'); ");
							rule.append("   return false;}");
						}

					}
					if ("最小长度".equals(ruleName)) {
						if ("字符串".equals(CONMX_TYPE)) {
							String temInPut = " form." + CONID + ".value";
							rule.append(" if (");
							rule.append(temInPut);
							rule.append(".value!=''&&");
							rule.append(temInPut);
							rule.append(".length < " + ruleValue
									+ " ){showErrorMsg('");
							rule.append(ConName);
							rule.append(" 不能小于最小长度:" + ruleValue + "！'); ");
							rule.append("   return false;}");
						}
					}

					// 这个要额外来写校验的
					if ("N选一".equals(ruleName)) {
						if (ruleValue != null && !ruleValue.equals("")) {
							rule.append(" if(");
							StringBuffer abuff = new StringBuffer();
							String[] len = StringUtil.toArr(ruleValue);
							for (int k = 0; k < len.length; k++) {
								rule.append(" form.M");
								rule.append(len[k]);
								rule.append(".value==''");
								abuff.append(aHs.get(len[k]));
								if (k != len.length - 1) {
									rule.append("&&");
									abuff.append(",");
								}

							}
							rule.append("){showErrorMsg('请在 ");
							rule.append(abuff.toString());
							rule.append(" 中必须有一项要有数据！');return false;}");
						}

					}
					// 这个要额外来写校验的
					if ("日期不容许跨年查询".equals(ruleName)) {
						if (ruleValue != null && !ruleValue.equals("")) {

							String[] len = StringUtil.toArr(ruleValue);
							for (int k = 0; k < len.length; k++) {
								if (k == 0) {
									rule.append(" a=form.M");
									rule.append(len[k]);
									rule.append(".value;");
								}
								if (k == 1) {
									rule.append(" b=form.M");
									rule.append(len[k]);
									rule.append(".value;");
								}

							}
							rule.append("if(a!=''&&b!='') ");
							rule.append("{ ");
							rule.append(" a=a.substring(0,4); ");
							rule.append(" b=b.substring(0,4); ");
							rule.append(" if(a!=b) { ");
							rule.append("    showErrorMsg('日期年份不容许跨年查询！');");
							rule.append("   return false; ");
							rule.append("  } ");
							rule.append(" } ");

						}

					}
					// 这个要额外来写校验的
					if ("日期跨度不超一个月".equals(ruleName)) {
						if (ruleValue != null && !ruleValue.equals("")) {

							String[] len = StringUtil.toArr(ruleValue);
							for (int k = 0; k < len.length; k++) {
								if (k == 0) {
									rule.append(" var c=form.M");
									rule.append(len[k]);
									rule.append(".value;");
								}
								if (k == 1) {
									rule.append(" var d=form.M");
									rule.append(len[k]);
									rule.append(".value;");
								}

							}
							rule.append("if(c!=''&&d!='') ");
							rule.append("{ ");
							rule.append(" c=c.replace('-',''); ");
							rule.append(" c=c.replace('-',''); ");
							rule.append(" d=d.replace('-',''); ");
							rule.append(" d=d.replace('-',''); ");
							rule.append(" if((parseInt(c)+100)<parseInt(d)) ");
							rule.append("      { ");
							rule.append("    showErrorMsg('当前时间跨度超过一个月！');");
							rule.append("   return false; ");
							rule.append("  } ");
							rule.append(" } ");

						}

					}
					if ("值合法性".equals(ruleName)) {

						object.append("   function check").append(CONID).append("(obj){ ");
						object.append("    var sql=\"").append(ruleValue).append("\".replace(\"")
						      .append(CONMX_NO).append("\",obj.value); ");
						object.append("  docheck(sql,obj,'");
						object.append(ConName);
						object.append("'); ");
						object.append("  } ");
					}

					if ("关联选择".equals(ruleName)) {
						if (ruleValue != null && !ruleValue.equals("")) {
							rule.append(" if(");
							StringBuffer abuff = new StringBuffer();
							String[] len = StringUtil.toArr(ruleValue);
							for (int k = 0; k < len.length; k++) {
								rule.append(" form.M");
								rule.append(len[k]);
								rule.append(".value!=''");
								if (k != len.length - 1) {
									rule.append("||");
								}

							}
							rule.append("){");
							rule.append(" if(");
							for (int k = 0; k < len.length; k++) {
								rule.append(" form.M");
								rule.append(len[k]);
								rule.append(".value==''");
								abuff.append(aHs.get(len[k]));
								if (k != len.length - 1) {
									rule.append("||");
									abuff.append(",");
								}

							}
							rule.append("){");
							rule.append("showErrorMsg('");
							rule.append(abuff.toString());
							rule.append(" 必须同时有数据！');return false;");
							rule.append("}}");
						}

					}

				}
			}

		}
		request.setAttribute("dateId",dateId);
		request.setAttribute("Conditions", html.toString());
		request.setAttribute("object", object.toString());
		request.setAttribute("JSRULE", rule.toString());
		request.setAttribute("hidden", hidden.toString());
		request.setAttribute("hiddenjs", hiddenjs.toString());
		return html.toString();
	}
	
	private String getDEFAULT(String DEFAULT, HttpServletRequest request) {
		if (DEFAULT.equals(""))
			return DEFAULT;
		if (DEFAULT.toUpperCase().equals("TODAY")) {
			Date date = DateUtil.getDate();
			DEFAULT = DateUtil.formatDateToStr(date, "yyyy-MM-dd");
		}
		if (DEFAULT.toUpperCase().equals("ORGAN")) {
			DEFAULT = request.getParameter("orgNo");

		}
		if (DEFAULT.toUpperCase().equals("USER")) {
			DEFAULT = request.getParameter("usrId");
		}
		return DEFAULT;
	}
	
	private String selValue(String SQLID, String COLS, String CONDITION,
			String FUNNAME) {
		StringBuffer object = new StringBuffer();
		// 增加选取的方法
		object.append("function sel").append(FUNNAME).append("(){");
		// 结果集sql
		object.append(" var selid='").append(SQLID).append("'; ");
		// 要赋值的字段
		object.append(" var returncol='").append(COLS).append("';");
		// 条件字段
		object.append("  var conditioncol='").append(CONDITION).append("'; ");
		object.append("  var checkflag='1'; ");
		object.append("  selcon(selid,returncol,conditioncol,checkflag);}");
		return object.toString();
	}
	
	// 拼SETVALUE函数
	private String setValue(String SQLID, String COLS, String CONDITION,
			String FUNNAME, String MSG) {
		StringBuffer object = new StringBuffer();
		object.append("function toset").append(FUNNAME).append("(){");
		// 结果集sql
		object.append(" var selid='").append(SQLID).append("'; ");
		// 要赋值的字段
		object.append(" var returncol='").append(COLS).append("';");
		// 条件字段
		object.append("  var conditioncol='").append(CONDITION).append("'; ");
		object.append("  tosetvalue(selid,returncol,conditioncol,'")
			  .append(FUNNAME).append( "','").append(MSG).append("');}");
		return object.toString();
	}
	
	private String getPubStr(String SQL) {
		Map params = new HashMap();
		params.clear();
		params.put("SerSQL", SQL);
		Map resultList = this.getConVal(params);
		String returnStr = "";
		if (resultList != null && resultList.get("CONVAL") != null)
			returnStr = resultList.get("CONVAL").toString();
		return returnStr;
	}
	
	private String getOPTIONS(String SQL, String CanNull) {
		Map params = new HashMap();
		params.clear();
		params.put("SerSQL", SQL);
		List resultList = this.findResultList(params);
		StringBuffer html = new StringBuffer();
		if (CanNull.equals("1")) {
			html.append(" <option value=''></option>");
		}
		int Numresuit = resultList.size();
		for (int i = 0; i < Numresuit; i++) {
			Map reSultMap = (Map) resultList.get(i);
			html.append(" <option value='").append(reSultMap.get("ID").toString())
				.append("'>").append(reSultMap.get("VAL").toString()).append("</option>");

		}
		return html.toString();
	}
	
	 /** 
     * 输出 excel文件到 客户端
     * @param request 
     * @param response 
     * @return 
     * @throws Exception 
     */ 
	public void doSCCVSDown(String repName, String REPCON, List expList,List colnames,
			HttpServletResponse response,List physical) throws Exception {
		if(expList.size() > excelPage){
			exportZip(response,expList,colnames,physical);
		}else{
			exportExcel(response,expList,colnames,physical);
		}
	}
	
	   /** 
     * 导出zip数据 
     * @param request 
     * @param response 
     * @return 
     * @throws Exception 
     */  
	private void exportZip( HttpServletResponse response,List expList,List colnames,List physical)throws Exception{ 
		//生成excel文件路径
		String url = getDirecory()+"exportExcel";
		createFile(url);
		url = url+ File.separatorChar +"exportExcel";
		HSSFWorkbook wb ;
		int listCount = expList.size();
		int count = listCount%excelPage == 0 ? listCount/excelPage : listCount/excelPage+1;
		for (int i = 0; i < count; i++) {
			int countPage = listCount == i ? listCount : (i+1)*excelPage;
			wb = toExcel(expList,colnames,i*excelPage,countPage,physical);
			File file = new File(url+i+".xls");
			createNewFile(file); 
			OutputStream os = new FileOutputStream(file);
			wb.write(os);  
	        os.flush();  
	        os.close();
		}
		exportAllZip(response);
    } 
	
	
    /** 
     * 导出单个Excel 
     * @param request 
     * @param response 
     * @return 
     * @throws Exception 
     */  
	private void exportExcel(HttpServletResponse response,List expList,List colnames,List physical){
    	HSSFWorkbook wb = toExcel(expList,colnames,0,expList.size(),physical);
    	try {
    		response.reset();
    		response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=exportExcel.xls");
			ServletOutputStream fileOut = response.getOutputStream();;
			wb.write(fileOut);
			fileOut.close();
		} catch (Exception e) {}
    }
	
    /** 
     * 拼装Excel 
     * @param request 
     * @param response 
     * @return 
     * @throws Exception 
     */  
	private HSSFWorkbook toExcel(List expList,List colnames,int start,int end,List physical){
    	HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("sheet");
		HSSFRow row = sheet.createRow((short) 0);
		row = sheet.createRow(0);
		int numEXP = colnames.size();
		for(int i = 0; i < numEXP; i++){
			HSSFRichTextString cellValue = new HSSFRichTextString(String.valueOf(colnames.get(i)));
			row.createCell((short) i).setCellValue(cellValue);
		}
		for (int i = start; i < end; i++) {
			row = sheet.createRow(i+1-start);
			Map reSultMap = (Map) expList.get(i);
			for (int k = 0; k < numEXP; k++) {
				String ID = (String)physical.get(k);
				HSSFRichTextString cellValue = new HSSFRichTextString(String.valueOf(reSultMap.get(ID)));
				row.createCell((short) k).setCellValue(cellValue);
			}
		}
		return wb;
    }
	
    /** 
     * 导出项目所有数据 
     * @param request 
     * @param response 
     * @return 
     * @throws Exception 
     */  
	private void exportAllZip( HttpServletResponse response){        
        try{  
            //生成excel文件  
        	String url = getDirecory()+"exportExcel";
            zipFile(url);//folder是之前获取的文件夹路径  
            createFile(url);
            StringBuilder uri = new StringBuilder();  
            uri.append(url);  
            uri.append(".zip");       
            downloadFile(response,uri);//去下载zip文件  
        }catch(Exception e){  
            e.printStackTrace();  
        }
    }  
	
    /** 
     * 将指定文件夹打包成zip 
     * @param folder 
     * @throws IOException  
     */  
    private void zipFile(String folder) throws IOException {  
        File zipFile = new File(folder + ".zip");  
        if (zipFile.exists()) {  
            zipFile.delete();  
        }  
        ZipOutputStream zipout = new ZipOutputStream(new FileOutputStream(zipFile));  
        File f = new File(folder);  
        File[] files = f.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = (File) files[i];
			zipout.putNextEntry(new ZipEntry(file.getName()));
			FileInputStream fileInputStream = new FileInputStream(file);  
	        byte[] buf = new byte[2048];  
	        BufferedInputStream origin = new BufferedInputStream(fileInputStream,2048);  
	        int len;  
	        while ((len = origin.read(buf,0,2048))!=-1) {  
	            zipout.write(buf,0,len);  
	        }
	        origin.close();  
	        fileInputStream.close(); 
		}
        zipout.flush();  
        zipout.close();  
    }  

	  /**导出 
	 * @param response 
	 * @param fileName 
	 * @return 
	 * @throws IOException 
	 */  
    private  void downloadFile(HttpServletResponse response,StringBuilder uri)   
	    throws IOException {  
	        //获取服务其上的文件名称  
	        StringBuffer filename = new StringBuffer();  
	        filename.append(uri);  
	        File file = new File(filename.toString());  
	        response.setHeader("Expires", "0");  
	        response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");  
	        response.setHeader("Pragma", "public");  
	        response.setContentType("application/x-msdownload;charset=UTF-8");  
	        response.setHeader("Content-Disposition", "attachment;filename=exportZip.zip");  
	          
	        //将此文件流写入到response输出流中  
	        FileInputStream inputStream = new FileInputStream(file);  
	        OutputStream outputStream = response.getOutputStream();   
	        byte[] buffer = new byte[1024];  
	        int i = -1;  
	        while ((i = inputStream.read(buffer)) != -1) {  
	            outputStream.write(buffer, 0, i);  
	        }   
	        outputStream.flush();  
	        outputStream.close();  
	        inputStream.close(); 
	  }  

    
	/**
	 * 得到文件路径
	 * @return String
	 */
	private String getDirecory(){
		String path = "download" + File.separatorChar ;
		File file = new File(path);
		if(!file.exists()){
			file.mkdirs();
		}
		return path;
	}

	/**
	 * 清除该路径文件
	 * @return String
	 */
	private void createFile(String url){
		File file = new File(url);
		if(!file.exists()){
			file.mkdirs();
		}else{
			if(file.isDirectory()){
				File[] fil = file.listFiles();
				for (int i = 0; i < fil.length; i++) {
					File fileh = (File) fil[i];
					fileh.delete();
				}
			}
		}
	}
	
	/**
	 * 创建文件路径
	 * @return String
	 * @throws IOException 
	 */
	private void createNewFile(File file) throws IOException{
		if(file.exists()){  
			file.delete();  
        }else{  
        	file.createNewFile();  
        }  
	}

	// 写CVS文件
	/*private void doSCCVSWriter(String repName, String REPCON, List expList)
			throws IOException {
		// CsvWriter writer = new CsvWriter(new PrintWriter(
		// new OutputStreamWriter(new FileOutputStream("temp.csv"),
		// Charset.forName("GBK"))), ',');
		//      

		//byte[] buffer;
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		CsvWriter writer = new CsvWriter(stream, ',', Charset.forName("GBK"));

		// CsvWriter writer = new CsvWriter("C:/name.csv", '\t',
		// Charset.forName("GBK"));
		writer.write(repName);
		writer.endRecord();
		if (REPCON != null && !REPCON.equals("")) {
			writer.write(REPCON.replaceAll("&nbsp", ""));
			writer.endRecord();
		}
		int Numresuit = expList.size();
		for (int i = 0; i < Numresuit; i++) {
			String data = expList.get(i).toString();
			writer.write(data);
			writer.endRecord();
		}
		writer.flush();
		writer.close();
		//buffer = stream.toByteArray();
		stream.close();

	}
	
	public void gethidden(String CONID, String CONName, String ZJCONS,
			StringBuffer hidden, StringBuffer hiddenjs, List CON) {

		String[] aZJCONS = StringUtil.toArr(ZJCONS);

		hidden.append(" <input type='radio' ");
		hidden.append(" name='dogroup'");
		hidden.append(" id='");
		hidden.append(CONID);
		hidden.append("'  value='");
		hidden.append(CONID);
		hidden.append("' ");
		hidden.append(" onclick='sethidden(this);'> ");
		hidden.append(CONName);
		hidden.append("&nbsp&nbsp&nbsp&nbsp");

		hiddenjs.append(" if(obj.value=='");
		hiddenjs.append(CONID);
		hiddenjs.append("')");
		hiddenjs.append("{");
		hiddenjs.append(" obj.checked='true'; form.groupvalue.value=obj.value ; ");
		StringBuffer tempjs = new StringBuffer();
		for (int l = 0; l < CON.size(); l++) {
			Map ConMap = (Map) CON.get(l);
			// 条件编号
			String CONMX_NO = (String) ConMap.get("CONMX_NO");
			String vCONID = "H" + CONMX_NO;
			vCONID = vCONID.trim();
			String vINPUT_TYPE = (String) ConMap.get("INPUT_TYPE");
			if (!"隐藏组件".equals(vINPUT_TYPE)) {
				tempjs.append(vCONID);
				tempjs.append(".style.display='';");

			}
		}
		for (int m = 0; m < aZJCONS.length; m++) {
			String NBZH = "H" + aZJCONS[m];
			NBZH = NBZH.trim();
			tempjs.append(NBZH);
			tempjs.append(".style.display='none';");
			String ClearStr = "M" + aZJCONS[m];
			tempjs.append(" form.");
			tempjs.append(ClearStr);
			tempjs.append(".value='';");

		}
		hiddenjs.append(tempjs.toString());
		hiddenjs.append("}");

	}*/

	public void getQureySQL(Map params, HttpServletRequest request) {
		// 获得模板信息
		String dateId = request.getParameter("dateId");
		String[] dateIds = dateId.split(",");
		for(int i = 0; i<dateIds.length; i++){
			dateId = request.getParameter(dateIds[i]);
			if((dateId != null)&&(dateId.length() > 4)){
				dateId = dateId.replaceAll("-", "");
				dateId = dateId.substring(0, 4);
				break;
			}
		}

		List ModList = this.findMbList(params);
		Map ModMsp = (Map) ModList.get(0);
		params.put("flg", ModMsp.get("ORGSQL"));
		params.put("tzmbbm", ModMsp.get("CHECKIN_DEPT"));
		params.put("resource", ModMsp.get("RESOURCE"));
		// 获得条件信息
		List ConList = this.findConList(params);
		int NumCon = ConList.size();
		// 组装SQL
		StringBuffer SQL = new StringBuffer("");
		StringBuffer CONS = new StringBuffer("");

		SQL.append(" select ");
		SQL.append(ModMsp.get("SELECT_SQL"));
		if (DBTYPE.equals("db2")) {
			if (ModMsp.get("ORDER_SQL") != null
					&& !ModMsp.get("ORDER_SQL").equals("")) {
				SQL.append(",ROW_NUMBER() OVER( ORDER BY ");
				SQL.append(ModMsp.get("ORDER_SQL"));
				SQL.append(") AS rn ");

			}
		}

		SQL.append(" from  ");
		String FROM_SQL = (String) ModMsp.get("FROM_SQL");
		// 动态数据表判断是否存在----------------------------
		Pattern p = Pattern.compile("\\[(.*?)\\]");
	    Matcher m = p.matcher(FROM_SQL);
	    while (m.find()) {
	    	String table = m.group(1);
	    	FROM_SQL = FROM_SQL.replace(table, table+"_"+dateId);
	    }

	    FROM_SQL = FROM_SQL.replaceAll("\\[", "");  
	    FROM_SQL = FROM_SQL.replaceAll("\\]", ""); 
	    FROM_SQL = FROM_SQL.replaceAll("#", "'"); 
		//-------------------------------------------------
		SQL.append(FROM_SQL);
		SQL.append(" where  ");
		if (ModMsp.get("WHERE_SQL") != null
				&& !ModMsp.get("WHERE_SQL").equals("")) {
			String WHERE_SQL = (String) ModMsp.get("WHERE_SQL");
			WHERE_SQL = WHERE_SQL.replaceAll("#", "'");
			SQL.append(ModMsp.get("WHERE_SQL"));
		} else {
			SQL.append(" 1=1  ");
		}

		// 初始化
		String groupvalue = request.getParameter("groupvalue");
		if (groupvalue != null && !groupvalue.equals("")) {
			CONS.append(" var vfrm=document.getElementById('" + groupvalue
					+ "'); ");
			CONS.append("  vfrm.onclick();");

		}

		// 拼条件
		for (int i = 0; i < NumCon; i++) {
			Map ConMap = (Map) ConList.get(i);
			String PHYSICAL_NAME = (String) ConMap.get("PHYSICAL_NAME");
			String CONMX_NO = (String) ConMap.get("CONMX_NO");
			String CONID = "M" + CONMX_NO;
			String CONIDVal = request.getParameter(CONID.trim());
			String CONMX_TYPE = (String) ConMap.get("CONMX_TYPE");
			String OPERATOR = (String) ConMap.get("OPERATOR");
			String ISJOIN = ConMap.get("ISJOIN").toString();
			String INPUT_VALUE = "";
			// 替换条件
			if (CONIDVal == null)
				CONIDVal = "";
			if (SQL.toString().indexOf(CONMX_NO) != -1) {
				String temSQL = SQL.toString().replaceAll(CONMX_NO, CONIDVal);
				SQL.delete(0, SQL.length());
				SQL.append(temSQL);
			}

			if (ConMap.get("INPUT_VALUE") != null)
				INPUT_VALUE = ConMap.get("INPUT_VALUE").toString();

			if (CONIDVal != null && !CONIDVal.equals("")) {
				CONIDVal = CONIDVal.trim();
				CONS.append(" form.");
				CONS.append(CONID);
				CONS.append(".value='");
				CONS.append(CONIDVal);
				CONS.append("';");
				// 应为机构有一个隐藏的ID
				if ("机构".equals(INPUT_VALUE)) {
					CONS.append(" form.N");
					CONS.append(CONID);
					CONS.append(".value='");
					String NCONID = "N" + CONID;
					CONS.append(request.getParameter(NCONID.trim()));
					CONS.append("';");
				}
			}

			// 字符串，日期，数字
			if (ISJOIN != null && ISJOIN.equals("是")) {
				if (CONMX_TYPE.equals("字符串")) {
					if (CONIDVal != null && !CONIDVal.equals("")) {
						CONIDVal = CONIDVal.trim();
						SQL.append(" and  ");
						SQL.append(PHYSICAL_NAME);
						SQL.append(" ");
						SQL.append(OPERATOR);
						if (OPERATOR.equals("LIKE")) {
							SQL.append(" '%");
							SQL.append(CONIDVal);
							SQL.append("%'");
						} else if (OPERATOR.equals("LIKEL")) {
							SQL.append(" '%");
							SQL.append(CONIDVal);
							SQL.append(" '");
						} else if (OPERATOR.equals("LIKER")) {
							SQL.append(" '");
							SQL.append(CONIDVal);
							SQL.append(" %'");
						} else {
							SQL.append(" '");
							SQL.append(CONIDVal);
							SQL.append("'");
						}
					}
				} else if (CONMX_TYPE.equals("日期")) {
					if (CONIDVal != null && !CONIDVal.equals("")) {
						CONIDVal = CONIDVal.trim();
						SQL.append(" and  ");
						SQL.append(PHYSICAL_NAME);
						SQL.append(OPERATOR);
						SQL.append(" '");
						SQL.append(CONIDVal);
						SQL.append(" '");
					}

				} else if (CONMX_TYPE.equals("数字")) {
					if (CONIDVal != null && !CONIDVal.equals("")) {
						CONIDVal = CONIDVal.trim();
						SQL.append(" and  ");
						SQL.append(PHYSICAL_NAME);
						SQL.append(OPERATOR);
						SQL.append(" ");
						SQL.append(CONIDVal);
						SQL.append(" ");

					}

				}else if (CONMX_TYPE.equals("文本")) {
					if (CONIDVal != null && !CONIDVal.equals("")) {
						String [] conidval = CONIDVal.split("\r");
						SQL.append(" and  ( 1 != 1");
						for(int j =0; j < conidval.length ; j++){
							SQL.append(" or ");
							SQL.append(PHYSICAL_NAME);
							SQL.append(OPERATOR);
							SQL.append(" '");
							SQL.append(conidval[j].trim());
							SQL.append("' ");
						}
						SQL.append(" ) ");
					}

				}
			}

		}
		if (DBTYPE.equals("orc")) {
			if (ModMsp.get("ORDER_SQL") != null
					&& !ModMsp.get("ORDER_SQL").equals("")) {
				SQL.append(" ORDER BY ");
				SQL.append(ModMsp.get("ORDER_SQL"));
			}
		}
		params.put("REP_SQL", SQL.toString());
		params.put("CONS_PAR", CONS.toString());
	}
	
	//获取大数据查询sql语句 无关联 仅适用于对公活期账户交易明细查询  对私活期账户交易明细查询
	public void getSQL(Map params, HttpServletRequest request) {
		// 获得模板信息
		
		List ModList = this.findMbList(params);
		Map ModMsp = (Map) ModList.get(0);
		params.put("flg", ModMsp.get("ORGSQL"));
		params.put("tzmbbm", ModMsp.get("CHECKIN_DEPT"));
		params.put("resource", ModMsp.get("RESOURCE"));
		// 获得条件信息
		List ConList = this.findConList(params);
		int NumCon = ConList.size();
		// 组装SQL
		//主表查询语句
		StringBuffer SQL = new StringBuffer("");
		//连接表查询语句1
		StringBuffer JOINSQL = new StringBuffer("");
		//连接表查询语句2
		StringBuffer JOINSQL2 = new StringBuffer("");
		StringBuffer CONS = new StringBuffer("");

		SQL.append(" select ");
		SQL.append(ModMsp.get("SELECT_SQL"));
		
		SQL.append(" from  ");
		String FROM_SQL = (String) ModMsp.get("FROM_SQL");
		// 动态数据表判断是否存在----------------------------
		Pattern p = Pattern.compile("\\[(.*?)\\]");
	    Matcher m = p.matcher(FROM_SQL);
	    while (m.find()) {
	    	String table = m.group(1);
	    	FROM_SQL = FROM_SQL.replace(table, table);
	    }

	    FROM_SQL = FROM_SQL.replaceAll("\\[", "");  
	    FROM_SQL = FROM_SQL.replaceAll("\\]", ""); 
	    FROM_SQL = FROM_SQL.replaceAll("#", "'"); 
		//-------------------------------------------------
		SQL.append(FROM_SQL);
		SQL.append(" where  ");
		if (ModMsp.get("WHERE_SQL") != null
				&& !ModMsp.get("WHERE_SQL").equals("")) {
			String WHERE_SQL = (String) ModMsp.get("WHERE_SQL");
			WHERE_SQL = WHERE_SQL.replaceAll("#", "'");
			SQL.append(ModMsp.get("WHERE_SQL"));
		} else {
			SQL.append(" 1=1  ");
		}

		// 初始化
		String groupvalue = request.getParameter("groupvalue");
		if (groupvalue != null && !groupvalue.equals("")) {
			CONS.append(" var vfrm=document.getElementById('" + groupvalue
					+ "'); ");
			CONS.append("  vfrm.onclick();");

		}

		// 拼条件
		for (int i = 0; i < NumCon; i++) {
			Map ConMap = (Map) ConList.get(i);
			String PHYSICAL_NAME = (String) ConMap.get("PHYSICAL_NAME");
			String CONMX_NO = (String) ConMap.get("CONMX_NO");
			String CONID = "M" + CONMX_NO;
			String CONIDVal = request.getParameter(CONID.trim());
			String CONMX_TYPE = (String) ConMap.get("CONMX_TYPE");
			String OPERATOR = (String) ConMap.get("OPERATOR");
			String ISJOIN = ConMap.get("ISJOIN").toString();
			String INPUT_VALUE = "";
			// 替换条件
			if (CONIDVal == null)
				CONIDVal = "";
			if (SQL.toString().indexOf(CONMX_NO) != -1) {
				String temSQL = SQL.toString().replaceAll(CONMX_NO, CONIDVal);
				SQL.delete(0, SQL.length());
				SQL.append(temSQL);
			}

			if (ConMap.get("INPUT_VALUE") != null)
				INPUT_VALUE = ConMap.get("INPUT_VALUE").toString();

			if (CONIDVal != null && !CONIDVal.equals("")) {
				CONIDVal = CONIDVal.trim();
				CONS.append(" form.");
				CONS.append(CONID);
				CONS.append(".value='");
				CONS.append(CONIDVal);
				CONS.append("';");
				// 应为机构有一个隐藏的ID
				if ("机构".equals(INPUT_VALUE)) {
					CONS.append(" form.N");
					CONS.append(CONID);
					CONS.append(".value='");
					String NCONID = "N" + CONID;
					CONS.append(request.getParameter(NCONID.trim()));
					CONS.append("';");
				}
			}

			// 字符串，日期，数字
			if (ISJOIN != null && ISJOIN.equals("是")) {
				if (CONMX_TYPE.equals("字符串")) {
					if (CONIDVal != null && !CONIDVal.equals("")) {
						CONIDVal = CONIDVal.trim();
						SQL.append(" and  ");
						SQL.append(PHYSICAL_NAME);
						SQL.append(" ");
						SQL.append(OPERATOR);
						if (OPERATOR.equals("LIKE")) {
							SQL.append(" '%");
							SQL.append(CONIDVal);
							SQL.append("%'");
						} else if (OPERATOR.equals("LIKEL")) {
							SQL.append(" '%");
							SQL.append(CONIDVal);
							SQL.append(" '");
						} else if (OPERATOR.equals("LIKER")) {
							SQL.append(" '");
							SQL.append(CONIDVal);
							SQL.append(" %'");
						} else {
							SQL.append(" '");
							SQL.append(CONIDVal);
							SQL.append("'");
						}
					}
				} else if (CONMX_TYPE.equals("日期")) {
					if (CONIDVal != null && !CONIDVal.equals("")) {
						CONIDVal = CONIDVal.trim();
						SQL.append(" and  ");
						SQL.append(PHYSICAL_NAME);
						SQL.append(OPERATOR);
						SQL.append(" '");
						SQL.append(CONIDVal);
						SQL.append(" '");
					}

				} else if (CONMX_TYPE.equals("数字")) {
					if (CONIDVal != null && !CONIDVal.equals("")) {
						CONIDVal = CONIDVal.trim();
						SQL.append(" and  ");
						SQL.append(PHYSICAL_NAME);
						SQL.append(OPERATOR);
						SQL.append(" ");
						SQL.append(CONIDVal);
						SQL.append(" ");

					}

				}else if (CONMX_TYPE.equals("文本")) {
					if (CONIDVal != null && !CONIDVal.equals("")) {
						String [] conidval = CONIDVal.split("\r");
						SQL.append(" and  ( 1 != 1");
						for(int j =0; j < conidval.length ; j++){
							SQL.append(" or ");
							SQL.append(PHYSICAL_NAME);
							SQL.append(OPERATOR);
							SQL.append(" '");
							SQL.append(conidval[j].trim());
							SQL.append("' ");
						}
						SQL.append(" ) ");
					}

				}
			}

		}
		
			if (ModMsp.get("ORDER_SQL") != null
					&& !ModMsp.get("ORDER_SQL").equals("")) {
				SQL.append(" ORDER BY ");
				SQL.append(ModMsp.get("ORDER_SQL"));
			}
		//SQL.append(" limit 1000");
		params.put("REP_SQL", SQL.toString());
		params.put("CONS_PAR", CONS.toString());
	}
	
	
	
	
	//获取大数据查询sql语句 无关联 仅适用于对私账户交易对手信息查询   对私活期账户交易明细查询 
		public void getSQLSFTAB(Map params, HttpServletRequest request) {
			// 获得模板信息
			
			List ModList = this.findMbList(params);
			Map ModMsp = (Map) ModList.get(0);
			params.put("flg", ModMsp.get("ORGSQL"));
			params.put("tzmbbm", ModMsp.get("CHECKIN_DEPT"));
			params.put("resource", ModMsp.get("RESOURCE"));
			// 获得条件信息
			List ConList = this.findConList(params);
			int NumCon = ConList.size();
			// 组装SQL
			//主表查询语句
			StringBuffer SQL = new StringBuffer("");
			//连接表查询语句1
			StringBuffer JOINSQL = new StringBuffer("");
			//连接表查询语句2
			StringBuffer JOINSQL2 = new StringBuffer("");
			StringBuffer CONS = new StringBuffer("");

	
		    
		    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String date1 = request.getParameter("M201211070003004");//开始日期
			String date2 = request.getParameter("M201211070003005");//结束日期
			if(date1 == null || "".equals(date1)){
				params.put("errorMsg", "开始日期不得为空！");
				return;
			}
			if(date1 == null || "".equals(date1)){
				params.put("errorMsg", "结束日期不得为空！");
				return;
			}
			
        	Map<String, Map<String, Date>> map;
			try {
				if(format.parse(date1).after(format.parse(date2))){
					params.put("errorMsg", "开始日期不得大于结束日期！");
					return;
				}
				if(format.parse(date2).after( new Date())){
					params.put("errorMsg", "选择日期不得大于当前日期！");
					return;
				}
				map = DateUtil.getStartStopBetween(format.parse(date1), format.parse(date2));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				params.put("errorMsg", "时间格式解析错误！");
				return;
			}
    		Set<String> keySet = map.keySet();
    	
    		for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
    			String key = (String) iterator.next();
    			Map<String,Date> yearMap = map.get(key);
    			String startDate = format.format(yearMap.get("start"));
    			String endDate = format.format(yearMap.get("stop"));
    			
    			
    			SQL.append(" select ");
    			SQL.append(ModMsp.get("SELECT_SQL"));
    			
    			SQL.append(" from  ");
    			String FROM_SQL = (String) ModMsp.get("FROM_SQL");
    			// 动态数据表判断是否存在----------------------------
    			Pattern p = Pattern.compile("\\[(.*?)\\]");
    		    Matcher m = p.matcher(FROM_SQL);
    		    while (m.find()) {
    		    	String table = m.group(1);
    		    	FROM_SQL = FROM_SQL.replace(table, table+"_"+key);
    		    }
    		    FROM_SQL = FROM_SQL.replaceAll("\\[", "");  
    		    FROM_SQL = FROM_SQL.replaceAll("\\]", ""); 
    		    FROM_SQL = FROM_SQL.replaceAll("#", "'"); 
    			//-------------------------------------------------
    			SQL.append(FROM_SQL);
    			SQL.append(" where  ");
    			if (ModMsp.get("WHERE_SQL") != null
    					&& !ModMsp.get("WHERE_SQL").equals("")) {
    				String WHERE_SQL = (String) ModMsp.get("WHERE_SQL");
    				WHERE_SQL = WHERE_SQL.replaceAll("#", "'");
    				SQL.append(ModMsp.get("WHERE_SQL"));
    		} else {
				SQL.append(" 1=1  ");
			}

			// 初始化
			String groupvalue = request.getParameter("groupvalue");
			if (groupvalue != null && !groupvalue.equals("")) {
				CONS.append(" var vfrm=document.getElementById('" + groupvalue
						+ "'); ");
				CONS.append("  vfrm.onclick();");

			}

			// 拼条件
			for (int i = 0; i < NumCon; i++) {
				Map ConMap = (Map) ConList.get(i);
				String PHYSICAL_NAME = (String) ConMap.get("PHYSICAL_NAME");
				String CONMX_NO = (String) ConMap.get("CONMX_NO");
				String CONID = "M" + CONMX_NO;
				String CONIDVal = request.getParameter(CONID.trim());
				String CONMX_TYPE = (String) ConMap.get("CONMX_TYPE");
				String OPERATOR = (String) ConMap.get("OPERATOR");
				String ISJOIN = ConMap.get("ISJOIN").toString();
				String INPUT_VALUE = "";
				// 替换条件
				if (CONIDVal == null)
					CONIDVal = "";
				if (SQL.toString().indexOf(CONMX_NO) != -1) {
					String temSQL = SQL.toString().replaceAll(CONMX_NO, CONIDVal);
					SQL.delete(0, SQL.length());
					SQL.append(temSQL);
				}

				if (ConMap.get("INPUT_VALUE") != null)
					INPUT_VALUE = ConMap.get("INPUT_VALUE").toString();

				if (CONIDVal != null && !CONIDVal.equals("")) {
					CONIDVal = CONIDVal.trim();
					CONS.append(" form.");
					CONS.append(CONID);
					CONS.append(".value='");
					CONS.append(CONIDVal);
					CONS.append("';");
					// 应为机构有一个隐藏的ID
					if ("机构".equals(INPUT_VALUE)) {
						CONS.append(" form.N");
						CONS.append(CONID);
						CONS.append(".value='");
						String NCONID = "N" + CONID;
						CONS.append(request.getParameter(NCONID.trim()));
						CONS.append("';");
					}
				}

				// 字符串，日期，数字
				if (ISJOIN != null && ISJOIN.equals("是")) {
					if (CONMX_TYPE.equals("字符串")) {
						if (CONIDVal != null && !CONIDVal.equals("")) {
							CONIDVal = CONIDVal.trim();
							SQL.append(" and  ");
							SQL.append(PHYSICAL_NAME);
							SQL.append(" ");
							SQL.append(OPERATOR);
							if (OPERATOR.equals("LIKE")) {
								SQL.append(" '%");
								SQL.append(CONIDVal);
								SQL.append("%'");
							} else if (OPERATOR.equals("LIKEL")) {
								SQL.append(" '%");
								SQL.append(CONIDVal);
								SQL.append(" '");
							} else if (OPERATOR.equals("LIKER")) {
								SQL.append(" '");
								SQL.append(CONIDVal);
								SQL.append(" %'");
							} else {
								SQL.append(" '");
								SQL.append(CONIDVal);
								SQL.append("'");
							}
						}
					} else if (CONMX_TYPE.equals("日期")) {
						if (CONIDVal != null && !CONIDVal.equals("")) {
							CONIDVal = CONIDVal.trim();
							SQL.append(" and  ");
							SQL.append(PHYSICAL_NAME);
							SQL.append(OPERATOR);
							SQL.append(" '");
							SQL.append(CONIDVal);
							SQL.append(" '");
						}

					} else if (CONMX_TYPE.equals("数字")) {
						if("SFTRDT".equalsIgnoreCase(PHYSICAL_NAME)){
								if("M201211070003004".equals(CONID)){
									SQL.append(" AND SFTRDT >= " + startDate);
								}else{
									SQL.append(" AND SFTRDT <= " + endDate);
								}
							}

					}else if (CONMX_TYPE.equals("文本")) {
						if (CONIDVal != null && !CONIDVal.equals("")) {
							String [] conidval = CONIDVal.split("\r");
							SQL.append(" and  ( 1 != 1");
							for(int j =0; j < conidval.length ; j++){
								SQL.append(" or ");
								SQL.append(PHYSICAL_NAME);
								SQL.append(OPERATOR);
								SQL.append(" '");
								SQL.append(conidval[j].trim());
								SQL.append("' ");
							}
							SQL.append(" ) ");
						}

					}
				}

			}
			
				if (ModMsp.get("ORDER_SQL") != null
						&& !ModMsp.get("ORDER_SQL").equals("")) {
					SQL.append(" ORDER BY ");
					SQL.append(ModMsp.get("ORDER_SQL"));
				}
				
				SQL.append(";");
    		}
			//SQL.append(" limit 1000");
			params.put("REP_SQL", SQL.toString());
			params.put("CONS_PAR", CONS.toString());
		}
	
	
	//获取大数据查询sql语句 一个关联表
		public void getLeftJoinOne(Map params, HttpServletRequest request) {
			// 获得模板信息
			
			List ModList = this.findMbList(params);
			Map ModMsp = (Map) ModList.get(0);
			params.put("flg", ModMsp.get("ORGSQL"));
			params.put("tzmbbm", ModMsp.get("CHECKIN_DEPT"));
			params.put("resource", ModMsp.get("RESOURCE"));
			// 获得条件信息
			List ConList = this.findConList(params);
			int NumCon = ConList.size();
			// 组装SQL
			StringBuffer SQL = new StringBuffer("");
			StringBuffer LEFT_SQL = new StringBuffer("");
			StringBuffer CONS = new StringBuffer("");
			
			LEFT_SQL.append("Select * from ereport.bp_organ as b where 1=1");

			SQL.append(" select ");
			SQL.append(ModMsp.get("SELECT_SQL"));
			
			SQL.append(" from  ");
			String FROM_SQL = (String) ModMsp.get("FROM_SQL");

			// 动态数据表判断是否存在----------------------------
			Pattern p = Pattern.compile("\\[(.*?)\\]");
		    Matcher m = p.matcher(FROM_SQL);
		    while (m.find()) {
		    	String table = m.group(1);
		    	FROM_SQL = FROM_SQL.replace(table, table);
		    }

		    FROM_SQL = FROM_SQL.replaceAll("\\[", "");  
		    FROM_SQL = FROM_SQL.replaceAll("\\]", ""); 
		    FROM_SQL = FROM_SQL.replaceAll("#", "'"); 
		    
		  
			//-------------------------------------------------
		    FROM_SQL =  FROM_SQL.substring(0,FROM_SQL.indexOf("left"));
			SQL.append(FROM_SQL);
			SQL.append(" where  ");
			if (ModMsp.get("WHERE_SQL") != null
					&& !ModMsp.get("WHERE_SQL").equals("")) {
				String WHERE_SQL = (String) ModMsp.get("WHERE_SQL");
				WHERE_SQL = WHERE_SQL.replaceAll("#", "'");
				SQL.append(ModMsp.get("WHERE_SQL"));
			} else {
				SQL.append(" 1=1  ");
			}

			// 初始化
			String groupvalue = request.getParameter("groupvalue");
			if (groupvalue != null && !groupvalue.equals("")) {
				CONS.append(" var vfrm=document.getElementById('" + groupvalue
						+ "'); ");
				CONS.append("  vfrm.onclick();");

			}

			// 拼条件
			for (int i = 0; i < NumCon; i++) {
				Map ConMap = (Map) ConList.get(i);
				String PHYSICAL_NAME = (String) ConMap.get("PHYSICAL_NAME");
				String CONMX_NO = (String) ConMap.get("CONMX_NO");
				String CONID = "M" + CONMX_NO;
				String CONIDVal = request.getParameter(CONID.trim());
				String CONMX_TYPE = (String) ConMap.get("CONMX_TYPE");
				String OPERATOR = (String) ConMap.get("OPERATOR");
				String ISJOIN = ConMap.get("ISJOIN").toString();
				String INPUT_VALUE = "";
				// 替换条件
				if (CONIDVal == null)
					CONIDVal = "";
				if (SQL.toString().indexOf(CONMX_NO) != -1) {
					String temSQL = SQL.toString().replaceAll(CONMX_NO, CONIDVal);
					SQL.delete(0, SQL.length());
					SQL.append(temSQL);
				}

				if (ConMap.get("INPUT_VALUE") != null)
					INPUT_VALUE = ConMap.get("INPUT_VALUE").toString();

				if (CONIDVal != null && !CONIDVal.equals("")) {
					CONIDVal = CONIDVal.trim();
					CONS.append(" form.");
					CONS.append(CONID);
					CONS.append(".value='");
					CONS.append(CONIDVal);
					CONS.append("';");
					// 应为机构有一个隐藏的ID
					if ("机构".equals(INPUT_VALUE)) {
						CONS.append(" form.N");
						CONS.append(CONID);
						CONS.append(".value='");
						String NCONID = "N" + CONID;
						CONS.append(request.getParameter(NCONID.trim()));
						CONS.append("';");
					}
				}

				// 字符串，日期，数字
				if (ISJOIN != null && ISJOIN.equals("是")) {
					if (CONMX_TYPE.equals("字符串")) {
						if (CONIDVal != null && !CONIDVal.equals("") ) {
							
							CONIDVal = CONIDVal.trim();
							if( "B.ORGNO".equalsIgnoreCase(PHYSICAL_NAME)){
								LEFT_SQL.append(" and  B.ORGNO = '");
								LEFT_SQL.append(CONIDVal);
								LEFT_SQL.append("'");
							}else{
								SQL.append(" and  ");
								SQL.append(PHYSICAL_NAME);
								SQL.append(" ");
								SQL.append(OPERATOR);
								if (OPERATOR.equals("LIKE")) {
									SQL.append(" '%");
									SQL.append(CONIDVal);
									SQL.append("%'");
								} else if (OPERATOR.equals("LIKEL")) {
									SQL.append(" '%");
									SQL.append(CONIDVal);
									SQL.append(" '");
								} else if (OPERATOR.equals("LIKER")) {
									SQL.append(" '");
									SQL.append(CONIDVal);
									SQL.append(" %'");
								} else {
									SQL.append(" '");
									SQL.append(CONIDVal);
									SQL.append("'");
								}
							}
							
						}
					
					} else if (CONMX_TYPE.equals("日期")) {
						if (CONIDVal != null && !CONIDVal.equals("")) {
							CONIDVal = CONIDVal.trim();
							SQL.append(" and  ");
							SQL.append(PHYSICAL_NAME);
							SQL.append(OPERATOR);
							SQL.append(" '");
							SQL.append(CONIDVal);
							SQL.append(" '");
						}

					} else if (CONMX_TYPE.equals("数字")) {
						if (CONIDVal != null && !CONIDVal.equals("")) {
							CONIDVal = CONIDVal.trim();
							SQL.append(" and  ");
							SQL.append(PHYSICAL_NAME);
							SQL.append(OPERATOR);
							SQL.append(" ");
							SQL.append(CONIDVal);
							SQL.append(" ");

						}

					}else if (CONMX_TYPE.equals("文本")) {
						if (CONIDVal != null && !CONIDVal.equals("")) {
							String [] conidval = CONIDVal.split("\r");
							SQL.append(" and  ( 1 != 1");
							for(int j =0; j < conidval.length ; j++){
								SQL.append(" or ");
								SQL.append(PHYSICAL_NAME);
								SQL.append(OPERATOR);
								SQL.append(" '");
								SQL.append(conidval[j].trim());
								SQL.append("' ");
							}
							SQL.append(" ) ");
						}

					}
				}

			}
			
				if (ModMsp.get("ORDER_SQL") != null
						&& !ModMsp.get("ORDER_SQL").equals("")) {
					SQL.append(" ORDER BY ");
					SQL.append(ModMsp.get("ORDER_SQL"));
				}
			//SQL.append(" limit 1000");
			
			params.put("LEFT_SQL", LEFT_SQL.toString());
			params.put("REP_SQL", SQL.toString());
			params.put("CONS_PAR", CONS.toString());
		}
		
		
		//获取大数据查询sql语句 关联BP_ORGAN和ORGAN
				public void getLeftJoinTwo(Map params, HttpServletRequest request) {
					// 获得模板信息
					
					List ModList = this.findMbList(params);
					Map ModMsp = (Map) ModList.get(0);
					params.put("flg", ModMsp.get("ORGSQL"));
					params.put("tzmbbm", ModMsp.get("CHECKIN_DEPT"));
					params.put("resource", ModMsp.get("RESOURCE"));
					// 获得条件信息
					List ConList = this.findConList(params);
					int NumCon = ConList.size();
					// 组装SQL
					StringBuffer SQL = new StringBuffer("");
					StringBuffer LEFT_SQL = new StringBuffer("");
					StringBuffer LEFT_SQL2 = new StringBuffer("");
					StringBuffer CONS = new StringBuffer("");
					
					LEFT_SQL.append("Select * from ereport.bp_organ as b where 1=1");
					LEFT_SQL2.append("Select * from YTPUB.organ as bo ");
					SQL.append(" select ");
					SQL.append(ModMsp.get("SELECT_SQL"));
					
					SQL.append(" from  ");
					String FROM_SQL = (String) ModMsp.get("FROM_SQL");

					// 动态数据表判断是否存在----------------------------
					Pattern p = Pattern.compile("\\[(.*?)\\]");
				    Matcher m = p.matcher(FROM_SQL);
				    while (m.find()) {
				    	String table = m.group(1);
				    	FROM_SQL = FROM_SQL.replace(table, table);
				    }

				    FROM_SQL = FROM_SQL.replaceAll("\\[", "");  
				    FROM_SQL = FROM_SQL.replaceAll("\\]", ""); 
				    FROM_SQL = FROM_SQL.replaceAll("#", "'"); 
				    
				  
					//-------------------------------------------------
				    FROM_SQL =  FROM_SQL.substring(0,FROM_SQL.indexOf("left"));
					SQL.append(FROM_SQL);
					SQL.append(" where  ");
					if (ModMsp.get("WHERE_SQL") != null
							&& !ModMsp.get("WHERE_SQL").equals("")) {
						String WHERE_SQL = (String) ModMsp.get("WHERE_SQL");
						WHERE_SQL = WHERE_SQL.replaceAll("#", "'");
						SQL.append(ModMsp.get("WHERE_SQL"));
					} else {
						SQL.append(" 1=1  ");
					}

					// 初始化
					String groupvalue = request.getParameter("groupvalue");
					if (groupvalue != null && !groupvalue.equals("")) {
						CONS.append(" var vfrm=document.getElementById('" + groupvalue
								+ "'); ");
						CONS.append("  vfrm.onclick();");

					}

					// 拼条件
					for (int i = 0; i < NumCon; i++) {
						Map ConMap = (Map) ConList.get(i);
						String PHYSICAL_NAME = (String) ConMap.get("PHYSICAL_NAME");
						String CONMX_NO = (String) ConMap.get("CONMX_NO");
						String CONID = "M" + CONMX_NO;
						String CONIDVal = request.getParameter(CONID.trim());
						String CONMX_TYPE = (String) ConMap.get("CONMX_TYPE");
						String OPERATOR = (String) ConMap.get("OPERATOR");
						String ISJOIN = ConMap.get("ISJOIN").toString();
						String INPUT_VALUE = "";
						// 替换条件
						if (CONIDVal == null)
							CONIDVal = "";
						if (SQL.toString().indexOf(CONMX_NO) != -1) {
							String temSQL = SQL.toString().replaceAll(CONMX_NO, CONIDVal);
							SQL.delete(0, SQL.length());
							SQL.append(temSQL);
						}

						if (ConMap.get("INPUT_VALUE") != null)
							INPUT_VALUE = ConMap.get("INPUT_VALUE").toString();

						if (CONIDVal != null && !CONIDVal.equals("")) {
							CONIDVal = CONIDVal.trim();
							CONS.append(" form.");
							CONS.append(CONID);
							CONS.append(".value='");
							CONS.append(CONIDVal);
							CONS.append("';");
							// 应为机构有一个隐藏的ID
							if ("机构".equals(INPUT_VALUE)) {
								CONS.append(" form.N");
								CONS.append(CONID);
								CONS.append(".value='");
								String NCONID = "N" + CONID;
								CONS.append(request.getParameter(NCONID.trim()));
								CONS.append("';");
							}
						}

						// 字符串，日期，数字
						if (ISJOIN != null && ISJOIN.equals("是")) {
							if (CONMX_TYPE.equals("字符串")) {
								if (CONIDVal != null && !CONIDVal.equals("") ) {
									
									CONIDVal = CONIDVal.trim();
									if( "B.ORGNO".equalsIgnoreCase(PHYSICAL_NAME)){
										LEFT_SQL.append(" and  B.ORGNO = '");
										LEFT_SQL.append(CONIDVal);
										LEFT_SQL.append("'");
									}else{
										SQL.append(" and  ");
										SQL.append(PHYSICAL_NAME);
										SQL.append(" ");
										SQL.append(OPERATOR);
										if (OPERATOR.equals("LIKE")) {
											SQL.append(" '%");
											SQL.append(CONIDVal);
											SQL.append("%'");
										} else if (OPERATOR.equals("LIKEL")) {
											SQL.append(" '%");
											SQL.append(CONIDVal);
											SQL.append(" '");
										} else if (OPERATOR.equals("LIKER")) {
											SQL.append(" '");
											SQL.append(CONIDVal);
											SQL.append(" %'");
										} else {
											SQL.append(" '");
											SQL.append(CONIDVal);
											SQL.append("'");
										}
									}
									
								}
							
							} else if (CONMX_TYPE.equals("日期")) {
								if (CONIDVal != null && !CONIDVal.equals("")) {
									CONIDVal = CONIDVal.trim();
									SQL.append(" and  ");
									SQL.append(PHYSICAL_NAME);
									SQL.append(OPERATOR);
									SQL.append(" '");
									SQL.append(CONIDVal);
									SQL.append(" '");
								}

							} else if (CONMX_TYPE.equals("数字")) {
								if (CONIDVal != null && !CONIDVal.equals("")) {
									CONIDVal = CONIDVal.trim();
									SQL.append(" and  ");
									SQL.append(PHYSICAL_NAME);
									SQL.append(OPERATOR);
									SQL.append(" ");
									SQL.append(CONIDVal);
									SQL.append(" ");

								}

							}else if (CONMX_TYPE.equals("文本")) {
								if (CONIDVal != null && !CONIDVal.equals("")) {
									String [] conidval = CONIDVal.split("\r");
									//SQL.append(" and  ( 1 != 1");
									for(int j =0; j < conidval.length ; j++){
										//SQL.append(" or ");
										SQL.append(" and  ");
										SQL.append(PHYSICAL_NAME);
										SQL.append(OPERATOR);
										SQL.append(" '");
										SQL.append(conidval[j].trim());
										SQL.append("' ");
									}
									//SQL.append(" ) ");
								}

							}
						}

					}
					
						if (ModMsp.get("ORDER_SQL") != null
								&& !ModMsp.get("ORDER_SQL").equals("")) {
							SQL.append(" ORDER BY ");
							SQL.append(ModMsp.get("ORDER_SQL"));
						}
				//	SQL.append(" limit 1000");
					
					params.put("LEFT_SQL", LEFT_SQL.toString());
					params.put("LEFT_SQL2", LEFT_SQL2.toString());
					params.put("REP_SQL", SQL.toString());
					params.put("CONS_PAR", CONS.toString());
				}
				
				
				//获取大数据查询sql语句 关联BP_ORGAN和KZH_NBZH
				public void getLeftJoinThree(Map params, HttpServletRequest request) {

					// 获得模板信息
					String REPMOD_NO = (String) params.get("REPMOD_NO");
					
					List<String> listSQL = new ArrayList<String>();
					
					List ModList = this.findMbList(params);
					Map ModMsp = (Map) ModList.get(0);
					params.put("flg", ModMsp.get("ORGSQL"));
					params.put("tzmbbm", ModMsp.get("CHECKIN_DEPT"));
					params.put("resource", ModMsp.get("RESOURCE"));
					// 获得条件信息
					List ConList = this.findConList(params);
					int NumCon = ConList.size();
					// 组装SQL
					StringBuffer SQL = new StringBuffer("");
					StringBuffer LEFT_SQL = new StringBuffer("");
					StringBuffer CONS = new StringBuffer("");
					
					String NBZH= "";
					String SFACNO= "";
					for (int i = 0; i < NumCon; i++) {
						Map m = (Map) ConList.get(i);
						String PHYSICAL_NAME = (String) m.get("PHYSICAL_NAME");
						String CONMX_NO = (String) m.get("CONMX_NO");
						String CONID = "M" + CONMX_NO;
						String CONIDVal = request.getParameter(CONID.trim());
						
						if("WBZH".equalsIgnoreCase(PHYSICAL_NAME) && CONIDVal != null && !"".equals(CONIDVal)){
							NBZH = this.getNBZH(request.getParameter(CONID));
							if("".equals(NBZH)){
								params.put("errorMsg", "输入的卡/折号未找到相应内部账号，请重新输入！");
								return;
							}
						}
						if("SFACNO".equalsIgnoreCase(PHYSICAL_NAME) && CONIDVal != null && !"".equals(CONIDVal)){
							SFACNO = request.getParameter(CONID);
						}
					}
					LEFT_SQL.append("Select * from ereport.bp_organ as b where 1=1");
            
					SQL.append(" select ");
					SQL.append(ModMsp.get("SELECT_SQL"));
					
					SQL.append(" from  ");
					String FROM_SQL = (String) ModMsp.get("FROM_SQL");

					// 动态数据表判断是否存在----------------------------
					Pattern p = Pattern.compile("\\[(.*?)\\]");
				    Matcher m = p.matcher(FROM_SQL);
				    while (m.find()) {
				    	String table = m.group(1);
				    	FROM_SQL = FROM_SQL.replace(table, table);
				    }

				    FROM_SQL = FROM_SQL.replaceAll("\\[", "");  
				    FROM_SQL = FROM_SQL.replaceAll("\\]", ""); 
				    FROM_SQL = FROM_SQL.replaceAll("#", "'"); 
				    
				  
					//-------------------------------------------------
				    FROM_SQL =  FROM_SQL.substring(0,FROM_SQL.indexOf("left"));
					SQL.append(FROM_SQL);
					SQL.append(" where  ");
					if (ModMsp.get("WHERE_SQL") != null
							&& !ModMsp.get("WHERE_SQL").equals("")) {
						String WHERE_SQL = (String) ModMsp.get("WHERE_SQL");
						WHERE_SQL = WHERE_SQL.replaceAll("#", "'");
						SQL.append(ModMsp.get("WHERE_SQL"));
					} else {
						SQL.append(" 1=1  ");
					}

					// 初始化
					String groupvalue = request.getParameter("groupvalue");
					if (groupvalue != null && !groupvalue.equals("")) {
						CONS.append(" var vfrm=document.getElementById('" + groupvalue
								+ "'); ");
						CONS.append("  vfrm.onclick();");

					}

					// 拼条件
					for (int i = 0; i < NumCon; i++) {
						Map ConMap = (Map) ConList.get(i);
						String PHYSICAL_NAME = (String) ConMap.get("PHYSICAL_NAME");
						String CONMX_NO = (String) ConMap.get("CONMX_NO");
						String CONID = "M" + CONMX_NO;
						String CONIDVal = request.getParameter(CONID.trim());
						String CONMX_TYPE = (String) ConMap.get("CONMX_TYPE");
						String OPERATOR = (String) ConMap.get("OPERATOR");
						String ISJOIN = ConMap.get("ISJOIN").toString();
						String INPUT_VALUE = "";
						
						if( "SFACNO".equalsIgnoreCase(PHYSICAL_NAME)){
							if(!"".equals(NBZH) && !"".equals(SFACNO)){
								if(!NBZH.equals(SFACNO)){
									params.put("errorMsg", "输入的卡折号与内部机构号无法对应,请重新输入！");
									return;
								}
							}
							if(!"".equals(NBZH) && "".equals(SFACNO)){
								CONIDVal= NBZH;
							}
						}
						
						
						// 替换条件
						if (CONIDVal == null)
							CONIDVal = "";
						if (SQL.toString().indexOf(CONMX_NO) != -1) {
							String temSQL = SQL.toString().replaceAll(CONMX_NO, CONIDVal);
							SQL.delete(0, SQL.length());
							SQL.append(temSQL);
						}

						if (ConMap.get("INPUT_VALUE") != null)
							INPUT_VALUE = ConMap.get("INPUT_VALUE").toString();

						if (CONIDVal != null && !CONIDVal.equals("")) {
							CONIDVal = CONIDVal.trim();
							CONS.append(" form.");
							CONS.append(CONID);
							CONS.append(".value='");
							CONS.append(CONIDVal);
							CONS.append("';");
							// 应为机构有一个隐藏的ID
							if ("机构".equals(INPUT_VALUE)) {
								CONS.append(" form.N");
								CONS.append(CONID);
								CONS.append(".value='");
								String NCONID = "N" + CONID;
								CONS.append(request.getParameter(NCONID.trim()));
								CONS.append("';");
							}
						}

						// 字符串，日期，数字
						if (ISJOIN != null && ISJOIN.equals("是")) {
							if (CONMX_TYPE.equals("字符串")) {
								if (CONIDVal != null && !CONIDVal.equals("") ) {
									
									CONIDVal = CONIDVal.trim();
									if( "B.ORGNO".equalsIgnoreCase(PHYSICAL_NAME)){
										LEFT_SQL.append(" and  B.ORGNO = '");
										LEFT_SQL.append(CONIDVal);
										LEFT_SQL.append("'");
									}else if( "WBZH".equalsIgnoreCase(PHYSICAL_NAME)){
										SQL.append(" ");
									}else{
										
										SQL.append(" and  ");
										SQL.append(PHYSICAL_NAME);
										SQL.append(" ");
										SQL.append(OPERATOR);
										if (OPERATOR.equals("LIKE")) {
											SQL.append(" '%");
											SQL.append(CONIDVal);
											SQL.append("%'");
										} else if (OPERATOR.equals("LIKEL")) {
											SQL.append(" '%");
											SQL.append(CONIDVal);
											SQL.append(" '");
										} else if (OPERATOR.equals("LIKER")) {
											SQL.append(" '");
											SQL.append(CONIDVal);
											SQL.append(" %'");
										} else {
											SQL.append(" '");
											SQL.append(CONIDVal);
											SQL.append("'");
										}
									}
									
								}
							
							} else if (CONMX_TYPE.equals("日期")) {
								if (CONIDVal != null && !CONIDVal.equals("")) {
									CONIDVal = CONIDVal.trim();
									SQL.append(" and  ");
									SQL.append(PHYSICAL_NAME);
									SQL.append(OPERATOR);
									SQL.append(" '");
									SQL.append(CONIDVal);
									SQL.append(" '");
								}

							} else if (CONMX_TYPE.equals("数字")) {
								if (CONIDVal != null && !CONIDVal.equals("")) {
									CONIDVal = CONIDVal.trim();
									SQL.append(" and  ");
									SQL.append(PHYSICAL_NAME);
									SQL.append(OPERATOR);
									SQL.append(" ");
									SQL.append(CONIDVal);
									SQL.append(" ");

								}

							}else if (CONMX_TYPE.equals("文本")) {
								if (CONIDVal != null && !CONIDVal.equals("")) {
									String [] conidval = CONIDVal.split("\r");
									SQL.append(" and  ( 1 != 1");
									for(int j =0; j < conidval.length ; j++){
										SQL.append(" or ");
										SQL.append(PHYSICAL_NAME);
										SQL.append(OPERATOR);
										SQL.append(" '");
										SQL.append(conidval[j].trim());
										SQL.append("' ");
									}
									SQL.append(" ) ");
								}

							}
						}

					}
					
						if (ModMsp.get("ORDER_SQL") != null
								&& !ModMsp.get("ORDER_SQL").equals("")) {
							SQL.append(" ORDER BY ");
							SQL.append(ModMsp.get("ORDER_SQL"));
						}
					//SQL.append(" limit 1000");
                   
					params.put("LEFT_SQL", LEFT_SQL.toString());
					params.put("REP_SQL", SQL.toString());
					params.put("CONS_PAR", CONS.toString());
				
				}
				
				
				
				//获取大数据查询sql语句 关联BP_ORGAN和KZH_NBZH   SFTAB分年
				public void getLeftJoinThreeSFTAB(Map params, HttpServletRequest request) {

					// 获得模板信息
					String REPMOD_NO = (String) params.get("REPMOD_NO");
					
				
					List ModList = this.findMbList(params);
					Map ModMsp = (Map) ModList.get(0);
					params.put("flg", ModMsp.get("ORGSQL"));
					params.put("tzmbbm", ModMsp.get("CHECKIN_DEPT"));
					params.put("resource", ModMsp.get("RESOURCE"));
					// 获得条件信息
					List ConList = this.findConList(params);
					int NumCon = ConList.size();
					// 组装SQL
					StringBuffer SQL = new StringBuffer("");
					StringBuffer LEFT_SQL = new StringBuffer("");
					StringBuffer CONS = new StringBuffer("");
					
					String NBZH= "";
					String SFACNO= "";
					for (int i = 0; i < NumCon; i++) {
						Map m = (Map) ConList.get(i);
						String PHYSICAL_NAME = (String) m.get("PHYSICAL_NAME");
						String CONMX_NO = (String) m.get("CONMX_NO");
						String CONID = "M" + CONMX_NO;
						String CONIDVal = request.getParameter(CONID.trim());
						
						if("WBZH".equalsIgnoreCase(PHYSICAL_NAME) && CONIDVal != null && !"".equals(CONIDVal)){
							NBZH = this.getNBZH(request.getParameter(CONID));
							if("".equals(NBZH)){
								params.put("errorMsg", "输入的卡/折号未找到相应内部账号，请重新输入！");
								return;
							}
						}
						if("SFACNO".equalsIgnoreCase(PHYSICAL_NAME) && CONIDVal != null && !"".equals(CONIDVal)){
							SFACNO = request.getParameter(CONID);
						}
					}
					LEFT_SQL.append("Select * from ereport.bp_organ as b where 1=1");
            


                    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
					String date1 = request.getParameter("M201211070001009");//开始日期
					String date2 = request.getParameter("M201211070001008");//结束日期
					if(date1 == null || "".equals(date1)){
						params.put("errorMsg", "开始日期不得为空！");
						return;
					}
					if(date1 == null || "".equals(date1)){
						params.put("errorMsg", "结束日期不得为空！");
						return;
					}
					
                	Map<String, Map<String, Date>> map;
					try {
						if(format.parse(date1).after(format.parse(date2))){
							params.put("errorMsg", "开始日期不得大于结束日期！");
							return;
						}
						if(format.parse(date2).after( new Date())){
							params.put("errorMsg", "选择日期不得大于当前日期！");
							return;
						}
						map = DateUtil.getStartStopBetween(format.parse(date1), format.parse(date2));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						params.put("errorMsg", "时间格式解析错误！");
						return;
					}
            		Set<String> keySet = map.keySet();
            	
            		for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();) {
            			String key = (String) iterator.next();
            			Map<String,Date> yearMap = map.get(key);
            			String startDate = format.format(yearMap.get("start"));
            			String endDate = format.format(yearMap.get("stop"));
            		
            			SQL.append(" select ");
       					SQL.append(ModMsp.get("SELECT_SQL"));
       					SQL.append(" from  ");
       					String FROM_SQL = (String) ModMsp.get("FROM_SQL");
       					// 动态数据表判断是否存在----------------------------
       					Pattern p = Pattern.compile("\\[(.*?)\\]");
       				    Matcher m = p.matcher(FROM_SQL);
            			  while (m.find()) {
         				    	String table = m.group(1);
         				    	FROM_SQL = FROM_SQL.replace(table, table+"_"+key);
         				    }

         				    FROM_SQL = FROM_SQL.replaceAll("\\[", "");  
         				    FROM_SQL = FROM_SQL.replaceAll("\\]", ""); 
         				    FROM_SQL = FROM_SQL.replaceAll("#", "'"); 
         				    
         				  
         					//-------------------------------------------------
         				    FROM_SQL =  FROM_SQL.substring(0,FROM_SQL.indexOf("left"));
         					SQL.append(FROM_SQL);
         					SQL.append(" where  ");
         					if (ModMsp.get("WHERE_SQL") != null
         							&& !ModMsp.get("WHERE_SQL").equals("")) {
         						String WHERE_SQL = (String) ModMsp.get("WHERE_SQL");
         						WHERE_SQL = WHERE_SQL.replaceAll("#", "'");
         						SQL.append(ModMsp.get("WHERE_SQL"));
         					} else {
         						SQL.append(" 1=1  ");
         					}

         					// 初始化
         					String groupvalue = request.getParameter("groupvalue");
         					if (groupvalue != null && !groupvalue.equals("")) {
         						CONS.append(" var vfrm=document.getElementById('" + groupvalue
         								+ "'); ");
         						CONS.append("  vfrm.onclick();");

         					}

         					// 拼条件
         					for (int i = 0; i < NumCon; i++) {
         						Map ConMap = (Map) ConList.get(i);
         						String PHYSICAL_NAME = (String) ConMap.get("PHYSICAL_NAME");
         						String CONMX_NO = (String) ConMap.get("CONMX_NO");
         						String CONID = "M" + CONMX_NO;
         						String CONIDVal = request.getParameter(CONID.trim());
         						String CONMX_TYPE = (String) ConMap.get("CONMX_TYPE");
         						String OPERATOR = (String) ConMap.get("OPERATOR");
         						String ISJOIN = ConMap.get("ISJOIN").toString();
         						String INPUT_VALUE = "";
         						
         						if( "SFACNO".equalsIgnoreCase(PHYSICAL_NAME)){
         							if(!"".equals(NBZH) && !"".equals(SFACNO)){
         								if(!NBZH.equals(SFACNO)){
         									params.put("errorMsg", "输入的卡折号与内部机构号无法对应,请重新输入！");
         									return;
         								}
         							}
         							if(!"".equals(NBZH) && "".equals(SFACNO)){
         								CONIDVal= NBZH;
         							}
         						}
         						
         						
         						// 替换条件
         						if (CONIDVal == null)
         							CONIDVal = "";
         						if (SQL.toString().indexOf(CONMX_NO) != -1) {
         							String temSQL = SQL.toString().replaceAll(CONMX_NO, CONIDVal);
         							SQL.delete(0, SQL.length());
         							SQL.append(temSQL);
         						}

         						if (ConMap.get("INPUT_VALUE") != null)
         							INPUT_VALUE = ConMap.get("INPUT_VALUE").toString();

         						if (CONIDVal != null && !CONIDVal.equals("")) {
         							CONIDVal = CONIDVal.trim();
         							CONS.append(" form.");
         							CONS.append(CONID);
         							CONS.append(".value='");
         							CONS.append(CONIDVal);
         							CONS.append("';");
         							// 应为机构有一个隐藏的ID
         							if ("机构".equals(INPUT_VALUE)) {
         								CONS.append(" form.N");
         								CONS.append(CONID);
         								CONS.append(".value='");
         								String NCONID = "N" + CONID;
         								CONS.append(request.getParameter(NCONID.trim()));
         								CONS.append("';");
         							}
         						}

         						// 字符串，日期，数字
         						if (ISJOIN != null && ISJOIN.equals("是")) {
         							if (CONMX_TYPE.equals("字符串")) {
         								if (CONIDVal != null && !CONIDVal.equals("") ) {
         									
         									CONIDVal = CONIDVal.trim();
         									if( "B.ORGNO".equalsIgnoreCase(PHYSICAL_NAME)){
         										LEFT_SQL.append(" and  B.ORGNO = '");
         										LEFT_SQL.append(CONIDVal);
         										LEFT_SQL.append("'");
         									}else if( "WBZH".equalsIgnoreCase(PHYSICAL_NAME)){
         										SQL.append(" ");
         									}else{
         										SQL.append(" and  ");
         										SQL.append(PHYSICAL_NAME);
         										SQL.append(" ");
         										SQL.append(OPERATOR);
         										if (OPERATOR.equals("LIKE")) {
         											SQL.append(" '%");
         											SQL.append(CONIDVal);
         											SQL.append("%'");
         										} else if (OPERATOR.equals("LIKEL")) {
         											SQL.append(" '%");
         											SQL.append(CONIDVal);
         											SQL.append(" '");
         										} else if (OPERATOR.equals("LIKER")) {
         											SQL.append(" '");
         											SQL.append(CONIDVal);
         											SQL.append(" %'");
         										} else {
         											SQL.append(" '");
         											SQL.append(CONIDVal);
         											SQL.append("'");
         										}
         									}
         									
         								}
         							
         							} else if (CONMX_TYPE.equals("日期")) {
         								if (CONIDVal != null && !CONIDVal.equals("")) {
         									CONIDVal = CONIDVal.trim();
         									SQL.append(" and  ");
         									SQL.append(PHYSICAL_NAME);
         									SQL.append(OPERATOR);
         									SQL.append(" '");
         									SQL.append(CONIDVal);
         									SQL.append(" '");
         								}

         							} else if (CONMX_TYPE.equals("数字")) {
         								if("SFTRDT".equalsIgnoreCase(PHYSICAL_NAME)){
     										if("M201211070001009".equals(CONID)){
     											SQL.append(" AND SFTRDT >= " + startDate);
     										}else{
     											SQL.append(" AND SFTRDT <= " + endDate);
     										}
     									}
        							}else if (CONMX_TYPE.equals("文本")) {
         								if (CONIDVal != null && !CONIDVal.equals("")) {
         									String [] conidval = CONIDVal.split("\r");
         									SQL.append(" and  ( 1 != 1");
         									for(int j =0; j < conidval.length ; j++){
         										SQL.append(" or ");
         										SQL.append(PHYSICAL_NAME);
         										SQL.append(OPERATOR);
         										SQL.append(" '");
         										SQL.append(conidval[j].trim());
         										SQL.append("' ");
         									}
         									SQL.append(" ) ");
         								}

         							}
         						}

         					}
         					if (ModMsp.get("ORDER_SQL") != null
       								&& !ModMsp.get("ORDER_SQL").equals("")) {
       							SQL.append(" ORDER BY ");
       							SQL.append(ModMsp.get("ORDER_SQL"));
       						}
       					//SQL.append(" limit 1000");
         			
         					SQL.append(";");
            		}
                	
            	
					
            		params.put("LEFT_SQL", LEFT_SQL.toString());
            		params.put("REP_SQL", SQL.toString());
					params.put("CONS_PAR", CONS.toString());
				
				
				}
				
	/**
	 * 通过外部账号 获取内部账户
	 * @param WBZH
	 * @return
	 */
	public String getNBZH(String WBZH){
		String NBZH = "";
		Connection conn = null;
		Statement stat = null;
		ResultSet  rsQry_1 = null;
		try{
			//Connection conn=DBManager.getConn();
			 conn = DBManager.getInstance().getConnection();  
			 stat = conn.createStatement();
			
			String getNBZHSql = "SELECT * FROM ODS.KZH_NBZH k WHERE k.WBZH = '" +WBZH+"'";
			 rsQry_1 = stat.executeQuery(getNBZHSql);
			if(rsQry_1.next()){
				NBZH = rsQry_1.getString("NBZH");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rsQry_1 != null){
				try {
					rsQry_1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
			DBManager.closeConn(conn,stat);
			return NBZH;
		}
	}

	public String getREPCON(String REPMOD_NO, List ConList,
			HttpServletRequest request) {

		Map params = new HashMap();
		params.put("REPMOD_NO", REPMOD_NO);
		// 获得条件信息
		List HeaList = this.findHeaList(params);
		int NumHea = HeaList.size();
		int NumCon = ConList.size();
		// 组装显示抬头
		StringBuffer Str = new StringBuffer("");
		for (int i = 0; i < NumHea; i++) {
			Map HeaMap = (Map) HeaList.get(i);
			String HEAD_NAME = "";
			if (HeaMap.get("HEAD_NAME") != null)
				HEAD_NAME = (String) HeaMap.get("HEAD_NAME");

			String CONMX_NO = "";
			if (HeaMap.get("CONMX_NO") != null)
				CONMX_NO = HeaMap.get("CONMX_NO").toString();
			String HEARES = "";
			if (HeaMap.get("HEARES") != null)
				HEARES = HeaMap.get("HEARES").toString();
			if (HEARES.equals("条件页面")) {
				String CONID = "M" + CONMX_NO;
				String CONIDVal = request.getParameter(CONID.trim());
				if (CONIDVal != null && !CONIDVal.equals("")) {
					Str.append(HEAD_NAME);
					Str.append(":");
					Str.append(CONIDVal);
					Str.append("&nbsp&nbsp&nbsp");
				}

			} else if (HEARES.equals("SQL")) {

				String HEASQL = HeaMap.get("HEASQL").toString();

				for (int j = 0; j < NumCon; j++) {
					Map ConMap = (Map) ConList.get(j);
					String tempCONMX_NO = ConMap.get("CONMX_NO").toString();
					if (HEASQL.indexOf(tempCONMX_NO) > 0) {
						String tempCONID = "M" + tempCONMX_NO;
						String tempVal = request.getParameter(tempCONID.trim());
						HEASQL = HEASQL.replaceAll(tempCONMX_NO, tempVal);
					}
				}
				Map varCon = new HashMap();
				varCon.put("SerSQL", HEASQL);
				Map Convalue = this.getConVal(varCon);
				String CONIDVal = "";
				if (Convalue != null && Convalue.get("CONVAL") != null)
					CONIDVal = Convalue.get("CONVAL").toString();
				if (CONIDVal != null && !CONIDVal.equals("")) {
					Str.append(HEAD_NAME);
					Str.append(":");
					Str.append(CONIDVal);
					Str.append("&nbsp&nbsp&nbsp");
				}
			}

		}
		return Str.toString();
	}
}

package com.yitong.commons.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import com.yitong.commons.dao.BaseDao;
import com.yitong.commons.model.Consts;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.model.inner.ListPage;
import com.yitong.commons.util.DBManager;

/**
 * 
 * <p>
 * service层基类，需要注入Dao对象
 * </p>
 * 
 */
public class BaseService implements IBaseService {
	
	protected static final Logger logger = Logger.getLogger(BaseService.class);
	
	
	private BaseDao ibatisDao;
	private BaseDao ibatisOds;
	
	@SuppressWarnings("unchecked")
	public List findListOds(String statementName, Object paramMap) {
		return ibatisOds.queryForList(statementName, paramMap);
	}
	@SuppressWarnings("unchecked")
	public IListPage pageQueryOds(String queryStatementName,
			String countStatementName, Map paramMap, int pageNo, int pageSize) {
		// 计数
		int total = ibatisOds.queryForInt(countStatementName, paramMap);
		int _pageNo = (total - 1) / pageSize + 1;
		if (_pageNo < pageNo) {
			pageNo = _pageNo;// 页码校验
		}
		int start = (pageNo - 1) * pageSize;
		int end = pageNo * pageSize;
		paramMap.put("startRow", new Integer(start));
		paramMap.put("endRow", new Integer(end));
		paramMap.put("pageSize", new Integer(pageSize));
		// 取单页结果集
		List data = ibatisOds.queryForList(queryStatementName, paramMap);
		return new ListPage(start, total, pageSize, data);
	}
	
	@SuppressWarnings("unchecked")
	public IListPage pageQueryBigdata(List<Map> COLList, Map params, int pageNo, int pageSize) throws SQLException  {
		//Class.forName("org.apache.phoenix.jdbc.PhoenixDriver").newInstance();
		//Properties props = new Properties();
		//props.put("phoenix.spool.directory", "/tmp");
		Connection conn = null;
		Statement stat = null;
		ResultSet rsQry = null;
		ResultSet rsCont = null;
		String REP_SQL =(String) params.get("SerSQL");
		//String CouSQL =(String) params.get("CouSQL");
		try {
			//conn = DriverManager.getConnection("jdbc:phoenix:10.0.137.84,10.0.137.86,10.0.137.87,10.0.137.88",props);
			   // conn =  DBManager.ds.getConnection();
			 conn = DBManager.getInstance().getConnection();  
			//conn=DBManager.getConn();
		    stat = conn.createStatement();
		//AND SFACNO = '3405171529010428000104476'
		/*String REP_SQL = " SELECT SFTRDT, SFTRCD , SFAMCD, SFTRAM , SFACBL, SFACCN , SFACNO, SFTLSQ , SFBFJG , SFBFZH, SFBFNM, SFDFJG, SFDFZH, SFDFNM, SFGSXT"+
					" FROM HISUSR.app_his_SFTAB p  LEFT JOIN ereport.bp_organ AS b ON substr (p.SFACNO, 1, 10) = B.WD_ORGNO AND B.ORGNO = '3400008886' "+
					" WHERE 1 = 1 AND SFTRDT >= 20130801 AND SFTRDT <= 20150831 AND SFACNO = '3405171529010428000104476' order by SFTRDT,SFDATE,SFTRTM limit 1000";
		
		String CouSQL = " SELECT count(*) from (select p.row "+
				 " FROM HISUSR.app_his_SFTAB p  LEFT JOIN ereport.bp_organ AS b ON substr (p.SFACNO, 1, 10) = B.WD_ORGNO AND B.ORGNO = '3400008886' "+
				 " WHERE 1 = 1  AND SFTRDT >= 20130801 AND SFTRDT <= 20150831 AND SFACNO = '3405171529010428000104476'  limit 1000) s";
		*/
		 rsQry = stat.executeQuery(REP_SQL);
		// rsCont =  stat.executeQuery(CouSQL);
		List<Map> dataList = new ArrayList<Map>();
		int total = 0;
		/*if(rsCont.next()){
			total = rsCont.getInt(1);
		}*/
		
		int k = 0;
		while(rsQry.next()){
			Map dataMap = new HashMap();
			//循环遍历所有列名，得到每一行所有列值
			for(Map colMap : COLList){
				String keyName = (String) colMap.get("PHYSICAL_NAME");
				dataMap.put(keyName, rsQry.getString(keyName));
			}
			k++;
			dataMap.put("RN", k);
			// 将得到的一行数据信息保存到list中
			dataList.add(dataMap);
		}
		
		total = dataList.size();
		// _pageNo总页数
		// pageNo当前页
		int _pageNo = (total - 1) / pageSize + 1;
		if (_pageNo < pageNo) {
			pageNo = _pageNo;
		}
		int start = (pageNo - 1) * pageSize;
		int end = 0;
		if(_pageNo == pageNo){
			end = total;
		}else{
			end = pageNo * pageSize;
		}
		
		List<Map> data = dataList.subList(start, end);
		
//		long t1 = System.currentTimeMillis();
//		while(rs.next()) {
//			System.out.println(rs.getString("custName"));
//		}
//		System.out.println(System.currentTimeMillis() - t1);
		
		return new ListPage(start, total, pageSize, data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(rsQry!= null){
				rsQry.close();
			}
			if(rsCont != null){
				rsCont.close();
			}
			if(stat !=null){
				stat.close();
			}
			DBManager.closeConn(conn,stat);
		}
		
	}
	
	
	public IListPage pageQueryBigdataSFTAB(List<Map> COLList, Map params, int pageNo, int pageSize) throws SQLException  {
	
		Connection conn = null;
		Statement stat = null;
		ResultSet rsQry = null;
		ResultSet rsCont = null;
		String REP_SQL =(String) params.get("SerSQL");
		String[] LIST_SQL = REP_SQL.split(";");
		try {
			int k = 0;
			int total = 0;
			List<Map> dataList = new ArrayList<Map>();
			 conn = DBManager.getInstance().getConnection();  
		    stat = conn.createStatement();
		    for(int i =0 ;i<LIST_SQL.length;i++){
		    	System.out.println("LIST_SQL_"+i+"---------------------->"+LIST_SQL[i]);
		    	 rsQry = stat.executeQuery(LIST_SQL[i]);
		    	 while(rsQry.next()){
		 			Map dataMap = new HashMap();
		 			//循环遍历所有列名，得到每一行所有列值
		 			for(Map colMap : COLList){
		 				String keyName = (String) colMap.get("PHYSICAL_NAME");
		 				dataMap.put(keyName, rsQry.getString(keyName));
		 			}
		 			k++;
		 			dataMap.put("RN", k);
		 			// 将得到的一行数据信息保存到list中
		 			dataList.add(dataMap);
		 		}
		    }
		
	
		total = dataList.size();
		// _pageNo总页数
		// pageNo当前页
		int _pageNo = (total - 1) / pageSize + 1;
		if (_pageNo < pageNo) {
			pageNo = _pageNo;
		}
		int start = (pageNo - 1) * pageSize;
		int end = 0;
		if(_pageNo == pageNo){
			end = total;
		}else{
			end = pageNo * pageSize;
		}
		
		List<Map> data = dataList.subList(start, end);
		
		return new ListPage(start, total, pageSize, data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(rsQry!= null){
				rsQry.close();
			}
			if(rsCont != null){
				rsCont.close();
			}
			if(stat !=null){
				stat.close();
			}
			DBManager.closeConn(conn,stat);
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public IListPage pageQueryBigdataByJoin(String REPMOD_NO,List<Map> COLList, Map params, int pageNo, int pageSize) throws SQLException {
		Connection conn = null;
		ResultSet rsQry_1 = null;
		ResultSet rsQry_2 = null;
		Statement stat = null;
		String REP_SQL =(String) params.get("SerSQL");
		String JOIN_SQL =(String) params.get("JOIN_SQL");
		try {
			//conn = DriverManager.getConnection("jdbc:phoenix:10.0.137.84,10.0.137.86,10.0.137.87,10.0.137.88",props);
			 conn = DBManager.getInstance().getConnection();  
	
		 stat = conn.createStatement();
		
	/*	String SQL_1  ="SELECT SFTRDT, SFTRCD , SFAMCD, SFTRAM , SFACBL, SFACCN , SFACNO, SFTLSQ , SFBFJG , SFBFZH, SFBFNM, SFDFJG, SFDFZH, SFDFNM, SFGSXT"+
					" FROM HISUSR.app_his_SFTAB p WHERE 1 = 1 AND SFTRDT >= 20130801 AND SFTRDT <= 20150831 AND (SFACNO ='3401030217010428000412371'  or SFACNO ='3401037539010428000051244' or SFACNO ='3401017943010428000058441' or SFACNO = '3401030301010428000401179' or SFACNO ='3415726329010428000092301'  or SFACNO ='3401020425010428000290306'or SFACNO ='3401010079010428000373658' or SFACNO ='3401020333010428000484013' or SFACNO ='3401030237010428000118649' or SFACNO ='3415726329010428000092301' or SFACNO ='3415726307010428000226377' or SFACNO ='3401010145010428000350082'  or SFACNO ='3401030255010428000557016') order by SFTRDT,SFDATE,SFTRTM";
		String SQL_2  ="SELECT b. WD_ORGNO FROM ereport.bp_organ  b WHERE b.ORGNO = '3400008886' ";
	*/
		 rsQry_1 = stat.executeQuery(REP_SQL);
		 rsQry_2 = stat.executeQuery(JOIN_SQL);
		
		
		List<Map> dataList_1 = new ArrayList<Map>();
		List<Map> dataList_2 = new ArrayList<Map>();
		
		//dataList_1与dataList_2合并后的结果集
		List<Map> dataList_3 = new ArrayList<Map>();
		
		while(rsQry_1.next()){
			Map<String, String> dataMap = new HashMap<String, String>();
			//循环遍历所有列名，得到每一行所有列值
			for(Map colMap : COLList){
				String keyName = (String) colMap.get("PHYSICAL_NAME");
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
			if(dataList_3.size() == 1000){
				break;
			}
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
		int total = dataList_3.size();
		// _pageNo总页数
		// pageNo当前页
		int _pageNo = (total - 1) / pageSize + 1;
		if (_pageNo < pageNo) {
			pageNo = _pageNo;
		}
		int start = (pageNo - 1) * pageSize;
		int end = 0;
		if(_pageNo == pageNo){
			end = total;
		}else{
			end = pageNo * pageSize;
		}
		
		//分页后的结果集
		List<Map> data = dataList_3.subList(start, end);
		
		return new ListPage(start, total, pageSize, data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(rsQry_1!= null){
				rsQry_1.close();
			}
			if(rsQry_2 != null){
				rsQry_2.close();
			}
			
			if(stat !=null){
				stat.close();
			}
			DBManager.closeConn(conn,stat);
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public IListPage pageQueryBigdataByJoinSFTAB(String REPMOD_NO,List<Map> COLList, Map params, int pageNo, int pageSize) throws SQLException {
		Connection conn = null;
		ResultSet rsQry_1 = null;
		ResultSet rsQry_2 = null;
		Statement stat = null;
		String REP_SQL =(String) params.get("SerSQL");
		String JOIN_SQL =(String) params.get("JOIN_SQL");
		
		String[] LIST_SQL = REP_SQL.split(";");
		try {
			 List<Map> dataList_1 = new ArrayList<Map>();
			 List<Map> dataList_2 = new ArrayList<Map>();
			//dataList_1与dataList_2合并后的结果集
			List<Map> dataList_3 = new ArrayList<Map>();
			
			 conn = DBManager.getInstance().getConnection();  
			 stat = conn.createStatement();
			
			 rsQry_2 = stat.executeQuery(JOIN_SQL);
			
			 for(int i =0 ;i<LIST_SQL.length;i++){
				 	System.out.println("LIST_SQL"+ i +"------------>"+LIST_SQL[i]);
				 	rsQry_1 = stat.executeQuery(LIST_SQL[i]);

					while(rsQry_1.next()){
						Map<String, String> dataMap = new HashMap<String, String>();
						//循环遍历所有列名，得到每一行所有列值
						for(Map colMap : COLList){
							String keyName = (String) colMap.get("PHYSICAL_NAME");
							dataMap.put(keyName, rsQry_1.getString(keyName));
						}
						if("201211070001".equals(REPMOD_NO)  ){
							dataMap.put("SFACNO", rsQry_1.getString("SFACNO"));
						}
						// 将得到的一行数据信息保存到list中
						dataList_1.add(dataMap);
					}
			}
			
		
		Map<String, String> dataMap_2 = new HashMap<String, String>();
		while(rsQry_2.next()){
			dataMap_2.put(rsQry_2.getString("WD_ORGNO"), rsQry_2.getString("WD_ORGNO"));
		}
		
		
		int k = 0;
		for(Map m : dataList_1){
			if(dataList_3.size() == 1000){
				break;
			}
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
		int total = dataList_3.size();
		// _pageNo总页数
		// pageNo当前页
		int _pageNo = (total - 1) / pageSize + 1;
		if (_pageNo < pageNo) {
			pageNo = _pageNo;
		}
		int start = (pageNo - 1) * pageSize;
		int end = 0;
		if(_pageNo == pageNo){
			end = total;
		}else{
			end = pageNo * pageSize;
		}
		
		//分页后的结果集
		List<Map> data = dataList_3.subList(start, end);
		
		return new ListPage(start, total, pageSize, data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(rsQry_1!= null){
				rsQry_1.close();
			}
			if(rsQry_2 != null){
				rsQry_2.close();
			}
			
			if(stat !=null){
				stat.close();
			}
			DBManager.closeConn(conn,stat);
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public IListPage pageQueryBigdataByJoin2(String REPMOD_NO,List<Map> COLList, Map params, int pageNo, int pageSize) throws  SQLException {
		Connection conn = null;
		ResultSet rsQry_1 = null;
		ResultSet rsQry_2 = null;
		ResultSet rsQry_3 = null;
		Statement stat = null;
		String REP_SQL =(String) params.get("SerSQL");
		String JOIN_SQL =(String) params.get("JOIN_SQL");
		String JOIN_SQL2 =(String) params.get("JOIN_SQL2");
		try {
			//conn = DriverManager.getConnection("jdbc:phoenix:10.0.137.84,10.0.137.86,10.0.137.87,10.0.137.88",props);
			 conn = DBManager.getInstance().getConnection();  
	
		 stat = conn.createStatement();
		
	/*	String SQL_1  ="SELECT SFTRDT, SFTRCD , SFAMCD, SFTRAM , SFACBL, SFACCN , SFACNO, SFTLSQ , SFBFJG , SFBFZH, SFBFNM, SFDFJG, SFDFZH, SFDFNM, SFGSXT"+
					" FROM HISUSR.app_his_SFTAB p WHERE 1 = 1 AND SFTRDT >= 20130801 AND SFTRDT <= 20150831 AND (SFACNO ='3401030217010428000412371'  or SFACNO ='3401037539010428000051244' or SFACNO ='3401017943010428000058441' or SFACNO = '3401030301010428000401179' or SFACNO ='3415726329010428000092301'  or SFACNO ='3401020425010428000290306'or SFACNO ='3401010079010428000373658' or SFACNO ='3401020333010428000484013' or SFACNO ='3401030237010428000118649' or SFACNO ='3415726329010428000092301' or SFACNO ='3415726307010428000226377' or SFACNO ='3401010145010428000350082'  or SFACNO ='3401030255010428000557016') order by SFTRDT,SFDATE,SFTRTM";
		String SQL_2  ="SELECT b. WD_ORGNO FROM ereport.bp_organ  b WHERE b.ORGNO = '3400008886' ";
	*/
		 rsQry_1 = stat.executeQuery(REP_SQL);
		 rsQry_2 = stat.executeQuery(JOIN_SQL);
		 rsQry_3 = stat.executeQuery(JOIN_SQL2);
		
		
		List<Map> dataList_1 = new ArrayList<Map>();
		List<Map> dataList_2 = new ArrayList<Map>();
		List<Map> dataList_3 = new ArrayList<Map>();
		
		//获取所欲ORGAN表的机构号-机构名称对应关系
		Map<String, String> dataMap_3 = new HashMap<String, String>();
		while(rsQry_3.next()){
			//循环遍历所有列名，得到每一行所有列值
			dataMap_3.put(rsQry_3.getString("ORG_NO"),rsQry_3.getString("ORG_NAME"));
		}
		
		while(rsQry_1.next()){
			Map<String, String> dataMap = new HashMap<String, String>();
			//循环遍历所有列名，得到每一行所有列值
			for(Map colMap : COLList){
				String keyName = (String) colMap.get("PHYSICAL_NAME");
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
		
		
		int k= 0;
		for(Map m : dataList_1){
			if(dataList_3.size() == 1000){
				break;
			}
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
		int total = dataList_3.size();
		// _pageNo总页数
		// pageNo当前页
		int _pageNo = (total - 1) / pageSize + 1;
		if (_pageNo < pageNo) {
			pageNo = _pageNo;
		}
		int start = (pageNo - 1) * pageSize;
		int end = 0;
		if(_pageNo == pageNo){
			end = total;
		}else{
			end = pageNo * pageSize;
		}
		
		//分页后的结果集
		List<Map> data = dataList_3.subList(start, end);
		
		return new ListPage(start, total, pageSize, data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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
	
	
	
	
	public BaseDao getIbatisOds() {
		return ibatisOds;
	}

	public void setIbatisOds(BaseDao ibatisOds) {
		this.ibatisOds = ibatisOds;
	}

	public BaseDao getIbatisDao() {
		return ibatisDao;
	}

	public void setIbatisDao(BaseDao ibatisDao) {
		this.ibatisDao = ibatisDao;
	}

	/*
	 * 新增
	 */
	public Object insert(String statementName, Object paramMap) {
		return ibatisDao.insert(statementName, paramMap);
	}

	/*
	 * 更新
	 */
	public boolean update(String statementName, Object paramMap) {
		return ibatisDao.update(statementName, paramMap);
	}
	
	@SuppressWarnings("unchecked")
	public Map loadByType(String statementName, Map paramMap) {
		return ibatisDao.loadByType(statementName, paramMap);
	}
	
	@SuppressWarnings("unchecked")
	public Map loadBatchImportConfig(String statementName, Map paramMap) {
		return ibatisDao.loadByType(statementName, paramMap);
	}

	/*
	 * 删除
	 */
	public boolean delete(String statementName, Object paramMap) {
		return ibatisDao.delete(statementName, paramMap);
	}
	
	/**
	 * 批量更新 
	 */
	@SuppressWarnings("unchecked")
	public boolean batch4Update(String statementName, List datas) {
		return ibatisDao.batch4Update(statementName, datas);
	}
	
	/**
	 * 批量更新 
	 */
	@SuppressWarnings("unchecked")
	public boolean batch4Update(String statementName, List datas, int batchSize) {
		return ibatisDao.batch4Update(statementName, datas, batchSize);
	}

	/*
	 * 加载单条记录
	 */
	public Object load(String statementName, Object paramMap) {
		return ibatisDao.load(statementName, paramMap);
	}

	/*
	 * 加载多条记录
	 */
	@SuppressWarnings("unchecked")
	public List findList(String statementName, Object paramMap) {
		return ibatisDao.queryForList(statementName, paramMap);
	}
	
	/*
	 * 加载自定义参数
	 */
	@SuppressWarnings("unchecked")
	public List findParamters(String paramType) {
		List rst = (List) ibatisDao.getCaches(paramType);
		if (rst != null) {
			return rst;
		}
		rst = ibatisDao.queryForList("SysvarInfo.findByVarType", paramType);
		ibatisDao.putCaches(paramType, rst);
		return rst;
	}

	public void clearCaches(Object key) {
		ibatisDao.clearCaches(key);
	}

	/**
	 * 加载参数
	 * 
	 * @param varId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map loadParameter(String varId) {
		Map rst = (Map) ibatisDao.getCaches(varId);
		if (rst != null) {
			return rst;
		}
		rst = (Map) ibatisDao.load("SysvarInfo.loadById", varId);
		ibatisDao.putCaches(varId, rst);
		return rst;
	}

	/**
	 * 加载报表
	 * 
	 * @param rptId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map loadReport(String rptId) {
		Map rst = (Map) ibatisDao.getCaches(rptId);
		if (rst != null) {
			return rst;
		}
		rst = (Map) ibatisDao.load("ReportInfo.loadById", rptId);
		ibatisDao.putCaches(rptId, rst);
		return rst;
	}

	/**
	 * 缓存用户
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Map loadUser(String userId) {
		Map rst = (Map) ibatisDao.load("UserInfo.loadById", userId);
		return rst;
	}

	/*
	 * 按默认行数分页
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQuery(String queryStatementName,
			String countStatementName, Map paramMap, int pageNo) {
		return pageQuery(queryStatementName, countStatementName, paramMap,
				pageNo, Consts.PAGE_SIZE);
	}

	/*
	 * 按指定行数分页
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQuery(String queryStatementName,
			String countStatementName, Map paramMap, int pageNo, int pageSize) {
		//总记录数
		int total = ibatisDao.queryForInt(countStatementName, paramMap);
		//总页数
		int _pageNo = (total - 1) / pageSize + 1;
		if (_pageNo < pageNo) {
			pageNo = _pageNo;// 页码校验
		}
		
		int start = (pageNo - 1) * pageSize;//当前页的起始记录
		int end = pageNo * pageSize;//当前页的最后一条记录
		paramMap.put("startRow", new Integer(start));
		paramMap.put("endRow", new Integer(end));
		paramMap.put("pageSize", new Integer(pageSize));
		// 取单页结果集
		List data = ibatisDao.queryForList(queryStatementName, paramMap);
		return new ListPage(start, total, pageSize, data);
	}

	@SuppressWarnings("unchecked")
	public boolean saveLog(Map paramMap) {
		this.insert("TLER.LOGS.insert", paramMap);
		return true;
	}

}

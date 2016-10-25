package com.yitong.app.action.judicial;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yitong.app.service.judicial.DTBBZXService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.util.DateUtil;
import com.yitong.commons.util.ParamUtil;

public class DDBBZXAction extends BaseAction {
	// 交易控制器开关
	int JYKZQ = 0;
	private DTBBZXService DTBBZXService;
	
	public void setDTBBZXService(DTBBZXService service) {
		DTBBZXService = service;
	}

	// 根据模板显示动态条件页面
	public ActionForward toQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 传入模板编号  

		String ModNO = request.getParameter("REPMOD_NO");
		String MKLX = request.getParameter("MKLX");
		String REP_DESC = request.getParameter("REP_DESC");
		String CONS_PAR = request.getParameter("CONS_PAR");
		String REQ_NO = request.getParameter("REQ_NO");
		String backFlg = request.getParameter("backFlg");
		if (REP_DESC == null)
			REP_DESC = "";
		Map params = new HashMap();
		params.put("REPMOD_NO", ModNO);
		// params.put("STATE", "启用");
		List ModList = DTBBZXService.findMbList(params);
		if (ModList.size() == 0) {
			request.setAttribute("msg", "当前选择的模板号不存在或者没有启用！");
			return mapping.findForward("query");
		}
		Map ModMsp = (Map) ModList.get(0);
		List ConList = DTBBZXService.findConList(params);
		if (ConList.size() == 0) {
			request.setAttribute("msg", "当前选择的模板号,还没有定义条件！");
			return mapping.findForward("query");
		}

		request.setAttribute("repName", ModMsp.get("REP_NAME"));
		request.setAttribute("REPMOD_NO", ModNO);
		request.setAttribute("REP_DESC", REP_DESC);
		request.setAttribute("MKLX", MKLX);
		request.setAttribute("backFlg", backFlg);
		request.setAttribute("usrId", request.getParameter("usrId"));
		request.setAttribute("orgNo", request.getParameter("orgNo"));
		request.setAttribute("orgNobf", request.getParameter("orgNo"));
		request.setAttribute("params", request.getParameter("params"));
		request.setAttribute("MSITCD",request.getParameter("MSITCD"));
		request.setAttribute("MSCTNO",request.getParameter("MSCTNO"));
		// 设置条件 和 额外约束
		DTBBZXService.getQureyHtml(ConList, "canedit", request);

		if (MKLX == null)
			MKLX = "";
		if (MKLX.equals("XGSQ")) {
			request.setAttribute("CONS_PAR", CONS_PAR);
			request.setAttribute("REQ_NO", REQ_NO);
		}
		if (MKLX.equals("XZSQ")) {
			return mapping.findForward("query_save");
		} else if (MKLX.equals("XGSQ")) {
			return mapping.findForward("query_edit");
		} else if (MKLX.equals("repList")) {
			return mapping.findForward("query_view");
		} else {
			return mapping.findForward("query_view");

		}

	}

	// 根据申请显示动态条件页面
	public ActionForward toViewREP(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 传入申请编号
		String REQ_NO = request.getParameter("REQ_NO");
		Map RepSQMap = DTBBZXService.load(REQ_NO);
		Map params = new HashMap();
		params.put("REQ_NO", REQ_NO);
		params.put("REPMOD_NO", (String) RepSQMap.get("REPMOD_NO"));
		// params.put("STATE", "启用");
		List ModList = DTBBZXService.findMbList(params);
		if (ModList.size() == 0) {
			request.setAttribute("msg", "当前选择的模板号不存在或者没有启用！");
			return mapping.findForward("query");
		}
		Map ModMsp = (Map) ModList.get(0);
		List ConList = DTBBZXService.findConList(params);
		if (ConList.size() == 0) {
			request.setAttribute("msg", "当前选择的模板号,还没有定义条件！");
			return mapping.findForward("query");
		}
		request.setAttribute("usrId", request.getParameter("usrId"));
		request.setAttribute("orgNo", request.getParameter("orgNo"));
		request.setAttribute("repName", ModMsp.get("REP_NAME"));
		request.setAttribute("REPMOD_NO", (String) RepSQMap.get("REPMOD_NO"));
		// 设置显示条件 和 额外的约束
		DTBBZXService.getQureyHtml(ConList, "readonly", request);
		String CONS_PAR = RepSQMap.get("CONS_PAR") == null ? ""
				: (String) RepSQMap.get("CONS_PAR");
		String REPCON = RepSQMap.get("REPCON") == null ? "" : (String) RepSQMap
				.get("REPCON");
		String REP_SQL = RepSQMap.get("REP_SQL") == null ? ""
				: (String) RepSQMap.get("REP_SQL");

		String MKLX = request.getParameter("MKLX");
		if (MKLX == null)
			MKLX = "";
		request.setAttribute("MKLX", MKLX);
		request.setAttribute("CONS_PAR", CONS_PAR);
		request.setAttribute("REPCON", REPCON);
		request.setAttribute("REP_SQL", REP_SQL);
		request.setAttribute("REQ_NO", REQ_NO);
		return mapping.findForward("query_view");

	}

	// 显示动态展示页面
	public ActionForward toViewREPJG(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		//"201303120001"屏蔽了卡/折号查找内部账号 模板
		String[] regeditModArr = {"201211070001","201211070002","201211070003","201211070004","201211070005","201211070006","201211070007","201211070008","201408060001","201408060002","201408060003","201408060004"};
		
		// 传入历史查询申请号
		String REQ_NO = request.getParameter("REQ_NO");
		String REPCON = request.getParameter("REPCON");
		String repName = request.getParameter("repName");
		String REP_SQL = request.getParameter("REP_SQL");
		String JOIN_SQL = request.getParameter("JOIN_SQL");
		String JOIN_SQL2 = request.getParameter("JOIN_SQL2");
		String REPMOD_NO = request.getParameter("REPMOD_NO");
		String MKLX = request.getParameter("MKLX");
		String backFlg = request.getParameter("backFlg");
		String usrId = request.getParameter("usrId").toString();
		String orgNo = request.getParameter("orgNo").toString();
		String resource = ParamUtil.get(request, "resource","His");
		
		
		// 翻页时不要重新获得SQL
		String SELTYPE = request.getParameter("SELTYPE");
		if (SELTYPE == null)
			SELTYPE = "";
		Map params = new HashMap();
		// 获得查询机构SQL
		params.put("REPMOD_NO", REPMOD_NO);
		
		List ConList = DTBBZXService.findConList(params);
		if (MKLX == null)
			MKLX = "";
		
		String flg = request.getParameter("flg");
		String tzmbbm = request.getParameter("tzmbbm");
		
		
		if (SELTYPE != null && !SELTYPE.equals("paramCover")) {
			Map apar = new HashMap();
			apar.put("REPMOD_NO", REPMOD_NO);
			//连接DB2数据库的SQL语句
			//DTBBZXService.getQureySQL(apar,request);
			//连接大数据的SQL语句
			
			if(!ArrayUtils.contains(regeditModArr, REPMOD_NO)){
				request.setAttribute("msg", "该模板编号未在系统代码内注册!");
				return mapping.findForward(FAILURE);
			}
			//关联BP_ORGAN
			if("201303120001".equals(REPMOD_NO) ||"201408060001".equals(REPMOD_NO)
					||"201408060002".equals(REPMOD_NO) ||"201408060003".equals(REPMOD_NO)
					||"201408060004".equals(REPMOD_NO) ){
				      
				DTBBZXService.getLeftJoinOne(apar,request);
				JOIN_SQL = apar.get("LEFT_SQL").toString();
				
			}//关联BP_ORGAN和ORGAN
			else if("201211070004".equals(REPMOD_NO)||"201211070005".equals(REPMOD_NO)
					||"201211070007".equals(REPMOD_NO) ||"201211070008".equals(REPMOD_NO)){
				DTBBZXService.getLeftJoinTwo(apar,request);
				
				JOIN_SQL = apar.get("LEFT_SQL").toString();
				JOIN_SQL2 = apar.get("LEFT_SQL2").toString();
			}//关联BP_ORGAN和KZH_NBZH
			else if("201211070002".equals(REPMOD_NO)){
				DTBBZXService.getLeftJoinThree(apar,request);
				String error = (String) apar.get("errorMsg");
				if(error != null && !"".equals(error)){
					request.setAttribute("msg", error);
					return mapping.findForward(FAILURE);
				}
				
				JOIN_SQL = apar.get("LEFT_SQL").toString();
			}//SFTAB分表
			else if("201211070001".equals(REPMOD_NO)){
				DTBBZXService.getLeftJoinThreeSFTAB(apar,request);
				String error = (String) apar.get("errorMsg");
				if(error != null && !"".equals(error)){
					request.setAttribute("msg", error);
					return mapping.findForward(FAILURE);
				}
				
				JOIN_SQL = apar.get("LEFT_SQL").toString();
			}
			//无关联 仅适用于对公活期账户交易明细查询  对私活期账户交易明细查询
			else if("201211070006".equals(REPMOD_NO)  ){
				DTBBZXService.getSQL(apar,request);
			}//SFTAB分表
			else if("201211070003".equals(REPMOD_NO)){
				DTBBZXService.getSQLSFTAB(apar,request);
				String error = (String) apar.get("errorMsg");
				if(error != null && !"".equals(error)){
					request.setAttribute("msg", error);
					return mapping.findForward(FAILURE);
				}
			}
			
			REP_SQL = apar.get("REP_SQL").toString();
			flg = String.valueOf(apar.get("flg"));
			tzmbbm = String.valueOf(apar.get("tzmbbm"));
			if("null".equals(flg))flg = "1";
			resource = apar.get("resource").toString();
		}
		//request.setAttribute("flg", flg);
		REPCON = DTBBZXService.getREPCON(REPMOD_NO, ConList, request);

		// 获得显示列 这里要把物理名 截取 只要小数点后面的
		params.clear();
		params.put("REPMOD_NO", REPMOD_NO);
		params.put("ISDISPLAY", "是");
		List COLList = DTBBZXService.findCOLList(params);

		if (COLList == null || COLList.size() == 0) {
			request.setAttribute("dojs", "alert('当前选择的申请号对应的模板没有设置显示列!')");
			return mapping.findForward("list");
		}
		
		// 获得结果集条数（DB2）
		/*String CouSQL = " select  count(1) cnt "
		+ REP_SQL.substring(REP_SQL.indexOf("from"), REP_SQL.length());*/
		// 获得结果集条数（大数据）
		String CouSQL = " select  count(1) cnt from("
				+ REP_SQL + ")";
		
		
		// 打开交易控制器
		// SocketClient client = new SocketClient();
		// if(client.add())
		if (true) {
			int pageNo = ParamUtil.getInt(request, "pageNo", 1);
			params.clear();
			params.put("SerSQL", REP_SQL);
			params.put("JOIN_SQL", JOIN_SQL);
			params.put("JOIN_SQL2", JOIN_SQL2);
			params.put("CouSQL", CouSQL);
			System.out.println("SerSQL================" + REP_SQL);
			System.out.println("JOIN_SQL================" + JOIN_SQL);
			System.out.println("JOIN_SQL2================" + JOIN_SQL2);
			System.out.println("CouSQL================" + CouSQL);
			//System.err.println("SerSQL==" + REP_SQL);
			//System.err.println("CouSQL==" + CouSQL);
			int pageSize = 	"3".equals(flg) ? 200 : 20 ;	
			IListPage page = null;
			try{
				if("His".equals(resource)){
					page = DTBBZXService.findResuListOds(params, pageNo,pageSize);
				}else if("bigData".equals(resource)){
				    page = DTBBZXService.findResuListBigdata(REPMOD_NO,COLList, params, pageNo, pageSize);
				}else{
					page = DTBBZXService.findResuList(params, pageNo,pageSize);
				}
				
			}catch (Exception e){
				e.printStackTrace();
				request.setAttribute("msg", "表或取的字段不存在或查询结果集过大!");
				return mapping.findForward(FAILURE);
			}
			
			// 关闭交易控制器
			// client.minus();
			// 插入日志表
			Date date = DateUtil.getDate();
			String todayid = String.valueOf(date.getTime());
			String isLog = ParamUtil.get(request, "isLog","TRUE");
			if(isLog.equals("TRUE")){
				Map logMap = new HashMap();
				String today = DateUtil.formatDateToStr(date, "yyyy-MM-dd HH:mm:ss");
				logMap.put("LOGID", usrId + todayid);
				logMap.put("REP_MOD", REPMOD_NO);
				logMap.put("USERID", usrId);
				logMap.put("USTIME", today);
				logMap.put("USORG", orgNo);
				logMap.put("CONDITION", REPCON);
				logMap.put("TYPE", "查询");
				DTBBZXService.insertLog(logMap);
			}
			request.setAttribute("backFlg", backFlg);
			request.setAttribute("isLog", "FALSE");
			request.setAttribute("page", page);
			request.setAttribute("flg", flg);
			request.setAttribute("tzmbbm", tzmbbm);
			request.setAttribute("resource", resource);
			request.setAttribute("COLList", COLList);
			request.setAttribute("Conditions", REPCON);
			request.setAttribute("REQ_NO", REQ_NO);
			request.setAttribute("repName", repName);
			request.setAttribute("MKLX", MKLX);
			request.setAttribute("LOGID", usrId + todayid);
			request.setAttribute("repName", repName);
			request.setAttribute("REQ_NO", REQ_NO);
			request.setAttribute("REPCON", REPCON);
			request.setAttribute("REP_SQL", REP_SQL);
			request.setAttribute("JOIN_SQL", JOIN_SQL);
			request.setAttribute("JOIN_SQL2", JOIN_SQL2);
			request.setAttribute("REPMOD_NO", REPMOD_NO);
			request.setAttribute("usrId", usrId);
			request.setAttribute("orgNo", orgNo);
			request.setAttribute("params", request.getParameter("params"));
			request.setAttribute("MSITCD",request.getParameter("MSITCD"));
			request.setAttribute("MSCTNO",request.getParameter("MSCTNO"));
			return mapping.findForward("list");
		} else {
			request.setAttribute("msg", "当前系统正忙，请稍后再试！");
			return mapping.findForward(FAILURE);
		}

	}

	public ActionForward toComitSQ(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String REQ_NO = request.getParameter("REQ_NO");
		String REPCON = request.getParameter("REPCON");
		String REP_SQL = request.getParameter("REP_SQL");
		String repName = request.getParameter("repName");
		String REPMOD_NO = request.getParameter("REPMOD_NO");
		String usrId = request.getParameter("usrId").toString();
		String orgNo = request.getParameter("orgNo").toString();
		String CONS_PAR = request.getParameter("CONS_PAR").toString();
		String CONJG = request.getParameter("CONJG");
		Map params = new HashMap();
		Date date = DateUtil.getDate();
		String today = DateUtil.formatDateToStr(date, "yyyy-MM-dd HH:mm:ss");
		params.put("REPMOD_NO", REPMOD_NO);
		params.put("REP_DESC", "自动生成的");
		// 设置SQL 和 CONS_PAR
		params.put("REP_SQL", REP_SQL);
		params.put("CONS_PAR", CONS_PAR);
		params.put("REPCON", REPCON);
		params.put("STATE", "冻结");
		params.put("REQ_NO", DTBBZXService.getSeq(date));
		params.put("CHECKIN_USER", usrId);
		params.put("CHECKIN_DEPT", "");
		params.put("CHECKIN_ORGAN", orgNo);
		params.put("CHECKIN_TIME", today);
		params.put("CONJG", CONJG);
		params.put("DQJG", orgNo);
		DTBBZXService.insert(params);
		request.setAttribute("dojs",
		" alert('增加成功！'); window.location.href= '/HisRep/hisrep/ddbbzx.shtml?action=torepList';");
		request.setAttribute("repName", repName);
		request.setAttribute("REQ_NO", REQ_NO);
		request.setAttribute("REPCON", REPCON);
		request.setAttribute("REP_SQL", REP_SQL);
		request.setAttribute("REPMOD_NO", REPMOD_NO);
		request.setAttribute("usrId", usrId);
		request.setAttribute("orgNo", orgNo);
		return mapping.findForward("list");

	}
	
	/** 
     * 通知单
     * @param request 
     * @param response 
     * @return 
     * @throws Exception 
     *//* 
	public ActionForward toTZSSX(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String REP_SQL = request.getParameter("sql");
		String endData = DateUtil.format(new Date(),"yyyyMMdd");
		String startData = REP_SQL.split(">=")[1].trim();
		
		if(startData.indexOf("<=") > -1){
			startData = startData.toUpperCase();
			endData = startData.substring(startData.indexOf("=")+1);
			startData = startData.substring(0,startData.indexOf("A"));
		}else{
			String year = startData.substring(0,4);
			endData = endData.indexOf(year) > -1 ? endData : year + "1231" ;
		}

		Map params = new HashMap();
		params.put("SerSQL", REP_SQL);

		String resource = request.getParameter("resource");
		List list = null;
		try{
			if("His".equals(resource)){
				 list = DTBBZXService.findResultListOds(params);
			}else{
				 list = DTBBZXService.findResultList(params);
			}
		}catch (Exception e){
			request.setAttribute("msg", "表或取的字段不存在!");
			return mapping.findForward(FAILURE);
		}
		
		request.setAttribute("list", list);
		params.clear();
		
		if(list != null && list.size() > 0){
			params  = (Map)list.get(list.size()-1);
		}
		
		params.put("startData",startData);
		params.put("endData",endData);
		request.setAttribute("map", params);
		request.setAttribute("MSITCD",request.getParameter("MSITCD"));
		request.setAttribute("MSCTNO",request.getParameter("MSCTNO"));
		return mapping.findForward("tzssx");
	}
*/
	
	/** 
     * 通知单 大数据
     * @param request 
     * @param response 
     * @return 
     * @throws Exception 
     */ 
	public ActionForward toTZSSX(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		String REP_SQL = request.getParameter("sql");
		String endData = DateUtil.format(new Date(),"yyyyMMdd");
		String startData = REP_SQL.split(">=")[1].trim();
	    String REPMOD_NO =  request.getParameter("REPMOD_NO");
		
		Map params = new HashMap();
		params.put("REPMOD_NO", REPMOD_NO);
		params.put("ISDISPLAY", "是");
		params.put("SerSQL", REP_SQL);
		List<Map> COLList = DTBBZXService.findCOLList(params);
		
		List colnames = new ArrayList();
		List physical = new ArrayList();
		for (Map COLMap : COLList) {
			colnames.add(COLMap.get("COLMX_NAME"));
			physical.add(COLMap.get("PHYSICAL_NAME"));
		}
		
		if(startData.indexOf("<=") > -1){
			startData = startData.toUpperCase();
			endData = startData.substring(startData.indexOf("=")+1,startData.indexOf("ORDER"));
			startData = startData.substring(0,startData.indexOf("A"));
		}else{
			String year = startData.substring(0,4);
			endData = endData.indexOf(year) > -1 ? endData : year + "1231" ;
		}

	
		

		String resource = request.getParameter("resource");
		List list = null;
		try{
			if("His".equals(resource)){
				 list = DTBBZXService.findResultListOds(params);
			}else if("bigData".equals(resource)){
				 list = DTBBZXService.findResultListBigdata(params, physical);
			}else{
				 list = DTBBZXService.findResultList(params);
			}
		}catch (Exception e){
			request.setAttribute("msg", "表或取的字段不存在!");
			return mapping.findForward(FAILURE);
		}
		
		request.setAttribute("list", list);
		params.clear();
		
		if(list != null && list.size() > 0){
			params  = (Map)list.get(list.size()-1);
		}
		
		params.put("startData",startData);
		params.put("endData",endData);
		request.setAttribute("map", params);
		request.setAttribute("MSITCD",request.getParameter("MSITCD"));
		request.setAttribute("MSCTNO",request.getParameter("MSCTNO"));
		return mapping.findForward("tzssx");
	}

	
	/** 
     * 存款余额
     * @param request 
     * @param response 
     * @return 
     * @throws Exception 
     *//* 
	public ActionForward toTZSSX1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String REP_SQL =  request.getParameter("sql");
		String rownum = request.getParameter("rownum");
		String REPMOD_NO =  request.getParameter("REPMOD_NO");
		StringBuffer strB = new StringBuffer();
		strB.append("select * from (").append(REP_SQL).append(") a")
		    .append(" where a.rn in( ").append(rownum).append(")");
		Map params = new HashMap();
		params.put("SerSQL", strB.toString());
		
		String resource = request.getParameter("resource");
		List list = null;
		try{
			if("His".equals(resource)){
				 list = DTBBZXService.findResultListOds(params);
			}else{
				 list = DTBBZXService.findResultList(params);
			}
		}catch (Exception e){
			request.setAttribute("msg", "表或取的字段不存在!");
			return mapping.findForward(FAILURE);
		}
		
		params.put("REPMOD_NO", REPMOD_NO);
		params.put("ISDISPLAY", "是");
		List COLList = DTBBZXService.findCOLList(params);
		
		request.setAttribute("COLList", COLList);	
		request.setAttribute("list", list);
		request.setAttribute("time", DateUtil.formatDateToStr(new Date(),"yyyy-MM-dd"));
		return mapping.findForward("tzssx1");

	}*/

	
	/** 
     * 存款余额 大数据
     * @param request 
     * @param response 
     * @return 
     * @throws Exception 
     */ 
	public ActionForward toTZSSX1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String REP_SQL =  request.getParameter("sql");
		String JOIN_SQL =  request.getParameter("JOIN_SQL");
		String JOIN_SQL2 =  request.getParameter("JOIN_SQL2");
		String rownum = request.getParameter("rownum");
		String REPMOD_NO =  request.getParameter("REPMOD_NO");
		
		Map params = new HashMap();
		params.put("REPMOD_NO", REPMOD_NO);
		params.put("ISDISPLAY", "是");
		List<Map> COLList = DTBBZXService.findCOLList(params);
		
		List colnames = new ArrayList();
		List physical = new ArrayList();
		for (Map COLMap : COLList) {
			colnames.add(COLMap.get("COLMX_NAME"));
			physical.add(COLMap.get("PHYSICAL_NAME"));
		}
		
		System.out.println("SerSQL=============="+REP_SQL);
		System.out.println("JOIN_SQL=============="+JOIN_SQL);
		System.out.println("JOIN_SQL2=============="+JOIN_SQL2);
		params.put("SerSQL",REP_SQL);
		params.put("JOIN_SQL", JOIN_SQL);
		params.put("JOIN_SQL2", JOIN_SQL2);
		params.put("rownum", rownum);
		
		String resource = request.getParameter("resource");
		List list = null;
		try{
			if("His".equals(resource)){
				 list = DTBBZXService.findResultListOds(params);
			}else if("bigData".equals(resource)){
				 list = DTBBZXService.findResultListBigdata(params,physical);
			}else{
				 list = DTBBZXService.findResultList(params);
			}
		}catch (Exception e){
			request.setAttribute("msg", "表或取的字段不存在!");
			return mapping.findForward(FAILURE);
		}
		
		
		
		request.setAttribute("COLList", COLList);	
		request.setAttribute("list", list);
		request.setAttribute("time", DateUtil.formatDateToStr(new Date(),"yyyy-MM-dd"));
		return mapping.findForward("tzssx1");

	}
	
	/** 
     * 导出 excel数据
     * @param request 
     * @param response 
     * @return 
     * @throws Exception 
     */ 
	public ActionForward exportCSV(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		String REPCON = "";
		String repName = "";
		String REP_SQL = request.getParameter("REP_SQL");
		String JOIN_SQL = request.getParameter("JOIN_SQL");
		String JOIN_SQL2 = request.getParameter("JOIN_SQL2");
		
		String REPMOD_NO = request.getParameter("REPMOD_NO");
		String usrId = request.getParameter("usrId").toString();
		String orgNo = request.getParameter("orgNo").toString();
		//String msg = "";
		Map params = new HashMap();
		String LOGID = request.getParameter("LOGID");
		params.put("LOGID", LOGID);
		/*Map aLogMap = DTBBZXService.findLogMap(params);
		if (aLogMap.get("CONDITION") != null) {
			REPCON = aLogMap.get("CONDITION").toString();
		}*/
		params.clear();
		params.put("REPMOD_NO", REPMOD_NO);
		Map aMB = DTBBZXService.findMbMap(params);
		repName = aMB.get("REP_NAME").toString();
		try {
			List EXPList = DTBBZXService.findEXPList(params);
			int NumEXP = EXPList.size();
			/*if (NumEXP == 0) {
				msg = "0";
			}*/
			List colnames = new ArrayList();
			List physical = new ArrayList();
			for (int i = 0; i < NumEXP; i++) {
				Map EXPMap = (Map) EXPList.get(i);
				colnames.add(EXPMap.get("COLMX_NAME"));
				physical.add(EXPMap.get("PHYSICAL_NAME"));
			}
			params.clear();

			if (true) {
				params.put("SerSQL", REP_SQL);
				params.put("JOIN_SQL", JOIN_SQL);
				params.put("JOIN_SQL2", JOIN_SQL2);

				params.put("REPMOD_NO", REPMOD_NO);
				List resultList = new ArrayList();
				if("His".equals(aMB.get("RESOURCE"))){
					resultList = DTBBZXService.findResultListOds(params);
				}else if("bigData".equals(aMB.get("RESOURCE"))){
					resultList = DTBBZXService.findResultListBigdata(params,physical);
				}else{
					resultList = DTBBZXService.findResultList(params);
				}
				
				DTBBZXService.doSCCVSDown(repName, REPCON, resultList,colnames, response,physical);
				// 插入日志表
				Map logMap = new HashMap();
				Date date = DateUtil.getDate();
				String today = DateUtil.formatDateToStr(date,"yyyy-MM-dd HH:mm:ss");
				String todayid = String.valueOf(date.getTime());
				logMap.put("LOGID", usrId + todayid);
				logMap.put("REP_MOD", REPMOD_NO);
				logMap.put("USERID", usrId);
				logMap.put("USTIME", today);
				logMap.put("USORG", orgNo);
				logMap.put("CONDITION", REPCON);
				logMap.put("TYPE", "导出");
				DTBBZXService.insertLog(logMap);
			} else {
				request.setAttribute("msg", "系统正忙请稍后再试！");
				return mapping.findForward(FAILURE);
			}

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "导出出错！");
			return mapping.findForward(FAILURE);

		}

		return null;

	}

	//模板列表展示界面
	public ActionForward torepList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();

		params.put("STATE", "启用");
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		ParamUtil.putStr2Map(request, "REP_NAME", params);
		//ParamUtil.putStr2Map(request, "FLAGSTATE", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		params.put("FLAGSTATE", "1");
		IListPage page = DTBBZXService.reppageQuery(params, pageNo);
		request.setAttribute("usrId", request.getParameter("usrId"));
		request.setAttribute("orgNo", request.getParameter("orgNo"));
		request.setAttribute("page", page);
		return mapping.findForward("replist");
	}
}
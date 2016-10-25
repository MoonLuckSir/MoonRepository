package com.yitong.app.action.judicial.lscxsh;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yitong.app.service.judicial.lscxsh.lscxShService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.util.DateUtil;
import com.yitong.commons.util.ParamUtil;

/**
 * 渠道维护
 * 
 * @author Administrator
 * 
 */
public class lscxShAction extends BaseAction {
	private lscxShService lscxShService;
	


	public void setLscxShService(lscxShService lscxShService) {
		this.lscxShService = lscxShService;
	}
	/**
	 * 查询列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		
		params.put("STATE", "'提交'");
		params.put("CONDITION", "DQJG='"+request.getParameter("orgNo")+"'");
		request.setAttribute("usrId", request.getParameter("usrId"));
	    request.setAttribute("orgNo", request.getParameter("orgNo"));
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		ParamUtil.putStr2Map(request, "REQ_NO", params);
		String STATE=request.getParameter("STATE");
		if(STATE!=null&&!STATE.equals(""))
		{
			params.put("STATE", "'"+STATE+"'");	
		}
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = lscxShService.pageQuery(params, pageNo);
		request.setAttribute("msg", "");
		request.setAttribute("page", page);
        return mapping.findForward("list");
	}
	
	//填写意见
	
	public ActionForward toTXYJ(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String REQ_NO = ParamUtil.get(request, "REQ_NO");
		String usrId=request.getParameter("usrId").toString();
		String orgNo=request.getParameter("orgNo").toString();
		request.setAttribute("REQ_NO", REQ_NO);
		request.setAttribute("usrId", usrId);
		request.setAttribute("orgNo", orgNo);
	    return mapping.findForward("txyj");
	    
	}
	
	//审核
	public ActionForward check(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String REQ_NO = ParamUtil.get(request, "REQ_NO");
		if(REQ_NO.equals("") || REQ_NO ==null ){
			request.setAttribute("msg", "历史查询申请编号不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "REQ_NO",params);
		String usrId=request.getParameter("usrId").toString();
		String orgNo=request.getParameter("orgNo").toString();
		//获得条件机构
		Map JGparams = new HashMap();
		String SQL="select  CONJG   as CONVAL  from   FINPRO.REP_REQ where REQ_NO='"+REQ_NO+"'  ";
		JGparams.put("SerSQL", SQL);
		Map Convalue=lscxShService.getConVal(JGparams);
		String ConVal="";
		if(Convalue.get("CONVAL")!=null)
			ConVal=Convalue.get("CONVAL").toString();
		if(ConVal.equals(""))
		{
			request.setAttribute("msg", "对不起，获得不了条件对应的机构！");
	        return mapping.findForward(SUCCESS);
		}
		
		JGparams.clear();
		//HisParam  aHisPar=new HisParam();
		if(PROJECT.equals("hfbank"))
		{
			//params.put("CONJG", request.getParameter("orgNo"));	
		}
		if(PROJECT.equals("jsbank"))
		{
		
		//判断机构是否在当前机构的管辖范围内
		String checkSQL=" select count(*) as CONVAL  from JSBRPT.JGCS where YYJG='"+ConVal+"' and SJJG='"+orgNo+"'";
		params.put("SerSQL", checkSQL);
		Map checkNum=lscxShService.getConVal(params);
		String  CheckNum=checkNum.get("CONVAL").toString();
		if(CheckNum.equals("0"))
		{
			request.setAttribute("msg", "对不起，您当前的机构无权审核，请提交上级审核！");
			return mapping.findForward(FAILURE);
		  
		}
		}
		
		params.put("STATE", "启用");
		lscxShService.updateById(params);
		request.setAttribute("msg", "审核成功！");
		
		Map HisMap = new HashMap();
		Date date = DateUtil.getDate();	
		String today = DateUtil.formatDateToStr(date, "yyyy-MM-dd HH:mm:ss");
		String todayid = DateUtil.formatDateToStr(date, "yyyyMMddHHmmss");
		String HIS_DESC=request.getParameter("HIS_DESC");
		if(HIS_DESC==null)
			HIS_DESC="";
		HisMap.put("SQHISID", usrId+todayid);
		HisMap.put("REQ_NO",REQ_NO);
		HisMap.put("ACTION","启用");
		HisMap.put("OPERATOR",usrId);
		HisMap.put("OPERTIME",today);
		HisMap.put("OPERORG",orgNo);
		HisMap.put("HIS_DESC",HIS_DESC.trim());
		lscxShService.insertHis(HisMap);
		lscxShService.clearCaches(REQ_NO);
        return mapping.findForward("txyj");
	}
	//否决
	public ActionForward uncheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String REQ_NO = ParamUtil.get(request, "REQ_NO");
		if(REQ_NO.equals("") || REQ_NO ==null ){
			request.setAttribute("msg", "历史查询申请编号不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "REQ_NO",params);
		String JGVal="";
		String SQJG="";
		//HisParam  aHisPar=new HisParam();
		if(PROJECT.equals("hfbank"))
		{
			
			JGVal=request.getParameter("orgNo");
			params.put("DQJG",request.getParameter("orgNo"));
			SQJG=request.getParameter("orgNo");
		}
		if(PROJECT.equals("jsbank"))
		{
		//获得提交机构
		Map JGparams = new HashMap();
		String SQL="";
		if(DBTYPE.equals("orc"))
		{
		 SQL=" select OPERORG as CONVAL from ( select  OPERORG  from FINPRO.REP_SQHIS where ACTION='提交' and REQ_NO='"+REQ_NO+"' order by OPERTIME DESC )  b where rownum<2"; 
		}
		if(DBTYPE.equals("db2"))
		{
		 SQL="  select  OPERORG as CONVAL  from FINPRO.REP_SQHIS where ACTION='提交' and REQ_NO='"+REQ_NO+"' order by OPERTIME DESC  fetch first 1 rows only "; 
		}

		JGparams.put("SerSQL", SQL);
		Map Convalue=lscxShService.getConVal(JGparams);
		if(Convalue.get("CONVAL")!=null)
			JGVal=Convalue.get("CONVAL").toString();
		
		if(JGVal.equals(""))
		{
		request.setAttribute("msg", "对不起，获得不了下级回退机构！");
        return mapping.findForward(SUCCESS);
		}
        
		
		JGparams.clear();
		SQL="select  CHECKIN_ORGAN   as CONVAL  from  FINPRO.REP_REQ where REQ_NO='"+REQ_NO+"'  ";
		JGparams.put("SerSQL", SQL);
		Map REPSQvalue=lscxShService.getConVal(JGparams);
		
		if(REPSQvalue!=null)
			SQJG=Convalue.get("CONVAL").toString();
		
		params.put("DQJG",JGVal);
		}
		if(JGVal.equals(SQJG))
		{
			params.put("STATE", "冻结");
		}else
		{
			params.put("STATE", "提交");
		}
		lscxShService.updateById(params);
		
		Map HisMap = new HashMap();
		Date date = DateUtil.getDate();	
		String today = DateUtil.formatDateToStr(date, "yyyy-MM-dd HH:mm:ss");
		String todayid = DateUtil.formatDateToStr(date, "yyyyMMddHHmmss");
		String usrId=request.getParameter("usrId").toString();
		String orgNo=request.getParameter("orgNo").toString();
		String HIS_DESC=request.getParameter("HIS_DESC");
		if(HIS_DESC==null)
			HIS_DESC="";
		HisMap.put("SQHISID", usrId+todayid);
		HisMap.put("REQ_NO",REQ_NO);
		HisMap.put("ACTION","回退");
		HisMap.put("OPERATOR",usrId);
		HisMap.put("OPERTIME",today);
		HisMap.put("OPERORG",orgNo);
		HisMap.put("HIS_DESC",HIS_DESC.trim());
		lscxShService.insertHis(HisMap);
		
		request.setAttribute("msg", "回退成功！");
		lscxShService.clearCaches(REQ_NO);
        return mapping.findForward("txyj");
	}

	//查看详细
	public ActionForward toView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String REQ_NO = ParamUtil.get(request, "REQ_NO");
		if(REQ_NO.equals("") || REQ_NO ==null ){
			request.setAttribute("msg", "查询申请编号不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		Map entry = lscxShService.load(REQ_NO);
		
		Map Hisparams = new HashMap();
		Hisparams.put("REQ_NO", REQ_NO);
		List page = lscxShService.QueryHis(Hisparams);
		request.setAttribute("page", page);
		
		request.setAttribute("rst", entry);
        return mapping.findForward("view");
	}
	//提交
	public ActionForward conmmit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String REQ_NO = ParamUtil.get(request, "REQ_NO");
		if(REQ_NO.equals("") || REQ_NO ==null ){
			request.setAttribute("msg", "历史查询申请编号不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "REQ_NO",params);
		//HisParam  aHisPar=new HisParam();
		if(PROJECT.equals("hfbank"))
		{
			
			params.put("DQJG",request.getParameter("orgNo"));
		}
		if(PROJECT.equals("jsbank"))
		{
		//获得上级机构
		Map JGparams = new HashMap();
		String SQL="select  ORG_PAR_NO  as CONVAL  from YTPUB.ORGAN  where ORG_NO=(select DQJG from FINPRO.REP_REQ where REQ_NO='"+REQ_NO+"' ) ";
		JGparams.put("SerSQL", SQL);
		Map Convalue=lscxShService.getConVal(JGparams);
		String JGVal="";
		if(Convalue!=null)
			JGVal=Convalue.get("CONVAL").toString();
		if(JGVal==null||JGVal.equals(""))
		{
			request.setAttribute("msg", "对不起，当前机构已经是顶级机构！");
	        return mapping.findForward(SUCCESS);
		}
		
		params.put("DQJG",JGVal);
		}
		params.put("STATE", "提交");
		lscxShService.updateById(params);
		
		//记流水账
		Map HisMap = new HashMap();
		Date date = DateUtil.getDate();	
		String today = DateUtil.formatDateToStr(date, "yyyy-MM-dd HH:mm:ss");
		String todayid = DateUtil.formatDateToStr(date, "yyyyMMddHHmmss");
		String usrId=request.getParameter("usrId").toString();
		String orgNo=request.getParameter("orgNo").toString();
		String HIS_DESC=request.getParameter("HIS_DESC");
		if(HIS_DESC==null)
			HIS_DESC="";
		HisMap.put("SQHISID", usrId+todayid);
		HisMap.put("REQ_NO",REQ_NO);
		HisMap.put("ACTION","提交");
		HisMap.put("OPERATOR",usrId);
		HisMap.put("OPERTIME",today);
		HisMap.put("OPERORG",orgNo);
		HisMap.put("HIS_DESC",HIS_DESC);
		lscxShService.insertHis(HisMap);
		request.setAttribute("msg", "提交成功！");
		lscxShService.clearCaches(REQ_NO);
        return mapping.findForward("txyj");
	}
	
	
	
	//预览查询
	public ActionForward toViewREP(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String REQ_NO = ParamUtil.get(request, "REQ_NO");
		if(REQ_NO.equals("") || REQ_NO ==null ){
			request.setAttribute("msg", "查询申请编号不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		Map entry = lscxShService.load(REQ_NO);
		request.setAttribute("rst", entry);
        return mapping.findForward("view");
	}
	
}

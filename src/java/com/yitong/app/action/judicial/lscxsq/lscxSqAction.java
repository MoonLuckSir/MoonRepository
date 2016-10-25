package com.yitong.app.action.judicial.lscxsq;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yitong.app.service.judicial.lscxsq.lscxSqService;
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
public class lscxSqAction extends BaseAction {
	private lscxSqService lscxSqService;

	public void setLscxSqService(lscxSqService lscxSqService) {
		this.lscxSqService = lscxSqService;
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
		params.put("STATE", "'冻结'");
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		ParamUtil.putStr2Map(request, "REQ_NO", params);
		params.put("CONDITION", "CHECKIN_USER='"+request.getParameter("usrId")+"'");
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = lscxSqService.pageQuery(params, pageNo);
		request.setAttribute("usrId", request.getParameter("usrId"));
		request.setAttribute("orgNo", request.getParameter("orgNo"));
			
		request.setAttribute("page", page);
        return mapping.findForward("list");
	}

	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("usrId", request.getParameter("usrId"));
	    request.setAttribute("orgNo", request.getParameter("orgNo"));
		return mapping.findForward("add");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		Date date = DateUtil.getDate();	
		String today = DateUtil.formatDateToStr(date, "yyyy-MM-dd HH:mm:ss");
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		ParamUtil.putStr2Map(request, "REP_DESC", params);
		//设置SQL 和 CONS_PAR
		getQureySQL(params, request);
		params.put("STATE", "冻结");
		params.put("REQ_NO", lscxSqService.getSeq(date));
		params.put("CHECKIN_USER", request.getParameter("usrId"));
		params.put("CHECKIN_DEPT", "");
		params.put("CHECKIN_ORGAN", request.getParameter("orgNo"));
		params.put("CHECKIN_TIME", today);
		params.put("DQJG", request.getParameter("orgNo"));
		
		
		//获得条件机构
		if(PROJECT.equals("hfbank"))
		{
			params.put("CONJG", request.getParameter("orgNo"));	
		}
		if(PROJECT.equals("jsbank"))
		{
			Map JGparams = new HashMap();
			ParamUtil.putStr2Map(request, "REPMOD_NO", JGparams);
			Map REPMOD=lscxSqService.findMbMap(JGparams);
			String ISKORG="否";
			if(REPMOD.get("ISKORG")!=null)
			ISKORG=REPMOD.get("ISKORG").toString();
			String ISSH="是";
			if(REPMOD.get("ISKORG")!=null)
			ISSH=REPMOD.get("ISSH").toString();
			String JGSQL="";
			//当只有 跨机构 且 需要审核的时候机构查询的SQL才是必输的
			String JGVal="";
			if(ISKORG.equals("是")&&ISSH.equals("是"))
			{
				if(REPMOD.get("ORGSQL")!=null)
				{
				 JGSQL=REPMOD.get("ORGSQL").toString();
			     List ConList=lscxSqService.findConList(JGparams);
			     int NumCon=ConList.size(); 
			     for( int j=0;j<NumCon;j++)
			    { 
				Map ConMap=(Map)ConList.get(j);	
				String tempCONMX_NO=ConMap.get("CONMX_NO").toString();
				if(JGSQL.indexOf(tempCONMX_NO.trim())>0)
				 {
					String tempCONID="M"+tempCONMX_NO; 
					String tempVal=request.getParameter(tempCONID.trim()); 
					if(tempVal==null)
						tempVal="";
					JGSQL=JGSQL.replaceAll(tempCONMX_NO, tempVal);
				 }
			    }
			    JGparams.clear();
			    JGparams.put("SerSQL", JGSQL);
			    Map Convalue=lscxSqService.getConVal(JGparams);
			    if(Convalue.get("CONVAL")!=null)
				  JGVal=Convalue.get("CONVAL").toString();
			     if(JGVal==null)
				 JGVal="";
			     params.put("CONJG", JGVal);	
			}else
			{
				request.setAttribute("msg", "当前模板没有定义营业机构SQL！");
				return mapping.findForward(SUCCESS);
			}
		   }else
		   {
			   params.put("CONJG", request.getParameter("orgNo"));	
		   }
			
		}
		
		lscxSqService.insert(params);
		request.setAttribute("msg", "增加成功！");
		return mapping.findForward(SUCCESS);  
		 
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String REQ_NO = ParamUtil.get(request, "REQ_NO");
		if(REQ_NO.equals("") || REQ_NO ==null ){
			request.setAttribute("msg", "查询申请编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "REQ_NO", params);
		lscxSqService.delete(params);
		request.setAttribute("msg", "删除成功！");
		lscxSqService.clearCaches(REQ_NO);
		return mapping.findForward(SUCCESS);
	}
	public ActionForward toView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String REQ_NO = ParamUtil.get(request, "REQ_NO");
		if(REQ_NO.equals("") || REQ_NO ==null ){
			request.setAttribute("msg", "查询申请编号不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		Map entry = lscxSqService.load(REQ_NO);
		Map Hisparams = new HashMap();
		Hisparams.put("REQ_NO", REQ_NO);
		List page = lscxSqService.QueryHis(Hisparams);
		request.setAttribute("page", page);
		request.setAttribute("rst", entry);
        return mapping.findForward("view");
	}
	//到修改界面
	public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String REQ_NO = ParamUtil.get(request, "REQ_NO");
		
		if(REQ_NO.equals("") || REQ_NO ==null ){
			request.setAttribute("msg", "历史查询申请编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		Map entry = lscxSqService.load(REQ_NO);
		request.setAttribute("rst", entry);

		return mapping.findForward("update");
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String REQ_NO = ParamUtil.get(request, "REQ_NO");
		if(REQ_NO.equals("") || REQ_NO ==null ){
			request.setAttribute("msg", "历史查询申请编号不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		ParamUtil.putStr2Map(request, "REP_DESC", params);
		ParamUtil.putStr2Map(request, "REQ_NO",params);
		//设置SQL 和 CONS_PAR
		getQureySQL(params, request);
		
		//获得条件机构
		Map JGparams = new HashMap();
		if(PROJECT.equals("hfbank"))
		{
			params.put("CONJG", request.getParameter("orgNo"));	
		}
		if(PROJECT.equals("jsbank"))
		{
		ParamUtil.putStr2Map(request, "REPMOD_NO", JGparams);
		Map REPMOD=lscxSqService.findMbMap(JGparams);
		String ISKORG="否";
		if(REPMOD.get("ISKORG")!=null)
		ISKORG=REPMOD.get("ISKORG").toString();
		String ISSH="是";
		if(REPMOD.get("ISKORG")!=null)
		ISSH=REPMOD.get("ISSH").toString();
		String JGSQL="";
		//当只有 跨机构 且 需要审核的时候机构查询的SQL才是必输的
		String JGVal="";
		 if(ISKORG.equals("是")&&ISSH.equals("是"))
		 {
			if(REPMOD.get("ORGSQL")!=null)
			{
				JGSQL=REPMOD.get("ORGSQL").toString();
				List ConList=lscxSqService.findConList(JGparams);
				int NumCon=ConList.size(); 
				for( int j=0;j<NumCon;j++)
				{ 
					Map ConMap=(Map)ConList.get(j);	
					String tempCONMX_NO=ConMap.get("CONMX_NO").toString();
					if(JGSQL.indexOf(tempCONMX_NO)>0)
					{
						String tempCONID="M"+tempCONMX_NO; 
						String tempVal=request.getParameter(tempCONID.trim()); 
						if(tempVal==null)
							tempVal="";
						JGSQL=JGSQL.replaceAll(tempCONMX_NO, tempVal);
					}
				}
				JGparams.clear();
				JGparams.put("SerSQL", JGSQL);
				Map Convalue=lscxSqService.getConVal(JGparams);
				if(Convalue.get("CONVAL")!=null)
					JGVal=Convalue.get("CONVAL").toString();
				if(JGVal==null)
					JGVal="";
				params.put("CONJG", JGVal);	
			}else
			{
				request.setAttribute("msg", "当前模板没有定义营业机构SQL！");
				return mapping.findForward(SUCCESS);
			}
		   }else
		   {
			   params.put("CONJG", request.getParameter("orgNo"));	
		   }
		}
		
		lscxSqService.updateById(params);
		request.setAttribute("msg", "修改成功！");
		lscxSqService.clearCaches(REQ_NO);

		return mapping.findForward(SUCCESS);
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
		if(PROJECT.equals("hfbank"))
		{
			params.put("CONJG", request.getParameter("orgNo"));	
		}
		if(PROJECT.equals("jsbank"))
		{
		//获得上级机构
		Map JGparams = new HashMap();
		String SQL="select  ORG_PAR_NO  as CONVAL  from YTPUB.ORGAN  where ORG_NO=(select DQJG from FINPRO.REP_REQ where REQ_NO='"+REQ_NO+"' ) ";
		JGparams.put("SerSQL", SQL);
		Map Convalue=lscxSqService.getConVal(JGparams);
		String JGVal="";
		if(Convalue.get("CONVAL")!=null)
			JGVal=Convalue.get("CONVAL").toString();
		
		if(JGVal==null||JGVal.equals(""))
		{
			request.setAttribute("msg", "对不起，当前机构已经是顶级机构,不需要申请直接查！");
	        return mapping.findForward(SUCCESS);
		}
		params.put("DQJG",JGVal);
		}
		params.put("STATE", "提交");
		lscxSqService.updateById(params);
		
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
		lscxSqService.insertHis(HisMap);
		request.setAttribute("msg", "提交成功！");
		lscxSqService.clearCaches(REQ_NO);
        return mapping.findForward(SUCCESS);
	}
	public void getQureySQL(Map params,HttpServletRequest request) {
		//获得模板信息
		List ModList=lscxSqService.findMbList(params);
         Map ModMsp=(Map)ModList.get(0);
        //获得条件信息
		List ConList=lscxSqService.findConList(params);
		int NumCon=ConList.size();
		//组装SQL
		StringBuffer SQL=new StringBuffer("");
		
		StringBuffer CONS=new StringBuffer("");
		
		SQL.append(" select ");
		SQL.append(ModMsp.get("SELECT_SQL"));
		if(DBTYPE.equals("db2"))
		{
		if(ModMsp.get("ORDER_SQL")!=null&&!ModMsp.get("ORDER_SQL").equals(""))
		{
			SQL.append(",ROW_NUMBER() OVER( ORDER BY ");
			SQL.append(ModMsp.get("ORDER_SQL"));
			SQL.append(") AS rn ");
			
		}
		}
		
		SQL.append(" from  ");
		SQL.append(ModMsp.get("FROM_SQL"));
		SQL.append(" where  ");
		if(ModMsp.get("WHERE_SQL")!=null&&!ModMsp.get("WHERE_SQL").equals(""))
		{
			SQL.append(ModMsp.get("WHERE_SQL"));
		}else
		{
			SQL.append(" 1=1  ");
		}
		//初始化
		String groupvalue=request.getParameter("groupvalue");
		if(groupvalue!=null&&!groupvalue.equals(""))
		{
			CONS.append(" var vfrm=document.getElementById('"+groupvalue+"'); ");
			CONS.append("  vfrm.onclick();");
			
		}
		//拼条件
		for( int i=0;i<NumCon;i++)
		{ 
			Map ConMap=(Map)ConList.get(i);	
			String PHYSICAL_NAME=(String)ConMap.get("PHYSICAL_NAME"); 
			String CONMX_NO=(String)ConMap.get("CONMX_NO");
			String CONID="M"+CONMX_NO; 
			String CONIDVal=request.getParameter(CONID.trim()); 
			String CONMX_TYPE=(String)ConMap.get("CONMX_TYPE");
			String OPERATOR=(String)ConMap.get("OPERATOR");
			String ISJOIN=ConMap.get("ISJOIN").toString();
			String INPUT_VALUE="";
			if(ConMap.get("INPUT_VALUE")!=null)
				INPUT_VALUE=ConMap.get("INPUT_VALUE").toString();
			//字符串，日期，数字
			if(CONMX_TYPE.equals("字符串"))
			{   
				if(CONIDVal!=null&&!CONIDVal.equals(""))
				{
			    CONIDVal=CONIDVal.trim();
			    CONS.append(" form.");
			    CONS.append(CONID);
				CONS.append(".value='");
				CONS.append(CONIDVal);
				CONS.append("';");
				if("机构".equals(INPUT_VALUE))
				{
					CONS.append(" form.N");
				    CONS.append(CONID);
					CONS.append(".value='");
					String NCONID="N"+CONID;
					CONS.append(request.getParameter(NCONID.trim()));
					CONS.append("';");	
				}
				if(ISJOIN!=null&&ISJOIN.equals("是"))
				{
				SQL.append(" and  ");
				SQL.append(PHYSICAL_NAME);
				SQL.append(OPERATOR);
				if(OPERATOR.equals("LIKE"))
				{
				 SQL.append(" '%");
				 SQL.append(CONIDVal);
				 SQL.append(" %'");
				}else if(OPERATOR.equals("LIKEL"))
				{
					 SQL.append(" '%");
					 SQL.append(CONIDVal);
					 SQL.append(" '");
				}else if(OPERATOR.equals("LIKER"))
				{
					 SQL.append(" '");
					 SQL.append(CONIDVal);
					 SQL.append(" %'");
				}else
				{
					 SQL.append(" '");
					 SQL.append(CONIDVal);
					 SQL.append("'");
				}
				}
				}
			}else if(CONMX_TYPE.equals("日期"))
			{    
				if(CONIDVal!=null&&!CONIDVal.equals(""))
				{
					CONIDVal=CONIDVal.trim();
					CONS.append(" form.");
					CONS.append(CONID);
				    CONS.append(".value='");
					CONS.append(CONIDVal);
					CONS.append("';");
					if(ISJOIN!=null&&ISJOIN.equals("是"))
					{
					SQL.append(" and  ");
					SQL.append(PHYSICAL_NAME);
					SQL.append(OPERATOR);
					SQL.append(" '");
					SQL.append(CONIDVal);
					SQL.append(" '");
					}
				}
			}else if(CONMX_TYPE.equals("数字"))
			{
				if(CONIDVal!=null&&!CONIDVal.equals(""))
				{
					CONIDVal=CONIDVal.trim();
					CONS.append(" form.");
					CONS.append(CONID);
				    CONS.append(".value='");
					CONS.append(CONIDVal);
					CONS.append("';");
					if(ISJOIN!=null&&ISJOIN.equals("是"))
					{
					SQL.append(" and  ");
					SQL.append(PHYSICAL_NAME);
					SQL.append(OPERATOR);
					SQL.append(CONIDVal.replaceAll("-",""));
					}
					
				}
				 
				
			}
			
		}
		if(DBTYPE.equals("orc"))
		{
			if(ModMsp.get("ORDER_SQL")!=null&&!ModMsp.get("ORDER_SQL").equals(""))
			{
				SQL.append(" ORDER BY ");
				SQL.append(ModMsp.get("ORDER_SQL"));
			}	
		}
		params.put("REP_SQL", SQL.toString());
		params.put("CONS_PAR", CONS.toString());
	}
	
	
	public String getREPCON(String REPMOD_NO,HttpServletRequest request) {
		
        Map params = new HashMap();
		params.put("REPMOD_NO", REPMOD_NO);
		params.put("ISBTDISPLAY", "是");
		//获得条件信息
		List ConList=lscxSqService.findConList(params);
		int NumCon=ConList.size();
		//组装显示抬头
		StringBuffer Str=new StringBuffer("");
		for( int i=0;i<NumCon;i++)
		{ 
			Map ConMap=(Map)ConList.get(i);	
			String CONMX_NAME=(String)ConMap.get("CONMX_NAME"); 
			String CONMX_NO=(String)ConMap.get("CONMX_NO");
			String CONID="M"+CONMX_NO; 
			String CONIDVal=request.getParameter(CONID.trim()); 
			Str.append(CONMX_NAME);
			Str.append(":");
			Str.append(CONIDVal);
			Str.append("&nbsp&nbsp&nbsp");
			
		}
		return Str.toString();
	}
	
	
	
public String getQureySQL(String REPMOD_NO) {
		
        Map params = new HashMap();
		params.put("REPMOD_NO", REPMOD_NO);
		//获得模板信息
		List ModList=lscxSqService.findMbList(params);
         Map ModMsp=(Map)ModList.get(0);
        //获得条件信息
		List ConList=lscxSqService.findConList(params);
		int NumCon=ConList.size();
		//组装SQL
		StringBuffer SQL=new StringBuffer("");
		SQL.append(" select ");
		SQL.append(ModMsp.get("SELECT_SQL"));
		if(DBTYPE.equals("db2"))
		{
		if(ModMsp.get("ORDER_SQL")!=null&&!ModMsp.get("ORDER_SQL").equals(""))
		{
			SQL.append(",ROW_NUMBER() OVER(");
			SQL.append(ModMsp.get("ORDER_SQL"));
			SQL.append(") AS rn ");
			
		}
		}
		SQL.append(" from  ");
		SQL.append(ModMsp.get(" FROM_SQL "));
		SQL.append(" where  ");
		if(ModMsp.get("WHERE_SQL")!=null&&!ModMsp.get("WHERE_SQL").equals(""))
		{
			SQL.append(ModMsp.get("WHERE_SQL"));
		}else
		{
			SQL.append(" 1=1  ");
		}	
		
		for( int i=0;i<NumCon;i++)
		{ 
			Map ConMap=(Map)ConList.get(i);	
			//String ORDER=(String)ConMap.get("ORDER"); 
			String PHYSICAL_NAME=(String)ConMap.get("PHYSICAL_NAME"); 
			String CONIDVal=""; 
			String CONMX_TYPE=(String)ConMap.get("CONMX_TYPE");
			String OPERATOR=(String)ConMap.get("OPERATOR");
			
			//字符串，日期，数字
			if(CONMX_TYPE.equals("字符串"))
			{   if(CONIDVal==null)
				CONIDVal="";
			    CONIDVal=CONIDVal.trim();
				SQL.append(" and  ");
				SQL.append(PHYSICAL_NAME);
				SQL.append(OPERATOR);
				if(OPERATOR.equals("LIKE"))
				{
				 SQL.append(" '%");
				 SQL.append(CONIDVal);
				 SQL.append(" %'");
				}else if(OPERATOR.equals("LIKEL"))
				{
					 SQL.append(" '%");
					 SQL.append(CONIDVal);
					 SQL.append(" '");
				}else if(OPERATOR.equals("LIKER"))
				{
					 SQL.append(" '");
					 SQL.append(CONIDVal);
					 SQL.append(" %'");
				}	
				
			}else if(CONMX_TYPE.equals("日期"))
			{
			  if(CONIDVal==null||CONIDVal.equals(""))
				CONIDVal="1975-11-11";
			    CONIDVal=CONIDVal.trim();
			    SQL.append(" and  ");
				SQL.append(PHYSICAL_NAME);
				SQL.append(OPERATOR);
				SQL.append(" '");
				SQL.append(CONIDVal);
				SQL.append(" '");
				
			}else if(CONMX_TYPE.equals("数字")||CONIDVal.equals(""))
			{
				   if(CONIDVal==null)
					CONIDVal="0";
				    CONIDVal=CONIDVal.trim();
				    SQL.append(" and  ");
					SQL.append(PHYSICAL_NAME);
					SQL.append(OPERATOR);
					SQL.append("");
					SQL.append(CONIDVal.replaceAll("-",""));
					SQL.append(" ");	
				
			}
			
		}
		if(DBTYPE.equals("orc"))
		{
			if(ModMsp.get("ORDER_SQL")!=null&&!ModMsp.get("ORDER_SQL").equals(""))
			{
				SQL.append(" ORDER BY ");
				SQL.append(ModMsp.get("ORDER_SQL"));
			}	
		}
		return SQL.toString();
	}

}

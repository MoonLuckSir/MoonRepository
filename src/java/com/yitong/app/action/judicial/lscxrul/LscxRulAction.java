package com.yitong.app.action.judicial.lscxrul;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yitong.app.service.judicial.lscxrul.LscxRulService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.util.ParamUtil;
import com.yitong.commons.util.StringUtil;

/**
 * 历史查询模板编
 * 
 * @author JACKY-XING
 * 
 */
public class LscxRulAction extends BaseAction {
	private LscxRulService lscxRulService;

	public void setLscxRulService(LscxRulService lscxRulService) {
		this.lscxRulService = lscxRulService;
	}

	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		String REPMOD_NO = ParamUtil.get(request,"REPMOD_NO");
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		Map map = lscxRulService.getZbxx(REPMOD_NO);
		request.setAttribute("rst", map);
		
		return mapping.findForward("add");
	}

	/**
	 * 
	 * 增加
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		//String userId = (String)request.getSession().getAttribute("hisRepUserId");
		
		//Date date = DateUtil.getDate();	 
		//String today = DateUtil.formatDateToStr(date, "yyyy-MM-dd HH:mm:ss");		
		//得到编号
		String CONMX_NO = ParamUtil.get(request,"CONMX_NO");
		String RULE_ID = lscxRulService.getSeq(CONMX_NO);
		System.out.println("RULE_ID=="+RULE_ID);
		params = new HashMap();
		
		params.put("RULE_ID", RULE_ID);
		ParamUtil.putStr2Map(request, "RULE_NAME", params);
		String  RULE_VALUE=ParamUtil.get(request, "RULE_VALUE");
		if(RULE_VALUE!=null&&!RULE_VALUE.equals(""))
		{
			RULE_VALUE=RULE_VALUE.toUpperCase();
			params.put("RULE_VALUE", RULE_VALUE);
		}
		ParamUtil.putStr2Map(request, "RULE_DESC", params);
		ParamUtil.putStr2Map(request, "CONMX_NO", params);
				
		String  RULE_NAME=ParamUtil.get(request, "RULE_NAME");
		if(RULE_NAME!=null&&RULE_NAME.equals("值合法性"))
		{
			Map Conparams = new HashMap();
			ParamUtil.putStr2Map(request, "CONMX_NO", Conparams);
			Conparams.put("ISCHECK", "是");
			lscxRulService.updateConById(Conparams);
			
		}
		if(RULE_NAME!=null&&RULE_NAME.equals("必输"))
		{
			Map Conparams = new HashMap();
			ParamUtil.putStr2Map(request, "CONMX_NO", Conparams);
			Conparams.put("ISNOTNULL", "是");
			lscxRulService.updateConById(Conparams);
			
		}
		if(RULE_NAME!=null&&RULE_NAME.equals("N选一"))
		{
			if(RULE_VALUE!=null&&!RULE_VALUE.equals(""))
			{
				String [] listcol=StringUtil.toArr(RULE_VALUE);
				
				for( int i=0;i<listcol.length;i++)
				{
					
					Map Conparams = new HashMap();
					Conparams.put("CONMX_NO", listcol[i]);
					Conparams.put("ISNOTNULL", "是");
					lscxRulService.updateConById(Conparams);
				
				}
					
			}
		}
		
		
		lscxRulService.insert(params);
		request.setAttribute("msg", "增加成功！");
		return mapping.findForward(SUCCESS);
	}

	//转到到修改界面
	public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String RULE_ID = ParamUtil.get(request, "RULE_ID");
		
		if(RULE_ID.equals("") || RULE_ID ==null ){
			request.setAttribute("msg", "规则ID不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "RULE_ID", params);
		Map entry = lscxRulService.load(RULE_ID);
		request.setAttribute("rst", entry);

		return mapping.findForward("update");
	}

	/**
	 * 修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String RULE_ID = ParamUtil.get(request, "RULE_ID");
		if(RULE_ID.equals("") || RULE_ID ==null ){
			request.setAttribute("msg", "规则ID不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map params = new HashMap();
		params.put("RULE_ID", RULE_ID);
		ParamUtil.putStr2Map(request, "RULE_NAME", params);
		
		String  RULE_VALUE=ParamUtil.get(request, "RULE_VALUE");
		if(RULE_VALUE!=null&&!RULE_VALUE.equals(""))
		{
			RULE_VALUE=RULE_VALUE.toUpperCase();
			params.put("RULE_VALUE", RULE_VALUE);
		}
		
		ParamUtil.putStr2Map(request, "RULE_DESC", params);
		
		String  RULE_NAME=ParamUtil.get(request, "RULE_NAME");
		if(RULE_NAME!=null&&RULE_NAME.equals("值合法性"))
		{
			Map Conparams = new HashMap();
			ParamUtil.putStr2Map(request, "CONMX_NO", Conparams);
			Conparams.put("ISCHECK", "是");
			lscxRulService.updateConById(Conparams);
			
		}
		
		
		if(RULE_NAME!=null&&RULE_NAME.equals("必输"))
		{
			Map Conparams = new HashMap();
			ParamUtil.putStr2Map(request, "CONMX_NO", Conparams);
			Conparams.put("ISNOTNULL", "是");
			lscxRulService.updateConById(Conparams);
			
		}
		
		Map entry = lscxRulService.load(RULE_ID);
		String OLDRULE_NAME="";
		if(entry!=null&&entry.get("RULE_NAME")!=null)
			OLDRULE_NAME=entry.get("RULE_NAME").toString();
		
		String OLDRULE_VALUE="";
		if(entry!=null&&entry.get("RULE_VALUE")!=null)
			OLDRULE_VALUE=entry.get("RULE_VALUE").toString();
		
		if(OLDRULE_NAME!=null&&OLDRULE_NAME.equals("N选一"))
		{
			if(OLDRULE_VALUE!=null&&!OLDRULE_VALUE.equals(""))
			{
				String [] listcol=StringUtil.toArr(OLDRULE_VALUE);
				
				for( int i=0;i<listcol.length;i++)
				{
					
					Map Conparams = new HashMap();
					Conparams.put("CONMX_NO", listcol[i]);
					Conparams.put("ISNOTNULL", "否");
					lscxRulService.updateConById(Conparams);
				
				}
					
			}
		}
		
		
		if(RULE_NAME!=null&&RULE_NAME.equals("N选一"))
		{
			if(RULE_VALUE!=null&&!RULE_VALUE.equals(""))
			{
				String [] listcol=StringUtil.toArr(RULE_VALUE);
				
				for( int i=0;i<listcol.length;i++)
				{
					
					Map Conparams = new HashMap();
					Conparams.put("CONMX_NO", listcol[i]);
					Conparams.put("ISNOTNULL", "是");
					lscxRulService.updateConById(Conparams);
				
				}
					
			}
		}
		
		
		
		lscxRulService.updateById(params);
		request.setAttribute("msg", "修改成功！");
		lscxRulService.clearCaches(RULE_ID);

		return mapping.findForward(SUCCESS);
	}

	/**
	 * 查看详细信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String RULE_ID = ParamUtil.get(request, "RULE_ID");
		if(RULE_ID.equals("") || RULE_ID ==null ){
			request.setAttribute("msg", "规则ID不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "RULE_ID", params);
		Map entry = lscxRulService.load(RULE_ID);
		request.setAttribute("rst", entry);

		return mapping.findForward("view");
	}

	/**
	 * 删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String RULE_ID = ParamUtil.get(request, "RULE_ID");
		if(RULE_ID.equals("") || RULE_ID ==null ){
			request.setAttribute("msg", "规则ID不存在！");
			return mapping.findForward(FAILURE);
		}
		
		Map entry = lscxRulService.load(RULE_ID);
		String OLDRULE_NAME="";
		if(entry!=null&&entry.get("RULE_NAME")!=null)
			OLDRULE_NAME=entry.get("RULE_NAME").toString();
		
		String OLDRULE_VALUE="";
		if(entry!=null&&entry.get("RULE_VALUE")!=null)
			OLDRULE_VALUE=entry.get("RULE_VALUE").toString();
		
		if(OLDRULE_NAME!=null&&OLDRULE_NAME.equals("值合法性"))
		{
			Map Conparams = new HashMap();
			ParamUtil.putStr2Map(request, "CONMX_NO", Conparams);
			Conparams.put("ISCHECK", "否");
			lscxRulService.updateConById(Conparams);
			
		}
		
		
		if(OLDRULE_NAME!=null&&OLDRULE_NAME.equals("必输"))
		{
			Map Conparams = new HashMap();
			ParamUtil.putStr2Map(request, "CONMX_NO", Conparams);
			Conparams.put("ISNOTNULL", "否");
			lscxRulService.updateConById(Conparams);
			
		}
		
		if(OLDRULE_NAME!=null&&OLDRULE_NAME.equals("N选一"))
		{
			if(OLDRULE_VALUE!=null&&!OLDRULE_VALUE.equals(""))
			{
				String [] listcol=StringUtil.toArr(OLDRULE_VALUE);
				
				for( int i=0;i<listcol.length;i++)
				{
					
					Map Conparams = new HashMap();
					Conparams.put("CONMX_NO", listcol[i]);
					Conparams.put("ISNOTNULL", "否");
					lscxRulService.updateConById(Conparams);
				
				}
					
			}
		}
		
		
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "RULE_ID", params);
		lscxRulService.delete(params);
		request.setAttribute("msg", "删除成功！");
		lscxRulService.clearCaches(RULE_ID);
		
		return mapping.findForward(SUCCESS);
	}

	public ActionForward toQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		return toList(mapping, form, request, response);
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
		ParamUtil.putStr2Map(request, "RULE_ID", params);
		ParamUtil.putStr2Map(request, "CONMX_NO", params);
		ParamUtil.putStr2Map(request, "RULE_NAME", params);
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = lscxRulService.pageQuery(params, pageNo);
		request.setAttribute("page", page);

		return mapping.findForward("list");
	}

	
	//下拉框
	public String getOptions(){
		StringBuffer sel_options  = new StringBuffer();
		try{
			String[] options = new String[]{"","制定","生效","失效"}; //下拉框要传的值！
			String[] values = new String[]{"","制定","生效","失效"}; //下拉框要显示的值！
			
			for(int i=0;i<options.length;i++){
				String option = options[i];
				sel_options.append("<option value=\"").append(option).append("\">");
				sel_options.append(values[i]);
				sel_options.append("</option>");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return sel_options.toString();
	}



}


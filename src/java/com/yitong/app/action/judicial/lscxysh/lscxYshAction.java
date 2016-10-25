package com.yitong.app.action.judicial.lscxysh;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yitong.app.service.judicial.lscxysh.lscxYshService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.util.ParamUtil;

/**
 * 渠道维护
 * 
 * @author Administrator
 * 
 */
public class lscxYshAction extends BaseAction {
	private lscxYshService lscxYshService;



	
	public void setLscxYshService(lscxYshService lscxYshService) {
		this.lscxYshService = lscxYshService;
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
		params.put("STATE", "'启用'");
		params.put("CONDITION", "CHECKIN_USER='"+request.getParameter("usrId")+"'");
		request.setAttribute("usrId", request.getParameter("usrId"));
	    request.setAttribute("orgNo", request.getParameter("orgNo"));
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		ParamUtil.putStr2Map(request, "REQ_NO", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = lscxYshService.pageQuery(params, pageNo);
		request.setAttribute("page", page);
        return mapping.findForward("list");
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
		Map entry = lscxYshService.load(REQ_NO);
		
		Map Hisparams = new HashMap();
		Hisparams.put("REQ_NO", REQ_NO);
		List page = lscxYshService.QueryHis(Hisparams);
		request.setAttribute("page", page);
		
		request.setAttribute("rst", entry);
        return mapping.findForward("view");
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
		Map entry = lscxYshService.load(REQ_NO);
		request.setAttribute("rst", entry);
        return mapping.findForward("view");
	}
	
	
}

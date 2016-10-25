package com.yitong.app.action.judicial.lscxhea;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yitong.app.service.judicial.lscxhea.LscxHeaService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.util.ParamUtil;

public class LscxHeaAction extends BaseAction {
	private LscxHeaService lscxHeaService;
	
	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return mapping.findForward("add");
	}
	

	/**
	 * 
	 * 增加用户
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
		/*String userId = (String)request.getSession().getAttribute("hisRepUserId");
		Date date = DateUtil.getDate();	 
		String today = DateUtil.formatDateToStr(date, "yyyy-MM-dd HH:mm:ss");*/
		//得到编号
		String REPMOD_NO = ParamUtil.get(request,"REPMOD_NO");
		String HEAD_NO = lscxHeaService.getSeq(REPMOD_NO);
		params = new HashMap();
		
		params.put("HEAD_NO", HEAD_NO);
		ParamUtil.putStr2Map(request, "HEAD_NAME", params);
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		ParamUtil.putStr2Map(request, "HEARES", params);
		ParamUtil.putStr2Map(request, "HEASQL", params);
		ParamUtil.putStr2Map(request, "CONMX_NO", params);
		ParamUtil.putStr2Map(request, "ORDERXH", params);
		lscxHeaService.insert(params);
		request.setAttribute("msg", "增加成功！");
		return mapping.findForward(SUCCESS);
	}

	//到修改界面
	public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String HEAD_NO = ParamUtil.get(request, "HEAD_NO");
		
		if(HEAD_NO.equals("") || HEAD_NO ==null ){
			request.setAttribute("msg", "抬头定制录入编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "HEAD_NO", params);
		Map entry = lscxHeaService.load(HEAD_NO);
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
		String HEAD_NO = ParamUtil.get(request, "HEAD_NO");
		if(HEAD_NO.equals("") || HEAD_NO ==null ){
			request.setAttribute("msg", "抬头定制录入编号不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "HEAD_NO", params);
		ParamUtil.putStr2Map(request, "HEAD_NAME", params);
		ParamUtil.putStr2Map(request, "HEARES", params);
		ParamUtil.putStr2Map(request, "HEASQL", params);
		ParamUtil.putStr2Map(request, "CONMX_NO", params);
		ParamUtil.putStr2Map(request, "ORDERXH", params);
		lscxHeaService.updateById(params);
		request.setAttribute("msg", "修改成功！");
		lscxHeaService.clearCaches(HEAD_NO);
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
		String HEAD_NO = ParamUtil.get(request, "HEAD_NO");
		if(HEAD_NO.equals("") || HEAD_NO ==null ){
			request.setAttribute("msg", "抬头定制录入编号不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "HEAD_NO", params);
		Map entry = lscxHeaService.load(HEAD_NO);
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
		String HEAD_NO = ParamUtil.get(request, "HEAD_NO");
		if(HEAD_NO.equals("") || HEAD_NO ==null ){
			request.setAttribute("msg", "抬头定制录入编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "HEAD_NO", params);
		lscxHeaService.delete(params);
		request.setAttribute("msg", "删除成功！");
		lscxHeaService.clearCaches(HEAD_NO);
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
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		ParamUtil.putStr2Map(request, "HEAD_NO", params);
		ParamUtil.putStr2Map(request, "HEAD_NAME", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = lscxHeaService.pageQuery(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}

	public void setLscxHeaService(LscxHeaService lscxHeaService) {
		this.lscxHeaService = lscxHeaService;
	}

}



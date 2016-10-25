package com.yitong.app.action.system;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yitong.app.service.system.ParamHisService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.util.ParamUtil;

/**
 * 配置表管理
 * 
 * @author Administrator
 * 
 */
public class ParamHisAction extends BaseAction {
	private ParamHisService paramHisService;

	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return mapping.findForward("add");
	}

	/**
	 * 
	 * 增加配置表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String id = ParamUtil.get(request, "id");
		if (ParamUtil.isEmpty(id)) {
			request.setAttribute("msg", "编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		params.put("id", id);
		Map entry = paramHisService.load(id);
		if (entry != null) {
			request.setAttribute("msg", "编号已存在，不能重复增加！");
			return mapping.findForward(FAILURE);
		}
		ParamUtil.putStr2Map(request, "tabSchema", params);
		ParamUtil.putStr2Map(request, "tabShotName", params);
		ParamUtil.putStr2Map(request, "tabYear", params);
		ParamUtil.putStr2Map(request, "tabType", params);
		ParamUtil.putStr2Map(request, "tabState", params);
		ParamUtil.putStr2Map(request, "tabCol", params);
		ParamUtil.putStr2Map(request, "tabcrc", params);
		paramHisService.insert(params);
		request.setAttribute("msg", "增加成功！");
		return mapping.findForward(SUCCESS);
	}

	public ActionForward toModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String id = ParamUtil.get(request, "id");
		if (ParamUtil.isEmpty(id)){
			request.setAttribute("msg", "编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map entry = paramHisService.load(id);
		request.setAttribute("rst", entry);

		return mapping.findForward("modi");
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
	public ActionForward modi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String id = ParamUtil.get(request, "id");
		if(ParamUtil.isEmpty(id)){
			request.setAttribute("msg", "编号不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map params = new HashMap();
		params.put("id", id);
		ParamUtil.putStr2Map(request, "tabSchema", params);
		ParamUtil.putStr2Map(request, "tabShotName", params);
		ParamUtil.putStr2Map(request, "tabYear", params);
		ParamUtil.putStr2Map(request, "tabType", params);
		ParamUtil.putStr2Map(request, "tabState", params);
		ParamUtil.putStr2Map(request, "tabCol", params);
		ParamUtil.putStr2Map(request, "tabcrc", params);
		paramHisService.updateById(params);
		request.setAttribute("msg", "修改成功！");
		return mapping.findForward(SUCCESS);
	}

	/**
	 * 查看
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String id = ParamUtil.get(request, "id");
		if(ParamUtil.isEmpty(id)){
			request.setAttribute("msg", "用户编号不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map entry = paramHisService.load(id);
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
		String id = ParamUtil.get(request, "id");
		if(ParamUtil.isEmpty(id)){
			request.setAttribute("msg", "用户编号不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map params = new HashMap();
		params.put("id", id);
		paramHisService.delete(params);
		request.setAttribute("msg", "删除成功！");
		
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
		ParamUtil.putStr2Map(request, "tabSchema", params);
		ParamUtil.putStr2Map(request, "tabShotName", params);
		ParamUtil.putStr2Map(request, "tabState", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = paramHisService.pageQuery(params, pageNo);
		request.setAttribute("page", page);

		return mapping.findForward("list");
	}

	public void setParamHisService(ParamHisService paramHisService) {
		this.paramHisService = paramHisService;
	}

}

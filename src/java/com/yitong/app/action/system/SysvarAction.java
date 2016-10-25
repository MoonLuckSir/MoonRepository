package com.yitong.app.action.system;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yitong.app.service.system.SysvarService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.util.ParamUtil;

/**
 * 渠道维护
 * 
 * @author Administrator
 * 
 */
public class SysvarAction extends BaseAction {
	private SysvarService sysvarService;

	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return mapping.findForward("add");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String varId = ParamUtil.get(request, "varId");
		if (ParamUtil.isEmpty(varId)) {
			request.setAttribute("msg", "参数码不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "varId", params);
		Map entry = sysvarService.load(varId);
		if (entry != null) {
			request.setAttribute("msg", "参数码已存在，不能重复增加！");
			return mapping.findForward(FAILURE);
		}
		ParamUtil.putStr2Map(request, "varName", params);
		ParamUtil.putStr2Map(request, "varType", params);
		ParamUtil.putStr2Map(request, "value", params);
		ParamUtil.putStr2Map(request, "varDesc", params);
		sysvarService.insert(params);
		String key = ParamUtil.get(request, "varType", "00");
		sysvarService.clearCaches(key);
		request.setAttribute("msg", "增加成功！");
		return mapping.findForward(SUCCESS);
	}

	public ActionForward toModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String varId = ParamUtil.get(request, "varId");
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "varId", params);
		Map entry = sysvarService.load(varId);
		request.setAttribute("var", entry);

		return mapping.findForward("modi");
	}

	public ActionForward modi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "varId", params);
		ParamUtil.putStr2Map(request, "varName", params);
		ParamUtil.putStr2Map(request, "varType", params);
		ParamUtil.putStr2Map(request, "value", params);
		ParamUtil.putStr2Map(request, "varDesc", params);
		sysvarService.updateById(params);
		String key = ParamUtil.get(request, "varType", "00");
		sysvarService.clearCaches(key);
		request.setAttribute("msg", "修改成功！");

		return mapping.findForward(SUCCESS);
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
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "varId", params);
		sysvarService.delete(params);
		String key = ParamUtil.get(request, "varType", "00");
		sysvarService.clearCaches(key);
		request.setAttribute("msg", "删除成功！");
		return mapping.findForward(SUCCESS);
	}

	/**
	 * 查询页面，暂时没有
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
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
		ParamUtil.putStr2Map(request, "varId", params);
		ParamUtil.putStr2Map(request, "varName", params);
		ParamUtil.putStr2Map(request, "varType", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = sysvarService.pageQuery(params, pageNo);
		request.setAttribute("page", page);

		return mapping.findForward("list");
	}

	public void setSysvarService(SysvarService sysvarService) {
		this.sysvarService = sysvarService;
	}

}

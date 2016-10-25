package com.yitong.app.action.system;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yitong.app.service.system.MenuService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.util.ParamUtil;

/**
 * 菜单管理
 * 
 * @author Administrator
 * 
 */
public class MenuAction extends BaseAction {
	private MenuService menuService;

	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return mapping.findForward("add");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "menuId", params);
		Map entry = menuService.load(params);
		if (entry != null) {
			request.setAttribute("msg", "菜单编号已存在，不能重复增加！");
			return mapping.findForward(FAILURE);
		}

		ParamUtil.putStr2Map(request, "menuName", params);
		ParamUtil.putStr2Map(request, "menuParId", params);
		ParamUtil.putStr2Map(request, "menuDesc", params);
		ParamUtil.putStr2Map(request, "menuUrl", params);
		ParamUtil.putInt2Map(request, "menuSort", params);
		ParamUtil.putStr2Map(request, "menuImg", params);
		menuService.insert(params);
		request.setAttribute("msg", "增加成功！");
		return mapping.findForward(SUCCESS);
	}

	public ActionForward toModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "menuId", params);
		Map entry = menuService.load(params);
		request.setAttribute("entry", entry);

		return mapping.findForward("modi");
	}

	public ActionForward modi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "menuId", params);
		ParamUtil.putStr2Map(request, "menuParId", params);
		ParamUtil.putStr2Map(request, "menuName", params);
		ParamUtil.putStr2Map(request, "menuDesc", params);
		ParamUtil.putStr2Map(request, "menuUrl", params);
		ParamUtil.putInt2Map(request, "menuSort", params);
		ParamUtil.putStr2Map(request, "menuImg", params);
		menuService.updateById(params);
		request.setAttribute("msg", "修改成功！");

		return mapping.findForward(SUCCESS);
	}

	public ActionForward toView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "menuId", params);
		Map entry = menuService.load(params);
		request.setAttribute("entry", entry);

		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "menuId", params);
		menuService.delete(params);
		request.setAttribute("msg", "删除成功！");
		return mapping.findForward(SUCCESS);
	}

	public ActionForward toQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return toList(mapping, form, request, response);
	}

	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "menuId", params);
		ParamUtil.putStr2Map(request, "menuName", params);
		ParamUtil.putStr2Map(request, "menuParId", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = menuService.pageQuery(params, pageNo);
		request.setAttribute("page", page);

		return mapping.findForward("list");
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

}

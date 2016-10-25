package com.yitong.app.action.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yitong.app.service.system.RoleService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.model.MenuHelper;
import com.yitong.commons.model.system.MenuInfo;
import com.yitong.commons.util.ParamUtil;
import com.yitong.commons.util.StringUtil;

/**
 * 角色管理
 * 
 * @author Administrator
 * 
 */
public class RoleAction extends BaseAction {
	private RoleService roleService;

	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return mapping.findForward("add");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "roleId", params);
		Map entry = roleService.load(params);
		if (entry != null) {
			request.setAttribute("msg", "角色编号已存在，不能重复增加！");
			return mapping.findForward(FAILURE);
		}

		ParamUtil.putStr2Map(request, "roleName", params);
		ParamUtil.putStr2Map(request, "roleDesc", params);
		ParamUtil.putStr2Map(request, "indexUrl", params);
		ParamUtil.putStr2Map(request, "roleLvl", params);
		ParamUtil.putStr2Map(request, "roleType", params);
		roleService.insert(params);
		request.setAttribute("msg", "增加成功！");
		return mapping.findForward(SUCCESS);
	}

	public ActionForward toModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String roleId = ParamUtil.get(request, "roleId");
		if (ParamUtil.isEmpty(roleId)) {
			request.setAttribute("msg", "请设置角色编号!");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "roleId", params);
		Map entry = roleService.load(params);
		request.setAttribute("entry", entry);

		return mapping.findForward("modi");
	}

	public ActionForward modi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "roleId", params);
		Map entry = roleService.load(params);
		if (entry == null) {
			request.setAttribute("msg", "角色不存在，请刷新数据！");
			return mapping.findForward(FAILURE);
		}
		ParamUtil.putStr2Map(request, "roleName", params);
		ParamUtil.putStr2Map(request, "roleDesc", params);
		ParamUtil.putStr2Map(request, "indexUrl", params);
		ParamUtil.putStr2Map(request, "roleLvl", params);
		ParamUtil.putStr2Map(request, "roleType", params);
		roleService.updateById(params);
		request.setAttribute("msg", "修改成功！");

		return mapping.findForward(SUCCESS);
	}

	public ActionForward toView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String roleId = ParamUtil.get(request, "roleId");
		if (ParamUtil.isEmpty(roleId)) {
			request.setAttribute("msg", "请设置角色编号!");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "roleId", params);
		Map entry = roleService.load(params);
		request.setAttribute("entry", entry);

		return mapping.findForward("view");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "roleId", params);
		roleService.delete(params);
		request.setAttribute("msg", "删除成功！");
		return mapping.findForward(SUCCESS);
	}

	public ActionForward toQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return toList(mapping, form, request, response);
	}

	/**
	 * 角色列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "roleId", params);
		ParamUtil.putStr2Map(request, "roleName", params);
		ParamUtil.putStr2Map(request, "roleParId", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = roleService.pageQuery(params, pageNo);
		request.setAttribute("page", page);

		return mapping.findForward("list");
	}

	/**
	 * 链接到分配菜单
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAlotMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String roleId = ParamUtil.get(request, "roleId");
		if (ParamUtil.isEmpty(roleId)) {
			request.setAttribute("msg", "请设置角色编号!");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		params.put("roleId", roleId);
		Map entry = roleService.load(params);
		if (entry == null) {
			request.setAttribute("msg", "角色不存在，请刷新数据！");
			return mapping.findForward(FAILURE);
		}
		// 加载角色
		request.setAttribute("entry", entry);
		// 加载所有菜单
		List allMenus = roleService.findAllMenus();
		// 生成JSON数据
		MenuHelper menuHelper = new MenuHelper(allMenus);
		request.setAttribute("treeData", menuHelper.getJsonData4MenuTree());
		// 加载已设置菜单
		loadCheckedMenu(roleId, request);

		return mapping.findForward("alotMenu");
	}

	/**
	 * 加載已分配的菜單
	 * 
	 * @param role
	 * @param request
	 */
	private void loadCheckedMenu(String roleId, HttpServletRequest request) {
		List list = roleService.findMenusByRoleId(roleId);
		if (list == null || list.isEmpty()) {
			request.setAttribute("oldData", "{size:0}");
		} else {
			StringBuffer bf = new StringBuffer();
			bf.append("{");
			bf.append("size:").append(list.size()).append(",");
			bf.append("children:[");
			int i = 0;
			for (; i < list.size() - 1; ++i) {
				MenuInfo m = (MenuInfo) list.get(i);
				bf.append("{");
				bf.append("id:\"").append(m.getMenuId()).append("\"");
				bf.append("},");
			}
			MenuInfo m = (MenuInfo) list.get(i);
			bf.append("{");
			bf.append("id:\"").append(m.getMenuId()).append("\"");
			bf.append("}");
			bf.append("]}");
			request.setAttribute("oldData", bf.toString());
		}
	}

	/**
	 * 分配角色菜单
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward alotMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String roleId = ParamUtil.get(request, "roleId");
		if (ParamUtil.isEmpty(roleId)) {
			request.setAttribute("msg", "请设置角色编号!");
			return mapping.findForward(FAILURE);
		}
		String checkedData = ParamUtil.get(request, "checkedData");
		if (ParamUtil.isEmpty(checkedData)) {
			request.setAttribute("msg", "请选择相关菜单!");
			return mapping.findForward(FAILURE);
		}
		String data = checkedData.replaceAll("\\[|\\]", "");
		Log("checkedData: " + data);

		List menus = new ArrayList();
		String[] aryMenus = data.split(",");
		for (int i = 0; i < aryMenus.length; i++) {
			String checked = aryMenus[i].trim();
			if (!ParamUtil.isEmpty(checked))
				menus.add(checked);
		}
		Map params = new HashMap();
		params.put("roleId", roleId);
		roleService.alotMenus(menus, params);

		request.setAttribute("msg", "菜单配置成功!");
		return mapping.findForward(SUCCESS);
	}

	/**
	 * 分配角色用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAlotUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String roleId = ParamUtil.get(request, "roleId");
		if (ParamUtil.isEmpty(roleId)) {
			request.setAttribute("msg", "请设置角色编号!");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		params.put("roleId", roleId);
		Map entry = roleService.load(params);
		if (entry == null) {
			request.setAttribute("msg", "角色不存在，请刷新数据！");
			return mapping.findForward(FAILURE);
		}
		// 加载角色
		request.setAttribute("entry", entry);

		return mapping.findForward("alotUser");
	}

	/**
	 * 加载某机构的用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward loadOrgUsers(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String orgId = ParamUtil.get(request, "orgId");
		Map params = new HashMap();
		params.put("orgId", orgId);
		Object rst = roleService.findUsers(params);
		request.setAttribute("users", rst);
		String roleId = ParamUtil.get(request, "roleId");
		// 加載已分配的用戶
		loadCheckedUser(roleId, orgId, request);

		return mapping.findForward("loadOrgUsers");
	}

	/**
	 * 加載已分配的用戶
	 * 
	 * @param role
	 * @param request
	 */
	private void loadCheckedUser(String roleId, String orgId,
			HttpServletRequest request) {
		Map params = new HashMap();
		params.put("roleId", roleId);
		params.put("orgId", orgId);
		List users = roleService.findUsers(params);
		if (users != null && !users.isEmpty()) {
			String[] temps = new String[users.size()];
			for (int j = users.size() - 1; j >= 0; j--) {
				Map temp = (Map) users.get(j);
				temps[j] = (String) temp.get("USER_ID");
			}
			request.setAttribute("checkedUsers", StringUtil.toStr(temps, "#"));
		} else {
			request.setAttribute("checkedUsers", "");
		}
	}

	public ActionForward alotUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String orgId = ParamUtil.get(request, "orgId");
		String roleId = ParamUtil.get(request, "roleId");
		if (ParamUtil.isEmpty(roleId) || ParamUtil.isEmpty(orgId)) {
			request.setAttribute("msg", "请检查参数设置!");
			return mapping.findForward(FAILURE);
		}
		String[] users = request.getParameterValues("userId");
		roleService.alotUsers(users, roleId, orgId);
		request.setAttribute("msg", "角色分配成功!");
		return mapping.findForward(SUCCESS);
	}

	/**
	 * 清除角色用户分配
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward alotUserRemove(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String roleId = ParamUtil.get(request, "roleId");
		if (ParamUtil.isEmpty(roleId)) {
			request.setAttribute("msg", "请检查参数设置!");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		params.put("roleId", roleId);
		roleService.deleteRoleUser(params);
		request.setAttribute("msg", "角色用户清理成功!");
		return mapping.findForward(SUCCESS);

	}

	/**
	 * 配制角色与报表关系
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAlotRpt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String roleId = ParamUtil.get(request, "roleId");
		if (ParamUtil.isEmpty(roleId)) {
			request.setAttribute("msg", "请设置角色编号!");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		params.put("roleId", roleId);
		Map entry = roleService.load(params);
		if (entry == null) {
			request.setAttribute("msg", "角色不存在，请刷新数据！");
			return mapping.findForward(FAILURE);
		}
		// 加载角色
		request.setAttribute("entry", entry);
		// 加载报表子类别
		List rst = roleService.findParamters("0302");
		request.setAttribute("rst0302", rst);
		// 加载报表类别
		rst = roleService.findParamters("02");
		request.setAttribute("rst02", rst);

		return mapping.findForward("alotRpt");
	}

	/**
	 * 加载报表清单
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward loadReports(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "rptType", params);
		ParamUtil.putStr2Map(request, "rptParType", params);
		List rpts = roleService.findReports(params);
		request.setAttribute("rpts", rpts);
		// 加载报表清单
		ParamUtil.putStr2Map(request, "roleId", params);
		List checkeds = roleService.findRoleReports(params);
		if (checkeds != null && !checkeds.isEmpty()) {
			StringBuffer bf = new StringBuffer();
			for (Iterator iter = checkeds.iterator(); iter.hasNext();) {
				Map temp = (Map) iter.next();
				bf.append(temp.get("RPT_ID")).append("#");
			}
			request.setAttribute("checkedRpts", bf.toString());
		} else {
			request.setAttribute("checkedRpts", "");
		}

		return mapping.findForward("loadReports");
	}

	public ActionForward alotRpt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String rptType = ParamUtil.get(request, "rptType");
		String roleId = ParamUtil.get(request, "roleId");
		if (ParamUtil.isEmpty(roleId) || ParamUtil.isEmpty(rptType)) {
			request.setAttribute("msg", "请检查参数设置!");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "roleId", params);
		ParamUtil.putStr2Map(request, "rptType", params);
		ParamUtil.putStr2Map(request, "rptParType", params);
		String[] users = request.getParameterValues("rptId");
		roleService.alotReports(users, params);
		request.setAttribute("msg", "角色分配成功!");
		return mapping.findForward(SUCCESS);
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

}

package com.yitong.app.action.system;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yitong.app.service.system.UserService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.model.UserSession;
import com.yitong.commons.util.ParamUtil;
import com.yitong.commons.util.security.MD5Encrypt;

/**
 * 用户管理
 * 
 * @author James-zhang 
 * 
 */
public class UserAction extends BaseAction {
	private UserService userService;

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
		String userId = ParamUtil.get(request, "userId");
		if (ParamUtil.isEmpty(userId)) {
			request.setAttribute("msg", "用户编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "userId", params);
		Map entry = userService.load(userId);
		if (entry != null) {
			request.setAttribute("msg", "用户编号已存在，不能重复增加！");
			return mapping.findForward(FAILURE);
		}
		ParamUtil.putStr2Map(request, "userName", params);
		ParamUtil.putStr2Map(request, "orgId", params);
		ParamUtil.putStr2Map(request, "rptRight", params);
		ParamUtil.putStr2Map(request, "userPos", params);
		ParamUtil.putStr2Map(request, "eMail", params);
		ParamUtil.putStr2Map(request, "phone", params);
		params.put("userPwd", MD5Encrypt.MD5(DEF_PWD));
		params.put("userStus", "1");
		ParamUtil.putStr2Map(request, "userDesc", params);
		userService.insert(params);
		request.setAttribute("msg", "增加成功！");
		return mapping.findForward(SUCCESS);
	}

	public ActionForward toModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String userId = ParamUtil.get(request, "userId");
		if(userId.equals("") || userId ==null ){
			request.setAttribute("msg", "用户编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "userId", params);
		Map entry = userService.loadUser(userId);
		request.setAttribute("rst", entry);

		return mapping.findForward("modi");
	}
	
	public ActionForward toSelfModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserSession userSess = this.getUserSession(request);
		Map entry = userService.loadUser(userSess.getUserId());
		request.setAttribute("rst", entry);

		return mapping.findForward("selfM");
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
		String userId = ParamUtil.get(request, "userId");
		if(userId.equals("") || userId ==null ){
			request.setAttribute("msg", "用户编号不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "userId", params);
		ParamUtil.putStr2Map(request, "userName", params);
		ParamUtil.putStr2Map(request, "orgId", params);
		ParamUtil.putStr2Map(request, "userPos", params);
		ParamUtil.putStr2Map(request, "rptRight", params);
		ParamUtil.putStr2Map(request, "eMail", params);
		ParamUtil.putStr2Map(request, "phone", params);
		ParamUtil.putStr2Map(request, "userDesc", params);
		userService.updateById(params);
		request.setAttribute("msg", "修改成功！");
		userService.clearCaches(userId);

		return mapping.findForward(SUCCESS);
	}
	
	
	/**
	 * 设置密码
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */	
	public ActionForward toSetPwd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserSession userSess = this.getUserSession(request);
		request.setAttribute("userSess", userSess);
		return mapping.findForward("setPwd");
	}
	
	/**
	 * 设置密码
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward setPwd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		String userId = ParamUtil.get(request, "userId");
		String oldPassword = ParamUtil.get(request, "oldPwd");
		String newPassword = ParamUtil.get(request, "userPwd");
		Map rst = userService.loadUser(userId);
		if (rst == null || "0".equals(rst.get("userStatus"))) {
			request.setAttribute("msg", "用户编号不存在！");
			return mapping.findForward(FAILURE);
		}
		
		String userPwd = (String) rst.get("USER_PWD");
		if (!MD5Encrypt.MD5(oldPassword).equals(userPwd)) {
			request.setAttribute("msg", "密码错误，请重新输入密码！");
			return mapping.findForward(FAILURE);
		}
		
		ParamUtil.putStr2Map(request, "userId", params);
		params.put("userPwd", MD5Encrypt.MD5(newPassword));
		userService.updateById(params);
		request.setAttribute("msg", "密码设置成功！");
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
		String userId = ParamUtil.get(request, "userId");
		if(userId.equals("") || userId ==null ){
			request.setAttribute("msg", "用户编号不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "userId", params);
		Map entry = userService.loadUser(userId);
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
		String userId = ParamUtil.get(request, "userId");
		if(userId.equals("") || userId ==null ){
			request.setAttribute("msg", "用户编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "userId", params);
		userService.delete(params);
		request.setAttribute("msg", "删除成功！");
		userService.clearCaches(userId);
		
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
		ParamUtil.putStr2Map(request, "userId", params);
		ParamUtil.putStr2Map(request, "userName", params);
		ParamUtil.putStr2Map(request, "orgId", params);
		ParamUtil.putStr2Map(request, "userPos", params);
		UserSession userSess = getUserSession(request);
		if( !"1".equals(userSess.getOrgLvl())){
			String  orgNo = userService.findOrgNo(userSess.getOrgId());
			params.put("orgNo", orgNo);
		}
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = userService.pageQuery(params, pageNo);
		request.setAttribute("page", page);

		return mapping.findForward("list");
	}

	/**
	 * 重设密码
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward resetPwd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		String userId = ParamUtil.get(request, "userId");
		if(userId.equals("") || userId ==null ){
			request.setAttribute("msg", "用户编号不存在！");
			return mapping.findForward(FAILURE);
		}
		String userPwd = MD5Encrypt.MD5(DEF_PWD);
		ParamUtil.putStr2Map(request, "userId", params);
		params.put("userPwd", userPwd);
		userService.updateById(params);
		request.setAttribute("msg", "密码重置成功！");
		userService.clearCaches(userId);
		
		return mapping.findForward(SUCCESS);
	}
	
	/**
	 * 分配角色
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAlotRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String userId = ParamUtil.get(request, "userId");
		if(userId.equals("") || userId ==null ){
			request.setAttribute("msg", "用户编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "userId", params);
		Map entry = userService.query(params);
		if (entry == null) {
			request.setAttribute("msg", "用户不存在，请刷新数据！");
			return mapping.findForward(FAILURE);
		}
		request.setAttribute("entry", entry);
		return mapping.findForward("alotRole");
	}
	
	/**
	 * 加载角色清单
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward loadRoles(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		List roles = userService.findRoles(params);
		request.setAttribute("roles", roles);
		// 加载报表清单
		String userId = ParamUtil.get(request, "userId");
		if (ParamUtil.isEmpty(userId)) {
			request.setAttribute("msg", "用户编号不存在!");
			return mapping.findForward(FAILURE);
		}
		ParamUtil.putStr2Map(request, "userId", params);
		List checkeds = userService.findUserRoles(params);
		if (checkeds != null && !checkeds.isEmpty()) {
			StringBuffer bf = new StringBuffer();
			for (Iterator iter = checkeds.iterator(); iter.hasNext();) {
				Map temp = (Map) iter.next();
				bf.append(temp.get("ROLE_ID")).append("#");
			}
			request.setAttribute("checkedRoles", bf.toString());
		} else {
			request.setAttribute("checkedRoles", "");
		}

		return mapping.findForward("loadUserRoles");
	}
	
	/**
	 * 保存分配的角色
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward alotUserRoles(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String userId = ParamUtil.get(request, "userId");
		if (ParamUtil.isEmpty(userId)) {
			request.setAttribute("msg", "用户不存在!");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "userId", params);
		String[] roles = request.getParameterValues("roleId");
		userService.alotUserRoles(roles, params);
		request.setAttribute("msg", "角色分配成功!");
		return mapping.findForward(SUCCESS);
	}

	
	/**
	 * 分配报表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAlotReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String userId = ParamUtil.get(request, "userId");
		if(userId.equals("") || userId ==null ){
			request.setAttribute("msg", "用户不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "userId", params);
		Map entry = userService.query(params);
		if (entry == null) {
			request.setAttribute("msg", "用户不存在，请刷新数据！");
			return mapping.findForward(FAILURE);
		}
		request.setAttribute("entry", entry);
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
	public ActionForward loadRpts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		List rpts = userService.findRpts(params);
		request.setAttribute("rpts", rpts);
		// 加载报表清单
		String userId = ParamUtil.get(request, "userId");
		if (ParamUtil.isEmpty(userId)) {
			request.setAttribute("msg", "用户不存在!");
			return mapping.findForward(FAILURE);
		}
		ParamUtil.putStr2Map(request, "userId", params);
		List checkeds = userService.findUserRpts(params);
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

		return mapping.findForward("loadUserRpts");
	}
	
	/**
	 * 保存分配的角色
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward alotUserRpts(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String userId = ParamUtil.get(request, "userId");
		if (ParamUtil.isEmpty(userId)) {
			request.setAttribute("msg", "请检查参数用户!");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "userId", params);
		String[] rpts = request.getParameterValues("rptId");
		userService.alotUserRpts(rpts, params);
		request.setAttribute("msg", "角色分配成功!");
		return mapping.findForward(SUCCESS);
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	/**
	 * 启动用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward avail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		String userId = ParamUtil.get(request, "userId");
		if(userId.equals("") || userId ==null ){
			request.setAttribute("msg", "用户编号不存在！");
			return mapping.findForward(FAILURE);
		}
		ParamUtil.putStr2Map(request, "userId", params);
		params.put("userStus", "1");
		userService.updateById(params);
		request.setAttribute("msg", "启动用户成功！");
		userService.clearCaches(userId);
		
		return mapping.findForward(SUCCESS);
	}
	
	/**
	 * 注销用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward unavail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		String userId = ParamUtil.get(request, "userId");
		if(userId.equals("") || userId ==null ){
			request.setAttribute("msg", "用户编号不存在！");
			return mapping.findForward(FAILURE);
		}
		ParamUtil.putStr2Map(request, "userId", params);
		params.put("userStus", "0");
		userService.updateById(params);
		request.setAttribute("msg", "注销用户成功！");
		userService.clearCaches(userId);
		
		return mapping.findForward(SUCCESS);
	}
}
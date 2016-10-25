package com.yitong.app.action.system;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yitong.app.service.system.LoginService;
import com.yitong.app.service.system.SysvarService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.Consts;
import com.yitong.commons.model.MenuHelper;
import com.yitong.commons.model.UserSession;
import com.yitong.commons.util.DateUtil;
import com.yitong.commons.util.ParamUtil;
import com.yitong.commons.util.security.MD5Encrypt;

public class LoginAction extends BaseAction {

	private LoginService loginService;
	private SysvarService sysvarService;

	public void setSysvarService(SysvarService sysvarService) {
		this.sysvarService = sysvarService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	/**
	 * 登陆校验
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward authLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 验证用户及密码
		String userId = ParamUtil.get(request, "userId");
		String password = request.getParameter("userPwd");
		if (ParamUtil.isEmpty(userId) || ParamUtil.isEmpty(password)) {
			request.setAttribute("msg", "您未被授权，请检查登陆信息！");
			return mapping.findForward(FAILURE);
		}
		Map rst = loginService.loadUser(userId);
		// Map rst = new HashMap();
		if (rst == null || "0".equals(rst.get("userStatus") + "")) {
			request.setAttribute("msg", "用户不存在，请重新登陆！");
			return mapping.findForward(FAILURE);
		}
		if( "0".equals(rst.get("USER_STUS"))){
			request.setAttribute("msg", "用户被注销！");
			return mapping.findForward(FAILURE);
		}
		String userPwd = (String) rst.get("USER_PWD");
		if (!MD5Encrypt.MD5(password).equals(userPwd)) {
			request.setAttribute("msg", "密码错误，请重新登陆！");
			return mapping.findForward(FAILURE);
		}
		// log.info("DEF_PWD: " + DEF_PWD);
		Map entry = sysvarService.load("000001");
		Calendar cal = Calendar.getInstance();
		if(entry != null){
			String pld = String.valueOf(rst.get("PWD_LAST_DATE"));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Integer add = new Integer(String.valueOf(entry.get("VALUE")));
			try {
				cal.setTime(sdf.parse(pld));
				cal.add(Calendar.DATE, add.intValue());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (password.equals(DEF_PWD)|| (cal.compareTo(Calendar.getInstance()) < 0)) {
			request.setAttribute("userSess", rst);
			return mapping.findForward("setPwd");
		}
		// 存储Session
		UserSession userSess = new UserSession();
		userSess.setUserId(userId);
		userSess.setOptUser(userId);
		userSess.setUserName((String) rst.get("USER_NAME"));
		userSess.setOrgName((String) rst.get("ORG_NAME"));
		userSess.setOrgId((String) rst.get("ORG_ID"));
		userSess.setOrgLvl((String) rst.get("ORG_LVL"));
		userSess.setOrgParId((String) rst.get("ORG_PAR_ID"));
		userSess.setUserType((String) rst.get("USER_TYPE"));
		userSess.setUserTypeName((String) rst.get("USER_TYPE_NAME"));

		request.getSession().setAttribute(Consts.USR_SESS, userSess);
		request.setAttribute(Consts.USR_JSON,
				generyUserSession(userSess, false));

		Map params = new HashMap();
		params.put("userId", userId);
		// 加载用户菜单
		List usrMenus = loginService.findMenuByUserId(params);
		//List usrMenus = loginService.findAllMenus();
		if (usrMenus == null || usrMenus.isEmpty()) {
			request.setAttribute("msg", "您未被授权，请检查角色菜单配置！");
			return mapping.findForward(FAILURE);
		}

		// log.info("MENU_ROOT: " + MenuHelper.MENU_ROOT);
		MenuHelper menuHelper = new MenuHelper(usrMenus);
		request.setAttribute("treeData", menuHelper.getJsonData4MenuTree());
		// 获取主角色
		// 设置系统首页
		setIndexUrl(request, userId);
		// 登陆日志
		// this.saveLog(request, loginService, "0", "系统登陆", "系统登陆成功!");

		return mapping.findForward("indexGo");
	}

	/**
	 * 设置用户页面会话
	 */
	private String generyUserSession(UserSession user, boolean portalLogin) {
		String today = DateUtil.format(new Date(), "yyyy年MM月dd日");
		StringBuffer bf = new StringBuffer(200);
		bf.append("{");
		bf.append("today:'").append(today).append("'");
		bf.append(",userId:'").append(user.getUserId()).append("'");
		bf.append(",userName:'").append(user.getUserName()).append("'");
		bf.append(",orgName:'").append(user.getOrgName()).append("'");
		bf.append(",orgId:'").append(user.getOrgId()).append("'");
		bf.append(",userType:'").append(user.getUserType()).append("'");
		bf.append(",typeName:'").append(user.getUserTypeName()).append("'");
		bf.append(",portal:").append(portalLogin);
		bf.append("}");
		return bf.toString();
	}

	/**
	 * 设置系统首页
	 * 
	 * @param request
	 * @param usr
	 */
	private void setIndexUrl(HttpServletRequest request, String userId) {
		String indexUrl = "pages/index/main.html";
		request.setAttribute("indexUrl", indexUrl);
	}

	/**
	 * 密码设置页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward setPwdLogin(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 验证用户及密码
		String userId = ParamUtil.get(request, "userId");
		String password = request.getParameter("userPwd");
		if (ParamUtil.isEmpty(userId) || ParamUtil.isEmpty(password)) {
			request.setAttribute("msg", "您未被授权，请检查登陆信息！");
			return mapping.findForward(FAILURE);
		}
		Map rst = loginService.loadUser(userId);
		if (rst == null || "0".equals(rst.get("userStatus"))) {
			request.setAttribute("msg", "用户不存在，请重新登陆！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		params.put("userId", userId);
		params.put("userPwd", MD5Encrypt.MD5(password));
		loginService.updatePwd(params);

		return this.authLogin(mapping, form, request, response);
	}
}
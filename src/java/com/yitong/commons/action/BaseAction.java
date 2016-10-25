package com.yitong.commons.action;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.RequestUtils;

import com.yitong.commons.model.Consts;
import com.yitong.commons.model.UserSession;
import com.yitong.commons.service.IBaseService;
import com.yitong.commons.util.ParamUtil;

/**
 * 
 * <ul>
 * Action 基类
 * <li>提供了基础参数获取方法:setBean(object,request)</li>
 * <li>提供了用户会话获取方法:getUserSession()</li>
 * </ul>
 * <ul>
 * 相关 Action 配制
 * <li>需要在/WEB-INF/spring-cfg/*.xml,/WEB-INF/struts-cfg/*.xml中声明并定义</li>
 * </ul>
 * 
 * @author uke
 * 
 */
public abstract class BaseAction extends DispatchAction implements Consts {

	/**
	 * 加载页面Form内容:
	 * <p>
	 * 对字符串类型的的属性可精确匹配
	 * </p>
	 * 
	 * @param bean
	 * @param request
	 */
	public void setBean(Object bean, HttpServletRequest request) {
		try {
			RequestUtils.populate(bean, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取用户Session
	 * 
	 * @param request
	 * @return
	 */
	public UserSession getUserSession(HttpServletRequest request) {
		return (UserSession) request.getSession().getAttribute(USR_SESS);
	}

	/**
	 * 保存访问日志
	 * 
	 * @param request
	 * @param type
	 * @param mode
	 * @param msg
	 * @return
	 */
	public boolean saveLog(HttpServletRequest request, IBaseService service,
			String type, String mode, String desc) {
		// 不保存访问痕迹
		saveLog(request, service, type, mode, desc, false, false);
		return true;
	}

	/**
	 * 保存访问日志
	 * 
	 * @param request
	 * @param service
	 * @param type
	 * @param mode
	 * @param desc
	 * @param saveConf
	 * @return
	 */
	public boolean saveLog(HttpServletRequest request, IBaseService service,
			String type, String mode, String desc, boolean saveConf,
			boolean forbidVar) {
		Map params = new HashMap();
		// 用户信息
		UserSession userSess = this.getUserSession(request);
		params.put("logMan", userSess.getUserId());
		params.put("logOrg", userSess.getOrgId());
		params.put("userType", userSess.getUserType());
		// 客户端IP
		params.put("accIp", request.getRemoteAddr());
		// 访问内容
		params.put("logType", type);
		params.put("logMode", mode);
		params.put("logDesc", desc);
		// 保存访问痕迹,注意日志大小
		if (saveConf){
			params.put("logConf", ParamUtil.favorit(request, forbidVar));
		}

		// 服务端IP
		try {
			InetAddress server = InetAddress.getLocalHost();
			params.put("sevIp", server.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// 保存日志
		service.saveLog(params);
		return true;
	}

	/**
	 * 临时日志,只显示不存储
	 * 
	 * @param request
	 * @param msg
	 */
	public void Log(String msg) {
		if (DEBUG) {
			log.debug("[系统日志] " + msg);
		}
	}

}

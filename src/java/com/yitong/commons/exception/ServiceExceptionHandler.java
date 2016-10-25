package com.yitong.commons.exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

/**
 * 自定义异常处理类
 * 
 * @author Covey
 * 
 */
public class ServiceExceptionHandler extends ExceptionHandler {

	/**
	 * 异常跳转控制
	 */
	public ActionForward execute(Exception ex, ExceptionConfig ec,
			ActionMapping mapping, ActionForm formInstance,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		ActionForward forward = null;
		if (ec.getPath() != null) {
			forward = new ActionForward(ec.getPath());
		} else {
			forward = mapping.getInputForward();
		}
		if (ex instanceof ServiceException) {
			System.out.println("Throw ServiceException!");
			ServiceException se = (ServiceException) ex;
			request.setAttribute("msg", se.getMessage());
			return forward;
		}
		return super.execute(ex, ec, mapping, formInstance, request, response);
	}
}

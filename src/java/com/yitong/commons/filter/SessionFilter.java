package com.yitong.commons.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.yitong.commons.model.Consts;
import com.yitong.commons.model.ParamCover;
import com.yitong.commons.util.ParamUtil;
import com.yitong.commons.util.StringUtil;

public class SessionFilter implements Filter, Consts {

	private String forwardTo;
	private String[] checkedAttrs;
	private String[] openedURL;

	public void init(FilterConfig filterConfig) throws ServletException {
		forwardTo = filterConfig.getInitParameter("forwardTo");
		String sessAttrs = filterConfig
				.getInitParameter("checkedSessionAttribute");
		checkedAttrs = StringUtil.toArr(sessAttrs);
		String urls = filterConfig.getInitParameter("openedURL");
		openedURL = StringUtil.toArr(urls);
	}

	/**
	 * 查看session是否過期， 如果是則導向指定頁面（web上配置的特定頁面除外）
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (checkSessionValidate(request, response)) {
			chain.doFilter(request, response);
		}
	}

	/**
	 * 查看Session是否过期
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	private boolean checkSessionValidate(ServletRequest request,
			ServletResponse response) throws ServletException, IOException {
		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			if (checkedAttrs != null) {
				HttpSession session = httpRequest.getSession();
				for (int i = 0; i < checkedAttrs.length; i++) {
					// Session是否过期
					if (session.getAttribute(checkedAttrs[i]) == null) {
						String servletPath = httpRequest.getServletPath()
								.replaceAll("//", "/");
						// 检查URL是否在监听范围内
						if (StringUtil.indexOf(openedURL, servletPath, true) < 0) {
							httpRequest.getRequestDispatcher(forwardTo)
									.forward(request, response);
							return false;
						}
					}
				}
			}
			// 日志監聽
			Log(httpRequest);
			// 參數傳遞
			paramCover(httpRequest);
		}
		return true;
	}

	/**
	 * 系统日志,显示Request請求中的所有參數
	 * 
	 * @param request
	 */
	protected void Log(HttpServletRequest request) {
		if (DEBUG) {
			System.out.println("[系統日志] " + ParamUtil.favorit(request));
		}
	}

	/**
	 * 參數轉換及傳遞
	 * 
	 * @param request
	 */
	protected void paramCover(HttpServletRequest request) {
		request.setAttribute(PARAM_COVER, new ParamCover(request));
	}

	public void destroy() {
	}
}

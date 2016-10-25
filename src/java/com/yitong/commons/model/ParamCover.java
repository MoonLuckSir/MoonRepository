package com.yitong.commons.model;

import javax.servlet.http.HttpServletRequest;

import com.yitong.commons.util.ParamUtil;

/**
 * HTPP请求参数转换工具类
 * <ul>
 * <li>A => _A: 加工参数</li>
 * <li>A => A ：保留原参数</li>
 * <li>_A=>_A : 保留加工过的参数</li>
 * <li>_A=> A : 还原加工过的参数</li>
 * </ul>
 * @author uke
 * 
 */
public class ParamCover {
	/**
	 * HttpServletRequest 参数前缀
	 */
	private static final String PREX = "_";
	private HttpServletRequest request;
	private String[] forbids = new String[] { "action", "pageNo"};

	public ParamCover(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 加工参数，a => _a ，参数以Hidden Input方式存储
	 * <ul>
	 * <li>参数名不包括action</li>
	 * <li>参数名不包括空值及NULL值</li>
	 * </ul>
	 * 
	 * @return
	 */
	public String getCoveredInputs() {
		return ParamUtil.fixParamToHtml(request, PREX);
	}

	/**
	 * 解析参数, 将a => a, 参数以Hidden Input方式存储
	 * 
	 * @return
	 */
	public String getUnCoveredInputs() {
		return ParamUtil.fixParamToHtml(request, "");
	}

	/**
	 * 解析参数, 将_a => _a, 参数以Hidden Input方式存储
	 * 
	 * @return
	 */
	public String getUnCovered_Inputs() {
		return ParamUtil.generyFixParamToHtml(request, PREX);
	}

	/**
	 * 解析参数, 将a => a, 参数以Hidden Input方式存储, 除必要的forbids参数外
	 * 
	 * @return
	 */
	public String getUnCoveredForbidInputs() {
		return ParamUtil.forbidFixParamToHtml(request, "", forbids);
	}

	/**
	 * 还原加工的参数，将_a => a，参数以Hidden Input方式存储 未包含_backUrl
	 * 
	 * @return
	 */
	public String getDecodeInputs() {
		return ParamUtil.decodeParamToHtml(request, PREX);
	}

	public String[] getForbids() {
		return forbids;
	}

	public void setForbids(String[] forbids) {
		this.forbids = forbids;
	}

}
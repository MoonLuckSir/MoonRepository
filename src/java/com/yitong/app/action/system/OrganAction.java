package com.yitong.app.action.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yitong.app.service.system.OrganService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.model.OPTreeHelper;
import com.yitong.commons.util.ParamUtil;

/**
 * 渠道维护
 * 
 * @author Administrator
 * 
 */
public class OrganAction extends BaseAction {
	private OrganService organService;

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
		ParamUtil.putStr2Map(request, "orgId", params);
		ParamUtil.putStr2Map(request, "orgName", params);
		ParamUtil.putStr2Map(request, "orgLvl", params);
		ParamUtil.putStr2Map(request, "orgParId", params);
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = organService.pageQuery(params, pageNo);
		request.setAttribute("page", page);

		return mapping.findForward("list");
	}

	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public void seletor(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String orgId = ParamUtil.get(request, "orgId");
		String root = OPTreeHelper.ROOT;
		if (!ParamUtil.isEmpty(orgId)) {
			root = orgId;
		}
		OPTreeHelper opTree = new OPTreeHelper(organService);
		opTree.setShowUser(ParamUtil.getBoolean(request, "showUser"));
		opTree.setShowSon(false);
		opTree.setOrgLvl(ParamUtil.getInt(request, "orgLvl", 5));
		opTree.setUserType(ParamUtil.get(request, "userType"));
		String data = opTree.getJsonData4OPTree(root);
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(data);
		out.close();
	}
    public void setOrganService(OrganService organService) {
		this.organService = organService;
	}

}

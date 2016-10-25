package com.yitong.app.action.judicial.lscxmb;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yitong.app.service.judicial.lscxmb.LscxMbService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.util.DateUtil;
import com.yitong.commons.util.ParamUtil;


/**
 * 历史查询模板编
 * 
 * @author JACKY-XING
 * 
 */
public class LscxMbAction extends BaseAction {
	private LscxMbService lscxMbService;

	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("usrId", request.getParameter("usrId"));
		request.setAttribute("orgNo", request.getParameter("orgNo"));
		
		/*String rst = "SELECT * FROM PUB.REP_SYS_VAR order by VAR_VALUE";
		List allList1 = lscxMbService.findVar(rst);
		
		Map map = null;
		List mode = new ArrayList();
	
		if (allList1.size() > 0) {
			for (int i = 0; i < allList1.size(); i++) {
				map = (Map) allList1.get(i);
				String VAR_TYP = map.get("VAR_TYP")+"";
				if (VAR_TYP.equals("0101")) {
					String VAR_VALUE = map.get("VAR_VALUE")+"";
					String VAR_NAME = map.get("VAR_NAME")+"";

					Map temp = new HashMap();
					temp.put("VAR_VALUE", VAR_VALUE);
					temp.put("VAR_NAME", VAR_NAME);

					mode.add(temp);
				}

			}

		}
		request.setAttribute("mode", mode);*/
		return mapping.findForward("add");
	}

	/**
	 * 
	 * 增加
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Map params = new HashMap();
		String userId = (String) request.getSession().getAttribute(
				"hisRepUserId");
		System.out.println(userId);
		Date date = DateUtil.getDate();
		String today = DateUtil.formatDateToStr(date, "yyyy-MM-dd HH:mm:ss");
		System.out.println("today=" + today);
		params.put("CHECKIN_TIME", today);
		// 得到编号
		String REPMOD_NO = lscxMbService.getSeq(date);
		System.out.println("REPMOD_NO==" + REPMOD_NO);

		params = new HashMap();
		params.put("REPMOD_NO", REPMOD_NO);
		params.put("CHECKIN_TIME", today);
		ParamUtil.putStr2Map(request, "REP_NO", params);
		ParamUtil.putStr2Map(request, "REP_NAME", params);
		ParamUtil.putStr2Map(request, "SELECT_SQL", params);
		ParamUtil.putStr2Map(request, "FROM_SQL", params);
		ParamUtil.putStr2Map(request, "WHERE_SQL", params);
		ParamUtil.putStr2Map(request, "ORDER_SQL", params);
		ParamUtil.putStr2Map(request, "WHERE_SQL", params);
		ParamUtil.putStr2Map(request, "REP_DESC", params);
		ParamUtil.putStr2Map(request, "ORGSQL", params);
		ParamUtil.putStr2Map(request, "ISKORG", params);
		ParamUtil.putStr2Map(request, "ISSH", params);
		ParamUtil.putStr2Map(request, "CHECKIN_DEPT", params);
		ParamUtil.putStr2Map(request, "FLAGSTATE", params);
		ParamUtil.putStr2Map(request, "resource", params);
		params.put("CHECKIN_USER", request.getParameter("usrId"));
		params.put("CHECKIN_ORGAN", request.getParameter("orgNo"));
		params.put("STATE", "制定");

		lscxMbService.insert(params);
		request.setAttribute("msg", "增加成功！");
		return mapping.findForward(SUCCESS);
	}

	// 到修改界面
	public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String REPMOD_NO = ParamUtil.get(request, "REPMOD_NO");

		if (REPMOD_NO.equals("") || REPMOD_NO == null) {
			request.setAttribute("msg", "历史查询模板编号不存在！");
			return mapping.findForward(FAILURE);
		}
		/*String rst = "SELECT * FROM PUB.REP_SYS_VAR order by VAR_VALUE";
		List allList1 = lscxMbService.findVar(rst);
		
		Map map = null;
		List mode = new ArrayList();
	
		if (allList1.size() > 0) {
			for (int i = 0; i < allList1.size(); i++) {
				map = (Map) allList1.get(i);
				String VAR_TYP = map.get("VAR_TYP")+"";
				if (VAR_TYP.equals("0101")) {
					String VAR_VALUE = map.get("VAR_VALUE")+"";
					String VAR_NAME = map.get("VAR_NAME")+"";

					Map temp = new HashMap();
					temp.put("VAR_VALUE", VAR_VALUE);
					temp.put("VAR_NAME", VAR_NAME);

					mode.add(temp);
				}

			}

		}
		request.setAttribute("mode", mode);*/
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		Map entry = lscxMbService.load(REPMOD_NO);
		request.setAttribute("rst", entry);

		return mapping.findForward("update");
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
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String REPMOD_NO = ParamUtil.get(request, "REPMOD_NO");
		if (REPMOD_NO.equals("") || REPMOD_NO == null) {
			request.setAttribute("msg", "历史查询模板编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "REP_NO", params);
		ParamUtil.putStr2Map(request, "REP_NAME", params);
		ParamUtil.putStr2Map(request, "SELECT_SQL", params);
		ParamUtil.putStr2Map(request, "FROM_SQL", params);
		ParamUtil.putStr2Map(request, "WHERE_SQL", params);
		ParamUtil.putStr2Map(request, "ORDER_SQL", params);
		ParamUtil.putStr2Map(request, "REP_DESC", params);
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		ParamUtil.putStr2Map(request, "CHECKIN_DEPT", params);
		ParamUtil.putStr2Map(request, "ORGSQL", params);
		ParamUtil.putStr2Map(request, "ISKORG", params);
		ParamUtil.putStr2Map(request, "ISSH", params);
		ParamUtil.putStr2Map(request, "FLAGSTATE", params);
		ParamUtil.putStr2Map(request, "resource", params);
		lscxMbService.updateById(params);
		request.setAttribute("msg", "修改成功！");
		lscxMbService.clearCaches(REPMOD_NO);

		return mapping.findForward(SUCCESS);
	}

	/**
	 * 查看详细信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String REPMOD_NO = ParamUtil.get(request, "REPMOD_NO");
		if (REPMOD_NO.equals("") || REPMOD_NO == null) {
			request.setAttribute("msg", "历史查询模板编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		Map entry = lscxMbService.load(REPMOD_NO);
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
		String REPMOD_NO = ParamUtil.get(request, "REPMOD_NO");
		if (REPMOD_NO.equals("") || REPMOD_NO == null) {
			request.setAttribute("msg", "历史查询模板编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		lscxMbService.delete(params);
		request.setAttribute("msg", "删除成功！");
		lscxMbService.clearCaches(REPMOD_NO);

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
		ParamUtil.putStr2Map(request, "REP_NAME", params);
		ParamUtil.putStr2Map(request, "STATE", params);
	
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = lscxMbService.pageQuery(params, pageNo);
		request.setAttribute("usrId", request.getParameter("usrId"));
		request.setAttribute("orgNo", request.getParameter("orgNo"));
		request.setAttribute("page", page);
		request.setAttribute("STATE", this.getOptions());
		return mapping.findForward("list");
	}



	/**
	 * 冻结
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward dodj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String REPMOD_NO = ParamUtil.get(request, "REPMOD_NO");
		if (REPMOD_NO.equals("") || REPMOD_NO == null) {
			request.setAttribute("msg", "历史查询模板编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		params.put("STATE", "冻结");
		lscxMbService.updateById(params);
		request.setAttribute("msg", "冻结成功！");
		lscxMbService.clearCaches(REPMOD_NO);
		return mapping.findForward(SUCCESS);
	}

	/**
	 * 启用
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doqy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String REPMOD_NO = ParamUtil.get(request, "REPMOD_NO");
		if (REPMOD_NO.equals("") || REPMOD_NO == null) {
			request.setAttribute("msg", "历史查询模板编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		params.put("STATE", "启用");
		lscxMbService.updateById(params);
		request.setAttribute("msg", "启用成功！");
		lscxMbService.clearCaches(REPMOD_NO);
		return mapping.findForward(SUCCESS);
	}

	// for 数据下发生成SQL
	public ActionForward scSQL(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Map params = new HashMap();
		params.clear();
		params
				.put(
						"SerSQL",
						" select OLDTONEWID, OLDTAB, OLDCOL, OLDTYPE, OLDCOLNAME, "
								+ "SYSNAME, NEWTABLE, NEWCOL, NEWCOLNAME, NEWTYPE"
								+ " from FINPRO.DAT_OLDTONEW order by SYSNAME,OLDTAB,XH ASC ");
		List SQLList = lscxMbService.findSQLList(params);
		String sel = "";
		String tab = "";
		String nextab = "";
		String newtab = "";

		String newtab_1 = "";
		//String newtab_2 = "";

		for (int i = 0; i < SQLList.size(); i++) {
			Map temp = (Map) SQLList.get(i);
			nextab = ((String) temp.get("OLDTAB")).trim();
			newtab_1 = ((String) temp.get("NEWTABLE")).trim();
			if (i == 0) {
				newtab = ((String) temp.get("NEWTABLE")).trim();
			}

			if (!tab.equals("") && !tab.equals(nextab)) {
				System.err.println(" export to c:\\" + tab
						+ ".txt of del select " + sel + " from " + newtab);
				sel = "";
				newtab = ((String) temp.get("NEWTABLE")).trim();
			} else {

				if (i != 0) {
					if (newtab.equals(newtab_1)
							|| newtab.indexOf(newtab_1) != -1) {

					} else {
						newtab = newtab + " left join "
								+ ((String) temp.get("NEWTABLE")).trim();
					}
				}
			}

			if (sel.equals("")) {
				if (temp.get("NEWCOL") == null
						|| ((String) temp.get("NEWCOL")).equals("")
						|| ((String) temp.get("NEWCOL")).trim().equals("空")
						|| ((String) temp.get("NEWCOL")).trim().equals("无")) {
					sel = "''";
				} else {
					sel = ((String) temp.get("NEWCOL")).trim();
				}
			} else {
				if (temp.get("NEWCOL") == null
						|| ((String) temp.get("NEWCOL")).equals("")
						|| ((String) temp.get("NEWCOL")).trim().equals("空")
						|| ((String) temp.get("NEWCOL")).trim().equals("无")) {
					sel = sel + "," + "''";
				} else {
					sel = sel + "," + ((String) temp.get("NEWCOL")).trim();
				}

				if (i == SQLList.size() - 1) {
					System.err.println(" export to c:\\" + nextab
							+ ".txt of del select " + sel + " from " + newtab);
				}
			}
			tab = ((String) temp.get("OLDTAB")).trim();

		}
		request.setAttribute("msg", "生成成功！");
		return mapping.findForward(SUCCESS);
	}

	public void setlscxMbService(LscxMbService lscxMbService) {
		this.lscxMbService = lscxMbService;
	}

	// 下拉框
	public String getOptions() {
		StringBuffer sel_options = new StringBuffer();
		try {
			String[] options = new String[] { "", "冻结", "启用" }; // 下拉框要传的值！
			String[] values = new String[] { "", "冻结", "启用" }; // 下拉框要显示的值！

			for (int i = 0; i < options.length; i++) {
				String option = options[i];
				sel_options.append("<option value=\"").append(option).append(
						"\">");
				sel_options.append(values[i]);
				sel_options.append("</option>");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sel_options.toString();
	}

	public ActionForward toFlag(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		/*String REPMOD_NO = ParamUtil.get(request, "REPMOD_NO", "");
		String REP_NO = ParamUtil.get(request, "REP_NO", "");*/
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		ParamUtil.putStr2Map(request, "REP_NO", params);
		// params.put("STATE", "启用");
		lscxMbService.updateByFlag(params);
		request.setAttribute("msg", "状态修改成功！");
		return mapping.findForward(SUCCESS);

	}

}

package com.yitong.app.action.judicial.batchset;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yitong.app.service.judicial.batchset.BatchSetService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.Consts;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.model.inner.ListPage;
import com.yitong.commons.util.ParamUtil;

//批量设置Action
public class BatchSetAction extends BaseAction {

	protected static final Logger logger = Logger
			.getLogger(BatchSetAction.class);

	private BatchSetService batchSetService;

	public void setBatchSetService(BatchSetService batchSetService) {
		this.batchSetService = batchSetService;
	}

	public ActionForward toQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception  {
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
	@SuppressWarnings("unchecked")
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map params = new HashMap();

		String isValid = ParamUtil.get(request, "isValid");
		String batchTypeName = ParamUtil.get(request, "batchTypeName");
		params.put("isValid", isValid);
		params.put("batchType", batchTypeName);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);

		IListPage page = batchSetService.pageQuery(params, pageNo);

		//将BATCH_IMPORT_CONFIG表中的数字证件类型转化为SYS_VAR表中对应的中文
		
		//从SYS_VAR表中获取证件类型
		List<Map> importNoTypes = batchSetService.getImportNoTypes();
		List<Map> objList = (List) page.getResult();
		if (objList != null && objList.size() > 0) {
			for (int i = 0; i < objList.size(); i++) {
				
				//从CONFIG表中获取证件类型
				String QUERY_NO_TYPE = objList.get(i).get("QUERY_NO_TYPE").toString();
				String str[] = QUERY_NO_TYPE.split(",");
				
				//比较CONFIG表中的分隔后的证件类型和SYS_VAR表中的VALUE是否相等，如果相等，则将其转化为对应的中文
				for (int j = 0; j < str.length; j++) {
					for (int m = 0; m < importNoTypes.size(); m++) {
						String value = (String)importNoTypes.get(m).get("VALUE");
						if (str[j].equals(value)) {
							str[j] = (String)importNoTypes.get(m).get("VAR_NAME");
						}
					}
				}
				//将内容为中文的数组转化为字符串
				String varTypeName = "";
				StringBuffer sb =new StringBuffer();
				for (int j = 0; j < str.length; j++) {
					sb.append(str[j]).append(",");
					varTypeName = sb.toString();
					varTypeName = varTypeName.substring(0, varTypeName.length()-1);
				}
				logger.info("list("+i+")的数据的证件类型-----------："+varTypeName);
				objList.get(i).put("VarTypeName", varTypeName);
			}
		}

		page = new ListPage(page.getStart(), page.getTotalCount(),page.getPageSize(), objList);

		// 从BATCH_IMPORT_CONFIG参数表中获取所有的交易类型
		List batchTypes = batchSetService.getBatchTypesFromConfig();
		int size = batchTypes.size();
		// 查询时用到的交易的类型
		List batchTypes2 = batchSetService.getBatchTypesFromVAR();
		request.setAttribute("size", size);
		request.setAttribute("batchTypes", batchTypes);
		request.setAttribute("batchTypes2", batchTypes2);
		request.setAttribute("page", page);
		request.setAttribute("IS_VALID", this.getOptions());// 状态下拉框
		return mapping.findForward("list");
	}

	@SuppressWarnings("unchecked")
	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 从参数表中SYS_VAR获取证件类型
		List importNoTypes = batchSetService.getImportNoTypes();
		int size = importNoTypes.size();
		logger.info("证件类型个数：" + size);
		// 从参数表SYS_VAR中获取交易类型
		List batchTypes = batchSetService.getBatchTypesFromVAR();
		request.setAttribute("importNoTypes", importNoTypes);
		request.setAttribute("size", size);
		request.setAttribute("batchTypes", batchTypes);
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
	@SuppressWarnings("unchecked")
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String number = batchSetService.getBatchId();
		String tradeString = ParamUtil.get(request, "batchType");
		int i = tradeString.indexOf("|");
		String batchType = tradeString.substring(0, i);
		String batchTypeName = tradeString.substring(i + 1);

		Map params = new HashMap();
		params.put("number", number);
		params.put("batchType", batchType);
		params.put("batchTypeName", batchTypeName);
		
		ParamUtil.putStr2Map(request, "isValid", params);
		ParamUtil.putStr2Map(request, "needQueryDate", params);
		ParamUtil.putStr2Map(request, "queryNoType", params);
		ParamUtil.putStr2Map(request, "exportColumn", params);
		ParamUtil.putStr2Map(request, "exportName", params);
		ParamUtil.putStr2Map(request, "querySQL", params);

		Map entry = batchSetService.load(number);
		if (entry != null) {
			request.setAttribute("msg", "批量编号已存在，不能重复增加！");
			return mapping.findForward(FAILURE);
		}

		// 若已存在了相同的交易类型和启用状态，无法添加，提示添加失败
		Map params2 = new HashMap();
		params2.put("batchType", batchType);
		params2.put("isValid", "Y");

		Map map = batchSetService.loadByType(params2);
		if (map != null) {
			request.setAttribute("msg", "一种交易类型只能对应一种有效状态，添加失败！");
			return mapping.findForward(FAILURE);
		}

		batchSetService.insert(params);
		request.setAttribute("msg", "增加成功！");
		return mapping.findForward(Consts.SUCCESS);
	}

	@SuppressWarnings("unchecked")
	public ActionForward toModi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String number = ParamUtil.get(request, "number");
		if ("".equals(number) || number == null) {
			request.setAttribute("msg", "批量编号不存在！");
			return mapping.findForward(Consts.FAILURE);
		}
		List batchTypes = batchSetService.getBatchTypesFromVAR();
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "number", params);
		Map entry = batchSetService.load(number);

		String queryNoType = entry.get("QUERY_NO_TYPE").toString();// 获取查询证件类型

		request.setAttribute("rst", entry);

		List importNoTypes = batchSetService.getImportNoTypes();
		int size = importNoTypes.size();
		request.setAttribute("size", size);
		request.setAttribute("chnQueryNoType", queryNoType);
		request.setAttribute("importNoTypes", importNoTypes);
		request.setAttribute("batchTypes", batchTypes);

		return mapping.findForward("modi");
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
	// 批量设置修改页面处理结果
	@SuppressWarnings("unchecked")
	public ActionForward modi(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String number = ParamUtil.get(request, "number");

		String batchType = ParamUtil.get(request, "batchType");
		// 从SYS_VAR表中根据交易类型获取交易类型名
		Map map = batchSetService.loadByBatchTypeFromVAR(batchType);
		String batchTypeName = (String) map.get("VAR_NAME");

		Map params = new HashMap();
		params.put("number", number);
		params.put("batchTypeName", batchTypeName);
		ParamUtil.putStr2Map(request, "needQueryDate", params);
		ParamUtil.putStr2Map(request, "queryNoType", params);
		ParamUtil.putStr2Map(request, "exportColumn", params);
		ParamUtil.putStr2Map(request, "exportName", params);
		ParamUtil.putStr2Map(request, "querySQL", params);

		batchSetService.updateById(params);
		request.setAttribute("msg", "修改成功！");
		batchSetService.clearCaches(number);

		return mapping.findForward(Consts.SUCCESS);
	}

	/**
	 * 查看详细信息
	 * 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String number = ParamUtil.get(request, "number");
		String noType = ParamUtil.get(request, "noType");
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "number", params);
		Map entry = batchSetService.load(number);

		request.setAttribute("rst", entry);
		request.setAttribute("noType", noType);

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
	@SuppressWarnings("unchecked")
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String number = ParamUtil.get(request, "number");
		if ("".equals(number) || number == null) {
			request.setAttribute("msg", "批量编号不存在！");
			return mapping.findForward(Consts.FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "number", params);
		batchSetService.delete(params);
		request.setAttribute("msg", "删除成功！");
		batchSetService.clearCaches(number);

		return mapping.findForward(Consts.SUCCESS);
	}

	@SuppressWarnings("unchecked")
	public ActionForward dodj(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String number = ParamUtil.get(request, "number");
		if (number.equals("") || number == null) {
			request.setAttribute("msg", "请选择要停用的批量设置编号！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		params.put("isValid", "N");
		params.put("number", number);
		batchSetService.updateById(params);
		request.setAttribute("msg", "停用成功！");
		batchSetService.clearCaches(number);
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
	@SuppressWarnings("unchecked")
	public ActionForward doqy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String number = ParamUtil.get(request, "number");
		if (number.equals("") || number == null) {
			request.setAttribute("msg", "请选择要启用的批量设置编号！");
			return mapping.findForward(FAILURE);
		}

		// 根据批量交易编号查询批量交易 并获取其交易类型
		Map result = batchSetService.load(number);
		String batchType = (String) result.get("BATCH_TYPE");

		Map params = new HashMap();
		params.put("batchType", batchType);
		params.put("isValid", "Y");
		// 用交易类型和状态（启用）查询批量交易，若存在，则返回，若不存在，继续操作
		Map map = batchSetService.loadByType(params);

		if (map != null) {
			request.setAttribute("msg", "一种交易类型只能对应一种启用状态，启用失败！");
			return mapping.findForward(FAILURE);
		}

		params = new HashMap();
		params.put("isValid", "Y");
		params.put("number", number);
		batchSetService.updateById(params);
		request.setAttribute("msg", "启用成功！");
		batchSetService.clearCaches(number);
		return mapping.findForward(SUCCESS);
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
				logger.info("下拉框............" + sel_options.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sel_options.toString();
	}
}

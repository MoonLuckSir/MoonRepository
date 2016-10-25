package com.yitong.app.action.judicial.gatsfck;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oreilly.servlet.ServletUtils;
import com.yitong.app.service.judicial.gatsfck.GatsfckService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.util.ParamUtil;

/**
 * 批量查询相关
 * 
 * @author db2admin
 * 
 */
public class GatckAction extends BaseAction {
	protected static final Logger logger = Logger.getLogger(GatckAction.class);

	private GatsfckService gatsfckService;


	public GatsfckService getGatsfckService() {
		return gatsfckService;
	}


	public void setGatsfckService(GatsfckService gatsfckService) {
		this.gatsfckService = gatsfckService;
	}


	/**
	 * 司法查控：公安厅查询任务信息列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings({"rawtypes"})
	public ActionForward toGatTaskList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "TASK_STU", params);
		ParamUtil.putStr2Map(request, "ksr", params);
		ParamUtil.putStr2Map(request, "jsr", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = gatsfckService.pageQueryGatTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("gattask");
	}
	
	
	/**
	 * 司法查控：查看任务下条件信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toViewTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "taskId", params);
		ParamUtil.putStr2Map(request, "BDHM", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = gatsfckService.pageQueryFyCond(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("fycond");
	}
	
	
	/**
	 * 司法查控：查看任务下条件信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toViewZgfyTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "taskId", params);
		ParamUtil.putStr2Map(request, "BDHM", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = gatsfckService.pageQueryZgfyCond(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("zgfycond");
	}
	
	
	/**
	 * 司法查控：跳转到出错信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toErrorInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		//String auditInfo = URLDecoder.decode(ParamUtil.get(request, "auditInfo"), "utf-8") ;
		//System.out.println(auditInfo);
		System.out.println(ParamUtil.get(request, "auditInfo"));
		return mapping.findForward("errorInfo");
	}
	
	
	/**
	 * 司法查控：跳转到最高法院出错信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toZGFYErrorInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		//String auditInfo = URLDecoder.decode(ParamUtil.get(request, "auditInfo"), "utf-8") ;
		//System.out.println(auditInfo);
		System.out.println(ParamUtil.get(request, "auditInfo"));
		return mapping.findForward("zgfyErrorInfo");
	}
	
	
	/**
	 * 提交审核：下载文件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward downLoadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		String savePath = request.getParameter("savePath");
		
		File file = new File(savePath);
		if(!file.exists()){
			request.setAttribute("msg", "无法下载,"+savePath+"路径下文件未找到!");
			return mapping.findForward(FAILURE);
		}
		String[] filenames = savePath.split("/");
		System.out.println(filenames[filenames.length-1]);
		try {
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(filenames[filenames.length-1], "UTF-8"));
			ServletOutputStream out = null;
			out = response.getOutputStream();
			ServletUtils.returnFile(savePath, out);// 下载文件
			out.close();

		} catch (UnsupportedEncodingException ex) {// iso8559_1编码异常
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {

		}
		return null;
	}
	
	
}

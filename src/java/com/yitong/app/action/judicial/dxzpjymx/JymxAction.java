package com.yitong.app.action.judicial.dxzpjymx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oreilly.servlet.ServletUtils;
import com.yitong.app.service.judicial.dxzpjymx.JymxService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.model.Properties;
import com.yitong.commons.util.ParamUtil;

public class JymxAction extends BaseAction {
	
	protected static final Logger logger = Logger.getLogger(JymxAction.class);
	private static String savePath = Properties.getString("savePath");// 上传路径
	private JymxService jymxService;
	
	public JymxService getJymxService() {
		return jymxService;
	}

	public void setJymxService(JymxService jymxService) {
		this.jymxService = jymxService;
	}

	/**
	 * 司法查询：账户交易明细查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toDxTaskList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		ParamUtil.putStr2Map(request, "STATUS", params);
		ParamUtil.putStr2Map(request, "ksr", params);
		ParamUtil.putStr2Map(request, "jsr", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = jymxService.pageQueryJyTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}
	
	
	/**
	 * 批量下载交易明细信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward batchDownloadJymxInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String curDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		Map params = new HashMap();
		String checkAppIds = ParamUtil.get(request, "checkAppIds");
		String[] appIdsArr = checkAppIds.split(",");
		StringBuffer sb = new StringBuffer();
		
		for(String tmp : appIdsArr){
			sb.append("'");
			sb.append(tmp);
			sb.append("'");
			sb.append(",");
		}
		String paramAppID = sb.toString();
		params.put("appIds", paramAppID.substring(0,paramAppID.length()-1));
		
		List<Map> zhjyqqList = jymxService.findZhjyqqList(params);
		List<Map> zhxxList = jymxService.findZhxxList(params);
		List<Map> jymxList = jymxService.findJymxList(params);
		
		
		try{
		   //生成交易明细请求文件
		   File jymxqqFile = jymxService.makeZhjyqqExcel(zhjyqqList, savePath+curDate+"_jymxqq.xls");
		   
		   //生成账户基本信息查询结果和交易明细查询结果
		   File zhjyjgFile = jymxService.makeZhjyjgExcel(zhxxList, jymxList, savePath+curDate+"_jymxjg.xls");
	       
		   //将要打包的文件放入List
	       List<File> fileList = new ArrayList<File>();
	       
	       //将交易明细查询放入List
	        if(jymxqqFile.exists()){
	        	fileList.add(jymxqqFile);
	        }
	        
	      //将账户交易明细查询结果放入List
	        if(zhjyjgFile.exists()){
	        	fileList.add(zhjyjgFile);
	        }
	       
	       //将所有选中的法律文书文件 放入List
	       for(Map m :zhjyqqList){
	    	   File attcFile = new File((String) m.get("ATTACHMENTS"));
	    	   if(attcFile.exists()){
	    		   fileList.add(attcFile);
	    	   }
	       }
	       	
	        
			File zipFileFile  = jymxService.compressFileListToZip(fileList, savePath+curDate+"_jymx_total.zip");
			if(!zipFileFile.exists()){
				request.setAttribute("msg", "无法下载,"+zipFileFile.getAbsolutePath()
						+"路径下文件未找到!");
				return mapping.findForward(FAILURE);
			}
			
			try {
				response.reset();
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(zipFileFile.getName(), "UTF-8"));
				ServletOutputStream out = null;
				out = response.getOutputStream();
				ServletUtils.returnFile(zipFileFile.getAbsolutePath(), out);// 下载文件
				out.close();

			} catch (UnsupportedEncodingException ex) {// iso8559_1编码异常
				ex.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	          
		return null;
				
	}
	
	
	/**
	 * 批量下载持卡主体细信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward batchDownloadCkztInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String curDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		Map params = new HashMap();
		String checkAppIds = ParamUtil.get(request, "checkAppIds");
		String[] appIdsArr = checkAppIds.split(",");
		StringBuffer sb = new StringBuffer();
		
		for(String tmp : appIdsArr){
			sb.append("'");
			sb.append(tmp);
			sb.append("'");
			sb.append(",");
		}
		String paramAppID = sb.toString();
		params.put("appIds", paramAppID.substring(0,paramAppID.length()-1));
		
		List<Map> ckztqqList = jymxService.findCkztqqList(params);
		List<Map> ckztjgList = jymxService.findCkztjgList(params);
		
		
		try{
		   //生成持卡主体查询请求文件
		   File ckztqqFile = jymxService.makeCkztqqExcel(ckztqqList, savePath+curDate+"_ckztqq.xls");
		 
		   //生成持卡主体查询结果文件
		   File ckztjgFile = jymxService.makeCkztjgExcel(ckztjgList, savePath+curDate+"_ckztjg.xls");
	       
		   //将要打包的文件放入List
	       List<File> fileList = new ArrayList<File>();
	       
	       //将持卡主体查询请求文件放入List
	        if(ckztqqFile.exists()){
	        	fileList.add(ckztqqFile);
	        }
	        
	      //将持卡主体查询结果放入List
	        if(ckztjgFile.exists()){
	        	fileList.add(ckztjgFile);
	        }
	       
	       //将所有选中的法律文书文件 放入List
	       for(Map m :ckztqqList){
	    	   File attcFile = new File((String) m.get("ATTACHMENTS"));
	    	   if(attcFile.exists()){
	    		   fileList.add(attcFile);
	    	   }
	       }
	       	
	       
			File zipFileFile  = jymxService.compressFileListToZip(fileList,  savePath+curDate+"_ckzt_total.zip");
			if(!zipFileFile.exists()){
				request.setAttribute("msg", "无法下载,"+zipFileFile.getAbsolutePath()+"路径下文件未找到!");
				return mapping.findForward(FAILURE);
			}
			
			try {
				response.reset();
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(zipFileFile.getName(), "UTF-8"));
				ServletOutputStream out = null;
				out = response.getOutputStream();
				ServletUtils.returnFile(zipFileFile.getAbsolutePath(), out);// 下载文件
				out.close();

			} catch (UnsupportedEncodingException ex) {// iso8559_1编码异常
				ex.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	          
		return null;
				
	}
	
	/**
	 * 批量下载客户全账户信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward batchDownloadQzhInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String curDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		Map params = new HashMap();
		String checkAppIds = ParamUtil.get(request, "checkAppIds");
		String[] appIdsArr = checkAppIds.split(",");
		StringBuffer sb = new StringBuffer();
		
		for(String tmp : appIdsArr){
			sb.append("'");
			sb.append(tmp);
			sb.append("'");
			sb.append(",");
		}
		String paramAppID = sb.toString();
		params.put("appIds", paramAppID.substring(0,paramAppID.length()-1));
		
		List<Map> qzhqqList = jymxService.findQzhqqList(params);
		List<Map> qzhzhList = jymxService.findQzhzhxxList(params);
		List<Map> qzhdjList = jymxService.findQzhdjxxList(params);
		
		try{
		   //生成全账户查询请求文件
		   File qzhqqFile = jymxService.makeQzhqqExcel(qzhqqList, savePath+curDate+"_qzhqq.xls");
		 
		   //生成全账户账号及冻结信息结果文件
		   File qzhjgFile = jymxService.makeQzhjgExcel(qzhzhList, qzhdjList, savePath+curDate+"_qzhjg.xls");
	       
		   //将要打包的文件放入List
	       List<File> fileList = new ArrayList<File>();
	       
	       //将持全账户查询请求文件放入List
	        if(qzhqqFile.exists()){
	        	fileList.add(qzhqqFile);
	        }
	        
	      //将全账户账号及冻结信息结果文件放入List
	        if(qzhjgFile.exists()){
	        	fileList.add(qzhjgFile);
	        }
	       
	       //将所有选中的法律文书文件 放入List
	       for(Map m :qzhqqList){
	    	   File attcFile = new File((String) m.get("ATTACHMENTS"));
	    	   if(attcFile.exists()){
	    		   fileList.add(attcFile);
	    	   }
	       }
	       	
	       
			File zipFileFile  = jymxService.compressFileListToZip(fileList,  savePath+curDate+"_qzh_total.zip");
			if(!zipFileFile.exists()){
				request.setAttribute("msg", "无法下载,"+zipFileFile.getAbsolutePath()+"路径下文件未找到!");
				return mapping.findForward(FAILURE);
			}
			
			try {
				response.reset();
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(zipFileFile.getName(), "UTF-8"));
				ServletOutputStream out = null;
				out = response.getOutputStream();
				ServletUtils.returnFile(zipFileFile.getAbsolutePath(), out);// 下载文件
				out.close();

			} catch (UnsupportedEncodingException ex) {// iso8559_1编码异常
				ex.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {

			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	          
		return null;
				
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward toView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String APPLICATIONID = ParamUtil.get(request, "APPLICATIONID");
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		Map entry = jymxService.load(APPLICATIONID);

		request.setAttribute("rst", entry);
		return mapping.findForward("view");
	}
	
	/**
	 * 司法查询：账户持卡主体查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toCkTaskList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		ParamUtil.putStr2Map(request, "STATUS", params);
		ParamUtil.putStr2Map(request, "ksr", params);
		ParamUtil.putStr2Map(request, "jsr", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = jymxService.pageQueryCkTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("ckztlist");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward toCkView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String APPLICATIONID = ParamUtil.get(request, "APPLICATIONID");
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		Map entry = jymxService.ckload(APPLICATIONID);

		request.setAttribute("rst", entry);
		return mapping.findForward("ckztview");
	}
	
	/**
	 * 司法查询：客户全账户查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toQzhTaskList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		ParamUtil.putStr2Map(request, "STATUS", params);
		ParamUtil.putStr2Map(request, "ksr", params);
		ParamUtil.putStr2Map(request, "jsr", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = jymxService.pageQueryQzhTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("qzhcxlist");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward toQzhView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String APPLICATIONID = ParamUtil.get(request, "APPLICATIONID");
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		Map entry = jymxService.qzhload(APPLICATIONID);

		request.setAttribute("rst", entry);
		return mapping.findForward("qzhcxview");
	}
	

	/**
	 * 司法查询：客户全账户查询反馈报文
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toFkTaskList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = jymxService.pageQueryFkTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("fklist");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward toFkView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String APPLICATIONID = ParamUtil.get(request, "APPLICATIONID");
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		Map entry = jymxService.fkload(APPLICATIONID);

		request.setAttribute("rst", entry);
		return mapping.findForward("fkview");
	}
	
	/**
	 * 司法查询：账户查询明细反馈
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toCxmxTaskList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		ParamUtil.putStr2Map(request, "CARDNUMBER", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = jymxService.pageQueryCxmxTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("cxmxlist");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward toCxmxView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		ParamUtil.putStr2Map(request, "TRANSACTIONTIME", params);
		ParamUtil.putStr2Map(request, "TRANSACTIONSERIAL", params);
	
		Map entry = jymxService.cxmxload(params);
		request.setAttribute("rst", entry);
		return mapping.findForward("cxmxview");
	}
	
	/**
	 * 司法查询：客户全账户查询反馈报文
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toCkfkTaskList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = jymxService.pageQueryCkfkTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("ckfklist");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward toCkfkView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String APPLICATIONID = ParamUtil.get(request, "APPLICATIONID");
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		Map entry = jymxService.ckfkload(APPLICATIONID);

		request.setAttribute("rst", entry);
		return mapping.findForward("ckfkview");
	}
	
	/**
	 * 司法查询：查询账户信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toZhTaskList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = jymxService.pageQueryZhTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("zhlist");
	}
	
	@SuppressWarnings("unchecked")
	public ActionForward toZhView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		ParamUtil.putStr2Map(request, "CARDNUMBER", params);
		ParamUtil.putStr2Map(request, "ACCOUNTSERIAL", params);
		Map entry = jymxService.zhload(params);
		request.setAttribute("rst", entry);
		return mapping.findForward("zhview");
	}
	
	/**
	 * 司法查询：查询冻结信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toDjView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = jymxService.pageQueryDjTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("djview");
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
	
	
	/**
	 * 司法查询：跳转到出错信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public ActionForward toErrorInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		System.out.println(ParamUtil.get(request, "errorInfo"));
		String errorInfo = URLDecoder.decode(ParamUtil.get(request, "errorInfo"), "utf-8") ;
		request.setAttribute("errorInfo", errorInfo);
		return mapping.findForward("errorInfo");
	}
	
}

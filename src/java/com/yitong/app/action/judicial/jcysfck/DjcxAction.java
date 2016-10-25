package com.yitong.app.action.judicial.jcysfck;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.oreilly.servlet.ServletUtils;
import com.yitong.app.action.judicial.plcx.BatchQueryAction;
import com.yitong.app.action.judicial.plcx.UploadForm;
import com.yitong.app.service.judicial.jcysfck.DjcxService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.model.Properties;
import com.yitong.commons.model.UserSession;
import com.yitong.commons.model.judicial.sfck.CxqqInfo_JCY;
import com.yitong.commons.util.ParamUtil;
import com.yitong.commons.util.XmlUtil;

/**
 * 批量查询相关
 * 
 * @author db2admin
 * 
 */
public class DjcxAction extends BaseAction {
	protected static final Logger logger = Logger.getLogger(DjcxAction.class);

	private DjcxService djcxService;
	private static String sfckUploadPath = Properties.getString("sfckUploadPath");// 上传路径
	private static String sfckDownloadPath = Properties.getString("sfckDownloadPath");// 生成路径
	private static String serverUrl = Properties.getString("serverUrl");//服务地址
	

	public DjcxService getDjcxService() {
		return djcxService;
	}



	public void setDjcxService(DjcxService djcxService) {
		this.djcxService = djcxService;
	}



	/**
	 * 司法查控：查询任务列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toTaskList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "TASK_NAME", params);
		ParamUtil.putStr2Map(request, "TASK_STU", params);
		ParamUtil.putStr2Map(request, "ksr", params);
		ParamUtil.putStr2Map(request, "jsr", params);
		UserSession userSession =  getUserSession(request);
		params.put("REG_USER", userSession.getUserId());
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = djcxService.pageQueryTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("task");
	}
	
	/**
	 * 司法查控：审核任务列表列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toShtjList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "TASK_NAME", params);
		ParamUtil.putStr2Map(request, "TASK_STU", params);
		ParamUtil.putStr2Map(request, "ksr", params);
		ParamUtil.putStr2Map(request, "jsr", params);
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = djcxService.pageQueryAudit(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("auditList");
	}
	
	/**
	 * 提交审核：下载导入文件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward downLoadFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		String savePath = request.getParameter("savePath");
		if (null == savePath || "".equals(savePath)){
			savePath = sfckDownloadPath+"downloadExcel.xls";
		}
			
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
	 * 司法查控：跳转添加查询任务
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toAddTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		List<Map> cxmbList = djcxService.findListCxmb();
		request.setAttribute("cxmbList", cxmbList);
		return mapping.findForward("addTask");
	}
	
	/**
	 * 司法查控：删除查询任务 以及 删除查询条件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward delTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		
		ParamUtil.putStr2Map(request, "taskId", params);
		djcxService.delTask(params);
		request.setAttribute("msg", "任务删除成功!");
		return mapping.findForward(SUCCESS);
	}
	
	/**
	 * 司法查控：跳转到审核不通过原因界面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toAuditInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		//String auditInfo = URLDecoder.decode(ParamUtil.get(request, "auditInfo"), "utf-8") ;
		//System.out.println(auditInfo);
		System.out.println(ParamUtil.get(request, "auditInfo"));
		return mapping.findForward("auditInfo");
	}
	
	/**
	 * 司法查控：跳转到生成失败原因界面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toMakeInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		 
		System.out.println(ParamUtil.get(request, "makeInfo"));
		return mapping.findForward("xzjgInfo");
	}
	
	
	
	/**
	 * 司法查控：提交任务审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward updateTaskStu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		String taskStu = ParamUtil.get(request, "taskStu");
		String reset = ParamUtil.get(request, "reset");
		String taskIds = ParamUtil.get(request, "taskIds");
		ParamUtil.putStr2Map(request, "taskIds", params);
		ParamUtil.putStr2Map(request, "taskStu", params);
		ParamUtil.putStr2Map(request, "audit_info", params);
		UserSession userSession =  getUserSession(request);
		params.put("AUDIT_USER", userSession.getUserId());
		
		String[] taskIdArr = taskIds.split(",");
		
		if("2".contains(taskStu)){
			for(String TASK_ID : taskIdArr){
				params.put("TASK_ID", TASK_ID);
				djcxService.updateTaskStu(params);
			}
			request.setAttribute("msg", "任务提交审核成功!");
		}else if("4".contains(taskStu)){
			
			
			List<String> errList = new ArrayList<String>();
			for(String TASK_ID : taskIdArr){
				HttpClient client = new HttpClient();
				PostMethod post = new PostMethod(serverUrl);
				//PostMethod post = new PostMethod("http://localhost:8080/Judicial/judicial/interface.shtml?action=returnStr");
				
				String jsonStr = djcxService.makeParams(TASK_ID);
				post.addParameter("aaa",jsonStr);
				post.addParameter("taskId",TASK_ID);
				post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
				try {
					int result =  client.executeMethod(post);
					System.out.println("=============="+result);
					if(result == 200){
						params.put("makeSdate", "1");
						params.put("TASK_ID", TASK_ID);
						djcxService.updateTaskStu(params);
					}else {
						errList.add(TASK_ID);
					}
				} catch (HttpException e) {
					request.setAttribute("msg", "任务审核异常,"+e.getMessage());
					// TODO Auto-generated catch block
					e.printStackTrace();
					return mapping.findForward(FAILURE);
				} catch (IOException e) {
					request.setAttribute("msg", "任务审核异常,"
							+e.getMessage());
					// TODO Auto-generated catch block
					e.printStackTrace();
					return mapping.findForward(FAILURE);
				}
			}
			if(errList.size() != 0){
				request.setAttribute("msg", "任务审核失败,编号为"+errList.toString());
				return mapping.findForward(FAILURE);
			}else{
				request.setAttribute("msg", "任务审核通过!");
			}
		}else if("3".contains(taskStu)){
			for(String TASK_ID : taskIdArr){
				params.put("TASK_ID", TASK_ID);
				djcxService.updateTaskStu(params);
			}
			request.setAttribute("msg", "任务审核不通过!");
		}else if("1".contains(taskStu)){
			for(String TASK_ID : taskIdArr){
				params.put("TASK_ID", TASK_ID);
				djcxService.updateTaskStu(params);
			}
			request.setAttribute("msg", "任务重置成功!");
		}
		return mapping.findForward(SUCCESS);
	}
	
	
	
	/**
	 * 司法查控：下载查询结果列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toXzjgList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "TASK_NAME", params);
		ParamUtil.putStr2Map(request, "TASK_STU", params);
		UserSession userSession =  getUserSession(request);
		params.put("REG_USER", userSession.getUserId());
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = djcxService.pageQueryCxjg(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("xzjgList");
	}
	
	/**
	 * 司法查控：查看任务信息列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toTaskInfoList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "TASK_NAME", params);
		ParamUtil.putStr2Map(request, "TASK_STU", params);
		ParamUtil.putStr2Map(request, "ksr", params);
		ParamUtil.putStr2Map(request, "jsr", params);
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = djcxService.pageQueryTaskInfo(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("taskInfoList");
	}
	
	
	/**
	 * 司法查控：添加查询任务
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward addTaskByExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();		
		ParamUtil.putStr2Map(request, "TASK_NAME", params);
		ParamUtil.putStr2Map(request, "QRY_DEPT", params);
		ParamUtil.putStr2Map(request, "CXMB_ID", params);
		ParamUtil.putStr2Map(request, "EXP_FORMAT", params);
		ParamUtil.putStr2Map(request, "REG_WAY", params);
		
		UserSession sess = getUserSession(request);
		String user = sess.getUserId();
		params.put("REG_USER", user);
		
		String QRY_DEPT = (String) params.get("QRY_DEPT");
		String taskId = QRY_DEPT+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		params.put("TASK_ID", taskId);
		
		UploadForm uf = (UploadForm) form;
		FormFile file = uf.getFile();
		String fileName = file.getFileName();
		if (!fileName.endsWith("xls")) {
			request.setAttribute("msg", "导入模板格式不正确，只接受xls格式文件");
			return mapping.findForward(FAILURE);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String saveName = taskId + "_" + sdf.format(new Date());
		
		
		String savePath = sfckUploadPath + saveName + ".xls" ;
		params.put("IMP_FILENAME", savePath);
		logger.info("+++++++++++++++++++++++++++++++++++++++上传路径" + sfckUploadPath + saveName);
		try {

			FileOutputStream fos = new FileOutputStream(savePath);
			fos.write(file.getFileData());// 写入
			fos.flush();// 释放
			fos.close();// 关闭
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}// 创建输出流 填写输出路径
		catch (IOException e) {
			e.printStackTrace();
		}

		String errorMsg = this.checkExcel(savePath, taskId);

		if (errorMsg != null) {
			request.setAttribute("msg", "批量导入查询条件失败!" + errorMsg);
			return mapping.findForward(FAILURE);
		}
		
		djcxService.addTask(params);
		request.setAttribute("msg", "登记查询条件成功,请提交审核!");
		return mapping.findForward(SUCCESS);
		
	}
	
	
	public ActionForward addTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();		
		ParamUtil.putStr2Map(request, "TASK_NAME", params);
		ParamUtil.putStr2Map(request, "QRY_DEPT", params);
		ParamUtil.putStr2Map(request, "CXMB_ID", params);
		ParamUtil.putStr2Map(request, "EXP_FORMAT", params);
		ParamUtil.putStr2Map(request, "REG_WAY", params);
		//ParamUtil.putStr2Map(request, "QRY_DEPT", params);
		String QRY_DEPT = (String) params.get("QRY_DEPT");
		
		UserSession sess = getUserSession(request);
		String user = sess.getUserId();
		params.put("REG_USER", user);
		
		
		String taskId = QRY_DEPT+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		params.put("TASK_ID", taskId);
		
		
		
		String exp_format = params.get("EXP_FORMAT").toString();
		String errorMsg = "";
		String savePath = "";
		UploadForm uf = (UploadForm) form;
		
		//验证附加文件大小
		FormFile extraFile = uf.getExtraFile();
		//System.out.println();21167518
		int filesize = extraFile.getFileSize();
		if(filesize > 20000000){
			request.setAttribute("msg", "上传失败,上传附加文件不得大于20M!");
			return mapping.findForward(FAILURE);
		}
		String extraFileName = extraFile.getFileName();
		
		//得到上传文件
		FormFile file = uf.getFile();
		String fileName = file.getFileName();
		
		String saveName = taskId ;
		savePath = sfckUploadPath + saveName + ".xls" ;
		if(exp_format.equals("Excel")){
			if (!fileName.toLowerCase().endsWith("xls")) {
				request.setAttribute("msg", "导入模板格式不正确，只接受xls格式文件");
				return mapping.findForward(FAILURE);
			}
		}else{
			if (!fileName.toLowerCase().endsWith("xml")) {
				request.setAttribute("msg", "导入模板格式不正确，只接受xml格式文件");
				return mapping.findForward(FAILURE);
			}
		}
		
		params.put("IMP_FILENAME", savePath);
		
		
		logger.info("+++++++++++++++++++++++++++++++++++++++上传路径" + savePath);
		
		try {
			FileOutputStream fos = new FileOutputStream(savePath);
			fos.write(file.getFileData());// 写入
			fos.flush();// 释放
			fos.close();// 关闭
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}// 创建输出流 填写输出路径
		catch (IOException e) {
			e.printStackTrace();
		}

		if(exp_format.equals("Excel")){
			errorMsg = this.checkExcel(savePath, taskId);
		}else{
			errorMsg = this.checkXml(savePath, taskId);
		}
		
		if (errorMsg != null) {
			request.setAttribute("msg", "批量导入查询条件失败!" + errorMsg);
			return mapping.findForward(FAILURE);
		}
		
		
		
		if(!"".equals(extraFileName)){
			String extraFileSavePath =sfckUploadPath + taskId + "_extraFile." + BatchQueryAction.getExtensionName(extraFileName);
			params.put("EXTRA_FILENAME", extraFileSavePath);
			try {
				FileOutputStream fos = new FileOutputStream(extraFileSavePath);
				fos.write(extraFile.getFileData());
				fos.flush();// 释放
				fos.close();// 关闭
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}// 写入
			
			logger.info("+++++++++++++++++++++++++++++++++++++++附属文件上传路径" + extraFileSavePath);
		}
		
		djcxService.addTask(params);
		request.setAttribute("msg", "登记查询条件成功,请提交审核!");
		return mapping.findForward(SUCCESS);
		
	}
	
	@SuppressWarnings("unchecked")
	public String checkExcel(String savePath, String taskId) {

		try {
			List<Map> list = new ArrayList<Map>();
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(savePath));
			HSSFWorkbook wb = new HSSFWorkbook(fs);// 得到Excel工作簿对象
			HSSFSheet sheet = wb.getSheetAt(0); // 得到Excel工作表对象 第一个sheet
			
			int rowcount = sheet.getLastRowNum(); // 获得Excel表的行数
			logger.info("Excel最后一行的行数为：" + (rowcount+1));
			if (rowcount == 0) {
				return "导入的Excel表行数为0，Excel为空表！";
			}
			
			// if (rowcount > 400) {
			// return "批量查询一次不得超过400条！";
			// }
			HSSFRow row = sheet.getRow(0);// 得到Excel工作表的行 第一行 表头
			HSSFCell cell = null;
			String cellValue = "";
			int columnNum = row.getLastCellNum(); // 获得Excel表的列数
			logger.info("Excel最后一列的列数为：" + columnNum);

			if (columnNum != 15) {
				return "Excel表头列数不对，请检查模板！";
			}
			
			if (rowcount > 0) {
				cellValue = "";
				int cellLength = 0;
				for (int i = 1; i <= rowcount; i++) {
					logger.info("第" + i + "行......................");
					Map params = new HashMap();
					row = sheet.getRow(i);
					params.put("TASK_ID", taskId);
					for (int k = 0; k < columnNum; k++) {
						try {
							cell = row.getCell(k);
							if (cell != null) {
								cellValue = this.getCellStringValue(cell).trim();
								cellLength = this.getWordCount(cellValue);
							} else {
								cellValue = "";
							}
							if (k == 0) {// 校验查询请求单号
								
								if ("".equals(cellValue)) {
									return "表中第" + (i+1) + "行第" + (k + 1) + "列错误，查询请求单号不得为空！";
								}
								if(cellLength > 20){
									return "表中第" + (i+1) + "行第" + (k + 1) + "列错误，查询请求单号不得超过20个字符！";
								}
								params.put("BDHM", cellValue);
								if(djcxService.hasBDHM(params)){
									 return "表中第" + (i+1) + "行第" + (k + 1) + "列错误，该查询请求单号在表中已存在！";
								}
							}	
							if (k == 1) {// 类别
								/*if ("".equals(cellValue)) {
									return "表中第" + (i + 1) + "行第" + (k + 1) + "列错误，类别不得为空！";
								}*/
								if(cellLength > 2){
									return "表中第" + (i+1) + "行第" + (k + 1) + "列错误，类别不得超过2个字符！";
								}
								params.put("LB", cellValue);
							}
							if (k == 2) {// 性质:
								if ("".equals(cellValue)) {
									return "表中第" + (i + 1) + "行第" + (k + 1) + "列错误，性质不得为空！";
								}
								if(cellLength > 1){
									return "表中第" + (i+1) + "行第" + (k + 1) + "列错误，性质不得超过1个字符！";
								}
								/*if (!"1".equals(cellValue) && !"2".equals(cellValue) && !"3".equals(cellValue)) {
									return "表中第" + (i + 1) + "行第" + (k + 1) + "列错误，性质参数只能为1、2、3其中一个！";
								}*/
								params.put("XZ", cellValue);
							}
							if (k == 3) {// 被查询人姓名
								/*if ("".equals(cellValue)) {
									return "表中第" + (i + 1) + "行第" + (k + 1) + "列错误，被查询人姓名不得为空！";
								}*/
								if(cellLength > 100){
									return "表中第" + (i+1) + "行第" + (k + 1) + "列错误，被查询人姓名不得超过100个字符！";
								}
								params.put("XM", cellValue);
								System.out.println(cellValue.length());
							}
							
							if (k == 4) {// 国家或地区
								if(cellLength > 15){
									return "表中第" + (i+1) + "行第" + (k + 1) + "列错误，国家或地区不得超过15个字符！";
								}
								params.put("GJ", cellValue);
							}
							if (k == 5) {// 证件类型
								if ("".equals(cellValue)) {
									return "表中第" + (i + 1) + "行第" + (k + 1) + "列错误，证件类型不得为空！";
								}
								if(cellLength > 15){
									return "表中第" + (i+1) + "行第" + (k + 1) + "列错误，证件类型不得超过15个字符！";
								}
								params.put("ZJLX", cellValue);
							}
							if (k == 6) {// 被查询人证件/组织机构号码
								if ("".equals(cellValue)) {
									return "表中第" + (i + 1) + "行第" + (k + 1) + "列错误，被查询人证件/组织机构号码不得为空！";
								}
								if(cellLength > 30){
									return "表中第" + (i+1) + "行第" + (k + 1) + "列错误，被查询人证件/组织机构号码不得超过30个字符！";
								}
								params.put("ZJHM", cellValue);
							}
							if (k == 7) {// 发证机关所在地
								if(cellLength > 50){
									return "表中第" + (i+1) + "行第" + (k + 1) + "列错误，发证机关所在地不得超过50个字符！";
								}
								params.put("FZJG", cellValue);
							}
							if (k == 8) {// 申请机构名称
								if(cellLength > 50){
									return "表中第" + (i+1) + "行第" + (k + 1) + "列错误，申请机构名称不得超过50个字符！";
								}
								params.put("JGHM", cellValue);
							}
							if (k == 9) {// 承办检察官
								if(cellLength > 20){
									return "表中第" + (i+1) + "行第" + (k + 1) + "列错误，承办人不得超过20个字符！";
								}
								params.put("CBR", cellValue);
							}
							if (k == 10) {// 执行案号
								if(cellLength > 50){
									return "表中第" + (i+1) + "行第" + (k + 1) + "列错误，执行案号不得超过50个字符！";
								}
								params.put("AH", cellValue);
							}
							if (k == 11) {// 往来账查询开始时间
								
								if(!"1".equals((String)params.get("XZ"))){
									if ("".equals(cellValue)) {
										return "表中第" + (i + 1) + "行第" + (k + 1) + "列错误，往来账查询开始时间不得为空！";
									}else{
										cellValue = cellValue.replace("-", "/");
										try {
											DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
											format.parse(cellValue);
										} catch (ParseException e) {
											e.printStackTrace();
											logger.info("表中第" + (i + 1) + "行第" + (k + 1) + "列错误，往来账查询开始时间日期格式有误，格式必须为yyyy/MM/dd HH:mm:ss样式。");
											return "表中第" + (i + 1) + "行第" + (k + 1) + "列错误，往来账查询开始时间日期格式有误，格式必须为yyyy/MM/dd HH:mm:ss样式。";
										}
									}
								}
								
								if(cellLength > 19){
									return "表中第" + (i+1) + "行第" + (k + 1) + "列错误，往来账查询开始时间不得超过19个字符！";
								}
								params.put("CXKSSJ", cellValue);
							}
							
							if (k == 12) {// 往来账查询结束时间
								if(!"1".equals((String)params.get("XZ"))){
									if ("".equals(cellValue)) {
										return "表中第" + (i + 1) + "行第" + (k + 1) + "列错误，往来账查询结束时间不得为空！";
									}else{
										cellValue = cellValue.replace("-", "/");
										try {
											DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
											format.parse(cellValue);
										} catch (ParseException e) {
											e.printStackTrace();
											logger.info("表中第" + (i + 1) + "行第" + (k + 1) + "列错误，往来账查询结束时间日期格式有误，格式必须为yyyy/MM/dd HH:mm:ss样式。");
											return "表中第" + (i + 1) + "行第" + (k + 1) + "列错误，往来账查询结束时间日期格式有误，格式必须为yyyy/MM/dd HH:mm:ss样式。";
										}
									}
								}
								
								if(cellLength > 19){
									return "表中第" + (i+1) + "行第" + (k + 1) + "列错误，往来账查询结束时间不得超过19个字符！";
								}
								params.put("CXJSSJ", cellValue);
							}
							if (k == 13) {// 要求最晚反馈时间
								if(cellLength > 19){
									return "表中第" + (i+1) + "行第" + (k + 1) + "列错误，要求最晚反馈时间不得超过19个字符！";
								}
								params.put("ZWFKSJ", cellValue);
							}
							if (k == 14) {// 申请日期
								if(cellLength > 19){
									return "表中第" + (i+1) + "行第" + (k + 1) + "列错误，申请日期不得超过19个字符！";
								}
								params.put("SQSJ", cellValue);
							}
						} catch (Exception e) {
							e.printStackTrace();
							logger.info("导入Excel实际查询条件为" + rowcount + "行，请认真核对Excel表格的空白部分");
							return "导入Excel实际查询条件为" + rowcount + "行，请认真核对Excel表格的空白部分";
						}
					}
					
					list.add(params);
				}

				// 检查Excel表内有无证件种类和证件号码同时相等的情况，即相同的查询条件
				Map p = new HashMap();
				String BDHM = "";
				String BDHM2 = "";
				
				for (int a = 0; a < list.size(); a++) { // 遍历Excel表的每一行
					p = list.get(a);
					BDHM = (String) p.get("BDHM");
					
					logger.info("------------------" + BDHM);
					
					// 双循环遍历list里元素不重复
					for (int b = (a + 1); b < list.size(); b++) { // 遍历Excel表的每一列
						p = list.get(b);
						BDHM2 = (String) p.get("BDHM");
						if (BDHM.equals(BDHM2) ) {
							return "表中第" + (a + 2) + "行错误，该查询条件的查询请求单号与第" + (b + 2) + "行重复！";
						}
					}
				}
				
				// 传入参数list进行批量
				Map m = new HashMap();
				for (int h = 0; h < list.size(); h++) {
					m = list.get(h);
					logger.info("导入表DBO.QRY_CONDI_DATA的数据............." + m);
					// 更新表批量详细表DBO.QRY_CONDI_DATA
					 djcxService.insertConData(m);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String checkXml(String savePath, String taskId) {
		List list = XmlUtil.parseXML(savePath);
		for(int i=0;i<list.size();i++){
			CxqqInfo_JCY cxqq = (CxqqInfo_JCY) list.get(i);
			cxqq.setTaskId(taskId);
			//更新表批量详细表DBO.QRY_CONDI_DATA
			 djcxService.insertConDataXml(cxqq);
		}
		
		return null;
	}
	
	public int getWordCount(String s){
		int length = 0;
		for(int i=0 ;i<s.length();i++){
			int ascii = Character.codePointAt(s, i);
			if(ascii >=0 && ascii <= 255){
				length++;
			}else{
				length += 2;
			}
		}
		return length;
	}
	/**
	 * 获取excel单元格值
	 * 
	 * @param row
	 * @param i
	 * @return
	 */
	private String getCellValue(HSSFRow row, int i) {
		String cellValue = "";
		HSSFCell cell = row.getCell(i);
		if (cell != null) {
			cellValue = this.getCellStringValue(cell).trim();
		} else {
			cellValue = "";
		}
		return cellValue;
	}

	public String toIntStr(String cellValue){
		if(cellValue.endsWith(".00")){
			cellValue.replace(".00", "");
		}
		return cellValue;
	}
	
	/**
	 * 根据单元格不同属性返回字符串值
	 */

	public String getCellStringValue(HSSFCell cell) {

		String cellValue = "";

		switch (cell.getCellType()) {

		case HSSFCell.CELL_TYPE_STRING:// 字符串类型
			cellValue = cell.getStringCellValue();
			if (cellValue.trim().equals("") || cellValue.trim().length() <= 0)
				cellValue = " ";
			break;

		case HSSFCell.CELL_TYPE_NUMERIC: // 数值类型
			cellValue = String.valueOf(cell.getNumericCellValue());
			if(cellValue.endsWith(".0")){
				cellValue = cellValue.replace(".0", "");
			}
			break;

		case HSSFCell.CELL_TYPE_FORMULA: // 公式
			cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;

		case HSSFCell.CELL_TYPE_BLANK:
			cellValue = " ";
			break;

		case HSSFCell.CELL_TYPE_BOOLEAN:
			break;

		case HSSFCell.CELL_TYPE_ERROR:
			break;

		default:

			break;

		}

		return cellValue;

	}
	
	

	
	
	
}

package com.yitong.app.action.judicial.plcx;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.yitong.app.service.judicial.plcx.BatchQueryService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.model.Properties;
import com.yitong.commons.model.UserSession;
import com.yitong.commons.util.DateUtil;
import com.yitong.commons.util.ParamUtil;

/**
 * 批量查询相关
 * 
 * @author db2admin
 * 
 */
public class BatchQueryAction extends BaseAction {
	protected static final Logger logger = Logger.getLogger(BatchQueryAction.class);

	private BatchQueryService batchQueryService;
	private static String uploadPath = Properties.getString("uploadPath");// 上传excel路径
	private static String downloadPath = Properties.getString("downloadPath");// 生成excel路径
	private static String modelPath = Properties.getString("modelPath");// excel模板路径

	/**
	 * 批量查询：导入查询条件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toQuery(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Calendar cal = Calendar.getInstance();
		String nowDate = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
		request.setAttribute("nowDate", nowDate);
		List<Map> batchTypesQy = batchQueryService.getBatchTypesQy();

		String batchType = (String) batchTypesQy.get(0).get("BATCH_TYPE");
		Map dateFlagMap = batchQueryService.getDateQueryFlag(batchType);
		String dateFlag = dateFlagMap.get("NEED_QUERY_DATE").toString();
		String result = "";
		if ("Y".equals(dateFlag)) {
			result = "是";
		} else {
			result = "否";
		}
		request.setAttribute("dateQueryFlag", result);

		request.setAttribute("batchTypes", batchTypesQy);
		return mapping.findForward("plcx");
	}

	/**
	 * 批量导入：下载模板
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward download(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String fileName = "BatchQueryModel.xls";

		if (null == fileName || "".equals(fileName))
			return null;

		logger.info("++++++++++++++++++++++++++++++++++++下载路径" + modelPath);
		try {
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
			ServletOutputStream out = null;
			out = response.getOutputStream();
			ServletUtils.returnFile(modelPath + fileName, out);// 下载文件
			out.close();

		} catch (UnsupportedEncodingException ex) {// iso8559_1编码异常
			ex.printStackTrace();
		} catch (IOException e) {
			request.setAttribute("msg", "下载链接失效，指定路径文件不存在！");
			e.printStackTrace();
			return mapping.findForward(FAILURE);
		} finally {

		}
		return null;
	}

	/**
	 * 批量查询导入：批量excel上传导入
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings( { "unchecked", "static-access" })
	public ActionForward uploadMB(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		UserSession userSess = getUserSession(request);
		Map params = new HashMap();
		String orgNo = userSess.getOrgId();
		String user = userSess.getUserId();
		String flag = ParamUtil.get(request, "flag");// 是否立即生成0否1是
		String batchType = ParamUtil.get(request, "batchType");// 批量查询交易名称

		UploadForm uf = (UploadForm) form;
		FormFile file = uf.getFile();
		String fileName = file.getFileName();
		String houzhui = this.getExtensionName(fileName);
		if (!"XLS".equals(houzhui.toUpperCase())) {
			request.setAttribute("msg", "导入模板格式不正确，只接受xls格式文件");
			return mapping.findForward(FAILURE);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String saveName = user + "_" + sdf.format(new Date());

		String savePath = uploadPath + saveName + "." + houzhui;
		logger.info("+++++++++++++++++++++++++++++++++++++++上传路径" + uploadPath + saveName);
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

		params.put("user", user);
		params.put("orgNo", orgNo);
		params.put("importFileName", fileName);
		params.put("uploadFileName", saveName + "." + houzhui);
		params.put("resultFileName", saveName);
		params.put("flag", flag);
		params.put("batchType", batchType);
		String errorMsg = this.checkExcel(savePath, orgNo, params);

		if (errorMsg != null) {
			request.setAttribute("msg", "批量导入查询条件失败!" + errorMsg);
			return mapping.findForward(FAILURE);
		}

		request.setAttribute("msg", "批量导入查询条件成功,请提交审核!");
		return mapping.findForward(SUCCESS);
	}

	/**
	 * Excel验证规则： 
	 * 1.验证excel表头是否有4列 
	 * 2.验证导入excel的列名是否与参数表一致
	 * 3.验证导入excel的证件种类是否与配置表中的可用证件种类一致 
	 * 4.证件号码不能为空
	 * 5.若需要查询日期： 开始日期、结束日期不能为空，且必须为8位数字 结束日期不能超过当日，开始日期不能大于结束日期 
	 * 6.各种证件种类长度验证
	 *   身份证号：18位数字或17位数字+1位字母 15位数字或14位数字+1位字母 
	 *   卡号：19位数字 
	 *   客户账号：23位数字 
	 *   内部账号：25位数字
	 *   组织机构代码：未判断 
	 *   客户号：12位数字 
	 * 7.证件种类、证件号码不能重复
	 * 
	 * @param savePath
	 * @param orgNo
	 * @param infoParams
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String checkExcel(String savePath, String orgNo, Map infoParams) {

		try {
			List<Map> list = new ArrayList<Map>();
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(savePath));
			HSSFWorkbook wb = new HSSFWorkbook(fs);// 得到Excel工作簿对象
			HSSFSheet sheet = wb.getSheetAt(0); // 得到Excel工作表对象 第一个sheet

			int rowcount = sheet.getLastRowNum(); // 获得Excel表的行数
			logger.info("Excel最后一行的行数为：" + rowcount);
			if (rowcount == 0) {
				return "导入的Excel表行数为0，Excel为空表！";
			}
			if (rowcount < 1) {
				return "导入的Excel表中不含有查询条件";
			}
			 if (rowcount > 2000) {
			 return "批量查询一次不得超过2000条！";
		 }
			HSSFRow row = sheet.getRow(0);// 得到Excel工作表的行 第一行 表头
			HSSFCell cell = null;
			String cellValue = "";
			int columnNum = row.getLastCellNum(); // 获得Excel表的列数
			logger.info("Excel最后一列的列数为：" + columnNum);

			if (columnNum != 4) {
				return "Excel表头列数不对，请重新下载模板核对！";
			}

			for (int j = 0; j < columnNum; j++) {
				cell = row.getCell(j);
				cellValue = this.getCellStringValue(cell);
				// 验证导入Excel表的列名
				String errorColumn = this.checkColumnName(j, cellValue);
				if (errorColumn != null) {
					return errorColumn;
				}
			}
			
			//获取参数表中excel列第一、二列名，用于提醒信息参数化修改
			List excelColumns = batchQueryService.getExcelColumns();
			String firstColumnName = excelColumns.get(0).toString();//查询类别
			String secondColumnName = excelColumns.get(1).toString();//查询号码

			// 以下从参数表中获得是否需要查询日期条件
			String batchType = (String) infoParams.get("batchType");
			Map batchImportConfig = batchQueryService.getBatchImportConfigInfo(batchType);
			String dateQueryFlag = (String) batchImportConfig.get("NEED_QUERY_DATE");// 是否需要查询日期条件

			// 根据导入的交易类型获取证件种类（逗号分隔的字符串）
			String string = batchImportConfig.get("QUERY_NO_TYPE").toString();
			String queryNoType = string = "'" + string.replace(",", "','") + "'";
			List queryNoTypeWithName = batchQueryService.getImportNoTypesNamesWithValue(queryNoType);

			String batchId = batchQueryService.getBatchId();
			infoParams.put("importRowNum", rowcount);
			infoParams.put("batchId", batchId);

			if (rowcount > 0) {
				cellValue = "";
				for (int i = 1; i <= rowcount; i++) {
					logger.info("第" + i + "行......................");
					Map params = new HashMap();
					row = sheet.getRow(i);
					if (row == null || (this.getCellValue(row, 0).equals("") && this.getCellValue(row, 1).equals(""))
							&& this.getCellValue(row, 2).equals("") && this.getCellValue(row, 3).equals("")) {
						continue;
					}
					for (int k = 0; k < columnNum; k++) {
						try {
							cell = row.getCell(k);
							if (cell != null) {
								cellValue = this.getCellStringValue(cell).trim();
							} else {
								cellValue = "";
							}
							if (k == 0) {// 校验号码类型
								if ("".equals(cellValue)) {
									return "表中第" + i + "行第" + k + "列错误，"+firstColumnName+"不得为空！";
								}
								boolean importNoTypeBoolean = false;
								for (int j = 0; j < queryNoTypeWithName.size(); j++) {
									// 比较导入Excel表中的证件种类在参数表BATCH_IMPORT_CONFIG表中是否存在
									Map queryNoTypeName = (Map) queryNoTypeWithName.get(j);
									if (cellValue.equals(queryNoTypeName.get("VAR_NAME"))) {
										importNoTypeBoolean = true;
										params.put("importNoType", queryNoTypeName.get("VALUE"));// 存入证件种类，放入表BATCH_IMPORT_DETAIL表中
										break;
									}
								}

								if (importNoTypeBoolean == false) {
									return "表中第" + (i + 1) + "行第" + (k + 1) + "列错误，不支持该"+firstColumnName+"的查询！";
								}
							}
							if (k == 1) {// 校验号码
								if ("".equals(cellValue)) {
									return "表中第" + (i + 1) + "行第" + (k + 1) + "列错误，"+secondColumnName+"不得为空！";
								}
								// 存入证件号码
								params.put("importNo", cellValue);
							}
							if ("Y".equals(dateQueryFlag)) {// 当需要日期查询条件时，开始日期和结束日期不可为空
							
								if (k == 2) {// 开始日期
									if ("".equals(cellValue)) {
										return "表中第" + (i + 1) + "行第" + (k + 1) + "列错误，开始日期不得为空！";
									}
									Pattern DatePattern = Pattern.compile("\\d{8}");
									Matcher DateMatcher = DatePattern.matcher(cellValue);
									if (!DateMatcher.matches()) {
										return "表中第" + (i + 1) + "行第" + (k + 1) + "列错误，开始日期必须为8个数字！";
									}
									// 存入开始日期
									logger.info("开始日期:" + cellValue);
									params.put("startDate", cellValue);
								}
								if (k == 3) {// 结束日期
									if ("".equals(cellValue)) {
										return "表中第" + (i + 1) + "行第" + (k + 1) + "列错误，结束日期不得为空！";
									}
									Pattern DatePattern = Pattern.compile("\\d{8}");
									Matcher DateMatcher = DatePattern.matcher(cellValue);
									if (!DateMatcher.matches()) {
										return "表中第" + (i + 1) + "行第" + (k + 1) + "列错误，结束日期必须为8个数字！";
									}
									SimpleDateFormat sdfEnd = new SimpleDateFormat("yyyyMMdd");
									String lastDate = sdfEnd.format(new Date());// 获得当前日期
									logger.info("当前日期:" + lastDate + "结束日期:" + cellValue);

									if (Integer.parseInt(cellValue) > Integer.parseInt(lastDate)) {
										return "表中第" + (i + 1) + "行第" + (k + 1) + "列错误，结束日期必须不能超过当前日期！";
									}
									logger.info("startDate：" + Integer.parseInt(params.get("startDate").toString()));
									if (Integer.parseInt(params.get("startDate").toString()) > Integer.parseInt(cellValue)) {
										return "表中第" + (i + 1) + "行查询开始日期不得大于结束日期";
									}
									// 存入结束日期
									logger.info("结束日期:" + cellValue);
									params.put("endDate", cellValue);
								}
							}
						} catch (Exception e) {
							e.printStackTrace();
							logger.info("导入Excel实际查询条件为" + rowcount + "行，请认真核对Excel表格的空白部分");
							return "导入Excel实际查询条件为" + rowcount + "行，请认真核对Excel表格的空白部分";
						}
					}
					// 存入批量查询的ID
					params.put("batchId", batchId);
					list.add(params);
				}

				// 检查Excel表内有无证件种类和证件号码同时相等的情况，即相同的查询条件
				Map p = new HashMap();
				String importNo = "";
				String importNoType = "";
				String importNo_2 = "";
				String importNoType_2 = "";
				for (int a = 0; a < list.size(); a++) { // 遍历Excel表的每一行
					p = list.get(a);
					importNo = (String) p.get("importNo");
					importNoType = (String) p.get("importNoType");
					logger.info(importNoType + "------------------" + importNo);
					// 验证每种证件种类对应的证件号码
					String str = this.checkIdNo(a, importNoType, importNo,batchType);
					if (str != null) {
						return str;
					}
					// 双循环遍历list里元素不重复
					for (int b = (a + 1); b < list.size(); b++) { // 遍历Excel表的每一列
						p = list.get(b);
						importNo_2 = (String) p.get("importNo");
						importNoType_2 = (String) p.get("importNoType");
						if (importNo.equals(importNo_2) && importNoType.equals(importNoType_2)) {
							return "表中第" + (a + 2) + "行错误，该查询条件的"+firstColumnName+"、"+secondColumnName+"与第" + (b + 2) + "行重复！";
						}
					}
				}
				// 更新批量导入表DBO.BATCH_IMPORT
				batchQueryService.insertInfo(infoParams);
				// 传入参数list进行批量
				Map m = new HashMap();
				for (int h = 0; h < list.size(); h++) {
					m = list.get(h);
					logger.info("导入表DBO.BATCH_IMPORT的数据............." + m);
					// 更新表批量详细表DBO.BATCH_IMPORT_DETAIL
					batchQueryService.addInfo(m);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 验证导入Excel表格的列名
	@SuppressWarnings("unchecked")
	public String checkColumnName(int j, String cellValue) {
		List excelColumns = batchQueryService.getExcelColumns();
		if (!excelColumns.get(j).equals(cellValue)) {
			return "Excel表头第" + (j + 1) + "列列名不对，请重新下载模板核对！";
		}
		return null;
	}

	// 验证导入Excel表格的证件号码
	public String checkIdNo(int a, String importNoType, String importNo,String batchType) {
		logger.info("Excel表第" + (a + 2) + "行的证件类型是：" + importNoType + "证件号码是：" + importNo);
		if ("1".equals(importNoType)) {
			Pattern idNoPattern = Pattern.compile("(\\d{17}[0-9a-zA-Z])|(\\d{14}[0-9a-zA-Z])");
			Matcher idNoMatcher = idNoPattern.matcher(importNo);
			if (!idNoMatcher.matches()) {
				return "表中第" + (a + 2) + "行第2列只能为15位或18位的身份证号！";
			}
		}
		if ("2".equals(importNoType)) {
			Pattern idNoPattern = Pattern.compile("\\d{19}");
			Matcher idNoMatcher = idNoPattern.matcher(importNo);
			if (!idNoMatcher.matches()) {
				return "表中第" + (a + 2) + "行第2列卡号只能为19位的数字";
			}
		}
		if ("4".equals(importNoType)) {
			Pattern idNoPattern = Pattern.compile("\\d{23}");
			Matcher idNoMatcher = idNoPattern.matcher(importNo);
			if (!idNoMatcher.matches()) {
				return "表中第" + (a + 2) + "行第2列客户账号只能为23位的数字";
			}
			if(batchType.startsWith("1")){//对私交易类型
				if(!importNo.startsWith("1")){
					return "表中第" + (a + 2) + "行第2列客户账号只能为对私客户账号";
				}
			}
			if(batchType.startsWith("2")){//对公交易类型
				if(!importNo.startsWith("2")){
					return "表中第" + (a + 2) + "行第2列客户账号只能为对公客户账号";
				}
			}
		}
		if ("5".equals(importNoType)) {
			Pattern idNoPattern = Pattern.compile("\\d{25}");
			Matcher idNoMatcher = idNoPattern.matcher(importNo);
			if (!idNoMatcher.matches()) {
				return "表中第" + (a + 2) + "行第2列内部账号只能为25位的数字";
			}
		}
		if ("6".equals(importNoType)) {
			// Pattern idNoPattern = Pattern.compile("\\d{25}");
			// Matcher idNoMatcher = idNoPattern.matcher(importNo);
			// if (!idNoMatcher.matches()) {
			// return "表中第"+(a+2)+"行第2列组织机构代码只能为25位的数字";
			// }
			if (importNoType.getBytes().length > 50) {
				return "表中第" + (a + 2) + "行第2列组织机构代码输入超过50位";
			}
		}
		if ("7".equals(importNoType)) {
			Pattern idNoPattern = Pattern.compile("\\d{12}");
			Matcher idNoMatcher = idNoPattern.matcher(importNo);
			if (!idNoMatcher.matches()) {
				return "表中第" + (a + 2) + "行第2列客户号只能为12位的数字";
			}
			if(batchType.startsWith("1")){//对私交易类型
				if(!importNo.startsWith("1")){
					return "表中第" + (a + 2) + "行第2列客户号只能为对私客户号";
				}
			}
			if(batchType.startsWith("2")){//对公交易类型
				if(!importNo.startsWith("2")){
					return "表中第" + (a + 2) + "行第2列客户号只能为对公客户号";
				}
			}
			
		}
		return null;
	}

	/**
	 * 
	 */

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

	/*
	 * 
	 * 
	 * Java文件操作 获取文件扩展名
	 * 
	 * Created on: 2011-8-2 Author: blueeagle
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/*
	 * Java文件操作 获取不带扩展名的文件名
	 * 
	 * Created on: 2011-8-2 Author: blueeagle
	 */
	public static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}

	public BatchQueryService getBatchQueryService() {
		return batchQueryService;
	}

	public void setBatchQueryService(BatchQueryService batchQueryService) {
		this.batchQueryService = batchQueryService;
	}

	/**
	 * 提交审核列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toCommitAuditList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		params.put("importUser", this.getUserSession(request).getUserId());
		ParamUtil.putStr2Map(request, "importStatus", params);
		ParamUtil.putStr2Map(request, "importFileName", params);
		ParamUtil.putStr2Map(request, "batchType", params);

		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = batchQueryService.pageQueryCommitAudit(params, pageNo);
		request.setAttribute("page", page);

		// 查询条件：批量查询交易名称
		List batchTypes = batchQueryService.getBatchTypesQy();
		request.setAttribute("batchTypes", batchTypes);

		return mapping.findForward("commitAuditList");
	}

	// 根据交易类型自动获得参数表中是否需要查询日期条件的NEED_QUERY_DATE的值
	@SuppressWarnings("unchecked")
	public void getNeedQueryDate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		String batchType = ParamUtil.get(request, "batchType");// 批量查询交易名称
		Map dateFlag = batchQueryService.getDateQueryFlag(batchType);
		String dateQueryFlag = dateFlag.get("NEED_QUERY_DATE").toString();

		String result = "";
		if ("Y".equals(dateQueryFlag)) {
			result = "是";
		} else if ("N".equals(dateQueryFlag)) {
			result = "否";
		} else {
			result = "";
		}

		response.setContentType("text/plain");

		response.setCharacterEncoding("UTF-8");

		StringBuffer bf = new StringBuffer();

		bf.append("{").append("name:\"").append(result).append("\"").append("}");

		PrintWriter out = response.getWriter();

		out.print(bf.toString());

		out.close();
	}

	/**
	 * 提交审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward commitAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String batchId = ParamUtil.get(request, "batchId");
		batchQueryService.commitAudit(batchId);

		request.setAttribute("msg", "提交审核成功");
		return mapping.findForward(SUCCESS);
	}

	/**
	 * 提交审核列表：删除操作 对于未提交、审核不通过的记录，可以进行删除操作
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward deleteInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String[] batchId = ParamUtil.get(request, "batchId").split(",");
		Map params = new HashMap();
		params.put("batchIds", batchId);
		batchQueryService.deleteInfo(params);

		request.setAttribute("msg", "删除成功");
		return mapping.findForward(SUCCESS);
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
	public ActionForward downLoadImportFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		String fileName = request.getParameter("fileName");
		if (null == fileName || "".equals(fileName))
			return null;
		String savePath = uploadPath + fileName;
		try {
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
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
	 * 批量查询审核列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toAuditList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "importUser", params);
		ParamUtil.putStr2Map(request, "importStatus", params);
		ParamUtil.putStr2Map(request, "importFileName", params);
		ParamUtil.putStr2Map(request, "batchType", params);

		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = batchQueryService.pageQueryAudit(params, pageNo);
		request.setAttribute("page", page);

		// 查询条件：批量查询交易名称
		List batchTypes = batchQueryService.getBatchTypesQy();
		request.setAttribute("batchTypes", batchTypes);

		return mapping.findForward("auditList");
	}

	/**
	 * 批量查询审核：审核页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toAudit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String batchIds = ParamUtil.get(request, "batchIds");
		String importStatus = ParamUtil.get(request, "importStatus");

		request.setAttribute("batchIds", batchIds);
		request.setAttribute("importStatus", importStatus);

		return mapping.findForward("audit");
	}

	/**
	 * 批量查询审核：审核通过、审核不通过操作
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward audit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String batchIds = ParamUtil.get(request, "batchIds");
		String importStatus = ParamUtil.get(request, "importStatus");

		Map params = new HashMap();
		params.put("userId", this.getUserSession(request).getUserId());
		params.put("orgId", this.getUserSession(request).getOrgId());
		ParamUtil.putStr2Map(request, "auditRemark", params);

		batchQueryService.audit(batchIds, importStatus, params);
		request.setAttribute("msg", "审核成功");
		return mapping.findForward(SUCCESS);
	}

	/**
	 * 批量查询结果文件下载列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toDownloadList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		params.put("importUser", this.getUserSession(request).getUserId());
		ParamUtil.putStr2Map(request, "importStatus", params);
		ParamUtil.putStr2Map(request, "importFileName", params);
		ParamUtil.putStr2Map(request, "batchType", params);

		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = batchQueryService.pageQueryDownload(params, pageNo);
		request.setAttribute("page", page);

		// 查询条件：批量查询交易名称
		List batchTypes = batchQueryService.getBatchTypesQy();
		request.setAttribute("batchTypes", batchTypes);

		return mapping.findForward("downloadList");
	}

	/**
	 * 批量查询：状态为审核通过待生成、审核不通过，查看审核意见
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward viewAuditRemark(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String batchId = ParamUtil.get(request, "batchId");
		request.setAttribute("rst", batchQueryService.viewAuditRemark(batchId));
		return mapping.findForward("auditRemark");
	}
	
	/**
	 * 批量查询：任务失败查询原因页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward viewErrorRemark(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String batchId = ParamUtil.get(request, "batchId");
		request.setAttribute("batchId",batchId);
		request.setAttribute("rst", batchQueryService.viewAuditRemark(batchId));
		return mapping.findForward("errorRemark");
	}

	/**
	 * 批量查询结果：文件下载
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward downLoadResultFile(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		String fileName = request.getParameter("fileName")+".tar.gz";
		if (null == fileName || "".equals(fileName))
			return null;
		String savePath = downloadPath + fileName;
		try {
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
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
	 * 批量查询结果文件下载：重新生成
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward resetImportStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String batchId = ParamUtil.get(request, "batchId");

		batchQueryService.resetImportStatus(batchId);

		request.setAttribute("msg", "操作成功");
		return mapping.findForward(SUCCESS);
	}

	/**
	 * 获取excel列值
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
}

package com.yitong.app.action.judicial.dxzpzfdj;


import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFSimpleShape;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import cfca.safeguard.Result;
import cfca.safeguard.api.bank.ClientEnvironment;
import cfca.safeguard.api.bank.Constants;
import cfca.safeguard.api.bank.SGBusiness;
import cfca.safeguard.api.bank.bean.tx.upstream.Tx100401;
import cfca.safeguard.api.bank.bean.tx.upstream.Tx100403;
import cfca.safeguard.api.bank.bean.tx.upstream.Tx100404;
import cfca.safeguard.api.bank.bean.tx.upstream.Tx100405;
import cfca.safeguard.api.bank.util.ResultUtil;
import cfca.safeguard.tx.Attachment;
import cfca.safeguard.tx.Transaction;
import cfca.safeguard.tx.business.bank.TxCaseReport_Transaction;
import cfca.safeguard.tx.business.bank.TxExceptionalEvent_Account;
import cfca.safeguard.tx.business.bank.TxExceptionalEvent_Accounts;
import cfca.safeguard.tx.business.bank.TxExceptionalEvent_Transaction;
import cfca.safeguard.tx.business.bank.TxInvolvedAccount_Account;
import cfca.safeguard.tx.business.bank.TxUnusualOpencard_Accounts;

import com.oreilly.servlet.ServletUtils;
import com.yitong.app.service.judicial.dxzpzfdj.ZfdjService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.util.ParamUtil;
import com.yitong.commons.util.StringUtil;

public class ZfdjAction extends BaseAction {

	protected static final Logger logger = Logger.getLogger(ZfdjAction.class);

	private ZfdjService zfdjService;

	public ZfdjService getZfdjService() {
		return zfdjService;
	}

	public void setZfdjService(ZfdjService zfdjService) {
		this.zfdjService = zfdjService;
	}

	/**
	 * 司法查询：止付查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toZfTaskList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		ParamUtil.putStr2Map(request, "STATUS", params);
		ParamUtil.putStr2Map(request, "ksr", params);
		ParamUtil.putStr2Map(request, "jsr", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = zfdjService.pageQueryZfTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}

	@SuppressWarnings("unchecked")
	public ActionForward toView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String APPLICATIONID = ParamUtil.get(request, "APPLICATIONID");
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		Map entry = zfdjService.load(APPLICATIONID);

		request.setAttribute("rst", entry);
		return mapping.findForward("view");
	}

	/**
	 * 司法查询：止付反馈查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toZffkTaskList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = zfdjService.pageQueryZffkTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("zffklist");
	}

	@SuppressWarnings("unchecked")
	public ActionForward toZffkView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String APPLICATIONID = ParamUtil.get(request, "APPLICATIONID");
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		Map entry = zfdjService.zffkload(APPLICATIONID);

		request.setAttribute("rst", entry);
		return mapping.findForward("zffkview");
	}

	/**
	 * 司法查询：止付解除查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toJcTaskList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		ParamUtil.putStr2Map(request, "STATUS", params);
		ParamUtil.putStr2Map(request, "ksr", params);
		ParamUtil.putStr2Map(request, "jsr", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = zfdjService.pageQueryJcTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("jclist");
	}

	@SuppressWarnings("unchecked")
	public ActionForward toJcView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String APPLICATIONID = ParamUtil.get(request, "APPLICATIONID");
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		Map entry = zfdjService.jcload(APPLICATIONID);

		request.setAttribute("rst", entry);
		return mapping.findForward("jcview");
	}

	/**
	 * 司法查询：止付解除反馈查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toJcfkTaskList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = zfdjService.pageQueryJcfkTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("jcfklist");
	}

	@SuppressWarnings("unchecked")
	public ActionForward toJcfkView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String APPLICATIONID = ParamUtil.get(request, "APPLICATIONID");
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		Map entry = zfdjService.jcfkload(APPLICATIONID);

		request.setAttribute("rst", entry);
		return mapping.findForward("jcfkview");
	}

	/**
	 * 司法查询：止付延期查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toYqTaskList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		ParamUtil.putStr2Map(request, "STATUS", params);
		ParamUtil.putStr2Map(request, "ksr", params);
		ParamUtil.putStr2Map(request, "jsr", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = zfdjService.pageQueryYqTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("yqlist");
	}

	@SuppressWarnings("unchecked")
	public ActionForward toYqView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String APPLICATIONID = ParamUtil.get(request, "APPLICATIONID");
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		Map entry = zfdjService.yqload(APPLICATIONID);

		request.setAttribute("rst", entry);
		return mapping.findForward("yqview");
	}

	/**
	 * 司法查询：止付延期反馈查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toYqfkTaskList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = zfdjService.pageQueryYqfkTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("yqfklist");
	}

	@SuppressWarnings("unchecked")
	public ActionForward toYqfkView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String APPLICATIONID = ParamUtil.get(request, "APPLICATIONID");
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		Map entry = zfdjService.yqfkload(APPLICATIONID);

		request.setAttribute("rst", entry);
		return mapping.findForward("yqfkview");
	}

	/**
	 * 司法查询：冻结查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toDjTaskList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		ParamUtil.putStr2Map(request, "ACCOUNTNUMBER", params);
		ParamUtil.putStr2Map(request, "STATUS", params);
		ParamUtil.putStr2Map(request, "ksr", params);
		ParamUtil.putStr2Map(request, "jsr", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = zfdjService.pageQueryDjTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("djlist");
	}

	@SuppressWarnings("unchecked")
	public ActionForward toDjView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String APPLICATIONID = ParamUtil.get(request, "APPLICATIONID");
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		Map entry = zfdjService.djload(APPLICATIONID);

		request.setAttribute("rst", entry);
		return mapping.findForward("djview");
	}

	/**
	 * 司法查询：冻结反馈查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toDjfkTaskList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = zfdjService.pageQueryDjfkTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("djfklist");
	}

	@SuppressWarnings("unchecked")
	public ActionForward toDjfkView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String APPLICATIONID = ParamUtil.get(request, "APPLICATIONID");
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		Map entry = zfdjService.djfkload(APPLICATIONID);

		request.setAttribute("rst", entry);
		return mapping.findForward("djfkview");
	}

	/**
	 * 司法查询：冻结解除查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toDjjcTaskList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		ParamUtil.putStr2Map(request, "ORIGINALAPPLICATIONID", params);
		ParamUtil.putStr2Map(request, "ACCOUNTNUMBER", params);
		ParamUtil.putStr2Map(request, "STATUS", params);
		ParamUtil.putStr2Map(request, "ksr", params);
		ParamUtil.putStr2Map(request, "jsr", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = zfdjService.pageQueryDjjcTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("djjclist");
	}

	@SuppressWarnings("unchecked")
	public ActionForward toDjjcView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String APPLICATIONID = ParamUtil.get(request, "APPLICATIONID");
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		Map entry = zfdjService.djjcload(APPLICATIONID);

		request.setAttribute("rst", entry);
		return mapping.findForward("djjcview");
	}

	/**
	 * 司法查询：冻结解除反馈查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toDjjcfkTaskList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = zfdjService.pageQueryDjjcfkTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("djjcfklist");
	}

	@SuppressWarnings("unchecked")
	public ActionForward toDjjcfkView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String APPLICATIONID = ParamUtil.get(request, "APPLICATIONID");
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		Map entry = zfdjService.djjcfkload(APPLICATIONID);

		request.setAttribute("rst", entry);
		return mapping.findForward("djjcfkview");
	}

	/**
	 * 司法查询：冻结延期查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toDjyqTaskList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		ParamUtil.putStr2Map(request, "ORIGINALAPPLICATIONID", params);
		ParamUtil.putStr2Map(request, "ACCOUNTNUMBER", params);
		ParamUtil.putStr2Map(request, "STATUS", params);
		ParamUtil.putStr2Map(request, "ksr", params);
		ParamUtil.putStr2Map(request, "jsr", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = zfdjService.pageQueryDjyqTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("djyqlist");
	}

	@SuppressWarnings("unchecked")
	public ActionForward toDjyqView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String APPLICATIONID = ParamUtil.get(request, "APPLICATIONID");
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		Map entry = zfdjService.djyqload(APPLICATIONID);

		request.setAttribute("rst", entry);
		return mapping.findForward("djyqview");
	}

	/**
	 * 司法查询：冻结延期反馈查询
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toDjyqfkTaskList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = zfdjService.pageQueryDjyqfkTask(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("djyqfklist");
	}

	@SuppressWarnings("unchecked")
	public ActionForward toDjyqfkView(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String APPLICATIONID = ParamUtil.get(request, "APPLICATIONID");
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "APPLICATIONID", params);
		Map entry = zfdjService.djyqfkload(APPLICATIONID);

		request.setAttribute("rst", entry);
		return mapping.findForward("djyqfkview");
	}
	

	/**
	 * 司法查询：司法查控业务统计表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public ActionForward toYwtjOperation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("ywtjview");
	}
	
	
	/**
	 * 司法查询：生成业务统计表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws ParseException 
	 * @throws UnsupportedEncodingException 
	 */
	public ActionForward makeYwtjExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws ParseException {
		response.setContentType("application/x-msdownload");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd 00:00:00");
		Map params = new HashMap();
		String startDay = request.getParameter("ksr");
		String endDay = request.getParameter("jsr");
		
		params.put("ksr", startDay+" 00:00:00");
		params.put("jsr", endDay+" 23:59:59");
		
		Map<String, String> map =   zfdjService.dxzpSfckAddUp(params);
		
		//开始生成Excel统计表
		int totalColNum = 4;
		//这里是poi操作excel的基本导入命令 思路 创建工作簿 工作簿创建工作表 工作表创建行 行创建单元格
        HSSFWorkbook wk = new HSSFWorkbook();
        HSSFSheet sheet = wk.createSheet("业务统计表");
        
        //设置四个列宽
        sheet.setColumnWidth(0, 6000) ; 
        sheet.setColumnWidth(1, 6000) ; 
        sheet.setColumnWidth(2, 6000) ; 
        sheet.setColumnWidth(3, 6000) ; 
        
        //标题单元格样式
        HSSFCellStyle titleCellStyle = wk.createCellStyle() ;  
        HSSFFont titleCellfont = wk.createFont(); 
        titleCellStyle.setAlignment(CellStyle.ALIGN_CENTER);//水平居中    
        titleCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中
        titleCellfont.setFontHeightInPoints((short) 14);// 设置字体大小
        titleCellfont.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);//加粗   
        
        //第二行单元格样式
        HSSFCellStyle secCellStyle =  wk.createCellStyle();
        secCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        
        //斜线单元格样式
        HSSFCellStyle patriarchCellStyle = wk.createCellStyle() ; 
        HSSFFont patriarchCellfont = wk.createFont(); 
        patriarchCellfont.setFontHeightInPoints((short) 9);// 设置字体大小
        patriarchCellStyle.setAlignment(CellStyle.ALIGN_CENTER);//水平居中    
        patriarchCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
        patriarchCellStyle.setFont(patriarchCellfont);
        
        //其他单元格样式
        HSSFCellStyle otherCellStyle = wk.createCellStyle() ;
        otherCellStyle.setWrapText(true);  //设置自动换行
        otherCellStyle.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
        otherCellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中 
    
        
        //设置标题 
        HSSFRow titleRow = sheet.createRow(0);
        HSSFCell titleCell = titleRow.createCell(0);
        titleCell.setCellValue("业务统计表");
        titleCell.setCellStyle(titleCellStyle);
        titleRow.setHeight((short) 1000);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
        
        
        //设置第二行
        HSSFRow secRow = sheet.createRow(1);
        secRow.setHeight((short)400);
        HSSFCell unitCell = secRow.createCell(0);
        HSSFCell dateCell = secRow.createCell(3);
        unitCell.setCellValue("填报单位：（公章）");
        unitCell.setCellStyle(secCellStyle);
        dateCell.setCellValue("日期：");
        dateCell.setCellStyle(secCellStyle);
        
        //设置表头行
        HSSFRow tableHeaderRow = sheet.createRow(2);
        tableHeaderRow.setHeight((short) 700) ; 
        //将表头所有列设置成数组
        HSSFCell[] tableHeaderCell = new HSSFCell[totalColNum];
        //将第一行所有列名写入到一个string数组里
        String[] colNames = new String[totalColNum];
        colNames[0] = "";
        colNames[1] = "业务笔数\r（单位：笔）";
        colNames[2] = "账户数量\r（单位：笔）";
        colNames[3] = "金额\r（单位：元）";
        //循环遍历第二行所有列
        for(int i=0;i<totalColNum;i++){
            //挨个创建表头单元格
        	tableHeaderCell[i] = tableHeaderRow.createCell(i);
        	if(i == 0){
        		tableHeaderCell[0].setCellValue("业务种类                 处理结果") ;  
        		tableHeaderCell[0].setCellStyle(patriarchCellStyle) ; 
        	}else{
        		//设置第一行单元格的值 也就是表头的名称 这里的new HSSFRichTextString(colNames[i]) 是把值都转换为文本类型
                tableHeaderCell[i].setCellValue(new HSSFRichTextString(colNames[i]));
                tableHeaderCell[i].setCellStyle(otherCellStyle) ;
        	}
            
        }
        
        //画线(由左上到右下的斜线)  
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();  
        HSSFClientAnchor a = new HSSFClientAnchor(0, 0, 1023, 255, (short)0, 2, (short)0, 2);  
        HSSFSimpleShape shape1 = patriarch.createSimpleShape(a);  
        shape1.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);   
        shape1.setLineStyle(HSSFSimpleShape.LINESTYLE_SOLID) ; 
        
        HSSFClientAnchor b = new HSSFClientAnchor(0, 0, 1023, 255, (short)3, 5, (short)3, 5);  
        HSSFSimpleShape shape2 = patriarch.createSimpleShape(b);  
        shape2.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);   
        shape2.setLineStyle(HSSFSimpleShape.LINESTYLE_SOLID) ;  
        
        //下面的一段代码是将结果集里的值插入到excel中去
        for(int k=3;k<7;k++){//设置行
            //循环创建行 从第二行开始
            HSSFRow row = sheet.createRow(k);
            row.setHeight((short) 700) ;
            for(int i=0;i<totalColNum;i++){//设置列
                //创建每个单元格
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(otherCellStyle);
                //为单元格赋值 同样的使用new HSSFRichTextString()是为了将值都转化为文本格式处理 
                //rs.getString(i+1)结果集是从1开始遍历值的
                if(k == 3 && i == 0){//第二行第一列
                	cell.setCellValue(new HSSFRichTextString("紧急止付"));
                }
                
                if(k == 3 && i == 1){//第二行第二列 紧急止付业务笔数
                	cell.setCellValue(new HSSFRichTextString( map.get("zfBizNum")));
                }
                
                if(k == 3 && i == 2){//第二行第三列 紧急止付账户数量
                	cell.setCellValue(new HSSFRichTextString(map.get("zfAcctNum")));
                }
                
                if(k == 3 && i == 3){//第二行第四列 紧急止付金额
                	cell.setCellValue(new HSSFRichTextString(map.get("zfAcctBalance")));
                }
                
                if(k == 4 && i == 0){//第三行第一列
                	cell.setCellValue(new HSSFRichTextString("快速冻结"));
                }
                
                if(k == 4 && i == 1){//第三行第二列 快速冻结业务笔数
                	cell.setCellValue(new HSSFRichTextString( map.get("djBizNum")));
                }
                
                
                if(k == 4 && i == 2){//第三行第三列 快速冻结账户数量
                	cell.setCellValue(new HSSFRichTextString( map.get("djAcctNum")));
                }
                
                
                if(k == 4 && i == 3){//第三行第四列  快速冻结金额
                	cell.setCellValue(new HSSFRichTextString( map.get("djAcctBalance")));
                }
                
                if(k == 5 && i == 0){//第四行第一列 快速查询
                	cell.setCellValue(new HSSFRichTextString("快速查询"));
                }
                
                if(k == 5 && i == 1){//第四行第二列 快速查询业务笔数
                	cell.setCellValue(new HSSFRichTextString( map.get("qryBizNum")));
                }
                
                if(k == 5 && i == 2){//第四行第三列 快速查询账户数量
                	cell.setCellValue(new HSSFRichTextString( map.get("qryAcctNum")));
                }
                
                if(k == 5 && i == 3){//第四行第四列 快速查询金额  无效
                	cell.setCellValue(new HSSFRichTextString(""));
                	cell.setCellStyle(patriarchCellStyle);
                }
                
                if(k == 6 && i == 0){//第五行第一列
                	cell.setCellValue(new HSSFRichTextString("合计"));
                }

        		
                if(k == 6 && i == 1){//第五行第二列 合计业务笔数
                	cell.setCellValue(new HSSFRichTextString( map.get("totalBizNum")));
                }
                
                if(k == 6 && i == 2){//第五行第三列 合计账户数量
                	cell.setCellValue(new HSSFRichTextString(map.get("totalAcctNum")));
                }
                
                if(k == 6 && i == 3){//第五行第四列 合计金额
                	cell.setCellValue(new HSSFRichTextString(map.get("totalAcctBalance")));
                }
                
                
            }
        
        }
      
        System.out.println("模板生成成功");
  
		String filename = "sfckywtjbb_"+startDay+"_"+endDay+".xls";
		OutputStream os= null;
		
		try {
			  response.setHeader("Content-Disposition","attachment;"+"filename="+new String(filename.getBytes(), "ISO-8859-1"));
			  os=response.getOutputStream();
			  wk.write(os);
			  os.flush();
		  } catch (Exception e) {
			e.getStackTrace();
		 }finally{
			 if(os != null){
				 try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
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
	public ActionForward toErrorInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		
		System.out.println(ParamUtil.get(request, "errorInfo"));
		String errorInfo = URLDecoder.decode(ParamUtil.get(request, "errorInfo"), "utf-8") ;
		request.setAttribute("errorInfo", errorInfo);
		return mapping.findForward("errorInfo");
	}
	
	/**
	 * 法律文书下载
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
	 * 异常开卡可疑名单上报页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toSendCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		return mapping.findForward("toSendCard");
	}
	
	
	/**
	 * 异常开卡可疑名单上报方法
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward sendCard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Tx100403 tx100403 = new Tx100403();
		String featureCode = request.getParameter("featureCode");//事件特征码
		String IDType = request.getParameter("IDType");//证件类型
		String IDNumber = request.getParameter("IDNumber");//证件号
		String IDName = request.getParameter("IDName");//姓名
		String cardNumber = request.getParameter("cardNumber");//卡/折号
		String accountOpenTime = request.getParameter("accountOpenTime");//开卡时间
		String accountOpenPlace = request.getParameter("accountOpenPlace");//开卡地点
		String operatorName = request.getParameter("operatorName");//经办人姓名
		String operatorPhoneNumber = request.getParameter("operatorPhoneNumber");//经办人电话
		
		String transSerialNumber = StringUtil.getTransSerialNumber();//传输报文流水号
		//String newTransSerialNumber = StringUtil.getTransSerialNumber();//传输报文流水号
		String applicationId = StringUtil.getApplicationID();//业务申请编号
		Map params = new HashMap();
		params.put("TRANSSERIALNUMBER", transSerialNumber);
		params.put("JSJG", "111111000000");//接收机构ID
		params.put("FROMTGORGANIZATIONID", "402361018886");//托管机构编码
		params.put("TXCODE", "100403");//交易编码
		params.put("APPLICATIONID", applicationId);//业务申请编号
		params.put("FEATURECODE", featureCode);//事件特征码
		params.put("BANKID", "402361018886");//上报结构
		params.put("IDTYPE", IDType);//证件类型
		params.put("IDNUMBER", IDNumber);//证件号
		params.put("IDNAME", IDName);//姓名
		params.put("CARDNUMBER", cardNumber);//卡/折号
		params.put("ACCOUNTOPENTIME", accountOpenTime);//开卡时间
		params.put("ACCOUNTOPENPLACE", accountOpenPlace);//开卡地点
		params.put("OPERATORNAME", operatorName);//经办人姓名
		params.put("OPERATORPHONENUMBER", operatorPhoneNumber);//经办人电话
		params.put("STATUS", "0");
		//params.put("ERRORINFO", "");
		
		tx100403.setTxCode("100403");//交易编码
		tx100403.setMessageFrom("111111000000");//接收机构ID，默认值为 111111000000
		tx100403.setTransSerialNumber(transSerialNumber);//传输报文流水号
		tx100403.setApplicationID(applicationId);//业务申请编号
		tx100403.setFeatureCode(featureCode);//事件特征码
		tx100403.setIdType(IDType);//证件类型
		tx100403.setIdNumber(IDNumber);//证件号
		tx100403.setIdName(IDName);//姓名
		tx100403.setOperatorName(operatorName);//经办人姓名
		tx100403.setOperatorPhoneNumber(operatorPhoneNumber);//经办人电话
		
		List<TxUnusualOpencard_Accounts> list = new ArrayList<TxUnusualOpencard_Accounts>();
        TxUnusualOpencard_Accounts txUnusualOpencard_Accounts = new TxUnusualOpencard_Accounts();
        txUnusualOpencard_Accounts.setCardNumber(cardNumber);
        txUnusualOpencard_Accounts.setAccountOpenTime(accountOpenTime);
        txUnusualOpencard_Accounts.setAccountOpenPlace(accountOpenPlace);
        list.add(txUnusualOpencard_Accounts);
        
        tx100403.setAccountsList(list);
        
		String requestXML= "";
		//String requestXML = sgBusiness.tx100403(tx100403,fromTGOrganizationId);
		try {
			ClientEnvironment.initTxClientEnvironment("../cfca");
		    SGBusiness sgBusiness = new SGBusiness();
			requestXML = sgBusiness.tx100403(tx100403, tx100403.getMessageFrom());
			
			String responseXML = sgBusiness.sendPackagedRequestXML(requestXML);
	        Result result = ResultUtil.chageXMLToResult(responseXML);
	        System.out.println(result.getCode());
	        if (Constants.SUCCESS_CODE_VALUE.equals(result.getCode())) {//成功
	        	//params.put("NEWTRANSSERIALNUMBER", newTransSerialNumber);
	        	//params.put("CODE", "100000");//返回码
	        	params.put("ERRORINFO", "100000" + "");//返回消息
	        	
            }else{//失败
            	//params.put("NEWTRANSSERIALNUMBER", newTransSerialNumber);
            	//params.put("CODE", "111111");//返回码失败
            	params.put("ERRORINFO", "111111" + result.getDescription());//返回消息
            }
	        
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		zfdjService.insertYckk(params);
		request.setAttribute("msg", "发送成功！");
		return mapping.findForward(SUCCESS);
	}
	
	
	/**
	 * 涉案账户可疑名单上报页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toSendAccount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		return mapping.findForward("toSendAccount");
	}
	
	/**
	 * 涉案账户可疑名单上报方法
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward sendAccount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Tx100404 tx100404 = new Tx100404();
		
		String FEATURECODE = request.getParameter("FEATURECODE");//事件特征码
		String CARDNUMBER = request.getParameter("CARDNUMBER");//卡/折号
		String ACCOUNTNAME = request.getParameter("ACCOUNTNAME");//账户名
		String IDTYPE = request.getParameter("IDTYPE");//证件类型
		String IDNUMBER = request.getParameter("IDNUMBER");//证件号
		String PHONENUMBER = request.getParameter("PHONENUMBER");//联系电话
		String ADDRESS = request.getParameter("ADDRESS");//通讯地址
		String POSTCODE = request.getParameter("POSTCODE");//邮政编码
		String ACCOUNTOPENPLACE = request.getParameter("ACCOUNTOPENPLACE");//开卡地点
		String ACCOUNTNUMBER = request.getParameter("ACCOUNTNUMBER");//定位账户账号
		String ACCOUNTSERIAL = request.getParameter("ACCOUNTSERIAL");//一般(子)账户序号
		String ACCOUNTTYPE = request.getParameter("ACCOUNTTYPE");//一般(子)账户类别
		String ACCOUNTSTATUS = request.getParameter("ACCOUNTSTATUS");//账户状态
		String CURRENCY = request.getParameter("CURRENCY");//币种
		String CASHREMIT = request.getParameter("CASHREMIT");//钞汇标志
		String TRANSACTIONSERIAL = request.getParameter("TRANSACTIONSERIAL");//交易流水号
		String TRANSACTIONTIME = request.getParameter("TRANSACTIONTIME");//交易时间
		String TRANSACTIONTYPE = request.getParameter("TRANSACTIONTYPE");//交易类型
		String BORROWINGSIGNS = request.getParameter("BORROWINGSIGNS");//借贷标志
		String TRANSACTIONAMOUNT = request.getParameter("TRANSACTIONAMOUNT");//交易金额
		String ACCOUNTBALANCE = request.getParameter("ACCOUNTBALANCE");//交易余额
		
		String OPPONENTNAME = request.getParameter("OPPONENTNAME");//交易对方名称
		String OPPONENTACCOUNTNUMBER = request.getParameter("OPPONENTACCOUNTNUMBER");//交易对方账卡号
		String OPPONENTCREDENTIALNUMBER = request.getParameter("OPPONENTCREDENTIALNUMBER");//交易对方证件号码
		String OPPONENTDEPOSITBANKID = request.getParameter("OPPONENTDEPOSITBANKID");//交易对方账号开户行
		String TRANSACTIONREMARK = request.getParameter("TRANSACTIONREMARK");//交易摘要
		String TRANSACTIONBRANCHNAME = request.getParameter("TRANSACTIONBRANCHNAME");//交易网点名称
		String TRANSACTIONBRANCHCODE = request.getParameter("TRANSACTIONBRANCHCODE");//交易网点代码
		String LOGNUMBER = request.getParameter("LOGNUMBER");//日志号
		String SUMMONSNUMBER = request.getParameter("SUMMONSNUMBER");//传票号
		
		String VOUCHERTYPE = request.getParameter("VOUCHERTYPE");//凭证种类
		String VOUCHERCODE = request.getParameter("VOUCHERCODE");//凭证号
		String CASHMARK = request.getParameter("CASHMARK");//现金标志
		String TERMINALNUMBER = request.getParameter("TERMINALNUMBER");//终端号
		String TRANSACTIONSTATUS = request.getParameter("TRANSACTIONSTATUS");//交易是否成功
		String TRANSACTIONADDRESS = request.getParameter("TRANSACTIONADDRESS");//交易发生地
		String MERCHANTNAME = request.getParameter("MERCHANTNAME");//商户名称
		String MERCHANTCODE = request.getParameter("MERCHANTCODE");//商户号
		String IPADDRESS = request.getParameter("IPADDRESS");//IP地址
		String MAC = request.getParameter("MAC");//MAC地址
		String TELLERCODE = request.getParameter("TELLERCODE");//交易柜员号
		String REPORTORGNAME = request.getParameter("REPORTORGNAME");//上报机构名称
		String OPERATORNAME = request.getParameter("OPERATORNAME");//经办人姓名
		String OPERATORPHONENUMBER = request.getParameter("OPERATORPHONENUMBER");//经办人电话
		String REMARK = request.getParameter("REMARK");//备注
		
		String transSerialNumber = StringUtil.getTransSerialNumber();//传输报文流水号
		//String newTransSerialNumber = StringUtil.getTransSerialNumber();//传输报文流水号
		String applicationId = StringUtil.getApplicationID();//业务申请编号
		
		tx100404.setTransSerialNumber(transSerialNumber);
		tx100404.setTxCode("100404");
		tx100404.setMessageFrom("111111000000");
		tx100404.setApplicationID(applicationId);
        tx100404.setFeatureCode(FEATURECODE);
        tx100404.setBankID("402361018886");
        tx100404.setCardNumber(CARDNUMBER);
        tx100404.setAccountName(ACCOUNTNAME);
        tx100404.setIdType(IDTYPE);
        tx100404.setIdNumber(IDNUMBER);
        tx100404.setPhoneNumber(PHONENUMBER);
        tx100404.setAddress(ADDRESS);
        tx100404.setPostCode(POSTCODE);
        tx100404.setAccountOpenPlace(ACCOUNTOPENPLACE);

        List<TxInvolvedAccount_Account> accountList = new ArrayList<TxInvolvedAccount_Account>();
        TxInvolvedAccount_Account txInvolvedAccount_Account = new TxInvolvedAccount_Account();
        txInvolvedAccount_Account.setAccountNumber(ACCOUNTNAME);
        txInvolvedAccount_Account.setAccountType(ACCOUNTTYPE);
        txInvolvedAccount_Account.setAccountStatus(ACCOUNTSTATUS);
        txInvolvedAccount_Account.setCurrency(CURRENCY);
        txInvolvedAccount_Account.setCashRemit(CASHREMIT);

        List<Transaction> transactionList = new ArrayList<Transaction>();

        Transaction transaction = new Transaction();
        transaction.setTransactionType(TRANSACTIONTYPE);
        transaction.setBorrowingSigns(BORROWINGSIGNS);
        transaction.setCurrency(CURRENCY);
        transaction.setTransactionAmount(TRANSACTIONAMOUNT);
        transaction.setAccountBalance(ACCOUNTBALANCE);
        transaction.setTransactionTime(TRANSACTIONTIME);
        transaction.setTransactionSerial(TRANSACTIONSERIAL);
        transaction.setOpponentName(OPPONENTNAME);
        transaction.setOpponentAccountNumber(OPPONENTACCOUNTNUMBER);
        transaction.setOpponentCredentialNumber(OPPONENTCREDENTIALNUMBER);
        transaction.setOpponentDepositBankID(OPPONENTDEPOSITBANKID);
        transaction.setTransactionRemark(TRANSACTIONREMARK);
        transaction.setTransactionBranchName(TRANSACTIONBRANCHNAME);
        transaction.setTransactionBranchCode(TRANSACTIONBRANCHCODE);
        transaction.setLogNumber(LOGNUMBER);
        transaction.setSummonsNumber(SUMMONSNUMBER);
        transaction.setVoucherType(VOUCHERTYPE);
        transaction.setVoucherCode(VOUCHERCODE);
        transaction.setCashMark(CASHMARK);
        transaction.setTerminalNumber(TERMINALNUMBER);
        transaction.setTransactionStatus(TRANSACTIONSTATUS);
        transaction.setTransactionAddress(TRANSACTIONADDRESS);
        transaction.setMerchantName(MERCHANTNAME);
        transaction.setMerchantCode(MERCHANTCODE);
        transaction.setIpAddress(IPADDRESS);
        transaction.setMac(MAC);
        transaction.setTellerCode(TELLERCODE);
        transaction.setRemark(REMARK);

        transactionList.add(transaction);
        txInvolvedAccount_Account.setTransactionList(transactionList);

        accountList.add(txInvolvedAccount_Account);
        tx100404.setAccountList(accountList);
        tx100404.setReportOrgName(REPORTORGNAME);
        tx100404.setOperatorName(OPERATORNAME);
        tx100404.setOperatorPhoneNumber(OPERATORPHONENUMBER);
        
		Map params = new HashMap();
		params.put("TRANSSERIALNUMBER", transSerialNumber);
		params.put("JSJG", "111111000000");
		params.put("FROMTGORGANIZATIONID", "");
		params.put("TXCODE", "100404");
		params.put("APPLICATIONID", applicationId);
		params.put("FEATURECODE", FEATURECODE);
		params.put("BANKID", "402361018886");
		params.put("CARDNUMBER", CARDNUMBER);
		params.put("ACCOUNTNAME", ACCOUNTNAME);
		params.put("IDTYPE", IDTYPE);
		params.put("IDNUMBER", IDNUMBER);
		params.put("PHONENUMBER", PHONENUMBER);
		params.put("ADDRESS", ADDRESS);
		params.put("POSTCODE", POSTCODE);
		params.put("ACCOUNTOPENPLACE", ACCOUNTOPENPLACE);
		params.put("ACCOUNTNUMBER", ACCOUNTNUMBER);
		params.put("ACCOUNTSERIAL", ACCOUNTSERIAL);
		params.put("ACCOUNTTYPE", ACCOUNTTYPE);
		params.put("ACCOUNTSTATUS", ACCOUNTSTATUS);
		params.put("CURRENCY", CURRENCY);
		params.put("CASHREMIT", CASHREMIT);
		params.put("TRANSACTIONTYPE", TRANSACTIONTYPE);
		params.put("BORROWINGSIGNS", BORROWINGSIGNS);
		params.put("TRANSACTIONAMOUNT", TRANSACTIONAMOUNT);
		params.put("ACCOUNTBALANCE", ACCOUNTBALANCE);
		params.put("TRANSACTIONTIME", TRANSACTIONTIME);
		params.put("TRANSACTIONSERIAL", TRANSACTIONSERIAL);
		params.put("OPPONENTNAME", OPPONENTNAME);
		params.put("OPPONENTACCOUNTNUMBER", OPPONENTACCOUNTNUMBER);
		params.put("OPPONENTCREDENTIALNUMBER", OPPONENTCREDENTIALNUMBER);
		params.put("OPPONENTDEPOSITBANKID", OPPONENTDEPOSITBANKID);
		params.put("TRANSACTIONREMARK", TRANSACTIONREMARK);
		params.put("TRANSACTIONBRANCHNAME", TRANSACTIONBRANCHNAME);
		params.put("TRANSACTIONBRANCHCODE", TRANSACTIONBRANCHCODE);
		params.put("LOGNUMBER", LOGNUMBER);
		params.put("SUMMONSNUMBER", SUMMONSNUMBER);
		params.put("VOUCHERTYPE", VOUCHERTYPE);
		params.put("VOUCHERCODE", VOUCHERCODE);
		params.put("CASHMARK", CASHMARK);
		params.put("TERMINALNUMBER", TERMINALNUMBER);
		params.put("TRANSACTIONSTATUS", TRANSACTIONSTATUS);
		params.put("TRANSACTIONADDRESS", TRANSACTIONADDRESS);
		params.put("MERCHANTNAME", MERCHANTNAME);
		params.put("MERCHANTCODE", MERCHANTCODE);
		params.put("IPADDRESS", IPADDRESS);
		params.put("MAC", MAC);
		params.put("TELLERCODE", TELLERCODE);
		params.put("REMARK", REMARK);
		params.put("REPORTORGNAME", REPORTORGNAME);
		params.put("REMARK", REMARK);
		params.put("REPORTORGNAME", "安徽省农村信用联合社");
		params.put("OPERATORNAME", OPERATORNAME);
		params.put("OPERATORPHONENUMBER", OPERATORPHONENUMBER);
		params.put("STATUS", "");
		
		String requestXML= "";
		//String requestXML = sgBusiness.tx100404(tx100404,fromTGOrganizationId);
		try {
			ClientEnvironment.initTxClientEnvironment("../cfca");
		    SGBusiness sgBusiness = new SGBusiness();
			requestXML = sgBusiness.tx100404(tx100404, tx100404.getMessageFrom());
			
			String responseXML = sgBusiness.sendPackagedRequestXML(requestXML);
	        Result result = ResultUtil.chageXMLToResult(responseXML);
	        
	        if (Constants.SUCCESS_CODE_VALUE.equals(result.getCode())) {//成功
	        	//params.put("NEWTRANSSERIALNUMBER", newTransSerialNumber);
	        	//params.put("CODE", "100000");//返回码
	        	params.put("ERRORINFO", "");//返回消息
	        	
            }else{//失败
            	//params.put("NEWTRANSSERIALNUMBER", newTransSerialNumber);
            	//params.put("CODE", "111111");//返回码失败
            	params.put("ERRORINFO", result.getDescription());//返回消息
            }
	        
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		zfdjService.insertSazh(params);
		request.setAttribute("msg", "发送成功！");
		return mapping.findForward(SUCCESS);
	}
	
	
	/**
	 * 异常事件可疑名单上报页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toSendEvent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		return mapping.findForward("toSendEvent");
	}
	
	/**
	 * 异常事件可疑名单上报方法
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward sendEvent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Tx100405 tx100405 = new Tx100405();
		
		String ACCOUNTNAME = request.getParameter("ACCOUNTNAME");//主账户名称
		String CARDNUMBER = request.getParameter("CARDNUMBER");//主账户
		String ACCOUNTNUMBER = request.getParameter("ACCOUNTNUMBER");//定位账户账号
		String ACCOUNTSERIAL = request.getParameter("ACCOUNTSERIAL");//一般(子)账户序号
		String ACCOUNTTYPE = request.getParameter("ACCOUNTTYPE");//一般(子)账户类别
		String ACCOUNTSTATUS = request.getParameter("ACCOUNTSTATUS");//账户状态
		String CURRENCY = request.getParameter("CURRENCY");//币种
		String CASHREMIT = request.getParameter("CASHREMIT");//钞汇标志
		String FEATURECODE = request.getParameter("FEATURECODE");//事件特征码
		String TRANSACTIONTYPE = request.getParameter("TRANSACTIONTYPE");//交易类型
		String BORROWINGSIGNS = request.getParameter("BORROWINGSIGNS");//借贷标志
		String TRANSACTIONAMOUNT = request.getParameter("TRANSACTIONAMOUNT");//交易金额
		String ACCOUNTBALANCE = request.getParameter("ACCOUNTBALANCE");//交易余额
		String TRANSACTIONTIME = request.getParameter("TRANSACTIONTIME");//交易时间
		String TRANSACTIONSERIAL = request.getParameter("TRANSACTIONSERIAL");//交易流水号
		String OPPONENTNAME = request.getParameter("OPPONENTNAME");//交易对方名称
		String OPPONENTACCOUNTNUMBER = request.getParameter("OPPONENTACCOUNTNUMBER");//交易对方账卡号
		String OPPONENTCREDENTIALNUMBER = request.getParameter("OPPONENTCREDENTIALNUMBER");//交易对方证件号码
		String OPPONENTDEPOSITBANKID = request.getParameter("OPPONENTDEPOSITBANKID");//交易对方账号开户行
		String TRANSACTIONREMARK = request.getParameter("TRANSACTIONREMARK");//交易摘要
		String TRANSACTIONBRANCHNAME = request.getParameter("TRANSACTIONBRANCHNAME");//交易网点名称
		String TRANSACTIONBRANCHCODE = request.getParameter("TRANSACTIONBRANCHCODE");//交易网点代码
		String LOGNUMBER = request.getParameter("LOGNUMBER");//日志号
		String SUMMONSNUMBER = request.getParameter("SUMMONSNUMBER");//传票号
		String VOUCHERTYPE = request.getParameter("VOUCHERTYPE");//凭证种类
		String VOUCHERCODE = request.getParameter("VOUCHERCODE");//凭证号
		String CASHMARK = request.getParameter("CASHMARK");//现金标志
		String TERMINALNUMBER = request.getParameter("TERMINALNUMBER");//终端号
		String TRANSACTIONSTATUS = request.getParameter("TRANSACTIONSTATUS");//交易是否成功
		String TRANSACTIONADDRESS = request.getParameter("TRANSACTIONADDRESS");//交易发生地
		String MERCHANTNAME = request.getParameter("MERCHANTNAME");//商户名称
		String MERCHANTCODE = request.getParameter("MERCHANTCODE");//商户号
		String IPADDRESS = request.getParameter("IPADDRESS");//IP地址
		String MAC = request.getParameter("MAC");//MAC地址
		String TELLERCODE = request.getParameter("TELLERCODE");//交易柜员号
		String REMARK = request.getParameter("REMARK");//备注
		String OPERATORNAME = request.getParameter("OPERATORNAME");//经办人姓名
		String OPERATORPHONENUMBER = request.getParameter("OPERATORPHONENUMBER");//经办人电话
		
		String transSerialNumber = StringUtil.getTransSerialNumber();//传输报文流水号
		//String newTransSerialNumber = StringUtil.getTransSerialNumber();//传输报文流水号
		String applicationId = StringUtil.getApplicationID();//业务申请编号
		
		tx100405.setTransSerialNumber(transSerialNumber);
        tx100405.setApplicationID(applicationId);
        tx100405.setBankID("402361018886");
        tx100405.setOperatorName(OPERATORNAME);
        tx100405.setOperatorPhoneNumber(OPERATORPHONENUMBER);

        List<TxExceptionalEvent_Accounts> accountsList = new ArrayList<TxExceptionalEvent_Accounts>();
        TxExceptionalEvent_Accounts txExceptionalEvent_Accounts = new TxExceptionalEvent_Accounts();
        txExceptionalEvent_Accounts.setAccountName(ACCOUNTNAME);
        txExceptionalEvent_Accounts.setCardNumber(CARDNUMBER);
        txExceptionalEvent_Accounts.setRemark(REMARK);

        TxExceptionalEvent_Account txExceptionalEvent_Account = new TxExceptionalEvent_Account();
        txExceptionalEvent_Account.setAccountNumber(ACCOUNTNUMBER);
        txExceptionalEvent_Account.setAccountType(ACCOUNTTYPE);
        txExceptionalEvent_Account.setAccountStatus(ACCOUNTSTATUS);
        txExceptionalEvent_Account.setCurrency(CURRENCY);
        txExceptionalEvent_Account.setCashRemit(CASHREMIT);

        List<TxExceptionalEvent_Transaction> transactionList = new ArrayList<TxExceptionalEvent_Transaction>();

        TxExceptionalEvent_Transaction transaction = new TxExceptionalEvent_Transaction();
        transaction.setFeatureCode(FEATURECODE); //事件特征码
        transaction.setTransactionType(TRANSACTIONTYPE);
        transaction.setBorrowingSigns(BORROWINGSIGNS);
        transaction.setCurrency(CURRENCY);
        transaction.setTransactionAmount(TRANSACTIONAMOUNT);
        transaction.setAccountBalance(ACCOUNTBALANCE);
        transaction.setTransactionTime(TRANSACTIONTIME);
        transaction.setTransactionSerial(TRANSACTIONSERIAL);
        transaction.setOpponentName(OPPONENTNAME);
        transaction.setOpponentAccountNumber(OPPONENTACCOUNTNUMBER);
        transaction.setOpponentCredentialNumber(OPPONENTCREDENTIALNUMBER);
        transaction.setOpponentDepositBankID(OPPONENTDEPOSITBANKID);
        transaction.setTransactionRemark(TRANSACTIONREMARK);
        transaction.setTransactionBranchName(TRANSACTIONBRANCHNAME);
        transaction.setTransactionBranchCode(TRANSACTIONBRANCHCODE);
        transaction.setLogNumber(LOGNUMBER);
        transaction.setSummonsNumber(SUMMONSNUMBER);
        transaction.setVoucherType(VOUCHERTYPE);
        transaction.setVoucherCode(VOUCHERCODE);
        transaction.setCashMark(CASHMARK);
        transaction.setTerminalNumber(TERMINALNUMBER);
        transaction.setTransactionStatus(TRANSACTIONSTATUS);
        transaction.setTransactionAddress(TRANSACTIONADDRESS);
        transaction.setMerchantName(MERCHANTNAME);
        transaction.setMerchantCode(MERCHANTCODE);
        transaction.setIpAddress(IPADDRESS);
        transaction.setMac(MAC);
        transaction.setTellerCode(TELLERCODE);
        transaction.setRemark(REMARK);

        transactionList.add(transaction);
        txExceptionalEvent_Account.setTransactionList(transactionList);

        List<TxExceptionalEvent_Account> txInvolvedAccount_AccountList = new ArrayList<TxExceptionalEvent_Account>();
        txInvolvedAccount_AccountList.add(txExceptionalEvent_Account);
        
        txExceptionalEvent_Accounts.setAccountList(txInvolvedAccount_AccountList);

        accountsList.add(txExceptionalEvent_Accounts);
        
        tx100405.setAccountsList(accountsList);
		
		Map params = new HashMap();
		params.put("TRANSSERIALNUMBER", transSerialNumber);
		params.put("JSJG", "111111000000");
		params.put("FROMTGORGANIZATIONID", "");
		params.put("TXCODE", "100405");
		params.put("APPLICATIONID", applicationId);
		params.put("BANKID", "402361018886");
		params.put("OPERATORNAME", OPERATORNAME);
		params.put("OPERATORPHONENUMBER", OPERATORPHONENUMBER);
		params.put("ACCOUNTNAME", ACCOUNTNAME);
		params.put("CARDNUMBER", CARDNUMBER);
		params.put("REMARK", REMARK);
		params.put("ACCOUNTNUMBER", ACCOUNTNUMBER);
		params.put("ACCOUNTSERIAL", ACCOUNTSERIAL);
		params.put("ACCOUNTTYPE", ACCOUNTTYPE);
		params.put("ACCOUNTSTATUS", ACCOUNTSTATUS);
		params.put("CURRENCY", CURRENCY);
		params.put("CASHREMIT", CASHREMIT);
		params.put("FEATURECODE", FEATURECODE);
		params.put("TRANSACTIONTYPE", TRANSACTIONTYPE);
		params.put("BORROWINGSIGNS", BORROWINGSIGNS);
		params.put("TRANSACTIONAMOUNT", TRANSACTIONAMOUNT);
		params.put("ACCOUNTBALANCE", ACCOUNTBALANCE);
		params.put("TRANSACTIONTIME", TRANSACTIONTIME);
		params.put("TRANSACTIONSERIAL", TRANSACTIONSERIAL);
		params.put("OPPONENTNAME", OPPONENTNAME);
		params.put("OPPONENTACCOUNTNUMBER", OPPONENTACCOUNTNUMBER);
		params.put("OPPONENTCREDENTIALNUMBER", OPPONENTCREDENTIALNUMBER);
		
		params.put("OPPONENTDEPOSITBANKID", OPPONENTDEPOSITBANKID);
		params.put("TRANSACTIONREMARK", TRANSACTIONREMARK);
		params.put("TRANSACTIONBRANCHNAME", TRANSACTIONBRANCHNAME);
		params.put("TRANSACTIONBRANCHCODE", TRANSACTIONBRANCHCODE);
		params.put("LOGNUMBER", LOGNUMBER);
		params.put("SUMMONSNUMBER", SUMMONSNUMBER);
		params.put("VOUCHERTYPE", VOUCHERTYPE);
		params.put("VOUCHERCODE", VOUCHERCODE);
		params.put("CASHMARK", CASHMARK);
		params.put("TERMINALNUMBER", TERMINALNUMBER);
		params.put("TRANSACTIONSTATUS", TRANSACTIONSTATUS);
		params.put("TRANSACTIONADDRESS", TRANSACTIONADDRESS);
		params.put("MERCHANTNAME", MERCHANTNAME);
		params.put("MERCHANTCODE", MERCHANTCODE);
		params.put("IPADDRESS", IPADDRESS);
		params.put("MAC", MAC);
		params.put("TELLERCODE", TELLERCODE);
		params.put("STATUS", "0");
		
		String requestXML= "";
		//String requestXML = sgBusiness.tx100405(tx100405, fromTGOrganizationId);
		try {
			ClientEnvironment.initTxClientEnvironment("../cfca");
		    SGBusiness sgBusiness = new SGBusiness();
			requestXML = sgBusiness.tx100405(tx100405, tx100405.getMessageFrom());
			
			String responseXML = sgBusiness.sendPackagedRequestXML(requestXML);
	        Result result = ResultUtil.chageXMLToResult(responseXML);
	        
	        if (Constants.SUCCESS_CODE_VALUE.equals(result.getCode())) {//成功
	        	//params.put("NEWTRANSSERIALNUMBER", newTransSerialNumber);
	        	//params.put("CODE", "100000");//返回码
	        	params.put("ERRORINFO", "");;//返回消息
	        	
            }else{//失败
            	//params.put("NEWTRANSSERIALNUMBER", newTransSerialNumber);
            	//params.put("CODE", "111111");//返回码失败
            	params.put("ERRORINFO", result.getDescription());
            }
	        
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		zfdjService.insertYcsj(params);
		request.setAttribute("msg", "发送成功！");
		return mapping.findForward(SUCCESS);
	}
	
	
	/**
	 * 案件举报页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toAjjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		return mapping.findForward("toAjjb");
	}
	
	/**
	 * 案件举报上报方法
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward ajjb(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Tx100401 tx100401 = new Tx100401();
		
		String APPLICATIONTYPE = request.getParameter("APPLICATIONTYPE");//案件举报类型
		String ISINITIALNODE = request.getParameter("ISINITIALNODE");//是否主动发送初始节点
		String REPORTENDTIME = request.getParameter("REPORTENDTIME");//上报时间
		String VICTIMNAME = request.getParameter("VICTIMNAME");//受害人姓名
		String VICTIMPHONENUMBER = request.getParameter("VICTIMPHONENUMBER");//受害人联系电话
		String VICTIMIDTYPE = request.getParameter("VICTIMIDTYPE");//证件类型
		String VICTIMIDNUMBER = request.getParameter("VICTIMIDNUMBER");//证件号
		String ACCIDENTDESCRIPTION = request.getParameter("ACCIDENTDESCRIPTION");//事件描述
		String REPORTORGNAME = request.getParameter("REPORTORGNAME");//上报机构名称
		String OPERATORNAME = request.getParameter("OPERATORNAME");//经办人姓名
		String OPERATORPHONENUMBER = request.getParameter("OPERATORPHONENUMBER");//经办人电话
		String ID = request.getParameter("ID");//交易流水号
		String TYPE = request.getParameter("TYPE");//交易类型
		String TIME = request.getParameter("TIME");//交易时间
		String CURRENCY = request.getParameter("CURRENCY");//币种
		String TRANSFERAMOUNT = request.getParameter("TRANSFERAMOUNT");//交易金额
		String TRANSFEROUTBANKID = request.getParameter("TRANSFEROUTBANKID");//转出账户所属银行机构编码
		String TRANSFEROUTBANKNAME = request.getParameter("TRANSFEROUTBANKNAME");//转出账户所属银行名称
		String TRANSFEROUTACCOUNTNAME = request.getParameter("TRANSFEROUTACCOUNTNAME");//转出账户名
		String TRANSFEROUTACCOUNTNUMBER = request.getParameter("TRANSFEROUTACCOUNTNUMBER");//转出卡/折号
		String TRANSFERINBANKID = request.getParameter("TRANSFERINBANKID");//转入账户所属银行机构编码
		String TRANSFERINBANKNAME = request.getParameter("TRANSFERINBANKNAME");//转入账户账户所属银行名称
		String TRANSFERINACCOUNTNAME = request.getParameter("TRANSFERINACCOUNTNAME");//转入账户账户名
		String TRANSFERINACCOUNTNUMBER = request.getParameter("TRANSFERINACCOUNTNUMBER");//转入账户卡/折号
		String IP = request.getParameter("IP");//IP地址
		String MAC = request.getParameter("MAC");//MAC地址
		String DEVICEID = request.getParameter("DEVICEID");//设备ID
		String PLACE = request.getParameter("PLACE");//交易地点
		String REMARK = request.getParameter("REMARK");//备注
		String ISCEASED = request.getParameter("ISCEASED");//是否已止付
		
		String transSerialNumber = StringUtil.getTransSerialNumber();//传输报文流水号
		//String newTransSerialNumber = StringUtil.getTransSerialNumber();//传输报文流水号
		String applicationId = StringUtil.getApplicationID();//业务申请编号
		
		tx100401.setTransSerialNumber(transSerialNumber);
        tx100401.setApplicationID(applicationId);
        tx100401.setApplicationType(APPLICATIONTYPE);
        tx100401.setReportEndTime(REPORTENDTIME);
        tx100401.setVictimName(VICTIMNAME);
        tx100401.setVictimPhoneNumber(VICTIMPHONENUMBER);
        tx100401.setVictimIDType(VICTIMIDTYPE);
        tx100401.setVictimIDNumber(VICTIMIDNUMBER);
        tx100401.setAccidentDescription(ACCIDENTDESCRIPTION);
        tx100401.setReportOrgName(REPORTORGNAME);
        tx100401.setOperatorName(OPERATORNAME);
        tx100401.setOperatorPhoneNumber(OPERATORPHONENUMBER);
        List<TxCaseReport_Transaction> list = new ArrayList<TxCaseReport_Transaction>();

        TxCaseReport_Transaction txCaseReport_Transaction = new TxCaseReport_Transaction();
        txCaseReport_Transaction.setId(ID);
        txCaseReport_Transaction.setTime(TIME);
        txCaseReport_Transaction.setType(TYPE);
        txCaseReport_Transaction.setCurrency(CURRENCY);
        txCaseReport_Transaction.setTransferAmount(TRANSFERAMOUNT);

        txCaseReport_Transaction.setTransferOutBankID(TRANSFEROUTBANKID);
        txCaseReport_Transaction.setTransferOutBankName(TRANSFEROUTBANKNAME);
        txCaseReport_Transaction.setTransferOutAccountName(TRANSFEROUTACCOUNTNAME);
        txCaseReport_Transaction.setTransferOutAccountNumber(TRANSFEROUTACCOUNTNUMBER);

        txCaseReport_Transaction.setTransferInBankID(TRANSFERINBANKID);
        txCaseReport_Transaction.setTransferInBankName(TRANSFERINBANKNAME);
        //txCaseReport_Transaction.setTransferInAccountName(TRANSFERINACCOUNTNAME + System.currentTimeMillis());
        txCaseReport_Transaction.setTransferInAccountName(TRANSFERINACCOUNTNAME);
        txCaseReport_Transaction.setTransferInAccountNumber(TRANSFERINACCOUNTNUMBER);

        txCaseReport_Transaction.setIp(IP);
        txCaseReport_Transaction.setMac(MAC);
        txCaseReport_Transaction.setDeviceID(DEVICEID);
        txCaseReport_Transaction.setPlace(PLACE);
        txCaseReport_Transaction.setRemark(REMARK);
        txCaseReport_Transaction.setIsCeased(ISCEASED);

        list.add(txCaseReport_Transaction);
        tx100401.setTransactionList(list);

        List<Attachment> attachmentList = new ArrayList<Attachment>();
//        Attachment attachment = new Attachment();
//        attachment.setContent("");
//        attachment.setFilename("heh.jpg");
//        attachmentList.add(attachment);
//        tx100401.setAttachmentList(attachmentList);
		
		Map params = new HashMap();
		
		params.put("TRANSSERIALNUMBER", transSerialNumber);
		params.put("JSJG", "111111000000");//402361018886
		params.put("TXCODE", "100401");
		params.put("ISINITIALNODE", ISINITIALNODE);
		params.put("FROMTGORGANIZATIONID", "");
		params.put("APPLICATIONID", applicationId);
		params.put("APPLICATIONTYPE", APPLICATIONTYPE);
		params.put("REPORTENDTIME", REPORTENDTIME);
		params.put("VICTIMNAME", VICTIMNAME);
		params.put("VICTIMPHONENUMBER", VICTIMPHONENUMBER);
		params.put("VICTIMIDTYPE", VICTIMIDTYPE);
		params.put("VICTIMIDNUMBER", VICTIMIDNUMBER);
		params.put("ACCIDENTDESCRIPTION", ACCIDENTDESCRIPTION);
		params.put("REPORTORGNAME", REPORTORGNAME);
		params.put("OPERATORNAME", OPERATORNAME);
		params.put("OPERATORPHONENUMBER", OPERATORPHONENUMBER);
		params.put("ID", ID);
		params.put("TIME", TIME);
		params.put("TYPE", TYPE);
		params.put("CURRENCY", CURRENCY);
		params.put("TRANSFERAMOUNT", TRANSFERAMOUNT);
		params.put("TRANSFEROUTBANKID", TRANSFEROUTBANKID);
		params.put("TRANSFEROUTBANKNAME", TRANSFEROUTBANKNAME);
		params.put("TRANSFEROUTACCOUNTNAME", TRANSFEROUTACCOUNTNAME);
		params.put("TRANSFEROUTACCOUNTNUMBER", TRANSFEROUTACCOUNTNUMBER);
		params.put("TRANSFERINBANKID", TRANSFERINBANKID);
		params.put("TRANSFERINBANKNAME", TRANSFERINBANKNAME);
		params.put("TRANSFERINACCOUNTNAME", TRANSFERINACCOUNTNAME);
		params.put("TRANSFERINACCOUNTNUMBER", TRANSFERINACCOUNTNUMBER);
		params.put("IP", IP);
		params.put("MAC", MAC);
		params.put("DEVICEID", DEVICEID);
		params.put("PLACE", PLACE);
		params.put("REMARK", REMARK);
		params.put("ISCEASED", ISCEASED);
		params.put("STATUS", "0");
		
		String requestXML= "";
		//String requestXML = sgBusiness.tx100401(tx100401, fromTGOrganizationId, mode, to);
		try {
			ClientEnvironment.initTxClientEnvironment("../cfca");
		    SGBusiness sgBusiness = new SGBusiness();
			requestXML = sgBusiness.tx100401(tx100401, tx100401.getMessageFrom(), "01", tx100401.getMessageFrom());
			
			String responseXML = sgBusiness.sendPackagedRequestXML(requestXML);
	        Result result = ResultUtil.chageXMLToResult(responseXML);
	        
	        if (Constants.SUCCESS_CODE_VALUE.equals(result.getCode())) {//成功
	        	//params.put("CODE", "100000");//返回码
	        	params.put("ERRORINFO", "");;//返回消息
	        	
            }else{//失败
            	//params.put("CODE", "111111");//返回码失败
            	params.put("ERRORINFO", result.getDescription());
            }
	        
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		zfdjService.insertAjjb(params);
		request.setAttribute("msg", "发送成功！");
		return mapping.findForward(SUCCESS);
	}
	

}

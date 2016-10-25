package com.yitong.app.action.judicial.jcysfck;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
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
import com.yitong.app.service.judicial.jcysfck.SfckService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.model.Properties;
import com.yitong.commons.model.UserSession;
import com.yitong.commons.util.ParamUtil;

/**
 * 批量查询相关
 * 
 * @author db2admin
 * 
 */
public class SfckAction extends BaseAction {
	protected static final Logger logger = Logger.getLogger(SfckAction.class);

	private SfckService sfckService;
	
	
	public SfckService getSfckService() {
		return sfckService;
	}

	public void setSfckService(SfckService sfckService) {
		this.sfckService = sfckService;
	}

	/**
	 * 司法查控：设置查询模板列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toCxmbList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "CXMB_NAME", params);
		ParamUtil.putStr2Map(request, "CXMB_STU", params);
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = sfckService.pageQueryCxmb(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("cxmb");
	}
	
	/**
	 * 司法查控：跳转添加查询模板
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toAddCxmb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		
		return mapping.findForward("addCxmb");
	}
	
	/**
	 * 司法查控：添加查询模板
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward addCxmb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();		
		ParamUtil.putStr2Map(request, "CXMB_NAME", params);
		ParamUtil.putStr2Map(request, "CXMB_DEPT", params);
		ParamUtil.putStr2Map(request, "CXMB_DESC", params);
		
		UserSession sess = getUserSession(request);
		String user = sess.getUserId();
		String org = sess.getOrgName();
		params.put("user", user);
		params.put("org", org);
		sfckService.addCxmb(params);
		request.setAttribute("msg", "增加成功！");
		return mapping.findForward(SUCCESS);
	}
	
	/**
	 * 司法查控：跳转查询模板修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toUpdateCxmb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		String CXMB_ID = ParamUtil.get(request, "CXMB_ID");
		Map res = sfckService.loadCxmb(CXMB_ID);
		request.setAttribute("res", res);
		return mapping.findForward("modiCxmb");
	}
	
	/**
	 * 司法查控：模板修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward updateCxmb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "CXMB_ID", params);
		ParamUtil.putStr2Map(request, "CXMB_NAME", params);
		ParamUtil.putStr2Map(request, "CXMB_DEPT", params);
		ParamUtil.putStr2Map(request, "CXMB_DESC", params);
		sfckService.updateCxmb(params);
		request.setAttribute("msg", "查询模板修改成功!");
		return mapping.findForward(SUCCESS);
	}
	
	/**
	 * 司法查控：模板删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward delCxmb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String CXMB_ID =ParamUtil.get(request, "CXMB_ID");
		sfckService.delCxmb(CXMB_ID);
		request.setAttribute("msg", "查询模板删除成功!");
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
		String CXMB_ID = ParamUtil.get(request, "CXMB_ID");
		if (CXMB_ID.equals("") || CXMB_ID == null) {
			request.setAttribute("msg", "查询模板编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "CXMB_ID", params);
		params.put("CXMB_STU", "1");
		sfckService.updateStu(params);
		request.setAttribute("msg", "启用成功！");
		
		return mapping.findForward(SUCCESS);
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
		String CXMB_ID = ParamUtil.get(request, "CXMB_ID");
		if (CXMB_ID.equals("") || CXMB_ID == null) {
			request.setAttribute("msg", "查询模板编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "CXMB_ID", params);
		params.put("CXMB_STU", "0");
		sfckService.updateStu(params);
		request.setAttribute("msg", "冻结成功！");
		
		return mapping.findForward(SUCCESS);
	}
	
	/**
	 * 司法查控：设置条件列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toConList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "CONDITION_NAME", params);
		ParamUtil.putStr2Map(request, "CXMB_ID", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = sfckService.pageQueryCon(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("tjsz");
	}
	
	/**
	 * 司法查控：跳转到新增条件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toAddCon(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		return mapping.findForward("addCon");
	}
	
	/**
	 * 司法查控：增加条件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward addCon(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();	
		
		String PHYSICAL_NAME = ParamUtil.get(request, "PHYSICAL_NAME");
		params.put("PHYSICAL_NAME", PHYSICAL_NAME.toUpperCase());
		
		ParamUtil.putStr2Map(request, "CXMB_ID", params);
		ParamUtil.putStr2Map(request, "CONDITION_NAME", params);
		ParamUtil.putStr2Map(request, "INPUT_TYPE", params);
		ParamUtil.putStr2Map(request, "VALUE_TYPE", params);
		ParamUtil.putStr2Map(request, "VALUE_DEF", params);
		ParamUtil.putStr2Map(request, "CONDITION_DESC", params);
		
		
		if(!sfckService.checkCon(params)){
			request.setAttribute("msg", "增加失败,该物理名已存在不可重复增加!");
			return mapping.findForward(FAILURE);
		}
		sfckService.addCon(params);
		request.setAttribute("msg", "查询条件增加成功！");
		return mapping.findForward(SUCCESS);
	}
	
	/**
	 * 司法查控：跳转到条件修改页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public ActionForward toUpdateCon(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "CXMB_ID", params);
		ParamUtil.putStr2Map(request, "CONDITION_ID", params);
		
		Map res = sfckService.loadCon(params);
		request.setAttribute("res", res);
		return mapping.findForward("modiCon");
	}
	
	/**
	 * 司法查控：修改条件
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public ActionForward modiCon(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		
		ParamUtil.putStr2Map(request, "CXMB_ID", params);
		ParamUtil.putStr2Map(request, "CONDITION_ID", params);
		ParamUtil.putStr2Map(request, "CONDITION_NAME", params);
		ParamUtil.putStr2Map(request, "INPUT_TYPE", params);
		ParamUtil.putStr2Map(request, "VALUE_TYPE", params);
		ParamUtil.putStr2Map(request, "VALUE_DEF", params);
		ParamUtil.putStr2Map(request, "CONDITION_DESC", params);
		
		
		sfckService.updateCon(params);
		request.setAttribute("msg", "查询条件修改成功!");
		return mapping.findForward(SUCCESS);
	}
	
	
	/**
	 * 司法查控：条件删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward delCon(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		String CONDITION_ID = ParamUtil.get(request, "CONDITION_ID");
		sfckService.delCon(CONDITION_ID);
		request.setAttribute("msg", "查询条件删除成功!");
		return mapping.findForward(SUCCESS);
	}
	
	/**
	 * 司法查控：条件规则列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toRulList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "RULE_NAME", params);
		ParamUtil.putStr2Map(request, "CONDITION_ID", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = sfckService.pageQueryRule(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("rule");
	}
	
	
	/**
	 * 司法查控：跳转到新增规则
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toAddRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		return mapping.findForward("addRule");
	}
	
	
	/**
	 * 司法查控：增加规则
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward addRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();	
		String CONDITION_ID = ParamUtil.get(request, "CONDITION_ID");
		String RULE_NAME = ParamUtil.get(request, "RULE_NAME");
		String RULE_VALUE = ParamUtil.get(request, "RULE_VALUE");
		String RULE_DESC = ParamUtil.get(request, "RULE_DESC");
		
		if(sfckService.isExistRule(CONDITION_ID,RULE_NAME)){
			request.setAttribute("msg", "规则增加失败,增加的规则类型在该条件下已存在!");
			return mapping.findForward(FAILURE);
		}
		
		if("1".equals(RULE_NAME)){
			RULE_VALUE = "1";
		}
		if(!this.checkRuleValue(RULE_VALUE,RULE_NAME)){
			request.setAttribute("msg", "规则增加失败,选择项规则值不符合规范,请检查是否是key:value;key:value...形式,以及符号是否是英文符号!");
			return mapping.findForward(FAILURE);
		}
		
		
		params.put("CONDITION_ID", CONDITION_ID);
		params.put("RULE_NAME", RULE_NAME);
		params.put("RULE_VALUE", RULE_VALUE);
		params.put("RULE_DESC", RULE_DESC);
		sfckService.addRule(params);
		request.setAttribute("msg", "条件规则增加成功！");
		return mapping.findForward(SUCCESS);
	}
	
	public boolean checkRuleValue(String ruleValue,String ruleName){
		if("2".equals(ruleName)){
			return ruleValue.contains(":") ;
		}
		return true;
	}
	/**
	 * 司法查控：跳转到规则修改页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public ActionForward toUpdateRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "RULE_ID", params);
		ParamUtil.putStr2Map(request, "CONDITION_ID", params);
		
		Map res = sfckService.loadRule(params);
		request.setAttribute("res", res);
		return mapping.findForward("modiRule");
	}
	
	/**
	 * 司法查控：修改规则
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public ActionForward modiRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		String CONDITION_ID = ParamUtil.get(request, "CONDITION_ID");
		String RULE_ID = ParamUtil.get(request, "RULE_ID");
		String RULE_NAME = ParamUtil.get(request, "RULE_NAME");
		String RULE_VALUE = ParamUtil.get(request, "RULE_VALUE");
		String RULE_DESC = ParamUtil.get(request, "RULE_DESC");
		
		if("1".equals(RULE_NAME)){
			RULE_VALUE = "1";
		}
		if(!this.checkRuleValue(RULE_VALUE,RULE_NAME)){
			request.setAttribute("msg", "规则修改失败,选择项规则值不符合规范,请检查是否是key:value;key:value...形式,以及符号是否是英文符号!");
			return mapping.findForward(FAILURE);
		}
		
		params.put("CONDITION_ID", CONDITION_ID);
		params.put("RULE_NAME", RULE_NAME);
		params.put("RULE_VALUE", RULE_VALUE);
		params.put("RULE_DESC", RULE_DESC);
		params.put("RULE_ID", RULE_ID);
		
		sfckService.updateRule(params);
		request.setAttribute("msg", "查询条件修改成功!");
		return mapping.findForward(SUCCESS);
	}
	

	/**
	 * 司法查控：删除规则
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward delRule(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "RULE_ID", params);
		ParamUtil.putStr2Map(request, "CONDITION_ID", params);
		
		sfckService.delRule(params);
		request.setAttribute("msg", "该条件规则删除成功!");
		return mapping.findForward(SUCCESS);
	}
	
	
	/**
	 * 司法查控：设置查询模板列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toDcmbList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "DCMB_NAME", params);
		ParamUtil.putStr2Map(request, "DCMB_STU", params);
		ParamUtil.putStr2Map(request, "CXMB_ID", params);
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = sfckService.pageQueryDcmb(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("dcmb");
	}
	
	
	/**
	 * 司法查控：跳转添加查询模板
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toAddDcmb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		
		return mapping.findForward("addDcmb");
	}
	
	/**
	 * 司法查控：添加导出模板
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward addDcmb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();		
		params.put("DCMB_TABLE", ParamUtil.get(request, "DCMB_TABLE").toUpperCase()) ;
		ParamUtil.putStr2Map(request, "CXMB_ID", params);
		ParamUtil.putStr2Map(request, "DCMB_NAME", params);
		ParamUtil.putStr2Map(request, "DCMB_DESC", params);
		
		if(!sfckService.checkDcmb(params)){
			request.setAttribute("msg", "增加失败,该对应表已存在不可重复增加!");
			return mapping.findForward(FAILURE);
		}
		sfckService.addDcmb(params);
		request.setAttribute("msg", "增加导出模板成功！");
		return mapping.findForward(SUCCESS);
	}
	
	/**
	 * 司法查控：跳转导出模板修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toUpdateDcmb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		String DCMB_ID = ParamUtil.get(request, "DCMB_ID");
		Map res = sfckService.loadDcmb(DCMB_ID);
		request.setAttribute("res", res);
		return mapping.findForward("modiDcmb");
	}
	
	/**
	 * 司法查控：导出模板修改
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward updateDcmb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();		
		params.put("DCMB_TABLE", ParamUtil.get(request, "DCMB_TABLE").toUpperCase()) ;
		ParamUtil.putStr2Map(request, "CXMB_ID", params);
		ParamUtil.putStr2Map(request, "DCMB_ID", params);
		ParamUtil.putStr2Map(request, "DCMB_NAME", params);
		ParamUtil.putStr2Map(request, "DCMB_DESC", params);
		sfckService.updateDcmb(params);
		request.setAttribute("msg", "导出模板修改成功!");
		return mapping.findForward(SUCCESS);
	}
	
	/**
	 * 司法查控：导出模板删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward delDcmb(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String DCMB_ID =ParamUtil.get(request, "DCMB_ID");
		sfckService.delDcmb(DCMB_ID);
		request.setAttribute("msg", "导出模板删除成功!");
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
	public ActionForward doqyDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String DCMB_ID = ParamUtil.get(request, "DCMB_ID");
		if (DCMB_ID.equals("") || DCMB_ID == null) {
			request.setAttribute("msg", "导出模板编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "DCMB_ID", params);
		params.put("DCMB_STU", "1");
		sfckService.updateStuDc(params);
		request.setAttribute("msg", "启用成功！");
		
		return mapping.findForward(SUCCESS);
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
	public ActionForward dodjDc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String DCMB_ID = ParamUtil.get(request, "DCMB_ID");
		if (DCMB_ID.equals("") || DCMB_ID == null) {
			request.setAttribute("msg", "导出模板编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "DCMB_ID", params);
		params.put("DCMB_STU", "0");
		sfckService.updateStuDc(params);
		request.setAttribute("msg", "冻结成功！");
		
		return mapping.findForward(SUCCESS);
	}
	
	/**
	 * 司法查控：导出字段列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toDczdList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "COL_NAME", params);
		ParamUtil.putStr2Map(request, "DCMB_ID", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = sfckService.pageQueryDczd(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("dczd");
	}
	
	/**
	 * 司法查控：跳转到新增导出字段
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toAddCol(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		return mapping.findForward("addCol");
	}
	
	/**
	 * 司法查控：增加导出字段
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward addCol(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();	
		
		String PHYSICAL_NAME = ParamUtil.get(request, "PHYSICAL_NAME");
		params.put("PHYSICAL_NAME", PHYSICAL_NAME.toUpperCase());
		
		ParamUtil.putStr2Map(request, "COL_NAME", params);
		ParamUtil.putStr2Map(request, "ORDERXH", params);
		ParamUtil.putStr2Map(request, "COLMX_DESC", params);
		ParamUtil.putStr2Map(request, "DCMB_ID", params);
		
		if(!sfckService.checkDczd(params)){
			request.setAttribute("msg", "增加失败,该物理名称已存在不可重复增加!");
			return mapping.findForward(FAILURE);
		}
		
		sfckService.addCol(params);
		request.setAttribute("msg", "导出字段增加成功！");
		return mapping.findForward(SUCCESS);
	}
	
	/**
	 * 司法查控：跳转到导出字段修改页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public ActionForward toUpdateCol(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		String COL_ID = ParamUtil.get(request, "COL_ID");
		Map res = sfckService.loadCol(COL_ID);
		request.setAttribute("res", res);
		return mapping.findForward("modiCol");
	}
	
	/**
	 * 司法查控：修改导出字段
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public ActionForward modiCol(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		String PHYSICAL_NAME = ParamUtil.get(request, "PHYSICAL_NAME");
		params.put("PHYSICAL_NAME", PHYSICAL_NAME.toUpperCase());
		
		ParamUtil.putStr2Map(request, "DCMB_ID", params);
		ParamUtil.putStr2Map(request, "COL_ID", params);
		ParamUtil.putStr2Map(request, "COL_NAME", params);
		ParamUtil.putStr2Map(request, "ORDERXH", params);
		ParamUtil.putStr2Map(request, "DCMB_NAME", params);
		ParamUtil.putStr2Map(request, "COLMX_DESC", params);
		
		sfckService.updateCol(params);
		request.setAttribute("msg", "导出字段修改成功!");
		return mapping.findForward(SUCCESS);
	}
	
	
	/**
	 * 司法查控：导出字段删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward delCol(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String COL_ID = ParamUtil.get(request, "COL_ID");
		
		sfckService.delCol(COL_ID);
		request.setAttribute("msg", "导出字段删除成功!");
		return mapping.findForward(SUCCESS);
	}
	
	
	
	
}

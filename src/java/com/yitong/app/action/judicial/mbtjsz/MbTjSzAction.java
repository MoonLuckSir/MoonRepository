package com.yitong.app.action.judicial.mbtjsz;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yitong.app.service.judicial.mbtjsz.MbTjSzService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.util.ParamUtil;

public class MbTjSzAction extends BaseAction {
	private MbTjSzService mbTjSzService;

	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return mapping.findForward("add");
	}

	/**
	 * 
	 * 增加用户
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
		/*String userId = (String)request.getSession().getAttribute("hisRepUserId");
		
		Date date = DateUtil.getDate();	 
		String today = DateUtil.formatDateToStr(date, "yyyy-MM-dd HH:mm:ss");*/
		
		//得到编号
		String REPMOD_NO = ParamUtil.get(request,"REPMOD_NO");
		String CONMX_NO = mbTjSzService.getSeq(REPMOD_NO);
		System.out.println("conmx_no=="+CONMX_NO);
		params = new HashMap();
		
		params.put("CONMX_NO", CONMX_NO);
		ParamUtil.putStr2Map(request, "CONMX_NAME", params);
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		ParamUtil.putStr2Map(request, "CONMX_TYPE", params);
		ParamUtil.putStr2Map(request, "INPUT_TYPE", params);
		ParamUtil.putStr2Map(request, "INPUT_VALUE", params);
		String  PHYSICAL_NAME=ParamUtil.get(request, "PHYSICAL_NAME");
		if(PHYSICAL_NAME!=null)
			PHYSICAL_NAME=PHYSICAL_NAME.toUpperCase();
		params.put("PHYSICAL_NAME", PHYSICAL_NAME);
		
		ParamUtil.putStr2Map(request, "DEFAULTVAL", params);
		ParamUtil.putStr2Map(request, "OPERATOR", params);	
		ParamUtil.putStr2Map(request, "ORDERXH", params);
		ParamUtil.putStr2Map(request, "CONMX_DESC", params);
		ParamUtil.putStr2Map(request, "ZJCONS", params);
		
		ParamUtil.putStr2Map(request, "ISJOIN", params);
		ParamUtil.putStr2Map(request, "ISFGF", params);
		
		mbTjSzService.insert(params);
		request.setAttribute("msg", "增加成功！");
		return mapping.findForward(SUCCESS);
	}

	//到修改界面
	public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String CONMX_NO = ParamUtil.get(request, "CONMX_NO");
		
		if(CONMX_NO.equals("") || CONMX_NO ==null ){
			request.setAttribute("msg", "条件定制录入编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "CONMX_NO", params);
		Map entry = mbTjSzService.load(CONMX_NO);
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
		String CONMX_NO = ParamUtil.get(request, "CONMX_NO");
		if(CONMX_NO.equals("") || CONMX_NO ==null ){
			request.setAttribute("msg", "条件定制录入编号不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "CONMX_NO", params);
		ParamUtil.putStr2Map(request, "CONMX_NAME", params);
		ParamUtil.putStr2Map(request, "CONMX_TYPE", params);
		ParamUtil.putStr2Map(request, "INPUT_TYPE", params);
		ParamUtil.putStr2Map(request, "INPUT_VALUE", params);
		
		String  PHYSICAL_NAME=ParamUtil.get(request, "PHYSICAL_NAME");
		if(PHYSICAL_NAME!=null)
			PHYSICAL_NAME=PHYSICAL_NAME.toUpperCase();
		params.put("PHYSICAL_NAME", PHYSICAL_NAME);
		ParamUtil.putStr2Map(request, "DEFAULTVAL", params);
		ParamUtil.putStr2Map(request, "OPERATOR", params);
		ParamUtil.putStr2Map(request, "ZJCONS", params);
		ParamUtil.putStr2Map(request, "ORDERXH", params);
		ParamUtil.putStr2Map(request, "CONMX_DESC", params);
		ParamUtil.putStr2Map(request, "ISJOIN", params);
		ParamUtil.putStr2Map(request, "ISFGF", params);
		
		mbTjSzService.updateById(params);
		request.setAttribute("msg", "修改成功！");
		mbTjSzService.clearCaches(CONMX_NO);

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
		String CONMX_NO = ParamUtil.get(request, "CONMX_NO");
		if(CONMX_NO.equals("") || CONMX_NO ==null ){
			request.setAttribute("msg", "条件定制录入编号不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "CONMX_NO", params);
		Map entry = mbTjSzService.load(CONMX_NO);
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
		String CONMX_NO = ParamUtil.get(request, "CONMX_NO");
		if(CONMX_NO.equals("") || CONMX_NO ==null ){
			request.setAttribute("msg", "条件定制录入编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "CONMX_NO", params);
		mbTjSzService.delete(params);
		request.setAttribute("msg", "删除成功！");
		mbTjSzService.clearCaches(CONMX_NO);
		
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
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		ParamUtil.putStr2Map(request, "CONMX_NAME", params);
		ParamUtil.putStr2Map(request, "CONMX_TYPE", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = mbTjSzService.pageQuery(params, pageNo);
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}

	public void setmbTjSzService(MbTjSzService mbTjSzService) {
		this.mbTjSzService = mbTjSzService;
	}
	
	//下拉框
	public String getOptions(){
		StringBuffer sel_options  = new StringBuffer();
		try{
			String[] options = new String[]{"","制定","生效","失效"}; //下拉框要传的值！
			String[] values = new String[]{"","制定","生效","失效"}; //下拉框要显示的值！
			
			for(int i=0;i<options.length;i++){
				String option = options[i];
				sel_options.append("<option value=\"").append(option).append("\">");
				sel_options.append(values[i]);
				sel_options.append("</option>");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return sel_options.toString();
	}

}



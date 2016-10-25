package com.yitong.app.action.judicial.lscxcol;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.yitong.app.service.judicial.lscxcol.LscxColService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.util.ParamUtil;

/**
 * 历史查询模板编
 * 
 * @author JACKY-XING
 * 
 */
public class LscxColAction extends BaseAction {
	private LscxColService lscxColService;


	public void setLscxColService(LscxColService lscxColService) {
		this.lscxColService = lscxColService;
	}


	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
		String REPMOD_NO = ParamUtil.get(request,"REPMOD_NO");
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		Map map = lscxColService.getZbxx(REPMOD_NO);
		request.setAttribute("rst", map);
		
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
		/*String userId = (String)request.getSession().getAttribute("hisRepUserId");
		
		Date date = DateUtil.getDate();	 
		String today = DateUtil.formatDateToStr(date, "yyyy-MM-dd HH:mm:ss");*/
		
		//得到编号
		String REPMOD_NO = ParamUtil.get(request,"REPMOD_NO");
		String COLMX_NO = lscxColService.getSeq(REPMOD_NO);
		System.out.println("COLMX_NO=="+COLMX_NO);
		params = new HashMap();
		
		params.put("COLMX_NO", COLMX_NO);
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
		ParamUtil.putStr2Map(request, "COLMX_NAME", params);
		ParamUtil.putStr2Map(request, "ALIGN", params);
		ParamUtil.putStr2Map(request, "WIDTH", params);
		ParamUtil.putStr2Map(request, "HEIGHT", params);
		ParamUtil.putStr2Map(request, "ISDISPLAY", params);
		String  PHYSICAL_NAME=ParamUtil.get(request, "PHYSICAL_NAME");
		if(PHYSICAL_NAME!=null)
			PHYSICAL_NAME=PHYSICAL_NAME.toUpperCase();
		params.put("PHYSICAL_NAME", PHYSICAL_NAME);
		ParamUtil.putStr2Map(request, "ORDERXH", params);	
		ParamUtil.putStr2Map(request, "COLMX_DESC", params);
		
		
		lscxColService.insert(params);
		request.setAttribute("msg", "增加成功！");
		return mapping.findForward(SUCCESS);
	}

	//到修改界面
	public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String COLMX_NO = ParamUtil.get(request, "COLMX_NO");
		
		if(COLMX_NO.equals("") || COLMX_NO ==null ){
			request.setAttribute("msg", "展现定制录入编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "COLMX_NO", params);
		Map entry = lscxColService.load(COLMX_NO);
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
		String COLMX_NO = ParamUtil.get(request, "COLMX_NO");
		if(COLMX_NO.equals("") || COLMX_NO ==null ){
			request.setAttribute("msg", "展现定制录入编号不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map params = new HashMap();
		params.put("COLMX_NO", COLMX_NO);
		ParamUtil.putStr2Map(request, "COLMX_NAME", params);
		ParamUtil.putStr2Map(request, "ALIGN", params);
		ParamUtil.putStr2Map(request, "WIDTH", params);
		ParamUtil.putStr2Map(request, "HEIGHT", params);
		ParamUtil.putStr2Map(request, "ISDISPLAY", params);
		String  PHYSICAL_NAME=ParamUtil.get(request, "PHYSICAL_NAME");
		if(PHYSICAL_NAME!=null)
			PHYSICAL_NAME=PHYSICAL_NAME.toUpperCase();
		params.put("PHYSICAL_NAME", PHYSICAL_NAME);
		ParamUtil.putStr2Map(request, "ORDERXH", params);	
		ParamUtil.putStr2Map(request, "COLMX_DESC", params);
		
		lscxColService.updateById(params);
		request.setAttribute("msg", "修改成功！");
		lscxColService.clearCaches(COLMX_NO);

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
		String COLMX_NO = ParamUtil.get(request, "COLMX_NO");
		if(COLMX_NO.equals("") || COLMX_NO ==null ){
			request.setAttribute("msg", "展现定制录入编号不存在！");
			return mapping.findForward(FAILURE);
		} 
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "COLMX_NO", params);
		Map entry = lscxColService.load(COLMX_NO);
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
		String COLMX_NO = ParamUtil.get(request, "COLMX_NO");
		if(COLMX_NO.equals("") || COLMX_NO ==null ){
			request.setAttribute("msg", "展现定制录入编号不存在！");
			return mapping.findForward(FAILURE);
		}
		Map params = new HashMap();
		ParamUtil.putStr2Map(request, "COLMX_NO", params);
		lscxColService.delete(params);
		request.setAttribute("msg", "删除成功！");
		lscxColService.clearCaches(COLMX_NO);
		
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
		System.out.println(ParamUtil.get(request, "REPMOD_NO"));
		ParamUtil.putStr2Map(request, "COLMX_NAME", params);
		ParamUtil.putStr2Map(request, "REPMOD_NO", params);
//		ParamUtil.putStr2Map(request, "STATE", params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = lscxColService.pageQuery(params, pageNo);
		request.setAttribute("page", page);
//		request.setAttribute("STATE", this.getOptions());
		return mapping.findForward("list");
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


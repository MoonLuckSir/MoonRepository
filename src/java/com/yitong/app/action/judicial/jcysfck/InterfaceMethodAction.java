package com.yitong.app.action.judicial.jcysfck;




import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;


import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.alibaba.fastjson.JSON;
import com.yitong.app.service.judicial.jcysfck.InterfaceMethodService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.util.ParamUtil;

	/**
	 * 批量查询相关
	 * 
	 * @author db2admin
	 * 
	 */
	public class InterfaceMethodAction extends BaseAction {
		protected static final Logger logger = Logger.getLogger(DjcxAction.class);
	
		private InterfaceMethodService interfaceMethodService;
		
		
		public InterfaceMethodService getInterfaceMethodService() {
			return interfaceMethodService;
	}





	public void setInterfaceMethodService(
			InterfaceMethodService interfaceMethodService) {
		this.interfaceMethodService = interfaceMethodService;
	}


	/**
	 * 接口方法 检察院
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String advanceTask(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = ParamUtil.get(request, "bbb");
		Map map = JSON.parseObject(jsonStr,Map.class);
		System.out.println("taskId:"+map.get("taskId")); 
		System.out.println("flag:"+map.get("flag")); 
		System.out.println("info:"+map.get("info"));
		interfaceMethodService.advanceTask(map);
		
		
		//System.out.println("success");
		return null;
	}
	
	/**
	 * 接口方法 法院
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String advanceTaskFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = ParamUtil.get(request, "bbb");
		Map map = JSON.parseObject(jsonStr,Map.class);
		System.out.println("taskId:"+map.get("taskId")); 
		System.out.println("flag:"+map.get("flag")); 
		System.out.println("info:"+map.get("info"));
		try {
			interfaceMethodService.advanceTaskFY(map);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//System.out.println("success");
		return null;
	}
	
	
	/**
	 * 接口方法 最高法院
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String advanceTaskZGFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String jsonStr = ParamUtil.get(request, "bbb");
		Map map = JSON.parseObject(jsonStr,Map.class);
		System.out.println("taskId:"+map.get("taskId")); 
		System.out.println("flag:"+map.get("flag")); 
		System.out.println("info:"+map.get("info"));
		try {
			interfaceMethodService.advanceTaskZGFY(map);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//System.out.println("success");
		return null;
	}
	
	
	/**
	 * 接口方法 测试
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String returnStr(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		String taskId = ParamUtil.get(request, "taskId");
		System.out.println("++++++++++"+taskId);
		return null;
	}
	
	
	
}

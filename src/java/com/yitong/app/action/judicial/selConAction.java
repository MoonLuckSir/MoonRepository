package com.yitong.app.action.judicial;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.yitong.app.service.judicial.selConService;
import com.yitong.commons.action.BaseAction;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.util.ParamUtil;
import com.yitong.commons.util.StringUtil;

/**
 * 通用选取
 * 
 * @author zxw 2010-09-29
 * 
 */
public class selConAction extends BaseAction {
	private selConService selConService;




	public void setSelConService(selConService selConService) {
		this.selConService = selConService;
	}
	/**
	 * 查询列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Map params = new HashMap();
		//模糊查询1,非模糊查询0
		String searchTpye="0";
		
		String selid=request.getParameter("selid");
		params.put("SELID", selid);
        Map selSetMap=selConService.findselSetMap(params);
        String sql=selSetMap.get("SQL").toString().trim();
        
        //回传值对应对应页面的框名称
        String cols=request.getParameter("retruncol");
        if(cols==null||cols.equals(""))
        cols=selSetMap.get("RETRUNVALUE").toString().trim();
        
        String selcons=selSetMap.get("SELCONS").toString().trim();
        String displaycol =selSetMap.get("DISPLAYCOL").toString().trim();
		String displayname=selSetMap.get("DISPLAYNAME").toString().trim();
		String keyvalue=selSetMap.get("KEYVALUE").toString().trim();
		String condition=selSetMap.get("CONDITIONCOL").toString().trim();
		String convalue=request.getParameter("convalue");
		if(convalue==null)
	    convalue="nodate";
		
		System.err.println("con==="+selSetMap.get("DEFCONDITION").toString());
		String defcondition=selSetMap.get("DEFCONDITION").toString().trim().replaceAll("\\$", "'");
		System.err.println("defcondition==="+defcondition);
		
		params.clear();
		String [] displaynames=StringUtil.toArr(displayname);
		String [] listcol=StringUtil.toArr(cols);
		String [] listcon=StringUtil.toArr(selcons);
		String [] displaycols=StringUtil.toArr(displaycol);
		String [] conditions=StringUtil.toArr(condition);
		List alist=new ArrayList();
		Map  dzMap =new HashMap();
		for( int i=0;i<displaycols.length;i++)
		{
			Map aMap =new HashMap();
			aMap.put("key", displaycols[i]);
			alist.add(aMap);
			dzMap.put(displaycols[i], displaynames[i]);
			
		}
		StringBuffer acon = new StringBuffer();
		acon.append(" where ");
		acon.append(defcondition);
		if(convalue!=null&&!convalue.equals(""))
		{
			String [] convalues=StringUtil.toArr(convalue);
			
			for( int i=0;i<conditions.length;i++)
			{
				if(convalues.length>i)
				{
				
				if(convalues[i]!=null&&!convalues[i].equals("")&&!convalues[i].equals("nodate"))
				{
				
					if(searchTpye.equals("1"))
					{
						
							acon.append(" and  ");
							acon.append(conditions[i]);
							acon.append("  LIKE '%");
							acon.append(convalues[i]);
							acon.append("%'  ");
							
						
					}
					if(searchTpye.equals("0"))
					{
						
							if(convalues.length>i)
							{
							acon.append(" and  ");
							acon.append(conditions[i]);
							acon.append("  = '");
							acon.append(convalues[i]);
							acon.append("'  ");
							}
						
					}
					
					
				}
				}
			}
			
		}
		StringBuffer inputcon = new StringBuffer();
		StringBuffer initcondate = new StringBuffer();
		for( int i=0;i<conditions.length;i++)
		{  
			
			if(conditions[i]!=null&&!conditions[i].equals(""))
			{
				String conName=conditions[i];
				if(conName.indexOf(".")>0)
				{
					conName=conName.substring(conName.indexOf(".")+1,conName.length());
					
				}	
				String temp=(String)dzMap.get(conName);
				int len=12*temp.length()+10;
				inputcon.append(" <td  align='right'  width='"+len+"' >"); 
				inputcon.append(temp); 
				inputcon.append(":</td>"); 
				inputcon.append("<td  width='120'>"); 
				inputcon.append("<input name='");
				inputcon.append(conName);
				inputcon.append("' type='text'  align='left'  style='width:110px;'"); 
				inputcon.append(" pattern='string'   label='");
				inputcon.append(temp);
				inputcon.append("' ");
				if(PROJECT.equals("jsbank"))
				{
					inputcon.append(" required='true' autocheck='true' ");	
				}
				if(PROJECT.equals("hfbank"))
				{
					inputcon.append(" required='true' autocheck='true' ");	
				}
				inputcon.append(" >");
				inputcon.append("</td>"); 
				
				if(request.getParameter(conName)!=null) 
				{
					
					if(searchTpye.equals("1"))
					{
					acon.append(" and  ");
					acon.append(conditions[i]);
					acon.append("  LIKE '%");
					acon.append(request.getParameter(conName));
					acon.append("%'  ");
					}
					if(searchTpye.equals("0"))
					{
					String tempStr=request.getParameter(conName).toString();
					if(tempStr!=null&&!tempStr.equals(""))
					{
					acon.append(" and  ");
					acon.append(conditions[i]);
					acon.append("  = '");
					acon.append(request.getParameter(conName));
					acon.append("'  ");
					}
						
					}
					initcondate.append("qryForm.");
					initcondate.append(conName);
					initcondate.append(".value='");
					initcondate.append(request.getParameter(conName));
					initcondate.append("';");
				}
				
			}
			
		}
		StringBuffer aStr = new StringBuffer();
		for( int i=0;i<displaynames.length;i++)
		{
			aStr.append("<th  align='center' norawp>");
			aStr.append(displaynames[i]);
			aStr.append("</th>");
			
		}
		sql=sql.toUpperCase();
		String CouSQL=" select  count(1) cnt "+sql.substring(sql.indexOf("FROM"),sql.length());
		params.put("SelSQL", sql+acon.toString());
		System.err.println("SelSQL=="+sql+acon.toString());
		params.put("CouSQL", CouSQL+acon.toString());
		System.err.println("CouSQL=="+CouSQL+acon.toString());
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = selConService.findResuList(params, pageNo);
		request.setAttribute("page", page);
		StringBuffer CaseStr = new StringBuffer();
		List apage=(List)page.getResult();
		for( int i=0;i<apage.size();i++)
		{
			
			Map aMap =(Map)apage.get(i);
			CaseStr.append(" if( tmpId.value =='");
			CaseStr.append(aMap.get(keyvalue));
			CaseStr.append("'){");
			aMap.put("keyvalue", keyvalue);
			for( int j=0;j<listcol.length;j++)
			{   
				CaseStr.append(" window.opener.document.all.");
				CaseStr.append(listcol[j]);
				CaseStr.append(".value='");
				CaseStr.append(aMap.get(listcon[j]));
				CaseStr.append("';");
					
			}
			CaseStr.append("}");
			
		}
		request.setAttribute("initcondate",initcondate.toString());
		request.setAttribute("inputcon",inputcon.toString());
		request.setAttribute("condition",condition);
		request.setAttribute("defcondition",defcondition);
		request.setAttribute("selid",selid);
		request.setAttribute("sql",sql);
		request.setAttribute("cols",cols);
		request.setAttribute("displayname",displayname);
		request.setAttribute("displaycol",displaycol);
		request.setAttribute("selcons",selcons);
		request.setAttribute("keyvalue",keyvalue);
		request.setAttribute("jscon",CaseStr.toString());
		request.setAttribute("discols",aStr.toString());
		request.setAttribute("displaycols",alist);
		return mapping.findForward("list");
	}
	
	
	public void toSetvalue(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map params = new HashMap();
		//模糊查询1,非模糊查询0
		String searchTpye="0";
		
		String selid=request.getParameter("selid");
		params.put("SELID", selid);
        Map selSetMap=selConService.findselSetMap(params);
        String sql=selSetMap.get("SQL").toString().trim();
        //回传值对应对应页面的框名称
        String cols=request.getParameter("retruncol").toString().trim();
        if(cols==null||cols.equals(""))
        cols=selSetMap.get("RETRUNVALUE").toString().trim();
        String selcons=selSetMap.get("SELCONS").toString().trim();
		String keyvalue=selSetMap.get("KEYVALUE").toString().trim();
		
		String condition=selSetMap.get("CONDITIONCOL").toString().trim();
		String convalue=request.getParameter("convalue").toString().trim();
		
		
		String defcondition=selSetMap.get("DEFCONDITION").toString().trim().replaceAll("\\$", "'");
		params.clear();
		String [] listcol=StringUtil.toArr(cols);
		String [] listcon=StringUtil.toArr(selcons);
		String [] conditions=StringUtil.toArr(condition);
		StringBuffer acon = new StringBuffer();
		acon.append(" where ");
		acon.append(defcondition);
		if(convalue!=null&&!convalue.equals(""))
		{
			String [] convalues=StringUtil.toArr(convalue);
			
			for( int i=0;i<conditions.length;i++)
			{
				if(convalues[i]!=null&&!convalues[i].equals("")&&!convalues[i].equals("nodate"))
				{
				
					if(searchTpye.equals("1"))
					{
						acon.append(" and  ");
						acon.append(conditions[i]);
						acon.append("  LIKE '%");
						acon.append(convalues[i]);
						acon.append("%'  ");
					}
					if(searchTpye.equals("0"))
					{
						if(convalues[i]!=null&&!convalues[i].equals(""))
						{
							acon.append(" and  ");
							acon.append(conditions[i]);
							acon.append("  = '");
							acon.append(convalues[i]);
							acon.append("'  ");
						}
						
					}
					
					
				}
				
			}
			
		}
		sql=sql.toUpperCase();
		String CouSQL=" select  count(1) cnt "+sql.substring(sql.indexOf("FROM"),sql.length());
		params.put("SelSQL", sql+acon.toString());
		params.put("CouSQL", CouSQL+acon.toString());
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = selConService.findResuList(params, pageNo);
		request.setAttribute("page", page);
		StringBuffer CaseStr = new StringBuffer();
		List apage=(List)page.getResult();
		
		if( apage.size()==1)
		{
			Map aMap =(Map)apage.get(0);
			aMap.put("keyvalue", keyvalue);
			for( int j=0;j<listcol.length;j++)
			{   

				String tempStr=aMap.get(listcon[j]).toString();
				if(tempStr==null||tempStr.equals(""))
				tempStr="nodata";
				CaseStr.append(tempStr);
				if(j!=listcol.length-1)
				CaseStr.append(",");	
					
			}
		
			
		}
		if(apage.size()>1)
			CaseStr.append("muchrows");
		PrintWriter out = response.getWriter();
		out.print(CaseStr.toString());
		out.close();
		
	}
	
	public void docheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map params = new HashMap();
		StringBuffer CaseStr = new StringBuffer();
		    String sql=request.getParameter("sql");
		    String CouSQL=" select  count(1) cnt "+sql.substring(sql.indexOf("FROM"),sql.length());
			params.put("SelSQL", sql);
			params.put("CouSQL", CouSQL);
			int pageNo = ParamUtil.getInt(request, "pageNo", 1);
			IListPage page = selConService.findResuList(params, pageNo);
			request.setAttribute("page", page);
			List apage=(List)page.getResult();
			if( apage.size()>0)
			{
			   CaseStr.append("ok");
			}else
			{
			   CaseStr.append("no");
			}	
		PrintWriter out = response.getWriter();
		out.print(CaseStr.toString());
		out.close();
		
	}
	

	
}

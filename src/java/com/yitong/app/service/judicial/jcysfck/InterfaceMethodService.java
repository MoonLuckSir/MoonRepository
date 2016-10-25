package com.yitong.app.service.judicial.jcysfck;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.log4j.chainsaw.Main;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.springframework.aop.ThrowsAdvice;
import org.xml.sax.InputSource;

import com.alibaba.fastjson.JSON;
import com.yitong.app.action.judicial.fysfck.SyyhConvertDataServiceLocator;
import com.yitong.app.action.judicial.fysfck.SyyhConvertDataServiceSoapBindingStub;
import com.yitong.commons.model.IListPage;
import com.yitong.commons.model.Properties;
import com.yitong.commons.model.judicial.sfck.CxqqInfo_JCY;
import com.yitong.commons.service.BaseService;
import com.yitong.commons.util.StringUtil;

public class InterfaceMethodService extends BaseService {
	
	private static String sfckDownloadPath = Properties.getString("sfckDownloadPath");// 生成路径
	private static String webServiceUrl = Properties.getString("webServiceUrl");// webservice服务地址
	private static String webServiceUserName = Properties.getString("webServiceUserName");// webservice服务地址
	private static String webServicePassWord = Properties.getString("webServicePassWord");// webservice服务地址
	
	private static String zgfy_webServiceUrl = Properties.getString("zgfy_webServiceUrl");// webservice服务地址
	private static String zgfy_webServiceUserName = Properties.getString("zgfy_webServiceUserName");// webservice服务地址
	private static String zgfy_webServicePassWord = Properties.getString("zgfy_webServicePassWord");// webservice服务地址
	
	/**
	 * 根据大数据反馈信息更新任务进程  检察院
	 * @param params
	 * @param pageNo
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	public boolean advanceTask(Map param){
		
		String flag = (String) param.get("flag");
		String taskId = (String) param.get("taskId");
		String filename = sfckDownloadPath+taskId+".tar.gz";
		param.put("filename", filename);
		if("success".equals(flag)){
			this.update("djcx.successTask", param);
		}else{
			this.update("djcx.failTask", param);
		}
		 return true;
	}
	
	/**
	 * 根据大数据反馈信息更新任务进程  法院
	 * @param params
	 * @param pageNo
	 * @return
	 * @throws ServiceException 
	 */
	@SuppressWarnings("unchecked")
	public boolean advanceTaskFY(Map param) throws ServiceException{
		String flag = (String) param.get("flag");
		String taskId = (String) param.get("taskId");
		
		String filename = sfckDownloadPath+taskId+".xml";
		String impfilename = sfckDownloadPath+taskId+".tar.gz";
		param.put("filename", filename);
		param.put("impfilename", impfilename);
		if("success".equals(flag)){
			this.update("fyck.successTask", param);
		}else{ 
			this.update("fyck.failTask", param);
			return true;
		}
		
		
		//修改任务查询结果已反馈
		try {
			File file = new File(filename);
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String buff = "";
			StringBuffer sb = new StringBuffer();
			while((buff = br.readLine()) != null){
				sb.append(buff);
			}
			
			//生成反馈信息 加密
			String feedbackinfo = StringUtil.encodeXML(sb.toString());
			
			System.out.println("读取生成的XML文件内容（反馈）  ======================"+sb.toString());
			
		/*	String decodefeedbackinfo = StringUtil.decodeXML(feedbackinfo);
			 PrintStream ps = new PrintStream(new File(sfckDownloadPath+"12345.txt"));
			ps.println(sb.toString());
			ps.println(feedbackinfo);
			ps.println(decodefeedbackinfo);*/
			
			
			//将数据反馈给法院端接口
			SyyhConvertDataServiceLocator service1 = new SyyhConvertDataServiceLocator();
			java.net.URL url = new java.net.URL(webServiceUrl);
			SyyhConvertDataServiceSoapBindingStub stub = new SyyhConvertDataServiceSoapBindingStub(
					url, service1);
			String userMarker = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
			"<usermarker> "+
				"<condition username=\""+webServiceUserName+"\" password=\""+webServicePassWord+"\"></condition>"+
			"</usermarker> ";
			String encodeUserMarkerStr = StringUtil.encodeXML(userMarker);
			
			String shfeedXzcxInfo =  stub.shfeedXzcxInfo(encodeUserMarkerStr, feedbackinfo);
			//解析得到的返回值
			List<Map> list = this.parseShfeedXzcxInfo(shfeedXzcxInfo);
			System.out.println("list==========================="+list.toString());
			if(list.size() != 0){
				if(list.get(0).containsKey("errMsg")){
					String errMsg = (String) list.get(0).get("errMsg");
					System.out.println("errMsg===================="+errMsg);
					//访问时整个出错
					param.put("taskStu", "10");
					param.put("errMsg", errMsg);
					this.update("fyck.updateTaskStu", param);
				}else{
					param.put("taskStu", "9");
					//反馈后更改状态为已反馈
					this.update("fyck.updateTaskStu", param);
					//反馈后更改执行完成时间
					this.update("fyck.updateTaskTime", null);
					System.out.println("param的值taskStu==="+param.get("taskStu")+";taskId====="+param.get("taskId")+";flag====="+param.get("flag"));
					//访问时个别条件出错
					for(Map m : list){
						if("fail".equals(m.get("result")) ){
							param.put("bdhm", m.get("bdhm"));
							param.put("msg", m.get("msg"));
							this.update("fyck.updateCondiErroinfo", param);
						}
					}
				}
				
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			param.put("info", e.toString());
			this.update("fyck.failTask", param);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			param.put("info", e.toString());
			this.update("fyck.failTask", param);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			param.put("info", e.toString());
			this.update("fyck.failTask", param);
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			param.put("info", e.toString());
			this.update("fyck.failTask", param);
			e.printStackTrace();
		}
		
		 return true;
	}
	
	
	/**
	 * 根据大数据反馈信息更新任务进程  最高法院
	 * @param params
	 * @param pageNo
	 * @return
	 * @throws ServiceException 
	 */
	@SuppressWarnings("unchecked")
	public boolean advanceTaskZGFY(Map param) throws ServiceException{
		String flag = (String) param.get("flag");
		String taskId = (String) param.get("taskId");
		
		String filename = sfckDownloadPath+taskId+".xml";
		String impfilename = sfckDownloadPath+taskId+".tar.gz";
		param.put("filename", filename);
		param.put("impfilename", impfilename);
		if("success".equals(flag)){
			
			this.update("fyck.successTask", param);
		}else{ 
			this.update("fyck.failTask", param);
			return true;
		}
		
		
		//修改任务查询结果已反馈
		try {
			File file = new File(filename);
			InputStreamReader isr = new InputStreamReader(new FileInputStream(file),"UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String buff = "";
			StringBuffer sb = new StringBuffer();
			while((buff = br.readLine()) != null){
				sb.append(buff);
			}
			
			//生成反馈信息 加密
			String feedbackinfo = StringUtil.encodeXML(sb.toString());
			
			System.out.println("读取生成的XML文件内容（反馈）  ======================"+sb.toString());
			
		/*	String decodefeedbackinfo = StringUtil.decodeXML(feedbackinfo);
			 PrintStream ps = new PrintStream(new File(sfckDownloadPath+"12345.txt"));
			ps.println(sb.toString());
			ps.println(feedbackinfo);
			ps.println(decodefeedbackinfo);*/
			
			
			//将数据反馈给法院端接口
			SyyhConvertDataServiceLocator service1 = new SyyhConvertDataServiceLocator();
			java.net.URL url = new java.net.URL(zgfy_webServiceUrl);
			SyyhConvertDataServiceSoapBindingStub stub = new SyyhConvertDataServiceSoapBindingStub(
					url, service1);
			String userMarker = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
			"<usermarker> "+
				"<condition username=\""+zgfy_webServiceUserName+"\" password=\""+zgfy_webServicePassWord+"\"></condition>"+
			"</usermarker> ";
			String encodeUserMarkerStr = StringUtil.encodeXML(userMarker);
			
			String shfeedXzcxInfo =  stub.shfeedXzcxInfo(encodeUserMarkerStr, feedbackinfo);
			//解析得到的返回值
			List<Map> list = this.parseShfeedXzcxInfo(shfeedXzcxInfo);
			System.out.println("list==========================="+list.toString());
			if(list.size() != 0){
				if(list.get(0).containsKey("errMsg")){
					String errMsg = (String) list.get(0).get("errMsg");
					errMsg = errMsg.replace("\"", "");
					System.out.println("errMsg===================="+errMsg);
					//访问时整个出错
					param.put("taskStu", "10");
					param.put("errMsg", errMsg);
					this.update("fyck.updateTaskStu", param);
				}else{
					param.put("taskStu", "9");
					//反馈后更改状态为已反馈
					this.update("fyck.updateTaskStu", param);
					//反馈后更改执行完成时间
					this.update("fyck.updateTaskTime2", null);
					System.out.println("param的值taskStu==="+param.get("taskStu")+";taskId====="+param.get("taskId")+";flag====="+param.get("flag"));
					//访问时个别条件出错
					for(Map m : list){
						if("fail".equals(m.get("result")) ){
							param.put("bdhm", m.get("bdhm"));
							param.put("msg", m.get("msg"));
							this.update("fyck.updateZgfyCondiErroinfo", param);
						}
					}
				}
				
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			param.put("info", e.toString());
			this.update("fyck.failTask", param);
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			param.put("info", e.toString());
			this.update("fyck.failTask", param);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			param.put("info", e.toString());
			this.update("fyck.failTask", param);
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			param.put("info", e.toString());
			this.update("fyck.failTask", param);
			e.printStackTrace();
		}
		
		 return true;
	}
	
	/*public static void main(String[] args) {
		String str = " <zhxx BDHM=\"20150623342902100406\" CCXH=\"1\" KHZH=\"10001879993410000000032\" YE=\"9.16\" FKSJ=\"2015/07/30 19:50:28\" CCLB=\"借记卡\" ZHZT=\"不动户\" KHWD=\"3417817165\" BZ=\"156\" TXDZ=\"四里园东92号7285550\" YZBM=\"\" LXDH=\"\" BEIZ=\"\" SFTZ=\"\"/>";
		
		try{
		//String feedbackinfo = StringUtil.encodeXML(str);
			throw new IOException();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			
			//e.printStackTrace();
		}
	}*/
	
	
	
	
	/**
	 * 解析shfeedXzcxInfo方法反馈字符串  法院
	 * @param params
	 * @param pageNo
	 * @return
	 * @throws ServiceException 
	 */
	 public List parseShfeedXzcxInfo(String shfeedXzcxInfo) {
		 	String xmlDoc = StringUtil.decodeXML(shfeedXzcxInfo);
	        //创建一个新的字符串
	        StringReader read = new StringReader(xmlDoc);
	        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入
	        InputSource source = new InputSource(read);
	        //创建一个新的SAXBuilder
	        SAXBuilder sb = new SAXBuilder();
	        List<Map> xmlList = new ArrayList<Map>();
	        try {
	        	
	            //通过输入源构造一个Document
	            Document doc = sb.build(source);
	            //取的根元素
	            Element root = doc.getRootElement();
	            System.out.println(root.getName());//输出根元素的名称（测试）
	            //得到根元素所有子元素的集合
	            List cxjglist = root.getChildren();
	            //获得XML中的命名空间（XML中未定义可不写）
	            Namespace ns = root.getNamespace();
	            Element et = null;
	            
	            if(root.getAttributeValue("errMsg") != null){
	            	Map map = new HashMap();
	            	String errMsg =root.getAttributeValue("errMsg");
	            	map.put("errMsg", errMsg);
	            	xmlList.add(map);
	            	return xmlList;
	            }
	            
	           Element cxjglistElement = (Element) cxjglist.get(0);
	           List jgList = cxjglistElement.getChildren();
	            for(int i=0;i<jgList.size();i++){
	            	Map params = new HashMap();
	                et = (Element) jgList.get(i);//循环依次得到子元素
	                params.put("bdhm", et.getAttributeValue("bdhm"));
	                params.put("result", et.getAttributeValue("result"));
	                params.put("msg", et.getAttributeValue("msg"));
	                xmlList.add(params);
	            }
	        } catch (JDOMException e) {
	            // TODO 自动生成 catch 块
	            e.printStackTrace();
	        } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        return xmlList;
	    }
	
	
}

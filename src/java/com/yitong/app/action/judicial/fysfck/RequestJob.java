package com.yitong.app.action.judicial.fysfck;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import sun.misc.BASE64Decoder;
import tdh.util.Basic64;

import com.alibaba.fastjson.util.Base64;
import com.sun.corba.se.impl.util.Version;
import com.yitong.app.service.judicial.fysfck.FysfckService;
import com.yitong.commons.model.Properties;
import com.yitong.commons.util.StringUtil;




public class RequestJob{
	private static String webServiceUrl = Properties.getString("webServiceUrl");// webservice服务地址
	private static String webServiceUserName = Properties.getString("webServiceUserName");// webservice服务地址
	private static String webServicePassWord = Properties.getString("webServicePassWord");// webservice服务地址
	private static String fyServerUrl = Properties.getString("fyServerUrl");//服务地址
	
	private FysfckService fysfckService;
	
	public FysfckService getFysfckService() {
		return fysfckService;
	}

	public void setFysfckService(FysfckService fysfckService) {
		this.fysfckService = fysfckService;
	}

	public static void main(String[] args) {
		System.out.println(org.apache.axis.Version.getVersion());
	}
	public void getRequestByWS() throws InterruptedException, ServiceException, RemoteException, MalformedURLException{
			
		SyyhConvertDataServiceLocator service1 = new SyyhConvertDataServiceLocator();
			java.net.URL url = new java.net.URL(webServiceUrl);
			SyyhConvertDataServiceSoapBindingStub stub = new SyyhConvertDataServiceSoapBindingStub(
					url, service1);
			String userMarker = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
			"<usermarker> "+
				"<condition username=\""+webServiceUserName+"\" password=\""+webServicePassWord+"\"></condition>"+
			"</usermarker> ";
			String encodeUserMarkerStr = StringUtil.encodeXML(userMarker);
			String getCxqqStr =  stub.getXzcxList(encodeUserMarkerStr);
		
		/*	String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
			"<cxqqList  ErrorMSG=\"地方地地方地方胜多负少地方国家计划方时地+++++++++    方时打发司法第三方 房顶上放水电费多少飞\">"+
				"<cxqq BDHM=\"20111128320000100001\" LB=\"YH\" XZ=\"1\" XM=\"333\" GJ=\"中国\" ZJLX=\"01\" DSRZJHM=\"3404211964112938313\" " +
				"FZJG=\"江苏南京\" FYMC=\"南京市中级人民法院\" CBR=\"张三\" AH=\"（2011）宁执字第00003号\" CKKSSJ=\"2011/09/30 21:09:30\" CKJSSJ=\"2011/11/30 21:09:30\"></cxqq>" +
				"<cxqq BDHM=\"20111128320000100002\" LB=\"YH\" XZ=\"1\" XM=\"222\" GJ=\"中国\" ZJLX=\"01\" DSRZJHM=\"340421196411293813\"" +
				" FZJG=\"江苏南京\" FYMC=\"南京市中级人民法院\" CBR=\"张三\" AH=\"（2011）宁执字第00005号\" CKKSSJ=\"2011/09/30 21:09:30\" CKJSSJ=\"2011/11/30 21:09:30\"></cxqq>" +
			"</cxqqList>";
			String getCxqqStr = StringUtil.encodeXML(xmlStr);*/
		/*String getCxqqStr = "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiPz4KPGN4cXFMaXN0PjxjeHFxIEJE" +
 		"SE09Ik1qQXhOVEEwTVRaRE1EQXhNREF3TURBd01ERT0iIExCPSJXVWc9IiBYWj0iTVE9PSIgWE09" +
 		"IjFjWEkvUT09IiBHSj0iIiBaSkxYPSJNREU9IiBEU1JaSkhNPSJNelF3TVRFd01UazRNREE0TVRB" +
 		"d056ZzUiIEZaSkc9IiIgRllNQz0ic0xLNzFjcWh1Tis4dHNqTHcvRzNxTlM2IiBDQlI9InphaTA3" +
 		"N3FqIiBBSD0iS0RJd01UVXB6ZTdXdExMaTE5YTEyakF3TURBeHVzVT0iIENLS1NTSj0iIiBDS0pT" +
 		"U0o9IiIgR1daQk09InplNjNxREF3TURBd01ERT0iLz48L2N4cXFMaXN0Pg==";*/
		
		/*//创建服务
		Service service = new Service();
		//创建调用句柄
		Call call = (Call) service.createCall();
		//设置请求地址
		call.setTargetEndpointAddress(webServiceUrl);
		
		call.setOperationName(new QName(null, "getXzcxList"));
		
		String getCxqqStr = (String) call.invoke(new Object[] { "111" });*/

		
			//将查询条件信息封装成List
			List<Map> xmlList = this.xmlElements(getCxqqStr);
			//如果无查询请求信息 则不建立任务
			if(xmlList.size() == 0){
				return ;
			}
			 
			Map params = new HashMap();
			String TASK_ID = "FY"+new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
			String bdhminfo = "";
			String encodebdhminfo ="";
			String zjInfo = "";
			//添加新任务
			params.put("TASK_ID", TASK_ID);
			params.put("QRY_DEPT", "FY");

			fysfckService.addTask(params);
			
			
			
			if(xmlList.get(0).containsKey("errMsg")){
				String errMsg = (String) xmlList.get(0).get("errMsg");
				//获取查询条件出错
				params.put("taskStu", "10");
				params.put("errMsg", errMsg);
				fysfckService.updateTaskStu(params);
				return ;
			}
			
			
			for(Map m :xmlList){
				m.put("TASK_ID", TASK_ID);
				/*//组装服务参数
				bdhminfo =  "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>"+
				"<bdhminfo>"+
				"<condition BDHM=\""+m.get("BDHM")+"\"></condition>"+
				"</bdhminfo>";
				System.out.println(bdhminfo);
				encodebdhminfo = StringUtil.encodeXML(bdhminfo);
				
				zjInfo = stub.zjInfo(encodeUserMarkerStr, encodebdhminfo);
				
				List<Map> zjlist = this.parsezjInfo(zjInfo);
				//如果无查证件信息 则返回
				if(zjlist.size() != 0){
					if(zjlist.get(0).containsKey("ErrorMSG")){
						String errMsg = (String) zjlist.get(0).get("ErrorMSG");
						//获取证件信息出错
						m.put("ERRORINFO", errMsg);
					}else{
						for (int j =0;j<zjlist.size();j++){
							Map zjmap = new HashMap();
							zjmap = zjlist.get(j);
							if(j == 0){
								if(!"".equals(zjmap.get("GZZBM"))){
									m.put("GZZBM1", zjmap.get("GZZBM"));
								}
								
								if(!"".equals(zjmap.get("GZZ")) && zjmap.get("GZZ") != null){
									//工作证存放路径
									String gzzPath = sfckDownloadPath + "GZZ_"+m.get("BDHM")+".jpg";
									m.put("GZZ1", gzzPath);
									saveToImgByStr((String)m.get("GZZ"), sfckDownloadPath, "GZZ1_"+m.get("BDHM")+".jpg");
								}
								if(!"".equals(zjmap.get("GWZBM"))){
									m.put("GWZBM1", zjmap.get("GWZBM"));
								}
								if(!"".equals(zjmap.get("GWZ")) && zjmap.get("GWZ") != null ){
									//公务证存放路径
									String gwzPath = sfckDownloadPath + "GWZ_"+m.get("BDHM")+".jpg";
									m.put("GWZ1", gwzPath);
									this.saveToImgByStr((String)m.get("GWZ"), sfckDownloadPath, "GWZ1_"+m.get("BDHM")+".jpg");
								}
							}
							if(j == 1){
								if(!"".equals(zjmap.get("GZZBM"))){
									m.put("GZZBM2", zjmap.get("GZZBM"));
								}
								
								if(!"".equals(zjmap.get("GZZ")) && zjmap.get("GZZ") != null){
									//工作证存放路径
									String gzzPath = sfckDownloadPath + "GZZ_"+m.get("BDHM")+".jpg";
									m.put("GZZ2", gzzPath);
									saveToImgByStr((String)m.get("GZZ"), sfckDownloadPath, "GZZ2_"+m.get("BDHM")+".jpg");
								}
								if(!"".equals(zjmap.get("GWZBM"))){
									m.put("GWZBM2", zjmap.get("GWZBM"));
								}
								if(!"".equals(zjmap.get("GWZ")) && zjmap.get("GWZ") != null ){
									//公务证存放路径
									String gwzPath = sfckDownloadPath + "GWZ_"+m.get("BDHM")+".jpg";
									m.put("GWZ2", gwzPath);
									this.saveToImgByStr((String)m.get("GWZ"), sfckDownloadPath, "GWZ2_"+m.get("BDHM")+".jpg");
								}
							}
							
						}
					}
				}*/
				
				
				fysfckService.insertConData(m);
			}
			
			
			
			//向大数据发送该任务下所有查询条件
			HttpClient client = new HttpClient();
			PostMethod post = new PostMethod(fyServerUrl);
			
			String jsonStr = fysfckService.makeParams(xmlList,TASK_ID);
			post.addParameter("aaa",jsonStr);
			post.addParameter("taskId",TASK_ID);
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			
				
			try {
				int result = client.executeMethod(post);
				System.out.println("result=============="+result);
				if(result != 200){
					params.put("taskStu", "10");   
					params.put("TASK_ID", TASK_ID);
					params.put("MAKE_INFO", "发送查询数据至大数据端时发生错误");
					fysfckService.updateTaskStu(params);
				}
			} catch (HttpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   System.out.println("Quartz的任务调度!"+xmlList.toString()); 
	
    }  
	
	
	
	 public List parsezjInfo(String zjInfo) {
		 	String xmlDoc = StringUtil.decodeXML(zjInfo);
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
	            if(root.getAttributeValue("ErrorMSG") != null){
	            	Map map = new HashMap();
	            	String errMsg =root.getAttributeValue("ErrorMSG");
	            	map.put("errMsg", errMsg);
	            	xmlList.add(map);
	            	return xmlList;
	            }
	            System.out.println(root.getName());//输出根元素的名称（测试）
	            //得到根元素所有子元素的集合
	            List jiedian = root.getChildren();
	            //获得XML中的命名空间（XML中未定义可不写）
	            Namespace ns = root.getNamespace();
	            Element et = null;
	            for(int i=0;i<jiedian.size();i++){
	            	Map params = new HashMap();
	                et = (Element) jiedian.get(i);//循环依次得到子元素
	              
	                params.put("ZJMC", et.getAttributeValue("ZJMC"));
	                params.put("GZZBM", et.getAttributeValue("GZZBM"));
	                params.put("GZZ", et.getAttributeValue("GZZ"));
	                params.put("GWZBM", et.getAttributeValue("GWZBM"));
	                params.put("GWZ", et.getAttributeValue("GWZ"));
	                
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
	
	 public List xmlElements(String getCxqqStr) {
		 	String xmlDoc = StringUtil.decodeXML(getCxqqStr);
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
	            if(root.getAttributeValue("ErrorMSG") != null){
	            	Map map = new HashMap();
	            	String errMsg =root.getAttributeValue("ErrorMSG");
	            	map.put("errMsg", errMsg);
	            	xmlList.add(map);
	            	return xmlList;
	            }
	            System.out.println(root.getName());//输出根元素的名称（测试）
	            //得到根元素所有子元素的集合
	            List jiedian = root.getChildren();
	            //获得XML中的命名空间（XML中未定义可不写）
	            Namespace ns = root.getNamespace();
	            Element et = null;
	            for(int i=0;i<jiedian.size();i++){
	            	Map params = new HashMap();
	                et = (Element) jiedian.get(i);//循环依次得到子元素
	                params.put("BDHM", et.getAttributeValue("BDHM"));
	                params.put("LB", et.getAttributeValue("LB"));
	                params.put("XZ", et.getAttributeValue("XZ"));
	                params.put("XM", et.getAttributeValue("XM"));
	                params.put("GJ", et.getAttributeValue("GJ"));
	                params.put("ZJLX", et.getAttributeValue("ZJLX"));
	                params.put("DSRZJHM", et.getAttributeValue("DSRZJHM"));
	                params.put("FZJG", et.getAttributeValue("FZJG"));
	                params.put("FYMC", et.getAttributeValue("FYMC"));
	                params.put("CBR", et.getAttributeValue("CBR"));
	                params.put("AH", et.getAttributeValue("AH"));
	                params.put("CKKSSJ", et.getAttributeValue("CKKSSJ"));
	                params.put("CKJSSJ", et.getAttributeValue("CKJSSJ"));
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
	
	
	
	
	/**
	 * 测试方法
	
	public static void main(String[] args) {
		String data = new RequestJob().getImgeHexString("d:/123.jpg","jpg");
		System.out.println(data);
		new RequestJob().saveToImgByStr(data, "e:/", "321.jpeg");

	} */
	
	/**
	 * 图片转换为二进制字符串
	 * @param imagePath 图片存放路径
     * @param imageType 图片类型
     * @return 
	 *      二进制字符串
	 */
	public  String getImgeHexString(String imagePath,String imageType) {   
	    String res = null;   
	    try {   
	            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(imagePath)));   
	            BufferedImage bm = ImageIO.read(bis);   
	            ByteArrayOutputStream bos = new ByteArrayOutputStream();   
	            ImageIO.write(bm, imageType, bos);   
	            bos.flush();   
	            byte[] data = bos.toByteArray();   
	            res = this.byte2hex(data);   
	            bos.close();   
	    } catch (Exception e) {   
	        e.printStackTrace();   
	    }   
	    return res;   
	}   
	
	
	/**
     * 将二进制字符串转换成图片保存
     * @param imgStr 二进制流转换的字符串
     * @param imgPath 图片的保存路径
     * @param imgName 图片的名称
     * @return 
     *      1：保存正常
     *      0：保存失败
     */
	 public int saveToImgByStr(String imgStr,String imgPath,String imgName){
		 try {
			 
		     System.out.println("===imgStr.length()====>" + imgStr.length()
		             + "=====imgStr=====>" + imgStr);
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
		         int stateInt = 1;
		         if(imgStr != null && imgStr.length() > 0){
		             try {
		                  
		                 // 将字符串转换成二进制，用于显示图片  
		                 // 将上面生成的图片格式字符串 imgStr，还原成图片显示  
		                 byte[] imgByte = this.hex2byte( imgStr );  
		      
		                 InputStream in = new ByteArrayInputStream(imgByte);
		                 File file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
		                 FileOutputStream fos=new FileOutputStream(file);
		                    
		                 byte[] b = new byte[1024];
		                 int nRead = 0;
		                 while ((nRead = in.read(b)) != -1) {
		                     fos.write(b, 0, nRead);
		                 }
		                 fos.flush();
		                 fos.close();
		                 in.close();
		             } catch (Exception e) {
		                 stateInt = 0;
		                 e.printStackTrace();
		             } finally {
		             }
		         }
		         return stateInt;
		     }
		      
		     /**
		      * 将二进制转换成图片保存
		      * @param imgStr 二进制流转换的字符串
		      * @param imgPath 图片的保存路径
		      * @param imgName 图片的名称
		      * @return 
		      *      1：保存正常
		      *      0：保存失败
		      */
		     public static int saveToImgByBytes(File imgFile,String imgPath,String imgName){
		  
		         int stateInt = 1;
		         if(imgFile.length() > 0){
		             try {
		                 File file=new File(imgPath,imgName);//可以是任何图片格式.jpg,.png等
		                 FileOutputStream fos=new FileOutputStream(file);
		                  
		                 FileInputStream fis = new FileInputStream(imgFile);
		                    
		                 byte[] b = new byte[1024];
		                 int nRead = 0;
		                 while ((nRead = fis.read(b)) != -1) {
		                     fos.write(b, 0, nRead);
		                 }
		                 fos.flush();
		                 fos.close();
		                 fis.close();
		      
		             } catch (Exception e) {
		                 stateInt = 0;
		                 e.printStackTrace();
		             } finally {
		             }
		         }
		         return stateInt;
		     }
		  
		     /**
		      * 二进制转字符串
		      * @param b
		      * @return
		      */
		     public  String byte2hex(byte[] b) // 二进制转字符串
		     {
		         StringBuffer sb = new StringBuffer();
		         String stmp = "";
		         for (int n = 0; n < b.length; n++) {
		             stmp = Integer.toHexString(b[n] & 0XFF);
		             if (stmp.length() == 1) {
		                 sb.append("0" + stmp);
		             } else {
		                 sb.append(stmp);
		             }
		  
		         }
		         return sb.toString();
		     }
		  
		     /**
		      * 字符串转二进制
		      * @param str 要转换的字符串
		      * @return  转换后的二进制数组
		      */
		     public  byte[] hex2byte(String str) { // 字符串转二进制.
		    	 
		    	return  it.sauronsoftware.base64.Base64.decode(str.getBytes()) ;
		    	 
		       /*  if (str == null)
		             return null;
		         str = str.trim();
		         int len = str.length();
		         if (len == 0 || len % 2 == 1)
		             return null;
		         byte[] b = new byte[len / 2];
		         try {
		             for (int i = 0; i < str.length(); i += 2) {
		                 b[i / 2] = (byte) Integer
		                         .decode("0X" + str.substring(i, i + 2)).intValue();
		             }
		             return b;
		         } catch (Exception e) {
		             return null;
		         }*/
		     }
	
}

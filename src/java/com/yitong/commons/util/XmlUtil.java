package com.yitong.commons.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.yitong.commons.model.judicial.sfck.CxqqInfo_JCY;



/**
 * XML工具类
 *
 */
public class XmlUtil {
	private static Logger logger = Logger.getLogger(XmlUtil.class);
	
	/**解析xml 至 list
	 * @param xmlFile
	 * @return
	 */
	public static synchronized List parseXML(String xmlFile) {
		SAXReader reader = new SAXReader();
		List result = new ArrayList();
		Document doc = null;
		try {
			doc = reader.read(new File(xmlFile));
		} catch (DocumentException e) {
			logger.error("read xml file error!", e);
			return null;
		}
		Element root = doc.getRootElement();
		
		List<Element> list;
		if (root.hasContent()) {
			list = root.elements();
			for (Element e : list) {
				if (e.getName().equals("cxqq")) {
					String BDHM = StringUtil.trim(e.attributeValue("BDHM"));
					String LB = StringUtil.trim(e.attributeValue("LB"));
					String XZ = StringUtil.trim(e.attributeValue("XZ"));
					String XM = StringUtil.trim(e.attributeValue("XM"));
					String GJ = StringUtil.trim(e.attributeValue("GJ"));
					String ZJLX = StringUtil.trim(e.attributeValue("ZJLX"));
					String DSRZJHM	= StringUtil.trim(e.attributeValue("DSRZJHM"));
					
					String FZJG = StringUtil.trim(e.attributeValue("FZJG"));
					String JGHM = StringUtil.trim(e.attributeValue("JGHM"));
					String CBR = StringUtil.trim(e.attributeValue("CBR"));
					String AH = StringUtil.trim(e.attributeValue("AH"));
					String CKKSSJ = StringUtil.trim(e.attributeValue("CKKSSJ"));
					String CKJSSJ = StringUtil.trim(e.attributeValue("CKJSSJ"));
					String ZWFKSJ	= StringUtil.trim(e.attributeValue("ZWFKSJ"));
					String SQSJ	= StringUtil.trim(e.attributeValue("SQSJ"));
					
					CxqqInfo_JCY item = new CxqqInfo_JCY();
					item.setBDHM(BDHM);
					item.setLB(LB);
					item.setXZ(XZ);
					item.setXM(XM);
					item.setGJ(GJ);
					item.setZJLX(ZJLX);
					item.setDSRZJHM(DSRZJHM);
					item.setFZJG(FZJG);
					item.setJGHM(JGHM);
					item.setCBR(CBR);
					item.setAH(AH);
					item.setCXKSSJ(CKKSSJ);
					item.setCXJSSJ(CKJSSJ);
					item.setZWFKSJ(ZWFKSJ);
					item.setSQSJ(SQSJ);
					
					result.add(item);
					
			   }
			}
		}
		
		return result;
	}
	
	public static void main(String[] args){
		List list = XmlUtil.parseXML("e:\\test.xml");
		
		System.out.println("=====list.size===="+list.size());
		for(int i=0;i<list.size();i++){
			CxqqInfo_JCY item = (CxqqInfo_JCY)list.get(i);
			System.out.println(item.getBDHM());
			System.out.println(item.getAH());
			System.out.println(item.getCBR());
			System.out.println(item.getCXJSSJ());
			System.out.println(item.getCXKSSJ());
			System.out.println(item.getDSRZJHM());
			System.out.println(item.getFZJG());
			System.out.println(item.getGJ());
			System.out.println(item.getJGHM());
			System.out.println(item.getLB());
			System.out.println(item.getSQSJ());
			System.out.println(item.getXM());
			System.out.println(item.getXZ());
			System.out.println(item.getZJLX());
			System.out.println(item.getZWFKSJ());
		}
	}

}

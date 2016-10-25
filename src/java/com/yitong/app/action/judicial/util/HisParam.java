package com.yitong.app.action.judicial.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 请求参数工具类
 * 
 * @author zxw
 * 
 */
public class HisParam {

	public String getProperties(String Obj){
		Properties p = new Properties();
		try {
			p.load(this.getClass().getResourceAsStream("/conf.properties"));
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		String temp = (String) p.get(Obj);
		if(temp == null ){
			temp = "";	
		}
		return temp;
	}
}

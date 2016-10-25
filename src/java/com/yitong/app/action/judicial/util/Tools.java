package com.yitong.app.action.judicial.util;


/**
 * 请求参数工具类
 * 
 * @author zxw
 * 
 */
public class Tools 
{ 
     
    public static String quote(String s) { 
        int slashEIndex = s.indexOf("\\E"); 
        if (slashEIndex == -1) 
            return "\\Q" + s + "\\E"; 

        StringBuffer  sb = new StringBuffer(s.length() * 2); 
        sb.append("\\Q"); 
        slashEIndex = 0; 
        int current = 0; 
        while ((slashEIndex = s.indexOf("\\E", current)) != -1) { 
            sb.append(s.substring(current, slashEIndex)); 
            current = slashEIndex + 2; 
            sb.append("\\E\\\\E\\Q"); 
        } 
        sb.append(s.substring(current, s.length())); 
        sb.append("\\E"); 
        return sb.toString(); 
    } 
}

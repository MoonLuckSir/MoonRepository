package com.yitong.app.service.judicial.dxzpjymx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataValidation;


import com.yitong.commons.model.IListPage;
import com.yitong.commons.service.BaseService;

public class JymxService extends BaseService {
	
	//账户交易明细查询
	@SuppressWarnings("unchecked")
	public IListPage pageQueryJyTask(Map params, int pageNo){
		return this.pageQuery("jymx.pageQuery", "jymx.pageCount", 
				params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Map load(String params) {
		logger.info("jymx.loadById.........");
		return (Map) load("jymx.loadById", params);
	}
	
	//账户持卡主体查询
	@SuppressWarnings("unchecked")
	public Map ckload(String params) {
		logger.info("jymx.loadByCkId.........");
		return (Map) load("jymx.loadByCkId", params);
	}
	
	@SuppressWarnings("unchecked")
	public IListPage pageQueryCkTask(Map params, int pageNo){
		return this.pageQuery("jymx.pageQueryCkTask", "jymx.pageCountCkTask", 
				params, pageNo);
	}
	
	//客户全账户查询
	@SuppressWarnings("unchecked")
	public Map qzhload(String params) {
		logger.info("jymx.loadByQzhId.........");
		return (Map) load("jymx.loadByQzhId", params);
	}
	
	@SuppressWarnings("unchecked")
	public IListPage pageQueryQzhTask(Map params, int pageNo){
		return this.pageQuery("jymx.pageQueryQzhTask", "jymx.pageCountQzhTask", 
				params, pageNo);
	}
	
	//客户全账户查询反馈报文
	@SuppressWarnings("unchecked")
	public IListPage pageQueryFkTask(Map params, int pageNo){
		return this.pageQuery("jymx.pageQueryFkTask", "jymx.pageCountFkTask", 
				params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Map fkload(String params) {
		logger.info("jymx.loadByFkId.........");
		return (Map) load("jymx.loadByFkId", params);
	}
	
	//账户查询明细反馈
	@SuppressWarnings("unchecked")
	public IListPage pageQueryCxmxTask(Map params, int pageNo){
		return this.pageQuery("jymx.pageQueryCxmxTask", "jymx.pageCountCxmxTask", 
				params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Map cxmxload(Map params) {
		logger.info("jymx.loadByCxmxId.........");
		return (Map) load("jymx.loadByCxmxId", params);
	}
	
	//账户交易明细查询
	@SuppressWarnings("unchecked")
	public IListPage pageQueryCkfkTask(Map params, int pageNo){
		return this.pageQuery("jymx.pageQueryCkfkTask", "jymx.pageCountCkfkTask", 
				params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Map ckfkload(String params) {
		logger.info("jymx.loadByCkfkId.........");
		return (Map) load("jymx.loadByCkfkId", params);
	}

	//账户信息查询
	@SuppressWarnings("unchecked")
	public IListPage pageQueryZhTask(Map params, int pageNo){
		return this.pageQuery("jymx.pageQueryZhTask", "jymx.pageCountZhTask", 
				params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Map zhload(Map params) {
		logger.info("jymx.loadByZhId.........");
		return (Map) load("jymx.loadByZhId", params);
	}
	
	//查询冻结信息
	@SuppressWarnings("unchecked")
	public IListPage pageQueryDjTask(Map params, int pageNo){
		return this.pageQuery("jymx.pageQueryDjTask", "jymx.pageCountDjTask", 
				params, pageNo);
	}
	
	//获取查询请求信息
	@SuppressWarnings("unchecked")
	public List<Map> findZhjyqqList(Map params){
	
		return this.findList("jymx.findZhjyqqList", params);
	}
	
	//获取账户基本信息
	@SuppressWarnings("unchecked")
	public List<Map> findZhxxList(Map params){
	
		return this.findList("jymx.findZhxxList", params);
		
	}
	
	//获取账户交易明细信息
	@SuppressWarnings("unchecked")
	public List<Map> findJymxList(Map params){
	
		return this.findList("jymx.findJymxList", params);
		
	}
	
	//获取持卡主体查询请求信息
	@SuppressWarnings("unchecked")
	public List<Map> findCkztqqList(Map params){
	
		return this.findList("jymx.findCkztqqList", params);
	}
	
	//获取持卡主体查询结果信息
	@SuppressWarnings("unchecked")
	public List<Map> findCkztjgList(Map params){
	
		return this.findList("jymx.findCkztjgList", params);
	}
	
	//获取全账户查询请求信息
	@SuppressWarnings("unchecked")
	public List<Map> findQzhqqList(Map params){
	
		return this.findList("jymx.findQzhqqList", params);
	}
	
	//获取全账户账号信息
	@SuppressWarnings("unchecked")
	public List<Map> findQzhzhxxList(Map params){
	
		return this.findList("jymx.findQzhzhxxList", params);
	}
	
	//获取全账户冻结信息
	@SuppressWarnings("unchecked")
	public List<Map> findQzhdjxxList(Map params){
	
		return this.findList("jymx.findQzhdjxxList", params);
	}
		
	//生成交易明细请求Excel
	public File makeZhjyqqExcel(List<Map> zhjyqqList,String filePath) throws IOException{
		
		 HSSFWorkbook wk = new HSSFWorkbook();
         HSSFSheet sheet = wk.createSheet("zhjyqq");
         //设置表头 先获得表的第一行
         HSSFRow firstRow = sheet.createRow(0);
         int totalColNum = 27;
         //将第一行的所有列设置成数组
         HSSFCell[] firstCell = new HSSFCell[totalColNum];
         //将第一行所有列名写入到一个string数组里
         String[] colNames = new String[totalColNum];
         
         colNames[0] = "业务申请编号";
         colNames[1] = "交易类型编码"; 
         colNames[2] = "发送机构编号";
         colNames[3] = "传输报文流水号";
         colNames[4] = "案件编号";
         colNames[5] = "案件类型";
         colNames[6] = "紧急程度";
         colNames[7] = "主体类别";
         colNames[8] = "账户所属银行机构编码";
         colNames[9] = "账户所属银行名称";
         colNames[10] = "账户名";
         colNames[11] = "卡/折号";
         colNames[12] = "查询内容";
         colNames[13] = "交易流水起始时间";
         colNames[14] = "交易流水截止时间";
         colNames[15] = "查询事由";
         colNames[16] = "查询说明";
         colNames[17] = "申请时间";
         colNames[18] = "申请机构编码";
         colNames[19] = "申请机构名称";
         colNames[20] = "经办人证件类型";
         colNames[21] = "经办人证件号";
         colNames[22] = "经办人姓名";
         colNames[23] = "经办人电话";
         colNames[24] = "协查人证件类型";
         colNames[25] = "协查人证件号";
         colNames[26] = "协查人姓名";
         
       
         //循环遍历第一行所有列
         for(int i=0;i<totalColNum;i++){
             //挨个创建第一行的单元格
             firstCell[i] = firstRow.createCell(i);
             //设置第一行单元格的值 也就是表头的名称 这里的new HSSFRichTextString(colNames[i]) 是把值都转换为文本类型
             firstCell[i].setCellValue(new HSSFRichTextString(colNames[i]));
         }
         
         
         //下面的一段代码是将结果集里的值插入到excel中去
         //定义j为1 也就是从第二行开始插入rs值
         int j=1;
         //设置数据有效性对象
         DataValidation data_validation = null;
         
         for(Map m : zhjyqqList){
             //循环创建行 从第二行开始
             HSSFRow row = sheet.createRow(j);
             for(int i=0;i<totalColNum;i++){
                 //创建每个单元格
                 HSSFCell cell = row.createCell(i);
                 //为单元格赋值 同样的使用new HSSFRichTextString()是为了将值都转化为文本格式处理 
                 //rs.getString(i+1)结果集是从1开始遍历值的
                 if(i == 0){
                	  cell.setCellValue(new HSSFRichTextString((String)m.get("APPLICATIONID")));
                 }
                 if(i == 1){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("TXCODE")));
                }
                 if(i == 2){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("MESSAGEFROM")));
                }
                 if(i == 3){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("TRANSSERIALNUMBER")));
                }
                 if(i == 4){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("CASENUMBER")));
                }
                 if(i == 5){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("CASETYPE")));
                }
                if(i == 6){
               	  cell.setCellValue(new HSSFRichTextString("02".equals(((String)m.get("EMERGENCYLEVEL"))) ? "紧急":"正常"));
               	}
                 if(i == 7){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("SUBJECTTYPE")));
                }
                 if(i == 8){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("BANKID")));
                }
                 if(i == 9){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("BANKNAME")));
                }
                 if(i == 10){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("ACCOUNTNAME")));
                }
                 if(i == 11){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("CARDNUMBER")));
                }
                 if(i == 12){
                  String str = (String)m.get("INQUIRYMODE");
                  String INQUIRYMODE = "";
                 
                  if("01".equals(str)){
                	  INQUIRYMODE = "账户基本信息";
                  }else if("02".equals(str)){
                	  INQUIRYMODE = "账户交易明细";
                  }else if("03".equals(str)){
                	  INQUIRYMODE = "账户基本信息+交易明细";
                  }else{
                	  INQUIRYMODE = "账户明细快速查询";
                  }
               	  cell.setCellValue(new HSSFRichTextString(INQUIRYMODE));
                }
                 if(i == 13){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("INQUIRYPERIODSTART")));
                }
                 if(i == 14){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("INQUIRYPERIODEND")));
                }
                 if(i == 15){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("REASON")));
                }
                 if(i == 16){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("REMARK")));
                }
                if(i == 17){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("APPLICATIONTIME")));
                }
                 if(i == 18){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("APPLICATIONORGID")));
                }
                 
                 if(i == 19){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("APPLICATIONORGNAME")));
                }
                 
                 if(i == 20){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("OPERATORIDTYPE")));
                }
                 if(i == 21){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("OPERATORIDNUMBER")));
                }
                 if(i == 22){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("OPERATORNAME")));
                }
                if(i == 23){
               	  cell.setCellValue(new HSSFRichTextString((String)m.get("OPERATORPHONENUMBER")));
                }
                if(i == 24){
              	  cell.setCellValue(new HSSFRichTextString((String)m.get("INVESTIGATORIDTYPE")));
                }
                if(i == 25){
                  cell.setCellValue(new HSSFRichTextString((String)m.get("INVESTIGATORIDNUMBER")));
                }
                if(i == 26){
                  cell.setCellValue(new HSSFRichTextString((String)m.get("INVESTIGATORNAME")));
                }
               
               
             }
             //行累加
             j++;
         }
         //新建输出流
         OutputStream out = new FileOutputStream(filePath);
         //通过输出流将得到的excel输出到某路径下
         wk.write(out);
         //关闭输出流
         out.close();

         return new File(filePath);
       
     
	}
	
	//生成账户交易明细结果Excel
	public File makeZhjyjgExcel(List<Map> zhxxList,List<Map> jymxList ,String filePath) throws IOException{
		 HSSFWorkbook wk = new HSSFWorkbook();
         HSSFSheet sheet1 = wk.createSheet("zhxx");
         HSSFSheet sheet2 = wk.createSheet("jymx");
         //设置表头 先获得表的第一行
         HSSFRow firstRow1= sheet1.createRow(0);
         HSSFRow firstRow2= sheet2.createRow(0);
         int totalColNum1 = 40;
         int totalColNum2 = 31;
         //将第一行的所有列设置成数组
         HSSFCell[] firstCell1 = new HSSFCell[totalColNum1];
         HSSFCell[] firstCell2 = new HSSFCell[totalColNum2];
         //将第一行所有列名写入到一个string数组里
         String[] colNames1 = new String[totalColNum1];
         
         colNames1[0] = "业务申请编号";
         colNames1[1] = "接收机构ID"; 
         colNames1[2] = "交易类型编码";
         colNames1[3] = "传输报文流水号";
         colNames1[4] = "主账户名称";
         colNames1[5] = "主账户";
         colNames1[6] = "开户网点";
         colNames1[7] = "开户网点代码";
         colNames1[8] = "开户日期";
         colNames1[9] = "销户日期";
         colNames1[10] = "销户网点";
         colNames1[11] = "备注";
         colNames1[12] = "定位账户账号";
         colNames1[13] = "一般(子)账户序号";
         colNames1[14] = "一般(子)账户类别";
         colNames1[15] = "账户状态";
         colNames1[16] = "币种";
         colNames1[17] = "钞汇标志";
         colNames1[18] = "账户余额";
         colNames1[19] = "可用余额";
         colNames1[20] = "最后交易时间";
         colNames1[21] = "证照类型代码";
         colNames1[22] = "证照号码";
         colNames1[23] = "主体名称";
         colNames1[24] = "联系手机";
         colNames1[25] = "帐/卡代办人姓名";
         colNames1[26] = "帐/卡代办人证件类型";
         colNames1[27] = "帐/卡代办人证件号码";
         colNames1[28] = "住宅地址";
         colNames1[29] = "住宅电话";
         colNames1[30] = "工作单位";
         colNames1[31] = "单位地址";
         colNames1[32] = "单位电话";
         colNames1[33] = "邮箱地址";
         colNames1[34] = "法人代表";
         colNames1[35] = "法人代表证件类型";
         colNames1[36] = "法人代表证件号码";
         colNames1[37] = "客户原工商注册号码";
         colNames1[38] = "国税纳税号";
         colNames1[39] = "地税纳税号";
         
         String[] colNames2 = new String[totalColNum2];
         colNames2[0] = "业务申请编号";
         colNames2[1] = "主账户"; 
         colNames2[2] = "传输报文流水号";
         colNames2[3] = "借贷标志";
         colNames2[4] = "交易类型";
         colNames2[5] = "交易金额";
         colNames2[6] = "币种";
         colNames2[7] = "交易时间";
         colNames2[8] = "交易余额";
         colNames2[9] = "交易对方名称";
         colNames2[10] = "交易流水号";
         colNames2[11] = "交易对方证件号码";
         colNames2[12] = "交易对方账卡号";
         colNames2[13] = "交易摘要";
         colNames2[14] = "交易对方账号开户行";
         colNames2[15] = "交易网点代码";
         colNames2[16] = "交易网点名称";
         colNames2[17] = "传票号";
         colNames2[18] = "日志号";
         colNames2[19] = "凭证号";
         colNames2[20] = "凭证种类";
         colNames2[21] = "终端号";
         colNames2[22] = "现金标志";
         colNames2[23] = "交易发生地";
         colNames2[24] = "交易是否成功";
         colNames2[25] = "商户号";
         colNames2[26] = "商户名称";
         colNames2[27] = "MAC地址";
         colNames2[28] = "IP地址";
         colNames2[29] = "备注";
         colNames2[30] = "交易柜员号";
         
         //循环遍历第一行所有列
         for(int i=0;i<totalColNum1;i++){
             //挨个创建第一行的单元格
             firstCell1[i] = firstRow1.createCell(i);
             //设置第一行单元格的值 也就是表头的名称 这里的new HSSFRichTextString(colNames[i]) 是把值都转换为文本类型
             firstCell1[i].setCellValue(new HSSFRichTextString(colNames1[i]));
         }
         
         //循环遍历第一行所有列
         for(int i=0;i<totalColNum2;i++){
             //挨个创建第一行的单元格
             firstCell2[i] = firstRow2.createCell(i);
             //设置第一行单元格的值 也就是表头的名称 这里的new HSSFRichTextString(colNames[i]) 是把值都转换为文本类型
             firstCell2[i].setCellValue(new HSSFRichTextString(colNames2[i]));
         }
         
         //下面的一段代码是将结果集里的值插入到excel中去
         //定义j为1 也就是从第二行开始插入rs值
         int j=1;
         
         for(Map m1 : zhxxList){
             //循环创建行 从第二行开始
             HSSFRow row = sheet1.createRow(j);
             for(int i=0;i<totalColNum1;i++){
                 //创建每个单元格
                 HSSFCell cell = row.createCell(i);
                 //为单元格赋值 同样的使用new HSSFRichTextString()是为了将值都转化为文本格式处理 
                 //rs.getString(i+1)结果集是从1开始遍历值的
                 if(i == 0){
                	  cell.setCellValue(new HSSFRichTextString((String)m1.get("APPLICATIONID")));
                 } 
                 if(i == 1){
               	  cell.setCellValue(new HSSFRichTextString((String)m1.get("JSJG")));
                }
                 if(i == 2){
               	  cell.setCellValue(new HSSFRichTextString((String)m1.get("TXCODE")));
                }
                 if(i == 3){
               	  cell.setCellValue(new HSSFRichTextString((String)m1.get("TRANSSERIALNUMBER")));
                }
                 if(i == 4){
                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTNAME")));
                }
                 if(i == 5){
                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("CARDNUMBER")));
                }
                if(i == 6){
                	cell.setCellValue(new HSSFRichTextString((String)m1.get("DEPOSITBANKBRANCH")));
               	}
                 if(i == 7){
                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("DEPOSITBANKBRANCHCODE")));
                }
                 if(i == 8){
                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTOPENTIME")));
                }
                 if(i == 9){
                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTCANCELLATIONTIME")));
                }
                 if(i == 10){
                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTCANCELLATIONBRANCH")));
                }
                 if(i == 11){
                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("REMARK")));
                }
                 if(i == 12){
                	
                  cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTNUMBER")));
                }
                 if(i == 13){
                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTSERIAL")));
                }
                 if(i == 14){
                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTTYPE")));
                }
                 if(i == 15){
                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTSTATUS")));
                }
                 if(i == 16){
                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("CURRENCY")));
                }
                if(i == 17){
                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("CASHREMIT")));
                }
                 if(i == 18){
                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTBALANCE")));
                }
                 
                 if(i == 19){
                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("AVAILABLEBALANCE")));
                }
                 
                 if(i == 20){
                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("LASTTRANSACTIONTIME")));
                }
                 if(i == 21){
                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTCREDENTIALTYPE")));
                }
                 if(i == 22){
                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTCREDENTIALNUMBER")));
                }
                if(i == 23){
                	cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTSUBJECTNAME")));
                }
                if(i == 24){
                	cell.setCellValue(new HSSFRichTextString((String)m1.get("TELNUMBER")));
                }
                if(i == 25){
                	cell.setCellValue(new HSSFRichTextString((String)m1.get("CARDOPERATORNAME")));
                }
                if(i == 26){
                	cell.setCellValue(new HSSFRichTextString((String)m1.get("CARDOPERATORCREDENTIALTYPE")));
                }
                if(i == 27){
                	cell.setCellValue(new HSSFRichTextString((String)m1.get("CARDOPERATORCREDENTIALNUMBER")));
                }
                if(i == 28){
                	cell.setCellValue(new HSSFRichTextString((String)m1.get("RESIDENTADDRESS")));
                }
                if(i == 29){
                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("RESIDENTTELNUMBER")));
                }
                if(i == 30){
                	cell.setCellValue(new HSSFRichTextString((String)m1.get("WORKCOMPANYNAME")));
                }
                if(i == 31){
                	cell.setCellValue(new HSSFRichTextString((String)m1.get("WORKADDRESS")));
                }
                if(i == 32){
                	cell.setCellValue(new HSSFRichTextString((String)m1.get("WORKTELNUMBER")));
                }
                if(i == 33){
                	cell.setCellValue(new HSSFRichTextString((String)m1.get("EMAILADDRESS")));
                }
                if(i == 34){
                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("ARTIFICIALPERSONREP")));
                }
                if(i == 35){
                	cell.setCellValue(new HSSFRichTextString((String)m1.get("ARTIFICIALPERSONREPCREDENTIALTYPE")));
                }
                if(i == 36){
                	cell.setCellValue(new HSSFRichTextString((String)m1.get("ARTIFICIALPERSONREPCREDENTIALNUMBER")));
                }
                
                if(i == 37){
                	cell.setCellValue(new HSSFRichTextString((String)m1.get("BUSINESSLICENSENUMBER")));
                }
                if(i == 38){
                	cell.setCellValue(new HSSFRichTextString((String)m1.get("STATETAXSERIAL")));
                }
                if(i == 39){
                	cell.setCellValue(new HSSFRichTextString((String)m1.get("LOCALTAXSERIAL")));
                }
               
              
               
             }
             //行累加
             j++;
         }
         
         
         int k=1;
         
         for(Map m2 : jymxList){
             //循环创建行 从第二行开始
             HSSFRow row = sheet2.createRow(k);
             for(int i=0;i<totalColNum2;i++){
                 //创建每个单元格
                 HSSFCell cell = row.createCell(i);
                 //为单元格赋值 同样的使用new HSSFRichTextString()是为了将值都转化为文本格式处理 
                 //rs.getString(i+1)结果集是从1开始遍历值的
                 if(i == 0){
                	  cell.setCellValue(new HSSFRichTextString((String)m2.get("APPLICATIONID")));
                 } 
                 if(i == 1){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("CARDNUMBER")));
                }
                 if(i == 2){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("TRANSSERIALNUMBER")));
                }
                 if(i == 3){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("BORROWINGSIGNS")));
                }
                 if(i == 4){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("TRANSACTIONTYPE")));
                }
                 if(i == 5){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("TRANSACTIONAMOUNT")));
                }
                if(i == 6){
                	cell.setCellValue(new HSSFRichTextString((String)m2.get("CURRENCY")));
               	}
                 if(i == 7){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("TRANSACTIONTIME")));
                }
                 if(i == 8){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("ACCOUNTBALANCE")));
                }
                 if(i == 9){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("OPPONENTNAME")));
                }
                 if(i == 10){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("TRANSACTIONSERIAL")));
                }
                 if(i == 11){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("OPPONENTCREDENTIALNUMBER")));
                }
                 if(i == 12){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("OPPONENTACCOUNTNUMBER")));
                }
                 if(i == 13){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("TRANSACTIONREMARK")));
                }
                 if(i == 14){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("OPPONENTDEPOSITBANKID")));
                }
                 if(i == 15){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("TRANSACTIONBRANCHCODE")));
                }
                                    

                 if(i == 16){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("TRANSACTIONBRANCHNAME")));
                }
                if(i == 17){
                	cell.setCellValue(new HSSFRichTextString((String)m2.get("SUMMONSNUMBER")));
                }
                 if(i == 18){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("LOGNUMBER")));
                }
                 
                 if(i == 19){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("VOUCHERCODE")));
                }
                 
                 if(i == 20){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("VOUCHERTYPE")));
                }
                 if(i == 21){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("TERMINALNUMBER")));
                }
                 if(i == 22){
                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("CASHMARK")));
                }
                if(i == 23){
                	cell.setCellValue(new HSSFRichTextString((String)m2.get("TRANSACTIONADDRESS")));
                }
                if(i == 24){
                	cell.setCellValue(new HSSFRichTextString((String)m2.get("TRANSACTIONSTATUS")));
                }
                if(i == 25){
                	cell.setCellValue(new HSSFRichTextString((String)m2.get("MERCHANTCODE")));
                }
                if(i == 26){
                	cell.setCellValue(new HSSFRichTextString((String)m2.get("MERCHANTNAME")));
                }
                if(i == 27){
                	cell.setCellValue(new HSSFRichTextString((String)m2.get("MAC")));
                }
                if(i == 28){
                	cell.setCellValue(new HSSFRichTextString((String)m2.get("IPADDRESS")));
                }
                if(i == 29){
                	cell.setCellValue(new HSSFRichTextString((String)m2.get("REMARK")));
                }
                if(i == 30){
                	cell.setCellValue(new HSSFRichTextString((String)m2.get("TELLERCODE")));
                }
               
             }
             //行累加
             k++;
         }
         //新建输出流
         OutputStream out = new FileOutputStream(filePath);
         //通过输出流将得到的excel输出到某路径下
         wk.write(out);
         //关闭输出流
         out.close();

         return new File(filePath);
	}
	
	
		//生成持卡主体请求Excel
		public File makeCkztqqExcel(List<Map> ckztqqList,String filePath) throws IOException{
			
			 HSSFWorkbook wk = new HSSFWorkbook();
	         HSSFSheet sheet = wk.createSheet("ckztqq");
	         //设置表头 先获得表的第一行
	         HSSFRow firstRow = sheet.createRow(0);
	         int totalColNum = 24;
	         //将第一行的所有列设置成数组
	         HSSFCell[] firstCell = new HSSFCell[totalColNum];
	         //将第一行所有列名写入到一个string数组里
	         String[] colNames = new String[totalColNum];
	         
	         colNames[0] = "业务申请编号";
	         colNames[1] = "交易类型编码"; 
	         colNames[2] = "发送机构编号";
	         colNames[3] = "传输报文流水号";
	         colNames[4] = "案件编号";
	         colNames[5] = "案件类型";
	         colNames[6] = "紧急程度";
	         colNames[7] = "主体类别";
	         colNames[8] = "账户所属银行机构编码";
	         colNames[9] = "账户所属银行名称";
	         colNames[10] = "账户名";
	         colNames[11] = "卡/折号";
	         colNames[12] = "查询说明";
	         colNames[13] = "查询事由";
	         colNames[14] = "申请时间";
	         colNames[15] = "申请机构编码";
	         colNames[16] = "申请机构名称";
	         colNames[17] = "经办人证件类型";
	         colNames[18] = "经办人证件号";
	         colNames[19] = "经办人姓名";
	         colNames[20] = "经办人电话";
	         colNames[21] = "协查人证件类型";
	         colNames[22] = "协查人证件号";
	         colNames[23] = "协查人姓名";
	         
	         //循环遍历第一行所有列
	         for(int i=0;i<totalColNum;i++){
	             //挨个创建第一行的单元格
	             firstCell[i] = firstRow.createCell(i);
	             //设置第一行单元格的值 也就是表头的名称 这里的new HSSFRichTextString(colNames[i]) 是把值都转换为文本类型
	             firstCell[i].setCellValue(new HSSFRichTextString(colNames[i]));
	         }
	         
	         
	         //下面的一段代码是将结果集里的值插入到excel中去
	         //定义j为1 也就是从第二行开始插入rs值
	         int j=1;
	      
	         for(Map m : ckztqqList){
	             //循环创建行 从第二行开始
	             HSSFRow row = sheet.createRow(j);
	             for(int i=0;i<totalColNum;i++){
	                 //创建每个单元格
	                 HSSFCell cell = row.createCell(i);
	                 //为单元格赋值 同样的使用new HSSFRichTextString()是为了将值都转化为文本格式处理 
	                 //rs.getString(i+1)结果集是从1开始遍历值的
	                 if(i == 0){
	                	  cell.setCellValue(new HSSFRichTextString((String)m.get("APPLICATIONID")));
	                 }
	                 if(i == 1){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("TXCODE")));
	                }
	                 if(i == 2){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("MESSAGEFROM")));
	                }
	                 if(i == 3){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("TRANSSERIALNUMBER")));
	                }
	                 if(i == 4){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("CASENUMBER")));
	                }
	                 if(i == 5){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("CASETYPE")));
	                }
	                if(i == 6){
	               	  cell.setCellValue(new HSSFRichTextString("02".equals(((String)m.get("EMERGENCYLEVEL"))) ? "紧急":"正常"));
	               	}
	                 if(i == 7){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("SUBJECTTYPE")));
	                }
	                 if(i == 8){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("BANKID")));
	                }
	                 if(i == 9){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("BANKNAME")));
	                }
	                 if(i == 10){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("ACCOUNTNAME")));
	                }
	                 if(i == 11){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("CARDNUMBER")));
	                }
	                 if(i == 12){
	                	 cell.setCellValue(new HSSFRichTextString((String)m.get("REMARK")));
	                }
	                 if(i == 13){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("REASON")));
	                }
	                 if(i == 14){
	                	 cell.setCellValue(new HSSFRichTextString((String)m.get("APPLICATIONTIME")));
	                }
	                 if(i == 15){
	                	 cell.setCellValue(new HSSFRichTextString((String)m.get("APPLICATIONORGID")));
	                }
	                 if(i == 16){
	                	 cell.setCellValue(new HSSFRichTextString((String)m.get("APPLICATIONORGNAME")));
	                }
	                if(i == 17){
	                	cell.setCellValue(new HSSFRichTextString((String)m.get("OPERATORIDTYPE")));
	                }
	                 if(i == 18){
	                	 cell.setCellValue(new HSSFRichTextString((String)m.get("OPERATORIDNUMBER")));
	                }
	                 
	                 if(i == 19){
	                	 cell.setCellValue(new HSSFRichTextString((String)m.get("OPERATORNAME")));
	                }
	                 
	                 if(i == 20){
	                	 cell.setCellValue(new HSSFRichTextString((String)m.get("OPERATORPHONENUMBER")));
	                }
	                 if(i == 21){
	                	 cell.setCellValue(new HSSFRichTextString((String)m.get("INVESTIGATORIDTYPE")));
	                }
	                 if(i == 22){
	                	 cell.setCellValue(new HSSFRichTextString((String)m.get("INVESTIGATORIDNUMBER")));
	                }
	                if(i == 23){
	                	cell.setCellValue(new HSSFRichTextString((String)m.get("INVESTIGATORNAME")));
	                }
	                
	               
	             }
	             //行累加
	             j++;
	         }
	         //新建输出流
	         OutputStream out = new FileOutputStream(filePath);
	         //通过输出流将得到的excel输出到某路径下
	         wk.write(out);
	         //关闭输出流
	         out.close();

	         return new File(filePath);
	       
	     
		}
		
		//生成持卡主体查询结果Excel
		public File makeCkztjgExcel(List<Map> ckztjgList,String filePath) throws IOException{
				
				 HSSFWorkbook wk = new HSSFWorkbook();
		         HSSFSheet sheet = wk.createSheet("ckztjg");
		         //设置表头 先获得表的第一行
		         HSSFRow firstRow = sheet.createRow(0);
		         int totalColNum = 24;
		         //将第一行的所有列设置成数组
		         HSSFCell[] firstCell = new HSSFCell[totalColNum];
		         //将第一行所有列名写入到一个string数组里
		         String[] colNames = new String[totalColNum];
		         
		         colNames[0] = "传输报文流水号";
		         colNames[1] = "发送传输报文流水号"; 
		         colNames[2] = "接收机构ID";
		         colNames[3] = "交易类型编码";
		         colNames[4] = "查询证照号码";
		         colNames[5] = "业务申请编号";
		         colNames[6] = "联系手机";
		         colNames[7] = "查询证照类型代码";
		         colNames[8] = "帐/卡代办人证件类型";
		         colNames[9] = "查询主体名称";
		         colNames[10] = "住宅地址";
		         colNames[11] = "帐/卡代办人姓名";
		         colNames[12] = "工作单位";
		         colNames[13] = "帐/卡代办人证件号码";
		         colNames[14] = "单位电话";
		         colNames[15] = "住宅电话";
		         colNames[16] = "邮箱地址";
		         colNames[17] = "单位地址";
		         colNames[18] = "法人代表";
		         colNames[19] = "法人代表证件类型";
		         colNames[20] = "法人代表证件号码";
		         colNames[21] = "客户原工商注册号码";
		         colNames[22] = "国税纳税号";
		         colNames[23] = "地税纳税号";
		         
		         //循环遍历第一行所有列
		         for(int i=0;i<totalColNum;i++){
		             //挨个创建第一行的单元格
		             firstCell[i] = firstRow.createCell(i);
		             //设置第一行单元格的值 也就是表头的名称 这里的new HSSFRichTextString(colNames[i]) 是把值都转换为文本类型
		             firstCell[i].setCellValue(new HSSFRichTextString(colNames[i]));
		         }
		         
		         
		         //下面的一段代码是将结果集里的值插入到excel中去
		         //定义j为1 也就是从第二行开始插入rs值
		         int j=1;
		      
		         for(Map m : ckztjgList){
		             //循环创建行 从第二行开始
		             HSSFRow row = sheet.createRow(j);
		             for(int i=0;i<totalColNum;i++){
		                 //创建每个单元格
		                 HSSFCell cell = row.createCell(i);
		                 //为单元格赋值 同样的使用new HSSFRichTextString()是为了将值都转化为文本格式处理 
		                 //rs.getString(i+1)结果集是从1开始遍历值的
		                 if(i == 0){
		                	  cell.setCellValue(new HSSFRichTextString((String)m.get("TRANSSERIALNUMBER")));
		                 }
		                 if(i == 1){
		               	  cell.setCellValue(new HSSFRichTextString((String)m.get("NEWTRANSSERIALNUMBER")));
		                }
		                 if(i == 2){
		               	  cell.setCellValue(new HSSFRichTextString((String)m.get("JSJG")));
		                }
		                 if(i == 3){
		               	  cell.setCellValue(new HSSFRichTextString((String)m.get("TXCODE")));
		                }
		                 if(i == 4){
		               	  cell.setCellValue(new HSSFRichTextString((String)m.get("ACCOUNTCREDENTIALNUMBER")));
		                }
		                 if(i == 5){
		               	  cell.setCellValue(new HSSFRichTextString((String)m.get("APPLICATIONID")));
		                } 
		                if(i == 6){
		               	  cell.setCellValue(new HSSFRichTextString((String)m.get("TELNUMBER")));
		               	}
		                 if(i == 7){
		               	  cell.setCellValue(new HSSFRichTextString((String)m.get("ACCOUNTCREDENTIALTYPE")));
		                }
		                 if(i == 8){
		               	  cell.setCellValue(new HSSFRichTextString((String)m.get("CARDOPERATORCREDENTIALTYPE")));
		                }
		                 if(i == 9){
		               	  cell.setCellValue(new HSSFRichTextString((String)m.get("ACCOUNTSUBJECTNAME")));
		                }
		                 if(i == 10){
		               	  cell.setCellValue(new HSSFRichTextString((String)m.get("RESIDENTADDRESS")));
		                }
		                 if(i == 11){
		               	  cell.setCellValue(new HSSFRichTextString((String)m.get("CARDOPERATORNAME")));
		                }
		                 if(i == 12){
		                	 cell.setCellValue(new HSSFRichTextString((String)m.get("WORKCOMPANYNAME")));
		                }
		                 if(i == 13){
		               	  cell.setCellValue(new HSSFRichTextString((String)m.get("CARDOPERATORCREDENTIALNUMBER")));
		                }
		                 if(i == 14){
		                	 cell.setCellValue(new HSSFRichTextString((String)m.get("WORKTELNUMBER")));
		                }
		                 if(i == 15){
		                	 cell.setCellValue(new HSSFRichTextString((String)m.get("RESIDENTTELNUMBER")));
		                }
		                 if(i == 16){
		                	 cell.setCellValue(new HSSFRichTextString((String)m.get("EMAILADDRESS")));
		                }
		                if(i == 17){
		                	cell.setCellValue(new HSSFRichTextString((String)m.get("WORKADDRESS")));
		                }
		                 if(i == 18){
		                	 cell.setCellValue(new HSSFRichTextString((String)m.get("ARTIFICIALPERSONREP")));
		                }
		                 
		                 if(i == 19){
		                	 cell.setCellValue(new HSSFRichTextString((String)m.get("ARTIFICIALPERSONREPCREDENTIALTYPE")));
		                }
		                 
		                 if(i == 20){
		                	 cell.setCellValue(new HSSFRichTextString((String)m.get("ARTIFICIALPERSONREPCREDENTIALNUMBER")));
		                }
		                 if(i == 21){
		                	 cell.setCellValue(new HSSFRichTextString((String)m.get("BUSINESSLICENSENUMBER")));
		                }
		                 if(i == 22){
		                	 cell.setCellValue(new HSSFRichTextString((String)m.get("STATETAXSERIAL")));
		                }
		                if(i == 23){
		                	cell.setCellValue(new HSSFRichTextString((String)m.get("LOCALTAXSERIAL")));
		                }
		                
		               
		             }
		             //行累加
		             j++;
		         }
		         //新建输出流
		         OutputStream out = new FileOutputStream(filePath);
		         //通过输出流将得到的excel输出到某路径下
		         wk.write(out);
		         //关闭输出流
		         out.close();

		         return new File(filePath);
		       
		     
			}
		
		//生成持卡主体请求Excel
		public File makeQzhqqExcel(List<Map> qzhqqList,String filePath) throws IOException{
			
			 HSSFWorkbook wk = new HSSFWorkbook();
	         HSSFSheet sheet = wk.createSheet("qzhqq");
	         //设置表头 先获得表的第一行
	         HSSFRow firstRow = sheet.createRow(0);
	         int totalColNum = 27;
	         //将第一行的所有列设置成数组
	         HSSFCell[] firstCell = new HSSFCell[totalColNum];
	         //将第一行所有列名写入到一个string数组里
	         String[] colNames = new String[totalColNum];
	         
	         colNames[0] = "业务申请编号";
	         colNames[1] = "交易类型编码"; 
	         colNames[2] = "发送机构编号";
	         colNames[3] = "传输报文流水号";
	         colNames[4] = "案件编号";
	         colNames[5] = "案件类型";
	         colNames[6] = "紧急程度";
	         colNames[7] = "银行名称";
	         colNames[8] = "银行机构编码";
	         colNames[9] = "主体类别";
	         colNames[10] = "证照类型代码";
	         colNames[11] = "证照号码";
	         colNames[12] = "主体名称";
	         colNames[13] = "查询内容";
	         colNames[14] = "查询事由";
	         colNames[15] = "查询说明";
	         colNames[16] = "申请时间";
	         colNames[17] = "申请机构编码";
	         colNames[18] = "申请机构名称";
	         colNames[19] = "经办人证件类型";
	         colNames[20] = "经办人证件号";
	         colNames[21] = "经办人姓名";
	         colNames[22] = "经办人电话";
	         colNames[23] = "协查人证件类型";
	         colNames[24] = "协查人姓名";
	         colNames[25] = "协查人证件号";
	         //循环遍历第一行所有列
	         for(int i=0;i<totalColNum;i++){
	             //挨个创建第一行的单元格
	             firstCell[i] = firstRow.createCell(i);
	             //设置第一行单元格的值 也就是表头的名称 这里的new HSSFRichTextString(colNames[i]) 是把值都转换为文本类型
	             firstCell[i].setCellValue(new HSSFRichTextString(colNames[i]));
	         }
	         
	         
	         //下面的一段代码是将结果集里的值插入到excel中去
	         //定义j为1 也就是从第二行开始插入rs值
	         int j=1;
	      
	         for(Map m : qzhqqList){
	             //循环创建行 从第二行开始
	             HSSFRow row = sheet.createRow(j);
	             for(int i=0;i<totalColNum;i++){
	                 //创建每个单元格
	                 HSSFCell cell = row.createCell(i);
	                 //为单元格赋值 同样的使用new HSSFRichTextString()是为了将值都转化为文本格式处理 
	                 //rs.getString(i+1)结果集是从1开始遍历值的
	                 if(i == 0){
	                	  cell.setCellValue(new HSSFRichTextString((String)m.get("APPLICATIONID")));
	                 }
	                 if(i == 1){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("TXCODE")));
	                }
	                 if(i == 2){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("MESSAGEFROM")));
	                }
	                 if(i == 3){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("TRANSSERIALNUMBER")));
	                }
	                 if(i == 4){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("CASENUMBER")));
	                }
	                 if(i == 5){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("CASETYPE")));
	                }
	                if(i == 6){
	               	  cell.setCellValue(new HSSFRichTextString("02".equals(((String)m.get("EMERGENCYLEVEL"))) ? "紧急":"正常"));
	               	}
	                 if(i == 7){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("BANKNAME")));
	                }
	                 if(i == 8){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("BANKID")));
	                }
	                 if(i == 9){
	               	  cell.setCellValue(new HSSFRichTextString("2".equals(((String)m.get("SUBJECTTYPE"))) ? "法人主体":"自然人主体"));
	                }
	                 if(i == 10){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("ACCOUNTCREDENTIALTYPE")));
	                }
	                 if(i == 11){
	               	  cell.setCellValue(new HSSFRichTextString((String)m.get("ACCOUNTCREDENTIALNUMBER")));
	                }
	                 if(i == 12){
	                	 cell.setCellValue(new HSSFRichTextString((String)m.get("ACCOUNTSUBJECTNAME")));
	                }
	                 if(i == 13){ 
	               	  cell.setCellValue(new HSSFRichTextString("02".equals(((String)m.get("INQUIRYMODE"))) ? "账户信息":"账户基本信息") );
	                }
	                 if(i == 14){
	                	 cell.setCellValue(new HSSFRichTextString((String)m.get("REASON")));
	                }
	                 if(i == 15){
	                	 cell.setCellValue(new HSSFRichTextString((String)m.get("REMARK")));
	                }
	                 if(i == 16){
	                	 cell.setCellValue(new HSSFRichTextString((String)m.get("APPLICATIONTIME")));
	                }
	                if(i == 17){
	                	cell.setCellValue(new HSSFRichTextString((String)m.get("APPLICATIONORGID")));
	                }
	                 if(i == 18){
	                	 cell.setCellValue(new HSSFRichTextString((String)m.get("APPLICATIONORGNAME")));
	                }
	                 
	                 if(i == 19){
	                	 cell.setCellValue(new HSSFRichTextString((String)m.get("OPERATORIDTYPE")));
	                }
	                 
	                 if(i == 20){ 
	                	 cell.setCellValue(new HSSFRichTextString((String)m.get("OPERATORIDNUMBER")));
	                }
	                 if(i == 21){
	                	 cell.setCellValue(new HSSFRichTextString((String)m.get("OPERATORNAME")));
	                }
	                 if(i == 22){
	                	 cell.setCellValue(new HSSFRichTextString((String)m.get("OPERATORPHONENUMBER")));
	                }
	                if(i == 23){
	                	cell.setCellValue(new HSSFRichTextString((String)m.get("INVESTIGATORIDTYPE")));
	                }
	                if(i == 24){
	                	cell.setCellValue(new HSSFRichTextString((String)m.get("INVESTIGATORNAME")));
	                }
	                if(i == 25){
	                	cell.setCellValue(new HSSFRichTextString((String)m.get("INVESTIGATORIDNUMBER")));
	                }
	              
	               
	             }
	             //行累加
	             j++;
	         }
	         //新建输出流
	         OutputStream out = new FileOutputStream(filePath);
	         //通过输出流将得到的excel输出到某路径下
	         wk.write(out);
	         //关闭输出流
	         out.close();

	         return new File(filePath);
	       
	     
		}

		//生成账户交易明细结果Excel
		public File makeQzhjgExcel(List<Map> qzhzhxxList,List<Map> qzhdjList ,String filePath) throws IOException{
			 HSSFWorkbook wk = new HSSFWorkbook();
	         HSSFSheet sheet1 = wk.createSheet("zhxx");
	         HSSFSheet sheet2 = wk.createSheet("djxx");
	         //设置表头 先获得表的第一行
	         HSSFRow firstRow1= sheet1.createRow(0);
	         HSSFRow firstRow2= sheet2.createRow(0);
	         int totalColNum1 = 40;
	         int totalColNum2 = 31;
	         //将第一行的所有列设置成数组
	         HSSFCell[] firstCell1 = new HSSFCell[totalColNum1];
	         HSSFCell[] firstCell2 = new HSSFCell[totalColNum2];
	         //将第一行所有列名写入到一个string数组里
	         String[] colNames1 = new String[totalColNum1];
	         
	         colNames1[0] = "传输报文流水号";
	         colNames1[1] = "发送传输报文流水号"; 
	         colNames1[2] = "主账户名称";
	         colNames1[3] = "主账户";
	         colNames1[4] = "开户网点";
	         colNames1[5] = "开户网点代码";
	         colNames1[6] = "开户日期";
	         colNames1[7] = "销户日期";
	         colNames1[8] = "销户网点";
	         colNames1[9] = "备注";
	         colNames1[10] = "定位账户账号";
	         colNames1[11] = "一般(子)账户序号";
	         colNames1[12] = "一般(子)账户类别";
	         colNames1[13] = "账户状态";
	         colNames1[14] = "币种";
	         colNames1[15] = "钞汇标志";
	         colNames1[16] = "账户余额";
	         colNames1[17] = "可用余额";
	         colNames1[18] = "最后交易时间";
	        
	         
	         String[] colNames2 = new String[totalColNum2];
	         colNames2[0] = "业务申请编号";
	         colNames2[1] = "发送传输报文流水号 "; 
	         colNames2[2] = "措施序号";
	         colNames2[3] = "账号";
	         colNames2[4] = "卡号";
	         colNames2[5] = "冻结开始日";
	         colNames2[6] = "冻结截止日";
	         colNames2[7] = "冻结机关名称";
	         colNames2[8] = "币种";
	         colNames2[9] = "冻结金额";
	         colNames2[10] = "冻结措施类型";
	         
	         //循环遍历第一行所有列
	         for(int i=0;i<totalColNum1;i++){
	             //挨个创建第一行的单元格
	             firstCell1[i] = firstRow1.createCell(i);
	             //设置第一行单元格的值 也就是表头的名称 这里的new HSSFRichTextString(colNames[i]) 是把值都转换为文本类型
	             firstCell1[i].setCellValue(new HSSFRichTextString(colNames1[i]));
	         }
	         
	         //循环遍历第一行所有列
	         for(int i=0;i<totalColNum2;i++){
	             //挨个创建第一行的单元格
	             firstCell2[i] = firstRow2.createCell(i);
	             //设置第一行单元格的值 也就是表头的名称 这里的new HSSFRichTextString(colNames[i]) 是把值都转换为文本类型
	             firstCell2[i].setCellValue(new HSSFRichTextString(colNames2[i]));
	         }
	         
	         //下面的一段代码是将结果集里的值插入到excel中去
	         //定义j为1 也就是从第二行开始插入rs值
	         int j=1;
	         
	         for(Map m1 : qzhzhxxList){
	             //循环创建行 从第二行开始
	             HSSFRow row = sheet1.createRow(j);
	             for(int i=0;i<totalColNum1;i++){
	                 //创建每个单元格
	                 HSSFCell cell = row.createCell(i);
	                 //为单元格赋值 同样的使用new HSSFRichTextString()是为了将值都转化为文本格式处理 
	                 //rs.getString(i+1)结果集是从1开始遍历值的
	                 if(i == 0){
	                	  cell.setCellValue(new HSSFRichTextString((String)m1.get("TRANSSERIALNUMBER")));
	                 } 
	                 if(i == 1){
	               	  cell.setCellValue(new HSSFRichTextString((String)m1.get("NEWTRANSSERIALNUMBER")));
	                }
	                 if(i == 2){
	               	  cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTNAME")));
	                }
	                 if(i == 3){
	               	  cell.setCellValue(new HSSFRichTextString((String)m1.get("CARDNUMBER")));
	                }
	                 if(i == 4){
	                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("DEPOSITBANKBRANCH")));
	                }
	                 if(i == 5){
	                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("DEPOSITBANKBRANCHCODE")));
	                }
	                if(i == 6){
	                	cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTOPENTIME")));
	               	}
	                 if(i == 7){
	                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTCANCELLATIONTIME")));
	                }
	                 if(i == 8){
	                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTCANCELLATIONBRANCH")));
	                }
	                 if(i == 9){
	                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("REMARK")));
	                }
	                 if(i == 10){
	                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTNUMBER")));
	                }
	                 if(i == 11){
	                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTSERIAL")));
	                }
	                 if(i == 12){
	                	
	                  cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTTYPE")));
	                }
	                 if(i == 13){
	                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTSTATUS")));
	                }
	                 if(i == 14){
	                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("CURRENCY")));
	                }
	                 if(i == 15){
	                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("CASHREMIT")));
	                }
	                 if(i == 16){
	                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("ACCOUNTBALANCE")));
	                }
	                if(i == 17){
	                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("AVAILABLEBALANCE")));
	                }
	                 if(i == 18){
	                	 cell.setCellValue(new HSSFRichTextString((String)m1.get("LASTTRANSACTIONTIME")));
	                }
	                 
	             }
	             //行累加
	             j++;
	         }
	         
	         
	         int k=1;
	         
	         for(Map m2 : qzhdjList){
	             //循环创建行 从第二行开始
	             HSSFRow row = sheet2.createRow(k);
	             for(int i=0;i<totalColNum2;i++){
	                 //创建每个单元格
	                 HSSFCell cell = row.createCell(i);
	                 //为单元格赋值 同样的使用new HSSFRichTextString()是为了将值都转化为文本格式处理 
	                 //rs.getString(i+1)结果集是从1开始遍历值的
	                 if(i == 0){ 
	                	  cell.setCellValue(new HSSFRichTextString((String)m2.get("APPLICATIONID")));
	                 } 
	                 if(i == 1){
	                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("NEWTRANSSERIALNUMBER")));
	                }
	                 if(i == 2){
	                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("FREEZESERIAL")));
	                }
	                 if(i == 3){
	                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("ACCOUNTNUMBER")));
	                }
	                 if(i == 4){
	                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("CARDNUMBER")));
	                }
	                 if(i == 5){
	                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("FREEZESTARTTIME")));
	                }
	                if(i == 6){
	                	cell.setCellValue(new HSSFRichTextString((String)m2.get("FREEZEENDTIME")));
	               	}
	                 if(i == 7){
	                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("FREEZEDEPTNAME")));
	                }
	                 if(i == 8){
	                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("CURRENCY")));
	                }
	                 if(i == 9){
	                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("FREEZEBALANCE")));
	                }
	                 if(i == 10){
	                	 cell.setCellValue(new HSSFRichTextString((String)m2.get("FREEZETYPE")));
	                }
	                
	             }
	             //行累加
	             k++;
	         }
	         //新建输出流
	         OutputStream out = new FileOutputStream(filePath);
	         //通过输出流将得到的excel输出到某路径下
	         wk.write(out);
	         //关闭输出流
	         out.close();

	         return new File(filePath);
		}
	//将文件打包成压缩包
	public File compressFileListToZip(List<File> fileList,String strZipFilePath) throws IOException {
		 byte[] buffer = new byte[1024];

	     ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipFilePath));

		 for(int i=0;i<fileList.size();i++) {
			 FileInputStream fis = new FileInputStream(fileList.get(i));
			 out.putNextEntry(new ZipEntry(fileList.get(i).getName()));
			 int len;
	         //读入需要下载的文件的内容，打包到zip文件
             while((len = fis.read(buffer))>0) {
        	   out.write(buffer,0,len);
             }
             out.closeEntry();
             fis.close();
		 }
	         out.close();	
	         return new File(strZipFilePath);
	}
	
}

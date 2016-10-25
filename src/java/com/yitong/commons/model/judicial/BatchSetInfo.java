package com.yitong.commons.model.judicial;

public class BatchSetInfo {
	private String number;//批量设置编号
	private String batchType;//交易类型
	private String batchTypeName;//交易类型名称
	
	
	private String queryNoType;//支持查询账号类型
	private String needQueryDate;//是否需要查询日期条件
	private String isValid;//配置是否有效
	
	private String exportColumn;//字段名
	private String exportName;//字段中文名
	private String querySQL;//FROM语句
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getBatchType() {
		return batchType;
	}
	public void setBatchType(String batchType) {
		this.batchType = batchType;
	}
	public String getBatchTypeName() {
		return batchTypeName;
	}
	public void setBatchTypeName(String batchTypeName) {
		this.batchTypeName = batchTypeName;
	}
	public String getQueryNoType() {
		return queryNoType;
	}
	public void setQueryNoType(String queryNoType) {
		this.queryNoType = queryNoType;
	}
	public String getNeedQueryDate() {
		return needQueryDate;
	}
	public void setNeedQueryDate(String needQueryDate) {
		this.needQueryDate = needQueryDate;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getExportColumn() {
		return exportColumn;
	}
	public void setExportColumn(String exportColumn) {
		this.exportColumn = exportColumn;
	}
	public String getExportName() {
		return exportName;
	}
	public void setExportName(String exportName) {
		this.exportName = exportName;
	}
	public String getQuerySQL() {
		return querySQL;
	}
	public void setQuerySQL(String querySQL) {
		this.querySQL = querySQL;
	}
	
	
}

package com.yitong.commons.model.system;


public class SysvarInfo {

	// Fields
	private String varId;
	private String varName;
	private String varType;
	private String value;
	private String varDesc; 
	// 子菜单
	public String getVarId() {
		return varId;
	}
	public void setVarId(String varId) {
		this.varId = varId;
	}
	public String getVarName() {
		return varName;
	}
	public void setVarName(String varName) {
		this.varName = varName;
	}
	public String getVarType() {
		return varType;
	}
	public void setVarType(String varType) {
		this.varType = varType;
	}

	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getVarDesc() {
		return varDesc;
	}
	public void setVarDesc(String varDesc) {
		this.varDesc = varDesc;
	}

}
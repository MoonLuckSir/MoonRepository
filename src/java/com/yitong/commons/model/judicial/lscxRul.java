package com.yitong.commons.model.judicial;

public class lscxRul {
	private String RULE_ID;//	规则ID
	private String RULE_NAME;//	规则名称
	private String RULE_VALUE;//	规则值
	private String RULE_DESC;// 	规则描述
	private String CONMX_NO;//		条件编号
	
	public String getRULE_ID() {
		return RULE_ID;
	}
	public void setRULE_ID(String rule_id) {
		RULE_ID = rule_id;
	}
	public String getRULE_NAME() {
		return RULE_NAME;
	}
	public void setRULE_NAME(String rule_name) {
		RULE_NAME = rule_name;
	}
	public String getRULE_VALUE() {
		return RULE_VALUE;
	}
	public void setRULE_VALUE(String rule_value) {
		RULE_VALUE = rule_value;
	}
	public String getRULE_DESC() {
		return RULE_DESC;
	}
	public void setRULE_DESC(String rule_desc) {
		RULE_DESC = rule_desc;
	}
	public String getCONMX_NO() {
		return CONMX_NO;
	}
	public void setCONMX_NO(String conmx_no) {
		CONMX_NO = conmx_no;
	}


}

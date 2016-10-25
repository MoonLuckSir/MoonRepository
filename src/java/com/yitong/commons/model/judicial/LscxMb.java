package com.yitong.commons.model.judicial;

public class LscxMb {
	private String REPMOD_NO;//	历史查询模板编号	主键
	private String REP_NO;//	历史查询编号	
	private String REP_NAME;//	历史查询名称	
	private String SELECT_SQL;// 	要查询的字段	
	private String FROM_SQL;//	来源数据集	
	private String ORDER_SQL;//	排序字段	
	private String CHECKIN_USER;//	制单人	
	private String CHECKIN_DEPT;//	制单部门	
	private String CHECKIN_ORGAN;//	制单机构	
	private String CHECKIN_TIME	;//制单时间	
	private String REP_DESC ;//	描述	
	private String STATE;//状态
	private String ORGSQL;//输出类型
	private String ISKORG;//
	private String ISSH;//
	public String getREPMOD_NO() {
		return REPMOD_NO;
	}
	public void setREPMOD_NO(String repmod_no) {
		REPMOD_NO = repmod_no;
	}
	public String getREP_NO() {
		return REP_NO;
	}
	public void setREP_NO(String rep_no) {
		REP_NO = rep_no;
	}
	public String getREP_NAME() {
		return REP_NAME;
	}
	public void setREP_NAME(String rep_name) {
		REP_NAME = rep_name;
	}
	public String getSELECT_SQL() {
		return SELECT_SQL;
	}
	public void setSELECT_SQL(String select_sql) {
		SELECT_SQL = select_sql;
	}
	public String getFROM_SQL() {
		return FROM_SQL;
	}
	public void setFROM_SQL(String from_sql) {
		FROM_SQL = from_sql;
	}
	public String getORDER_SQL() {
		return ORDER_SQL;
	}
	public void setORDER_SQL(String order_sql) {
		ORDER_SQL = order_sql;
	}
	public String getCHECKIN_USER() {
		return CHECKIN_USER;
	}
	public void setCHECKIN_USER(String checkin_user) {
		CHECKIN_USER = checkin_user;
	}
	public String getCHECKIN_DEPT() {
		return CHECKIN_DEPT;
	}
	public void setCHECKIN_DEPT(String checkin_dept) {
		CHECKIN_DEPT = checkin_dept;
	}
	public String getCHECKIN_ORGAN() {
		return CHECKIN_ORGAN;
	}
	public void setCHECKIN_ORGAN(String checkin_organ) {
		CHECKIN_ORGAN = checkin_organ;
	}
	public String getCHECKIN_TIME() {
		return CHECKIN_TIME;
	}
	public void setCHECKIN_TIME(String checkin_time) {
		CHECKIN_TIME = checkin_time;
	}
	public String getREP_DESC() {
		return REP_DESC;
	}
	public void setREP_DESC(String rep_desc) {
		REP_DESC = rep_desc;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String state) {
		STATE = state;
	}
	
	public String getORGSQL() {
		return ORGSQL;
	}
	public void setORGSQL(String orgsql) {
		ORGSQL = orgsql;
	}
	public String getISKORG() {
		return ISKORG;
	}
	public void setISKORG(String iskorg) {
		ISKORG = iskorg;
	}
	public String getISSH() {
		return ISSH;
	}
	public void setISSH(String issh) {
		ISSH = issh;
	}


}

package com.yitong.commons.model;

public class UserSession {
	private String userId;
	private String userName;
	private String orgId;
	private String userType;
	private String orgLvl;
	private String orgParId;
	private String userTypeName;
	private String rptRight;
	private String orgName;
	// 实际操作人员
	private String optUser;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getOrgParId() {
		return orgParId;
	}

	public void setOrgParId(String orgParId) {
		this.orgParId = orgParId;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOptUser() {
		return optUser;
	}

	public void setOptUser(String optUser) {
		this.optUser = optUser;
	}

	public String getRptRight() {
		return rptRight;
	}

	public void setRptRight(String rptRight) {
		this.rptRight = rptRight;
	}

	public String getOrgLvl() {
		return orgLvl;
	}

	public void setOrgLvl(String orgLvl) {
		this.orgLvl = orgLvl;
	}

}

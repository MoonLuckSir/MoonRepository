package com.yitong.commons.model.system;

public class UserInfo {
	private String userId;
	private String userName;
	private String userPwd;
	private String orgId;
	private String userPos;
	private String userType;
	private String eMail;
	private String phone;
	private String userStus; 

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

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUserPos() {
		return userPos;
	}

	public void setUserPos(String userPos) {
		this.userPos = userPos;
	}

	public String getEMail() {
		return eMail;
	}

	public void setEMail(String mail) {
		eMail = mail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserStus() {
		return userStus;
	}

	public void setUserStus(String userStus) {
		this.userStus = userStus;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
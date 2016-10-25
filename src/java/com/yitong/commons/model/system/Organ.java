package com.yitong.commons.model.system;

public class Organ {
	private String orgId;
	private String orgName;
	private String orgLvl;
	private String orgParId;
	private String orgStus;
	private String orgDesc;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgStus() {
		return orgStus;
	}

	public void setOrgStus(String orgStus) {
		this.orgStus = orgStus;
	}

	public String getOrgLvl() {
		return orgLvl;
	}

	public void setOrgLvl(String orgLvl) {
		this.orgLvl = orgLvl;
	}

	public String getOrgParId() {
		return orgParId;
	}

	public void setOrgParId(String orgParId) {
		this.orgParId = orgParId;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}
}

package com.yitong.commons.model;

import java.util.List;

import com.yitong.commons.model.system.Organ;

public interface IOrgTreeService {

	/**
	 * 机构子机构
	 * 
	 * @param orgNo
	 * @return
	 */
	public abstract List findChildrenByOrgId(String orgId);

	/**
	 * 查询某机构的某类型系统用户
	 * 
	 * @param OrgId
	 * @return
	 */
	public abstract List findUsers(String orgId, String userType);

	/**
	 * 获取机构信息
	 * 
	 * @param OrgId
	 * @return
	 */
	public abstract Organ loadOrganById(String orgId);

}
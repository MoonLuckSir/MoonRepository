package com.yitong.app.service.judicial;

import java.util.Map;

import com.yitong.commons.model.IListPage;
import com.yitong.commons.service.BaseService;

public class selConService extends BaseService {
	public IListPage findResuList(Map params, int pageNo) {
		return pageQuery( "selcon.pageQuery","selcon.pageCount", params, pageNo, 10);
	}
	
	public Map findselSetMap (Map params) {
		return (Map)load("selSet.query",params);
	}

}

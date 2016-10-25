package com.yitong.commons.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.yitong.commons.dao.BaseDao;
import com.yitong.commons.model.IListPage;

public interface IBaseService {

	public abstract void setIbatisDao(BaseDao ibatisDao);

	/*
	 * 新增
	 */
	public abstract Object insert(String statementName, Object paramMap);

	/*
	 * 更新
	 */
	public abstract boolean update(String statementName, Object paramMap);

	/*
	 * 删除
	 */
	public abstract boolean delete(String statementName, Object paramMap);

	/*
	 * 加载单条记录
	 */
	public abstract Object load(String statementName, Object paramMap);

	/*
	 * 加载多条记录
	 */
	public abstract List findList(String statementName, Object paramMap);

	/*
	 * 加载自定义参数
	 */
	public abstract List findParamters(String paramType);

	/*
	 * 按默认行数分页
	 */
	public abstract IListPage pageQuery(String queryStatementName,
			String countStatementName, Map paramMap, int pageNo);

	/*
	 * 按指定行数分页
	 */
	public abstract IListPage pageQuery(String queryStatementName,
			String countStatementName, Map paramMap, int pageNo, int pageSize);
	
	public IListPage pageQueryBigdata(List<Map> COLList, Map params, int pageNo, int pageSize) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException;		
	
	public IListPage pageQueryBigdataByJoin(String REPMOD_NO,List<Map> COLList, Map params, int pageNo, int pageSize) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException ;

	/**
	 * 保存系统访问日志
	 * 
	 * @param params
	 * @return
	 */
	public abstract boolean saveLog(Map paramMap);

}
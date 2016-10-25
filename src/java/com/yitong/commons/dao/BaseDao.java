/**
 * ibatis 基本操作方法
 * @author YQL
 */
package com.yitong.commons.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ibatis.sqlmap.client.SqlMapExecutor;

public class BaseDao extends SqlMapClientDaoSupport {

	private static final Log log = LogFactory.getLog(BaseDao.class);

	/*
	 * 新增
	 * 
	 * @see com.yitong.app.dao.base.IBaseIbatisDAO#insert(java.lang.String,
	 *      java.lang.Object)
	 */
	public Object insert(String statementName, Object paramMap) {
		log.debug("ibatis insert " + statementName);
		return getSqlMapClientTemplate().insert(statementName, paramMap);
	}

	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("key1", "1");
		map.put("key2", "2");
		System.out.println(map.get("key3"));
	}
	
	/*
	 * 更新
	 * 
	 * @see com.yitong.app.dao.base.IBaseIbatisDAO#update(java.lang.String,
	 *      java.lang.Object)
	 */
	public boolean update(String statementName, Object paramMap) {
		log.debug("ibatis update " + statementName);
		return this.getSqlMapClientTemplate().update(statementName, paramMap) > 0;
	}

	/*
	 * 删除
	 * 
	 * @see com.yitong.app.dao.base.IBaseIbatisDAO#delete(java.lang.String,
	 *      java.lang.Object)
	 */
	public boolean delete(String statementName, Object paramMap) {
		log.debug("ibatis delete " + statementName);
		return getSqlMapClientTemplate().delete(statementName, paramMap) > 0;
	}

	/*
	 * 加载单条记录
	 * 
	 * @see com.yitong.app.dao.base.IBaseIbatisDAO#load(java.lang.String,
	 *      java.lang.Object)
	 */
	public Object load(String statementName, Object paramMap) {
		log.debug("ibatis load " + statementName);
		return getSqlMapClientTemplate().queryForObject(statementName, paramMap);
	}
	
	@SuppressWarnings("unchecked")
	public Map loadByType(String statementName, Map paramMap) {
		log.debug("ibatis load " + statementName);
		return (Map)getSqlMapClientTemplate().queryForObject(statementName, paramMap);
	}

	/*
	 * 可用于计数
	 * 
	 * @see com.yitong.app.dao.base.IBaseIbatisDAO#query4Int(java.lang.String,
	 *      java.lang.Object)
	 */
	public int queryForInt(String statementName, Object paramMap) {
		logger.info("BaseDao......queryForInt");
		Integer rst = (Integer) load(statementName, paramMap);
		return rst == null ? 0 : rst.intValue();
	}

	/*
	 * 查询文本
	 * 
	 * @see com.yitong.app.dao.base.IBaseIbatisDAO#query4Str(java.lang.String,
	 *      java.lang.Object)
	 */
	public String queryForStr(String statementName, Object paramMap) {
		return (String) load(statementName, paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.app.dao.base.IBaseIbatisDAO#query4List(java.lang.String,
	 *      java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public List queryForList(String statementName, Object paramObj) {
		return this.getSqlMapClientTemplate().queryForList(statementName,
				paramObj);
	}

	/**
	 * 批量更新
	 * 
	 * @param statementName
	 * @param datas
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean batch4Update(final String statementName, final List datas) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Iterator iter = datas.iterator(); iter.hasNext();) {
					Map data = (Map) iter.next();
					executor.insert(statementName, data);
				}
				executor.executeBatch();
				return null;
			}
		});
		return true;
	}

	/**
	 * 批量更新 可指定提交记录数
	 * 
	 * @param statementName
	 * @param datas
	 * @param batchSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean batch4Update(final String statementName, final List datas,
			final int batchSize) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				int batch = 0;
				for (Iterator iter = datas.iterator(); iter.hasNext();) {
					Map data = (Map) iter.next();
					executor.insert(statementName, data);
					batch++;
					if (batch > batchSize) {
						batch = 0;
						executor.executeBatch();
					}
				}
				if (batch > 0)
					executor.executeBatch();
				return null;
			}
		});
		return true;
	}

	@SuppressWarnings("unchecked")
	private Map _caches = new HashMap();

	public void clearCaches(Object key) {
		_caches.remove(key);
	}

	@SuppressWarnings("unchecked")
	public void putCaches(Object key, Object value) {
		_caches.put(key, value);
	}

	public Object getCaches(Object key) {
		return _caches.get(key);
	}

}

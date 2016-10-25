package com.yitong.commons.model;

/**
 * 分页对象. 包含当前页数据及分页信息如总记录数.
 * 
 * @author uke
 */
public interface IListPage {

	public final static int DEFAULT_PAGE_SIZE = Properties.getInt("PAGE_SIZE");

	public abstract String getForm();

	public abstract void setForm(String form);

	public abstract long getStart();

	public abstract void setStart(long start);

	public abstract void setPageSize(int pageSize);

	public abstract void setTotalCount(long totalCount);

	/**
	 * 取每页数据容量.
	 */
	public abstract int getPageSize();

	/**
	 * 取总记录数.
	 */
	public abstract long getTotalCount();

	/**
	 * 取总页数.
	 */
	public abstract long getTotalPageCount();

	/**
	 * 取当前页中的记录.
	 */
	public abstract Object getResult();

	/**
	 * 取该页当前页码,页码从1开始.
	 */
	public abstract long getCurrentPageNo();

	/**
	 * 该页是否有下一页.
	 */
	public abstract boolean hasNextPage();

	/**
	 * 该页是否有上一页.
	 */
	public abstract boolean hasPreviousPage();

	/**
	 * 获取HTML内容
	 * 
	 * @return
	 */
	public abstract String getFooterHtml();

	/**
	 * 分页按钮栏
	 * 
	 * @return
	 */
	public abstract String getToolbarHtml();

}
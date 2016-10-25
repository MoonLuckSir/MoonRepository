package com.yitong.commons.model.inner;

import java.util.ArrayList;

import com.yitong.commons.model.IListPage;
import com.yitong.commons.util.StringUtil;

/**
 * 分页对象. 包含当前页数据及分页信息如总记录数.
 * 
 * @author uke
 */
public class ListPage implements IListPage {

	private static String DEFAULT_FORM = "listForm";
	private int pageSize = DEFAULT_PAGE_SIZE; // 每页的记录数

	private long start; // 当前页第一条数据在List中的位置,从0开始

	private Object data; // 当前页中存放的记录,类型一般为List

	private long totalCount; // 总记录数
	private long totalPageCount;// 總頁數

	private String form = DEFAULT_FORM; // 页面Form名称

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#getForm()
	 */
	public String getForm() {
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#setForm(java.lang.String)
	 */
	public void setForm(String form) {
		this.form = form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#getStart()
	 */
	public long getStart() {
		return start;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#setStart(long)
	 */
	public void setStart(long start) {
		this.start = start;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#setPageSize(int)
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#setTotalCount(long)
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#getPageSize()
	 */
	public int getPageSize() {
		return this.pageSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#getTotalCount()
	 */
	public long getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 构造方法，只构造空页.
	 */
	public ListPage() {
		this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList());
	}

	/**
	 * 默认构造方法.
	 * 
	 * @param start
	 *            本页数据在数据库中的起始位置
	 * @param totalSize
	 *            数据库中总记录条数
	 * @param pageSize
	 *            本页容量
	 * @param data
	 *            本页包含的数据
	 */
	public ListPage(long start, long totalSize, int pageSize, Object data) {
		this.start = start;
		this.totalCount = totalSize;
		this.pageSize = pageSize;
		this.data = data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#getTotalPageCount()
	 */
	public long getTotalPageCount() {
		if (totalPageCount == 0) {
			if (totalCount % pageSize == 0)
				totalPageCount = totalCount / pageSize;
			else
				totalPageCount = totalCount / pageSize + 1;
		}
		return totalPageCount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#getResult()
	 */
	public Object getResult() {
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#getCurrentPageNo()
	 */
	public long getCurrentPageNo() {
		return start / pageSize + 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#hasNextPage()
	 */
	public boolean hasNextPage() {
		return this.getCurrentPageNo() < this.getTotalPageCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.yitong.commons.model.IPage#hasPreviousPage()
	 */
	public boolean hasPreviousPage() {
		return this.getCurrentPageNo() > 1;
	}

	/**
	 * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
	 * 
	 * @param pageNo
	 * @return
	 */
	public static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
	}

	/**
	 * 获取任一页第一条数据在数据集的位置.
	 * 
	 * @param pageNo
	 *            从1开始的页号
	 * @param pageSize
	 *            每页记录条数
	 * @return 该页第一条数据
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		return (pageNo - 1) * pageSize;
	}

	/*
	 * 列表页面提示
	 * 
	 * @see com.yitong.commons.model.IPage#getFooterHtml()
	 */
	public String getFooterHtml() {
		long curPageNo = getCurrentPageNo();
		long totalPage = getTotalPageCount();
		StringBuffer html = new StringBuffer();
		html.append("【第").append(curPageNo).append("页】");
		html.append("【共").append(totalPage).append("页】");
		html.append("【共").append(getTotalCount()).append("条】");

		// 顯示快進頁面
		if (totalPage > 2) {
			html.append("页码：<input id='_gotoPageNo' class='page_no' maxlength='5'>");
			html.append("<a href='javascript:_gotoPage2();'>快速进入<span class='lastPage'></span></a>"); 
			html.append("<script type='text/javascript'>");
			html.append("function _gotoPage2(){");
			html.append("var inpt = g('_gotoPageNo');");
			html.append("var pageNo = inpt.value*1;");
			// 頁碼等於當前頁碼時，不飜頁
			html.append("if(").append(curPageNo).append(" == pageNo ){return;");
			// 頁碼是否超出範圍
			html.append("}else if (").append(totalPage).append(" < pageNo){");
			html.append("showErrorMsg('页码超出范围!');return;");
			html.append("}else if ( 1 > pageNo){");
			html.append("showErrorMsg('页码超出范围!');return;");
			html.append("}");
			// 頁碼跳轉
			html.append(getForm()).append(".pageNo.value = pageNo;");
			html.append("showWaitPanel('');");
			html.append("setTimeout('").append(getForm()).append(".submit()',100);");
			html.append("}");
			html.append("</script>");
		}
		return html.toString();
	}

	/**
	 * 翻页控制
	 * 
	 * @return
	 */
	public String getToolbarHtml() {
		StringBuffer html = new StringBuffer();
		String temp = "<span class='{0}' onclick='gotoPage(" + getForm() + ",{1})'></span>";
		String temp2 = "<span class='{0}'></span>";
		long curPageNo = getCurrentPageNo();
		long totalPage = getTotalPageCount();
		if (curPageNo > 1) {
			html.append(StringUtil.getMessage(temp, "firstPage", "1"));
			html.append(StringUtil.getMessage(temp, "prevPage", "" + (curPageNo - 1)));
		} else {
			html.append(StringUtil.getMessage(temp2, "firstDis2"));
			html.append(StringUtil.getMessage(temp2, "prevDis2"));
		} 
		html.append("第").append(curPageNo).append("页"); 
		if (curPageNo < totalPage) {
			html.append(StringUtil.getMessage(temp, "nextPage", "" + (curPageNo + 1)));
			html.append(StringUtil.getMessage(temp, "lastPage", "" + totalPage));
		} else {
			html.append(StringUtil.getMessage(temp2, "nextDis2"));
			html.append(StringUtil.getMessage(temp2, "lastDis2"));
		} 
		return html.toString();
	}
}
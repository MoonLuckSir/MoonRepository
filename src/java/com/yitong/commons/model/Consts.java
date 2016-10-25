package com.yitong.commons.model;

/**
 * 基础属性
 * 
 * @author uke
 * 
 */
public interface Consts {

	public static final boolean DEBUG = Properties.getBoolean("DEBUG"); 
	public static final String ROOT_DIR_RPT = Properties
			.getString("ROOT_DIR_RPT");
	public static final String RPT_NAME = Properties.getString("RPT_NAME");
	/**
	 * 缺省密码
	 */
	public static final String DEF_PWD = Properties.getString("DEF_PWD");

	/**
	 * 缺省分頁記錄數
	 */
	public static final int PAGE_SIZE = Properties.getInt("PAGE_SIZE");

	/**
	 * 用户Session标识
	 */
	public static final String USR_SESS = "user";
	public static final String USR_JSON = "userData";
	/**
	 * 报表对象标识
	 */
	public static final String RPT_VO = "rpt";
	public static final String PARAM_COVER = "paramCover";

	/**
	 * 报表查询菜单编号
	 */
	public static final String RPT_MENU_NO = Properties
			.getString("RPT_MENU_NO");
	public static final String RPT_MENU_URL = Properties
			.getString("RPT_MENU_URL");
	public static final String RPT_MENU_IMG = Properties
			.getString("RPT_MENU_IMG");
	public static final String RPT_MENU_IMG2 = Properties
			.getString("RPT_MENU_IMG2");
	public static final String RPT_TYPE = Properties.getString("RPT_TYPE");

	/**
	 * 公共页面链接：空白页面
	 */
	public static String BLANK = "blank";
	/**
	 * 公共页面链接：预置页面，提示：正在开发中……
	 */
	public static String INPROGRESS = "inprogress";
	/**
	 * 公共页面链接：操作失败警示页面,返回时不刷新原页面
	 */
	public static String FAILURE = "failure";
	/**
	 * 公共页面链接: 操作成功提示页面,参数以_下划线标识
	 */
	public static String SUCCESS = "success";
	/**
	 * 公共页面链接：操作失败警示页面,返回时刷新原页面
	 */
	public static String FAILEXT = "failext";
	/**
	 * 公共页面链接: 操作成功提示页面,普通参数
	 */
	public static String SUCCEXT = "succext";
	/**
	 * 公共页面链接: 翻页工具栏
	 */
	public static String PAGE_FOOTER = "pfooter";
	/**
	 * 公共页面链接：報表列表页面
	 */
	public static String RPT_LIST = "rptList";
	/**
	 * 公共页面链接：報表單頁展現
	 */
	public static String RPT_VIEW = "rptView";
	/**
	 * 公共页面链接：報表單頁內嵌展現
	 */
	public static String RPT_INNER_VIEW = "rptInnerView";
	/**
	 * 项目名称
	 */
	public static final String PROJECT = Properties.getString("PROJECT");
	/**
	 * 数据库类型
	 */
	public static final String DBTYPE = Properties.getString("DBTYPE");
	/**
	 * SOCKETIP
	 */
	public static final String SOCKETIP = Properties.getString("SOCKETIP");
	/**
	 * SOCKETPORT
	 */
	public static final String SOCKETPORT = Properties.getString("SOCKETPORT");

}

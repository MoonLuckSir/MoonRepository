package com.yitong.commons.model;
import java.util.Collection;

import com.yitong.commons.model.system.Organ;
import com.yitong.commons.model.system.UserInfo;
import com.yitong.commons.util.ParamUtil;
import com.yitong.commons.util.sort.Compare;
import com.yitong.commons.util.sort.SortVector;

/**
 * 机构人员树结构生成对象
 * 
 * @author uke
 * 
 */
public class OPTreeHelper {

	/**
	 * 缺省顶级机构
	 */
	public static final String ROOT = Properties.getString("ORG_ROOT");

	private boolean showSon = false;
	private boolean showUser = false;
	private String userType = null;
	private int orgLvl = 5;

	private IOrgTreeService orgService;

	public OPTreeHelper(IOrgTreeService orgService) {
		this.orgService = orgService;
	}

	/**
	 * 获取机构及机构人员信息
	 * 
	 * @param orgNo
	 * @return
	 */
	public String getJsonData4OPTree(String orgNo) {
		if (ParamUtil.isEmpty(orgNo)) {
			return "{orgSize:0,usrSize:0}";
		}
		Organ organ = (Organ) orgService.loadOrganById(orgNo);
		if (organ == null) {
			return "{orgSize:0,usrSize:0}";
		}
		StringBuffer bf = new StringBuffer();
		bf.append("{");
		
		// 加载子机构
		Collection orgChildren = orgService.findChildrenByOrgId(orgNo);
		if (orgChildren == null || orgChildren.isEmpty()) {
			bf.append("orgSize:0,");
		} else {
			orgChildren = getSortOrgans(orgChildren);
			bf.append("orgSize:").append(orgChildren.size()).append(",");
			bf.append("organ:[");
			int i = 0;
			Object[] organs = orgChildren.toArray();
			for (; i < organs.length - 1; i++) {
				Organ childOrgan = (Organ) organs[i];
				bf.append("{");
				// 加载机构信息
				loadOrgan(childOrgan, bf);
				bf.append("},");
			}
			Organ childOrgan = (Organ) organs[i];
			bf.append("{");
			// 加载机构信息
			loadOrgan(childOrgan, bf);
			bf.append("}");
			bf.append("],");
		}

		// 是否显示系统用户
		if (!isShowUser()) {
			bf.append("usrSize:0");
			bf.append("}");
			return bf.toString();
		}

		// 机构人员
		Collection usrChildren = orgService.findUsers(orgNo, userType);
		if (usrChildren == null || usrChildren.isEmpty()) {
			bf.append("usrSize:0");
		} else {
			bf.append("usrSize:").append(usrChildren.size()).append(",");
			bf.append("user:[");
			Object[] users = getSortUsers(usrChildren).toArray();
			int i = 0;
			for (; i < users.length - 1; i++) {
				UserInfo user = (UserInfo) users[i];
				bf.append("{");
				loadUser(user, bf);
				bf.append("},");
			}
			UserInfo user = (UserInfo) users[i];
			bf.append("{");
			loadUser(user, bf);
			bf.append("}");
			bf.append("]");
		}
		bf.append("}");
		return bf.toString();
	}

	/**
	 * 加载某机构的子机构及人员
	 * 
	 * @param organ
	 * @param bf
	 */
	private void loadOrgan(Organ organ, StringBuffer bf) {
		bf.append("id:\"").append(organ.getOrgId()).append("\",");
		bf.append("name:\"").append(organ.getOrgName()).append("\",");
		bf.append("orgLvl:\"").append(organ.getOrgLvl()).append("\",");

		if (Integer.parseInt(organ.getOrgLvl()) < orgLvl) {
			bf.append("orgSize:1,");
		} else {
			bf.append("orgSize:0,");
		}
		// 是否显示用户
		if (!isShowUser()) {
			bf.append("usrSize:0");
			return;
		}
		// 机构人员
		Collection usrChildren = null;// = orgDao.findUsers(organ.getOrgId());
		if (usrChildren == null || usrChildren.isEmpty()) {
			bf.append("usrSize:0");
		} else {
			bf.append("usrSize:").append(usrChildren.size()).append(",");
			bf.append("user:[");
			Object[] users = getSortUsers(usrChildren).toArray();
			int i = 0;
			for (; i < users.length - 1; i++) {
				UserInfo user = (UserInfo) users[i];
				bf.append("{");
				loadUser(user, bf);
				bf.append("},");
			}
			UserInfo user = (UserInfo) users[i];
			bf.append("{");
			loadUser(user, bf);
			bf.append("}");
			bf.append("]");
		}
	}

	/**
	 * 加载用户信息
	 * 
	 * @param user
	 * @param bf
	 */
	private void loadUser(UserInfo user, StringBuffer bf) {
		bf.append("id:\"").append(user.getUserId()).append("\",");
		bf.append("name:\"").append(user.getUserName()).append("\"");
	}

	private Collection getSortUsers(Collection temps) {
		SortVector children = new SortVector(new Compare() {
			public boolean lessThan(Object lhs, Object rhs) {
				String l = ((UserInfo) lhs).getUserId();
				String r = ((UserInfo) rhs).getUserId();
				return l.compareTo(r) < 0;
			}

			public boolean lessThanOrEqual(Object lhs, Object rhs) {
				return lessThan(lhs, rhs);
			}
		});
		children.addAll(temps);
		children.sort();
		return children;
	}

	private Collection getSortOrgans(Collection temps) {
		SortVector children = new SortVector(new Compare() {
			public boolean lessThan(Object lhs, Object rhs) {
				Integer l = new Integer(((Organ) lhs).getOrgLvl());
				Integer r = new Integer(((Organ) rhs).getOrgLvl());
				return l.intValue() < r.intValue();
			}

			public boolean lessThanOrEqual(Object lhs, Object rhs) {
				return lessThan(lhs, rhs);
			}
		});
		children.addAll(temps);
		children.sort();
		return children;
	}

	public boolean isShowUser() {
		return showUser;
	}

	public void setShowUser(boolean showUser) {
		this.showUser = showUser;
	}

	public boolean isShowSon() {
		return showSon;
	}

	public void setShowSon(boolean showSon) {
		this.showSon = showSon;
	}

	public int getOrgLvl() {
		return orgLvl;
	}

	public void setOrgLvl(int orgLvl) {
		this.orgLvl = orgLvl;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
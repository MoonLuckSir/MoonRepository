<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/tools.css">
		<title>查看账户持卡主体反馈信息</title>
	</head>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="27">
					<table width="100%" height="100%" cellspacing="0" cellpadding="0"
						class="wz">
						<tr>
							<td width="28" align="center"><label class="wz_img"></label></td>
							<td>
								当前位置：司法查询&gt;&gt;  人民银行电信诈骗查控 &gt;&gt; 账户持卡主体反馈信息
							</td>
							<td width="50" align="right">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="rowSplit"></td>
			</tr>
			<tr>
				<td> 
					<table width="100%" border="0" cellspacing="1">
						<tr>
							<td width="50%" valign="top">
								<table width="100%" border="0" cellpadding="4" cellspacing="4"
									class="detail">
									<tr>
										<td class="detail2">
											<table width="100%" border="0" cellpadding="1"
												cellspacing="1" class="detail3">
												<tr>
													<td height="26" width="30%" align="right"
													class="detail_label">
														传输报文流水号：
													</td>
													<td height="26" width="70%" align="left"
													class="detail_content">
														${rst.TRANSSERIALNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														接收机构ID：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.JSJG }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														查询证照号码：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.ACCOUNTCREDENTIALNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														联系手机：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.TELNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														帐/卡代办人证件类型：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.CARDOPERATORCREDENTIALTYPE }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														住宅地址：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.RESIDENTADDRESS }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														工作单位：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.WORKCOMPANYNAME }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														单位电话：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.WORKTELNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														邮箱地址：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.EMAILADDRESS }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														法人代表：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.ARTIFICIALPERSONREP }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														法人代表证件号码：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.ARTIFICIALPERSONREPCREDENTIALNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														国税纳税号：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.STATETAXSERIAL }
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
							<td width="50%" valign="top">
								<table width="100%" border="0" cellpadding="4" cellspacing="4"
									class="detail">
									<tr>
										<td class="detail2">
											<table width="100%" border="0" cellpadding="1"
												cellspacing="1" class="detail3">
												<tr>
													<td height="26" width="30%" align="right" 
														class="detail_label">
														发送传输报文流水号：
													</td>
													<td height="26" width="70%" align="left" 
														class="detail_content">
														${rst.NEWTRANSSERIALNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														交易类型编码：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.TXCODE }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														业务申请编号：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.APPLICATIONID }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														查询证照类型代码：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.ACCOUNTCREDENTIALTYPE }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														查询主体名称：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.ACCOUNTSUBJECTNAME }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														帐/卡代办人姓名：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.CARDOPERATORNAME }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														帐/卡代办人证件号码：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.CARDOPERATORCREDENTIALNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														住宅电话：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.RESIDENTTELNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														单位地址：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.WORKADDRESS }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														法人代表证件类型：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.ARTIFICIALPERSONREPCREDENTIALTYPE }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														客户原工商注册号码：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.BUSINESSLICENSENUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														地税纳税号：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.LOCALTAXSERIAL }
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="20" align="center" valign="middle"> 
					<input type="button" class="btn" value="返回"
							onclick="history.back();"> 
				</td>
			</tr>
		</table>
	</body> 
</html>
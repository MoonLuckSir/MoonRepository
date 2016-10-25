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
		<title>查看止付解除详细信息</title>
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
								当前位置：司法查询&gt;&gt;  人民银行电信诈骗查控 &gt;&gt; 止付延期详细信息
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
													<td width="30%" height="26" align="left"
														class="detail_label">
														传输报文流水号：
													</td>
													<td width="70%" height="26" align="left"
														class="detail_content">
														${rst.TRANSSERIALNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														发送机构编号：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.MESSAGEFROM }
													</td>
												</tr>
												<tr>
													<td height="23" align="right" class="detail_label">
														案件编号：
													</td>
													<td height="23" align="left" class="detail_content">
														${rst.CASENUMBER }
													</td>
												</tr>
												<tr>
													<td height="23" align="right" class="detail_label">
														原举报申请编号：
													</td>
													<td height="23" align="left" class="detail_content">
														${rst.ORIGINALAPPLICATIONID }
													</td>
												</tr>
												<tr>
													<td height="23" align="right" class="detail_label">
														止付账户所属银行机构编码：
													</td>
													<td height="23" align="left" class="detail_content">
														${rst.BANKID }
													</td>
												</tr>
												<tr>
													<td height="23" align="right" class="detail_label">
														止付账号类别：
													</td>
													<td height="23" align="left" class="detail_content">
														<c:if test="${rst.ACCOUNTTYPE eq '01'}">
																个人
														</c:if>
														<c:if test="${rst.ACCOUNTTYPE eq '02'}">
																对公
														</c:if>
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														止付帐卡号：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.ACCOUNTNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														止付失效时间：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.EXPIRETIME }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														申请时间：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.APPLICATIONTIME }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														申请机构名称：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.APPLICATIONORGNAME }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														经办人证件号：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.OPERATORIDNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														经办人电话：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.OPERATORPHONENUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														协查人证件号：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.INVESTIGATORIDNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														止付解除日期：
													</td>
													<td height="26" align="left" class="detail_content">
														<fmt:formatDate value="${rst.QRY_DATE}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> 
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
														交易类型编码：
													</td>
													<td height="26" width="70%" align="left"
													 			class="detail_content">
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
														案件类型：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.CASETYPE }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														紧急程度：
													</td>
													<td height="26" align="left" class="detail_content">
														<c:if test="${rst.EMERGENCYLEVEL eq '01'}">
																正常
														</c:if>
														<c:if test="${rst.EMERGENCYLEVEL eq '02'}">
																加急
														</c:if>
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														止付解除账户所属银行名称：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.BANKNAME }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														止付账户名：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.ACCOUNTNAME }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														止付延期起始时间：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.POSTPONESTARTTIME }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														止付延期说明：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.EXTENDREMARK }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														申请机构编码：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.APPLICATIONORGID }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														经办人证件类型：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.OPERATORIDTYPE }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														经办人姓名：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.OPERATORNAME }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														协查人证件类型：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.INVESTIGATORIDTYPE }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														协查人姓名：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.INVESTIGATORNAME }
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
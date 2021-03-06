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
		<title>查看全账户查询信息</title>
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
								当前位置：司法查询&gt;&gt;  人民银行电信诈骗查控 &gt;&gt; 账户全账户查询
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
														业务申请编号：
													</td>
													<td width="70%" height="26" align="left"
														class="detail_content">
														${rst.APPLICATIONID }
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
													<td height="26" align="right" class="detail_label">
														案件编号：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.CASENUMBER }
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
															加紧
														</c:if>
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														银行机构编码：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.BANKID }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														证照类型代码：
													</td>
													<td height="26" align="left" class="detail_content">
											
														
														<c:if test="${rst.ACCOUNTCREDENTIALTYPE eq 01}">
															身份证
														</c:if>
														<c:if test="${rst.ACCOUNTCREDENTIALTYPE eq 02}">
															驾驶证	
														</c:if>
														<c:if test="${rst.ACCOUNTCREDENTIALTYPE eq 03}">
															护照		
														</c:if>
														<c:if test="${rst.ACCOUNTCREDENTIALTYPE eq 04}">
															军官证
														</c:if>
														<c:if test="${rst.ACCOUNTCREDENTIALTYPE eq 05}">
															士兵证	
														</c:if>
														<c:if test="${rst.ACCOUNTCREDENTIALTYPE eq 06}">
															户口本	
														</c:if>
														<c:if test="${rst.ACCOUNTCREDENTIALTYPE eq 07}">
															港澳居民往来内地通行证
														</c:if>
														<c:if test="${rst.ACCOUNTCREDENTIALTYPE eq 08}">
															警官证	
														</c:if>
														<c:if test="${rst.ACCOUNTCREDENTIALTYPE eq 09}">
															社会保障号	
														</c:if>
														<c:if test="${rst.ACCOUNTCREDENTIALTYPE eq 10}">
															台湾通报来往内地通行证
														</c:if>
														<c:if test="${rst.ACCOUNTCREDENTIALTYPE eq 11}">
															临时身份证		
														</c:if>
														<c:if test="${rst.IDTYPE eq 12}">
															外国人居住证		
														</c:if>
														<c:if test="${rst.IDTYPE eq 13}">
															组织机构代码(对公)		
														</c:if>
														<c:if test="${rst.IDTYPE eq 14}">
															统一社会信用代码(对公)			
														</c:if>
														<c:if test="${rst.IDTYPE eq 15}">
															营业执照号(对公)		
														</c:if>
														<c:if test="${rst.IDTYPE eq 16}">
															(原)工商注册号		
														</c:if>
														<c:if test="${rst.IDTYPE eq 99}">
															其他			
														</c:if>
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														主体名称：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.ACCOUNTSUBJECTNAME }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														查询事由：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.REASON }
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
														协查人姓名：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.INVESTIGATORNAME }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														查询日期：
													</td>
													<td height="26" align="left" class="detail_content">
														<fmt:formatDate value="${rst.QRY_DATE}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> 
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														查看信息
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.LOOKINFOMATION }
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
														传输报文流水号：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.TRANSSERIALNUMBER }
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
														银行名称：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.BANKNAME }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														主体类别：
													</td>
													<td height="26" align="left" class="detail_content">
														<c:if test="${rst.SUBJECTTYPE eq '1'}">
															自然人主体
														</c:if>
														<c:if test="${rst.SUBJECTTYPE eq '2'}">
															法人主体
														</c:if>
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														证照号码：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.ACCOUNTCREDENTIALNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														查询内容：
													</td>
													<td height="26" align="left" class="detail_content">
														<c:if test="${rst.INQUIRYMODE eq '01'}">
															账户基本信息
														</c:if>
														<c:if test="${rst.INQUIRYMODE eq '02'}">
															账户信息
														</c:if>
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														查询说明：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.REMARK }
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
														协查人证件号：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.INVESTIGATORIDNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="right" class="detail_label">
														法律文书等附件：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.ATTACHMENTS }
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
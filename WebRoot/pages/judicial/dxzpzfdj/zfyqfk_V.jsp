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
		<title>查看止付延期反馈信息</title>
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
								当前位置：司法查询&gt;&gt;  人民银行电信诈骗查控 &gt;&gt; 止付延期反馈信息查看
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
					<table align="center" width="50%" border="0" cellspacing="1">
						<tr>
							<td width="50%" valign="top">
								<table width="100%" border="0" cellpadding="4" cellspacing="4"
									class="detail">
									<tr>
										<td class="detail2">
											<table width="100%" border="0" cellpadding="1"
												cellspacing="1" class="detail3">
												<tr>
													<td width="30%" height="23" align="left"
														class="detail_label">
														传输报文流水号：
													</td>
													<td width="70%" height="23" align="left"
														class="detail_content">
														${rst.TRANSSERIALNUMBER }
													</td>
												</tr>
												<tr>
													<td height="23" align="right" class="detail_label">
														发送传输报文流水号：
													</td>
													<td height="23" align="left" class="detail_content">
														${rst.NEWTRANSSERIALNUMBER }
													</td>
												</tr>
												<tr>
													<td height="23" align="right" class="detail_label">
														接收机构：
													</td>
													<td height="23" align="left" class="detail_content">
														${rst.JSJG }
													</td>
												</tr>
												<tr>
													<td height="23" align="right" class="detail_label">
														交易类型编码：
													</td>
													<td height="23" align="left" class="detail_content">
														${rst.TXCODE }
													</td>
												</tr>
												<tr>
													<td height="23" align="right" class="detail_label">
														业务申请编号：
													</td>
													<td height="23" align="left" class="detail_content">
														${rst.APPLICATIONID }
													</td>
												</tr>
												<tr>
													<td height="23" align="right" class="detail_label">
														止付延期结果：
													</td>
													<td height="23" align="left" class="detail_content">
														${rst.RESULT }
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
													<td height="23" align="right" class="detail_label">
														止付帐卡号：
													</td>
													<td height="23" align="left" class="detail_content">
														${rst.ACCOUNTNUMBER }
													</td>
												</tr>
												<tr>
													<td height="23" align="right" class="detail_label">
														卡/折号：
													</td>
													<td height="23" align="left" class="detail_content">
														${rst.CARDNUMBER }
													</td>
												</tr>
												<tr>
													<td height="23" align="right" class="detail_label">
														币种：
													</td>
													<td height="23" align="left" class="detail_content">
														${rst.CURRENCY }
													</td>
												</tr>
												<tr>
													<td height="23" align="right" class="detail_label">
														账户余额：
													</td>
													<td height="23" align="left" class="detail_content">
														${rst.ACCOUNTBALANCE }
													</td>
												</tr>
												<tr>
													<td height="23" align="right" class="detail_label">
														止付延期起始时间：
													</td>
													<td height="23" align="left" class="detail_content">
														${rst.STARTTIME }
													</td>
												</tr>
												<tr>
													<td height="23" align="right" class="detail_label">
														止付延期结束时间：
													</td>
													<td height="23" align="left" class="detail_content">
														${rst.EXPIRETIME }
													</td>
												</tr>
												<tr>
													<td height="23" align="right" class="detail_label">
														未能止付延期原因：
													</td>
													<td height="23" align="left" class="detail_content">
														${rst.FAILURECAUSE }
													</td>
												</tr>
												<tr>
													<td height="23" align="right" class="detail_label">
														反馈说明：
													</td>
													<td height="23" align="left" class="detail_content">
														${rst.FEEDBACKREMARK }
													</td>
												</tr>
												<tr>
													<td height="23" align="right" class="detail_label">
														反馈机构名称：
													</td>
													<td height="23" align="left" class="detail_content">
														${rst.FEEDBACKORGNAME }
													</td>
												</tr>
												<tr>
													<td height="23" align="right" class="detail_label">
														经办人姓名：
													</td>
													<td height="23" align="left" class="detail_content">
														${rst.OPERATORNAME }
													</td>
												</tr>
												<tr>
													<td height="23" align="right" class="detail_label">
														经办人电话：
													</td>
													<td height="23" align="left" class="detail_content">
														${rst.OPERATORPHONENUMBER }
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
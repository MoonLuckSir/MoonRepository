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
		<title>查看账户查询明细信息</title>
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
								当前位置：司法查询&gt;&gt;  人民银行电信诈骗查控 &gt;&gt; 账户查询明细查询
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
													<td height="26" align="left" class="detail_label">
														传输报文流水号：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.TRANSSERIALNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														交易类型：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.TRANSACTIONTYPE }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														币种：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.CURRENCY }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														交易余额：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.ACCOUNTBALANCE }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														交易流水号：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.TRANSACTIONSERIAL }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														交易对方账卡号：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.OPPONENTACCOUNTNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														交易对方账号开户行：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.OPPONENTDEPOSITBANKID }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														交易网点名称：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.TRANSACTIONBRANCHNAME }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														日志号：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.LOGNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														凭证种类：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.VOUCHERTYPE }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														现金标志：
													</td>
													<td height="26" align="left" class="detail_content">
														<c:if test="${rst.CASHMARK eq '00'}">
																其他
														</c:if>
														<c:if test="${rst.CASHMARK eq '01'}">
																现金交易
														</c:if>
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														交易是否成功：
													</td>
													<td height="26" align="left" class="detail_content">
														<c:if test="${rst.TRANSACTIONSTATUS eq '00'}">
																成功
														</c:if>
														<c:if test="${rst.TRANSACTIONSTATUS eq '01'}">
																失败
														</c:if>
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														商户名称：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.MERCHANTNAME }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														IP地址 ：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.IPADDRESS }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														交易柜员号：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.TELLERCODE }
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
														主账户：
													</td>
													<td height="26" width="70%" align="left" 
														class="detail_content">
														${rst.CARDNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														借贷标志：
													</td>
													<td height="26" align="left" class="detail_content">
														<c:if test="${rst.BORROWINGSIGNS eq '0'}">
																借
														</c:if>
														<c:if test="${rst.BORROWINGSIGNS eq '1'}">
																贷
														</c:if>
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														交易金额：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.TRANSACTIONAMOUNT }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														交易时间：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.TRANSACTIONTIME }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														交易对方名称：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.OPPONENTNAME }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														交易对方证件号码：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.OPPONENTCREDENTIALNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														交易摘要：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.TRANSACTIONREMARK }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														交易网点代码：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.TRANSACTIONBRANCHCODE }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														传票号：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.SUMMONSNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														凭证号：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.VOUCHERCODE }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														终端号：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.TERMINALNUMBER }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														交易发生地：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.TRANSACTIONADDRESS }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														商户号：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.MERCHANTCODE }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														MAC地址：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.MAC }
													</td>
												</tr>
												<tr>
													<td height="26" align="left" class="detail_label">
														备注：
													</td>
													<td height="26" align="left" class="detail_content">
														${rst.REMARK }
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
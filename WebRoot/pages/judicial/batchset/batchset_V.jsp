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
		<title>查看批量设置信息</title>
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
								当前位置：系统管理 &gt;&gt;  批量设置 &gt;&gt; 批量设置信息查看
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
							<td height="35" align="center" valign="middle"
								class="detail_title">
								批量设置详细信息查看
							</td>
						</tr>
						<tr>
							<td width="100%" align="center">
								<table width="70%" border="0" cellpadding="4" cellspacing="4"
									class="detail">
									<tr>
										<td class="detail2">
											<table width="100%" border="0" cellpadding="1"
												cellspacing="1" class="detail3">
												<tr>
													<td width="30%" height="30" align="left"
														class="detail_label">
														交易编号：
													</td>
													<td width="70%" height="30" align="left"
														class="detail_content">
														${rst.NUMBER }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														交易类型：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.BATCH_TYPE_NAME }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														支持查询账号类型：
													</td>
													<td height="30" align="left" class="detail_content">
														${noType}
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														是否需要查询日期条件：
													</td>
													<td height="30" align="left" class="detail_content">
														<c:if test="${rst.NEED_QUERY_DATE eq 'Y'}">
															是
														</c:if>
														<c:if test="${rst.NEED_QUERY_DATE eq 'N'}">
															否
														</c:if>
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														配置是否有效：
													</td>
													<td height="30" align="left" class="detail_content">
														<c:if test="${rst.IS_VALID eq 'Y'}">
															有效
														</c:if>
														<c:if test="${rst.IS_VALID eq 'N'}">
															无效
														</c:if>
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														字段名：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.EXPORT_COLUMN }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														字段中文名
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.EXPORT_NAME }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														FROM语句
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.QUERY_SQL }
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td height="49" align="center" valign="middle"> 
									<input type="button" class="btn" value="关 闭"
										onclick="history.back();"> 
							</td>
						</tr>
					</table> 
				</td>
			</tr>
		</table>
	</body> 
</html>
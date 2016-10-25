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
		<title>查看用户</title>
	</head>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="27">
					<table width="100%" height="100%" cellspacing="0" cellpadding="0"
						class="wz">
						<tr>
							<td width="28" align="center"><label class="wz_img"></label></td>
							<td>当前位置：系统管理 &gt;&gt;配置表管理  &gt;&gt; 查看配置表</td>
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
								查看配置表
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
													<td width="25%" height="30" align="right"
														class="detail_label">
														编号：
													</td>
													<td width="75%" height="30" align="left"
														class="detail_content">
														${rst.ID }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														模式名：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.TABSCHEMA }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														原始表名：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.TABSHOTNAME }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														年度：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.TABYEAR }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														系统渠道：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.TABCRC }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														状态：
													</td>
													<td height="30" align="left" class="detail_content">
														<c:choose>
															<c:when test="${rst.TABSTATE eq '0'}">
																<span class="FontGreen">正常</span>
															</c:when>
															<c:otherwise>
																<c:choose>
																	<c:when test="${rst.TABSTATE eq '1'}">
																	 	<span class="FontRed">废弃</span>
																	</c:when>
																</c:choose>
															</c:otherwise>
														</c:choose>
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														类型：
													</td>
													<td height="30" align="left" class="detail_content">
														<c:choose>
															<c:when test="${rst.TABTYPE eq 'O'}">单表</c:when>
															<c:otherwise>
																<c:choose>
																	<c:when test="${rst.TABTYPE eq 'Y'}">年</c:when>
																	<c:otherwise>
																		<c:choose>
																			<c:when test="${rst.TABTYPE eq 'M'}">月</c:when>
																		</c:choose>
																	</c:otherwise>
																</c:choose>
															</c:otherwise>
														</c:choose>
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														表字段：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.TABCOL }
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
									<input type="button" class="btn" value="返 回"
										onclick="history.back();"> 
							</td>
						</tr>
					</table> 
				</td>
			</tr>
		</table>
	</body> 
</html>

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
							<td>当前位置：系统管理 &gt;&gt; 用户管理 &gt;&gt; 修改用户</td>
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
								查看用户
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
														用户编号：
													</td>
													<td width="75%" height="30" align="left"
														class="detail_content">
														${rst.USER_ID }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														用户姓名：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.USER_NAME }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														所属机构：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.ORG_ID }|${rst.ORG_NAME }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														用户职位：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.USER_POS }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														电子邮箱：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.EMAIL }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														电话号码：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.PHONE }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														用户状态：
													</td>
													<td height="30" align="left" class="detail_content">
														<c:choose>
															<c:when test="${rst.USER_STUS eq '1'}">
																<span class="FontGreen">正常</span>
															</c:when>
															<c:otherwise>
																<span class="FontRed">已注销</span>
															</c:otherwise>
														</c:choose>
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

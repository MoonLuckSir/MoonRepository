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
							<td>
								当前位置：报表查询 &gt;&gt; 司法查询定制设计 &gt;&gt; 条件定制录入详细信息
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
								条件定制录入
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
													<td width="50%" height="30" align="right"
														class="detail_label">
														模板编号：
													</td>
													<td width="50%" height="30" align="left"
														class="detail_content">
														${rst.REPMOD_NO }
													</td>
												</tr>
												<tr>
													<td width="25%" height="30" align="right"
														class="detail_label">
														交易名称：
													</td>
													<td width="75%" height="30" align="left"
														class="detail_content">
														${rst.REP_NAME }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														列名称：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.COLMX_NAME }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														物理名：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.PHYSICAL_NAME }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														宽度：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.WIDTH }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														高度：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.HEIGHT }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														对齐：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.ALIGN }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														是否显示：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.ISDISPLAY }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														序号：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.ORDER }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														描述：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.COLMX_DESC }
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
										onclick="window.close();"> 
							</td>
						</tr>
					</table> 
				</td>
			</tr>
		</table>
	</body> 
</html>

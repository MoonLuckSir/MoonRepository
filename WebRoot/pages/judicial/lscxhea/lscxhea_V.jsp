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
								当前位置：报表查询 &gt;&gt; 司法查询定制设计 &gt;&gt; 规则信息录入详细信息
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
													<td width="25%" height="30" align="right"
														class="detail_label">
														条件名称：
													</td>
													<td width="75%" height="30" align="left"
														class="detail_content">
														${rst.CONMX_NAME }
													</td>
												</tr>
												<tr>
													<td width="25%" height="30" align="right"
														class="detail_label">
														模板编号：
													</td>
													<td width="75%" height="30" align="left"
														class="detail_content">
														${rst.REPMOD_NO }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														数据类型：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.CONMX_TYPE }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														输入框类型：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.INPUT_TYPE }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														组件名称：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.INPUT_VALUE }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														组件涉及列：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.ZJCONS }
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
														默认值：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.DEFAULT }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														运算符：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.OPERATOR }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														排序号：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.ORDERXH }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														是否表头展现：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.ISBTDISPLAY }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														是否拼接条件：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.ISJOIN }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														抬头来源：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.HEARES }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														抬头语句：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.HEASQL }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														是否有分行符：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.ISFGF }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														描述：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.CONMX_DESC }
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

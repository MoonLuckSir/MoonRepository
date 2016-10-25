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
								当前位置：司法查询 &gt;&gt; 司法查询申请 &gt;&gt; 司法查询申请详细信息
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
								司法查询申请信息
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
														司法查清申请编号：
													</td>
													<td width="75%" height="30" align="left"
														class="detail_content">
														${rst.REQ_NO }
													</td>
												</tr>
												<tr>
													<td width="25%" height="30" align="right"
														class="detail_label">
														司法模板编号：
													</td>
													<td width="75%" height="30" align="left"
														class="detail_content">
														${rst.REPMOD_NO }
													</td>
												</tr>
												<tr>
													<td width="25%" height="30" align="right"
														class="detail_label">
														交易编号：
													</td>
													<td width="75%" height="30" align="left"
														class="detail_content">
														${rst.REP_NO }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														交易名称：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.REP_NAME }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														申请人:：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.CHECKIN_USER }
													</td>
												</tr>
												
												<tr>
													<td height="30" align="right" class="detail_label">
														申请机构：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.CHECKIN_ORGAN }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														申请时间：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.CHECKIN_TIME }
													</td>
												</tr>
												
												<tr>
													<td height="30" align="right" class="detail_label">
														状态：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.STATE }
													</td>
												</tr>
												<tr>
													<td height="30" align="right" class="detail_label">
														审批流程：
													</td>
													
													<td height="30" align="left" class="detail_content">
													<table width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
													<tr >
								                    <th  align="center" norawp>
									                  操作人
								                    </th>
								                    <th  align="center" norawp>
									                   操作机构
								                    </th>
								                    <th  align="center" norawp>
									                  操作时间
								                    </th>
													<th  align="center" norawp>
									                   操作
								                    </th>
								                    <th  align="center" >
									                  意见
								                    </th>
								                    </tr>
													<c:forEach items="${page}" var="rvt" varStatus="row">
													<tr>
													<td align="left">
										               ${rvt.OPERATOR}
									                </td>
									                <td align="left">
										               ${rvt.OPERORG}
									                </td>
									                <td align="left">
										               ${rvt.OPERTIME}
									                </td>
									                <td align="left">
										               ${rvt.ACTION}
									                </td>
									                <td align="left">
										               ${rvt.HIS_DESC}
									                </td>
									                
													</tr>
													</c:forEach>	
													</table>	
													</td>
													
												</tr>
												
												<tr>
													<td height="30" align="right" class="detail_label">
														描述：
													</td>
													<td height="30" align="left" class="detail_content">
														${rst.REP_DESC }
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

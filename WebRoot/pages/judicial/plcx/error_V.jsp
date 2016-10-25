<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/tools.css">
		<title>批量查询报错原因</title>
	</head>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="27">
					<table width="100%" height="100%" cellspacing="0" cellpadding="0"
						class="wz">
						<tr>
							<td width="28" align="center">
								<label class="wz_img"></label>
							</td>
							<td>
								当前位置：司法查询 &gt;&gt; 批量查询 &gt;&gt; 批量查询审核
							</td>
							<td width="50" align="right">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="rowSplit"></td>
			</tr>
			<tr>
				<td>
					<form name="form" method="post"
						action="${ctx}/judicial/plcx.shtml?action=audit"
						onsubmit="return doSave();">
						<input type="hidden" name="SM" value="${param.SM}">
						${paramCover.unCovered_Inputs }
						<input type="hidden" name="batchIds" value="${batchIds}"/>
						<input type="hidden" name="importStatus" value="${importStatus}"/>
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									批量查询任务报错信息
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
															任务编号：
														</td>
														<td width="75%" class="detail_content">
																${batchId}
														</td>
													</tr>
													<tr>
														<td width="25%" height="30" align="right"
															class="detail_label">
															报错信息：
														</td>
														<td width="75%" class="detail_content">
																<textarea name="auditRemark" label="报错信息"  
																	cols="50" rows="6"
																	class="textArea425px" pattern="string" 
																	maxlength="150" autoChecked="true" >${rst.AUDIT_REMARK}</textarea>
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
					</form>
				</td>
			</tr>
		</table>
	</body>
	<script type="text/javascript" src="${ctx}/scripts/util/calendar.js"></script>
	<%@ include file="/commons/jslibs.jsp"%>
	<%@ include file="/commons/jslibs4tree.jsp"%>
	<script type="text/javascript">InitFormValidator(0);</script>
	<script type="text/javascript">
		
		
	</script>
</html>

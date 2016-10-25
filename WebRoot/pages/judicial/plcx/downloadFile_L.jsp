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
		<%@ include file="/commons/jslibs.jsp"%>
		<title>批量查询结果下载列表</title>
	</head>
	<body>
		<table width="100%" height="100%" border="0" cellspacing="0"
			cellpadding="0" class="panel">
			<tr>
				<td height="27">
					<table width="100%" height="100%" cellspacing="0" cellpadding="0"
						class="wz">
						<tr>
							<td width="28" align="center">
								<label class="wz_img"></label>
							</td>
							<td>
								当前位置：司法查询 &gt;&gt; 批量查询 &gt;&gt; 查询结果列表
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
				<td height="40">
					<form action="${ctx}/judicial/plcx.shtml?action=toDownloadList"
						method="post" name="qryForm" onsubmit="return doQuery();">
						<table width="100%" border="0" cellpadding="2" cellspacing="0"
							class="qry">
							<tr>
								<td width="100" align="right">
									导入文件名：
								</td>
								<td width="120">
									<input name="importFileName" type="text" autocheck="true"
										style="width: 110px;" pattern="string" maxlength="100">
									<script type="text/javascript">
										qryForm.importFileName.value="${param.importFileName}";
									</script>
								</td>
								<td width="40" align="right">
									状态：
								</td>
								<td width="120">
									<select name="importStatus">
										<option value="">不限</option>
										<option value="2">审核通过待生成</option>
										<option value="3">审核不通过</option>
										<option value="4">正在生成</option>
										<option value="5">已生成</option>
										<option value="6">生成失败</option>
									</select>
									<script type="text/javascript">
										qryForm.importStatus.value="${param.importStatus}";
									</script>
								</td>
								<td width="100" align="right">
									查询交易名称：
								</td>
								<td width="120">
									<select name="batchType">
										<option value="">不限</option>
										<c:forEach items="${batchTypes}" var="bt">
											<option value="${bt.BATCH_TYPE}">${bt.BATCH_TYPE_NAME}</option>
										</c:forEach>
									</select>
									<script type="text/javascript">
										qryForm.batchType.value="${param.batchType}";
									</script>
								</td>
								<td>
									<input type="submit" class="btn" value="查 询">
									<input type="reset" class="btn" value="重 置">
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td class="rowSplit"></td>
			</tr>
			<tr>
				<td valign="top">
					<div class="lst_area"> 
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="lst">
							<tr class="fixedRow">
								<th width="10" height="25">
								</th>
								<th width="100" height="25">
									导入时间
								</th>
								<th width="100" align="center">
									导入文件名
								</th>
								<th width="120" align="center">
									下载文件名
								</th>
								<th align="center">
									查询交易名称
								</th>
								<th align="center">
									状态
								</th>
								<th align="center">
									生成开始时间
								</th>
								<th align="center">
									生成结束时间
								</th>
								 <th width="140">
									操作
								</th>
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)"
									onmouseout="mout(this)">
									<td height="25" class="row_no">
										${rst.RN}
									</td>
									<td >
										<fmt:formatDate value="${rst.IMPORT_TIME}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;
									</td>
									<td>
										${rst.IMPORT_FILE_NAME}&nbsp;
									</td>
									<td >
										${rst.RESULT_FILE_NAME}.tar.gz&nbsp;
									</td>
									<td>
										${rst.BATCH_TYPE_NAME}&nbsp;
									</td>
									<td>
										<c:if test="${rst.IMPORT_STATUS eq 0}">
											未提交
										</c:if>
										<c:if test="${rst.IMPORT_STATUS eq 1}">
											提交待审核
										</c:if>
										<c:if test="${rst.IMPORT_STATUS eq 2}">
											<a href="${ctx}/judicial/plcx.shtml?action=viewAuditRemark&batchId=${rst.BATCH_ID}">审核通过待生成</a>
										</c:if>
										<c:if test="${rst.IMPORT_STATUS eq 3}">
											<a href="${ctx}/judicial/plcx.shtml?action=viewAuditRemark&batchId=${rst.BATCH_ID}">审核不通过</a>
										</c:if>
										<c:if test="${rst.IMPORT_STATUS eq 4}">
											正在生成
										</c:if>
										<c:if test="${rst.IMPORT_STATUS eq 5}">
											已生成
										</c:if>
										
										<c:if test="${rst.IMPORT_STATUS eq 6}">
											<a href="${ctx}/judicial/plcx.shtml?action=viewErrorRemark&batchId=${rst.BATCH_ID}">生成失败</a>
										</c:if>
										&nbsp;
									</td>
									<td>
										<fmt:formatDate value="${rst.START_TIME}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;
									</td>
									<td>
										<fmt:formatDate value="${rst.END_TIME}" pattern="yyyy-MM-dd HH:mm:ss"/>&nbsp;
									</td>
									<td>
										<a href="${ctx}/judicial/plcx.shtml?action=downLoadImportFile&fileName=${rst.UPLOAD_FILE_NAME}">下载导入文件</a>&nbsp;&nbsp;
										<c:if test="${rst.IMPORT_STATUS eq 5}">
											<a href="${ctx}/judicial/plcx.shtml?action=downLoadResultFile&fileName=${rst.RESULT_FILE_NAME}" >查询结果文件下载</a>&nbsp;&nbsp;
										</c:if>
										<c:if test="${rst.IMPORT_STATUS ne 5}">
											&nbsp;查询结果文件下载&nbsp;&nbsp;
										</c:if>
										<c:if test="${rst.IMPORT_STATUS eq 6}">
											<a href="#" onclick="resetImportStatus('${rst.BATCH_ID}')">重新生成</a>
										</c:if>
										<c:if test="${rst.IMPORT_STATUS ne 6}">
											&nbsp;重新生成
										</c:if>
										
									</td>
									
								</tr>
							</c:forEach>
							<c:if test="${empty page.result}">
								<tr>
									<td height="25" colspan="15" align="center" class="lst_empty">
										&nbsp;无相关信息&nbsp;
									</td>
								</tr>
							</c:if>
						</table> 
					</div>
				</td>
			</tr>
			<tr>
				<td height="30" align="center">
					<table width="100%" height="100%" border="0" cellpadding="0"
						cellspacing="0" class="lst_toolbar">
						<tr>
							<td>
								<form action="${ctx}/judicial/plcx.shtml?action=toDownloadList"
									method="post" name="listForm" onsubmit="return false;">
									<input type="hidden" name="pageNo">
									${paramCover.unCoveredForbidInputs } ${page.footerHtml}
								</form>
							</td>
							<td align="right">
								${page.toolbarHtml}
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<form method="post" name="detailForm">
			<input type="hidden" name="batchId" />
			<input type="hidden" name="_backUrl"
				value="${ctx}/judicial/plcx.shtml?action=toDownloadList">
			${paramCover.coveredInputs}
		</form>
	</body>
	<%@ include file="/commons/jslibs4tree.jsp"%>
	<script type="text/javascript">
		function doQuery(){			
			if(FormValidate(qryForm)){
				setTimeout("qryForm.submit()",100);
			}
			return false;
		}	
		
		function resetImportStatus(batchId){
			if(confirm('确认进行重新生成操作？')){
				showWaitPanel('');	
				detailForm.action="${ctx}/judicial/plcx.shtml?action=resetImportStatus";
				detailForm.batchId.value = batchId;
				setTimeout("detailForm.submit()",100);
			}
		}
	</script>
</html>

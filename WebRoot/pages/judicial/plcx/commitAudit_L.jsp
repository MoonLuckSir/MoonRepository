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
		<title>批量查询提交审核列表</title>    
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
								当前位置：司法查询 &gt;&gt; 批量查询 &gt;&gt; 提交审核
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
					<form action="${ctx}/judicial/plcx.shtml?action=toCommitAuditList"
						method="post" name="qryForm" onsubmit="return doQuery();">
						<table width="100%" border="0" cellpadding="2" cellspacing="0"
							class="qry">
							<tr>
								<td width="110" align="right">
									导入文件名：
								</td>
								<td width="120">
									<input name="importFileName" type="text" autocheck="true"
										style="width: 110px;" pattern="string" maxlength="100">
									<script type="text/javascript">
										qryForm.importFileName.value="${param.importFileName}";
									</script>
								</td>
								<td width="70" align="right">
									状态：
								</td>
								<td width="120">
									<select name="importStatus">
										<option value="">不限</option>
										<option value="0">未提交</option>
										<option value="1">提交待审核</option>
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
								<td width="140" align="right">
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
								<th width="90" height="25">
									导入时间
								</th>
								<th width="70" align="center">
									导入文件名
								</th>
								<th width="120" align="center">
									转换后文件名
								</th>
								<th width="120" align="center">
									下载文件名
								</th>
								<th width="75" align="center">
									是否立即生成
								</th>
								<th width="145" align="center">
									查询交易名称
								</th>
								<th width="55" align="center">
									状态
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
										${rst.UPLOAD_FILE_NAME}&nbsp;
									</td>
									<td >
										${rst.RESULT_FILE_NAME}.csv.gz&nbsp;
									</td>
									<td align="center">
										<c:if test="${rst.FLAG eq 0}">
											否
										</c:if>
										<c:if test="${rst.FLAG eq 1}">
											是
										</c:if>
										&nbsp;
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
											生成失败
										</c:if>
										&nbsp;
									</td>
									<td>
										<a href="${ctx}/judicial/plcx.shtml?action=downLoadImportFile&fileName=${rst.UPLOAD_FILE_NAME}">下载导入文件</a>&nbsp;&nbsp;&nbsp;
										<c:if test="${rst.IMPORT_STATUS eq 0 }">
											<a href="#" onclick="commitAudit('${rst.BATCH_ID}')">提交审核</a>
										</c:if>
										<c:if test="${rst.IMPORT_STATUS ne 0 }">
											&nbsp;提交审核
										</c:if>
										&nbsp;&nbsp;&nbsp;
										<c:if test="${rst.IMPORT_STATUS eq 0 || rst.IMPORT_STATUS eq 3}">
											<a href="#" onclick="deleteInfo('${rst.BATCH_ID}')">删除</a>
										</c:if>
										<c:if test="${rst.IMPORT_STATUS ne 0 &&  rst.IMPORT_STATUS ne 3}">
											&nbsp;删除
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
								<form action="${ctx}/judicial/plcx.shtml?action=toCommitAuditList"
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
				value="${ctx}/judicial/plcx.shtml?action=toCommitAuditList">
			<input type="hidden" name="orgId">
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
		
		function commitAudit(batchId){
			if(confirm('确认进行提交审核操作？')){
				showWaitPanel('');	
				detailForm.action="${ctx}/judicial/plcx.shtml?action=commitAudit";
				detailForm.batchId.value = batchId;
				setTimeout("detailForm.submit()",100);
			}
		}
		
		function deleteInfo(batchId){
			if(confirm('确认进行删除操作？')){
				showWaitPanel('');	
				detailForm.action="${ctx}/judicial/plcx.shtml?action=deleteInfo";
				detailForm.batchId.value = batchId;
				setTimeout("detailForm.submit()",100);
			}
		}
	</script>
</html>

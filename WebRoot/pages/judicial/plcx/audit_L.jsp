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
		<script type="text/javascript"
			src="${ctx}/scripts/core/dialog.js"></script>    
		<%@ include file="/commons/jslibs.jsp"%>
		<title>批量查询审核列表</title>
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
								当前位置：司法查询&gt;&gt; 批量查询 &gt;&gt; 批量查询审核
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
					<form action="${ctx}/judicial/plcx.shtml?action=toAuditList"
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
								<td width="70" align="right">
									状态：
								</td>
								<td width="120">
									<select name="importStatus">
										<option value="">不限</option>
										<option value="1">提交待审核</option>
										<option value="2">审核通过待生成</option>
										<option value="3">审核不通过</option>
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
								<td >
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
				<td height="30">
					<div class="lst_title">
						<input type="button" class="btn" value="审核通过" onClick="audit('2','batchChk');" />
						<input type="button" class="btn" value="审核不通过" onClick="audit('3','batchChk');" />
					</div>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<div class="lst_area"> 
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="lst">
							<tr class="fixedRow">
								<th width="50" height="25" align="center">
									<input type="checkbox"
										onClick="checkAllStates('batchChk',this.checked)"/>全选
								</th>
								<th width="100" height="25">
									导入时间
								</th>
								<th width="110" align="center">
									导入文件名
								</th>
								<th width="120" align="center">
									转换后文件名
								</th>
								<th width="120" align="center">
									下载文件名
								</th>
								<th width="80" align="center">
									是否立即生成
								</th>
								<th align="center">
									查询交易名称
								</th>
								<th width="80" align="center">
									状态
								</th>
								 <th width="80">
									操作
								</th>
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)"
									onmouseout="mout(this)">
									<td align="center" height="25" class="row_no">
										<input type="checkbox" name="batchChk" value="${rst.BATCH_ID}" <c:if test="${rst.IMPORT_STATUS ne 1}">disabled</c:if> />
										${rst.RN}
									</td>
									<td>
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
										<c:if test="${rst.IMPORT_STATUS eq 1}">
											提交待审核
										</c:if>
										<c:if test="${rst.IMPORT_STATUS eq 2}">
											<a href="${ctx}/judicial/plcx.shtml?action=viewAuditRemark&batchId=${rst.BATCH_ID}">审核通过待生成</a>
										</c:if>
										<c:if test="${rst.IMPORT_STATUS eq 3}">
											<a href="${ctx}/judicial/plcx.shtml?action=viewAuditRemark&batchId=${rst.BATCH_ID}">审核不通过</a>
										</c:if>
										&nbsp;
									</td>
									<td align="center">
										<a href="${ctx}/judicial/plcx.shtml?action=downLoadImportFile&fileName=${rst.UPLOAD_FILE_NAME}">下载导入文件</a>&nbsp;&nbsp;&nbsp;
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
								<form action="${ctx}/judicial/plcx.shtml?action=toAuditList"
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
		<form method="post" name="detailForm" action="${ctx}/judicial/plcx.shtml?action=toAudit">
			<input type="hidden" name="batchIds" />
			<input type="hidden" name="importStatus">
			<input type="hidden" name="_backUrl"
				value="${ctx}/judicial/plcx.shtml?action=toAuditList">
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
		
		function checkMultable(name) {
			var cnt = checkStates(name);
			if (cnt < 1) {
				showMsgPanel('请至少选择1条记录!', '');
				return false;
			}
			return true;
		}
		
		function getCheckBoxValue(name) {
			var chks = document.getElementsByName(name);
			var values = [];
			var cnt = -1;
			if (chks && chks.length > 0) {
				cnt = 0;
				for (var i = 0, j = chks.length; i < j; i++) {
					if (chks[i].disabled == true)
						continue;
					if (chks[i].checked == true) {
						cnt++;
						values[values.length] = chks[i].value;
					}
				}
			}
			return cnt <= 0 ? null : values.join();
		}

		function audit(importStatus,chkName){
			if (!checkMultable(chkName))
				return;
			var str = "";
			if(importStatus==2){
				str = "审核通过";
			}else{
				str = "审核不通过";
			}
			if(confirm('确认进行'+str+'操作？')){
				var batchIds = getCheckBoxValue(chkName);
				showWaitPanel();
				detailForm.batchIds.value=batchIds;
				detailForm.importStatus.value=importStatus;
				setTimeout("detailForm.submit()",100);
			}
		}
	</script>
</html>

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
		<title>历史查询模板列表</title>
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
								当前位置：司法查控 &gt;&gt; 任务信息查看
							</td>
							<td width="50" align="right">&nbsp;
								
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="rowSplit"></td>
			</tr>
			<tr>
				<td height="20">
					<form action="${ctx}/judicial/djcx.shtml?action=toTaskInfoList" method="post"
						name="qryForm" onSubmit="return doQuery();">
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>
								<!--<td width="100" align="right">
									任务名称：
								</td>
								<td width="120">
									<input name="TASK_NAME" type="text" autocheck="true" style="width:110px;"
										pattern="string" maxlength="20" label="任务名称">
									<script type="text/javascript">
										qryForm.TASK_NAME.value="${param.TASK_NAME}";
									</script>
								</td>	-->
								
								<td width="70" align="right">
									任务状态：
								</td>
								<td width="120">						
									<select name="TASK_STU" style="width:110px;">
										<option value="">-----请选择-----</option>
										<option value="1">待提交审核</option>
										<option value="2">待审核</option>
										<option value="3">审核不通过</option>
										
										<option value="4">审核通过,正在生成</option>
										<option value="5">审核通过,已生成</option>
										<option value="6">审核通过,生成失败</option>
									</select>
									<script type="text/javascript">
										qryForm.TASK_STU.value="${param.TASK_STU}";
									</script>				
								</td>	
								<td width="70" align="right">
									登记日期：
								</td>
								<td width="300">						
									 <input style="width:120px;" class="Wdate" type="text" id="d01" value="${param.ksr }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d02\')}',vel:'ksr'})" readonly>
        							 <input name="ksr" id="ksr" type="hidden" value="${param.ksr }"/>&nbsp;-&nbsp;<input style="width:120px;" class="Wdate" type="text" id="d02" value="${param.jsr }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d01\')}',vel:'jsr'})" readonly>
     								  <input name="jsr" id="jsr" type="hidden"  value="${param.jsr }"/>
								</td>		
								<td align="left"> 
									<input type="submit" class="btn" value="查 询"> 
									<input type="button" onclick="cz();" class="btn" value="重 置"> 
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr><td class="rowSplit"></td></tr>
			<tr>
				<td height="30">
					<div class="lst_title">
						
					</div>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<div class="lst_area">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="lst">
							<tr class="fixedRow">
								<th width="50" align="center">
									序号
								</th>
								<th width="140" align="center">
									任务编号
								</th>
								<!--<th width="140" align="center">
									任务名称
								</th>-->
								
								<th  align="center">
									登记人
								</th>
								<th  align="center">
									登记时间
								</th>
								<th  align="center">
									审核人
								</th>
								<th  align="center">
									审核时间
								</th>
								<th  align="center">
									数据生成开始时间
								</th>
								<th  align="center">
									数据生成结束时间
								</th>	
								<th  align="center">
									任务状态
								</th>	
								<!--<th  align="center">
									数据生成信息
								</th>-->
								<th  align="center">
									操作
								</th>																																					
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" id="${r}" onMouseOver="mover(this)" onMouseOut="mout(this)" >
									
									<td align="center">
										<c:choose>
											<c:when test="${row.count lt 10}">
												0${row.count}
											</c:when>
											<c:otherwise>
												${row.count}
											</c:otherwise>
										</c:choose>
									</td>
									<td align="center">
										${rst.TASK_ID }
									</td>
									<!--<td align="center">
										${rst.TASK_NAME }
									</td>
									-->
									
									<td align="center">
										${rst.REG_USER}
									</td>
									<td align="center">
										<fmt:formatDate value="${rst.REG_DATE}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> 
									</td>
									<td align="center">
										${rst.AUDIT_USER}
									</td>
									<td align="center">
										<fmt:formatDate value="${rst.AUDIT_DATE}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> 
									</td>
									<td align="center">
										<fmt:formatDate value="${rst.MAKE_SDATE}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> 
									</td>
									<td align="center">
										<fmt:formatDate value="${rst.MAKE_EDATE}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> 
									</td>
									
									<td align="center">
									<c:if test="${rst.TASK_STU eq 1}">
											待提交审核
										</c:if>
										<c:if test="${rst.TASK_STU eq 2}">
											<font color="blue">待审核</font> 
										</c:if>
										<c:if test="${rst.TASK_STU eq 3}">
											<a href="#" style="text-decoration: underline" onclick="showAuditInfo('${rst.TASK_ID }','${rst.AUDIT_INFO}');"><font color="red">审核不通过</font></a>
										</c:if>
										<c:if test="${rst.TASK_STU eq 4}">
											<font color="blue">审核通过,正在生成</font> 
										</c:if>
										<c:if test="${rst.TASK_STU eq 5}">
											<font color="green">审核通过,已生成</font> 
										</c:if>
										<c:if test="${rst.TASK_STU eq 6}">
											<a href="#" style="text-decoration: underline" onclick="showMakeInfo('${rst.TASK_ID }','${rst.MAKE_INFO}');"><font color="red">审核通过,生成失败</font></a>
										</c:if>
									</td>
									<!--<td align="center">
										${rst.MAKE_INFO}
									</td>-->
									<td align="center">
											-<c:choose>
											<c:when test="${not empty rst.EXTRA_FILENAME }">
												<a href="${ctx}/judicial/djcx.shtml?action=downLoadFile&savePath=${rst.EXTRA_FILENAME}">下载附加文件</a>-
											</c:when>
											<c:otherwise>
												<a href="#" disabled>下载附加文件</a>-
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${not empty rst.IMP_FILENAME }">
												<a href="${ctx}/judicial/djcx.shtml?action=downLoadFile&savePath=${rst.IMP_FILENAME}">下载导入文件</a>-
											</c:when>
											<c:otherwise>
												<a href="#" disabled>下载导入文件</a>-
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty page.result}">
								<tr>
									<td height="25" colspan="10" align="center" class="lst_empty">
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
								<form action="${ctx}/judicial/djcx.shtml?action=toTaskInfoList"
									method="post" name="listForm">
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
			<input type="hidden" name="_backUrl" value="${ctx}/judicial/djcx.shtml?action=toTaskInfoList">
			<input type="hidden" name="CXMB_ID">
			<input type="hidden" name="taskIds">
			<input type="hidden" name="taskStu">
			<input type="hidden" name="usrId" value='${usrId}'>
		    <input type="hidden" name="orgNo" value='${orgNo}'>
			${paramCover.coveredInputs}
		</form>
		<input type="hidden" name="tmpId" >
		<input type="hidden" name="tmpStus" >
	</body>
	<%@ include file="/commons/jslibs4tree.jsp"%>
	<script type="text/javascript">		
		
		function cz(){
			qryForm.TASK_STU.value = "";
			qryForm.d01.value = "";
			qryForm.ksr.value = "";
			qryForm.jsr.value = "";
			qryForm.d02.value = "";
		   
		}
		
		function showMakeInfo(taskId,makeInfo){
			var url = "${ctx}/judicial/djcx.shtml?action=toMakeInfo&taskId="+taskId+"&makeInfo="+makeInfo;
			showDialog(encodeURI(url), 800, 400);
			
		}
		
		function doQuery(){			
			if(FormValidate(qryForm)){
				setTimeout("qryForm.submit()",100);
			}
			return false;
		}	
		
		function showAuditInfo(taskId,auditInfo){
			var url = "${ctx}/judicial/djcx.shtml?action=toAuditInfo&taskIds="+taskId+"&readonly=true&auditInfo="+auditInfo;
			
			showDialog(encodeURI(url), 800, 400);
		}
		
		
	</script>
</html>

<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/tools.css">
	
		<%@ include file="/commons/jslibs.jsp"%>
		<title>最高法院司法查控任务查询</title>
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
								当前位置：司法查控 &gt;&gt; 最高法院司法查控任务列表 
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
					<form action="${ctx}/judicial/fyck.shtml?action=toZgfyTaskList" method="post"
						name="qryForm" onSubmit="return doQuery();">
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>
							
								<td width="70" align="right">
									任务状态：
								</td>
								<td width="120">						
									<select name="TASK_STU" style="width:110px;">
										<option value="">-----请选择-----</option>
										<option value="7">正在进行查询</option>
										<option value="8">查询结果已生成</option>
										<option value="9">查询结果已反馈</option>
										<option value="10">查询中报错</option>
									</select>
									<script type="text/javascript">
										qryForm.TASK_STU.value="${param.TASK_STU}";
									</script>				
								</td>
								<td width="100" align="right">
									任务查询日期：
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
								<th width="100" align="center">
									序号
								</th>
								<th width="140" align="center">
									任务编号
								</th>
								<th  align="center">
									查询部门
								</th>
								<th  align="center">
									查询日期
								</th>
								
								<th  align="center">
									任务状态
								</th>
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
									<td align="center">
										最高法院
									</td>
									<td align="center">
										<fmt:formatDate value="${rst.REG_DATE}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> 
									</td>
									<td align="center">
										<c:if test="${rst.TASK_STU eq 7}">
											<font color="blue">正在进行查询</font>
										</c:if>
										<c:if test="${rst.TASK_STU eq 8}">
											<font color="green">查询结果已生成,正在反馈</font>
										</c:if>
										<c:if test="${rst.TASK_STU eq 9}">
											<font color="green">查询结果已反馈</font>
										</c:if>
										
										<c:if test="${rst.TASK_STU eq 10}">
											<a href="#" style="text-decoration: underline" onclick="showErrorInfo('${rst.TASK_ID }','${rst.MAKE_INFO}');"><font color="red">查询中报错</font></a>
										</c:if>
									</td>
									<td align="center">
										<a href="#" onclick="toView('${rst.TASK_ID }');">查看</a>-
										<c:choose>
											<c:when test="${rst.TASK_STU eq 8 or rst.TASK_STU eq 9}">
												<a href="${ctx}/judicial/fyck.shtml?action=downLoadFile&savePath=${rst.IMP_FILENAME}">查看生成结果</a>-
											</c:when>
											<c:otherwise>
												<a href="#" disabled>下载生成文件</a>-
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
								<form action="${ctx}/judicial/fyck.shtml?action=toZgfyTaskList"
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
			<input type="hidden" name="_backUrl" value="${ctx}/judicial/fyck.shtml?action=toZgfyTaskList">
			<input type="hidden" name="CXMB_ID">
			<input type="hidden" name="taskIds">
			<input type="hidden" name="taskId">
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
	
		function showErrorInfo(taskId,errorInfo){
			var url = "${ctx}/judicial/fyck.shtml?action=toZGFYErrorInfo&taskIds="+taskId+"&errorInfo="+errorInfo;
			showDialog(encodeURI(url), 800, 400);
		}
		function doQuery(){			
			if(FormValidate(qryForm)){
				setTimeout("qryForm.submit()",100);
			}
			return false;
		}	
		
		function toView(taskId){			
			detailForm.action = "${ctx}/judicial/fyck.shtml?action=toViewZgfyTask";
			detailForm.taskId.value = taskId;
			setTimeout("detailForm.submit()",100);
			return false;
		}	
		
		
		
	</script>
</html>

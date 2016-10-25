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
								当前位置：司法查控 &gt;&gt; 查询任务列表 
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
					<form action="${ctx}/judicial/djcx.shtml?action=toTaskList" method="post"
						name="qryForm" onSubmit="return doQuery();">
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>
							<!-- 	<td width="100" align="right">
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
										<option value="1">未提交审核</option>
										<option value="3">审核不通过</option>
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
						<input type="button" class="btn" value="增 加" onClick="addEntry();" />
						&nbsp;&nbsp;
						<input type="button" class="btn" value="提交审批" onClick="commitTask('2');" />
						<!--<input type="button" class="btn" value="删 除" onClick="deleteEntry();" />
						
						  input type="button" class="btn" value="生成SQL" onclick="scSQL();" /-->
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
									<input id="allSelect" type="checkbox" onclick="selectAll(this);"/>全选
								</th>
								<th width="140" align="center">
									任务编号
								</th>
								<!--<th width="140" align="center">
									任务名称
								</th>
									 <th  align="center">
									查询部门
								</th>
								
							 <th  align="center">
									查询模板
								</th>
								-->
								<th  align="center">
									导出格式
								</th>
								<th  align="center">
									登记方式
								</th>
								
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
										
										<input type="checkbox" name="taskId" value="${rst.TASK_ID }"/>
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
									<!-- <td align="center">
										${rst.TASK_NAME }
									</td>
									<td align="center">
										${rst.QRY_DEPT}
									</td>
									<td align="center">
										${rst.CXMB_ID}
									</td>-->
									<td align="center">
										${rst.EXP_FORMAT}
									</td>
									<td align="center">
										<c:if test="${rst.REG_WAY eq 1}">
											文件导入
										</c:if>
										<c:if test="${rst.REG_WAY eq 2}">
											手工录入
										</c:if>
										
									</td>
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
										<c:if test="${rst.TASK_STU eq 1}">
											未提交审核
										</c:if>
										
										<c:if test="${rst.TASK_STU eq 3}">
											<a href="#" style="text-decoration: underline" onclick="showAuditInfo('${rst.TASK_ID }','${rst.AUDIT_INFO}');"><font color="red">审核不通过</font></a>
										</c:if>
									
									</td>
									<td align="center">
										<!---<a href="#">查看</a>-->-
										<c:choose>
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
										<!---<a href="#" onclick="commitTask('${rst.TASK_ID }','2');">提交审批</a>--->
										<a href="#" onclick="delTask('${rst.TASK_ID }');">删除任务</a>-
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
								<form action="${ctx}/judicial/djcx.shtml?action=toTaskList"
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
			<input type="hidden" name="_backUrl" value="${ctx}/judicial/djcx.shtml?action=toTaskList">
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
			
	$("input[name='taskId']").click(function(){
	    var flag = true;
	    $("input[name='taskId']").each(function (){
	        if(!$(this).prop("checked")){
	            flag = false;
	        }
	    });
	    if(flag){
	        $("#allSelect").attr("checked","checked");
	    }else{
	        $("#allSelect").removeAttr("checked");
	    }
	});
	
	function cz(){
		qryForm.TASK_STU.value = "";
		qryForm.d01.value = "";
		qryForm.ksr.value = "";
		qryForm.jsr.value = "";
		qryForm.d02.value = "";
		   
	}
	
	function selectAll(obj){
	    if(obj.checked==true){
	        $("input[name='taskId']").attr("checked","checked");
	    }else{
	        $("input[name='taskId']").removeAttr("checked");
	    }
	}
		
		function showAuditInfo(taskId,auditInfo){
			var url = "${ctx}/judicial/djcx.shtml?action=toAuditInfo&taskIds="+taskId+"&readonly=true&auditInfo="+auditInfo;
			
			showDialog(encodeURI(url), 800, 400);
		}
		
		function delTask(taskId){		
			if(confirm("确定要删除该任务吗?")){
				detailForm.taskId.value=taskId;
				detailForm.action="${ctx}/judicial/djcx.shtml?action=delTask";
				detailForm.submit();
			}
			
			return true;
		}	
		
		function commitTask(taskStu){	
			var checkBoxs = document.getElementsByName("taskId");
			var str = "";
			if(confirm("确定要提交审核任务吗?")){
				for(var i=0;i<checkBoxs.length;i++){
					if(checkBoxs[i].checked == true){
						str += checkBoxs[i].value+",";
					}
				}
				if(str == ""){
					alert("请选择一条记录提交");
					return false;
				}
				detailForm.taskIds.value=str;
				detailForm.taskStu.value=taskStu;
				detailForm.action="${ctx}/judicial/djcx.shtml?action=updateTaskStu";
				showWaitPanel('');
				setTimeout("detailForm.submit()",100);	
			}
			return true;
		}	
		
		function doQuery(){			
			if(FormValidate(qryForm)){
				setTimeout("qryForm.submit()",100);
			}
			return false;
		}	
		
	
		
		function addEntry(){
			detailForm.action="${ctx}/judicial/djcx.shtml?action=toAddTask";
			setTimeout("detailForm.submit()",100);	
		}
		
	</script>
</html>

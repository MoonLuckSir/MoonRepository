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
		<title>审核反馈信息</title>
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
								当前位置：司法查控&gt;&gt; 审核查询条件&gt;&gt; 审核反馈信息  
							</td> 
							<td width="50" align="right">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td class="rowSplit"></td></tr> 
			<tr>
				<td>
					<form name="form" method="post"
						action="${ctx}/judicial/djcx.shtml?action=updateTaskStu" >
						<input type="hidden" name="SM" value="${param.SM}">
						<input type="hidden" name="taskStu" value="3">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									审核反馈信息
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
														<td width="25%" height="30" class="detail_label">
															任务编号：
														</td>
														<td width="75%" class="detail_content"> 
															<input readonly="readonly" class="readonly" name="taskIds" type="text" autocheck="true" required="true" label="任务编号"
																pattern="string" value="${param.taskIds}">
														</td>
													</tr>
													
													<tr>
														<td height="30" align="right" class="detail_label">
															审核不通过原因：
														</td>
														<td class="detail_content">
															<textarea rows="5"  cols="30" autocheck="true" <c:if test="${not empty param.readonly}">readonly="readonly"</c:if>  name="audit_info" autocheck="true" required="true" pattern="string" label="审核不通过原因">${param.auditInfo }</textarea>
															
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
									<c:choose>
										<c:when test="${empty param.readonly}">
											<input type="button" onclick="doSave();" class="btn" value="确 定">
											&nbsp;&nbsp;
											<input type="reset" class="btn" value="重 置">
											&nbsp;&nbsp;
											<input type="button" onclick="history.back();" class="btn" value="返 回">
										</c:when>
										<c:otherwise>
											<input type="button" onclick="cancel1()" class="btn" value="返 回">
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
		<IFRAME name="innerFrame" src="${ctx }/pages/index/blank.html" width="100%" height="0"></IFRAME>
	</body>
	<%@ include file="/commons/jslibs.jsp"%>
	<script type="text/javascript">InitFormValidator(0);</script>
	<script type="text/javascript">
		function doSave(){
			if(FormValidate(0)){
				setTimeout("form.submit()",100);
			}
			return false;
		}
		
		function cancel1(){
			window.close();
		}
		
		function cancel(){
			//alert(window.parent.dialogArguments.document.execCommand('Refresh'));
			
			window.parent.dialogArguments.document.execCommand('Refresh');
			window.close();
			
		}
		
		function checkSave(){
			if(innerFrame.isOk){
				_showMsgPanel(innerFrame.getMessage(),"系统提示","cancel();");
			}else{
				setTimeout("checkSave();",200);
			}
		}
	</script>
</html>

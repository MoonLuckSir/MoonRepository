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
		<title>查询报错信息 </title>
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
								当前位置：人民银行电信诈骗查控&gt;&gt;查控报错信息 
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
						action="" >
						<input type="hidden" name="SM" value="${param.SM}">
						<input type="hidden" name="taskStu" value="3">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									查询报错信息 
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
															<input readonly="readonly" class="readonly" name="APPLICATIONID" type="text" autocheck="true" required="true" label="任务编号"
																pattern="string" value="${param.APPLICATIONID }">
														</td>
													</tr>
													
													<tr>
														<td height="30" align="right" class="detail_label">
															查询报错信息：
														</td>
														<td class="detail_content">
															<textarea rows="5"  cols="30" autocheck="true" readonly="readonly"  name="errorInfo" autocheck="true" required="true" pattern="string" label="查询报错信息">${errorInfo }</textarea>
															
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
									<input type="button" onclick="cancel1()" class="btn" value="返 回">	
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

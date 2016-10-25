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
		<title>分配角色报表</title>
	</head>
	<body>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
			<tr>
				<td height="27">
					<table width="100%" height="100%" cellspacing="0" cellpadding="0"
						class="wz">
						<tr>
							<td width="28" align="center"><label class="wz_img"></label></td>
							<td>  
								当前位置：系统管理 &gt;&gt; 角色管理 &gt;&gt; 分配角色报表
							</td> 
							<td width="50" align="right">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td class="rowSplit"></td></tr> 
			<tr>
				<td>
					<table width="100%" height="100%" cellspacing="0" cellpadding="0">
						<tr>
							<td valign="top" width="30%">
								<div class="lst_area">
									<table width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
										<tr><th width="20"></th><th height="25" width="100" align="center">属性</th><th align="center">属性值</th></tr>
										<tr><th>1</th><td height="25" align="center">角色编号</td><td>${entry.ROLE_ID }</td></tr>
										<tr><th>2</th><td height="25" align="center">角色名称</td><td>${entry.ROLE_NAME }</td></tr>
										<tr><th>3</th><td height="25" align="center">角色级别</td><td>${entry.ROLE_LVL }</td></tr>
										<tr><th>4</th><td height="25" align="center">角色类别</td><td>${entry.ROLE_TYPE}</td></tr>
										<tr><th>5</th><td height="25" align="center">首页链接</td><td>${entry.INDEX_URL}</td></tr>
										<tr><th>6</th><td height="25" align="center">角色注释</td><td>${entry.ROLE_DESC }</td></tr>
									</table> 
								</div>
							</td>
							<td valign="top">
								<div class="lst_area">
									<form name="form" method="post" target="innerFrame"
										action="${ctx}/system/role.shtml?action=alotRpt"
										onsubmit="return doSave();">
										<input type="hidden" name="roleId" value="${param.roleId }">
										<table style="width: 100%; height: 100%;" border="0" cellspacing="0">
											<tr>
												<td height="30">
												<table style="width: 100%; height: 100%;" border="0" cellspacing="0">
													<tr>
														<td width="70" align="right" nowrap>
															报表类别：
														</td>
														<td width="120">
															<select name="rptType" style="width:100px;" onchange="loadUsers();"> 
																<c:forEach items="${rst0302}" var="rst">
																	<option value="${rst.VAR_ID}">${rst.VAR_NAME}</option>
																</c:forEach>
															</select>
														</td>													
														<td width="70" align="right" nowrap>
															报表类别：
														</td>
														<td width="120">
															<select name="rptParType" style="width:100px;" onchange="loadUsers();"> 
																<option value="">--全部--</option>
																<c:forEach items="${rst02}" var="rst">
																	<option value="${rst.VAR_ID}">${rst.VAR_NAME}</option>
																</c:forEach>
															</select> 
														</td>
														<td></td>
													</tr></table>
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<div id="userDiv" style="width:100%;height:100%;overflow-y:auto;border:1 solid #ccc;"></div>
												</td>
											</tr>								
											<tr>
												<td height="30">
													<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="lst_toolbar">
														<tr><td align="center">
															<input type="button" class="btn" value="保 存" onclick="doSave();"/> 
															<input type="button" class="btn" value="重 置" onclick="loadUsers();"/> 
															<input type="button" class="btn" value="返 回" onclick="gotoBack();"/></td></tr>
													</table>
												</td>
											</tr> 
										</table> 
									</form>
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<IFRAME name="innerFrame" src="${ctx}/pages/index/blank.html"
			width="100%" height="0"></IFRAME> 
	</body> 
	<%@ include file="/commons/jslibs.jsp"%>
	<script type="text/javascript">  	
		var his=1;
	   	function gotoBack(){
	   		showWaitPanel('');
	   		if(his>1){
	   			setTimeout("history.go(0-his);",fastSpeed);
	   		}else{ 
	   			setTimeout("history.back()",fastSpeed);
	   		}	   		
	   	}	
	   	function doSave(){
			if(FormValidate(0)){
				his++;
				innerFrame.location.href="${ctx}/pages/index/blank.html"; 
				setTimeout("form.submit()",fastSpeed);
				setTimeout("checkSave()",200);
			}
			return false;
		} 
		function checkSave(){
			if(innerFrame.isOk){
				showMsgPanel(innerFrame.getMessage()); 
			}else{
				// 仍在執行
				setTimeout("checkSave()",200);
			}	
		} 
		function loadUsers(){
			showWaitPanel("");
			var url="${ctx}/system/role.shtml?action=loadReports&roleId=${param.roleId}";
			url+="&rptType="+form.rptType.value;
			url+="&rptParType="+form.rptParType.value;
			var request = YAHOO.util.Connect.asyncRequest("POST", url, {
				success : function(oResponse) {
					g('userDiv').innerHTML = oResponse.responseText;
					closeWindow();
				},
				failure : function(oResponse) {
					showMsgPanel("加载报表清单失败！");
				} 
			});
		} 
		
		var init=function(){
			loadUsers();
		}();
	</script>
</html>

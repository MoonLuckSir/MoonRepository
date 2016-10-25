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
		<title>分配角色</title>
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
								当前位置：系统管理 &gt;&gt; 用户管理 &gt;&gt; 分配角色
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
										<tr><th>1</th><td height="25" align="center">用户编号</td><td>${entry.USER_ID }</td></tr>
										<tr><th>2</th><td height="25" align="center">用户名称</td><td>${entry.USER_NAME }</td></tr>
										<tr><th>3</th><td height="25" align="center">所属机构</td><td>${entry.ORG_NAME }</td></tr>
										<tr><th>4</th><td height="25" align="center">报表权限</td><td>
											<c:choose>
												<c:when test="${entry.RPT_RIGHT eq '1'}">
													用户报表
												</c:when>
												<c:otherwise>  
													角色报表 
												</c:otherwise>
											</c:choose>
										</td></tr>
										<tr><th>5</th><td height="25" align="center">用户职位</td><td>${entry.USER_POS}</td></tr>
										<tr><th>6</th><td height="25" align="center">用户状态</td><td>
											<c:choose>
												<c:when test="${entry.USER_STUS eq '1'}">
													<span class="FontGreen">正常</span>
												</c:when>
												<c:otherwise>
													<span class="FontRed">已注销</span>
												</c:otherwise>
											</c:choose></td></tr>
									</table> 
								</div>
							</td>
							<td valign="top">
								<div class="lst_area">
									<form name="form" method="post" action="${ctx}/system/user.shtml?action=alotUserRoles"
										onsubmit="return doSave();">
										<input type="hidden" name="userId" value="${param.userId }">
										<input type="hidden" name="_backUrl" value="${ctx}/system/user.shtml?action=toQuery">
										<table style="width: 100%; height: 100%;" border="0" cellspacing="0">
											<tr>
												<td colspan="2">
													<div id="roleDiv" style="width:100%;height:100%;overflow-y:auto;border:1 solid #ccc;"></div>
												</td>
											</tr>								
											<tr>
												<td height="30">
													<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="lst_toolbar">
														<tr><td align="center">
															<input type="button" class="btn" value="保 存" onclick="doSave();"/> 
															<input type="button" class="btn" value="重 置" onclick="loadRoles();"/> 
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
	</body> 
	<%@ include file="/commons/jslibs.jsp"%>
	<script type="text/javascript">  	
		var his=1;
	   	function gotoBack(){
	   		if(his>1){
		   		history.go(0-his);
	   		}else{
	   			history.back();
	   		}	   		
	   	}	
	   	function doSave(){
			if(FormValidate(0)){
				his++;
				setTimeout("form.submit()",100);
				setTimeout("checkSave()",500);
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
		function loadRoles(){
			showWaitPanel("");
			var url="${ctx}/system/user.shtml?action=loadRoles&userId=${param.userId}";
			var request = YAHOO.util.Connect.asyncRequest("POST", url, {
				success : function(oResponse) {
					var div=g('roleDiv');
					div.innerHTML = oResponse.responseText;
					closeWindow();
				},
				failure : function(oResponse) {
					showMsgPanel("加载报表清单失败！");
				} 
			});
		} 
		
		var init=function(){
			loadRoles();
		}();
	</script>
</html>

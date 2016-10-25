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
		<title>分配用户角色</title>
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
								当前位置：系统管理 &gt;&gt; 角色管理 &gt;&gt; 分配用户角色
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
					 	<td width="35%">					 	
							<div class="lst_area">
								<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="lst">
									<tr>
										<th height="25" align="left" nowrap>&nbsp;&nbsp;归属机构</th> 
									</tr>
									<tr><td colspan="2" style="border-top:2 solid #eee;padding:0px 5px;">
										<div id="organTree" style="overflow: auto; height: 100%; width: 100%;"></div></td></tr>
								</table>	  
							</div></td>
					 	<td>	 		 	
							<div class="lst_area">
							<form name="form" method="post" target="innerFrame"
								action="${ctx}/system/role.shtml?action=alotUser"
								onsubmit="return doSave();">
								<input type="hidden" name="roleId" value="${param.roleId }">
								<table style="width: 100%; height: 100%;" border="0" cellspacing="0">
									<tr>
										<td height="30">
										<table style="width: 100%; height: 100%;" border="0" cellspacing="0">
											<tr>
											<td width="90" align="right" nowrap>
												用户所在机构：
											</td>
											<td>
												<input type="text" name="tempName"
													value="9900 | 省联社" class="readonly"
													callback="loadUsers" style="width: 280px;">
												<input type="hidden" name="orgId" value="9900">
											</td></tr></table>
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
					</td></tr></table> 
					<IFRAME name="innerFrame" src="${ctx}/pages/index/blank.html"
						width="100%" height="0"></IFRAME> 
				</td>
			</tr>
		</table>
	</body>
	<%@ include file="/commons/jslibs.jsp"%>
	<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/treeview/treeview.css" />
	<script type="text/javascript" src="${ctx}/scripts/yui/treeview-min.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/core/organ-dynamic.js"></script> 
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
			if(form.orgId.value==""){
				if(confirm("您确信要清除角色用户配制吗？")){
					showWaitPanel('');
					his++;
					innerFrame.location.href="${ctx}/pages/index/blank.html";
					form.action="${ctx}/system/role.shtml?action=alotUserRemove";
					setTimeout("form.submit()",100);
					setTimeout("checkSave()",500);
				}				
			}else if(FormValidate(0)){
				his++;
				innerFrame.location.href="${ctx}/pages/index/blank.html";
				form.action="${ctx}/system/role.shtml?action=alotUser";
				setTimeout("form.submit()",fastSpeed);
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
		function loadUsers(){
			showWaitPanel("");
			var url="${ctx}/system/role.shtml?action=loadOrgUsers&roleId=${param.roleId}&orgId="+form.orgId.value;
			var request = YAHOO.util.Connect.asyncRequest("POST", url, {
				success : function(oResponse) {
					var userDiv=g('userDiv');
					userDiv.innerHTML = oResponse.responseText;
					closeWindow();
				},
				failure : function(oResponse) {
					showMsgPanel("加载用户失败！");
				} 
			});
		} 
		var init = function(){
			var url="${ctx}/system/organ.shtml?action=seletor";
			var nmObj = form.tempName;
			var valObj = form.orgId;
			var rootNo = valObj.value;
			var rootLabel = nmObj.value;
			buildDynaOrgTree(rootNo, rootLabel, nmObj, valObj, "organTree", url, '${ctx}') ;
			loadUsers();
		}();
	
	</script>
</html>

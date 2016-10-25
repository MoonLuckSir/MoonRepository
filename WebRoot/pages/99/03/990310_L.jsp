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
		<title>角色列表</title>
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
								当前位置：系统管理 &gt;&gt; 角色管理 &gt;&gt; 角色列表
							</td> 
							<td width="50" align="right">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td class="rowSplit"></td></tr>
			<tr>
				<td height="20">
					<form action="${ctx}/system/role.shtml?action=toList" method="post"
						name="qryForm" onsubmit="return doQuery();">
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>
								<td width="70" align="right">
									角色编号：
								</td>
								<td width="120">
									<input name="roleId" type="text" autocheck="true" style="width:110px;"
										pattern="string" maxlength="6" label="角色编号">
									<script type="text/javascript">
										qryForm.roleId.value="${param.roleId}";
									</script>
								</td>	
								<td width="70" align="right">
									角色名称：
								</td>
								<td width="120">
									<input name="roleName" type="text" autocheck="true" style="width:110px;"
										pattern="string" maxlength="100" label="角色名称">
									<script type="text/javascript">
										qryForm.roleName.value="${param.roleName}";
									</script>
								</td>	
								
								<td align="left"> 
									<input type="submit" class="btn" value="查 询"> 
									<input type="reset" class="btn" value="重 置"> 
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
						<input type="button" class="btn" value="增 加" onclick="addEntry();"/> 
						<input type="button" class="btn" value="修 改" onclick="modifyEntry();"/> 
						<input type="button" class="btn" value="删 除" onclick="deleteEntry();"/> 
						<input type="button" class="btn" value="查 看" onclick="viewEntry();"/> 
						&nbsp;
						<input type="button" class="btn" value="分配菜单" onclick="alotMenu()"/> 
						&nbsp; 
					</div>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<div class="lst_area">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="lst">
							<tr class="fixedRow">
								<th width="20" height="25"> 
								</th>
								<th width="20"> 												
								</th>
								<th width="120" height="25">
									角色编号
								</th>
								<th width="318" align="center">
									角色名称
								</th>
								<th width="100" align="center">
									角色级别
								</th>
								<th width="100" align="center">
									角色类别
								</th> 
								<th align="center">
									备注
								</th>
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)">
									<td height="25" nowrap class="row_no">
										${rst.RN}
									</td>
									<th width="20"> 	
										<input name="eid" type="radio" value="${rst.ROLE_ID }" onclick="setEid(this);">											
									</th>
									<td align="center">
										${rst.ROLE_ID }
									</td>
									<td>
										&nbsp;${rst.ROLE_NAME}
									</td>
									<td align="center">
										${rst.ROLE_LVL}
									</td>
									<td align="center">
										${rst.ROLE_TYPE}
									</td> 
									<td>
										&nbsp;${rst.ROLE_DESC}
									</td> 
								</tr>
							</c:forEach>
							<c:if test="${empty page.result}">
								<tr>
									<td height="25" colspan="7" align="center" class="lst_empty">
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
					<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="lst_toolbar">
						<tr>
							<td>
								<form action="${ctx}/system/role.shtml?action=toList"
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
			<input type="hidden" name="_backUrl"
				value="${ctx}/system/role.shtml?action=toList">
			<input type="hidden" name="roleId">
			${paramCover.coveredInputs}
		</form>
		<!-- 记录单选框的选择值 -->
		<input type="hidden" id="tmpId">
	</body>
	<%@ include file="/commons/jslibs.jsp"%> 
	<script type="text/javascript">
		var eid = g("tmpId");
		function setEid(obj){
			eid.value=obj.value;
		}		
		function doQuery(){			
			if(FormValidate(qryForm)){
				setTimeout("qryForm.submit()",100);
			}
			return false;
		}	
		function checkEid(roleId){		
			if(roleId==null || ""==roleId.trim()){
				showErrorMsg("请选择角色对象！");
				return false;
			}
			return true;
		}		
		function todo(act,roleId){
			showWaitPanel('');
			detailForm.action=act;
			detailForm.roleId.value=roleId;
			setTimeout("detailForm.submit()",100);	
			try{ eid.parentNode.focus(); }catch(e){}//去除焦点	
		}		
		function addEntry(){
			todo("${ctx}/system/role.shtml?action=toAdd","");
		}
		function modifyEntry(){
			var roleId = eid.value;
			if(!checkEid(roleId))return;
			todo("${ctx}/system/role.shtml?action=toModi",roleId);
		}	
		function viewEntry(){
			var roleId = eid.value;
			if(!checkEid(roleId))return;
			todo("${ctx}/system/role.shtml?action=toView",roleId);
		}	
		function deleteEntry(){
			var roleId = eid.value;
			if(!checkEid(roleId))return;
			if(confirm("您确信要删除该角色吗？")){ 				
				todo("${ctx}/system/role.shtml?action=delete",roleId);
			}
		}	
		function alotMenu(){
			var roleId = eid.value;
			if(!checkEid(roleId))return;
			todo("${ctx}/system/role.shtml?action=toAlotMenu",roleId);
		}	
		function alotUser(){
			var roleId = eid.value;
			if(!checkEid(roleId))return;
			todo("${ctx}/system/role.shtml?action=toAlotUser",roleId);
		}		
		function alotReport(){
			var roleId = eid.value;
			if(!checkEid(roleId))return;
			todo("${ctx}/system/role.shtml?action=toAlotRpt",roleId);
		}	
		//<input type="button" class="btn" value="分配用户" onclick="alotUser()"/>  
	</script>
</html>

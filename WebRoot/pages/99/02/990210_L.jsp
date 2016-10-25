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
		<title>用户列表</title>
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
								当前位置：系统管理 &gt;&gt; 用户管理 &gt;&gt; 用户列表
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
				<td height="20">
					<form action="${ctx}/system/user.shtml?action=toList" method="post"
						name="qryForm" onsubmit="return doQuery();">
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>
								<td width="70" align="right">
									用户编号：
								</td>
								<td width="120">
									<input name="userId" type="text" autocheck="true" style="width:110px;"
										pattern="string" maxlength="20" label="用户编号">
									<script type="text/javascript">
										qryForm.userId.value="${param.userId}";
									</script>
								</td>	
								<td width="70" align="right">
									用户名称：
								</td>
								<td width="120">
									<input name="userName" type="text" autocheck="true" style="width:110px;"
										pattern="string" maxlength="60" label="用户名称">
									<script type="text/javascript">
										qryForm.userName.value="${param.userName}";
									</script>
								</td>	
								<td width="70" align="right">
									所属机构：
								</td>
								<td width="200">
									<input name="orgName" type="text" readonly 
										onclick="select_org(this)" style="width:180px;">
									<input type="button" class="btnSelect" onclick="select_org(this)" value="..">
									<input name="orgId" type="hidden">
									<script type="text/javascript">
										qryForm.orgName.value="${param.orgName}";
										qryForm.orgId.value="${param.orgId}";
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
						<input type="button" class="btn" value="增 加" onclick="addEntry();" />
						<input type="button" class="btn" value="修 改" onclick="modiEntry();" />
						<input type="button" class="btn" value="删 除" onclick="deleteEntry();" />
						<input type="button" class="btn" value="查 看" onclick="viewEntry();" />
						&nbsp;
						<input type="button" class="btn" value="密码重置" onclick="resetPwd();" />
						<input type="button" class="btn" value="分配角色" onclick="setRole();" /> 
						&nbsp;
						<input type="button" class="btn" value="启用用户" onclick="avail();" />
						<input type="button" class="btn" value="注销用户" onclick="unavail();" />
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
									用户编号
								</th>
								<th width="120" align="center">
									用户姓名
								</th>
								<th width="240" align="center">
									所属机构
								</th>
					<!--  		<th width="100" align="center">
									报表权限
								</th>  -->
								<th align="center">
									用户职位
								</th>
								<th width="100" align="center">
									用户状态
								</th>
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)"
									onmouseout="mout(this)">
									<td height="25" nowrap class="row_no">
										${rst.RN}
									</td>
									<th width="20">
										<input type="radio" name="eid" value="${rst.USER_ID }${rst.USER_STUS}"
											onclick="setEid(this);">
									</th>
									<td align="left">
										${rst.USER_ID }
									</td>
									<td>
										&nbsp;${rst.USER_NAME}
									</td>
									<td align="left">
										${rst.ORG_ID}|${rst.ORG_NAME }
									</td>
									<!--  
									<td align="center">
										<c:choose>
											<c:when test="${rst.RPT_RIGHT eq '0'}">
												角色报表权限
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${rst.RPT_RIGHT eq '1'}">用户报表权限</c:when>
												</c:choose>
											</c:otherwise>
										</c:choose>
									</td>
									-->
									<td>
										&nbsp;${rst.USER_POS}
									</td>
									<td align="center">
										<c:choose>
											<c:when test="${rst.USER_STUS eq '1'}">
												<span class="FontGreen">正常</span>
											</c:when>
											<c:otherwise>
												<span class="FontRed">已注销</span>
											</c:otherwise>
										</c:choose>
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
					<table width="100%" height="100%" border="0" cellpadding="0"
						cellspacing="0" class="lst_toolbar">
						<tr>
							<td>
								<form action="${ctx}/system/user.shtml?action=toList"
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
			<input type="hidden" name="_backUrl" value="${ctx}/system/user.shtml?action=toList">
			<input type="hidden" name="userId">
			${paramCover.coveredInputs}
		</form>
		<input type="hidden" name="tmpId">
		<input type="hidden" name="tmpStus">
	</body>
	<%@ include file="/commons/jslibs4tree.jsp"%>
	<script type="text/javascript">		
		function doQuery(){			
			if(FormValidate(qryForm)){
				setTimeout("qryForm.submit()",100);
			}
			return false;
		}	
		var tmpId = g("tmpId");
		var tmpStus = g("tmpStus");
		function setEid(obj){
			var x = obj.value.trim().length;
			tmpId.value=obj.value.substr(0,x-1);
			tmpStus.value = obj.value.substr(x-1,x);
		}		
		function checkEid(userId){		
			if(userId==null || ""==userId.trim()){
				showErrorMsg("请选择用户对象！");
				return false;
			}
			return true;
		}		
		function todo(act,userId){
			showWaitPanel('');
			detailForm.action=act;
			detailForm.userId.value=userId;
			setTimeout("detailForm.submit()",100);	
			try{ tmpId.parentNode.focus(); }catch(e){}
		}		
		function deleteEntry(){ 
			var userId = tmpId.value;
			if(!checkEid(userId))return;
			if(confirm("您确信要删除该用户吗？")){
				todo("${ctx}/system/user.shtml?action=delete",userId);
			}
		}
		function modiEntry(){
			var userId = tmpId.value;
			if(!checkEid(userId))return;
			todo("${ctx}/system/user.shtml?action=toModi",userId);
		}
		function addEntry(){
			todo("${ctx}/system/user.shtml?action=toAdd","");
		}
		function resetPwd(){
			var userId = tmpId.value;
			if(!checkEid(userId))return;
			todo("${ctx}/system/user.shtml?action=resetPwd",userId);
		}
		function setRole(){
			var userDesc = tmpStus.value;
			if(userDesc !=  '1'){
				showErrorMsg("已注销用户不能分配角色！");
				return;
			}
			var userId = tmpId.value;
			if(!checkEid(userId))return;
			todo("${ctx}/system/user.shtml?action=toAlotRole",userId);
		}
		function setReport(){
			var userId = tmpId.value;
			if(!checkEid(userId))return;
			todo("${ctx}/system/user.shtml?action=toAlotReport",userId);
		}
		function viewEntry(){
			var userId = tmpId.value;
			if(!checkEid(userId))return;
			todo("${ctx}/system/user.shtml?action=toView",userId);
		}
		function select_org(btn){			
			var nameTarget=qryForm.orgName;
			var srcTarget=qryForm.orgId;
			var sUrl="${ctx}/system/organ.shtml?action=seletor";
			selectOrgan(btn,nameTarget,srcTarget,false,sUrl,'${ctx}');	
		} 
		function avail(){
			var userId = tmpId.value;
			var userDesc = tmpStus.value;
			if(!checkEid(userDesc))return;
			if(userDesc=="0"){
				if(confirm("您确信要启动该用户吗？")){
					todo("${ctx}/system/user.shtml?action=avail",userId);
				}
			}else{
				alert("该用户的状态是正常的，不可执行此操作！");
			}
		}
		function unavail(){
			var userId = tmpId.value;
			var userDesc = tmpStus.value;
			if(!checkEid(userDesc))return;
			if(userDesc=="1"){
				if(confirm("您确信要注销该用户吗？注销后，该用户不可用！")){
					todo("${ctx}/system/user.shtml?action=unavail",userId);
				}
			}else{
				alert("该用户的状态已注销的，不可执行此操作！");
			}
		}
	</script>
</html>

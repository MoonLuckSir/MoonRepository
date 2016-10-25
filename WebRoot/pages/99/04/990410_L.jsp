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
		<title>菜单列表</title>
	</head>
	<body>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel"> 
			<tr>
				<td height="27">
					<table width="100%" height="100%" cellspacing="0" cellpadding="0" class="wz">
						<tr>
							<td width="28" align="center"><label class="wz_img"></label></td>
							<td>当前位置：系统管理 &gt;&gt; 菜单管理 &gt;&gt; 菜单列表</td> 
							<td width="50" align="right">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td class="rowSplit"></td></tr>
			<tr>
				<td height="20">
					<form action="${ctx}/system/menu.shtml?action=toList" method="post"
						name="qryForm" onsubmit="return doQuery();">
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>
								<td width="70" align="right">
									菜单编号：
								</td>
								<td width="120">
									<input name="menuId" type="text" autocheck="true" style="width:110px;"
										pattern="string" maxlength="6" >
									<script type="text/javascript">
										qryForm.menuId.value="${param.menuId}";
									</script>
								</td>	
								<td width="70" align="right">
									菜单名称：
								</td>
								<td width="120">
									<input name="menuName" type="text" autocheck="true" style="width:110px;"
										pattern="string" maxlength="60" >
									<script type="text/javascript">
										qryForm.menuName.value="${param.menuName}";
									</script>
								</td>	
								<td width="70" align="right">
									上级菜单：
								</td>
								<td width="120">
									<input name="menuParId" type="text" autocheck="true" style="width:110px;"
										pattern="string" maxlength="6" >
									<script type="text/javascript">
										qryForm.menuParId.value="${param.menuParId}";
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
					</div>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<div class="lst_area">
						<table width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
							<tr class="fixedRow">
								<th width="20" height="25"> </th>
								<th width="120" height="25">
									菜单编号
								</th>
								<th width="318" align="center">
									菜单名称
								</th>
								<th width="120" align="center">
									上级菜单
								</th>
								<th align="center" nowrap="nowrap">
									备注
								</th>
								<th width="100" align="center">
									排序
								</th>
								<th width="130">
									操作
								</th>
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)">
									<td height="25" nowrap class="row_no">
										${rst.RN}
									</td>
									<td align="center">
										${rst.MENU_ID }
									</td>
									<td>
										&nbsp;${rst.MENU_NAME}
									</td>
									<td align="center">
										${rst.MENU_PAR_ID}
									</td>
									<td>
										&nbsp;${rst.MENU_DESC}
									</td>
									<td align="center">
										${rst.MENU_SORT}
									</td> 
									<td align="center">
										-
										<a href="javascript:modifyEntry('${rst.MENU_ID }')">修改</a>-
										<a href="javascript:deleteEntry('${rst.MENU_ID }')">删除</a>-
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
								<form action="${ctx}/system/menu.shtml?action=toList"
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
				value="${ctx}/system/menu.shtml?action=toList">
			<input type="hidden" name="menuId">
			${paramCover.coveredInputs}
		</form>
	</body>
	<%@ include file="/commons/jslibs.jsp"%>
	<script type="text/javascript">
		function doQuery(){			
			if(FormValidate(qryForm)){
				setTimeout("qryForm.submit()",fastSpeed);
			}
			return false;
		}	
		function deleteEntry(menuId){
			if(confirm("您确信要删除该菜单吗？")){
				showWaitPanel('');				
				detailForm.action="${ctx}/system/menu.shtml?action=delete";
				detailForm.menuId.value=menuId;
				setTimeout("detailForm.submit()",fastSpeed);
			}
		}
		function modifyEntry(menuId){
			showWaitPanel('');
			detailForm.action="${ctx}/system/menu.shtml?action=toModi";
			detailForm.menuId.value=menuId;
			setTimeout("detailForm.submit()",fastSpeed);
		}
		function addEntry(){
			showWaitPanel('');
			detailForm.action="${ctx}/system/menu.shtml?action=toAdd";
			detailForm.menuId.value="";
			setTimeout("detailForm.submit()",fastSpeed);
		}
	</script>
</html>

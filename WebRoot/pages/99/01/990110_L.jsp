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
		<title>机构列表</title>
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
								当前位置：系统管理 &gt;&gt; 机构管理 &gt;&gt; 机构列表
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
				<td height="40">
					<form action="${ctx}/system/organ.shtml?action=toList"
						method="post" name="qryForm" onsubmit="return doQuery();">
						<table width="100%" border="0" cellpadding="2" cellspacing="0"
							class="qry">
							<tr>
								<td width="70" align="right">
									机构编号：
								</td>
								<td width="120">
									<input name="orgId" type="text" autocheck="true"
										style="width: 110px;" pattern="int" maxlength="20">
									<script type="text/javascript">
										qryForm.orgId.value="${param.orgId}";
									</script>
								</td>
								<td width="70" align="right">
									机构名称：
								</td>
								<td width="120">
									<input name="orgName" type="text" autocheck="true"
										style="width: 110px;" pattern="string" maxlength="10">
									<script type="text/javascript">
										qryForm.orgName.value="${param.orgName}";
									</script>
								</td>
								<td></td>
							</tr>
							<tr>
								<td width="70" align="right">
									机构级别：
								</td>
								<td width="120">
									<input name="orgLvl" type="text" autocheck="true" label="机构编号"
										style="width: 110px;" pattern="string" maxlength="3">
									<script type="text/javascript">
										qryForm.orgLvl.value="${param.orgLvl}";
									</script>
								</td>
								<td width="70" align="right">
									上级机构：
								</td>
								<td width="280">
									<input name="orgParName" type="text" readonly 
										onclick="select_orgPar(this)" style="width:250;" >
									<input type="button" class="btnSelect" onclick="select_orgPar(this)" value="..">
									<input name=orgParId type="hidden">
									<script type="text/javascript">
										qryForm.orgParId.value="${param.orgParId}";
										qryForm.orgParName.value="${param.orgParName}";
									</script>
								</td>
								<td >
									<input type="submit" class="btn" value="查 询">
									<input type="reset" class="btn" value="重 置">
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td class="rowSplit"></td>
			</tr>
			<tr>
				<td valign="top">
					<div class="lst_area"> 
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="lst">
							<tr class="fixedRow">
								<th width="30" height="25">
								</th>
								<th width="60" height="25">
									机构编号
								</th>
								<th width="318" align="center">
									机构名称
								</th>
								<th width="100" align="center">
									机构级别
								</th>
								<th width="120" align="center">
									上级机构
								</th>
								<th width="120" align="center">
									机构状态
								</th>
								<th align="center">
									备注
								</th>
								<!--  <th width="130">
									操作
								</th>-->
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<tr class="list_row${r}" onmouseover="mover(this)"
									onmouseout="mout(this)">
									<td height="25" class="row_no">
										${rst.RN}
									</td>
									<td align="center">
										${rst.ORG_ID }
									</td>
									<td>
										&nbsp;${rst.ORG_NAME}
									</td>
									<td align="center">
										${rst.ORG_LVL}
									</td>
									<td align="center">
										${rst.ORG_PAR_ID}
									</td>
									<td>
										${rst.ORG_STUS}
									</td>
									<td>
										&nbsp;${rst.ORG_DESC}
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
								<form action="${ctx}/system/organ.shtml?action=toList"
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
				value="${ctx}/system/organ.shtml?action=toList">
			<input type="hidden" name="orgId">
			${paramCover.coveredInputs}
		</form>
	</body>
	<%@ include file="/commons/jslibs4tree.jsp"%>
	<script type="text/javascript">
		function doQuery(){			
			if(FormValidate(qryForm)){
				setTimeout("qryForm.submit()",100);
			}
			return false;
		}	
		function deleteEntry(orgId){
			if(confirm("您确信要删除该菜单吗？")){
				showWaitPanel('');				
				detailForm.action="${ctx}/system/organ.shtml?action=delete";
				detailForm.orgId.value=orgId;
				setTimeout("detailForm.submit()",100);
			}
		}
		function modifyEntry(orgId){
			showWaitPanel('');
			detailForm.action="${ctx}/system/organ.shtml?action=toModi";
			detailForm.orgId.value=orgId;
			setTimeout("detailForm.submit()",100);
		}
		function addEntry(){
			showWaitPanel('');
			detailForm.action="${ctx}/system/organ.shtml?action=toAdd";
			detailForm.orgId.value="";
			setTimeout("detailForm.submit()",100);
		}
		function select_orgPar(btn){			
			var nameTarget=qryForm.orgParName;
			var srcTarget=qryForm.orgParId;
			var sUrl="${ctx}/system/organ.shtml?action=seletor&orgLvl=4";
			selectOrgan(btn,nameTarget,srcTarget,false,sUrl,'${ctx}');	
		} 
	</script>
</html>

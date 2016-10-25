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
		<title>配置表列表</title>
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
								当前位置：系统管理 &gt;&gt; 配置表管理 &gt;&gt; 配置表列表
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
					<form action="${ctx}/system/paramHis.shtml?action=toList" method="post"
						name="qryForm" onsubmit="return doQuery();">
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>
								<td width="70" align="right">
									模式名：
								</td>
								<td width="120">
									<input name="tabSchema" type="text" autocheck="true" style="width:110px;"
										pattern="string" maxlength="50" label="年度表名">
									<script type="text/javascript">
										qryForm.tabSchema.value="${param.tabSchema}";
									</script>
								</td>	
								<td width="70" align="right">
									原始表名：
								</td>
								<td width="90">
									<input name="tabShotName" type="text" autocheck="true" style="width:110px;"
										pattern="string" maxlength="40" label="原始表名">
									<script type="text/javascript">
										qryForm.tabShotName.value="${param.tabShotName}";
									</script>
								</td>
								<td width="70" align="right">
									状态：
								</td>
								<td width="120">
									<select name="tabState" style="width:100px;">
										<option></option>
										<option value="0">正常</option>
										<option value="1">废弃</option>
									</select>	
									<script type="text/javascript">
										qryForm.tabState.value="${param.tabState}";
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
								<th>
									模式名
								</th>
								<th align="center">
									原始表名
								</th>
								<th  align="center">
									年度
								</th>
								<th align="center">
									状态
								</th>
								<th align="center">
									类型
								</th>
								<th align="center">
									系统渠道
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
										<input type="radio" name="eid" value="${rst.ID}">
									</th>
									<td align="left">
										${rst.TABSCHEMA}
									</td>
									<td align="left">
										&nbsp;${rst.TABSHOTNAME}
									</td>
									<td align="left">
										&nbsp;${rst.TABYEAR}
									</td>
									<td align="center">
										<c:choose>
											<c:when test="${rst.TABSTATE eq '0'}">
												<span class="FontGreen">正常</span>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${rst.TABSTATE eq '1'}">
													 	<span class="FontRed">废弃</span>
													</c:when>
												</c:choose>
											</c:otherwise>
										</c:choose>
									</td>
									<td align="center">
										<c:choose>
											<c:when test="${rst.TABTYPE eq '0'}">
												<span>单表</span>
											</c:when>
											<c:otherwise>
												<c:choose>
													<c:when test="${rst.TABTYPE eq 'M'}">
													 	<span >月</span>
													</c:when>
													<c:otherwise>
														<span>年</span>
													</c:otherwise>
												</c:choose>
											</c:otherwise>
										</c:choose>
									</td>
									<td align="left">
										&nbsp;${rst.TABCRC}
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
								<form action="${ctx}/system/paramHis.shtml?action=toList"
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
			<input type="hidden" name="_backUrl" value="${ctx}/system/paramHis.shtml?action=toList">
			<input type="hidden" name="id">
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
				
		function checkEid(id){		
			if(id==null || ""==id.trim()){
				showErrorMsg("请选择配置表对象！");
				return false;
			}
			return true;
		}	
			
		function getRadioValue(name) {
			var radios = document.getElementsByName(name);
			var value = "";
			var flag = false;
			for (var i = 0; i < radios.length; i++) {
				if (radios[i].checked == true) {
					value = radios[i].value;
					flag = true;
					break;
				}
			}
			if (!flag) {
				return null;
			} else {
				return value;
			}
		}
		
		function todo(act,id){
			showWaitPanel('');
			detailForm.action=act;
			detailForm.id.value=id;
			setTimeout("detailForm.submit()",100);	
			try{ tmpId.parentNode.focus(); }catch(e){}
		}		

		function addEntry(){
			todo("${ctx}/system/paramHis.shtml?action=toAdd","");
		}
		

		function modiEntry(){
			var id = getRadioValue("eid");
			if(!checkEid(id))return;
			todo("${ctx}/system/paramHis.shtml?action=toModi",id);
		}
		
		function deleteEntry(){ 
			var id = getRadioValue("eid");
			if(!checkEid(id))return;
			if(confirm("您确信要删除吗？")){
				todo("${ctx}/system/paramHis.shtml?action=delete",id);
			}
		}
		
		function viewEntry(){
			var id = getRadioValue("eid");
			if(!checkEid(id))return;
			todo("${ctx}/system/paramHis.shtml?action=toView",id);
		}
	</script>
</html>

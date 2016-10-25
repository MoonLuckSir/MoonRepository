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
		<title>历史查询模板列表</title>
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
								当前位置：报表查询 &gt;&gt; 司法查询定制设计 &gt;&gt; 司法查询模板 &gt;&gt; 规则信息录入
							</td>
							<td width="50" align="right">&nbsp;
								
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="rowSplit"></td>
			</tr>
			<tr>
				<td height="20" align="right">
					<input type="button" class="btn" value="返 回" onClick="backurl();">
				</td>
			</tr>
			<tr><td class="rowSplit"></td></tr>
			<tr>
				<td height="30">
					<div class="lst_title">
						<input type="button" class="btn" value="增 加" onClick="addEntry();" />
						<input type="button" class="btn" value="修 改" onClick="modiEntry();" />
						<input type="button" class="btn" value="删 除" onClick="deleteEntry();" />
						
					</div>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<div class="lst_area">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="lst">
							<tr class="fixedRow">
								<th width="20"  height="25">
								</th>
								<th width="120" align="center">
									条件编号
								</th>
								<th width="160" align="center">
									规则名称
								</th>
								<th width="120" nowrap align="center">
									规则值
								</th>
								<th width="140" align="center">
									规则描述
								</th>																													
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" onMouseOver="mover(this)"
									onmouseout="mout(this)"  onclick="setEid(document.getElementById('eid${rr}'));">
									<th width="20">
										<input type="radio" name="eid" id="eid${rr}" value="${rst.RULE_ID }|${rst.RULE_NAME}"
											onclick="setEid(this);">
									</th>
									<td align="left">
										${rst.CONMX_NO }
									</td>
									<td align="left">
										${rst.RULE_NAME}
									</td>
									<td align="left">
										${rst.RULE_VALUE}
									</td>
									<td align="left">
										${rst.RULE_DESC}
									</td>
									
								</tr>
							</c:forEach>
							<c:if test="${empty page.result}">
								<tr>
									<td height="25" colspan="13" align="center" class="lst_empty">
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
								<form action="${ctx}/judicial/lscxrul.shtml?action=toList"
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
			<input type="hidden" name="_backUrl" value="${ctx}/judicial/lscxrul.shtml?action=toList">
			<input type="hidden" name="RULE_ID">
			<input type="hidden" name="CONMX_NO">
			<input type="hidden" name="REPMOD_NO">
			${paramCover.coveredInputs}
		</form>
		<input type="hidden" name="tmpId">
		<input type="hidden" name="tmpStus">
		<input type="hidden" name="tmpCONMX_NO" value="${param.CONMX_NO}">
		<input type="hidden" name="tmpREPMOD_NO" value="${param.REPMOD_NO}">
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
			obj.checked='true';
			var x = obj.value.trim().length;
			var wz = obj.value.indexOf("|");
			tmpId.value=obj.value.substr(0,wz);
			tmpStus.value = obj.value.substr(wz+1,x);
		}		
		function checkEid(RULE_ID){		
			if(RULE_ID==null || ""==RULE_ID.trim()){
				showErrorMsg("请选择一个对象！");
				return false;
			}
			return true;
		}	
		function checkZJEid(ZJID){		
			if(ZJID==null || ""==ZJID.trim()){
				showErrorMsg("主表ID丢失,请刷新页面！");
				return false;
			}
			return true;
		}	
		function todo(act,mxid,cmmxid,zbid){
			showWaitPanel('');
			detailForm.action=act;
			detailForm.REPMOD_NO.value = zbid;
			detailForm.CONMX_NO.value=mxid;
			detailForm.RULE_ID.value=cmmxid;
			setTimeout("detailForm.submit()",100);	
			try{ tmpId.parentNode.focus(); }catch(e){}
		}		
		function deleteEntry(){ 
			var RULE_ID = tmpId.value;
			if(!checkEid(RULE_ID))return;
			if(confirm("您确信要删除该条信息吗？")){
				todo("${ctx}/judicial/lscxrul.shtml?action=delete",'',RULE_ID,'');
			}
		}
		function modiEntry(){
			var RULE_ID = tmpId.value;
			if(!checkEid(RULE_ID))return;
			todo("${ctx}/judicial/lscxrul.shtml?action=toUpdate",'',RULE_ID,'');
		}
		function addEntry(){
			var CONMX_NO = tmpCONMX_NO.value;
			if(!checkZJEid(CONMX_NO))return;
			todo("${ctx}/judicial/lscxrul.shtml?action=toAdd",CONMX_NO,'','');
		}
		function viewEntry(){
			var RULE_ID = tmpId.value;
			if(!checkEid(RULE_ID))return;
			todo("${ctx}/judicial/lscxrul.shtml?action=toView",'',RULE_ID,'');
		}
		function toDetail(RULE_ID ){
			window.open("${ctx}/judicial/lscxrul.shtml?action=toView&&RULE_ID="+RULE_ID,
			"模板展现列明细","scrollbars=yes,resizable=yes,width=745,height=500,left=263,top=207"); //menubar=yes,
		}

		function backurl(){
			var REPMOD_NO = tmpREPMOD_NO.value;
			if(!checkZJEid(REPMOD_NO))return;
			todo("${ctx}/judicial/mbtjsz.shtml?action=toList",'','',REPMOD_NO);
		}

	</script>
</html>

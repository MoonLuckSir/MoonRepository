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
								当前位置：司法查控 &gt;&gt; 条件设置 &gt;&gt; 设计规则
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
				<td height="20">
					<form action="${ctx}/judicial/sfck.shtml?action=toRulList" method="post"
						name="qryForm" onSubmit="return doQuery();">
						<input type="hidden" name="CONDITION_ID" value="${param.CONDITION_ID }">
						<input type="hidden" name="CXMB_ID" value="${param.CXMB_ID }">
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>
								<td width="100" align="right">
									规则名称：
								</td>
								<td width="120">
									<select name="RULE_NAME"   >
										<option value="">---请选择---</option>
												<option value="1">必输项</option>
												<option value="2">下拉列表框</option>
												<option value="3">最大值</option>
												<option value="4">最小值</option>
												<option value="5">最大长度</option>
												<option value="6">最小长度</option>
										</select>
									<script type="text/javascript">
											qryForm.RULE_NAME.value = ${param.RULE_NAME}
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
						<input type="button" class="btn" value="增 加" onClick="addEntry();" />
						<input type="button" class="btn" value="修 改" onClick="modiEntry();" />
						<input type="button" class="btn" value="删 除" onClick="deleteEntry();" />
						
						&nbsp;
						<input type="button" class="btn" value="返回" onClick="goback();" />
					
						
						<!--  input type="button" class="btn" value="生成SQL" onclick="scSQL();" /-->
					</div>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<div class="lst_area">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="lst">
							<tr class="fixedRow">
								
								<th width="20">
								</th>
								<th width="100" align="center">
									规则编号
								</th>
								<th width="140" align="center">
									规则名称
								</th>
								<th  align="center">
									规则值
								</th>
								<th width="140" align="center">
									对应条件
								</th>
								<th  align="center">
									规则描述
								</th>																																	
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" id="${r}" onMouseOver="mover(this)" onMouseOut="mout(this)" onClick="setEid(document.getElementById('eid${rr}'));">
									<th width="20">
										<input type="radio" name="eid" id="eid${rr}" value="${rst.RULE_ID}"
											onclick="setEid(this);">
									</th>
									<td align="center">
										${rst.RULE_ID }
									</td>
									
									<td align="center">
										<c:if test="${rst.RULE_NAME eq '1'}">必输项</c:if>
										<c:if test="${rst.RULE_NAME eq '2'}">选择项</c:if>
										<c:if test="${rst.RULE_NAME eq '3'}">最大值</c:if>
										<c:if test="${rst.RULE_NAME eq '4'}">最小值</c:if>
										<c:if test="${rst.RULE_NAME eq '5'}">最大长度</c:if>
										<c:if test="${rst.RULE_NAME eq '6'}">最小长度</c:if>
										
									</td>
									<td align="center">
										${rst.RULE_VALUE }
									</td>
									<td align="center">
										${rst.CONDITION_NAME }
									</td>
									<td align="center">
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
								<form action="${ctx}/judicial/sfck.shtml?action=toRulList"
									method="post" name="listForm">
									<input type="hidden" name="pageNo">
									<input type="hidden" name="CONDITION_ID" value="${param.CONDITION_ID }">
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
			<input type="hidden" name="_backUrl" value="${ctx}/judicial/sfck.shtml?action=toRulList">
			<input type="hidden" name="CONDITION_ID" value="${param.CONDITION_ID }">
			<input type="hidden" name="CXMB_ID" value="${param.CXMB_ID }">
			<input type="hidden" name="RULE_ID">
			<input type="hidden" name="usrId" value='${usrId}'>
		    <input type="hidden" name="orgNo" value='${orgNo}'>
			${paramCover.coveredInputs}
		</form>
		<input type="hidden" name="tmpConid" value="${param.CONDITION_ID }">
		<input type="hidden" name="tmpRuleid" >
	</body>
	<%@ include file="/commons/jslibs4tree.jsp"%>
	<script type="text/javascript">		
		function doQuery(){			
			if(FormValidate(qryForm)){
				setTimeout("qryForm.submit()",100);
			}
			return false;
		}	
		
		var tmpConid = g("tmpConid");
		var tmpRuleid = g("tmpRuleid");
		function setEid(obj){
			obj.checked="true";
			tmpRuleid.value=obj.value;
	//		alert(tmpId.value+"@@@@"+ tmpStus.value); 
		}
	
		function checkEid(RULE_ID){		
			if(RULE_ID==null || ""==RULE_ID.trim()){
				showErrorMsg("请选择规则！");
				return false;
			}
			return true;
		}		
		function todo(act,RULE_ID){
			showWaitPanel('');
			detailForm.action=act;
			detailForm.RULE_ID.value=RULE_ID;
			setTimeout("detailForm.submit()",100);	
			try{ tmpId.parentNode.focus(); }catch(e){}
		}		
		function deleteEntry(){ 
			var RULE_ID = tmpRuleid.value;
			if(!checkEid(RULE_ID))return;
			if(confirm("您确信要删除该条信息吗？")){
				todo("${ctx}/judicial/sfck.shtml?action=delRule",RULE_ID);
			}
		}
		
		function modiEntry(){
			var RULE_ID = tmpRuleid.value;
			if(!checkEid(RULE_ID))return;
			todo("${ctx}/judicial/sfck.shtml?action=toUpdateRule",RULE_ID);
		}
		
		function addEntry(){
			todo("${ctx}/judicial/sfck.shtml?action=toAddRule","");
		}
		
		
		function viewEntry(){
			var CXMB_ID = tmpMbid.value;
			if(!checkEid(CONDITION_ID))return;
			todo("${ctx}/judicial/lscxmb.shtml?action=toView",CXMB_ID);
		}
		function toDetail(CONDITION_ID ){
			window.open("${ctx}/judicial/lscxmb.shtml?action=toView&&CONDITION_ID="+CONDITION_ID,
			"历史查询模板","scrollbars=yes,resizable=yes,width=745,height=500,left=263,top=207"); //menubar=yes,
		}
		
		
		
		function goback(){ 
			detailForm.action = "${ctx}/judicial/sfck.shtml?action=toConList"
			setTimeout("detailForm.submit()",100);	
			
		}
		function scSQL(){ 
			var CONDITION_ID='11111';
			todo("${ctx}/judicial/lscxmb.shtml?action=scSQL",CONDITION_ID);
			
		}
		
		
		function doMoBan()
		{
	
		var CONDITION_ID = tmpId.value;
		var url	 = "${ctx}/judicial/lscxmb.shtml?action=toFlag";
			url += "&CONDITION_ID="+CONDITION_ID;
			url += "&REP_NO=1";
	
		location.href=url;
		
		}
		
	
		
		
	</script>
</html>

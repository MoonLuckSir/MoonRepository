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
								当前位置：司法查控 &gt;&gt; 查询模板 &gt;&gt; 条件设置
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
					<form action="${ctx}/judicial/sfck.shtml?action=toConList" method="post"
						name="qryForm" onSubmit="return doQuery();">
						<input type="hidden" name="CXMB_ID" value="${param.CXMB_ID }">
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>
								<td width="100" align="right">
									条件名称：
								</td>
								<td width="120">
									<input name="CONDITION_NAME" type="text" autocheck="true" style="width:110px;"
										pattern="string" maxlength="20" label="条件名称">
									<script type="text/javascript">
										qryForm.CONDITION_NAME.value="${param.CONDITION_NAME}";
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
						<input type="button" class="btn" value="设计规则" onClick="rule_set();" />
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
									条件编号
								</th>
								<th width="140" align="center">
									条件名称
								</th>
								
								<th  align="center">
									物理名称
								</th>
								<th  align="center">
									
									输入框类型
								</th>
								<th  align="center">
									数据类型
								</th>
								<th  align="center">
									默认值
								</th>	
								<th  align="center">
									所属模板
								</th>	
								<th width="140" align="center">
									条件描述
								</th>																																					
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" id="${r}" onMouseOver="mover(this)" onMouseOut="mout(this)" onClick="setEid(document.getElementById('eid${rr}'));">
									<th width="20">
										<input type="radio" name="eid" id="eid${rr}" value="${rst.CONDITION_ID}"
											onclick="setEid(this);">
									</th>
									<td align="center">
										${rst.CONDITION_ID }
									</td>
									<td align="center">
										${rst.CONDITION_NAME }
									</td>
									
									<td align="center">
										${rst.PHYSICAL_NAME }
									</td>
									<td align="center">
										<c:if test="${rst.INPUT_TYPE eq '1'}">
											输入框
										</c:if>
										<c:if test="${rst.INPUT_TYPE eq '2'}">
											下拉列表框
										</c:if>
										<c:if test="${rst.INPUT_TYPE eq '3'}">
											日期选择组件
										</c:if>
										
									</td>
									<td align="center">
										<c:if test="${rst.VALUE_TYPE eq '1'}">
											字符串
										</c:if>
										<c:if test="${rst.VALUE_TYPE eq '2'}">
											数字
										</c:if>
										<c:if test="${rst.VALUE_TYPE eq '3'}">
											日期
										</c:if>
									</td>
									<td align="center">
										${rst.VALUE_DEF}
									</td>
									<td align="center">
										${rst.CXMB_NAME }
									</td>
									<td align="center">
										${rst.CONDITION_DESC}
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
								<form action="${ctx}/judicial/sfck.shtml?action=toConList"
									method="post" name="listForm">
									<input type="hidden" name="pageNo">
									<input type="hidden" name="CXMB_ID" value="${param.CXMB_ID }">
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
			<input type="hidden" name="_backUrl" value="${ctx}/judicial/sfck.shtml?action=toConList">
			<input type="hidden" name="CXMB_ID" value="${param.CXMB_ID }">
			<input type="hidden" name="CONDITION_ID">
			<input type="hidden" name="usrId" value='${usrId}'>
		    <input type="hidden" name="orgNo" value='${orgNo}'>
			${paramCover.coveredInputs}
		</form>
		<input type="hidden" name="tmpMbid" value="${param.CXMB_ID }">
		<input type="hidden" name="tmpConid" >
	</body>
	<%@ include file="/commons/jslibs4tree.jsp"%>
	<script type="text/javascript">		
		function doQuery(){			
			if(FormValidate(qryForm)){
				setTimeout("qryForm.submit()",100);
			}
			return false;
		}	
		var tmpMbid = g("tmpMbid");
		var tmpConid = g("tmpConid");
		function setEid(obj){
			obj.checked="true";
			tmpConid.value=obj.value;
	//		alert(tmpId.value+"@@@@"+ tmpStus.value); 
		}
	
		function checkEid(CONDITION_ID){		
			if(CONDITION_ID==null || ""==CONDITION_ID.trim()){
				showErrorMsg("请选择条件！");
				return false;
			}
			return true;
		}		
		function todo(act,CONDITION_ID){
			showWaitPanel('');
			detailForm.action=act;
			detailForm.CONDITION_ID.value=CONDITION_ID;
			setTimeout("detailForm.submit()",100);	
			try{ tmpId.parentNode.focus(); }catch(e){}
		}		
		function deleteEntry(){ 
			var CONDITION_ID = tmpConid.value;
			if(!checkEid(CONDITION_ID))return;
			if(confirm("您确信要删除该条信息吗？")){
				todo("${ctx}/judicial/sfck.shtml?action=delCon",CONDITION_ID);
			}
		}
		
		function modiEntry(){
			var CONDITION_ID = tmpConid.value;
			if(!checkEid(CONDITION_ID))return;
			todo("${ctx}/judicial/sfck.shtml?action=toUpdateCon",CONDITION_ID);
		}
		
		function addEntry(){
			todo("${ctx}/judicial/sfck.shtml?action=toAddCon","");
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
		
		function rule_set()
		{
			var CONDITION_ID = tmpConid.value;
			if(!checkEid(CONDITION_ID))return;
			todo("${ctx}/judicial/sfck.shtml?action=toRulList",CONDITION_ID);
		}
		
	  
		function goback(){ 
			detailForm.action = "${ctx}/judicial/sfck.shtml?action=toCxmbList"
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

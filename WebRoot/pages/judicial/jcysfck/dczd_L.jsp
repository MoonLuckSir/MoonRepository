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
								当前位置：司法查控 &gt;&gt; 导出模板 &gt;&gt; 设置导出字段
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
					<form action="${ctx}/judicial/sfck.shtml?action=toDczdList" method="post"
						name="qryForm" onSubmit="return doQuery();">
						<input type="hidden" name="CXMB_ID" value="${param.CXMB_ID }">
						<input type="hidden" name="DCMB_ID" value="${param.DCMB_ID }">
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>
								<td width="100" align="right">
									字段名称：
								</td>
								<td width="120">
									<input name="COL_NAME" type="text" autocheck="true" style="width:110px;"
										pattern="string" maxlength="20" label="字段名称">
									<script type="text/javascript">
										qryForm.COL_NAME.value="${param.COL_NAME}";
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
								<th  align="center">
									字段编号
								</th>
								<th  align="center">
									字段名称
								</th>
								
								<th  align="center">
									物理名称
								</th>
								<th  align="center">
									序号
								</th>
							<!--  	<th  align="center">
									是否强制转换为字符
								</th>	-->	
								<th  align="center">
									所属模板
								</th>
								<th width="340" align="center">
									字段描述
								</th>																																					
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" id="${r}" onMouseOver="mover(this)" onMouseOut="mout(this)" onClick="setEid(document.getElementById('eid${rr}'));">
									<th width="20">
										<input type="radio" name="eid" id="eid${rr}" value="${rst.COL_ID}"
											onclick="setEid(this);">
									</th>
									<td align="center">
										${rst.COL_ID }
									</td>
									<td align="center">
										${rst.COL_NAME }
									</td>
									
									<td align="center">
										${rst.PHYSICAL_NAME }
									</td>
									<td align="center">
										${rst.ORDERXH}
									</td>
									<td align="center">
										${rst.DCMB_NAME }
									</td>
									<td align="center">
										${rst.COLMX_DESC}
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
								<form action="${ctx}/judicial/sfck.shtml?action=toDczdList"
									method="post" name="listForm">
									<input type="hidden" name="pageNo">
									<input type="hidden" name="DCMB_ID" value="${param.DCMB_ID }">
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
			<input type="hidden" name="_backUrl" value="${ctx}/judicial/sfck.shtml?action=toDczdList">
			<input type="hidden" name="DCMB_ID" value="${param.DCMB_ID }">
			<input type="hidden" name="CXMB_ID" value="${param.CXMB_ID }">
			<input type="hidden" name="COL_ID">
			<input type="hidden" name="usrId" value='${usrId}'>
		    <input type="hidden" name="orgNo" value='${orgNo}'>
			${paramCover.coveredInputs}
		</form>
		<input type="hidden" name="tmpMbid" value="${param.DCMB_ID }">
		<input type="hidden" name="tmpColid" >
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
		var tmpColid = g("tmpColid");
		function setEid(obj){
			obj.checked="true";
			tmpColid.value=obj.value;
	//		alert(tmpId.value+"@@@@"+ tmpStus.value); 
		}
	
		function checkEid(COL_ID){		
			if(COL_ID==null || ""==COL_ID.trim()){
				showErrorMsg("请选择条件！");
				return false;
			}
			return true;
		}		
		function todo(act,COL_ID){
			showWaitPanel('');
			detailForm.action=act;
			detailForm.COL_ID.value=COL_ID;
			setTimeout("detailForm.submit()",100);	
			try{ tmpId.parentNode.focus(); }catch(e){}
		}		
		function deleteEntry(){ 
			var COL_ID = tmpColid.value;
			if(!checkEid(COL_ID))return;
			if(confirm("您确信要删除该条信息吗？")){
				todo("${ctx}/judicial/sfck.shtml?action=delCol",COL_ID);
			}
		}
		
		function modiEntry(){
			var COL_ID = tmpColid.value;
			if(!checkEid(COL_ID))return;
			todo("${ctx}/judicial/sfck.shtml?action=toUpdateCol",COL_ID);
		}
		
		function addEntry(){
			todo("${ctx}/judicial/sfck.shtml?action=toAddCol","");
		}
		
		
		function viewEntry(){
			var CXMB_ID = tmpMbid.value;
			if(!checkEid(COL_ID))return;
			todo("${ctx}/judicial/lscxmb.shtml?action=toView",CXMB_ID);
		}
		function toDetail(COL_ID ){
			window.open("${ctx}/judicial/lscxmb.shtml?action=toView&&CONDITION_ID="+CONDITION_ID,
			"历史查询模板","scrollbars=yes,resizable=yes,width=745,height=500,left=263,top=207"); //menubar=yes,
		}
		
		
		function goback(){ 
			detailForm.action = "${ctx}/judicial/sfck.shtml?action=toDcmbList"
			setTimeout("detailForm.submit()",100);	
			
		}
		
	</script>
</html>

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
								当前位置：报表查询 &gt;&gt; 司法查询定制设计 &gt;&gt; 司法查询模板 &gt;&gt;模板展现列明细
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
					<form action="${ctx}/judicial/lscxcol.shtml?action=toList" method="post"
						name="qryForm" onSubmit="return doQuery();">
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>	
							<input type="hidden" name="REPMOD_NO" value="${param.REPMOD_NO}" />
								<td width="70" align="right">
									列名称：
								</td>
								<td width="120">						
									<input name="COLMX_NAME" type="text" label="列名称" style="width:110px"/>
								</td>	
								<td align="left"> 
									<input type="submit" class="btn" value="查 询"> 
									<input type="reset" class="btn" value="重 置"> 
								</td>
								<td align="right">
									<input type="button" class="btn" value="返 回" onClick="backurl();">
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
									列名称
								</th>
								<th width="240" align="center">
									模板编号
								</th>
								<th width="100" nowrap align="center">
									交易名称
								</th>
								<th width="100" align="center">
									物理名
								</th>
								<th width="100" align="center">
									宽度
								</th>
								<th width="100" align="center">
									高度
								</th>
								<th width="100" align="center">
									对齐
								</th>
								<th width="100" align="center">
									是否显示
								</th>
								<th width="100" align="center">
									序号
								</th>	
								<th width="100" align="center">
									描述
								</th>	
								<th width="50" align="center">
									<font color="red">详细信息</font>
								</th>																																							
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" onMouseOver="mover(this)"
									onmouseout="mout(this)"  onclick="setEid(document.getElementById('eid${rr}'));">
									
									<th width="20">
										<input type="radio" name="eid" id="eid${rr}" value="${rst.COLMX_NO }|${rst.COLMX_NAME}"
											onclick="setEid(this);">
									</th>
									<td align="left">
										${rst.COLMX_NAME }
									</td>
									<td align="left">
										${rst.REPMOD_NO}
									</td>
									<td align="left">
										${rst.REP_NAME}
									</td>
									<td align="left">
										${rst.PHYSICAL_NAME}
									</td>
									<td align="left">
										${rst.WIDTH}
									</td>
									<td align="left">
										${rst.HEIGHT}
									</td>
									<td align="left">
										${rst.ALIGN}
									</td>
									<td align="left">
										${rst.ISDISPLAY}
									</td>
									<td align="left">
										${rst.ORDERXH}
									</td>
									<td align="left">
										${rst.COLMX_DESC}
									</td>
									<td align="left" nowrap> 
										<a href="##" onClick="javascript:toDetail('${rst.COLMX_NO }')">详细信息</a>
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
								<form action="${ctx}/judicial/lscxcol.shtml?action=toList"
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
			<input type="hidden" name="_backUrl" value="${ctx}/judicial/lscxcol.shtml?action=toList">
			<input type="hidden" name="COLMX_NO">
			<input type="hidden" name="REPMOD_NO">
			${paramCover.coveredInputs}
		</form>
		<input type="hidden" name="tmpId">
		<input type="hidden" name="tmpStus">
		<input type="hidden" name="tmpZbId" value="${param.REPMOD_NO}">
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
		function checkEid(COLMX_NO){		
			if(COLMX_NO==null || ""==COLMX_NO.trim()){
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
		function todo(act,zbid,mxid){
			showWaitPanel('');
			detailForm.action=act;
			detailForm.REPMOD_NO.value=zbid;
			detailForm.COLMX_NO.value=mxid;
			setTimeout("detailForm.submit()",100);	
			try{ tmpId.parentNode.focus(); }catch(e){}
		}		
		function deleteEntry(){ 
			var COLMX_NO = tmpId.value;
			if(!checkEid(COLMX_NO))return;
			if(confirm("您确信要删除该条信息吗？")){
				todo("${ctx}/judicial/lscxcol.shtml?action=delete",'',COLMX_NO);
			}
		}
		function modiEntry(){
			var COLMX_NO = tmpId.value;
			if(!checkEid(COLMX_NO))return;
			todo("${ctx}/judicial/lscxcol.shtml?action=toUpdate",'',COLMX_NO);
		}
		function addEntry(){
			var REPMOD_NO = tmpZbId.value;
			if(!checkZJEid(REPMOD_NO))return;
			todo("${ctx}/judicial/lscxcol.shtml?action=toAdd",REPMOD_NO,'');
		}
		function viewEntry(){
			var COLMX_NO = tmpZbId.value;
			if(!checkEid(COLMX_NO))return;
			todo("${ctx}/judicial/lscxcol.shtml?action=toView",'',COLMX_NO);
		}
		function toDetail(COLMX_NO ){
			window.open("${ctx}/judicial/lscxcol.shtml?action=toView&&COLMX_NO="+COLMX_NO,
			"模板展现列明细","scrollbars=yes,resizable=yes,width=745,height=500,left=263,top=207"); //menubar=yes,
		}

		function backurl(){
			todo("${ctx}/judicial/lscxmb.shtml?action=toList",'');
		}

	</script>
</html>

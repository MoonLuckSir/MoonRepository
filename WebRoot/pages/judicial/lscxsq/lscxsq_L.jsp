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
		<title>历史查询申请列表</title>
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
								当前位置：司法查询 &gt;&gt; 司法查询申请管理 &gt;&gt; 司法查询申请
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
					<form action="${ctx}/judicial/lscxsq.shtml?action=toList" method="post"
						name="qryForm" onSubmit="return doQuery();">
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>
								<td width="120" align="right">
									司法查询申请编号：
								</td>
								<td width="120">
									<input name="REQ_NO" type="text" autocheck="true" style="width:110px;"
										pattern="string" maxlength="20" label="历史查询申请编号">
									<script type="text/javascript">
										qryForm.REQ_NO.value="${param.REQ_NO}";
									</script>
								</td>
								<td width="100" align="right">
									司法模板编号：
								</td>
								<td width="120">
									<input name="REPMOD_NO" type="text" autocheck="true" style="width:110px;"
										pattern="string" maxlength="20" label="历史模板编号">
									<script type="text/javascript">
										qryForm.REPMOD_NO.value="${param.REPMOD_NO}";
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
						<input type="button" class="btn" value="提 交" onClick="docommit();" />
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
								<th  align="center" norawp>
									申请编号
								</th>
								<th  align="center" norawp>
									模板编号
								</th>
								<th  nowrap align="center" >
									申请人
								</th>
								<th norawpalign="center">
									申请机构
								</th>
								<th norawp align="center">
									申请时间
								</th>
								<th norawp align="center">
									状态
								</th>
								
								<th norawp align="center">
									<font color="red">详细信息</font>
								</th>
								
																																														
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<tr class="list_row${r}" onMouseOver="mover(this)"
									onmouseout="mout(this)">
									<th width="20">
										<input type="radio" name="eid" value="${rst.REQ_NO }|${rst.STATE}"
											onclick="setEid(this);">
									</th>
									<td align="left">
										${rst.REQ_NO }
									</td>
									<td>
										${rst.REPMOD_NO}
									</td>
									<td align="left">
										${rst.CHECKIN_USER}
									</td>
									<td align="left">
										${rst.CHECKIN_ORGAN}
									</td>
									<td align="left">
										${rst.CHECKIN_TIME}
									</td>
									<td align="left">
										${rst.STATE}
									</td>
									
									<td align="left" nowrap> 
										<a href="##" onClick="javascript:viewEntry('${rst.REQ_NO }')">详细信息</a>
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
								<form action="${ctx}/judicial/lscxsq.shtml?action=toList"
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
			<input type="hidden" name="_backUrl" value="${ctx}/judicial/lscxsq.shtml?action=toList">
			<input type="hidden" name="REQ_NO">
			<input type="hidden" name="usrId" value='${usrId}'>
		    <input type="hidden" name="orgNo" value='${orgNo}'>
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
			var wz = obj.value.indexOf("|");
			tmpId.value=obj.value.substr(0,wz);
			tmpStus.value = obj.value.substr(wz+1,x);
		}		
		function checkEid(REQ_NO){		
			if(REQ_NO==null || ""==REQ_NO.trim()){
				showErrorMsg("请选择记录！");
				return false;
			}
			return true;
		}		
		function todo(act,REQ_NO){
			showWaitPanel('');
			detailForm.action=act;
			detailForm.REQ_NO.value=REQ_NO;
			setTimeout("detailForm.submit()",100);	
			try{ tmpId.parentNode.focus(); }catch(e){}
		}		
		function deleteEntry(){ 
			var REQ_NO = tmpId.value;
			if(!checkEid(REQ_NO))return;
			if(confirm("您确信要删除该记录吗？")){
				todo("${ctx}/judicial/lscxsq.shtml?action=delete",REQ_NO);
			}
		}
		function modiEntry(){
			var REQ_NO = tmpId.value;
			if(!checkEid(REQ_NO))return;
			todo("${ctx}/judicial/lscxsq.shtml?action=toUpdate",REQ_NO);
		}
		function docommit(){
			var REQ_NO = tmpId.value;
			if(!checkEid(REQ_NO))return;
			todo("${ctx}/judicial/lscxsq.shtml?action=conmmit",REQ_NO);
		}
		
		
		function addEntry(){
			todo("${ctx}/judicial/lscxsq.shtml?action=toAdd","");
		}
		function viewEntry(REQ_NO){
			window.open("${ctx}/judicial/lscxsq.shtml?action=toView&REQ_NO="+REQ_NO,
			"历史查询申请","scrollbars=yes,resizable=yes,width=745,height=500,left=263,top=207");
		}
      
	</script>
</html>

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
								当前位置：报表查询 &gt;&gt; 司法查询定制设计 &gt;&gt; 司法查询控制
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
					<form action="${ctx}/judicial/lscxmb.shtml?action=toList1" method="post"
						name="qryForm" onSubmit="return doQuery();">
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>
								<td width="100" align="right">
									司法查询名称：
								</td>
								<td width="120">
									<input name="REP_NAME" type="text" autocheck="true" style="width:110px;"
										pattern="string" maxlength="20" label="历史查询名称">
									<script type="text/javascript">
										qryForm.REP_NAME.value="${param.REP_NAME}";
									</script>
								</td>	
								<td width="70" align="right">
									状态：
								</td>
								<td width="120">						
									<select name="STATE" style="width:110px;">
										${STATE}
									</select>				
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
					
						<input type="button" class="btn" value="模板设置" onClick="doMoBan();" />
					    <input type="button" class="btn" value="列表设置" onClick="doList();" />
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
									司法查询模板编号
								</th>
								<th width="100" align="center">
									交易编号
								</th>
								<th width="240" align="center">
									交易名称
								</th>
								<!--  
								<th width="100" nowrap align="center">
									查询语句
								</th>
								<th width="100" align="center">
									来源语句
								</th>
								<th width="100" align="center">
									排序语句
								</th>
								<th width="100" align="center">
									分隔符
								</th>	
								<th width="100" align="center">
									输出类型
								</th>
								-->
								
								<th  align="center">
									制单人
								</th>
								
								<th width="100" align="center">
									制单机构
								</th>
								<th width="100" align="center">
									制单时间
								</th>
								<th  align="center">
									状态
								</th>		
								<th width="50" align="center">
									<font color="red">详细信息</font>
								</th>																																							
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" id="${r}" onMouseOver="mover(this)" onMouseOut="mout(this)" onClick="setEid(document.getElementById('eid${rr}'));">
									<th width="20">
										<input type="radio" name="eid" id="eid${rr}" value="${rst.REPMOD_NO}|${rst.STATE} "
											onclick="setEid(this);">
									</th>
									<td align="left">
										${rst.REPMOD_NO }
									</td>
									<td>
										${rst.REP_NO}
									</td>
									<td align="left">
										${rst.REP_NAME}
									</td>
									<!--
									<td align="left">
										${rst.SELECT_SQL}
									</td>
									<td align="left">
										${rst.FROM_SQL}
									</td>
									<td align="left">
										${rst.ORDER_SQL}
									</td>
									<td align="left">
										${rst.ITERATOR}
									</td>
									<td align="left">
										${rst.OUTPUT}
									</td>
									-->
									
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
										<a href="##" onClick="javascript:toDetail('${rst.REPMOD_NO }')">详细信息</a>
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
								<form action="${ctx}/judicial/lscxmb.shtml?action=toList1"
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
			<input type="hidden" name="_backUrl" value="${ctx}/judicial/lscxmb.shtml?action=toList1">
			<input type="hidden" name="REPMOD_NO">
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
			obj.checked="true";
			var x = obj.value.trim().length;
			var wz = obj.value.indexOf("|");
			tmpId.value=obj.value.substr(0,wz);
			tmpStus.value = obj.value.substr(wz+1,x);
	//		alert(tmpId.value+"@@@@"+ tmpStus.value); 
		}
	
		function checkEid(REPMOD_NO){		
			if(REPMOD_NO==null || ""==REPMOD_NO.trim()){
				showErrorMsg("请选择模板！");
				return false;
			}
			return true;
		}		
		function todo(act,REPMOD_NO){
			showWaitPanel('');
			detailForm.action=act;
			detailForm.REPMOD_NO.value=REPMOD_NO;
			setTimeout("detailForm.submit()",100);	
			try{ tmpId.parentNode.focus(); }catch(e){}
		}		
	
		

		
	
	
		
		
		function doMoBan()
		{
	
		var REPMOD_NO = tmpId.value;
		var url	 = "${ctx}/judicial/lscxmb.shtml?action=toFlag";
			url += "&REPMOD_NO="+REPMOD_NO;
			url += "&REP_NO=1";
	
		location.href=url;
		
		}
		
		function doList()
		{
		var REPMOD_NO = tmpId.value;
		var url	 = "${ctx}/judicial/lscxmb.shtml?action=toFlag";
			url += "&REPMOD_NO="+REPMOD_NO;
			url += "&REP_NO=2";
	
		location.href=url;
		}
		
		
	</script>
</html>

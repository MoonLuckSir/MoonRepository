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
		<title>司法数据查询</title>
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
								当前位置：司法查询 &gt;&gt; 司法查询管理 &gt;&gt; 司法数据查询
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
					<form action="${ctx}/judicial/ddbbzx.shtml?action=torepList" method="post"
						name="qryForm" onSubmit="return doQuery();">
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>
								<td width="80" align="right">
									交易名称：
								</td>
								<td width="120">
									<input name="REP_NAME" type="text" autocheck="true" style="width:110px;"
										pattern="string" maxlength="50" label="交易名称">
									<script type="text/javascript">
										qryForm.REP_NAME.value="${param.REP_NAME}";
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
				<td valign="top">
					<div class="lst_area">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="lst">
							<tr class="fixedRow">
								<th width="20">
								</th>
								<th  align="center" norawp>
									交易编号
								</th>
								<th  align="center" norawp>
									交易名称
								</th>
								<th  nowrap align="center" >
									模板编号
								</th>
								<th norawpalign="center">
									制定时间
								</th>
								<th norawp align="center">
									制定机构
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
										&nbsp;<a title="${rst.REP_NAME}" href="javascript:selectReport('${rst.REPMOD_NO }')">${rst.REP_NO}</a>
									</td>
									<td>
										&nbsp;<a title="${rst.REP_NAME}" href="javascript:selectReport('${rst.REPMOD_NO }')">${rst.REP_NAME}</a>
									</td>
									<td align="left">
										&nbsp;<a title="${rst.REP_NAME}" href="javascript:selectReport('${rst.REPMOD_NO }')">${rst.REPMOD_NO}</a>
									</td>
									<td align="left">
										&nbsp;<a title="${rst.REP_NAME}" href="javascript:selectReport('${rst.REPMOD_NO }')">${rst.CHECKIN_TIME}</a>
									</td>
									<td align="left">
										&nbsp;<a title="${rst.REP_NAME}" href="javascript:selectReport('${rst.REPMOD_NO }')">${rst.CHECKIN_ORGAN}</a>
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
								<form action="${ctx}/judicial/ddbbzx.shtml?action=torepList" 
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
		<form method="post" name="detailForm" action="${ctx}/judicial/ddbbzx.shtml?action=toQuery" >
			<input type="hidden" name="REPMOD_NO" >
			<input type="hidden" name="MKLX">
			<input type="hidden" name="usrId" value='${usrId}'>
		    <input type="hidden" name="orgNo" value='${orgNo}'>
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
        function selectReport(rptmod){
			detailForm.REPMOD_NO.value=rptmod;
			detailForm.MKLX.value='repList';
            showWaitPanel('数据查询');			
			setTimeout("detailForm.submit()",100);
		}
      
	</script>
</html>

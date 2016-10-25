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
		<title>通用选取列表</title>
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
								当前位置：通用选取列表
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
					<form action="${ctx}/judicial/selcon.shtml?action=toList" method="post"
						name="qryForm" onsubmit="return doSave();" >
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<input type="hidden" name="selid" value="${selid}" >
                            <input type="hidden" name="retruncol" value="${cols}" >
							<tr>
							${inputcon}	
							
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
						<input type="button" class="btn" value="确 认" onclick="docommit();" />
						
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
								${discols}
								<script type="text/javascript">	
								${initcondate}
	                            </script>
							</tr>
							
									<c:forEach items="${page.result}" var="rst" varStatus="row">
				                     <tr class="list_row">
				                     <th width="20">
										<input type="radio" name="eid"  value="${rst[rst.keyvalue]}" onclick="setEid(this);">
									 </th>
				                      <c:forEach items="${displaycols}" var="vcol"  >
					                  <td height="25"  nowrap >
					                  ${rst[vcol.key]}
					                  </td>
				                     </c:forEach>
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
								<form action="${ctx}/judicial/selcon.shtml?action=toList"
								  method="post" name="listForm">
									<input type="hidden" name="pageNo">
									<input type="hidden" name="selid" value="${selid}" >
									<input type="hidden" name="retruncol" value="${cols}">
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
		<input type="hidden" name="tmpId">
	</body>
	<%@ include file="/commons/jslibs4tree.jsp"%>
	<script type="text/javascript">		
		function setEid(obj){
			tmpId.value=obj.value;
		
		}		
	  function docommit(){
	     ${jscon}
	     window.close();
	  }
	  function doSave(){
			if(FormValidate(0)){
				setTimeout("qryForm.submit();",100);
			}
			return false;
		}
	  
	</script>
</html>

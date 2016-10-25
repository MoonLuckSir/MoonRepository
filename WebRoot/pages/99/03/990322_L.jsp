<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<title>查询角色</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
	</head>
	<body>
		<table width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr class="fixedRow">
				<th width="20" height="25"> 
				</th>
				<th width="20"> 
					<input type="checkbox" onclick="checkAllStates('rptId',this.checked)">												
				</th>
				<th width="100" align="center">
					报表编号
				</th>
				<th width="200" align="center">
					报表名称
				</th>
				<th width="100" align="center">
					报表周期
				</th>
				<th align="center">
					报表备注
				</th>
				<th>
					&nbsp;
				</th>
			</tr>
			<c:forEach items="${rpts}" var="rst" varStatus="row">
				<c:set var="r" value="${row.count % 2}"></c:set>
				<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)">
					<td height="25" nowrap class="row_no">
						${rst.RN}
					</td>
					<th width="20"> 	
						<input name="rptId" type="checkbox" value="${rst.RPT_ID }" 
							<c:if test="${fn:contains(checkedRpts, rst.RPT_ID)}">checked</c:if>>											
					</th>
					<td> 
						${rst.RPT_ID } 
					</td>
					<td>
						&nbsp;${rst.RPT_NAME}
					</td>
					<td>
						&nbsp;${rst.RPT_CYC}
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty rpts}">
				<tr>
					<td height="25" colspan="8" align="center" class="lst_empty">
						&nbsp;无相关信息&nbsp;
					</td>
				</tr>
			</c:if>
		</table>
	</body>
</html>

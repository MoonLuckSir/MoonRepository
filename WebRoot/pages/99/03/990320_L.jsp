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
					<input type="checkbox" onclick="checkAllStates('userId',this.checked)">												
				</th>
				<th width="100" align="center">
					用户编号
				</th>
				<th width="100" align="center">
					用户姓名
				</th>
				<th width="100" align="center">
					用户类型
				</th>
				<th>
					&nbsp;
				</th>
			</tr>
			<c:forEach items="${users}" var="rst" varStatus="row">
				<c:set var="r" value="${row.count % 2}"></c:set>
				<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)">
					<td height="25" nowrap class="row_no">
						${rst.RN}
					</td>
					<th width="20"> 	
						<input name="userId" type="checkbox" value="${rst.USER_ID }" 
							<c:if test="${fn:contains(checkedUsers, rst.USER_ID)}">checked</c:if>>											
					</th>
					<td> 
						${rst.USER_ID } 
					</td>
					<td>
						&nbsp;${rst.USER_NAME}
					</td>
					<td>
						&nbsp;${rst.USER_TYPE_NAME}
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty users}">
				<tr>
					<td height="25" colspan="6" align="center" class="lst_empty">
						&nbsp;无相关信息&nbsp;
					</td>
				</tr>
			</c:if>
		</table>
	</body>
</html>

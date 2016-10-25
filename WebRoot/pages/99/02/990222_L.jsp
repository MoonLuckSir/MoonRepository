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
					<input type="checkbox" onclick="checkAllStates('roleId',this.checked)">												
				</th>
				<th width="100" align="center">
					角色编号
				</th>
				<th width="200" align="center">
					角色名称
				</th>
				<th width="100" align="center">
					角色级别
				</th>
				<th align="center">
					角色类别
				</th>
				<th align="center">
					备注
				</th>
				<th>
					&nbsp;
				</th>
			</tr>
			<c:forEach items="${roles}" var="rst" varStatus="row">
				<c:set var="r" value="${row.count % 2}"></c:set>
				<tr class="list_row${r}" onmouseover="mover(this)" onmouseout="mout(this)">
					<td height="25" nowrap class="row_no">
						${rst.RN}
					</td>
					<th width="20"> 	
						<input name="roleId" type="checkbox" value="${rst.ROLE_ID }" 
							<c:if test="${fn:contains(checkedRoles, rst.ROLE_ID)}">checked</c:if>>											
					</th>
					<td> 
						${rst.ROLE_ID } 
					</td>
					<td>
						&nbsp;${rst.ROLE_NAME}
					</td>
					<td>
						&nbsp;${rst.ROLE_LVL}
					</td>
					<td>
						&nbsp;${rst.ROLE_TYPE}
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty roles}">
				<tr>
					<td height="25" colspan="10" align="center" class="lst_empty">
						&nbsp;无相关信息&nbsp;
					</td>
				</tr>
			</c:if>
		</table>
	</body>
</html>

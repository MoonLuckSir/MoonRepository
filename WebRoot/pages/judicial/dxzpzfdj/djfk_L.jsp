<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@page import="java.net.URLEncoder"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/tools.css">
	
		<%@ include file="/commons/jslibs.jsp"%>
		<title>人民银行电信诈骗冻结反馈信息查询</title>
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
								当前位置：司法查询 &gt;&gt; 人民银行电信诈骗查控列表&gt;&gt; 冻结反馈信息查询 
							</td>
							<td width="50" align="right">&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td class="rowSplit"></td></tr>
			<tr>
				<td height="30">
					<div class="lst_title">
					<input type="button" class="btn" value="返回"
									onclick="history.back();"> 
					</div>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<div class="lst_area">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="lst">
							<tr class="fixedRow">
								<th width="30" align="center">
									序号
								</th>
								<th  align="center">
									传输报文流水号
								</th>
								<th  align="center">
									业务申请编号
								</th>
								<th  align="center">
									冻结账号类别
								</th>
								<th  align="center">
									冻结帐卡号
								</th>
								<th  align="center">
									卡/折号
								</th>
								<th  align="center">
									冻结起始时间
								</th>
								<th  align="center">
									冻结结束时间
								</th>
								<th  align="center">
									操作
								</th>																																					
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" id="${r}" onMouseOver="mover(this)" onMouseOut="mout(this)" >
									
									<td align="center">
										<c:choose>
											<c:when test="${row.count lt 10}">
												0${row.count}
											</c:when>
											<c:otherwise>
												${row.count}
											</c:otherwise>
										</c:choose>
									</td>
									<td align="center">
										${rst.TRANSSERIALNUMBER }
									</td>
									<td align="center">
										${rst.APPLICATIONID }
									</td>
									<td align="center">
										<c:if test="${rst.ACCOUNTTYPE eq '01'}">
												个人
										</c:if>
										<c:if test="${rst.ACCOUNTTYPE eq '02'}">
												对公
										</c:if>
									</td>
									<td align="center">
										${rst.ACCOUNTNUMBER }
									</td>
									<td align="center">
										${rst.CARDNUMBER }
									</td>
									<td align="center">
										${rst.STARTTIME }
									</td>
									<td align="center">
										${rst.EXPIRETIME }
									</td>
									<td align="center">
										<a href="${ctx}/judicial/zfdj.shtml?action=toDjfkView&APPLICATIONID=${rst.APPLICATIONID}">查看</a>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty page.result}">
								<tr>
									<td height="25" colspan="9" align="center" class="lst_empty">
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
								<form action="${ctx}/judicial/zfdj.shtml?action=toDjfkTaskList"
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
			<input type="hidden" name="_backUrl" value="${ctx}/judicial/zfdj.shtml?action=toDjfkTaskList">
			<input type="hidden" name="APPLICATIONID">
			<input type="hidden" name="STATUS">
			${paramCover.coveredInputs}
		</form>
	</body>
	<%@ include file="/commons/jslibs4tree.jsp"%>
</html>

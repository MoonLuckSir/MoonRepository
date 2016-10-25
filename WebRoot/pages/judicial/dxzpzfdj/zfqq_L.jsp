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
		<title>人民银行电信诈骗止付信息查询</title>
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
								当前位置：司法查询 &gt;&gt; 人民银行电信诈骗查控列表 &gt;&gt; 止付请求任务查询列表
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
				 	<form action="${ctx}/judicial/zfdj.shtml?action=toZfTaskList" method="post"
						name="qryForm" onSubmit="return doQuery();">
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>
								<td width="100" align="right">
									业务申请编号：
								</td>
								<td width="120">
									<input name="APPLICATIONID" type="text" autocheck="true"
										style="width: 110px;" pattern="int" maxlength="50">
									<script type="text/javascript">
										qryForm.APPLICATIONID.value="${param.APPLICATIONID}";
									</script>
								</td>
								<td width="70" align="right">
									任务状态：
								</td>
								<td width="120">						
									<select name="STATUS" style="width:110px;">
										<option value="">-----请选择-----</option>
										<option value="7">正在进行止付操作</option>
										<option value="8">止付操作待反馈</option>
										<option value="9">止付操作已反馈</option>
										<option value="10">止付操作中报错</option>
										<option value="11">止付反馈中报错</option>
										<option value="13">解除止付成功</option>
										<option value="14">解除止付失败</option>
									</select>
									<script type="text/javascript">
										qryForm.STATUS.value="${param.STATUS}";
									</script>				
								</td>
								<td width="100" align="right">
									请求日期：
								</td>
								<td width="300">						
									 <input style="width:120px;" class="Wdate" type="text" id="d01" value="${param.ksr }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d02\')}',vel:'ksr'})" readonly>
        							 <input name="ksr" id="ksr" type="hidden" value="${param.ksr }"/>&nbsp;-&nbsp;<input style="width:120px;" class="Wdate" type="text" id="d02" value="${param.jsr }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d01\')}',vel:'jsr'})" readonly>
     								 <input name="jsr" id="jsr" type="hidden"  value="${param.jsr }"/>
								</td>	
								<td align="left"> 
									<input type="submit" class="btn" value="查 询"> 
									<input type="button" onclick="cz();" class="btn" value="重 置"> 
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
									业务申请编号
								</th>
								<!-- <th  align="center">
									案件编号
								</th>
								<th  align="center">
									止付账户所属银行名称
								</th>
								<th  align="center">
									止付账号类别
								</th> -->
								<th  align="center">
									止付账户名
								</th>
								<th  align="center">
									止付帐卡号
								</th>
								<th  align="center">
									止付起始时间
								</th>
								<th  align="center">
									止付截止时间
								</th>
								<th  align="center">
									止付解除时间
								</th>
								<th  align="center">
									请求日期
								</th>
								<th  align="center">
									任务状态
								</th>
								<th  align="center">
									执行结果
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
										${rst.APPLICATIONID }
									</td>
									<%-- <td align="center">
										${rst.CASENUMBER }
									</td>
									<td align="center">
										${rst.BANKNAME }
									</td>
									<td align="center">
										<c:if test="${rst.ACCOUNTTYPE eq '01'}">
												个人
										</c:if>
										<c:if test="${rst.ACCOUNTTYPE eq '02'}">
												对公
										</c:if>
									</td> --%>
									<td align="center">
										${rst.ACCOUNTNAME }
									</td>
									<td align="center">
										${rst.ACCOUNTNUMBER }
									</td>
									<td align="center">
										${rst.STARTTIME }
									</td>
									<td align="center">
										${rst.EXPIRETIME }
									</td>
									<td align="center">
										<fmt:formatDate value="${rst.QRY_DATE}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> 
									</td>
									<td align="center">
										<fmt:formatDate value="${rst.QRY_DATE}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> 
									</td>
									<td align="center">
										<c:if test="${rst.STATUS eq 7}">
											<font color="blue">正在进行止付操作</font>
										</c:if>
										<c:if test="${rst.STATUS eq 8}">
											<font color="green">止付操作待反馈</font>
										</c:if>
										<c:if test="${rst.STATUS eq 9}">
											<font color="green">止付操作已反馈</font>
										</c:if>
										<c:if test="${rst.STATUS eq 10}">
											<a href="#" style="text-decoration: underline" onclick="showErrorInfo('${rst.APPLICATIONID }','${rst.ERRORINFO }');"><font color="red">止付操作中报错</font></a>
										</c:if>
										<c:if test="${rst.STATUS eq 11}">
											<a href="#" style="text-decoration: underline" onclick="showErrorInfo('${rst.APPLICATIONID }','${rst.ERRORINFO }');"><font color="orange">止付反馈中报错</font></a>
										</c:if>
										<c:if test="${rst.STATUS eq 13}">
											<font color="green">解除止付成功</font>
										</c:if>
										<c:if test="${rst.STATUS eq 14}">
											<a href="#" style="text-decoration: underline" onclick="showErrorInfo('${rst.APPLICATIONID }','${rst.ERRORINFO }');"><font color="red">解除止付失败</font></a>
										</c:if>
									</td>
									<td align="center">
										<c:choose>
											<c:when test="${rst.EXECUTERES eq 0}">
												<font color="green">止付执行成功</font>
											</c:when>
											<c:when test="${rst.EXECUTERES eq 1}">
												<a href="#" style="text-decoration: underline" onclick="showErrorInfo('${rst.APPLICATIONID }','${rst.EXECUTERESINFO }');"><font color="red">止付执行失败</font></a>
											</c:when>
											<c:otherwise>
												<font color="blue">未执行止付</font>
											</c:otherwise>
										</c:choose>
										
									</td>
									<td align="center">
										<a href="${ctx}/judicial/zfdj.shtml?action=toView&APPLICATIONID=${rst.APPLICATIONID}">查看</a>-
										<%-- <a href="${ctx}/judicial/zfdj.shtml?action=toZffkTaskList&APPLICATIONID=${rst.APPLICATIONID}">查控结果</a>- --%>
										<c:choose>
											<c:when test="${rst.EXECUTERES eq 0}">
												<a href="${ctx}/judicial/zfdj.shtml?action=toZffkTaskList&APPLICATIONID=${rst.APPLICATIONID}">查控结果</a>-
											</c:when>
											<c:otherwise>
												<a href="#" disabled>查控结果</a>-
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${not empty rst.ATTACHMENTS }">
												<a href="${ctx}/judicial/zfdj.shtml?action=downLoadFile&savePath=${rst.ATTACHMENTS}">法律文书下载</a>-
											</c:when>
											<c:otherwise>
												<a href="#" disabled>法律文书下载</a>-
											</c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty page.result}">
								<tr>
									<td height="25" colspan="12" align="center" class="lst_empty">
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
								<form action="${ctx}/judicial/zfdj.shtml?action=toZfTaskList"
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
			<input type="hidden" name="_backUrl" value="${ctx}/judicial/zfdj.shtml?action=toZfTaskList">
			<input type="hidden" name="APPLICATIONID">
			<input type="hidden" name="STATUS">
			${paramCover.coveredInputs}
		</form>
	</body>
	<%@ include file="/commons/jslibs4tree.jsp"%>
	<script type="text/javascript">	
	
		function cz(){
			qryForm.STATUS.value = "";
			qryForm.d01.value = "";
			qryForm.ksr.value = "";
			qryForm.jsr.value = "";
			qryForm.d02.value = "";
			qryForm.APPLICATIONID.value = "";
		}
		function showErrorInfo(APPLICATIONID,errorInfo){
			var url = "${ctx}/judicial/zfdj.shtml?action=toErrorInfo&APPLICATIONID="+APPLICATIONID+"&errorInfo="+errorInfo;
			showDialog(encodeURI(encodeURI(url)), 800, 400);
		}
		function doQuery(){			
			//if(FormValidate(qryForm)){
				setTimeout("qryForm.submit()",100);
			//}
			return false;
		}
		
	</script>
</html>

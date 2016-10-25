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
		<title>法院司法查控条件列表 </title>
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
								当前位置：司法查控 &gt;&gt; 法院司法查控条件列表 
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
					<form action="${ctx}/judicial/fyck.shtml?action=toViewZgfyTask" method="post"
						name="qryForm" onSubmit="return doQuery();">
						<input type="hidden" name="taskId" value="${param.taskId }"> 
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>
							
								<td width="70" align="right">
									条件编号：
								</td>
								<td width="120">						
									<input name="BDHM" type="text" autocheck="true" style="width:110px;"
										pattern="string" maxlength="20" label="条件编号">
									<script type="text/javascript">
										qryForm.BDHM.value="${param.BDHM}";
									</script>
													
								</td>
								
								<td align="left"> 
									<input type="submit" class="btn" value="查 询"> 
									<input type="reset"  class="btn" value="重 置"> 
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
						<input type="button" class="btn" value="返 回" onclick="back();"/>
						
					</div>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<div class="lst_area">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="lst">
							<tr class="fixedRow">
								<th align="center">
									序号
								</th>
								<th width="140" align="center">
									条件编号
								</th>
								<th  align="center">
									姓名
								</th>
								<th  align="center">
									性质
								</th>
								<th  align="center">
									证件类型
								</th>
								<th  align="center">
									证件号码
								</th>
								<th  align="center">
									执行法院名称
								</th>
								<th  align="center">
									往来账查询开始时间
								</th>
								<th  align="center">
									往来账查询结束时间
								</th>
								
								<th  align="center">
									出错信息
								</th>
								<!--<th  align="center">
									操作
								</th>																																					
							--></tr>
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
										${rst.BDHM }
									</td>
									<td align="center">
										${rst.XM }
									</td>
									<td align="center">
										<c:if test="${rst.XZ eq '1'}">
											查
										</c:if>
										<c:if test="${rst.XZ eq '2'}">
											控
										</c:if>
									</td>
									<td align="center">
										<c:if test="${rst.ZJLX eq '01'}">
												居民身份证
										</c:if>
										<c:if test="${rst.ZJLX eq '02'}">
												户口薄
										</c:if>
										<c:if test="${rst.ZJLX eq '03'}">
												中国护照
										</c:if>
										<c:if test="${rst.ZJLX eq '04'}">
												外国护照
										</c:if>
										<c:if test="${rst.ZJLX eq '05'}">
												港澳居民来往内陆通行证
										</c:if>
										<c:if test="${rst.ZJLX eq '06'}">
												港澳同胞回乡证
										</c:if>
										<c:if test="${rst.ZJLX eq '07'}">
												台湾居民来往大陆通行证
										</c:if>
										<c:if test="${rst.ZJLX eq '08'}">
												解放军士兵证
										</c:if>
										<c:if test="${rst.ZJLX eq '09'}">
												解放军军官证
										</c:if>
										<c:if test="${rst.ZJLX eq '10'}">
												解放军文职干部证
										</c:if>
										<c:if test="${rst.ZJLX eq '11'}">
												解放军离休干部荣誉证
										</c:if>
										<c:if test="${rst.ZJLX eq '12'}">
												解放军军官退休证
										</c:if>
										<c:if test="${rst.ZJLX eq '13'}">
												解放军文职干部退休证
										</c:if>
										<c:if test="${rst.ZJLX eq '14'}">
												武警士兵证
										</c:if>
										<c:if test="${rst.ZJLX eq '15'}">
												武警警官证
										</c:if>
										<c:if test="${rst.ZJLX eq '16'}">
												武警文职干部证
										</c:if>
										<c:if test="${rst.ZJLX eq '17'}">
												武警离休干部荣誉证
										</c:if>
										<c:if test="${rst.ZJLX eq '18'}">
												武警警官退休证
										</c:if>
										<c:if test="${rst.ZJLX eq '19'}">
												武警文职干部退休证
										</c:if>
										<c:if test="${rst.ZJLX eq '20'}">
												组织机构代码证
										</c:if>
										<c:if test="${rst.ZJLX eq '99'}">
												其他证件
										</c:if>
									</td>
									<td align="center">
										${rst.DSRZJHM }
									</td>
									<td align="center">
										${rst.FYMC }
									</td>
									
									<td align="center">
										${rst.CKKSSJ}
									</td>
									<td align="center">
										${rst.CKJSSJ}
									</td>
									
									<td align="center">
										${rst.ERRORINFO }
									</td>
									<!--<td align="center">
										<c:choose>
											<c:when test="${ not empty rst.GZZ}">
												<a href="${ctx}/judicial/fyck.shtml?action=downLoadFile&savePath=${rst.GZZ}">查看工作证</a>-
											</c:when>
											<c:otherwise>
												<a href="#" disabled>查看工作证</a>-
											</c:otherwise>
										</c:choose>
										<c:choose>
											<c:when test="${ not empty rst.GWZ}">
												<a href="${ctx}/judicial/fyck.shtml?action=downLoadFile&savePath=${rst.GWZ}">查看公务证</a>-
											</c:when>
											<c:otherwise>
												<a href="#" disabled>查看公务证</a>-
											</c:otherwise>
										</c:choose>
										
									</td>
								--></tr>
							</c:forEach>
							<c:if test="${empty page.result}">
								<tr>
									<td height="25" colspan="10" align="center" class="lst_empty">
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
								<form action="${ctx}/judicial/fyck.shtml?action=toViewTask"
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
			<input type="hidden" name="_backUrl" value="${ctx}/judicial/fyck.shtml?action=toViewZgfyTask">
			<input type="hidden" name="CXMB_ID">
			<input type="hidden" name="taskIds">
			<input type="hidden" name="taskId">
			<input type="hidden" name="taskStu">
			<input type="hidden" name="usrId" value='${usrId}'>
		    <input type="hidden" name="orgNo" value='${orgNo}'>
			${paramCover.coveredInputs}
		</form>
		<input type="hidden" name="tmpId" >
		<input type="hidden" name="tmpStus" >
	</body>
	<%@ include file="/commons/jslibs4tree.jsp"%>
	<script type="text/javascript">		
			
	
		
		
		function doQuery(){			
			if(FormValidate(qryForm)){
				setTimeout("qryForm.submit()",100);
			}
			return false;
		}	
		
		function toView(taskId){			
			detailForm.action = "${ctx}/judicial/fyck.shtml?action=toViewZgfyTask";
			detailForm.taskId.value = taskId;
			setTimeout("detailForm.submit()",100);
			return false;
		}	
		function back(){
			detailForm.action = "${ctx}/judicial/fyck.shtml?action=toZgfyTaskList";
			setTimeout("detailForm.submit()",100);
			return false;
		}	
		
		
	</script>
</html>

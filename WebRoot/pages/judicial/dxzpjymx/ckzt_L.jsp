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
		<title>人民银行电信诈骗持卡主体查询</title>
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
								当前位置：司法查询 &gt;&gt; 人民银行电信诈骗查控列表 &gt;&gt; 账户持卡主体查询
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
				 	<form action="${ctx}/judicial/jymx.shtml?action=toCkTaskList" method="post"
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
										<option value="7">正在进行查询</option>
										<option value="8">查询结果待反馈</option>
										<option value="9">查询结果已反馈</option>
										<option value="10">查询中报错</option>
									</select>
									<script type="text/javascript">
										qryForm.STATUS.value="${param.STATUS}";
									</script>				
								</td>
								<td width="100" align="right">
									任务查询日期：
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
						<input type="button" class="btn" id="plxz" value="批量下载任务信息"> 
					</div>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<div class="lst_area">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="lst">
							<tr class="fixedRow">
								<th width="50" align="center">
									<input id="allSelect" type="checkbox" value="0" onclick="selectAll(this);"/> 全选
								</th>
								<th width="100" align="center">
									业务申请编号
								</th>
								<th  align="center">
									主体类别
								</th>
								<th  align="center">
									所属银行名称
								</th>
								<th  align="center">
									账户名
								</th>
								<th  align="center">
									卡/折号
								</th>
								<th  align="center">
									查询日期
								</th>
								<th  align="center">
									任务状态
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
										<input type="checkbox" name="chekAppId" class="appIdCls" value="${rst.APPLICATIONID }"/>
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
									<td align="center">
										<c:if test="${rst.SUBJECTTYPE eq '1'}">
												自然人主体
										</c:if>
										<c:if test="${rst.SUBJECTTYPE eq '2'}">
												法人主体
										</c:if>
									</td>
									<td align="center">
										${rst.BANKNAME }
									</td>
									<td align="center">
										${rst.ACCOUNTNAME }
									</td>
									<td align="center">
										${rst.CARDNUMBER }
									</td>
									<td align="center">
										<fmt:formatDate value="${rst.QRY_DATE}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> 
									</td>
									<td align="center">
										<c:if test="${rst.STATUS eq 7}">
											<font color="blue">正在进行查询</font>
										</c:if>
										<c:if test="${rst.STATUS eq 8}">
											<font color="green">查询结果待反馈</font>
										</c:if>
										<c:if test="${rst.STATUS eq 9}">
											<font color="green">查询结果已反馈</font>
										</c:if>
										<c:if test="${rst.STATUS eq 10}">
											<a href="#" style="text-decoration: underline" onclick="showErrorInfo('${rst.APPLICATIONID }','${rst.ERRORINFO }');"><font color="red">查询中报错</font></a>
										</c:if>
									</td>
									<td align="center">
										<a href="${ctx}/judicial/jymx.shtml?action=toCkView&APPLICATIONID=${rst.APPLICATIONID}">查看</a>-
										<a href="${ctx}/judicial/jymx.shtml?action=toCkfkTaskList&APPLICATIONID=${rst.APPLICATIONID}">查看结果</a>
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
								<form action="${ctx}/judicial/jymx.shtml?action=toCkTaskList"
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
			<input type="hidden" name="_backUrl" value="${ctx}/judicial/jymx.shtml?action=toCkTaskList">
			<input type="hidden" name="APPLICATIONID">
			<input type="hidden" name="STATUS">
			<input type="hidden" name="checkAppIds">
			${paramCover.coveredInputs}
		</form>
	</body>
	<%@ include file="/commons/jslibs4tree.jsp"%>
	<script type="text/javascript">	
		$("#plxz").click(function(){
			var tempVal = ''  ;
			$("input:checked").each(function(){
				tempVal += $(this).val()+",";
			});
			//alert(tempVal);
			if(tempVal == '' ){
				alert("请选择至少一条记录进行下载");
				return ;
			}
			detailForm.action="${ctx}/judicial/jymx.shtml?action=batchDownloadCkztInfo";
			detailForm.checkAppIds.value=tempVal;
			setTimeout("detailForm.submit()",100);
		});
	
		$("input[name='chekAppId']").click(function(){
		    var flag = true;
		    $("input[name='chekAppId']").each(function (){
		        if(!$(this).prop("checked")){
		            flag = false;
		        }
		    });
		    if(flag){
		        $("#allSelect").attr("checked","checked");
		    }else{
		        $("#allSelect").removeAttr("checked");
		    }
		});

		function selectAll(obj){
		    if(obj.checked==true){
		        $("input[name='chekAppId']").attr("checked","checked");
		    }else{
		        $("input[name='chekAppId']").removeAttr("checked");
		    }
		}
		function cz(){
			qryForm.STATUS.value = "";
			qryForm.APPLICATIONID.value = "";
			qryForm.d01.value = "";
			qryForm.ksr.value = "";
			qryForm.jsr.value = "";
			qryForm.d02.value = "";
			   
		}
		function showErrorInfo(APPLICATIONID,errorInfo){
			var url = "${ctx}/judicial/jymx.shtml?action=toErrorInfo&APPLICATIONID="+APPLICATIONID+"&errorInfo="+errorInfo;
			showDialog(encodeURI(encodeURI(url)), 800, 400);
		}
		function doQuery(){			
			if(FormValidate(qryForm)){
				setTimeout("qryForm.submit()",100);
			}
			return false;
		}
		
	</script>
</html>

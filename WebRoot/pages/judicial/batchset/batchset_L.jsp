<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/${theme}/css/style.css">
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/${theme}/css/tools.css">
		<%@ include file="/commons/jslibs.jsp"%>
		<title>批量设置信息</title>
	</head>
	<body>
		<table width="100%" height="100%" border="0" cellspacing="0"
			cellpadding="0" class="panel">
			<tr>
				<td height="27">
					<table width="100%" height="100%" cellspacing="0" cellpadding="0" class="wz">
						<tr>
							<td width="28" align="center">
								<label class="wz_img"></label>
							</td>
							<td>
								当前位置：系统管理 &gt;&gt;  批量设置 &gt;&gt; 批量设置信息列表
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
					<form action="${ctx}/judicial/batchset.shtml?action=toList"
						method="post" name="qryForm" onSubmit="return doQuery();">
						<table width="100%" border="0" cellpadding="2" cellspacing="0"
							class="qry">
							<tr>
								<td width="70" align="center">
									交易类型：
								</td>
								<!-- 此处的交易类型从SYS_VAR参数表中获取 -->
								<td width="120">
									<select name="batchTypeName" style="width: 180px;">
										<option value="">不限</option>
										<c:forEach items="${batchTypes2}" var="bt">
											<option value="${bt.VALUE}">${bt.VAR_NAME}</option>
										</c:forEach>
									</select>
									<script type="text/javascript">
										qryForm.batchTypeName.value="${param.batchTypeName}";
									</script>
								</td>
								<td width="70" align="center">
									状态：
								</td>
								<td width="90" >
									<select name="isValid" style="width:90px;">
										<option value="">不限</option>
										<option value="Y">有效</option>
										<option value="N">无效</option>
									</select>
									<script type="text/javascript">
										qryForm.isValid.value="${param.isValid}";
									</script>
								</td>
								
								
								
								<td align="left">&nbsp;&nbsp;&nbsp;
									<input type="submit" class="btn" value="查 询">
									<input type="reset" class="btn" value="重 置">
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td class="rowSplit"></td>
			</tr>
			<tr>
				<td height="30">
					<div class="lst_title">
						<input type="button" class="btn" value="增 加" onClick="addEntry();" />
						<input type="button" class="btn" value="修 改" onClick="modiEntry();" />
						<input type="button" class="btn" value="删 除" onClick="deleteEntry();" />
						<input type="button" class="btn" value="启 用" onClick="enable_set();" /> 
						<input type="button" class="btn" value="停 用" onClick="freeze_set();" />
					</div>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<div class="lst_area">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="lst">
							<tr class="fixedRow">

								<th width="2%">
								</th>
								<th width="16%" align="center">
									批量查询交易类型
								</th>
								<th width="16%" align="center">
									是否需要查询日期条件
								</th>
								<th width="22%" align="center">
									批量查询证件类型
								</th>
								<th width="16%" align="center">
									配置是否有效
								</th>
								<th width="10%" align="center">
									<font color="red">详细信息</font>
								</th>
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" id="${r}" onMouseOver="mover(this)"
									onMouseOut="mout(this)"
									onClick="setEid(document.getElementById('eid${rr}'));">
									<th width="20">
										<input type="radio" name="eid" id="eid${rr}"
											 value="${rst.NUMBER}|${rst.IS_VALID} "
											onclick="setEid(this);">
									</th>
									<!-- 此处的交易类型从BATCH_IMPORT_CONFIG参数表中获取 -->
									<td align="center">
										${rst.BATCH_TYPE_NAME}
									</td>
									<td align="center">
										<c:if test="${rst.NEED_QUERY_DATE eq 'Y'}">
											是
										</c:if>
										<c:if test="${rst.NEED_QUERY_DATE eq 'N'}">
											否
										</c:if>
									</td>
									<td align="center">
										${rst.VarTypeName}
									</td>

									<td align="center">
										<c:if test="${rst.IS_VALID eq 'Y'}">
											有效
										</c:if>
										<c:if test="${rst.IS_VALID eq 'N'}">
											无效
										</c:if>
									</td>
									<td align="center" nowrap>
										<a href="##" onClick="javascript:toDetail('${rst.NUMBER }','${rst.VarTypeName}')">详细信息</a>
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
			<!-- 分页代码 ListPage类中 -->
			<tr>
				<td height="30" align="center">
					<table width="100%" height="100%" border="0" cellpadding="0"
						cellspacing="0" class="lst_toolbar">
						<tr>
							<td>
								<form action="${ctx}/judicial/batchset.shtml?action=toList"
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
			<input type="hidden" name="_backUrl"
				value="${ctx}/judicial/batchset.shtml?action=toList">
			<input type="hidden" name="number">
			<input type="hidden" name="noType">
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
		}
	
		function checkEid(number){		
			if(number==null || ""==number.trim()){
				showErrorMsg("请选择批量！");
				return false;
			}
			return true;
		}		
		function todo(act,number,noType){
			showWaitPanel('');
			detailForm.action=act;
			detailForm.number.value=number;
			detailForm.noType.value=noType;
			setTimeout("detailForm.submit()",100);	
			try{ tmpId.parentNode.focus(); }catch(e){}
		}
		function addEntry(){
			todo("${ctx}/judicial/batchset.shtml?action=toAdd","","");
		}		
		function modiEntry(){
			var number = tmpId.value.trim();
			var is_valid = tmpStus.value.trim();
	        if(is_valid=='Y') {
		        showErrorMsg("启用状态不能修改！");
		        return;
	        }
			if(!checkEid(number))return;
			todo("${ctx}/judicial/batchset.shtml?action=toModi",number,"");
		}
		function deleteEntry(){
			var is_valid = tmpStus.value.trim();
	        if(is_valid=='Y') {
		        showErrorMsg("启用状态不能删除!");
		        return;
	        } 
			var NUMBER = tmpId.value.trim();
			if(!checkEid(NUMBER))return;
			if(confirm("您确信要删除该条信息吗？")){
				todo("${ctx}/judicial/batchset.shtml?action=delete",NUMBER,"");
			}
		}
		function enable_set(){ 
			
			var number = tmpId.value;
			if(number==null || ""==number.trim()){
				showErrorMsg("请选择批量！");
				return false;
			}
			var isValid = tmpStus.value.trim();
	        if(isValid != 'N'){
	        	showErrorMsg("操作无效，无效的状态才能启用！");
	        	return;
	        }
			if(!checkEid(number))return;
			todo("${ctx}/judicial/batchset.shtml?action=doqy",number,"");
		}
		function freeze_set(){ 
			
			var number = tmpId.value;
			if(number==null || ""==number.trim()){
				showErrorMsg("请选择批量！");
				return false;
			}
			var isValid = tmpStus.value.trim();
			if(isValid != 'Y'){
	        	showErrorMsg("启用状态才能冻结！");
	        	return;
	        }
			if(!checkEid(number))return;
			todo("${ctx}/judicial/batchset.shtml?action=dodj",number,"");
		}
		
		function toDetail(number,noType){
			todo("${ctx}/judicial/batchset.shtml?action=toView",number,noType);
		}
	</script>
</html>

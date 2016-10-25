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
		<title>增加批量设置</title>
	</head>
	<body>	
	<div class="lst_area">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="27">
					<table width="100%" height="100%" cellspacing="0" cellpadding="0" class="wz">
						<tr>
							<td width="28" align="center">
								<label class="wz_img"></label>
							</td>
							<td>
								当前位置：系统管理 &gt;&gt;  批量设置 &gt;&gt; 批量设置信息增加
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
				<td>
					<form name="form" method="post"
						action="${ctx}/judicial/batchset.shtml?action=add"
						onsubmit="return doSave();">
						<input type="hidden" name="usrId" value='${usrId}'>
		                <input type="hidden" name="orgNo" value='${orgNo}'>
						<input type="hidden" name="SM" value="${param.SM}">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									司法查询批量设置信息
								</td>
							</tr>
							<tr>
								<td width="100%" align="center">
									<table width="70%" border="0" cellpadding="4" cellspacing="4"
										class="detail">
										<tr>
											<td class="detail2">
												<table width="100%" border="0" cellpadding="1"
													cellspacing="1" class="detail3">
													<tr>
														<td height="30" align="right" class="detail_label">
															批量设置交易类型：
														</td>
														<td class="detail_content">
															<select name="batchType" id="batchType"
																style="width: 180px">
																<c:forEach items="${batchTypes}" var="bt">
																	<option value="${bt.VALUE}|${bt.VAR_NAME}">
																		${bt.VAR_NAME}
																	</option>
																</c:forEach>
															</select>
															<span style="color: red">*</span>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															支持查询证件类型：
														</td>
														
														<td class="detail_content">
															<select id="queryNoType" multiple="multiple" style="width: 180px" label="查询证件类型" required="true" autocheck="true" pattern="string" size="${size}">
																<c:forEach items="${importNoTypes}" var="int">
																	<option value="${int.VALUE}">
																		${int.VAR_NAME}
																	</option>
																</c:forEach>
															</select>
															<span style="color: red">*</span>
															<input type="hidden" id="inputAccType" name="queryNoType">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															是否需要查询日期条件：
														</td>
														<td height="30" align="left" class="detail_content">
															<select name="needQueryDate" id="needQueryDate" style="width: 180px" >
																<option value="Y">是</option>
																<option value="N">否</option>
															</select>
															<span style="color: red">*</span>
														</td>
													</tr>
													<tr style="display:none">
														<td height="30" align="right" class="detail_label">
															配置是否有效：
														</td>
														<td class="detail_content">
														   <select name="isValid" style="width:180px" >
																<option value="Y" >启用</option>
															</select>
														</td>
													</tr>	
													<tr>
														<td height="30" align="right" class="detail_label">
															字段名：
														</td>
														<td class="detail_content">
															<textarea rows="2" name="exportColumn" class="textArea" style="width: 600px" label="字段名" 
															 required="true" autocheck="true" pattern="string" maxlength="100" id="exportColumn"></textarea>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															字段中文名：
														</td>
														<td class="detail_content">
															<textarea rows="4" name="exportName" class="textArea" style="width: 600px" label="字段中文名" 
															required="true" autocheck="true" pattern="string"  maxlength="200"  id="exportName"></textarea>
														</td>
													</tr>
													
													<tr>
														<td height="30" align="right" class="detail_label">
															FROM语句：
														</td>
														<td class="detail_content">
															<textarea rows="6" name="querySQL" class="textArea" style="width: 600px" label="FROM语句" 
															 required="true" autocheck="true"  pattern="string" maxlength="300"  id="querySQL"></textarea>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="49" align="center" valign="middle">
									<input type="submit" class="btn" value="增 加">
									&nbsp;&nbsp;
									<input type="reset" class="btn" value="重 置">
									<c:if test="${not empty param._backUrl}">&nbsp;&nbsp;
										<input type="button" class="btn" value="返 回"
											onclick="history.back();">
									</c:if>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
		</div>
	</body>
	<script type="text/javascript" src="${ctx}/scripts/util/calendar.js"></script>
	<%@ include file="/commons/jslibs.jsp"%>
	<%@ include file="/commons/jslibs4tree.jsp"%>
	<script type="text/javascript">InitFormValidator(0);</script>
	<script type="text/javascript">
		function doSave(){
		
			var a = document.getElementById("queryNoType");
			var val = '';
			var seleLength = a.options.length;
			for(var i = 0;i<seleLength;i++){
				if(a.options[i].selected){
					val += a.options[i].value + ",";
				}
			}
			if(val == ''){
				alert("请选择一种或多种 支持查询的证件类型");
				return false;
			}
			
			document.getElementById("inputAccType").value = val.substr(0,val.length-1);
			
			if(FormValidate(0)){
				setTimeout("form.submit();",100);
			}
			
			return false;
		}
	</script>
</html>

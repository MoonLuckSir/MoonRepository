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
		<title>查询菜单</title>
	</head>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="27">
					<table width="100%" height="100%" cellspacing="0" cellpadding="0"
						class="wz">
						<tr>
							<td width="28" align="center"><label class="wz_img"></label></td>
							<td>  
								当前位置：司法查控 &gt;&gt; 条件设置 &gt;&gt; 修改规则
							</td> 
							<td width="50" align="right">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td class="rowSplit"></td></tr> 
			<tr>
				<td>
					<form name="form" method="post"
						action="${ctx}/judicial/sfck.shtml?action=modiRule"
						onsubmit="return doSave();">
						<input type="hidden" name="SM" value="${param.SM}">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									修改条件规则
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
														<td width="25%" height="30" class="detail_label">
															规则编号：
														</td>
														<td width="75%" class="detail_content"> 
															<input readonly="readonly" class="readonly" name="RULE_ID" type="text" autocheck="true" required="true" label="规则编号"
																pattern="string"  value="${res.RULE_ID}">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															对应条件：
														</td>
														<td class="detail_content">
															<input name="CONDITION_NAME" type="text" pattern="string" label="对应条件" readonly="readonly" class="readonly"
																autocheck="true"  required="true" 
																value="${res.CONDITION_NAME}">
															<input type="hidden" name="CONDITION_ID" value="${res.CONDITION_ID}">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															规则类型：
														</td>
														<td class="detail_content">
															<input type="hidden" name="RULE_NAME" value="${res.RULE_NAME}"/>
															<select name="RULE_NAME" onchange="changeRuleType();" disabled="disabled" >
																<option value="1">必输项</option>
																<option value="2">下拉列表框</option>
																<option value="3">最大值</option>
																<option value="4">最小值</option>
																<option value="5">最大长度</option>
																<option value="6">最小长度</option>
															</select>
															<script type="text/javascript">
																form.RULE_NAME.value = ${res.RULE_NAME}
															</script>
														</td>
													</tr>
													
													
													<tr>
														<td height="30" align="right" class="detail_label">
															规则值：
														</td>
														<td class="detail_content">
															<input name="RULE_VALUE" type="text"  maxlength="180" pattern="string"
															   required="true" autocheck="true" label="规则值" value="${res.RULE_VALUE }">&nbsp;<font color="red">(规则类型选择必输项，规则值默认为1;规则类型为选择项，规则值格式必须为key:value;key:value...的形式;其他类型规则值为数字)</font>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															描述：
														</td>
														<td class="detail_content">
															<textarea  rows="5" cols="30" autocheck="true"  name="RULE_DESC" pattern="string" label="描述">${res.RULE_DESC }</textarea>
															
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
									<input type="submit" class="btn" value="修 改">
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
	</body>
	<%@ include file="/commons/jslibs.jsp"%>
	<script type="text/javascript">InitFormValidator(0);</script>
	<script type="text/javascript">
		function doSave(){
			var RULE_NAME = document.getElementById("RULE_NAME").value;
			var RULE_VALUE = document.getElementById("RULE_VALUE").value;
			var reg = /[^[0-9]+/
			if(RULE_NAME != "2" && reg.test(RULE_VALUE)){
					alert("当规则类型不为选择项时,规则值不可为非数字!");
					return false;
			}
			alert(RULE_NAME);
			if(FormValidate(0)){
				
				setTimeout("form.submit();",100);
			}
			return false;
		}
		
		function checkInptNumber(obj){
			var reg = /[^[0-9]+/
			if(reg.test(obj.value)){
				obj.value="";
			}
		}
		
		function changeRuleType(){
			var RULE_NAME = document.getElementById("RULE_NAME").value;
		   if(RULE_NAME == '1'){
		   		form.RULE_VALUE.value = "1";
		   }
		   
		}
	</script>
</html>

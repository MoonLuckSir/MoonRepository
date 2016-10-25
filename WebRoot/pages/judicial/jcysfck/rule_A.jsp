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
		<title>增加模板</title>
	</head>
	<body>	
	<div class="lst_area">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="27">
					<table width="100%" height="100%" cellspacing="0" cellpadding="0"
						class="wz">
						<tr>
							<td width="28" align="center">
								<label class="wz_img"></label>
							</td>
							<td>
								当前位置：司法查控 &gt;&gt; 条件设置 &gt;&gt; 增加规则
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
						action="${ctx}/judicial/sfck.shtml?action=addRule"
						onsubmit="return doSave();">
						<input type="hidden" name="usrId" value='${usrId}'>
						<input type="hidden" name="CONDITION_ID" value='${param.CONDITION_ID}'>
		                <input type="hidden" name="orgNo" value='${orgNo}'>
						<input type="hidden" name="SM" value="${param.SM}">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									新增条件规则信息
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
													
													<!--  <tr>
														<td height="30" align="right" class="detail_label">
															对应条件：
														</td>
														<td class="detail_content">
															<input name="CONDITION_ID" type="text" pattern="string" readonly="readonly" class="readonly"
															    autocheck="true" label="对应条件" required="true" value="${param.CONDITION_ID}">
														</td>
													</tr>-->
												
												     <tr>
														<td height="30" align="right" class="detail_label">
															规则类型：
														</td>
														<td class="detail_content">
															<select name="RULE_NAME" onchange="changeRuleType();">
																<option value="1">必输项</option>
																<option value="2">下拉列表框</option>
																<option value="3">最大值</option>
																<option value="4">最小值</option>
																<option value="5">最大长度</option>
																<option value="6">最小长度</option>
															</select>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															规则值：
														</td>
														<td class="detail_content">
															<input name="RULE_VALUE" type="text"  maxlength="180" pattern="string"
															   required="true" autocheck="true" label="规则值" value="1">&nbsp;<font color="red">(规则类型选择必输项，规则值默认为1;规则类型为选择项，规则值格式必须为key:value;key:value...的形式;其他类型规则值为数字)</font>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															描述：
														</td>
														<td class="detail_content">
															<textarea  rows="5" cols="30" autocheck="true"  name="RULE_DESC" pattern="string" label="描述"></textarea>
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
			var RULE_NAME = document.getElementById("RULE_NAME").value;
			var RULE_VALUE = document.getElementById("RULE_VALUE").value;
			var reg = /[^[0-9]+/
			if(RULE_NAME != "2" && reg.test(RULE_VALUE)){
						alert("当规则类型不为选择项时,规则值不可为非数字!");
						return false;
				}
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
			var onlyNm = new RegExp(/[^0-9]/g);
		   if(RULE_NAME == '1'){
		   		form.RULE_VALUE.value = "1";
		   }
		   
		}
		
		
		
		function select_orgPar(btn){			
			var nameTarget=form.orgParName;
			var srcTarget=form.orgId;
			var sUrl="${ctx}/judicial/lscxmb.shtml?action=seletor";
			selectOrgan(btn,nameTarget,srcTarget,false,sUrl,'${ctx}');	
		} 
	</script>
</html>

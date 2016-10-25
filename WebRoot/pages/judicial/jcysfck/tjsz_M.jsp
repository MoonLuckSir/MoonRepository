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
								当前位置：司法查控&gt;&gt; 条件设置 &gt;&gt; 修改条件 
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
						action="${ctx}/judicial/sfck.shtml?action=modiCon"
						onsubmit="return doSave();">
						<input type="hidden" name="SM" value="${param.SM}">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									修改查询条件
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
															条件编号：
														</td>
														<td width="75%" class="detail_content"> 
															<input readonly="readonly" class="readonly" name="CONDITION_ID" type="text" autocheck="true" required="true" label="条件编号"
																pattern="string" maxlength="6" value="${res.CONDITION_ID}">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															条件名称：
														</td>
														<td class="detail_content">
															<input name="CONDITION_NAME" type="text" pattern="string" label="条件名称" maxlength="50"
																size="50" autocheck="true"  required="true" 
																value="${res.CONDITION_NAME}">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															物理名称：
														</td>
														<td class="detail_content"> 
															<input readonly="readonly" class="readonly" name="PHYSICAL_NAME" type="text" pattern="string"  label="物理名称" required="true" 
																autocheck="true" maxlength="20" value="${res.PHYSICAL_NAME}"><font color="red">(表示该条件在数据库表中的字段名)</font>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															输入框类型：
														</td>
														<td class="detail_content">
															<select name="INPUT_TYPE" >
																<option value="1">输入框</option>
																<option value="2">下拉列表框</option>
																<option value="3">选择组件</option>
																<option value="4">文本框</option>
																<option value="5">隐藏组件</option>
															</select>
															<script type="text/javascript">
																form.INPUT_TYPE.value = ${res.INPUT_TYPE}
															</script>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															数据类型：
														</td>
														<td class="detail_content">
															<select name="VALUE_TYPE">
																<option value="1">字符串</option>
																<option value="2">数字</option>
																<option value="3">日期</option>
															</select>
															<script type="text/javascript">
																form.VALUE_TYPE.value = ${res.VALUE_TYPE}
															</script>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															默认值：
														</td>
														<td class="detail_content">
															<input name="VALUE_DEF" type="text" pattern="string"
															    autocheck="true" label="默认值" value="${res.VALUE_DEF}">&nbsp;<font color="red">(输入DATE代表当前日期，输入ORG代表当前机构，输入USER代表当前用户)</font>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															所属模板：
														</td>
														<td class="detail_content">
															<input name="CXMB_NAME" type="text" pattern="string"  readonly="readonly" class="readonly"
															    autocheck="true" label="所属模板" value="${res.CXMB_NAME}">
															<input name="CXMB_ID" type="hidden" value="${res.CXMB_ID}">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															描述：
														</td>
														<td class="detail_content">
															<textarea rows="5" cols="30" autocheck="true"  name="CONDITION_DESC" pattern="string" label="描述">${res.CONDITION_DESC}</textarea>
															
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
			if(FormValidate(0)){
				setTimeout("form.submit();",100);
			}
			return false;
		}
	</script>
</html>

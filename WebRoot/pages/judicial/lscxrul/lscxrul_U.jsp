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
		<title>规则信息录入</title>
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
								当前位置：报表查询 &gt;&gt; 司法查询定制设计 &gt;&gt; 司法查询模板 &gt;&gt; 规则信息修改
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
						action="${ctx}/judicial/lscxrul.shtml?action=update"
						onsubmit="return doSave();">
						<input type="hidden" name="SM" value="${param.SM}">
						<input type="hidden" name="CONMX_NO" value="${rst.CONMX_NO}">
						<input type="hidden" name="RULE_ID" value="${rst.RULE_ID}">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									规则信息录入
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
														<td width="50%" height="30" align="right" class="detail_label">
															规则名称：
														</td>
														<td width="50%" class="detail_content">
														<select name="RULE_NAME" style="width:155px" >
																<option value="" <c:if test="${rst.RULE_NAME eq '' }">selected</c:if>></option>
																<option value="N选一" <c:if test="${rst.RULE_NAME eq 'N选一' }">selected</c:if>>N选一</option>
																<option value="必输" <c:if test="${rst.RULE_NAME eq '必输' }">selected</c:if>>必输</option>
																<option value="最大值" <c:if test="${rst.RULE_NAME eq '最大值' }">selected</c:if>>最大值</option>
															    <option value="最小值" <c:if test="${rst.RULE_NAME eq '最小值' }">selected</c:if>>最小值</option>
															    <option value="最大长度" <c:if test="${rst.RULE_NAME eq '最大长度' }">selected</c:if>>最大长度</option>
															    <option value="最小长度" <c:if test="${rst.RULE_NAME eq '最小长度' }">selected</c:if>>最小长度</option>
															    <!--  <option value="日期不容许跨年查询" <c:if test="${rst.RULE_NAME eq '日期不容许跨年查询' }">selected</c:if>>日期不容许跨年查询</option>
															   	<option value="日期跨度不超一个月" <c:if test="${rst.RULE_NAME eq '日期跨度不超一个月' }">selected</c:if> >日期跨度不超一个月</option>
															   -->
															    <option value="值合法性" <c:if test="${rst.RULE_NAME eq '值合法性' }">selected</c:if>>值合法性</option>
															    <option value="关联选择" <c:if test="${rst.RULE_NAME eq '关联选择' }">selected</c:if>>关联选择</option>
															</select>	 
															 
															 
															 
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															规则值：
														</td>
														<td class="detail_content">
															<input name="RULE_VALUE" type="text" pattern="string" size='80' label="规则值"
															  value="${rst.RULE_VALUE }">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															规则描述：
														</td>
														<td class="detail_content">
															<input name="RULE_DESC" type="text" pattern="string" label="规则描述"
																 autocheck="true" value="${rst.RULE_DESC }">
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

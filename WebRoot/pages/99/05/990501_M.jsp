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
		<title>修改参数</title>
	</head>
	<body>
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
								当前位置：系统管理 &gt;&gt; 参数管理 &gt;&gt; 修改参数
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
				<td>
					<form name="form" method="post"
						action="${ctx}/system/sysvar.shtml?action=modi"
						onsubmit="return doSave();">
						<input type="hidden" name="SM" value="${param.SM}">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									修改参数
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
															参数码：
														</td>
														<td width="75%" class="detail_content">
															<input name="varId" type="text" readonly class="readonly"
																maxlength="10" value="${var.VAR_ID }">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															参数名称：
														</td>
														<td class="detail_content">
															<input name="varName" type="text" pattern="string"
																label="参数名称" size="50" autocheck="true" maxlength="100"
																required="true" value="${var.VAR_NAME }">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															参数类码：
														</td>
														<td class="detail_content">
															<input name="varType" type="text" pattern="string"
															 label="参数类码" autocheck="true"
																maxlength="10" value="${var.VAR_TYPE }">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															参数值：
														</td>
														<td class="detail_content">
															<input name="value" type="text" pattern="string"
																label="参数值" autocheck="true"
																maxlength="10" value="${var.VALUE }">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															参数描述：
														</td>
														<td class="detail_content">
															<input name="varDesc" type="text" size="60" label="参数描述"
																pattern="string" maxlength="100"
																value="${var.VAR_DESC }">
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

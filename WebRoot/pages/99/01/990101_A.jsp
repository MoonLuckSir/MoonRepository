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
		<title>查询机构</title>
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
								当前位置：系统管理 &gt;&gt; 机构管理 &gt;&gt; 增加机构
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
						action="${ctx}/system/organ.shtml?action=add"
						onsubmit="return doSave();">
						<input type="hidden" name="SM" value="${param.SM}">
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									机构信息
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
															机构编号：
														</td>
														<td width="75%" class="detail_content">
															<input name="orgId" type="text" autocheck="true"
																label="机构编号" required="true" pattern="int"
																maxlength="20">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															机构名称：
														</td>
														<td class="detail_content">
															<input name="orgName" type="text" pattern="string"
																label="机构名称" size="50" autocheck="true" maxlength="100"
																required="true">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															机构级别：
														</td>
														<td class="detail_content">
															<select name="orgLvl" style="width: 240px">
																<option value="">
																	----全部----
																</option>
																<c:forEach items="${orgLvls}" var="orgLvl">
																	<option value="${orgLvl.value}">
																		${orgLvl.value} - ${orgLvl.name}
																	</option>
																</c:forEach>
															</select>
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															上级机构：
														</td>
														<td class="detail_content">
															<input name="orgParId" type="text" pattern="int"
																label="上级机构" autocheck="true" maxlength="20">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															机构状态：
														</td>
														<td class="detail_content">
															<input name="orgStus" type="text" size="50" label="机构状态"
																pattern="string" maxlength="10">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															备注：
														</td>
														<td class="detail_content">
															<input name="orgDesc" type="text" size="50" label="备注"
																pattern="string" maxlength="200">
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
	</body>
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

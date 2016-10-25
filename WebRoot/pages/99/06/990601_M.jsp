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
		<title>增加用户</title>
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
								当前位置：系统管理 &gt;&gt;  配置表管理  &gt;&gt; 增加配置表
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
						action="${ctx}/system/paramHis.shtml?action=modi"
						onsubmit="return doSave();">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									修改配置表
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
													<input name="id" type="hidden" value="${rst.ID}">
													<tr>
														<td height="30" align="right" class="detail_label">
															模式名：
														</td>
														<td class="detail_content">
															<input name="tabSchema" type="text" pattern="string"
																size="50" autocheck="true" maxlength="50" label="年度表名"
																required="true" value="${rst.TABSCHEMA}">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															原始表名：
														</td>
														<td class="detail_content">
															<input name="tabShotName" type="text" pattern="string"
																size="50" autocheck="true" maxlength="40" label="原始表名"
																required="true" value="${rst.TABSHOTNAME}">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															年度：
														</td>
														<td class="detail_content">
															<input name="tabYear" type="text" pattern="string" label="年度"
																size="50" autocheck="true" maxlength="4" value="${rst.TABYEAR}">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															系统渠道：
														</td>
														<td class="detail_content">
															<input name="tabcrc" type="text" pattern="string" label="系统渠道"
																size="50" autocheck="true" maxlength="50" value="${rst.TABCRC}">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															状态：
														</td>
														<td class="detail_content">
															<select name="tabState" style="width:300px;">
																<option value="0" <c:if test="${rst.TABSTATE eq '0' }">selected</c:if>>正常</option>
																<option value="1" <c:if test="${rst.TABSTATE eq '1' }">selected</c:if>>废弃</option>
															</select>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															类型：
														</td>
														<td class="detail_content">
															<select name="tabType" style="width:300px;">
																<option value="O" <c:if test="${rst.TABTYPE eq 'O' }">selected</c:if>>单表</option>
																<option value="M" <c:if test="${rst.TABTYPE eq 'M' }">selected</c:if>>月</option>
																<option value="Y" <c:if test="${rst.TABTYPE eq 'Y' }">selected</c:if>>年</option>
															</select>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															表字段：
														</td>
														<td class="detail_content">
															<input name="tabCol" type="text" pattern="string" label="表字段"
																size="50" autocheck="true" maxlength="2000" value="${rst.TABCOL}">
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
	<script type="text/javascript" src="${ctx}/scripts/util/calendar.js"></script>
	<%@ include file="/commons/jslibs.jsp"%>
	<%@ include file="/commons/jslibs4tree.jsp"%>
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
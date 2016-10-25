<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<title>查询用户</title>
	</head>
	<body>
		<table width="100%" border="0" cellspacing="10" cellpadding="0">
			<tr>
				<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="34" height="27" class="wz_left">
								&nbsp;
							</td>
							<td class="wz_bg">
								当前位置：系统管理 &gt;&gt; 用户管理 &gt;&gt; 查询用户
							</td>
							<td width="12" class="wz_right">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<form name="form" method="post"
						action="${ctx}/system/user.shtml?action=toList"
						onsubmit="return doSave();">
						<input type="hidden" name="SM" value="${param.SM}">
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									查询用户
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
														<td width="25%" height="30" align="right"
															class="detail_label">
															参数码：
														</td>
														<td width="75%" height="30" align="left"
															class="detail_content">
															&nbsp;
															<input name="userId" type="text" autocheck="true"
																pattern="string" maxlength="20">
															<script type="text/javascript">
																form.userId.value="${param.userId}";
															</script>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															参数名称：
														</td>
														<td height="30" align="left" class="detail_content">
															&nbsp;
															<input name="userName" type="text" pattern="string"
																size="50" autocheck="true" maxlength="60">
															<script type="text/javascript">
																form.userName.value="${param.userName}";
															</script>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															参数类码：
														</td>
														<td height="30" align="left" class="detail_content">
															&nbsp;
															<input name="orgId" type="text" pattern="string"
																size="50" autocheck="true" maxlength="20">
															<script type="text/javascript">
																form.orgId.value="${param.orgId}";
															</script>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															用户职位：
														</td>
														<td height="30" align="left" class="detail_content">
															&nbsp;
															<input name="userPos" type="text" pattern="string"
																size="50" autocheck="true" maxlength="20">
															<script type="text/javascript">
																form.userPos.value="${param.userPos}";
															</script>
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
									<input type="submit" class="btn" value="查 询">
									&nbsp;&nbsp;
									<input type="reset" class="btn" value="重 置">
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
</html>

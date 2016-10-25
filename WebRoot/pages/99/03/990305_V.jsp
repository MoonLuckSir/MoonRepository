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
		<title>修改角色</title>
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
								当前位置：系统管理 &gt;&gt; 角色管理 &gt;&gt; 修改角色
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
						action="${ctx}/system/role.shtml?action=modi"
						onsubmit="return doSave();">
						<input type="hidden" name="SM" value="${param.SM}">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									角色信息
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
															角色编号：
														</td>
														<td width="75%" class="detail_content"> 
															${entry.ROLE_ID}
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															角色名称：
														</td>
														<td class="detail_content">
															${entry.ROLE_NAME}
														</td>
													</tr> 
													<tr>
														<td height="30" class="detail_label">
															角色级别：
														</td>
														<td class="detail_content">
															${entry.ROLE_LVL}
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															角色类别：
														</td>
														<td class="detail_content">
															${entry.ROLE_TYPE}
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															首页链接：
														</td>
														<td class="detail_content">
															${entry.INDEX_URL}
														</td>
													</tr> 
													<tr>
														<td height="30" class="detail_label">
															角色注释：
														</td>
														<td class="detail_content">
															${entry.ROLE_DESC}
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
										<input type="button" class="btn" value="返 回"
											onclick="history.back();">
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

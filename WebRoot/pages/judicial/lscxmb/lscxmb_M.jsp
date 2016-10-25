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
								当前位置：系统管理 &gt;&gt; 菜单管理 &gt;&gt; 修改菜单
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
						action="${ctx}/system/menu.shtml?action=modi"
						onsubmit="return doSave();">
						<input type="hidden" name="SM" value="${param.SM}">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									菜单信息
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
															菜单编号：
														</td>
														<td width="75%" class="detail_content"> 
															<input name="menuId" type="text" autocheck="true" required="true" label="菜单编号"
																pattern="string" maxlength="6" value="${entry.MENU_ID}">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															菜单名称：
														</td>
														<td class="detail_content">
															<input name="menuName" type="text" pattern="string" label="菜单名称"
																size="50" autocheck="true" maxlength="100" required="true" 
																value="${entry.MENU_NAME}">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															上级菜单：
														</td>
														<td class="detail_content"> 
															<input name="menuParId" type="text" pattern="string" required="true" label="上级菜单"
																autocheck="true" maxlength="6" value="${entry.MENU_PAR_ID}">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															排序编号：
														</td>
														<td class="detail_content">
															<input name="menuSort" type="text" pattern="int"
																label="排序编号" required="true" autocheck="true"
																maxlength="2" value="${entry.MENU_SORT}">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															菜单链接：
														</td>
														<td class="detail_content">
															<input name="menuUrl" type="text" size="50" label="菜单链接"
																value="${entry.MENU_URL}" pattern="string"
																maxlength="100">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															菜单图片：
														</td>
														<td class="detail_content">
															<input name="menuImg" type="text" size="50" label="菜单图片"
																value="${entry.MENU_IMG}" pattern="string"
																maxlength="100">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															菜单注释：
														</td>
														<td class="detail_content">
															<input name="menuDesc" type="text" size="50" label="菜单注释"
																value="${entry.MENU_DESC}" pattern="string"
																maxlength="100">
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

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
								当前位置：系统管理 &gt;&gt; 用户管理 &gt;&gt; 增加用户
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
						action="${ctx}/system/user.shtml?action=add"
						onsubmit="return doSave();">
						<input type="hidden" name="SM" value="${param.SM}">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									用户信息
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
															用户编号：
														</td>
														<td width="75%" class="detail_content">
															<input name="userId" type="text" autocheck="true" label="用户编号"
																pattern="string" maxlength="20" required="true">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															用户姓名：
														</td>
														<td class="detail_content">
															<input name="userName" type="text" pattern="string"
																size="50" autocheck="true" maxlength="60" label="用户姓名"
																required="true">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															所属机构：
														</td>
														<td class="detail_content">
															<input name="orgParName" type="text" readonly 
																onclick="select_orgPar(this)" style="width:250px;" 
																value=""><input type="button" 
																class="btnSelect" onclick="select_orgPar(this)" value="..">
															<input name="orgId" type="hidden" value="${param.orgId }">
														</td>
													</tr>
													<tr style="display:none">
														<td height="30" align="right" class="detail_label">
															报表权限：
														</td>
														<td class="detail_content">
															<select name="rptRight" style="width:150px;">
																<option value="0">0-角色报表权限</option>
																<option value="1">1-用户报表权限</option>
															</select>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															用户职位：
														</td>
														<td class="detail_content">
															<input name="userPos" type="text" pattern="string" label="用户职位"
																size="50" autocheck="true" maxlength="20">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															电子邮箱：
														</td>
														<td class="detail_content">
															<input name="eMail" type="text" pattern="string" label="电子邮箱"
																size="50" autocheck="true" maxlength="100">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															电话号码：
														</td>
														<td class="detail_content">
															<input name="phone" type="text" pattern="string" label="电话号码"
																size="50" autocheck="true" maxlength="100">
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
		function select_orgPar(btn){			
			var nameTarget=form.orgParName;
			var srcTarget=form.orgId;
			var sUrl="${ctx}/system/organ.shtml?action=seletor";
			selectOrgan(btn,nameTarget,srcTarget,false,sUrl,'${ctx}');	
		} 
	</script>
</html>

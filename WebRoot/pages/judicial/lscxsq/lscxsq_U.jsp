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
		<title>历史查询申请</title>
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
								当前位置：司法查询 &gt;&gt; 司法查询申请 &gt;&gt; 司法查询申请修改
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
						action="${ctx}/judicial/ddbbzx.shtml?action=toQuery"
						onsubmit="return doSave();">
						<input type="hidden" name="SM" value="${param.SM}">
						<input type="hidden" name="REQ_NO" value="${rst.REQ_NO}">
						<input type="hidden" name="REPMOD_NO" value="${rst.REPMOD_NO}">
						<input type="hidden" name="CONS_PAR" value="${rst.CONS_PAR}">
						<input type="hidden" name="MKLX" value="XGSQ">
						<input type="hidden" name="usrId" value='${usrId}'>
		                <input type="hidden" name="orgNo" value='${orgNo}'>
						
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									司法查询申请信息
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
														<td width="50%" height="25" align="right"
															class="detail_label">
															查询申请编号：
														</td>
														<td width="50%" class="detail_content">
															${rst.REQ_NO}
														</td>
													</tr>
													<!--  
													<tr>
														<td width="50%" height="25" align="right"
															class="detail_label">
															查询模板编号：
														</td>
														<td width="50%" class="detail_content">
															<input name="REPMOD_NO" type="text" autocheck="true" label="查询模板编号"
																size="30" pattern="string" value="${rst.REPMOD_NO}" maxlength="20" required="true">
														</td>
													</tr>
													<tr>
														<td width="50%" height="25" align="right"
															class="detail_label">
															交易编号：
														</td>
														<td width="50%" class="detail_content">
															<input name="REP_NO" type="text" autocheck="true" label="交易编号"
																size="30" pattern="string" maxlength="20" value="${rst.REP_NO}" required="true">
														</td>
													</tr>
													<tr>
														<td height="50" align="right" class="detail_label">
															交易名称：
														</td>
														<td class="detail_content">
															<input name="REP_NAME" type="text" pattern="string"
																size="30" autocheck="true" maxlength="60" label="交易名称" value="${rst.REP_NAME}"
																required="true">
														</td>
													</tr>
													-->
														<tr>
														<td width="50%" height="25" align="right"
															class="detail_label">
															查询模板编号：
														</td>
														<td width="50%" class="detail_content">
															${rst.REPMOD_NO}
														</td>
													</tr>
													<tr>
														<td width="50%" height="25" align="right"
															class="detail_label">
															交易编号：
														</td>
														<td width="50%" class="detail_content">
															${rst.REP_NO}
														</td>
													</tr>
													<tr>
														<td height="50" align="right" class="detail_label">
															交易名称：
														</td>
														<td class="detail_content">
															${rst.REP_NAME}
														</td>
													
													<tr>
														<td height="50" align="right" class="detail_label">
															描述：
														</td>
														<td class="detail_content"><textarea name="REP_DESC" type="text" pattern="string" label="描述" cols="25"  rows="4"  maxlength="200"  >${rst.REP_DESC}</textarea>
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
									<input type="submit" class="btn" value="下一步">
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

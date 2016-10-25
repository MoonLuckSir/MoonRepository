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
								当前位置：司法查控&gt;&gt; 导出模板 &gt;&gt; 修改导出模板
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
						action="${ctx}/judicial/sfck.shtml?action=updateDcmb"
						onsubmit="return doSave();">
						<input type="hidden" name="SM" value="${param.SM}">
						<input type="hidden" name="CXMB_ID" value='${param.CXMB_ID}'>
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									修改导出模板
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
															模板编号：
														</td>
														<td width="75%" class="detail_content"> 
															<input readonly="readonly" class="readonly" name="DCMB_ID" type="text" autocheck="true"  label="模板编号"
																pattern="string"  value="${res.DCMB_ID}">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															模板名称：
														</td>
														<td class="detail_content">
															<input name="DCMB_NAME" type="text" pattern="string" label="模板名称"
																 autocheck="true" maxlength="100" required="true" maxlength="50"
																value="${res.DCMB_NAME}">
														</td>
													</tr>
													<tr>
														<td width="25%" height="30" class="detail_label">
															模板对应表：
														</td>
														<td width="75%" class="detail_content"> 
															<input readonly="readonly" class="readonly" name="DCMB_TABLE" type="text" autocheck="true" required="true" label="对应表"
																pattern="string" maxlength="20" value="${res.DCMB_TABLE}">
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															所属查询模板：
														</td>
														<td class="detail_content">
															<input readonly="readonly" class="readonly" name="CXMB_NAME" type="text" pattern="string" label="所属查询模板"
																 autocheck="true" 
																value="${res.CXMB_NAME}">
														</td>
													</tr>
													
													<tr>
														<td height="30" align="right" class="detail_label">
															描述：
														</td>
														<td class="detail_content">
															<textarea rows="5" cols="30" autocheck="true"  name="DCMB_DESC" pattern="string" label="备注">${res.DCMB_DESC}</textarea>
															
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

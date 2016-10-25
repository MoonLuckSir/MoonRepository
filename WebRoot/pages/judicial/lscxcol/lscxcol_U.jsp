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
		<title>条件定制录入</title>
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
								当前位置：报表查询 &gt;&gt; 司法查询定制设计 &gt;&gt; 司法查询模板 &gt;&gt; 条件定制修改
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
						action="${ctx}/judicial/lscxcol.shtml?action=update"
						onsubmit="return doSave();">
						<input type="hidden" name="SM" value="${param.SM}">
						<input type="hidden" name="COLMX_NO" value="${rst.COLMX_NO}">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									司法查询模板信息
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
														<td width="50%" height="30" align="right"
															class="detail_label">
															模板编号：
														</td>
														<td width="50%" class="detail_content">
															${rst.REPMOD_NO }
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															交易名称：
														</td>
														<td class="detail_content">
															${rst.REP_NAME }
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															列名称：
														</td>
														<td class="detail_content">
															<input name="COLMX_NAME" type="text" pattern="string" label="列名称"
															 autocheck="true"  value="${rst.COLMX_NAME }">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															物理名：
														</td>
														<td class="detail_content">
															<input name="PHYSICAL_NAME" type="text" pattern="string" label="物理名"
															 autocheck="true"  value="${rst.PHYSICAL_NAME }">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															宽度：
														</td>
														<td class="detail_content">
															<input name="WIDTH" type="text" pattern="string" label="宽度"
																 autocheck="true" value="${rst.WIDTH }">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															高度：
														</td>
														<td class="detail_content">
															<input name="HEIGHT" type="text" pattern="string" label="高度"
																 autocheck="true" value="${rst.HEIGHT }">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															对齐：
														</td>
														<td class="detail_content">
															 <select name="ALIGN" style="width:155px" >
																<option value="left" <c:if test="${rst.ALIGN eq 'left' }">selected</c:if>>left</option>
																<option value="right" <c:if test="${rst.ALIGN eq 'right' }">selected</c:if>>right</option>
																<option value="center" <c:if test="${rst.ALIGN eq 'center' }">selected</c:if>>center</option>
																
															</select>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															是否显示：
														</td>
														<td class="detail_content">
															<select name="ISDISPLAY" style="width:155px" >
																<option value="是" <c:if test="${rst.ISDISPLAY eq '是' }">selected</c:if>>是</option>
																<option value="否" <c:if test="${rst.ISDISPLAY eq '否' }">selected</c:if>>否</option>
															</select>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															序号：
														</td>
														<td class="detail_content">
															<input name="ORDERXH" type="text" pattern="string" label="序号"
															 autocheck="true"  value="${rst.ORDERXH }">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															描述：
														</td>
														<td class="detail_content">
															<input name="COLMX_DESC" type="text" pattern="string" label="描述"
															 autocheck="true"  value="${rst.COLMX_DESC }">
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

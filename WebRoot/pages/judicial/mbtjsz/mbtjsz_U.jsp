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
						action="${ctx}/judicial/mbtjsz.shtml?action=update"
						onsubmit="return doSave();">
						<input type="hidden" name="SM" value="${param.SM}">
						<input type="hidden" name="CONMX_NO" value="${rst.CONMX_NO}">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									条件定制修改
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
															条件名称：
														</td>
														<td width="50%" class="detail_content">
															<input name="CONMX_NAME" type="text" autocheck="true" label="条件名称"
																pattern="string" required="true" value="${rst.CONMX_NAME }">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															数据类型：
														</td>
														<td class="detail_content">
															<select name="CONMX_TYPE" style="width:155px" >
																<option value="字符串" <c:if test="${rst.CONMX_TYPE eq '字符串' }">selected</c:if>>字符串</option>
																<option value="日期" <c:if test="${rst.CONMX_TYPE eq '日期' }">selected</c:if>>日期</option>
																<option value="数字" <c:if test="${rst.CONMX_TYPE eq '数字' }">selected</c:if>>数字</option>
																<option value="文本" <c:if test="${rst.CONMX_TYPE eq '文本' }">selected</c:if>>文本</option>															
															</select>	
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															输入框类型：
														</td>
														<td class="detail_content">
															<select name="INPUT_TYPE" style="width:155px" >
																<option value="输入框" <c:if test="${rst.INPUT_TYPE eq '输入框' }">selected</c:if>>输入框</option>
																<option value="下拉框组件" <c:if test="${rst.INPUT_TYPE eq '下拉框组件' }">selected</c:if>>下拉框组件</option>
																<option value="选择组件" <c:if test="${rst.INPUT_TYPE eq '选择组件' }">selected</c:if>>选择组件</option>
																<option value="回显框" <c:if test="${rst.INPUT_TYPE eq '回显框' }">selected</c:if>>回显框</option>
															    <option value="隐藏组件" <c:if test="${rst.INPUT_TYPE eq '隐藏组件' }">selected</c:if>>隐藏组件</option>
															    <option value="文本框" <c:if test="${rst.INPUT_TYPE eq '文本框' }">selected</c:if>>文本框</option>
															</select>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															组件名称：
														</td>
														<td class="detail_content">
															<select name="INPUT_VALUE" style="width:155px" >
																<option value="" <c:if test="${rst.INPUT_VALUE eq '' }">selected</c:if>></option>
																<option value="日期" <c:if test="${rst.INPUT_VALUE eq '日期' }">selected</c:if>>日期</option>
															    <option value="机构" <c:if test="${rst.INPUT_VALUE eq '机构' }">selected</c:if>>机构</option>
															    <option value="总账类型" <c:if test="${rst.INPUT_VALUE eq '总账类型' }">selected</c:if>>总账类型</option>
															</select>
														</td>
													</tr>
													
													<tr>
														<td height="30" align="right" class="detail_label">
															组件涉及列：
														</td>
														<td class="detail_content">
															<input name="ZJCONS" type="text" pattern="string" label="组件涉及列"
																 autocheck="true" value="${rst.ZJCONS }">
														</td>
													</tr>
													
													<tr>
														<td height="30" align="right" class="detail_label">
															物理名：
														</td>
														<td class="detail_content">
															<input name="PHYSICAL_NAME" type="text" pattern="string" label="物理名"
																  value="${rst.PHYSICAL_NAME }">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															默认值：
														</td>
														<td class="detail_content">
															<input name="DEFAULTVAL" type="text" pattern="string" label="默认值"
																 autocheck="true" value="${rst.DEFAULTVAL }">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															运算符：
														</td>
														<td class="detail_content">
															<select name="OPERATOR" style="width:155px" >
																<option value="=" <c:if test="${rst.OPERATOR eq '=' }">selected</c:if>>=</option>
																<option value="&lt;&gt;" <c:if test="${rst.OPERATOR eq '<>' }">selected</c:if>>&lt;&gt;</option>
																<option value="&gt;" <c:if test="${rst.OPERATOR eq '>' }">selected</c:if>>&gt;</option>
																<option value="&lt;" <c:if test="${rst.OPERATOR eq '<' }">selected</c:if>>&lt;</option>
																<option value="&gt;=" <c:if test="${rst.OPERATOR eq '>=' }">selected</c:if>>&gt;=</option>
																<option value="&lt;=" <c:if test="${rst.OPERATOR eq '<=' }">selected</c:if>>&lt;=</option>
																<option value="LIKE" <c:if test="${rst.OPERATOR eq 'LIKE' }">selected</c:if>>LIKE</option>
																<option value="LIKEL" <c:if test="${rst.OPERATOR eq 'LIKEL' }">selected</c:if>>LIKEL</option>
																<option value="LIKER" <c:if test="${rst.OPERATOR eq 'LIKER' }">selected</c:if>>LIKER</option>
															</select>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															排序号：
														</td>
														<td class="detail_content">
															<input name="ORDERXH" type="text" pattern="string" label="排序号"
																 autocheck="true"  value="${rst.ORDERXH }">
														</td>
													</tr>
													
													
													<tr>
														<td height="30" align="right" class="detail_label">
															是否拼接条件：
														</td>
														<td class="detail_content">
															<select name="ISJOIN" style="width:155px" >
																<option value="是" <c:if test="${rst.ISJOIN eq '是' }">selected</c:if>>是</option>
																<option value="否" <c:if test="${rst.ISJOIN eq '否' }">selected</c:if>>否</option>
															</select>
														</td>
													</tr>
													
													<tr>
														<td height="30" align="right" class="detail_label">
															是否有分行符：
														</td>
														<td class="detail_content">
															<select name="ISFGF" style="width:155px" >
															    <option value="否" <c:if test="${rst.ISFGF eq '否' }">selected</c:if>>否</option>
																<option value="是" <c:if test="${rst.ISFGF eq '是' }">selected</c:if>>是</option>
																
															</select>
														</td>
													</tr>
													
													<tr>
														<td height="30" align="right" class="detail_label">
															描述：
														</td>
														<td class="detail_content">
															<input name="CONMX_DESC" type="text" pattern="string" label="描述"
															 autocheck="true"  value="${rst.CONMX_DESC }">
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

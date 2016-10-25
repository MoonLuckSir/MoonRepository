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
		<title>增加模板</title>
	</head>
	<body>	
	<div class="lst_area">
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
								当前位置：司法查控&gt;&gt; 条件设置 &gt;&gt; 增加条件 
							</td>
							<td width="50" align="right">&nbsp;
								
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
						action="${ctx}/judicial/sfck.shtml?action=addCon"
						onsubmit="return doSave();">
						<input type="hidden" name="usrId" value='${usrId}'>
		                <input type="hidden" name="orgNo" value='${orgNo}'>
						<input type="hidden" name="SM" value="${param.SM}">
						<input type="hidden" name="CXMB_ID" value='${param.CXMB_ID}'>
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									新增模板条件信息
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
															<input name="CONDITION_NAME" type="text" autocheck="true" label="条件名称" maxlength="50"
																pattern="string"  required="true">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															物理名称：
														</td>
														<td class="detail_content">
															<input name="PHYSICAL_NAME" type="text" pattern="string" maxlength="20"
															    autocheck="true" label="物理名称" required="true"><font color="red">(表示该条件在数据库表中的字段名)</font>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															输入框类型：
														</td>
														<td class="detail_content">
															<select name="INPUT_TYPE">
																<option value="1">输入框</option>
																<option value="2">下拉列表框</option>
																<option value="3">日期选择组件</option>
															</select>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															数据类型：
														</td>
														<td class="detail_content">
															<select name="VALUE_TYPE">
																<option value="1">字符串</option>
																<option value="2">数字</option>
																<option value="3">日期</option>
															</select>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															默认值：
														</td>
														<td class="detail_content">
															<input name="VALUE_DEF" type="text" pattern="string"
															    autocheck="true" label="默认值" >&nbsp;<font color="red">(输入DATE代表当前日期，输入ORG代表当前机构，输入USER代表当前用户)</font>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															描述：
														</td>
														<td class="detail_content">
															<textarea rows="5" cols="30" autocheck="true"  name="CONDITION_DESC" pattern="string" label="描述"></textarea>
															
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
		</div>
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
		
		function changeDEPT(obj){
		    if(obj.value == '2'){
				document.getElementById("trid").style.display='';
			}else{
				document.getElementById("trid").style.display='none';
			}
		}
		
		function select_orgPar(btn){			
			var nameTarget=form.orgParName;
			var srcTarget=form.orgId;
			var sUrl="${ctx}/judicial/lscxmb.shtml?action=seletor";
			selectOrgan(btn,nameTarget,srcTarget,false,sUrl,'${ctx}');	
		} 
	</script>
</html>

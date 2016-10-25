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
								当前位置：报表查询 &gt;&gt; 司法查询定制设计 &gt;&gt; 新增司法查询模板
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
						action="${ctx}/judicial/lscxmb.shtml?action=add"
						onsubmit="return doSave();">
						<input type="hidden" name="usrId" value='${usrId}'>
		                <input type="hidden" name="orgNo" value='${orgNo}'>
						<input type="hidden" name="SM" value="${param.SM}">
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
															交易编号：
														</td>
														<td width="50%" class="detail_content">
															<input name="REP_NO" type="text" autocheck="true" label="交易编号"
																pattern="string"  required="true">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															交易名称：
														</td>
														<td class="detail_content">
															<input name="REP_NAME" type="text" pattern="string"
															    autocheck="true" label="交易名称"
																required="true">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															SELECT语句：
														</td>
														<td class="detail_content">
															<textarea rows="4" name="SELECT_SQL" class="textArea" style="width: 530px" autocheck="true" 
															label="SELECT语句" pattern="string" required="true" maxlength="200"  id="SELECT_SQL"></textarea>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															FROM语句：
														</td>
														<td class="detail_content">
															<textarea rows="6" name="FROM_SQL" class="textArea" style="width: 530px" label="FROM语句" 
															 required="true" autocheck="true"  pattern="string" maxlength="300"  id="FROM_SQL"></textarea>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															WHERE语句：
														</td>
														<td class="detail_content">
															<textarea rows="4" name="WHERE_SQL" class="textArea" style="width: 530px" 
															label="WHERE语句" pattern="string"  maxlength="100"  id="WHERE_SQL"></textarea>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															ORDER语句：
														</td>
														<td class="detail_content">
															<textarea rows="2" name="ORDER_SQL" class="textArea" style="width: 530px" label="ORDER语句" 
															required="true" autocheck="true" pattern="string"  maxlength="100"  id="ORDER_SQL"></textarea>
														</td>
													</tr>
													
													<tr>
														<td height="30" align="right" class="detail_label">
															状态控制：
														</td>
														<td height="30" align="left" class="detail_content">
															<select name="FLAGSTATE" id="FLAGSTATE" style="width: 145px" >
																<option value="1">司法查询列表</option>
															</select>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															数据源：
														</td>
														<td class="detail_content">
														   <select name="resource" style="width:155px" >
																<option value="His" >数据平台数据源</option>
																<option value="FinPro" >历史平台数据源</option>
															</select>
														</td>
													</tr>	
													<tr style='display:none' >
														<td height="30" align="right" class="detail_label">
															是否跨机构：
														</td>
														<td class="detail_content">
														   <select name="ISKORG" style="width:155px" >
														        <option value="否" <c:if test="${rst.ISKORG eq '否' }">selected</c:if>>否</option>
																<option value="是" <c:if test="${rst.ISKORG eq '是' }">selected</c:if>>是</option>
																
															</select>
														</td>
													</tr>
													<tr  style='display:none'>
														<td height="30" align="right" class="detail_label">
															是否需要审核：
														</td>
														<td class="detail_content">
														   <select name="ISSH" style="width:155px" >
														        <option value="否" <c:if test="${rst.ISSH eq '否' }">selected</c:if>>否</option>
																<option value="是" <c:if test="${rst.ISSH eq '是' }">selected</c:if>>是</option>
																
															</select>
														</td>
													</tr>
													<tr style='display:none' >
													 <td height="30" align="right" class="detail_label">
															查询营业机构SQL：
														</td>
														<td class="detail_content" >
															<input name="" type="text" pattern="string" label="查询机构SQL" size='70'
																  >
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															操作类型：
														</td>
														<td class="detail_content" >
															<select name="ORGSQL" style="width:155px" onchange="changeDEPT(this)">
																<option value="1" <c:if test="${rst.ORGSQL eq '1' }">selected</c:if>>无</option>
																<option value="2" <c:if test="${rst.ORGSQL eq '2' }">selected</c:if>>跳转</option>
																<option value="3" <c:if test="${rst.ORGSQL eq '3' }">selected</c:if>>打印余额</option>
																<option value="4" <c:if test="${rst.ORGSQL eq '4' }">selected</c:if>>打印通知单</option>
															</select>
														</td>
													</tr>
													<tr id ="trid"  style="display:none" >
													    <td height="30" align="right" class="detail_label">
															跳转模板编号：
														</td>
														<td class="detail_content" >
															<input name="CHECKIN_DEPT" type="text" pattern="string" label="跳转模板编号" size='70' >
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															描述：
														</td>
														<td class="detail_content">
															<input name="REP_DESC" type="text" pattern="string" label="备注"  size='70'
																 autocheck="true" >
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

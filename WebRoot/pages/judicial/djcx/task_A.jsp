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
		<title>增加任务</title>
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
								当前位置：司法查控&gt;&gt; 登记查询 &gt;&gt; 新增查询任务
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
					<form name="form" method="post" enctype="multipart/form-data"
						action="${ctx}/judicial/djcx.shtml?action=addTask"
						onsubmit="return doSave();">
						<input type="hidden" name="usrId" value='${usrId}'>
		                <input type="hidden" name="orgNo" value='${orgNo}'>
		                <input type="hidden" name="_backUrl" value='${param._backUrl}'>
						<input type="hidden" name="SM" value="${param.SM}">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									新增查询任务
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
													<input name="QRY_DEPT" type="hidden" pattern="string"  value="JC"
															    autocheck="true" label="查询部门" />
													<!-- <tr>
														<td width="50%" height="30" align="right"
															class="detail_label">
															任务名称：
														</td>
														<td width="50%" class="detail_content">
															<input name="TASK_NAME" type="text" autocheck="true" label="任务名称"
																pattern="string"  required="true" maxlength="50">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															查询部门：
														</td>
														<td class="detail_content">
															<input name="QRY_DEPT" type="text" pattern="string"
															    autocheck="true" label="查询部门" maxlength="10">
														</td>
													</tr>
													 
													<tr>
														<td height="30" align="right" class="detail_label">
															查询模板：
														</td>
														<td class="detail_content">
															<select name="CXMB_ID" >
																<option value="">---请选择---</option>
																<c:forEach items="${cxmbList}" var="rst">
																	<option value="${rst.CXMB_ID }">${rst.CXMB_NAME }</option>
																</c:forEach>
															</select><span style="color: red">*</span>
														</td>
													</tr> 
													-->
													 <tr>
														<td height="30" align="right" class="detail_label">
															导入\导出格式：
														</td>
														<td class="detail_content">
															<select name="EXP_FORMAT" >
																<option value="Excel">Excel</option>
																<!-- <option value="">---请选择---</option>
																<option value="Xml">Xml</option>-->
															</select>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															登记方式：
														</td>
														<td class="detail_content">
															<select name="REG_WAY">
																<option value="1">文件导入</option>
																<!--  <option value="2">手工录入</option>-->
															</select>
														</td>
													</tr>
													
													
													<tr>
														<td width="25%" height="30" class="detail_label">
															文件名：
														</td>
														<td width="75%" class="detail_content">
															&nbsp;
															<input  name="filename" type="text"
																readonly="true" class="readonly" style="width: 180px"
																pattern="string" required="true" autocheck="true"
																label="文件名" />
														</td>
													</tr>
		
													<tr>
														<td height="30" class="detail_label">
															选择需要导入的文件：
														</td>
														<td class="detail_content">
															&nbsp;
															<input id="file" name="file" type="file" value="浏览..."
																onChange="fillname();" style="width: 250px"
																pattern="string" required="true" autocheck="true"
																label="导入的文件" />
															&nbsp;&nbsp;
															 <a style="color: blue;"  href="${ctx}/judicial/djcx.shtml?action=downLoadFile" onclick="download();">下载模板</a>
														</td>
													</tr>
													<tr>
														<td height="30" class="detail_label">
															附加文件：
														</td>
														<td class="detail_content">
															&nbsp;
															<input id="extraFile" name="extraFile" type="file" value="浏览..."
																onChange="fillname();" style="width: 250px"
																label="附加文件" />
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
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" class="btn" value="重 置">
									&nbsp;&nbsp;
									<c:if test="${not empty param._backUrl}">&nbsp;&nbsp;
										<input type="button" class="btn" value="返 回"
											onclick="goback();">
									</c:if>
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
		
		<form method="post" name="detailForm">
			
			${paramCover.coveredInputs}
		</form>
		</div>
	</body>
	<script type="text/javascript" src="${ctx}/scripts/util/calendar.js"></script>
	<%@ include file="/commons/jslibs.jsp"%>
	<%@ include file="/commons/jslibs4tree.jsp"%>
	<script type="text/javascript">InitFormValidator(0);</script>
	<script type="text/javascript">
		function goback(){
			detailForm.action = "${ctx}/judicial/djcx.shtml?action=toTaskList";
			detailForm.submit();
		}
	
		function changeFmt(){
			if(form.EXP_FORMAT.value == "Excel"){
				form.action = "${ctx}/judicial/djcx.shtml?action=addTaskByExcel";
			}
			if(form.EXP_FORMAT.value == "Xml"){
				form.action = "${ctx}/judicial/djcx.shtml?action=addTaskByExcel";
			}
			
		}

		function doSave(){
			if(FormValidate(0)){
				if(form.EXP_FORMAT.value == ""){
					alert("请选择导入\导出格式!");
					return false;
				}
				showWaitPanel('');
				setTimeout("form.submit();",100);
			}
			return false;
		}
		
		function fillname(){
			var file = form.file.value;
			var path = file.split("\\");
			var names = path[path.length-1];
			//var name = names.split(".");
			//var filename = names[0];
			form.filename.value = names;
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

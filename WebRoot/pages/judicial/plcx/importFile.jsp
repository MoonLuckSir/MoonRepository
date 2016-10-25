<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/${theme}/css/style.css">
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/${theme}/css/tools.css">
		<%@ include file="/commons/jslibs.jsp"%>
		<script type="text/javascript" src="${ctx}/scripts/util/calendar.js"></script>
		<title>导入查询条件</title>
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
								当前位置：司法查询 &gt;&gt; 批量查询 &gt;&gt; 导入查询条件
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
					<form name="form" method="post" enctype="multipart/form-data"
						action="${ctx}/judicial/plcx.shtml?action=uploadMB"
						onsubmit="return doSave();">
						<input type="hidden" name="SM" value="${param.SM}">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									导入查询条件
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
															文件名：
														</td>
														<td width="75%" class="detail_content">
															&nbsp;
															<input id="filename" name="filename" type="text"
																readonly="true" class="readonly" style="width: 180px"
																pattern="string" required="true" autocheck="true"
																label="文件名" />
														</td>
													</tr>

													<tr>
														<td height="30" class="detail_label">
															批量查询交易名称：
														</td>
														<td class="detail_content">
															&nbsp;
															<select name="batchType" id="batchType"
																style="width: 180px" onchange="select(this)">
																<c:forEach items="${batchTypes}" var="bt">
																	<option value="${bt.BATCH_TYPE}">
																		${bt.BATCH_TYPE_NAME}
																	</option>
																</c:forEach>
															</select>
															<span style="color: red">*</span>
														</td>
													</tr>
													
													<tr>
														<td height="30" class="detail_label">
															是否需要日期查询条件：
														</td>
														<td class="detail_content">
															&nbsp;
															<input type="text" name="dateQueryFlag" id="dateQueryFlag"style="width: 180px" readonly class="readonly"/>
															<span style="color: red">*</span>
														</td>
													</tr>
												<!-- 	<tr>
														<td height="30" class="detail_label">
															是否立即生成：
														</td>
														<td class="detail_content">
															&nbsp;
															<select name="flag" id="flag" style="width: 180px">
																<option value="0">
																	否
																</option>
																<option value="1">
																	是
																</option>
															</select>
															<span style="color: red">*</span>
														</td>
													</tr> -->
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
															<a href="#" onclick="download();">下载模板</a>
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
									<input type="submit" class="btn" value="上 传">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" class="btn" onClick="Reset()" value="重 置">
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
		<form method="post" name="detailForm">
			<input type="hidden" name="_backUrl"
				value="${ctx}/judicial/plcx.shtml?action=getNeedQueryDate">
			<input type="hidden" name="batchType">
			${paramCover.coveredInputs}
		</form>
		<input type="hidden" name="tmpId">
		<input type="hidden" name="tmpStus">
	</body>
	<%@ include file="/commons/jslibs.jsp"%>
	<script type="text/javascript">InitFormValidator(0);</script>
	<script type="text/javascript">
		
		var tmpId = g("tmpId");
		var tmpStus = g("tmpStus");
		var dateQueryFlag = "${dateQueryFlag}";	
		function todo(act,batchType){
			showWaitPanel('');
			detailForm.action=act;
			detailForm.batchType.value=batchType;
			setTimeout("detailForm.submit()",100);	
			try{ tmpId.parentNode.focus(); }catch(e){}
		}
		
		function select(tt){
			var batchType = tt.value;
			var sUrl="${ctx}/judicial/plcx.shtml?action=getNeedQueryDate&batchType="+batchType;
		    var request = YAHOO.util.Connect.asyncRequest('POST', sUrl, {
	        	success:function(result){      
	            	var oResults = eval("(" + result.responseText + ")");
	            	form.dateQueryFlag.value = oResults.name;
				}
		   	});
		}
	
		function fillname(){
			var file = form.file.value;
			var path = file.split("\\");
			var names = path[path.length-1];
			//var name = names.split(".");
			//var filename = names[0];
			form.filename.value = names;
		}
		function download(){
			form.action = "${ctx}/judicial/plcx.shtml?action=download";
			setTimeout("form.submit();",100);
			return false;
		}
		function doSave(){
			if(form.batchType.value==""){
				alert("请选择批量查询交易名称！");
				return false;
			}
			if(FormValidate(0)){
				form.action="${ctx}/judicial/plcx.shtml?action=uploadMB";
				setTimeout("form.submit();",100);
			}
			return false;
		}
		
		window.onload = function fun(){
			form.dateQueryFlag.value = dateQueryFlag;
		}
		
		function Reset(){
			form.reset();
			form.dateQueryFlag.value = dateQueryFlag;
		}
		
	</script>
</html>

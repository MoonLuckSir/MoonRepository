<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/tools.css">
		<%@ include file="/commons/jslibs.jsp"%>
		<title>司法查控业务统计</title>
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
								当前位置：司法查询&gt;&gt;  人民银行电信诈骗查控 &gt;&gt; 司法查控业务统计
							</td>
							<td width="50" align="right">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="rowSplit"></td>
			</tr>
			<tr style="padding-top: 150px">
				<td> 
				<form name="form" method="post" 
						action="${ctx}/judicial/zfdj.shtml?action=makeYwtjExcel"
						>
					<table width="100%" border="0" cellspacing="1">
						<tr>
							<td width="50%" valign="top">
								<table width="100%" border="0" cellpadding="4" cellspacing="4"
									class="detail">
									<tr>
										<td class="detail2">
											<table width="100%" border="0" cellpadding="1"
												cellspacing="1" class="detail3">
												<tr>
													<td width="40%" height="23" align="left"
														class="detail_label">
														统计开始日期：
													</td>
													<td width="60%" height="23" align="left"
														class="detail_content">
														 <input style="width:180px;" class="Wdate" type="text" id="d01" value="${param.ksr }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'d02\')}',vel:'ksr'})" readonly>
        							 					 <input name="ksr" id="ksr" type="hidden" value="${param.ksr }"/>&nbsp;
														 <span style="color: red;">*</span>
													</td>
												</tr>
												
											</table>
										</td>
									</tr>
								</table>
							</td>
							<td width="50%" valign="top">
								<table width="100%" border="0" cellpadding="4" cellspacing="4"
									class="detail">
									<tr>
										<td class="detail2">
											<table width="100%" border="0" cellpadding="1"
												cellspacing="1" class="detail3">
												<tr>
													<td height="23" width="40%" align="right"
													 			class="detail_label">
														统计结束日期：
													</td>
													<td height="23" width="60%" align="left"
													 			class="detail_content">
														<input style="width:180px;" class="Wdate" type="text" id="d02" value="${param.jsr }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'d01\')}',vel:'jsr'})" readonly>
     												    <input name="jsr" id="jsr" type="hidden"  value="${param.jsr }"/>
     												    <span style="color: red;">*</span>
													</td>
												</tr>
												
											</table>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					</form>
				</td>
			</tr>
			<tr style="padding-top: 30px">
				<td height="40" align="center" valign="middle" > 
					<input type="button" class="btn" value="生成统计表"
							onclick="makeExcel();"> 
				</td>
			</tr>
		</table>
		
		<script type="text/javascript">	
			function makeExcel(){
			
				var k = document.getElementById("ksr").value;
				var j = document.getElementById("jsr").value;
				if(k=null || k == ""){
					alert("开始日期不能为空");
					return;
				}
				if(j=null || j == ""){
					alert("结束日期不能为空");
					return;
				}
				
				form.submit();
			}
			function showErrorInfo(APPLICATIONID,errorInfo){
				var url = "${ctx}/judicial/jymx.shtml?action=toErrorInfo&APPLICATIONID="+APPLICATIONID+"&errorInfo="+errorInfo;
				showDialog(encodeURI(url), 800, 400);
			}
		</script>
	</body> 
	
	
</html>
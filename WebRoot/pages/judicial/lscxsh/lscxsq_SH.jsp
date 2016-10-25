<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/pages/judicial/selcon/selconpar.jsp"%>
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
							<td width="28" align="center">
								<label class="wz_img"></label>
							</td>
							<td>
								当前位置：司法查询 &gt;&gt; 司法查询申请 &gt;&gt; 司法查询申请
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
						action="${ctx}/judicial/ddbbzx.shtml?action=toQuery"
						onsubmit="return doSave();">
						<table width="100%" border="0" cellspacing="1">
						<input type="hidden" name="REQ_NO" value="${REQ_NO}">
			            <input type="hidden" name="usrId" value="${usrId}" >
			            <input type="hidden" name="orgNo"value="${orgNo}" >
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									填写意见
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
														<td height="100" align="right" class="detail_label">
														    意见：
														</td>
														<td class="detail_content"><textarea name="HIS_DESC"  pattern="string"  value='' label="意见" cols="40"  rows="3" ></textarea>
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
									<input type="button" class="btn" value="通 过" onClick="check();" >
									&nbsp;&nbsp;
									<input type="button" class="btn" value="回 退"  onclick="uncheck();" >
									&nbsp;&nbsp;
									<input type="button" class="btn" value="提 交"  onclick="commit();" >
									
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
		
		function todo(act){
			showWaitPanel('');
			form.action=act;
			setTimeout("form.submit()",100);
		}
		function check(){
			todo("${ctx}/judicial/lscxsh.shtml?action=check");
		}
		function uncheck(){
			todo("${ctx}/judicial/lscxsh.shtml?action=uncheck");
		}
		function commit(){
			todo("${ctx}/judicial/lscxsh.shtml?action=conmmit");
		}
		var msg="${msg}";
		if(msg!=''&&msg!=null)
		{
		 alert(msg);
		 opener.location.reload();
		 window.close();
		}
		
	</script>
	
</html>

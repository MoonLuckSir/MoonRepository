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
		<title>异常开卡可疑名单上报</title>
	</head>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="27">
					<table width="100%" height="100%" cellspacing="0" cellpadding="0" class="wz">
						<tr>
							<td width="28" align="center"><label class="wz_img"></label></td>
							<td>
								当前位置：司法查询&gt;&gt;  人民银行电信诈骗查控 &gt;&gt; 异常开卡可疑名单上报
							</td>
							<td width="50" align="right">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="rowSplit"></td>
			</tr>
			<tr style="padding-top: 80px">
				<td> 
				<form name="form" method="post" action="${ctx}/judicial/zfdj.shtml?action=sendCard">
					<input type="hidden" name="_backUrl" value="${ctx}/judicial/zfdj.shtml?action=toSendCard">
					<table width="60%" border="0" cellspacing="1" align="center">
						<tr>
							<td width="50%" valign="top" >
								<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
									<tr>
										<td class="detail2">
											<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
												<!-- <tr>
													<td width="40%" height="23" align="left" class="detail_label">
														传输报文流水号：
													</td>
													<td width="60%" height="23" align="left" class="detail_content">
														 <input style="width:250px;"  type="text" id="TRANSSERIALNUMBER" name="TRANSSERIALNUMBER">
														 <span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td height="23" width="40%" align="right" class="detail_label">
														业务申请编号：
													</td>
													<td height="23" width="60%" align="left" class="detail_content">
														<input style="width:250px;" type="text" id="APPLICATIONID" name="APPLICATIONID" autocheck="true" label="用户编号"
																pattern="string" maxlength="50" required="true">
     												    <span style="color: red;">*</span>
													</td>
												</tr> -->
												<tr>
													<td width="40%" height="23" align="left" class="detail_label">
														事件特征码：
													</td>
													<td width="60%" height="23" align="left" class="detail_content">
														<select id="featureCode" name="featureCode" style="width: 250px;">
															<option value=''>-请选择-</option>
															<option value='1001'>频繁开卡</option>
															<option value='1002'>累计开卡</option>
															<option value='0000'>取消上报</option>
														</select>
														<span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="40%" height="23" align="left" class="detail_label">
														证件类型：
													</td>
													<td width="60%" height="23" align="left" class="detail_content">
														<select id="IDType" name="IDType" style="width: 250px;">
															<option value=''>-请选择-</option>
															<option value='01'>身份证</option>
															<option value='02'>户口本</option>
															<option value='03'>军官证</option>
															<option value='04'>警官证</option>
															<option value='05'>士兵证</option>
															<option value='07'>护照</option>
															<option value='08'>港澳台同胞回乡证</option>
															<option value='09'>其它</option>
															
														</select>
														<span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="40%" height="23" align="left" class="detail_label">
														证件号：
													</td>
													<td width="60%" height="23" align="left" class="detail_content">
														 <input style="width:250px;"  type="text" id="IDNumber" name="IDNumber" maxlength="30">
														 <span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="40%" height="23" align="left" class="detail_label">
														姓名：
													</td>
													<td width="60%" height="23" align="left" class="detail_content">
														 <input style="width:250px;"  type="text" id="IDName" name="IDName" maxlength="90">
														 <span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="40%" height="23" align="left" class="detail_label">
														卡/折号：
													</td>
													<td width="60%" height="23" align="left" class="detail_content">
														 <input style="width:250px;"  type="text" id="cardNumber" name="cardNumber" maxlength="30">
														 <span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="40%" height="23" align="left" class="detail_label">
														开卡时间：
													</td>
													<td width="60%" height="23" align="left" class="detail_content">
														 <!-- <input style="width:250px;"  type="text" id="accountOpenTime" name="accountOpenTime"> -->
														 <input style="width:250px;" class="Wdate" type="text" id="accountOpenTime" name="accountOpenTime" value="${param.accountOpenTime }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
														 <span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="40%" height="23" align="left" class="detail_label">
														开卡地点：
													</td>
													<td width="60%" height="23" align="left" class="detail_content">
														 <input style="width:250px;"  type="text" id="accountOpenPlace" name="accountOpenPlace" maxlength="255">
														 <span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="40%" height="23" align="left" class="detail_label">
														经办人姓名：
													</td>
													<td width="60%" height="23" align="left" class="detail_content">
														 <input style="width:250px;"  type="text" id="operatorName" name="operatorName" maxlength="60">
													</td>
												</tr>
												<tr>
													<td width="40%" height="23" align="left" class="detail_label">
														经办人电话：
													</td>
													<td width="60%" height="23" align="left" class="detail_content">
														 <input style="width:250px;"  type="text" id="operatorPhoneNumber" name="operatorPhoneNumber" maxlength="30">
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
					<input type="button" class="btn" value="发送" onclick="sendCard();"> 
				</td>
			</tr>
		</table>
		
		<script type="text/javascript">	
			function sendCard(){
			
				var featureCode = document.getElementById("featureCode").value;//事件特征码
				var IDType = document.getElementById("IDType").value;//证件类型
				var IDNumber = document.getElementById("IDNumber").value;//证件号
				var IDName = document.getElementById("IDName").value;//姓名
				var cardNumber = document.getElementById("cardNumber").value;//卡/折号
				var accountOpenTime = document.getElementById("accountOpenTime").value;//开卡时间
				var accountOpenPlace = document.getElementById("accountOpenPlace").value;//开卡地点
				
				if(featureCode=null || featureCode == ""){
					WriteErrMessage(form.featureCode,"事件特征码不能为空！");
					return;
				}
				if(IDType=null || IDType == ""){
					WriteErrMessage(form.IDType,"证件类型不能为空！");
					return;
				}
				if(IDNumber=null || IDNumber == ""){
					WriteErrMessage(form.IDNumber,"证件号不能为空！");
					return;
				}
				if(IDName=null || IDName == ""){
					WriteErrMessage(form.IDName,"姓名不能为空！");
					return;
				}
				if(cardNumber=null || cardNumber == ""){
					WriteErrMessage(form.cardNumber,"卡/折号不能为空！");
					return;
				}
				if(accountOpenTime=null || accountOpenTime == ""){
					WriteErrMessage(form.accountOpenTime,"开卡时间不能为空！");
					return;
				}
				if(accountOpenPlace=null || accountOpenPlace == ""){
					WriteErrMessage(form.accountOpenPlace,"开卡地点不能为空！");
					return;
				}
				
				
				form.submit();
			}
			
		</script>
	</body> 
	
	
</html>
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
		<title>案件举报</title>
	</head>
	<body>
		<div style="overflow: auto;width: 100%;height: 100%;padding: 0px;">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="27">
					<table width="100%" height="100%" cellspacing="0" cellpadding="0" class="wz">
						<tr>
							<td width="28" align="center"><label class="wz_img"></label></td>
							<td>
								当前位置：司法查询&gt;&gt;  人民银行电信诈骗查控 &gt;&gt; 案件举报
							</td>
							<td width="50" align="right">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="rowSplit"></td>
			</tr>
			<tr style="padding-top: 20px">
				<td> 
				<form name="form" method="post" action="${ctx}/judicial/zfdj.shtml?action=ajjb">
					<input type="hidden" name="_backUrl" value="${ctx}/judicial/zfdj.shtml?action=toAjjb">
					<table width="80%" border="0" cellspacing="1" align="center">
						<tr>
							<td width="50%" valign="top" >
								<table width="100%" border="0" cellpadding="4" cellspacing="4" class="detail">
									<tr>
										<td class="detail2">
											<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														案件举报类型：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<select id="APPLICATIONTYPE" name="APPLICATIONTYPE" style="width: 250px;">
															<option value=''>-请选择-</option>
															<option value='01'>报案</option>
															<option value='02'>止付后补流程</option>
														</select>
														<span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														是否主动发送初始节点：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<select id="ISINITIALNODE" name="ISINITIALNODE" style="width: 250px;">
															<option value=''>-请选择-</option>
															<option value='01'>是</option>
															<option value='02'>否</option>
														</select>
														<span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														上报时间：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;" class="Wdate" type="text" id="REPORTENDTIME" name="REPORTENDTIME" value="${param.REPORTENDTIME }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
														<span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														受害人姓名：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="VICTIMNAME" name="VICTIMNAME" maxlength="90">
														<span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														受害人联系电话：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="VICTIMPHONENUMBER" name="VICTIMPHONENUMBER" maxlength="30">
														<span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														证件类型：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<select id="VICTIMIDTYPE" name="VICTIMIDTYPE" style="width: 250px;">
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
													<td width="20%" height="23" align="left" class="detail_label">
														证件号：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="VICTIMIDNUMBER" name="VICTIMIDNUMBER" maxlength="30">
														<span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														事件描述：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="ACCIDENTDESCRIPTION" name="ACCIDENTDESCRIPTION" maxlength="450">
														<span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														上报机构名称：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="REPORTORGNAME" name="REPORTORGNAME" maxlength="225">
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														经办人姓名：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="OPERATORNAME" name="OPERATORNAME" maxlength="60">
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														经办人电话：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="OPERATORPHONENUMBER" name="OPERATORPHONENUMBER" maxlength="30">
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														交易流水号：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="ID" name="ID" maxlength="64">
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														交易类型：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="TYPE" name="TYPE" maxlength="20">
														<span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														交易时间：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;" class="Wdate" type="text" id="TIME" name="TIME" value="${param.TIME }" onClick="WdatePicker({dateFmt:'yyyyMMddHHmmss'})" readonly>
														<span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														币种：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<select id="CURRENCY" name="CURRENCY" style="width: 250px;">
															<option value=''>-请选择-</option>
															<option value='CNY'>人民币</option>
															<option value='USD'>美元</option>
															<option value='EUR'>欧元</option>
														</select>
														<span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														交易金额：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="TRANSFERAMOUNT" name="TRANSFERAMOUNT" maxlength="25">
														<span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														转出账户所属银行机构编码：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="TRANSFEROUTBANKID" name="TRANSFEROUTBANKID" maxlength="12">
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														转出账户所属银行名称：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="TRANSFEROUTBANKNAME" name="TRANSFEROUTBANKNAME" maxlength="225">
														<span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														转出账户名：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="TRANSFEROUTACCOUNTNAME" name="TRANSFEROUTACCOUNTNAME" maxlength="100">
														<span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														转出卡/折号：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="TRANSFEROUTACCOUNTNUMBER" name="TRANSFEROUTACCOUNTNUMBER" maxlength="30">
														<span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														转入账户所属银行机构编码：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="TRANSFERINBANKID" name="TRANSFERINBANKID" maxlength="30">
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														转入账户账户所属银行名称：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="TRANSFERINBANKNAME" name="TRANSFERINBANKNAME" maxlength="225">
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														转入账户账户名：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="TRANSFERINACCOUNTNAME" name="TRANSFERINACCOUNTNAME" maxlength="100">
														<span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														转入账户卡/折号：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="TRANSFERINACCOUNTNUMBER" name="TRANSFERINACCOUNTNUMBER" maxlength="30">
														<span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														IP地址：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="IP" name="IP" maxlength="30">
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														MAC地址：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="MAC" name="MAC" maxlength="20">
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														设备ID：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="DEVICEID" name="DEVICEID" maxlength="20">
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														交易地点：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="PLACE" name="PLACE" maxlength="255">
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														备注：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="REMARK" name="REMARK" maxlength="255">
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														是否已止付：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<select id="ISCEASED" name="ISCEASED" style="width: 250px;">
															<option value=''>-请选择-</option>
															<option value='0'>已止付</option>
															<option value='1'>未止付</option>
														</select>
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
					<input type="button" class="btn" value="发送" onclick="sendCard();"> 
				</td>
			</tr>
		</table>
		</div>
		<script type="text/javascript">	
			function sendCard(){
			
				var APPLICATIONTYPE = document.getElementById("APPLICATIONTYPE").value;//案件举报类型
				var ISINITIALNODE = document.getElementById("ISINITIALNODE").value;//是否主动发送初始节点
				var REPORTENDTIME = document.getElementById("REPORTENDTIME").value;//上报时间
				var VICTIMNAME = document.getElementById("VICTIMNAME").value;//受害人姓名
				var VICTIMPHONENUMBER = document.getElementById("VICTIMPHONENUMBER").value;//受害人联系电话
				var VICTIMIDTYPE = document.getElementById("VICTIMIDTYPE").value;//证件类型
				var VICTIMIDNUMBER = document.getElementById("VICTIMIDNUMBER").value;//证件号
				var ACCIDENTDESCRIPTION = document.getElementById("ACCIDENTDESCRIPTION").value;//事件描述
				var TIME = document.getElementById("TIME").value;//交易时间
				var TYPE = document.getElementById("TYPE").value;//交易类型
				var CURRENCY = document.getElementById("CURRENCY").value;//币种
				var TRANSFERAMOUNT = document.getElementById("TRANSFERAMOUNT").value;//交易金额
				var TRANSFEROUTBANKNAME = document.getElementById("TRANSFEROUTBANKNAME").value;//转出账户所属银行名称
				var TRANSFEROUTACCOUNTNAME = document.getElementById("TRANSFEROUTACCOUNTNAME").value;//转出账户名
				var TRANSFEROUTACCOUNTNUMBER = document.getElementById("TRANSFEROUTACCOUNTNUMBER").value;//转出卡/折号
				var TRANSFERINACCOUNTNAME = document.getElementById("TRANSFERINACCOUNTNAME").value;//转入账户账户名
				var TRANSFERINACCOUNTNUMBER = document.getElementById("TRANSFERINACCOUNTNUMBER").value;//转入账户卡/折号
				var ISCEASED = document.getElementById("ISCEASED").value;//是否已止付
				
				if(APPLICATIONTYPE=null || APPLICATIONTYPE == ""){
					WriteErrMessage(form.APPLICATIONTYPE,"案件举报类型不能为空！");
					return;
				}
				if(ISINITIALNODE=null || ISINITIALNODE == ""){
					WriteErrMessage(form.ISINITIALNODE,"是否主动发送初始节点不能为空！");
					return;
				}
				if(REPORTENDTIME=null || REPORTENDTIME == ""){
					WriteErrMessage(form.REPORTENDTIME,"上报时间不能为空！");
					return;
				}
				if(VICTIMNAME=null || VICTIMNAME == ""){
					WriteErrMessage(form.VICTIMNAME,"受害人姓名不能为空！");
					return;
				}
				if(VICTIMPHONENUMBER=null || VICTIMPHONENUMBER == ""){
					WriteErrMessage(form.VICTIMPHONENUMBER,"受害人联系电话不能为空！");
					return;
				}
				if(VICTIMIDTYPE=null || VICTIMIDTYPE == ""){
					WriteErrMessage(form.VICTIMIDTYPE,"证件类型不能为空！");
					return;
				}
				if(VICTIMIDNUMBER=null || VICTIMIDNUMBER == ""){
					WriteErrMessage(form.VICTIMIDNUMBER,"证件号不能为空！");
					return;
				}
				if(ACCIDENTDESCRIPTION=null || ACCIDENTDESCRIPTION == ""){
					WriteErrMessage(form.ACCIDENTDESCRIPTION,"事件描述不能为空！");
					return;
				}
				if(TIME=null || TIME == ""){
					WriteErrMessage(form.TIME,"交易时间不能为空！");
					return;
				}
				if(TYPE=null || TYPE == ""){
					WriteErrMessage(form.TYPE,"交易类型不能为空！");
					return;
				}
				if(CURRENCY=null || CURRENCY == ""){
					WriteErrMessage(form.CURRENCY,"币种不能为空！");
					return;
				}
				if(TRANSFERAMOUNT=null || TRANSFERAMOUNT == ""){
					WriteErrMessage(form.TRANSFERAMOUNT,"交易金额不能为空！");
					return;
				}
				if(TRANSFEROUTBANKNAME=null || TRANSFEROUTBANKNAME == ""){
					WriteErrMessage(form.TRANSFEROUTBANKNAME,"转出账户所属银行名称不能为空！");
					return;
				}
				if(TRANSFEROUTACCOUNTNAME=null || TRANSFEROUTACCOUNTNAME == ""){
					WriteErrMessage(form.TRANSFEROUTACCOUNTNAME,"转出账户名不能为空！");
					return;
				}
				if(TRANSFEROUTACCOUNTNUMBER=null || TRANSFEROUTACCOUNTNUMBER == ""){
					WriteErrMessage(form.TRANSFEROUTACCOUNTNUMBER,"转出卡/折号不能为空！");
					return;
				}
				if(TRANSFERINACCOUNTNAME=null || TRANSFERINACCOUNTNAME == ""){
					WriteErrMessage(form.TRANSFERINACCOUNTNAME,"转入账户账户名不能为空！");
					return;
				}
				if(TRANSFERINACCOUNTNUMBER=null || TRANSFERINACCOUNTNUMBER == ""){
					WriteErrMessage(form.TRANSFERINACCOUNTNUMBER,"转入账户卡/折号不能为空！");
					return;
				}
				if(ISCEASED=null || ISCEASED == ""){
					WriteErrMessage(form.ISCEASED,"是否已止付不能为空！");
					return;
				}
				
				
				form.submit();
			}
			
		</script>
	</body> 
	
	
</html>
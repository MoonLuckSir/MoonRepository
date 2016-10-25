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
		<title>涉案账户可疑名单上报</title>
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
								当前位置：司法查询&gt;&gt;  人民银行电信诈骗查控 &gt;&gt; 涉案账户可疑名单上报
							</td>
							<td width="50" align="right">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="rowSplit"></td>
			</tr>
			<tr style="padding-top: 0px">
				<td> 
				<form name="form" method="post" action="${ctx}/judicial/zfdj.shtml?action=sendAccount">
					<input type="hidden" name="_backUrl" value="${ctx}/judicial/zfdj.shtml?action=toSendAccount">
					<div style="overflow: auto;width: 100%;height: 100%;padding: 0px;">
					<table width="80%" border="0" cellspacing="1" align="center" >
						<tr>
							<td width="50%" valign="top" >
								<table width="100%" border="0" cellpadding="4" cellspacing="4"  >
									<tr>
										<td >
											<div style="overflow: auto;width: 100%;height: 100%;padding: 0px;">
											<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
												
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														事件特征码：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<select id="FEATURECODE" name="FEATURECODE" style="width: 250px;">
															<option value=''>-请选择-</option>
															<option value='2001'>黑名单账户</option>
															<option value='0000'>取消上报</option>
														</select>
														<span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														卡/折号：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														 <input style="width:250px;"  type="text" id="CARDNUMBER" name="CARDNUMBER" maxlength="30">
														 <span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														账户名：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														 <input style="width:250px;"  type="text" id="ACCOUNTNAME" name="ACCOUNTNAME" maxlength="180">
														 <span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														证件类型：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<select id="IDTYPE" name="IDTYPE" style="width: 250px;">
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
														 <input style="width:250px;"  type="text" id="IDNUMBER" name="IDNUMBER" maxlength="30">
														 <span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														联系电话：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														 <input style="width:250px;"  type="text" id="PHONENUMBER" name="PHONENUMBER" maxlength="30">
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														通讯地址：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														 <input style="width:250px;"  type="text" id="ADDRESS" name="ADDRESS" maxlength="225">
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														邮政编码：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														 <input style="width:250px;"  type="text" id="POSTCODE" name="POSTCODE" pattern="int" maxlength="10">
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														开卡地点：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														 <input style="width:250px;"  type="text" id="ACCOUNTOPENPLACE" name="ACCOUNTOPENPLACE" maxlength="225">
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														定位账户账号：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														 <input style="width:250px;"  type="text" id="ACCOUNTNUMBER" name="ACCOUNTNUMBER" maxlength="50">
														 <span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														一般(子)账户序号：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														 <input style="width:250px;"  type="text" id="ACCOUNTSERIAL" name="ACCOUNTSERIAL" maxlength="8">
														 <span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														一般(子)账户类别：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														 <input style="width:250px;"  type="text" id="ACCOUNTTYPE" name="ACCOUNTTYPE" maxlength="30">
														 <span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														账户状态：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<select id="ACCOUNTSTATUS" name="ACCOUNTSTATUS" style="width: 250px;">
															<option value=''>-请选择-</option>
															<option value='0'>正常</option>
															<option value='1'>冻结</option>
															<option value='2'>销户</option>
														</select>
														<span style="color: red;">*</span>
													</td>
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
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														钞汇标志：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="CASHREMIT" name="CASHREMIT" maxlength="10">
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														交易流水号：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="TRANSACTIONSERIAL" name="TRANSACTIONSERIAL" maxlength="50" pattern="int">
														<span style="color: red;">*</span>
													</td>
												</tr>
												
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														交易类型：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="TRANSACTIONTYPE" name="TRANSACTIONTYPE" maxlength="50">
														<span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														借贷标志：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<select id="BORROWINGSIGNS" name="BORROWINGSIGNS" style="width: 250px;">
															<option value=''>-请选择-</option>
															<option value='0'>借</option>
															<option value='1'>贷</option>
														</select>
														<span style="color: red;">*</span>
													</td>
												</tr>
												
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														交易金额：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="TRANSACTIONAMOUNT" name="TRANSACTIONAMOUNT" maxlength="25" pattern="int">
														<span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														交易余额：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="ACCOUNTBALANCE" name="ACCOUNTBALANCE" maxlength="25" pattern="int">
														<span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														交易时间：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;" class="Wdate" type="text" id="TRANSACTIONTIME" name="TRANSACTIONTIME" value="${param.TRANSACTIONTIME }" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly>
														<span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														交易对方名称：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="OPPONENTNAME" name="OPPONENTNAME" maxlength="60" >
													</td>
													
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														交易对方账卡号：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="OPPONENTACCOUNTNUMBER" name="OPPONENTACCOUNTNUMBER" maxlength="50" >
														<span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														交易对方证件号码：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="OPPONENTCREDENTIALNUMBER" name="OPPONENTCREDENTIALNUMBER" maxlength="30" >
													</td>
													
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														交易对方账号开户行：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="OPPONENTDEPOSITBANKID" name="OPPONENTDEPOSITBANKID" maxlength="90" >
														<span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														交易摘要：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="TRANSACTIONREMARK" name="TRANSACTIONREMARK" maxlength="225" >
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														交易网点名称：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="TRANSACTIONBRANCHNAME" name="TRANSACTIONBRANCHNAME" maxlength="225" >
														<span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														交易网点代码：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="TRANSACTIONBRANCHCODE" name="TRANSACTIONBRANCHCODE" maxlength="20" >
														<span style="color: red;">*</span>
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														日志号：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="LOGNUMBER" name="LOGNUMBER" maxlength="30" >
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														传票号：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="SUMMONSNUMBER" name="SUMMONSNUMBER" maxlength="30" >
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														凭证种类：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="VOUCHERTYPE" name="VOUCHERTYPE" maxlength="10" >
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														凭证号：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="VOUCHERCODE" name="VOUCHERCODE" maxlength="30" >
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														现金标志：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<select id="CASHMARK" name="CASHMARK" style="width: 250px;">
															<option value=''>-请选择-</option>
															<option value='00'>其它</option>
															<option value='01'>现金交易</option>
														</select>
														<span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														终端号：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="TERMINALNUMBER" name="TERMINALNUMBER" maxlength="30" >
													</td>
												</tr>
												
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														交易是否成功：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<select id="TRANSACTIONSTATUS" name="TRANSACTIONSTATUS" style="width: 250px;">
															<option value=''>-请选择-</option>
															<option value='00'>成功</option>
															<option value='01'>失败</option>
														</select>
														<span style="color: red;">*</span>
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														交易发生地：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="TRANSACTIONADDRESS" name="TRANSACTIONADDRESS" maxlength="225" >
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														商户名称：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="MERCHANTNAME" name="MERCHANTNAME" maxlength="225" >
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														商户号：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="MERCHANTCODE" name="MERCHANTCODE" maxlength="30" >
													</td>
												</tr>
												
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														IP地址：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="IPADDRESS" name="IPADDRESS" maxlength="30" >
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														MAC地址：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="MAC" name="MAC" maxlength="50" >
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														交易柜员号：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="TELLERCODE" name="TELLERCODE" maxlength="30" >
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														上报机构名称：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														<input style="width:250px;"  type="text" id="REPORTORGNAME" name="REPORTORGNAME" maxlength="225" >
													</td>
												</tr>
												
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														经办人姓名：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														 <input style="width:250px;"  type="text" id="OPERATORNAME" name="OPERATORNAME">
													</td>
													<td width="20%" height="23" align="left" class="detail_label">
														经办人电话：
													</td>
													<td width="30%" height="23" align="left" class="detail_content">
														 <input style="width:250px;"  type="text" id="OPERATORPHONENUMBER" name="OPERATORPHONENUMBER">
													</td>
												</tr>
												<tr>
													<td width="20%" height="23" align="left" class="detail_label">
														备注：
													</td>
													<td colspan="3" width="80%" height="23" align="left" class="detail_content">
														 <textarea name="REMARK" id="REMARK" rows="3" style="width: 80%;" ></textarea>
													</td>
												</tr>
												
											</table>
											</div>
										</td>
									</tr>
								</table>
							</td>
							
						</tr>
					</table>
					</div>
					</form>
				</td>
			</tr>
			<tr style="padding-top: 30px">
				<td height="40" align="center" valign="middle" > 
					<input type="button" class="btn" value="发送" onclick="sendAccount();"> 
				</td>
			</tr>
		</table>
		</div>
		<script type="text/javascript">	
			function sendAccount(){
			
				var FEATURECODE = document.getElementById("FEATURECODE").value;//事件特征码
				var CARDNUMBER = document.getElementById("CARDNUMBER").value;//c
				var ACCOUNTNAME = document.getElementById("ACCOUNTNAME").value;//账户名
				var IDTYPE = document.getElementById("IDTYPE").value;//证件类型
				var IDNUMBER = document.getElementById("IDNUMBER").value;//证件号
				var ACCOUNTNUMBER = document.getElementById("ACCOUNTNUMBER").value;//定位账户账号
				var ACCOUNTSERIAL = document.getElementById("ACCOUNTSERIAL").value;//一般(子)账户序号
				var ACCOUNTTYPE = document.getElementById("ACCOUNTTYPE").value;//一般(子)账户类别
				var CURRENCY = document.getElementById("CURRENCY").value;//币种
				var TRANSACTIONSERIAL = document.getElementById("TRANSACTIONSERIAL").value;//交易流水号
				var TRANSACTIONTYPE = document.getElementById("TRANSACTIONTYPE").value;//交易类型
				var TRANSACTIONTIME = document.getElementById("TRANSACTIONTIME").value;//交易时间
				var BORROWINGSIGNS = document.getElementById("BORROWINGSIGNS").value;//借贷标志
				var TRANSACTIONAMOUNT = document.getElementById("TRANSACTIONAMOUNT").value;//交易金额
				var ACCOUNTBALANCE = document.getElementById("ACCOUNTBALANCE").value;//交易余额
				
				var OPPONENTACCOUNTNUMBER = document.getElementById("OPPONENTACCOUNTNUMBER").value;//交易对方账卡号
				var OPPONENTDEPOSITBANKID = document.getElementById("OPPONENTDEPOSITBANKID").value;//交易对方账号开户行
				var TRANSACTIONBRANCHNAME = document.getElementById("TRANSACTIONBRANCHNAME").value;//交易网点名称
				var TRANSACTIONBRANCHCODE = document.getElementById("TRANSACTIONBRANCHCODE").value;//交易网点代码
				var CASHMARK = document.getElementById("CASHMARK").value;//现金标志
				var TRANSACTIONSTATUS = document.getElementById("TRANSACTIONSTATUS").value;//交易是否成功
				
				if(FEATURECODE=null || FEATURECODE == ""){
					WriteErrMessage(form.FEATURECODE,"事件特征码不能为空！");
					return;
				}
				if(CARDNUMBER=null || CARDNUMBER == ""){
					WriteErrMessage(form.CARDNUMBER,"卡/折号不能为空！");
					return;
				}
				if(ACCOUNTNAME=null || ACCOUNTNAME == ""){
					WriteErrMessage(form.ACCOUNTNAME,"账户名不能为空！");
					return;
				}
				if(IDTYPE=null || IDTYPE == ""){
					WriteErrMessage(form.IDTYPE,"证件类型不能为空！");
					return;
				}
				if(IDNUMBER=null || IDNUMBER == ""){
					WriteErrMessage(form.IDNUMBER,"证件号不能为空！");
					return;
				}
				if(ACCOUNTNUMBER=null || ACCOUNTNUMBER == ""){
					WriteErrMessage(form.ACCOUNTNUMBER,"定位账户账号不能为空！");
					return;
				}
				if(ACCOUNTSERIAL=null || ACCOUNTSERIAL == ""){
					WriteErrMessage(form.ACCOUNTSERIAL,"一般(子)账户序号不能为空！");
					return;
				}
				if(ACCOUNTTYPE=null || ACCOUNTTYPE == ""){
					WriteErrMessage(form.ACCOUNTTYPE,"一般(子)账户类别不能为空！");
					return;
				}
				if(CURRENCY=null || CURRENCY == ""){
					WriteErrMessage(form.CURRENCY,"币种不能为空！");
					return;
				}
				if(TRANSACTIONTYPE=null || TRANSACTIONTYPE == ""){
					WriteErrMessage(form.TRANSACTIONTYPE,"交易类型不能为空！");
					return;
				}
				if(TRANSACTIONSERIAL=null || TRANSACTIONSERIAL == ""){
					WriteErrMessage(form.TRANSACTIONSERIAL,"交易流水号不能为空！");
					return;
				}
				if(TRANSACTIONTIME=null || TRANSACTIONTIME == ""){
					WriteErrMessage(form.TRANSACTIONTIME,"交易时间不能为空！");
					return;
				}
				if(BORROWINGSIGNS=null || BORROWINGSIGNS == ""){
					WriteErrMessage(form.BORROWINGSIGNS,"借贷标志不能为空！");
					return;
				}
				
				if(TRANSACTIONAMOUNT=null || TRANSACTIONAMOUNT == ""){
					WriteErrMessage(form.TRANSACTIONAMOUNT,"交易金额不能为空！");
					return;
				}
				if(ACCOUNTBALANCE=null || ACCOUNTBALANCE == ""){
					WriteErrMessage(form.ACCOUNTBALANCE,"交易余额不能为空！");
					return;
				}
				if(OPPONENTACCOUNTNUMBER=null || OPPONENTACCOUNTNUMBER == ""){
					WriteErrMessage(form.OPPONENTACCOUNTNUMBER,"交易对方账卡号不能为空！");
					return;
				}
				if(OPPONENTDEPOSITBANKID=null || OPPONENTDEPOSITBANKID == ""){
					WriteErrMessage(form.OPPONENTDEPOSITBANKID,"交易对方账号开户行不能为空！");
					return;
				}
				if(TRANSACTIONBRANCHNAME=null || TRANSACTIONBRANCHNAME == ""){
					WriteErrMessage(form.TRANSACTIONBRANCHNAME,"交易网点名称不能为空！");
					return;
				}
				if(TRANSACTIONBRANCHCODE=null || TRANSACTIONBRANCHCODE == ""){
					WriteErrMessage(form.TRANSACTIONBRANCHCODE,"交易网点代码不能为空！");
					return;
				}
				if(CASHMARK=null || CASHMARK == ""){
					WriteErrMessage(form.CASHMARK,"现金标志不能为空！");
					return;
				}
				if(TRANSACTIONSTATUS=null || TRANSACTIONSTATUS == ""){
					WriteErrMessage(form.TRANSACTIONSTATUS,"交易是否成功不能为空！");
					return;
				}
				
				form.submit();
			}
			
		</script>
	</body> 
	
	
</html>
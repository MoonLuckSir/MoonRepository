<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
	<head>
		<title>${REP_NAME}</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/style.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/tools.css">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/msg.css"> 
		<%@ include file="/commons/jslibs.jsp"%>
	</head>
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center"><div id="printid"><input type="button" class="btn" onClick="javascript:prints();"  value="打 印"></div></td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr height="40">
							<td width="25">&nbsp;</td>
							<td colspan="4" width="170">__________________：</td>
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr height="30">
							<td width="25">&nbsp;</td>
							<td width="80">&nbsp;</td>
							<td width="65">你单位</td>
							<td width="30">&nbsp;</td>
							<td width="30">字第</td>
							<td width="50">&nbsp;</td>
							<td width="150">号查询通知书收悉.现将</td>
							<td >&nbsp;</td>
						</tr>
						<tr height="30">
							<td colspan="8">的存款情况提供如下：</td>
						</tr>
					</table>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr height="20">
							<td colspan="7">&nbsp;</td>
						</tr>
						<tr height="20">
							<td width="25">&nbsp;</td>
							<td width="80">查询日期</td>
							<td width="25">：</td>
							<td colspan="4">${map.startData} -- ${map.endData}</td>
						</tr>
						<tr height="20">
							<td width="25">&nbsp;</td>
							<td width="80">客户帐号</td>
							<td width="25">：</td>
							<td colspan="4">${map.SFACCN}</td>
						</tr>
						<tr height="20">
							<td width="25">&nbsp;</td>
							<td width="80">户名称</td>
							<td width="25">：</td>
							<td colspan="4">${map.SFBFNM}</td>
						</tr>
						<tr height="20">
							<td width="25">&nbsp;</td>
							<td width="80">帐户号</td>
							<td width="25">：</td>
							<td colspan="4">${map.SFACNO}</td>
						</tr>
						<tr height="20">
							<td width="25">&nbsp;</td>
							<td width="80">户类型</td>
							<td width="25">：</td>
							<td colspan="4">
							    <c:if test="${map.SFPKFG eq '0'}">存折户</c:if>
							    <c:if test="${map.SFPKFG eq '1'}">支票户</c:if>
								<c:if test="${MSITCD eq '0400'}">活期存款(支票户)</c:if>
								<c:if test="${MSITCD eq '0408'}">单位协定存款</c:if>
								<c:if test="${MSITCD eq '0410'}">其他活期存款</c:if>
								<c:if test="${MSITCD eq '0411'}">财政性存款</c:if>
								<c:if test="${MSITCD eq '0412'}">待结算财政款项</c:if>
								<c:if test="${MSITCD eq '0414'}">地方财政库款</c:if>
								<c:if test="${MSITCD eq '0415'}">财政预算专项存款</c:if>
								<c:if test="${MSITCD eq '0416'}">财政预算外存款</c:if>
								<c:if test="${MSITCD eq '0417'}">县级财政预算外存款</c:if>
								<c:if test="${MSITCD eq '0420'}">县级待结算财政款项</c:if>
								<c:if test="${MSITCD eq '0435'}">县级地方财政存款</c:if>
								<c:if test="${MSITCD eq '0452'}">境内非银行同业存放款项</c:if>
								<c:if test="${MSITCD eq '0453'}">财政零余额款项</c:if>
								<c:if test="${MSITCD eq '0457'}">预算单位零余额款项</c:if>
								<c:if test="${MSITCD eq '0536'}">临时存款</c:if>
								<c:if test="${MSITCD eq '0543'}">融资性担保机构客户担保保证金</c:if>
								<c:if test="${MSITCD eq '0544'}">承兑汇票保证金</c:if>
								<c:if test="${MSITCD eq '0427'}">个人储蓄账户</c:if>
								<c:if test="${MSITCD eq '0428'}">个人结算账户</c:if>&nbsp;
							</td>
						</tr>
						<tr height="20">
							<td width="25">&nbsp;</td>
							<td width="80">户机构名称</td>
							<td width="25">：</td>
							<td>${map.SFBFJG}</td>
							<td width="60">账号余额</td>
							<td width="20">：</td>
							<td>${map.SFACBL}</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr height="20"><td colspan="5">&nbsp;</td></tr>
						<tr height="40"><th colspan="7" align="center">账户交易明细</td></th>
						<tr height="40">
							<th nowrap>&nbsp;交易日期&nbsp;</th>
							<th nowrap>&nbsp;交易对手名称&nbsp;</th>
							<th nowrap>&nbsp;交易对手账号&nbsp;</th>
							<th nowrap>&nbsp;交易对手机构&nbsp;</th>
							<th nowrap>&nbsp;现转标志&nbsp;</th>
							<th nowrap>&nbsp;借贷发生额&nbsp;</th>
							<th nowrap>&nbsp;贷方发生额&nbsp;</th>
							<th nowrap>&nbsp;账户余额&nbsp;</th>
						</tr>                     		                     
						<c:forEach items="${list}" var="rst">
							<tr height="20"> 
								<td align="center">${rst.SFTRDT}&nbsp;</td>
								<td align="center">${rst.SFDFNM}&nbsp;</td>
								<td align="center">${rst.SFDFZH}&nbsp;</td>
								<td align="center">${rst.SFDFJG}&nbsp;</td>
								<td align="center"><c:if test="${rst.SFCATR eq '0'}">现金</c:if><c:if test="${rst.SFCATR eq '1'}">转账</c:if>&nbsp;</td>
								<td align="right"><c:if test="${rst.SFAMCD eq '0'}">${rst.SFTRAM}</c:if>&nbsp;</td>
								<td align="right"><c:if test="${rst.SFAMCD eq '1'}">${rst.SFTRAM}</c:if>&nbsp;</td>
								<td align="right">${rst.SFACBL}&nbsp;</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr height="80">
							<td width="70%">&nbsp;</td>
							<td width="100">单位签章：</td>
							<td>&nbsp;</td>
						 </tr>
						 <tr height="40">
							<td colspan="2">&nbsp;</td>
							<td>年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日&nbsp;&nbsp;&nbsp;&nbsp;</td>
						 </tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
	<script type="text/javascript">
		function pageSetup_Null(){
			try{
				var Wsh = new ActiveXObject("WScript.Shell");
				Wsh.RegWrite("HKEY_CURRENT_USER\\Software\\Microsoft\\Intemet Explorer\\PageSetup\\header","");
				Wsh.RegWrite("HKEY_CURRENT_USER\\Software\\Microsoft\\Intemet Explorer\\PageSetup\\footer","");
			}catch(e){}
		}
		
		pageSetup_Null();
		
		// 提交上传bad文件
		function prints(){
			document.getElementById("printid").style.display = "none";
			setTimeout("print();",100);
		}	
	</script>
</html>
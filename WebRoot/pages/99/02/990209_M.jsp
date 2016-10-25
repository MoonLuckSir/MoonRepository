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
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/${theme}/css/msg.css"> 
		<%@ include file="/commons/jslibs.jsp"%>
		<title>安徽农金司法查询系统</title>
	</head>
	<body>
		<table border="0" cellpadding="0" cellspacing="0"
			style="width: 100%; height: 100%;">
			<tr>
				<td align="center" valign="middle"> 
					<form name="form" method="post"
						action="${ctx}/login.shtml?action=setPwdLogin"
						onsubmit="return doSave();">
						<input type="hidden" name="SM" value="${param.SM}">
						<input type="hidden" name="init" value="${userSess.pwdInit}">
						<table width="600" align="center" border="0" cellspacing="0" class="gridForm">
							<tr>
								<td width="100%" align="center" style="">
									<div class="lst_area">
									<table width="100%" border="0" cellpadding="0"
										cellspacing="0">
										<tr>
											<th colspan="2" height="40">
												设置密码
											</th>
										</tr>
										<tr>
											<td height="1" colspan="2"></td>
										</tr>
										<tr>
											<td width="25%" height="30" align="right">
												用户编号：
											</td>
											<td width="75%">
												&nbsp;
												<input type="text" name="userId" readonly
													class="readonly" value="${userSess.USER_ID}">
												<span class="FontRed">${msg }</span>	
											</td>
										</tr>
										<tr>
											<td height="30" align="right">
												用户姓名：
											</td>
											<td>
												&nbsp;
												<input type="text" readonly class="readonly"
													value="${userSess.USER_NAME}">
											</td>
										</tr> 
										<tr>
											<td height="30" align="right">
												新 密 码：
											</td>
											<td>
												&nbsp;
												<input type="password" name="userPwd" pattern="string"
													label="新密码" required="true" minlength="6" maxlength="15"
													autocheck="true" value=""
													valType="number:true,upperChar:true,lowerChar:true">
												<span class="FontBlue">密码可由字母、数字、特殊字符组成!</span>
											</td>
										</tr>
										<tr>
											<td height="30" align="right">
												确认新密码：
											</td>
											<td>
												&nbsp;
												<input type="password" name="userPwd2" pattern="string"
													label="确认新密码" required="true" minlength="6"
													maxlength="15" autocheck="true" value="">
											</td>
										</tr>
										<tr>
											<td height="5" colspan="2" class="splitToolbar">
											</td>
										</tr>
										<tr>
											<td height="32" colspan="2" align="center" class="gridToolbar">
												<input type="submit" class="btn" value="确 定">
												&nbsp;&nbsp;
												<input type="reset" class="btn" value="重 置">
												&nbsp;&nbsp;
												<input type="button" class="btn" value="退 出" onclick="history.back();">
											</td>
										</tr>
									</table></div>
								</td>
							</tr>
						</table>
					</form> 
				</td>
			</tr>
			<tr><td height="200">&nbsp;</td></tr>
		</table>
	</body>
	<script type="text/javascript">InitFormValidator(0);</script>
	<script type="text/javascript">
		var pwdSpan = g('pwdSpan');
		function doSave(){
			if(FormValidate(0)){
				//校验密码强度
				if(checkPassword(form.userPwd)< 2){					
					WriteErrMessage(form.userPwd,"新密码过于简单,请重新设置！");
					return false;
				} 
				if(form.userPwd2.value!=form.userPwd.value){
					form.userPwd.value="";
					form.userPwd2.value="";
					WriteErrMessage(form.userPwd,"新密码与确认密码不一致,请重新设置！");
					return false;
				}
				setTimeout("form.submit();",100);
			}
			return false;
		}
		var regNm = new RegExp(/[0-9]/g);
		var regUChar = new RegExp(/[A-Z]/g);
		var regLChar = new RegExp(/[a-z]/g);
		var regSChar = new RegExp(/[^A-Za-z0-9]/g);
		function checkPassword(inpt){			
			//大写、小字字母、数字，每项系数为1。
			//特殊字符：系数为2
			//系数>=2，强度OK		
			var value=inpt.value;		
			var right=0;	 
			try{
				if(value.match(regUChar)) right++; 
				if(value.match(regLChar)) right++; 
				if(value.match(regNm)) right++; 
				if(value.match(regSChar)) right+=2;
			}catch(e){}
			return right;
		}
	</script>
</html>
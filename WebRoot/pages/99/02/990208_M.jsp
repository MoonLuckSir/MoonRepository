<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/main.css">
		<%@ include file="/commons/jslibs.jsp"%>
		<title>修改个人密码</title>
	</head>
	<body>
		<table border="0" cellpadding="0" cellspacing="0"
			style="width: 100%; height: 100%;">
			<tr>
				<td align="center" valign="middle">
					<table style="width: 100%; height: 100%;" border="0"
						cellpadding="0" cellspacing="0" class="gridForm">
						<tr>
							<td height="30">
								&nbsp;
							</td>
						</tr>
						<tr>
							<td width="100%" align="center" valign="top">
								<form name="form" method="post"
									action="${ctx}/system/user.shtml?action=setPwdSelf"
									onsubmit="return doSave();">
									<input type="hidden" name="_backUrl"
										value="${ctx }/system/user.shtml?action=toSetPwdSelf">
									<table width="650" align="center" border="0" cellspacing="0"
										class="gridForm">
										<tr>
											<th>
												修改密码
											</th>
										</tr>
										<tr>
											<td height="1" class="splitHeader"></td>
										</tr>
										<tr>
											<td height="10">
												&nbsp;
											</td>
										</tr>
										<tr>
											<td width="100%" align="center">
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td width="30%" height="30" align="right">
															用户编号：
														</td>
														<td>
															&nbsp;
															<input type="text" name="userId" readonly
																class="readonly" value="${userSess.userId}">
														</td>
													</tr>
													<tr>
														<td height="30" align="right">
															用户姓名：
														</td>
														<td>
															&nbsp;
															<input type="text" readonly class="readonly"
																value="${userSess.userName}">
														</td>
													</tr>
													<tr>
														<td height="30" align="right">
															原 密 码：
														</td>
														<td>
															&nbsp;
															<input type="password" name="oldPwd" pattern="string"
																label="原密码" required="true" minlength="4" maxlength="15"
																autocheck="true" value="">
														</td>
													</tr>
													<tr>
														<td height="30" align="right">
															新 密 码：
														</td>
														<td>
															&nbsp;
															<input type="password" name="newPwd" pattern="string"
																label="新密码" required="true" minlength="4" maxlength="15"
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
															<input type="password" name="newPwd2" pattern="string"
																label="确认新密码" required="true" minlength="4"
																maxlength="15" autocheck="true" value="">
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td height="10">
												&nbsp;
											</td>
										</tr>
										<tr>
											<td height="1" class="splitToolbar">
											</td>
										</tr>
										<tr>
											<td height="32" align="center" class="gridToolbar">
												<input type="submit" class="button" value="确 定">
												&nbsp;&nbsp;
												<input type="reset" class="button" value="重 置">
											</td>
										</tr>
									</table>
								</form>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
	<script type="text/javascript">InitFormValidator(0);</script>
	<script type="text/javascript">
		var pwdSpan = g('pwdSpan');
		function doSave(){
			if(FormValidate(0)){
				//校验密码强度
				if(checkPassword(form.newPwd)< 2){					
					WriteErrMessage(form.newPwd,"新密码过于简单,请重新设置！");
					return false;
				}
				if(form.oldPwd.value == form.newPwd.value){
					WriteErrMessage(form.newPwd,"新密码不能与现有密码相同,请重新设置！");
					return false;					
				}
				if(form.newPwd2.value !== form.newPwd.value){
					form.newPwd.value="";
					form.newPwd2.value="";
					WriteErrMessage(form.newPwd,"新密码与确认密码不一致,请重新设置！");
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
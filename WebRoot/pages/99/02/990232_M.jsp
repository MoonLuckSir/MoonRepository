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
		<%@ include file="/commons/jslibs.jsp"%>
		<title>修改个人密码</title>
	</head>
	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="27">
					<table width="100%" height="100%" cellspacing="0" cellpadding="0"
						class="wz">
						<tr>
							<td width="28" align="center"><label class="wz_img"></label></td>
							<td>当前位置：首页 &gt;&gt; 个人信息 &gt;&gt; 修改密码</td>
							<td width="50" align="right">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="rowSplit"></td>
			</tr>
			<tr>
				<td>
					<form name="form" method="post" action="${ctx}/system/user.shtml?action=setPwd">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									修改密码
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
														<td width="25%" height="30" align="right"
															class="detail_label">
															用户编号：
														</td>
														<td width="75%" height="30" align="left"
															class="detail_content">
															<input name="userId" type="text" autocheck="true"
																pattern="string" readonly class="readonly" label="用户编号"
																value="${userSess.userId}">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															用户姓名：
														</td>
														<td height="30" align="left" class="detail_content">
															<input name="userName" type="text" pattern="string"
																size="50" maxlength="60" label="用户姓名"
															    value="${userSess.userName}" readonly class="readonly">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															原 密 码：
														</td>
														<td height="30" align="left" class="detail_content">
															<input name="oldPwd" type="password" pattern="string" label="密 码"
																size="50" autocheck="true" minlength="6" maxlength="15" required="true">
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															新 密 码：
														</td>
														<td height="30" align="left" class="detail_content">
															<input name="userPwd" type="password" pattern="string" label="新 密 码"
																size="50" autocheck="true" minlength="6" maxlength="15" required="true">
															<span class="FontBlue">密码可由字母、数字、特殊字符组成!</span>
														</td>
													</tr>
													<tr>
														<td height="30" align="right" class="detail_label">
															确认新密码：
														</td>
														<td height="30" align="left" class="detail_content">
															<input name="userPwd2" type="password" pattern="string" label="确认新密码"
																size="50" autocheck="true" minlength="6" maxlength="15" required="true">
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
									<input type="button" class="btn" value="修 改" onclick="doSave()">
									&nbsp;&nbsp;
									<input type="reset" class="btn" value="重 置">
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
	</body>
	<script type="text/javascript">InitFormValidator(0);</script>
		<script type="text/javascript">
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

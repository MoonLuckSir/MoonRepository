<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>  
<HTML>
<HEAD>
<TITLE>安徽农金司法查询系统</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<LINK type=text/css rel=stylesheet href="${ctx}/styles/theme/css/login.css" >
<script type="text/javascript" src="${ctx}/scripts/core/check-min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/core/validate-min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/util/plus.js"></script>
</HEAD>
<BODY>
<FORM name=form action="${ctx}/login.shtml?action=authLogin" method=post onsubmit="return checkForm();">
<TABLE width="100%" height="100%" cellSpacing=0 cellPadding=0 border=0>
  <TR><TD></TD></TR>
  <TR>
    <TD vAlign=top align=center width=100% height=618>
      <TABLE height=100% cellSpacing=0 cellPadding=0 border=0 width=1024 background="${ctx}/images/index/login.png" >
      	 <TR><TD WIDTH=440 height=260></TD><TD></TD></TR>
	     <TR>
	    	<TD></TD>
	      	<TD height=45>
	      		<INPUT style="WIDTH:200px;height:30px" name=userId label="登录帐号" 
	      		class="login_sub" tabindex="1"  maxlength="15" 
				pattern="String" autocheck="true" required="true" >
			</TD>
		 </TR>
		 <TR>
		 	<TD></TD>
			<TD height=65>
	       		<INPUT style="WIDTH:200px;height:30px" type=password name=userPwd 
	       		label="登录密码" class="login_sub" tabindex="2" minlength="5"
	       		maxlength="15" pattern="String" autocheck="true" required="true" >
	       	</TD>
	     </TR>
		 <TR>
		 	<TD></TD>
			<TD height=50>	
	        	<input type="submit" value=" "style="background-repeat: no-repeat;width:96px; 
	        	height:32px;border:0px;background:url(${ctx }/images/index/btn.gif);cursor:hand">
	        </TD>
        </TR> 
        <TR><TD></TD><TD></TD></TR>
      </TABLE>
    </TD>
  </TR>
  <TR><TD></TD></TR>
</TABLE>
</FORM>
</BODY>
<script type="text/javascript">
	var loginPage = true;
	//验证
	function checkForm(){ 
		if(FormValidate(0)){
			form.submit();
		}
		return false;
	}
	function showWaitPanel() {  
		var B = "<div id='YT_MSG'><div id='YT_LOAD_MSG' class='loading'>正在处理……</div>                                                                                                                                                                                                                                                                                                          </div>";
		showMessageBox(B, 200, 300, {fast:true});
	}	
	// 提示信息
	function showMsgPanel(msg, title) { 
		showErrorMsg(msg,title,{fast:true});
	}
	
	var init = function(){
		try{ 
			if(window.dialogArguments){ 
    			window.dialogArguments.location.href='${ctx}';	
				window.close();	
				return;
			}else if(!parent.loginPage){ 
				parent.location.href="${ctx}";
				return;
			}
		}catch(e){
			alert(e);
		}			 
		form.userId.focus();
	}();
</script>
</HTML>
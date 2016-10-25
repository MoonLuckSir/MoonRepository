<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/pages/judicial/selcon/selconpar.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/theme/css/style.css">
		<link rel="stylesheet" type="text/css"
			href="${ctx}/styles/theme/css/tools.css">
		<%@ include file="/commons/jslibs.jsp"%>
		<script type="text/javascript" src="${ctx}/scripts/util/calendar.js"></script>
		
		<script type="text/javascript" src="${ctx}/scripts/yt/organ-selector.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/yui/logger-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/treeview/treeview.css" />
		<script type="text/javascript" src="${ctx}/scripts/yui/treeview-min.js"></script>
		<title>历史查询</title>
	</head>
	<body  onload="doload()" > 
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
								当前位置：司法查询管理 &gt;&gt; 司法查询 &gt;&gt; ${repName}
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
						action="${ctx}/judicial/lscxsq.shtml?action=add"
						onsubmit="return doSave();">
						${paramCover.unCovered_Inputs }
						<table width="100%" border="0" cellspacing="1">
						<input type="hidden" name="REPMOD_NO" value="${REPMOD_NO}">
						<input type="hidden" name="usrId" value='${usrId}'>
		                <input type="hidden" name="orgNo" value='${orgNo}'>
		                <input type="hidden" name="groupvalue" value=''>
							<tr>
								<td height="35" align="center" valign="middle"
									class="detail_title">
									${repName}
								</td>
							</tr>
							<tr>
							<table width="100%" border="0" cellpadding="1" cellspacing="1">
	                             <tr norawp  align='center'>
	                             <td>
	                            ${hidden}
	                            <td>
	                             <tr>
	                        </table>
	                        </tr>
							<tr>
								<td width="100%" align="center">
									<table width="100%" border="0" cellpadding="4" cellspacing="4"
										class="detail">
										    <tr>
											<td class="detail2">
												<table width="100%" border="0" cellpadding="1" cellspacing="1" class="detail3">
												${Conditions}	
													
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="49" align="center" valign="middle">
									<input type="submit" class="btn" value="保 存">
									&nbsp;&nbsp;
									<input type="reset" class="btn" value="重 置">
									<c:if test="${not empty param._backUrl}">&nbsp;&nbsp;
										<input type="button" class="btn" value="返 回"
											onclick="history.back();">
									</c:if>
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
	 function doload()
	    {
	      var frm=document.getElementsByName("dogroup");
	      if(frm!=null)
	      {
	        var flag='0';
	        for(i=0;i<frm.length;i++)
	        {
	           if(frm[i].checked)
	           flag='1';
	        }
	        if(flag=='0')
	         frm[0].onclick();
	      }
	       
	    }
	    function sethidden(obj){
	       ${hiddenjs}
	    }
		function doSave(){
		    ${JSRULE}
			if(FormValidate(0)){
			    setTimeout("form.submit();",100);
			}
			return false;
		}
		  ${object}
		  
		 function select_orgPar(btn,vname,vid){
			 var nameTarget= eval("form."+vname);
			 var srcTarget=eval("form."+vid);
			 var sUrl="${ctx}/system/organ.shtml?action=seletor";
			selectOrgan(btn,nameTarget,srcTarget,false,sUrl,'${ctx}');	
		} 
	</script>
</html>

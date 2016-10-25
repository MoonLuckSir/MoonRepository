<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<%@ include file="/pages/judicial/selcon/selconpar.jsp"%>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" type="text/css" href="${ctx}/styles/theme/css/style.css">
		<link rel="stylesheet" type="text/css"href="${ctx}/styles/theme/css/tools.css">
		<%@ include file="/commons/jslibs.jsp"%>
		<script type="text/javascript" src="${ctx}/scripts/util/calendar.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/yt/organ-selector.js"></script>
		<script type="text/javascript" src="${ctx}/scripts/yui/logger-min.js"></script>
		<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/treeview/treeview.css" />
		<script type="text/javascript" src="${ctx}/scripts/yui/treeview-min.js"></script>
		<title>历史查询</title>
	</head>
	<body>
		<div class="lst_area">
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
						action="${ctx}/judicial/ddbbzx.shtml?action=toViewREPJG">
						<table width="100%" border="0" cellspacing="1">
						<input type="hidden" name="REPMOD_NO" value="${REPMOD_NO}">
						<input type="hidden" name="REP_DESC" value="${REP_DESC}">
						<input type="hidden" name="REP_SQL" value="${REP_SQL}">
						<input type="hidden" name="JOIN_SQL" value="${JOIN_SQL}">
						<input type="hidden" name="JOIN_SQL2" value="${JOIN_SQL2}">
						<input type="hidden" name="REPCON" value="${REPCON}">
						<input type="hidden" name="repName" value="${repName}">
						<input type="hidden" name="CONS_PAR" value="${CONS_PAR}">
						<input type="hidden" name="REQ_NO" value="${REQ_NO}">
						<input type="hidden" name="MKLX" value="${MKLX}">
						<input type="hidden" name="usrId" value='${usrId}'>
		                <input type="hidden" name="orgNo" value='${orgNo}'>
		                <input type="hidden" name="xxx" value='${orgNobf}'>
		                <input type="hidden" name="groupvalue" value=''>
		                <input type="hidden" name="params" value ="${params}">
		                <input type="hidden" name="MSITCD" value ="${MSITCD}">
		                <input type="hidden" name="MSCTNO" value ="${MSCTNO}">
		                <input type="hidden" name="endDateId" value='${endDateId}'>
		                <input type="hidden" name="JZid" value='${JZid}'>
		                <input type="hidden" name="dateId" value='${dateId}'>
		                <input type="hidden" name="StartDateId" value='${StartDateId}'>
		                <input type="hidden" name="backFlg" value='${backFlg}'>
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
									<input type="button" class="btn" onClick="doSave()" value="查 询"> 
									&nbsp;&nbsp;
									<c:if test="${MKLX ne 'repList'}">&nbsp;&nbsp;
									<input type="button" class="btn" value="关 闭"
										onclick="window.close();">
									</c:if>
									
									<c:if test="${MKLX eq 'repList'}">
										<c:if test="${backFlg ne '1'}">&nbsp;&nbsp;
										<input type="button" class="btn" value="返 回"
											onclick="doBack();">
										</c:if>
									</c:if>
									
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
		</div>
	</body>
	<script type="text/javascript">InitFormValidator(0);${CONS_PAR}
	</script>
	<script type="text/javascript">
	 
	 
	    function doload()
	    {
	       ${CONS_PAR}
	      var frm=document.getElementsByName("dogroup");
	      var mklx='${MKLX}';
	      if(frm!=null)
	      {
	        var flag='0';
	        for(i=0;i<frm.length;i++)
	        {
	           if(frm[i].checked)
	           {
	            frm[i].onclick();
	            flag='1';
	           }
	           if(mklx!='repList')
	           {
	             frm[i].disabled='true';
	           }
	          
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
		}
		
		
	    ${object}
	    function select_orgPar(btn,vname,vid){
			 var nameTarget= eval("form."+vname);
			 document.form.xxx.value=document.form.orgNo.value
			 var srcTarget=eval("form."+vid);
			 var sUrl="${ctx}/system/organ.shtml?action=seletor";
			selectOrgan(btn,nameTarget,srcTarget,false,sUrl,'${ctx}');	
		} 
		
	   function doBack(){
	      window.location="${ctx}/judicial/ddbbzx.shtml?action=torepList&usrId=${usrId}&orgNo=${orgNo}";
		}
	
		
	</script>
</html>

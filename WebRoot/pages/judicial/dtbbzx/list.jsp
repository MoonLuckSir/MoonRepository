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
	<body onload="dojs();">
	  <div class="lst_area">
	  <form name="form" method="post" action="${ctx}/judicial/ddbbzx.shtml?action=exportCSV" onsubmit="return doSave();" >
	  <table width="100%" border="0" cellpadding="1" cellspacing="1">
	         <input type="hidden" name="REQ_NO" value="${REQ_NO}">
			 <input type="hidden" name="REPCON" value="${REPCON}">
	         <input type="hidden" name="REP_SQL" value="${REP_SQL}">
	         <input type="hidden" name="JOIN_SQL" value="${JOIN_SQL}">
	         <input type="hidden" name="JOIN_SQL2" value="${JOIN_SQL2}">
	         <input type="hidden" name="REPMOD_NO" value="${REPMOD_NO}">
	         <input type="hidden" name="repName" value="${repName}">
	         <input type="hidden" name="usrId" value="${usrId}">
	         <input type="hidden" name="orgNo" value="${orgNo}">
	         <input type="hidden" name="CONS_PAR" value="${CONS_PAR}">
	         <input type="hidden" name="CONJG" value="${CONJG}">
	         <input type="hidden" name="LOGID" value="${LOGID}">
	         <input type="hidden" name="params" value ="${params}">
	         <input type="hidden" name="MSITCD" value ="${MSITCD}">
		     <input type="hidden" name="MSCTNO" value ="${MSCTNO}">
	         <input type="hidden" name="backFlg" value="${backFlg}">
	         <input type="hidden" name="MKLX" value="repList"> 
            <tr>
				<td height="35" align="center" valign="middle"
					class="detail_title" colspan='2'>
					${repName}
				</td>
			</tr>
			<tr>
				<td height="20" align="left" width='90%' valign="middle">
					${Conditions}
				</td>
				<c:if test="${flg eq '3'}">
				<td height="20" align="right" width='5%' valign="middle">
					<input type="button" class="btn" onClick="openToTZSSX();"  value="存款余额">
				</td>
				</c:if>
				<c:if test="${flg eq '4'}">
				<td height="20" align="right" width='5%' valign="middle">
					<input type="button" class="btn" onClick="openPostToTZSSX();"  value="通知单">
				</td>
				</c:if>
				<c:if test="${flg eq '1'}">
					<td height="20" align="right" width='5%' valign="middle">
						<input type="button" class="btn" onClick="javascript:print();"  value="打 印">
					</td>
					<td height="20" align="right" width='5%' valign="middle">
						<input type="button" class="btn" onclick="doSave();" value="导 出">
					</td>
				</c:if>
				<c:if test="${MKLX eq 'repList'}">&nbsp;&nbsp;
					<td height="20" align="right" width='5%' valign="middle">
						<input type="button" class="btn" value="返 回" onclick="doback()">
					</td>
				</c:if>
			</tr>
		</table>	
		<table width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr>
				<c:if test="${flg eq '3'}">
				<th width="60">
					<input type="checkbox" onClick="checkAllStates('custChk',this.checked)"/>全选
				</th>
				</c:if>
				<c:forEach items="${COLList}" var="cols" varStatus="col" >
				<th  width="${cols.WIDTH}" height="${cols.HEIGHT}"  align='center' nowrap >
					${cols.COLMX_NAME}
				</th>
				</c:forEach>
			</tr>
			<c:forEach items="${page.result}" var="rst" varStatus="row">
				<tr class="list_row">
				<c:if test="${flg eq '3'}">
				  <td>
					<div title="${rst.RN}" style="width: 60; overflow: hidden; text-overflow: ellipsis; white-space: nowrap;">
						<input type="checkbox" name="custChk" value="${rst.RN}">${rst.RN}
					</div>
				  </td>	
				</c:if>  					
				  <c:forEach items="${COLList}" var="vcol"  >
					<td height="25"  nowrap  height="${vcol.HEIGHT}" align="${vcol.ALIGN}">
					<c:set var="tempV"  value="${vcol.PHYSICAL_NAME}"/> 
					<c:if test="${flg eq '2'}"><a href="javascript:selectReport('${rst['PARAMS']}','${rst['MSCTNO']}','${rst['MSITCD']}')"></c:if> ${rst[tempV]} <c:if test="${flg eq '2'}"></a></c:if>
					</td>
				</c:forEach>
				</tr>
			</c:forEach>
			<c:if test="${empty page.result}">
				<tr>
					<td height="25" colspan="25" align="center" class="lst_empty">
						&nbsp;无相关信息&nbsp;
					</td>
				</tr>
			</c:if>
		</table>
		</form>
		<table width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
			<tr>
				<td height="30" align="center">
					<table width="100%" height="100%" border="0" cellpadding="0"
						cellspacing="0" class="lst_toolbar">
						<tr>
							<td>
								<form action="${ctx}/judicial/ddbbzx.shtml?action=toViewREPJG"
									method="post" name="listForm">
									 <input type="hidden" name="REQ_NO" value="${REQ_NO}">
			                         <input type="hidden" name="SELTYPE" value="paramCover">
			                         <input type="hidden" name="REPCON" value="${REPCON}">
	                                 <input type="hidden" name="REP_SQL" id ="REP_SQL" value="${REP_SQL}">
	                                 <input type="hidden" name="JOIN_SQL" id ="JOIN_SQL" value="${JOIN_SQL}">
	                                 <input type="hidden" name="JOIN_SQL2" id ="JOIN_SQL2" value="${JOIN_SQL2}">
	                                 <input type="hidden" name="REPMOD_NO" value="${REPMOD_NO}">
	                                 <input type="hidden" name="repName" value="${repName}">
	                                 <input type="hidden" name="usrId" value="${usrId}">
	                                 <input type="hidden" name="orgNo" value="${orgNo}">
	                                 <input type="hidden" name="CONS_PAR" value="${CONS_PAR}">
	                                 <input type="hidden" name="CONJG" value="${CONJG}">
	                                 <input type="hidden" name="LOGID" value="${LOGID}">
	                                 <input type="hidden" name="isLog" value="${isLog}">
	                                 <input type="hidden" name="params" value ="${params}">
	         						 <input type="hidden" name="MSITCD" value ="${MSITCD}">
		     						 <input type="hidden" name="MSCTNO" value ="${MSCTNO}">
		     						 <input type="hidden" name="tzmbbm" value="${tzmbbm}">
	                                 <input type="hidden" name="flg" value="${flg}">
	                                 <input type="hidden" name="MKLX" value="repList"> 
									 <input type="hidden" name="pageNo">
									 <input type="hidden" name="resource" value="${resource}">
									 ${page.footerHtml}
								</form>
							</td>
							<td align="right">
								${page.toolbarHtml}
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>	
	<script type="text/javascript">
	 function dojs()
	 {
	   ${dojs}
	 }
	 
	 function doSave(){
	   setTimeout("form.submit();",100);
	 }
	 function doback(){
	    form.action="${ctx}/judicial/ddbbzx.shtml?action=toQuery";
	    setTimeout("form.submit();",100);
	 }
	 </script>
	 
	  <form name="oForm" id="oForm" method="post" action=""  target="MenuPopup">
	  		<input type="hidden" name="sql" id ="sql" value="${REP_SQL}"/>
	  		<input type="hidden" name="JOIN_SQL"  value="${JOIN_SQL}"/>
	  		<input type="hidden" name="JOIN_SQL2"  value="${JOIN_SQL2}"/>
	  	     <input type="hidden" name="MSITCD" value ="${MSITCD}">
		     <input type="hidden" name="MSCTNO" value ="${MSCTNO}">
		     <input type="hidden" name="REPMOD_NO" value ="${REPMOD_NO}">
		     <input type="hidden" name="resource" value="${resource}">
	        <input type="hidden" name="rownum" id ="rownum"/>
	  </form>
	  
       	<form method="post" name="goform" action="" target="MenuPopup1">
			<input type="hidden" name="REPMOD_NO" value="${tzmbbm}">
			<input type="hidden" name="MKLX" value='repList'>
			<input type="hidden" name="usrId" value='${usrId}'>
		    <input type="hidden" name="orgNo" value='${orgNo}'>
		    <input type="hidden" name="backFlg" value="1">
		    <input type="hidden" name="params" id ="params">
		    <input type="hidden" name="MSITCD" id ="MSITCD">
		    <input type="hidden" name="MSCTNO" id ="MSCTNO">
		</form>
	</body>
	<script type="text/javascript">
	 function selectReport(params,MSCTNO,MSITCD){
	 	document.goform.params.value=params;
	 	document.goform.MSITCD.value=MSITCD;
	 	document.goform.MSCTNO.value=MSCTNO;
		document.goform.action = "${ctx}/judicial/ddbbzx.shtml?action=toQuery";
		document.goform.submit();
	 }
	 
     function doexport(){ 
		 var sUrl="${ctx}/judicial/ddbbzx.shtml?action=exportCSV&REPCON=${REPCON}&LOGID=${LOGID}&REP_SQL=${REP_SQL}&REPMOD_NO=${REPMOD_NO}&usrId=${usrId}&orgNo=${orgNo}&JOIN_SQL=${JOIN_SQL}&JOIN_SQL2=${JOIN_SQL2}";   
		    var request = YAHOO.util.Connect.asyncRequest('POST', sUrl, {
	    			  //请求正常时调用方法
					  success:function(o){
					   var retrunval= o.responseText;
					   alert(retrunval);
					   if(retrunval=='1')
					   {
					   alert('系统正忙，请稍后再试！');
					   }
					   if(retrunval=='3')
					   {
					     alert('访问交易控制器出错！');
					   }
					   
					  },
	  				failure:function(o){
							if(o.responseXML != undefined){	
								alert('导出失败！');
							}
					}
			});
		}	
		
		function openToTZSSX(){
			url ="${ctx}/judicial/ddbbzx.shtml?action=toTZSSX1";
			openPostWindow(url);
		} 
		
		function openPostWindow(url){
		   var array = object_selected('custChk');
		   if(array.length==0){
			   showMsgPanel("请选择一条记录！");
			    return;
		    }
		    document.getElementById("rownum").value= array.join();
		
		    var oForm = document.getElementById("oForm");
		    oForm.action = url;
		  	//oForm.attachEvent("onsubmit",function(){openWindow();});
		    oForm.submit();
		}
		
		function openPostToTZSSX(){
			var oForm = document.getElementById("oForm");
			oForm.action = "${ctx}/judicial/ddbbzx.shtml?action=toTZSSX";
		  	//oForm.attachEvent("onsubmit",function(){openWindow();});
		    oForm.submit();
		}
		
		
		function openWindow(){
			var chasm = screen.availWidth;
			var mount = screen.availHeight-40;
			var windowprops = "status=no,location=no,scrollbars=auto,menubars=no,toolbars=no,resizable=no,width=" 
							  + chasm + ",height=" + mount +" ',left=0,top=0";
			window.open("about:blank", "MenuPopup", windowprops);
		}
		
		
		function object_selected(name){
			var array = new Array();
			var j =0;
			var chks = document.getElementsByName(name);
			for(var i=0;i < chks.length;i++){
				if(chks[i].checked==true){
					array[j++] = chks[i].value;
				}
			}
			return array;
		}
		
		function checkAllStates(name, states) {
			var chks = document.getElementsByName(name);
			for (var i = 0, j = chks.length; i < j; i++) {
				if (chks[i].disabled == true)
					continue;
				chks[i].checked = states;
			}
		}
		
	</script>
</html>

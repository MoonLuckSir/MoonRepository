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
		<title>历史查询模板列表</title>
	</head>
	<body>
		<table width="100%" height="100%" border="0" cellspacing="0"
			cellpadding="0" class="panel">
			<tr>
				<td height="27">
					<table width="100%" height="100%" cellspacing="0" cellpadding="0"
						class="wz">
						<tr>
							<td width="28" align="center">
								<label class="wz_img"></label>
							</td>
							<td>
								当前位置：报表查询 &gt;&gt; 司法查询定制设计 &gt;&gt; 司法查询模板
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
				<td height="20">
					<form action="${ctx}/judicial/lscxmb.shtml?action=toList" method="post"
						name="qryForm" onSubmit="return doQuery();">
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>
								<td width="100" align="right">
									司法查询名称：
								</td>
								<td width="120">
									<input name="REP_NAME" type="text" autocheck="true" style="width:110px;"
										pattern="string" maxlength="20" label="历史查询名称">
									<script type="text/javascript">
										qryForm.REP_NAME.value="${param.REP_NAME}";
									</script>
								</td>	
								<td width="70" align="right">
									状态：
								</td>
								<td width="120">						
									<select name="STATE" style="width:110px;">
										<option value=""></option>
										<option value="冻结">冻结</option>
										<option value="启用">启用</option>
									</select>
									<script type="text/javascript">
										qryForm.STATE.value="${param.STATE}";
									</script>				
								</td>	
								<td align="left"> 
									<input type="submit" class="btn" value="查 询"> 
									<input type="reset" class="btn" value="重 置"> 
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr><td class="rowSplit"></td></tr>
			<tr>
				<td height="30">
					<div class="lst_title">
						<input type="button" class="btn" value="增 加" onClick="addEntry();" />
						<input type="button" class="btn" value="修 改" onClick="modiEntry();" />
						<input type="button" class="btn" value="删 除" onClick="deleteEntry();" />
						&nbsp;
						<input type="button" class="btn" value="条件设置" onClick="con_set();" />
						<input type="button" class="btn" value="抬头设置" onClick="hea_set();" />
						
						<input type="button" class="btn" value="展现设置" onClick="show_set();" />
						<input type="button" class="btn" value="导出设置" onClick="export_set();" />
						&nbsp;
						<input type="button" class="btn" value="启用" onClick="enable_set();" /> 
						<input type="button" class="btn" value="冻结" onClick="freeze_set();" />
					
						
						<!--  input type="button" class="btn" value="生成SQL" onclick="scSQL();" /-->
					</div>
				</td>
			</tr>
			<tr>
				<td valign="top">
					<div class="lst_area">
						<table width="100%" border="0" cellpadding="1" cellspacing="1"
							class="lst">
							<tr class="fixedRow">
								
								<th width="20">
								</th>
								<th width="100" align="center">
									司法查询模板编号
								</th>
								<th width="100" align="center">
									交易编号
								</th>
								<th width="240" align="center">
									交易名称
								</th>
								<!--  
								<th width="100" nowrap align="center">
									查询语句
								</th>
								<th width="100" align="center">
									来源语句
								</th>
								<th width="100" align="center">
									排序语句
								</th>
								<th width="100" align="center">
									分隔符
								</th>	
								<th width="100" align="center">
									输出类型
								</th>
								-->
								
								<th  align="center">
									制单人
								</th>
								
								<th width="100" align="center">
									制单机构
								</th>
								<th width="100" align="center">
									制单时间
								</th>
								<th  align="center">
									状态
								</th>		
								<th width="50" align="center">
									<font color="red">详细信息</font>
								</th>																																							
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" id="${r}" onMouseOver="mover(this)" onMouseOut="mout(this)" onClick="setEid(document.getElementById('eid${rr}'));">
									<th width="20">
										<input type="radio" name="eid" id="eid${rr}" value="${rst.REPMOD_NO}|${rst.STATE} "
											onclick="setEid(this);">
									</th>
									<td align="left">
										${rst.REPMOD_NO }
									</td>
									<td>
										${rst.REP_NO}
									</td>
									<td align="left">
										${rst.REP_NAME}
									</td>
									<!--
									<td align="left">
										${rst.SELECT_SQL}
									</td>
									<td align="left">
										${rst.FROM_SQL}
									</td>
									<td align="left">
										${rst.ORDER_SQL}
									</td>
									<td align="left">
										${rst.ITERATOR}
									</td>
									<td align="left">
										${rst.OUTPUT}
									</td>
									-->
									
									<td align="left">
										${rst.CHECKIN_USER}
									</td>
									
									<td align="left">
										${rst.CHECKIN_ORGAN}
									</td>
									<td align="left">
										${rst.CHECKIN_TIME}
									</td>
									<td align="left">
										${rst.STATE}
									</td>
									<td align="left" nowrap> 
										<a href="##" onClick="javascript:toDetail('${rst.REPMOD_NO }')">详细信息</a>
									</td>
								</tr>
							</c:forEach>
							<c:if test="${empty page.result}">
								<tr>
									<td height="25" colspan="13" align="center" class="lst_empty">
										&nbsp;无相关信息&nbsp;
									</td>
								</tr>
							</c:if>
						</table>
					</div>
				</td>
			</tr>
			<tr>
				<td height="30" align="center">
					<table width="100%" height="100%" border="0" cellpadding="0"
						cellspacing="0" class="lst_toolbar">
						<tr>
							<td>
								<form action="${ctx}/judicial/lscxmb.shtml?action=toList"
									method="post" name="listForm">
									<input type="hidden" name="pageNo">
									${paramCover.unCoveredForbidInputs } ${page.footerHtml}
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
		<form method="post" name="detailForm">
			<input type="hidden" name="_backUrl" value="${ctx}/judicial/lscxmb.shtml?action=toList">
			<input type="hidden" name="REPMOD_NO">
			<input type="hidden" name="usrId" value='${usrId}'>
		    <input type="hidden" name="orgNo" value='${orgNo}'>
			${paramCover.coveredInputs}
		</form>
		<input type="hidden" name="tmpId">
		<input type="hidden" name="tmpStus">
	</body>
	<%@ include file="/commons/jslibs4tree.jsp"%>
	<script type="text/javascript">		
		function doQuery(){			
			if(FormValidate(qryForm)){
				setTimeout("qryForm.submit()",100);
			}
			return false;
		}	
		var tmpId = g("tmpId");
		var tmpStus = g("tmpStus");
		function setEid(obj){
			obj.checked="true";
			var x = obj.value.trim().length;
			var wz = obj.value.indexOf("|");
			tmpId.value=obj.value.substr(0,wz);
			tmpStus.value = obj.value.substr(wz+1,x);
	//		alert(tmpId.value+"@@@@"+ tmpStus.value); 
		}
	
		function checkEid(REPMOD_NO){		
			if(REPMOD_NO==null || ""==REPMOD_NO.trim()){
				showErrorMsg("请选择模板！");
				return false;
			}
			return true;
		}		
		function todo(act,REPMOD_NO){
			showWaitPanel('');
			detailForm.action=act;
			detailForm.REPMOD_NO.value=REPMOD_NO;
			setTimeout("detailForm.submit()",100);	
			try{ tmpId.parentNode.focus(); }catch(e){}
		}		
		function deleteEntry(){ 
			var STATE = tmpStus.value.trim();
		        if(STATE=='启用')
		        {
		         showErrorMsg("启用状态不能删除！");
		         return;
		    }
			var REPMOD_NO = tmpId.value;
			if(!checkEid(REPMOD_NO))return;
			if(confirm("您确信要删除该条信息吗？")){
				todo("${ctx}/judicial/lscxmb.shtml?action=delete",REPMOD_NO);
			}
		}
		function modiEntry(){
			var REPMOD_NO = tmpId.value;
			var STATE = tmpStus.value.trim();
	        if(STATE=='启用')
	        {
	         showErrorMsg("启用状态不能修改！");
	         return;
	        }
			if(!checkEid(REPMOD_NO))return;
			todo("${ctx}/judicial/lscxmb.shtml?action=toUpdate",REPMOD_NO);
		}
		function addEntry(){
			todo("${ctx}/judicial/lscxmb.shtml?action=toAdd","");
		}
		function viewEntry(){
			var REPMOD_NO = tmpId.value;
			if(!checkEid(REPMOD_NO))return;
			todo("${ctx}/judicial/lscxmb.shtml?action=toView",REPMOD_NO);
		}
		function toDetail(REPMOD_NO ){
			window.open("${ctx}/judicial/lscxmb.shtml?action=toView&&REPMOD_NO="+REPMOD_NO,
			"历史查询模板","scrollbars=yes,resizable=yes,width=745,height=500,left=263,top=207"); //menubar=yes,
		}
		
		function con_set()
		{
			var STATE = tmpStus.value.trim();
		        if(STATE=='启用')
		        {
		         showErrorMsg("启用状态不能条件设置！");
		         return;
		    }		
			var REPMOD_NO = tmpId.value;
			if(!checkEid(REPMOD_NO))return;
			todo("${ctx}/judicial/mbtjsz.shtml?action=toList",REPMOD_NO);
		}
		
	   function hea_set()
		{
			var STATE = tmpStus.value.trim();
		        if(STATE=='启用')
		        {
		         showErrorMsg("启用状态不能抬头设置！");
		         return;
		    }
			var REPMOD_NO = tmpId.value;
			if(!checkEid(REPMOD_NO))return;
			todo("${ctx}/judicial/lscxhea.shtml?action=toList",REPMOD_NO);
		}
		
		
		function show_set(){
			var STATE = tmpStus.value.trim();
		        if(STATE=='启用')
		        {
		         showErrorMsg("启用状态不能展现设置！");
		         return;
		    }
			var REPMOD_NO = tmpId.value;
			if(!checkEid(REPMOD_NO))return;
			todo("${ctx}/judicial/lscxcol.shtml?action=toList",REPMOD_NO);
		}
		function export_set(){
			var STATE = tmpStus.value.trim();
		        if(STATE=='启用')
		        {
		         showErrorMsg("启用状态不能导出设置！");
		         return;
		    }
			var REPMOD_NO = tmpId.value;
			if(!checkEid(REPMOD_NO))return;
			todo("${ctx}/judicial/lscxexp.shtml?action=toList",REPMOD_NO);
		}
		function enable_set(){ 
			var REPMOD_NO = tmpId.value;
			var STATE = tmpStus.value.trim();
	        if(STATE!='冻结'&&STATE!='制定')
	        {
	         showErrorMsg("冻结，制定的状态才能启用！");
	         return;
	        }
			if(!checkEid(REPMOD_NO))return;
			todo("${ctx}/judicial/lscxmb.shtml?action=doqy",REPMOD_NO);
			
		}
		function freeze_set(){ 
			var REPMOD_NO = tmpId.value;
			var STATE = tmpStus.value.trim();
			if(STATE!='启用')
	        {
	         showErrorMsg("启用状态才能冻结！");
	         return;
	        }
			if(!checkEid(REPMOD_NO))return;
			todo("${ctx}/judicial/lscxmb.shtml?action=dodj",REPMOD_NO);
			
		}
		function scSQL(){ 
			var REPMOD_NO='11111';
			todo("${ctx}/judicial/lscxmb.shtml?action=scSQL",REPMOD_NO);
			
		}
		
		
		function doMoBan()
		{
	
		var REPMOD_NO = tmpId.value;
		var url	 = "${ctx}/judicial/lscxmb.shtml?action=toFlag";
			url += "&REPMOD_NO="+REPMOD_NO;
			url += "&REP_NO=1";
	
		location.href=url;
		
		}
		
	
		
		
	</script>
</html>

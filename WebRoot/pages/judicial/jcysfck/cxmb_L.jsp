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
								当前位置：司法查控 &gt;&gt; 查询模板列表 
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
					<form action="${ctx}/judicial/sfck.shtml?action=toCxmbList" method="post"
						name="qryForm" onSubmit="return doQuery();">
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>
								<td width="100" align="right">
									模板名称：
								</td>
								<td width="120">
									<input name="CXMB_NAME" type="text" autocheck="true" style="width:110px;"
										pattern="string" maxlength="20" label="查询模板名称">
									<script type="text/javascript">
										qryForm.CXMB_NAME.value="${param.CXMB_NAME}";
									</script>
								</td>	
								<td width="70" align="right">
									状态：
								</td>
								<td width="120">						
									<select name="CXMB_STU" style="width:110px;">
										<option value="">--请选择--</option>
										<option value="0">冻结</option>
										<option value="1">启用</option>
									</select>
									<script type="text/javascript">
										qryForm.CXMB_STU.value="${param.CXMB_STU}";
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
						<!--  <input type="button" class="btn" value="设置导出模板" onClick="exp_set();" />-->
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
									查询模板编号
								</th>
								<th width="140" align="center">
									模板名称
								</th>
								<th  align="center">
									模板创建人
								</th>
								
								<th  align="center">
									模板创建机构
								</th>
								
								<th  align="center">
									模板创建日期
								</th>
								<th  align="center">
									查询模板归属部门
								</th>
								
								<th  align="center">
									模板状态
								</th>		
								<th width="140" align="center">
									描述
								</th>																																					
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" id="${r}" onMouseOver="mover(this)" onMouseOut="mout(this)" onClick="setEid(document.getElementById('eid${rr}'));">
									<th width="20">
										<input type="radio" name="eid" id="eid${rr}" value="${rst.CXMB_ID}|${rst.CXMB_STU } "
											onclick="setEid(this);">
									</th>
									<td align="center">
										${rst.CXMB_ID }
									</td>
									<td align="center">
										${rst.CXMB_NAME }
									</td>
									<td align="center">
										${rst.CXMB_USER}
									</td>
									<td align="center">
										${rst.CXMB_ORG}
									</td>
									
									<td align="center">
										<fmt:formatDate value="${rst.CXMB_CRETIME}" type="both"/> 
									</td>
									
									<td align="center">
										${rst.CXMB_DEPT}
									</td>
									<td align="center">
										<c:if test="${rst.CXMB_STU eq 0}">
											<font color="red">冻结</font>
										</c:if>
										<c:if test="${rst.CXMB_STU eq 1}">
											<font color="green">启用</font>
										</c:if>
									</td>
									<td align="center">
										${rst.CXMB_DESC}
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
								<form action="${ctx}/judicial/sfck.shtml?action=toCxmbList"
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
			<input type="hidden" name="_backUrl" value="${ctx}/judicial/sfck.shtml?action=toCxmbList">
			<input type="hidden" name="CXMB_ID">
			<input type="hidden" name="usrId" value='${usrId}'>
		    <input type="hidden" name="orgNo" value='${orgNo}'>
			${paramCover.coveredInputs}
		</form>
		<input type="hidden" name="tmpId" >
		<input type="hidden" name="tmpStus" >
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
	
		function checkEid(CXMB_ID){		
			if(CXMB_ID==null || ""==CXMB_ID.trim()){
				showErrorMsg("请选择模板！");
				return false;
			}
			return true;
		}		
		function todo(act,CXMB_ID){
			showWaitPanel('');
			detailForm.action=act;
			detailForm.CXMB_ID.value=CXMB_ID;
			setTimeout("detailForm.submit()",100);	
			try{ tmpId.parentNode.focus(); }catch(e){}
		}		
		function deleteEntry(){ 
			var STATE = tmpStus.value.trim();
		        if(STATE=='1')
		        {
		         showErrorMsg("启用状态不能删除！");
		         return;
		    }
			var CXMB_ID = tmpId.value;
			if(!checkEid(CXMB_ID))return;
			if(confirm("您确信要删除该条信息吗？")){
				todo("${ctx}/judicial/sfck.shtml?action=delCxmb",CXMB_ID);
			}
		}
		function modiEntry(){
			var CXMB_ID = tmpId.value;
			var STATE = tmpStus.value.trim();
	        if(STATE=='1')
	        {
	         showErrorMsg("启用状态不能修改！");
	         return;
	        }
			if(!checkEid(CXMB_ID))return;
			todo("${ctx}/judicial/sfck.shtml?action=toUpdateCxmb",CXMB_ID);
		}
		function addEntry(){
			todo("${ctx}/judicial/sfck.shtml?action=toAddCxmb","");
		}
		function viewEntry(){
			var CXMB_ID = tmpId.value;
			if(!checkEid(CXMB_ID))return;
			todo("${ctx}/judicial/lscxmb.shtml?action=toView",CXMB_ID);
		}
		function toDetail(CXMB_ID ){
			window.open("${ctx}/judicial/lscxmb.shtml?action=toView&&CXMB_ID="+CXMB_ID,
			"历史查询模板","scrollbars=yes,resizable=yes,width=745,height=500,left=263,top=207"); //menubar=yes,
		}
		
		function con_set()
		{
			var STATE = tmpStus.value.trim();
		        if(STATE=='1')
		        {
		         showErrorMsg("启用状态不能设置条件！");
		         return;
		    }		
			var CXMB_ID = tmpId.value;
			if(!checkEid(CXMB_ID))return;
			todo("${ctx}/judicial/sfck.shtml?action=toConList",CXMB_ID);
		}
		
	   function exp_set()
		{
			var STATE = tmpStus.value.trim();
		        if(STATE=='1')
		        {
		         showErrorMsg("启用状态不能设置导出模板！");
		         return;
		    }
			var CXMB_ID = tmpId.value;
			if(!checkEid(CXMB_ID))return;
			todo("${ctx}/judicial/sfck.shtml?action=toDcmbList",CXMB_ID);
		}
		
		
	
		function enable_set(){ 
			var CXMB_ID = tmpId.value;
			var STATE = tmpStus.value.trim();
	        if(STATE=='1')
	        {
	         showErrorMsg("该模板已启用！");
	         return;
	        }
			if(!checkEid(CXMB_ID))return;
			todo("${ctx}/judicial/sfck.shtml?action=doqy",CXMB_ID);
			
		}
		function freeze_set(){ 
			var CXMB_ID = tmpId.value;
			var STATE = tmpStus.value.trim();
			if(STATE=='0')
	        {
	         showErrorMsg("该模板已冻结！");
	         return;
	        }
			if(!checkEid(CXMB_ID))return;
			todo("${ctx}/judicial/sfck.shtml?action=dodj",CXMB_ID);
			
		}
		function scSQL(){ 
			var CXMB_ID='11111';
			todo("${ctx}/judicial/lscxmb.shtml?action=scSQL",CXMB_ID);
			
		}
		
		
		function doMoBan()
		{
	
		var CXMB_ID = tmpId.value;
		var url	 = "${ctx}/judicial/lscxmb.shtml?action=toFlag";
			url += "&CXMB_ID="+CXMB_ID;
			url += "&REP_NO=1";
	
		location.href=url;
		
		}
		
	
		
		
	</script>
</html>

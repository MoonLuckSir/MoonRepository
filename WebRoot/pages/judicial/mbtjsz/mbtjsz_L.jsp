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
								当前位置：报表查询 &gt;&gt; 司法查询定制设计 &gt;&gt; 司法查询模板 &gt;&gt;模板条件明细
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
					<form action="${ctx}/judicial/mbtjsz.shtml?action=toList" method="post"
						name="qryForm" onSubmit="return doQuery();">
						<table width="100%" border="0" cellpadding="2"
							cellspacing="0" class="qry">
							<tr>
								<td width="100" align="right">
									报表名称编号：
								</td>
								<td width="120">
									<input name="REPMOD_NO" type="text" autocheck="true" style="width:110px"
										pattern="string" label="报表名称编号">
									<script type="text/javascript">
										qryForm.REPMOD_NO.value="${param.REPMOD_NO}";
									</script>
								</td>	
								<td width="70" align="right">
									条件名称：
								</td>
								<td width="120">						
									<input name="CONMX_NAME" type="text" label="条件名称" style="width:110px"/>
								</td>	
								<td width="70" align="right">
									数据类型：
								</td>
								<td width="120">						
									<select name="CONMX_TYPE" style="width:110px">
										<option value="字符串" >字符串</option>
										<option value="时间" >时间</option>
										<option value="数字" >数字</option>
									</select>
						
								</td>	
								<td align="left"> 
									<input type="submit" class="btn" value="查 询"> 
									<input type="reset"  class="btn" value="重 置"> 									
								</td>
								<td align="right">
									<input  type="button" class="btn" value="返 回" onClick="backurl();">
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
						<input type="button" class="btn" value="设计规则" onClick="sjgz();" />
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
									条件编号
								</th>
								<th width="100" align="center">
									条件名称
								</th>
								<th width="240" align="center">
									司法查询模板编号
								</th>
								<th width="100" nowrap align="center">
									数据类型
								</th>
								<th width="100" align="center">
									输入框类型
								</th>
								<th width="100" align="center">
									组件名称
								</th>
								<th width="100" align="center">
									物理名称
								</th>
								
								<th width="100" align="center">
									默认值
								</th>
								<th width="100" align="center">
									运算符
								</th>
								<th width="100" align="center">
									排序号
								</th>	
								<th width="50" align="center">
									<font color="red">详细信息</font>
								</th>
																																														
							</tr>
							<c:forEach items="${page.result}" var="rst" varStatus="row">
								<c:set var="r" value="${row.count % 2}"></c:set>
								<c:set var="rr" value="${row.count}"></c:set>
								<tr class="list_row${r}" onMouseOver="mover(this)"
									onmouseout="mout(this)"  onclick="setEid(document.getElementById('eid${rr}'));">
									
									<th width="20">
										<input type="radio" name="eid" id="eid${rr}" value="${rst.CONMX_NO }|${rst.CONMX_NAME}"
											onclick="setEid(this);">
									</th>
									<td align="left">
										${rst.CONMX_NO }
									</td>
									<td>
										${rst.CONMX_NAME}
									</td>
									<td align="left">
										${rst.REPMOD_NO}
									</td>
									<td align="left">
										${rst.CONMX_TYPE}
									</td>
									<td align="left">
										${rst.INPUT_TYPE}
									</td>
									<td align="left">
										${rst.INPUT_VALUE}
									</td>
									<td align="left">
										${rst.PHYSICAL_NAME}
									</td>
									
									<td align="left">
										${rst.DEFAULTVAL}
									</td>
									<td align="left">
										${rst.OPERATOR}
									</td>
									<td align="left">
										${rst.ORDERXH}
									</td>
									
									<td align="left" nowrap> 
										<a href="##" onClick="javascript:toDetail('${rst.CONMX_NO }')">详细信息</a>
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
								<form action="${ctx}/judicial/mbtjsz.shtml?action=toList"
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
			<input type="hidden" name="_backUrl" value="${ctx}/judicial/mbtjsz.shtml?action=toList">
			<input type="hidden" name="CONMX_NO">
			<input type="hidden" name="REPMOD_NO">
			${paramCover.coveredInputs}
		</form>
		<input type="hidden" name="tmpId">
		<input type="hidden" name="tmpStus">
		<input type="hidden" name="tmpZbId" value="${param.REPMOD_NO}">
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
			obj.checked='true';
			var x = obj.value.trim().length;
			var wz = obj.value.indexOf("|");
			tmpId.value=obj.value.substr(0,wz);
			tmpStus.value = obj.value.substr(wz+1,x);
//			alert(tmpId.value+"@@@@"+ tmpStus.value);
		}		
		function checkEid(CONMX_NO){		
			if(CONMX_NO==null || ""==CONMX_NO.trim()){
				showErrorMsg("请选择一个对象！");
				return false;
			}
			return true;
		}	
		function checkZJEid(ZJID){		
			if(ZJID==null || ""==ZJID.trim()){
				showErrorMsg("主表ID丢失,请刷新页面！");
				return false;
			}
			return true;
		}	
		function todo(act,zbid,mxid){
			showWaitPanel('');
			detailForm.action=act;
			detailForm.REPMOD_NO.value=zbid;
			detailForm.CONMX_NO.value=mxid;
			setTimeout("detailForm.submit()",100);	
			try{ tmpId.parentNode.focus(); }catch(e){}
		}		
		function deleteEntry(){ 
			var CONMX_NO = tmpId.value;
			if(!checkEid(CONMX_NO))return;
			if(confirm("您确信要删除该条信息吗？")){
				todo("${ctx}/judicial/mbtjsz.shtml?action=delete",'',CONMX_NO);
			}
		}
		function modiEntry(){
			var CONMX_NO = tmpId.value;
			if(!checkEid(CONMX_NO))return;
			todo("${ctx}/judicial/mbtjsz.shtml?action=toUpdate",'',CONMX_NO);
		}
		function addEntry(){
			var REPMOD_NO = tmpZbId.value;
			if(!checkZJEid(REPMOD_NO))return;
			todo("${ctx}/judicial/mbtjsz.shtml?action=toAdd",REPMOD_NO,'');
		}
		function viewEntry(){
			var CONMX_NO = tmpZbId.value;
			if(!checkEid(CONMX_NO))return;
			todo("${ctx}/judicial/mbtjsz.shtml?action=toView",'',CONMX_NO);
		}
		function toDetail(CONMX_NO ){
			window.open("${ctx}/judicial/mbtjsz.shtml?action=toView&&CONMX_NO="+CONMX_NO,
			"模板条件明细","scrollbars=yes,resizable=yes,width=745,height=500,left=263,top=207"); //menubar=yes,
		}
		
		function sjgz()
		{
			var CONMX_NO = tmpId.value;
			if(!checkEid(CONMX_NO))return;
			var REPMOD_NO = tmpZbId.value;
			if(!checkEid(REPMOD_NO))return;
			todo("${ctx}/judicial/lscxrul.shtml?action=toList",REPMOD_NO,CONMX_NO); //到子页面
		}
		function backurl(){
			todo("${ctx}/judicial/lscxmb.shtml?action=toList",''); //反正到上一级：即父页面
		}

	</script>
</html>

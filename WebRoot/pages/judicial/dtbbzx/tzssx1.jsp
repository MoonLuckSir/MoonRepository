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
	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td align="center"><div id="printid"><input type="button" class="btn" onClick="javascript:prints();"  value="打 印"></div></td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
                	    <tr height="40">
							<td colspan="6">查询时间：${time}</th>
						</tr>
						<tr>
							<c:forEach items="${COLList}" var="cols" varStatus="col" >
								<th  width="${cols.WIDTH}" height="${cols.HEIGHT}"  align='center' nowrap >
									${cols.COLMX_NAME}
								</th>
							</c:forEach>
						</tr>
						<c:forEach items="${list}" var="rst" varStatus="row">
							<tr class="list_row">
							  <c:forEach items="${COLList}" var="vcol"  >
								<td height="25"
								 height="${vcol.HEIGHT}" align="${vcol.ALIGN}">
								<c:set var="tempV"  value="${vcol.PHYSICAL_NAME}"/> 
								${rst[tempV]}&nbsp;
								</td>
							  </c:forEach>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>
	</body>
	<script type="text/javascript">
		function pageSetup_Null(){
			try{
				var Wsh = new ActiveXObject("WScript.Shell");
				Wsh.RegWrite("HKEY_CURRENT_USER\\Software\\Microsoft\\Intemet Explorer\\PageSetup\\header","");
				Wsh.RegWrite("HKEY_CURRENT_USER\\Software\\Microsoft\\Intemet Explorer\\PageSetup\\footer","");
			}catch(e){}
		}
		
		pageSetup_Null();
		
		// 提交上传bad文件
		function prints(){
			document.getElementById("printid").style.display = "none";
			setTimeout("print();",100);
		}	
	</script>
</html>

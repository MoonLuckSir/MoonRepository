<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/commons/taglibs.jsp"%>
<html>
	<body onLoad="dojs();">
	<script type="text/javascript">
	<%
	String mklx=request.getParameter("mklx");
	%>
	 var mklx='<%=mklx%>'; 
	 var usrId=parent.user.userId;
	 var orgNo=parent.user.orgId;
	 function dojs()
	 {
	
	   if(mklx=='lscxsq')
	   window.location.href= '${ctx}/judicial/lscxsq.shtml?action=toList&usrId='+usrId+"&orgNo="+orgNo;
	   if(mklx=='lscxsh')
	   window.location.href= '${ctx}/judicial/lscxsh.shtml?action=toList&usrId='+usrId+"&orgNo="+orgNo;
	   if(mklx=='lscxysh')
	   window.location.href= '${ctx}/judicial/lscxysh.shtml?action=toList&usrId='+usrId+"&orgNo="+orgNo;
	   if(mklx=='lscxlb')
	   window.location.href= '${ctx}/judicial/ddbbzx.shtml?action=torepList&FLAGSTATE=1&usrId='+usrId+"&orgNo="+orgNo;
	   if(mklx=='lscxmb')
	   window.location.href= '${ctx}/judicial/lscxmb.shtml?action=toList&usrId='+usrId+"&orgNo="+orgNo;
	   if(mklx=='lsdszt')
	   window.location.href= '${ctx}/judicial/ddbbzx.shtml?action=torepList&FLAGSTATE=2&usrId='+usrId+"&orgNo="+orgNo;
	  	  
	 }
	
	 </script>
	</body>
</html>
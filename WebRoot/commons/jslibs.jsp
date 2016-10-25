<script type="text/javascript" src="${ctx}/scripts/yui/yahoo-min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/yui/event-min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/yui/connection-min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/yui/dom-min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/core/check-min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/core/validate-min.js"></script>
<script type="text/javascript" src="${ctx}/scripts/util/plus.js"></script>
<script type="text/javascript" src="${ctx}/scripts/util/jquery-1.7.2.js"></script>
<script type="text/javascript" src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>


<script type="text/javascript">
	var ctx = '${ctx}';
	try{
		if(parent && parent.user && parent.user.userId!=='${userSess.userId}'){
			//parent.location.href='${ctx}';	
		}
	}catch(e){
	}
</script>
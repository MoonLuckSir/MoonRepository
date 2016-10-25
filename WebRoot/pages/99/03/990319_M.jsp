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
		<link rel="stylesheet" type="text/css" href="${ctx}/assets/css/treeview/treeview.css" />
		<link rel="stylesheet" type="text/css" href="${ctx}/assets/tasknode.css" />
		<title>分配角色菜单</title>
	</head>
	<body>
		<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" class="panel">
			<tr>
				<td height="27">
					<table width="100%" height="100%" cellspacing="0" cellpadding="0"
						class="wz">
						<tr>
							<td width="28" align="center"><label class="wz_img"></label></td>
							<td>  
								当前位置：系统管理 &gt;&gt; 角色管理 &gt;&gt; 分配角色菜单
							</td> 
							<td width="50" align="right">&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td class="rowSplit"></td></tr> 
			<tr>
				<td>
					<table width="100%" height="100%" cellspacing="0" cellpadding="0">
						<tr>
							<td valign="top" width="30%">
								<div class="lst_area">
									<table width="100%" border="0" cellpadding="1" cellspacing="1" class="lst">
										<tr><th width="20"></th><th height="25" width="100" align="center">属性</th><th align="center">属性值</th></tr>
										<tr><th>1</th><td height="25" align="center">角色编号</td><td>${entry.ROLE_ID }</td></tr>
										<tr><th>2</th><td height="25" align="center">角色名称</td><td>${entry.ROLE_NAME }</td></tr>
										<tr><th>3</th><td height="25" align="center">角色级别</td><td>${entry.ROLE_LVL }</td></tr>
										<tr><th>4</th><td height="25" align="center">角色类别</td><td>${entry.ROLE_TYPE}</td></tr>
										<tr><th>5</th><td height="25" align="center">首页链接</td><td>${entry.INDEX_URL}</td></tr>
										<tr><th>6</th><td height="25" align="center">角色注释</td><td>${entry.ROLE_DESC }</td></tr>
									</table>
									<div style="display:none">
										<div id="oldData">${oldData}</div>
										<div id="treeData">${treeData}</div>										
										<form name="form" method="post"
											action="${ctx}/system/role.shtml?action=alotMenu">
											<input type="hidden" name="roleId" value="${param.roleId}">
											<input type="hidden" name="checkedData">
											${paramCover.unCovered_Inputs }
										</form>
									</div>
								</div>
							</td>
							<td valign="top">
								<div class="lst_area">
									<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="lst">
										<tr>
											<th height="25" align="left" nowrap>&nbsp;&nbsp;角色菜单</th>
											<th width="280" align="right" nowrap>
												<a id="expand" href="#">全部展开</a>
												<a id="collapse" href="#">全部关闭</a>
												<a id="check" href="#">选择全部</a>
												<a id="uncheck" href="#">取消全选</a>
											</th>
										</tr>
										<tr><td colspan="2" style="border-top:2 solid #eee;padding:0px 5px;">
											<div id="treeDiv1" style="overflow: auto; height: 100%; width: 100%;"></div></td></tr>
									</table>	
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td height="30">
					<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" class="lst_toolbar">
						<tr><td align="center">
							<input type="button" class="btn" value="保 存" onclick="doSave();"/> 
							<input type="button" class="btn" value="返 回" onclick="history.back();"/></td></tr>
					</table>
				</td>
			</tr> 
		</table>
	</body> 
	<%@ include file="/commons/jslibs.jsp"%>
	<script type="text/javascript" src="${ctx}/scripts/yui/treeview-min.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/yui/element-beta-min.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/yui/button-beta-min.js"></script>
	<script type="text/javascript" src="${ctx}/scripts/yui/TaskNode.js"></script> 
	<script type="text/javascript"> 
	var div = g('treeData');
	var cData = g('checkedData');	
	var oldData = g('oldData');
	var oldResults = eval("(" + oldData.innerHTML + ")");
	
	var tree, currentIconMode;
	var nodes = [];
	var nodeIndex;
	var YuiE=YAHOO.util.Event;
	function treeInit() { 
		buildRandomTaskNodeTree();
	}
	 
	YuiE.on("expand", "click", function(e) { 
		tree.expandAll();
		YuiE.preventDefault(e);
	});
	 
	YuiE.on("collapse", "click", function(e) { 
		tree.collapseAll();
		YuiE.preventDefault(e);
	});
	 
	YuiE.on("check", "click", function(e) { 
		checkAll();
		YuiE.preventDefault(e);
	});
	 
	YuiE.on("uncheck", "click", function(e) { 
		uncheckAll();
		YuiE.preventDefault(e);
	});
	  
	function buildRandomTaskNodeTree() { 
		tree = new YAHOO.widget.TreeView("treeDiv1"); 
	 
		var oResults = eval("(" + div.innerHTML + ")");
		loadNodes(tree.getRoot(), oResults);
	
		tree.subscribe("expand", function(node) {
			YAHOO.log(node.index + " was expanded", "info", "example"); 
		});
	
		tree.subscribe("collapse", function(node) {
			YAHOO.log(node.index + " was collapsed", "info", "example");
		});
	
		tree.subscribe("labelClick", function(node) {
			YAHOO.log(node.index + " label was clicked", "info", "example");
		});
	
		tree.subscribe("checkClick", function(node) {
			YAHOO.log(node.index + " check was clicked", "info", "example");
		});
	
		tree.draw();
	
		markCheckedNodes();
	}
	
	var callback = null;
	
	function onCheckClick(node) {
		YAHOO.log(node.label + " check was clicked, new state: " + node.checkState, "info", "example");
	}
	
	function checkAll() {
		var topNodes = tree.getRoot().children;
		for (var i = 0; i < topNodes.length; ++i) {
			topNodes[i].check();
		}
	}
	
	function uncheckAll() {
		var topNodes = tree.getRoot().children;
		for (var i = 0; i < topNodes.length; ++i) {
			topNodes[i].uncheck();
		}
	}
	
	function onLabelClick(node) {
		new YAHOO.widget.TaskNode("new", node, false);
		node.refresh();
		return false;
	} 
	
	function getCheckedNodes(nodes) {
		nodes = nodes || tree.getRoot().children;
		checkedNodes = [];
		for (var i = 0, l = nodes.length; i < l; i = i + 1) {
			var n = nodes[i]; 
			if (n.checkState > 0) {
				if (n.no) {
					checkedNodes.push(n.no);
				} else {
					checkedNodes.push(n.label);
				}
			}
			if (n.hasChildren()) {
				checkedNodes = checkedNodes.concat(getCheckedNodes(n.children));
			}
		}
	
		return checkedNodes;
	} 
	
	function loadNodes(node,oResults){
		for (var i=0, j=oResults.size; i<j; i++) {
	   		if(oResults.children[i]){
	   			var curNode = oResults.children[i];
	   		    var hasChildren= curNode.size > 0;
	   			var tempNode = new YAHOO.widget.TaskNode("menu"+i, node, !hasChildren);
	   			if(curNode.dsc){
	   				tempNode.label=" " + curNode.id+" | "+curNode.name+"  ("+curNode.dsc+")";	
	   			}else{
	   				tempNode.label=" " + curNode.id+" | "+curNode.name;
	   			}
	   			tempNode.no=curNode.id;
	   			if(false && curNode.img){
	   				tempNode.myimg='${ctx}/images/theme/'+curNode.img;
	   			}else{
	   				tempNode.myimg='${ctx}/images/left/house.png';
	   			} 
	   			if(hasChildren){
	   				loadNodes(tempNode,curNode);
	   			} 
	   		}					
		}	
	}
	
	function saveCheckedNodes() {
		if (cData) {
			cData.value = YAHOO.lang.dump(getCheckedNodes());
		}
	}
	function doSave(){
		showWaitPanel(); 
		saveCheckedNodes();
		setTimeout("form.submit();",fastSpeed); 
	}
	
	function markCheckedNodes() {
		if (oldResults && oldResults.size > 0) {
			var topNodes = tree.getRoot().children;
			for (var i = 0; i < topNodes.length; ++i) {
				var topNode=topNodes[i];
				if(topNode.hasChildren()){
					markCheckedChildrenNodes(topNode);
				}else{
					markCheckedNode(topNode);
				}
			}
		}
	}
	
	function markCheckedChildrenNodes(n) {
		var tempNodes = n.children;
		for (var i = 0; i < tempNodes.length; ++i) {
			if (tempNodes[i].hasChildren()) {
				markCheckedChildrenNodes(tempNodes[i]);
			} else {
				markCheckedNode(tempNodes[i]);
			}
		}
	}
	
	function markCheckedNode(n) {
		if (n.no) {
			for (var i = 0, j = oldResults.size; i < j; ++i) {
				if (n.no == oldResults.children[i].id) {
					n.check();
				}
			}
		}
	}
	
	function resetMarkChecked() {
		uncheckAll();
		markCheckedNodes();
	}
	
	YuiE.onDOMReady(treeInit);
	</script>
</html>

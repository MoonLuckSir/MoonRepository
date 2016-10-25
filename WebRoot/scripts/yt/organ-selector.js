/**
 * <li>depends: check.js,plus.js </li>
 * <li>LOG depends: yui,yui-log</li>
 * 
 * @author uke
 */
var os;
function selectOrgan(btn, nmObj, valObj, mult, url, ctx) {
	os = (os == null) ? new OrganSelector(url, ctx) : os;
	os.controlObj = btn;
	os.nameObj = nmObj;
	os.valueObj = valObj;
	os.multable = mult;
	os.show();
}
function OrganSelector(url, ctx) {
	this.url = url;
	this.panel = this.getElementById("OSPanel");
	this.container = this.getElementById("OSContainerPanel");
	this.expand = this.getElementById("expandcontractdiv");
	this.ctx = ctx;
}

OrganSelector.prototype.update = function() {
	// showWaitPanel('');
	YAHOO.util.Connect.asyncRequest('POST', this.url, {
		success : function(o) {
			if (o.responseText !== undefined) {
				os.treeData = eval("(" + o.responseText + ")");
				os.draw();
				// closeWindow();
			}
		},
		failure : function(o) {
			if (o.responseText !== undefined) {
				os.treeData = eval("({orgSize:0,usrSize:0})");
				// closeWindow();
			}
		}
	});
}

OrganSelector.prototype.draw = function() {
	Log("draw", "info", "OrganSelector");
	this.panel.innerHTML = "<div id='OSPanelTree'></div>";
	// instantiate the tree:
	var tree = new YAHOO.widget.TreeView('OSPanelTree');
	Log("draw:==1", "info", "OrganSelector");
	
	// turn dynamic loading on for entire tree:
	
	var rootOrg = '3400008888';
	var rootOrgLabel = "3400008888 | 安徽省联社";
	//下面4行初始化根节点
	if(os.valueObj!=null&&os.valueObj.value!=null&&os.valueObj.value!='')
	 rootOrg = os.valueObj.value;
	if(os.nameObj!=null&&os.nameObj.value!=null&&os.nameObj.value!='')
	 rootOrgLabel =os.nameObj.value; 
	
	
	//if (parent && parent.user) {
	//	rootOrg = parent.user.orgNo;
	//	rootOrgLabel = parent.user.orgNo + ' | ' + parent.user.usrOrg;
	//}
	tree.subscribe("expand", function(node) {
		//Log("expand:==1", "info", "OrganSelector");
		os.focus = true;
		os.controlObj.focus();
	});
	tree.subscribe("collapse", function(node) {
		//Log("collapse:==1", "info", "OrganSelector");
		os.focus = true;
		os.controlObj.focus();
	});
	tree.subscribe("labelClick", function(node) {
		//Log("labelClick:==1", "info", "OrganSelector");
		os.nameObj.value = node.label.trim();
		os.valueObj.value = node.no;
		os.onblur();
	});
	tree.subscribe("checkClick", function(node) {
		//Log("checkClick:==1", "info", "OrganSelector");
		os.focus = true;
		os.controlObj.focus();
	});    
	
	// all the states in India:
	var tempNode = new YAHOO.widget.TextNode("root", tree.getRoot(), false);
	tempNode.setDynamicLoad(loadNodeData, null);
	tempNode.label = rootOrgLabel;
	tempNode.no = rootOrg;
	tempNode.myimg = os.ctx + '/images/theme/left/home.gif';
	tempNode.load = true;
	tree.draw();
	tempNode.toggle();	
	this.loaded=true;
	
}

function loadNodeData(node, fnLoadComplete) {
	if(!node.load){
		return;
	}
	// showWaitPanel('');
	var nodeLabel = encodeURI(node.label);

	// prepare URL for XHR request:
	
	var sUrl = os.ctx + "/system/organ.shtml?action=seletor&orgId=" + node.no;
    
	// prepare our callback object
	var callback = {
		success : function(oResponse) {
			var oResults = eval("(" + oResponse.responseText + ")");
			var tmp,hasChildren;
			for (var i = 0, j = oResults.orgSize; i < j; i++) {
				if (oResults.organ[i]) {
					tmp = oResults.organ[i];
					hasChildren= tmp.orgSize > 0;
					var tempNode = new YAHOO.widget.TextNode("org" + i, node,
							!hasChildren);
							
					tempNode.label = " " + tmp.id + " | " + tmp.name;
					tempNode.no = tmp.id;
					tempNode.myimg = os.ctx + '/images/theme/left/home.gif';
					if (!hasChildren) {
						tempNode.hasChildren = function() {
							return false;
						};
					}else{					
						tempNode.setDynamicLoad(loadNodeData, null);
						tempNode.load = true;
					}
				}
			}
			for (var n = 0, p = oResults.usrSize; n < p; n++) {
				if (oResults.user[n]) {
					var tempNode = new YAHOO.widget.TextNode("usr" + n, node,
							true);
					tempNode.label = " " + oResults.user[n].id + " | "
							+ oResults.user[n].name;
					tempNode.no = "U_" + oResults.user[n].id;
					tempNode.myimg = os.ctx
							+ '/images/theme/left/user_green.png';
					tempNode.hasChildren = function() {
						return false;
					};
					tempNode.isUser = true;
				}
			}
			oResponse.argument.fnLoadComplete();
			closeWindow();
		},
		failure : function(oResponse) {
			oResponse.argument.fnLoadComplete();
			closeWindow();
		},
		argument : {
			"node" : node,
			"fnLoadComplete" : fnLoadComplete
		},
		timeout : 4000
	};
	if (node.isUser) {
		node.refresh();
		fnLoadComplete();
	} else {
		YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);
	}
}

OrganSelector.prototype.show = function() {
    Log("show", "info", "OrganSelector");
	if (this.loaded) {
	} else {
		this.panel.innerHTML = "正在加载……";
		this.draw();
	}
	var xy = this.getAbsPoint(this.nameObj);
	this.container.style.left = xy.x + "px";
	this.container.style.top = (xy.y + this.nameObj.offsetHeight) + "px";

	this.focus = true;
	this.panel.style.display = "";
	this.container.style.display = "";

	this.container.onmouseover = function() {
		//Log("onmouseover:==1", "info", "OrganSelector");
		os.focus = true;
	}
	this.container.onmouseout = function() {
		//Log("onmouseout:==2", "info", "OrganSelector");
		os.focus = false;
	}
	this.panel.onmouseover = this.container.onmouseover;
	this.panel.onmouseout = this.container.onmouseout;
	this.panel.onclick = function() {
		os.focus = true;
		os.controlObj.focus();
	}
	this.controlObj.onblur = function() {
		if (!os.focus)
			os.onblur();
	}
	this.container.onblur = function() {
		//Log("container.onblur:==2", "info", "OrganSelector");
		if (!os.focus){
			os.onblur();
		}
	}
	this.panel.onblur = this.container.onblur;
}

OrganSelector.prototype.clean = function() {
	if ("true" === this.nameObj.required) {
		// 必输项
	} else {
		this.valueObj.value = "";
		this.nameObj.value = "";
		this.hide();
	}
}
OrganSelector.prototype.refresh = function() {

}
OrganSelector.prototype.hide = function() {
	this.panel.style.display = "none";
	this.container.style.display = "none";
	os.focus = false;
}
OrganSelector.prototype.onblur = function() {
	this.hide();
}
OrganSelector.prototype.getAbsPoint = function(e) {
	var x = e.offsetLeft;
	var y = e.offsetTop;
	while (e = e.offsetParent) {
		x += e.offsetLeft;
		y += e.offsetTop;
	}
	return {
		"x" : x,
		"y" : y
	};
}

OrganSelector.prototype.getElementById = function(id) {
	if (typeof(id) != "string" || id == "")
		return null;
	if (document.getElementById)
		return document.getElementById(id);
	if (document.all)
		return document.all(id);
	try {
		return eval(id);
	} catch (e) {
		return null;
	}
}

initOrganSelector = function() {
	var OSB = [];
	OSB[OSB.length] = '<div id="OSContainerPanel" style="position: absolute;display: none;z-index: 9999; height: 300px;background-color: #FFFFFF;border: 1px solid #CCCCCC;width:400px;font-size:12px;">';
	OSB[OSB.length] = '<div id="expandcontractdiv"><a href="javascript:os.clean();">清空</a>&nbsp;&nbsp;<a href="javascript:os.hide();">关闭</a></div>';
	OSB[OSB.length] = '<div id="OSPanel" style="overflow: auto;height: 280px;width:392px;"></div>';
	OSB[OSB.length] = '<iframe style="position: absolute; z-index: -1; width: 100%; height: 100%; top: 0; left: 0; scrolling: no;" frameborder="0" ></iframe>'
	OSB[OSB.length] = '</div>';
	document.write(OSB.join(""));
}();
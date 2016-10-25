/**
 * <li>depends: check.js,plus.js </li>
 * <li>LOG depends: yui,yui-log</li>
 * 
 * @author uke
 */
var os;
function selectOrgan(btn, nmObj, valObj, showUser, url, ctx, chkBoth) {
	os = (os == null) ? new OrganSelector(url, ctx) : os;
	os.controlObj = btn;
	os.nameObj = nmObj;
	os.valueObj = valObj;
	os.showUser = showUser;
	os.checkBoth = chkBoth ? true : false; // 可选机构和用户
	os.show();
}
function OrganSelector(url, ctx) {
	this.url = url;
	this.panel = g("OSPanel");
	this.container = g("OSContainerPanel");
	this.OSContainer = g("OSContainer");
	this.expand = g("expandcontractdiv");
	this.ctx = ctx;
}
OrganSelector.prototype.draw = function() {
	Log("draw", "info", "OrganSelector");
	this.panel.innerHTML = "<div id='OSPanelTree'></div>";
	// instantiate the tree:
	var tree = new YAHOO.widget.TreeView('OSPanelTree');
	Log("draw:==1", "info", "OrganSelector");

	// turn dynamic loading on for entire tree:
	if (os.rootOrg) {
	} else if (parent && parent.user) {
		os.rootOrg = parent.user.orgId;
		os.rootOrgLabel = os.rootOrg + " | " + parent.user.orgName;
	} else {
		os.rootOrg = '9900';
		os.rootOrgLabel = "9900 | 浦发卡中心上海总部 ";
	}

	tree.subscribe("expand", function(node) {
		os.focus = true;
		os.controlObj.focus();
	});
	tree.subscribe("collapse", function(node) {
		os.focus = true;
		os.controlObj.focus();
	});
	tree.subscribe("labelClick", function(node) {
		if (os.showUser && node.isOrg && !os.checkBoth)
			return;
		os.nameObj.value = node.label.trim();
		os.valueObj.value = node.no;
		os.hide();
		if (os.controlObj.afterBlur) {
			eval(os.controlObj.afterBlur + "(node);");
		}
	});
	tree.subscribe("checkClick", function(node) {
		os.focus = true;
		os.controlObj.focus();
	});

	// all the states in India:
	var tempNode = new YAHOO.widget.TextNode("root", tree.getRoot(), false);
	tempNode.setDynamicLoad(loadNodeData, null);
	tempNode.label = os.rootOrgLabel;
	tempNode.no = os.rootOrg;
	tempNode.myimg = os.ctx + '/images/left/house.png';
	tempNode.load = true;
	tempNode.isOrg = true;
	tempNode.multiExpand = false;
	tree.draw();
	tempNode.toggle();
	this.loaded = true;
}

function loadNodeData(node, fnLoadComplete) {
	if (!node.load) {
		return;
	}
	// showWaitPanel('');
	var nodeLabel = encodeURI(node.label);

	// prepare URL for XHR request:
	var sUrl = os.url + "&orgId=" + node.no + "&showUser=" + os.showUser;
	// prepare our callback object
	var callback = {
		success : function(oResponse) {
			var oResults = eval("(" + oResponse.responseText + ")");
			var tmp, hasChildren;
			for (var i = 0, j = oResults.orgSize; i < j; i++) {
				if (oResults.organ[i]) {
					tmp = oResults.organ[i];
					hasChildren = tmp.orgSize > 0;
					var tempNode = new YAHOO.widget.TextNode("org" + i, node,
							!hasChildren);

					tempNode.label = " " + tmp.id + " | " + tmp.name;
					tempNode.no = tmp.id;
					tempNode.orgLvl = tmp.orgLvl;
					tempNode.myimg = os.ctx + '/images/left/house.png';
					if (!hasChildren) {
						tempNode.hasChildren = function() {
							return false;
						};
					} else {

						tempNode.setDynamicLoad(loadNodeData, null);
						tempNode.load = true;
					}
					tempNode.isOrg = true;
					tempNode.multiExpand = false;
				}
			}
			for (var n = 0, p = oResults.usrSize; n < p; n++) {
				if (oResults.user[n]) {
					var tempNode = new YAHOO.widget.TextNode("usr" + n, node,
							true);
					tempNode.label = " " + oResults.user[n].id + " | "
							+ oResults.user[n].name;
					tempNode.no = "U_" + oResults.user[n].id;
					tempNode.userType = '';
					tempNode.myimg = os.ctx + '/images/left/user.png';
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
		YAHOO.util.Connect.asyncRequest('POST', sUrl, callback);
	}
}

OrganSelector.prototype.show = function() {
	Log("show", "info", "OrganSelector");
	if (this.loaded) {
	} else {
		this.draw();
	}
	var xy = this.getAbsPoint(this.nameObj);
	// 當控件高度或寬度超出頁面時, 調整位置
	var winHeight = window.document.body.clientHeight;
	var winWidth = window.document.body.clientWidth;
	var x = xy.x;
	var y = xy.y + this.nameObj.offsetHeight;
	var panelHeight = 210;
	var panelWidth = 400;
	if (y + panelHeight > winHeight) {
		y = y - panelHeight - this.nameObj.offsetHeight;
	}

	this.container.style.left = x + "px";
	this.container.style.top = y + "px";

	this.focus = false;
	this.OSContainer.style.display = "";
	this.container.style.display = "";
	this.panel.style.display = "";

	this.container.onmouseover = function() {
		os.focus = true;
	}
	this.container.onmouseout = function() {
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
		if (!os.focus) {
			os.onblur();
		}
	}
	this.panel.onblur = this.container.onblur;
}

OrganSelector.prototype.clean = function() {
	if ("true" === this.nameObj.required) { 
		alert("该内容为必输项，不能清空！");
	} else {
		this.valueObj.value = "";
		this.nameObj.value = "";
		this.hide();
		if (this.controlObj.afterBlur) {
			eval(this.controlObj.afterBlur + "();");
		}
	}
}
OrganSelector.prototype.refresh = function() {
}
OrganSelector.prototype.remove = function() {
}
OrganSelector.prototype.hide = function() {
	this.panel.style.display = "none";
	this.container.style.display = "none";
	this.OSContainer.style.display = "none";
	this.focus = false;
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

initOrganSelector = function() {
	var s = [];
	s[s.length] = '<div id="OSContainer" style="display:none;">';
	s[s.length] = '<div id="OSContainerPanel" style="position: absolute;display: none;z-index: 3; height: 200px;background-color: #fff;border: 1px solid #ccc;width:360px;font-size:12px;padding-bottom:5px;">';
	s[s.length] = '<div id="expandcontractdiv" style="text-align:right;background-color: #ffc;padding:2px;"><a href="javascript:os.clean();">清空</a>&nbsp;&nbsp;<a href="javascript:os.hide();">关闭</a></div>';
	s[s.length] = '<div id="OSPanel" style="overflow: auto;height: 180px;width:352px;padding:5px;"></div>';
	s[s.length] = '</div>';
	s[s.length] = '<iframe style="position:absolute;z-index:2;width:expression(this.previousSibling.offsetWidth);';
	s[s.length] = 'height:expression(this.previousSibling.offsetHeight);';
	s[s.length] = 'left:expression(this.previousSibling.offsetLeft);top:expression(this.previousSibling.offsetTop);';
	s[s.length] = 'display:expression(this.previousSibling.style.display);" scrolling="no" frameborder="no"></iframe>';
	s[s.length] = '</div>';
	document.write(s.join(""));
}();
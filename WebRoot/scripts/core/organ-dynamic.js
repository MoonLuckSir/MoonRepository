/**
 * <li>depends: check.js,plus.js </li>
 * <li>LOG depends: yui,yui-log</li>
 * 
 * @author uke
 */
var os2;
function buildDynaOrgTree(rootNo, rootLabel, nmObj, valObj, divId, url, ctx) {
	os2 = (os2 == null) ? new OrganDynamic(divId, url, ctx) : os2;
	os2.nameObj = nmObj;
	os2.valueObj = valObj;
	os2.multable = false;
	os2.rootNo = rootNo;
	os2.rootLabel = rootLabel;
	os2.show();
}
function OrganDynamic(divId, url, ctx) {
	this.url = url;
	this.panel = g(divId);
	this.ctx = ctx;
}
OrganDynamic.prototype.draw = function() {
	Log("draw", "info", "OrganDynamic");
	this.panel.innerHTML = "<div id='OSPanelTree'></div>";
	// instantiate the tree:
	var tree = new YAHOO.widget.TreeView('OSPanelTree');
	Log("draw:==1", "info", "OrganDynamic");

	tree.subscribe("expand", function(node) {
	});
	tree.subscribe("collapse", function(node) {
	});
	tree.subscribe("labelClick", function(node) {
		os2.nameObj.value = node.label.trim();
		os2.valueObj.value = node.no;
		if (os2.nameObj.callback) {
			eval(os2.nameObj.callback + "(node);");
		}
	});
	tree.subscribe("checkClick", function(node) {
	});

	// all the states in India:
	var tempNode = new YAHOO.widget.TextNode("root", tree.getRoot(), false);
	tempNode.setDynamicLoad(loadNodeData, null);
	tempNode.label = os2.rootLabel;
	tempNode.no = os2.rootNo;
	tempNode.myimg = os2.ctx + '/images/left/house.png';
	tempNode.load = true;
	tempNode.isOrg = true;
	tempNode.multiExpand = true;
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
	var sUrl = os2.url + "&orgId=" + node.no + "&showUser=false";
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
					tempNode.myimg = os2.ctx + '/images/left/house.png';
					if (!hasChildren) {
						tempNode.hasChildren = function() {
							return false;
						};
					} else {

						tempNode.setDynamicLoad(loadNodeData, null);
						tempNode.load = true;
					}
					tempNode.isOrg = true;
					tempNode.multiExpand = true;
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

OrganDynamic.prototype.show = function() {
	Log("show", "info", "OrganDynamic");
	if (this.loaded) {
	} else {
		this.draw();
	}
}

OrganDynamic.prototype.clean = function() {
	if ("true" === this.nameObj.required) {
		// 必输项
	} else {
		this.valueObj.value = "";
		this.nameObj.value = "";
	}
}
OrganDynamic.prototype.refresh = function() {
}
OrganDynamic.prototype.remove = function() {
}
OrganDynamic.prototype.hide = function() {
}
OrganDynamic.prototype.onblur = function() {
}
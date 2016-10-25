/*
 * Ext JS Library 2.2 Copyright(c) 2006-2008, Ext JS, LLC. licensing@extjs.com
 * 
 * http://extjs.com/license
 */

Ext.onReady(function() {
	/**
	 * 生成树
	 */
	var Tree = Ext.tree;
	var root = new Tree.AsyncTreeNode({
				text : '数据指标',
				//draggable : false,
				id : Ext.get("rptType").dom.value
			});
	var tree = new Tree.TreePanel({
				title : "分项数据",
				el : 'tree-div',
				height : 300,
				width:'100%',
				useArrows : true,
				autoScroll : true,
				frame : true,// 圆角和浅蓝色背景
				lines : true,
				animate : false,// 展开,收缩动画,false时,则没有动画效果
				enableDD : false,// 不仅可以拖动,还可以通过Drag改变节点的层次结构
				containerScroll : false,
				collapsible : false,// 可折叠
				enableDrag : false,// 树的节点可以拖动Drag
				rootVisible : false,//将title根目录加至树中
				trackMouseOver : false,// false则mouseover无效果
				useArrows : false,// 小箭头
				//autoHeight : true,//自动适应内容高度

				loader : new Tree.TreeLoader({
							dataUrl :'T0103.shtml?action=extRptItem'
						}),
				dropConfig : {
					appendOnly : true
				}
			});

	new Tree.TreeSorter(tree, {
				folderSort : true
			});

	tree.setRootNode(root);
		tree.on("click", function(node, event) {
			if(node.id.indexOf("[")>=0){
				Ext.get("qry_cont").dom.value = node.id;
			}
	});
		//定义子节点load
	tree.on("beforeload", function(node, event) {
		tree.loader.dataUrl= 'T0103.shtml?action=extRptItem&rptId='+node.id;	
	});	
	tree.render();
	root.expand(false, false);

	var ccy = new Ext.form.ComboBox({
				fieldLabel : '币别',
				name:'ccy',
				typeAhead : true,
				triggerAction : 'all',
				transform : 'ccy',
				width : 100,
				forceSelection : true
			});

});
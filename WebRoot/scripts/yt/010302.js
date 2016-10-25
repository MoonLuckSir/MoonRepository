/*
 * Ext JS Library 2.2 Copyright(c) 2006-2008, Ext JS, LLC. licensing@extjs.com
 * 
 * @qilongYU by 2008-11-03
 */

Ext.onReady(function() {

	/**
	 * 生成选择树
	 */
	var Tree = Ext.tree;

	var root = new Tree.AsyncTreeNode({
			text : '数据指标',
			draggable : false,
			id :Ext.get("rptType").dom.value// 'root'
		});
			
	var tree = new Tree.TreePanel({
				title : "分项数据",
				el : 'tree-div',
				height:300,
				width:300,
				useArrows : true,
				autoScroll : true,
				frame : true,// 圆角和浅蓝色背景
				lines : true,
				animate : false,// 展开,收缩动画,false时,则没有动画效果
				enableDD : false,// 不仅可以拖动,还可以通过Drag改变节点的层次结构
				containerScroll : true,
				collapsible : false,// 可折叠
				enableDrag : false,// 树的节点可以拖动Drag
				rootVisible : false,
				trackMouseOver : false,// false则mouseover无效果
				useArrows : false,// 小箭头
				autoHeight : false,
				loader : new Tree.TreeLoader({
							dataUrl : 'T0103.shtml?action=extRptItem'
						}),
				dropConfig : {
					appendOnly : true
				}
			});
			
	new Tree.TreeSorter(tree, {
				folderSort : true
			});

	tree.on("click", function(node, event) {
			if(node.id.indexOf("[")>=0){
				if (Ext.get("qry_cont").dom.value== "") {
					Ext.get("qry_cont").dom.value+= node.id;
				} else
					Ext.get("qry_cont").dom.value+= '+' + node.id;
			}
	});
		//定义子节点load
	tree.on("beforeload", function(node, event) {
		tree.loader.dataUrl= 'T0103.shtml?action=extRptItem&rptId='+node.id;	
	});
			
	tree.setRootNode(root);
	tree.render();
	root.expand(false, false);

	/**
	 * 币别下拉选择框
	 */
	var ccy = new Ext.form.ComboBox({
				fieldLabel : '币别',
				name:'ccy',
				typeAhead : true,
				triggerAction : 'all',
				transform : 'ccy',
				width : 100,
				forceSelection : true
			});

	/**
	 * 取得自定义的查询条件
	 */
	var RecordDef = Ext.data.Record.create([{
				name : 'qryId',
				mapping : 'qryId'
			}, {
				name : 'qryName',
				mapping : 'qryName'
			}, {
				name : 'qryCont',
				mapping : 'qryCont'
			}]);
	var store = new Ext.data.Store({
				autoLoad : true,
				proxy : new Ext.data.HttpProxy({
							url : '/FinPro/biz01/T0103.shtml?action=expQuery'
						}),
				reader : new Ext.data.JsonReader({
							id : 'qryId',
							fields : ['qryId', 'qryName', 'qryCont']
						}, RecordDef),
				remoteSort : false
			});
	store.load();

	/**
	 * 自定义查询box select
	 */
	var combo = new Ext.form.ComboBox({
		store : store,
		fieldLabel : '查询公式',
		//title : '自定义公式:',
		//listClass: 'x-combo-list-small',
		name : 'qryId',		
		hiddenName : 'tmp',
		valueField : 'qryName',
		displayField : 'qryName',
		anchor : '100%',
		typeAhead : true,
		mode : 'local',
		triggerAction : 'all',
		// selectOnFocus : true,
		// forceSelection : true,
		// emptyText : '请选择...',
		width : 200,
		// applyTo : 'qryId',
		editable : true,
		blankText : '自定义查询条件不能为空',
		listeners : {
			// render: function(f, index){
			// },
			select : function(combo, record, index) {
				// if ( index> -1) { //在下拉框中将值传给控件
				// this.setValue(this.el.dom.value);
				// }
				Ext.get("qry_cont").dom.value= record.get('qryCont'), 
				document.getElementById("qry_id").value = record.get('qryId')
			}
		}
	});

	/**
	 * 当blue时自动将手工输入的值传递给combos
	 */
	combo.on('blur', function(e) {
				this.setValue(this.el.dom.value);
			});

	var qry_cont = new Ext.form.TextArea({ // TextArea
		fieldLabel : '查询内容',
		hideParent : true,
		preventScrollbars : true,
		name : 'qry_cont',
		name : 'qry_cont',
		id : 'qry_cont',
		autoShow : true,
		height : 180,
		width : '100%',
		blankText : '查询内容不能为空'
	});

	var button=Ext.get("qrySub");
	button.on('click',function(){
		document.getElementById("tmp").value=document.getElementById("qry_id").value ;
	})

	
	/**
	 * form 表单
	 */
	var form1 = new Ext.form.FormPanel({
		title : "查询公式",
		width : '100%',
		height:300,
		frame : true,// 圆角和浅蓝色背景
		renderTo : "form1",// 呈现
		labelWidth:80,
		items : [{
			xtype:"panel",
			layout : 'table', // 把整个空间划分成列
			fieldLabel : '查询公式',
            isFormField:true,
			items : [combo, {
					xtype : 'button',
					text : '保 存',
					type : 'submit',
					// 定义表单提交事件
					handler : function() {
						if (form1.form.isValid()) {// 验证合法后使用加载进度条
							wait(),
							// 提交到服务器操作
							form1.form.doAction('submit', {
								url : '/FinPro/biz01/T0103.shtml?action=expSave',// 文件路径
								method : 'post',// 提交方法post或get
								params : '',
								// 提交成功的回调函数
								success : function(form, action) {
									if (action.result.msg == 'ok') {
										Ext.Msg.alert('提示窗口','自定义查询条件已经保存成功，请查询相关信息！');
										combo.reset();
										store.proxy = new Ext.data.HttpProxy({
											url : '/FinPro/biz01/T0103.shtml?action=expQuery'
										});
										store.load();
									} else {
										Ext.Msg.alert('提示窗口',action.result.msg);
									}
								},
								// 提交失败的回调函数
								failure : function() {
									Ext.Msg.alert('错误', '服务器出现错误请稍后再试！');
								}
							});
						}
					}
					},{
					xtype : 'button',
					text : '删 除',
					type : 'submit',
					handler : function() {
						wait(),
						// 提交到服务器操作
						form1.form.doAction('submit', {
									url : '/FinPro/biz01/T0103.shtml?action=expDelete',// 文件路径
									method : 'post',// 提交方法post或get
									params : '',
									// 提交成功的回调函数
									success : function(form, action) {
										if (action.result.msg == 'ok') {
											Ext.Msg.alert('提示窗口','自定义查询条件已经删除成功，请查询相关信息！');
											combo.reset();
											store.proxy = new Ext.data.HttpProxy({
												url : '/FinPro/biz01/T0103.shtml?action=expQuery'
											});
											store.load();
											Ext.get("qry_cont").dom.value="";
										} else {
											Ext.Msg.alert('提示窗口',
													action.result.msg);
										}
									},
									// 提交失败的回调函数
									failure : function() {
										Ext.Msg.alert('错误', '服务器出现错误请稍后再试！');
									}
								});
					}
			}]
		}, qry_cont, {
			xtype : 'hidden',
			id : 'qry_id'
		}, {
			xtype:"panel",
			cls:'x-plain',
			plain:true,
			html:"<div align='center'><input type='button' class='btn' id='+' value=' + '/>&nbsp;" +
				 "<input type='button' class='btn' id='-' value=' - '/>&nbsp;" +
				 "<input type='button' class='btn' id='*' value=' * '/>&nbsp;" +
				 "<input type='button' class='btn' id='/' value=' / '/>&nbsp;" + 
				 "<input type='button' class='btn' id='=' value=' = '/>&nbsp;" + 
				 "<input type='button' class='btn' id='(' value=' ( '/>&nbsp;" + 
				 "<input type='button' class='btn' id=')' value=' ) '/></div>"
		}]
	});

	Ext.get('+').on('click', function(e) {
				Ext.get("qry_cont").dom.value += "+";
			});
	Ext.get('-').on('click', function(e) {
				Ext.get("qry_cont").dom.value += "-";
			});
	Ext.get('*').on('click', function(e) {
				Ext.get("qry_cont").dom.value += "*";
			});
	Ext.get('/').on('click', function(e) {
				Ext.get("qry_cont").dom.value += "/";
			});
	Ext.get('=').on('click', function(e) {
				Ext.get("qry_cont").dom.value += "=";
			});
	Ext.get('(').on('click', function(e) {
				Ext.get("qry_cont").dom.value += "(";
			});
	Ext.get(')').on('click', function(e) {
				Ext.get("qry_cont").dom.value += ")";
			});
	/**
	 * 页面等待加载box
	 */
	var wait = function() {
		Ext.MessageBox.show({
					title : '请稍等',
					msg : '正在加载...',
					progressText : '',
					width : 300,
					progress : true,
					closable : false,
					animEl : 'loding'
				});
		// 控制进度速度
		var f = function(v) {
			return function() {
				var i = v / 11;
				Ext.MessageBox.updateProgress(i, '');
			};
		};

		for (var i = 1; i < 13; i++) {
			setTimeout(f(i), i * 150);
		}
	}
        
});

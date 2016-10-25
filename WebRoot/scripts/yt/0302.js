/*
 * 表间关系公式管理 @qilongYU by 2008-11-05
 */

Ext.onReady(function() {

	Ext.QuickTips.init();
	
	/**
	 * 取得报表类型
	 */
	var def = Ext.data.Record.create([{
				name : 'typeId',
				mapping : 'typeId'
			}, {
				name : 'typeName',
				mapping : 'typeName'
			}]);
	var store1 = new Ext.data.Store({
				autoLoad : true,
				proxy : new Ext.data.HttpProxy({
							url : '/FinPro/biz03/T0302.shtml?action=rptType'
						}),
				reader : new Ext.data.JsonReader({
							id : 'typeId',
							fields : ['typeId', 'typeName']
						}, def),
				remoteSort : false
			});

	/**
	 * 由报表类型得到报表清单
	 */
	var store2 = new Ext.data.Store({
				autoLoad : true,
				proxy : new Ext.data.HttpProxy({
							url : '/FinPro/biz03/T0302.shtml?action=rptList&id='
						}),
				reader : new Ext.data.JsonReader({
							id : 'rptId',
							fields : ['rptId', 'rptName']
						}, [{
									name : 'rptId',
									mapping : 'rptId'
								}, {
									name : 'rptName',
									mapping : 'rptName'
								}]),
				remoteSort : false
			});

	store1.load();
	var combo = new Ext.form.ComboBox({
				fieldLabel : '报表种类',
				isFormField : true,
				store : store1,
				name : 'typeId',
				hiddenName : 'typeId',
				valueField : 'typeId',
				displayField : 'typeName',
				anchor : '90%',
				typeAhead : true,
				mode : 'local',
				triggerAction : 'all',
				width : 200,
				editable : true,
				// applyTo : 'rptType',
				selectOnFocus : true,
				forceSelection : true,
				emptyText : '请选择...'
			});
	combo.on('select', function() {
				rptName.reset();
				store2.proxy = new Ext.data.HttpProxy({
							url : '/FinPro/biz03/T0302.shtml?action=rptList&id='
									+ combo.getValue()
						});
				store2.load();
			});

	
	var rptId = "";
	var ifr = '<iframe src="" name="ifr" id="ifr" width="100%" height="100%" marginheight="0" scrolling="auto" frameborder="0"></iframe>';
	var rptName = new Ext.form.ComboBox({
		fieldLabel : '报表名称',
		store : store2,
		name : 'rptId',
		hiddenName : 'rptId',
		valueField : 'rptId',
		displayField : 'rptName',
		anchor : '30%',
		typeAhead : true,
		mode : 'local',
		triggerAction : 'all',
		width : 200,
		editable : true,
		// applyTo : 'rptName',
		selectOnFocus : true,
		forceSelection : true,
		emptyText : '请选择...',
		listeners : {
			select : function(combo, record, index) {
				rptId = record.get('rptId');
				ifr = '<iframe src="/FinPro/pages/03/0302/0302_STable.jsp?rptId='
						+ rptId
						+ '" name="ifr" id="ifr" width="100%" marginwidth="0" height="100%" marginheight="0" scrolling="auto" frameborder="0"></iframe>';
				addTab(record.get('rptName'), ifr)
			}
		}
	});

	/**
	 * form表单
	 */
	var form1 = new Ext.form.FormPanel({
		title : "当前位置：模板管理 >> 报表模板管理 >> 表间关系公式 ",
		width : '100%',
		height : '400px',
		frame : true,// 圆角和浅蓝色背景
		renderTo : "form1",// 呈现
		labelWidth:80,
		items : [{
			   xtype:"textarea",
               name:"calCode",
               id:"calCode",
               height:100,
			   width: '100%',
			   fieldLabel :'公式编辑'
			//html : '<textarea id="calCode" style="width: 100%; height: 100"></textarea>'
		}, {
			labelAlign : 'left',
			buttons : [{
				text : '+',
				cls : "btn",//
				handler : function() {
					document.getElementById("calCode").value = document
							.getElementById("calCode").value
							+ "+"
				}
			}, {
				text : '-',
				handler : function() {
					document.getElementById("calCode").value = document
							.getElementById("calCode").value
							+ "-"
				}
			}, {
				text : '*',
				handler : function() {
					document.getElementById("calCode").value = document
							.getElementById("calCode").value
							+ "*"
				}
			}, {
				text : '/',
				handler : function() {
					document.getElementById("calCode").value = document
							.getElementById("calCode").value
							+ "/"
				}
			}, {
				text : '=',
				handler : function() {
					document.getElementById("calCode").value = document
							.getElementById("calCode").value
							+ "="
				}
			}, {
				text : '(',
				handler : function() {
					document.getElementById("calCode").value = document
							.getElementById("calCode").value
							+ "("
				}
			}, {
				text : ')',
				handler : function() {
					document.getElementById("calCode").value = document
							.getElementById("calCode").value
							+ ")"
				}
			}, {
				text : '保 存',
				type : 'submit',
				// 定义表单提交事件
				handler : function() {
					if (form1.form.isValid()) {// 验证合法后使用加载进度条
						// wait(),
						// 提交到服务器操作
						 this.getEl().dom.calCode =document.getElementById("calCode").value;
						form1.form.doAction('submit', {
							url : '/FinPro/biz03/T0302.shtml?action=save',
							method : 'post',
							params : "calId="
									+ document.getElementById("calId").value,
							// 提交成功的回调函数
							success : function(form, action) {
								Ext.Msg.alert('提示窗口', action.result.msg);
								//Ext.MessageBox.alert('提示窗口', 'Changes saved successfully.', action.result.msg);
								//action.result.calId;
								document.location = '/FinPro/biz03/T0302.shtml?action=toQuery';
							},
							// 提交失败的回调函数
							failure : function() {
								Ext.Msg.alert('错误', '服务器出现错误请稍后再试！');
							}
						});
					}
				}
			}]
		},{
			xtype:"panel",
			layout : 'column', // 把整个空间划分成列
			fieldLabel : '请选择报表',
	        isFormField:true,	
			items:[ combo, rptName]
		}]
	});

	//修改时加载到文本框中
	document.getElementById("calCode").value = document.getElementById("code").value;
	/**
	 * 报表展现Tabpanel
	 */
	var tabPanel = new Ext.TabPanel({
				renderTo : 'rptTable',
				activeTab : 0,// 当前活动的table 序列
				resizeTabs : true, // turn on tab resizing
				minTabWidth : 115,
				tabWidth : 200,
				enableTabScroll : true,
				width : '98%',
				height : 300,
				// enableTabScroll : true,
				// lazyLoad : true,
				tabPosition : 'top',
				// preventDefault : true,
				fitToFrame : true,
				region : 'east',
				border : true,// 加边框
				// margins:'0 4 4 0',
				// deferredRender:false,
				frame : true,// 圆角和浅蓝色背景
				// plain:true,//标题背景
				defaults : {
					autoScroll : true
				},
				items : [{
							closable : true,// 关闭窗口
							id : "tmptab",
							title : '报表表样',
							html : ifr
						}]
			});

	/**
	 * 增加tablePanel中的报表展现
	 */
	function addTab(tabTitle, targetUrl) {
		tabPanel.remove("tmptab", true);
		tabPanel.add({
					closable : true,
					title : tabTitle,
					iconCls : 'tabs',
					html : targetUrl
				}).show();
	}

	// 更新tab内容，如不存在就新建一个
	function updateTab(tabId, title, url) {
		var tab = tabPanel.getItem(tabId);
		if (tab) {
			tab.getUpdater().update(url);
			tab.setTitle(title);
		} else {
			tab = addTab(title, url);
		}
		tabPanel.setActiveTab(tab);
	}
});

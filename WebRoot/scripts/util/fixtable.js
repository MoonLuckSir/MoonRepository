/**
 * <li>大报表浏览，固定表头行，固定表头列 </li> *
 * 
 * @author uke
 */
// 创建报表视图层
function ScrollLayer(rptTable) {
	this.report = rptTable;
}

ScrollLayer.prototype = {
	// 页面滚动高度、宽度
	// 页面宽度、高度
	// 报表宽度、高度
	// 报表行列
	// 报表标题部分、页脚部分
	log : function(s) {
		this.logs[this.logs.length] = s;
	},
	logInfo : function() {
		return this.logs.join("");
	},
	clearLogs : function() {
		this.logs = [];
	},
	beginIndexCol : -1,
	beginIndexRow : -1,
	endIndexCol : -1,
	endIndexRow : -1,
	rptHeadTop : -1,
	rptDataTop : -1,
	tmpRow : [],
	init : function() {
		this.padding();
		//alert(this.ml);
		//return;
		this.clearLogs();
		this.winCW = document.body.clientWidth;
		this.winCH = document.body.clientHeight;
		this.winSW = document.body.scrollWidth;
		this.winSH = document.body.scrollHeight;
		// 标记滚动条
		this.scrollSign();
		// 无滚动条时中断
		if (this.scrollNo)
			return;// 中断

		var rpt = this.report;
		this.rptWidth = rpt.offsetWidth;
		this.rptHeight = rpt.offsetHeight;
		this.rptTop = rpt.offsetTop;
		this.rptLeft = rpt.offsetLeft;

		// 分离出报表标题部分、页脚部分、列宽
		this.readCols();
		this.readReport();
		this.createLayer();
	},
	createLayer : function() {
		// 固定列
		this.createFixColsDiv();
		// 报表标题
		this.createRptTitleDiv();
		// 交叉部分
		this.createRptHeaderDiv();

		// 报表上方空白区域
		this.createDiv21();
		// 报表下方空白区域
		this.createDiv22();
		// 报表左方空白区域
		this.createDiv23();
		// 报表右方空白区域
		this.createDiv24();
	},
	padding:function(){
		var body = document.body;
		this.ml = body.style.marginLeft.replace("px","")*1;
		this.mt = body.style.marginTop.replace("px","")*1;
		this.mr = body.style.marginRight.replace("px","")*1;
		this.mb = body.style.marginBottom.replace("px","")*1;
	},
	readReport : function() {
		// 解析报表主表格
		var table = this.report;
		this.clearRptTitles();
		this.clearRptHeaders();
		this.clearRptFooters();
		try {
			this.rptRows = table.rows.length;
			var tr, type;
			for (var i = 0, j = this.rptRows; i < j; i++) {
				tr = table.rows[i];
				type = tr.cells[0].content;
				if (type == "title") {
					this.appendRptTitle(tr);
				} else if (type == "header") {
					this.appendRptHeader(tr, i);
				}
			}
		} catch (e) {
		}
	},
	readCols : function() {
		var cols = document.getElementsByTagName("col");
		var col;
		for (var i = 0, j = cols.length; i < j; i++) {
			col = cols[i];
		}
		this.cols = cols;
	},
	clearRptTitles : function() {
		this.rts = [];
	},
	clearRptHeaders : function() {
		this.rhs = [];
	},
	clearRptFooters : function() {
		this.rfs = [];
	},
	appendRptTitle : function(row) {
		this.rts[this.rts.length] = this.cloneTr(row);
	},
	appendRptHeader : function(row, index) {
		// 标记列表起始位置
		var tmp = {};
		this.cloneAttrs(row, tmp, ["height"]);
		// 检查固定行列的标识是否已设置
		var fixInit = this.beginIndexCol >= 0;
		var _endIndex = 0;
		var cells = [], cell, fc = true;
		for (var i = 0, j = row.cells.length; i < j; i++) {
			cell = row.cells[i];
			if (fixInit) {
				// 已设置
			} else if (fc && cell.fix) {
				// 行列固定
				var colSpan = 1;
				if (cell.colSpan) {
					colSpan = cell.colSpan * 1;
				}
				_endIndex += colSpan;
			} else {
				fc = false;// 合法固定列校验标识
			}
			cells[cells.length] = this.cloneTd(cell);
		}
		tmp.cells = cells;
		if (!fixInit && _endIndex >= 0) {
			this.headerTop = row.offsetTop;
			this.beginIndexCol = 0;
			this.endIndexCol = _endIndex;
		}
		this.rhs[this.rhs.length] = tmp;
		this.rts[this.rts.length] = tmp;
	},
	appendRptFooter : function(row) {
		this.rfs[this.rfs.length] = this.cloneTr(row);
	},
	countColsWidth : function(begin, end) {
		var cols = this.cols;
		var width = 0;
		for (var i = begin, j = end; i < j; i++) {
			width += cols[i].style.width;
		}
		return width;
	},
	createTable : function(width) {
		var table = document.createElement("table");
		table.border = 0;
		table.cellPadding = 0;
		table.cellSpacing = 0;
		table.width = width;
		table.style.cssText = "table-layout:fixed;border-collapse:collapse;";
		return table;
	},
	createColGroup : function(begin, end) {
		var colGroup = document.createElement("colgroup");
		var cols = this.cols;
		for (var i = begin, j = end, col; i < j; i++) {
			col = document.createElement("col");
			col.style.cssText = cols[i].style.cssText;
			colGroup.appendChild(col);
		}
		return colGroup;
	},
	createTableDiv : function(zIndex, top, left, table) {
		var layer = document.createElement("div");
		layer.style.cssText = this.createCssText(15, top+this.mt, left+this.ml);
		document.body.appendChild(layer);
		layer.appendChild(table);
		return layer;
	},
	createRptTitleDiv : function() {
		if (!this.scrollY) {
			return;
		}
		var rows = this.rts;
		if (rows == null || rows.length < 1) {
			return;
		}
		// 创建表
		var table = this.createTable(this.rptWidth);
		// 列定义
		var colGroup = this.createColGroup(0, this.cols.length);
		table.appendChild(colGroup);
		var nrow, srow, cells, ncell, scell;
		for (var i = 0, j = rows.length; i < j; i++) {
			srow = rows[i];
			cells = srow.cells;
			if (cells == null || cells.length < 1) {
				continue;
			}
			nrow = table.insertRow();
			this.cloneAttrs(srow, nrow, ["height"]);
			for (var m = 0, n = cells.length; m < n; m++) {
				scell = cells[m];
				if (scell == null) {
					continue;
				}
				ncell = nrow.insertCell();
				this.cloneAttrs(scell, ncell, ["className", "colSpan",
						"rowSpan"]);
				ncell.style.width = scell.width;
				ncell.innerHTML = scell.innerHTML ? scell.innerHTML : "&nbsp;";
			}
		}

		var layer = this.createTableDiv(12, this.rptTop, this.rptLeft, table);
		this.rptTitleDiv = layer;
	},
	createRptHeaderDiv : function() {
		// 固定区交叉部
		if (!this.scrollX) {
			return;
		}
		if (this.beginIndexCol < 0) {
			// 未设置固定区域
			return;
		}
		// 截止固定列序号
		var fixCols = this.endIndexCol;
		// 表宽度
		var tableWidth = this.countColsWidth(this.beginIndexCol, fixCols);
		// 创建表
		var table = this.createTable(tableWidth);
		// 列定义
		var colGroup = this.createColGroup(this.beginIndexCol, fixCols);
		table.appendChild(colGroup);

		// 提取交叉区域
		var rhs = this.rhs;
		var _tmpRow = [];
		var nrow, srow, cells, ncell, scell;
		for (var i = 0, j = rhs.length; i < j; i++) {
			srow = rhs[i];
			cells = srow.cells;
			if (cells == null || cells.length < 1) {
				continue;
			}
			var _cols = 0;
			if (_tmpRow.length > 0) {
				for (var x = _tmpRow.length - 1; x >= 0; x--) {
					var _cell = _tmpRow[x];
					_cols += _cell.colSpan;
					if (_cell.rowSpan <= 1) {
						_tmpRow[x] = null;
					} else {
						_tmpRow[x].rowSpan -= 1;
					}
				}
			}
			nrow = table.insertRow();
			this.cloneAttrs(srow, nrow, ["height"]);
			if (_cols >= fixCols) {
				continue;
			}
			for (var m = 0, n = cells.length, cell; m < n; m++) {
				if ((_cols + m) > fixCols) {
					break;
				}
				scell = cells[m];
				ncell = nrow.insertCell();
				this.cloneAttrs(scell, ncell, ["className", "colSpan",
						"rowSpan"]);
				ncell.innerHTML = scell.innerHTML ? scell.innerHTML : "&nbsp;";
				var colSpan = 1;
				if (scell.rowSpan) {
					if (scell.colSpan) {
						colSpan = scell.colSpan * 1;
					}
					var rowSpan = scell.rowSpan * 1 - 1;
					if (rowSpan > 0) {
						_tmpRow[_tmpRow.length] = {
							rowSpan : rowSpan,
							colSpan : colSpan
						};
					}
				} else if (scell.colSpan) {
					colSpan = scell.colSpan * 1;
				}
				_cols += colSpan;
			}
		}
		this.rptHeaderDiv = this.createTableDiv(13, this.headerTop
				+ this.rptTop, this.rptLeft, table);
	},
	createFixColsDiv : function() {
		// 固定列区域
		if (!this.scrollX) {
			return;
		}
		if (this.beginIndexCol < 0) {
			// 未设置固定区域
			return;
		}
		// 截止固定列序号
		var fixCols = this.endIndexCol;
		// 表宽度
		var tableWidth = this.countColsWidth(this.beginIndexCol, fixCols);
		// 创建表
		var table = this.createTable(tableWidth);
		// 列定义
		var colGroup = this.createColGroup(this.beginIndexCol, fixCols);
		table.appendChild(colGroup);

		// 提取交叉区域
		var rows = this.report.rows;
		var _tmpRow = [];
		var nrow, srow, cells, ncell, scell, type;
		for (var i = 0, j = rows.length; i < j; i++) {
			srow = rows[i];
			try {
				type = srow.cells[0].content;
			} catch (e) {
				continue;
			};
			if (type == "header" || type == "data") {
				cells = srow.cells;
				if (cells == null || cells.length < 1) {
					continue;
				}
				var _cols = 0;
				if (_tmpRow.length > 0) {
					var _tmpRow2 = [];
					for (var x = 0, y = _tmpRow.length; x < y; x++) {
						var _cell = _tmpRow[x];
						if (_cell && _cell.colSpan && _cell.rowSpan) {
							_cols += _cell.colSpan;
							if (_cell.rowSpan > 1) {
								_cell.rowSpan -= 1;
								_tmpRow2[_tmpRow2.length] = _cell;
							}
						}
					}
					_tmpRow = _tmpRow2;
				}
				if (_cols >= fixCols) {
					continue;
				}
				nrow = table.insertRow();
				this.cloneAttrs(srow, nrow, ["height"]);
				for (var m = 0, n = cells.length, cell; m < n; m++) {
					if ((_cols + m) >= fixCols) {
						break;
					}
					scell = cells[m];
					ncell = nrow.insertCell();
					this.cloneAttrs(scell, ncell, ["className", "colSpan",
							"rowSpan"]);
					ncell.innerHTML = scell.innerHTML
							? scell.innerHTML
							: "&nbsp;";

					if (scell.rowSpan) {
						var colSpan = 0;
						if (scell.colSpan) {
							colSpan = scell.colSpan * 1 - 1;
						}
						var rowSpan = scell.rowSpan * 1 - 1;
						if (rowSpan > 0) {
							_tmpRow[_tmpRow.length] = {
								"rowSpan" : rowSpan,
								"colSpan" : colSpan
							};
						}
					}
					if (scell.colSpan) {
						_cols += scell.colSpan * 1 - 1;
					}
				}
			}
		}
		this.fixColsDiv = this.createTableDiv(11, this.headerTop + this.rptTop,
				this.rptLeft, table);

	},
	createSpanArea : function(index, left, top, width, height) {
		var layer = document.createElement("div");
		layer.style.cssText = [
				"background-color: #FFFFFF;position: absolute;z-index: ",
				index, ";top:", top, "px;left:", left, "px;width:", width,
				"px;height:", height, "px;"].join("");
		document.body.appendChild(layer);
		return layer;
	},
	createDiv21 : function() {
		// 空白区_1: 列表上方空白区域
		if (this.scrollY && this.rptTop > 0) {
			this.div21 = this.createSpanArea(27, 0, 0, this.winCW+this.ml+this.mr, this.rptTop + this.mt);
		}
	},
	createDiv22 : function() {
		// 空白区_2: 列表下方空白区域
	},
	createDiv23 : function() {
		// 空白区_3: 列表左方空白区域
		if (this.scrollX && (this.rptLeft) > 0) {
			this.div23 = this
					.createSpanArea(23, 0, 0, this.rptLeft+this.ml, this.winCH+this.mt+this.mb);
		}
	},
	createDiv24 : function() {
		// 空白区_4: 列表右方空白区域
	},
	createCssText : function(index, top, left) {
		return ["background-color: #FFFFFF;position: absolute;z-index: ",
				index, ";top:", top, "px;left:", left, "px;"].join("");
	},
	cloneObj : function(obj) {
		var tmp = {};
		this.log("cloneObj:	" + obj.tagName);

		return tmp;
	},
	cloneTr : function(obj) {
		var tmp = {};
		this.cloneAttrs(obj, tmp, ["height"]);
		var cells = [];
		for (var i = 0, j = obj.cells.length; i < j; i++) {
			cells[cells.length] = this.cloneTd(obj.cells[i]);
		}
		tmp.cells = cells;
		return tmp;
	},
	cloneTd : function(obj) {
		var tmp = {};
		this.cloneAttrs(obj, tmp, ["className", "colSpan", "rowSpan", "fix"]);
		tmp.width = obj.style.width;
		tmp.innerHTML = obj.innerHTML;
		return tmp;
	},
	cloneAttrs : function(src, tag, attrs) {
		var key;
		for (var i = 0, j = attrs.length; i < j; i++) {
			key = attrs[i];
			if (src[key]) {
				this.log("cloneAttrs:	" + key + "\n");
				try {
					tag[key] = src[key];
				} catch (e) {
					this.log("cloneAttrs error:	" + key + "\n");
				}
			}
		}
	},
	moverLayers : function() {
		var sTop = document.body.scrollTop;
		var sLeft = document.body.scrollLeft;
		if (this.div21) {
			// 报表上方空白区域
			this.div21.style.top = sTop;
		}
		if (this.div23) {
			// 报表左方空白区域
			this.div23.style.top = sTop;
			this.div23.style.left = sLeft;
		}
		if (this.rptTitleDiv) {
			// 报表标题
			var div = this.rptTitleDiv;
			div.style.top = this.rptTop + sTop + this.mt;
		}
		if (this.rptHeaderDiv) {
			// 列表交叉区域
			var div = this.rptHeaderDiv;
			div.style.top = this.headerTop + this.rptTop + sTop + this.mt;
			div.style.left = this.rptLeft + sLeft + this.ml;
		}
		if (this.fixColsDiv) {
			this.fixColsDiv.style.left = this.rptLeft + sLeft + this.ml;
		}
	},
	scrollSign : function() {
		// 标记滚动条
		// 显示横向滚动条
		this.scrollX = this.winSW > this.winCW;
		// 显示纵向滚动条
		this.scrollY = this.winSH > this.winCH;
		// 无滚动条
		this.scrollNo = (!this.scrollX) && (!this.scrollY);
		// 是否双向滚动条
		this.scrollXY = this.scrollX && this.scrollY;
	},
	remove:function(obj){
		try{obj.innerHTML="";
		obj=null;}catch(e){}
	},
	distory:function(){
		if (this.rptTitleDiv) {
			this.remove(this.rptTitleDiv);
		}
		if (this.rptHeaderDiv) {
			this.remove(this.rptHeaderDiv);
		}
		if (this.fixColsDiv) {
			this.remove(this.fixColsDiv);
		}
		if (this.div21) {
			//this.remove(this.div21);
		}
		if (this.div23) {
			//this.remove(this.div23);
		}
		if (this.div22) {
			//this.remove(this.div22);
		}
		if (this.div24) {
			//this.remove(this.div24);
		}
	},
	toString : function() {
		var sb = [];
		sb[sb.length] = "\nwindow.clientWidth: 		" + document.body.clientWidth;
		sb[sb.length] = "\nwindow.clientHeight: 	" + document.body.clientHeight;
		sb[sb.length] = "\nwindow.scrollWidth: 		" + document.body.scrollWidth;
		sb[sb.length] = "\nwindow.scrollHeight: 	" + document.body.scrollHeight;
		sb[sb.length] = "\nwindow.scrollLeft: 		" + document.body.scrollLeft;
		sb[sb.length] = "\nwindow.scrollTop: 		" + document.body.scrollTop;
		return sb.join("");
	}
};

var cal;
function SelectDate3(obj, btn) {
	cal = (cal == null) ? new Calendar(0) : cal;
	cal.dateFormatStyle = "yyyyMMdd";
	cal.show(obj, btn);
	cal.bindData();
} 
function SelectDate(obj, fmt) {
	cal = (cal == null) ? new Calendar(0) : cal;
	fmt = (fmt) ? fmt : "yyyyMMdd";
	cal.dateFormatStyle = fmt;
	cal.show(obj);
	cal.bindData();
}
/**
 * 返回日期
 * 
 * @param d
 *            the delimiter
 * @param p
 *            the pattern of your date
 */
String.prototype.toDate = function(style) {
	var y = this.substring(style.indexOf('y'), style.lastIndexOf('y') + 1);// 年
	var m = this.substring(style.indexOf('M'), style.lastIndexOf('M') + 1);// 月
	var d = this.substring(style.indexOf('d'), style.lastIndexOf('d') + 1);// 日
	if (isNaN(y))
		y = new Date().getFullYear();
	if (isNaN(m))
		m = new Date().getMonth();
	if (isNaN(d) || d == "")
		d = new Date().getDate();

	var dt;
	eval("dt = new Date('" + y + "', '" + (m - 1) + "','" + d + "')");
	return dt;
}
/**
 * 格式化日期 
 */
Date.prototype.format = function(style) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"w+" : "天一二三四五六".charAt(this.getDay()), // week
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds() // millisecond
	}
	if (/(y+)/.test(style)) {
		style = style.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	}
	for (var k in o) {
		if (new RegExp("(" + k + ")").test(style)) {
			style = style.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return style;
};
Date.prototype.clearTime = function(clone) {
	this.setHours(0);
	this.setMinutes(0);
	this.setSeconds(0);
	this.setMilliseconds(0);
	return this;
};

Calendar = function(lang, dateFormatStyle) {
	this.init(lang, dateFormatStyle);
};

Calendar.prototype = {
	lang : 0,
	dateFormatStyle : 'yyyy-MM-dd',
	pick : 0,
	dateControl : null,
	panel : null,
	form : null,
	date : null,
	year : null,
	month : null,
	container : null,
	prevMonth : null,
	nextMonth : null,
	calendarClear : null,
	calendarClose : null,
	calendarYear : null,
	calendarMonth : null,
	calendarToday : null,
	tmpY : null,
	tmpM : null,
	pickView : null,
	dateHead : null,
	dateTable : null,
	beginYear : 1909,
	endYear : 2050,
	isFocus : false,
	colors : {
		"val_bg" : "#8db2e3",// 有效日期背景色 2008-04-16 uke add
		"cur_word" : "#FFFFFF", // 当日日期文字颜色
		"cur_bg" : "#0099FF", // 当日日期单元格背影色
		"sel_bg" : "#FFCCCC", // 已被选择的日期单元格背影色 2006-12-03 寒羽枫添加
		"sun_word" : "#FF0000", // 星期天文字颜色
		"sat_word" : "#0000FF", // 星期六文字颜色
		"td_word_light" : "#333333", // 单元格文字颜色
		"td_word_dark" : "#CCCCCC", // 单元格文字暗色
		"td_bg_out" : "#EFEFEF", // 单元格背影色
		"td_bg_over" : "#FFCC00", // 单元格背影色
		"tr_word" : "#FFFFFF", // 日历头文字颜色
		"tr_bg" : "#015B87", // 日历头背影色
		"input_border" : "#CCCCCC", // input控件的边框颜色
		"input_bg" : "#EFEFEF" // input控件的背影色
	},
	language : {
		"year" : [[""], [""], [""]],
		"months" : [
				["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
				["JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"],
				["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"]],
		"weeks" : [
				["日", "一", "二", "三", "四", "五", "六"],
				["SUN", "MON", "TUR", "WED", "THU", "FRI", "SAT"],
				["日", "一", "二", "三", "四", "五", "六"]],
		"clear" : [["清空"], ["CLS"], ["清空"]],
		"today" : [["今天"], ["TODAY"], ["今天"]],
		"close" : [["关闭"], ["CLOSE"], ["关闭"]]
	},
	init : function(lang, dateFormatStyle) {
		if (lang != null) {
			this.lang = lang;
		}
		if (dateFormatStyle != null) {
			this.dateFormatStyle = dateFormatStyle;
		}
		this.panel = g("calendarPanel");
		this.container = g("ContainerPanel");
		this.date = new Date();
		this.year = this.date.getFullYear();
		this.month = this.date.getMonth();
		this.draw();
		this.setYearMonth();
	},
	draw : function() {
		calendar = this;
		var mvAry = []; 
	    mvAry[mvAry.length] = '  <div style="margin: 0px;">';
		mvAry[mvAry.length] = '    <div id="MonthView" style="display:none;"></div>';
		mvAry[mvAry.length] = '    <div id="YMPickView" style="display:none;"></div>';
	    mvAry[mvAry.length] = '    <table id="calendarHead" width="100%" border="0" cellpadding="0" cellspacing="1">';
	    mvAry[mvAry.length] = '      <tr>';
	    mvAry[mvAry.length] = '        <th align="left" width="1%"><input style="border: 1px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';width:16px;height:20px;" type="button" id="prevMonth" value="&lt;" /></th>';
	    mvAry[mvAry.length] = '        <th align="center" width="98%" nowrap="nowrap"><div style="cursor:hand;font-size:12px;"><span onclick="cal.showPickView();" id="calendarYear"></span>&nbsp;<span onclick="cal.showMonthView();" id="calendarMonth"></span></div></th>';
	    mvAry[mvAry.length] = '        <th align="right" width="1%"><input style="border: 1px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';width:16px;height:20px;" type="button" id="nextMonth" value="&gt;" /></th>';
	    mvAry[mvAry.length] = '      </tr>';
	    mvAry[mvAry.length] = '    </table>';
	    mvAry[mvAry.length] = '    <table id="calendarTable" width="100%" style="border:0px solid #CCCCCC;background-color:#FFFFFF" border="0" cellpadding="3" cellspacing="1">';
	    mvAry[mvAry.length] = '      <tr>';
	    for (var i = 0; i < 7; i++) {
	        mvAry[mvAry.length] = '      <th align="center" style="font-size:12px;font-weight:normal;background-color:' + calendar.colors["tr_bg"] + ';color:' + calendar.colors["tr_word"] + ';">' + calendar.language["weeks"][this.lang][i] + '</th>';
	    }
	    mvAry[mvAry.length] = '      </tr>';
	    for (var i = 0; i < 6; i++) {
	        mvAry[mvAry.length] = '    <tr align="center">';
	        for (var j = 0; j < 7; j++) {
	            if (j == 0) {
	                mvAry[mvAry.length] = '  <td style="cursor:default;color:' + calendar.colors["sun_word"] + ';"></td>';
	            }
	            else if (j == 6) {
	                mvAry[mvAry.length] = '  <td style="cursor:default;color:' + calendar.colors["sat_word"] + ';"></td>';
	            }
	            else {
	                mvAry[mvAry.length] = '  <td style="cursor:default;"></td>';
	            }
	        }
	        mvAry[mvAry.length] = '    </tr>';
	    }
	    mvAry[mvAry.length] = '      <tr style="background-color:' + calendar.colors["input_bg"] + ';">';
	    mvAry[mvAry.length] = '        <th colspan="2"><input type="button" id="calendarClear" value="' + calendar.language["clear"][this.lang] + '" style="border: 1px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';width:100%;height:20px;font-size:12px;"/></th>';
	    mvAry[mvAry.length] = '        <th colspan="3"><input type="button" id="calendarToday" value="' + calendar.language["today"][this.lang] + '" style="border: 1px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';width:100%;height:20px;font-size:12px;"/></th>';
	    mvAry[mvAry.length] = '        <th colspan="2"><input type="button" id="calendarClose" value="' + calendar.language["close"][this.lang] + '" style="border: 1px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';width:100%;height:20px;font-size:12px;"/></th>';
	    mvAry[mvAry.length] = '      </tr>';
	    mvAry[mvAry.length] = '    </table>';
	    mvAry[mvAry.length] = '  </div>';
	    this.panel.innerHTML = mvAry.join("");
		this.dateHead = g('calendarHead');
		this.dateTable = g('calendarTable');

		this.calendarYear = g("calendarYear");
		this.calendarMonth = g("calendarMonth");
		this.prevMonth = g("prevMonth");
		this.prevMonth.onclick = function() {
			calendar.goPrevMonth();
		}; 
		this.prevMonth.onblur = function() {
			calendar.onblur();
		};

		this.nextMonth = g("nextMonth");
		this.nextMonth.onclick = function() {
			calendar.goNextMonth();
		}; 
		this.nextMonth.onblur = function() {
			calendar.onblur();
		};

		this.calendarClear = g("calendarClear");
		this.calendarClose = g("calendarClose");
		this.calendarToday = g("calendarToday");
	},
	bindYear : function() {
		this.calendarYear.innerHTML = this.year + "年";
	},
	bindMonth : function() {
		var m = (this.month + 1);
		if (m < 10) {
			m = "0" + m;
		}
		this.calendarMonth.innerHTML = m + "月";
	},
	setPickYear : function(obj) {
		if (this.tmpY) {
			this.tmpY.className = "";
		}
		this.tmpY = obj;
		this.tmpY.className = "FontRed";
	},
	setPickMonth : function(obj) {
		if (this.tmpM) {
			this.tmpM.className = "";
		}
		this.tmpM = obj;
		this.tmpM.className = "FontRed";
	},
	showPickView : function() {
		if (this.pickView) {
		} else {
			this.pickView = g('YMPickView');
		}
		this.drawPickView();
		this.pickView.style.display = "";
		this.dateHead.style.display = "none";
		this.dateTable.style.display = "none";
	},
	hidePickView : function() {
		this.pickView.style.display = "none";
		this.dateHead.style.display = "";
		this.dateTable.style.display = "";
	},
	showMonthView:function(){
		if(this.monthView){
		}else{
			this.monthView=g('MonthView');
		}
		this.drawMonthView();
		this.monthView.style.display = "";
		this.dateHead.style.display = "none";
		this.dateTable.style.display = "none";
	},
	hideMonthView:function(){
		this.monthView.style.display = "none";
		this.dateHead.style.display = "";
		this.dateTable.style.display = ""; 
	},
	pickMonth : function(obj) {		
		if (obj.month) {
			this.month = obj.month * 1 - 1;
		}
		this.boundDate();
		this.hideMonthView();
	},
	pickCurYM : function() {
		// 当前年月
		var today = new Date();
		this.year = today.getFullYear();
		this.month = today.getMonth();
		this.boundDate();
		this.hidePickView();
	},
	pickOk : function() {
		if (this.tmpY) {
			this.year = this.tmpY.year * 1;
		}
		if (cal.tmpM) {
			this.month = this.tmpM.month * 1 - 1;
		}
		this.boundDate();
		this.hidePickView();
	},
	pickCancle : function() {
		this.hidePickView();
	},
	setYearMonth : function() {
		var y = this.date.getFullYear();// 年
		var m = this.date.getMonth() + 1;// 月
		m = m < 10 ? "0" + m : m;
		if (this.yearPick) {
		} else {
			this.yearPick = g('calendarYear');
		}
		if (this.monthPick) {
		} else {
			this.monthPick = g('calendarMonth');
		}
		this.yearPick.innerHTML = y + "年";
		this.monthPick.innerHTML = m + "月";
	},
	goPrevMonth : function() { 
		this.month--;
		if (this.month == -1) {
			this.year--;
			this.month = 11;
		}
		this.date = new Date(this.year, this.month, 1);
		this.setYearMonth();
		this.bindData();
	},
	goNextMonth : function() { 
		this.month++;
		if (this.month == 12) {
			this.year++;
			this.month = 0;
		}
		this.date = new Date(this.year, this.month, 1);
		this.setYearMonth();
		this.bindData();
	},
	drawMonthView : function() {
		var sb = [];
		sb[sb.length] = '<table style="width:100%;height:100%;" border="0">';
		var months = this.language["months"][this.lang];
		for (var i = 0; i < 6; i++) {		
			sb[sb.length] = '<tr>';		
			sb[sb.length] = '	<td><a href="#" class="abtn" ><span id="Mon'+(i+1)+'" month="'+(i+1)+'" onclick="cal.pickMonth(this)">'+months[i]+'</span></a></td>';
			sb[sb.length] = '	<td><a href="#" class="abtn" ><span id="Mon'+(i+7)+'" month="'+(i+7)+'" onclick="cal.pickMonth(this)">'+months[i+6]+'</span></a></td>';
			sb[sb.length] = '</tr>';
		} 		
		sb[sb.length] = '   <tr>';
		sb[sb.length] = '		<td height="25" colspan="2" align="center" style="background-color:' + this.colors["input_bg"] + ';" >'; 
		sb[sb.length] = '			<input type="button" value="取 消" style="border: 1px solid ' + this.colors["input_border"] + ';background-color:' + this.colors["input_bg"] + ';height:20px;font-size:12px;" onclick="cal.hideMonthView();"/>';
		sb[sb.length] = '		</td>'
		sb[sb.length] = '	</tr>';
		sb[sb.length] = '</table>';
		this.monthView.innerHTML = sb.join("");		
		this.initPickMonth();
	},
	initPickMonth:function(){
		var obj;
		for (var i = 1; i < 13; i++) {
			obj = g('Mon' + i);
			if (obj && obj.month * 1 == (this.month + 1)) {
				this.tmpM = obj;
				this.tmpM.className = "FontRed";
				break;
			}
		}
	},
	drawPickView : function() {
		var sb = [];
		sb[sb.length] = '<div style="margin: 0px;z-index:1;" id="calendarTop">';
		sb[sb.length] = '<table width="185" style="height:150" border="0" cellpadding="0" cellspacing="1">';
		sb[sb.length] = '<tr>';
		// 年份选择
		sb[sb.length] = '<td>';
		sb[sb.length] = '<table style="height: 125px">';
		sb[sb.length] = '<tr align="center" height="20">';
		sb[sb.length] = '<td>';
		sb[sb.length] = '<input style="border: 1px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';width:16px;height:20px;" type="button" onclick="cal.prevYear()" value="&lt;" />';
		sb[sb.length] = '</td><td>';
		sb[sb.length] = '<input style="border: 1px solid ' + calendar.colors["input_border"] + ';background-color:' + calendar.colors["input_bg"] + ';width:16px;height:20px;" type="button" onclick="cal.nextYear()" value="&gt;" />';
		sb[sb.length] = '</td>';
		sb[sb.length] = '</tr>';		 
		var pick = this.pick * 10;// 年间隔
		for (var i = 0; i < 5; i++) { 
			sb[sb.length] = '<tr>';										
			sb[sb.length] = '<td nowrap><a href="#" class="abtn"><span onclick="cal.setPickYear(this)" id="Y'+(i+1)+'" year="'+(cal.year-5+i+pick)+'">'+(cal.year-5+i+pick)+'年</span></a></td>';
			sb[sb.length] = '<td nowrap><a href="#" class="abtn"><span onclick="cal.setPickYear(this)" id="Y'+(i+1)*2+'" year="'+(cal.year+i+pick)+'">'+(cal.year+i+pick)+'年</span></a></td>';
			sb[sb.length] = '</tr>';		
		} 		
		sb[sb.length] = '</table>';
		sb[sb.length] = '</td>';
		sb[sb.length] = '<td width="1" bgcolor="#6699FF"></td>'; 
		// 月份选择
		sb[sb.length] = '<td width="85">';
		sb[sb.length] = '<table style="width:100%;height:100%;" border="0">';
		var months = this.language["months"][this.lang];
		for (var i = 0; i < 6; i++) {		
			sb[sb.length] = '<tr>';		
			sb[sb.length] = '<td><a href="#" class="abtn" ><span id="M'+(i+1)+'" month="'+(i+1)+'" onclick="cal.setPickMonth(this)">'+months[i]+'</span></a></td>';
			sb[sb.length] = '<td><a href="#" class="abtn" ><span id="M'+(i+7)+'" month="'+(i+7)+'" onclick="cal.setPickMonth(this)">'+months[i+6]+'</span></a></td>';
			sb[sb.length] = '</tr>';
		} 
		sb[sb.length] = '</table>'; 
		sb[sb.length] = '</td>';   
		sb[sb.length] = '</tr>'; 
		sb[sb.length] = '<tr>';
		sb[sb.length] = '<td height="25" colspan="3" align="center" style="background-color:' + this.colors["input_bg"] + ';" >';
		sb[sb.length] = '<input type="button" value="今 月" style="border: 1px solid ' + this.colors["input_border"] + ';background-color:' + this.colors["input_bg"] + ';height:20px;font-size:12px;" onclick="cal.pickCurYM();"/>&nbsp';
		sb[sb.length] = '<input type="button" value="确 定" style="border: 1px solid ' + this.colors["input_border"] + ';background-color:' + this.colors["input_bg"] + ';height:20px;font-size:12px;" onclick="cal.pickOk();"/>&nbsp';
		sb[sb.length] = '<input type="button" value="取 消" style="border: 1px solid ' + this.colors["input_border"] + ';background-color:' + this.colors["input_bg"] + ';height:20px;font-size:12px;" onclick="cal.pickCancle();"/>';
		sb[sb.length] = '</td>'
		sb[sb.length] = '</tr>';
		sb[sb.length] = '</table>';
		sb[sb.length] = '</div>';
		this.pickView.innerHTML = sb.join("");	
		this.initPickData();
	},
	initPickData : function() {
		var obj;
		for (var i = 1; i < 13; i++) {
			obj = g('M' + i);
			if (obj && obj.month * 1 == (this.month + 1)) {
				this.tmpM = obj;
				this.tmpM.className = "FontRed";
				break;
			}
		}
		// initYear
		for (var i = 1; i < 11; i++) {
			obj = g('Y' + i);
			if (obj && obj.year * 1 == (this.year + this.pick * 10)) {
				this.tmpY = obj;
				this.tmpY.className = "FontRed";
				break;
			}
		}
	},
	nextYear : function() {
		this.pick += 1;
		this.drawPickView();
	},
	prevYear : function() {
		this.pick -= 1;
		this.drawPickView();
	},
	boundDate : function() { 
		this.date = new Date(this.year, this.month, 1);
		this.bindData();
	},
	update : function() {
		this.date = new Date(this.year, this.month, 1);
		this.bindData();
	},
	bindData : function() {
		this.bindYear();
		this.bindMonth();
		var calendar = this;
		var dateArray = this.getMonthViewArray(this.year, this.month);
		var tds = this.dateTable.getElementsByTagName("td");
		var tday;
		for (var i = 0; i < tds.length; i++) {
			tday = tds[i];
			// tds[i].style.color = calendar.colors["td_word_light"];
			tday.style.backgroundColor = this.colors["td_bg_out"];
			tday.onclick = function() { return; };
			tday.onmouseover = function() { return; };
			tday.onmouseout = function() { return; };
			if (i > dateArray.length - 1) 
				break;
			tday.innerHTML = dateArray[i];
			if (dateArray[i] != "&nbsp;") {
				tday.onclick = function() {
					var sb = [];
					try {
						if (calendar.dateControl != null) {
							var dt = new Date(calendar.year, calendar.month, this.innerHTML);
							var t = dt.getTime();
							if (t < calendar.min || t > calendar.max) {
								return;
							}
							var tmpDay = dt.format(calendar.dateFormatStyle);
							calendar.dateControl.value = tmpDay;
							if (calendar.dateControl.callback) {
								eval(calendar.dateControl.callback);
							}
						}
						calendar.hide();
					} catch (e) {
						showMsgPanel(sb.join(""));
					}
				};
				tday.onmouseover = function() {
					this.style.backgroundColor = calendar.colors["td_bg_over"];
				};
				tday.onmouseout = function() {
					this.style.backgroundColor = calendar.colors["td_bg_out"];
				};
				var tdDate = new Date(this.year, this.month, dateArray[i]);
				if (this.today == tdDate.format(this.dateFormatStyle)) {
					tday.style.backgroundColor = this.colors["cur_bg"];
					tday.onmouseover = function() {
						this.style.backgroundColor = calendar.colors["td_bg_over"];
					};
					tday.onmouseout = function() {
						this.style.backgroundColor = calendar.colors["cur_bg"];
					};
				}
				/** *********************uke begin 003************************** */
				var tdDateTime = tdDate.clearTime().getTime();
				if (tdDateTime < this.min || tdDateTime > this.max) {
				} else {
					tday.style.backgroundColor = this.colors["val_bg"];
					tday.onmouseover = function() {
						this.style.backgroundColor = calendar.colors["td_bg_over"];
					}
					tday.onmouseout = function() {
						this.style.backgroundColor = calendar.colors["val_bg"];
					}
				}

				/** *********************uke end 003************************** */
				if (this.dateControl != null
						&& this.dateControl.value == tdDate.format(this.dateFormatStyle)) {
					tday.style.backgroundColor = this.colors["sel_bg"];
					tday.onmouseover = function() {
						this.style.backgroundColor = calendar.colors["td_bg_over"];
					}
					tday.onmouseout = function() {
						this.style.backgroundColor = calendar.colors["sel_bg"];
					}
				}
			}
		}
		tday = null;
	},
	getMonthViewArray : function(y, m) {
		var mvArray = [];
		var dayOfFirstDay = new Date(y, m, 1).getDay();
		var daysOfMonth = new Date(y, m + 1, 0).getDate();
		for (var i = 0; i < 42; i++) {
			mvArray[i] = "&nbsp;";
		}
		for (var i = 0; i < daysOfMonth; i++) {
			mvArray[i + dayOfFirstDay] = i + 1;
		}
		return mvArray;
	}, 
	getAbsPoint : function(e) {
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
	},
	show : function(dateObj, popControl) {
		if (dateObj == null) {
			throw new Error("arguments[0] is necessary")
		}
		this.dateControl = dateObj;
		var objValue = dateObj.value.trim();
		if (objValue.length > 0) {
			if (this.checkIsValidDate(objValue)) {
				this.date = new Date(objValue.toDate(this.dateFormatStyle));
			} else {
				this.date = new Date();
			}
		} else {
			this.date = new Date();
		}

		this.today = new Date().format(this.dateFormatStyle);
		this.year = this.date.getFullYear();
		this.month = this.date.getMonth();

		/** **************add by uke 2008-04-16 begin 001******************** */
		this.min = new Date(this.beginYear, 1, 1).clearTime().getTime();
		this.max = new Date(this.endYear, 1, 1).clearTime().getTime();
		// 设置最小日期值
		if (dateObj.startDateField && dateObj.startDateField.length > 0) {
			var start = g(dateObj.startDateField);
			if (start && start.value.length > 0) {
				// YAHOO.log("minDate:"+start.value, "info", "validate");
				this.min = new Date(start.value.toDate(this.dateFormatStyle)).clearTime().getTime();
			} else if (dateObj.minDate && dateObj.minDate.length > 0) {
				// YAHOO.log("minDate:"+dateObj.minDate, "info", "validate");
				this.min = new Date(dateObj.minDate.toDate(this.dateFormatStyle)).clearTime().getTime();
			}
		} else if (dateObj.minDate !== undefined && dateObj.minDate.length > 0) {
			// YAHOO.log("minDate:"+dateObj.minDate, "info", "validate");
			this.min = new Date(dateObj.minDate.toDate(this.dateFormatStyle)).clearTime().getTime();
		}
		// 设置最大日期值
		if (dateObj.endDateField && dateObj.endDateField.length > 0) {
			var end = g(dateObj.endDateField);
			if (end && end.value.length > 0) {
				// YAHOO.log("maxDate:"+end.value, "info", "validate");
				this.max = new Date(end.value.toDate(this.dateFormatStyle)).clearTime().getTime();
			} else if (dateObj.maxDate && dateObj.maxDate.length > 0) {
				// YAHOO.log("maxDate:"+dateObj.maxDate, "info", "validate");
				this.max = new Date(dateObj.maxDate.toDate(this.dateFormatStyle)).clearTime().getTime();
			}
		} else if (dateObj.maxDate && dateObj.maxDate.length > 0) {
			// YAHOO.log("maxDate:"+dateObj.maxDate, "info", "validate");
			this.max = new Date(dateObj.maxDate.toDate(this.dateFormatStyle)).clearTime().getTime();
		}
		/** ***************************end 001******************************* */
		if (popControl == null) {
			popControl = dateObj;
		}

		var xy = this.getAbsPoint(dateObj);
		// 當控件高度或寬度超出頁面時, 調整位置
		var winHeight = window.document.body.clientHeight;
		var winWidth = window.document.body.clientWidth;
		var x = xy.x;
		var y = xy.y + dateObj.offsetHeight;
		var calHeight = 210;
		var calWidth = 180;
		if (y + calHeight > winHeight) {
			y = xy.y - calHeight;
		}
		this.panel.style.left = x + "px";
		this.panel.style.top = y + "px";

		// 由寒羽枫 2006-06-25 修改 → 把 visibility 变为 display，并添加失去焦点的事件
		this.panel.style.display = "";
		this.container.style.display = "";

		dateObj.onblur = function() {
			calendar.onblur();
		};
		popControl.onblur = function() {
			calendar.onblur();
		};
		this.container.onmouseover = function() {
			calendar.isFocus = true;
		};
		this.container.onmouseout = function() {
			calendar.isFocus = false;
		};
		this.container.onblur = function() {
			calendar.onblur();
		};		
		this.calendarToday.onclick = function() {  
			var today = new Date();
			var todayValue = today.clearTime().getTime();
			if (calendar.min && todayValue < calendar.min) {
				return;
			}
			if (calendar.max && todayValue > calendar.max) {
				return;
			} 
			calendar.dateControl.value= calendar.today;
			calendar.hide();
		}; 
		
		this.calendarClear.onclick = function() {
			if (calendar.dateControl && calendar.dateControl.required == "true") {
				// 标识为必输项时,日期不能清空
			} else {
				calendar.dateControl.value= "";
				calendar.hide();
			}
		};
		
		this.calendarClose.onclick = function() {
			calendar.hide();
		}; 
	},
	hide : function() {
		this.panel.style.display = "none";
		this.container.style.display = "none";
		this.isFocus = false;
	},
	onblur : function() {
		if (!this.isFocus) {
			this.hide();
		}
	},
	checkIsValidDate : function(str) {
		return true;
	}
};
var doc=function() {
	document.write('<div id="ContainerPanel" style="display:none"><div id="calendarPanel" style="position: absolute;display: none;z-index: 9;');
	document.write('background-color: #FFFFFF;border: 1px solid #CCCCCC;width:175px;font-size:12px;"></div>');
	if (document.all) {
		document.write('<iframe style="position:absolute;z-index:6;width:expression(this.previousSibling.offsetWidth);');
		document.write('height:expression(this.previousSibling.offsetHeight);');
		document.write('left:expression(this.previousSibling.offsetLeft);top:expression(this.previousSibling.offsetTop);');
		document.write('display:expression(this.previousSibling.style.display);" scrolling="no" frameborder="no"></iframe>');
	}
	document.write('</div>');
}();

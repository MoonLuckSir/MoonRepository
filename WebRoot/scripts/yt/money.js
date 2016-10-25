/**
 * <li>depends: check.js,plus.js </li>
 * <li>LOG depends: yui,yui-log,check,validate</li>
 * 
 * @author uke
 */
var money_arys = [];
var skip_tab = false;
var skip_tabs = 0;
function initMoneyText(inpt) {
	money_log("initMoneyText:	");
	inpt.aryIndex = money_arys.length;
	addMoneyToAry(inpt.aryIndex, inpt);
	
	if (inpt.type == "hidden") {
		return;
	}
	// format
	if (isDecimal(inpt.value.toDecimal())) {
		var right = inpt.right ? Number(inpt.right) : 2;
		inpt.value = fmtMoney(inpt.value.toDecimal(), right, ".", ",");
	}
	inpt.className = "decimal";
	if (_setDirty) {
		inpt.dirty=false;
	}
	if (inpt.readOnly) {
		money_log("inpt readonly:	" + inpt.name);
		inpt.className = "decimal readonly";
		return;
	}

	// add event listener
	money_addEventListener(inpt, "keydown", moneyOnKeyDown, false);
	money_addEventListener(inpt, "keypress", moneyOnKeyPress, false);
	money_addEventListener(inpt, "blur", moneyOnBlur, false);
}
money_addEventListener = function(eventSource, eventType, fn, useCapture) {
	if (eventSource.addEventListener) {
		money_log("addEventListener:" + eventType);
		eventSource.addEventListener(eventType, fn, useCapture);
	} else if (eventSource.attachEvent) {
		money_log("attachEvent:	" + eventType);
		eventSource.attachEvent("on" + eventType, fn);
	}
};

String.prototype.toDecimal = function() {
	return function() {
		var number=this.replace(decimalRe, "");
		if(number=="")
			return 0;
		return number*1;
	};
}();

function addMoneyToAry(i, inpt) {
	money_arys[i] = inpt;
}

function getMoneyFromAry(i) {
	return money_arys[i];
}

function skipMoneyToNextRow(i) {
	if ((i + skip_tabs) >= money_arys.length) {
		return null;
	}
	var inpt = getMoneyFromAry(i + skip_tabs);
	if (inpt.readOnly || inpt.type == "hidden" ) {
		if ((inpt.aryIndex + skip_tabs) < money_arys.length) {
			return skipMoneyToNextRow(inpt.aryIndex);
		}
		return null;
	}
	return inpt;
}

function skipMoneyToPreRow(i){
	if ( i < skip_tabs) {
		return null;
	}
	var inpt = getMoneyFromAry(i - skip_tabs);	
	if (inpt.readOnly || inpt.type === "hidden" ) {
		if (inpt.aryIndex > skip_tabs) {
			return skipMoneyToPreRow(inpt.aryIndex);
		}
		return null;
	}
	return inpt;
}

function money_log(msg, d, m) {
	var model = m || "money";
	var debug = d || "info";// info,debug,warn,error
	Log(msg, debug, model);
}
function moneyOnKeyDown(e) {
	if (init_form)
		return;
	var event = window.event;
	var inpt = event.srcElement || event.target;
	var code = event.keyCode;
	//Enter or Down key 
	if ((code == 13||code==40) && skip_tab && skip_tabs > 0) {
		money_log("skip_tab:	" + skip_tabs);
		var next = skipMoneyToNextRow(inpt.aryIndex);
		if(next){
			next.focus();
			next.select();
		}
		return;
	}
	if(code==38&& skip_tab && skip_tabs > 0){
		money_log("skip_tab:	" + skip_tabs);
		var next = skipMoneyToPreRow(inpt.aryIndex);
		if(next){
			next.focus();
			next.select();
		}
		return;
	}
	if (code == 13 && inpt.enter2tab && inpt.enter2tab == "true") {
		event.keyCode = 9;
		return;
	}
	// Skip alt,tab, chinese , Esc, End, Home, Left, Right, ..
	if (event.altKey || event.shiftKey || code == 9 || code == 13 || code == 27
			|| (code >= 33 && code <= 40)) {
		return;
	}
	// Set dirty
	money_dirty(inpt);
	
	var left = inpt.left ? Number(inpt.left) : 14;
	var right = inpt.right ? Number(inpt.right) : 2;
	var maxlength = Math.floor(left / 3) + left + right + 1;
	// Skip chinese
	if (code == 229) {
		// maxlength
		if (inpt.value.length < maxlength) {
			return;
		}
	}
	if (event.ctrlKey) {
		if (code == 86) {// ctrl + v
			money_paste(inpt, event);
		} else {
			return;
		}
	}
	// keycode "-"
	if (code == 109 || (!event.shiftKey && code == 189)) {
		money_negative(inpt, event);
	}
	// keycode "+" or "="
	if (code == 107 || code == 187) {
		money_plus(inpt, event);
	}
	if (code >= 96 && code <= 105) {
		code -= 48;
	}
	money_log("moneyOnKeyDown:	" + code);
	// Backspace,Delete,0~9
	if (code == 8 || code == 46 || (code >= 48 && code <= 57)) {
		formatMoney(inpt, true, event);
	}
	// IE
	event.returnValue = false;
	event.keyCode = 0;
	money_log("cancle key down evnet:");

}
function moneyOnKeyPress(e) {
	if (init_form)
		return;
	var event = window.event;
	var inpt = event.srcElement || event.target;
	var code = event.keyCode;
	money_log("moneyOnKeyPress:	" + code, "info", "money");	
	if (code >= 65296 && code <= 65305) {
		code -= 65248;
	}
	if ((code >= 48 && code <= 57)) {
		formatMoney(inpt, true, event);
	} else if (code == 45) {
		money_negative(inpt, event);
	} else if (code == 61) {
		money_plus(inpt, event);
	}
	// money_log("input Value: " + inpt.value, "info", "money");
	// IE
	event.returnValue = false;
	event.keyCode = 0;
	money_log("cancle key press evnet:");
}
function moneyOnKeyUp(e) {
	money_log("moneyOnKeyUp:", "info", "money");
	var event = window.event;
	var inpt = event.srcElement || event.target;
	var code = event.keyCode;
	// IE
	event.returnValue = false;
	event.keyCode = 0;
	money_log("cancle key up evnet:");
}
function moneyOnFocus(e) {
}
function moneyOnBlur(e) {
	if (init_form||blur_form)
		return;
	blur_form=true;
	var event = window.event;
	var inpt = event.srcElement || event.target;
	money_log("moneyOnBlur:" + inpt.name);
	// IE
	if (!money_validate(inpt)) {
		event.returnValue = false;
		event.keyCode = 0;
		money_log("cancle blur event:");
	}
	// do other actions
	if (inpt.blur) {
		try {
			eval(inpt.blur + "(inpt)");
		} catch (e) {
		}
	}
	blur_form=false;
}

function money_dirty(inpt){
	if (_setDirty) {
		if (inpt.dirty) {
		} else {
			inpt.className = inpt.className + " dirty-cell" ;
			inpt.dirty = true;
		}
	}
}
function money_validate(inpt) {
	ClearSpanMessage();
	var value = inpt.value;
	money_log("money_validate:" + value);
	var money = value.replace(/[\d-,.]/g, "");
	// is required
	if (inpt.required && inpt.required == "true") {
		var temp = value.replace(/[^\d-]/g, "");
		if (Number(temp) == 0) {
			var msg = GetSpanMessage(MsgMustInput, inpt.label, inpt.params);
			WriteErrMessage(inpt, msg);
			return false;
		}
	}
	if (money.length > 0) {
		var msg = GetSpanMessage(ONLY_NUMBER, inpt.label, inpt.params);
		WriteErrMessage(inpt, msg);
		return false;
	}
	return true;
}
formatMoney = function(inpt, focusThis, event) {
	// Log("formatMoney.1", "info", "money");
	var moneyText = inpt;
	var text = moneyText.value;
	if (null == text) {
		return;
	}
	var left = moneyText.left ? Number(moneyText.left) : 14;
	var right = moneyText.right ? Number(moneyText.right) : 2;
	var len = (left * 1) + (right * 1);
	var dec = (right * 1);
	var comma = true;
	var First = "";
	var ccySign = "";
	var code = event.keyCode;

	var isPaste = event.ctrlKey && code == 86;
	// Key Pad 0 ~ 9
	if (code >= 96 && code <= 105) {
		code = code - 48;
	}
	var value = moneyText.value;
	// existed "-"
	if (moneyText.allowLowerZero == "true") {
		var reg = /[-]{1}/;
		if (reg.test(value)) {
			First = "-";
		}
	}
	// currency symbol
	if (moneyText.ccySign) {
		ccySign = moneyText.ccySign;
	}
	// dolt enable
	if (moneyText.forbidDolt == "true") {
		comma = false;
	}
	var ch = String.fromCharCode(code);
	// SBC
	if (code >= 65296 && code <= 65305) {
		code = code - 65248;
		ch = "" + (code - 48);
	}
	var cursor = new caretPos(moneyText);
	cursor.getPosition();

	// keycode "-"
	var temp = value.replace(/[^0-9.,]/g, "");
	// Backspace
	if (code == 8) {
		money_log("keycode:Backspace");

		if (cursor.selectStart == cursor.selectEnd) {
			// Get Char under cursor
			ch = value.substr(cursor.selectStart - 1, 1);
			if (ch == "-") {
				First = "";
			}
			if (ch == ccySign) {
				ccySign = "";
			}
			cursor.selectStart = (ch == "," || ch == ".") ? cursor.selectStart
					- 2 : cursor.selectStart - 1;
			if (cursor.selectStart < 0) {
				cursor.selectStart = 0;
			}
		}
		// Get the char before cursor
		ch = (cursor.selectStart > 0)
				? value.substr(cursor.selectStart - 1, 1)
				: "";
		if (ch == "," || ch == ".") {
			cursor.selectStart--;
			ch = value.substr(cursor.selectStart - 1, 1);
		}
		if (ch == "-" || ch == ccySign) {
			ch = "";
		}
		// if value of money less zero
		if (parseFloat(value) == 0) {
			First = "";
		}
		// Reomve the selection
		value = value.substring(0, cursor.selectStart - 1)
				+ (ch == "" ? "" : "X") + value.substr(cursor.selectEnd);
	}
	// Delete
	if (code == 46) {
		money_log("keycode:Delete");
		if (cursor.selectStart == cursor.selectEnd) {
			// Get Char under cursor
			ch = value.substr(cursor.selectStart, 1);
			if (ch == "-") {
				First = "";
			}
			if (ch == ccySign) {
				ccySign = "";
			}
			cursor.selectEnd = (ch == "," || ch == ".")
					? cursor.selectEnd + 2
					: cursor.selectEnd + 1;
			if (cursor.selectEnd > value.length) {
				cursor.selectEnd = value.length;
			}
		}
		// Get the char after cursor
		ch = (cursor.selectEnd < value.length) ? value.substr(cursor.selectEnd,
				1) : "";
		if (ch == "," || ch == ".") {
			cursor.selectEnd++;
			ch = value.substr(cursor.selectEnd, 1);
		}
		if (ch == "-" || ch == ccySign) {
			ch = "";
		}
		// if value of money less zero
		if (parseFloat(value) == 0) {
			First = "";
		}
		cursor.setPosition(cursor.selectStart, cursor.selectEnd);
		// Reomve the selection
		value = value.substring(0, cursor.selectStart) + (ch == "" ? "" : "X")
				+ value.substr(cursor.selectEnd + 1);
	}
	// .
	if ((code == 110 || (!event.shiftKey && code == 190))) {
		money_log("keycode:.");
		return false;
		var maxlength = len + Math.floor(left / 3) + 1;
		// alert("maxlength: "+maxlength);
		if (text.length == maxlength) {
			return;
		}
		var re = value.replace(/[^0-9]/g, "").length * 1;
		if (re < len) {
			ch = "0";
			if (re + 2 <= len && parseFloat(value.replace(/[^0-9]/g, "")) != 0) {
				value = value.substring(0, cursor.selectStart) + "XX"
						+ value.substr(cursor.selectEnd);
			} else {
				value = value.substring(0, cursor.selectStart) + "X"
						+ value.substr(cursor.selectEnd);
			}
		} else {
			if (re >= len
					&& parseFloat(text.replace(/[^0-9]/g, "").charAt(0)) <= 0) {
				ch = "0";
				var tureLen = value.replace(/[^0-9]/g, "").replace(/^0+/g, "").length;
				if (tureLen + 2 <= len
						&& parseFloat(value.replace(/[^0-9]/g, "")) != 0) {
					value = value.substring(0, cursor.selectStart) + "XX"
							+ value.substr(cursor.selectEnd);
				} else {
					value = value.substring(0, cursor.selectStart) + "X"
							+ value.substr(cursor.selectEnd);
				}
			} else {
				// other component to MoneyTextField
				First = "";
				value = "0";
			}
		}
	}
	// 0 ~ 9
	if (code >= 48 && code <= 57) {
		money_log("keycode:0 ~ 9");

		var selectLength = cursor.selectEnd - cursor.selectStart;
		var maxlength = len + Math.floor(left / 3) + 1 - selectLength;
		// alert("maxlength: "+maxlength);
		if (text.length == maxlength) {
			return;
		}
		// Assemble value
		value = value.substring(0, cursor.selectStart) + "X"
				+ value.substr(cursor.selectEnd);
	}
	// delete,backspace,-,+,.,
	if (code == 8 || code == 46 || (code >= 48 && code <= 57) || code == 109
			|| (!event.shiftKey && code == 189) || code == 107
			|| (event.shiftKey && code == 187) || code == 110
			|| (!event.shiftKey && code == 190)) {
		// Remove non-numeric characters
		value = value.replace(/[^0-9X]/g, "");
		// Add "."
		if (dec > 0) {
			value = padChar(value, "0", len, true);
			value = value.substring(0, value.length - dec) + "."
					+ value.substr(value.length - dec);
		}
		// replace leading zeros
		value = (code == 48) ? value.replace(/^[0X]+/g, "") : value.replace(
				/^[0]+/g, "");
		if (value == "") {
			value = "0";
		}
		// Add one zero if first char is "."
		if (value.substr(0, 1) == ".") {
			value = "0" + value;
		}
		// Truncate if exceed size
		value = value.substr(value.length - len - (dec > 0 ? 1 : 0));
		// alert("value3: " + value);
		var NumVal = parseFloat(value.replace(/X/g, ch));
		// Add Commas
		if (comma) {
			value = addCommas(value);
		}
		// set back cursor position
		var pos = value.length;
		if (NumVal > 0) {
			pos = value.indexOf("X");
			if (code == 46) {
				if (pos == -1) {
					pos = value.length;
				}
			} else {
				if (value.replace(/[^X]/g, "").length == 2) {
					pos = pos + 2;
				} else {
					pos++;
				}
			}
		}
		var temp = "" + ccySign + First + value.replace(/X/g, ch);
		// alert(""+temp);
		if (ccySign != "" && First != "") {
			pos = pos + 2;
		} else {
			if (ccySign != "" || First != "") {
				pos = pos + 1;
			}
		}
		moneyText.value = "" + temp;
		// alert("value1:"+moneyText.value);
		cursor.setPosition(pos, pos);
		// alert("value2:"+moneyText.value);
	}
	return false;
};

// negative -_
money_negative = function(moneyText, event) {
	money_log("keycode:- ");
	// keycode "-"
	var cursor = new caretPos(moneyText);
	cursor.getPosition();
	// currency symbol
	var value = moneyText.value;
	// existed "-"
	var First = "";
	if (moneyText.allowLowerZero == "true") {
		var reg = /[-]{1}/;
		if (reg.test(value)) {
			First = "-";
		}
	}
	var ccySign = "";
	if (moneyText.ccySign) {
		ccySign = moneyText.ccySign;
	}
	var temp = value.replace(/[^0-9.,]/g, "");
	if (moneyText.allowLowerZero == "true" && First == "") {
		moneyText.value = "" + ccySign + "-" + temp;
		cursor.setPosition(cursor.selectStart + 1, cursor.selectEnd + 1);
	}
}
// plus +=
money_plus = function(moneyText, event) {
	money_log("keycode:+ or = ");
	var cursor = new caretPos(moneyText);
	cursor.getPosition();
	var value = moneyText.value;
	// existed "-"
	var First = "";
	if (moneyText.allowLowerZero == "true") {
		var reg = /[-]{1}/;
		if (reg.test(value)) {
			First = "-";
		}
	}
	var ccySign = "";
	if (moneyText.ccySign) {
		ccySign = moneyText.ccySign;
	}
	var temp = value.replace(/[^0-9.,]/g, "");
	if (moneyText.allowLowerZero == "true" && First == "-") {
		moneyText.value = "" + ccySign + temp;
		cursor.setPosition(cursor.selectStart - 1, cursor.selectEnd - 1);
	}
}
// ctrl+v
money_paste = function(moneyText, event) {
	var data = window.clipboardData.getData("text");
	if (!data) {
		return;
	}
	var value = moneyText.value;
	var First = "";
	if (moneyText.allowLowerZero == "true") {
		var reg = /[-]{1}/;
		if (reg.test(value)) {
			First = "-";
		}
	}
	data = data.replace(/[^0-9.]/g, "");
	money_log("paste2: " + data);
	if (data.length > 0) {
		var left = moneyText.left ? Number(moneyText.left) : 14;
		var right = moneyText.right ? Number(moneyText.right) : 2;
		if (data.length <= (left + right + 1)) {
			moneyText.value = First + fmtMoney(data, right, ".", ",");
			moneyText.select();
			moneyText.focus();
		}
	}
}
// Format Left String
padChar = function(source, chr, targetlength, leading) {
	var strSource = "" + source;
	var limit = targetlength - strSource.length;
	if (limit > 0) {
		for (var i = 0; i < limit; i++) {
			if (leading) {
				strSource = chr + strSource;
			} else {
				strSource = strSource + chr;
			}
		}
	}
	return strSource;
};

// Format Right String
addCommas = function(nStr) {
	nStr += "";
	nStr = nStr.replace(/,/g, "");
	x = nStr.split(".");
	x1 = x[0];
	x2 = x.length > 1 ? "." + x[1] : "";
	var rgx = /(\w+)(\w{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, "$1" + "," + "$2");
	}
	return x1 + x2;
};
function caretPos(obj) {
	this.element = obj;
	this.selectStart = 0;
	this.selectEnd = 0;
	this.getPosition = function() {
		if (document.selection) {
			this.selectStart = Math.abs(document.selection.createRange()
					.moveStart("character", -1000000));
			this.selectEnd = Math.abs(document.selection.createRange().moveEnd(
					"character", -1000000));
		} else {
			this.selectStart = this.element.selectionStart;
			this.selectEnd = this.element.selectionEnd;
		}
	};
	this.setPosition = function(start, end) {
		if (this.element.setSelectionRange) {
			// this.element.focus();
			this.element.setSelectionRange(start, end);
		} else {
			if (this.element.createTextRange) {
				var range = this.element.createTextRange();
				range.collapse(true);
				range.moveEnd("character", end);
				range.moveStart("character", start);
				range.select();
			}
		}
	};
}
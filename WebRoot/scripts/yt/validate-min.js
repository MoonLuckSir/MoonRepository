/**
 * <li>depends: check.js,plus.js </li>
 * <li>LOG depends: yui,yui-log</li>
 * 
 * @author uke
 */
var showLog = false;
var init_form = true;
var blur_form = false;
var init_text = false;
var _setDirty = false;
function Log(msg, lev, type) {
	if (showLog) {
		YAHOO.log(msg, lev, type);
	}
}
// 显示日志
function showLogging() {
	showLog = true;
}
// 显示变更标识
function showDirty() {
	_setDirty = true;
}
function validator_log(msg, d, m) {
	var model = m || "validate";
	var debug = d || "info";// info,debug,warn,error
	Log(msg, debug, model);
}
function InitFormValidator(f) {
	validator_log("InitFormValidator.", "info", "validate");
	switch (typeof(f)) {
		case "string" :
			FormCheckInit((document.getElementById(f) || document.forms[f]));
			break;
		default :
			FormCheckInit(document.forms[0]);
	}
	init_form = false;
}
var span_required = "<span class='validate'>&nbsp;*</span>";
// 初始化校验
function FormCheckInit(form) {
	var el, name, val;
	for (var i = 0, j = form.elements.length; i < j; i++) {
		el = form.elements[i];
		name = el.name;
		val = el.value;
		if (name) {
			if (el.pattern) {
				// 必输项
				if (el.required && el.required == "true") {
					el.insertAdjacentHTML("afterEnd", span_required);
				}
				// format
				switch (el.pattern.toLowerCase()) {
					case 'money' :
						initMoneyText(el);
						break;
					case 'date' :
					 	break;
					case 'string':
						if(init_text)
							initTextField(el);
						break;
				}
			}
		}
	}
}
function FormValidate(f) {
	validator_log("FormValidate", "info", "validate");
	switch (typeof(f)) {
		case "string" :
			return FormCheck((document.getElementById(form) || document.forms[form]));
		default :
			return FormCheck(document.forms[0]);
	}
}
// 校验单一页面控件
function InputCheck(inpt) {
	validator_log("InputCheck Input:\t" + inpt.name, "info", "validate");
	if (inpt.readOnly) {
		return true;
	}
	if (inpt.autocheck && inpt.autocheck == "true") {
		// 必输项校验
		if (inpt.value.trim().length == 0) {
			if (inpt.required && inpt.required == "true") {
				var msg = GetSpanMessage(MsgMustInput, inpt.label, inpt.params);
				WriteErrMessage(inpt, msg);
				return false;
			}
			return true;
		}
		// 校驗值內容
		if (inpt.pattern) {
			switch (inpt.pattern.toLowerCase()) {
				case "int" :
					validator_log("InputCheck Integer:\t", "info", "validate");
					// 校验整型内容
					if (!CheckIntegerInput(inpt)) {
						return false;
					}
					break;
				case "date" :
					validator_log("InputCheck Date:\t", "info", "validate");
					if (!CheckYearMonth(inpt)) {
						return false;
					}
					if (!CheckDate(inpt)) {
						return false;
					}
					break;
				default :
				case "string" :
					validator_log("InputCheck String:\t", "info", "validate");
					// 校验字符串内容
					if (!CheckStringInput(inpt)) {
						return false;
					}
					break;
				case "money" :
					break;
			}
		}
	}
	return true;
}
var SpanErrorMsgID = "SpanErrorMsg";
var MsgMustInput = "{0}不允许为空";
var MsgMinLength = "{0}长度不能小于{1}位";
var MsgMustInputAll = "{0}必须输入{1}位";
var MsgNeedSetMaxLength = "{0}控件需要设置maxlength属性";
function FormCheck(form) {
	validator_log("FormCheck", "info", "validate");
	ClearSpanMessage();
	var el, name, val, disabled, data = '', hasSubmit = false;
	for (var i = 0, j = form.elements.length; i < j; i++) {
		el = form.elements[i];
		disabled = el.disabled;
		name = el.name;
		val = el.value;
		if (el.readOnly || el.hidden) {
			continue;
		}
		if (!disabled && name && el.pattern) {
			switch (el.pattern.toLowerCase()) {
				case 'int' :
				case 'money' :
				case 'date' :
				case 'string' :
					if (!InputCheck(el)) {
						return false;
					}
			}
		}
	}
	showWaitPanel('');
	return true;
}
var errDiv;
function setErrorContainer(divId) {
	// surport mult-area to show log messages
	errDiv = document.all(divId);
}
function hasErrorContainer() {
	return errDiv;
}
function WriteErrMessage(errMsg) {
	if (hasErrorContainer()) {
		errDiv.innerHTML = errMsg;
		try {
			if (errDiv.length == 2) {
				errDiv[0].innerHTML = errMsg;
				errDiv[1].innerHTML = errMsg;
			}
		} catch (e) {
		}
	} else {
		alert(errMsg);
	}
}
function WriteErrMessage(element, errMsg) {
	try {
		if (hasErrorContainer()) {
			errDiv.innerText = errMsg;
			try {
				if (errDiv.length == 2) {
					errDiv[0].innerHTML = errMsg;
					errDiv[1].innerText = errMsg;
				}
			} catch (e) {
			}
		} else {
			var s = "<span class='validate' name='" + SpanErrorMsgID
					+ "'>&nbsp;" + errMsg + "</span>";
			element.insertAdjacentHTML("afterEnd", s);
		}
	} catch (e) {
	}
	element.focus();
	element.select();
}
function GetSpanMessage(msg, label, params) {
	var label = label ? label : "";
	var retMessage = msg.replace("{0}", label);
	if (params) {
		for (var i = 1; i <= params.length; i++) {
			var paraIndex = "{" + i + "}"; // {n}
			retMessage = retMessage.replace(paraIndex, params[i - 1]);
		}
	}
	return retMessage;
}
function ClearSpanMessage() {
	validator_log("ClearSpanMessage.", "info", "validate");
	if (hasErrorContainer()) {
		errDiv.innerText = "";
		try {
			if (errDiv.length == 2) {
				errDiv[0].innerHTML = "";
				errDiv[1].innerText = "";
			}
		} catch (e) {
		}
		return;
	}
	var spans = getByNameAndTagName(SpanErrorMsgID, "span");
	if (spans == null) {
		return;
	}
	// validator_log("ClearSpanMessage:t" + spans.length, "info", "validate");
	for (var i = 0, j = spans.length; i < j; i++) {
		spans[i].innerHTML = "";
	}
}
function getByNameAndTagName(name, tagName) {
	var s = document.getElementsByTagName(tagName);
	if (!s) {
		return null;
	}
	var j = 0;
	var ret = new Array();
	for (var i = 0, k = s.length; i < k; i++) {
		if (s[i].name == name) {
			ret[j] = s[i];
			j++;
		}
	}
	if (j == 0) {
		return null;
	}
	return ret;
}
var NOT_INPUT_NUMBER = "{0}不允许输入数字";
var NOT_INPUT_CHINESE = "{0}不允许输入汉字";
var NOT_INPUT_UCHAR = "{0}不允许输入大写字母";
var NOT_INPUT_LCHAR = "{0}不允许输入小写字母";
var NOT_INPUT_CHAR = "{0}不允许输入字母";
var regNm = new RegExp(/[0-9]/g);
var regUChar = new RegExp(/[A-Z]/g);
var regLChar = new RegExp(/[a-z]/g);
var regChinese = new RegExp(/[\u4E00-\u9FA5]/g);
function CheckStringInput(inpt) {
	if (inpt.valueType) {
		var vtypes = eval("({" + inpt.valueType + "})");
		var hasNumber = vtypes.number != "undefined" && vtypes.number;
		var hasUChar = vtypes.upperChar != "undefined" && vtypes.upperChar;
		var hasLChar = vtypes.lowerChar != "undefined" && vtypes.lowerChar;
		var hasChinese = vtypes.chinese != "undefined" && vtypes.chinese;
		var hasDolt = vtypes.dolts != "undefined" && vtypes.dolts;
		var notNumber = !hasNumber && inpt.value.match(regNm);
		var notUChar = !hasUChar && inpt.value.match(regUChar);
		var notLChar = !hasLChar && inpt.value.match(regLChar);
		var notChina = !hasChinese && inpt.value.match(regChinese);
		if (notNumber) {
			// NOT_INPUT_NUMBER
			var msg = GetSpanMessage(NOT_INPUT_NUMBER, inpt.label, inpt.params);
			WriteErrMessage(inpt, msg);
			return false;
		}
		if (!hasUChar && !hasLChar && (notUChar || notLChar)) {
			// NOT_INPUT_CHAR
			var msg = GetSpanMessage(NOT_INPUT_CHAR, inpt.label, inpt.params);
			WriteErrMessage(inpt, msg);
			return false;
		}
		if (notUChar) {
			var msg = GetSpanMessage(NOT_INPUT_UCHAR, inpt.label, inpt.params);
			WriteErrMessage(inpt, msg);
			return false;
		}
		if (notLChar) {
			var msg = GetSpanMessage(NOT_INPUT_LCHAR, inpt.label, inpt.params);
			WriteErrMessage(inpt, msg);
			return false;
		}
		if (notChina) {
			var msg = GetSpanMessage(NOT_INPUT_CHINESE, inpt.label, inpt.params);
			WriteErrMessage(inpt, msg);
			return false;
		}
	}
	if (!mustInputAllCheck(inpt)) {
		return false;
	}
	return true;
}
var onlyNm = new RegExp(/[^0-9]/g);
var ONLY_NUMBER = "{0}只允许输入数字";
function CheckIntegerInput(inpt) {
	if (inpt.value.match(onlyNm)) {
		var msg = GetSpanMessage(ONLY_NUMBER, inpt.label, inpt.params);
		WriteErrMessage(inpt, msg);
		return false;
	}
	validator_log("CheckIntegerInput.", "info", "validate");
	if (!mustInputAllCheck(inpt)) {
		return false;
	}
	return true;
}
var MIN_DATE = "{0}不能小于{1}";
var MAX_DATE = "{0}不能大于{1}";
var DATE_MSG = "{0}格式必须为yyyy-MM-dd";
var regDate = /^(19[3-9]\d|2[01]{2}\d)(-|\/)(0[1-9]|1[012])\2(0[1-9]|[1-2]\d|3[01])$/;
function CheckDate(inpt) {
	if (inpt.value.length > 0) {
		if (inpt.formatter == "yyyy-MM-dd") {
			var fmt = inpt.formatter;
			if (!regDate.exec(inpt.value)) {
				var msg = GetSpanMessage(DATE_MSG, inpt.label, null);
				WriteErrMessage(inpt, msg);
				return false;
			}
			var dt = inpt.value.toDate(fmt).getTime();
			if (inpt.minDate) {
				var min = inpt.minDate.length > 0 ? inpt.minDate.toDate(fmt)
						.getTime() : Number.NEGATIVE_INFINITY;
				if (min > dt) {
					var msg = GetSpanMessage(MIN_DATE, inpt.label,
							[inpt.minDate]);
					WriteErrMessage(inpt, msg);
					return false;
				}
			}
			if (inpt.maxDate) {
				var max = inpt.maxDate.length > 0 ? inpt.maxDate.toDate(fmt)
						.getTime() : Number.POSITIVE_INFINITY;
				if (max < dt) {
					var msg = GetSpanMessage(MAX_DATE, inpt.label,
							[inpt.maxDate]);
					WriteErrMessage(inpt, msg);
					return false;
				}
			}
		}
	}
	return true;
}
var yearMonth = /^(19[3-9]\d|2[01]{2}\d)(-|\/)(0[1-9]|1[012])$/;
var YEAR_MONTH = "{0}格式必须为yyyy-MM";
function CheckYearMonth(inpt) {
	if (inpt.value.length > 0) {
		if (inpt.formatter == "yyyy-MM") {
			if (!yearMonth.exec(inpt.value)) {
				var msg = GetSpanMessage(YEAR_MONTH, inpt.label, null);
				WriteErrMessage(inpt, msg);
				return false;
			}
			var curValue = inpt.value.replace(/-/g, "");
			var checkMin = false;
			var checkMax = false;
			if (inpt.startDateField) {
				var start = document.getElementById(inpt.startDateField);
				if (start && start.value.length >= 7) {
					checkMin = true;
					var stValue = start.value.replace(/-/g, "").substr(0, 6);
					if (curValue < stValue) {
						var msg = GetSpanMessage(MIN_DATE, inpt.label,
								[stValue]);
						WriteErrMessage(inpt, msg);
						return false;
					}
				}
			}
			if (inpt.endDateField) {
				var end = document.getElementById(inpt.endDateField);
				if (end && end.value.length >= 7) {
					checkMax = true;
					var edValue = end.value.replace(/-/g, "").substr(0, 6);
					if (curValue > edValue) {
						var msg = GetSpanMessage(MAX_DATE, inpt.label,
								[edValue]);
						WriteErrMessage(inpt, msg);
						return false;
					}
				}
			}
			if (inpt.minDate) {
				var minValue = inpt.minDate.replace(/-/g, "").substr(0, 6);
				if (curValue < minValue) {
					var msg = GetSpanMessage(MIN_DATE, inpt.label,
							[inpt.minDate]);
					WriteErrMessage(inpt, msg);
					return false;
				}
			}
			if (inpt.maxDate) {
				var maxValue = inpt.maxDate.replace(/-/g, "").substr(0, 6);
				if (curValue > maxValue) {
					var msg = GetSpanMessage(MAX_DATE, inpt.label,
							[inpt.maxDate]);
					WriteErrMessage(inpt, msg);
					return false;
				}
			}
		}
	}
	return true;
}
var StringTooLonger = "{0}長度超出{1}位,一個漢字等同3位字母";
mustInputAllCheck = function(inpt) {
	var maxLength = inpt.maxlength;
	if (maxLength) {
	} else {
		maxLength = inpt.maxLength;
	}
	var a = stringLength(inpt.value);
	if (maxLength) {
		if (inpt.mustInputAll) {
			if (maxLength * 1 > a) {
				var msg = GetSpanMessage(MsgMustInputAll, inpt.label,
						[maxLength]);
				WriteErrMessage(inpt, msg);
				return false;
			}
		}
		if (maxLength < a) {
			var msg = GetSpanMessage(StringTooLonger, inpt.label, [maxLength]);
			WriteErrMessage(inpt, msg);
			return false;
		}
	} else {
		if (inpt.mustInputAll) {
			var msg = GetSpanMessage(MsgNeedSetMaxLength, inpt.name, null);
			WriteErrMessage(inpt, msg);
			return false;
		}
	}
	var minLength = inpt.minlength;
	if (minLength) {
	} else {
		minLength = inpt.minLength;
	}
	if (minLength) {
		if (minLength * 1 > a) {
			var msg = GetSpanMessage(MsgMinLength, inpt.label, [minLength]);
			WriteErrMessage(inpt, msg);
			return false;
		}
	}
	return true;
};
function stringLength(strTemp) {
	// UTF-8
	return strTemp.replace(/[^\x00-\xff]/g, "***").length;
}
fmtMoney = function(n, c, d, t) {
	var p = n < 0 ? "-" : "";
	var m = (c = Math.abs(c) + 1 ? c : 2, d = d || ",", t = t || ".", /(\d+)(?:(\.\d+)|)/
			.exec(n + "")), x = m[1].length > 3 ? m[1].length % 3 : 0;
	return p + (x ? m[1].substr(0, x) + t : "")
			+ m[1].substr(x).replace(/(\d{3})(?=\d)/g, "$1" + t)
			+ (c ? d + (+m[2] || 0).toFixed(c).substr(2) : "");
};

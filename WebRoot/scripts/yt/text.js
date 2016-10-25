/**
 * <li>depends: check.js,plus.js </li>
 * <li>LOG depends: yui,yui-log,check,validate</li>
 * 
 * @author uke
 */
 var text_arys = [];
var skip_text_tabs = 0;
function initTextField(inpt) {	
	text_log("initTextField:	");	
	inpt.aryIndex = text_arys.length;
	addTextToAry(inpt.aryIndex, inpt);
	
	if (inpt.type == "hidden") {
		return;
	}
	if (inpt.readOnly) {
		text_log("inpt.readOnly:	" + inpt.name);
		inpt.className = "readonly";
		return;
	}	
	
	// add event listener
	money_addEventListener(inpt, "keydown", textOnKeyDown, false);
	money_addEventListener(inpt, "blur", textOnBlur, false);
}

function text_log(msg, d, m) {
	var model = m || "text";
	var debug = d || "info";// info,debug,warn,error
	Log(msg, debug, model);
}

function addTextToAry(i, inpt) {
	text_arys[i] = inpt;
}

function getTextFromAry(i) {
	return text_arys[i];
}

function skipTextToNextRow(i) {
	if ((i + skip_text_tabs) >= text_arys.length) {
		return getTextFromAry(i);
	}
	var inpt = getTextFromAry(i + skip_text_tabs);
	if (inpt.type == "hidden" || inpt.readOnly) {
		if ((inpt.aryIndex + skip_text_tabs) < text_arys.length) {
			return skipTextToNextRow(inpt.aryIndex);
		}
		return null;
	}
	return inpt;
}

function skipTextToPreRow(i) {
	if (i < skip_text_tabs) {
		return getTextFromAry(i);
	}
	var inpt = getTextFromAry(i - skip_text_tabs);
	if (inpt.type == "hidden" || inpt.readOnly) {
		if (inpt.aryIndex > skip_text_tabs) {
			return skipTextToPreRow(inpt.aryIndex);
		}
		return null;
	}
	return inpt;
}

function textOnKeyDown(e) {
	if (init_form)
		return;
	var event = window.event;
	var inpt = event.srcElement || event.target;
	var code = event.keyCode;
	if ((code == 13||code==40) && skip_tab && skip_text_tabs > 0) {
		text_log("skip_tab Down:	" + skip_text_tabs);
		var next = skipTextToNextRow(inpt.aryIndex);
		if(next){
			next.select();
			next.focus();
		}
		return;
	}
	if( code==38 && skip_tab && skip_text_tabs > 0){
		text_log("skip_tab Up:	" + skip_text_tabs);
		var next = skipTextToPreRow(inpt.aryIndex);
		if(next){
			next.select();
			next.focus();
		}
		return;
		
	}	
	if (code == 13 && inpt.enter2tab && inpt.enter2tab == "true") {
		event.keyCode = 9;
		return;
	}
}

function textOnBlur(e) {
	if (init_form)
		return;		
	if (init_form||blur_form)
		return;		
	blur_form=true;
	var event = window.event;
	var inpt = event.srcElement || event.target;
	text_log("textOnBlur:" + inpt.name);
	// IE
	if (!text_validate(inpt)) {
		event.returnValue = false;
		event.keyCode = 0;
		text_log("cancle blur event:");
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

function text_validate(inpt) {
	ClearSpanMessage();
	var value = inpt.value;
	text_log("text_validate:" + value);
	if (!CheckStringInput(inpt)) {
		return false;
	}
	return true;
}
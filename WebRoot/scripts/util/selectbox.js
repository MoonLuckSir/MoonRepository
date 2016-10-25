/**
* simply copy selected options from  from to to
* @param frm form object
* @param from the `from` selectbox name
* @param to the `to` selectbox name
**/
function simpleCopy(frm,from,to) {
    var objFrom = getFormElement(frm,from);
    var objTo = getFormElement(frm,to);

    //alert(objTo.options.length);
    if(objFrom.selectedIndex == -1) {
        //		alert("请先从左边选择相应的选项再进行添加操作。");
        return;
    }
    var index;
    var notice = '';
    for(var i = 0;i < objFrom.options.length;i ++) {
        if(objFrom.options[i].selected) {
            if(optionExists(objTo,objFrom.options[i])) {
                continue;
            }

            index = objTo.options.length;
            objTo.options[index] = new Option(objFrom.options[i].text,objFrom.options[i].value);
        }
    }
    //	if(notice.length) {
    //		alert(notice);
    //	}
}

function moveOptions(form,fromBox,toBox) {

    var from = getFormElement(form,fromBox);
    var to = getFormElement(form,toBox);
   
    if(from.selectedIndex == -1) {
        return;
    }
    var index;
    for(var i = from.options.length - 1;i >= 0;i --) {
        if(from.options[i].selected) {
            if(!optionExists(to,from.options[i])) {
                index = to.options.length;
                to.options[index] = new Option(from.options[i].text,from.options[i].value);
            }
            from.options[i] = null;
        }
    }

}

/**
* copy selected options to to
**/
function copyOptions(frm,from,to,fromCat) {
    var objFC = getFormElement(frm,fromCat);
    var objFrom = getFormElement(frm,from);
    var objTo = getFormElement(frm,to);

    //alert(objTo.options.length);

    if(objFrom.selectedIndex == -1) {
        //		alert("请先从左边选择相应的选项再进行添加操作。");
        return;
    }

    //category option
    var catOption = objFC.options[objFC.selectedIndex];
    //find location
    var index = objTo.options.length;

    var hasCat = false;

    for(var i = 0;i < objTo.options.length;i ++) {
        if(objTo.options[i].value == catOption.value) {
            index = i + 1;
            hasCat = true;
            break;
        }
    }

    if(!hasCat) {
        objTo.options[index] = new Option('【' + catOption.text + '】',catOption.value);
        index ++;
    }

    var notice = '';

    for(var i = 0;i < objFrom.options.length;i ++) {
        if(objFrom.options[i].selected) {
            if(optionExists(objTo,objFrom.options[i])) {
                //				if(!notice.length) {
                //					notice += "下列选项已经添加，请勿重复选择:\n";
                //				}
                //				notice += objFrom.options[i].text + "\n";
                continue;
            }
            var option = new Option(objFrom.options[i].text,objFrom.options[i].value);
            insertOption(objTo,option,index);
            index ++;
        }
    }

    objFrom.selectedIndex = -1;
    //	if(notice.length) {
    //		//alert(notice);
    //	}
}

function copyAll(frm,from,to,fromCat) {
    var objFrom = getFormElement(frm,from);
    for(var i = 0;i < objFrom.options.length;i ++) {
        objFrom.options[i].selected = true;
    }
    copyOptions(frm,from,to,fromCat);
}
/**
* remove selected options
* @param frm form object
* @param selectbox name of selectbox
**/
function removeOptions(frm,selectbox) {
    var obj = getFormElement(frm,selectbox);

    var leftBracket = '【';

    for(var i = obj.options.length -1;i >= 0;i --) {
        var o = obj.options[i];
        if(!o.selected || o.text.substr(0,1) == leftBracket)
        continue;
        obj.options[i] = null;
    }

    var maxIndex = obj.options.length - 1;
    while(maxIndex >= 0 && obj.options[maxIndex].text.substr(0,1) == leftBracket) {
        obj.options[maxIndex] = null;
        maxIndex --;
    }

    flag = false;
    for(var i = obj.options.length - 1;i >= 0;i --) {
        if(obj.options[i].text.substr(0,1) != leftBracket) {
            flag = false;
        } else {
            if(flag == true) {
                obj.options[i] = null;
            }
            flag = true;
        }
    }

    obj.selectedIndex = -1;
}

/**
* remove all
**/
function removeAll(frm,selectbox) {
    obj = getFormElement(frm,selectbox);
    obj.options.length = 0;
}
/**
* insert an option as selectbox.options[index]
* @param selectbox select obj
* @param option option to be inserted
* @parm index location of option to be inserted
**/
function insertOption(selectbox,option,index) {
    for(var i = selectbox.options.length;i > index;i --) {
        selectbox.options[i] = new Option(selectbox.options[i - 1].text,selectbox.options[i - 1].value);
    }
    selectbox.options[index] = option;
}

/**
* check if an option exists in a select box
* @param selectbox selectbox object
* @param option option
**/
function optionExists(selectbox,option) {
    for(var i = 0;i < selectbox.options.length;i ++) {
        if(selectbox.options[i].value == option.value) {
            return true;
        }
    }
    return false;
}

/**
* get form element obj by name
* @param frm form object
* @param name element name
**/
function getFormElement(frm,name) {
    for(var i = 0;i < frm.elements.length;i ++) {
        if(frm.elements[i].name == name)
        return frm.elements[i];
    }
}

function selectAll(frm,name) {
    var box = getFormElement(frm,name);
    for(var i = 0; i < box.options.length;i ++) {
        box.options[i].selected = true;
    }
}
function selectNone(frm,name) {
    var box = getFormElement(frm,name);
    for(var i = 0; i < box.options.length;i ++) {
        box.options[i].selected = false;
    }
}
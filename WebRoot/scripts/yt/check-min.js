function isIP(strIP){
    if (isNull(strIP)) 
        return false;
    var re = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g 
    if (re.test(strIP)) {
        if (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256) 
            return true;
    }
    return false;
}

function isNull(str){
    if (str == "") 
        return true;
    var regu = "^[ ]+$";
    var re = new RegExp(regu);
    return re.test(str);
}

function isNumber(s){
    var regu = "^[0-9]+$";
    var re = new RegExp(regu);
    if (s.search(re) != -1) {
        return true;
    }
    else {
        return false;
    }
}

function isDecimal(str){
    if (isInteger(str)) 
        return true;
    var re = /^[-]{0,1}(\d+)[\.]+(\d+)$/;
    if (re.test(str)) {
        if (RegExp.$1 == 0 && RegExp.$2 == 0) 
            return false;
        return true;
    }
    else {
        return false;
    }
}

function isPort(str){
    return (isNumber(str) && str < 65536);
}

function isEmail(str){
    var myReg = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
    if (myReg.test(str)) 
        return true;
    return false;
}
function isInteger(str){
    var regu = /^[-]{0,1}[0-9]{1,}$/;
    return regu.test(str);
}
function isDecimal(str, l, d){
    if (isInteger(str)) {
        if (l == null) 
            return true;
        if (str < 0) 
            l--;
        if (str.length <= l) 
            return true;
    }    
    var re = /^[-]{0,1}(\d+)[\.]+(\d+)$/;
    if (re.test(str)) {
        if (l == null) 
            return true;
        if (d == null) 
            d = 0;
        if (RegExp.$1 == 0 && RegExp.$2 == 0) 
            return false;
        if (RegExp.$1.length + RegExp.$2.length <= l &&
        RegExp.$2.length <= d) 
            return true;
    }
    return false;
}

function getMaxDay(year, month){
    if (month == 4 || month == 6 || month == 9 || month == 11) 
        return "30";
    if (month == 2) 
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) 
            return "29";
        else 
            return "28";
    return "31";
}
/**
 * 名称：金额输入控件
 * 用途：可输入“##,###,00#”数字，
 *       并对其输入值进行格式化。
 *
 */

function numEditText(vName, vStyle, vMinVal, vMaxVal, vDefVal, vSize) {
    //--input name  控件名称标识
    this.name = vName;
    //--input class  样式名称
    this.style = vStyle;
    //--小数位数
    this.min = vMinVal;
    //--整数位数
    this.max = vMaxVal;
    //--input default value 初始值
    this.amt = vDefVal;
    //--input size  宽度
    this.size = vSize;
}

function numEditText(vName, vDefVal) {
    this.name = vName;
    this.amt = vDefVal;
    this.style = "numEdit";
    this.size = 18;
    this.min = 2;
    this.max = 15;
}

function showNumEditText() {
    document.write("<input name='" + this.name + "'size='" + this.size + "'class='" + this.style + "'value='" + this.amt + "' onblur='formatNumToStr(this)'>");
}

function formatObjToNum(obj){
    var df=obj.value;
    df=df.replace(',' ,"");
    df=df.replace(',' ,"");
    df=df.replace(',' ,"");
    df=df.replace(',' ,"");
    df=df.replace(',' ,"");
    return df;
}

//格式化数字文本
function formatObjToStrNum(obj) {
    try {
        var df=obj.value;
        df=formatObjToNum(obj);
        if(0==df){
            obj.value="0.00";
            return 0;
        }
        if(isNaN(df)){
            obj.focus();
            return;
        }
        var df2=""+Math.round(df*100);
        var len=df2.length;

        if(len>17){
            alert("金额过大");
            obj.focus();
            return;
        }else if(len>=15){
            df=df2.substring(0,len-14)+","+
              df2.substring(len-14,len-11)+","+
              df2.substring(len-11,len-8)+","+
              df2.substring(len-8,len-5)+","+
              df2.substring(len-5,len-2)+"."+df2.substring(len-2);

        }else if(len>=12){
            df=df2.substring(0,len-11)+","+
              df2.substring(len-11,len-8)+","+
              df2.substring(len-8,len-5)+","+
              df2.substring(len-5,len-2)+"."+df2.substring(len-2);

        }else if(len>=9){
            df=df2.substring(0,len-8)+","+
              df2.substring(len-8,len-5)+","+
              df2.substring(len-5,len-2)+"."+df2.substring(len-2);

        }else if(len>=6){
           df=df2.substring(0,len-5)+","+
              df2.substring(len-5,len-2)+"."+df2.substring(len-2);
        }else if(len>=3){
           df=df2.substring(len-5,len-2)+"."+df2.substring(len-2);
        }else if(len=2){
           if(obj.value>=0.1){
               df="0."+df2;
           }else{
               df="0.0"+df2;
           }
        }
        obj.value=df;
    } catch(e) {
        alert("有非法字符");
        obj.focus();
    }
}

//格式化数字文本
function formatStrNumToObj(val,obj) {
    try {
        var df=val;
        if(isNaN(df)){
            obj.value="0.00";
            return;
        }
        var df2=""+Math.round(df*100);
        var len=df2.length;
        if(len>17){
            alert("金额过大");
            return ;
        }else if(len>=15){
            df=df2.substring(0,len-14)+","+
              df2.substring(len-14,len-11)+","+
              df2.substring(len-11,len-8)+","+
              df2.substring(len-8,len-5)+","+
              df2.substring(len-5,len-2)+"."+df2.substring(len-2);

        }else if(len>=12){
            df=df2.substring(0,len-11)+","+
              df2.substring(len-11,len-8)+","+
              df2.substring(len-8,len-5)+","+
              df2.substring(len-5,len-2)+"."+df2.substring(len-2);

        }else if(len>=9){
            df=df2.substring(0,len-8)+","+
              df2.substring(len-8,len-5)+","+
              df2.substring(len-5,len-2)+"."+df2.substring(len-2);

        }else if(len>=6){
           df=df2.substring(0,len-5)+","+
              df2.substring(len-5,len-2)+"."+df2.substring(len-2);
        }else if(len>=3){
           df=df2.substring(len-5,len-2)+"."+df2.substring(len-2);
        }else if(len=2){
           if(obj.value>=0.1){
               df="0."+df2;
           }else{
               df="0.0"+df2;
           }
        }
        obj.value=df;
    } catch(e) {
        alert("有非法字符");
        obj.focus();
    }
}


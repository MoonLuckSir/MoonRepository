/**
* 去除多余空格函数
* trim:去除两边空格 lTrim:去除左空格 rTrim: 去除右空格
* 用法：
*     var str = "  hello ";
*     str = str.trim();
*/
String.prototype.trim = function()
{
    return this.replace(/(^[\s]*)|([\s]*$)/g, "");
}
String.prototype.lTrim = function()
{
    return this.replace(/(^[\s]*)/g, "");
}
String.prototype.rTrim = function()
{
    return this.replace(/([\s]*$)/g, "");
}

//判断值是否在数组中存在
Array.prototype.inArray = function (value) {
  for (var i = 0; i < this.length; i++) {
    if (this[i] === value) {
      return true;
    }
  }
  return false;
}


//排除数组重复项
Array.prototype.Unique = function()
{
  var a = {}; for(var i=0; i<this.length; i++)
  {
    if(typeof a[this[i]] == "undefined")
      a[this[i]] = 1;
  }
  this.length = 0;
  for(var i in a)
    this[this.length] = i;
  return this;
}

/**---------------------------------------------*/


/**
*下拉列表日期
*====================================================
*/
var today=new Date();
year=today.getFullYear();
month=today.getMonth()+1;


function dropYearOne(){
	for(i=2003;i<=2010;i++){
		if(i==year){
			if((month-1)==0){
				if((i-1)<2006){
					document.write("<option value="+i+" selected>"+i+"</option>");
				}else 
					document.write("<option value="+(i-1)+" selected>"+(i-1)+"</option>");
			}else{
				document.write("<option value="+i+" selected>"+i+"</option>");
			}
		}else{
			document.write("<option value="+i+">"+i+"</option>");
		}
	}
}

function dropMonthOne(){
	var previousMonth=month-1;
	if(previousMonth==0)
		previousMonth=month;

	for(i=1;i<=12;i++){
		if(i.toString().length==1)
			tmp="0"+i;
		else
			tmp=i;
			
		if(i==previousMonth)
			document.write("<option value="+tmp+" selected>"+tmp+"</option>");
		else
			document.write("<option value="+tmp+">"+tmp+"</option>");
	}
}

function dropYearTwo(){
	for(i=2003;i<=2010;i++){
		if(i==year){
			document.write("<option value="+i+" selected>"+i+"</option>");
		}else{
			document.write("<option value="+i+">"+i+"</option>");
		}
	}
}

function dropMonthTwo(){
	for(i=1;i<=12;i++){
		if(i.toString().length==1)
			tmp="0"+i;
		else
			tmp=i;
			
		if(i==month)
			document.write("<option value="+tmp+" selected>"+tmp+"</option>");
		else
			document.write("<option value="+tmp+">"+tmp+"</option>");
	}
}

//=======================================================================

/**========================================================函数库=================================================================*/
/*

-------------- 函数检索 --------------------------------------------

trim函数:                        trim() lTrim() rTrim()
校验字符串是否为空:                	checkIsNotEmpty(str)
校验字符串是否为整型:               checkIsInteger(str)
校验整型最小值:                    checkIntegerMinValue(str,val)
校验整型最大值:                    checkIntegerMaxValue(str,val) 
校验整型是否为非负数:               isNotNegativeInteger(str)
校验字符串是否为浮点型:             	checkIsDouble(str) 
校验浮点型最小值:                  checkDoubleMinValue(str,val)
校验浮点型最大值:                  checkDoubleMaxValue(str,val)
校验浮点型是否为非负数:             	isNotNegativeDouble(str)
校验字符串是否为日期型:             	checkIsValidDate(str)
校验两个日期的先后:                	checkDateEarlier(strStart,strEnd)
校验字符串是否为日期型(用于八位日期的):  checkIsValidDateWithEight(str)
将八位日期转换为10位日期，以指定的字符分隔：convertDateStr(datestr,splitstr)
校验字符串是否为中文:               checkIsChinese(str)
计算字符串的长度，一个汉字两个字符:   	realLength()
校验字符串是否符合自定义正则表达式:   	checkMask(str,pat)
得到文件的后缀名:                  getFilePostfix(oFile)
  
将键入的回车转换为Tab键   			ConvertEnterToTab()
将键入的回车转换提交  				EnterToSubmit(functionName,formName)
将键入的回车转换提交，无须验证的提交	EnterToSubmitTwo(formName)
验证是否有指定名称的单选框被选中		radio_Check(radio_name)
 
弹出警告对话框,并且将输入内容选中		f_alert(obj,alertInfo) 
校验移动电话号码					check_mobile(obj)
校验电话号码						check_phone(obj) 

判断是否为数字						check_number(obj)
得到值中的数字						parseNumber(theval)
将数字转换成三位逗号分隔的样式		formatNum(num,digit)
判断是否为小写英文字母 				check_lowercase(obj)
判断是否为大写英文字母				check_uppercase(obj)  
判断是否为英文字母					check_letter(obj)   
检查字符串是否只由汉字,字母,数字组成	check_ZhOrNumOrLett(obj)
校验ip地址的格式					check_IP(obj)
检查输入对象的值是否符合端口号格式		check_port(obj)
检查输入对象的值是否符合网址格式		check_URL(obj)
检查输入对象的值是否符合E-Mail格式	check_email(obj)
判断是否为邮政编码					check_zipcode(obj)
用户ID，可以为数字,字母,下划线的组合	check_userID(obj)
验证身份证号码是否有效 				check_IDno(obj)

判断当前对象是否可见				isVisible(obj)
判断当前对象及其父对象是否可见		checkPrVis(obj)

相关复选框操作     					selectAll(PelName,elName)  第928行之后
								selectOne(PelName)
								GetId(obj,tmp_obj)
								GetAllId(obj,tmp_obj,elName)
								GetAllCheckedId(obj_name,tmp_obj)获取目前所有已经选中的的复选框对象
								
将代表天数的数字转换成 N日，N个月，N年的形式
							  	convertNumToStrDate(num)
							  

将下拉列表框的默认值选中为指定的值 	setDefaultSelected(ele,eleValue)
 
屏蔽右键   			在body标签里加上		oncontextmenu='self.event.returnValue=false' 
禁止用左键选定页面		在body标签里加上 		onselectstart='return false'
判断对象是否为数组						isArray(data)
禁止空字符串,以及去除多余的空格			getNotNullStr(value)
获取列表框中选择项的文本					getSelectedText(objName)
对控件的onKeypress事件的处理，只能输入数字  validateTextInput(evt)
-------------- 函数检索 ----------------------------------------------
*/



/********************************** Empty **************************************/
/**
*校验字符串是否为空
*返回值：
*如果不为空，定义校验通过，返回true
*如果为空，校验不通过，返回false               参考提示信息：输入域不能为空！
*/
function checkIsNotEmpty(str)
{
    if(str.trim() == "")
        return false;
    else
        return true;
}//~~~
/*--------------------------------- Empty --------------------------------------*/
/********************************** Integer *************************************/
/**
*校验字符串是否为整型
*返回值：
*如果为空，定义校验通过，      返回true
*如果字串全部为数字，校验通过，返回true
*如果校验不通过，              返回false     参考提示信息：输入域必须为数字！
*/
function checkIsInteger(str)
{
    //如果为空，则通过校验
    if(str == "")
        return true;
    if(/^[-]?(\d)+$/.test(str))
        return true;
    else
        return false;
}//~~~
/**
*校验整型最小值
*str：要校验的串。  val：比较的值
*
*返回值：
*如果为空，定义校验通过，                返回true
*如果满足条件，大于等于给定值，校验通过，返回true
*如果小于给定值，                        返回false              参考提示信息：输入域不能小于给定值！
*/
function checkIntegerMinValue(str,val)
{
    //如果为空，则通过校验
    if(str == "")
        return true;
    if(typeof(val) != "string")
        val = val + "";
    if(checkIsInteger(str) == true)
    {
        if(parseInt(str,10)>=parseInt(val,10))
            return true;
        else
            return false;
    }
    else
        return false;
}//~~~
/**
*校验整型最大值
*str：要校验的串。  val：比较的值
*
*返回值：
*如果为空，定义校验通过，                返回true
*如果满足条件，小于等于给定值，校验通过，返回true
*如果大于给定值，                        返回false       参考提示信息：输入值不能大于给定值！
*/
function checkIntegerMaxValue(str,val)
{
    //如果为空，则通过校验
    if(str == "")
        return true;
    if(typeof(val) != "string")
        val = val + "";
    if(checkIsInteger(str) == true)
    {
        if(parseInt(str,10)<=parseInt(val,10))
            return true;
        else
            return false;
    }
    else
        return false;
}//~~~
/**
*校验整型是否为非负数
*str：要校验的串。
*
*返回值：
*如果为空，定义校验通过，返回true
*如果非负数，            返回true
*如果是负数，            返回false               参考提示信息：输入值不能是负数！
*/
function isNotNegativeInteger(str)
{
    //如果为空，则通过校验
    if(str == "")
        return true;
    if(checkIsInteger(str) == true)
    {
        if(parseInt(str,10) < 0)
            return false;
        else
            return true;
    }
    else
        return false;
}//~~~
/*--------------------------------- Integer --------------------------------------*/
/********************************** Double ****************************************/
/**
*校验字符串是否为浮点型
*返回值：
*如果为空，定义校验通过，      返回true
*如果字串为浮点型，校验通过，  返回true
*如果校验不通过，              返回false     参考提示信息：输入域不是合法的浮点数！
*/
function checkIsDouble(str)
{
    //如果为空，则通过校验
    if(str == "")
        return true;
    //如果是整数，则校验整数的有效性
   
    if(str.indexOf(".") == -1)
    {
        if(checkIsInteger(str) == true)
            return true;
        else
            return false;
    }
    else
    {
        if(/^[-]?\d+[.]{1}\d+$/.test(str))
            return true;
        else
            return false;
    }
}//~~~
/**
*校验浮点型最小值
*str：要校验的串。  val：比较的值
*
*返回值：
*如果为空，定义校验通过，                返回true
*如果满足条件，大于等于给定值，校验通过，返回true
*如果小于给定值，                        返回false              参考提示信息：输入域不能小于给定值！
*/
function checkDoubleMinValue(str,val)
{
    //如果为空，则通过校验
    if(str == "")
        return true;
    if(typeof(val) != "string")
        val = val + "";
    if(checkIsDouble(str) == true)
    {
        if(parseFloat(str)>=parseFloat(val))
            return true;
        else
            return false;
    }
    else
        return false;
}//~~~
/**
*校验浮点型最大值
*str：要校验的串。  val：比较的值
*
*返回值：
*如果为空，定义校验通过，                返回true
*如果满足条件，小于等于给定值，校验通过，返回true
*如果大于给定值，                        返回false       参考提示信息：输入值不能大于给定值！
*/
function checkDoubleMaxValue(str,val)
{
    //如果为空，则通过校验
    if(str == "")
        return true;
    if(typeof(val) != "string")
        val = val + "";
    if(checkIsDouble(str) == true)
    {
        if(parseFloat(str)<=parseFloat(val))
            return true;
        else
            return false;
    }
    else
        return false;
}//~~~
/**
*校验浮点型是否为非负数
*str：要校验的串。
*
*返回值：
*如果为空，定义校验通过，返回true
*如果非负数，            返回true
*如果是负数，            返回false               参考提示信息：输入值不能是负数！
*/
function isNotNegativeDouble(str)
{
    //如果为空，则通过校验
    if(str == "")
        return true;
    if(checkIsDouble(str) == true)
    {
        if(parseFloat(str) < 0)
            return false;
        else
            return true;
    }
    else
        return false;
}//~~~
/*--------------------------------- Double ---------------------------------------*/
/********************************** date ******************************************/
/**
*校验字符串是否为日期型
*返回值：
*如果为空，定义校验通过，           返回true
*如果字串为日期型，校验通过，       返回true
*如果日期不合法，                   返回false    参考提示信息：输入域的时间不合法！（yyyy-MM-dd）
*/
function checkIsValidDate(str)
{
    //如果为空，则通过校验
    if(str == "")
        return true;
    var pattern = /^((\\d{4})|(\\d{2}))-((\\d{1})|(\\d{2}))-((\\d{1})(\\d{2}))$/g;
    if(!pattern.test(str))
        return false;
    var arrDate = str.split("-");
    if(parseInt(arrDate[0],10) < 100)
        arrDate[0] = 2000 + parseInt(arrDate[0],10) + "";
    var date =  new Date(arrDate[0],(parseInt(arrDate[1],10) -1)+"",arrDate[2]);
    if(date.getYear() == arrDate[0]
       && date.getMonth() == (parseInt(arrDate[1],10) -1)+""
       && date.getDate() == arrDate[2])
        return true;
    else
        return false;
}//~~~
/**
*校验两个日期的先后
*返回值：
*如果其中有一个日期为空，校验通过,          返回true
*如果起始日期早于等于终止日期，校验通过，   返回true
*如果起始日期晚于终止日期，                 返回false    参考提示信息： 起始日期不能晚于结束日期。
*/
function checkDateEarlier(strStart,strEnd)
{
    if(checkIsValidDate(strStart) == false || checkIsValidDate(strEnd) == false)
        return false;
    //如果有一个输入为空，则通过检验
   
    if (( strStart == "" ) || ( strEnd == "" ))
        return true;
    var arr1 = strStart.split("-");
    var arr2 = strEnd.split("-");
    var date1 = new Date(arr1[0],parseInt(arr1[1].replace(/^0/,""),10) - 1,arr1[2]);
    var date2 = new Date(arr2[0],parseInt(arr2[1].replace(/^0/,""),10) - 1,arr2[2]);
    if(arr1[1].length == 1)
        arr1[1] = "0" + arr1[1];
    if(arr1[2].length == 1)
        arr1[2] = "0" + arr1[2];
    if(arr2[1].length == 1)
        arr2[1] = "0" + arr2[1];
    if(arr2[2].length == 1)
        arr2[2]="0" + arr2[2];
    var d1 = arr1[0] + arr1[1] + arr1[2];
    var d2 = arr2[0] + arr2[1] + arr2[2];
    if(parseInt(d1,10) > parseInt(d2,10))
       return false;
    else
       return true;
}//~~~

/**
*校验字符串是否为正确的日期型(用于八位日期的)
*返回值：如果是正确的返回true ，否则返回false
*/
function checkIsValidDateWithEight(str){
	var exp="^((((1[6-9]|[2-9][0-9])[0-9]{2})(0[13578]|1[02])(0[1-9]|[12][0-9]|3[01]))|(((1[6-9]|[2-9][0-9])[0-9]{2})(0[13456789]|1[012])(0[1-9]|[12][0-9]|30))|(((1[6-9]|[2-9][0-9])[0-9]{2})02(0[1-9]|1[0-9]|2[0-8]))|(((1[6-9]|[2-9][0-9])(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))0229))$";
	return checkMask(str,exp);
}//~~~



/**
*将八位日期转换为10位日期，以指定的字符分隔（默认为“-”）
*返回值：分隔后的字符串
*/
function convertDateStr(datestr,splitstr){
	if(datestr=="")
		return "";
	var tmp_1=datestr.substring(0,4);
	var tmp_2=datestr.substring(4,6);
	var tmp_3=datestr.substring(6,8);
	if(typeof splitstr =="undefined" || splitstr=="")
		splitstr = "-";
	return tmp_1+splitstr+tmp_2+splitstr+tmp_3;
}



/*--------------------------------- date -----------------------------------------*/

/********************************** chinese ***************************************/
/**
*校验字符串是否为中文
*返回值：
*如果为空，定义校验通过，           返回true
*如果字串为中文，校验通过，         返回true
*如果字串为非中文，             返回false    参考提示信息：必须为中文！
*/
function checkIsChinese(str)
{
    //如果值为空，通过校验
    if (str == "")
        return true;
    var pattern = /^([\\u4E00-\\u9FA5]|[\\uFE30-\\uFFA0])*$/gi;
    if (pattern.test(str))
        return true;
    else
        return false;
}//~~~
/**
* 计算字符串的长度，一个汉字两个字符
*/
String.prototype.realLength = function()
{
  return this.replace(/[^\\x00-\\xff]/g,"**").length;
}
/*--------------------------------- chinese --------------------------------------*/
/********************************** mask ***************************************/
/**
*校验字符串是否符合自定义正则表达式
*str 要校验的字串  pat 自定义的正则表达式
*返回值：
*如果为空，定义校验通过，           返回true
*如果字串符合，校验通过，           返回true
*如果字串不符合，                   返回false    参考提示信息：必须满足***模式
*/
function checkMask(str,pat)
{
    //如果值为空，通过校验
    if (str == "")
        return true;
    var pattern = new RegExp(pat,"gi")
    if (pattern.test(str))
        return true;
    else
        return false;
}//~~~
/*--------------------------------- mask --------------------------------------*/
/********************************** file ***************************************/
/**
* added by LxcJie 2004.6.25
* 得到文件的后缀名
* oFile为file控件对象
*/
function getFilePostfix(oFile)
{
    if(oFile == null)
        return null;
    var pattern = /(.*)\\.(.*)$/gi;
    if(typeof(oFile) == "object")
    {
        if(oFile.value == null || oFile.value == "")
            return null;
        var arr = pattern.exec(oFile.value);
        return RegExp.$2;
    }
    else if(typeof(oFile) == "string")
    {
        var arr = pattern.exec(oFile);
        return RegExp.$2;
    }
    else
        return null;
}

/********************************** Tab***************************************/
/**
* added by JinZhaoYu 2006.7.1
* 将键入的回车转换为Tab键
*
*/
function ConvertEnterToTab()
{
	if(event.keyCode==13){
	    event.keyCode=9;//ie下tab的值为9，ns下为7
	}
}
/********************************** Enter***************************************/
/**
* added by JinZhaoYu 2006.7.1
* 将键入的回车转换提交
* 参数：functionName  将要调用的验证函数
*		formName	表单名称
*/
function EnterToSubmit(functionName,formName)
{
    if(event.keyCode==13){
	   returnValue=eval(functionName+"();");
	   if(returnValue==true){
	   	eval(formName+".submit();");
	   }
	}
}

/********************************** Enter***************************************/
/**
* added by JinZhaoYu 2006.7.1
* 将键入的回车转换提交，无须客户端验证的提交
* 参数：	formName	表单名称
*/
function EnterToSubmitTwo(formName)
{
    if(event.keyCode==13){
	   	eval(formName+".submit();");
	}
}

/********************************** radio_Check***************************************/
/**
* added by JinZhaoYu 2006.9.28
* 验证是否有指定名称的单选框被选中
* 参数：	radio_name	单选框名称
*/

function radio_Check(radio_name){
              var radio_tiku=document.getElementsByName(radio_name);
              for(var i=0;i<radio_tiku.length;i++){
                     if (radio_tiku[i].checked){
                            return true;
                     }
              }
              return false;
}

/********************************** 移动电话号码***************************************/
/* 
用途：检查输入手机号码是否正确 
要求：
一、移动电话号码为11或12位，如果为12位,那么第一位为0 
二、11位移动电话号码的第一位和第二位为"13"或者前三位为159 
三、12位移动电话号码的第二位和第三位为"13"或者前三位为159 

输入:包含要检验的值的对象 
返回： 
	如果通过验证返回true,否则返回false 
*/ 
function check_mobile(obj){      
    var regu =/(^[1][3][0-9]{9}$)|(^0[1][3][0-9]{9}$)|(^[1][5][9][0-9]{8}$)|(^0[1][5][9][0-9]{8}$)/;   
    var re = new RegExp(regu);   
    if (re.test( obj.value )) {   
      return true;   
    }   
    f_alert(obj,"请输入正确的手机号码");   
    return false;      
}

/********************************** 电话号码***************************************/
/* 
用途：检查输入的电话号码格式是否正确 
要求：
一、电话号码由数字、"("、")"和"-"构成 
二、电话号码为3到8位 
三、如果电话号码中包含有区号，那么区号为三位或四位 
四、区号用"("、")"或"-"和其他部分隔开 
五、也可以包含分机号，如：0557-8888888-1111、(0557)8888888-12345

输入： 包含要检验的值的对象  
返回： 
	如果通过验证返回true,否则返回false 
*/ 
function check_phone(obj)    
{   
    var regu =/(^([0][1-9]{2,3})[-]?\d{3,8}(-\d{1,6})?$)|(^\([0][1-9]{2,3}\)\d{3,8}(\(\d{1,6}\))?$)|(^\([0][1-9]{2,3}\)\d{3,8}([-]\d{1,6})?$)|(^\d{3,8}([-]\d{1,6})?$)/;    
    var re = new RegExp(regu);   
    if (re.test( obj.value )) {   
      return true;   
    }   
    f_alert(obj,"请输入正确的电话号码");   
    return false;   
}   

/********************************** 警告对话框***************************************/
/* 弹出警告对话框，用户点确定后将光标置于出错文本框上， 并且将原来输入内容选中。*/ 
function f_alert(obj,alertInfo)   
{   
    alert(alertInfo + "！");    
    obj.select();
    if(isVisible(obj) && checkPrVis(obj))   
  		obj.focus();   
} 

/**********************************判断当前对象是否可见***************************************/ 
function isVisible(obj){   
    var visAtt,disAtt;   
    try{   
        disAtt=obj.style.display;   
        visAtt=obj.style.visibility;   
    }catch(e){}   
    if(disAtt=="none" || visAtt=="hidden")   
        return false;   
    return true;   
}   


/**********************************判断当前对象及其父对象是否可见**********************************/ 
function checkPrVis(obj){   
    var pr=obj.parentNode;   
    do{   
        if(pr == undefined || pr == "undefined") return true;   
        else{   
            if(!isVisible(pr)) return false;   
        }   
    }while(pr=pr.parentNode);   
    return true;   
}   

/**********************************验证身份证号码是否有效**********************************/ 
/* 
功能：验证身份证号码是否有效 
提示信息：未输入或输入身份证号不正确！ 
使用：check_IDno(obj) 
返回：bool 
*/ 
function check_IDno(obj)   
{    
    var aCity={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"};   
    
    var iSum = 0;   
    var info = "";   
    var strIDno = obj.value;   
    var idCardLength = strIDno.length;     
    if(!/^\d{17}(\d|x)$/i.test(strIDno)&&!/^\d{15}$/i.test(strIDno))    
    {   
        f_alert(obj,"非法身份证号");   
        return false;   
    }   
    
    //在后面的运算中x相当于数字10,所以转换成a   
    strIDno = strIDno.replace(/x$/i,"a");   
  
    if(aCity[parseInt(strIDno.substr(0,2))]==null)   
    {   
        f_alert(obj,"非法地区");   
        return false;   
    }   
       
    if (idCardLength==18)   
    {   
        sBirthday=strIDno.substr(6,4)+"-"+Number(strIDno.substr(10,2))+"-"+Number(strIDno.substr(12,2));   
        var d = new Date(sBirthday.replace(/-/g,"/"))   
        if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate()))   
        {          
            f_alert(obj,"非法生日");   
            return false;   
        }   
  
        for(var i = 17;i>=0;i --)   
            iSum += (Math.pow(2,i) % 11) * parseInt(strIDno.charAt(17 - i),11);   
  
        if(iSum%11!=1)   
        {   
            f_alert(obj,"非法身份证号");   
            return false;   
        }   
    }   
    else if (idCardLength==15)   
    {   
        sBirthday = "19" + strIDno.substr(6,2) + "-" + Number(strIDno.substr(8,2)) + "-" + Number(strIDno.substr(10,2));   
        var d = new Date(sBirthday.replace(/-/g,"/"))   
        var dd = d.getFullYear().toString() + "-" + (d.getMonth()+1) + "-" + d.getDate();      
        if(sBirthday != dd)   
        {   
            f_alert(obj,"非法生日");   
            return false;   
        }   
    }   
    return true;    
}   

/**********************************用户ID**********************************/ 
/* 
用户ID，可以为数字、字母、下划线的组合， 
第一个字符不能为数字,且总长度不能超过20。 
*/ 
function check_userID(obj)   
{   
    var userID = obj.value;   
    if(userID.length > 20)   
    {   
        f_alert(obj,"ID长度不能大于20");   
        return false;   
    }   
  
    if(!isNaN(userID.charAt(0)))   
    {   
        f_alert(obj,"ID第一个字符不能为数字");   
        return false;   
    }   
    if(!/^\w{1,20}$/.test(userID))    
    {   
        f_alert(obj,"ID只能由数字、字母、下划线组合而成");   
        return false;   
    }   
    return true;   
}   


/**********************************判断是否为邮政编码**********************************/ 
function check_zipcode(obj)   
{   
    if(!check_number(obj))   
        return false;   
    if(obj.value.length!=6)   
    {   
        f_alert(obj,"邮政编码长度必须是6位");   
        return false;   
    }   
    return true;   
} 




/**********************************E-Mail格式**********************************/ 
/* 
用途：检查输入对象的值是否符合E-Mail格式 
输入：str 输入的字符串 
返回：如果通过验证返回true,否则返回false 
*/ 
function check_email(obj){     
    var myReg = /^([-_A-Za-z0-9\.]+)@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;    
    if(myReg.test( obj.value )) return true;    
    f_alert(obj,"请输入合法的电子邮件地址");   
    return false;    
} 

/**********************************网址格式**********************************/ 
/* 
用途：检查输入对象的值是否符合网址格式 
输入：str 输入的字符串 
返回：如果通过验证返回true,否则返回false 
*/ 
function check_URL(obj){
    var myReg = /^((http:[\/][\/])?\w+([.]\w+|[\/]\w*)*)?$/;    
    if(myReg.test( obj.value )) return true;    
    f_alert(obj,"请输入合法的网页地址");   
    return false;    
}   


/**********************************判断是否为数字**********************************/ 
/* 
* 判断是否为数字，是则返回true,否则返回false 
*/ 
function check_number(obj)   
{          
    if (/^(-?)\d+(\.?\d*)$/.test(obj.value))  
    {   
       return true;   
    }    
    else    
    {   
       f_alert(obj,"请输入数字");   
       return false;   
    }   
}


/**********************************得到值中的数字**********************************/ 
/* 
* 首先判断是否为数字，是则转换为浮点型并返回,否则返回0 
*/ 

function parseNumber(theval)   
	{          
		var tmpval=parseFloat(theval);
		if(isNaN(tmpval))
			tmpval=0;
		return tmpval;    
	}

/**********************************将数字转换成三位逗号分隔的样式 **********************************/ 
/* 
* num 要格式化的数字
* digit 小数位
*/ 
function   formatNum(num,   digit)   // 
  {  
      if(!/^(\+|-)?(\d+)(\.\d+)?$/.test(num)){
      	return   num;
      }  
      var   a   =   RegExp.$1,   b   =   RegExp.$2,   c   =   RegExp.$3;  
      var   re   =  new   RegExp().compile("(\\d)(\\d{3})(,|$)");  
      while(re.test(b))
      	b   =   b.replace(re,   "$1,$2$3");  
      if (c   &&   digit   &&   new   RegExp("^.(\\d{"+   digit   +"})(\\d)").test(c)){  
      	if   (RegExp.$2>4)
      		c   =   (parseFloat(RegExp.$1)+1)/Math.pow(10,   digit);  
      	else
      		c   =   "."+   RegExp.$1;
      }  
      return   a   +""+   b   +""+   (c+"").substr((c+"").indexOf("."));  
  }  

/**********************************端口号格式**********************************/ 
/* 
用途：检查输入对象的值是否符合端口号格式 
输入：str 输入的字符串 
返回：如果通过验证返回true,否则返回false 
*/ 
function check_port(obj)   
{   
    if(!check_number(obj))   
        return false;   
    if(obj.value < 65536)   
        return true;   
    f_alert(obj,"请输入合法的计算机IP地址端口号");   
    return false;    
}   

/**********************************ip地址的格式**********************************/ 
/* 
用途：校验ip地址的格式 
输入：strIP：ip地址 
返回：如果通过验证返回true,否则返回false； 
*/ 
function check_IP(obj)    
{    
    var re=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/; //匹配IP地址的正则表达式   
    if(re.test( obj.value ))   
    {   
        if( RegExp.$1 <256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256) return true;   
    }   
    f_alert(obj,"请输入合法的计算机IP地址");   
    return false;    
}   
  
/****************************检查输入字符串是否只由汉字、字母、数字组成**************************/ 
/* 
用途：检查输入字符串是否只由汉字、字母、数字组成 
输入： 
value：字符串 
返回： 
如果通过验证返回true,否则返回false 
*/ 
function check_ZhOrNumOrLett(obj){    //判断是否是汉字、字母、数字组成   
    var regu = "^[0-9a-zA-Z\u4e00-\u9fa5]+$";      
    var re = new RegExp(regu);   
    if (re.test( obj.value )) {   
      return true;   
    }   
    f_alert(obj,"请输入汉字、字母或数字");   
    return false;   
}   

/**********************************是否为英文字母**********************************/ 
/* 
* 判断是否为英文字母，是则返回true,否则返回false 
*/ 
function check_letter(obj)   
{          
    if (/^[A-Za-z]+$/.test( obj.value ))    
    {   
       return true;   
    }    
    f_alert(obj,"请输入英文字母");   
    return false;   
} 


/**********************************是否为大写英文字母**********************************/ 
/* 
* 判断是否为大写英文字母，是则返回true,否则返回false 
*/ 
function check_uppercase(obj)   
{          
    if (/^[A-Z]+$/.test( obj.value ))    
    {   
       return true;   
    }    
    f_alert(obj,"请输入大写英文字母");   
    return false;   
}   
 
/**********************************是否为小写英文字母**********************************/ 
/* 
* 判断是否为小写英文字母，是则返回true,否则返回false 
*/ 

function check_lowercase(obj)   
{          
    if (/^[a-z]+$/.test( obj.value ))    
    {   
       return true;   
    }    
    f_alert(obj,"请输入小写英文字母");   
    return false;   
}

//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&  复选框操作    &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

/**********************************点击一个“全选”复选框，选中所有的**********************************/ 
/* 
*PelName 为那个“全选”的名字
* elName 为那些复选框的统一的名字
*/ 
function selectAll(PelName,elName){
	var checkbox=document.getElementsByName(elName);
	if(document.all(PelName).checked==false){
		for(var i=0;i<checkbox.length;i++){
			checkbox[i].checked=false;
		}
	}else{
		for(var i=0;i<checkbox.length;i++){
			checkbox[i].checked=true;
		}
	}
}

/**********************************点击一个复选框，将“全选”取消选中**********************************/ 
/* 
*PelName 为那个“全选”的名字
*/
function selectOne(PelName){
	if(document.all(PelName).checked==true){
		document.all(PelName).checked=false;
	}	
}

/**********************************点击一个复选框，获取或清空该框对应的值**********************************/ 
/* 
*obj 		该复选框对象
*tmp_obj   	临时存储值得隐藏域名称
*/
function GetId(obj,tmp_obj){
	thislist=eval("document.getElementById(tmp_obj)");	
 	if(obj.checked){ 
   		thislist.value+=(thislist.value.trim()=="")?(obj.value.trim()):(","+obj.value.trim());
  	}else{
		thislist.value=thislist.value.replace(","+obj.value.trim(),"");
		thislist.value=thislist.value.replace(obj.value.trim()+",","");
		thislist.value=thislist.value.replace(obj.value.trim(),"");
	}
}

/**********************************点击一个“全选”复选框，获取所有的值，或者清空**********************************/ 
/* 
*obj 		该“全选”复选框对象
*tmp_obj   	临时存储值得隐藏域名称
*elName    	要获取其所有的值得复选框名称
*/
function GetAllId(obj,tmp_obj,elName){
	thislist=eval("document.getElementById(tmp_obj)");		
 	if(obj.checked){ 
		var checkbox=document.getElementsByName(elName);
		thislist.value="";
		for(var i=0;i<checkbox.length;i++){
			thislist.value+=(thislist.value.trim()=="")?(checkbox[i].value.trim()):(","+checkbox[i].value.trim());
		}
  	}else{
		thislist.value="";
	}
	//alert(thislist.value);
}


/**********************************获取目前所有已经选中的的复选框对象**********************************/ 
/* 
*obj_name 	复选框对象的名称
*tmp_obj   	临时存储值得隐藏域名称
*/
function GetAllCheckedId(obj_name,tmp_obj){
	var checkbox=document.getElementsByName(obj_name);
	thislist=eval("document.getElementById(tmp_obj)");
	thislist.value="";
	for(var i=0;i<checkbox.length;i++){
	 	if(checkbox[i].checked){ 
	   		thislist.value+=(thislist.value.trim()=="")?(checkbox[i].value.trim()):(","+checkbox[i].value.trim());
	  	}
	}
}


//&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&  复选框操作 End   &&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&


/**********************************将代表天数的数字转换成 N日，N个月，N年的形式**********************************/ 
/* 
*num 		该数字
*返回转换后的字符串，或者空串
*/
function convertNumToStrDate(num){
	var numb=num.trim();
	var returnVal="";
	if(numb!=""&&!isNaN(numb)&&num!="99999999"){
		var year = numb / 360 ;
		var month = numb / 30 ;
		
		if(year>=1){
			returnVal=parseInt(year)+"年";
		}else if(month>=1){
			returnVal=parseInt(month)+"个月";
		}else{
			returnVal=numb+"日";
		}
	}
	return returnVal;		
}


/**********************************将下拉列表框的默认值选中为指定的值**********************************/ 
/* 
*ele select对象名称
*eleValue 默认的值
*
*/
function setDefaultSelected(ele,eleValue){
	var obj=document.getElementById(ele);
	
	for(var i=0;i<obj.length;i++){
		if(obj[i].value==eleValue)
			obj.options[i].selected=true;
	}
}

/* 
*根据下拉列表框的文本
*ele select对象名称
*eleValue 默认的值
*/
function setDefaultSelectedForText(ele,eleValue){
		var obj=document.getElementById(ele);
		
		for(var i=0;i<obj.length;i++){
			if(obj.children[i].innerText==eleValue)
				obj.options[i].selected=true;
	}
}

/**********************************判断对象是否为数组**********************************/ 
/* 
*参数：对象
*
*返回：如果是数组，返回true
*/

function isArray(data)
{
    return (data && data.join) ? true : false;
};

/**********************************禁止空字符串,以及去除多余的空格**********************************/ 
/* 
*参数：要处理的值
*
*返回：处理后的值
*/
function getNotNullStr(value){
	var tmp_v="";
	if(typeof value!="undefined"){
		try{
			tmp_v=value.trim();
		}catch(ex){}
	}		
	return tmp_v;
}
	
/**********************************获取列表框中选择项的文本*********************************/ 
/* 
*参数：控件的名称
*
*返回：值
*/	
function getSelectedText(objName){
	return document.getElementById(objName).children[document.getElementById(objName).selectedIndex].innerText;
}

/**********************************复原表,参数为表的名称*********************************/ 
/* 
*参数：表的名称
*删除表的所有行，除了第一行之外
*
*/
function clearTable(tabName){
	var theTab=document.getElementById(tabName);
	while(true){
		if(theTab.rows.length==1)
			break;
		theTab.deleteRow(theTab.rows.length-1);
	}
}




/**********************************对控件的onKeypress事件的处理，只能输入数字*********************************/ 
/* 
*参数：event 事件对象
*
*/
function validateTextInput(evt)
{
   var value=evt.srcElement.value;
   if (evt.keyCode==46){
       if(value.match(/\.\d*/g,'.')||(value==""))
           evt.returnValue=false;
   }
 
   if(evt.keyCode==45){
   		if(value.indexOf("-") != -1)
       		evt.returnValue=false;
   }

   if(((evt.keyCode>=48)&&(evt.keyCode<=57))||(evt.keyCode==45)||(evt.keyCode==46)||(evt.keyCode==8)||(evt.keyCode==37)||(evt.keyCode==39)){
		// 0             9                .            backspace           left         right
   }else{
       event.returnValue=false;
   }

}


/**********************************判断表单中的控件是否为文本框，是的话则设置为只读*********************************/ 
/* 
*
*
*/
function setTextReadOnly(){
	var eles = document.forms[0].elements;
	for ( var i = 0; i < eles.length; i++ ){
        if ( eles[i].type == "text" )
            eles[i].readOnly = true;
    }
}




/*--------------------------------- file --------------------------------------*/




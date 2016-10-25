/*
 *  JS
 */

  /*判断是否是闰年*/
		var RunNian = function(year){
 			return ((year%400==0) || ((year%4==0) && (year%100!=0)))?true:false;
		}
		
		/*得到最大天数*/
		var max_date = function(year,month){
			switch (parseInt(month)){
     			 case 1: 
     			 case 3: 
     			 case 5: 
     			 case 7: 
     			 case 8: 
     			 case 10: 
     			 case 12: return "31";
     			 case 4: 
     			 case 6: 
     			 case 9: 
     			 case 11: return "30";
     			 case 2: if(RunNian(parseInt(year))){
     			 			 return "29";
     			 		  }else{
     			 		  	 return "28";
     			 		  }
     			default : return "31";
			}
		}
		
		/**
		* 得到旬日期
		**/
		function getXunDate(year,month,xun){
			
			 switch(parseInt(xun)){
			 	case 1 : return year+"-"+month+"-10";  //上旬
			 	case 2 : return year+"-"+month+"-20";  //中旬
			 	case 3 : return year+"-"+month+"-"+max_date(year,month);//下旬
			 	default : return year+"-"+month+"30";  //其它
			 } 
		}
		/**
		 * 得到季报
		 */
		var getJiBao = function(sess){
			switch (parseInt(sess)){
				 case 1:  return "-03-31";  //第一季
				 case 2:  return "-06-30";  //第二季
				 case 3:  return "-09-30";  //第三季
				 case 4:  return "-12-31";  //第四季
				 default:
				  		  return "-03-31";
			}
		}
		
		/**
 * 去除空格
 */
function replaceBlank(str) {
	return str.replace(/\s/g, "");
}

function validate(){
	if(replaceBlank(document.getElementById("rptOrg").value) == ""){
			alert("请输入要查询的机构号");
			return false;
	}
	if(replaceBlank(document.getElementById("date_1").value) == ""){
			alert("请输入要查询的日期");
			return false;
	}
	return true;
}



		function hiddenDiv(value){
			var head = "SenFe_";
			for (var i = 0; i <=5 ;i++){
				head = head+i;
				var obj = document.getElementById(head);
				if(i == value){
					obj.style.display="block"; 
				}else{
					obj.style.display="none";
				}
				head = "SenFe_";
			}
		}
		
		function form_comit(){
		  if(validate()){
		  	showWaitPanel("数据查询");
			var type = document.getElementById("perId").value;
			var dt = "",year="",month="",temp="",month_tmp="";   //dt 总日期
			switch(parseInt(type)){
				case 0: dt = document.getElementById("date_1").value; break; //日报
				case 1:
						year = document.getElementById("year_x").value;
						month = document.getElementById("month_x").value;
						month_tmp = month.length < 2?"0"+month:month;
						temp = document.getElementById("xun").value;
						dt = getXunDate(year,month_tmp,temp); break;  //旬报
				case 2:
						year = document.getElementById("year_y").value;
						month = document.getElementById("month_y").value;
						month_tmp = month.length < 2?"0"+month:month;
						dt = year+"-"+month_tmp+"-"+max_date(year,month); break;  //月报
				case 3: 
						year = document.getElementById("year_j").value;  //季报
						temp = document.getElementById("sess").value;
						dt = year + getJiBao(temp); break; 
				case 4: 
						year = document.getElementById("year_hy").value; //半年报
						temp = document.getElementById("halfY").value;
						if("1"==temp){
							dt = year + "-06-30";
						}else{
							dt = year + "-12-31";
						}
						break; 
				case 5: 
						year = document.getElementById("year_yy").value; //年报
						dt = year+"-12-31";break; 
				default: 
						dt="";
			}
			if(dt == ""){
				alert("日期选择上出错");
				return false;
			}
			document.getElementById("rptDate").value = dt;
			rptForm.submit();
			}
		}
		
		
		
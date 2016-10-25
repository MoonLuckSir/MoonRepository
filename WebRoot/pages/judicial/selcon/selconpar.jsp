<%@ page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<html>

	<script type="text/javascript">
	
		
		function selcon(selid,retruncol,conditioncol,checkflag)
		{
		  var  arr=new Array();
		  var  arr=conditioncol.split(",");
		   var con="";
		    for(i=0;i<arr.length;i++){
		      var temp=eval("document.all."+arr[i]).value;
		      if(temp==null||temp=='')
		      temp="nodate";
		      //这个判断是为江苏银行定制的，其它项目应该要去掉；
		      if(checkflag=='1')
		      {
		      if(i==0 && temp=="nodate")
		      {
		        alert('要先输入值，才能选！');
		        eval("document.all."+arr[0]).focus();
		        return false;
		       }
		      }
		      con=con+temp;
		     if(arr.length!=i+1)
		     con=con+",";
		    }
		  window.open("${ctx}/judicial/selcon.shtml?action=toList&selid="+selid+"&convalue="+con+"&retruncol="+retruncol,
			"通用选取","scrollbars=yes,resizable=yes,width=745,height=500,left=263,top=207");
		 }
		function selconbyidcon(selid,conditioncol,checkflag)
		{
		  var  arr=new Array();
		  var  arr=conditioncol.split(",");
		   var con="";
		    for(i=0;i<arr.length;i++){
		      var temp=eval("document.all."+arr[i]).value;
		      if(temp==null||temp=='')
		      temp="nodate";
		      //这个判断是为江苏银行定制的，其它项目应该要去掉；
		      if(checkflag=='1')
		      {
		      if(i==0 && temp=="nodate")
		      {
		        alert('要先输入值，才能选！');
		        eval("document.all."+arr[0]).focus();
		        return false;
		       }
		      }
		      con=con+temp;
		     if(arr.length!=i+1)
		     con=con+",";
		    }
		  window.open("${ctx}/judicial/selcon.shtml?action=toList&selid="+selid+"&convalue="+con,
			"通用选取","scrollbars=yes,resizable=yes,width=745,height=500,left=263,top=207");
		 }
		
		function selconbyid(selid)
		 {
		   window.open("${ctx}/judicial/selcon.shtml?action=toList&selid="+selid+"&convalue=nodate",
			"通用选取","scrollbars=yes,resizable=yes,width=745,height=500,left=263,top=207");
		 }
		
		function selconbyidcol(selid,retruncol)
		 {
		   window.open("${ctx}/judicial/selcon.shtml?action=toList&selid="+selid+"&convalue=nodate"+"&retruncol="+retruncol,
			"通用选取","scrollbars=yes,resizable=yes,width=745,height=500,left=263,top=207");
		 }
		
		
		function tosetvaluepub(selid,retruncol,conditioncol){
		  
		   var  arr=new Array();
		   var  arr=conditioncol.split(",");
		   var con="";
		    for(i=0;i<arr.length;i++){
		      var temp=eval("document.all."+arr[i]).value;
		     
		      if(temp==null||temp=='')
		      temp="nodate";
		      //这个判断是为江苏银行定制的，其它项目应该要去掉；
		      if(i==0 && temp=="nodate")
		      {
		        return false;
		      }
		      con=con+temp;
		     if(arr.length!=i+1)
		     con=con+",";
		    }
		    
		  if(true){
			var sUrl="${ctx}/judicial/selcon.shtml?action=toSetvalue&selid="+selid+"&convalue="+con+"&retruncol="+retruncol;
		    var request = YAHOO.util.Connect.asyncRequest('POST', sUrl, {
	    			  //请求正常时调用方法
					  success:function(o){
					   var retrunval= o.responseText;
					   if(retrunval!=null&&retrunval!=''&&retrunval!='muchrows')
					   {
					   alert(retrunval);
					   var  arr=new Array();
		               var  arr=retruncol.split(",");
		               var  arrval=new Array();
		               var  arrval=retrunval.split(",");
		                for(i=0;i<arr.length;i++){
		                 var temp=arrval[i];
		                 if(temp!='nodata')
		                  eval("document.all."+arr[i]).value=temp;
		                }
		               }
		              },
	  				failure:function(o){
							if(o.responseXML != undefined){	
								
							}
					}
			});
		}
	}
	function tosetvalue(selid,retruncol,conditioncol,buttid,msg){
		  
		   var  arr=new Array();
		   var  arr=conditioncol.split(",");
		   var con="";
		    for(i=0;i<arr.length;i++){
		      var temp=eval("document.all."+arr[i]).value;
		     
		      if(temp==null||temp=='')
		      temp="nodate";
		      //这个判断是为江苏银行定制的，其它项目应该要去掉；
		      if(i==0 && temp=="nodate")
		      {
		        return false;
		      }
		      con=con+temp;
		     if(arr.length!=i+1)
		     con=con+",";
		    }
		    
		  if(true){
			var sUrl="${ctx}/judicial/selcon.shtml?action=toSetvalue&selid="+selid+"&convalue="+con+"&retruncol="+retruncol;
		    var request = YAHOO.util.Connect.asyncRequest('POST', sUrl, {
	    			  //请求正常时调用方法
					  success:function(o){
					   var retrunval= o.responseText;
					   if(retrunval==null||retrunval=='')
					   {
					    var  arr=new Array();
		                var  arr=retruncol.split(",");
		                for(i=0;i<arr.length;i++){
		                  eval("document.all."+arr[i]).value='输入的值不正确';
		                }
		               }
					   
					   if(retrunval!=null&&retrunval!=''&&retrunval!='muchrows')
					   {
					   var  arr=new Array();
		               var  arr=retruncol.split(",");
		               var  arrval=new Array();
		               var  arrval=retrunval.split(",");
		                for(i=0;i<arr.length;i++){
		                 var temp=arrval[i];
		                 if(temp!='nodata')
		                  eval("document.all."+arr[i]).value=temp;
		                }
		               }
		               if(retrunval!=null&&retrunval!=''&&retrunval=='muchrows')
		               {
		                    var  arr=new Array();
		                    var  arr=retruncol.split(",");
		                   var flag=0;
		                   for(i=0;i<arr.length;i++){
		                      var a=eval("document.all."+arr[i]).value;
		                      if(a==null||a=='')
		                      flag=1;
		                   }
		                   if(flag==1)
		                   {
		                    alert(msg);
		                    eval("document.all."+buttid).onclick();
		                   }
		                   
		                }
		               
					  },
	  				failure:function(o){
							if(o.responseXML != undefined){	
								
							}
					}
			});
		}
	}	
function docheck(sql,obj,name){
		  
		   if(sql==null||sql=='')
		    {
		        return false;
		    }
		   if(obj.value=='您输入的值不对')
		    {
		        return false;
		    } 
		  if(true){
			var sUrl="${ctx}/judicial/selcon.shtml?action=docheck&sql="+sql;
		    var request = YAHOO.util.Connect.asyncRequest('POST', sUrl, {
	    			  //请求正常时调用方法
					  success:function(o){
					   var retrunval= o.responseText;
					   if(retrunval!=null&&retrunval=='no')
		               {
		                  //alert('对不起，'+name+':您输入的值不正确！');
		                  obj.value='您输入的值不对';
		                  //form.flag.value='1';
		                }else
		                {
		                  //form.flag.value='0';
		                }
		               
					  },
	  				failure:function(o){
							if(o.responseXML != undefined){	
								
							}
					}
			});
		}
	}	
	
		
	</script>
	
</html>

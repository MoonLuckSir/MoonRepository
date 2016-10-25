// JavaScript Document


function NewDialog(id,URLString,dialogWidth,dialogHeight) { 
	if (id > 0){ 
		return showModelessDialog(URLString, window, "dialogWidth:" + dialogWidth + "px; dialogHeight:" + dialogHeight + "px; help:no; resizable:no; scroll:no; status:no; center:yes"); 
	}else{ 
		return showModalDialog(URLString, window, "dialogWidth:" + dialogWidth + "px; dialogHeight:" + dialogHeight + "px; help:no; resizable:no; scroll:no; status:no"); 
	} 
}


//全选功能  約束： 全选id name  "selectAll"   复选框id namne "checkbox"   其value一般都是database  primary key

function selectAll()
{
	var cbox=document.getElementsByName("checkbox");
	var titleCheck=document.getElementById("selectAll");
	var cboxLen=cbox.length;
	for(var i=0;i<cboxLen;i++)
	{
			cbox[i].checked=titleCheck.checked;
	}

}
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ include file="/commons/taglibs.jsp"%>
<html>
    <head>
        <title>安徽农金司法查询系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
        <link rel="shortcut icon" href="${ctx}/images/theme/nx.gif">
<script language="JavaScript">
var user = eval("(${userData})"); 
var treeData = eval("(${treeData})");
var rootMenu ='010000';
IndexPage=function(){};
IndexPage.prototype = {
    showLeft : function() {
        midFrame.cols="217,7,*";
        sepFrame.imgSep.src="${ctx}/styles/${theme}/images/left/sep_but1.gif";
    },
    hideLeft : function() {
        midFrame.cols="0,7,*";
        sepFrame.imgSep.src="${ctx}/styles/${theme}/images/left/sep_but2.gif";
    }
};
var indexPage=new IndexPage();
function indexUrl(){
    return '${ctx}/${indexUrl}';
}
function writeTopMenus() {
    if(treeData.size > 0){
        rootMenu=treeData.children[0].id;
    }
    var sb = [];
    for (var i = 0, j = treeData.size; i < j; i++) {
        var menu = treeData.children[i];
        
        sb[sb.length] = '<td width="84" align="center" background="../../images/left/top_but.gif">';
        sb[sb.length] = '<a href="#"';
        sb[sb.length] = ' title="' + menu.name + '" ';
        sb[sb.length] = ' onclick="parent.treeCtl.showMenu(\'' + menu.id + '\')"';
        sb[sb.length] = '>' + menu.name + '</a>';
        sb[sb.length] = '</td>';

    }
    return sb.join("");
};

TreeCtl = function(menuId) {
    this.menuId = menuId;
};
TreeCtl.prototype = {
    showMenu : function() {
    }
};
var treeCtl=new TreeCtl(rootMenu);

</script>
    </head>    
<frameset rows="86,*,10" cols="*" frameborder="no" border="0" framespacing="0">
     <frame src="${ctx}/pages/index/top.html" name="topFrame" noresize scrolling="no" marginwidth="0" marginheight="0" id="topFrame"/>
     <frameset rows="*" cols="217,7,*" framespacing="0" frameborder="no" border="0" id="midFrame">
        <frame src="${ctx}/pages/index/left.html" name="leftFrame" noresize scrolling="No" id="leftFrame"/>
        <frame src="${ctx}/pages/index/sepline.html" name="sepFrame" noresize scrolling="no" />
        <frame src="${ctx}/${indexUrl}" name="mainFrame" scrolling="No" noresize id="mainFrame"/>
     </frameset>
    <frame src="${ctx}/pages/index/foot.html" noresize scrolling="no" marginwidth="0" marginheight="0" id="topBottom"/>
</frameset>
<noframes><body></body></noframes></html>

function ReportPage() {
};
ReportPage.prototype.bigZoom = function() {
	try {
		report.zoomDiv('150%');
	} catch (e) {
	}
};
ReportPage.prototype.normalZoom = function() {
	try {
		report.zoomDiv('100%');
	} catch (e) {
	}
};
ReportPage.prototype.firstPage = function() {
	try {
		showWaitPanel();
		report.firstPage();
		setTimeout("reportPageInit();", 500);
	} catch (e) {
		closeWindow();
	}
};
ReportPage.prototype.lastPage = function() {
	try {
		showWaitPanel();
		report.lastPage();
		setTimeout("reportPageInit();", 500);
	} catch (e) {
		closeWindow();
	}
};
ReportPage.prototype.prevPage = function() {
	try {
		showWaitPanel();
		report.prevPage();
		setTimeout("reportPageInit();", 500);
	} catch (e) {
		closeWindow();
	}
};
ReportPage.prototype.nextPage = function() {
	try {
		showWaitPanel();
		report.nextPage();
		setTimeout("reportPageInit();", 500);
	} catch (e) {
		closeWindow();
	}
};
ReportPage.prototype.printPage = function() {
	try {
		report.printPage();
	} catch (e) {
	}
};
ReportPage.prototype.saveAsExcel = function() {
	try {
		report.report_saveAsExcel();
	} catch (e) {
	}
};
ReportPage.prototype.switchScreen = function(obj) {
	if(obj.fullScreen){
		obj.fullScreen=false;
		parent.indexPage.normalScreen();
		obj.innerHTML="<span class='maxWinBtn'></span>&nbsp;最大窗口</span>";
	}else{
		parent.indexPage.fullScreen();
		obj.fullScreen=true;
		obj.innerHTML="<span class='norWinBtn'></span>&nbsp;标准窗口</span>";
	}
}; 
ReportPage.prototype.drawToolbar = function(curPage, totalPage) {
	var sb = [];	
	g("curPage").innerHTML = curPage;
	g("totPage").innerHTML = totalPage;
	try {
		if (curPage > 1) {
			sb[sb.length] = '<span class="firstPage" onclick="rptPage.firstPage()"></span>';
			sb[sb.length] = '<span class="prevPage" onclick="rptPage.prevPage()"></span>';
		} else {
			sb[sb.length] = '<span class="firstDis2"></span><span class="prevDis2"></span>';
		}
		sb[sb.length] = '第';
		sb[sb.length] = curPage;
		sb[sb.length] = '页';
		if (curPage < totalPage) {
			sb[sb.length] = '<span class="nextPage" onclick="rptPage.nextPage()"></span>';
			sb[sb.length] = '<span class="lastPage" onclick="rptPage.lastPage()"></span>';
		} else {
			sb[sb.length] = '<span class="nextDis2"></span><span class="lastDis2"></span>';
		}
	} catch (e) {
	}
	return sb.join("");
};

ReportPage.prototype.initPage = function() {
	var html = "";
	try {
		var curPage = report.report_getCurrPage() * 1;
		var totalPage = report.report_getTotalPage() * 1;
		html = rptPage.drawToolbar(curPage, totalPage);
	} catch (e) {
		html = rptPage.drawToolbar(0, 0);
	}
	g("pageDiv").innerHTML = html;
	g("pageDiv2").innerHTML = html;
};
var rptPage = new ReportPage();
function reportPageInit() {
	rptPage.initPage();
}
function reportClean() {
	g("totPage").innerHTML = "0";
	g("curPage").innerHTML = "0";
}
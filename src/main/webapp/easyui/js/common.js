var webRootPath = "/uniworks";
var topMenuId;
var $element=$(window),lastWidth=$element.width(),lastHeight=$element.height();	


//브라우저 사이즈를 기준으로 resize
function setHeight() {
	var c = $('.easyui-layout');   		   				
	var width = $(window).width() - 50;
	var height = $(window).height() - 50;	
	
	c.layout('resize',{
		width: width,
		height: height
	});	
	/*
	if ($element.width()!=lastWidth||$element.height()!=lastHeight){
		$('#panel').panel('resize',{
			width: lastWidth,
			height: lastHeight
		});
		$('#datagrid').datagrid('resize', {
			width: lastWidth,
			height: lastHeight
		}); 
		lastWidth = $element.width();lastHeight=$element.height();	 
	}
	*/
}

/**
 * 좌측 sub menu 표시
 * @param coId
 * @param highMenuId
 * @param lang
 * @param title
 */
function getSidebarMenu(coId, highMenuId, lang, title) {
	topMenuId = highMenuId;
	var subMenu = "";
	var menuId = highMenuId.substring(0,6);
	var url = webRootPath + "/rest/subMenu/coId/" + coId + "/highMenuId/" + menuId + "/lang/" + lang;	
	
	$.getJSON(url, function (data, status){
		if (status == 'success') {
			setSubMenuHead(title);
			subMenu = getSubMenuLevel2(data);			
		} else {
			return;
		}
	});	
}

/**
 * 좌측 sub menu를 표기하기 위한 헤드 부분
 * @param title
 * @returns {String}
 */
function setSubMenuHead(title) {
	var p = $('#selTopMenu').layout();
	p.panel('setTitle', title);	
}

/**
 * 좌측 sub menu - 2레벨
 * @param data
 * @returns {String}
 */
function getSubMenuLevel2(data) {
	var subMenu = "";
	var strHighMenu = "";
	
	//3레벨의 상위 레벨 코드를 모두 기록.
	$.each(data, function(index, entry) {
		if (entry["menuLevel"] == "3") {
			strHighMenu += entry["highMenuId"] + ",";
		}
	});
	
	//accordion을 생성
	$('#leftSubMenu').accordion({
		multiple: true,
		border: false
	});			
	
	removeMenuAccordion();
	var noChildNo = 0;	
	$.each(data, function(index, entry) {
		if (entry["menuLevel"] == "2") {
			var contentMenu = getSubMenuLevel3(entry["menuId"], data);
			var param = entry["menuId"] + ',' + entry["menuDsplName"] + ',' + entry["bodyUrl"] + ',' + entry["highMenuId"] + ',' + entry["menuLevel"];
			//하위 메뉴가 없는 경우
			if (contentMenu == null || contentMenu.length < 1) {
				$('#leftSubMenu').accordion('add', {
					title: entry["menuDsplName"],
					selected: false,	
					content: '<div id="subPanel" class="easyui-panel" style="width:100%;border:false;padding:0px"><input id="' + 'noChildNo' + noChildNo + '" class="hidden" type="hidden" value="' + param + '"></div>'
				});				
			} else {
				$('#leftSubMenu').accordion('add', {
					title: entry["menuDsplName"],
					content: contentMenu,
					selected: false
				});
			}
			noChildNo++;
		} 
	});	
	
	return subMenu;
}

/**
 * 좌측 sub menu - 3레벨의 메뉴를 표기 
 * @param data
 * @returns {String}
 */
function getSubMenuLevel3(hightMenuId, data) {
	var subMenu = "";	
	var no = 0;
	$.each(data, function(index, entry) {
		if (entry["menuLevel"] == "3") {						
			if (hightMenuId == entry["highMenuId"]) {
				no++;
				subMenu += '<div id="p' + no + '" class="easyui-panel" style="width:100%;border:false;padding:1px"><a href="javascript:showContentPage(\'' + entry["menuId"] + '\', \'' + entry["menuDsplName"] + '\', \'' + entry["bodyUrl"] + '\', \'' + entry["highMenuId"] + '\',\'' + entry["menuLevel"] + '\',\'' +  entry["cntnId"] + '\');"' + 'class="easyui-linkbutton" plain="true" style="width:100%;">' + entry["menuDsplName"] + '</a></div> \r\n';
			}
		} 
	});	
	
	return subMenu;
}

/**
 * 이전에 Display했던 accordion 메뉴 제거.
 */
function removeMenuAccordion() {
	var thepanels = $('#leftSubMenu').accordion('panels');
	while (thepanels.length){
	  $('#leftSubMenu').accordion('remove', 0);
	}
}

/**
 * 컨텐츠 페이지로 이동
 * @param menuId
 * @param menuDsplName
 * @param bodyUrl
 */
function showContentPage(menuId, menuDsplName, bodyUrl, highMenuId, menuLevel, cntnId) {
	var param = "headMenuId=" + topMenuId + "&menuId=" + menuId + "&menuLevel=" + menuLevel + "&cntnId=" + cntnId +"&cntnName=" + menuDsplName;
	var mh = $(window).height() - 100;
	
	getMenuHierarchyInfo(topMenuId, menuId, menuLevel);
	var url = webRootPath + bodyUrl + "?" + param;
	
	$('#frmMain').css('height', mh + 'px');
	$('#frmMain').attr("src", webRootPath + bodyUrl + "?" + param);
	
	document.getElementById('frmMain').onload = resizeIframe;
	window.onresize = resizeIframe;
	
	$('#frmMain').attr('src', url);
}

/**
 * 메뉴 네비게이션 표기
 * @param startMenuId
 * @param endMenuId
 * @param menuLevel
 */
function getMenuHierarchyInfo(startMenuId, endMenuId, menuLevel) {
	var url = webRootPath + "/rest/startMenuId/" + startMenuId + "/endMenuId/" + endMenuId + "/menuLevel/" + menuLevel + ".json";
	var navMenu = '';
	
	$.getJSON(url, function (data, status){
		if (status == 'success') {			
			navMenu = getMenuHierarchyInfoFormat(data);
			var p = $('#content').layout();
	    	p.panel('setTitle', navMenu);
		} else {
			return;
		}		
	});
}

/**
 * 1레벨에서 최종 선택된 메뉴까지의 path를 표기
 * @param data
 * @returns {String}
 */
function getMenuHierarchyInfoFormat(data) {
	var navMenu = '';
	if (data.menuDsplName1 != undefined && data.menuDsplName1 != null && data.menuDsplName1 != "") {
		navMenu += data.menuDsplName1;
		if (data.menuDsplName2 != undefined && data.menuDsplName2 != null && data.menuDsplName2 != "") {
			navMenu += " > " + data.menuDsplName2;
			if (data.menuDsplName3 != undefined && data.menuDsplName3 != null && data.menuDsplName3 != "") {
				navMenu += " > " + data.menuDsplName3;
			}
		}
	}
	return navMenu;
}

function resizeIframe() {
    //var height = parent.document.documentElement.clientHeight;   
    //height -= parent.document.getElementById('frmMain').offsetTop;
    //height -= 20; /* whatever you set your body bottom margin/padding to be */
    //parent.document.getElementById('frmMain').style.height = height +"px";
	var fullHeight = parent.document.documentElement.clientHeight;
	var minusHeight = fullHeight * 0.20;
	//console.log("fullHeight :" + fullHeight);
	//console.log("minusHeight :" + minusHeight);	
	parent.document.getElementById('frmMain').style.height = (fullHeight - minusHeight) +"px";		
}

function calcHeight(){
 	//find the height of the internal page

 	var the_height= parent.document.getElementById('frmMain').contentWindow.document.body.scrollHeight;

 	//change the height of the iframe
 	parent.document.getElementById('frmMain').height = the_height;

 	//document.getElementById('the_iframe').scrolling = "no";
 	parent.document.getElementById('frmMain').style.overflow = "hidden";
}
/**
 * DataGrid에서 날짜 포맷에 맞게 변환하는 함수
 * @param val
 * @param row
 * @returns {String}
 */
function formatDate(val, row) {
	if (val == null || val == "") return "";
	var date = new Date(Number(val));
	
	/*
	var year = 1900 + Number(val.year);
	var month = Number(val.month) + 1;
	var date = Number(val.date);
	var hours = Number(val.hours);
	var minutes = Number(val.minutes);
	var seconds = Number(val.seconds);
	
	var date = new Date(year, month, date, hours, minutes, seconds);
	*/	
	
	var strMonth = '';
	if (date.getMonth() + 1 < 10) {
		strMonth = '0' + (date.getMonth() + 1);
	} else {
		strMonth = (date.getMonth() + 1);
	}
	var strDate = '';
	if (date.getDate() < 10) {
		strDate = '0' + date.getDate();
	} else {
		strDate = date.getDate();
	}
	
	var strHours = '';
	if (date.getHours() < 10) {
		strHours = '0' + date.getHours();
	} else {
		strHours = date.getHours();
	}
	
	var strMinutes = '';
	if (date.getMinutes() < 10) {
		strMinutes = '0' + date.getMinutes();
	} else {
		strMinutes = date.getMinutes();
	}
	
	var strSeconds = '';
	if (date.getSeconds() < 10) {
		strSeconds = '0' + date.getSeconds();
	} else {
		strSeconds = date.getSeconds();
	}
	
	var strDate = date.getFullYear() + '.' + strMonth + '.' + strDate + ' ' + strHours + ':' + strMinutes + ':' + strSeconds; 
	return strDate;
}

/**
 * 결재문서 작성 화면으로 이동
 * @param apprMstId
 * @param apprDesc
 * @param cntnId
 */
function approvalFormToWrite (apprMstId, apprDesc, cntnId) {
	var url = webRootPath + '/approval/approval_write?apprMstId=' + apprMstId + '&cntnId=' + cntnId + '&apprDesc=' + apprDesc;
	parent.$('#frmMain').attr('src', url);
}

/* 
 * 선택한 보존연한 정보를 입력받아서 현재일자를 기준으로 보존연한 정보를 셋팅한다.
 * 00-1달, 01-1년, 03-3년, 05-5년, 10-10년, 99-영구
 * source : 보존연한값을 선택할 수 있는 select box
 * target : 보존연한에 해당하는 일자를 계산해서 보여주기 위한 대상(div) 
 */
function getServiceLifeDate(source, target, target2) {
	var serviceLife = source;
	var now = new Date();
	var year = now.getFullYear();
	var month = now.getMonth();
	var day = now.getDate();
	
	if (serviceLife == '00' && month == 11) {
		now.setFullYear(year + 1, 0);
		year = now.getFullYear();
		month = now.getMonth() + 1;
	} else if (serviceLife == '00' && month != 11) {
		now.setFullYear(year, month + 1);
		year = now.getFullYear();
		month = now.getMonth(month) + 1;
	} else if (serviceLife == '99') {
		year = 9999;
		month = 12;
		day = 31;
	} else {
		now.setFullYear(year + Number(serviceLife));
		year = now.getFullYear();
		month = now.getMonth() + 1;
	}
	
	if (month < 10) month = "0" + month;
	if (day < 10) day = "0" + day;

	$('#' + target).text(year + "." + month + "." + day);
	$('#' + target2).val(year + "" + month + "" + day);
}

/**
 * 조직 목록을 가져와서 트리를 그린다.
 * @param rootUrl
 * @param level
 * @param oganCode
 */
function getAllOganList(rootUrl, oganTree, topNode) {
	$("#" + oganTree).tree({
		url: rootUrl + '/rest/ogan/getAllOganList',
		method: "get",
		cascadeCheck: true,
		loadFilter: function(rows) {
			return convert(rows);
		}
	});	
}

/**
 * easyui tree를 Dispaly한다.
 * @param rows
 * @returns {Array}
 */
function convert(rows) {
	function exists(rows, parentId, parentLev) {		
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].id == parentId && rows[i].oganLev == (parentLev + 1)) {
				return true;
			}
			return false;
		}
	}

	var nodes = [];
	// get the top level nodes	
	for (var i = 0; i < rows.length; i++) {
		var row = rows[i];
		if (!exists(rows, row.parentId, row.parentLev) && row.oganLev == 1) {
			nodes.push({
				id: row.id,
				text: row.text,
				oganLev:row.oganLev
			})
		}
	}	

	var toDo = [];
	for(var i = 0; i < nodes.length; i++){
        toDo.push(nodes[i]);
    }
		
    while(toDo.length){
        var node = toDo.shift();    // the parent node
        // get the children nodes
        for(var i=0; i<rows.length; i++){
            var row = rows[i];            
            if (row.parentId == node.id) { //&& (row.parentLev + 1) == node.oganLev){
                var child = "";
                child = {id:row.id,text:row.text,oganLev:row.oganLev};
                if (node.children){
                    node.children.push(child);
                } else {
                    node.children = [child];
                }
                toDo.push(child);
            }
        }
    }
        
    return nodes;
}

/**
 * alert 창을 띄운다.
 * @param msg
 */
function alertMsg(title, msg) {
	$.messager.defaults.ok = "OK";
	$.messager.alert({
		width : 350,
		title : title,
		msg : msg,
		icon : 'warning',
		fn: function(r) {
		}
	});
}

/**
 * alert 창을 띄운다.
 * @param msg
 */
function alertMsgCallbackFocus(title, msg, obj) {
	$.messager.defaults.ok = "OK";
	$.messager.alert({
		width : 350,
		title : title,
		msg : msg,
		icon : 'warning',
		fn: function(r) {
			obj.focus();
		}
	});	
}

/**
 * 파일 사이즈를 KB 사이즈로 변환해서 소수점 2자리까지만 표기
 * @param num
 */
function formatterFileSize(num, row) {
	var size = num / 1024;
	var convertSize = comma(size.toFixed(2));
	return convertSize + " KB";
}

//1000단위에 콤마찍기
function comma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

//1000단위의 콤마풀기
function uncomma(str) {
    str = String(str);
    return str.replace(/[^\d]+/g, '');
}

function fileDownload2(cntnId, dcmtRgsrNo, fileId) {
	var url = webRootPath + "/download/cntnId/" + cntnId + "/dcmtRgsrNo/" + dcmtRgsrNo + "/fileId/" + fileId;
	self.location = url;	
}

function fileDownload(targetUrl) {
	$('#frmMain').attr("sandbox", 'allow-downloads allow-same-origin allow-scripts allow-popups allow-forms allow-top-navigation allow-pointer-lock');
	console.log(targetUrl);        	
	window.addEventListener('focus', window_focus, false);
	function window_focus() {
		window.removeEventListener('focus', window_focus, false);
		URL.revokeObjectURL(targetUrl);    		
	}
	location.href = targetUrl; 	
}
/***
 * Datebox에서 사용 : 날짜 사이에 -로 구분
 * @param date
 * @returns
 */
function dashformatter(date){
    var y = date.getFullYear();
    var m = date.getMonth()+1;
    var d = date.getDate();
    return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}

/***
 * Datebox에서 사용 : -를 제거한 날짜 반환
 * @param s
 * @returns
 */
function dashparser(s){
    if (!s) return new Date();
    var ss = (s.split('-'));
    var y = parseInt(ss[0],10);
    var m = parseInt(ss[1],10);
    var d = parseInt(ss[2],10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
        return new Date(y,m-1,d);
    } else {
        return new Date();
    }
}

/***
 * form 내의 객체의 값들을 모두 json 형태로 변환한다.
 * @param formId
 * @returns
 */
function parseFormHelper (formId) {
    var serialized = $("#"+formId).serializeArray();    
    var s = '';
    var data = {};
	
	for(s in serialized){
        data[serialized[s]['name']] = serialized[s]['value']
    }
	
    return JSON.stringify(data);
}

Date.prototype.format = function (f) {
    if (!this.valueOf()) return " ";

    var weekKorName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var weekKorShortName = ["일", "월", "화", "수", "목", "금", "토"];
    var weekEngName = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
    var weekEngShortName = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
    var d = this;

    return f.replace(/(yyyy|yy|MM|dd|KS|KL|ES|EL|HH|hh|mm|ss|a\/p)/gi, function ($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear(); // 년 (4자리)
            case "yy": return (d.getFullYear() % 1000).zf(2); // 년 (2자리)
            case "MM": return (d.getMonth() + 1).zf(2); // 월 (2자리)
            case "dd": return d.getDate().zf(2); // 일 (2자리)
            case "KS": return weekKorShortName[d.getDay()]; // 요일 (짧은 한글)
            case "KL": return weekKorName[d.getDay()]; // 요일 (긴 한글)
            case "ES": return weekEngShortName[d.getDay()]; // 요일 (짧은 영어)
            case "EL": return weekEngName[d.getDay()]; // 요일 (긴 영어)
            case "HH": return d.getHours().zf(2); // 시간 (24시간 기준, 2자리)
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2); // 시간 (12시간 기준, 2자리)
            case "mm": return d.getMinutes().zf(2); // 분 (2자리)
            case "ss": return d.getSeconds().zf(2); // 초 (2자리)
            case "a/p": return d.getHours() < 12 ? "오전" : "오후"; // 오전/오후 구분
            default: return $1;
        }
    });
};


String.prototype.string = function (len) { var s = '', i = 0; while (i++ < len) { s += this; } return s; };
String.prototype.zf = function (len) { return "0".string(len - this.length) + this; };
Number.prototype.zf = function (len) { return this.toString().zf(len); };
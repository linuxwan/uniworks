<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Uniworks Groupware</title>
    <link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/themes/icon.css">    
    <link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/uniworks.css">
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/jquery.min.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/locale/easyui-lang-${userSession.language}.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/common.js"></script>
    
    <script type="text/javascript">
    var cntnId = '${param.cntnId}';
    var docWriteNo = 0; //
    $(function(){
    	$("#boardList").datagrid({
    		onClickRow: function(rowIndex, rowData) {
    			goView(rowData.cntnId, rowData.dcmtRgsrNo);
    		}
    	});    	    	
    	
    	$('#btnSearch').bind('click', function(){
    		$('#boardList').datagrid({loadFilter:pagerFilter}).datagrid('loadData', getData());
    	});
    	
    	$(window).resize(function(){
    		$("div.easyui-tabs").each(function(){
    			if ($(this).is(':visible')) {
    				$(this).tabs('resize', {
    					height: '100%'
    				});
    			}
    		});
    		
    		$("#boardList").datagrid('resize', {
    			height: '100%'
    		});    		
    	}).resize();
    	
    	$("#listTabsLayer").tabs({    		
    		onSelect:function(title) {
    			var chkTabTitle = "<spring:message code="resc.label.list"/>";
    			if (title == chkTabTitle) {
    				$('#boardList').datagrid({loadFilter:pagerFilter}).datagrid('loadData', getData());
    			}
    		}
    	});
    	
    	$('#boardList').datagrid({loadFilter:pagerFilter}).datagrid('loadData', getData());
    	
    	var today = new Date();
    	var year = today.getFullYear();
    	var crntDate = year + '-' + (today.getMonth() + 1) + '-' + today.getDate(); 
    	$('#startDate').datebox('setValue', year + '-01-01');
    	$('#finishDate').datebox('setValue', crntDate);    	
    });
        
    /*
     * 게시판 문서 목록을 Ajax로 호출
    */
    function getAjaxData() {
    	var startDate = $('#startDate').datebox('getValue');
    	var finishDate = $('#finishDate').datebox('getValue');
    	var searchType = $('#searchType').combobox('getValue');
    	var searchWord = $('#searchWord').textbox('getValue');
    	
    	var today = new Date();
    	var month = today.getMonth() + 1;
    	var day = today.getDate();
    	
    	if (month < 10) month = '0' + month;
    	if (day < 10) day = '0' + day;
    	if (startDate == "") startDate = today.getFullYear() + "-01-01";
    	if (finishDate == "") finishDate = today.getFullYear() + "-" + month + "-" + day;  
    	if (searchWord == "") searchWord = "null";
    	
    	rows = [];    	
    	var url="<c:out value="${contextPath}"/>/rest/board/board_list_01/cntnId/" + $("#cntnId").val() + "/startDate/" + startDate + "/finishDate/" + finishDate + "/searchType/" + searchType + "/searchWord/" + searchWord;
    	
    	$.ajaxSetup({async: false});
    	$.getJSON(url, function (data, status){
    		if (status == 'success') {    			
    			$.each(data, function(index, entry) {
    				rows.push({
    					cntnId: entry["cntnId"],
    					prntDcmtRgsrNo: entry["prntDcmtRgsrNo"],
    					dcmtRgsrNo: entry["dcmtRgsrNo"],
    					title: entry["title"],
    					authEmpName: entry["authEmpName"],
    					dcmtRgsrDatetime: entry["dcmtRgsrDatetime"],
    					atchIndc: entry["atchIndc"],
    					viewCnt: entry["viewCnt"],
    					boardId: entry["boardId"]
    				});
    			});    		
    			return rows;
    		} else {
    			return;
    		}
    	});
    }
                    
    function getData() {  
    	getAjaxData();
    	return rows;    	
    }
    
    function pagerFilter(data){    	
		if (typeof data.length == 'number' && typeof data.splice == 'function'){	// is array
			data = {
				total: data.length,
				rows: data
			}
		}
		
		var dg = $(this);
		var opts = dg.datagrid('options');
		var pager = dg.datagrid('getPager');
		pager.pagination({
			onSelectPage:function(pageNum, pageSize){
				opts.pageNumber = pageNum;
				opts.pageSize = pageSize;
				pager.pagination('refresh',{
					pageNumber:pageNum,
					pageSize:pageSize
				});
				dg.datagrid('loadData',data);
			},
			onRefresh:function(pageNum, pageSize) {
				opts.pageNumber = pageNum;
				opts.pageSize = pageSize;
				pager.pagination('refresh',{
					pageNumber:pageNum,
					pageSize:pageSize
				});
				$("#boardList").datagrid("load", {});
				$('#boardList').datagrid({loadFilter:pagerFilter}).datagrid('loadData', getData());
			}
		});
		if (!data.originalRows){
			data.originalRows = (data.rows);
		}
		var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
		var end = start + parseInt(opts.pageSize);
		data.rows = (data.originalRows.slice(start, end));
		
		return data;
	}
    
    /**
     * 게시판 작성 문서 조회
    */
    function goView(cntnId, dcmtRgsrNo) {
    	var frameId = "listTabsFrame-" + dcmtRgsrNo;
    	
    	if ($("#listTabsLayer").tabs("exists", dcmtRgsrNo)) {
    		$("#listTabsLayer").tabs("close", dcmtRgsrNo);
    	}
    	var securityParam = "&_csrf=" + $('#_csrf').val();
    	var src = "<c:out value="${contextPath}"/>/approval/approval_view_form_01?cntnId=" + cntnId + "&dcmtRgsrNo=" + dcmtRgsrNo + securityParam;
    	
    	var content = "<iframe name='" + frameId + "' src='" + src + "' id='" + frameId + "' frameborder='0' style='border:0;width:100%;height:100%;padding:10px 20px 0 0;' sandbox='allow-same-origin allow-scripts allow-popups allow-forms allow-top-navigation allow-pointer-lock' seamless='seamless'></iframe>";
    	
    	$("#listTabsLayer").tabs("add", {
    		title: dcmtRgsrNo,
    		content: content,    		
    		closable: true,
    		bodyCls: 'noscroll'
    	});	
    }    
    /**
     *	작성화면
    */
    function goWrite() {
    	var frameId = "listTabsFrame-" + docWriteNo;
    	var menuId = '${param.menuId}';
    	if ($("#listTabsLayer").tabs("exists", frameId)) {
    		$("#listTabsLayer").tabs("close", frameId);
    	}
    	var securityParam = "&_csrf=" + $('#_csrf').val();
    	var src = "<c:out value="${contextPath}"/>/board/board_write_form_01?cntnId=" + cntnId + "&menuId=" + menuId + "&docWriteNo=" + docWriteNo + securityParam;
    	console.log("src :" + src);
    	var title = '<spring:message code="resc.btn.enrollment"/>';
    	var content = "<iframe name='" + frameId + "' src='" + src + "' id='" + frameId + "' frameborder='0' style='border:0;width:100%;height:100%;padding:10px 20px 0 0;' sandbox='allow-same-origin allow-scripts allow-popups allow-forms allow-top-navigation allow-pointer-lock' seamless='seamless'></iframe>";
    	
    	$("#listTabsLayer").tabs("add", {
    		title: title + "-" + docWriteNo,
    		content: content,    		
    		closable: true,
    		bodyCls: 'noscroll'
    	});	
    	docWriteNo++;
    }
    
    function callResize()  
    {  
        var height = document.body.scrollHeight;
        document.getElementById("listTabsLayer").style.height = parseInt(height) * 0.8 + 'px';
        parent.resizeTopIframe(height);  
	} 
    
    window.onload = callResize;    
    </script>
</head>
<body>	
	<form id="boardListTabForm" method="post" action="">
	<jsp:include page="/WEB-INF/views/include/common/hidden_type_01.jsp"></jsp:include>	
	<div id="listTabsLayer" class="easyui-tabs" style="width:100%;height:100vh;">
		<div class="noscroll" title="<spring:message code="resc.label.list"/>" style="padding:20px;display:none;height:100vh;"> 			
		    <table id="boardList" class="easyui-datagrid" style="width:100%;height:100vh;" 		        
		        title="${cntnName}" 
		        data-options="rownumbers:true, singleSelect:true, pagination:true, autoRowHeight:false, pageSize:10, toolbar:'#tb'">
		    <thead>
		        <tr>
		        	<th data-options="field:'division',width:'25%',halign:'center',align:'center'"><spring:message code="resc.label.division"/></th>
		        	<th data-options="field:'authEmpName',width:'10%',halign:'center',align:'center'"><spring:message code="resc.label.author"/></th>
	        		<th data-options="field:'title',width:'30%',halign:'center',align:'center'"><spring:message code="resc.label.title"/></th>
	        		<th data-options="field:'atchIndc',width:'10%',halign:'center',align:'left'"><spring:message code="resc.label.attchFile"/></th>
	        		<th data-options="field:'viewCnt',width:'10%',halign:'center',align:'left'"><spring:message code="resc.label.viewCount"/></th>	        		
	        		<th data-options="field:'dcmtRgsrDatetime',width:'15%',halign:'center',align:'left',formatter:formatDate"><spring:message code="resc.label.createDateTime"/></th>        		
		        </tr>
		    </thead>
			</table>
			<div id="tb" style="padding:2px 5px;">
				<spring:message code="resc.label.inquiryPeriod"/> : <input id="startDate" name="startDate" class="easyui-datebox" style="width:110px" data-options="formatter:dashformatter,parser:dashparser">
				~ <input id="finishDate" name="finishDate" class="easyui-datebox" style="width:110px" data-options="formatter:dashformatter,parser:dashparser">				
				&nbsp;&nbsp;
				<spring:message code="resc.label.searchItem"/> : 
				<select id="searchType" name="searchType" class="easyui-combobox" panelHeight="auto" style="width:100px">
				<c:forEach items="${searchItemList}" var="opt" varStatus="st">
			    	<option value="${opt.subCode}">${opt.rescKeyValue}</option>
			    </c:forEach>	
				</select>
				<input id="searchWord" name="searchWord" class="easyui-textbox" style="width:140px">
				<a href="#" id="btnSearch" class="easyui-linkbutton" iconCls="icon-search"><spring:message code="resc.label.search"/></a>
				<a href="javascript:void(0)" id="btnWrite" class="easyui-linkbutton" iconCls="icon-add" style="float: right;" onclick="goWrite()"><spring:message code="resc.btn.enrollment"/></a>				
			</div>
		</div>
	</div>
	</form>
</body>
</html>
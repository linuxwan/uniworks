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
    $(function(){    	
    	$("#apprList").datagrid({
    		onClickRow: function(rowIndex, rowData) {
    			goView(rowData.cntnId, rowData.dcmtRgsrNo);
    		}
    	});    	    	
    	
    	$(window).resize(function(){
    		$("div.easyui-tabs").each(function(){
    			if ($(this).is(':visible')) {
    				$(this).tabs('resize', {
    					height: '100%'
    				});
    			}
    		});
    		
    		$("#apprList").datagrid('resize', {
    			height: '100%'
    		});    		
    	}).resize();
    	
    	$("#listTabsLayer").tabs({    		
    		onSelect:function(title) {
    			var chkTabTitle = "<spring:message code="resc.label.list"/>";
    			if (title == chkTabTitle) {
    				$('#apprList').datagrid({loadFilter:pagerFilter}).datagrid('loadData', getData());
    			}
    		}
    	});
    	
    	$('#apprList').datagrid({loadFilter:pagerFilter}).datagrid('loadData', getData());
    });
    
    /*
     * 결재 대기문서 목록을 Ajax로 호출
    */
    function getAjaxData() {
    	rows = [];
    	
    	var url="<c:out value="${contextPath}"/>/rest/approval/approval_waiting_list_01";
    	$.ajaxSetup({async: false});
    	$.getJSON(url, function (data, status){
    		if (status == 'success') {    			
    			$.each(data, function(index, entry) {
    				rows.push({
    					cntnId: entry["cntnId"],
    					cntnName: entry["cntnName"],
    					dcmtRgsrNo: entry["dcmtRgsrNo"],
    					docTitle: entry["docTitle"],
    					empName: entry["empName"],
    					dcmtRgsrDatetime: entry["dcmtRgsrDatetime"] 
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
				$("#apprList").datagrid("load", {});
				$('#apprList').datagrid({loadFilter:pagerFilter}).datagrid('loadData', getData());
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
        
    function goView(cntnId, dcmtRgsrNo) {
    	var frameId = "listTabsFrame-" + dcmtRgsrNo;
    	
    	if ($("#listTabsLayer").tabs("exists", dcmtRgsrNo)) {
    		$("#listTabsLayer").tabs("close", dcmtRgsrNo);
    	}
    	var src = "<c:out value="${contextPath}"/>/approval/approval_view_form_01?cntnId=" + cntnId + "&dcmtRgsrNo=" + dcmtRgsrNo;
    	var content = "<iframe name=\"" + frameId + "\" src=\"" + src + "\" id=\"" + frameId + "\" frameborder=\"0\" style=\"border:0;width:100%;height:100%;padding:10px 20px 0 0; sandbox=\"allow-same-origin allow-scripts allow-popups allow-forms allow-top-navigation allow-pointer-lock\" seamless=\"seamless\" \"></iframe>";
    	
    	$("#listTabsLayer").tabs("add", {
    		title: dcmtRgsrNo,
    		content: content,
    		closable: true,
    		bodyCls: 'noscroll'
    	});	
    }    
    </script>
</head>
<body>	
	<form id="apprListTabForm" method="post" action="">
	<jsp:include page="/WEB-INF/views/include/common/hidden_type_01.jsp"></jsp:include>	
	<div id="listTabsLayer" class="easyui-tabs" style="width:100%;height:690px;">
		<div class="noscroll" title="<spring:message code="resc.label.list"/>" style="padding:20px;display:none;"> 
			<table id="apprList" class="easyui-datagrid" style="width:100%;height:450px" 		        
		        title="<spring:message code="resc.label.waitingDocList"/>" 
		        data-options="rownumbers:true, singleSelect:true, pagination:true, autoRowHeight:false, pageSize:10">
		    <thead>
		        <tr>
		        	<th data-options="field:'cntnId',width:'10%',halign:'center',align:'center'"><spring:message code="resc.label.cntnId"/></th>
	        		<th data-options="field:'cntnName',width:'10%',halign:'center',align:'center'"><spring:message code="resc.label.cntnName"/></th>
	        		<th data-options="field:'dcmtRgsrNo',width:'18%',halign:'center',align:'left'"><spring:message code="resc.label.dcmtRgsrNo"/></th>
	        		<th data-options="field:'docTitle',width:'30%',halign:'center',align:'left'"><spring:message code="resc.label.docTitle"/></th>
	        		<th data-options="field:'empName',width:'8%',halign:'center',align:'center'"><spring:message code="resc.label.empName"/></th>
	        		<th data-options="field:'dcmtRgsrDatetime',width:'15%',halign:'center',align:'left',formatter:formatDate"><spring:message code="resc.label.dcmtRgsrDatetime"/></th>        		
		        </tr>
		    </thead>
			</table>
		</div>
	</div>
	</form>
</body>
</html>
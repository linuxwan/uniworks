<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title><spring:message code='resc.label.lineApprList'/></title>
    <link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/themes/icon.css">    
    <link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/uniworks.css">
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/jquery.min.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/locale/easyui-lang-${userSession.language}.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/common.js"></script>
    <script type="text/javascript">
    var url = "";
    var rows = [];
    
    $(function(){    	    	
    	url = "<c:out value="${contextPath}"/>/rest/approval/personal_line_appr_01";	
    	$('#lineApprList').datagrid('loadData', getData());
    	
    	$('#btnConfirm').bind('click', function(){
    		var rowData = $("#lineApprList").datagrid('getSelected');	
    		var apprLevel = rowData.lastApprLev;
    		
    		for (var i = 1; i <= apprLevel; i++) {
    		    var apprEmpNo = "apprEmpNo" + i;
    		    var apprEmpName = "apprEmpName" + i;
    		    
    		    opener.setLineApproval(rowData[apprEmpNo]);
    		}
    		
    		window.close();
    	});
    });
    
    /**
     * 팝업창에서 호출하기 위한 함수(refresh)
     */
    function reload() {
    	$('#lineApprList').datagrid('loadData', getData());
    }
    
    /*
     * 컨텐츠 정보를 가져온다.
    */
    function getData() {
    	getAjaxData(url);
    	return rows; 
    }
    /*
     * 등록된 컨텐츠 목록을 Ajax로 호출
    */
    function getAjaxData(url) {
    	rows = [];
   	    	
    	$.ajaxSetup({async: false});    	 
    	$.getJSON(url, function (data, status){
    		if (status == 'success') {    			 
    			$.each(data, function(index, entry) {
    				rows.push({
    					coId: entry["coId"],
    					seqNo: entry["seqNo"],
    					lastApprLev: entry["lastApprLev"],
    					apprEmpNo1: entry["apprEmpNo1"],
    					apprEmpNo2: entry["apprEmpNo2"],
    					apprEmpNo3: entry["apprEmpNo3"],
    					apprEmpNo4: entry["apprEmpNo4"],
    					apprEmpNo5: entry["apprEmpNo5"],
    					apprEmpNo6: entry["apprEmpNo6"],
    					apprEmpNo7: entry["apprEmpNo7"],
    					apprEmpNo8: entry["apprEmpNo8"],
    					apprEmpNo9: entry["apprEmpNo9"],
    					apprEmpNo10: entry["apprEmpNo10"],
    					apprEmpNo11: entry["apprEmpNo11"],
    					apprEmpNo12: entry["apprEmpNo12"],
    					apprEmpName1: entry["apprEmpName1"],
    					apprEmpName2: entry["apprEmpName2"],
    					apprEmpName3: entry["apprEmpName3"],
    					apprEmpName4: entry["apprEmpName4"],
    					apprEmpName5: entry["apprEmpName5"],
    					apprEmpName6: entry["apprEmpName6"],
    					apprEmpName7: entry["apprEmpName7"],
    					apprEmpName8: entry["apprEmpName8"],
    					apprEmpName9: entry["apprEmpName9"],
    					apprEmpName10: entry["apprEmpName10"],
    					apprEmpName11: entry["apprEmpName11"],
    					apprEmpName12: entry["apprEmpName12"]
    				});
    			});
    			
    			return rows;
    		} else {
    			return;
    		}
    	});
    }
    /**
     * 조회
    **/
    function inquiry() {
    	reload();
    }
    /**
     * 삭제
    **/
    function removeit() {    	
    	var rowData = $("#lineApprList").datagrid('getSelected');		    	
    	
    	if (rowData == null) {
    		var title = '<spring:message code="resc.label.confirm"/>';
    		var msg = '<spring:message code="resc.msg.noSelectLineAppr"/>';
    		alertMsg(title, msg);
			return;
    	}
    	
    	msg = '<spring:message code="resc.msg.confirmDel"/>';    		
		$.messager.confirm(title, msg, function(r) {
			if (r) {
				deleteLineAppr();
			}
		});
    }
    
    function deleteLineAppr() {
    	var rowData = $("#lineApprList").datagrid('getSelected');			    				    	
	    
		var strUrl = "<c:out value="${contextPath}"/>/rest/approval/remove_personal_line_appr_01?userId=" + '${userSession.userId}' + "&seqNo=" + rowData.seqNo;
    	
    	$.ajax({
			type: 'GET',
			url: strUrl,						 				
			dataType: 'json',						
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
		        xhr.setRequestHeader("Content-Type", "application/json");
				//데이터를 전송하기 전에 헤더에 csrf값을 설정한다.					
				xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
			},  				
			success : function(msg) {
				var title = '<spring:message code="resc.label.confirm"/>';		    			
				$.messager.alert(title, msg, "info",  function(){
					inquiry();
				});						
			},
			error : function(xhr, status, error) {
				console.log("error: " + status);
			}
		});
    }
    </script>
</head>
<body>
	<div class="easyui-layout" style="width:950px;height:440px;">
		<table style="width:100%;height:400px;">
			<tr> 
			<td style="width:100%">			
			    <table id="lineApprList" class="easyui-datagrid" style="width:100%;height:100%" 		        
			        title="<spring:message code="resc.label.lineApprList"/>" 
			        data-options="rownumbers:true, singleSelect:true, pagination:false, autoRowHeight:true, toolbar:'#tm'">
			    <thead>
			        <tr>			        	
		        		<th data-options="field:'lastApprLev',width:'6%',halign:'center',align:'center'"><spring:message code="resc.label.apprDgr"/></th>
		        		<th data-options="field:'apprEmpName1',width:'7%',halign:'center',align:'center'"><spring:message code="resc.label.approver"/> 1</th>			        		
		        		<th data-options="field:'apprEmpName2',width:'7%',halign:'center',align:'center'"><spring:message code="resc.label.approver"/> 2</th>
		        		<th data-options="field:'apprEmpName3',width:'8%',halign:'center',align:'center'"><spring:message code="resc.label.approver"/> 3</th>
		        		<th data-options="field:'apprEmpName4',width:'8%',halign:'center',align:'center'"><spring:message code="resc.label.approver"/> 4</th>
		        		<th data-options="field:'apprEmpName5',width:'8%',halign:'center',align:'center'"><spring:message code="resc.label.approver"/> 5</th>
		        		<th data-options="field:'apprEmpName6',width:'8%',halign:'center',align:'center'"><spring:message code="resc.label.approver"/> 6</th>
		        		<th data-options="field:'apprEmpName7',width:'8%',halign:'center',align:'center'"><spring:message code="resc.label.approver"/> 7</th>        		
		        		<th data-options="field:'apprEmpName8',width:'8%',halign:'center',align:'center'"><spring:message code="resc.label.approver"/> 8</th>
		        		<th data-options="field:'apprEmpName9',width:'8%',halign:'center',align:'center'"><spring:message code="resc.label.approver"/> 9</th>
		        		<th data-options="field:'apprEmpName10',width:'8%',halign:'center',align:'center'"><spring:message code="resc.label.approver"/> 10</th>
		        		<th data-options="field:'apprEmpName11',width:'8%',halign:'center',align:'center'"><spring:message code="resc.label.approver"/> 11</th>
		        		<th data-options="field:'apprEmpName12',width:'8%',halign:'center',align:'center'"><spring:message code="resc.label.approver"/> 12</th>
			        </tr>
			    </thead>
				</table>
				<div id="tm" style="height:auto">    
			        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" onclick="inquiry()"><spring:message code="resc.btn.inquiry"/></a>				        			    
			        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()"><spring:message code="resc.btn.delete"/></a>			               
			    </div>
			</td>
			</tr> 
		</table>
		<div style="padding:1px;float:left">
			<a href="#" id="btnConfirm" class="easyui-linkbutton" style="width:100px"><spring:message code="resc.label.confirm"/></a>
			<a href="javascript:window.close();" class="easyui-linkbutton" style="width:100px"><spring:message code="resc.btn.cancel"/></a>
		</div>
	</div>
</body>
</html>
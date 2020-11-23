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
</head>
<body>
	<div class="easyui-layout" style="width:950px;height:440px;">
		<table style="width:100%;height:430px;">
			<tr> 
			<td style="width:100%">			
			    <table id="lineApprList" class="easyui-datagrid" style="width:100%;height:100%" 		        
			        title="<spring:message code="resc.label.lineApprList"/>" 
			        data-options="rownumbers:true, singleSelect:true, pagination:false, autoRowHeight:true, toolbar:'#tm'">
			    <thead>
			        <tr>
			        	<th data-options="field:'seqNo',width:'8%',halign:'center',align:'center'"><spring:message code="resc.label.seqNo"/></th>
		        		<th data-options="field:'lastApprLev',width:'8%',halign:'center',align:'left'"><spring:message code="resc.label.apprDgr"/></th>
		        		<th data-options="field:'apprEmpName1',width:'7%',halign:'center',align:'left'"><spring:message code="resc.label.approver"/> 1</th>			        		
		        		<th data-options="field:'apprEmpName2',width:'7%',halign:'center',align:'left'"><spring:message code="resc.label.approver"/> 2</th>
		        		<th data-options="field:'apprEmpName3',width:'7%',halign:'center',align:'left'"><spring:message code="resc.label.approver"/> 3</th>
		        		<th data-options="field:'apprEmpName4',width:'7%',halign:'center',align:'left'"><spring:message code="resc.label.approver"/> 4</th>
		        		<th data-options="field:'apprEmpName5',width:'7%',halign:'center',align:'left'"><spring:message code="resc.label.approver"/> 5</th>
		        		<th data-options="field:'apprEmpName6',width:'7%',halign:'center',align:'left'"><spring:message code="resc.label.approver"/> 6</th>
		        		<th data-options="field:'apprEmpName7',width:'7%',halign:'center',align:'left'"><spring:message code="resc.label.approver"/> 7</th>        		
		        		<th data-options="field:'apprEmpName8',width:'7%',halign:'center',align:'left'"><spring:message code="resc.label.approver"/> 8</th>
		        		<th data-options="field:'apprEmpName9',width:'7%',halign:'center',align:'left'"><spring:message code="resc.label.approver"/> 9</th>
		        		<th data-options="field:'apprEmpName10',width:'7%',halign:'center',align:'left'"><spring:message code="resc.label.approver"/> 10</th>
		        		<th data-options="field:'apprEmpName11',width:'7%',halign:'center',align:'left'"><spring:message code="resc.label.approver"/> 11</th>
		        		<th data-options="field:'apprEmpName12',width:'7%',halign:'center',align:'left'"><spring:message code="resc.label.approver"/> 12</th>
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
	</div>
</body>
</html>
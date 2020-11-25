<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="resc.label.lineApprSelect"></spring:message></title>
    <link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/themes/icon.css">    
    <link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/uniworks.css">
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/jquery.min.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/locale/easyui-lang-${userSession.language}.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/common.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/tinymce/tinymce.min.js"></script>    
	<script type="text/javascript" src="<c:out value="${contextPath}"/>/tinymce/tinymce_init.js"></script>
	<script type="text/javascript" src="<c:out value="${contextPath}"/>/plugin/jquery.popupwindow.js"></script>
    
    <script type="text/javascript">
    $(function(){
    	getAllOganList('<c:out value="${contextPath}"/>', 'oganTree', '${userSession.coId}');
    	
    	$('#oganTree').tree({
    		onClick: function(node) {    			
    			getMembersListOftheOganCharge(node.id, node.oganLev)
    		}
    	});
    	
    	$('#btnConfirm').bind('click', function(){
    		var apprLevel = ${apprLevel};
    		
    		for (var i = 1; i <= apprLevel; i++) {
    			var txtApprInfo = $("#apprLine_" + i).textbox('getValue');
    			var index = txtApprInfo.indexOf('(');
    			var apprInfo = txtApprInfo.substring(0, index) + '<br/>' + txtApprInfo.substring(index);
    			$('#label_' + i, opener.document).html(apprInfo);
    			$('#apprLine_' + i, opener.document).val($('#apprEmpNo_' + i).val());
    			$('#apprLineDesc_' + i, opener.document).val(apprInfo);
    		}
    		
    		window.close();
    	});
    			
		$('#btnSearchWord').bind('click', function(){
			var searchWord = $('#searchWord').textbox('getValue');
			
			if (seachWordCheck()) {
				$('#tabs').tabs('select', 1);
				
				getSearchEmpName(searchWord);
			}
		});  			
    	
		//결재선 저장
		$('#btnApprLineSave').bind('click', function(){
			var apprLevel = ${apprLevel};
			var strApprList = "";
			var checkApprList = false;
    		for (var i = 1; i <= apprLevel; i++) {    			
    			if (i == 1) {
    				strApprList += 'apprEmpNo_' + i + ": " + $('#apprEmpNo_' + i).val();	
    			} else {
    				strApprList += ',apprEmpNo_' + i + ": " + $('#apprEmpNo_' + i).val();
    			}
    			
    			if ($('#apprEmpNo_' + i).val().length > 0) checkApprList = true;
    		}
    		
    		if (checkApprList == false) {
    			var title = "<spring:message code="resc.label.error"/>";
        		var msg = "<spring:message code="resc.msg.selectApprover"/>";
        		alertMsg(title, msg);
    			return;
    		}
    		
    		addLineApprs(strApprList, apprLevel);
		});
		
		//결재선 정보 가져오기
		$('#btnApprLineCall').bind('click', function() {
			var url = "<c:out value="${contextPath}"/>/approval/popup/personal_line_appr";
    		var popupWin = $.popupWindow(url, { height: 500, width: 800, name: 'personalApprLine', scrollbars: true });
		});
		
    	openInitialLineApprover();
    	
    	/* 조직 선택 시 BaseOganLev에 따라서 보직자만 또는 모든 조직원을 목록으로 가져온다. */
    	function getMembersListOftheOganCharge(oganCode, oganLev) {
    		var url = "<c:out value="${contextPath}"/>/rest/hr/getEmpChrgListForOganTree/oganLev/" + oganLev + "/oganCode/" + oganCode;
    		
    		$('#empList').datagrid({
    			url: url,
    			method: "get"
    		});    	
    	} 
    	
    	/* 설정된 라인결재자 정보가 있을 경우, 라인결재자 선택 팝업에서 Display한다. */
    	function openInitialLineApprover() {
    		var apprLevel = ${apprLevel};
    		for (var i = 1; i <= apprLevel; i++) {
    			var apprLineInfo = $('#apprLineDesc_' + i, opener.document).val().replace("<br/>", "");
    			var apprLineEmpNo = $('#apprLine_' + i, opener.document).val();
    			$("#apprLine_" + i).textbox('setValue', apprLineInfo);
    			$('#apprEmpNo_' + i).val(apprLineEmpNo);
    		}
    	}
    	
    	/* 성명으로 직원 찾기 */
    	function getSearchEmpName(searchWord) {
    		var url = "<c:out value="${contextPath}"/>/rest/hr/getSearchEmpName/empName/" + searchWord + "/workIndc/1";
    		
    		$('#searchEmpList').datagrid({
    			url: url,
    			method: 'get'
    		});
    	}
    	
    	//라인 결재자 정보 저장
        function addLineApprs(lineApprs, apprLevel) {
        	$.ajax({
        		url: '<c:out value="${contextPath}"/>'	+ '/rest/approval/save_line_appr',
        		type: 'get',
        		async:false,
        	    cache:false,
        		data: {lineApprovals:lineApprs, lastApprLev:apprLevel},
        		success: function(r) {
        			$.messager.alert('<spring:message code="resc.label.info"/>', r);    			
        		},
        		error: function(xhr, status, error) {
        			$.messager.alert('<spring:message code="resc.label.error"/>', status + "\r\n" + error);    			
        		}
        	});
        }  
    });
    
    /* 좌측의 조직도에서 결재자를 선택 후 추가. */
    function addApprLine(apprLineNo, no) {
    	var tab = $('#tabs').tabs('getSelected');
    	var index = $("#tabs").tabs("getTabIndex", tab);
    	var selectRow = "";
    	
    	//index가 0이면 조직도 Tab, 1이면 성명으로 검색한 결과
    	if (index == 0) {
    		selectRow = "empList";
    	} else if (index == 1) {
    		selectRow = "searchEmpList";
    	}

    	var row = $("#" + selectRow).datagrid('getSelected');
    	if (row) {    		
    		if (checkApprLine(row.empNo)) {
    			//기본조직 레벨에 맞는 조직명칭을 가져온다.
    			var asgnOganDesc = 'asgnOganDesc${userSession.baseOganLev}';
    			var selEmp = row.empNameKor + ' ' + row.dutyDesc + '(' + row[asgnOganDesc] + ')';
    			$("#" + apprLineNo).textbox('setValue', selEmp);
    			$("#apprEmpNo_" + no).val(row.empNo);    			
    		}
    	} else {
    		var title = "<spring:message code="resc.label.confirm"/>";
    		var msg = "<spring:message code="resc.msg.selectApprover"/>";
    		alertMsg(title, msg);
    	}
    }
    
    /* 선택한 라인 결재자 삭제. */
    function delApprLine(apprLineNo, no) {
    	$("#" + apprLineNo).textbox('setValue', '');
    	$("#apprEmpNo_" + no).val('');
    }
    
    /* 이미 등록된 결재자가 있는지 체크 */
    function checkApprLine(empNo) {
    	console.log(empNo);
    	var apprLevel = ${apprLevel};
    	for (var i = 1; i <= apprLevel; i++) {
    		var apprEmpNo = $("#apprEmpNo_" + i).val();
    		if (apprEmpNo == empNo) {
    			var title = "<spring:message code="resc.label.warning"/>";
    			var msg = "<spring:message code="resc.msg.selectedApprover"/>";
    			alertMsg(title, msg);
    			return false;
    		}
    	}
    	return true;
    }
    
    /* 검색어를 입력하지 않고 검색 버튼을 눌렀는지 체크 */
    function seachWordCheck() {
    	var searchWord = $('#searchWord').textbox('getValue');
    	if (searchWord.length < 1) {
    		var title = "<spring:message code="resc.label.warning"/>";
    		var msg = "<spring:message code="resc.msg.inputSearchWord"/>";
    		alertMsg(title, msg);
			return false;
    	}
    	return true;
    }
    
    function setLineApproval(apprEmpNo) {
    	console.log("apprEmpNo : " + apprEmpNo);
    	var node = $('#oganTree').tree('find', {id: apprEmpNo});
    }
    </script>
</head>
<body>
	<div class="easyui-layout" style="width:660px;height:610px;">
		<div data-options="region:'north'" style="height:50px;">
			<div style="padding:8px;">
				<table>
				<tr>
					<td>
						<select id="selSearchType" name="searchType" class="easyui-combobox" style="width:100px;" data-options="panelHeight:'auto'">
							<option value="N" selected="selected"><fmt:message key="resc.label.empName"/></option>	<!-- 직원이름으로 검색 -->
							<!--<option value="O"><fmt:message key="resc.label.ogan"/></option>--> <!-- 조직명칭으로 검색 -->
							<!--<option value="G"><fmt:message key="resc.label.group"/></option>-->
						</select>
					</td>
					<td>	
						<input class="easyui-textbox" id="searchWord" name="searchWord" style="width:100%;"></input>
					</td>
					<td>
						<a href="#" id="btnSearchWord" class="easyui-linkbutton btnSearch" style="width:70px"><spring:message code="resc.btn.search"/></a>
						<a href="#" id="btnApprLineSave" class="easyui-linkbutton btnSearch" style="width:80px"><spring:message code="resc.btn.apprLineSave"/></a>
						<a href="#" id="btnApprLineCall" class="easyui-linkbutton btnSearch" style="width:110px"><spring:message code="resc.btn.apprLineCall"/></a>
					</td>
				</tr>
				</table>		
			</div>
		</div>
		<div data-options="region:'south'" style="height:40px;">
			<div style="padding:5px;float:right">
				<a href="#" id="btnConfirm" class="easyui-linkbutton" style="width:100px"><spring:message code="resc.label.confirm"/></a>
				<a href="javascript:window.close();" class="easyui-linkbutton" style="width:100px"><spring:message code="resc.btn.cancel"/></a>
			</div>
		</div>
		<div data-options="region:'center',title:''" style="padding:10px">
			<div id="tabs" class="easyui-tabs" style="width:100%;height:100%;">
				<div id="oganTab" title='<spring:message code="resc.label.ogan"/>' style="padding:10px;">
					<div class="easyui-panel" style="width:100%;height:70%;">
						<ul id="oganTree" class="easyui-tree" style="width:100%;height:100px;">					
						</ul>
					</div>
					<div class="easyui-panel" style="width:100%;height:30%;">
						<table id="empList" class="easyui-datagrid" style="width:100%;height:100%;" data-options="fitColumns:true,singleSelect:true">
						    <thead>
						        <tr>
						            <th data-options="field:'empNo',width:100,hidden:true"><spring:message code="resc.label.empNo"/></th>
						            <th data-options="field:'empNameKor',width:'40%',align:'left'"><spring:message code="resc.label.empName"/></th>
						            <th data-options="field:'dutyDesc',width:'30%',align:'left'"><spring:message code="resc.label.dutyDesc"/></th>
						            <th data-options="field:'pstnDesc',width:'30%',align:'left'"><spring:message code="resc.label.position"/></th>
						        </tr>
						    </thead>
						</table>
					</div>
				</div>
				<div id="searchTab" title='<spring:message code="resc.label.search"/>' style="padding:10px">
					<table id="searchEmpList" class="easyui-datagrid" style="width:100%;height:100%;" data-options="fitColumns:true,singleSelect:true">
					    <thead>
					        <tr>
					            <th data-options="field:'empNo',width:100,hidden:true"><spring:message code="resc.label.empNo"/></th>
					            <th data-options="field:'empNameKor',width:'40%',align:'left'"><spring:message code="resc.label.empName"/></th>
					            <th data-options="field:'dutyDesc',width:'30%',align:'left'"><spring:message code="resc.label.dutyDesc"/></th>
					            <th data-options="field:'pstnDesc',width:'30%',align:'left'"><spring:message code="resc.label.position"/></th>
					        </tr>
					    </thead>
					</table>
				</div>
			</div>
		</div>
		<div data-options="region:'east',split:true,title:'<spring:message code="resc.label.approver"/>',collapsible:false" style="width:300px;">
			<div style="width:100%;height:5px"></div>
			<c:forEach items="${apprLineList}" var="apprLine" varStatus="st">
				<div class="easyui-panel" title="${st.count}<spring:message code="resc.label.orderApprover"/>" style="width:100%;">
					<div>
						<a href="javascript:addApprLine('apprLine_${st.count}',${st.count});" class="easyui-linkbutton" data-options="iconCls:'icon-add'"><spring:message code="resc.btn.add"/></a>
       					<a href="javascript:delApprLine('apprLine_${st.count}',${st.count});" class="easyui-linkbutton" data-options="iconCls:'icon-remove'"><spring:message code="resc.btn.delete"/></a>        					
       				</div>          				
       				<input class="easyui-textbox" name='apprLine_${st.count}' id='apprLine_${st.count}' style="width:100%;height:34px" readonly="readonly">
       				<input type="hidden" id="apprEmpNo_${st.count}"> 				        				
				</div>		
				<div style="width:100%;height:5px"></div>		
			</c:forEach>
		</div>		
	</div>
</body>
</html>    
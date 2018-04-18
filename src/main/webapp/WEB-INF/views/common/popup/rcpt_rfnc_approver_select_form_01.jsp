<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="resc.btn.rcptRfncSelect"></spring:message></title>
    <link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/themes/icon.css">    
    <link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/uniworks.css">
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/jquery.min.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/locale/easyui-lang-${userSession.language}.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/common.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/tinymce/tinymce.min.js"></script>    
	<script type="text/javascript" src="<c:out value="${contextPath}"/>/tinymce/tinymce_init.js"></script>
    
    <script type="text/javascript">
    var strOganCode = "";
	var strOganName = "";
	var empNo = "";
	var empName = "";
	
    $(function(){    	    	
    	getAllOganList('<c:out value="${contextPath}"/>', 'oganTree', '${userSession.coId}');
    	
    	$('#oganTree').tree({
    		onClick: function(node) {    			
    			getMembersListOftheOganCharge(node.id, node.oganLev);
    			strOganCode = node.id;
    			strOganName = node.text;
    		}
    	});
    	
    	$('#btnConfirm').bind('click', function(){
    		var rcptValue = "";
    		var rcptText = "";
    		var rfncValue = "";
    		var rfncText = "";
    		
    		//수신처 정보를 opener에 설정
    		var rcpt = $('#selRcpt').datalist('getRows');
    		for (var i = 0; i < rcpt.length; i++) {
    			if (i == 0) {
    				rcptValue += rcpt[i].value;
    				rcptText += rcpt[i].text;
    			} else {
    				rcptValue += "," + rcpt[i].value;
    				rcptText += "," + rcpt[i].text;
    			}
    		}
    		
    		$('#label_selRcpt', opener.document).html(rcptText);
    		$('#hd_selRcpt', opener.document).val(rcptValue);
    		$('#hd_selRcptDesc', opener.document).val(rcptText);
    		
    		//참조처 정보를 opener에 설정
    		var rfnc = $('#selRfnc').datalist('getRows');
    		for (var i = 0; i < rfnc.length; i++) {
    			if (i == 0) {
    				rfncValue += rfnc[i].value;
    				rfncText += rfnc[i].text;
    			} else {
    				rfncValue += "," + rfnc[i].value;
    				rfncText += "," + rfnc[i].text;
    			}
    		}
    		
    		$('#label_selRfnc', opener.document).html(rfncText);
    		$('#hd_selRfnc', opener.document).val(rfncValue);
    		$('#hd_selRfncDesc', opener.document).val(rfncText);
    		
    		window.close();
    	});
    	
    	//검색 버튼 클릭 
		$('#btnSearchWord').bind('click', function(){
			var searchType = $('#selSearchType').combobox('getValue');
			var searchWord = $('#searchWord').textbox('getValue');

			if (searchType == 'O') {	//조직으로 검색 시				
				if (seachWordCheck()) {
					$('#tabs').tabs('select', 1);
					
					$('#searchList').datagrid({
						columns: [[
							{field:'coId', title:'<spring:message code="resc.label.coId"/>', width:0, hidden: true},
							{field:'oganLev', title:'<spring:message code="resc.label.oganLev"/>', width:'30%', align:'left'},
							{field:'oganCode', title:'<spring:message code="resc.label.oganCode"/>', width:'30%', align:'left'},
							{field:'oganName', title:'<spring:message code="resc.label.oganName"/>', width:'40%', align:'left'}
						]]
					});
					
					getSearchOganName(searchWord);
				}
			} else if (searchType == 'N') {	//성명으로 검색 시
				if (seachWordCheck()) {
					$('#tabs').tabs('select', 1);
					
					$('#searchList').datagrid({
						columns: [[
							{field:'empNo', title:'<spring:message code="resc.label.empNo"/>', width:0, hidden: true},
							{field:'empNameKor', title:'<spring:message code="resc.label.empName"/>', width:'40%', align:'left'},
							{field:'dutyDesc', title:'<spring:message code="resc.label.dutyDesc"/>', width:'30%', align:'left'},
							{field:'pstnDesc', title:'<spring:message code="resc.label.position"/>', width:'30%', align:'left'}
						]]
					});
					getSearchEmpName(searchWord);
				}
			}
		});  			
    	
    	openInitialRcptRfnc();
    	
    	/* 조직 선택 시 BaseOganLev에 따라서 보직자만 또는 모든 조직원을 목록으로 가져온다. */
    	function getMembersListOftheOganCharge(oganCode, oganLev) {
    		var url = "<c:out value="${contextPath}"/>/rest/hr/getEmpChrgListForOganTree/oganLev/" + oganLev + "/oganCode/" + oganCode;
    		
    		$('#empList').datagrid({
    			url: url,
    			method: "get"
    		});    	
    	} 
    	
    	/* 설정된 라인결재자 정보가 있을 경우, 라인결재자 선택 팝업에서 Display한다. */
    	function openInitialRcptRfnc() {
    		var rcptValue = $('#hd_selRcpt', opener.document).val().split(',');
    		var rcptText = $('#hd_selRcptDesc', opener.document).val().split(',');
    		var rfncValue = $('#hd_selRfnc', opener.document).val().split(',');
    		var rfncText = $('#hd_selRfncDesc', opener.document).val().split(',');

    		if ($.trim(rcptValue) != "" && $.trim(rcptText) != "") {
				for (var i = 0; i < rcptValue.length; i++) {
					var rcpt = {value: rcptValue[i], text: rcptText[i]}; 	
					$('#selRcpt').datalist('appendRow', rcpt);
				}
    		}
			
    		if ($.trim(rfncValue) != "" && $.trim(rfncText) != "") {
				for (var i = 0; i < rfncValue.length; i++) {
					var rfnc = {value: rfncValue[i], text: rfncText[i]}; 	
					$('#selRfnc').datalist('appendRow', rfnc);
				}
    		}
    	}
    	
    	/* 성명으로 직원 찾기 */
    	function getSearchEmpName(searchWord) {
    		var url = "<c:out value="${contextPath}"/>/rest/hr/getSearchEmpName/empName/" + searchWord + "/workIndc/1";
    		
    		$('#searchList').datagrid({
    			url: url,
    			method: 'get'
    		});
    	}
    	
    	/* 조직명으로 조직장이 있는 조직 찾기 */
    	function getSearchOganName(searchWord) {
    		var url = "<c:out value="${contextPath}"/>/rest/ogan/getOganListByChief/searchWord/" + searchWord;
    		
    		$('#searchList').datagrid({
    			url: url,
    			method: 'get'
    		});
    	}
    });
    
    /* 좌측의 조직도, 조직도 검색결과에서 수신처를 선택 후 추가. */
    function addRcpt(selRcpt) {
    	var tab = $('#tabs').tabs('getSelected');
    	var index = $("#tabs").tabs("getTabIndex", tab);
    	var searchType = $('#selSearchType').combobox('getValue');
    	var selectRow = "";    	
    	
    	//index가 0이면 조직도 Tab, 1이면 성명/조직으로 검색한 결과
    	if (index == 0) {
    		selectRow = "empList";    
    		if (strOganCode.length < 1) {
    			var title = "<spring:message code="resc.label.warning"/>";
    			var msg = "<spring:message code="resc.msg.noSelectOgan"/>";
    			alertMsg(title, msg);
    			return;
    		}
    		
    		var rows = $('#empList').datagrid('getRows');
    		
    		var check = false;
    		for (var i = 0; i < rows.length; i++) {
    			if (rows[i].pstnDesc != null && rows[i].pstnDesc.length > 0) check = true;	
    		}
    		//조직책임자가 없는 조직일 경우, 조직책임자가 없다는 메시지 출력 후 종료.
    		if (!check) {
    			var title = "<spring:message code="resc.label.warning"/>";
    			var msg = "<spring:message code="resc.msg.noOganChrg"/>";
    			alertMsg(title, msg);
    			return;
    		}
   		    		
    		var text = strOganName + "<spring:message code="resc.label.chief"/>";    		
			var value = "O:" + strOganCode;    		
    		var dlist = $('#' + selRcpt);
			var rcpt = {value: value, text: text}; 								
			
			if (checkRcptRfnc(value)) dlist.datalist('appendRow', rcpt); 
    	} else if (index == 1) {
    		selectRow = "searchList";
    		if (searchType == 'N') {
    			var title = "<spring:message code="resc.label.warning"/>";
    			var msg = "<spring:message code="resc.msg.selRcptOnlyOgan"/>";
    			alertMsg(title, msg);
    		} else if (searchType == 'O') {	//조직 검색 결과인 경우
    			var row = $('#' + selectRow).datalist('getSelected');
    		
    			if (row) {
    				var dlist = $('#' + selRcpt);
    				var oganInfo = $('#' + selectRow).datalist('getSelected');    				
    				var value = "O:" + oganInfo['oganCode'];    				
    				var text = oganInfo['oganName'] + "<spring:message code="resc.label.chief"/>";    		
    				var rcpt = {value: value, text: text}; 	
    				if (checkRcptRfnc(value)) dlist.datalist('appendRow', rcpt);
    				return;
    			} else {	//선택하지 않았을 경우.
    				var title = "<spring:message code="resc.label.warning"/>";
    				var msg = "<spring:message code="resc.msg.noSelOganEmp"/>";
        			alertMsg(title, msg);
        			return;
    			}
    		}
    	}    	
    }
    
    /* 좌측의 조직도, 검색결과(성명, 조직)에서 참조처 선택 */
    function addRfnc(selRfnc) {
    	var tab = $('#tabs').tabs('getSelected');
    	var index = $("#tabs").tabs("getTabIndex", tab);
    	var searchType = $('#selSearchType').combobox('getValue');
    	var selectRow = "";
    	
    	//index가 0이면 조직도 Tab, 1이면 성명/조직으로 검색한 결과
    	if (index == 0) {
    		selectRow = "empList";    
    		if (strOganCode.length < 1) {
    			var title = "<spring:message code="resc.label.warning"/>";
    			var msg = "<spring:message code="resc.msg.noSelectOgan"/>";
    			alertMsg(title, msg);
    			return;
    		}
    		
    		var row = $('#empList').datagrid('getSelected');
    		if (row) {	//조직을 선택 후 가져온 직원 목록에서 선택했을 경우
    			empNo = row.empNo;
    			empName = row.empNameKor;
    			
    			var text = empName + " " + row.dutyDesc;
    			if (row.pstnDesc != null && row.pstnDesc.length > 1) {
    				text += "(" + row.pstnDesc + ")";
    			}

    			var value = "N:" + empNo;    		
        		var dlist = $('#' + selRfnc);
    			var rcpt = {value: value, text: text}; 								
    			
    			if (checkRcptRfnc(value)) dlist.datalist('appendRow', rcpt);
    			return;
    		} else {	//조직도에서 조직만 선택했을 경우, 조직장을 추가
    			var rows = $('#empList').datagrid('getRows');
        		
        		var check = false;
        		for (var i = 0; i < rows.length; i++) {
        			if (rows[i].pstnDesc != null && rows[i].pstnDesc.length > 0) check = true;	
        		}
        		//조직책임자가 없는 조직일 경우, 조직책임자가 없다는 메시지 출력 후 종료.
        		if (!check) {
        			var title = "<spring:message code="resc.label.warning"/>";
        			var msg = "<spring:message code="resc.msg.noOganChrg"/>";
        			alertMsg(title, msg);
        			return;
        		}
       		    		
        		var text = strOganName + "<spring:message code="resc.label.chief"/>";    		
    			var value = "O:" + strOganCode;    		
        		var dlist = $('#' + selRfnc);
    			var rcpt = {value: value, text: text}; 								
    			
    			if (checkRcptRfnc(value)) dlist.datalist('appendRow', rcpt); 
    		}    		    		
    	} else if (index == 1) {
    		selectRow = "searchList";
    		if (searchType == 'N') {	//성명으로 검색한 결과일 경우
    			var row = $('#' + selectRow).datagrid('getSelected');
    			if (row) {
    				empNo = row.empNo;
        			empName = row.empNameKor;
        			
        			var text = empName + " " + row.dutyDesc;
        			if (row.pstnDesc != null && row.pstnDesc.length > 1) {
        				text += "(" + row.pstnDesc + ")";
        			}

        			var value = "N:" + empNo;    		
            		var dlist = $('#' + selRfnc);
        			var rcpt = {value: value, text: text}; 								
        			
        			if (checkRcptRfnc(value)) dlist.datalist('appendRow', rcpt);
        			return;
    			} else {	//검색결과에서 직원을 선택하지 않았을 경우,
    				var title = "<spring:message code="resc.label.warning"/>";
        			var msg = "<spring:message code="resc.msg.noSelOganEmp"/>";
        			alertMsg(title, msg);
        			return;
    			}
    		} else if (searchType == 'O') {	//조직으로 검색한 결과일 경우
    			var row = $('#' + selectRow).datalist('getSelected');
    			if (row) {
    				var dlist = $('#' + selRfnc);
    				var oganInfo = $('#' + selectRow).datalist('getSelected');
    				var value = "O:" + oganInfo['oganCode'];
    				var text = oganInfo['oganName'] + "<spring:message code="resc.label.chief"/>";
    				var rcpt = {value: value, text: text}; 	
    				if (checkRcptRfnc(value)) dlist.datalist('appendRow', rcpt);
    				return;
    			} else {	//선택하지 않았을 경우.
    				var title = "<spring:message code="resc.label.warning"/>";
    				var msg = "<spring:message code="resc.msg.noSelOganEmp"/>";
        			alertMsg(title, msg);
        			return;
    			}
    		}
    	}
    }
    
    /* 선택한 수신추/참조처 정보를 삭제한다 */    
    function delRcptRfnc(selRcptRfnc) {
    	var row = $('#' + selRcptRfnc).datagrid('getSelected');
    	if (row) {
    		var index = $('#' + selRcptRfnc).datagrid('getRowIndex', row);
    		$('#' + selRcptRfnc).datagrid('deleteRow', index);
    	} else {
    		var title = "<spring:message code="resc.label.warning"/>";
			var msg = "<spring:message code="resc.msg.selectDelItem"/>";
			alertMsg(title, msg);
			return false;    		
    	}
    }
    
    /**
     * 이미 선택한 수신처/참조처가 있는 지 체크.
     */
    function checkRcptRfnc(value) {
    	var dlist = $('#selRcpt');
    	var rows = dlist.datalist('getRows');
    	var chk = true;
    	
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].value == value) {
				chk = false;
				break;
			}
		}
		
		dlist = $('#selRfnc');
		rows = dlist.datalist('getRows');
		for (var i = 0; i < rows.length; i++) {
			if (rows[i].value == value) {
				chk = false;
				break;
			}
		}
		
		if (!chk) {
			var title = "<spring:message code="resc.label.warning"/>";
			var msg = "<spring:message code="resc.msg.selectedApprover"/>";
			alertMsg(title, msg);
		}
		
		return chk;
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
							<option value="O"><fmt:message key="resc.label.ogan"/></option> <!-- 조직명칭으로 검색 -->
							<!--<option value="G"><fmt:message key="resc.label.group"/></option>-->
						</select>
					</td>
					<td>	
						<input class="easyui-textbox" id="searchWord" name="searchWord" style="width:100%;"></input>
					</td>
					<td>
						<a href="#" id="btnSearchWord" class="easyui-linkbutton btnSearch" style="width:100px"><spring:message code="resc.btn.search"/></a>
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
					<table id="searchList" class="easyui-datagrid" style="width:100%;height:100%;" data-options="fitColumns:true,singleSelect:true">
					    <thead>
					        <tr>
					            <th data-options="field:'empNo',width:0,hidden:true"><spring:message code="resc.label.empNo"/></th>
					            <th data-options="field:'empNameKor',width:'40%',align:'left'"><spring:message code="resc.label.empName"/></th>
					            <th data-options="field:'dutyDesc',width:'30%',align:'left'"><spring:message code="resc.label.dutyDesc"/></th>
					            <th data-options="field:'pstnDesc',width:'30%',align:'left'"><spring:message code="resc.label.position"/></th>
					        </tr>
					    </thead>
					</table>
				</div>
			</div>
		</div>
		<div data-options="region:'east',split:true,title:'<spring:message code="resc.btn.rcptRfncSelect"/>',collapsible:false" style="width:300px;">
			<br/>							
			<div class="easyui-panel" title="<spring:message code="resc.label.rcpt"/>" style="width:100%;height:220px">
				<div>
					<a href="javascript:addRcpt('selRcpt');" class="easyui-linkbutton" data-options="iconCls:'icon-add'"><spring:message code="resc.btn.add"/></a>
    				<a href="javascript:delRcptRfnc('selRcpt');" class="easyui-linkbutton" data-options="iconCls:'icon-remove'"><spring:message code="resc.btn.delete"/></a>        					
    			</div>          				
    			<ul class="easyui-datalist" id='selRcpt' style="width:100%;height:165px"></ul> 				        				
			</div>
			<br/>
			<div class="easyui-panel" title="<spring:message code="resc.label.rfnc"/>" style="width:100%;height:220px">
				<div>
					<a href="javascript:addRfnc('selRfnc');" class="easyui-linkbutton" data-options="iconCls:'icon-add'"><spring:message code="resc.btn.add"/></a>
    				<a href="javascript:delRcptRfnc('selRfnc');" class="easyui-linkbutton" data-options="iconCls:'icon-remove'"><spring:message code="resc.btn.delete"/></a>        					
    			</div>          				
    			<ul class="easyui-datalist" id='selRfnc' style="width:100%;height:165px"></ul> 				        				
			</div>				
		</div>		
	</div>
</body>
</html>    
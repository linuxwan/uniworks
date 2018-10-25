<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="resc.label.cprtApprSelect"></spring:message></title>
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
    $(function(){    	
    	getAllOganList('<c:out value="${contextPath}"/>', 'oganTree', '${userSession.coId}');
    	
    	$('#oganTree').tree({
    		onClick: function(node) {    			
    			getMembersListOftheOganCharge(node.id, node.oganLev)
    		}
    	});
    	
    	$('#btnConfirm').bind('click', function(){
    		var apprLevel = ${apprLevel};
    		var strData = "";
    		var selCprtn = "";
    		var selCprtnDesc = "";
    		$('#hd_selCprtn', opener.document).val('');
    		$('#hd_selCprtnDesc', opener.document).val('');
    		window.opener.$("#cprtnList").datagrid('loadData', []);	//Opener쪽의 Grid 초기화
    		
    		for (var i = 1; i < apprLevel; i++) {
    			var dlist = $("#cprtLine_" + i);
            	var rows = dlist.datalist('getRows');        	        	
            	
            	for (var j = 0; j < rows.length; j++) {            		            		
            		window.opener.$("#cprtnList").datagrid('appendRow', {
            			apprDgr: i,
            			deptCode: rows[j].value,
            			deptDesc: rows[j].text,
            			apprStus: '',
            			comment: ''
					});
            		
            		if (i == 0 && j == 0) {
            			selCprtn = i + ":" + rows[j].value;
            			selCprtnDesc = i + ":" + rows[j].text;
            		} else {
            			selCprtn = selCprtn + "," + i + ":" + rows[j].value;
            			selCprtnDesc = selCprtnDesc + "," + i + ":" + rows[j].text;
            		}
        		}            	
    		}
    		    		
    		$('#hd_selCprtn', opener.document).val(selCprtn);
    		$('#hd_selCprtnDesc', opener.document).val(selCprtnDesc);
    		window.close();
    	});
    			
		$('#btnSearchWord').bind('click', function(){
			var searchWord = $('#searchWord').textbox('getValue');
			
			if (seachWordCheck()) {
				$('#tabs').tabs('select', 1);
				
				getSearchEmpName(searchWord);
			}
		});  			    	   	     	    	  
    	
    	getApprLineInfo();
    	getApprCprtnInfo();
    });        
    
    /* 조직 선택 시 BaseOganLev에 따라서 보직자만 또는 모든 조직원을 목록으로 가져온다. 
     * 조직 책임자가 있는지 확인해서 없을 경우 메시지 출력
    */
	function getMembersListOftheOganCharge(oganCode, oganLev) {
		var url = "<c:out value="${contextPath}"/>/rest/hr/getEmpChrgListForOganTree/oganLev/" + oganLev + "/oganCode/" + oganCode;
		var check = false;
			
		$.ajax({
			type: 'get',
			url: url,
			async: false,
			dataType: 'json',
			success: function(data) {
				$('#empList').datagrid('loadData', data);
			}
		});
		
		var rows = $('#empList').datagrid('getRows');
				
		for (var i = 0; i < rows.length; i++) {
			var pstnDesc = rows[i].pstnDesc;
			if (pstnDesc != null && pstnDesc != "" && pstnDesc.length > 1) {
				check = true;
				break;
			}
		}
		
		if (!check || rows.length < 1) {
			var title = "<spring:message code="resc.label.confirm"/>";
    		var msg = "<spring:message code="resc.msg.noOganChrg"/>";
    		alertMsg(title, msg);
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
    
    /* 좌측의 조직도에서 결재자를 선택 후 추가. */
    function addCprtLine(apprLineNo, no) {
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
   			if (row.pstnDesc != null && row.pstnDesc != "" && row.pstnDesc.length > 1) {	    		    			
    			//기본조직 레벨에 맞는 조직명칭을 가져온다.	    			
    			var text = row.deptDesc + "<spring:message code="resc.label.chief"/>";    		
    			var value = row.deptCode;    		
        		var dlist = $('#' + apprLineNo);
    			var cprt = {value: value, text: text}; 		    			
    			
    			if (checkCprtLine(row.deptCode)) {
    				dlist.datalist('appendRow', cprt);
    			}    				    		
   			} else {
   	    		var title = "<spring:message code="resc.label.confirm"/>";
   	    		var msg = "<spring:message code="resc.msg.selectChief"/>";
   	    		alertMsg(title, msg);
   	    	}
   		} else {
   			var title = "<spring:message code="resc.label.confirm"/>";
    		var msg = "<spring:message code="resc.msg.selectApprover"/>";
    		alertMsg(title, msg);
   		}
    	
    }
    
    /* 선택한 협조 결재자 삭제. */
    function delCprtLine(cprtLineNo, no) {
    	var row = $('#' + cprtLineNo).datagrid('getSelected');
    	if (row) {
    		var index = $('#' + cprtLineNo).datagrid('getRowIndex', row);
    		$('#' + cprtLineNo).datagrid('deleteRow', index);    		    		
    	} else {
    		var title = "<spring:message code="resc.label.warning"/>";
			var msg = "<spring:message code="resc.msg.selectDelItem"/>";
			alertMsg(title, msg);
			return false;    		
    	}
    }
    
    /* 이미 등록된 결재자가 있는지 체크 */
    function checkCprtLine(value) {
    	var apprLevel = ${apprLevel};
    	
    	for (var i = 1; i < apprLevel; i++) {
    		var chk = true;
    		var dlist = $("#cprtLine_" + i);
        	var rows = dlist.datalist('getRows');        	        	
        	
        	for (var j = 0; j < rows.length; j++) {
        		if (rows[j].value.indexOf(value) > -1) {
    				chk = false;
    				break;
    			}
    		}
        	
    		if (!chk) {
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
    
    /* Opener의 결재자 레벨과 결재자 정보를 가져와서 보여준다. */
    function getApprLineInfo() {
    	var apprLevel = ${apprLevel};    	
    	
    	for (var i = 1; i <= apprLevel; i++) {
    		var name = $('#apprLineDesc_' + i, opener.document).val().replace("<br/>", "");
    		var title = '';
    		    		
    		if (name == null || name.trim() == '') {
    			title = i + '<spring:message code="resc.label.orderApprover"/>';    			
				$("#addCprtLine_" + i).hide();
	    		$("#delCprtLine_" + i).hide();	    			    			
    		} else {
    			title = i + '<spring:message code="resc.label.orderApprover"/>' + "-" + $('#apprLineDesc_' + i, opener.document).val().replace("<br/>", "");
    			lineApprCheck = true;
    			if (i == apprLevel) {
    				$("#addCprtLine_" + i).hide();
        			$("#delCprtLine_" + i).hide();
        			var preName = $('#apprLineDesc_' + (i - 1), opener.document).val().replace("<br/>", "");
        			if (i > 1) {
        				if (preName.trim() != '') {
    						$("#addCprtLine_" + (i-1)).show();
        					$("#delCprtLine_" + (i-1)).show();
        				} else {
        					$("#addCprtLine_" + (i-1)).hide();
        					$("#delCprtLine_" + (i-1)).hide();
        				}
        			}
    			}
    		}
    		
	    	$('#lineAppr'+i).panel({'title': title});
    	}    	  	    
    }
    
    /* Opener의 협조결재자 정보를 가져와서 협조결재자를 보여준다. */
    function getApprCprtnInfo() {
    	var selCprtn = $('#hd_selCprtn', opener.document).val();
		var selCprtnDesc = $('#hd_selCprtnDesc', opener.document).val();
		var apprLevel = ${apprLevel};
		var selCprtnArray = selCprtn.split(",");
		var selCprtnDescArray = selCprtnDesc.split(",");
		
		for (var i = 1; i < apprLevel; i++) {
			for (var j = 0; j < selCprtnArray.length; j++) {
				var selCprtnInfoArr = selCprtnArray[j].split(":");
				var selCprtnDescInfoArr = selCprtnDescArray[j].split(":");
				
				if (i == selCprtnInfoArr[0]) {
					var dlist = $('#cprtLine_' + i);
    				var cprt = {value: selCprtnInfoArr[1], text: selCprtnDescInfoArr[1]}; 		    			
    			
    				dlist.datalist('appendRow', cprt);	    				
				}
			}	
		}
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
						            <th data-options="field:'empNameKor',width:'20%',align:'left'"><spring:message code="resc.label.empName"/></th>
						            <th data-options="field:'dutyDesc',width:'25%',align:'left'"><spring:message code="resc.label.dutyDesc"/></th>
						            <th data-options="field:'pstnDesc',width:'25%',align:'left'"><spring:message code="resc.label.position"/></th>
						            <th data-options="field:'deptDesc',width:'30%'"><spring:message code="resc.label.deptDesc"/></th>
						            <th data-options="field:'deptCode',width:100,hidden:true"><spring:message code="resc.label.deptCode"/></th>
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
					            <th data-options="field:'empNameKor',width:'20%',align:'left'"><spring:message code="resc.label.empName"/></th>
					            <th data-options="field:'dutyDesc',width:'25%',align:'left'"><spring:message code="resc.label.dutyDesc"/></th>
					            <th data-options="field:'pstnDesc',width:'25%',align:'left'"><spring:message code="resc.label.position"/></th>
					            <th data-options="field:'deptDesc',width:'30%'"><spring:message code="resc.label.deptDesc"/></th>
					            <th data-options="field:'deptCode',width:100,hidden:true"><spring:message code="resc.label.deptCode"/></th>
					        </tr>
					    </thead>
					</table>
				</div>
			</div>
		</div>
		<div data-options="region:'east',split:true,title:'<spring:message code="resc.label.approver"/>',collapsible:false" style="width:300px;">
			<div style="width:100%;height:5px"></div>
			<c:forEach items="${cprtLineList}" var="cprtLine" varStatus="st">
				<div class="easyui-panel" title="${st.count}<spring:message code="resc.label.orderApprover"/>" id="lineAppr${st.count}" style="width:100%;">
					<div>
						<a href="javascript:addCprtLine('cprtLine_${st.count}',${st.count});" id="addCprtLine_${st.count}" class="easyui-linkbutton" data-options="iconCls:'icon-add'"><spring:message code="resc.btn.add"/></a>
       					<a href="javascript:delCprtLine('cprtLine_${st.count}',${st.count});" id="delCprtLine_${st.count}" class="easyui-linkbutton" data-options="iconCls:'icon-remove'"><spring:message code="resc.btn.delete"/></a>        					
       				</div>          				
       				<input class="easyui-datalist" name='cprtLine_${st.count}' id='cprtLine_${st.count}' style="width:100%;height:55px" readonly="readonly"> 				        				
				</div>				
				<div style="width:100%;height:5px"></div>
			</c:forEach>
		</div>		
	</div>
</body>
</html>    
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><spring:message code="resc.label.attchFile"></spring:message></title>
    <link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/themes/icon.css">    
    <link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/uniworks.css">
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/jquery.min.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/locale/easyui-lang-${userSession.language}.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/common.js"></script>    
    <script type="text/javascript">
    $(document).ready(function(){
    	loadInitial();
    	
    });
    
  	//초기 로딩 시 기존 첨부문서 목록을 가져와서 뿌린다.
	function loadInitial() {
		var checkAttach = $('#attachList').val();
		
		if (checkAttach == '' || checkAttach.length < 1) {
			var attachList = $('#attachList', opener.document).val();
			addAttachFileList(attachList);
			$('#attachList').val($('#attachList', opener.document).val());
		} else {
			var attachList = $('#attachList').val();
			addAttachFileList(attachList);
		}
		
		openerAttachFileListAdd();
		$('#attachList', opener.document).val($('#attachList').val());
		if (checkAttach != '' && checkAttach.length > 1) {
			$('#atchIndc', opener.document).val('Y');
		} else {
			$('#atchIndc', opener.document).val('N');
		}
  	}
  	
  	//첨부파일 추가 버튼 클릭 시 
  	function addFile() {
  		var fileName = $('#attchFile').filebox('getText');
  		//파일을 선택하지 않았을 경우, 경고 메시지
  		if (fileName == null || fileName.trim() == "") {
  			var title = "<spring:message code="resc.label.warning"/>";
    		var msg = "<spring:message code="resc.msg.selectFile"/>";
    		alertMsg(title, msg);
    		return;
  		} else {
  			$('#mode').val('add');
			$('#frmAttchUpload').submit();			
  		}
  	}
  	
  	//파일 추가 버튼 클릭 시 파일을 Grid에 추가.
  	function addAttachFileList(attachList) {
  		var arrFileList = new Array();
		var arrFileData = new Array();
		var fileId = "";
		var attchFileName = "";
		var fileSize = 0;
		var tempIndc = "";

		if (attachList != "") {
			if (attachList.indexOf("|") > -1) {
				arrFileList = attachList.split("|");
				
				for (var i = 0; i < arrFileList.length; i++) {
					arrFileData = arrFileList[i].split("^");
					fileId = arrFileData[0];
					attchFileName = arrFileData[1];
					fileSize = arrFileData[2];
					tempIndc = arrFileData[3];
					
					dataGridAppendRow(fileId, attchFileName, fileSize, tempIndc);
				}
			} else {
				arrFileData = attachList.split("^");
				fileId = arrFileData[0];
				attchFileName = arrFileData[1];
				fileSize = arrFileData[2];
				tempIndc = arrFileData[3];
				
				dataGridAppendRow(fileId, attchFileName, fileSize, tempIndc);
			}			
		} 
  	}
  	
  	//파일 삭제 버튼 클릭 시 파일을 Grid에서 삭제
  	function deleteFile() {
  		//datagrid에서 파일 삭제.
  		var row = $('#fileList').datagrid('getSelected'); 
  		if (row == null) {
  			var title = "<spring:message code="resc.label.warning"/>";
    		var msg = "<spring:message code="resc.msg.selectDelFile"/>";
    		alertMsg(title, msg);
    		return;
  		}
  		
  		$('#fileId').val(row.fileId);
  		
  		var index = $('#fileList').datagrid('getRowIndex', row);
  		$('#fileList').datagrid('deleteRow', index);
  		
  		var attachList = '';
  		var ids = $("#fileList").datagrid('getRows');
  		for (var i = 0; i < ids.length; i++) {
  			if (i > 0) attachList += '|';
  			attachList += ids[i].fileId + '^' + ids[i].attchFileName + '^' + ids[i].fileSize + '^' + ids[i].tempIndc;
  		}
  		$('#attachList').val(attachList);
  		console.log("attachList 1 : " + $('#attachList').val());
		$('#mode').val('del');
		openerAttachFileListAdd();
		$('#frmAttchUpload').submit();
  	}
  	
  	//datagrid에 Row를 추가하는 함수
  	function dataGridAppendRow(fileId, attchFileName, fileSize, tempIndc) {
  		$('#fileList').datagrid('appendRow', {
			fileId: fileId,
			attchFileName: attchFileName,
			fileSize: fileSize,
			tempIndc: tempIndc
		});
  	}
  	
  	//확인 버튼 클릭 시 작성 화면의 첨부파일 목록 Grid 정보를 변경
  	function openerAttachFileListAdd() {
  		var arrFileList = new Array();
		var arrFileData = new Array();
		var attachList = $('#attachList').val();

		if (attachList != "" && attachList.length > 1) {
			window.opener.$("#fileUploadList").datagrid('loadData', []);			
			
			if (attachList.indexOf("|") > -1) {
				arrFileList = attachList.split("|");
				console.log("arrFileList 1:" + arrFileList);
				for (var i = 0; i < arrFileList.length; i++) {
					arrFileData = arrFileList[i].split("^");
					window.opener.$("#fileUploadList").datagrid('appendRow', {
						fileId: arrFileData[0],
						attchFileName: arrFileData[1],
						fileSize: arrFileData[2],
						tempIndc: arrFileData[3]
					});													
				}
			} else {				
				arrFileData = attachList.split("^");
				window.opener.$("#fileUploadList").datagrid('appendRow', {
					fileId: arrFileData[0],
					attchFileName: arrFileData[1],
					fileSize: arrFileData[2],
					tempIndc: arrFileData[3]
				});				
			}			
		} else {
			window.opener.$("#fileUploadList").datagrid('loadData', []);
		}
		
		$('#attachList', opener.document).val($('#attachList').val());
  	}
  	
  	//확인 버튼 클릭
  	function confirm() {  
  		openerAttachFileListAdd();
		window.close();
  	}
    </script>
</head>
<body>
	<div class="easyui-panel" title="<spring:message code="resc.label.attchFile"/>" style="width:360px;height:360px;padding:10px;">
		<form:form id="frmAttchUpload" modelAttribute="nw118m" method="post" enctype="multipart/form-data" action="/uniworks/upload/upload_form?${_csrf.parameterName}=${_csrf.token}">
			<input id="mode" name="mode" type="hidden"/>
			<input id="cntnId" name="cntnId" type="hidden"/>
            <input id="dcmtRgsrNo" name="dcmtRgsrNo" type="hidden"/>
            <input id="attachList" name="attachList" type="hidden" value="${attachList}"/>
            <input id="fileId" name="fileId" type="hidden"/>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			<table>
				<tr>
					<td><spring:message code="resc.label.attchFile"/>:</td>
					<td><input id="attchFile" name="fileData" class="f1 easyui-filebox" data-options="prompt:'Choose a file...'" style="width:275px"></input></td>
				</tr>
				<tr>
					<td colspan="2">
						<div class="floatRight2">
							<a href="javascript:addFile();" class="easyui-linkbutton" data-options="iconCls:'icon-add'"><spring:message code="resc.btn.add"/></a>
	        				<a href="javascript:deleteFile();" class="easyui-linkbutton" data-options="iconCls:'icon-remove'"><spring:message code="resc.btn.delete"/></a>
        				</div>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<table id="fileList" class="easyui-datagrid" style="width:100%;" data-options="fitColumns:true,singleSelect:true">
						    <thead>
						        <tr>
						            <th data-options="field:'fileId',width:1,hidden:true"><spring:message code="resc.label.fileId"/></th>
						            <th data-options="field:'attchFileName',width:'65%',align:'left'"><spring:message code="resc.label.fileName"/></th>
						            <th data-options="field:'fileSize',width:'35%',align:'right',formatter:formatterFileSize"><spring:message code="resc.label.fileSize"/></th>
						            <th data-options="field:'tempIndc',width:1,hidden:true"><spring:message code="resc.label.attchTempIndc"/></th>
						        </tr>
						    </thead>
						</table>
					</td>
				</tr>
			</table>			
		</form:form>
	</div>
	<div class="floatRight2">
		<a href="javascript:confirm();" class="easyui-linkbutton" style="width:70px;"><spring:message code="resc.btn.confirm"/></a>
      	<a href="javascript:window.close();" class="easyui-linkbutton" style="width:70px;"><spring:message code="resc.btn.cancel"/></a>
    </div>
</body>
</html>
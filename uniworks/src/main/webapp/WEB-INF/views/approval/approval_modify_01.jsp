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
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/tinymce/tinymce.min.js"></script>    
	<script type="text/javascript" src="<c:out value="${contextPath}"/>/tinymce/tinymce_init.js"></script>
	<script type="text/javascript" src="<c:out value="${contextPath}"/>/plugin/jquery.popupwindow.js"></script>
    
    <script type="text/javascript">
    $(function(){
    	//취소버튼 클릭 시 결재양식함으로 이동
        $(".btnCancel").click(function(evt) {
        	$.messager.confirm("<spring:message code="resc.label.confirm"/>", "<spring:message code="resc.msg.docWriteCancel"/>", function(r) {
        		if (r) {       
        			//opener 페이지 reload
        			var popupIndc = $("#popupIndc").val();
        			if (popupIndc == "Y") {	//Popup 창일 경우,
            			window.opener.location.reload();
        				window.close();
        			} else {
        				goApprForm();
        			}
        		}
        	});        	
        });
    	
    	//저장만 버튼 클릭 시
    	$(".btnSave").click(function(evt) {
    		//$("#apprStus").val("0");
    		var ids = $("#fileUploadList").datagrid('getRows');
    		checkAttachFiles();
    		$('#saveType').val('S');
    		var title = "<spring:message code="resc.label.warning"/>";
    		var msg = "<spring:message code="resc.msg.contents"/>";
    		$('#apprWriteForm').attr("action", '<c:out value="${contextPath}"/>/approval/approval_modify_01');
    		if (isEmptyTinyMCE('content', title, msg)) {
    			$('#apprWriteForm').submit();
    		}    		        		
    	});
    	
    	//저장 후 닫기 버튼 클릭 시
    	$(".btnSaveClose").click(function(evt) {
    		//$("#apprStus").val("0");
    		checkAttachFiles();
    		$('#saveType').val('C');
    		var title = "<spring:message code="resc.label.warning"/>";
    		var msg = "<spring:message code="resc.msg.contents"/>";
    		$('#apprWriteForm').attr("action", '<c:out value="${contextPath}"/>/approval/approval_modify_01');
    		if (isEmptyTinyMCE('content', title, msg)) {
    			$('#apprWriteForm').submit();
    		} 
    	});
    	
    	//승인 요청 버튼 클릭
        $(".btnApprReq").click(function(evt){
			if (!validationLineApprCheck()) return false;	//라인결재자 선택이 되지 않으면 승인요청을 하지 못함
			if (!validationRcptCheck()) return false;	//수신처 선택이 되지 않으면 승인요청을 하지 못함
        	$.messager.confirm("<spring:message code="resc.label.confirm"/>", "<spring:message code="resc.msg.apprReq"/>", function(r) {
        		if (r) {
        			$("#apprStus").val("7");
        			$("#apprWriteForm").attr('method', 'post');
					$("#apprWriteForm").attr('action', '<c:out value="${contextPath}"/>/approval/modifying_approved_request_01');
					$("#apprWriteForm").submit();
        		}
        	});
        });
    	
    	//승인 버튼 클릭
    	$(".btnAppr").click(function(evt){
    		if (!validationLineApprCheck()) return false;	//라인결재자 선택이 되지 않으면 승인요청을 하지 못함
			if (!validationRcptCheck()) return false;	//수신처 선택이 되지 않으면 승인요청을 하지 못함
    		$('#dlgAppr').dialog('open');    		
    	});
    	
    	//반려 버튼 클릭
    	$(".btnApproveReject").click(function(evt){
    		if (!validationLineApprCheck()) return false;	//라인결재자 선택이 되지 않으면 승인요청을 하지 못함
			if (!validationRcptCheck()) return false;	//수신처 선택이 되지 않으면 승인요청을 하지 못함
    		$('#dlgReject').dialog('open'); 
    	});
            	   	               
        var baseServiceLife = $("#serviceLife").combobox('getValue');
        getServiceLifeDate(baseServiceLife, 'divClsDate', 'prsvTerm');
        
        $("#serviceLife").combobox({
    		onChange:function(newValue,oldValue) {
    			getServiceLifeDate(newValue,'divClsDate', 'prsvTerm');
    		}	
    	});        	               
    });      
    
  	//결재 승인 버튼 클릭 시 호출되는 함수
    function approved() {
    	$('#comment').val($('#apprComment').val());
    	$("#apprStus").val("7");
		$("#apprWriteForm").attr('method', 'post');
		$("#apprWriteForm").attr('action', '<c:out value="${contextPath}"/>/approval/modifying_approved_request_02');
		$("#apprWriteForm").submit();
    }
 	
 	//결재 반려 버튼 클릭 시 호출되는 함수
    function reject() {
    	$('#comment').val($('#rejectComment').val());
    	$("#apprStus").val("5");
		$("#apprWriteForm").attr('method', 'post');
		$("#apprWriteForm").attr('action', '<c:out value="${contextPath}"/>/approval/modifying_approved_request_02');
		$("#apprWriteForm").submit();
 	}
 	
	//승인 요청 전  필수 입력 사항에 대한 validation 체크를 한다.
	//라인결재자 - 최종 승인자 선택여부 확인
	function validationLineApprCheck() {
		//최종 승인자 입력 여부 확인
		var level = $("#apprLevel").val();
		var maxApprLine = $("#apprLine_" + level).val();
		if ($.trim(maxApprLine) == null || $.trim(maxApprLine).length < 1) {
			var title = "<spring:message code="resc.label.warning"/>";
			var msg = "<spring:message code="resc.msg.selectApprover"/>";
			alertMsg(title, msg);
			return false;
		} else {
			return true;
		}
	}
	
	//수신처 선택 여부를 체크
	function validationRcptCheck() {
		if($("#hd_selRcpt").length) {
			//선택된 수신처가 있는지 확인.
			var selRcptChk = $("#hd_selRcpt").val();
			if ($.trim(selRcptChk) == null || $.trim(selRcptChk).length < 1) {
				var title = "<spring:message code="resc.label.warning"/>";
				var msg = "<spring:message code="resc.msg.selRcpt"/>";
				alertMsg(title, msg);
				return false;
			} else {
				return true;
			}
		}
	} 
	
	//결재양식함으로 이동.
    function goApprForm() {
    	var url = '<c:out value="${contextPath}"/>/approval/approval_form';
    	parent.$('#frmMain').attr('src', url);        	
    }
    </script>
</head>	
<body>	
	<div>
		<h2><c:out value="${apprMst.apprDesc}"/></h2>
	</div>
	
	<div>
		<a href="#" class="easyui-linkbutton btnSave" style="width:60px"><spring:message code="resc.btn.save"/></a>
		<a href="#" class="easyui-linkbutton btnSaveClose" style="width:100px"><spring:message code="resc.btn.saveClose"/></a>
		<c:if test="${(doc.crntApprDgr == 0 && doc.apprStus == 0 && doc.authEmpNo == userSession.userId) && approved == true}">
		<a href="#" class="easyui-linkbutton btnApprReq" style="width:100px"><spring:message code="resc.btn.apprReq"/></a>		
		</c:if>
		<c:if test="${(doc.crntApprDgr != 0 && doc.apprStus != 0) && approved == true}">
		<a href="#" class="easyui-linkbutton btnAppr" style="width:60px"><spring:message code="resc.btn.approved"/></a>
		<a href="#" class="easyui-linkbutton btnApproveReject" style="width:60px"><spring:message code="resc.btn.apprRej"/></a>
		</c:if>
		<a href="#" class="easyui-linkbutton btnCancel" style="width:60px"><spring:message code="resc.btn.cancel"/></a>
	</div>
	
	<form:form commandName="approvalDocForm01" id="apprWriteForm" method="post" action="">
	<hr class="thin bg-grayLighter">
	<jsp:include page="/WEB-INF/views/include/common/hidden_type_01.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/include/common/comment_dialog_02.jsp"></jsp:include>
	<input type="hidden" id="lineApprLevel" name="lineApprLevel" value='<c:out value="${apprLevel}" />'/>
	<input id="attachList" name="attachList" type="hidden" value="${strAttachList}"/>
	<input id="apprStus" name="apprStus" type="hidden" value="${doc.apprStus}"/>
	<input id="atchIndc" name="atchIndc" type="hidden" value="${atchIndc}"/>
	<input id="saveType" name="saveType" type="hidden" value=""/>
	<input id="popupIndc" name="popupIndc" type="hidden" value="${popupIndc}"/>
	<input id="filePathType" name="filePathType" type="hidden" value="A" />
	<input id="maxApprDgr" name="maxApprDgr" type="hidden" value="${doc.maxApprDgr}" />
	<input id="crntApprDgr" name="crntApprDgr" type="hidden" value="${doc.crntApprDgr}" />
	<input id="steadApprIndc" name="steadApprIndc" type="hidden" value="N" />
	<input id="comment" name="comment" type="hidden" value=""/>
	
	<div style="width:100%;height:100%;">	
		<table style="width:100%;">
			<tr>
				<td style="width:40%;">
					<!-- 작성자 정보 -->
					<div class="floatLeft2">
					<jsp:include page="/WEB-INF/views/include/approval/auth_info_write_01.jsp"></jsp:include>
					</div>
				</td>
			
				<td style="width:60%;">
					<!-- Line 결재자 정보 -->
					<div class="floatLeft2">
					<c:choose>
					<c:when test="${(doc.crntApprDgr == 0 && doc.apprStus == 0 && doc.authEmpNo == userSession.userId)}">
					<jsp:include page="/WEB-INF/views/include/approval/line_approver_edit_01.jsp"></jsp:include>
					</c:when>
					<c:otherwise>
					<jsp:include page="/WEB-INF/views/include/approval/line_approver_01.jsp"></jsp:include>
					</c:otherwise>
					</c:choose>
					</div>
				</td>			
			</tr>
		</table>
	</div>
	
	
	<!-- 수신처/참조처  -->
	<div style="width:100%;height:100%;">
		<table style="width:100%;">
			<tr>
				<td>
				<jsp:include page="/WEB-INF/views/include/approval/rcpt_rfnc_cprtn_edit_01.jsp"></jsp:include>
				</td>
			</tr>
		</table>
	</div>
				
	<!-- 문서 Body -->
	<div style="width:100%;height:100%;">
		<jsp:include page="/WEB-INF/views/include/approval/appr_doc_body_edit_01.jsp"></jsp:include>
	</div>
		
	<!-- 첨부파일 -->
	<table>
		<tbody>
			<tr>
				<td>
					<div class="floatLeft2">
					<jsp:include page="/WEB-INF/views/common/attch_file_add_01.jsp"></jsp:include>
					</div>
				</td>
			</tr>
		</tbody>
	</table>					
	<hr class="thin bg-grayLighter">
	</form:form>
		
	<div>
		<a href="#" class="easyui-linkbutton btnSave" style="width:60px"><spring:message code="resc.btn.save"/></a>
		<a href="#" class="easyui-linkbutton btnSaveClose" style="width:100px"><spring:message code="resc.btn.saveClose"/></a>
		<c:if test="${(doc.crntApprDgr == 0 && doc.apprStus == 0 && doc.authEmpNo == userSession.userId) && approved == true}">		
		<a href="#" class="easyui-linkbutton btnApprReq" style="width:100px"><spring:message code="resc.btn.apprReq"/></a>		
		</c:if>
		<c:if test="${(doc.crntApprDgr != 0 && doc.apprStus != 0) && approved == true}">
		<a href="#" class="easyui-linkbutton btnAppr" style="width:60px"><spring:message code="resc.btn.approved"/></a>
		<a href="#" class="easyui-linkbutton btnApproveReject" style="width:60px"><spring:message code="resc.btn.apprRej"/></a>
		</c:if>
		<a href="#" class="easyui-linkbutton btnCancel" style="width:60px"><spring:message code="resc.btn.cancel"/></a>
	</div>		
</body>
</html>
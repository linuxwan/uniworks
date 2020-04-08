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
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/plugin/jquery.popupwindow.js"></script>
    
    <script type="text/javascript">
    $(function(){
    	//삭제 버튼 클릭
        $(".btnDelete").click(function(evt) {
        	$.messager.confirm("<spring:message code="resc.label.confirm"/>", "<spring:message code="resc.msg.docDelete"/>", function(r) {
        		if (r) {        			
        			deleteApprDoc();
        		}
        	});        	
        });
        
        //수정 버튼 클릭
        $(".btnModify").click(function(evt){
        	var url = "<c:out value="${contextPath}"/>/approval/modify_approval_doc_01?";
        	var param = "dcmtRgsrNo=" + $("#dcmtRgsrNo").val() + "&cntnId=" + $("#cntnId").val() + "&popupIndc=Y";
    		var popupWin = $.popupWindow(url + param, { createNew: false, location: false, height: 900, width: 950, name: 'modifyApprDoc', scrollbars: true });
        });
        
        //승인 요청 버튼 클릭
        $(".btnApprReq").click(function(evt){
			if (!validationLineApprCheck()) return false;	//라인결재자 선택이 되지 않으면 승인요청을 하지 못함
			if (!validationRcptCheck()) return false;	//수신처 선택이 되지 않으면 승인요청을 하지 못함
			
			$.messager.confirm("<spring:message code="resc.label.confirm"/>", "<spring:message code="resc.msg.apprReq"/>", function(r) {
        		if (r) {
        			$("#apprStus").val("7");
        			$("#detailForm").attr('method', 'post');
					$("#detailForm").attr('action', '<c:out value="${contextPath}"/>/approval/approved_request_01');
					$("#detailForm").submit();
        		}
        	});			
        });
        
        //승인 버튼 클릭
        $(".btnAppr").click(function(evt){
        	$('#dlgAppr').dialog('open');
        });                
        
        //반려 버튼 클릭
        $(".btnApproveReject").click(function(evt){
        	$('#dlgReject').dialog('open');        	
        });      
        
        //결재comment보기
        $(".btnViewCmmtsApproval").click(function(evt){
        	$('#commentsList').dialog('open');
        });
        
        //협조결재 승인 버튼 클릭
        $(".btnCprtnApproved").click(function(evt){
        	$('#dlgCprtnAppr').dialog('open');
        });
        
        //협조결재 반려 버튼 클릭
        $(".btnCprtnReject").click(function(evt){
        	$('#dlgCprtnReject').dialog('open');
        });
        
        //결재코멘트를 가져오기 위한 URL설정
        var strUrl = '<c:out value="${contextPath}"/>/rest/approval/line_appr_comments_01';
        strUrl += "?cntnId=" + $('#cntnId').val() + "&dcmtRgsrNo=" + $('#dcmtRgsrNo').val();
        
        $('#apprComments').datagrid({
        	url: strUrl,
        	method: 'get'
        });        
    });    
    
 	//결재 승인
    function approved() {
    	$('#comment').val($('#apprComment').val());
    	$("#apprStus").val("7");
		$("#detailForm").attr('method', 'post');
		$("#detailForm").attr('action', '<c:out value="${contextPath}"/>/approval/approved_request_01');
		$("#detailForm").submit();
    }
 	
 	//결재 반려
    function reject() {
    	$('#comment').val($('#rejectComment').val());
    	$("#apprStus").val("5");
		$("#detailForm").attr('method', 'post');
		$("#detailForm").attr('action', '<c:out value="${contextPath}"/>/approval/approved_request_01');
		$("#detailForm").submit();
 	}
 	
 	//협조결재 승인
 	function cprtnApproved() {
 		$('#comment').val($('#cprtnApprComment').val());
 		$("#apprStus").val("7");
 		$("#detailForm").attr('method', 'post');
 		$("#detailForm").attr('action', '<c:out value="${contextPath}"/>/approval/cprtn_approved_request_01');
		$("#detailForm").submit();
 	}
 	
 	//협조결재 반려
 	function cprtnReject() {
 		$('#comment').val($('#rejectCprtnApprComment').val());
 		$("#apprStus").val("5");
 		$("#detailForm").attr('method', 'post');
 		$("#detailForm").attr('action', '<c:out value="${contextPath}"/>/approval/cprtn_approved_request_01');
		$("#detailForm").submit();
 	}
 	
    //문서 삭제  Ajax 호출
    function deleteApprDoc() {
    	var cntnId = $("#cntnId").val();
    	var dcmtRgsrNo = $("#dcmtRgsrNo").val();
    	var apprMstId = $("#apprMstId").val();        	
    	
    	$.ajax({
    		url: '<c:out value="${contextPath}"/>'	+ '/rest/approval/remove_approval_doc_01',
    		type: 'get',
    		async:false,
    	    cache:false,
    		data: {cntnId:cntnId, dcmtRgsrNo:dcmtRgsrNo, apprMstId:apprMstId},
    		success: function(r) {
    			$.messager.alert('<spring:message code="resc.btn.delete"/>', r);
    			parent.$("#listTabsLayer").tabs('close', dcmtRgsrNo);	    			
    		},
    		error: function(xhr, status, error) {
    			$.messager.alert('<spring:message code="resc.label.error"/>', status + "\r\n" + error);    			
    		}
    	});
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
		//선택된 수신처가 있는지 확인.
		var rcptIndc = '${apprMst.rcptIndc}';
		var selRcptChk = $("#hd_selRcpt").val();
		if (rcptIndc == "Y" && ($.trim(selRcptChk) == null || $.trim(selRcptChk).length < 1)) {
			var title = "<spring:message code="resc.label.warning"/>";
			var msg = "<spring:message code="resc.msg.selRcpt"/>";
			alertMsg(title, msg);
			return false;
		} else {
			return true;
		}
	}
    </script>
</head>	
<body>	
	<div>
		<h2><c:out value="${doc.cntnName}"/></h2>
	</div>
	
	<div>
		<c:if test="${(doc.crntApprDgr == 0 && doc.apprStus == 0 && doc.authEmpNo == userSession.userId) && approved == true}">
		<a href="#" class="easyui-linkbutton btnModify" style="width:60px"><spring:message code="resc.btn.modify"/></a>
		<a href="#" class="easyui-linkbutton btnDelete" style="width:60px"><spring:message code="resc.btn.delete"/></a>
		<a href="#" class="easyui-linkbutton btnApprReq" style="width:100px"><spring:message code="resc.btn.apprReq"/></a>
		</c:if>
		<c:if test="${(doc.crntApprDgr != 0 && doc.apprStus == 3) && approved == true}">
		<a href="#" class="easyui-linkbutton btnModify" style="width:60px"><spring:message code="resc.btn.modify"/></a>
		<a href="#" class="easyui-linkbutton btnAppr" style="width:60px"><spring:message code="resc.btn.approved"/></a>
		<a href="#" class="easyui-linkbutton btnApproveReject" style="width:60px"><spring:message code="resc.btn.apprRej"/></a>
		</c:if>	
		<c:if test="${(doc.crntApprDgr != 0 && doc.apprStus == 3) && cprtnApproved == true }">		
		<a href="#" class="easyui-linkbutton btnCprtnApproved" style="width:100px"><spring:message code="resc.btn.cprtnApproved"/></a>
		<a href="#" class="easyui-linkbutton btnCprtnReject" style="width:100px"><spring:message code="resc.btn.cprtnReject"/></a>
		</c:if>	
		<c:if test="${(doc.authEmpNo == userSession.userId && doc.crntApprDgr != 0 && doc.apprStus == '5')}">
		<a href="#" class="easyui-linkbutton btnDelete" style="width:60px"><spring:message code="resc.btn.delete"/></a>
		</c:if>
		<c:if test="${(doc.crntApprDgr != 0 && doc.apprStus != '0')}">
		<a href="#" class="easyui-linkbutton btnViewCmmtsApproval" style="width:120px"><spring:message code="resc.btn.viewCmntsApproval"/></a>
		</c:if>
	</div>
	
	<form:form id="detailForm" method="post" action="">
	<hr class="thin bg-grayLighter">
	<jsp:include page="/WEB-INF/views/include/common/hidden_type_01.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/include/common/comment_dialog_01.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/include/common/cprtn_comment_dialog_01.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/include/approval/line_approver_comment_01.jsp"></jsp:include>
	<input id="attachList" name="attachList" type="hidden" value="${strAttachList}"/>
	<input id="apprStus" name="apprStus" type="hidden" value="${apprStus}"/>
	<input id="atchIndc" name="atchIndc" type="hidden" value="${atchIndc}"/>
	<input id="saveType" name="saveType" type="hidden" value=""/>
	<input id="filePathType" name="filePathType" type="hidden" value="A" />
	<input id="steadApprIndc" name="steadApprIndc" type="hidden" value="N" />
	<input id="comment" name="comment" type="hidden" value=""/>
	
	<div style="width:100%;height:100%;margin:0 auto;text-align:center">
		<c:choose>
			<c:when test="${doc.apprStus == 0}">
			<h3>[<spring:message code="resc.label.apprStus"/> : <spring:message code="resc.label.apprWrite"/>]</h3>
			</c:when>
			<c:when test="${doc.apprStus == 3}">
			<h3>[<spring:message code="resc.label.apprStus"/> : <spring:message code="resc.label.apprProgress"/>]</h3>
			</c:when>			
			<c:when test="${doc.apprStus == 5}">
			<h3>[<spring:message code="resc.label.apprStus"/> : <spring:message code="resc.label.apprReject"/>]</h3>
			</c:when>
			<c:when test="${doc.apprStus == 7}">
			<h3>[<spring:message code="resc.label.apprStus"/> : <spring:message code="resc.label.apprComplete"/>]</h3>
			</c:when>
		</c:choose>
	</div>
	
	<div style="width:100%;height:100%;">	
		<table style="width:100%;">
			<tr>
				<td style="width:40%;">
					<!-- 작성자 정보 -->
					<div class="floatLeft2">
					<jsp:include page="/WEB-INF/views/include/approval/auth_info_view_01.jsp"></jsp:include>
					</div>
				</td>
			
				<td style="width:60%;">
					<!-- Line 결재자 정보 -->
					<div class="floatLeft2">
					<jsp:include page="/WEB-INF/views/include/approval/line_approver_01.jsp"></jsp:include>
					</div>
				</td>			
			</tr>
		</table>
	</div>
	
	<!-- 수신처, 참조처, 협조결재자  -->
	<div style="width:100%;height:100%;">
		<table style="width:100%;">
			<tr>
				<td><jsp:include page="/WEB-INF/views/include/approval/rcpt_rfnc_cprtn_01.jsp"></jsp:include></td>
			</tr>
		</table>
	</div>
		
	<!-- 문서 Body -->
	<div style="width:100%;height:100%;">
		<jsp:include page="/WEB-INF/views/include/approval/appr_doc_body_01.jsp"></jsp:include>
	</div>
	
	<!-- 첨부파일 -->
	<table>
		<tbody>
			<tr>
				<td>
					<div class="floatLeft2">
					<jsp:include page="/WEB-INF/views/common/attch_file_view_01.jsp"></jsp:include>
					</div>
				</td>
			</tr>
		</tbody>
	</table>					
	<hr class="thin bg-grayLighter">
	</form:form>
	
	<div>
		<c:if test="${doc.crntApprDgr == 0 && doc.apprStus == 0 && doc.authEmpNo == userSession.userId && approved == true}">
		<a href="#" class="easyui-linkbutton btnModify" style="width:60px"><spring:message code="resc.btn.modify"/></a>
		<a href="#" class="easyui-linkbutton btnDelete" style="width:60px"><spring:message code="resc.btn.delete"/></a>
		<a href="#" class="easyui-linkbutton btnApprReq" style="width:100px"><spring:message code="resc.btn.apprReq"/></a>
		</c:if>
		<c:if test="${(doc.crntApprDgr != 0 && doc.apprStus == 3) && approved == true}">
		<a href="#" class="easyui-linkbutton btnModify" style="width:60px"><spring:message code="resc.btn.modify"/></a>
		<a href="#" class="easyui-linkbutton btnAppr" style="width:60px"><spring:message code="resc.btn.approved"/></a>
		<a href="#" class="easyui-linkbutton btnApproveReject" style="width:60px"><spring:message code="resc.btn.apprRej"/></a>
		</c:if>
		<c:if test="${(doc.crntApprDgr != 0 && doc.apprStus == 3) && cprtnApproved == true }">		
		<a href="#" class="easyui-linkbutton btnCprtnApproved" style="width:100px"><spring:message code="resc.btn.cprtnApproved"/></a>
		<a href="#" class="easyui-linkbutton btnCprtnReject" style="width:100px"><spring:message code="resc.btn.cprtnReject"/></a>
		</c:if>	
		<c:if test="${(doc.authEmpNo == userSession.userId && doc.crntApprDgr != 0 && doc.apprStus == '5')}">
		<a href="#" class="easyui-linkbutton btnDelete" style="width:60px"><spring:message code="resc.btn.delete"/></a>
		</c:if>
		<c:if test="${(doc.crntApprDgr != 0 && doc.apprStus != '0')}">
		<a href="#" class="easyui-linkbutton btnViewCmmtsApproval" style="width:120px"><spring:message code="resc.btn.viewCmntsApproval"/></a>
		</c:if>		
	</div>
</body>
</html>
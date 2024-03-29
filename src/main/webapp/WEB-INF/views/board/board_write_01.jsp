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
        			var title = '<spring:message code="resc.btn.enrollment"/>' + '-' + '${docWriteNo}';
        			parent.$("#listTabsLayer").tabs('close', title);	
        		}
        	});        	
        });
    	
    	//등록 버튼 클릭 시
    	$(".btnEnrollment").click(function(evt) {
    		createBoard();
    	});
    	
    	$(".btnSaveOnly").click(function(evt){
    		createBoard();
    	});
    	    	
    	//승인 요청 버튼 클릭
        $(".btnApprReq").click(function(evt){
			if (!validationLineApprCheck()) return false;	//라인결재자 선택이 되지 않으면 승인요청을 하지 못함
			
			$.messager.confirm("<spring:message code="resc.label.confirm"/>", "<spring:message code="resc.msg.apprReq"/>", function(r) {
        		if (r) {
        			$("#saveType").val("B");
        			$("#apprStus").val("7");
        			$("#apprWriteForm").attr('method', 'post');
					$("#apprWriteForm").attr('action', '<c:out value="${contextPath}"/>/approval/approval_registration_01');
					$("#apprWriteForm").submit();
        		}
        	});			
        });
        
    	function checkAttachFiles() {
    		var ids = $("#fileUploadList").datagrid('getRows');
    		var atchFileCnt = ids.length;
    		if (atchFileCnt > 0) {
    			$('#atchIndc').val('Y');
    		} else {
    			$('#atchIndc').val('N');
    		} 
    	}
    	        
        var baseServiceLife = '${boardMst.validTermCode}';
        getServiceLifeDate(baseServiceLife, 'divClsDate', 'prsvTerm');
        $('#serviceLife').combobox('readonly', true);
        
        $("#serviceLife").combobox({
    		onChange:function(newValue,oldValue) {
    			getServiceLifeDate(newValue,'divClsDate', 'prsvTerm');
    		}	
    	});        
    });      
    
    function createBoard() {
    	var title = "<spring:message code="resc.label.warning"/>";
		var msg = "<spring:message code="resc.msg.contents"/>";    		    		
		
		if($('#boardDocForm01').form('enableValidation').form('validate') && isEmptyTinyMCE('content', title, msg)) {
			$('#content').val(tinymce.get('content').getContent());
    		var formData = parseFormHelper('boardDocForm01');	    		
    		var strUrl = '<c:out value="${contextPath}"/>/rest/board/create';	    		    		
    			    			    		    		
    		$.ajax({
				type: 'POST',
				url: strUrl,
				data: formData,					
				dataType: 'json',						
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
			        xhr.setRequestHeader("Content-Type", "application/json");
					//데이터를 전송하기 전에 헤더에 csrf값을 설정한다.					
					xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
				},  				
				success : function(message) {
					var title = '<spring:message code="resc.label.confirm"/>';		    			
					$.messager.alert(title, message, "info",  function(){
						var title = '<spring:message code="resc.btn.enrollment"/>' + '-' + '${docWriteNo}'
	        			parent.$("#listTabsLayer").tabs('close', title);
					});						
				},
				error : function(xhr, status, error) {
					console.log("error: " + status);
				}
    		});	    		
    		return false;
    	}
    }
    
  	//해당게시판 목록으로 이동.
    function goBoardListForm() {
    	var url = '<c:out value="${contextPath}"/>/board/board_list_01';
    	parent.$('#frmMain').attr('src', url);        	
    }
  
  	//승인 요청 전  필수 입력 사항에 대한 validation 체크를 한다.
	//라인결재자 - 최종 승인자 선택여부 확인
	function validationLineApprCheck() {
		//최종 승인자 입력 여부 확인
		var cnfmUser = $("#rgsrCnfmUser").val();
		
		if ($.trim(cnfmUser) == null || $.trim(cnfmUser).length < 1) {
			var title = "<spring:message code="resc.label.warning"/>";
			var msg = "<spring:message code="resc.msg.selectApprover"/>";
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
		<h2><c:out value="${boardMst.boardName}"/></h2>
	</div>
	
	<div>		
	<c:choose>		
		<c:when test="${boardMst.apprIndc != 'Y'}">
		<a href="#" class="easyui-linkbutton btnEnrollment" style="width:100px"><spring:message code="resc.btn.enrollment"/></a>
		</c:when>
		<c:when test="${boardMst.apprIndc == 'Y'}">
		<a href="#" class="easyui-linkbutton btnSaveOnly" style="width:100px"><spring:message code="resc.btn.saveOnly"/></a>
		<a href="#" class="easyui-linkbutton btnSaveClose" style="width:100px"><spring:message code="resc.btn.saveClose"/></a>
		<a href="#" class="easyui-linkbutton btnApprReq" style="width:100px"><spring:message code="resc.btn.apprReq"/></a>
		</c:when>
	</c:choose>
		<a href="#" class="easyui-linkbutton btnCancel" style="width:100px"><spring:message code="resc.btn.cancel"/></a>
	</div>
	
	<form:form id="boardDocForm01" method="post" action="">
	<hr class="thin bg-grayLighter">
	<jsp:include page="/WEB-INF/views/include/common/hidden_type_01.jsp"></jsp:include>
	<input type="hidden" id="lineApprLevel" name="lineApprLevel" value='<c:out value="${apprLevel}" />'/>
	<input id="attachList" name="attachList" type="hidden" value="${attachList}"/>
	<input id="apprStus" name="apprStus" type="hidden" value="${apprStus}"/>
	<input id="atchIndc" name="atchIndc" type="hidden" value="${boardMst.atchIndc}"/>
	<input id="saveType" name="saveType" type="hidden" value=""/>
	<input id="filePathType" name="filePathType" type="hidden" value="B" />
	<input id="coId" name="coId" type="hidden" value="${userSession.coId}" />
	<input id="boardId" name="boardId" type="hidden" value="${boardMst.boardId}" />
	
	<div style="width:100%;height:100%;">	
		<table style="width:100%;">
			<tr>
				<td style="width:75%;">
					<!-- 작성자 정보 -->
					<div class="floatLeft2">
					<jsp:include page="/WEB-INF/views/include/board/auth_info_write_01.jsp"></jsp:include>
					</div>
				</td>
			<c:if test="${boardMst.apprIndc == 'Y'}">
				<td style="width:25%;">
					<!-- 결재자 정보 -->
					<div class="floatLeft2">
					<jsp:include page="/WEB-INF/views/include/board/line_approver_edit_01.jsp"></jsp:include>
					</div>
				</td>		
			</c:if>	
			</tr>
		</table>
	</div>
	<br/>
			
	<!-- 문서 Body -->
	<div style="width:100%;height:100%;">
		<jsp:include page="/WEB-INF/views/include/board/board_doc_body_edit_01.jsp"></jsp:include>
	</div>
	<br/>
	
	<!-- 첨부파일 -->
	<c:if test="${boardMst.atchIndc == 'Y'}">
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
	</c:if>
	<hr class="thin bg-grayLighter">
	</form:form>
	
	<div>		
	<c:choose>		
		<c:when test="${boardMst.apprIndc != 'Y'}">
		<a href="#" class="easyui-linkbutton btnEnrollment" style="width:100px"><spring:message code="resc.btn.enrollment"/></a>
		</c:when>
		<c:when test="${boardMst.apprIndc == 'Y'}">
		<a href="#" class="easyui-linkbutton btnSaveOnly" style="width:100px"><spring:message code="resc.btn.saveOnly"/></a>	
		<a href="#" class="easyui-linkbutton btnSaveClose" style="width:100px"><spring:message code="resc.btn.saveClose"/></a>	
		<a href="#" class="easyui-linkbutton btnApprReq" style="width:100px"><spring:message code="resc.btn.apprReq"/></a>
		</c:when>
	</c:choose>
		<a href="#" class="easyui-linkbutton btnCancel" style="width:100px"><spring:message code="resc.btn.cancel"/></a>
	</div>
</body>
</html>
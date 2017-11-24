<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script type="text/javascript">
	function selectAttachFilePopup() {		
		var url = "<c:out value="${contextPath}"/>/upload/upload_form";
		$.popupWindow(url, { createNew: false, location: false, height: 420, width: 400, name: 'attachFile' });		
	}	
	
	function checkAttachFiles() {
		var ids = $("#fileUploadList").datagrid('getRows');
		var atchFileCnt = ids.length;
		if (atchFileCnt > 0) {
			$('#atchIndc').val('Y');
		} else {
			$('#atchIndc').val('N');
		} 
	} 
	</script>
	<table class="apprRcptRfnc" style="width:100%;height:auto;">
		<tbody>
			<tr>
				<td class="td_head01" id="attchFile" style="width:100%;">
					<spring:message code="resc.label.attchFile"/>	
					<a href="javascript:selectAttachFilePopup();" class="easyui-linkbutton" style="width:100px"><spring:message code="resc.btn.attchFileAdd"/></a>				
				</td>
			</tr>			
			<tr>
				<td style="width:100%;">					
					<table id="fileUploadList" class="easyui-datagrid" style="width:98%;" data-options="fitColumns:true,singleSelect:true">
					    <thead>
					        <tr>
					            <th data-options="field:'fileId',width:1,hidden:true"><spring:message code="resc.label.fileId"/></th>
					            <th data-options="field:'no',width:'10%',align:'center'"><spring:message code="resc.label.num"/></th>
					            <th data-options="field:'attchFileName',width:'65%',align:'left'"><spring:message code="resc.label.fileName"/></th>
					            <th data-options="field:'fileSize',width:'25%',align:'right',formatter:formatterFileSize"><spring:message code="resc.label.fileSize"/></th>
					        </tr>
					    </thead>
					    <tbody>
					    <c:forEach items="${attachList}" var="attach" varStatus="st">
					    	<tr>
					    		<td>${attach.fileId}</td>
					    		<td>${st.count}</td>
					    		<td>${attach.attchFileName}</td>
					    		<td>${attach.fileSize}</td>
					    	</tr>
					    </c:forEach>
					    </tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
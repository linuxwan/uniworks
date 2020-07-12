<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script type="text/javascript">
	String.prototype.replaceAll = function(org, dest) {
	    return this.split(org).join(dest);
	}
	
	function fileDownload(targetUrl) {
		var popup = window.open(targetUrl, '_blank');
		//var changeURL = targetUrl.replaceAll('/', '~');
		//window.open('<c:out value="${contextPath}"/>/blank/url/' + changeURL, '_blank');
	}
	</script>	
	<table class="apprRcptRfnc" style="width:100%;height:auto;">
		<tbody>
			<tr>
				<td class="td_head01" id="attchFile" style="width:100%;">
					<spring:message code="resc.label.attchFile"/>									
				</td>
			</tr>			
			<tr>
				<td style="width:100%;">					
					<table id="fileUploadList" class="easyui-datagrid" style="width:98%;" data-options="fitColumns:true,singleSelect:true">
					    <thead>
					        <tr>
					        	<th data-options="field:'fileId',width:1,hidden:true"><spring:message code="resc.label.fileId"/></th>
					            <th data-options="field:'no',width:'10%',align:'center'"><spring:message code="resc.label.num"/></th>
					            <th data-options="field:'attchFileName',width:'60%',align:'left'"><spring:message code="resc.label.fileName"/></th>
					            <th data-options="field:'fileSize',width:'20%',align:'right',formatter:formatterFileSize"><spring:message code="resc.label.fileSize"/></th>
					            <th data-options="field:'download',width:'10%',align:'center'"></th>
					        </tr>
					    </thead>
					    <tbody>
					    <c:forEach items="${attachList}" var="attach" varStatus="st">
					    	<tr>
					    		<td>${attach.fileId}</td>
					    		<td>${st.count}</td>
					    		<td>${attach.attchFileName}</td>
					    		<td>${attach.fileSize}</td>
					    		<td><a href="javascript:fileDownload('<c:out value="${contextPath}"/>/download/cntnId/${attach.cntnId}/dcmtRgsrNo/${attach.dcmtRgsrNo}/fileId/${attach.fileId}');" class="easyui-linkbutton"><spring:message code="resc.btn.attchFileDown"/></a></td>
					    	</tr>
					    </c:forEach>
					    </tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
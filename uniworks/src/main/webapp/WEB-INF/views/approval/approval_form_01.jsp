<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="overflow: hidden;">
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
    
    <script type="text/javascript">
    $(function() {
    	$(window).resize(function(){ 
    		$("#p").resizable();
    		$(".easyui-panel").resizable();
    		$("#approvalForm").resizable();
    	}).resize();
    });
    </script>
</head>
<body>
	<form id="apprForm" method="post">
	<jsp:include page="/WEB-INF/views/include/common/hidden_type_01.jsp"></jsp:include>
	<div id="p" class="easyui-panel" title="" style="width:100%;height:auto;padding:10px;">
	<table id="approvalForm" style="width:100%;height:auto;padding:10px;">
	<c:forEach var="list" items="${categoryList}" varStatus="status">
	<c:if test="${status.count % 4 == 1}">	
		<tr><td style="width:25%;">
		<div class="easyui-panel" title="<c:out value="${list.apprItemName}"/>" style="width:100%;height:100%;padding:0px;">
			<c:forEach var="itemList" items="${list.apprItemList}">
		    <a href='javascript:approvalFormToWrite("<c:out value="${itemList.apprMstId}"/>","<c:out value="${itemList.apprDesc}"/>","<c:out value="${itemList.cntnId}"/>");' class="easyui-linkbutton" plain="true" style="width:100%;"><c:out value="${itemList.apprDesc}"/></a> <br/>
			</c:forEach>	
		</div>
		</td>
	</c:if>	
	
	<c:if test="${status.count % 4 == 2}">
		<td style="width:25%;">
		<div class="easyui-panel" title="<c:out value="${list.apprItemName}"/>" style="width:100%;height:100%;padding:0px;">
			<c:forEach var="itemList" items="${list.apprItemList}">
		    <a href='javascript:approvalFormToWrite("<c:out value="${itemList.apprMstId}"/>","<c:out value="${itemList.apprDesc}"/>","<c:out value="${itemList.cntnId}"/>");' class="easyui-linkbutton" plain="true" style="width:100%;"><c:out value="${itemList.apprDesc}"/></a> <br/>
			</c:forEach>	
		</div>
		</td>
	</c:if>
	
	<c:if test="${status.count % 4 == 3}">
		<td style="width:25%;">
		<div class="easyui-panel" title="<c:out value="${list.apprItemName}"/>" style="width:100%;height:100%;padding:0px;">
			<c:forEach var="itemList" items="${list.apprItemList}">			
		    <a href='javascript:approvalFormToWrite("<c:out value="${itemList.apprMstId}"/>","<c:out value="${itemList.apprDesc}"/>","<c:out value="${itemList.cntnId}"/>");' class="easyui-linkbutton" plain="true" style="width:100%;"><c:out value="${itemList.apprDesc}"/></a> <br/>
			</c:forEach>	
		</div>
		</td>
	</c:if>
	
	<c:if test="${status.count % 4 == 0}">
		<td style="width:25%;">
		<div class="easyui-panel" title="<c:out value="${list.apprItemName}"/>" style="width:100%;height:100%;padding:0px;">
			<c:forEach var="itemList" items="${list.apprItemList}">
		    <a href='javascript:approvalFormToWrite("<c:out value="${itemList.apprMstId}"/>","<c:out value="${itemList.apprDesc}"/>","<c:out value="${itemList.cntnId}"/>");' class="easyui-linkbutton" plain="true" style="width:100%;"><c:out value="${itemList.apprDesc}"/></a> <br/>
			</c:forEach>	
		</div>
		</td></tr>		
	</c:if>		
	</c:forEach>
	</table>	
	</div>
	</form>
</body>
</html>
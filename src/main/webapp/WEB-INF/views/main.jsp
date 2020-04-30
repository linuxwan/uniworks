<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html style="overflow: hidden;">
<head>
    <meta charset="UTF-8">
    <title>Uniworks Groupware</title>
    <link rel='shortcut icon' type='image/x-icon' href='<c:out value="${contextPath}"/>/image/testimonials.png' />
    <link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/themes/icon.css">    
    <link rel="stylesheet" type="text/css" href="<c:out value="${contextPath}"/>/easyui/css/uniworks.css">
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/jquery.min.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/locale/easyui-lang-${userSession.language}.js"></script>
    <script type="text/javascript" src="<c:out value="${contextPath}"/>/easyui/js/common.js"></script>
    
    <script type="text/javascript">
    	$(function(){
    		$('.easyui-layout').layout();
    		setHeight();
    		
    		$(window).resize(function(){
    			setHeight();
    		});
    		
    		$('#leftSubMenu').accordion({
    			onSelect:function(title, index) {
    				var param = $(this).find('#noChildNo' + index).val();
    				if (param != undefined) {
    					var strSplit = param.split(',');
    					
    					showContentPage(strSplit[0], strSplit[1], strSplit[2], strSplit[3], strSplit[4]);
    				}
    			}
    		});  
    		
    		$('#selLang').combobox({
    		    onChange:function(newValue,oldValue){
    		        location.href = '<c:out value="${contextPath}"/>/changeLanguage?lang=' + newValue;
    		    }
    		});       		    		    		
    	});                	    	    	    	
    	    	
    	<c:forEach items="${topMenuList}" var="menu" varStatus="menuState">
        <c:if test="${menuState.index == 0}">
        getSidebarMenu('<c:out value="${userSession.coId}"/>','MENU010000','<c:out value="${userSession.language}"/>','<c:out value="${menu.menuDsplName}"/>');
        </c:if>
        </c:forEach>        
        
        function resizeTopIframe(dynheight) {  
        	document.getElementById("frmMain").style.height = parseInt(dynheight) + 'px';  
        } 
    </script>
</head>
<body>	
	<div class="easyui-layout" style="width:100%;height:auto;">
		<div data-options="region:'north'" style="height:60px;overflow:hidden;">
			<div style="padding:15px;">
        		<a href="" class="easyui-linkbutton" data-options="plain:true">Home</a>
        		<c:forEach items="${topMenuList}" var="menu" varStatus="menuState">
        			<a href="javascript:getSidebarMenu('<c:out value="${userSession.coId}"/>','<c:out value="${menu.menuId}"/>','<c:out value="${userSession.language}"/>','<c:out value="${menu.menuDsplName}"/>');" class="easyui-linkbutton" data-options="plain:true"><c:out value="${menu.menuDsplName}"/></a>
        		</c:forEach>
        	</div>
		</div>
		<div data-options="region:'south',split:true" style="height:40px;"><div style="padding:8px;" align="center">Copyright(c) 2020 Park Chung Wan. All Right Reserved.</div></div>
		<div data-options="region:'east',split:true" title="<spring:message code="resc.msg.welcome" />" style="width:180px;">
			<div id="user" class="easyui-panel" title="${userSession.empName}" style="width:100%;height:auto;border:false;padding:1px">
				<a href="<c:out value="${contextPath}"/>/logout" class="easyui-linkbutton" style="width:100%"><spring:message code="resc.btn.logout"/></a>
			</div>			
			<div id="multiLanguage" class="easyui-panel" title="<spring:message code="resc.label.multiLanguage" />" style="width:100%;height:auto;border:false;padding:5px">
				<select id="selLang" class="easyui-combobox" name="state" style="width:100%;" data-options="panelHeight:'auto'">
				<c:forEach items="${langList}" var="lang">
					<option value="${lang.rescKeyValue}" <c:if test="${lang.rescKeyValue == userSession.language}">selected="selected"</c:if> ><c:out value="${lang.rescKeyDesc}"/></option>
				</c:forEach>
				</select>				
			</div>
		</div>
		<div id="selTopMenu" data-options="region:'west',split:true" title="Sub Memu" style="width:200px;height:auto;">
			<div id="leftSubMenu">								
			</div>
		</div>
		<div id="content" data-options="region:'center',title:'Main Title',iconCls:'icon-ok'">
			<div id="divMain" style="height:auto" data-options="fit:true,border:false,plain:true">
				<iframe id="frmMain" name="frmMain" src="" sandbox="allow-same-origin allow-scripts allow-popups allow-forms allow-top-navigation allow-pointer-lock" seamless="seamless" style="overflow-x:hidden; overflow:auto; width:100%;"></iframe>
			</div>
		</div>
	</div>   
</body>
</html>
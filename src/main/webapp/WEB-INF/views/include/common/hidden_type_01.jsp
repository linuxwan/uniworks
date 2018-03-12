<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 
	<input type="hidden" id="crntPage" name="crntPage" value="${param.crntPage}" />
	<input type="hidden" id="menuLevel" name="menuLevel" value="${param.menuLevel}" />
 -->	
	<input type="hidden" id="headMenuId" name="headMenuId" value="${param.headMenuId}" />
	<input type="hidden" id="menuId" name="menuId" value="${param.menuId}" />	
	<input type="hidden" id="bodyUrl" name="bodyUrl" value="${param.bodyUrl}" />
	<input type="hidden" id="cntnId" name="cntnId" value="${param.cntnId}" />
	<input type="hidden" id="dcmtRgsrNo" name="dcmtRgsrNo" value="${param.dcmtRgsrNo}" />
	<input type="hidden" id="apprStus" name="apprStus" value="${param.apprStus}" />
	<input type="hidden" id="apprMstId" name="apprMstId" value="${apprMstId}" />
	<input type="hidden" id="apprLevel" name="apprLevel" value="${apprLevel}" />
	<input type="hidden" id="${_csrf.parameterName}" name="${_csrf.parameterName}" value="${_csrf.token}"/>
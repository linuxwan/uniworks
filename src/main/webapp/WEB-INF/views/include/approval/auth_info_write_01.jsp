<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<table id="apprAuthInfo" style="width:100%;height:auto;">
		<tbody>
			<tr style="height:31px">
				<td class="td_head01" colspan="4" style="width:100%;"><spring:message code="resc.label.author"/></td>
			</tr>			
		<c:choose>
			<c:when test="${doc.authEmpNo != null}">
			<tr style="height:31px">
				<td class="td_title01"><spring:message code="resc.label.empName"/></td>
				<td class="td_class01">${doc.empName}
					<input type="hidden" id="authEmpNo" name="authEmpNo" value="${doc.authEmpNo}"/>
				</td>
				<td class="td_title01"><spring:message code="resc.label.position"/></td>
				<td class="td_class01">${doc.authDutyDesc}
					<input type="hidden" id="authDutyDesc" name="authDutyDesc" value="${doc.authDutyDesc}"/>
				</td>
			</tr>	
			<tr style="height:31px">
				<td class="td_title01"><spring:message code="resc.label.dept"/></td>
				<td class="td_class01">${doc.authDeptDesc}
					<input type="hidden" id="authDeptCode" name="authDeptCode" value="${doc.authDeptCode}"/>
					<input type="hidden" id="authDeptDesc" name="authDeptDesc" value="${doc.authDeptDesc}"/>
				</td>				
				<td class="td_title01"><spring:message code="resc.label.telNo"/></td>
				<td class="td_class01">${doc.authTelNo}
					<input type="hidden" id="authTelNo" name="authTelNo" value="${doc.authTelNo}"/>
				</td>
			</tr>	
			<tr style="height:31px">
				<td class="td_title01"><spring:message code="resc.label.prsvTerm"/></td>
				<td class="td_class01">				
					<select class="easyui-combobox" id="serviceLife" name="prsvTermType" style="width:70px;" data-options="panelHeight:'auto'">
						<c:forEach items="${serviceLife}" var="serviceLife" >
						<option value='<c:out value="${serviceLife.subCode}"/>' <c:if test="${doc.prsvTermType == serviceLife.subCode}">selected="selected"</c:if> ><c:out value="${serviceLife.rescKeyValue}"/></option>
						</c:forEach>
					</select>
				</td>
				<td colspan="2" class="td_class01">
					<div id="divClsDate" class="floatLeft2"></div>
					<input type="hidden" id="prsvTerm" name="prsvTerm" value="${doc.prsvTerm}"/>
				</td>								
			</tr>	
			</c:when>
			<c:otherwise>
			<tr style="height:31px">
				<td class="td_title01"><spring:message code="resc.label.empName"/></td>
				<td class="td_class01">${userSession.empName}
					<input type="hidden" id="authEmpNo" name="authEmpNo" value="${userSession.userId}"/>
				</td>
				<td class="td_title01"><spring:message code="resc.label.position"/></td>
				<td class="td_class01">${userSession.dutyDesc}					
					<input type="hidden" id="authDutyDesc" name="authDutyDesc" value="${userSession.dutyDesc}"/>
				</td>
			</tr>	
			<tr style="height:31px">
				<td class="td_title01"><spring:message code="resc.label.dept"/></td>
				<td class="td_class01">${userSession.deptDesc}
					<input type="hidden" id="authDeptCode" name="authDeptCode" value="${userSession.deptCode}"/>
					<input type="hidden" id="authDeptDesc" name="authDeptDesc" value="${userSession.deptDesc}"/>
				</td>				
				<td class="td_title01"><spring:message code="resc.label.telNo"/></td>
				<td class="td_class01">${userSession.offcTelNo}
					<input type="hidden" id="authTelNo" name="authTelNo" value="${userSession.offcTelNo}"/>
				</td>
			</tr>
			<tr style="height:31px">
				<td class="td_title01"><spring:message code="resc.label.prsvTerm"/></td>
				<td class="td_class01">
					<select class="easyui-combobox" id="serviceLife" name="prsvTermType" style="width:70px;" data-options="panelHeight:'auto'">
						<c:forEach items="${serviceLife}" var="serviceLife" >
						<option value='<c:out value="${serviceLife.subCode}"/>' <c:if test="${prsvTermType == serviceLife.subCode}">selected="selected"</c:if> ><c:out value="${serviceLife.rescKeyValue}"/></option>
						</c:forEach>
					</select>
				</td>
				<td colspan="2" class="td_class01">
					<div id="divClsDate" class="floatLeft2"></div>
					<input type="hidden" id="prsvTerm" name="prsvTerm" value=""/>
				</td>								
			</tr>	
			</c:otherwise>			
		</c:choose>
		</tbody>
	</table>
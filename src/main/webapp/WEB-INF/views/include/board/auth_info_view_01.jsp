<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<table id="apprAuthInfo" style="width:100%;height:auto;">
		<tbody>
			<tr style="height:31px">
				<td class="td_head01" colspan="4" style="width:100%;"><spring:message code="resc.label.author"/></td>
			</tr>			
			<tr style="height:31px">
				<td class="td_title01"><spring:message code="resc.label.empName"/></td>
				<td class="td_class01">${doc.authEmpName}
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
			<c:if test="${boardMst.validTermIndc == 'Y'}">
			<tr style="height:31px">
				<td class="td_title01"><spring:message code="resc.label.prsvTerm"/></td>
				<td class="td_class01">		
					<table>
					<tr><td>		
						<select class="easyui-combobox" id="serviceLife" name="prsvTermType" style="width:70px;" data-options="panelHeight:'auto'">
							<c:forEach items="${serviceLife}" var="serviceLife" >
							<option value='<c:out value="${serviceLife.subCode}"/>' <c:if test="${boardMst.validTermCode == serviceLife.subCode}">selected="selected"</c:if> ><c:out value="${serviceLife.rescKeyValue}"/></option>
							</c:forEach>
						</select> 
					</td>
					<td>
						<div id="divClsDate" class="floatLeft2"></div>
						<input type="hidden" id="prsvTerm" name="prsvTerm" value="${doc.prsvTerm}"/>
					</td></tr>
					</table>
				</td>
				<td class="td_title01"><spring:message code="resc.label.dcmtRgsrDatetime"/></td>
				<td class="td_class01"><fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${doc.dcmtRgsrDatetime}'/></td>								
			</tr>	
			</c:if>
		</tbody>
	</table>
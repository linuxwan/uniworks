<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<table id="apprAuthInfo" style="width:100%;height:auto;">
		<tbody>
			<tr>
				<td class="td_head01" colspan="4" style="width:100%;"><spring:message code="resc.label.author"/></td>
			</tr>
			<tr>
				<td class="td_title01"><spring:message code="resc.label.empName"/></td>
				<td class="td_class01">${doc.empName}</td>				
				<td class="td_title01"><spring:message code="resc.label.position"/></td>
				<td class="td_class01">${doc.authDutyDesc}</td>
			</tr>
			<tr>
				<td class="td_title01"><spring:message code="resc.label.dept"/></td>
				<td class="td_class01">${doc.authDeptDesc}</td>				
				<td class="td_title01"><spring:message code="resc.label.telNo"/></td>
				<td class="td_class01">${doc.authTelNo}</td>
			</tr>
			<tr>
				<td class="td_title01"><spring:message code="resc.label.prsvTerm"/></td>
				<td colspan="3" class="td_class01">${doc.prsvTermTypeDesc} &nbsp; (<fmt:formatDate value="${doc.prsvTerm}" pattern="yyyy.MM.dd"/>)</td>								
			</tr>
		</tbody>
	</table>
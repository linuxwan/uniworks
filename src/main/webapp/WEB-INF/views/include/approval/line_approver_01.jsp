<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<table id="apprLine" style="width:100%;height:auto;">
		<tbody>
		<tr>
			<td class="td_head01" colspan='<c:out value="${apprLevel}"/>'>
				<div class="floatCenter2">				
				<spring:message code="resc.btn.lineApprSelect"/>
				</div>
			</td>
		</tr>
		<tr>
			<c:forEach items="${lineAppr}" var="lineAppr" varStatus="st">			
				<td class="td_head01">${st.count}<spring:message code="resc.label.orderApprover"/></td>
			</c:forEach>
		</tr>
		<tr>
			<c:forEach items="${lineAppr}" var="lineAppr" varStatus="st">
			<td class="td_class01">
				<c:choose>
				<c:when test="${lineAppr.apprEmpName == null}">
				<span id="label_${st.count}"></span>
				<input type="hidden" id="apprLine_${st.count}" name="apprLine_${st.count}" value="" />
				<input type="hidden" id="apprLineDesc_${st.count}" name="apprLineDesc_${st.count}" value="" />
				</c:when>
				<c:when test="${lineAppr.apprEmpName != null}">
				<span id="label_${st.count}">${lineAppr.apprEmpName} ${lineAppr.dutyDesc} <br/>(${lineAppr.apprDeptDesc}) <br/> 
				<fmt:formatDate type="both" value="${lineAppr.apprDateTime}" pattern="yyyy.MM.dd HH:mm:ss"/>
				<input type="hidden" id="apprLine_${st.count}" name="apprLine_${st.count}" value="${lineAppr.apprEmpNo}" />
				<input type="hidden" id="apprLineDesc_${st.count}" name="apprLineDesc_${st.count}" value="${lineAppr.apprEmpName} ${lineAppr.dutyDesc} <br/>(${lineAppr.apprDeptDesc})" />
				</span>
				</c:when>
				<c:otherwise>
				<span id="label_${st.count}"></span>
				<input type="hidden" id="apprLine_${st.count}" name="apprLine_${st.count}" value="" />
				<input type="hidden" id="apprLineDesc_${st.count}" name="apprLineDesc_${st.count}" value="" />
				</c:otherwise>
				</c:choose>
				
			</td>			
			</c:forEach>					
		</tr>	
		</tbody>	
	</table> 
	
	
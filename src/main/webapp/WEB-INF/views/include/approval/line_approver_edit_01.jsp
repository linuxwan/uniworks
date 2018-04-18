<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script type="text/javascript">
	function selectLineApproverPopup() {
		var apprLevel = $("#apprLevel").val();
		var url = "<c:out value="${contextPath}"/>/approval/popup/selectLineApprovers?apprLevel=" + apprLevel;
		$.popupWindow(url, { name: 'selectLineAppr', height: 650, width: 700 });
	}	
	</script>

	<table id="apprLine" style="width:100%;height:auto;">	
		<tbody>
	<c:choose>
		<c:when test="${doc != null}">		
		<tr>
			<td class="td_head01" colspan='<c:out value="${doc.maxApprDgr}"/>'>
				<div class="floatCenter2">				
				<a href="javascript:selectLineApproverPopup();" class="easyui-linkbutton" style="width:100px"><spring:message code="resc.btn.lineApprSelect"/></a>
				</div>
			</td>
		</tr>
		</c:when>
		<c:otherwise>
		<tr>
			<td class="td_head01" colspan='<c:out value="${apprLevel}"/>'>
				<div class="floatCenter2">				
				<a href="javascript:selectLineApproverPopup();" class="easyui-linkbutton" style="width:100px"><spring:message code="resc.btn.lineApprSelect"/></a>
				</div>
			</td>
		</tr>
		</c:otherwise>
	</c:choose>
		
		<tr>
			<c:forEach items="${lineAppr}" var="lineAppr" varStatus="st">			
				<td class="td_head01">${st.count}<spring:message code="resc.label.orderApprover"/></td>
			</c:forEach>
		</tr>
		<tr style="height:62px">
			<c:forEach items="${lineAppr}" var="lineAppr" varStatus="st">
			<td class="td_class01">
				<c:choose>
				<c:when test="${lineAppr.apprEmpName == null}">
				<span id="label_${st.count}"></span>
				<input type="hidden" id="apprLine_${st.count}" name="apprLine_${st.count}" value="" />
				<input type="hidden" id="apprLineDesc_${st.count}" name="apprLineDesc_${st.count}" value="" />
				</c:when>
				<c:when test="${lineAppr.apprEmpName != null}">
				<span id="label_${st.count}">${lineAppr.apprEmpName} ${apprLine.dutyDesc} <br/>(${lineAppr.apprDeptDesc})</span>
				<input type="hidden" id="apprLine_${st.count}" name="apprLine_${st.count}" value="${lineAppr.apprEmpNo}" />
				<input type="hidden" id="apprLineDesc_${st.count}" name="apprLineDesc_${st.count}" value="${lineAppr.apprEmpName} ${lineAppr.dutyDesc} <br/>(${lineAppr.apprDeptDesc})" />
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
	
	
	
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script type="text/javascript">
	function selectLineApproverPopup() {
		var url = "<c:out value="${contextPath}"/>/board/popup/selectLineApprover";
		$.popupWindow(url, { name: 'selectLineAppr', height: 650, width: 700 });
	}	
	</script>

	<table id="apprLine" style="width:100%;height:auto;">	
		<tbody>
		
		<tr>	
			<td class="td_head01"><spring:message code="resc.label.approver"/></td>
		</tr>
		<tr style="height:62px">
			<td class="td_class01">
				<c:choose>
				<c:when test="${doc.rgsrCnfmUserName == null}">
				<span id="label"></span>
				<input type="hidden" id="rgsrCnfmUser" name="rgsrCnfmUser" value="" />
				<input type="hidden" id="rgsrCnfmDutyDesc" name="rgsrCnfmDutyDesc" value="" />
				<input type="hidden" id="rgsrCnfmDeptCode" name="rgsrCnfmDeptCode" value="" />
				<input type="hidden" id="rgsrCnfmDeptDesc" name="rgsrCnfmDeptDesc" value="" />
				<input type="hidden" id="rgsrCnfmDateTime" name="rgsrCnfmDateTime" value="" />
				</c:when>
				<c:when test="${doc.rgsrCnfmUserName != null}">
				<span id="label">${doc.rgsrCnfmUserName} ${doc.rgsrCnfmDutyDesc} <br/>(${doc.rgsrCnfmDeptDesc})</span>
				<input type="hidden" id="rgsrCnfmUser" name="rgsrCnfmUser" value="" />
				<input type="hidden" id="rgsrCnfmDutyDesc" name="rgsrCnfmDutyDesc" value="${doc.rgsrCnfmDutyDesc}" />
				<input type="hidden" id="rgsrCnfmDeptCode" name="rgsrCnfmDeptCode" value="${doc.rgsrCnfmDeptCode}" />
				<input type="hidden" id="rgsrCnfmDeptDesc" name="rgsrCnfmDeptDesc" value="${doc.rgsrCnfmDeptDesc}" />
				<input type="hidden" id="rgsrCnfmDateTime" name="rgsrCnfmDateTime" value="${doc.rgsrCnfmDateTime}" />
				</c:when>
				<c:otherwise>
				<span id="label"></span>
				<input type="hidden" id="rgsrCnfmUser" name="rgsrCnfmUser" value="" />
				<input type="hidden" id="rgsrCnfmDutyDesc" name="rgsrCnfmDutyDesc" value="" />
				<input type="hidden" id="rgsrCnfmDeptCode" name="rgsrCnfmDeptCode" value="" />
				<input type="hidden" id="rgsrCnfmDeptDesc" name="rgsrCnfmDeptDesc" value="" />
				<input type="hidden" id="rgsrCnfmDateTime" name="rgsrCnfmDateTime" value="" />
				</c:otherwise>
				</c:choose>								
			</td>								
		</tr>	
		</tbody>		
	</table> 
	
	
	
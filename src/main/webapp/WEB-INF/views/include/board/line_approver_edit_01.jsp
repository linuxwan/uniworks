<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script type="text/javascript">
	function selectLineApproverPopup() {
		var url = "<c:out value="${contextPath}"/>/board/popup/selectLineApprover";
		$.popupWindow(url, { name: 'selectLineAppr', height: 520, width: 520 });
	}	
	
	//직원 검색 PopUp 창에서 호출하는 Function
  	function callBackEmpInfo(userId, userName, dutyCode, dutyDesc, deptCode, deptDesc) {		
		var apprInfo = userName + " " + dutyDesc + "<br/>" + "(" + deptDesc + ")";
		console.log("apprInfo : " + apprInfo);		
		$('#label').html(apprInfo);
    	$('#userId').val(userId);    	
    	$('#userName').val(userName);
    	$('#rgsrCnfmUser').val(userId);
    	$('#rgsrCnfmDutyCode').val(dutyCode);
    	$('#rgsrCnfmDutyDesc').val(dutyDesc);				
    	$('#rgsrCnfmDeptCode').val(deptCode);
    	$('#rgsrCnfmDeptDesc').val(deptDesc);
  	}
	</script>

	<table id="apprLine" style="width:100%;height:auto;">	
		<tbody>
	<c:choose>
		<c:when test="${doc != null}">		
		<tr>
			<td class="td_head01">
				<div class="floatCenter2">				
				<a href="javascript:selectLineApproverPopup();" class="easyui-linkbutton" style="width:100px"><spring:message code="resc.btn.lineApprSelect"/></a>
				</div>
			</td>
		</tr>
		</c:when>
		<c:otherwise>
		<tr>
			<td class="td_head01">
				<div class="floatCenter2">				
				<a href="javascript:selectLineApproverPopup();" class="easyui-linkbutton" style="width:100px"><spring:message code="resc.btn.cnfmSelect"/></a>
				</div>
			</td>
		</tr>
		</c:otherwise>
	</c:choose>
		
		<tr>	
			<td class="td_head01">${st.count}<spring:message code="resc.label.approver"/></td>
		</tr>
		<tr style="height:40px">
			<td class="td_class01">
				<span id="label"></span>
				<input type="hidden" id="userId" name="userId" value="" />
				<input type="hidden" id="userName" name="userName" value="" />
				<input type="hidden" id="rgsrCnfmUser" name="rgsrCnfmUser" value="" />
				<input type="hidden" id="rgsrCnfmDutyCode" name="rgsrCnfmDutyCode" value="" />
				<input type="hidden" id="rgsrCnfmDutyDesc" name="rgsrCnfmDutyDesc" value="" />
				<input type="hidden" id="rgsrCnfmDeptCode" name="rgsrCnfmDeptCode" value="" />
				<input type="hidden" id="rgsrCnfmDeptDesc" name="rgsrCnfmDeptDesc" value="" />
				<input type="hidden" id="rgsrCnfmDateTime" name="rgsrCnfmDateTime" value="" />
			</td>								
		</tr>	
		</tbody>		
	</table> 
	
	
	
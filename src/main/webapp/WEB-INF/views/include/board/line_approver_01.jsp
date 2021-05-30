<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<script type="text/javascript">
	function selectLineApproverPopup() {
		var url = "<c:out value="${contextPath}"/>/board/popup/selectLineApprover";
		$.popupWindow(url, { name: 'selectLineAppr', height: 520, width: 520 });
	}	
	
	//직원 검색 PopUp 창에서 호출하는 Function
  	function callBackEmpInfo(targetObj, userId, userName) {
  		$('#' + targetObj + "Name").textbox('setValue', userName + "(" + userId + ")");
    	$('#' + targetObj + "Id").val(userId);    	
  	}
	</script>

	<table id="apprLine" style="width:100%;height:auto;">	
		<tbody>
		
		<tr>	
			<td class="td_head01"><spring:message code="resc.label.approver"/></td>
		</tr>
		<tr style="height:62px">
			<td class="td_class01">
				<span id="label"></span>
				<input type="hidden" id="rgsrCnfmUser" name="rgsrCnfmUser" value="" />
				<input type="hidden" id="rgsrCnfmDutyDesc" name="rgsrCnfmDutyDesc" value="" />
				<input type="hidden" id="rgsrCnfmDeptCode" name="rgsrCnfmDeptCode" value="" />
				<input type="hidden" id="rgsrCnfmDeptDesc" name="rgsrCnfmDeptDesc" value="" />
				<input type="hidden" id="rgsrCnfmDateTime" name="rgsrCnfmDateTime" value="" />
			</td>								
		</tr>	
		</tbody>		
	</table> 
	
	
	
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	
	<script type="text/javascript">
	var cprtIndc = '${apprMst.cprtnIndc}';
	
	if (cprtIndc == 'Y') {
		
	}
	
	function selectRcptRfncPopup() {
		var apprLevel = $("#apprLevel").val();
		var url = "<c:out value="${contextPath}"/>/approval/popup/selectRcptRfnc?apprLevel=" + apprLevel + "&apprMstId=" + '${apprMst.apprMstId}';
		$.popupWindow(url, { name: 'selectRcptRfnc', height: 650, width: 700 });
	}
	
	function selectCprtnPopup() {
		var apprLevel = $("#apprLevel").val();
		var lineApprCheck = false;	//라인 결재자 확인
		
		for (var i = 1; i <= apprLevel; i++) {
			var apprLine = $("#apprLine_" + i).val();
			if (apprLine != null && apprLine.length > 0) lineApprCheck = true;
		}
		
		//라인 결재자가 선택되지 않았을 경우에는 라인 결재자를 선택하라는 메시지 출력
		if (!lineApprCheck) {
			var title = "<spring:message code="resc.label.warning"/>";    	
    		var msg = "<spring:message code="resc.msg.selectApprover"/>";
    		alertMsg(title, msg);  
		} else {	//라인 결재자가 선
			var url = "<c:out value="${contextPath}"/>/approval/popup/selectCprtApprovers?apprLevel=" + apprLevel;
			$.popupWindow(url, { name: 'selectCprtn', height: 650, width: 700 });
		}
	}
	</script>
	
	<div class="floatLeft2" >
		<table class="apprRcptRfnc" style="width:100%;height:auto;">
			<tbody>
			<c:if test="${apprMst.rcptIndc == 'Y'}">	<!-- 수신처 -->
				<tr>
					<td class="td_title01">										
						<a href="javascript:selectRcptRfncPopup();" class="easyui-linkbutton" style="width:100px"><spring:message code="resc.btn.rcptSelect" /></a>
					</td>
					<td class="td_class01">
						<span id="label_selRcpt">${rcptMap['selRcptDesc']}</span>
						<input type="hidden" id="hd_selRcpt" name="selRcpt" value="${rcptMap['selRcpt']}" />
						<input type="hidden" id="hd_selRcptDesc" name="selRcptDesc" value="${rcptMap['selRcptDesc']}" />
					</td>
				</tr>
			</c:if>
			<c:if test="${apprMst.rfncIndc == 'Y'}">	<!-- 참조처 -->
				<tr>
					<td class="td_title01">
						<a href="javascript:selectRcptRfncPopup();" class="easyui-linkbutton" style="width:100px"><spring:message code="resc.btn.rfncSelect" /></a>
					</td>
					<td class="td_class01">
						<span id="label_selRfnc">${rfncMap['selRfncDesc']}</span>
						<input type="hidden" id="hd_selRfnc" name="selRfnc" value="${rfncMap['selRfnc']}" />
						<input type="hidden" id="hd_selRfncDesc" name="selRfncDesc" value="${rfncMap['selRfncDesc']}" />
					</td>
				</tr>
			</c:if>
			<c:if test="${apprMst.cprtnIndc == 'Y'}">	<!-- 협조처 -->
				<tr>
					<td class="td_title01">																
					<c:choose>
					<c:when test="${(doc.crntApprDgr == 0 && doc.apprStus == 0 && doc.authEmpNo == userSession.userId)}">
						<a href="javascript:selectCprtnPopup();" class="easyui-linkbutton" style="width:100px"><spring:message code="resc.btn.cprtnSelect" /></a>
					</c:when>
					<c:when test="${doc == null}">
						<a href="javascript:selectCprtnPopup();" class="easyui-linkbutton" style="width:100px"><spring:message code="resc.btn.cprtnSelect" /></a>
					</c:when>
					<c:otherwise>
						<spring:message code="resc.btn.cprtnSelect" />
					</c:otherwise>
					</c:choose>
					</td>
					<td class="td_class01">
						<span id="label_selCprtn"></span>
						<div class="easyui-panel" style="width:100%;height:100px">
							<table id="cprtnList" class="easyui-datagrid" style="width:100%;height:100%;" data-options="fitColumns:true,singleSelect:true">
							    <thead>
							        <tr>
							            <th data-options="field:'apprDgr',width:'10%'"><spring:message code="resc.label.apprDgr"/></th>
							            <th data-options="field:'deptDesc',width:'20%'"><spring:message code="resc.label.deptDesc"/></th>
							            <th data-options="field:'deptCode',width:'10%',hidden:true"><spring:message code="resc.label.deptCode"/></th>
							            <th data-options="field:'apprStus',width:'25%'"><spring:message code="resc.label.apprDateTime"/></th>
							            <th data-options="field:'comment',width:'35%'"><spring:message code="resc.label.comment"/></th>
							        </tr>
							    </thead>
							    <tbody>
							    <c:forEach items="${cprtnAppr}" var="cprtnAppr" varStatus="st">
							    	<tr>
							            <td>${cprtnAppr.apprDgr}</td>
							            <td>${cprtnAppr.deptDesc}</td>
							            <td>${cprtnAppr.deptCode}</td>
							            <td>${cprtnAppr.apprDateTime}</td>
							            <td>${cprtnAppr.comment}</td>
							        </tr>
							    </c:forEach>
							    </tbody>
							</table>
						</div>
						<input type="hidden" id="hd_selCprtn" name="selCprtn" value="${cprtnMap['selCprtn']}"/>
						<input type="hidden" id="hd_selCprtnDesc" name="selCprtnDesc" value="${cprtnMap['selCprtnDesc']}"/>
					</td>
				</tr>
			</c:if>
			</tbody>
		</table>
	</div>

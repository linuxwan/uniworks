<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>	

	<div class="floatLeft2" >
		<table class="apprRcptRfnc" style="width:100%;height:auto;">
			<tbody>
			<c:if test="${apprMst.rcptIndc == 'Y'}">
				<tr>
					<td class="td_title01">										
						<spring:message code="resc.label.rcpt" />
					</td>
					<td class="td_class01">
						<span id="label_selRcpt">${rcptMap['selRcptDesc']}</span>
						<input type="hidden" id="hd_selRcpt" name="selRcpt" value="${rcptMap['selRcpt']}" />
						<input type="hidden" id="hd_selRcptDesc" name="selRcptDesc" value="${rcptMap['selRcptDesc']}" />
					</td>
				</tr>
			</c:if>
			<c:if test="${apprMst.rfncIndc == 'Y'}">
				<tr>
					<td class="td_title01">
						<spring:message code="resc.label.rfnc" />
					</td>
					<td class="td_class01">
						<span id="label_selRfnc">${rfncMap['selRfncDesc']}</span>
						<input type="hidden" id="hd_selRfnc" name="selRfnc" value="${rfncMap['selRfnc']}" />
						<input type="hidden" id="hd_selRfncDesc" name="selRfncDesc" value="${rfncMap['selRfncDesc']}" />
					</td>
				</tr>
			</c:if>
			<c:if test="${apprMst.cprtnIndc == 'Y'}">
				<tr>
					<td class="td_title01">
						<spring:message code="resc.label.cprtn" />
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
							            <th data-options="field:'apprStus',width:'25%',formatter:formatDate"><spring:message code="resc.label.apprDateTime"/></th>
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

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="commentsList" class="easyui-dialog" title="Comments" style="width:750px;height:320px;padding:10px"
            data-options="
            	modal: true,
            	closed: true,
                buttons: '#dlg-appr-buttons'">
	<div style="margin-bottom:20px">
	   <table id="apprComments" class="easyui-datagrid" title="Comments" style="width:700px;height:200px"
            data-options="singleSelect:true,collapsible:true">
        <thead>
            <tr>
                <th data-options="field:'apprDgr',width:60,align:'center'"><spring:message code="resc.label.apprDgr"/></th>
                <th data-options="field:'apprEmpName',width:80,align:'left'"><spring:message code="resc.label.empName"/></th>
                <th data-options="field:'apprDeptDesc',width:100,align:'left'"><spring:message code="resc.label.dept"/></th>
                <th data-options="field:'dutyDesc',width:80,align:'left'"><spring:message code="resc.label.dutyDesc"/></th>
                <th data-options="field:'apprDateTime',width:120,align:'left',formatter:formatDate"><spring:message code="resc.label.apprCnfmDT"/></th>
                <th data-options="field:'comment',width:240,align:'left'"><spring:message code="resc.label.comment"/></th>
            </tr>
        </thead>
		</table>
	</div>
</div>

<div id="dlg-appr-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#commentsList').dialog('close')"><spring:message code="resc.btn.close"/></a>
</div>
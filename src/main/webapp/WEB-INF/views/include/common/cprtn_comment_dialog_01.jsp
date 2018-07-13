<!--협조 승인 버튼 클릭 시 나타나는 Comment 창 -->
<div id="dlgCprtnAppr" class="easyui-dialog" title="Comments" style="width:400px;height:215px;padding:10px"
            data-options="
            	modal: true,
            	closed: true,
                buttons: '#dlg-cprtnappr-buttons'">
	<div style="margin-bottom:20px">
	    <input class="easyui-textbox" id="cprtnApprComment" label="Comments:" labelPosition="top" multiline="true" value="" style="width:100%;height:100px">	    
	</div>
</div>

<div id="dlg-cprtnappr-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:cprtnApproved()"><spring:message code="resc.btn.approved"/></a>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlgCprtnAppr').dialog('close')"><spring:message code="resc.btn.cancel"/></a>
</div>

<!--협조 반려 버튼 클릭 시 나타나는 Comment 창 -->
<div id="dlgCprtnReject" class="easyui-dialog" title="Comments" style="width:400px;height:215px;padding:10px"
            data-options="
            	modal: true,
            	closed: true,
                buttons: '#dlg-cprtnreject-buttons'">
	<div style="margin-bottom:20px">
	    <input class="easyui-textbox" id="rejectCprtnApprComment" label="Comments:" labelPosition="top" multiline="true" value="" style="width:100%;height:100px">
	</div>
</div>

<div id="dlg-cprtnreject-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:cprtnReject()"><spring:message code="resc.btn.apprRej"/></a>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlgCprtnReject').dialog('close')"><spring:message code="resc.btn.cancel"/></a>
</div>
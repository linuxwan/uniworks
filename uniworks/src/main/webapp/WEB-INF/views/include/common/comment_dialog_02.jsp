<!-- 승인 버튼 클릭 시 나타나는 Comment 창 -->
<div id="dlgAppr" class="easyui-dialog" title="Comments" style="width:400px;height:215px;padding:10px"
            data-options="
            	modal: true,
            	closed: true,
                buttons: '#dlg-appr-buttons'">
	<div style="margin-bottom:20px">
	    <input class="easyui-textbox" id="apprComment" label="Comments:" labelPosition="top" multiline="false" value="" style="width:100%;height:100px">	    	    
	</div>
</div>

<div id="dlg-appr-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:approved()"><spring:message code="resc.btn.approved"/></a>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlgAppr').dialog('close')"><spring:message code="resc.btn.cancel"/></a>
</div>

<!-- 반려 버튼 클릭 시 나타나는 Comment 창 -->
<div id="dlgReject" class="easyui-dialog" title="Comments" style="width:400px;height:215px;padding:10px"
            data-options="
            	modal: true,
            	closed: true,
                buttons: '#dlg-reject-buttons'">
	<div style="margin-bottom:20px">
	    <input class="easyui-textbox" id="rejectComment" label="Comments:" labelPosition="top" multiline="false" value="" style="width:100%;height:100px">
	</div>
</div>

<div id="dlg-reject-buttons">
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:reject()"><spring:message code="resc.btn.apprRej"/></a>
	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlgReject').dialog('close')"><spring:message code="resc.btn.cancel"/></a>
</div>
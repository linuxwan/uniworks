<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<table>
		<tbody>
			<tr>
				<td>
					<div class="floatLeft2">
						<table class="apprRcptRfnc">
							<tbody>
						<c:choose>
							<c:when test="${doc.dcmtRgsrNo != null}">															
								<tr>
									<td class="td_title01"><fmt:message key="resc.label.title"/></td>
									<td class="td_class01">
										<input class="easyui-textbox" id="docTitle" name="docTitle" value="${doc.docTitle}" style="width:97%" />
										<br/><font color="red"><form:errors path="docTitle" /></font>									
									</td>
								</tr>
								<tr>
									<td class="td_title01" colspan="2"><fmt:message key="resc.label.contents"/></td>
								</tr>
								<tr>
									<td class="td_class01" colspan="2">
										<textarea class="tinymce01" id="content" name="content">${doc.content}</textarea>
										<br/><font color="red"><form:errors path="content" /></font>										
									</td>
								</tr>
							</c:when>
							<c:otherwise>																					
								<tr>
									<td class="td_title01"><fmt:message key="resc.label.title"/></td>
									<td class="td_class01">
										<input class="easyui-textbox" id="docTitle" name="docTitle" value="${docTitle}" style="width:97%" />
										<br/><font color="red"><form:errors path="docTitle" /></font>									
									</td>
								</tr>
								<tr>
									<td class="td_title01" colspan="2"><fmt:message key="resc.label.contents"/></td>
								</tr>
								<tr>
									<td class="td_class01" colspan="2">
										<textarea class="tinymce01" id="content" name="content">${content}</textarea>
										<br/><font color="red"><form:errors path="content" /></font>										
									</td>
								</tr>							
							</c:otherwise>
						</c:choose>
							</tbody>
						</table>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<table>
		<tbody>
			<tr>
				<td>
					<div class="floatLeft2">
						<table class="apprRcptRfnc">
							<tbody>
						<c:choose>
							<c:when test="${board.dcmtRgsrNo != null}">															
								<tr>
									<td class="td_title01"><fmt:message key="resc.label.title"/></td>
									<td class="td_class01">
										<input class="easyui-textbox" id="title" name="title" value="${board.title}" style="width:97%" />								
									</td>
								</tr>
								<tr>
									<td class="td_title01" colspan="2"><fmt:message key="resc.label.contents"/></td>
								</tr>
								<tr>
									<td class="td_class01" colspan="2">
										<textarea class="tinymce01" id="content" name="content">${board.content}</textarea>									
									</td>
								</tr>
							</c:when>
							<c:otherwise>																					
								<tr>
									<td class="td_title01"><fmt:message key="resc.label.title"/></td>
									<td class="td_class01">
										<input class="easyui-textbox" id="title" name="title" value="" style="width:97%" />								
									</td>
								</tr>
								<tr>
									<td class="td_title01" colspan="2"><fmt:message key="resc.label.contents"/></td>
								</tr>
								<tr>
									<td class="td_class01" colspan="2">
										<textarea class="tinymce01" id="content" name="content"></textarea>																			
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
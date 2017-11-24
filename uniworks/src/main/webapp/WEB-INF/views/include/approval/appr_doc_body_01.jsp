<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

	<table>
		<tbody>
			<tr>
				<td>
					<div class="floatLeft2">
						<table class="apprRcptRfnc">
							<tbody>
								<tr>
									<td class="td_title01"><fmt:message key="resc.label.docNo"/> </td>
									<td class="td_class01">
								<c:choose>
									<c:when test="${doc.apprStus > '5' || doc.apprStus < '5'}">		
										<c:out value="${doc.docNo}"/>  
										<input type="hidden" id="hdDocNo" name="docNo" value="${doc.docNo}"/>
									</c:when>
									<c:otherwise>
										<c:out value="${userSession.deptDesc}"/> ${year} -  
										<input type="hidden" id="hdDocNo" name="docNo" value='<c:out value="${userSession.deptDesc}"/> ${year} -'/>
									</c:otherwise>
								</c:choose>
									</td>
								</tr>
								<tr>
									<td class="td_title01"><fmt:message key="resc.label.title"/></td>
									<td class="td_class01">
										<c:out value="${doc.docTitle}" />										
									</td>
								</tr>
								<tr>
									<td class="td_title01" colspan="2"><fmt:message key="resc.label.contents"/></td>
								</tr>
								<tr>
									<td class="td_class01" colspan="2">
										<div class="tinymce01" id="content">${doc.content}</div>										
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
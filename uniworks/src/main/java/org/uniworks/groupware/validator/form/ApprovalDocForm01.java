/**
 * 박충완이 작성한 소스입니다.
 * 이 소스는 모든 개발자들이 자유롭게 수정/배포할 수 있습니다.
 * 단, 이 소스를 근간으로 애플리케이션을 개발하실 때에는 꼭 출처를 명시해 주세요.
 */
package org.uniworks.groupware.validator.form;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Chungwan Park
 * ApprovalDocForm01.java 2014. 9. 21.
 */
public class ApprovalDocForm01 {
	@NotEmpty
	private String docTitle;
	@NotEmpty
	private String content;
	
	public String getDocTitle() {
		return docTitle;
	}
	public void setDocTitle(String docTitle) {
		this.docTitle = docTitle;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
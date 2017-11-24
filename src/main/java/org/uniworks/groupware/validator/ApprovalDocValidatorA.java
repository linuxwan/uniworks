/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.uniworks.groupware.validator.form.ApprovalDocForm01;

/**
 * @author Park Chungwan
 *
 */

public class ApprovalDocValidatorA implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub		
		return (ApprovalDocForm01.class.isAssignableFrom(clazz));
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		ApprovalDocForm01 docForm = (ApprovalDocForm01) target;
		if (docForm.getDocTitle() == null || docForm.getDocTitle().length() < 1) ValidationUtils.rejectIfEmptyOrWhitespace(errors, "docTitle", "resc.msg.docTitle");
		if (docForm.getContent() == null || docForm.getContent().length() < 1) ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "resc.msg.contents");
	}

}

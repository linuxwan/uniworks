/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.uniworks.groupware.domain.Company;

/**
 * @author Park Chungwan
 *
 */
@Service
public interface CompanyService {
	Company getCompanyMaster(Map<String, Object> map);
}

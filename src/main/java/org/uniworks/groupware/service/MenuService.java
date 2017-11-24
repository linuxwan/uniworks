/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.uniworks.groupware.domain.Menu;

/**
 * @author Park Chungwan
 *
 */
@Service
public interface MenuService {
	/**
	 * 최상위 메뉴(1레벨) 목록을 가져온다.
	 * @param map
	 * @return
	 */
	List<Menu> getTopMenu(Map<String, Object> map);
	
	/**
	 * 최상위 메뉴의 하위 메뉴를 모두 가져온다.
	 * @param map
	 * @return
	 */
	List<Menu> getSubMenuAll(Map<String, Object> map);
}

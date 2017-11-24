/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.mapper;

import java.util.List;
import java.util.Map;

import org.uniworks.groupware.domain.CommonCode;
import org.uniworks.groupware.domain.MenuHierarchyInfo;

/**
 * @author Park Chungwan
 *
 */
public interface CommonMapper {
	/**
	 * 주코드에 속하는 하위 코드 목록을 가져온다.
	 * @param map
	 * @return
	 */
	List<CommonCode> selectByCommonSubCodeList(Map<String, Object> map);
	/**
	 * 시작 메뉴와 마지막 메뉴 코드 값을 입력해서 메뉴 계층 정보(코드, 명칭)를 가져온다.
	 * 본문 최상단의 네비게이션 정보 표기시에 사용(예. 전자 비서 > 전자 결재 > 결재양식함) 
	 * @param map
	 * @return
	 */
	MenuHierarchyInfo selectMenuHierarchyLev3(Map<String, Object> map);
	/**
	 * 시작 메뉴와 마지막 메뉴 코드 값을 입력해서 메뉴 계층 정보(코드, 명칭)를 가져온다.
	 * 본문 최상단의 네비게이션 정보 표기시에 사용(예. 전자 비서 > 메일) 
	 * @param map
	 * @return
	 */
	MenuHierarchyInfo selectMenuHierarchyLev2(Map<String, Object> map);
}

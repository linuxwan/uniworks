/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.service.internal;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uniworks.groupware.domain.CommonCode;
import org.uniworks.groupware.domain.MenuHierarchyInfo;
import org.uniworks.groupware.mapper.CommonMapper;
import org.uniworks.groupware.service.CommonService;

/**
 * @author Park Chungwan
 *
 */
@Service
@Transactional(readOnly = true) 
public class CommonServiceImpl implements CommonService {
	@Autowired CommonMapper cmmMapper;
	/**
	 * 주코드에 속하는 하위코드 목록을 가져온다.
	 * @param map
	 * @return
	 */
	@Override
	public List<CommonCode> getCommonSubCodeList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<CommonCode> codeList = cmmMapper.selectByCommonSubCodeList(map);
		return codeList;
	}
	/**
	 * 시작 메뉴와 마지막 메뉴 코드 값을 입력해서 메뉴 계층 정보(코드, 명칭)를 가져온다.
	 * 본문 최상단의 네비게이션 정보 표기시에 사용(예. 전자 비서 > 전자 결재 > 결재양식함) 
	 * @param map
	 * @return
	 */
	@Override
	public MenuHierarchyInfo getMenuHierarchyInfo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		MenuHierarchyInfo menuHierarchy = null;

		String menuLevel = (String) map.get("menuLevel");
		
		//메뉴 레벨이 2 또는 3인지 확인.
		if (menuLevel.equalsIgnoreCase("2")) {
			menuHierarchy = cmmMapper.selectMenuHierarchyLev2(map);
		} else {
			menuHierarchy = cmmMapper.selectMenuHierarchyLev3(map);
		}
		
		return menuHierarchy;
	}
}

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
import org.uniworks.groupware.domain.Menu;
import org.uniworks.groupware.mapper.MenuMapper;
import org.uniworks.groupware.service.MenuService;

/**
 * @author Park Chungwan
 *
 */
@Service 
@Transactional(readOnly = true) 
public class MenuServiceImpl implements MenuService {
	@Autowired MenuMapper menuMapper;
	/* (non-Javadoc)
	 * @see org.uniworks.groupware.service.MenuService#getTopMenu(java.util.Map)
	 */
	@Override
	public List<Menu> getTopMenu(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Menu> topMenuList = menuMapper.selectByTopMenu(map);
		return topMenuList;
	}

	/* (non-Javadoc)
	 * @see org.uniworks.groupware.service.MenuService#getSubMenuAll(java.util.Map)
	 */
	@Override
	public List<Menu> getSubMenuAll(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<Menu> subMenuList = menuMapper.selectBySubMenu(map);
		return subMenuList;
	}
}

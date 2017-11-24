/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.mapper;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.uniworks.groupware.common.config.AppConfig;
import org.uniworks.groupware.common.config.BoneCPConfig;
import org.uniworks.groupware.domain.Menu;

/**
 * @author Park Chungwan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class, BoneCPConfig.class})
public class MenuMapperTest {
	Logger logger = LoggerFactory.getLogger(MenuMapperTest.class); 
	
	@Autowired
	MenuMapper menuMapper;
	private Map<String, Object> map;	
	
	@Before
	public void setUp() {
		setMenu();
	}
	
	@Test
	public void testSelectByTopMenu() {
		List<Menu> menuList = menuMapper.selectByTopMenu(map);
		assertTrue(menuList.size() > 0);
		for (int i = 0; i < menuList.size(); i++) {
			Menu menu = menuList.get(i);
			logger.debug(menu.getMenuId() + ":" + menu.getHighMenuId() + ":" + menu.getMenuDsplName());
		}
	}
	
	@Test
	public void testSelectBySubMenu() {
		map.put("highMenuId", "MENU01%");
		List<Menu> subMenuList = menuMapper.selectBySubMenu(map);
		assertTrue(subMenuList.size() > 0);
		for (int i = 0; i < subMenuList.size(); i++) {
			Menu menu = subMenuList.get(i);
			logger.debug(menu.getMenuId() + ":" + menu.getHighMenuId() + ":" + menu.getMenuDsplName());
		}
	}
	
	private void setMenu() {
		map = new HashMap<String, Object>();
		map.put("coId", "A001");
		map.put("lang", "ko");
	}
}

/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.service;

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
import org.springframework.transaction.annotation.Transactional;
import org.uniworks.groupware.common.config.AppConfig;
import org.uniworks.groupware.common.config.BoneCPConfig;
import org.uniworks.groupware.domain.Menu;

/**
 * @author Park Chungwan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class, BoneCPConfig.class})
@Transactional
public class MenuServiceTest {
	Logger logger = LoggerFactory.getLogger(MenuServiceTest.class);
	
	@Autowired
	private MenuService menuService;
	private Map<String, Object> map;
	
	@Before
	public void setUp() {
		setMenu();
	}
	
	@Test
	public void testGetTopMenu() {
		List<Menu> topMenuList = menuService.getTopMenu(map);
		assertTrue(topMenuList.size() > 0);
	}
	
	@Test
	public void testGetSubMenuAll() {
		map.put("highMenuId", "MENU01%");
		List<Menu> subMenuList = menuService.getSubMenuAll(map);
		assertTrue(subMenuList.size() > 0);
	}
	
	private void setMenu() {
		map = new HashMap<String, Object>();
		map.put("coId", "A001");
		map.put("lang", "ko");
	}
}

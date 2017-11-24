/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.uniworks.groupware.domain.Menu;
import org.uniworks.groupware.domain.MenuHierarchyInfo;
import org.uniworks.groupware.service.CommonService;
import org.uniworks.groupware.service.MenuService;

/**
 * @author Park Chungwan
 *
 */
@Controller
@RequestMapping(value = "/rest")
public class MenuController {
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@Autowired MenuService menuService;
	@Autowired CommonService cmmService;
	
	/**
	 * Top 메뉴 목록을 가져온다.
	 * @param coId
	 * @param lang
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/topMenu/coId/{coId}/lang/{lang}", method = RequestMethod.GET)
	@ResponseBody
	public List<Menu> getTopMenuList(@PathVariable("coId") String coId, @PathVariable("lang") String lang) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId",  coId);
		map.put("lang", lang);
		List<Menu> topMenuList = menuService.getTopMenu(map);
				
		return topMenuList;
	}
	
	/**
	 * Top 메뉴 아래의 하위 메뉴 모두를 가져온다.
	 * @param coId
	 * @param lang
	 * @param highMenuId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/subMenu/coId/{coId}/highMenuId/{highMenuId}/lang/{lang}", method = RequestMethod.GET)
	@ResponseBody
	public List<Menu> getSubMenuList(@PathVariable("coId") String coId, @PathVariable("lang") String lang, 
			@PathVariable("highMenuId") String highMenuId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId",  coId);
		map.put("highMenuId", highMenuId + "%");	//like 검색
		map.put("lang", lang);
		List<Menu> subMenuList = menuService.getSubMenuAll(map);
				
		return subMenuList;
	}
	
	@RequestMapping(value = "/startMenuId/{startMenuId}/endMenuId/{endMenuId}/menuLevel/{menuLevel}", method = RequestMethod.GET)
	@ResponseBody
	public MenuHierarchyInfo getMenuHierarchyInfo(@PathVariable("startMenuId") String startMenuId, 
			@PathVariable("endMenuId") String endMenuId, @PathVariable("menuLevel") String menuLevel) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("startMenuId", startMenuId);
		map.put("endMenuId", endMenuId);
		map.put("menuLevel", menuLevel);
		
		MenuHierarchyInfo menuHierarchyInfo = cmmService.getMenuHierarchyInfo(map);
		return menuHierarchyInfo;
	}
}

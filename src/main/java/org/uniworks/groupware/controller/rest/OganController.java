/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.controller.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;
import org.uniworks.groupware.common.UserSession;
import org.uniworks.groupware.common.util.DateUtil;
import org.uniworks.groupware.common.util.StringUtil;
import org.uniworks.groupware.domain.Ogan;
import org.uniworks.groupware.domain.OganTree;
import org.uniworks.groupware.service.OganService;

/**
 * @author Park Chungwan
 *
 */
@Controller
@RequestMapping(value = "/rest")
public class OganController {
	private static final Logger logger = LoggerFactory.getLogger(OganController.class);
	@Autowired private OganService oganService;
		
	/**
	 * 파라메타로 넘어오는 조직레벨에 해당하는 조직 목록을 가져온다.
	 * @param oganLev 조직레벨
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/ogan/getByLevelOganList/oganLev/{oganLev}", method = RequestMethod.GET)
	@ResponseBody
	public List<Ogan> getByLevelOganList(@PathVariable("oganLev") String oganLev, HttpServletRequest request) {
		//Session정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		DateUtil dt = new DateUtil();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("oganLev", oganLev);
		map.put("oganEstbDate", dt.getString());
		
		List<Ogan> oganList = oganService.getByLevelOganList(map);
		
		return oganList;
	}	
	
	/**
	 * 파라메타로 넘어오는 조직레벨과 조직코드에 해당하는 하위 조직 목록을 가져온다.
	 * @param oganLev 조직레벨
	 * @param oganCode 조직코드
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/ogan/getBySubOganList/oganLev/{oganLev}/oganCode/{oganCode}", method = RequestMethod.GET)
	@ResponseBody
	public List<Ogan> getBySubOganList(@PathVariable("oganLev") String oganLev, @PathVariable("oganCode") String oganCode,
			HttpServletRequest request) {
		//Session정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		DateUtil dt = new DateUtil();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("oganLev", oganLev);
		map.put("oganCode", oganCode);
		map.put("oganEstbDate", dt.getString());
		
		List<Ogan> oganList = oganService.getBySubOganList(map);
		
		return oganList;
	}
	
	/**
	 * 파라메타로 넘어오는 조직레벨과 조직코드에 해당하는 하위 조직 목록을 가져온다.
	 * @param oganLev 조직레벨
	 * @param oganCode 조직코드
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/ogan/getAllOganList", method = RequestMethod.GET)
	@ResponseBody
	public List<OganTree> getBySubOganTree(HttpServletRequest request) {
		//Session정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		DateUtil dt = new DateUtil();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("oganEstbDate", dt.getString());
		
		List<OganTree> oganList = oganService.getByAllOganTree(map);
		
		return oganList;
	}
	
	/**
	 * 조직 명칭 검색 시에 조직장이 있는 조직만 가져온다.
	 * 검색어에 대한 like 검색.
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/ogan/getOganListByChief/searchWord/{searchWord}", method = RequestMethod.GET)
	@ResponseBody
	public List<Ogan> getOganListByChief(@PathVariable("searchWord") String searchWord, HttpServletRequest request) {
		//Session정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		DateUtil dt = new DateUtil();
		String oganName = StringUtil.null2String(searchWord, "");
		oganName = "%" + oganName + "%";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("oganEstbDate", dt.getString());
		map.put("oganName", oganName);
		
		List<Ogan> oganList = oganService.getOganListByChief(map);
		
		return oganList;
	}
}

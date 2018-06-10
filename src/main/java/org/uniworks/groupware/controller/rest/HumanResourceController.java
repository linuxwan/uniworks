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
import org.uniworks.groupware.domain.HumanResource;
import org.uniworks.groupware.service.HumanResourceService;

/**
 * @author Park Chungwan
 *
 */
@Controller
@RequestMapping(value = "/rest")
public class HumanResourceController {
	private static final Logger logger = LoggerFactory.getLogger(HumanResourceController.class);
	
	@Autowired private HumanResourceService hrService;
	
	/**
	 * 기준 조직레벨 보다 작을 경우에는 조직책임자를 그렇지 않을 경우에는 조직원 모두를 가져온다.
	 * @param oganLev
	 * @param oganCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/hr/getEmpChrgListForOganTree/oganLev/{oganLev}/oganCode/{oganCode}", method = RequestMethod.GET)
	@ResponseBody
	public List<HumanResource> getEmpChrgListForOganTree (@PathVariable("oganLev") String oganLev, @PathVariable("oganCode") String oganCode,
			HttpServletRequest request) {
		//Session정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String baseOganLev = userSession.getBaseOganLev();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());		
		map.put("oganCode", oganCode);		
		map.put("workIndc", "1");		
		
		//baseOganLev보다 조직레벨이 작을 경우에는 보직자만...
		if (Integer.parseInt(oganLev) < Integer.parseInt(baseOganLev)) {
			baseOganLev = oganLev;
			map.put("pstnIndc", "Y");
		} else {	//그렇지 않을 경우 모든 구성원들을 Display
			map.put("pstnIndc", "N");
		}
		map.put("pstnIndc", "N"); //보직자가 아니더라도 가져오도록 함.
		map.put("baseOganLev", baseOganLev);
		
		List<HumanResource> hr = hrService.getByOganLevelEmpList(map);
		
		return hr;
	}
	
	/**
	 * 이름으로 직원 찾기. Like검색을 통해서 검색된 직원 목록을 가져온다.
	 * @param empName
	 * @param workIndc
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/hr/getSearchEmpName/empName/{empName}/workIndc/{workIndc}", method = RequestMethod.GET)
	@ResponseBody
	public List<HumanResource> getSearchEmpName (@PathVariable("empName") String empName, @PathVariable("workIndc") String workIndc,
			HttpServletRequest request) {
		//Session정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("empName", "%" + empName + "%");
		map.put("workIndc", workIndc);
		map.put("baseOganLev", userSession.getBaseOganLev());
		
		List<HumanResource> hr = hrService.getBySearchEmpName(map);
		
		return hr;
	}
}

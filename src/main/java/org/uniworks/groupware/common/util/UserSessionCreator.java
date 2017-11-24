/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.WebUtils;
import org.uniworks.groupware.common.UserSession;
import org.uniworks.groupware.domain.Company;
import org.uniworks.groupware.domain.HumanResource;
import org.uniworks.groupware.domain.Nw100m;

/**
 * @author Park Chungwan
 *
 */
@Controller
public class UserSessionCreator {
	private static final Logger logger = LoggerFactory.getLogger(UserSessionCreator.class);
	
	public static void createUserSession(Nw100m nw100m, Company co, HumanResource hr, HttpServletRequest request) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		if (userSession == null) {
			userSession = new UserSession();
			//사용자 정보를 세션에 설정.
			userSession.setCoId(nw100m.getCoId());	//회사 구분
			userSession.setUserId(nw100m.getUserId());//사용자 ID
			userSession.setPswd(nw100m.getPswd());	//로그인 비밀번호
			userSession.setCnfmPswd(nw100m.getCnfmPswd());	//본인확인 비밀번호
			userSession.setInternalMailAddr(nw100m.getInternalMailAddr());	//사내 메일 주소
			userSession.setOutsideMailAddr(nw100m.getOutsideMailAddr());		//사외 메일 주소
			userSession.setLanguage(request.getLocale().getLanguage());
			
			if (request.getLocale().getCountry() == null || request.getLocale().getCountry() == "") {
				userSession.setLocale(ApplicationConfigReader.get("default.country"));
				userSession.setLowCaseLocale(ApplicationConfigReader.get("default.country"));
			} else {
				userSession.setLocale(request.getLocale().getCountry());
				userSession.setLowCaseLocale(request.getLocale().getCountry());
			}
			//logger.debug("CommonUtil userSession.getLowCaseLocale(): " + userSession.getLowCaseLocale());
			//logger.debug("request getLocale().getCountry() : " + request.getLocale().getCountry());
			//logger.debug("request getLocale().getLanguage() ; " + request.getLocale().getLanguage());
			
			//사용자의 인사정보를 세션에 설정.
			String allOganCode = "";	//소속된 조직코드를 모두 나열, 구분자 ,
			userSession.setEmpName(hr.getEmpNameKor());	//사용자 이름
			userSession.setSysUserId(hr.getSysUserId());	//시스템 사용자 ID
			userSession.setDutyCode(hr.getDutyCode());	//직위 코드
			userSession.setDutyDesc(hr.getDutyDesc());	//직위 명칭
			userSession.setPstnCode(hr.getPstnCode());	//보직 코드
			userSession.setPstnDesc(hr.getPstnDesc());	//보직 명칭
			userSession.setOffcTelNo(hr.getOffcTelNo());	//사무실 전화번호
			userSession.setMoblPhoneNo(hr.getMoblPhoneNo());	//Mobile 전화번호
			userSession.setAsgnOganCode1(hr.getAsgnOganCode1());	//소속조직코드1
			if (hr.getAsgnOganCode1() != null && !hr.getAsgnOganCode1().isEmpty()) allOganCode += hr.getAsgnOganCode1();
			userSession.setAsgnOganDesc1(hr.getAsgnOganDesc1());	//소속조직코드1
			userSession.setAsgnOganCode2(hr.getAsgnOganCode2());	//소속조직코드2
			if (hr.getAsgnOganCode2() != null && !hr.getAsgnOganCode2().isEmpty()) allOganCode = allOganCode + "," + hr.getAsgnOganCode2();
			userSession.setAsgnOganDesc2(hr.getAsgnOganDesc2());	//소속조직코드2
			userSession.setAsgnOganCode3(hr.getAsgnOganCode3());	//소속조직코드3
			if (hr.getAsgnOganCode3() != null && !hr.getAsgnOganCode3().isEmpty()) allOganCode = allOganCode + "," + hr.getAsgnOganCode3();
			userSession.setAsgnOganDesc3(hr.getAsgnOganDesc3());	//소속조직코드3
			userSession.setAsgnOganCode4(hr.getAsgnOganCode4());	//소속조직코드4
			if (hr.getAsgnOganCode4() != null && !hr.getAsgnOganCode4().isEmpty()) allOganCode = allOganCode + "," + hr.getAsgnOganCode4();
			userSession.setAsgnOganDesc4(hr.getAsgnOganDesc4());	//소속조직코드4
			userSession.setAsgnOganCode5(hr.getAsgnOganCode5());	//소속조직코드5
			if (hr.getAsgnOganCode5() != null && !hr.getAsgnOganCode5().isEmpty()) allOganCode = allOganCode + "," + hr.getAsgnOganCode5();
			userSession.setAsgnOganDesc5(hr.getAsgnOganDesc5());	//소속조직코드5
			userSession.setAsgnOganCode6(hr.getAsgnOganCode6());	//소속조직코드6
			if (hr.getAsgnOganCode6() != null && !hr.getAsgnOganCode6().isEmpty()) allOganCode = allOganCode + "," + hr.getAsgnOganCode6();
			userSession.setAsgnOganDesc6(hr.getAsgnOganDesc6());	//소속조직코드6
			userSession.setAsgnOganCode7(hr.getAsgnOganCode7());	//소속조직코드7
			if (hr.getAsgnOganCode7() != null && !hr.getAsgnOganCode7().isEmpty()) allOganCode = allOganCode + "," + hr.getAsgnOganCode7();
			userSession.setAsgnOganDesc7(hr.getAsgnOganDesc7());	//소속조직코드7
			userSession.setAsgnOganCode8(hr.getAsgnOganCode8());	//소속조직코드8
			if (hr.getAsgnOganCode8() != null && !hr.getAsgnOganCode8().isEmpty()) allOganCode = allOganCode + "," + hr.getAsgnOganCode8();
			userSession.setAsgnOganDesc8(hr.getAsgnOganDesc8());	//소속조직코드8
			userSession.setAsgnOganCode9(hr.getAsgnOganCode9());	//소속조직코드9
			if (hr.getAsgnOganCode9() != null && !hr.getAsgnOganCode9().isEmpty()) allOganCode = allOganCode + "," + hr.getAsgnOganCode9();
			userSession.setAsgnOganDesc9(hr.getAsgnOganDesc9());	//소속조직코드9
			userSession.setAsgnOganCode10(hr.getAsgnOganCode10());	//소속조직코드10
			if (hr.getAsgnOganCode10() != null && !hr.getAsgnOganCode10().isEmpty()) allOganCode = allOganCode + "," + hr.getAsgnOganCode10();
			userSession.setAsgnOganDesc10(hr.getAsgnOganDesc10());	//소속조직코드10
			userSession.setAsgnOganCode11(hr.getAsgnOganCode11());	//소속조직코드11
			if (hr.getAsgnOganCode11() != null && !hr.getAsgnOganCode11().isEmpty()) allOganCode = allOganCode + "," + hr.getAsgnOganCode11();
			userSession.setAsgnOganDesc11(hr.getAsgnOganDesc11());	//소속조직코드11
			userSession.setAsgnOganCode12(hr.getAsgnOganCode12());	//소속조직코드12
			if (hr.getAsgnOganCode12() != null && !hr.getAsgnOganCode12().isEmpty()) allOganCode = allOganCode + "," + hr.getAsgnOganCode12();
			userSession.setAsgnOganDesc12(hr.getAsgnOganDesc12());	//소속조직코드12
			userSession.setBaseOganLev(co.getBaseOganLev()); 		//기준조직레벨
			userSession.setDeptCode(UserSessionCreator.getBaseAsgnOganLevelCode(hr, co));
			userSession.setDeptDesc(UserSessionCreator.getBaseAsgnOganLevelDesc(hr, co));
			userSession.setAllOganCode(allOganCode);
			//HttpSession 생성
			HttpSession session = request.getSession(true);
			// HttpSeesion Timeout 설정 (12시간 유지로 설정 - 시간 * 분 * 초)
			// application.properties 파일에서 세션 타임아웃 시간을 가져온다.
			String strSessionTimeout = ApplicationConfigReader.get("session.timeout");
			Integer timeout = Integer.parseInt(strSessionTimeout);
			session.setMaxInactiveInterval(timeout.intValue() * 60 * 60);
			session.setAttribute("userSession", userSession);
		}
	}
	
	/**
	 * 쿠키를 생성한다.
	 * @param request
	 * @param response
	 * @param key Cookie 변수명
	 * @param value Cookie 
	 */
	public static void createCookie(HttpServletRequest request, HttpServletResponse response, String key, String value) {
		Cookie cookie = new Cookie(key, value);
		cookie.setMaxAge(60 * 60 * 24);
		response.addCookie(cookie);
	}
	
	/**
	 * 쿠키 정보를 가져온다.
	 * @param request
	 * @param key Cookie 변수명
	 * @return Cookie 변수명에 저장된 쿠키 값을 반환
	 */
	public static String getCookie(HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;		
		String userId = null;

		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookie = cookies[i];
				if (cookie.getName().equals(key)) userId = (String) cookie.getValue();
			}
		}
		
		return userId;
	}
	
	/**
	 * 가지고 온 인사정보에서 기준 부서의 명칭을 가져오는 함수. 통상적으로 기준이 되는 조직(부서)가 최소 3~6까지 범위를 가지는 것으로 가정.
	 * 향 후 3~6까지의 범위가 벗어나는 경우 추가를 해야 함.
	 * @param hr
	 * @return
	 */
	public static String getBaseAsgnOganLevelDesc(HumanResource hr, Company cm) {
		String deptDesc = null;
		Integer baseOganLev = new Integer(cm.getBaseOganLev());
		Integer crntOganLev = new Integer(hr.getBaseAsgnOganLev());
		String methodName = "";
		
		Method[] methods = hr.getClass().getMethods();
		
		if (crntOganLev < baseOganLev) {
			methodName = "getAsgnOganDesc" + hr.getBaseAsgnOganLev();			
		} else if (crntOganLev >= baseOganLev) {
			methodName = "getAsgnOganDesc" + cm.getBaseOganLev();			
		}
		
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(methodName)) {
				try {
					deptDesc = (String) methods[i].invoke(hr);
				} catch (IllegalAccessException lae) {
					
				} catch (InvocationTargetException ite) {
					
				}
			}
		}
		
		//invokeMethodName(methodName, hr);
		return deptDesc;
	}
	
	/**
	 * 가지고 온 인사정보에서 기준 부서의 코드를 가져오는 함수. 통상적으로 기준이 되는 조직(부서)가 최소 3~6까지 범위를 가지는 것으로 가정.
	 * 향 후 3~6까지의 범위가 벗어나는 경우 추가를 해야 함.
	 * @param hr
	 * @return
	 */
	public static String getBaseAsgnOganLevelCode(HumanResource hr, Company cm) {
		String deptCode = null;
		Integer baseOganLev = new Integer(cm.getBaseOganLev());
		Integer crntOganLev = new Integer(hr.getBaseAsgnOganLev());
		String methodName = "";
		
		Method[] methods = hr.getClass().getMethods();
		
		if (crntOganLev < baseOganLev) {
			methodName = "getAsgnOganCode" + hr.getBaseAsgnOganLev();			
		} else if (crntOganLev >= baseOganLev) {
			methodName = "getAsgnOganCode" + cm.getBaseOganLev();			
		}
		
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(methodName)) {
				try {
					deptCode = (String) methods[i].invoke(hr);
				} catch (IllegalAccessException lae) {
					
				} catch (InvocationTargetException ite) {
					
				}
			}
		}
		
		return deptCode;
	}
	
	public static String invokeMethodName(String methodName, HumanResource hr) {
		String returnStr = "";
		
		Method[] methods = hr.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(methodName)) {
				try {
					returnStr = (String) methods[i].invoke(hr);
				} catch (IllegalAccessException lae) {
					
				} catch (InvocationTargetException ite) {
					
				}
			}
		}
		return returnStr;
	}
}

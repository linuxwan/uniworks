/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.controller.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import org.uniworks.groupware.common.UserSession;
import org.uniworks.groupware.domain.approval.LineApprover;

/**
 * @author Park Chungwan
 *
 */
@Controller
public class ApprovalPopupController {
	private static final Logger logger = LoggerFactory.getLogger(ApprovalPopupController.class);
	
	/**
	 * Line 결재자 선택 팝업 창을 Display한다.
	 * @param request
	 * @return
	 */
	@RequestMapping("approval/popup/selectLineApprovers")
	public ModelAndView selectLineApprovers(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("common/popup/line_approver_select_form_01");
		
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String apprLevel = request.getParameter("apprLevel");
		String coId = userSession.getCoId();
		if (apprLevel == null) apprLevel = "0";
		
		ArrayList<LineApprover> arrApprLine = new ArrayList<LineApprover>();
		for (int i = 0; i < Integer.parseInt(apprLevel); i++) {
			arrApprLine.add(new LineApprover());
		}
		
		mav.addObject("userSession", userSession);
		mav.addObject("coId", coId);
		mav.addObject("apprLevel", apprLevel);
		mav.addObject("apprLineList", arrApprLine);		
		return mav;
	}
	
	/**
	 * 수신처/참조처 팝업 창을 Display한다.
	 * @param request
	 * @return
	 */
	@RequestMapping("approval/popup/selectRcptRfnc")
	public ModelAndView selectRcptApprovers(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("common/popup/rcpt_rfnc_approver_select_form_01");
		
		//Session정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String apprLevel = request.getParameter("apprLevel");
		String coId = userSession.getCoId();
		if (apprLevel == null) apprLevel = "0";
		
		ArrayList<LineApprover> arrApprLine = new ArrayList<LineApprover>();
		for (int i = 0; i < Integer.parseInt(apprLevel); i++) {
			arrApprLine.add(new LineApprover());
		}
		
		mav.addObject("userSession", userSession);
		mav.addObject("coId", coId);
		mav.addObject("apprLevel", apprLevel);
		mav.addObject("apprLineList", arrApprLine);
		return mav;
	}
	
	/**
	 * 협조결재자 선택을 위한 팝업 창을 Display한다.
	 * @param request
	 * @return
	 */
	@RequestMapping("approval/popup/selectCprtApprovers")
	public ModelAndView selectCprtApprovers(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("common/popup/cprt_approver_select_form_01");
		
		//Session정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String apprLevel = request.getParameter("apprLevel");
		String coId = userSession.getCoId();
		if (apprLevel == null) apprLevel = "0";
		
		ArrayList<LineApprover> arrApprLine = new ArrayList<LineApprover>();
		for (int i = 0; i < Integer.parseInt(apprLevel); i++) {
			arrApprLine.add(new LineApprover());
		}
		
		mav.addObject("userSession", userSession);
		mav.addObject("coId", coId);
		mav.addObject("apprLevel", apprLevel);
		mav.addObject("cprtLineList", arrApprLine);
		return mav;
	}
}

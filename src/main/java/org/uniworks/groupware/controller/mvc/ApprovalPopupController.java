/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.controller.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import org.uniworks.groupware.common.UserSession;
import org.uniworks.groupware.common.util.StringUtil;
import org.uniworks.groupware.domain.approval.ApprovalMaster;
import org.uniworks.groupware.domain.approval.LineApprover;
import org.uniworks.groupware.service.ApprovalService;

/**
 * @author Park Chungwan
 *
 */
@Controller
public class ApprovalPopupController {
	@Autowired ApprovalService apprService;
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
		String apprMstId = StringUtil.null2void(request.getParameter("apprMstId"));
		if (apprLevel == null) apprLevel = "0";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", coId);
		map.put("apprMstId", apprMstId);
		map.put("lang", userSession.getLanguage());
		ApprovalMaster apprMst = apprService.getApprovalMasterInfo(map);
		
		String outHeight = "220px";
		String inHeight = "155px";
		if (apprMst.getRcptIndc().equalsIgnoreCase("Y") && apprMst.getRfncIndc().equalsIgnoreCase("Y")) {
			outHeight = "220px";
			inHeight = "155px";
		} else {
			outHeight = "440px";
			inHeight = "380px";
		}
		
		ArrayList<LineApprover> arrApprLine = new ArrayList<LineApprover>();
		for (int i = 0; i < Integer.parseInt(apprLevel); i++) {
			arrApprLine.add(new LineApprover());
		}
		mav.addObject("apprMst", apprMst);
		mav.addObject("outHeight", outHeight);
		mav.addObject("inHeight", inHeight);
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
	
	/**
	 * 등록된 결재선 목록을 가져온다.
	 * @param request
	 * @return
	 */
	@RequestMapping("/approval/popup/personal_line_appr")
	public ModelAndView selectPersonalLineAppr(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("common/popup/personal_line_appr_01");
		//Session정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CO_ID", userSession.getCoId());
		map.put("USER_ID", userSession.getUserId());
				
		mav.addObject("userSession", userSession);
		return mav;
	}
}

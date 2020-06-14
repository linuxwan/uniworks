/**
 * 박충완(gomoosin)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.controller.mvc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import org.uniworks.groupware.common.HiddenField;
import org.uniworks.groupware.common.UserSession;
import org.uniworks.groupware.common.util.DateUtil;
import org.uniworks.groupware.common.util.StringUtil;
import org.uniworks.groupware.domain.CommonCode;
import org.uniworks.groupware.domain.board.BoardMaster;
import org.uniworks.groupware.service.BoardService;
import org.uniworks.groupware.service.CommonService;

/**
 * @author gomoosin
 *
 */
@Controller
public class BoardCaseAController {
	private static final Logger logger = LoggerFactory.getLogger(BoardCaseAController.class);
	@Autowired CommonService commonService;
	@Autowired BoardService boardService;
	
	/**
	 * 게시판 목록
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "board/board_list_01", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView boardListA(@ModelAttribute("param") HiddenField param, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("board/board_list_01");
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		Map<String, Object> map = new HashMap<String, Object>();
		//지원 언어 목록 체크
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("majCode", "CD022"); //지원언어가 저장되어져 있는 주코드 CD001
		map.put("orderBy", "rescKey");	//코드 정렬 방법 셋팅
		List<CommonCode> searchItemList = commonService.getCommonSubCodeList(map);	
		
		mav.addObject("searchItemList", searchItemList);
		mav.addObject("cntnName", param.getCntnName());
		return mav;
	}
	
	/**
	 * 게시판 작성 화면
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "board/board_write_form_01", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView boardWriteForm01(@ModelAttribute("param") HiddenField param, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("board/board_write_01");
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int docWriteNo = StringUtil.null2zeroint(request.getParameter("docWriteNo"));
		DateUtil crntDate = new DateUtil();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("crntDate", crntDate.getString());
				
		BoardMaster boardMaster = boardService.selectBoardMasterInfo(map);

		map.put("majCode", "CD008");
		map.put("orderBy", "rescKey");
		List<CommonCode> commonCodeList = commonService.getCommonSubCodeList(map);
		
		mav.addObject("docWriteNo", docWriteNo);
		mav.addObject("boardMst", boardMaster);	
		mav.addObject("userSession", userSession);
		mav.addObject("serviceLife", commonCodeList);	//보존연한
		return mav;
	}
}

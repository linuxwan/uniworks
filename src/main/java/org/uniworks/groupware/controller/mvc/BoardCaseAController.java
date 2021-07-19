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
import org.uniworks.groupware.domain.Nw115m;
import org.uniworks.groupware.domain.board.BoardDoc;
import org.uniworks.groupware.domain.board.BoardMaster;
import org.uniworks.groupware.service.BoardService;
import org.uniworks.groupware.service.CommonService;
import org.uniworks.groupware.service.Nw115mService;

/**
 * @author gomoosin
 *
 */
@Controller
public class BoardCaseAController {
	private static final Logger logger = LoggerFactory.getLogger(BoardCaseAController.class);
	@Autowired CommonService commonService;
	@Autowired BoardService boardService;
	@Autowired Nw115mService nw115mService;
	
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
		
		DateUtil crntDate = new DateUtil();
		map.put("crntDate", crntDate.getString());
		map.put("cntnId", param.getCntnId());
				
		BoardMaster boardMaster = boardService.selectBoardMasterInfo(map);
		//구분 필드 표시 여부 체크
		if (!boardMaster.getTypeCode1().isEmpty() || !boardMaster.getTypeCode2().isEmpty() ||
			!boardMaster.getTypeCode3().isEmpty() || !boardMaster.getTypeCode4().isEmpty()) {
			mav.addObject("gubun", "Y");
		} else {
			mav.addObject("gubun", "N");
		}
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
		map.put("cntnId", param.getCntnId());
				
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
	
	/**
	 * 게시판 조회 화면
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "board/board_view_form_01", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView boardViewForm01(@ModelAttribute("param") HiddenField param, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("board/board_view_01");
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String cntnId = StringUtil.null2void(request.getParameter("cntnId"));
		String dcmtRgsrNo = StringUtil.null2void(request.getParameter("dcmtRgsrNo"));
		
		if (!cntnId.isEmpty()) param.setCntnId(cntnId);
		
		DateUtil crntDate = new DateUtil();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("crntDate", crntDate.getString());
		map.put("cntnId", cntnId);
		map.put("dcmtRgsrNo", dcmtRgsrNo);
				
		BoardMaster boardMaster = boardService.selectBoardMasterInfo(map);

		map.put("majCode", "CD008");
		map.put("orderBy", "rescKey");
		List<CommonCode> commonCodeList = commonService.getCommonSubCodeList(map);		
		
		BoardDoc boardDoc = boardService.selectBoardByPrimaryKey(map);
		
		//첨부파일 가져오기
		List<Nw115m> attachList = getAttachFileList(map);
		String strAttachList = getAttachFileListToString(map);
		
		mav.addObject("doc", boardDoc);
		mav.addObject("boardMst", boardMaster);
		mav.addObject("attachList", attachList);
		mav.addObject("strAttachList", strAttachList);
		mav.addObject("userSession", userSession);
		mav.addObject("serviceLife", commonCodeList);	//보존연한
		return mav;
	}
	/**
	 * 사용자 검색 팝업창 호출
	 * @param param
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/board/popup/selectLineApprover", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView boardSelectLineApprover(@ModelAttribute("param") HiddenField param, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("board/select_approver_form_01");
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");		
		
		Map<String, Object> map = new HashMap<String, Object>();
		//지원 언어 목록 체크
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("majCode", "CD019"); //지원언어가 저장되어져 있는 주코드 CD001
		map.put("orderBy", "rescKey");	//코드 정렬 방법 셋팅
		List<CommonCode> sortTypeList = commonService.getCommonSubCodeList(map);	
		
		mav.addObject("searchTypeList", sortTypeList);
		mav.addObject("userSession", userSession);

		return mav;
	}
	
	/**
	 * 첨부파일 목록을 가져와서 attachList에 할당할 문자열을 반환
	 * fileId^attchFileName^fileSize^tempIndc으로 |으로 구분.
	 * @param map
	 * @return
	 */
	private String getAttachFileListToString(Map<String, Object> map) {
		String attachList = "";
		List<Nw115m> fileList = getAttachFileList(map);
		
		Nw115m attachFile = null;
		for (int i = 0; i < fileList.size(); i++) {
			attachFile = fileList.get(i);
			if (i > 0) attachList += "|";
			attachList += attachFile.getFileId() + "^" + attachFile.getAttchFileName() + "^" + attachFile.getFileSize() + "^N";
		}
		
		return attachList;
	}
	
	/**
	 * 첨부파일 목록 가져오기
	 * @param map
	 * @return
	 */
	private List<Nw115m> getAttachFileList(Map<String, Object> map) {
		List<Nw115m> fileList = nw115mService.getNw115mList(map);
		return fileList;
	}
}

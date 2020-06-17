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
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;
import org.uniworks.groupware.common.UserSession;
import org.uniworks.groupware.common.util.AttachFileUtil;
import org.uniworks.groupware.common.util.CommUtil;
import org.uniworks.groupware.common.util.DateUtil;
import org.uniworks.groupware.common.util.WebUtil;
import org.uniworks.groupware.domain.Nw115m;
import org.uniworks.groupware.domain.Nw130m;
import org.uniworks.groupware.domain.board.BoardDoc;
import org.uniworks.groupware.service.BoardService;
import org.uniworks.groupware.service.FileService;
import org.uniworks.groupware.service.Nw130mService;

/**
 * @author Park Chung Wan
 *
 */
@Controller
@RequestMapping(value = "/rest")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired BoardService boardService;	
	@Autowired Nw130mService nw130mService;
	@Autowired private FileService fileService;
	@Autowired private MessageSource messageSource;
		
	@GetMapping(value = "/board/board_list_01/cntnId/{cntnId}/startDate/{startDate}/finishDate/{finishDate}/searchType/{searchType}/searchWord/{searchWord}")
	public ResponseEntity<List<BoardDoc>> approvalWritingList(@PathVariable("cntnId") String cntnId, @PathVariable("startDate") String startDate,
				@PathVariable("finishDate") String finishDate, @PathVariable("searchType") String searchType, @PathVariable("searchWord") String searchWord,
				HttpServletRequest request) {
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String tempStartDate = startDate.replace("-", "");
		String tempFinishDate = finishDate.replace("-", "");
		
		if (searchWord.equalsIgnoreCase("null")) searchWord = "%";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("cntnId", cntnId);
		map.put("startDate", tempStartDate);
		map.put("finishDate", tempFinishDate);
		map.put("searchType", searchType);
		map.put("searchWord", searchWord);
		
		List<BoardDoc> boardList = boardService.selectBoardList(map);
		
		return new ResponseEntity<List<BoardDoc>>(boardList, HttpStatus.OK);
	}
	
	/**
	 * 관리자 정보를 등록한다.
	 * @param cm010c
	 * @param ucBuilder
	 * @return
	 */
	@PostMapping(value = "/board/create")
	public ResponseEntity<String> createBoard(@RequestBody Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
		String result = "";
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		
		Nw130m nw130m = new Nw130m();
		WebUtil.bind(model, nw130m);
		
		nw130m.setDcmtRgsrDatetime(DateUtil.getCurrentDate());
		nw130m.setDcmtRgsrNo(CommUtil.createSequenceNo("B"));
								
		request.setAttribute("cntnId", nw130m.getCntnId());
		request.setAttribute("attachList", model.get("attachList"));
		request.setAttribute("filePathType", model.get("filePathType"));
		List<Nw115m> attachFileList = AttachFileUtil.setAttachFileList(model, nw130m.getDcmtRgsrNo(), fileService);
		
		int cnt = boardService.addBoardDocument(nw130m, attachFileList);
		
		if (cnt > 0) {
			result = messageSource.getMessage("resc.msg.addOk", null, response.getLocale());			
		} else {
			result = messageSource.getMessage("resc.msg.addFail", null, response.getLocale());
		}				
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}

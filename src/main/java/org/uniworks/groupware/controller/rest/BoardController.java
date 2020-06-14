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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;
import org.uniworks.groupware.common.UserSession;
import org.uniworks.groupware.common.util.DateUtil;
import org.uniworks.groupware.domain.board.BoardDoc;
import org.uniworks.groupware.service.BoardService;

/**
 * @author Park Chung Wan
 *
 */
@Controller
@RequestMapping(value = "/rest")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired BoardService boardService;	
		
	@GetMapping(value = "/board/board_list_01/cntnId/{cntnId}/startDate/{startDate}/finishDate/{finishDate}/searchType/{searchType}/searchWord/{searchWord}")
	public ResponseEntity<List<BoardDoc>> approvalWritingList(@PathVariable("cntnId") String cntnId, @PathVariable("startDate") String startDate,
				@PathVariable("finishDate") String finishDate, @PathVariable("searchType") String searchType, @PathVariable("searchWord") String searchWord,
				HttpServletRequest request) {
		//Session 정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		DateUtil tempStartDate = new DateUtil(startDate.replace("-", ""));
		DateUtil tempFinishDate = new DateUtil(finishDate.replace("-", ""));
		
		if (searchWord.equalsIgnoreCase("null")) searchWord = "%";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", userSession.getCoId());
		map.put("lang", userSession.getLanguage());
		map.put("cntnId", cntnId);
		map.put("startDate", tempStartDate.getDate());
		map.put("finishDate", tempFinishDate.getDate());
		map.put("searchType", searchType);
		map.put("searchWord", searchWord);
		
		List<BoardDoc> boardList = boardService.selectBoardList(map);
		
		return new ResponseEntity<List<BoardDoc>>(boardList, HttpStatus.OK);
	}
}

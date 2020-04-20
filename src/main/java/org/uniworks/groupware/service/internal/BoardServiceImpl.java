/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.service.internal;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uniworks.groupware.domain.board.BoardDoc;
import org.uniworks.groupware.mapper.BoardMapper;
import org.uniworks.groupware.service.BoardService;

/**
 * @author Park Chung Wan
 *
 */
@Service
@Transactional(readOnly = true) 
public class BoardServiceImpl implements BoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardServiceImpl.class);
	@Autowired BoardMapper boardMapper;
	/**
	 * 게시판 목록 가져오기
	 * @param map
	 * @return
	 */
	@Override	
	public List<BoardDoc> selectBoardList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return boardMapper.selectBoardList(map);
	}
}

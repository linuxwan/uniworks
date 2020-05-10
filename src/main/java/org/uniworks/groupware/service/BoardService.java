/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.service;

import java.util.List;
import java.util.Map;

import org.uniworks.groupware.domain.board.BoardDoc;
import org.uniworks.groupware.domain.board.BoardMaster;

/**
 * @author Park Chung Wan
 *
 */
public interface BoardService {
	/**
	 * 게시판 마스터 정보 가져오기
	 * @param map
	 * @return
	 */
	BoardMaster selectBoardMasterInfo(Map<String, Object> map);
	/**
	 * 게시판 목록 가져오기
	 * @param map
	 * @return
	 */
	List<BoardDoc> selectBoardList(Map<String, Object> map);
}

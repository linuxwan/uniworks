/** 
 * 박충완(Park Chungwan)이 작성한 코드 입니다. 
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다. 
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.	
 */ 
package org.uniworks.groupware.mapper; 

import java.util.List; 
import java.util.Map; 

import org.uniworks.groupware.domain.Nw120m; 


/** 
 * @author Park Chungwan 
 * 관련 테이블 : Nw120m
 */ 
public interface Nw120mMapper { 
	/**
	 * Max SeqNo 가져오기
	 * @param map
	 * @return
	 */
	int selectMaxSeqNo(Map<String, Object> map);
	/** 
	 * 목록 가져오기 
	 * @param map 
	 * @return 
	 */ 
	List<Nw120m> select(Map<String, Object> map); 
	/** 
	 * 정보 가져오기 
	 * @param map 
	 * @return 
	 */ 
	Nw120m selectByPrimaryKey(Map<String, Object> map); 
	/** 
	 * 등록한다 
	 * @param nw120m 
	 * @return 
	 */ 
	int insert(Nw120m nw120m); 
	/**
	 * 등록한다.
	 * @param map
	 * @return
	 */
	int insertMap(Map<String, Object> map);
	/** 
	 * 수정한다 
	 * @param nw120m 
	 * @return 
	 */ 
	int updateByPrimaryKey(Nw120m nw120m); 
	/**
	 * 수정한다 
	 * @param map
	 * @return
	 */
	int updateByPrimaryKeyMap(Map<String, Object> map);
	/** 
	 * 삭제한다 
	 * @param map 
	 * @return 
	 */ 
	int deleteByPrimaryKey(Map<String, Object> map); 
} 

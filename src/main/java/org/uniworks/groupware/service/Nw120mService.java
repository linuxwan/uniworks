/** 
 * 박충완(Park Chungwan)이 작성한 코드 입니다. 
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다. 
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.	
 */ 
package org.uniworks.groupware.service; 

import java.util.List;
import java.util.Map;

import org.uniworks.groupware.domain.Nw120m; 

public interface Nw120mService { 
	/**
	 * 가장 큰 일련번호를 가져온다.
	 * @param map
	 * @return
	 */
	int getMaxSeqNo(Map<String, Object> map);
	/** 
	 * 목록을 조회한다. 
	 * 
	 * @return 
	 */ 
	List<Nw120m> getNw120mList(java.util.Map<String,Object> map); 

	/** 
	 * 조회한다. 
	 * 
	 * @return 
	 */ 
	Nw120m getNw120m(java.util.Map<String,Object> map); 

	/** 
	 * 등록한다. 
	 * 
	 * @return 
	 */ 
	int addNw120m(Nw120m nw120m); 
	/**
	 * 등록한다. 
	 * @param map
	 * @return
	 */
	int addNw120mMap(java.util.Map<String,Object> map);
	/** 
	 * 수정한다. 
	 * 
	 * @return 
	 */ 
	int updateNw120m(Nw120m nw120m); 
	/**
	 * 수정한다. 
	 * @param map
	 * @return
	 */
	int updateNw120mMap(java.util.Map<String,Object> map);
	/** 
	 * 삭제한다. 
	 * 
	 * @return 
	 */ 
	int deleteNw120m(java.util.Map<String,Object> map); 
} 

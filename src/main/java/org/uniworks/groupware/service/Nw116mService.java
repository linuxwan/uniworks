/** 
 * 박충완(Park Chungwan)이 작성한 코드 입니다. 
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다. 
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.	
 */ 
package org.uniworks.groupware.service; 

import java.util.List; 

import org.uniworks.groupware.domain.Nw116m; 

public interface Nw116mService { 
	/** 
	 * 목록을 조회한다. 
	 * 
	 * @return 
	 */ 
	List<Nw116m> getNw116mList(java.util.Map<String,Object> map); 

	/** 
	 * 조회한다. 
	 * 
	 * @return 
	 */ 
	Nw116m getNw116m(java.util.Map<String,Object> map); 

	/** 
	 * 등록한다. 
	 * 
	 * @return 
	 */ 
	int addNw116m(Nw116m nw116m); 

	/** 
	 * 수정한다. 
	 * 
	 * @return 
	 */ 
	int updateNw116m(Nw116m nw116m); 

	/** 
	 * 삭제한다. 
	 * 
	 * @return 
	 */ 
	int deleteNw116m(java.util.Map<String,Object> map); 
} 

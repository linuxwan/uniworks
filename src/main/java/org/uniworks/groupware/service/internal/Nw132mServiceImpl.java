/** 
 * 박충완(Park Chungwan)이 작성한 코드 입니다. 
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다. 
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.	
 */ 
package org.uniworks.groupware.service.internal; 

import java.util.List; 
import java.util.Map; 

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service; 
import org.springframework.transaction.annotation.Propagation; 
import org.springframework.transaction.annotation.Transactional; 

import org.uniworks.groupware.domain.Nw132m; 
import org.uniworks.groupware.mapper.Nw132mMapper; 
import org.uniworks.groupware.service.Nw132mService; 

@Service 
@Transactional(readOnly = true) 
public class Nw132mServiceImpl implements Nw132mService { 
	@Autowired Nw132mMapper nw132mMapper; 

	/** 
	 * 목록을 조회한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	public List<Nw132m> getNw132mList(Map<String, Object> map) { 
		return nw132mMapper.select(map); 
	} 
	/** 
	 * 조회한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	public Nw132m getNw132m(Map<String, Object> map) { 
		return nw132mMapper.selectByPrimaryKey(map); 
	} 
	/** 
	 * 등록한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public int addNw132m(Nw132m nw132m) { 
		return nw132mMapper.insert(nw132m); 
	} 
	/** 
	 * 수정한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public int updateNw132m(Nw132m nw132m) { 
		return nw132mMapper.updateByPrimaryKey(nw132m); 
	} 
	/** 
	 * 삭제한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public int deleteNw132m(Map<String, Object> map) { 
		return nw132mMapper.deleteByPrimaryKey(map); 
	} 
} 

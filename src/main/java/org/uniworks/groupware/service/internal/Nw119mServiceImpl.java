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

import org.uniworks.groupware.domain.Nw119m; 
import org.uniworks.groupware.mapper.Nw119mMapper; 
import org.uniworks.groupware.service.Nw119mService; 

@Service 
@Transactional(readOnly = true) 
public class Nw119mServiceImpl implements Nw119mService { 
	@Autowired Nw119mMapper nw119mMapper; 

	/** 
	 * 목록을 조회한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	public List<Nw119m> getNw119mList(Map<String, Object> map) { 
		return nw119mMapper.select(map); 
	} 
	/** 
	 * 조회한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	public Nw119m getNw119m(Map<String, Object> map) { 
		return nw119mMapper.selectByPrimaryKey(map); 
	} 
	/** 
	 * 등록한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public int addNw119m(Nw119m nw119m) { 
		return nw119mMapper.insert(nw119m); 
	} 
	/** 
	 * 수정한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public int updateNw119m(Nw119m nw119m) { 
		return nw119mMapper.updateByPrimaryKey(nw119m); 
	} 
	/** 
	 * 삭제한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public int deleteNw119m(Map<String, Object> map) { 
		return nw119mMapper.deleteByPrimaryKey(map); 
	} 
} 

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

import org.uniworks.groupware.domain.Nw014m; 
import org.uniworks.groupware.mapper.Nw014mMapper; 
import org.uniworks.groupware.service.Nw014mService; 

@Service 
@Transactional(readOnly = true) 
public class Nw014mServiceImpl implements Nw014mService { 
	@Autowired Nw014mMapper nw014mMapper; 

	/** 
	 * 목록을 조회한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	public List<Nw014m> getNw014mList(Map<String, Object> map) { 
		return nw014mMapper.select(map); 
	} 
	/** 
	 * 조회한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	public Nw014m getNw014m(Map<String, Object> map) { 
		return nw014mMapper.selectByPrimaryKey(map); 
	} 
	/** 
	 * 등록한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public int addNw014m(Nw014m nw014m) { 
		return nw014mMapper.insert(nw014m); 
	} 
	/** 
	 * 수정한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public int updateNw014m(Nw014m nw014m) { 
		return nw014mMapper.updateByPrimaryKey(nw014m); 
	} 
	/** 
	 * 삭제한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public int deleteNw014m(Map<String, Object> map) { 
		return nw014mMapper.deleteByPrimaryKey(map); 
	} 
} 

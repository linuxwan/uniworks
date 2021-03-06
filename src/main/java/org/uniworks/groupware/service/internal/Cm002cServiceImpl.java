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

import org.uniworks.groupware.domain.Cm002c; 
import org.uniworks.groupware.mapper.Cm002cMapper; 
import org.uniworks.groupware.service.Cm002cService; 

@Service 
@Transactional(readOnly = true) 
public class Cm002cServiceImpl implements Cm002cService { 
	@Autowired Cm002cMapper cm002cMapper; 

	/** 
	 * 목록을 조회한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	public List<Cm002c> getCm002cList(Map<String, Object> map) { 
		return cm002cMapper.select(map); 
	} 
	/** 
	 * 조회한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	public Cm002c getCm002c(Map<String, Object> map) { 
		return cm002cMapper.selectByPrimaryKey(map); 
	} 
	/** 
	 * 등록한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public int addCm002c(Cm002c cm002c) { 
		return cm002cMapper.insert(cm002c); 
	} 
	/** 
	 * 수정한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public int updateCm002c(Cm002c cm002c) { 
		return cm002cMapper.updateByPrimaryKey(cm002c); 
	} 
	/** 
	 * 삭제한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public int deleteCm002c(Map<String, Object> map) { 
		return cm002cMapper.deleteByPrimaryKey(map); 
	} 
} 

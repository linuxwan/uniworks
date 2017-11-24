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

import org.uniworks.groupware.domain.Nw113m; 
import org.uniworks.groupware.mapper.Nw113mMapper; 
import org.uniworks.groupware.service.Nw113mService; 

@Service 
@Transactional(readOnly = true) 
public class Nw113mServiceImpl implements Nw113mService { 
	@Autowired Nw113mMapper nw113mMapper; 

	/** 
	 * 목록을 조회한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	public List<Nw113m> getNw113mList(Map<String, Object> map) { 
		return nw113mMapper.select(map); 
	} 
	/** 
	 * 조회한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	public Nw113m getNw113m(Map<String, Object> map) { 
		return nw113mMapper.selectByPrimaryKey(map); 
	} 
	/** 
	 * 등록한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public int addNw113m(Nw113m nw113m) { 
		return nw113mMapper.insert(nw113m); 
	} 
	/** 
	 * 수정한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public int updateNw113m(Nw113m nw113m) { 
		return nw113mMapper.updateByPrimaryKey(nw113m); 
	} 
	/** 
	 * 삭제한다. 
	 * 
	 * @return 
	 */ 
	@Override 
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED) 
	public int deleteNw113m(Map<String, Object> map) { 
		return nw113mMapper.deleteByPrimaryKey(map); 
	} 
} 

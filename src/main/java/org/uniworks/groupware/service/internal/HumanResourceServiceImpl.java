/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.service.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.uniworks.groupware.domain.Hr010m;
import org.uniworks.groupware.domain.Hr011m;
import org.uniworks.groupware.domain.HumanResource;
import org.uniworks.groupware.mapper.Hr010mMapper;
import org.uniworks.groupware.mapper.Hr011mMapper;
import org.uniworks.groupware.mapper.HumanResourceMapper;
import org.uniworks.groupware.service.HumanResourceService;

/**
 * @author Park Chungwan
 *
 */
@Service
@Transactional(readOnly = true) 
public class HumanResourceServiceImpl implements HumanResourceService {
	@Autowired HumanResourceMapper hrMapper;
	@Autowired Hr010mMapper hr010mMapper;
	@Autowired Hr011mMapper hr011mMapper;
	
	/**
	 * 인사마스터 정보에서 직원 정보를 가져온다.
	 * @param map
	 * @return
	 */
	@Override
	public HumanResource getByHumanResource(Map<String, Object> map) {
		// TODO Auto-generated method stub
		HumanResource hr = hrMapper.selectByHumanResource(map);
		return hr;
	}

	/**
	 * 인사마스터 정보에서 직원정보를 가져온다. 기본 조직정보 포함.
	 * @param map
	 * @return
	 */
	@Override
	public HumanResource getEmployeeInfoByEmpNo(Map<String, Object> map) {
		// TODO Auto-generated method stub
		HumanResource hr = hrMapper.selectEmployeeInfoByEmpNo(map);
		return hr;
	}

	/**
	 * 특정 조직의 속한 직원 모두를 가져온다. BaseOganLev의 값보다 작은 상위 조직의 경우에는 보직자만 가져온다.
	 * @param map
	 * @return
	 */
	@Override
	public List<HumanResource> getByOganLevelEmpList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<HumanResource> hrList = hrMapper.selectByOganLevelEmpList(map);
		return hrList;
	}

	/**
	 * 성명으로 직원 찾기
	 * @param map
	 * @return
	 */
	@Override
	public List<HumanResource> getBySearchEmpName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<HumanResource> hrList = hrMapper.selectBySearchEmpName(map);
		return hrList;
	}
		
	/**
	 * 사번으로 직원 정보 가져오기
	 * @param map
	 * @return
	 */
	@Override
	public HumanResource getBySearchEmpNo(Map<String, Object> map) {
		return hrMapper.selectBySearchEmpNo(map);
	}

	/**
	 * 회사별 소속 직원 정보 등록.
	 * @param record
	 */
	@Override
	public void addEmpInfo(Hr010m record, ArrayList<Hr011m> arr) {
		// TODO Auto-generated method stub
		hr010mMapper.insert(record);
		
		// Hr011m 테이블의 정보를 등록
		addHr011m(arr);
	}
	
	/**
	 * Hr011m 테이블에 언어별 이름을 insert한다.
	 * @param arr
	 */
	private void addHr011m(ArrayList<Hr011m> arr) {
		for (int i = 0; i < arr.size(); i++) {
			Hr011m hr011m = arr.get(i);
			hr011mMapper.insert(hr011m);
		}
	}
}

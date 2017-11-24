/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.service;

import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.uniworks.groupware.common.config.AppConfig;
import org.uniworks.groupware.common.config.BoneCPConfig;
import org.uniworks.groupware.domain.Nw100m;

/**
 * @author Park Chungwan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class, BoneCPConfig.class})
@Transactional
public class Nw100mServiceTest {
	Logger logger = LoggerFactory.getLogger(Nw100mServiceTest.class);
	
	@Autowired
	Nw100mService nw100mService;
	private Nw100m nw100m;
	
	@Before
	public void setUp() {
		setUser();
	}
	
	@Test
	public void getNw100mListTest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", "A001");
		List<Nw100m> nw100mList = nw100mService.getNw100mList(map);
		assertTrue(nw100mList.size() > 0);
	}
	
	@Test
	public void getNw100mTest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", "A001");
		map.put("userId", "00000001");
		Nw100m nw100m = nw100mService.getNw100m(map);
		assertTrue(nw100m.getUserId().equals("00000001"));
	}
	
	@Test
	@Rollback(true)
	public void addNw100mTest() {
		int row = nw100mService.addNw100m(nw100m);
		assertTrue(row == 1);
	}
		
	private void setUser() {
		Calendar dt = Calendar.getInstance();
		Date crntDT = dt.getTime();
		
		this.nw100m = new Nw100m();
		nw100m.setCoId("A001");
		nw100m.setUserId("00000002");
		nw100m.setPswd("sksahffk");
		nw100m.setPswdChngDate(new Timestamp(crntDT.getTime()));
		nw100m.setPswdInitIndc("N");
		nw100m.setInternalMailAddr("linuxwan@naver.com");
		nw100m.setOutsideMailAddr("linuxwan@naver.com");
		nw100m.setCnfmPswd("sksahffk");
		nw100m.setCnfmPswdChngDate(new Timestamp(crntDT.getTime()));
		nw100m.setUserRgsrDate(new Timestamp(crntDT.getTime()));
	}
}

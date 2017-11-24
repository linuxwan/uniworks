/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.uniworks.groupware.common.config.AppConfig;
import org.uniworks.groupware.common.config.BoneCPConfig;
import org.uniworks.groupware.domain.Nw100m;

/**
 * @author Park Chungwan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class, BoneCPConfig.class})
public class Nw100mMapperTest {
	Logger logger = LoggerFactory.getLogger(Nw100mMapperTest.class); 
	
	@Autowired
	Nw100mMapper nw100mMapper;
	private Nw100m nw100m;
	
	@Before
	public void setUp() {
		setUser();
	}	
	
	@Test
	public void testUserMapper() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", "A001");
		map.put("userId", "00000001");
		
		List<Nw100m> users = nw100mMapper.select(map);
		assertTrue(users.size() > 0);
		
		nw100mMapper.insert(nw100m);
		
		users = nw100mMapper.select(map);
		assertTrue(users.size() > 0);
		
		Nw100m userInfo = nw100mMapper.selectByPrimaryKey(map);
		assertEquals(userInfo.getUserId(), "00000001");
		
		userInfo.setInternalMailAddr("chungwan.park@gmail.com");
		nw100mMapper.updateByPrimaryKey(userInfo);
		assertEquals(userInfo.getInternalMailAddr(), "chungwan.park@gmail.com");
		
		map.put("userId", "00000099");
		nw100mMapper.deleteByPrimaryKey(map);
		
		userInfo = nw100mMapper.selectByPrimaryKey(map);
		assertNull(userInfo);
	}
	
	private void setUser() {
		Calendar dt = Calendar.getInstance();
		Date crntDT = dt.getTime();
		
		this.nw100m = new Nw100m();
		nw100m.setCoId("A001");
		nw100m.setUserId("00000099");
		nw100m.setPswd("sksahffk");
		nw100m.setPswdChngDate(new Timestamp(crntDT.getTime()));
		nw100m.setPswdInitIndc("N");
		nw100m.setInternalMailAddr("linuxwan@naver.com");
		nw100m.setOutsideMailAddr("linuxwan@naver.com");
		nw100m.setCnfmPswd("sksahffk");
		nw100m.setCnfmPswdChngDate(new Timestamp(crntDT.getTime()));
		nw100m.setUserRgsrDate(new Timestamp(crntDT.getTime()));
		nw100m.setUseIndc("Y");
	}
}

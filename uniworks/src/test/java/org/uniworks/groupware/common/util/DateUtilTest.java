/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.common.util;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.uniworks.groupware.common.config.AppConfig;
import org.uniworks.groupware.common.config.BoneCPConfig;

/**
 * @author Park Chungwan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class, BoneCPConfig.class})
@Transactional
public class DateUtilTest {
	Logger logger = LoggerFactory.getLogger(DateUtilTest.class);
	
	@Test
	public void getCurrentDateTimeConvertToTimestampTest() {
		DateUtil dt = new DateUtil();
		Timestamp ts = dt.getCurrentDateTimeConvertToTimestamp();
		logger.debug("Timestamp ts : " + ts.toString());		
	}
}

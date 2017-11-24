/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.uniworks.groupware.common.config.AppConfig;
import org.uniworks.groupware.common.config.BoneCPConfig;
import org.uniworks.groupware.domain.CommonCode;

/**
 * @author Park Chungwan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class, BoneCPConfig.class})
public class CommonMapperTest {
	Logger logger = LoggerFactory.getLogger(CommonMapperTest.class); 
	@Autowired CommonMapper cmmMapper;
	
	@Test
	public void testGetByCommonSubCodeList() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("majCode", "CD004");
		map.put("lang", "ko");
		map.put("orderBy", "rescKey");
		List<CommonCode> cmmCodeList = cmmMapper.selectByCommonSubCodeList(map);
		
		for (int i = 0; i < cmmCodeList.size(); i++) {
			CommonCode cmmCode = cmmCodeList.get(i);
			logger.debug(cmmCode.getMajCode() + ":" + cmmCode.getSubCode() + ":" + cmmCode.getRescKeyValue());
		}
	}
}

/**
 * 박충완(gomoosin)이 작성한 코드 입니다.
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
import org.uniworks.groupware.domain.Nw112m;

/**
 * @author gomoosin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class, BoneCPConfig.class})
public class ApprovalMapperTest {
	Logger logger = LoggerFactory.getLogger(ApprovalMapperTest.class);
	@Autowired ApprovalMapper apprMapper;
	
	@Test
	public void selectCprtnApprovalPersonInfoTest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("coId", "A001");
		map.put("cntnId", "CNTN000005");
		map.put("dcmtRgsrNo", "201705051493953995815A7");
		map.put("apprDgr", 3);
		
		List<Nw112m> list = apprMapper.selectCprtnApprovalPersonInfo(map);
		logger.debug("list size: " + list.size());
		
		logger.debug(list.toString());
	}
}

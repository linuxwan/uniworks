/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

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
import org.springframework.transaction.annotation.Transactional;
import org.uniworks.groupware.common.UserSession;
import org.uniworks.groupware.common.config.AppConfig;
import org.uniworks.groupware.common.config.BoneCPConfig;
import org.uniworks.groupware.domain.approval.ApprovalDoc;
import org.uniworks.groupware.domain.approval.LineApprover;

/**
 * @author Park Chungwan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class, BoneCPConfig.class})
@Transactional	//실제 데이터 insert, update, delete를 수행하지 않는다.
public class ApprovalServiceTest {
	private static Logger logger = LoggerFactory.getLogger(ApprovalServiceTest.class);
	@Autowired ApprovalService apprService;
	private Map<String, Object> map;
	
	@Before
	public void setUpBefore() {
		map = new HashMap<String, Object>();	
		map.put("coId", "A001");
		map.put("cntnId", "CNTN000006");
		map.put("dcmtRgsrNo", "201606181466213886343A4");
		map.put("lang", "ko");
	}
	
	//결재상태 변경 테스트.
	@Test
	public void changeLineApproverStatusTest() {
		String coId = "A001";
		String cntnId = "CNTN000006";
		String dcmtRgsrNo = "201606181466213886343A4";
		String lang = "ko";
		String userId = "00000009";
		String steadApprIndc = "N";
		String apprStus = "7";
		String comment = "";
				
		apprService.changeLineApproverStatus(coId, cntnId, dcmtRgsrNo, lang, userId, steadApprIndc, apprStus, comment);		
		map.put("apprEmpNo", "00000009");
		map.put("apprDgr", 1);
		List<LineApprover> list = apprService.getLineApproverNw111m(map);
		LineApprover lineAppr = list.get(0);
		assertThat(lineAppr.getApprStus(), is("7"));
		ApprovalDoc apprDoc = apprService.getByApprovalDocNw110m(map);
		assertThat(apprDoc.getCrntApprDgr(), is(2));		
	}
	
	@Test
	public void checkApprovalDocModifyAuthrityTest() {
		String coId = "A001";
		String cntnId = "CNTN000006";
		String dcmtRgsrNo = "201606181466213886343A4";
		String lang = "ko";
		String userId = "00000001";
		UserSession userSession = new UserSession();
		userSession.setLanguage(lang);
		userSession.setUserId(userId);
		
		boolean chk = apprService.checkApprovalDocModifyAuthrity(coId, cntnId, dcmtRgsrNo, userSession);
		assertFalse(chk);
		userId = "00000009";
		chk = apprService.checkApprovalDocModifyAuthrity(coId, cntnId, dcmtRgsrNo, userSession);
		assertTrue(chk);
	}
}

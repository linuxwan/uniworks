/**
 * 박충완(Park Chungwan)이 작성한 코드 입니다.
 * Uniworks라는 개인적 프로젝트를 완성하기 위해서 작성 중 입니다.
 * 이 소스의 코드를 사용하실 경우에는 꼭 출처를 명시해 주시기 바랍니다.
 */
package org.uniworks.groupware.common.util.approval;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.uniworks.groupware.common.UserSession;
import org.uniworks.groupware.common.config.AppConfig;
import org.uniworks.groupware.common.config.BoneCPConfig;
import org.uniworks.groupware.domain.Nw113m;
import org.uniworks.groupware.domain.Nw114m;

/**
 * @author Park Chungwan
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class, BoneCPConfig.class})
@Transactional
public class ApprovalCommonUtilTest {
	Logger logger = LoggerFactory.getLogger(ApprovalCommonUtilTest.class);
	private MockHttpServletRequest request = new MockHttpServletRequest();
	
	@Before
	public void setUpBefore() {
		UserSession userSession = new UserSession();
		userSession.setCoId("A001");
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(24 * 60 * 60);
		session.setAttribute("userSession", userSession);
		
		request.setParameter("cntnId", "CNTN000003");
		request.setParameter("apprMstId", "APPR0001");
		request.setParameter("selRcpt", "O:B002, O:B001, O:B003");
		request.setParameter("selRcptDesc", "개발부문장, 관리부문장, 영업부문장");
		
		request.setParameter("selRfnc", "O:B003,  N:00000002");
		request.setParameter("selRfncDesc", "영업부문장,  홍길동 부사장 (관리부문)");
	}
	
	@Test
	public void setRcptDestListTest() {
		String dcmtRgsrNo = "1234567890";
		List<Nw113m> rcptDestList = ApprovalCommonUtil.setRcptDestList(request, dcmtRgsrNo);
		assertThat(rcptDestList.size(), is(3));
		
		Nw113m rcptDest = rcptDestList.get(0);
		assertThat(rcptDest.getRcptOganGrpCode(), is("B002"));
		assertThat(rcptDest.getRcptOganGrpName(), is("개발부문장"));
		assertThat(rcptDest.getSeqNo(), is(1));
		
		rcptDest = rcptDestList.get(1);
		assertThat(rcptDest.getRcptOganGrpCode(), is("B001"));
		assertThat(rcptDest.getRcptOganGrpName(), is("관리부문장"));
		assertThat(rcptDest.getSeqNo(), is(2));
		
		rcptDest = rcptDestList.get(2);
		assertThat(rcptDest.getRcptOganGrpCode(), is("B003"));
		assertThat(rcptDest.getRcptOganGrpName(), is("영업부문장"));
		assertThat(rcptDest.getSeqNo(), is(3));
	}
	
	@Test
	public void setRfncDestListTest() {
		String dcmtRgsrNo = "1234567890";
		List<Nw114m> rfncDestList = ApprovalCommonUtil.setRfncDestList(request, dcmtRgsrNo);
		assertThat(rfncDestList.size(), is(2));
		
		Nw114m rfncDest = rfncDestList.get(0);
		assertThat(rfncDest.getRfncType(), is("O"));
		assertThat(rfncDest.getRfncCode(), is("B003"));
		assertThat(rfncDest.getRfncCodeName(), is("영업부문장"));
		
		rfncDest = rfncDestList.get(1);
		assertThat(rfncDest.getRfncType(), is("N"));
		assertThat(rfncDest.getRfncCode(), is("00000002"));
		assertThat(rfncDest.getRfncCodeName(), is("홍길동 부사장 (관리부문)"));
	}
}

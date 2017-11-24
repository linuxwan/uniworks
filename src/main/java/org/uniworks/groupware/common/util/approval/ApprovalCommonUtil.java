/**
 * 박충완이 작성한 소스입니다.
 * 이 소스는 모든 개발자들이 자유롭게 수정/배포할 수 있습니다.
 * 단, 이 소스를 근간으로 애플리케이션을 개발하실 때에는 꼭 출처를 명시해 주세요.
 */
package org.uniworks.groupware.common.util.approval;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.util.WebUtils;
import org.uniworks.groupware.common.UserSession;
import org.uniworks.groupware.common.util.StringUtil;
import org.uniworks.groupware.domain.HumanResource;
import org.uniworks.groupware.domain.Nw112m;
import org.uniworks.groupware.domain.Nw113m;
import org.uniworks.groupware.domain.Nw114m;
import org.uniworks.groupware.domain.approval.LineApprover;
import org.uniworks.groupware.service.HumanResourceService;

/**
 * @author Chungwan Park
 * ApprovalCommonUtil.java 2014. 10. 26.
 */
public class ApprovalCommonUtil {
	protected final static Log logger = LogFactory.getLog(ApprovalCommonUtil.class);
	
	/**
	 * 선택한 라인결재자 정보를 받아서 LineApprover 객체에 값을 할당한 후 ArrayList에 추가 후 반환.
	 * @param request
	 * @return
	 */
	public static List<LineApprover> setLineApproverList(HttpServletRequest request, HumanResourceService hrService, String dcmtRgsrNo) {
		ArrayList<LineApprover> arrList = new ArrayList<LineApprover>();
		//Session정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		int apprLev = Integer.parseInt(StringUtil.null2zeroString(request.getParameter("lineApprLevel")));
		String cntnId = request.getParameter("cntnId");
		
		for(int i = 1; i <= apprLev; i++) {
			String empNo = StringUtil.null2void(request.getParameter("apprLine_" + String.valueOf(i)));
			if (!empNo.equals("")) {
				//문서등록번호(dcmtRgsrNo)의 경우에는 서비스단에서 생성해야 함.
				LineApprover line = new LineApprover();
				line.setApprEmpNo(empNo.substring(empNo.indexOf(':') + 1));
				line.setCntnId(cntnId);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("coId", userSession.getCoId());
				map.put("empNo", line.getApprEmpNo());
				map.put("lang", userSession.getLanguage());
				map.put("workIndc", "1");
				map.put("baseOganLev", userSession.getBaseOganLev());
				
				HumanResource hr = hrService.getEmployeeInfoByEmpNo(map);
				line.setApprDeptCode(hr.getDeptCode());
				line.setApprDeptDesc(hr.getDeptDesc());
				line.setApprDgr(i);
				line.setDutyDesc(hr.getDutyDesc());
				line.setPstnDesc(hr.getPstnDesc());
				line.setCntnId(cntnId);
				line.setCoId(userSession.getCoId());
				line.setDcmtRgsrNo(dcmtRgsrNo);
				line.setApprStus("0");
				line.setSteadApprIndc("N");
				
				arrList.add(line);
			}
		}
		
		if (arrList.size() < 1) arrList = null;
		return arrList;
	}
	
	/**
	 * 파라미터 값으로 받아온 협조처 정보(협조처 조직코드, 조직명칭 - selCprtn, selCprtnDesc)를 Nw112m 클래스에 값을 설정해서 ArrayList에 Add.
	 * @param request
	 * @param dcmtRgsrNo
	 * @return
	 */
	public static List<Nw112m> setCprtApproverList(HttpServletRequest request, String dcmtRgsrNo) {
		List<Nw112m> cprtnApprList = new ArrayList<Nw112m>();
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String cntnId = request.getParameter("cntnId");
		String selCprtn = request.getParameter("selCprtn");	//협조처 조직코드 - [1:조직코드1, 2:조직코드2, 3:조직코드3]의 형식으로 구성. 앞의 숫자는 라인 결재차수를 말함.
		String selCprtnDesc = request.getParameter("selCprtnDesc");	//협조처 조직명 - [조직명1, 조직명2, 조직명3]의 형식으로 구성.
		
		if (selCprtn != null && selCprtn.length() > 0) {
			String[] selCprtnList = StringUtil.split2Array(selCprtn, ",");
			String[] selCprtnDescList = StringUtil.split2Array(selCprtnDesc, ",");
			int dgr = 0;
			for (int i = 0; i < selCprtnList.length; i++) {
				int beginIndex = selCprtnList[i].indexOf(":");
				int apprDgr = Integer.parseInt(selCprtnList[i].substring(0, beginIndex));
				String cprtnOganGrpCode = selCprtnList[i].substring(beginIndex + 1);
				String cprtnOganGrpName = selCprtnDescList[i].substring(beginIndex + 1);
				Nw112m cprtnDest = new Nw112m();
				cprtnDest.setCoId(userSession.getCoId());
				cprtnDest.setCntnId(cntnId);
				cprtnDest.setDcmtRgsrNo(dcmtRgsrNo);
				cprtnDest.setSeqNo(i + 1);
				cprtnDest.setDeptCode(cprtnOganGrpCode);
				cprtnDest.setDeptDesc(cprtnOganGrpName);
				cprtnDest.setPstnDesc(cprtnOganGrpName);
				cprtnDest.setApprDgr(apprDgr);	//Line결재 차수
				//결재상태를 설정. 맨 처음 협조결재를 진행해야 하는 경우 결재 상태를 1(대기)로 변경. 그렇지 않은 경우에는 0으로 설정
				if (i == 0) {
					cprtnDest.setApprStus("1");
					dgr = cprtnDest.getApprDgr();
				} else {
					if (dgr == cprtnDest.getApprDgr()) {
						cprtnDest.setApprStus("1");
					} else {
						cprtnDest.setApprStus("0");
					}
				}
				cprtnDest.setSteadApprIndc("N");
				cprtnApprList.add(cprtnDest);
			}
		} else {
			cprtnApprList = null;
		}
		
		return cprtnApprList;
	}
	
	/**
	 * 파라미터 값으로 받아온 수신처 정보(수신처 조직코드, 조직명칭 - selRcpt, selRcptDesc)를 Nw113m 클래스에 값을 설정해서 ArrayList에 Add.
	 * @param request
	 * @param dcmtRgsrNo
	 * @return
	 */
	public static List<Nw113m> setRcptDestList(HttpServletRequest request, String dcmtRgsrNo) {
		List<Nw113m> rcptDestList = new ArrayList<Nw113m>();
		//Session정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String cntnId = request.getParameter("cntnId");
		String selRcpt = request.getParameter("selRcpt");	//수신처 조직코드 - [조직코드1, 조직코드2, 조직코드3]의 형식으로 구성.
		String selRcptDesc = request.getParameter("selRcptDesc");	//수신처 조직명 - [조직명1, 조직명2, 조직명3]의 형식으로 구성.
		
		if (selRcpt != null && selRcpt.length() > 0) {
			String[] selRcptList = StringUtil.split2Array(selRcpt, ",");	// ", "으로 구분.
			String[] selRcptDescList = StringUtil.split2Array(selRcptDesc, ",");
			
			for (int i = 0; i < selRcptList.length; i++) {
				int beginIndex = selRcptList[i].indexOf(":");	//수신처 조직코드임을 구분하는 O:에 대해서 :의 위치를 찾아온다.
				String rcptOganGrpCode = selRcptList[i].substring(beginIndex + 1);	//O: 다음의 조직코드를 가져온다.
				String rcptOganGrpName = selRcptDescList[i];
				Nw113m rcptDest = new Nw113m();
				rcptDest.setCoId(userSession.getCoId());	//회사구분 코드
				rcptDest.setCntnId(cntnId);	//컨텐츠ID
				rcptDest.setDcmtRgsrNo(dcmtRgsrNo);	//문서등록번호
				rcptDest.setSeqNo(i + 1);	//시퀀스번호
				rcptDest.setRcptOganGrpCode(rcptOganGrpCode);	//수신처 조직코드
				rcptDest.setRcptOganGrpName(rcptOganGrpName);	//수신처 조직명칭
				
				rcptDestList.add(rcptDest);
			}
		} else {
			rcptDestList = null;
		}
		
		return rcptDestList;
	}
	
	/**
	 * 파라미터 값으로 받아온 참조처 정보(참조처 코드, 참조처 명칭 - selRfnc, selRfncDesc)를 Nw114m 클래스에 값을 생성해서 ArrayList에 Add.
	 * 참조처 유형(rfncType)은 조직일 경우 O, 개인일 경우 N 값을 가진다.
	 * @param request
	 * @param dcmtRgsrNo
	 * @return
	 */
	public static List<Nw114m> setRfncDestList(HttpServletRequest request, String dcmtRgsrNo) {
		List<Nw114m> rfncDestList = new ArrayList<Nw114m>();
		//Session정보를 가져온다.
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String cntnId = request.getParameter("cntnId");
		String selRfnc = request.getParameter("selRfnc");	//참조처 코드 - [O:조직코드1, O:조직코드2, N:개인사번]의 형식으로 구성.
		String selRfncDesc = request.getParameter("selRfncDesc");	//참조처 명칭 - [조직명1, 조직명2, 개인성명 보직(조직명)]의 형식으로 구성.
		
		if (selRfnc != null && selRfnc.length() > 0) {
			String[] selRfncList = StringUtil.split2Array(selRfnc, ",");	//", "으로 구분
			String[] selRfncDescList = StringUtil.split2Array(selRfncDesc, ",");
			
			for (int i = 0; i < selRfncList.length; i++) {
				int beginIndex = selRfncList[i].indexOf(":");	//참조처의 조직코드일 경우 O, 개인일 경우 N으로 표기하고 :으로 구분
				String rfncType = selRfncList[i].substring(0, beginIndex);
				String rfncCode = selRfncList[i].substring(beginIndex + 1); //O: 또는 N: 다음의 코드값을 가져온다.
				String rfncCodeName = selRfncDescList[i];
				
				Nw114m rfncDest = new Nw114m();
				rfncDest.setCoId(userSession.getCoId());	//회사구분 코드
				rfncDest.setCntnId(cntnId);	//컨텐츠ID
				rfncDest.setDcmtRgsrNo(dcmtRgsrNo);	//문서등록번호 
				rfncDest.setSeqNo(i + 1);	//시퀀스번호
				rfncDest.setRfncType(rfncType);	//참조처 유형
				rfncDest.setRfncCode(rfncCode);	//참조처 코드
				rfncDest.setRfncCodeName(rfncCodeName);	//참조처 명칭
				
				rfncDestList.add(rfncDest);
			}
		} else {
			rfncDestList = null;
		}
		
		return rfncDestList;
	}
	
	/**
	 * 작성화면에서 넘어온 라인결재자 정보를 List에 저장. 저장만 또는 저장 후 닫기 시에 Validation 오류가 났을 경우에 라인결재자를
	 * Display하기 위한 용도임.
	 * @param request
	 * @return
	 */
	public static List<LineApprover> getParameterLineApproverList1(HttpServletRequest request) {
		ArrayList<LineApprover> arrList = new ArrayList<LineApprover>();
		int apprLev = Integer.parseInt(StringUtil.null2zeroString(request.getParameter("lineApprLevel")));
		
		//라인결재자 정보를 Request에서 추출해서 ArrayList<LineApprover>에 저장.
		for(int i = 1; i <= apprLev; i++) {
			String empNo = StringUtil.null2void(request.getParameter("apprLine_" + String.valueOf(i)));	//사번
			String desc = StringUtil.null2void(request.getParameter("apprLineDesc_" + String.valueOf(i)));	//이름. 보직명, 조직			
			LineApprover line = new LineApprover();
			line.setApprEmpNo(empNo);
			line.setApprDgr(i);
			line.setApprDeptDesc(desc);
			
			arrList.add(line);
		}
		return arrList;
	}	
}
